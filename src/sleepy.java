import java.util.*;
import java.io.*;

class BIT {
    public static int BITree[];
    static int n;

    public static void update(int index) {
        //Stores 'val' at 'index'.
        for (index++; index <= n; index += index & (-index)) {
            BITree[index]++;
        }
    }

    public int getSum(int index) {
        //Finds prefix sum of index.
        int sum = 0;
        for (index++; index > 0; index -= index & (-index)) {
            sum += BITree[index];
        }
        return sum;
    }

    public BIT (int arr[]) {
        //Constructor for binary index tree.
        n = arr.length;
        BITree = new int[n+1];
        for (int i = 1; i <= n; i++) {
            BITree[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            update(i);
        }
    }
    public BIT (int n) {
        this.n = n;
        BITree = new int[n+1];
        for (int i = 1; i <= n; i++) {
            BITree[i] = 0;
        }
    }
}

public class sleepy {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sleepy.in"));
        int N = Integer.parseInt(in.readLine());
        int[] init = new int[N];
        StringTokenizer str = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            init[i] = Integer.parseInt(str.nextToken());
            init[i]--;
        }
        PrintWriter out = new PrintWriter(new File("sleepy.out"));
        int k = N - 1;
        while (k > 0 && init[k-1] < init[k]) {
            k--;
        }
        out.println(k);
        int sorted = N - k;
        BIT cows = new BIT(N);
        for (int i = k; i < N; i++) {
            cows.update(init[i]);
        }
        for (int i = 0; i < k; i++) {
            out.print((k - 1 - i) + cows.getSum(init[i]));
            if (i != k - 1) out.print(" ");
            cows.update(init[i]);
        }
        out.close();
    }
}