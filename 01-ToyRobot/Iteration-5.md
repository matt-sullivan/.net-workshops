# Iteration 5
- The robot may be instructed to avoid specific corner triangles
- It will accept the command 'AVOID (corner)'
- Where (corner) is one of NORTH WEST, NORTH EAST, SOUTH WEST, or SOUTH EAST
- The middle rows and columns have the full size, e.g. 8 x 8
- Each row or column away from the middle is one shorter (cumulative) in that corner
- The AVOID command should be discarded if the robot is currently in one of the specified corner locations.

Example board, with avoid for NORTH WEST, NORTH EAST, and SOUTH EAST, then the following shows the valid locations ("X" are not)

```
X X X . . X X X
X X . . . . X X
X . . . . . . X
. . . . . . . .
. . . . . . . .
. . . . . . . X
. . . . . . X X
. . . . . X X X
```

- Normal rules for other commands apply, except that the robot will not move into the specified corners, but stay on the board
- A robot that is not on the table can choose to ignore the MOVE, LEFT, RIGHT, AVOID and REPORT commands.

Example:
```
PLACE 2,2,SOUTH
AVOID SOUTH WEST
MOVE
MOVE
REPORT
Output: 2,1,SOUTH
```