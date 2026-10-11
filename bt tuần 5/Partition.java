import java.util.Scanner;

public class Partition {
    public static int[] partition(int[] arr) {
        int pivot = arr[0];
        int[] result = new int[arr.length];
        int index = 0;

        for (int value : arr) {
            if (value < pivot) result[index++] = value;
        }
        for (int value : arr) {
            if (value == pivot) result[index++] = value;
        }
        for (int value : arr) {
            if (value > pivot) result[index++] = value;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int[] result = partition(arr);
        for (int i = 0; i < result.length; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(result[i]);
        }
        System.out.println();
        sc.close();
    }
}
