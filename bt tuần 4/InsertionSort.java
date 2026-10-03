import java.util.*;

public class InsertionSort {

    public static void insertIntoSorted(int[] arr) {
        int value = arr[arr.length - 1];
        int i = arr.length - 2;

        while (i >= 0 && arr[i] > value) {
            arr[i + 1] = arr[i];
            printArray(arr);
            i--;
        }

        arr[i + 1] = value;
        printArray(arr);
    }

    static void printArray(int[] ar) {
        for (int n : ar) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] ar = new int[s];

        for (int i = 0; i < s; i++) {
            ar[i] = in.nextInt();
        }

        insertIntoSorted(ar);
    }
}
```
