resource "aws_eks_cluster" "demo" {
    name = "${var.cluster-name}"
    role_arn = "${aws_iam_role.demo.arn}"

    vpc_config {
        security_group_ids = ["${aws_security_group.demo-cluster.id}"]
        subnet_ids = ["${module.vpc.public_subnets[0]}", "${module.vpc.public_subnets[1]}", "${module.vpc.public_subnets[2]}"]
    }

    depends_on = [
        "aws_iam_role_policy_attachment.demo-AmazonEKSClusterPolicy",
        "aws_iam_role_policy_attachment.demo-AmazonEKSVPCResourceController"
    ]


}