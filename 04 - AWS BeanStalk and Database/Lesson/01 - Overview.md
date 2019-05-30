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

## Creating an Application

Build a `.jar` file for your application by running the `bootJar` Gradle task in IntelliJ. It will be built in the `build/libs` directory in your project.

1. Visit <https://console.aws.amazon.com>
2. In "Find Services" type "beanstalk" and click it
3. In the top right click "Create New Application"
4. Fill in "Application Name" as "shopping-cart"
5. Click Create
6. Click "Actions > Create environment"
7. Select "Web server environment" and click "Select"
8. Environment name is intending to be "test", "production", etc., but AWS doesn't care, it's up to you.
9. Enter a domain if you'd like, the generated one will work too.
10. Select "Platform > Preconfigured platform" and pick "Java" from the drop down.
11. Select "Application code > Upload your code".
12. Click "Upload" and choose your `.jar` file in `build/libs`.
13. Click "Configure more options"
14. Under "Database", click "Modify"
    1. Leave "Engine" at "mysql"
    2. Change "Engine version" to the lastest of "8.0.x", today it's "8.0.15"
    3. Enter a "username" for database access, don't re-use another username you have.
    4. Check "Password" and enter a password, don't re-use a password!
    5. Leave "Retention" at "Create snapshot"
    6. Leave "Availability" at "Low (one AZ)"
    7. Click "Save"
15. Under "Network", click "Modify"
    1. Leave "VPC" at "... (default)"
    2. Check "Assign a public IP"
    3. Check two AZs, `us-east-1a`, `us-east-1b`
    4. Click "Save"
16. Click "Create environment"

The database will not work because we don't know the address of the database host. Wait for everything to be created then modify the environment for the database. BeanStalk will restart the application.

1. Click "Configuration on the left
2. Under "Software" click "Modify"
3. Modify the "Environment properties" to add the following:

| Name                          | Value                               |
| ----------------------------- | ----------------------------------- |
| SERVER_PORT                   | 5000                                |
| SPRING_DATASOURCE_URL         | jdbc:mysql://xyz.us-east-1.rds.amazonaws.com:3306/ebdb?useTimezone=true&serverTimezone=UTC |
| SPRING_DATASOURCE_USERNAME    | username from above                 |
| SPRING_DATASOURCE_PASSWORD    | password from above                 |
| SPRING_JPA_DATABASE_PLATFORM  | org.hibernate.dialect.MySQL5Dialect |
| SPRING_JPA_HIBERNATE_DDL_AUTO | update                              |

Note that BeanStalk chooses the name of the database for you: `ebdb`.

4. Click "Apply"

## Logs

You can see the logs from your application by clicking on the "Logs" option in the left. You can choose to see the last 100 lines of the logs or download all logs. Generally, the last 100 lines are sufficient to see errors. Looking through all of the logs can be overwhelming because not only will your application log be included, but logs for all parts of the environment.

## Reference

* [Spring Boot on AWS BeanStalk](https://aws.amazon.com/blogs/devops/deploying-a-spring-boot-application-on-aws-using-aws-elastic-beanstalk/)
