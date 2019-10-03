import java.io.*;
import java.util.StringTokenizer;

public class planting {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("planting.in"));
        int N = Integer.parseInt(in.readLine());
        int[] freq = new int[N];
        for (int i = 0; i < N-1; i++) {
            StringTokenizer str = new StringTokenizer(in.readLine());
            int num1 = Integer.parseInt(str.nextToken());
            int num2 = Integer.parseInt(str.nextToken());
            freq[num1-1]++;
            freq[num2-1]++;
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (freq[i] > max) {
                max = freq[i];
            }
        }
        FileWriter out = new FileWriter(new File("planting.out"));
        out.write("" + (max+1));
        out.close();
    }
}
