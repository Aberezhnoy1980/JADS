package ru.aberezhnoy;

public class Homework5App {


    private static double powerRec(double base, int pow) {
        double res;
        if (pow == 0)
            res = 1;
        else if (base == 0)
            res = 0;
        else if (pow < 0) {
            double tempRes = 1;
            res = 1 / base * (tempRes * powerRec(base, pow + 1));
        }
        else
            res = base * powerRec(base, pow - 1);
        return res;
    }

    private static int powerRecFromLesson(int base, int pow) {
        return (pow == 0) ? 1 : (powerRecFromLesson(base, pow - 1) * base);
    }

    //  ** task from lesson
    private static int[][] moves = {
            {-2, 1}, {-1, 2}, {1, 2}, {2, 1},
            {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    private static boolean isPossible(int[][] desk, int x, int y) {
        return x >= 0 && x < desk[0].length &&
                y >= 0 && y < desk.length &&
                desk[y][x] == 0;
    }

    private static boolean knight(int[][] desk, int x, int y, int move) {
        desk[y][x] = move;
        if (move > desk.length * desk[0].length - 1) return true;

        int nextX;
        int nextY;
        for (int i = 0; i < moves.length - 1; i++) {
            nextX = x + moves[i][0];
            nextY = y + moves[i][1];
            if (isPossible(desk, nextX, nextY) && knight(desk, nextX, nextY, move + 1))
                return true;
        }
        desk[y][x] = 0;
        return false;
    }

    private static void printDesk(int[][] desk) {
        for (int y = 0; y < desk.length; y++) {
            for (int x = 0; x < desk[y].length; x++) {
                System.out.printf("%3d", desk[y][x]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(powerRec(2, 0));

        int[][] desk = new int[8][8];
        knight(desk, 0, 1, 1);
        printDesk(desk);
    }
}
