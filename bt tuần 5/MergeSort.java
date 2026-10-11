import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    private static final int RUNS = 3;

    public static void sort(int[] arr) {
        if (arr.length > 1) {
            int[] temp = new int[arr.length];
            mergeSort(arr, temp, 0, arr.length - 1);
        }
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int p = left; p <= right; p++) arr[p] = temp[p];
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > value) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = value;
        }
    }

    private static int[] makeData(String type, int n, Random random) {
        int[] arr = new int[n];
        switch (type) {
            case "Random":
                for (int i = 0; i < n; i++) arr[i] = random.nextInt(Math.max(10, n * 10));
                break;
            case "Ascending":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "Descending":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case "Equal":
                Arrays.fill(arr, 7);
                break;
            default:
                throw new IllegalArgumentException("Unknown data type: " + type);
        }
        return arr;
    }

    private static double averageMillis(String algorithm, int[] original) {
        long total = 0;
        for (int run = 0; run < RUNS; run++) {
            int[] copy = Arrays.copyOf(original, original.length);
            long start = System.nanoTime();
            if (algorithm.equals("Insertion")) insertionSort(copy);
            else sort(copy);
            long end = System.nanoTime();
            total += end - start;
        }
        return total / (double) RUNS / 1_000_000.0;
    }

    public static void main(String[] args) {
        int[] sizes = args.length == 0
                ? new int[]{1000, 5000, 10000, 20000}
                : Arrays.stream(args).mapToInt(Integer::parseInt).toArray();

        Random random = new Random(2026);
        String[] types = {"Random", "Ascending", "Descending", "Equal"};

        System.out.println("Type,N,Insertion_avg_ms,MergeSort_avg_ms");
        for (int n : sizes) {
            for (String type : types) {
                int[] data = makeData(type, n, random);
                double insertion = averageMillis("Insertion", data);
                double merge = averageMillis("Merge", data);
                System.out.printf("%s,%d,%.3f,%.3f%n", type, n, insertion, merge);
            }
        }
    }
}
