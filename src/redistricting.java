import java.util.*;
import java.io.*;

public class redistricting {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("redistricting.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        str = null;
        System.gc();

        char[] d = new char[N];
        dp = new int[N];
        Arrays.fill(dp,-1);
        String st = in.readLine();
        for (int i = 0; i < N; i++) {
            d[i] = st.charAt(i);
        }
        FileWriter out = new FileWriter(new File("redistricting.out"));
        out.write("" + solve(0, d, N, k));
        out.close();
    }

    public static int solve(int index, char[] d, int N, int k) {
        if (index >= N) return 0;
        if(dp[index]!=-1)return dp[index];
        int min = Integer.MAX_VALUE, res = 0, G = 0, H = 0;
        for (int i = 0; i < k && (index+i+1) <= N; i++) {
            res = solve(index + i+1, d, N, k);
            if (d[index+i] == 'G') G++;
            else if (d[index+i] == 'H') H++;
            if (G >= H) res++;
            if (res < min) min = res;
        }
        dp[index]=min;
        return min;
    }
}