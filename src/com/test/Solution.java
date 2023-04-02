package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode root=new ListNode();
        ListNode cursor=root;
        int carry=0;

        while (l1!=null || l2!=null ||carry!=0 ) {
            int intl1 = l1 == null ? 0 : l1.val;
            int intl2 = l1 == null ? 0 : l1.val;
            int sum=intl1+intl2+carry;
            carry=sum/10;
            ListNode listNode=new ListNode(sum%10);
            cursor.next=listNode;
            cursor=listNode;

            if (l1!=null) l1=l1.next;
            if (l2!=null) l2=l2.next;


        }


        return root.next;

    }

    public String longestCommonPrefix(String[] strs) {
        for (String str : strs) {

        }
return null;
    }

    public int searchInsert(int[] nums, int target) {
        int leftIndex=0;
        int rightIndex=nums.length-1;
        int length=nums.length;
        while(true){
            int middle =length/2;
            if (nums[middle]==target){
                return middle;
            }

            //说明在左边
            if (nums[middle]>target){
                rightIndex=middle;

            }else{
                leftIndex=middle;
            }

        }


    }


}
