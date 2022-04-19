
public class GreedyPlayer implements Player {
	
	int id;

	
	//JUST A NAME for AI 
    public String name() {
        return "Greedy AI";
    }
	
    // id your player ID 1 or 2?
    
    // msecPerMove = there is a time limit, which is a variable. 
    //The default value is 250 ms, but you can change this in the GUI.
    
    // rows, cols - these specify the size of the connect 4 board
    
    public void init(int id, int msecPerMove, int rows, int cols) {
    	this.id = id; // your opponent's id is 3-id // calls this id 
    }
    
    
//  For each move:
//	Temporarily make the move using board.move()
//	Calculate a score based on how the board is for you now that you've made the move
//	Undo the move using board.unmove 
//	Return the move that had the highest calculated score
    //greedy  h(nn) shortest distance to the goal 
    
    			//value of node // node cost //heurisitic function 
    public void calcMove (
    		Connect4Board board, int oppMoveCol, Arbitrator arb) throws TimeUpException  {
    	
        if (board.isFull()) {
            throw new Error ("Complaint: The board is full!");
        }
    	int max = -100; // temp for the max 
    	//board.move( col, id)
    	for(int i =0; i <= 6 ; i++) {
    		if(board.isValidMove(i)){
        	board.move(i, id); // makes a move on the board while calling the ID 
        //	calcScore(board,id); // runs the function calcScore to make a score 
        	if(calcScore(board,id)>max) { // compares the calcScore with the max 
        		max= calcScore(board,id);
                arb.setMove(i); // finally makes the move if this is the best move to make
        	}
        	board.unmove(i, id); // undos the move 
    	}
    }
    	return; 
    	
    	
    	//calcuate score 
    	// overall score = myscore- oppscore
    	
    	//board.unmove
    	
    	// return score 
    	
//            int col = 0;
//            do { col = rand.nextInt(board.numCols());
//            } while (!board.isValidMove(col));
//            arb.setMove(col);
    }
    
	// Return the number of connect-4s that player #id has.
	public int calcScore(Connect4Board board, int id)
	{
		final int rows = board.numRows();
		final int cols = board.numCols();
		int score = 0;
		// Look for horizontal connect-4s.
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c <= cols - 4; c++) {
				if (board.get(r, c + 0) != id) continue;
				if (board.get(r, c + 1) != id) continue;
				if (board.get(r, c + 2) != id) continue;
				if (board.get(r, c + 3) != id) continue;
				score++;
			}
		}
		// Look for vertical connect-4s.
		for (int c = 0; c < cols; c++) {
			for (int r = 0; r <= rows - 4; r++) {
				if (board.get(r + 0, c) != id) continue;
				if (board.get(r + 1, c) != id) continue;
				if (board.get(r + 2, c) != id) continue;
				if (board.get(r + 3, c) != id) continue;
				score++;
			}
		}
		// Look for diagonal connect-4s.
		for (int c = 0; c <= cols - 4; c++) {
			for (int r = 0; r <= rows - 4; r++) {
				if (board.get(r + 0, c + 0) != id) continue;
				if (board.get(r + 1, c + 1) != id) continue;
				if (board.get(r + 2, c + 2) != id) continue;
				if (board.get(r + 3, c + 3) != id) continue;
				score++;
			}
		}
		for (int c = 0; c <= cols - 4; c++) {
			for (int r = rows - 1; r >= 4 - 1; r--) {
				if (board.get(r - 0, c + 0) != id) continue;
				if (board.get(r - 1, c + 1) != id) continue;
				if (board.get(r - 2, c + 2) != id) continue;
				if (board.get(r - 3, c + 3) != id) continue;
				score++;
			}
		}
		return score;
	}

}
