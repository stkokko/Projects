import acm.program.*;
public class Sudoku extends Program {
	public void run() {
		int[][] grid = new int[9][9];
		grid[0][2] = 2;
		grid[0][3] = 4;
		grid[0][5] = 5;
		grid[0][6] = 8;
		grid[1][1] = 4;
		grid[1][2] = 1;
		grid[1][3] = 8;
		grid[1][7] = 2;
		grid[2][0] = 6;
		grid[2][4] = 7;
		grid[2][7] = 3;
		grid[2][8] = 9;
		grid[3][0] = 2;
		grid[3][4] = 3;
		grid[3][7] = 9;
		grid[3][8] = 6;
		grid[4][2] = 9;
		grid[4][3] = 6;
		grid[4][5] = 7;
		grid[4][6] = 1;
		grid[5][0] = 1;
		grid[5][1] = 7;
		grid[5][4] = 5;
		grid[5][8] = 3;
		grid[6][0] = 9;
		grid[6][1] = 6;
		grid[6][4] = 8;
		grid[6][8] = 1;
		grid[7][1] = 2;
		grid[7][5] = 9;
		grid[7][6] = 5;
		grid[7][7] = 6;
		grid[8][2] = 8;
		grid[8][3] = 3;
		grid[8][5] = 6;
		grid[8][6] = 9;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(grid[i][j] == 0) {
					grid[i][j] = readInt("Enter a number from 1 to 9 so that this number appears only once at " + i + " line, " + j + " column and inside each 3x3 box: ");
					while(grid[i][j] < 1 || grid[i][j] > 9) {
						grid[i][j] = readInt("Be carefull!You have to enter a number from 1 to 9: ");
					}
				}
			}
		}
		if(checkSudokuSolution(grid)) {
			println("Good job!You solve it!");
		}else{
			println("You failed to solve it.Nice try!");
		}
	}
	private boolean checkSudokuSolution(int[][] grid) {
		boolean sol_box = true;
		boolean sol_lin = true;
		boolean sol_col = true;
		int[] Col = new int[9];
		int[] Lin = new int[9];
		int[] Box = new int[9];
		int n = 0;
		int i , j;
		for(i = 0; i < 9; i++) {
			for(j = 0; j < 9; j++) {
				Lin[n] = Lin[n] + grid[i][j];
				Col[n] = Col[n] + grid[j][i];
		    }
			n++;
		}
		for(i = 0; i < 9; i++) {
			if(Lin[i] != 45) sol_lin = false;
			if(Col[i] != 45) sol_col = false;
		}
		int k = 0;
		int m , l;
		for(i = 0; i < 3; i++) {
			for(j = 0; j < 3; j++) {
				Box[k] = Box[k] + grid[i][j];
			}
			for(m = 3; m < 6; m++) {
				Box[k + 1] = Box[k + 1] + grid[i][m];
			}
			for(l = 6; l < 9; l++) {
				Box[k + 2] = Box[k + 2] + grid[i][l];
			}
		}
		k = k + 3;
		for(i = 3; i < 6; i++) {
			for(j = 0; j < 3; j++) {
				Box[k] = Box[k] + grid[i][j];
			}
			for(m = 3; m < 6; m++) {
				Box[k + 1] = Box[k + 1] + grid[i][m];
			}
			for(l = 6; l < 9; l++) {
				Box[k + 2] = Box[k + 2] + grid[i][l];
			}
		}
		k = k + 3;
		for(i = 6; i < 9; i++) {
			for(j = 0; j < 3; j++) {
				Box[k] = Box[k] + grid[i][j];
			}
			for(m = 3; m < 6; m++) {
				Box[k + 1] = Box[k + 1] + grid[i][m];
			}
			for(l = 6; l < 9; l++) {
				Box[k + 2] = Box[k + 2] + grid[i][l];
			}
		}
		for(i = 0; i < 9; i++) {
			if(Box[i] != 45) sol_box = false;
		}
		return sol_box && sol_lin && sol_col;
	}
}
		
		
				
					
				
		