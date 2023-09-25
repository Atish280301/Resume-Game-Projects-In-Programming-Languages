import java.util.Scanner;
public class Sudoku_Solver {
	public static boolean function1(char[][] board, int row, int col, int num)
	{
		for(int i = 0; i < board.length; i++) {
			if(board[i][col] == (char)(num + '0'))
				return false;
			if(board[row][i] == (char)(num + '0'))
				return false;
		}
		int sr = (row / 3) * 3, sc = (col / 3) * 3;
		for(int i = sr; i < sr + 3; i++)
		{
			for(int j = sc; j < sc + 3; j++)
			{
				if(board[i][j] == (char)(num + '0'))
					return false;
			}
		}
		return true;
	}
	public static boolean function2(char[][] board, int row, int col) {
		if(row == board.length)
			return true;
		int r = 0, c = 0;
		if(col != board.length - 1) {
			r = row;
			c = col + 1;
		}
		else {
			r = row + 1;
			c = 0;
		}
		if(board[row][col] != '.') {
			if(function2(board, r, c)) {
				return true;
			}
		}
		else {
			for(int i = 1 ; i <= 9; i++) {
				if(function1(board, row, col, i)) {
					board[row][col] = (char)(i + '0');
					if(function2(board,r,c)) {
						return true;
					}
					else {
						board[row][col] = '.';
					}
				}
			}
		}
		return false;
	}
	public static void function(char[][] array) {
		function2(array, 0, 0);
	}
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		char[][] array = new char[9][9];
		System.out.println("Enter Elements : ");
		for(int i = 0; i < 9 ; i++) {
			for(int j = 0; j < 9; j++) {
				array[i][j] = in.nextLine().charAt(0);
			}
		}
		function(array);
		for(int i = 0; i < 9 ; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.println(array[i][j]+" ");
			}
		}
	}
}
