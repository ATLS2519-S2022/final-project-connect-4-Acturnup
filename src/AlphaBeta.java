
public class AlphaBeta implements Player{
	
	   public String name() 
	   	{
	        return "Alpha Beta AI";
	    }
	   
		int id; 
		int opponent_id;
	    int cols; 
	    
	    public void init(int id, int msecPerMove, int rows, int cols) 
	    {
	    	this.id = id; 
	    	this.cols = cols;
	    	opponent_id = 3-id;
	    }
	    
	    public void calcMove (Connect4Board board, int oppMoveCol, Arbitrator arb) 
	    	throws TimeUpException  
	    {
	    	
	        if (board.isFull()) 
	        {
	            throw new Error ("Complaint: The board is full!");
	        }
	        int move = 0; 
	        int maxDepth = 1;
	        
	        while(!arb.isTimeUp() && maxDepth <= board.numEmptyCells()) 
	        {
	        	
		    		int bestScore = -1000;
		    		for(int i = 0; i <=6; i++)
		    		{
		        		if(board.isValidMove(i))
		        		{
		    			board.move(i, id);
		    			int score = alphabeta(board, maxDepth- 1,false, arb, -1000,1000);
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
	

	    public int alphabeta(Connect4Board board, int depth, boolean isMaximizing, Arbitrator arb, int alpha, int beta) 
		        throws TimeUpException {
//		    	if depth = 0 or there's no moves left or time is up
//		    			return the heuristic value of node 
		    	
		    	if (depth == 0 || board.numEmptyCells() == 0 || arb.isTimeUp()) 
		    	{
		    		return score(board);
		    	}
		    	// maximizing (player1)
		    	if (isMaximizing) 
		    	{
		    		int bestScore = -1000;
		    		for(int i = 0; i <=6; i++)
		    		{
		        		if(board.isValidMove(i)){
		    			board.move(i, id);
		    			bestScore = Math.max(bestScore, alphabeta(board, depth - 1,false, arb, alpha,beta));
		       			board.unmove(i, id);
		       			alpha = Math.max(alpha,bestScore);
		       			if (alpha >= beta) 
		       				{
		       				break;
		       				}
		        		}
		    		}

		    		return bestScore;
		    	}
		    	else
		    	{ // minimizing (other player)
		    		int bestScore =1000;
		    		for(int i = 0;i <=6; i++) 
		    		{
		        		if(board.isValidMove(i))
		        		{
		    			board.move(i, opponent_id);
		    			bestScore = Math.min(bestScore, alphabeta(board,depth -1, true,arb,alpha,beta)); 
		    			board.unmove(i, opponent_id);
		    			beta = Math.min(beta,bestScore);
		    			if(alpha >= beta)
		    				{
		    				break; 
		    				}
		        		}
		    		}
		    		return bestScore;
		    	}
	    }
		    	
	    public int score(Connect4Board board) 
				{		    	
				return calcScore(board, id) - calcScore(board, opponent_id);
			    }
		    	
	

	public int calcScore(Connect4Board board, int id)
		{
		final int rows = board.numRows();
		final int cols = board.numCols();
		int score = 0;
		// Look for horizontal connect-4s.
		for (int r = 0; r < rows; r++) 
		{
			for (int c = 0; c <= cols - 4; c++) 
			{
				if (board.get(r, c + 0) != id) continue;
				if (board.get(r, c + 1) != id) continue;
				if (board.get(r, c + 2) != id) continue;
				if (board.get(r, c + 3) != id) continue;
				score++;
			}
		}
		// Look for vertical connect-4s.
		for (int c = 0; c < cols; c++) {
			for (int r = 0; r <= rows - 4; r++) 
			{
				if (board.get(r + 0, c) != id) continue;
				if (board.get(r + 1, c) != id) continue;
				if (board.get(r + 2, c) != id) continue;
				if (board.get(r + 3, c) != id) continue;
				score++;
			}
		}
		// Look for diagonal connect-4s.
		for (int c = 0; c <= cols - 4; c++) {
			for (int r = 0; r <= rows - 4; r++) 
			{
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
