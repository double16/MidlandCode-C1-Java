# Testing Types

Automated testing is writing code to test your software and configuring tools to run that code on a regular basis. This maintains quality and finds bugs introduced by seemingly unrelated changes.

There are a few different "layers" of testing. Each layer has a different level of detail, complexity and time to execute.

## Unit

Unit testing is the easiest, most detailed and fasted to run. Individual classes, methods and functions are tested. There is no database or web server involved.

## Integration

Integration testing verifies things like API calls and database operation. The database and web server will be running. This takes more complexity and time to set up.

## Functional or End to End (E2E)

E2E testing is the most expensive. This level tests the user stories. For a web application a real browser is usually run and controlled by software. Sometimes a "headless" version of the browser is used. The browser is running, but the user interface is hidden.
