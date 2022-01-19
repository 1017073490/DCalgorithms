package com.zhangxing.algorithms.sliding_windows;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] input = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] output = new SlidingWindowMaximum().maxSlidingWindowLRScan(input, 3);
        for (int i : output) {
            System.out.println(i);
        }
    }

    public int[] maxSlidingWindowViolentSolution(int[] nums, int k) {
        // 时间复杂度：O(N * k)
        // 空间复杂度：O(N - k)
        // 暴力法：遍历每一个窗口，对每个窗口遍历每个元素求最大值
        int[] resArray = new int[nums.length - k + 1];
        // 遍历数组：从 0 到 n - k，作为滑到窗口的起始位置
        for (int i = 0; i <= nums.length - k; i++) {
            // 找窗口内的最大值
            int max = nums[i];
            // 遍历每个窗口
            for (int j = i + 1; j < i + k; j++) {
                if (max < nums[j]) {
                    max = nums[j];
                }
            }
            resArray[i] = max;
        }
        return resArray;
    }

    public int[] maxSlidingWindowMaxHeap(int[] nums, int k) {
        // 时间复杂度：O(N * log k)
        // 空间复杂度：O(N - k)
        int[] resArray = new int[nums.length - k + 1];
        // 使用优先队列，实现大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 准备好工作，构建大顶堆。堆中有 k 个，即窗口大小个数的元素
        for (int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }
        // 当前大顶堆的堆顶元素就是第一个窗口的最大值
        resArray[0] = maxHeap.peek();
        // 遍历所有的窗口，一共 n - k + 1 个窗口
        for (int i = 1; i <= nums.length - k; i++) {
            // 删除堆中上一个窗口的第一个元素
            maxHeap.remove(nums[i - 1]);
            // 添加当前窗口的最后一个元素入堆
            maxHeap.add(nums[i + k - 1]);
            resArray[i] = maxHeap.peek();
        }
        return resArray;
    }

    public int[] maxSlidingWindowDeque(int[] nums, int k) {
        // 时间复杂度：O(N)
        // 空间复杂度：O(N)
        // 双向队列
        int[] resArray = new int[nums.length - k + 1];
        // 定义双向队列，保存元素的索引
        // 每来一个元素，总要得到当前最大值，但是不是总固定保留 k 个元素，
        // 只把之前所有比当前元素小的那个数全部删掉
        // 队列每一论迭代只保留，从大到小、递减的一个队列
        // 更新原则为：保存 “ 更新更大 ” 的元素
        // 两个操作结合，队首可能需要删除、队尾可能需要添加
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        // 初始化
        for (int i = 0; i < k; i++) {
            // 如果队尾元素小于当前元素，直接删除
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        resArray[0] = nums[deque.getFirst()];
        // 遍历数组所有元素，作为窗口的结束位置，
        for (int i = k; i < nums.length; i++) {
            // 判断，如果需要删掉的就是上一个窗口最大值，那么需要将队列中的最大值删掉
            // 因为窗口必须移动
            if (!deque.isEmpty() && deque.getFirst() == i - k) {
                deque.removeFirst();
            }
            // 判断新增元素是否可以删除队尾元素
            // 如果队尾小于当前元素，直接删除
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            // 保存结果
            resArray[i - k + 1] = nums[deque.getFirst()];
        }
        return resArray;
    }

    public int[] maxSlidingWindowLRScan(int[] nums, int k) {
        // 时间复杂度：O(N)
        // 空间复杂度：O(N)
        int n = nums.length;
        // 定义结果数组
        int[] resArray = new int[n - k + 1];
        // 定义存放块内最大值的 left 和 right 数组
        int[] left = new int[n];
        int[] right = new int[n];
        // 左右扫描
        for (int i = 0; i < n; i++) {
            // 从左至右
            if (i % k == 0) {
                // 初始位置
                left[i] = nums[i];
            } else {
                // 不是起始位置，直接和 left[i-1] 比较取较大值
                left[i] = Math.max(left[i - 1], nums[i]);
            }
            // 从右至左
            int j = n - 1 - i;
            if (j % k == k - 1 || j == n - 1) {
                // 初始位置
                right[j] = nums[j];
            } else {
                // 不是起始位置，直接和 right[j+1] 比较取较大值
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }
        // 再次扫描一次，对每个窗口取最大值
        for (int i = 0; i < n - k + 1; i++) {
            resArray[i] = Math.max(right[i], left[i + k - 1]);
        }
        return resArray;
    }
}
