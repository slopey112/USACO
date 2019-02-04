import java.io.*;
import java.util.*;

public class backforth {

    static HashSet<Integer> chk = new HashSet<>();
    public static void main(String[] args) throws IOException {
        int barn[] = new int[20];
        int milk[] = new int[20];
        BufferedReader in = new BufferedReader(new FileReader("backforth.in"));
        StringTokenizer line1 = new StringTokenizer(in.readLine());
        for (int i = 0; i < 10; i++) {
            barn[i] = 0;
            milk[i] = Integer.parseInt(line1.nextToken());
        }
        StringTokenizer line2 = new StringTokenizer(in.readLine());
        for (int i = 10; i < 20; i++) {
            barn[i] = 1;
            milk[i] = Integer.parseInt(line2.nextToken());
        }
        simulate(barn, milk, 0, 1000, 1000);
        FileWriter out = new FileWriter(new File("backforth.out"));
        out.write("" + chk.size());
        out.close();
    }
    public static void simulate(int[] barn, int milk[], int day, int milk1, int milk2) {
        if (day > 3) {
            chk.add(milk1);
            return;
        }
        for (int i = 0; i < 20; i++) {
            if (barn[i] % 2 == day % 2) {
                barn[i]++;
                if (day % 2 == 0) {
                    simulate(barn, milk, day+1, (milk1 - milk[i]), (milk2 + milk[i]));
                } else {
                    simulate(barn, milk, day+1, (milk1 + milk[i]), (milk2 - milk[i]));
                }
                barn[i]--;
            }
        }
    }
}