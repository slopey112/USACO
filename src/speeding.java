import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class speeding {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("speeding.in"));
        int N = in.nextInt(), M = in.nextInt();
        int[] test = new int[100];
        int[] cow = new int[100];
        int counter = 0;
        for (int i = 0; i < N; i++) {
            int seg = in.nextInt(), limit = in.nextInt();
            for (int j = 0; j < seg; j++) {
                test[counter] = limit;
                counter++;
            }
        }
        counter = 0;
        for (int i = 0; i < M; i++) {
            int seg = in.nextInt(), limit = in.nextInt();
            for (int j = 0; j < seg; j++) {
                cow[counter] = limit;
                counter++;
            }
        }
        int result = 0;
        for (int i = 0; i < 100; i++) {
            int temp = cow[i] - test[i];
            if (temp > result) {
                result = temp;
            }
        }
        FileWriter out = new FileWriter(new File("speeding.out"));
        out.write("" + result);
        in.close();
        out.close();
    }
}