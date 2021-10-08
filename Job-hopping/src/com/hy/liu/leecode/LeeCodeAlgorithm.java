package com.hy.liu.leecode;

import java.util.*;

/**
 * LeeCode算法题整理
 *
 * @author haiying.liu
 */
public class LeeCodeAlgorithm {

    /**
     * LeeCode第一题 两数之和
     *
     * @param nums   数组
     * @param target 相加结果
     * @return 两数索引
     */
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(target - nums[i])) {

                return new int[]{map.get(target - nums[i]), i};
            }

            map.put(nums[i], i);
        }

        return null;
    }

    /**
     * 滑动窗口求最长字串 LeeCode算法题 第3题
     *
     * @param str 需要计算的字符串
     * @return 最长字串长度
     */
    private Integer lengthOfLongestSubstring(String str) {

        int n = str.length(), ans = 0;

        Map<Character, Integer> map = new HashMap<>();

        for (int end = 0, start = 0; end < n; end++) {

            char alpha = str.charAt(end);

            if (map.containsKey(alpha)) {

                start = Math.max(map.get(alpha), start);
            }

            ans = Math.max(ans, end - start + 1);

            map.put(alpha, end + 1);
        }

        return ans;
    }

    /**
     * LeeCode 451题 根据字符出现频率排序
     *
     * @param s 需要计算的字符串
     * @return 排序后的字符串
     */
    public String frequencySort(String s) {

        if (s.isEmpty())

            return "";

        StringBuilder builder = new StringBuilder();

        Map<Character, Integer> charMap = new LinkedHashMap<>();


        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            charMap.compute(ch, (character, integer) -> integer == null ? 1 : integer + 1);
        }

        charMap.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .forEach(item -> {
                    builder.append(String.valueOf(item.getKey()).repeat(Math.max(0, item.getValue())));
                });

        return builder.toString();
    }

    // leecode数独
    public Boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < board.length; i++) {

            List<Character> characterList1 = new ArrayList<>();

            List<Character> characterList2 = new ArrayList<>();

            List<Character> characterList3 = new ArrayList<>();

            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] != '.')

                    characterList1.add(board[i][j]);

                if (board[j][i] != '.')

                    characterList2.add(board[j][i]);
            }
            Set<Character> set1 = new HashSet<>(characterList1);

            Set<Character> set2 = new HashSet<>(characterList2);

            if (characterList1.size() != set1.size() || characterList2.size() != set2.size()) {

                return false;
            }
        }

        for (int i = 0; i < board.length; i += 3) {


            for (int j = 0; j < board[i].length; j += 3) {

                List<Character> characterList3 = new ArrayList<>();

                characterList3.add(board[i][j]);
                characterList3.add(board[i][j + 1]);
                characterList3.add(board[i][j + 2]);
                characterList3.add(board[i + 1][j]);
                characterList3.add(board[i + 1][j + 1]);
                characterList3.add(board[i + 1][j + 2]);
                characterList3.add(board[i + 2][j]);
                characterList3.add(board[i + 2][j + 1]);
                characterList3.add(board[i + 2][j + 2]);

                characterList3.removeAll(new ArrayList<>(Collections.singleton('.')));

                HashSet<Character> set = new HashSet<>(characterList3);

                if (characterList3.size() != set.size())

                    return false;
            }

        }

        return true;

    }

    // leecode 第725题
    public ListNode[] splitListToParts(ListNode head, int k) {

        ListNode temp = head;

        // 链表长度
        int n = 0;

        while (Objects.nonNull(temp)) {

            n++;

            temp = temp.next;
        }

        int len = n / k, remainder = n % k;

        ListNode[] ans = new ListNode[k];

        ListNode cur = head;

        for (int i = 0; i < k; i++) {

            ans[i] = cur;

            for (int j = 1; j < len + (i < remainder ? 1 : 0); j++) {

                cur = cur.next;
            }

            if (Objects.isNull(cur))
                break;

            ListNode next = cur.next;

            cur.next = null;

            cur = next;
        }

        return ans;
    }

    public int searchInsert(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            if (target == nums[i]) {

                return i;
            }
        }

        for (int i = 0; i < nums.length; i++) {

            if (target >= nums[i] && target <= nums[i + 1])

                return i + 1;
        }

        return 0;

    }

    /**
     * leecode 5. 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {


        if (s == null || s.length() < 2) {
            return s;
        }

        int left = 0, right = 0;

        for (int i = 0; i < s.length(); i++) {

            int len1 = expandAroundCenter(s, i, i);

            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > right - left) {

                left = i - (len - 1) / 2;

                right = i + len / 2;
            }

        }

        return s.substring(left, right + 1);
    }

    // 计算回文子串长度
    private int expandAroundCenter(String s, int left, int right) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {

            --left;

            ++right;

        }

        return right - left - 1;
    }

    /**
     * leecode 430. 扁平化多级双向链表
     *
     * @param head
     * @return
     */
    public Node flatten(Node head) {

        dfs(head);

        return head;
    }

    public Node dfs(Node node) {

        Node tmp = node;

        Node last = null;

        while (tmp != null) {

            Node next = tmp.next;

            if (tmp.child != null) {

                Node childNode = dfs(tmp.child);

                next = tmp.next;

                tmp.next = tmp.child;

                tmp.next.prev = tmp;

                if (next != null) {

                    childNode.next = next;

                    next.prev = childNode;
                }

                tmp.child = null;

                last = childNode;
            } else {

                last = tmp;
            }

            tmp = next;
        }

        return last;
    }

    /**
     * leecode 371. 两整数之和
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {

        while (b != 0) {

            int carry = (a & b) << 1;

            a ^= b;

            b = carry;
        }

        return a;
    }

//    /**
//     *
//     * leecode 1.两数之和
//     *
//     * @param nums
//     * @param target
//     * @return
//     */
    public int[] twoSum1(int[] nums, int target) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (hashMap.containsKey(target - nums[i])) {

                return new int[] {hashMap.get(target - nums[i]), i};
            }

            hashMap.put(nums[i], i);
        }

        throw new RuntimeException("NoAnswer");
    }

    /**
     * leecode 11. 盛最多水的容器 (最暴力解法)  时间复杂度很高
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int sq = 0;

        for (int i = 0; i < height.length; i++) {

            for (int j = i + 1; j < height.length; j++) {

                int width = j - i;

                int len = Math.min(height[i], height[j]);

                sq = Math.max(sq, len * width);
            }
        }

        return sq;
    }

    /**
     *
     * leecode 11. 盛最多水的容器(双指针解法)
     *
     * @return
     */
    public int maxArea2(int[] height) {

        int l = 0, r = height.length - 1;

        int ans = 0;

        while (l < r) {

            int area = Math.min(height[l], height[r]) * (r - l);

            ans = Math.max(ans, area);

            if (height[l] <= height[r]) {

                l ++;
            } else {

                r --;
            }
        }

        return ans;
    }

    /**
     *
     * leecode 437. 路径总和 III
     *
     * @param root 根节点
     * @param targetSum 所需数字和
     * @return 路径总数
     */
    public int pathSum(TreeNode root, int targetSum) {

        if (root == null) {

            return 0;
        }

        int ret = 0;

        ret += rootSum(root, targetSum);

        ret += pathSum(root.left, targetSum);

        ret += pathSum(root.right, targetSum);

        return ret;
    }

    public int rootSum(TreeNode root, int targetSum) {

        int ret = 0;

        if (root == null) {

            return 0;
        }

        int val = root.val;

        if (val == targetSum) {

            ret ++;
        }

        ret += rootSum(root.left, targetSum - val);

        ret += rootSum(root.right, targetSum - val);

        return ret;
    }

    public int findMinMoves(int[] machines) {

        int num = Arrays.stream(machines).reduce(0, Integer::sum);

        if (num % machines.length != 0) {

            return -1;
        }

        int avg = num / machines.length;

        int ans = 0, sum = 0;

        for (int mac: machines) {

            mac -= avg;

            sum += mac;

            ans = Math.max(ans, Math.max(Math.abs(sum), mac));
        }

        return ans;
    }

    /**
     *
     * leecode 223. 矩形面积
     *
     * @param ax1
     * @param ay1
     * @param ax2
     * @param ay2
     * @param bx1
     * @param by1
     * @param bx2
     * @param by2
     * @return
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {

        int areaA = (ax2 - ax1) * (ay2 - ay1), areaB = (bx2 - bx1) * (by2 - by1);

        int w = Math.min(ax2, bx2) - Math.max(ax1, bx1), h = Math.min(ay2, by2) - Math.max(ay1, by1);

        int areaX = Math.max(w, 0) * Math.max(h, 0);

        return areaA + areaB - areaX;
    }

    /**
     *
     * leecode 187. 重复的DNA序列
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {

        Map<String, Integer> map = new HashMap<>();

        List<String> ans = new ArrayList<>();

        for (int i = 0; i <= s.length() - 10; i ++) {

            String str = s.substring(i, i + 10);

            map.put(str, map.getOrDefault(str, 0) + 1);

            if (map.get(str) == 2) {

                ans.add(str);
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        LeeCodeAlgorithm leeCode = new LeeCodeAlgorithm();

        System.out.println(leeCode.findRepeatedDnaSequences("AAAAAAAAAAAA"));
    }
}

class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}


