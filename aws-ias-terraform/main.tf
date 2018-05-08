# Specify the provider and access details
provider "aws" {
  region = "${var.aws_region}"
}

module "vpc" {
  source = "terraform-aws-modules/vpc/aws"
  name   = "vpc-dev-env"
  cidr   = "10.0.0.0/16"

  azs             = ["ap-south-1a", "ap-south-1b"]
  private_subnets = ["10.0.1.0/24"]
  public_subnets  = ["10.0.101.0/24"]

  enable_nat_gateway = true
  single_nat_gateway = true

  tags = {
    Owner       = "user"
    Environment = "dev"
  }
}

module "http_sg" {
  source = "terraform-aws-modules/security-group/aws//modules/http-80"

  name                = "http-sg"
  description         = "Security group with HTTP ports open for everybody (IPv4 CIDR), egress ports are all world open"
  vpc_id              = "${module.vpc.vpc_id}"
  ingress_cidr_blocks = ["0.0.0.0/0"]
}

module "ssh_server_sg" {
  source              = "terraform-aws-modules/security-group/aws//modules/ssh"
  name                = "ssh-wide-open-sg"
  vpc_id              = "${module.vpc.vpc_id}"
  ingress_cidr_blocks = ["0.0.0.0/0"]
}

module "tcp_sg" {
  source  = "terraform-aws-modules/security-group/aws"
  name                = "tcp-sg"
  description         = "Security group with TCP ports"
  vpc_id              = "${module.vpc.vpc_id}"
  
   ingress_with_cidr_blocks = [
    {
      from_port   = 8080
      to_port     = 8080
      protocol    = "tcp"
      description = "User-service ports (ipv4)"
      cidr_blocks = "0.0.0.0/0"
    },
  ]

}

resource "aws_iam_role" "role" {
  name = "CustomerS3AdminUser"

  assume_role_policy = <<EOF
{
	"Version": "2012-10-17",
	"Statement": [{
		"Action": "sts:AssumeRole",
		"Principal": {
			"Service": "ec2.amazonaws.com"
		},
		"Effect": "Allow",
		"Sid": ""
	}]
}
EOF
}

resource "aws_iam_role_policy_attachment" "test-attach" {
  role       = "${aws_iam_role.role.name}"
  policy_arn = "arn:aws:iam::aws:policy/AmazonS3FullAccess"
}

resource "aws_iam_instance_profile" "profile" {
  role = "${aws_iam_role.role.name}"
}

resource "aws_instance" "web" {
  ami                  = "ami-08c54abff5220cc66"
  instance_type        = "t2.micro"
  subnet_id            = "${element(module.vpc.public_subnets, 0)}"
  security_groups      = ["${module.ssh_server_sg.this_security_group_id}", "${module.http_sg.this_security_group_id}" , "${module.tcp_sg.this_security_group_id}"]
  key_name             = "cloudpratice"
  iam_instance_profile = "${aws_iam_instance_profile.profile.name}"

  tags {
    Name = "customer-dev-web"
  }
}

