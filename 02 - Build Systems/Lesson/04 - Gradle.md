# Gradle

>[Gradle](https://gradle.org) is an open-source build automation tool focused on flexibility and performance. Gradle build scripts are written using a Groovy or Kotlin DSL. Read about Gradle features to learn what is possible with Gradle.
-- [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html)

Gradle takes the ideas of Maven, removes the XML and replaces it with Groovy code. Groovy is a language built on top of Java. It allows smaller code and many other features, including writing code without classes.

The script for Gradle is named `build.gradle`. The following is from a Spring Boot example:

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.1.4.RELEASE")
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'idea'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

bootJar {
    baseName = 'gs-rest-service'
    version =  '0.1.0'
}

repositories {
    mavenCentral()
}

sourceCompatibility = 1.8
targetCompatibility = 1.8

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    testCompile('org.springframework.boot:spring-boot-starter-test')
}
```

Like Maven, Gradle assumes code is in `src/main/java`, `src/main/test`, etc.

Gradle has tasks to execute various parts of the build:

```shell
$ ./gradlew tasks

Application tasks
-----------------
bootRun - Runs this project as a Spring Boot application.

Build tasks
-----------
assemble - Assembles the outputs of this project.
bootJar - Assembles an executable jar archive containing the main classes and their dependencies.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles main classes.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the main classes.
testClasses - Assembles test classes.

Build Setup tasks
-----------------
init - Initializes a new Gradle build.
wrapper - Generates Gradle wrapper files.

Documentation tasks
-------------------
javadoc - Generates Javadoc API documentation for the main source code.

Help tasks
----------
buildEnvironment - Displays all buildscript dependencies declared in root project 'simple-rest'.
components - Displays the components produced by root project 'simple-rest'. [incubating]
dependencies - Displays all dependencies declared in root project 'simple-rest'.
dependencyInsight - Displays the insight into a specific dependency in root project 'simple-rest'.
dependencyManagement - Displays the dependency management declared in root project 'simple-rest'.
dependentComponents - Displays the dependent components of components in root project 'simple-rest'. [incubating]
help - Displays a help message.
model - Displays the configuration model of root project 'simple-rest'. [incubating]
projects - Displays the sub-projects of root project 'simple-rest'.
properties - Displays the properties of root project 'simple-rest'.
tasks - Displays the tasks runnable from root project 'simple-rest'.

Verification tasks
------------------
check - Runs all checks.
test - Runs the unit tests.
```

## Artifact Location

The `.jar` files, called artifacts, will be downloaded into the `.gradle` directory of the user's home directory. Every Gradle project by that user shares the artifacts. This prevents downloading multiple times and keeping multiple copies.
