package ru.aberezhnoy.dataStructures;

public class MyArray {
    private int[] arr;
    private int capacity;

    //new int[5];
    public MyArray(int size) {
        this.capacity = 0;
        this.arr = new int[size];
    }

    // = {1,2,3,4,5};
    public MyArray(int[] init) {
        this.capacity = init.length;
        this.arr = init;
    }

    void display() {
        for (int i = 0; i < this.capacity; ++i) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    public int get(int index) {
        return arr[index];
    }

    public void set(int value, int index) {
        arr[index] = value;
    }

    boolean deleteByValue(int value) {
        for (int i = 0; i < this.capacity; i++) {
            if (this.arr[i] == value) {
                System.arraycopy(this.arr, i + 1, this.arr, i, this.capacity - i - 1);
                --capacity;
                return true;
            }
        }
        return false;
    }

    boolean deleteByIndex(int index) {
        for (int i = 0; i < this.capacity; i++) {
            if (index == i) {
                System.arraycopy(this.arr, i + 1, this.arr, i, this.capacity - i - 1);
                --capacity;
                return true;
            }
        }
        return false;
    }

    public void deleteAll(int value) {
        for (int i = 0; i < capacity; i++) {
            if (arr[i] == value) {
                deleteByIndex(i--);
            }
        }
    }

    public void deleteAll() {
        capacity = 0;
    }

    public void insert(int index, int value) {
        int[] temp = arr;
        if (capacity >= arr.length) {
            arr = new int[capacity * 2];
            System.arraycopy(temp, 0, arr, 0, index);
        }
        System.arraycopy(temp, index, arr, index + 1, capacity);
        arr[index] = value;
        capacity++;
    }

    void append(int value) {
        if (this.capacity == this.arr.length) {
            int[] old = this.arr;
            this.arr = new int[old.length * 2];
            System.arraycopy(old, 0, arr, 0, old.length);
        }
        this.arr[this.capacity++] = value;
    }

    public boolean isInArray(int value) { // O(n)
        for (int i = 0; i < this.capacity; i++)
            if (this.arr[i] == value) return true;
        return false;
    }

    //O(log(N))
    public boolean hasValue(int value) {
        int low = 0;
        int high = this.capacity - 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (value == this.arr[mid]) {
                return true;
            } else {
                if (value < this.arr[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return false;
    }

    private void swap(int a, int b) {
        int tmp = this.arr[a];
        this.arr[a] = this.arr[b];
        this.arr[b] = tmp;
    }

    public int sortBubble() {
        int iterCount = 0;
        for (int iter = 0; iter < capacity; iter++) {
            for (int index = 0; index < capacity - 1; index++) {
                iterCount++;
                if (this.arr[index] > this.arr[index + 1]) swap(index, index + 1);
            }
        }
        return iterCount;
    }

    public int sortSelect() {
        int iterCount = 0;
        for (int index = 0; index < capacity; index++) {
            int curr = index;
            for (int srch = index + 1; srch < capacity; srch++) {
                iterCount++;
                if (this.arr[srch] < this.arr[curr]) curr = srch;
            }
            if (curr != index) swap(index, curr);
        }
        return iterCount;
    }

    public int getMax() {
        if (capacity == 0) throw new RuntimeException("Empty array");
        if (capacity == 1) return arr[0];
        int maxValue = arr[0];
        for (int i = 1; i < capacity; i++) {
            if (maxValue < arr[i]) maxValue = arr[i];
        }
        return maxValue;
    }

    public int getMin() {
        if (capacity == 0) throw new RuntimeException("Empty array");
        if (capacity == 1) return arr[0];
        int minValue = arr[0];
        for (int i = 1; i < capacity; i++) {
            if (minValue > arr[i]) minValue = arr[i];
        }
        return minValue;
    }

    int pigeon() {
        int iterCount = 0;
        int min = getMin();
        int max = getMax();
        int[] freq = new int[max - min + 1];
        for (int i = 0; i < capacity; i++) {
            iterCount++;
            freq[arr[i] - min]++;
        }

        int arrIndex = 0;
        for (int i = 0; i < freq.length; i++)
            for (int elems = freq[i]; elems > 0; elems--) {
                iterCount++;
                arr[arrIndex++] = i + min;
            }
        return iterCount;
    }

    public int sortInsert() {
        int iterCount = 0;
        for (int curr = 1; curr < capacity; curr++) {
            int temp = this.arr[curr];
            int move = curr;
            while (move > 0 && this.arr[move - 1] >= temp) {
                iterCount++;
                this.arr[move] = this.arr[move - 1];
                move--;
            }
            this.arr[move] = temp;
        }
        return iterCount;
    }

    public int sortDoubleBubble() {
        int iterCount = 0;
        boolean flag = false;
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity - 1 - i; j++) {
                iterCount++;
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break;
        }
        return iterCount;
    }
}

