variable "cluster-name" {
  default = "terraform-eks-demo"
  type    = string
}

variable "public_subnet_list" {
  default = ["10.0.101.0/24", "10.0.102.0/24"]
  type    = list(string)
}

variable "private_subnet_list" {
  default = ["10.0.1.0/24", "10.0.2.0/24"]
  type    = list(string)
}

variable "region" {
  default = "us-east-1"
  type = string
}

variable "environment" {
  default = "DEV"
  type = string
}



