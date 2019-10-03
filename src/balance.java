import java.io.*;
import java.util.StringTokenizer;

public class balance {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("balance.in"));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer str = new StringTokenizer(in.readLine());
        boolean[] arr1 = new boolean[N];
        boolean[] arr2 = new boolean[N];
        for (int i = 0; i < N; i++) arr1[i] = Integer.parseInt(str.nextToken()) == 1;
        for (int i = 0; i < N; i++) arr2[i] = Integer.parseInt(str.nextToken()) == 1;
        int p1 = 0, p2 = 0;
        int tct = 0, zct = 0;
        for (int i = 0; i < N; i++) {
            if (zct != 0 && arr1[i]) {
                p1 += tct * zct;
                tct = 1;
                zct = 0;
            } else if (arr1[i]) tct++;
            else if (!arr1[i] && tct > 0) zct++;

        }
        p1 += tct * zct;
        tct = 0;
        zct = 0;
        for (int i = 0; i < N; i++) {
            if (zct != 0 && arr2[i]) {
                p2 += tct * zct;
                tct = 1;
                zct = 0;
            } else if (arr2[i]) tct++;
            else if (!arr2[i] && tct > 0) zct++;

        }
        p2 += tct * zct;

        PrintWriter out = new PrintWriter(new File("balance.out"));
        out.print(Math.min(p1, p2));
        out.close();
    }
}
