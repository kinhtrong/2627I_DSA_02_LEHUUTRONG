public class PriorityQueue {
    private final int[] data;
    private int size;

    public PriorityQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        data = new int[capacity];
        size = 0;
    }

    public void insert(int value) {
        if (size == data.length) {
            throw new IllegalStateException("Priority queue is full.");
        }
        data[size] = value;
        size++;
    }

    public int deleteMin() {
        if (size == 0) {
            throw new IllegalStateException("Priority queue is empty.");
        }

        int minIndex = 0;
        for (int i = 1; i < size; i++) {
            if (data[i] < data[minIndex]) {
                minIndex = i;
            }
        }

        int minValue = data[minIndex];
        for (int i = minIndex; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return minValue;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue(10);
        pq.insert(7);
        pq.insert(2);
        pq.insert(9);
        pq.insert(1);

        while (!pq.isEmpty()) {
            System.out.print(pq.deleteMin() + " ");
        }
        System.out.println();
    }
}
