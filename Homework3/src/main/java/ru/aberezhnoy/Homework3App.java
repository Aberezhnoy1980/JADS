package ru.aberezhnoy;

import ru.aberezhnoy.dataStructures.Stack;

public class Homework3App {

    private static String fancyReverse(String s) {
        Stack stack = new Stack(s.length());
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append((char) stack.pop());
        }
        return sb.toString();
    }

    private static int bracesCheck(String expression) {
        final int TYPES = 3;
        char braces[][] = {{'(', ')'}, {'{', '}'}, {'[', ']'}};
        Stack st = new Stack(10);
        int len = expression.length();
        for (int i = 0; i < len; i++) {
            char ch = expression.charAt(i);
            for (int j = 0; j < TYPES; j++) {
                if (ch == braces[j][0])
                    st.push(j);
            }
            for (int k = 0; k < TYPES; k++) {
                if (ch == braces[k][1]) {
                    if (st.isEmpty()) return i;
                    if (k == st.peek()) st.pop();
                    else return i;
                }
            }
        }
        if (!st.isEmpty())
            return len;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(fancyReverse("HelloWorld"));

        System.out.println(bracesCheck("(){}[]"));
        System.out.println(bracesCheck("()][]"));
    }
}
