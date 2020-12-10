# Location
variable "location" {
  default = "eastus"
}

# Resource Group
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

variable "dns_prefix" {
    default = "aks1"
}

variable "default_node_pool_name" {
    default = "default"
}

variable "node_count" {
    default = 1
}

variable "vm_size" {
    default = "Standard_B2s"
}

# Cosmos
variable "cosmos_account_name" {
  default = "accbooks"
}

variable "offer_type" {
  default = "Standard"
}

variable "cosmos_db_name" {
  default = "books"
}

variable "cosmos_db_throughput" {
  default = 400
}

variable "consistency_level" {
  default = "BoundedStaleness"
}

variable "max_interval_in_seconds" {
  default = 10
}

variable "max_staleness_prefix" {
  default = 200
}

variable "failover_priority" {
  default = 0
}



