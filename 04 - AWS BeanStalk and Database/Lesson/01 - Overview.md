# AWS BeanStalk Overview

## Components

Amazon Web Services (AWS) is a large collection of managed services. _Managed_ means that AWS handles the install and maintenance of software for you and configuration to various degrees. The intent is to free your time to build software to support your business.

We'll summarize a few services necessary to run your shopping cart application and make it available to the public. For a real production application there are more configurations you'd want to make to handle scaling for increased load and security that is beyond our scope.

### EC2

Elastic Compute Cloud (EC2) is a (small?) collection of services to support compute, i.e. application code. You can think of this as virtual machines (VM), disk and networking. A virtual machine looks like a "real" computer but exists in software instead of individual hardware. The hardware running VMs is generally large and runs many VMs at the same time.

In EC2, an "instance" looks like a single computer. We'll use one instance for the example but in production you'd want two or more.

### Network

Networking is a complicated topic. We'll keep to what we need for this example.

#### VPC

The starting point for AWS networking is a Virtual Private Cloud (VPC). This is your own network space inside Amazon's cloud that is separate from everyone else. This is done to reduce complexity and increase security. We will deploy the web application into a default VPC, one that AWS creates as part of your account.

#### AZ

AWS provides Availability Zones (AZ). The intent is to provide fault tolerance for hardware failures. When you run your services across multiple AZs and configure properly, when one AZ goes down your application keeps running. AZs are collected into regions, which is a geographic area, such as "US East 1", "US West 1", ... The AZs are close to each other but far enough apart that a physical tragedy should not affect multiple AZs.

### RDS (Database)

Relational Database Service (RDS) is Amazon's managed service for databases like MySQL, PostgresQL, etc. It's a great service to use because the data is usually the most valuable asset to a business and AWS provides several features to prevent data loss. Managing databases can be difficult. AWS manages your database across AZs, can take periodic backups while the database is running, perform restores, etc. All of this can be configured and run using the user interface.

## Reference

* [Spring Boot on AWS BeanStalk](https://aws.amazon.com/blogs/devops/deploying-a-spring-boot-application-on-aws-using-aws-elastic-beanstalk/)
