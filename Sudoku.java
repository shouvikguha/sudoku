public class Sudoku{
    private static final int SIZE_OF_GRID = 9;

    public static void main(String[] args){
        int [][] grid = {
            {0, 0, 0,    3, 5, 6,    2, 0, 0},
            {4, 0, 2,    0, 0, 0,    5, 0, 0},
            {0, 7, 0,    0, 0, 0,    0, 1, 0},
            {8, 0, 0,    0, 4, 0,    0, 7, 0},
            {0, 5, 0,    0, 3, 0,    0, 0, 0},
            {0, 0, 0,    0, 0, 0,    3, 0, 9},
            {0, 0, 4,    1, 6, 5,    0, 0, 0},
            {0, 0, 9,    0, 0, 0,    0, 0, 0},
            {1, 6, 0,    7, 0, 0,    0, 0, 0}
        }; 
        
        printSudoku(grid);

        if (solveSudoku(grid)) {
            System.out.println("\nSudoku puzzle solved successfully! 🤩\n");
        }
        else {
            System.out.println("\nSudoku puzzle could not be solved... 😭\n");
        }
          
        printSudoku(grid);
        
    }

    private static boolean repeatinRow(int [][] grid, int row, int num){
        for(int i = 0; i< SIZE_OF_GRID; i++){
            if(grid[row][i] == num){
                return true;
            }
        }
        return false;
    }

    private static boolean repeatinCol(int [][] grid, int col, int num){
        for(int i = 0; i< SIZE_OF_GRID; i++){
            if(grid[i][col] == num){
                return true;
            }
        }
        return false;
    }


    private static boolean repeatinSquare(int [][] grid, int row, int col, int num){
        int localSquareCol = col - col%3;
        int localSquareRow = row - row%3; 
        // n−(nmodx)=(qx+r)−r=qx
        for(int i = localSquareRow; i<localSquareRow+3; i++){
            for(int j = localSquareCol; j<localSquareCol+3; j++){
                if(grid[i][j] == num){
                    return true;
                }
            }
        }
        return false;
    } 

    private static boolean validityCheck(int[][] grid, int row, int col, int num){
        return !repeatinRow(grid, row, num) && !repeatinCol(grid, col, num) && !repeatinSquare(grid, row, col, num);
    }

    private static boolean solveSudoku(int[][] grid) {
        for (int row = 0; row < SIZE_OF_GRID; row++) {
          for (int col = 0; col < SIZE_OF_GRID; col++) {
            if (grid[row][col] == 0) {
              for (int numberAttempt = 1; numberAttempt <= SIZE_OF_GRID; numberAttempt++) {
                if (validityCheck(grid, row, col, numberAttempt)) {
                  grid[row][col] = numberAttempt;
                  
                  if (solveSudoku(grid)) {
                    return true;
                  }
                  else {
                    grid[row][col] = 0;
                  }
                }
              }
              return false;
            }
          }
        }
        return true;
      }


    private static void printSudoku(int[][] grid) {
        for (int row = 0; row < SIZE_OF_GRID; row++) {
          if (row % 3 == 0 ) {
            System.out.println("-------------------------------");
          }
          for (int col = 0; col < SIZE_OF_GRID; col++) {
            if (col % 3 == 0) {
              System.out.print("|");
            }
            System.out.print(" "+ grid[row][col]+" ");
            if (col == 8){
                System.out.print("|");
            }
          }
          System.out.print("\n");
          if(row == 8){
            System.out.println("-------------------------------");
          }
        }
      }
}