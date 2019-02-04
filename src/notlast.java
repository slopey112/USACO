import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class notlast {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("notlast.in"));
        int N = Integer.parseInt(in.readLine());
        int Bessie = 0;
        int Elsie = 0;
        int Daisy = 0;
        int Gertie = 0;
        int Annabelle = 0;
        int Maggie = 0;
        int Henrietta = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            String name = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            if(name.equals("Bessie")) Bessie += num;
            else if (name.equals("Elsie")) Elsie += num;
            else if (name.equals("Daisy")) Daisy += num;
            else if (name.equals("Gertie")) Gertie += num;
            else if (name.equals("Annabelle")) Annabelle += num;
            else if (name.equals("Maggie")) Maggie += num;
            else Henrietta += num;
        }
        int[] cows = {Bessie, Elsie, Daisy, Gertie, Annabelle, Maggie, Henrietta};
        int min = 10000;
        for (int i = 0; i < cows.length; i++) {
            if (cows[i] < min) min = cows[i];
        }
        ArrayList<int[]> check = new ArrayList<>();
        int big = 10000;
        for (int i = 0; i < cows.length; i++) {
            if (cows[i] > min && cows[i] <= big) {
                if (check.size() > 0 && cows[i] < check.get(check.size()-1)[0]) {
                    check.clear();
                    check.add(new int[]{cows[i], i});
                    big = cows[i];
                } else {
                    check.add(new int[]{cows[i], i});
                    big = cows[i];
                }
            }
        }
        String answer = "";
        if (check.size() == 1) {
            if (check.get(0)[1] == 0) answer = "Bessie";
            else if (check.get(0)[1] == 1) answer = "Elsie";
            else if (check.get(0)[1] == 2) answer = "Daisy";
            else if (check.get(0)[1] == 3) answer = "Gertie";
            else if (check.get(0)[1] == 4) answer = "Annabelle";
            else if (check.get(0)[1] == 5) answer = "Maggie";
            else if (check.get(0)[1] == 6) answer = "Henrietta";
        } else {
            answer = "Tie";
        }
        FileWriter out = new FileWriter(new File("notlast.out"));
        out.write(answer + "\n");
        out.close();
    }
}