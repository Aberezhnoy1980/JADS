package ru.aberezhnoy;

public class Homework1App {
    public static void main(String[] args) {

        System.out.println("Task 1: raising a number to a power\n" + raisingNumberToPower(2, -2));

        System.out.println("Task 2: exponentiation (using the evenness property of the degree)\n" + raisingNumberToPowerUsingEvenness(2, 9));

        System.out.println("Task 3: get the sum of all numbers in series from 0 to 100\n" + getSumFrom0To100(100, 102));

    }

    private static double raisingNumberToPower(double base, int pow) {
        double result = 1;
        if (pow > 0) {
            for (int i = pow; i > 0; i--) {
                result *= base;
            }
        } else if (pow == 0) {
            result = 1;
        } else {
            double tempResult = 1;
            for (int i = pow; i < 0; i++) {
                result = 1 / (tempResult *= base);
            }
        }
        return result;
    }

    private static int raisingNumberToPowerUsingEvenness(int base, int pow) {
        int result = 1;
        if (pow % 2 != 0) {
            pow--;
            result *= base;
        }
        pow /= 2;
        base *= base;
        for (int i = pow; i > 0; i--) {
            result *= base;
//            System.out.println(pow); проверял эффективность на количество итераций.
        }
        return result;
    }


    private static int getSumFrom0To100(int startValue, int endValue) {
        int sum = 0;
        for (int i = startValue; i <= endValue; i++) {
            sum += i;
        }
        return sum;
    }

//    private static int raisingNumberToPowerUsingEvenness(int base, int pow) {
//        int result = 1;
//        while (pow > 0) {
//            if (pow % 2 == 1) {
//                result *= base;
//                --pow;
//            }
//            base *= base;
//            pow >>= 1;
//        }
//        return result;
//    }
//    private static void raisingNumberToPower() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("please, input a base number");
//        int base = sc.nextInt();
//        System.out.println("please, input a power");
//        int pow = sc.nextInt();
//        System.out.println("Result is: " + Math.pow(base, pow));
//    }
//
//
//    private static void raisingNumberToPowerUsingEvenness() {
//        int count = 1;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("please, input a base number");
//        int base = sc.nextInt();
//        System.out.println("please, input a power");
//        int pow = sc.nextInt();
//        if (pow % 2 == 0) {
//            pow /= 2;
//            base *= base;
//        } else {
//            pow--;
//            count *= base;
//        }
//        System.out.println("Result is: " + (int) Math.pow(base, pow) * count);
//    }
//
}
