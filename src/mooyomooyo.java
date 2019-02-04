import java.util.*;
import java.io.*;

public class mooyomooyo {
  public static void main (String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader("mooyomooyo.in"));
    StringTokenizer str1 = new StringTokenizer(in.readLine());
    int N = Integer.parseInt(str1.nextToken());
    int K = Integer.parseInt(str1.nextToken());
    int[][] grid = new int[N][10];
    for (int i = 0; i < N; i++) {
      String str = in.readLine();
      for (int j = 0; j < 10; j++) {
        grid[i][j] = str.charAt(j)-'0';
      }
    }
    while (true) {
      boolean stop = true;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < 10; j++) {
          if (grid[i][j] != 0) {
            int count = floodfill(grid, grid[i][j], i, j, false);
            if (count >= K) {
              floodfill(grid, grid[i][j], i, j, true);
              stop=false;
            }
          }
        }
      }
      applygravity(grid);
      if(stop)break;
  }
    PrintWriter out = new PrintWriter(new File("mooyomooyo.out"));
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < 10; j++) {
        out.print(grid[i][j]);
      }
      out.println();
    }
    out.close();
  }
  public static int floodfill(int[][] grid, int a, int row, int col, boolean remove) {
    if (row >= grid.length || row < 0 || col >= 10 || col < 0 || grid[row][col] != a) {
      return 0;
    }
    int ans = 1;
    grid[row][col] = 0;
    ans += floodfill(grid, a, row + 1, col, remove);
    ans += floodfill(grid, a, row - 1, col, remove);
    ans += floodfill(grid, a, row, col + 1, remove);
    ans += floodfill(grid, a, row, col - 1, remove);
    if (!remove) {
      grid[row][col] = a;
    }
    return ans;
  }
  public static void applygravity(int[][] grid) {
    for (int i = 0; i < 10; i++) {
      int ct = grid.length - 2;
      for (int j = grid.length - 1; j >= 0; j--) {
        if (grid[j][i] == 0) {
          while (grid[ct][i] == 0 && ct > 0) {
            ct--;
          }
          grid[j][i] = grid[ct][i];
          grid[ct][i] = 0;
        }
      }
    }
  }/*
  public static void print2darray(int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < 10; j++) {
        System.out.print(grid[i][j]);
      }
      System.out.println();
    }
  }*/
}
