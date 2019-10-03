import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class mountains {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("mountains.in"));
        int N = Integer.parseInt(in.readLine());
        int[][] peaks = new int[N][2];
        ArrayList<ArrayList<Integer>> bvals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer str = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());

            peaks[i][0] = x;
            peaks[i][1] = y;

        }
        Arrays.sort(peaks, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    int Ax = a[1] - a[0];
                    int Bx = b[1] - b[0];
                    if (Ax < 0) Ax = 0;
                    if (Bx < 0) Bx = 0;
                    int Aa = ((a[0] + a[1] - Ax) * a[1]) / 2;
                    int Ba = ((b[0] + b[1] - Bx) * b[1]) / 2;
                    return Integer.compare(Ba, Aa);
                } else {
                    return Integer.compare(b[1], a[1]);
                }
            }
        });
        printArray(peaks);
        int ct = N;
        for (int i = 1; i < N; i++) {
            int b1 = peaks[i-1][1] - peaks[i-1][0];
            int b2 = peaks[i-1][1] + peaks[i-1][0];
            if (checkBlock(b1, b2, peaks[i][0], peaks[i][1])) {
                ct--;
            } else {
                for (int j = 0; j < bvals.size(); j++) {
                    if (checkBlock(bvals.get(j).get(0), bvals.get(j).get(1), peaks[i][0], peaks[i][1])) {
                        ct--;
                        break;
                    }
                }
                bvals.add(new ArrayList<>(Arrays.asList(b1, b2)));
            }
        }
        FileWriter out = new FileWriter(new File("mountains.out"));
        out.write("" + ct);
        out.close();
    }
    public static void printArray(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.println(grid[i][0] + " " + grid[i][1]);
        }
    }
    public static boolean checkBlock(int b1, int b2, int x, int y) {
        if (y <= x + b1 && y <= (-1 * x) + b2) {
            return true;
        }
        return false;
    }
}
