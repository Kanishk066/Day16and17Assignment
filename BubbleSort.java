package com.bl.algorithmproblems;

import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of integers: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter the integers:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        bubbleSort(nums);

        System.out.println("The sorted list is:");
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
