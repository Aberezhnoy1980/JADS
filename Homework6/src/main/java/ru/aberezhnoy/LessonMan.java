package ru.aberezhnoy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LessonMan {
    public static void main(String[] args) throws IOException {
        int value;
        Tree theTree = new Tree();
        theTree.insert(new Person("Ivan", 1, 10));
//        theTree.insert(new Person());
//        theTree.insert(new Person());
//        theTree.insert(new Person());
//        theTree.insert(new Person());
//        theTree.insert(new Person());
//        theTree.insert(new Person());
//        theTree.insert(new Person());

        while (true) {
            System.out.println("Enter first letter of show, ");
            System.out.println("Insert, find, delete or traverse: ");
            int choice = getChar();
            switch (choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.println("Enter value to insert: ");
                    value = getInt();
                    theTree.insert(new Person());
                    break;
                case 'f':
                    System.out.println("Enter value to find: ");
                    value = getInt();
                    Node found = theTree.find(value);
                    if (found != null) {

                        System.out.println("Found: ");
                    }
                    found.display();
                    System.out.println("\n");
                    break;
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }

    private static class Stack {
        public int maxSize;
        public Node[] stack;
        public int top;

        public Stack(int maxSize) {
            this.maxSize = maxSize;
            this.stack = new Node[this.maxSize];
            this.top = -1;
        }

        public void push(Node node) {
            this.stack[++this.top] = node;
        }

        public Node pop() {
            return this.stack[this.top--];
        }

        public Node peek() {
            return this.stack[this.top];
        }

        public boolean isEmpty() {
            return (this.top == -1);
        }

        public boolean isFull() {
            return (this.top == this.maxSize - 1);
        }
    }

    private static class Node {
        public Person person;
        public Node leftChild;
        public Node rightChild;

        public void display() {
            System.out.println("Name: " + person.name + ", age: " + person.age);
        }
    }

    private static class Person {
        public String name;
        public int id;
        public int age;

        public Person() {
        }

        public Person(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }

    private static class Tree {
        private Node root;

        public Node find(int key) {
            // method body
            Node current = root;
            while (current.person.id != key) {
                if (key < current.person.id) {
                    current = current.leftChild;
                } else {
                    current = current.rightChild;
                }

                if (current == null) {
                    return null;
                }
            }
            return current;
        }

        public void insert(Person person) {
            // method body
            Node node = new Node();
            if (root == null) {
                root = node;
            } else {
                Node current = root;
                Node parent;
                while (true) {
                    parent = current;
                    if (person.id < current.person.id) {
                        current = current.leftChild;
                        if (current == null) {
                            parent.leftChild = node;
                            return;
                        }
                    } else {
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = node;
                            return;
                        }
                    }
                }
            }
        }

        public boolean delete(int id) {
            // method body
            Node current = root;
            Node parent = root;
            boolean isLeftChild = true;

            while (current.person.id != id) {
                parent = current;
                if (id < current.person.id) {
                    isLeftChild = true;
                    current = current.leftChild;
                } else {
                    isLeftChild = false;
                    current = current.rightChild;
                }
                if (current == null) {
                    return false;
                }
            }

            if (current.leftChild == null && current.rightChild == null) {
                if (current == root) {
                    root = null;
                } else if (isLeftChild) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            } else if (current.rightChild == null) {
                if (current == root) {
                    root = current.leftChild;
                } else if (isLeftChild) {
                    parent.leftChild = current.leftChild;
                } else {
                    parent.rightChild = current.leftChild;
                }
            } else if (current.leftChild == null) {
                if (current == root) {
                    root = current.rightChild;
                } else if (isLeftChild) {
                    parent.leftChild = current.rightChild;
                } else {
                    parent.rightChild = current.rightChild;
                }
            } else {
                Node successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.leftChild = successor;
                } else {
                    parent.rightChild = successor;
                }
                successor.leftChild = current.leftChild;
            }
            return true;
        }
        public void inOrder(Node rootNode) {
            if (rootNode != null) {
                inOrder(rootNode.leftChild);
                rootNode.display();
                inOrder(rootNode.rightChild);
            }
        }

        public Node min() {
            Node current, last = null;
            current = root;
            while (current != null) {
                last = current;
                current = current.leftChild;
            }

            return last;
        }

        public Node max() {
            Node current, last = null;
            current = root;
            while (current != null) {
                last = current;
                current = current.rightChild;
            }
            return last;
        }

        public Node getSuccessor(Node node) {
            Node successorParent = node;
            Node successor = node;
            Node current = node.rightChild;

            while (current != null) {
                successorParent = successor;
                successor = current;
                current = current.leftChild;
            }
            if (successor != node.rightChild) {
                successorParent.leftChild = successor.rightChild;
                successor.rightChild = node.rightChild;
            }
            return successor;
        }

        public void travers(int traversType) {
            switch (traversType) {
                case 1:
                    System.out.println("Preorder traversal");
                    preOrder(root);
                    break;
            }
        }

        public void preOrder(Node rootNode) {
            if (rootNode != null) {
                rootNode.display();
                preOrder(rootNode.leftChild);
                preOrder(rootNode.rightChild);
            }
        }

        public void postOrder(Node rootNode) {
            if (rootNode != null) {
                postOrder(rootNode.leftChild);
                postOrder(rootNode.rightChild);
                rootNode.display();
            }
        }

        public void displayTree() {
            // method body
            Stack stack = new Stack(100);
            stack.push(root);
            int nBlanks = 32;
            boolean isRowEmpty = false;

            while (!isRowEmpty) {
                Stack localStack = new Stack(100);
                isRowEmpty = true;
                for (int i = 0; i < nBlanks; i++) {
                    System.out.println(" ");
                }
                while (!stack.isEmpty()) {
                    Node temp = stack.pop();
                    if (temp != null) {
                        temp.display();
                        localStack.push(temp.leftChild);
                        localStack.push(temp.rightChild);
                        if (temp.leftChild != null || temp.rightChild != null) {
                            isRowEmpty = false;
                        }
                    } else {
                        System.out.println("--");
                        localStack.push(null);
                        localStack.push(null);
                    }
                    for (int j = 0; j < nBlanks * 2 - 2; j++)
                        System.out.println(' ');
                }
                System.out.println(" ");
                nBlanks = nBlanks / 2;
                while (!localStack.isEmpty()) {
                    stack.push(localStack.pop());
                }
                System.out.println("...................................................");
            }
        }
    }
}
