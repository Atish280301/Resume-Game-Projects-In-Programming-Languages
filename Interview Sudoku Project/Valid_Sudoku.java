import java.util.*;
public class Valid_Sudoku {
	public static boolean function(char[][]ar) {
		HashSet hs = new HashSet();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(ar[i][j] != '.') {
					if(!hs.add("row"+i+ar[i][j]) || !hs.add("column"+j+ar[i][j]))
						return false;
					if(!hs.add("box" + (i / 3) * 3 + j / 3 + ar[i][j]))
						return false;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[][] array = new char[9][9];
		System.out.println("Enter Elements : ");
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				array[i][j] = in.nextLine().charAt(0);
			}
		}
		System.out.println("The Array Is : ");
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.println(array[i][j]+" ");
			}
		}
		boolean output = function(array);
		System.out.println("Output : "+output);
	}
}
