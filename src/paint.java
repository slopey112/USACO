import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class paint {
    public static void main(String[] args) throws IOException {
        FileWriter out = new FileWriter(new File("paint.out"));
        Scanner in = new Scanner(new File("paint.in"));
        int[] arr = {in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()};
        int max1 = Math.max(arr[0], arr[1]);
        int max2 = Math.max(arr[2], arr[3]);
        if ((max2 < max1 && Math.min(arr[0], arr[1]) < max2) || (max1 < max2 && Math.min(arr[2], arr[3]) < max1) || max2 == max1) {
            Arrays.sort(arr);
            int value = arr[3] - arr[0];
            out.write("" + value);
            in.close();
            out.close();
            return;
        }
        else {
            Arrays.sort(arr);
            int result = (arr[3]-arr[2]) + (arr[1] - arr[0]);
            out.write("" + result);
            in.close();
            out.close();
            return;
        }
    }
}
