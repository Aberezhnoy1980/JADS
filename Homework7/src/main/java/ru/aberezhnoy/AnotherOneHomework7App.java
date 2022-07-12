package ru.aberezhnoy;

import java.util.Queue;
import java.util.Stack;
import java.util.*;

public class AnotherOneHomework7App {

    public static void main(String[] args) {

        graphTest();
        dfsTest();
        bfsTest();
        findShortestWay();
    }

    static void graphTest() {
        Graph graph = new GraphImpl(4);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B", "C");
        graph.addEdge("B", "A", "C", "D");
        graph.addEdge("C", "A", "B", "D");
        graph.addEdge("D", "B", "C");

        System.out.println(("Graph size is: " + graph.getSize()));

        graph.display();
        System.out.println();
    }

    static void dfsTest() {
        Graph graph = new GraphImpl(7);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        graph.addEdge("A", "B", "C", "D");
        graph.addEdge("B", "E");
        graph.addEdge("D", "F");
        graph.addEdge("F", "G");

        graph.dfs("A");
        System.out.println();
    }

    static void bfsTest() {
        Graph graph = new GraphImpl(8);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdge("A", "B", "C", "D");
        graph.addEdge("B", "E");
        graph.addEdge("E", "H");
        graph.addEdge("C", "F");
        graph.addEdge("D", "G");

        graph.bfs("A");
        System.out.println();
    }

    static void findShortestWay() {

        GraphImpl cities = new GraphImpl(10);

        cities.addVertex("Moscow");
        cities.addVertex("Tula");
        cities.addVertex("Lipetsk");
        cities.addVertex("Voronezh");
        cities.addVertex("Ryazan");
        cities.addVertex("Tambov");
        cities.addVertex("Saratov");
        cities.addVertex("Kaluga");
        cities.addVertex("Orel");
        cities.addVertex("Kursk");

        cities.addEdge("Moscow", "Tula", "Ryazan", "Kaluga");
        cities.addEdge("Tula", "Lipetsk");
        cities.addEdge("Lipetsk", "Voronezh");
        cities.addEdge("Ryazan", "Tambov");
        cities.addEdge("Tambov", "Saratov");
        cities.addEdge("Saratov", "Voronezh");
        cities.addEdge("Kaluga", "Orel");
        cities.addEdge("Orel", "Kursk");
        cities.addEdge("Kursk", "Voronezh");

        System.out.println("Breadth traversal sequence:");
        cities.findShortestWayByBfs("Moscow", "Voronezh");
        ArrayList<String> arrayList = cities.getAllLabelsInBfsQueue();
        System.out.println("Traversal sequence before moving the loop to the second traversal ring:");
        int step = cities.queueLengthBeforeFirstRemove("Moscow", "Voronezh");
        ArrayList<String> bestWay = new ArrayList<>();
        int indexOfFinish = arrayList.indexOf("Voronezh");

        for (int i = indexOfFinish; i > 0; i -= step) {
            bestWay.add(arrayList.get(i));
        }
        bestWay.add(arrayList.get(0));

        Collections.reverse(bestWay);
        System.out.println("Shortest way is: " + bestWay);
    }

    interface Graph {
        void addVertex(String label);

        boolean addEdge(String sourceLabel, String destinationLabel, String... others);

        int getSize();

        void display();

        // Depth-first search
        void dfs(String sourceLabel);

        // Breadth-first search
        void bfs(String sourceLabel);
    }

    static class Vertex {

        private final String label;

        private boolean visited;

        Vertex(String label) {
            this.label = label;
        }

        String getLabel() {
            return label;
        }

        boolean isVisited() {
            return visited;
        }

        void setVisited(boolean visited) {
            this.visited = visited;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return Objects.equals(label, vertex.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }

        @Override
        public String toString() {
            return "V{" + label + "}";
        }
    }

    static class GraphImpl implements Graph {

        private final List<Vertex> vertexList;

        private final boolean[][] adjMat;

        private ArrayList<String> allLabelsInBfsQueue = new ArrayList<>();

        GraphImpl(int maxVertexCount) {
            this.vertexList = new ArrayList<>();
            this.adjMat = new boolean[maxVertexCount][maxVertexCount];
        }

        public ArrayList<String> getAllLabelsInBfsQueue() {
            return allLabelsInBfsQueue;
        }

        @Override
        public void addVertex(String label) {
            vertexList.add(new Vertex(label));
        }

        @Override
        public boolean addEdge(String sourceLabel, String destinationLabel, String... others) {
            boolean result = addEdge(sourceLabel, destinationLabel);
            for (String another : others) {
                result &= addEdge(sourceLabel, another);
            }
            return result;
        }

        boolean addEdge(String sourceLabel, String destinationLabel) {
            int sourceIndex = indexOf(sourceLabel);
            int destinationIndex = indexOf(destinationLabel);

            if (sourceIndex == -1 || destinationIndex == -1 || sourceIndex == destinationIndex) {
                return false;
            }
            adjMat[sourceIndex][destinationIndex] = true;
            adjMat[destinationIndex][sourceIndex] = true;
            return true;
        }

        private int indexOf(String label) {
            for (int i = 0; i < vertexList.size(); i++) {
                Vertex vertex = vertexList.get(i);
//                if (Objects.equals(vertexList.get(i).getLabel(), label)) {
//                if (vertexList.get(i).getLabel().equals(label)) {
                if (vertex.getLabel().equals(label)) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public int getSize() {
            return vertexList.size();
        }

        @Override
        public void display() {
            for (int i = 0; i < getSize(); i++) {
                System.out.print(vertexList.get(i));
                for (int j = 0; j < getSize(); j++) {
                    if (adjMat[i][j]) {
                        System.out.print(" -> " + vertexList.get(j));
                    }
                }
                System.out.println();
            }
        }

        @Override
        public void dfs(String sourceLabel) {
            int sourceIndex = indexOf(sourceLabel);
            if (sourceIndex == -1) throw new IllegalArgumentException("Invalid sourceLabel: " + sourceLabel);

            Stack<Vertex> stack = new Stack<>();
            Vertex vertex = vertexList.get(sourceIndex);

            visitVertex(stack, vertex);
            while (!stack.isEmpty()) {
                vertex = getNearUnvisitedVertex(stack.peek());
                if (vertex != null) {
                    visitVertex(stack, vertex);
                } else {
                    stack.pop();
                }
            }
            resetVertexState();
        }

        void resetVertexState() {
            for (Vertex vertex : vertexList) {
                vertex.setVisited(false);
            }
        }

        Vertex getNearUnvisitedVertex(Vertex current) {
            int currentIndex = vertexList.indexOf(current);
            for (int i = 0; i < getSize(); i++) {
                if (adjMat[currentIndex][i] && !vertexList.get(i).isVisited()) {
                    return vertexList.get(i);
                }
            }
            return null;
        }

        void visitVertex(Stack<Vertex> stack, Vertex vertex) {
            System.out.println(vertex.getLabel());
            stack.push(vertex);
            vertex.setVisited(true);
        }

        void visitVertex(Queue<Vertex> queue, Vertex vertex) {
            System.out.println(vertex.getLabel());
            queue.add(vertex);
            allLabelsInBfsQueue.add(vertex.getLabel());
            vertex.setVisited(true);
        }

        @Override
        public void bfs(String sourceLabel) {
            int sourceIndex = indexOf(sourceLabel);
            if (sourceIndex == -1) throw new IllegalArgumentException("Invalid sourceLabel: " + sourceLabel);
            Queue<Vertex> queue = new LinkedList<>();
            Vertex vertex = vertexList.get(sourceIndex);

            visitVertex(queue, vertex);
            while (!queue.isEmpty()) {
                vertex = getNearUnvisitedVertex(queue.peek());
                if (vertex != null) {
                    visitVertex(queue, vertex);
                } else {
                    queue.remove();
                }
            }
            resetVertexState();
        }

        public void findShortestWayByBfs(String sourceLabel, String destinationLabel) {
            int sourceIndex = indexOf(sourceLabel);
            if (sourceIndex == -1) throw new IllegalArgumentException("Invalid sourceLabel: " + sourceLabel);

            Queue<Vertex> queue = new LinkedList<>();
            Vertex vertex = vertexList.get(sourceIndex);

            visitVertex(queue, vertex);
            while (!queue.isEmpty()) {
                vertex = getNearUnvisitedVertex(queue.peek());
                if (vertex != null) {
                    visitVertex(queue, vertex);
                } else {
                    queue.remove();
                }
            }
            resetVertexState();
        }

        public int queueLengthBeforeFirstRemove(String sourceLabel, String destinationLabel) {
            int sourceIndex = indexOf(sourceLabel);
            if (sourceIndex == -1) throw new IllegalArgumentException("Invalid sourceLabel: " + sourceLabel);

            Queue<Vertex> queue = new LinkedList<>();
            Vertex vertex = vertexList.get(sourceIndex);

            visitVertex(queue, vertex);
            while (!queue.isEmpty()) {
                vertex = getNearUnvisitedVertex(queue.peek());
                if (vertex != null) {
                    visitVertex(queue, vertex);
                } else {
                    queue.remove();
                    return queue.size();
                }
            }
            resetVertexState();
            return 0;
        }
    }
}
