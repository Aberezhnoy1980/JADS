package ru.aberezhnoy;

public class Classwork5App {
    public static void iterPrint(int i) {
        while (i >= 0) {
            System.out.print(i-- + " ");
        }
        System.out.print(i + " ");
    }

    private static void recPrint(int i) {
        if (i >= 0) {
            System.out.print(i + " ");
            recPrint(--i);
        }
        System.out.print(i + " ");
    }

    private static int operations = 0;

    private static void put(int from, int to) {
        System.out.printf("%d -> %d | ", from , to);
        if ((++operations % 5) == 0) System.out.println();
    }

    private static void hanoi(int from, int to, int n) {
        int temp = from ^ to; //01^10=11, 01^11=10, 10^11=01
        if (n == 1) {
            put(from, to);
        } else {
            hanoi(from, temp, n - 1);
            put(from, to);
            hanoi(temp, to, n - 1);
        }
    }

    public static void main(String[] args) {
        hanoi(1, 2, 3);
        System.out.println();
        System.out.println(operations);

        iterPrint(5);
        System.out.println();
        recPrint(5);
        System.out.println();
    }
}
