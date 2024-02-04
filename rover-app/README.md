# Mars Rover Server App (Spring Boot Web Server)

Mars Rover application with Web API as input adapter running on Spring Boot

### How to Use

#### Pre-requisites

Java 17 compiler and runtime environment are available.

#### Building and Running the Server Application

In the `rover-app` directory, run the gradle `bootRun` command:
```
> ./gradlew bootRun
```

This would build and run the server application.
```
> Task :bootRun

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v3.1.8)
...
... o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
... org.nasa.marsrover.rover.Application     : Started Application in 1.772 seconds...
...
```
