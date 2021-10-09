package com.hy.liu.leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SummaryRanges {

    private List<Integer> list;

    private List<int[]> ans;

    private int L = 0;

    public SummaryRanges() {

        list = new ArrayList<>();

        ans = new ArrayList<>();

    }

    public void addNum(int val) {

        list.add(val);
    }

    public int[][] getIntervals() {

        int[] in = new int[2];

        Collections.sort(list);

        if (list.size() == 1) {

            int[][] ans = new int[1][2];

            ans[0][0] = list.get(0);

            ans[0][1] = list.get(0);

            return ans;
        }

        for (int i = L; i <= list.size() - 1; i ++) {

            if (i == L) {

                in[0] = list.get(i);
            }

            if (list.get(i) + 1 < list.get(i + 1)) {

                L = i + 1;

                in[1] = list.get(i);

                ans.add(in);

                getIntervals();
            }
        }

        int[][] ans1 = new int[ans.size()][2];

        for (int i = 0; i < ans.size(); i ++) {

            ans1[i][0] = ans.get(i)[0];

            ans1[i][1] = ans.get(i)[1];
        }

        return ans1;
    }

    public static void main(String[] args) {

        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);      // arr = [1]
        summaryRanges.getIntervals(); // 返回 [[1, 1]]
        summaryRanges.addNum(3);      // arr = [1, 3]
        summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
        summaryRanges.addNum(7);      // arr = [1, 3, 7]
        summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
        summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
        summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
        summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
        summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
    }
}
