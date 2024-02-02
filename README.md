# Mars Rover

This project aims to solve and provide a demo application for [Mars Rover Technical Challenge](MarsRover.md).

### Project Structure

The project was structured with extensibility in mind allowing future features (such as networking) and use cases to be implemented with minimal changes to the existing domain logic.

The following shows how the project was organized, inspired by the
[Hexagonal Architecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)) (Ports and Adapters)

* `org.nasa.marsrover.rover`
    * `core`
        * `application`  
          Contains the implementation of the core use cases like maneuvering the `Rover` in a `Terrain`.
        * `ports`  
          Contains definition of ports (input and output ports) that allow communication between the core and adapters.
    * `adapters`
      Contains implementation of input adapters that executes the core use cases and output adapters used by the core.

***  

### How to Use

#### Pre-requisites:
- Java 17 Runtime Environment installed and selected
- Git CLI installed

#### Download and build the application:

```
$ git clone https://github.com/nitoygo/mars-rover
$ cd mars-rover
$ ./gradlew build
```

#### Run the jar output

```
$ java -jar .\build\libs\mars-rover-1.0-SNAPSHOT.jar
```

```
Starting command line chat interface...
Type 'exit' to end)
>> LMLMLMLMLM
0,1,W
```


### Roadmap

- [x] Implement basic features
- [ ] Implement unit tests
- [ ] Implement ci/cd scripts
