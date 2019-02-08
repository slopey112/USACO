/*
ID: slopey112
LANG: JAVA
TASK: ride
*/

import java.io.*;

public class ride {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("ride.in"));
        char[] a = in.readLine().toCharArray();
        char[] b = in.readLine().toCharArray();
        String msg = (num(a) == num(b)) ? "GO\n" : "STAY\n";
        PrintWriter out = new PrintWriter(new File("ride.out"));
        out.print(msg);
        out.close();
    }
    static int num(char[] a) {
        int sum = 1;
        for (char z : a) sum *= (int) z - 64;
        return sum % 47;
    }
}
