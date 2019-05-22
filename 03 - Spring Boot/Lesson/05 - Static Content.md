# Static Content

HTML, CSS, JS files can be served from Spring Boot by placing them into the `src/main/resources/public` directory. For generated sources, such as those produced by webpack, you'll want to use the build system such as Gradle. Only files that should be committed to source control should be in `src/main/resources/public`.

Be careful adding `index.html`. It will take the place of other things that would return from `http://localhost:8080/`, such as the REST HAL browser.

For a modern JavaScript single-page application we'll use [JHipster](https://www.jhipster.tech) later on.
