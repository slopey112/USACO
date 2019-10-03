import java.io.*;
import java.util.*;

public class shuffle {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer str = new StringTokenizer(in.readLine());
        int[] seq = new int[N];
        for (int i = 0; i < N; i++) {
            //seq is the pattern that we are shuffling the cows by.
            seq[i] = Integer.parseInt(str.nextToken());
        }
        StringTokenizer str1 = new StringTokenizer(in.readLine());
        int[] ids = new int[N];
        for (int i = 0; i < N; i++) {
            //id is the ID of each individual cow. Will be changed to simulate shuffling.
            ids[i] = Integer.parseInt(str1.nextToken());
        }
        for (int i = 0; i < 3; i++) {
            //Simulates shuffle
            int[] copy = new int[N];
            for (int j = 0; j < N; j++) {
                copy[j] = ids[seq[j]-1];
            }
            CopyToArray(ids, copy);
        }
        PrintWriter out = new PrintWriter(new File("shuffle.out"));
        for (int i : ids) {
            out.println(i);
        }
        out.close();
    }
    public static int[] CopyToArray(int[] original, int[] copy) {
        //Utility function that copies one array to the other.
        for (int i = 0; i < copy.length; i++) {
            original[i] = copy[i];
        }
        return original;
    }
}
