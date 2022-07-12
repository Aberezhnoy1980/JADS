package ru.aberezhnoy;

public class Iterator<T> implements MyIterator<T> {
    Node<T> current;
    DoubleRelatedList<T> doubleRelatedList;

    public Iterator(DoubleRelatedList<T> doubleRelatedList) {
        this.doubleRelatedList = doubleRelatedList;
    }

    @Override
    public void reset() {
        current = doubleRelatedList.getHead();
    }

    @Override
    public boolean atEnd() {
        if (!listExists()) throw new RuntimeException("iterator is null");
        return current.next == null;
    }

    @Override
    public boolean atBegin() {
        if (!listExists()) throw new RuntimeException("iterator is null");
        return current.prev == null;
    }

    private boolean listExists() {
        return doubleRelatedList.getIterator() != null;
    }

    @Override
    public T deleteCurrent() {
        if (!listExists()) throw new RuntimeException("iterator is null");
        T temp = current.obj;
        if (atBegin() && atEnd()) {
            doubleRelatedList.setHead(null);
            doubleRelatedList.setTail(null);
            reset();
        } else if (atBegin()) {
            doubleRelatedList.setHead(current.next);
            doubleRelatedList.getHead().prev = null;
            reset();
        } else if (atEnd()) {
            doubleRelatedList.setTail(current.prev);
            doubleRelatedList.getTail().next = null;
            current = current.next;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        return temp;

    }

    @Override
    public void insertAfter(T c) {
        if (!listExists()) throw new RuntimeException("iterator is null");
        Node<T> temp = new Node<>(c);
        if (!atEnd()) {
            temp.next = current.next;
            current.next.prev = temp;
        } else {
            doubleRelatedList.setTail(temp);
        }
        current.next = temp;
        temp.prev = current;
    }

    @Override
    public void insertBefore(T c) {
        if (!listExists()) throw new RuntimeException("iterator is null");
        Node<T> temp = new Node<>(c);
        if (!atBegin()) {
            temp.prev = current.prev;
            current.prev.next = temp;
        } else {
            doubleRelatedList.setHead(temp);
        }
        current.prev = temp;
        temp.next = current;
    }

    @Override
    public T getCurrent() {
        if (!listExists()) throw new RuntimeException("iterator is null");
        return current.obj;
    }

    @Override
    public boolean hasNext() {
        if (!listExists()) throw new RuntimeException("iterator is null");
        return current.next != null;
    }

    @Override
    public T next() {
        if (!listExists()) throw new RuntimeException("iterator is null");
        current = current.next;
        return current.prev.obj;
    }
}
