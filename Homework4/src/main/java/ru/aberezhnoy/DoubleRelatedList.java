package ru.aberezhnoy;

public class DoubleRelatedList<T> {
    private final MyIterator<T> iterator;
    private Node<T> head;
    private Node<T> tail;

    public DoubleRelatedList() {
        head = null;
        tail = null;
        iterator = new Iterator(this);
        iterator.reset();
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public MyIterator<T> getIterator() {
        return iterator;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(T obj) {
        Node<T> n = new Node<>(obj);
        n.next = head;
        if (head == null) {
            tail = n;
        } else {
            head.prev = n;
        }
        head = n;
        iterator.reset();
    }

    public T pop() {
        if (isEmpty()) return null;
        T obj = tail.obj;
        if (tail.prev != null) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            tail = null;
            head = null;
        }
        iterator.reset();
        return obj;
    }

    public boolean contains(T obj) {
        Node<T> n = new Node<>(obj);
        Node<T> current = head;
        while (!current.equals(n)) {
            if (current.next == null) return false;
            else current = current.next;
        }
        return true;
    }

    public T delete(T obj) {
        Node<T> n = new Node<>(obj);
        Node<T> current = head;
        Node<T> previous = head;

        while (!current.equals(n)) {
            if (current.next == null) return null;
            else {
                previous = current;
                current = current.next;
            }
        }
        if (current == head && current == tail) {
            head = null;
            tail = null;
            iterator.reset();
        } else if (current == head) {
            head.next.prev = null;
            head = head.next;
        } else if (current == tail) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            previous.next = current.next;
            current.next.prev = previous;
        }
        return current.obj;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        Node current = head;
        StringBuilder sb = new StringBuilder("[ ");
        while (current != null) {
            sb.append(current);
            current = current.next;
            sb.append((current == null) ? " ]" : ", ");
        }
        return sb.toString();
    }
}
