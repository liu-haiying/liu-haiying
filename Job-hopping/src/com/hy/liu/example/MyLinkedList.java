package com.hy.liu.example;

import java.util.Objects;

public class MyLinkedList<T> {

    private class Node {

        private Node pre;

        private Node last;

        private T data;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    private Node head = new Node(null);

    private Node last = new Node(null);

    private int size;

    /**
     * 获取size
     *
     * @return
     */
    public int getSize() {

        return size;
    }

    /**
     * 头插
     *
     * @param data 数据
     * @return 是否插入成功
     */
    public boolean addFirst(T data) {

        addBefore(new Node(data), head);

        return true;
    }

    /**
     * 尾插
     *
     * @param data 数据
     * @return 是否插入成功
     */
    public boolean addLast(T data) {

        addAfter(new Node(data), last);

        return true;
    }

    /**
     * 默认插入方法（尾插）
     *
     * @param data 数据
     * @return 是否插入成功
     */
    public boolean add(T data) {

        return addLast(data);
    }

    /**
     * 指定位置插入元素
     *
     * @param index 索引
     * @param data  数据
     * @return 是否插入成功
     */
    public boolean add(int index, T data) {

        addBefore(new Node(data), getNode(index));

        return true;
    }

    /**
     * 获取指定位置元素
     *
     * @param index 索引
     * @return 值
     */
    public T getValue(int index) {

        return getNode(index).data;
    }

    /**
     * 从头部移除元素
     *
     * @return 是否删除成功
     */
    public boolean removeFirst() {

        removeNode(head);

        return true;
    }

    /**
     * 从尾部移除元素
     *
     * @return 是否删除成功
     */
    public boolean removeLast() {

        removeNode(last);

        return true;
    }

    /**
     * 指定位置移除元素
     *
     * @param index 索引
     * @return 是否删除成功
     */
    public boolean remove(int index) {

        removeNode(getNode(index));

        return true;
    }

    /**
     * 根据索引获取节点
     *
     * @param index 索引
     * @return 节点
     */
    private Node getNode(int index) {

        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException("Out of Bounds");

        if (index == 0) {

            return head;
        }

        if (index == size - 1) {

            return last;
        }

        Node node;

        if (index < size / 2) {

            node = head;

            for (int i = 0; i < index; i++) {

                node = node.last;
            }

        } else {

            node = last;

            for (int i = size - 1; i >= index + 1; i--) {

                node = node.pre;
            }

        }
        return node;
    }

    /**
     * 头插法
     *
     * @param newNode 新节点
     * @param node    原来的节点
     */
    private void addBefore(Node newNode, Node node) {

        if (isEmpty()) {

            head = newNode;

            head.last = last;

            last = head;

            last.pre = head;

            size++;

            return;
        }

        newNode.pre = node.pre;

        node.pre.last = newNode;

        node.pre = newNode;

        newNode.last = node;


        if (node == head) {

            head = newNode;
        }

        ++size;
    }

    private void addAfter(Node newNode, Node node) {

        if (isEmpty()) {

            head = newNode;

            head.last = last;

            last = head;

            last.pre = head;

            size++;

            return;
        }

        newNode.last = node.last;

        node.last.pre = newNode;

        node.last = newNode;

        newNode.pre = node;


        if (node == last) {

            last = newNode;
        }

        ++size;
    }

    /**
     * 移除节点
     *
     * @param node
     */
    private void removeNode(Node node) {

        if (size == 0)
            throw new IndexOutOfBoundsException("List is empty");

        if (node == head) {

            head = node.last;
        }

        if (node == last) {

            last = node.pre;
        }

        node.last.pre = node.pre;

        node.pre.last = node.last;

        node.pre = null;

        node.last = null;

        --size;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    private boolean isEmpty() {

        return size == 0;
    }

    @Override
    public String toString() {

        if (Objects.isNull(head)) {

            return "[]";
        }

        StringBuilder builder = new StringBuilder("[");

        Node node = head;

        for (int i = 0; i < size; i++) {

            builder.append(node.data);

            if (i < size - 1) {

                builder.append(",");
            }

            node = node.last;

        }

        return builder.append("]").toString();
    }
}
