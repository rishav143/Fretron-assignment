import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int pathCount = 1;
    public static void findPaths(int i, int x, int y, int[][] board, List<String> results) {
        if(x < 0 || y < 0 || x >= board.length || y >= board.length) {
            return;
        }
        if(board[x][y] == 1) {
            results.add("Arrive at ("+x + "," + y + ")");
            printResult(results);
            return;
        }
        int newX = x + dr[i], newY = y + dc[i];
        if(x < 0 || y < 0 || x >= board.length || y >= board.length && board[x][y] == -1) {
            //jump castle over the soldier
            results.add("Jump Over ("+x + "," + y + ")");
            findPaths(i, newX, newY, board, results);
            
            //kill soldier if soldier is there
            int k = (i + 1) % 4;
            newX = x + dr[k];
            newY = y + dc[k];
            results.add("Kill at ("+x + "," + y + ")," + "then turn left");
            findPaths(k, newX, newY, board, results);
            results.remove(results.size()-1);
        }
        //otherwise move castle in the front direction
        else findPaths(i, newX, newY, board, results);
    }

    public static void printResult(List<String> results) {
        System.out.println("\nPath " + pathCount + " \n=======");
        for (String result : results) {
            System.out.println(result);
        }
        pathCount++;
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

        int board[][] = new int[row_max + 1][col_max + 1];
        for (int[] pair : coordinates) {
            board[pair[0]][pair[1]] = -1;
        }
        board[startRow][startCol] = 1;

        List<String> results = new ArrayList<>();
        results.add("Start ("+startRow + "," + startCol + ")");

        findPaths(0, startRow+1, startCol, board, results);
    }

    public static void main(String[] args) {
        findHomeInput();
    }
}
