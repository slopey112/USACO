import java.io.*;

public class perimeter {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("perimeter.in"));
        int N = Integer.parseInt(in.readLine());
        int[][] grid = new int[N][N];
        boolean[][] mark = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            for (int j = 0; j < N; j++) {
                if (str.charAt(j) == '#') {
                    mark[i][j] = true;
                    grid[i][j] = 1;
                }
            }
        }
        int area = 0, peri = 10000000;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    int[] ans = floodfill(grid, mark, i, j);
                    if (ans[0] > area) {
                        area = ans[0];
                        peri = ans[1];
                    } else if (ans[0] == area && ans[1] < peri) {
                        area = ans[0];
                        peri = ans[1];
                    }
                }
            }
        }
        FileWriter out = new FileWriter(new File("perimeter.out"));
        out.write(area + " " + peri);
        out.close();
    }/*
    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
    public static void printGrid(boolean[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }*/
    public static int[] floodfill(int[][] grid, boolean[][] mark, int row, int col) {
        if (row >= grid.length || row < 0 || col >= grid.length || col < 0 || !mark[row][col]) return new int[] {0, 0};
        mark[row][col] = false;
        int[] ans = {1, 0};
        if (row+1 >= grid.length || grid[row+1][col] == 0) ans[1]++;
        if (row-1 < 0 || grid[row-1][col] == 0) ans[1]++;
        if (col+1 >= grid.length || grid[row][col+1] == 0) ans[1]++;
        if (col-1 < 0 || grid[row][col-1] == 0) ans[1]++;
        int[] ans1 = floodfill(grid, mark,row+1, col);
        ans[0] += ans1[0];
        ans[1] += ans1[1];
        ans1 = floodfill(grid, mark, row-1, col);
        ans[0] += ans1[0];
        ans[1] += ans1[1];
        ans1 = floodfill(grid, mark, row, col+1);
        ans[0] += ans1[0];
        ans[1] += ans1[1];
        ans1 = floodfill(grid, mark, row, col-1);
        ans[0] += ans1[0];
        ans[1] += ans1[1];
        return ans;
    }
}
