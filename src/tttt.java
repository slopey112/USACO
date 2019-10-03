import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class tttt {
    public static String getWins(String a, String b, String c, int select) {
        ArrayList<String> check = new ArrayList<>();
        check.add(a);
        if(!check.contains(b)) check.add(b);
        if(!check.contains(c)) check.add(c);
        Collections.sort(check);
        if (select == 1) {
            if (check.size() == 1) return check.get(0);
            else return "none";
        } else if (select == 2) {
            if (check.size() == 2) return check.get(0) + check.get(1);
            else return "none";
        } else {
            throw new IllegalArgumentException();
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("tttt.in"));
        HashSet<String> one = new HashSet<String>();
        HashSet<String> two = new HashSet<String>();
        String[] set = new String[9];
        int c = 0;
        for (int i = 0; i < 3; i++) {
            String temp = in.nextLine();
            for (int j = 0; j < 3; j++) {
                set[c] = temp.substring(j, j+1);
                c++;
            }
        }
        if (!getWins(set[0], set[1], set[2], 1).equals("none")) one.add(getWins(set[0], set[1], set[2], 1));
        if (!getWins(set[0], set[1], set[2], 2).equals("none")) two.add(getWins(set[0], set[1], set[2], 2));
        if (!getWins(set[3], set[4], set[5], 1).equals("none")) one.add(getWins(set[3], set[4], set[5], 1));
        if (!getWins(set[3], set[4], set[5], 2).equals("none")) two.add(getWins(set[3], set[4], set[5], 2));
        if (!getWins(set[6], set[7], set[8], 1).equals("none")) one.add(getWins(set[6], set[7], set[8], 1));
        if (!getWins(set[6], set[7], set[8], 2).equals("none")) two.add(getWins(set[6], set[7], set[8], 2));
        if (!getWins(set[0], set[3], set[6], 1).equals("none")) one.add(getWins(set[0], set[3], set[6], 1));
        if (!getWins(set[0], set[3], set[6], 2).equals("none")) two.add(getWins(set[0], set[3], set[6], 2));
        if (!getWins(set[1], set[4], set[7], 1).equals("none")) one.add(getWins(set[1], set[4], set[7], 1));
        if (!getWins(set[1], set[4], set[7], 2).equals("none")) two.add(getWins(set[1], set[4], set[7], 2));
        if (!getWins(set[2], set[5], set[8], 1).equals("none")) one.add(getWins(set[2], set[5], set[8], 1));
        if (!getWins(set[2], set[5], set[8], 2).equals("none")) two.add(getWins(set[2], set[5], set[8], 2));
        if (!getWins(set[0], set[4], set[8], 1).equals("none")) one.add(getWins(set[0], set[4], set[8], 1));
        if (!getWins(set[0], set[4], set[8], 2).equals("none")) two.add(getWins(set[0], set[4], set[8], 2));
        if (!getWins(set[2], set[4], set[6], 1).equals("none")) one.add(getWins(set[2], set[4], set[6], 1));
        if (!getWins(set[2], set[4], set[6], 2).equals("none")) two.add(getWins(set[2], set[4], set[6], 2));
        FileWriter out = new FileWriter(new File("tttt.out"));
        out.write(one.size() + "\n" + two.size());
        in.close();
        out.close();
    }
}