# Resource group
resource "azurerm_resource_group" "rg" {
  name     = var.resource_group_name
  location = var.location
}

# Cosmos Account
resource "azurerm_cosmosdb_account" "cosmos_account" {
  name                = var.cosmos_account_name
  location            = var.location
  resource_group_name = var.resource_group_name
  offer_type          = var.cosmos_offer_type
  kind = var.cosmos_account_kind

  capabilities {
    name = var.cosmos_account_capabilities_name
  }

  consistency_policy {
    consistency_level       = var.cosmos_consistency_level
    max_interval_in_seconds = var.cosmos_max_interval_in_seconds
    max_staleness_prefix    = var.cosmos_max_staleness_prefix
  }

  geo_location {
    location          = var.location
    failover_priority = var.cosmos_failover_priority
  }
}

# Cosmos DB
resource "azurerm_cosmosdb_mongo_database" "cosmos_db" {
  name                = var.cosmos_db_name
  resource_group_name = var.resource_group_name
  account_name        = var.cosmos_account_name
  throughput          = var.cosmos_db_throughput
}

# ACR
resource "azurerm_container_registry" "acr" {
  name                = var.acr_name
  resource_group_name = var.resource_group_name
  location            = var.location
  sku                 = var.acr_sku
}

# AKS
resource "azurerm_kubernetes_cluster" "aks" {
  name                = var.aks_cluster_name
  location            = var.location
  resource_group_name = var.resource_group_name
  dns_prefix          = var.aks_dns_prefix
  
  default_node_pool {
    name       = var.aks_default_node_pool_name
    node_count = var.aks_node_count
    vm_size    = var.aks_vm_size
  }

  identity {
    type = var.aks_identity_type
  }
}