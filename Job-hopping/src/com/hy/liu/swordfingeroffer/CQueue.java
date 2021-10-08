package com.hy.liu.swordfingeroffer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 * @author 刘海瀛
 * @Date 2021/10/08
 */
public class CQueue {

    Deque<Integer> stackAdd;

    Deque<Integer> stackDel;

    public CQueue() {

        stackAdd = new LinkedList<>();

        stackDel = new LinkedList<>();
    }

    public void appendTail(int value) {

        stackAdd.add(value);
    }

    public int deleteHead() {

        if (stackDel.isEmpty()) {

            while (!stackAdd.isEmpty()) {

                stackDel.add(stackAdd.pop());
            }
        }

        if (stackDel.isEmpty()) {

            return -1;
        }

        return stackDel.pop();
    }
}
