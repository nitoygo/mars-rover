# Mars Rover

This project aims to solve and provide a demo application for [Mars Rover Technical Challenge](MarsRover.md).

### Branches

- `master` - Single application containing the minimum required solution for the challenge (no frameworks used)
- `spring` - Client-Server application using spring boot framework for both client and server
- `react-spring` - (Not yet implemented) Will contain a very simple react frontend as the client

### Project Structure

The project was structured with extensibility in mind allowing future features (such as networking) and use cases to be implemented with minimal changes to the existing domain logic.

The following shows how the project was organized, inspired by the
[Hexagonal Architecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)) (Ports and Adapters)

* `org.nasa.marsrover.rover`
    * `core`
        * `application`  
          Contains implementation of core domain logic / use cases like maneuvering the `Rover` in a `Terrain`.
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

#### Run the application

```
$ java -jar .\build\libs\mars-rover-1.0-SNAPSHOT.jar
```

```
Starting command line chat interface...
Type 'exit' to end)
>> LMLMLMLMLM
0,1,W
```

Hint: You can teleport the rover by inputting x,y,direction format.
```
1,1,E
```
The above input will teleport the rover to x=1,y=1, and facing East

### Roadmap

- [x] Implement basic features
- [ ] Implement unit tests
- [ ] Implement ci/cd scripts
