# Static Content

HTML, CSS, JS files can be served from Spring Boot by placing them into the `src/main/resources/public` directory. For generated sources, such as those produced by webpack, you'll want to use the build system such as Gradle. Only files that should be committed to source control should be in `src/main/resources/public`.

For a modern JavaScript single-page application we'll add a project for Angular that is created using the Angular CLI and integrate that with our Spring Boot build.
