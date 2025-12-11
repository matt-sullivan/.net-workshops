# Iteration 4
- MOVE takes an optional extra parameter of how many spaces to move
- If there is no additional parameter the robot moves 1 space
- If movement would take the robot off the board then it stops at the edge of the board.

Examples:

a)
```
PLACE 0,0,NORTH
MOVE 2
REPORT
Output: 0,2,NORTH
```

b)
```
PLACE 2,3,SOUTH
MOVE 5
REPORT
Output: 2,0,SOUTH
```