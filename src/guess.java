import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class guess {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("guess.in"));
        ArrayList<ArrayList<String>> att = new ArrayList<ArrayList<String>>();
        int N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            int a = 0;
            StringTokenizer str = new StringTokenizer(in.readLine());
            String name = str.nextToken();
            int K = Integer.parseInt(str.nextToken());
            ArrayList<String> tmp = new ArrayList<>();
            for (int j = 0; j < K; j++) {
                tmp.add(str.nextToken());
            }
            att.add(tmp);
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    int intersection = sharedAttributes(att.get(i), att.get(j));
                    if (intersection > max) {
                        max = intersection;
                    }
                }
            }
        }
        FileWriter out = new FileWriter(new File("guess.out"));
        out.write("" + (max + 1));
        out.close();
    }
    public static int sharedAttributes(ArrayList<String> a, ArrayList<String> b) {
        int ct = 0;
        if (a.size() > b.size()) {
            for (String str : a) {
                if (b.contains(str)) ct++;
            }
        } else {
            for (String str : b) {
                if (a.contains(str)) ct++;
            }
        }
        return ct;
    }
}
