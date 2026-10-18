import java.util.Scanner;

public class FindTheMedian {
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }

    public static int findMedian(int[] arr) {
        int target = arr.length / 2;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int pivotIndex = partition(arr, low, high);

            if (pivotIndex == target) return arr[pivotIndex];
            if (pivotIndex < target) low = pivotIndex + 1;
            else high = pivotIndex - 1;
        }

        throw new IllegalArgumentException("Input array must not be empty.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        System.out.println(findMedian(arr));
        sc.close();
    }
}
