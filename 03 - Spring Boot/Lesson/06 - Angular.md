# Angular

We will use Angular as the frontend technology and integrate it with Spring Boot. You will still be able to use Angular tools such as `ng` the same as without integrating with Spring Boot.

## API Endpoints

You'll need to code to your front end to use the endpoints you've created with `@RepositoryRestResource` and `@RestController`. Do not include `http://localhost:8080` or you'll have issues later when deploying to a server that is using a different host and/or port. If `http://localhost:8080/api/people` is your endpoint, use `/people`.

## Build Integration

Gradle supports multi-project builds, which means you can separate different parts of your applications by function (backend vs. frontend) and/or technology (Java vs. Angular). This makes managing the application easier.

1. In a terminal, cd to the root of your existing Gradle project.
2. Run `ng new frontend` to generate a new Angular project. If you did it correctly there should be a file named `frontend/node_modules/@angular/cli/bin/ng`.
3. Create a `build.gradle` file in `frontend` and make it look like this:

```groovy
plugins {
  id "com.moowork.node" version "1.3.1"
}

node {
  version = '10.15.3'
  download = true
}

task clean(type: Delete) {
  group 'build'
  delete "${projectDir}/dist"
}

task build(type: NodeTask, dependsOn: npmInstall) {
  group 'build'
  script = file('node_modules/@angular/cli/bin/ng')
  args = [ 'build' ]

  inputs.dir('src')
  inputs.file('package.json')
  inputs.file('package-lock.json')
  inputs.file('angular.json')
  outputs.dir('dist')
}
```

4. In the root Gradle project `settings.gradle`, include the new project:

```groovy
include 'frontend'
```

5. Add the following tasks to the root project `build.gradle`:

```groovy

task installFrontEnd(type: Sync) {
    group 'build'
    description 'Builds and copies frontend into Spring Boot static location'
    dependsOn ':frontend:build'
    from 'frontend/dist/frontend'
    into 'src/main/resources/static'
}

clean.delete "src/main/resources/static"

// processResources prepares files in src/main/resources for use. We need the Angular app in place before this task.
processResources.dependsOn(installFrontEnd)
```

6. Spring Boot will handle all of your HTTP requests, which makes Angular routes not work because Spring Boot returns a "Not Found" response. Add the following file in the `src/main/java` directory, inside the `edu.<your_name>.simple_rest` package. Name the file `WebApplicationConfig.java`. Make sure to fix the package name in the first line.

```java
package edu.<your_name>.simplerest;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/notFound").setViewName("forward:/index.html");
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
                    "/notFound"));
        };
    }
}
```
   
7. Add this to the root project `.gitignore`:

```.gitignore
### Angular frontend ###
src/main/resources/static/
node_modules/
```

## Run It

Restart the `bootRun` task. It should take a lot longer this time. The new project will:

1. Download `nodejs`
2. Install the npm packages (if not already done)
3. Build the Angular distribution
4. Install into the Spring Boot `src/main/resources/static` directory
5. Run the Spring Boot application

Go to <http://localhost:8080>. You should see your Angular app. The down side is the HAL browser will be at <http://localhost:8080/api/browser/index.html#/> and won't be able to find all of your REST endpoints. You'll need to use the `Explore` input box to go to `/api`.

## Using `ng serve`

WARNING: This approach isn't well tested, yet.

In order to use `ng serve` for faster development, you need to get Angular to contact `http://localhost:8080` instead of `http://localhost:4200`.

Add this file `frontend/proxy.conf.json`:

```json
{
  "/api": {
    "target": "http://localhost:8080",
    "secure": false
  }
}
```

Modify `frontend/angular.json`. Find the `"serve"` entry and add the `proxyConfig` as shown:

```json
        "serve": {
          "builder": "@angular-devkit/build-angular:dev-server",
          "options": {
            "browserTarget": "frontend:build",
            "proxyConfig": "proxy.conf.json"
          },
```

You will need to run both `bootRun` and `ng serve` at the same time. Use <http://localhost:4200> in your browser and you should be able to reload changes to Angular. (Note that <http://localhost:8080> will still serve your 'old' Angular code, don't get confused.)
