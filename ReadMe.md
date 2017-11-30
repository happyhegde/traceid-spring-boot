# POC - Log Tracing - Java - Spring Boot
Implementation of log tracing in Java - Spring Boot,
without using any other tracing libraries

## Getting started
 - Java 8
 - Spring boot 1.5.8
 - Gradle 3.5

### Building the module
The module uses gradle as package manager and uses gradle wrapper to manage the gradle version
Command line argument
```
./gradlew clean build
```
The above command compiles the java classes and creates a 'jar' file in the location

### Running the application
Once we have the jar file, we can run it using the below command
```
java -jar build/libs/traceid-1.0.0.jar
```

##### When gradle wrapper command doesn't
If the given build command doesn't work, run the following command to update the gradle wrapper

```
$ gradle wrapper --gradle-version 3.5
```


## The differences
Without using the implementation of Tracing the log statement would look like:

```
2017-11-30 12:46:45.057  INFO 22309 --- [nio-8080-exec-2] c.s.p.t.c.RequestResponseFilter          : API call: GET: /test
```

After the implementation, the log statements
```
2017-11-30 12:48:15.727  INFO [X-TRACE-ID: bb2942c5-4c1b-4693-85fc-eaa6cdc2aeed] 22476 --- [nio-8080-exec-2] c.s.p.t.c.RequestResponseFilter          : API call: GET: /test
```

The pattern can be changed for custom text by modifying the FilterChain code and PostProcessor code in config folder