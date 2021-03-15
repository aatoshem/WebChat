variable "region" {
  default     = "us-east-1"
  description = "AWS region"
}

variable "cluster_name" {
  default = "aws-for-mini-project"
}

# variable "map_accounts" {
#   description = "Additional AWS account numbers to add to the aws-auth configmap."
#   type        = list(string)

#   default = [
#     "777777777777",
#     "888888888888",
#   ]
# }

# variable "map_roles" {
#   description = "Additional IAM roles to add to the aws-auth configmap."
#   type = list(object({
#     rolearn  = string
#     username = string
#     groups   = list(string)
#   }))

#   default = [
#     {
#       rolearn  = "arn:aws:iam::66666666666:role/role1"
#       username = "role1"
#       groups   = ["system:masters"]
#     },
#   ]
# }