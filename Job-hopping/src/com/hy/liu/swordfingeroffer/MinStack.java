package com.hy.liu.swordfingeroffer;

import java.util.Stack;

/**
 * 剑指 Offer 30. 包含min函数的栈
 * @author 刘海瀛
 * @Date 2021/10/08
 */
public class MinStack {

    Stack<Integer> stackData;

    Stack<Integer> stackMin;

    /** initialize your data structure here. */
    public MinStack() {

        stackData = new Stack<>();

        stackMin = new Stack<>();
    }

    public void push(int x) {

        stackData.push(x);

        if (stackMin.empty() || stackMin.peek() >= x)

            stackMin.push(x);
    }

    public void pop() {

        Integer res = stackData.pop();

        if (res.equals(stackMin.peek()))

            stackMin.pop();
    }

    public int top() {

        return stackData.peek();
    }

    public int min() {

        return stackMin.peek();
    }
}
