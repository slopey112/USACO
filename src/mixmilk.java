import java.io.*;
import java.util.StringTokenizer;

class milkBucket {
    private int capacity;
    private int amount;
    public milkBucket(int c, int a) {
        capacity = c;
        amount = a;
    }
    public int getCapacity() {
        return capacity;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int a) {
        amount = a;
    }
}

public class mixmilk {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("mixmilk.in"));
        StringTokenizer first = new StringTokenizer(in.readLine());
        milkBucket one = new milkBucket(Integer.parseInt(first.nextToken()), Integer.parseInt(first.nextToken()));
        StringTokenizer second = new StringTokenizer(in.readLine());
        milkBucket two = new milkBucket(Integer.parseInt(second.nextToken()), Integer.parseInt(second.nextToken()));
        StringTokenizer third = new StringTokenizer(in.readLine());
        milkBucket three = new milkBucket(Integer.parseInt(third.nextToken()), Integer.parseInt(third.nextToken()));
        int pointer = 1;
        if (one.getAmount() == 0 && two.getAmount() == 0 && three.getAmount() == 0) {
            PrintWriter out = new PrintWriter("mixmilk.out");
            out.println(one.getAmount());
            out.println(two.getAmount());
            out.println(three.getAmount());
            out.close();
            return;
        }
        for (int i = 1; i <= 100; i++) {
            if (pointer == 1) {
                int canTake = two.getCapacity() - two.getAmount();
                if (canTake > one.getAmount()) {
                    two.setAmount(two.getAmount() + one.getAmount());
                    one.setAmount(0);
                } else {
                    two.setAmount(two.getCapacity());
                    if (canTake != 0) one.setAmount(one.getAmount() - canTake);
                }

            } else if (pointer == 2) {
                int canTake = three.getCapacity() - three.getAmount();
                if (canTake > two.getAmount()) {
                    three.setAmount(two.getAmount() + three.getAmount());
                    two.setAmount(0);
                } else {
                    three.setAmount(three.getCapacity());
                    if (canTake != 0) two.setAmount(two.getAmount() - canTake);
                }

            } else if (pointer == 3) {
                int canTake = one.getCapacity() - one.getAmount();
                if (canTake > three.getAmount()) {
                    one.setAmount(one.getAmount() + three.getAmount());
                    three.setAmount(0);
                } else {
                    one.setAmount(one.getCapacity());
                    if (canTake != 0) three.setAmount(three.getAmount() - canTake);
                }
            }
            if (pointer == 3) {
                pointer = 1;
            } else {
                pointer++;
            }
        }
        PrintWriter out = new PrintWriter("mixmilk.out");
        out.println(one.getAmount());
        out.println(two.getAmount());
        out.println(three.getAmount());
        out.close();
    }
}
