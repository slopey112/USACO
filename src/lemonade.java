import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class lemonade {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lemonade.in"));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer str = new StringTokenizer(in.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(str.nextToken());
        Arrays.sort(arr);
        int ct = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] < ct) break;
            ct++;
        }
        PrintWriter out = new PrintWriter(new File("lemonade.out"));
        out.print(ct);
        out.close();
    }
}
