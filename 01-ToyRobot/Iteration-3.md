# Iteration 3
- LEFT and RIGHT will rotate the robot 45 degrees in the specified direction without changing the position of the robot.

Examples:

a)
```
PLACE 0,0,NORTH
RIGHT
MOVE
REPORT
Output: 1,1,NORTH EAST
```

b)
```
PLACE 2,3,SOUTH WEST
MOVE
RIGHT
MOVE
REPORT
Output: 0,2,WEST
```

c)
```
PLACE 1,2,EAST
AVOID 2,2
AVOID 1,3
MOVE
LEFT
MOVE
REPORT
Output: 2,3,NORTH EAST
```