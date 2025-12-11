# Iteration 2
- There might be obstructions on the table surface. 
- The robot is free to roam around the surface of the table, but must be prevented from falling to destruction or entering an obstructed cell. Any command that would result in these must be prevented, however further valid commands must still be allowed.
- AVOID will inform the robot about an obstruction on the table in position X,Y.
- The PLACE command should be discarded if it places the robot on an obstructed cell or outside of the table surface.
- The AVOID command should be discarded if it tells the robot to avoid the current coordinates or if the given coordinates fall outside of the table surface.
- MOVE will move the toy robot one unit forward in the direction it is currently facing, unless the destination cell is obstructed or outside of table boundaries, in which case the command should be discarded.
- A robot that is not on the table can choose to ignore the MOVE, LEFT, RIGHT, AVOID and REPORT commands.

Example:
```
PLACE 1,2,EAST
AVOID 2,2
AVOID 2,3
MOVE
PLACE 2,3,EAST
MOVE
LEFT
MOVE
REPORT
Output: 1,3,NORTH
```