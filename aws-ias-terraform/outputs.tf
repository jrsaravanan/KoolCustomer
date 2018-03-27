# VPC
output "vpc_id" {
  description = "The ID of the VPC"
  value       = "${module.vpc.vpc_id}"
}

# Subnets
output "private_subnets" {
  description = "List of IDs of private subnets"
  value       = ["${module.vpc.private_subnets}"]
}

output "public_subnets" {
  description = "List of IDs of public subnets"
  value       = ["${module.vpc.public_subnets}"]
}

output "http_sg_id" {
  description = "List of IDs of public subnets"
  value       = ["${module.http_sg.this_security_group_id}"]
}

output "ssh_sg_id" {
  description = "List of IDs of public subnets"
  value       = ["${module.ssh_server_sg.this_security_group_id}"]
}
