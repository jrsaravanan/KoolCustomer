## KoolCustomer 

AWS Infrastucture creation script.

It create VPC and one public subnet and one private subnet. Add Security Group to access the public subnet.
default region set to be ap-south-1. Key is not generated , create key "cloudpratice" or change it to your AWS key. 
To Configure terraform : https://www.terraform.io/docs/providers/aws/

## To Run
``` sh

#Initalize - download provider and modules
terraform init

#Plan - View the changes
terraform plan

#Apply - Execute and Provision the resources
terraform apply

#Destroy - delete once after the testing
terraform destroy


``` 
