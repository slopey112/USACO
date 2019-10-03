import java.io.*;
import java.util.StringTokenizer;

public class pails {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("pails.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        int X = Integer.parseInt(str.nextToken());
        int Y = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());
        int tries = M / Math.min(X, Y);
        int check = 0;
        for (int i = 0; i < tries; i++) {
            int XTries = X * i;
            int YTries = Y * (tries - i);
            int total = XTries + YTries;
            if (total > check && total <= M) check = total;
        }
        FileWriter out = new FileWriter(new File("pails.out"));
        out.write("" + check);
        out.close();
    }
}