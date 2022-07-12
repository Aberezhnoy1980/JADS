package ru.aberezhnoy.homework;

public class TreeNode {
    private final Integer integer;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode(Integer integer) {
        this.integer = integer;
    }

    public Integer getInteger() {
        return integer;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return String.format("TN(%d", integer);
    }
}
