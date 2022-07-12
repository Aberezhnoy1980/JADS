package ru.aberezhnoy;

public class Graph {

    private int max_vertices;
    private Vertex[] vertices;
    private int[][] adjacency;
    private int size;

    public Graph(int max_vertices) {
        this.max_vertices = max_vertices;
        this.vertices = new Vertex[max_vertices];
        adjacency = new int[max_vertices][max_vertices];
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
        System.out.print(vertices[vertex]);
    }

    private int getUnvisited(int vertex) {
        for (int i = 0; i < size; i++) {
            if (adjacency[vertex][i] == 1 && !vertices[i].getIsVisited()) {
                return i;
            }
        }
        return -1;
    }

    public int getIndex(char label) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].getLabel() == label)
                return i;
        }
        return -1;
    }

    public void depthTraverse() {

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
        resetFlags();
    }

    public void widthTraverse() {

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
        resetFlags();
    }

    private void resetFlags() {
        for (int i = 0; i < size; i++) {
            vertices[i].isVisited(false);
        }
    }

    public Queue widthTraversePath(char sourceLabel, char destinationLabel) {
        int start = getIndex(sourceLabel);
        int stop = getIndex(destinationLabel);

        Queue queue = new Queue(max_vertices);
        vertices[start].isVisited(true);
        queue.insert(start);
        boolean done = false;
        while (!queue.isEmpty()) {
            int v1 = queue.remove();
            int v2;
            while ((v2 = getUnvisited(v1)) != -1) {
                vertices[v2].isVisited(true);

                if (v2 == stop) {
                    done = true;
                    break;
                }
                queue.insert(v2);
            }
        }
        resetFlags();
        if (done)
            return queue;
        else
            return null;
    }

    public Stack shortestWay(char labelSource, char labelDestination) {

        Stack result = new Stack(max_vertices);
        Queue queue = new Queue(max_vertices);

        int source = getIndex(labelSource);
        int destination = getIndex(labelDestination);

        if (source == -1 || destination == -1 || source == destination) return null;

        vertices[source].isVisited(true);
        queue.insert(source);
        while (!queue.isEmpty()) {
            int vertexCurrent = queue.remove();
            int vertexNext;
            while ((vertexNext = getUnvisited(vertexCurrent)) != -1) {
                vertices[vertexNext].setParent(vertices[vertexCurrent]);
                vertices[vertexNext].isVisited(true);
                if (vertexNext == destination) break;
                queue.insert(vertexNext);
            }
            if (vertexNext == destination) break;
        }
        if (!vertices[destination].getIsVisited()) return null;

        result.push(vertices[destination].getLabel());
        int current = destination;
        while (vertices[current].getParent() != null)
            for (int i = 0; i < vertices.length; i++)
                if (vertices[current].getParent() == vertices[i]) {
                    result.push(vertices[i].getLabel());
                    current = i;
                    break;
                }

        for (int i = 0; i < size; i++) {
            vertices[i].isVisited(false);
            vertices[i].setParent(null);
        }
        return result;
    }

}
