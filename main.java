import java.util.Scanner;
import java.util.ArrayList;
public class main {
	public static void main(String []args)
	{
	System.out.print("Please input your sudoku! If there isn't a number, put '#' to represent\nEX:\n");
	System.out.print("1 2 3 4 5 6 7 8 9\r\n"
			+ "# # # # # # # # #\r\n"
			+ "# # # # # # # # #\r\n"
			+ "# # # # # # # # #\r\n"
			+ "# # # # # # # # #\r\n"
			+ "# # # # # # # # #\r\n"
			+ "# # # # # # # # #\r\n"
			+ "# # # # # # # # #\r\n"
			+ "# # # # # # # # #");


	Scanner sc=new Scanner(System.in);
	char[][] sudoku = new char[9][9];
	for(int i=0;i<9;i++)
	{
		for(int j=0;j<9;j++)
		{
			sudoku[i][j]=sc.next().charAt(0);
		}
	}
	board board1=new board(sudoku);
	
	while(board1.checker(sudoku)==1)
	{
		System.out.print("There is no answer bro,please put a new one");
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				sudoku[i][j]=sc.next().charAt(0);
			}
		}
	}
	board1.solveSudoku(sudoku);
	board1.printer(sudoku);
	System.out.print("\nThere will be extender! Please input the same sudoku." +"\n");
	extender extender1=new extender(sudoku);
	for(int i=0;i<9;i++)
	{
		for(int j=0;j<9;j++)
		{
			sudoku[i][j]=sc.next().charAt(0);
		}
	}
	System.out.print("\n");
	while(extender1.isValidSudoku(sudoku)==false)
	{
		extender1.checker_extender(sudoku);
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				sudoku[i][j]=sc.next().charAt(0);
			}
		}
	}
	extender1.solveSudoku(sudoku);
	extender1.printer_extender(sudoku);
	
	
	
	//System.out.print("Do you want to play sudoku by yourself? If yes, type EASY, MEDIUM OR HARD.");
	//String reader=sc.next();
	//extender1.game_extender(reader);
	
	
	
	}

	
	
	
	

}
