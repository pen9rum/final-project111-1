import java.util.Scanner;

public class extender extends board{
	Scanner sc=new Scanner(System.in);
	public extender(char [][] board){
		super(board);
	}
	public void printer_extender(char[][] board)
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				System.out.print(board[i][j]+"|");
			}
			System.out.print("\n");
			for(int k=0;k<18;k++)
			{
				System.out.print("-");
			}
			System.out.print("\n");
		}
		
	}
	
	int error_number=0;
	public void checker_extender(char[][] board)
	{
		if(isValidSudoku(board)==true)
		{
			return;
		}
		else
		{
			if(error_number==1)
			{
				System.out.print("error_message1---same number in the row \n");
			}
			if(error_number==2)
			{
				System.out.print("error_message2--- same number in the column\n");
			}
			else
			{
				System.out.print("error_message3--- same number in the square\n");
			}
		  // only judge the first outcome error , if the error occurs on the sane point , then it both shows!
		}
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
            if (rows[i][num] ) {
            	error_number=1;
              return false;// 如果rows[i]的num出現過了 就return false!
             
            }
            rows[i][num] = true;
            if (columns[j][num]) {
            	error_number=2;
              return false;//如果columns[j]的num出現過了 就return false!
            }
            columns[j][num] = true;
            if (blocks[(i / 3) * 3 + j / 3][num]) {
            	error_number=3;
              return false;//如果blocks[i]的num出現過了 就return false! (i/3*3不等於i需考慮mode，等於類似加權的概念，控制哪個block.)
            }
            blocks[(i / 3) * 3 + j / 3][num] = true;//重置
          }
        }
        return true;
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
	                amount-=1;//as same as above, row /column/block
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
	                board[r][c] = (char)('1' + i);//ascii
	                rows[r][i] =  cols[c][i] = blks[blk][i] = true;
	                dfs(rows, cols, blks, board, amount - 1, pos + 1, flag);
	                if (flag[0]) return;// answer~
	                rows[r][i] =  cols[c][i] = blks[blk][i] = false;
	            }
	        }
	        board[r][c] = '#';// no answer , backtrack
	    }// if not dot
	}


   
	/*public void game_extender(String reader)
	{
		//System.out.print("Ok, goodbye");
		if(reader.equals("No"))
		{
			System.out.print("Ok, goodbye");
		}
		else if(reader.equals("Easy") || reader.equals("EASY"))
		{
			System.out.print("Your question is\n");
			System.out.print("# 8 # 7 9 # 4 # #\r\n"
					+ "6 # 1 # 4 2 # # #\r\n"
					+ "# 7 # 6 # # # # 8\r\n"
					+ "7 # 6 # # # # 2 #\r\n"
					+ "1 3 # # # # # 8 4\r\n"
					+ "# 2 # # # # 6 # 9\r\n"
					+ "9 # # 3 # 8 # 7 #\r\n"
					+ "# 6 # 2 1 # 8 # 3\r\n"
					+ "# # 8 # 5 7 # 6 #");
			 char[][] sudoku = new char[9][9];
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					sudoku[i][j]=sc.next().charAt(0);
				}
			}
			if(isValidSudoku(sudoku)== true)
			{
				System.out.print("Right");
			}
			if(isValidSudoku(sudoku)== false)
			{
				System.out.print("Wrong");
			}
			
			
		}
		else if(reader=="MEDIUM" || reader=="Medium")
		{
			System.out.print("Your question is");
			System.out.print("# 8 # 7 9 # 4 # #\r\n"
					+ "			6 # 1 # 4 2 # # #\r\n"
					+ "			# 7 # 6 # # # # 8\r\n"
					+ "			7 # 6 # # # # 2 #\r\n"
					+ "			1 3 # # # # # 8 4\r\n"
					+ "			# 2 # # # # 6 # 9\r\n"
					+ "			9 # # # # 8 # 7 #\r\n"
					+ "			# 6 # 2 1 # 8 # 3\r\n"
					+ "			# # 8 # 5 7 # 6 #");
		}
		else if(reader=="HARD" || reader=="Hard")
		{
			System.out.print("Your question is");
			System.out.print("# 8 # 7 9 # 4 # #\r\n"
					+ "			6 # 1 # 4 2 # # #\r\n"
					+ "			# 7 # 6 # # # # 8\r\n"
					+ "			7 # 6 # # # # 2 #\r\n"
					+ "			1 3 # # # # # 8 4\r\n"
					+ "			# 2 # # # # 6 # 9\r\n"
					+ "			9 # # # # 8 # 7 #\r\n"
					+ "			# # # 2 1 # 8 # 3\r\n"
					+ "			# # 8 # 5 7 # 6 #");
		}
	}*/
}
