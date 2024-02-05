# Mars Rover

This project aims to solve and provide a demo application for [Mars Rover Technical Challenge](MarsRover.md).

### Branches

- `master` - Single application containing the minimum required solution for the challenge (no frameworks used)
- `spring` - Client-Server application using spring boot framework for both client and server
- `react-spring` - Client-Server application with a simple react app as the client

### General Project Structure

The project was structured with extensibility in mind allowing future features (such as networking) and use cases to be implemented with minimal changes to the existing domain logic.

The following shows how the project was organized, inspired by the
[Hexagonal Architecture](<https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)>) (Ports and Adapters)

- `org.nasa.marsrover.rover`
  - `core`
    - `application`  
      Implementation of core domain logic / use cases like maneuvering the `Rover` in a `Terrain`.
    - `ports`  
      Definition of ports (input and output ports) that allow communication between the core and adapters.
  - `adapters`  
    Implementation of input adapters that executes the core use cases and output adapters used by the core.

---

### How to Use

#### Pre-requisites:

- Java 17 Runtime Environment installed and selected
- Git CLI installed
- NodeJS v20 /NPM v10 installed

#### Running the application

Download source code

```
$ git clone https://github.com/nitoygo/mars-rover
```

Build and run the server (Rover)

```
$ cd mars-rover/rover-server
$ ./gradlew bootRun
```

Setup ReactJS client app (Command Center)

```
$ cd mars-rover/rover-client-react
$ npm install
:
$ npm start
:
```
Visit http://localhost:3000 (or check nodejs logs for exact port)  
Type commands in the text field as you would with the CLI version  
You may need to setup the rover's initial location by inputting x,y,direction format.
```
1,1,E
```
The above input will teleport the rover to x=1,y=1, and facing East

### Roadmap

- [x] Implement basic features
- [x] Implement basic unit tests
- [x] Implement backend and frontend using spring framework
- [x] Implement frontend using reactjs
- [ ] Implement ci/cd scripts

### Misc

Some packages can still be refactored into common libraries
