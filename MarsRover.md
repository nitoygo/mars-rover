### Mars Rover Technical Challenge

The problem below requires some kind of input. You are free to implement any mechanism for feeding
input into your solution (for example, using hard coded data within a unit test, or main method). You
are also free to implementing output (for example returning a value from a method).
You should provide sufficient evidence that your solution is complete by, as a minimum, indicating that
it works correctly against the supplied test data.

#### A robotic rover is to be landed by NASA on a plateau on Mars.

This plateau, which is curiously rectangular, must be navigated by the rover so that their on board
cameras can get a complete view of the surrounding terrain to sent back to Earth.

A rover's position is represented by a combination of an x and y co-ordinate and a letter representing
one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An
example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North.

In order to control a rover, NASA sends a simple string of letters. The possible letters are 'L', 'R' and
'M'. 'L' and 'R' makes the rover spin 90 degrees left or right respectively, without moving from its
current spot.

'M' means move forward one grid point, and maintain the same heading.

Assume that the square directly North from (x, y) is (x, y+1).

The rover should initially be placed at 0, 0, N.

<u>Input:</u>  
A single line of input is a series of instructions telling the rover how to explore the plateau.

<u>Output:</u>  
The output for each rover should be its final co-ordinates and heading.

<u>Test Input:</u>  
LMLMLMLMLM

<u>Expected Output:</u>  
0, 1, W