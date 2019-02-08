import java.util.*;
import java.io.*;

public class bphoto {
    static class BIT {
        private static int[] BITree;
        private static int n;

        void update(int index) {
            //Stores 'val' at 'index'.
            index++;
            for (; index <= n; index += index & (-index)) {
                BITree[index]++;
            }
        }
        int getSum(int index) {
            //Finds prefix sum of index.
            int sum = 0;
            index++;
            for (; index > 0; index -= index & (-index)) {
                sum += BITree[index];
            }
            return sum;
        }
        BIT (int N) {
            //Optional constructor for binary index
            // tree that allows user to construct values themselves.
            n = N;
            BITree = new int[N+1];
        }
    }
    static class Cow implements Comparable<Cow> {
        private int height, index;
        private Cow (int height, int index) {
            this.height = height;
            this.index = index;
        }
        public int compareTo(Cow a) {
            return a.height - height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("bphoto.in"));
        int N = Integer.parseInt(in.readLine());
        Cow[] cows = new Cow[N];
        for (int i = 0; i < N; i++) cows[i] = new Cow(Integer.parseInt(in.readLine()), i);

        BIT sums = new BIT(N);
        Arrays.sort(cows);
        int ans = 0, c = 0;
        for (Cow now : cows) {
            int L = sums.getSum(now.index);
            int R = c - L;

            if (Math.max(L, R) > 2 * Math.min(L, R)) ans++;

            c++;
            sums.update(now.index);
        }
        PrintWriter out = new PrintWriter(new File("bphoto.out"));
        out.print(ans);
        out.close();
    }
}