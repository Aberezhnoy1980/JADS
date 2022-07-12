package ru.aberezhnoy;

import java.util.Arrays;

public class Homework7App {
    public static void main(String[] args) {

        Graph graph = new Graph(10);

        graph.addVertex('a');
        graph.addVertex('b');
        graph.addVertex('c');
        graph.addVertex('d');
        graph.addVertex('e');
        graph.addVertex('f');
        graph.addVertex('g');
        graph.addVertex('h');
        graph.addVertex('i');
        graph.addVertex('j');

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 0);
        graph.addEdge(3, 7);
        graph.addEdge(5, 6);
        graph.addEdge(6, 8);
        graph.addEdge(6, 9);
        graph.addEdge(7, 9);

        graph.depthTraverse();
        System.out.println();
        System.out.println("------------------");
        graph.widthTraverse();
        System.out.println();
        System.out.println("------------------");

        Stack shortestWay = graph.shortestWay('h', 'e');
        if (shortestWay != null) {
            while (!shortestWay.isEmpty()) {
                System.out.print((char) shortestWay.pop());
                System.out.print(shortestWay.isEmpty() ? "" : " -> ");
            }
        }

        System.out.println();
        Queue queue = graph.widthTraversePath('h', 'e');
        System.out.print(Arrays.toString(queue.getQueue()));
    }
}
