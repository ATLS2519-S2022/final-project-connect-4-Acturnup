
public class MiniMax implements Player{

//	Initialize a maximum search depth to be 1
//	While there is time remaining to calculate your move (you can check this with the arb.isTimeUp() method) and your current search depth is <= the number of moves remaining (you can check this with the board.numEmptyCells() method):
//	Do a minimax search to the depth of your maximum search variable
//	Set your move as the best move found so far
//	Increment your maximum search depth

		int id; 
		int opponent_id;
	    int cols; 
	    
	    @Override
	    public String name() {
	        return "MiniMax";
	    }

	    @Override
	    public void init(int id, int msecPerMove, int rows, int cols) {
	    	this.id = id; //id is your player's id, opponent's id is 3-id
	    	this.cols = cols;
	    	opponent_id = 3-id;
	    }

	    @Override
	    public void calcMove(
	        Connect4Board board, int oppMoveCol, Arbitrator arb) 
	        throws TimeUpException {
	        // Make sure there is room to make a move.
	        if (board.isFull()) {
	            throw new Error ("Complaint: The board is full!");
	        }
	        int move = 0; 
	        int maxDepth = 1;
	        
	        // while there's time left and maxDepth <= number of moves remaining
	        while(!arb.isTimeUp() && maxDepth <= board.numEmptyCells()) {
	        	
	        	// do minimax search
	        	//minimax(board,);??
	        	

		    		int bestScore = -1000;
		    		for(int i = 0; i <=6; i++)
		    		{
		        		if(board.isValidMove(i)){
		    			board.move(i, id);
		    			int score = minimax(board, maxDepth- 1,false, arb);
		    			if (bestScore < score) 
		    			{
		    				move = i;
		    				bestScore = score;
		    			}
		    			board.unmove(i, id);
		        		}
		    		}
	        	// start the first level of minimax, set move as you're finding the bestScore
	            arb.setMove(move);
	            maxDepth++;    
	        }
	    }
	    
	    public int minimax(Connect4Board board, int depth, boolean isMaximizing, Arbitrator arb) 
	        throws TimeUpException {
//	    	if depth = 0 or there's no moves left or time is up
//	    			return the heuristic value of node 
	    	
	    	if (depth == 0 || board.numEmptyCells() == 0 || arb.isTimeUp()) {
	    		return score(board);
	    	}
	    	// maximizing (player1)
	    	if (isMaximizing) {
	    		int bestScore = -1000;
	    		for(int i = 0; i <=6; i++)
	    		{
	        		if(board.isValidMove(i)){
	    			board.move(i, id);
	    			bestScore = Math.max(bestScore, minimax(board, depth - 1,false, arb));
	       			board.unmove(i, id);
	        		}
	    		}

	    		return bestScore;
	    	}
	    	else{ // minimizing (other player)
	    		int bestScore =1000;
	    		for(int i = 0;i<=6; i++) {
	        		if(board.isValidMove(i)){
	    			board.move(i, opponent_id);
	    			bestScore = Math.min(bestScore, minimax(board,depth -1, true,arb)); 
	    			board.unmove(i, opponent_id);
	        		}
	    		}
	    		return bestScore;
	    	}
	    	
	    	

//	    	if isMaximizing then
//	    			bestScore = -1000
//	    			for each possible next move do
//						board.move(...)
//	    				bestScore = Math.max(bestScore, minimax(child, depth - 1, FALSE)) 
//	    				board.unmove(...)
//	    			return bestScore
	    	
	    	
//	    	else /* minimizing player */ 
//	    			bestScore = 1000
//	    			for each possible next move do
//						board.move(...)    	
//	    				bestScore = Math.min(bestScore, minimax(child, depth - 1, TRUE)) 
//						board.unmove(...)
//	    			return bestScore	
	    	
	    	//return 0; // delete
	    }
	    

	    // your score - opponent's score
	    public int score(Connect4Board board) {
	    	return calcScore(board, id) - calcScore(board, opponent_id);
	    }
	    
	    // dont need to change 
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

