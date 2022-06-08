package ru.aberezhnoy.dataStructures;

public class Stack {
    private int maxSize;
    private int[] stack;
    private int head;

    public Stack(int size) {
        this.maxSize = size;
        this.stack = new int[this.maxSize];
        this.head = -1;
    }

    public boolean isEmpty() {
        return this.head == -1;
    }

    public boolean isFull() {
        return this.head == this.maxSize - 1;
    }

    public void push(int i) {
        if (isFull()) {
            this.maxSize *= 2;
            int[] newStack = new int[this.maxSize];
            System.arraycopy(this.stack, 0, newStack, 0, this.stack.length);
            this.stack = newStack;
        }
        this.stack[++this.head] = i;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return this.stack[this.head--];
    }

    public int peek() {
        return this.stack[this.head];
    }
}
