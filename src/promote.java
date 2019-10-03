import java.io.*;
import java.util.StringTokenizer;

public class promote {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("promote.in"));
        int[] dif = new int[4];
        for (int i = 0; i < 4; i++) {
            StringTokenizer a = new StringTokenizer(in.readLine());
            dif[i] = (Integer.parseInt(a.nextToken()) - Integer.parseInt(a.nextToken())) * -1;
        }
        int store = 0;
        int[] results = new int[3];
        for (int i = 3; i > 0; i--) {
            if (dif[i] >= 0) {
                results[i-1] = store + dif[i];
                store += dif[i];
            } else {
                int output = store - Math.abs(dif[i]);
                results[i-1] = output;
                store = output;
            }
        }
        PrintWriter out = new PrintWriter(new File("promote.out"));
        for (int i = 0; i < 3; i++) {
            out.println(results[i]);
        }
        out.close();
    }
}