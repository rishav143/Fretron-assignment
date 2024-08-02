import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void findPaths(char dir, int x, int y, int startX, int startY, int[][] board, String s, List<String> results) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return;
        }
        if (x == startX && y == startY && s.contains("Kill")) {
            results.add(s + "Arrive (" + x + "," + y + ")\n");
            return;
        }

        int newX = x, newY = y;
        String newS = s;
        char newDir = dir;

        switch (dir) {
            case 'D':
                newX += 1;
                break;
            case 'R':
                newY += 1;
                break;
            case 'L':
                newY -= 1;
                break;
            case 'U':
                newX -= 1;
                break;
        }

        if (newX < 0 || newY < 0 || newX >= board.length || newY >= board[0].length) {
            return;
        }

        if (board[newX][newY] == -1) {
            String str = "Jump over (" + newX + "," + newY + ").\n";
            findPaths(dir, newX, newY, startX, startY, board, newS + str, results);

            switch (dir) {
                case 'D':
                    newDir = 'R';
                    newY += 1;
                    break;
                case 'R':
                    newDir = 'U';
                    newX -= 1;
                    break;
                case 'L':
                    newDir = 'D';
                    newX += 1;
                    break;
                case 'U':
                    newDir = 'L';
                    newY -= 1;
                    break;
            }
            if (newX >= 0 && newY >= 0 && newX < board.length && newY < board[0].length && board[newX][newY] != -1) {
                str = "Kill at (" + newX + "," + newY + "), then turn left.\n";
                board[newX][newY] = 0; // Mark the soldier as killed
                findPaths(newDir, newX, newY, startX, startY, board, newS + str, results);
                board[newX][newY] = -1; // Restore the soldier
            }
        } else {
            findPaths(dir, newX, newY, startX, startY, board, newS, results);
        }
    }

    public static void findHomeInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("find_my_home_castle -soldiers ");
        int soldiers = sc.nextInt();
        List<int[]> coordinates = new ArrayList<>();
        int row_max = 0, col_max = 0;
        for (int i = 0; i < soldiers; i++) {
            System.out.print("Enter coordinates for soldier " + (i + 1) + ": ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            row_max = Math.max(row_max, row);
            col_max = Math.max(col_max, col);
            coordinates.add(new int[]{row, col});
        }

        System.out.print("Enter the coordinates for your \"special\" castle: ");
        int startRow = sc.nextInt();
        int startCol = sc.nextInt();

        int board[][] = new int[row_max + 2][col_max + 2]; // extra space for boundary
        for (int[] pair : coordinates) {
            board[pair[0]][pair[1]] = -1;
        }
        board[startRow][startCol] = 1;

        String s = "Start (" + startRow + "," + startCol + ")\n";
        List<String> results = new ArrayList<>();
        findPaths('D', startRow + 1, startCol, startRow, startCol, board, s, results);
        findPaths('R', startRow, startCol + 1, startRow, startCol, board, s, results);
        findPaths('L', startRow, startCol - 1, startRow, startCol, board, s, results);
        findPaths('U', startRow - 1, startCol, startRow, startCol, board, s, results);

        System.out.println("Thanks. Here are the unique paths for your ‘special_castle’:");
        int pathCount = 1;
        for (String result : results) {
            System.out.println("\nPath " + pathCount + " \n=======");
            System.out.println(result);
            pathCount++;
        }
    }

    public static void main(String[] args) {
        findHomeInput();
    }
}

// import java.util.Scanner;
// import java.util.List;
// import java.util.ArrayList;
// public class Main
// {
//     public static void findPaths(char dir, int x, int y, int [][] board, String s) {
//         if(x > 0 && y > 0 && x < board.length && y < board.length) {
//             if(board[x][y] == 1) {
//                 print_result(s);
//                 return;
//             }
//             int newX = x, newY = y;
//             String news = "";
//             char newDir = '';
//             switch(dir) {
//                 case 'D':
//                     newX += 1;
//                 case 'R':
//                     newY += 1;
//                 case 'L':
//                     newY -= 1;
//                 case 'U':
//                     newX -= 1;
//             }
//             while(true) {
//                 if(board[x][y] == -1) {
//                     switch(dir) {
//                         case 'D':
//                             newDir = 'R';
//                             newY += 1;
//                         case 'R':
//                             newDir = 'U';
//                             newX -= 1;
//                         case 'L':
//                             newDir = 'D';
//                             newX += 1;
//                         case 'U':
//                             newDir = 'L';
//                             newY -= 1;
//                     }
//                     ns = "kill at "+"("+x+","+y+")"+"," + "then turn left.\n";
//                     findPaths(newDir, newX, newY, board, s);
//                 } else {
//                     findPaths(dir, newX, newY, board, s);
//                 }
//             }
//         }
//     }
    
//     public static void findHomeInput() {
//         Scanner sc = new Scanner(System.in);
//         System.out.print("find_my_home_castle -soldiers ");
//         int soldiers = sc.nextInt();
//         List<List<Integer>> cord = new ArrayList<>();
//         int row_max = 0, col_max = 0;
//         for(int i = 0; i < soldiers; i++) {
//             System.out.print("Enter coordinates for soldier "+(i+1)+": ");
//             List<Integer> pair = new ArrayList<>();
//             int row = sc.nextInt();
//             int col = sc.nextInt();
//             pair.add(row);
//             pair.add(col);
            
//             row_max = Math.max(row_max, row);
//             col_max = Math.max(col_max, col);
            
//             cord.add(pair);
//         }
        
//         System.out.print("Enter the coordinates for your \"special\" castle: ");
//         int startRow = sc.nextInt();
//         int startCol = sc.nextInt();
//         List<Integer> ans = new ArrayList<>();
//         int board[][] = new int[row_max+1][col_max+1];
//         for(int i = 0; i < cord.size(); i++) {
//             board[cord.get(i).get(0)][cord.get(i).get(1)] = -1;
//         }
        
//         board[startRow][startCol] = 1;
//         String s = "Start " + "("+startRow+","+startCol+")\n";
//         findPaths('D', startRow+1, startCol, board, ans);
//     }
    
// 	public static void main(String[] args) {
// 		findHomeInput();
// 	}
// }

// // int newX = x, newY = y;
// //             String s = "";
// //             char newDir = '';
// //             if(board[x][y] == -1) {
// //                 switch(dir) {
// //                     case 'D':
// //                         newDir = 'R';
// //                         newY += 1;
// //                     case 'R':
// //                         newDir = 'U';
// //                         newX -= 1;
// //                     case 'L':
// //                         newDir = 'D';
// //                         newX += 1;
// //                     case 'U':
// //                         newDir = 'L';
// //                         newY -= 1;
// //                 }
// //                 s = "kill at "+"("+x+","+y+")"+"," + "then turn left"
// //                 findPaths(newDir, newX, newY, board, ans);
// //             }
