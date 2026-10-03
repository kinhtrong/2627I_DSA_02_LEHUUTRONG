import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int[] count = new int[100];

        for (int i = 0; i < n; i++) {
            int value = in.nextInt();
            count[value]++;
        }

        for (int i = 0; i < 100; i++) {
            System.out.print(count[i] + " ");
        }
    }
}
