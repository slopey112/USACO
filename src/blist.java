import java.io.*;
import java.util.*;

public class blist {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("blist.in"));
        int N = Integer.parseInt(in.readLine());
        int[][] cows = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer str = new StringTokenizer(in.readLine());
            for (int j = 0; j < 3; j++) {
                cows[i][j] = Integer.parseInt(str.nextToken());
            }
        }
        Arrays.sort(cows, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int[] times = new int[cows[N-1][1]];
        for (int i = 0; i < N; i++) {
            for (int j = cows[i][0]; j <= cows[i][1]; j++) {
                times[j-1] += cows[i][2];
            }
        }
        int compare = 0;
        for (int i = 0; i < times.length; i++) {
            if (times[i] > compare) compare = times[i];
        }
        FileWriter out = new FileWriter(new File("blist.out"));
        out.write("" + compare);
        out.close();
    }
}
