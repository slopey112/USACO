import java.io.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;


public class homework {
    public static int[] arrMin(int a, int[] z) {
        int[] fin = {10001, 0};
        for (int i = a; i < z.length; i++) {
            if (z[i] < fin[0]) {
                fin[0] = z[i];
                fin[1] = i;
            }
        }
        return fin;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("homework.in"));
        int N = Integer.parseInt(in.readLine());
        String[] strVal = in.readLine().split(" ");
        PriorityQueue<Integer> match = new PriorityQueue<>(N);
        Queue<Integer> input = new LinkedList<Integer>();
        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(strVal[i]);
            match.add(values[i]);
            input.add(values[i]);
        }
        double check = 0;
        ArrayList<Integer> K = new ArrayList<>();
        int[] tCheck = {10001, 0};
        int sum = 0;
        for (int j = 0; j < N; j++) {
            int curr = values[j];
            if (j > 0 && curr < tCheck[0]) {
                tCheck[0] = curr;
                tCheck[1] = j;
            }
            sum += curr;
        }
        int minIndex = tCheck[1];
        int min = tCheck[0];
        for (int i = 1; i < N-1; i++) {
            int current = input.peek();
            input.remove();
            sum -= current;
            int avg = sum;
            avg -= min;
            if (current == match.peek()) {
                while (match.peek() != current) {
                    match.remove();
                }
                min = match.peek();
            }
            double temp = (double) avg/(N-i-1);
            if (temp > check) {
                K.clear();
                K.add(i);
                check = temp;
            } else if (temp == check) {
                K.add(i);
            }
        }
        PrintWriter out = new PrintWriter(new File("homework.out"));
        for (int i : K) out.println(i);
        out.close();
    }
}