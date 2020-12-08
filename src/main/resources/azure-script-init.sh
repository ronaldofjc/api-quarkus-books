#!/bin/bash

# Pré-requisitos
# Conta no Portal Azure
# Realizar o login no client da azure (az login)

# Este script vai criar os seguintes recursos na azure
# 1 - Resource Group - Grupo de Recursos
# 2 - Registro de Container - ACR Container Registry
# 3 - Cluster Kubernetes - AKS Kubernetes Service
# 4 - Conectar o ACR com o AKS
# 5 - Conta do CosmosDB
# 6 - Banco de dados CosmosDB 

# Variáveis
resourceGroupName="rgbooks"
location="eastus"
accountName="accbooks"
serverVersion="3.6"
cosmosdbName="books"
acrName="acrbooks"
aksName="aksbooks"

echo "Iniciando script..."

echo "- Primeira etapa - Criando grupo de recursos"
rsgExists=$(az group exists -n $resourceGroupName)
if [ $rsgExists = "false" ]; then
    echo "início"
    az group create -l $location -n $resourceGroupName
    echo "fim"
else
    echo "Já existe um grupo de recursos com este nome na azure"
fi

echo "valida se o grupo de recursos foi criado"
rsgExists=$(az group exists -n $resourceGroupName)

echo "- Segunda etapa - Criando registro de container"
if [ $rsgExists = "true" ]; then
    acrExists=$(az acr check-name --name $acrName)
    if [[ $acrExists != *"AlreadyExists"* ]]; then
        echo "início"
        az acr create --resource-group $resourceGroupName --name $acrName --sku Basic
        echo "fim"
    else
        echo "Já existe um registro de container com este nome na azure"
    fi
else
    echo "Não existe um grupo de recursos com este nome na azure para criar o registro de container"
fi

echo "- Terceira etapa - Criando um cluster kubernetes"
if [ $rsgExists = "true" ]; then
    aksExists=$(az aks show --name $aksName --resource-group $resourceGroupName)
    if [[ $aksExists != *"Succeeded"* ]] || [[ $aksExists != *"Creating"* ]]; then
        echo "início"
        latestK8sVersion=$(az aks get-versions -l $location --query 'orchestrators[-1].orchestratorVersion' -o tsv)
        az aks create \
            --resource-group $resourceGroupName \
            --name $aksName \
            --node-count 1 \
            --enable-addons monitoring \
            --generate-ssh-keys \
            --kubernetes-version $latestK8sVersion
        echo "fim"
    else
        echo "Já existe um cluster kubernetes com este nome na azure"
    fi
else
    echo "Não existe um grupo de recursos com este nome na azure para criar o cluster kubernetes"
fi

echo "- Quarta etapa - Conectando ACR com o AKS"
clientId=$(az aks show -n $aksName -g $resourceGroupName --query "servicePrincipalProfile.clientId" -o tsv)
acrId=$(az acr show -n $acrName -g $resourceGroupName --query "id" -o tsv)
az role assignment create --assignee $clientId --role acrpull --scope $acrId

echo "- Quinta etapa - Criando conta do CosmosDB"
cosmosdbExists=$(az cosmosdb check-name-exists -n $accountName)
if [ $cosmosdbExists = "false" ]; then
    echo "início"
    az cosmosdb create \
        -n $accountName \
        -g $resourceGroupName \
        --kind MongoDB \
        --server-version $serverVersion \
        --default-consistency-level Eventual
    echo "fim"
else
    echo "Já existe uma conta do cosmos com este nome na azure"
fi

echo "- Sexta etapa - Criando banco de dados CosmosDB"
cosmosDatabaseExists=$(az cosmosdb mongodb database exists -a $accountName -n $cosmosdbName -g $resourceGroupName)
if [ $cosmosDatabaseExists = "false" ]; then
    echo "início"
    az cosmosdb mongodb database create \
        -a $accountName \
        -g $resourceGroupName \
        -n $cosmosdbName
    echo "fim"
else
    echo "Já existe um banco de dados com este nome na azure"
fi

echo "Término do script!"

# Remover os recursos na Azure
# az group delete --name $resourceGroupName --yes --no-wait
