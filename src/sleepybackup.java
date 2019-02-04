import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class sleepybackup {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sleepy.in"));
        int N = Integer.parseInt(in.readLine());
        LinkedList<Integer> vals = new LinkedList<>();
        StringTokenizer str = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            vals.add(Integer.parseInt(str.nextToken()));
        }
        ArrayList<Integer> steps = sort(vals, N);
        int res = steps.get(0);
        FileWriter out = new FileWriter(new File("sleepy.out"));
        out.write(res + "\n");
        for (int i = 1; i < steps.size(); i++) {
            out.write("" + steps.get(i));
            if (i != steps.size() - 1) out.write(" ");
        }
        out.close();
    }
    public static boolean sortedp(LinkedList<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < arr.get(i-1)) {
                return false;
            }
        }
        return true;
    }
    public static ArrayList<Integer> sort(LinkedList<Integer> arr, int length) {
        int ct = 0;
        ArrayList<Integer> res = new ArrayList<>();
        res.add(0);
        int N = length;
        while(!(sortedp(arr))) {
            for (int i = N - 1; i >= 1; i--) {
                if (arr.get(i) < arr.peek() || i != N - 1 && arr.get(i) > arr.get(i+1)) {
                    res.add(i);
                    arr.add(i, arr.poll());
                    i = N;
                    ct++;
                }
            }
        }
        res.set(0, ct);
        return res;
    }
}
