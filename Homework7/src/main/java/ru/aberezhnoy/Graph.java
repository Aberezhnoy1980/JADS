package ru.aberezhnoy;

public class Graph {

    private final int MAX_VERTICES = 32;
    private Vertex[] vertices;
    private int[][] adjacency;
    private int size;

    public Graph() {
        vertices = new Vertex[MAX_VERTICES];
        adjacency = new int[MAX_VERTICES][MAX_VERTICES];
        size = 0;
    }

    public void addVertex(char label) {
        vertices[size++] = new Vertex(label);
    }

    public void addEdge(int start, int end) { // (vertices[char] start, vertices[char] end)
        adjacency[start][end] = 1;
        adjacency[end][start] = 1;
    }

    public void displayVertex(int vertex) {
        System.out.println(vertices[vertex]);
    }

    private int getUnvisited(int vertex) {
        for (int i = 0; i < size; i++) {
            if (adjacency[vertex][i] == 1 && !vertices[i].getIsVisited()) {
                return i;
            }
        }
        return -1;
    }

    public void depthTravers() {

        Stack stack = new Stack(size);
        vertices[0].isVisited(true);
        displayVertex(0);
        stack.push(0);
        while (!stack.isEmpty()) {
            int v = getUnvisited(stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                vertices[v].isVisited(true);
                displayVertex(v);
                stack.push(v);
            }
        }
        for (int i = 0; i < size; i++) {
            vertices[i].isVisited(false);
        }
    }

    public void widthTravers() {

        Queue queue = new Queue(size);
        vertices[0].isVisited(true);
        displayVertex(0);
        queue.insert(0);
        int v2;
        while (!queue.isEmpty()) {
            int v1 = queue.remove();
            while ((v2 = getUnvisited(v1)) != -1) {
                vertices[v2].isVisited(true);
                displayVertex(v2);
                queue.insert(v2);
            }
        }
        for (int i = 0; i < size; i++) {
            vertices[i].isVisited(false);
        }
    }
}
