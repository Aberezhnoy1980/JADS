package ru.aberezhnoy.dataStructures;

public class Queue {
    private int maxSize;
    private int[] queue;
    private int head;
    private int tail;
    private int items;

    public int getSize() {
        return maxSize;
    }

    public int[] getQueue() {
        return queue;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public int getCapacity() {
        return items;
    }

    public void setCapacity(int items) {
        this.items = items;
    }

    public Queue(int size) {
        maxSize = size;
        queue = new int[maxSize];
        head = 0;
        tail = -1;
        items = 0;
    }

    public boolean isEmpty() {
        return (items == 0);
    }

    public boolean isFull() {
        return (items == maxSize);
    }

    public int size() {
        return items;
    }

    public void insert(int i) {
        if (isFull()) {
            maxSize *= 2;
            int[] tmpArr = new int[maxSize];
            if (tail >= head) {
                System.arraycopy(queue, 0, tmpArr, 0, queue.length);
            } else {
                System.arraycopy(queue, 0, tmpArr, 0, tail + 1);
                System.arraycopy(queue, head, tmpArr, maxSize - (queue.length - head), queue.length - head);
                head = maxSize - head - 1;
            }
        }
        if (tail == maxSize - 1) {
            tail = -1;
        }
        queue[++tail] = i;
        ++items;
    }

    public int remove() {
        int temp = queue[head++];
        head %= maxSize;
        items--;
        return temp;
    }

    public int peek() {
        return queue[head];
    }
}
