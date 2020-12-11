# LOCATION
variable "location" {
  default = "eastus"
}

# RESOURCE GROUP
variable "resource_group_name" {
  default = "rg-books"
}

# ACR
variable "acr_name" {
  default = "acrbooks"
}

variable "acr_sku" {
  default = "Basic"
}

# AKS
variable "aks_cluster_name" {
  description = "Name used for both AKS Cluster and DNS Prefix"
  default     = "aksbooks"
}

variable "aks_dns_prefix" {
    default = "aks1"
}

variable "aks_default_node_pool_name" {
    default = "default"
}

variable "aks_node_count" {
    default = 1
}

variable "aks_vm_size" {
    default = "Standard_B2s"
}

variable "aks_identity_type" {
    default = "SystemAssigned"
}

# COSMOS
variable "cosmos_account_name" {
  default = "accbooks"
}

variable "cosmos_offer_type" {
  default = "Standard"
}

variable "cosmos_db_name" {
  default = "books"
}

variable "cosmos_db_throughput" {
  default = 400
}

variable "cosmos_consistency_level" {
  default = "BoundedStaleness"
}

variable "cosmos_max_interval_in_seconds" {
  default = 10
}

variable "cosmos_max_staleness_prefix" {
  default = 200
}

variable "cosmos_failover_priority" {
  default = 0
}

variable "cosmos_account_kind" {
  default = "MongoDB"
}

variable "cosmos_account_capabilities_name" {
  default = "EnableMongo"
}
