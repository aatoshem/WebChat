# AWS infrastructure setup
## Set up your local machine to access your EKS cluster

1. Download Kubectl from ``` https://v1-18.docs.kubernetes.io/docs/tasks/tools/install-kubectl/ ```

2. Downlaod "AWS IAM Authenticator" from 
``` https://docs.aws.amazon.com/eks/latest/userguide/install-aws-iam-authenticator.html ```
## Steps to provision infrastructure

1. Download required terraform modules by running:
 ``` terraform init ```
2. To create infrasture, run:  
 ``` terraform apply ```

 3. Configure Kubectl
  ```
  terraform output kubeconfig > ~/.kube/config
  ```

  4. Configure config-map-auth-aws
  ```
  terraform output config-map-aws-auth > config-map-aws-auth.yaml

  kubectl apply -f config-map-aws-auth.yaml
  ```

## Tear down infrastructure

``` terraform destroy ```