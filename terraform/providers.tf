terraform {
  required_providers {
    azurerm = {
      source = "hashicorp/azurerm"
      version = ">= 2.39"
    }
  }
}

provider "azurerm" {
  features {}
}