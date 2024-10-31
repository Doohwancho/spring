package com.tdd.tddTest.util;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SortArray {
    public static int[] findTopN_Sort(int[] arr, int n) {
        // 전체 배열 정렬
        Arrays.sort(arr);  // O(n log n)
        // 뒤에서 n개 선택
        return Arrays.copyOfRange(arr, arr.length - n, arr.length);
    }
    
    public static int[] findTopN_MinHeap(int[] arr, int n) {
        // n개 크기의 최소 힙 생성
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);
        
        for (int num : arr) {  // O(n)
            if (minHeap.size() < n) {
                minHeap.offer(num);  // O(log n)
            } else if (num > minHeap.peek()) {
                minHeap.poll();      // O(log n)
                minHeap.offer(num);  // O(log n)
            }
        }
        
        // 결과 배열로 변환
        return minHeap.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public static int[] findTopN_QuickSelect(int[] arr, int n) {
        int left = 0;
        int right = arr.length - 1;
        int targetIdx = arr.length - n;
        
        while (left < right) {
            int pivotIdx = partition(arr, left, right);
            if (pivotIdx == targetIdx) {
                break;
            } else if (pivotIdx < targetIdx) {
                left = pivotIdx + 1;
            } else {
                right = pivotIdx - 1;
            }
        }
        
        return Arrays.copyOfRange(arr, targetIdx, arr.length);
    }
    
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, right);
        return i + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
