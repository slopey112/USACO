import java.util.*;
import java.io.*;

public class convention {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("convention.in"));
        StringTokenizer line1 = new StringTokenizer(in.readLine());
        StringTokenizer line2 = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line1.nextToken());
        int M = Integer.parseInt(line1.nextToken());
        int C = Integer.parseInt(line1.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(line2.nextToken());
        }
        Arrays.sort(arr);
        int high = arr[N-1], low = 0;
        while (high > low) {
            int mid = (high - low) / 2 + 1;
            if (check(arr, mid, M, C)) {
                high = (high - mid) / 2 + 1;
            } else {
                low = (mid - low) / 2 + 1;
            }
        }
        System.out.println(high);
    }

    public static boolean check(int[] arr, int worstcase, int cars, int cows) {
        int dif = arr[0], ct = 1, cutoffs = 0;
        for (int i = 1; i < arr.length; i++) {
            dif = arr[i] - dif;
            if (ct == cows || dif >= worstcase) {
                System.out.println(arr[i]);
                cutoffs++;
                ct = 0;
                dif = arr[i];
            }
            ct++;
        }
        if (cutoffs > cars) {
            return false;
        } else {
            return true;
        }
    }
}
