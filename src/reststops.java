import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class reststops {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("reststops.in"));
        int L = in.nextInt(), N = in.nextInt(), rf = in.nextInt(), rb = in.nextInt(), result = 0;
        int[][] rest = new int[N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                rest[i][j] = in.nextInt();
            }
        }
        in.close();
        Arrays.sort(rest, new Comparator<int[]>() {
            public int compare(final int[] a, final int[] b){
                if (a[1] == b[1] || a[1] > b[1]) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        int counter = rest[0][1] * (rest[0][0] * (rf - rb));
        for (int i = 1; i < N; i++) {
            if (rest[i][0] > rest[i-1][0]) {
                counter += rest[i][1] * (rest[i][0] - rest[i-1][0]) * (rf - rb);
            } else {
                rest[i][0] = rest[i-1][0];
            }
        }
        System.out.println(Arrays.deepToString(rest));
        FileWriter out = new FileWriter(new File("reststops.out"));
        out.write("" + counter);
        out.close();
    }
}