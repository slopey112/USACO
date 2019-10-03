import java.io.*;
import java.util.StringTokenizer;

public class teleport {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("teleport.in"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(str.nextToken());
        int b = Integer.parseInt(str.nextToken());
        int x = Integer.parseInt(str.nextToken());
        int y = Integer.parseInt(str.nextToken());
        int normalDist = Math.abs(a-b);
        int tpDist1 = Math.abs(Math.min(a, b) - Math.min(x, y));
        int tpDist2 = Math.abs(Math.max(a, b) - Math.max(x, y));
        int finalTpDist = tpDist1 + tpDist2;
        FileWriter out = new FileWriter(new File("teleport.out"));
        if (normalDist > finalTpDist) {
            out.write("" + finalTpDist);
        } else {
            out.write("" + normalDist);
        }
        out.close();
    }
}