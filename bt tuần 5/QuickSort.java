import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private static final int RUNS = 3;

    public static void insertionSort(int[] arr) {
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

    public static void mergeSort(int[] arr) {
        if (arr.length > 1) mergeSort(arr, new int[arr.length], 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int p = left; p <= right; p++) arr[p] = temp[p];
    }

    public static void quickSortWithShuffle(int[] arr, Random random) {
        shuffle(arr, random);
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSortWithoutShuffle(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void shuffle(int[] arr, Random random) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int pivotIndex = partitionHoareStyle(arr, low, high);
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }

    private static int partitionHoareStyle(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low + 1;
        int j = high;

        while (true) {
            while (i <= high && arr[i] < pivot) i++;
            while (j > low && arr[j] > pivot) j--;
            if (i >= j) break;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;
        return j;
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

    private static double averageMillis(String algorithm, int[] original, Random random) {
        long total = 0;
        for (int run = 0; run < RUNS; run++) {
            int[] copy = Arrays.copyOf(original, original.length);
            long start = System.nanoTime();
            switch (algorithm) {
                case "Insertion": insertionSort(copy); break;
                case "Merge": mergeSort(copy); break;
                case "QuickShuffle": quickSortWithShuffle(copy, random); break;
                case "QuickNoShuffle": quickSortWithoutShuffle(copy); break;
            }
            total += System.nanoTime() - start;
        }
        return total / (double) RUNS / 1_000_000.0;
    }

    public static void main(String[] args) {
        int[] sizes = args.length == 0
                ? new int[]{1000, 5000, 10000}
                : Arrays.stream(args).mapToInt(Integer::parseInt).toArray();

        Random random = new Random(2026);
        String[] types = {"Random", "Ascending", "Descending", "Equal"};
        System.out.println("Type,N,Insertion_avg_ms,MergeSort_avg_ms,QuickSort_shuffle_avg_ms,QuickSort_no_shuffle_avg_ms");

        for (int n : sizes) {
            for (String type : types) {
                int[] data = makeData(type, n, random);
                double insertion = averageMillis("Insertion", data, random);
                double merge = averageMillis("Merge", data, random);
                double quickShuffle = averageMillis("QuickShuffle", data, random);
                double quickNoShuffle = averageMillis("QuickNoShuffle", data, random);
                System.out.printf("%s,%d,%.3f,%.3f,%.3f,%.3f%n",
                        type, n, insertion, merge, quickShuffle, quickNoShuffle);
            }
        }
    }
}
