package ru.aberezhnoy.homework;

import java.util.List;

public class Tree {
    private TreeNode root;

    public Tree() {
        root = null;
    }

    public Tree(List<Integer> integers) {
        integers.forEach(Tree.this::insert);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void insert(Integer i) {
        TreeNode node = new TreeNode(i);
        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (i < current.getInteger()) {
                    current = current.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(node);
                        return;
                    }
                } else if (i > current.getInteger()) {
                    current = current.getRightChild();
                    if (current == null) {
                        parent.setRightChild(node);
                        return;
                    }
                } else return;
            }
        }
    }

    public boolean isBalancedWithPrecision(boolean precision) {
        return Math.abs(countDepth(root.getLeftChild()) - countDepth(root.getRightChild())) <= ((precision) ? 0 : 1);
    }

    private int countDepth(TreeNode node) {
        if (node == null) return 0;

        int left = 0;
        int right = 0;

        if (node.getLeftChild() != null)
            left = countDepth(node.getLeftChild());

        if (node.getRightChild() != null)
            right = countDepth((node.getRightChild()));

        return 1 + Math.max(left, right);
    }

    public int isBalancedEverySubtreeChecked(TreeNode root) {
        if (root == null)
            return 0;
        int lDepth;
        int rDepth;
        lDepth = isBalancedEverySubtreeChecked(root.getLeftChild());
        if (lDepth == -1)
            return -1;
        rDepth = isBalancedEverySubtreeChecked(root.getRightChild());
        if (rDepth == -1)
            return -1;
        if (Math.abs(lDepth - rDepth) > 1)
            return -1;
        return 1 + ((lDepth > rDepth) ? lDepth : rDepth);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}
