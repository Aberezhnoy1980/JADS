package ru.aberezhnoy;

import java.util.Objects;

public class Node <T> {
    T obj;
    Node<T> next;
    Node<T> prev;

    public Node(T obj) {
        this.obj = obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node))return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(obj, node.obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public String toString() {
        return obj.toString();
    }

}
