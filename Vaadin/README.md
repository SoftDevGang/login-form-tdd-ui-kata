# Setup Java 8/Vaadin 8

Setup to TDD a simple UI.

This is a Java 8 [Apache Maven](https://maven.apache.org/) project. Run `./mvnw test` to run your tests. [JUnit](https://junit.org/junit5/) and [Mockito](https://site.mockito.org/) are provided as dependencies.

Specific framework for testing Vaadin UIs is [Karibu Testing](https://github.com/mvysny/karibu-testing/tree/master/karibu-testing-v8).

Run `mvnw jetty:run` to run the app and open http://localhost:8080/ in browser.
