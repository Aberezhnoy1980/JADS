package ru.aberezhnoy;

import java.util.Objects;

public class Vertex {

    private char label;
    private Vertex parent;
    private boolean isVisited;

    public Vertex(char label) {
        this.label = label;
        this.isVisited = false;
    }

    @Override
    public String toString() {
        return "V: " + label + "-> ";
    }

    public char getLabel() {
        return label;
    }

    public boolean getIsVisited() {
        return isVisited;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public void isVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass() || o == null) return false;
        Vertex vertex = (Vertex) o;
        return label == vertex.label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, isVisited);
    }
}
