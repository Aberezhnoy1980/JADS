package ru.aberezhnoy;

public class Homework2App {
    public static void main(String[] args) {

        MyArray arr = new MyArray(new int[]{11,21,3,14,5,6,7,8,8,8,8,8});
        arr.display();
        arr.append(10);
        arr.display();
        arr.deleteByValue(6);
        arr.display();
        arr.deleteAll(8);
        arr.display();
        arr.deleteByIndex(0);
        arr.display();
        arr.sortBubble();
        arr.display();
        arr.sortDoubleBubble();
        arr.display();
        arr.pigeon();
        arr.display();
        arr.sortInsert();
        arr.display();
        arr.sortSelect();
        arr.display();
        arr.deleteAll();
        arr.display();

//        long current = System.nanoTime();
//        String s0 = "Ex";
//        for (int i = 0; i < 1_00_000; i++) {
//            s0 += i;
//        }
//        float delta = (System.nanoTime() - current) * 0.000000001f;
//        System.out.println(delta + " sec");
//
//        current = System.nanoTime();
//        StringBuilder sb = new StringBuilder("Ex");
//        for (int i = 0; i < 1_00_000; i++) {
//            sb.append(i);
//        }
//        delta = (System.nanoTime() - current) * 0.000000001f;
//        System.out.println(delta + " sec");
//
//        System.out.println(s0.equals(sb.toString()));
//
//
//        int[] arr1;
//        int arr0[];
//
//        arr1 = new int[5];
//        arr0 = new int[]{1,2,3,4,5};
//        int[] arr2 = {1,2,3,4,5};
//        Integer[] arr3 = {1,2,3,4,5};
//
//        arr3[2] = null;
//
//        System.out.println(arr0);
//        System.out.println(Arrays.toString(arr2));
//
//        arr2[2] = Integer.MAX_VALUE;
//
//        System.out.println(Arrays.toString(arr2));

    }
}
