import java.util.Scanner;

class MaxHeap {
    private int[] arr;
    private int maxSize, heapSize;

    MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.heapSize = 0;
        this.arr = new int[maxSize];
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    int getMax() {
        return heapSize > 0 ? arr[0] : -1;
    }

    int curSize() {
        return heapSize;
    }

    private void maxHeapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;

        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    }

    void removeMax() {
        if (heapSize == 0) {
            System.out.println("Heap is empty");
            return;
        }
        arr[0] = arr[--heapSize];
        maxHeapify(0);
    }

    void insertKey(int x) {
        if (heapSize == maxSize) {
            System.out.println("Overflow: Could not insert key");
            return;
        }

        int i = heapSize++;
        arr[i] = x;

        while (i > 0 && arr[parent(i)] < arr[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(15);
        int[] elements = {3, 10, 12, 8, 2, 14};

        for (int e : elements) {
            heap.insertKey(e);
        }

        System.out.println("Current size of heap: " + heap.curSize());
        System.out.println("Current maximum element: " + heap.getMax());

        heap.removeMax();
        System.out.println("Current size of heap: " + heap.curSize());

        heap.insertKey(15);
        heap.insertKey(5);

        System.out.println("Current size of heap: " + heap.curSize());
        System.out.println("Current maximum element: " + heap.getMax());
    }
}
