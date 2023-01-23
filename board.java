
public class board {
	protected char[][] sudoku = new char[9][9];
	public board(char[][] sudoku)
	{
		this.sudoku=sudoku;
	}
	public void printer(char[][] board)
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.print("\n");
		}
		
	}
	
	
	
	public int checker(char[][] board)
	{
		if(isValidSudoku(board)==true)
		{
			return 2;
		}
		else
		{
			return 1;
		}
	}
	
	public void solveSudoku(char[][] board) {
	    boolean[][] rows = new boolean[9][9];
	    boolean[][] cols = new boolean[9][9];
	    boolean[][] blks = new boolean[9][9];
	   // System.out.print("a");
	    int amount = 81;
	    for(int i = 0; i < 9; i++)
	        for(int j = 0; j < 9; j++) {
	            if (board[i][j] != '#') {
	                rows[i][board[i][j] - '1'] = true;
	                blks[3 * (i / 3) + j / 3][board[i][j] - '1'] = true;
	                cols[j][board[i][j] - '1'] = true;
	                amount-=1;
	            }
	        }

	    dfs(rows, cols, blks, board, amount, 0, new boolean[1]);
	}

	public void dfs(boolean[][] rows, boolean[][] cols, boolean[][] blks, char[][] board, int amount, int pos, boolean[] flag) {
	    if (amount == 0) {
	        flag[0] = true;
	        return;
	    }

	    int r = pos / 9, c = pos % 9;
	    if (board[r][c] != '#') {
	        dfs(rows, cols, blks, board,amount, pos + 1, flag);
	    } //if dot
	    else {
	        int blk = 3 * (r / 3) + c / 3;
	        for (int i = 0; i < 9; i++)
	        {
	            if (!rows[r][i] && !cols[c][i] && !blks[blk][i]) 
	            {
	                board[r][c] = (char)('1' + i);
	                rows[r][i] =  cols[c][i] = blks[blk][i] = true;
	                dfs(rows, cols, blks, board, amount - 1, pos + 1, flag);
	                if (flag[0]) return;
	                rows[r][i] =  cols[c][i] = blks[blk][i] = false;
	            }
	        }
	        board[r][c] = '#';
	    }// if not dot
	}


	
	public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] blocks = new boolean[9][9];
        for (int i = 0; i < board.length; i++) {
          for (int j = 0; j < board[i].length; j++) {
            if (board[i][j] == '#') {
              continue;
            }
            int num = board[i][j] - '1';
            if (rows[i][num]) {
              return false;
            }
            rows[i][num] = true;
            if (columns[j][num]) {
              return false;
            }
            columns[j][num] = true;
            if (blocks[(i / 3) * 3 + j / 3][num]) {
              return false;
            }
            blocks[(i / 3) * 3 + j / 3][num] = true;
          }
        }
        return true;
    }
}
