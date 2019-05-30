# AWS BeanStalk Deploy

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
