Test Demo
===

This is a ridiculously over designed Hello World application. The idea behind this
is to have a simple project showing an example of Cucumber acceptance tests and to have 100%
unit test coverage.

Build
---
To build this project you will need to install and setup
- [JDK](http://openjdk.java.net/install/)
- [Apache Maven](https://maven.apache.org/install.html)

Go to the project home directory and run  
```mvn clean compile```

This will compile the code and run all of the unit tests.

Start Server
---
To start the server run  
```mvn spring-boot:run```  
This will start the server on port 5000. You can now open your browser and go to
http://localhost:5000/demo/greet?name=YOURNAME
which will return a greeting for you.

Acceptance Test
---
If you wish to run the acceptance tests for the project, start up the server and then run  
```mvn verify```

Coverage
---
If instead of doing a  
```mvn clean compile```  
you run  
```mvn clean verify```  
in addition to the code being compiled it will also do a test coverage check to ensure that all code has associated tests.

If you get the error  
```Coverage checks have not been met. See log for details.```  
then open *MODULE_NAME/target/site/jacoco/index.html* in your browser to get a breakdown
of the test coverage to and see which methods/branches are not covered.

