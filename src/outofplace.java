import java.io.*;
import java.util.ArrayList;

public class outofplace {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("outofplace.in"));
        ArrayList<Integer> inputs = new ArrayList<Integer>();
        int N = Integer.parseInt(in.readLine());
        inputs.add(Integer.parseInt(in.readLine()));
        for (int i = 1; i < N; i++) {
            int temp = Integer.parseInt(in.readLine());
            if (temp != inputs.get(inputs.size()-1)) inputs.add(temp);
        }
        int counter = 0;
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 1; j < (inputs.size() - i); j++) {
                if (inputs.get(j) < inputs.get(j-1)) {
                    int temp = inputs.get(j);
                    inputs.set(j, inputs.get(j-1));
                    inputs.set(j - 1, temp);
                    counter++;
                }
            }
        }
        FileWriter out = new FileWriter(new File("outofplace.out"));
        out.write("" + counter);
        out.close();
    }
}