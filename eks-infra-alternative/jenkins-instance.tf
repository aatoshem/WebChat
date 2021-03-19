# Get AMI
data "aws_ami" "amazon-linux-2" {
  most_recent = true
  owners = ["amazon"]
  filter {
    name = "name"
    values = [
      "amzn2-ami-hvm-*-x86_64-gp2",
    ]
  }
  filter {
    name = "owner-alias"
    values = [
      "amazon",
    ]
  }
}

# Creates Jenkins Instance
resource "aws_instance" "jenkins-instance" {
  ami             =  data.aws_ami.amazon-linux-2.id
  instance_type   = "t2.medium"
  /* key_name        = "${var.keyname}" */
  /* vpc_id          = "${module.vpc.vpc_id}" */
  vpc_security_group_ids = [aws_security_group.sg_allow_ssh_jenkins.id]
  subnet_id          = aws_subnet.public-subnet-1.id
  user_data = file("install_jenkins.sh")

  associate_public_ip_address = true
  tags = {
    Name = "Jenkins-Instance"
  }
}

# Creates a Security group for Jenkins
resource "aws_security_group" "sg_allow_ssh_jenkins" {
  name        = "allow_ssh_jenkins"
  description = "Allow SSH and Jenkins inbound traffic"
  vpc_id      = module.vpc.vpc_id

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port       = 0
    to_port         = 0
    protocol        = "-1"
    cidr_blocks     = ["0.0.0.0/0"]
  }
}

# Returns the Elastic IP Address assigned to Jenkins
output "jenkins_ip_address" {
  value = aws_eip.jenkins-eip.public_ip
}

# Creates an Application Load Balancer for Jenkins
resource "aws_lb" "jenkins-lb" {
  name               = "test-lb-tf"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.sg_allow_ssh_jenkins.id]
  subnets            = [aws_subnet.public-subnet-1.id, aws_subnet.public-subnet-2.id]

  enable_deletion_protection = true

  tags = {
    Environment = "DEV"
  }
}

resource "aws_lb_target_group" "test" {
  name     = "Jekins-TargetGroup"
  port     = 80
  protocol = "HTTP"
  vpc_id   = module.vpc.vpc_id
}

resource "aws_lb_target_group_attachment" "test" {
  target_group_arn = aws_lb_target_group.test.arn
  target_id        = aws_instance.jenkins-instance.id
  port             = 80
}

#Creates a subnet for Jenkins
resource "aws_subnet" "public-subnet-2" {
  cidr_block        = "10.0.104.0/24"
  vpc_id            = module.vpc.vpc_id
  availability_zone = "${var.region}b"
  tags = {
    Name = "${var.environment}-Public-Subnet-2"
  }
}

#Creates a subnet for Jenkins
resource "aws_subnet" "public-subnet-1" {
  cidr_block        = "10.0.103.0/24"
  vpc_id            = module.vpc.vpc_id
  availability_zone = "${var.region}a"
  tags = {
    Name = "${var.environment}-Public-Subnet-1"
  }
}

# Creates an Elastic IP Address for Jenkins
resource "aws_eip" "jenkins-eip" {
  instance = aws_instance.jenkins-instance.id
  vpc      = true
}

# Creates Route Table
resource "aws_route_table" "public-route-table" {
  vpc_id = module.vpc.vpc_id
  tags = {
    Name = "Jenkins-Public-RouteTable"
  }
}

# Route Table Association with Subnet
resource "aws_route_table_association" "public-route-1-association" {
  route_table_id = aws_route_table.public-route-table.id
  subnet_id      = aws_subnet.public-subnet-1.id
}

resource "aws_route" "public-internet-igw-route" {
  route_table_id         = aws_route_table.public-route-table.id
  gateway_id             = module.vpc.igw_id
  destination_cidr_block = "0.0.0.0/0"
}

