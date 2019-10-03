import java.util.*;
import java.io.*;

public class checklist {
    private final int[][] hol;
    private final int[][] gur;
    private final int H;
    private final int G;
    private final boolean HOL = true;
    private final boolean GUR = false;
    private int[][] dpH;
    private int[][] dpG;

    public checklist (FileReader a) throws IOException {
        BufferedReader in = new BufferedReader(a);
        StringTokenizer str = new StringTokenizer(in.readLine());
        H = Integer.parseInt(str.nextToken());
        G = Integer.parseInt(str.nextToken());
        hol = new int[H][2];
        gur = new int[G][2];
        dpH = new int[H+1][G+1];
        dpG = new int[H+1][G+1];
        for (int i = 0; i < H; i++) {
            Arrays.fill(dpH[i], -1);
            Arrays.fill(dpG[i], -1);
        }
        for (int i = 0; i < H; i++) {
            str = new StringTokenizer(in.readLine());
            hol[i][0] = Integer.parseInt(str.nextToken());
            hol[i][1] = Integer.parseInt(str.nextToken());
        }
        for (int i = 0; i < G; i++) {
            str = new StringTokenizer(in.readLine());
            gur[i][0] = Integer.parseInt(str.nextToken());
            gur[i][1] = Integer.parseInt(str.nextToken());
        }
    }

    private int mine(int i, int j, boolean z) {
        if (i >= H - 1 && j >= G - 1) return 0;
        if (z == HOL && dpH[i+1][j+1] != -1) return dpH[i+1][j+1];
        if (z == GUR && dpG[i+1][j+1] != -1) return dpG[i+1][j+1];

        int ans = 0;

        int x = (z == HOL) ? hol[i][0] : gur[j][0];
        int y = (z == HOL) ? hol[i][1] : gur[j][1];

        int tmp1 = Integer.MAX_VALUE, tmp2 = Integer.MAX_VALUE;

        if (j + 1 >= G) tmp1 = mine(i + 1, j, HOL) + dis(x, y, hol[i+1]);
        else if (i + 1 >= H - 1) tmp2 = mine(i, j + 1, GUR) + dis(x, y, gur[j+1]);
        else {
            tmp1 = mine(i + 1, j, HOL) + dis(x, y, hol[i+1]);
            tmp2 = mine(i, j + 1, GUR) + dis(x, y, gur[j+1]);
        }

        ans += Math.min(tmp1, tmp2);

        if (z == HOL) dpH[i+1][j+1] = ans;
        else dpG[i+1][j+1] = ans;

        return ans;
    }

    public int mine() {
        int x = hol[0][0];
        int y = hol[0][1];

        int tmp1 = mine(1, -1, HOL) + dis(x, y, hol[1]);
        int tmp2 = mine(0, 0, GUR) + dis(x, y, gur[0]);

        return Math.min(tmp1, tmp2);
    }

    private int dis(int x, int y, int[] arr) {
        return (int) (Math.pow(arr[0] - x, 2) + Math.pow(arr[1] - y, 2));
    }

    public static void main(String[] args) throws IOException {
        checklist test = new checklist(new FileReader("checklist.in"));
        PrintWriter out = new PrintWriter(new File("checklist.out"));
        out.print(test.mine());
        out.close();
    }
}