import java.util.*;
import java.io.*;

public class cardgame {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cardgame.in"));
        int N = Integer.parseInt(in.readLine());
        boolean[] bestmp = new boolean[2*N];
        int[] els1 = new int[N/2];
        int[] els2 = new int[N/2];
        int[] bes = new int[N];

        for (int i = 0; i < N / 2; i++) {
            int t = Integer.parseInt(in.readLine()) - 1;
            bestmp[t] = true;
            els1[i] = t;
        }
        for (int i = N / 2; i < N; i++) {
            int t = Integer.parseInt(in.readLine()) - 1;
            bestmp[t] = true;
            els2[i-N/2] = t;
        }

        int mark = 0;
        for (int i = 0; i < N * 2; i++) {
            if (!bestmp[i]) {
                bes[mark] = i;
                mark++;
            }
        }
        //els1 are the cards Elsie can play the first half, and
        //els2 are the cards Elise can play the second half.

        //bes are the cards Bessie can play at any time.
        Arrays.sort(els1);
        Arrays.sort(els2);
        Arrays.sort(bes);

        int ct = 0;
        for (int i = 0, j = N/2; i < N/2; i++, j++, ct++) {
            while (j < N && bes[j] < els1[i]) j++;
            if (j == N) break;
        }

        for (int i = N/2-1, j = N/2-1; i >= 0; i--, j--, ct++) {
            while (j >= 0 && bes[j] > els2[i]) j--;
            if (j == -1) break;
        }

        PrintWriter out = new PrintWriter(new File("cardgame.out"));
        out.print(ct);
        out.close();
    }
}
