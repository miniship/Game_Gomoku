Game Gomoku(Five in a row)
- version: 1.1
- run in local machine

I. Introdution
- Write a gomoku game for computers to compete with human in a 20 x 20 matrix using the following algorithm:
Step 1: Make a list of moves in 5 x 5 matrixes stored in a txt file, moves with higher priority are listed before moves with lower priority.
Example
	4 4 4 4 2
	4 4 4 1 4
	4 4 1 4 4
	4 1 4 4 4
	3 4 4 4 4
				
1 : computer's previous moves
2 : human's previous moves
3 : empty cell and the place the computer will play next
0 : empty cell
4 : cell with any values, it doesn't affect this move

Step 2: When it is the computer's turn, it compares each move in the list with every location on the board to find a match and play accordingly.

II. Make the move list
- Make a row with computer's previous moves, empty cells and place to play next.
- Rotate that row in a 5 x 5 matrix to make more moves.
- Defend moves are made by switch computer's previous moves to human's previous moves.
- Make complex moves by combining 2 row.

- FourInArowMaker: make a row with 4 computer's previous moves and 1 place to play next
Example
	1 3 1 1 1

- ThreeInArowMaker: make a row with 3 computer's previous moves, 1 empty cell and 1 place to play next.
There are 2 cases: previous's moves and place to play next are close together or not.
Examples
	0 3 1 1 1
	3 0 1 1 1

- TwoInArowMaker: make a row with 2 computer's previous moves, 2 empty cell and 1 place to play next.
There are 2 cases: previous's moves and place to play next are close together or not.
Examples
	0 3 1 1 0
	0 3 0 1 1

- OneInArowMaker: make a row with 1 computer's previous moves, 3 empty cell and 1 place to play next.
Example
	0 0 0 3 1

- MoveWithSingleRowMaker: make moves by rotating a row in a 5 x 5 matrix and fill in the remaining cells with value 4
Example
	4 4 4 4 0
	4 4 4 1 4
	4 4 1 4 4
	4 1 4 4 4
	3 4 4 4 4

- MoveWithDoubleRowMaker: make moves by rotating 2 rows in a 5 x 5 matrix and fill in the remaining cells with value 4
Example
	4 4 4 4 0
	4 4 4 1 4
	4 4 1 4 4
	1 3 1 0 1
	1 4 4 4 4

- MoveMaker: make moves from level 1 to level 5, moves with lower level are better moves

III. Changes
- Add moves by combining attack moves with defend moves
- Remove some weak moves
- Shuffle some simple moves to make the computer play more naturally