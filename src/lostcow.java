import java.io.*;
import java.util.StringTokenizer;

public class lostcow {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lostcow.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(str.nextToken());
        int y = Integer.parseInt(str.nextToken());
        int[] pos = new int[1000];
        pos[x] = 2;
        pos[y] = 1;
        int counter = 0;
        boolean notFound = true;
        int dist = 1;
        while (notFound = true) {
            if (dist > 0) {
                for (int i = pos[x]; i < dist; i++) {
                    if (pos[i] == 1) {
                        notFound = false;
                        break;
                    }
                    if (i == 999) {
                        break;
                    }
                    counter++;
                }
            } else {
                for (int i = pos[x]; i > Math.abs(dist); i++) {
                    if (pos[i] == 1) {
                        notFound = false;
                        break;
                    }
                    if (i == 0) {
                        break;
                    }
                    counter++;
                }
            }
            dist = dist * -2;
        }
        FileWriter out = new FileWriter(new File("lostcow.out"));
        out.write("" + counter);
        out.close();
    }
}