package ru.aberezhnoy.homework;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Homework6App {
    static void uniqueRandomGenerateAndFill(List<Integer> integerList, int amount, int max, int min) {
        SecureRandom sr = new SecureRandom();
        while (integerList.size() < amount) {
            int integer = sr.nextInt(max - min) + min;
            if (!integerList.contains(integer)) {
                integerList.add(integer);
            }
        }
    }

    public static void main(String[] args) {

        final int TREES = 200;
        int balancedWithPrecision = 0;
        int balancedWithCheckingEverySubtree = 0;
        for (int i = 0; i < TREES; i++) {
            List<Integer> integerList = new ArrayList<>();
            uniqueRandomGenerateAndFill(integerList, 100, 100, -100);
            Tree tree = new Tree(integerList);
            balancedWithPrecision += (tree.isBalancedWithPrecision(false)) ? 1 : 0;
        }
        System.out.println(TREES + " trees created\nwith " + "100" + " elements each\nin range from " + "-100" + " to " + "100\n" + "using recursive structures\n" + "with a share of unbalanced structures = " + (100f - balancedWithPrecision * 100f / TREES + "%\n"));
    }
}
