#!/bin/bash
sudo yum -y update

echo "Install Java JDK 11"
yum remove -y java
yum install -y java-1.11.*-openjdk