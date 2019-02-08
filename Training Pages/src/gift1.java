/*
ID: slopey112
LANG: JAVA
TASK: gift1
*/

import java.io.*;
import java.util.*;

public class gift1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("gift1.in"));
        int N = Integer.parseInt(in.readLine());
        HashMap<String, Integer> names = new HashMap<>(N);
        String[] list = new String[N];
        for (int i = 0; i < N; i++) {
            String a = in.readLine();
            names.put(a, 0);
            list[i] = a;
        }
        for (int i = 0; i < N; i++) {
            String name = in.readLine();
            StringTokenizer str = new StringTokenizer(in.readLine());
            int money = Integer.parseInt(str.nextToken()), P = Integer.parseInt(str.nextToken()), div = (P == 0) ? 1 : P;
            int give = money / div, get = -(money - (money % div));
            names.replace(name, names.get(name) + get);
            for (int j = 0; j < P; j++) {
                String recipient = in.readLine();
                names.replace(recipient, names.get(recipient) + give);
            }
        }
        PrintWriter out = new PrintWriter(new File("gift1.out"));
        for (String a : list) out.println(a + " " + names.get(a));
        out.close();
    }
}
