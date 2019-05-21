# JAR (Java Archive)

Java will generate thousands of `.class` files. How do we manage and distribute those?

Java created a `.jar` file, which is a `.zip` file of the compiled classes. It also includes a `META-INF` directory that includes information about the classes. The developer usually doesn't need to be concerned about the `META-INF` content, the build tools will provide that.

## WAR (Web Archive)

Similar to a `.jar` file is a `.war` file. It is also a `.zip` file that includes the `.html`, `.css`, `.js` and other files that belong in the document root. There is a `WEB-INF` directory instead of `META-INF` containing various files describing the web application and the Java code needed to run it. The `web.xml` describes properties of web application. Typically the developer will create and maintain this file.
