import java.io.*;
import java.util.StringTokenizer;

public class shell {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("shell.in"));
        int N = Integer.parseInt(in.readLine());
        int[][] vals = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer str = new StringTokenizer(in.readLine());
            vals[i][0] = Integer.parseInt(str.nextToken());
            vals[i][1] = Integer.parseInt(str.nextToken());
            vals[i][2] = Integer.parseInt(str.nextToken());
        }

        int pos1 = simulate(vals, 1, N);
        int pos2 = simulate(vals, 2, N);
        int pos3 = simulate(vals, 3, N);

        FileWriter out = new FileWriter(new File("shell.out"));
        out.write("" + Math.max(Math.max(pos1, pos2), Math.max(pos2, pos3)));
        out.close();
    }

    public static int simulate(int[][] pos, int initial, int N) {
        int[] shell = new int[3];
        shell[initial-1] = 1;
        int ct = 0;
        for (int i = 0; i < N; i++) {
            int a = pos[i][0];
            int b = pos[i][1];
            int g = pos[i][2];
            int tmp = shell[b-1];
            shell[b-1] = shell[a-1];
            shell[a-1] = tmp;
            if (shell[g-1] == 1) {
                ct ++;
            }
        }
        return ct;
    }
}
