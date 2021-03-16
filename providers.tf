terraform {
  required_providers {
    http = {
      source = "hashicorp/http"
      version = "2.0.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
}

data "aws_region" "current" {}

data "aws_availability_zones" "available" {
  state = "available"
}

provider "http" {}