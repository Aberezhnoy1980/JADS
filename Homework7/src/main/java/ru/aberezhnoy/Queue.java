package ru.aberezhnoy;

import java.util.Arrays;

public class Queue {
    private int maxSize;
    private int[] queue;
    private int head;
    private int tail;
    private int items;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new int[maxSize];
        this.head = 0;
        this.tail = -1;
        this.items = 0;
    }

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
            int[] tempArr = new int[maxSize];
            if (tail >= head) {
                System.arraycopy(queue, 0, tempArr, 0, queue.length);
            } else {
                System.arraycopy(queue, 0, tempArr, 0, tail + 1);
                System.arraycopy(queue, head, tempArr, maxSize - (queue.length - head), queue.length - head);
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

    @Override
    public String toString() {
        return "Queue{" +
                "maxSize=" + maxSize +
                ", queue=" + Arrays.toString(queue) +
                ", head=" + head +
                ", tail=" + tail +
                ", items=" + items +
                '}';
    }
    public static void main(String[] args) {

        Queue queue = new Queue(10);

        queue.insert(0);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.insert(6);
        queue.insert(7);
        queue.insert(8);
        queue.insert(9);

        System.out.println(queue);

        while( !queue.isEmpty() ) {
            int n = queue.remove();
            System.out.println(n);
        }
    }
}
