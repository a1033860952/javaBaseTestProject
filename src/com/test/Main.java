package com.test;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(permute(new int[]{1}));

    }

    static List<Integer> path;
    static List<List<Integer>> res;
    static boolean[] dp;

    public static List<List<Integer>> permute(int[] nums) {
        path = new ArrayList<>();
        res = new ArrayList<>();
        dp = new boolean[]{false, false, false};
        dfs(0, nums);
        return res;
    }


    private static void dfs(int u, int[] num) {
        int length = num.length;
        if (u == length) {
            //如果不重新net个list，值会跟着改变的
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < length; i++) {
            //当目前这个位数没有被使用过才能到下一步
            if (!dp[i]) {
                path.add(num[i]);
                dp[i] = true;
                dfs(u + 1, num);
                dp[i] = false;
                path.remove(path.size() - 1);
            }

        }

    }


    public static int rangeBitwiseAnd(int left, int right) {
        //这题采用了&的特性，即相同位的二进制与运算后得到出的结果不是1，那么再与运算后面进制的数将没有意义，因为任何数与0都是0
        //且left和right的最高位二进制为必须是一致为1，因为1&1得到的结果为1
        //若一开始两位的有效二进制就是不对等的，比如1011和11011，一个有效二进制位为4位，一个是五位，注定最后结果为0
        //当两个二进制最高位不是都为1，哪怕第二个高位是一致的也没有意义意义，因为在这个区间内，一定有能让这两个第二高位与运算后不一致
        //而如果两个二进制最高位与得到的结果是1，就一定能得到一个保底与运算的数值，因为在这个区间内，一定不会有比left小的，比right大的数
        //也就保证了两个最高位的1无人能动，然后往下第二高位，若与运算后还是1，就还是上面那个原因
        int answer = 0;
        //int的最大值为2147483647，所以的31位
        for (int i = 31; i >= 0; i--) {
            //若都是无效位0则继续往下走，遇到有任意一个不为0，就说明到了最高位了，判断不一致，就没必要往下了
            if ((left >> i & 1) != (right >> i & 1)) {
                break;
            }
            //因为left比right小，所以判断它
            if ((left >> i & 1) == 1) {
                answer += 1 << i;
            }

        }
        return answer;


    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        //行长度
        int rowLength = matrix.length;
        //列长度
        List<Integer> answerList = new ArrayList<>();
        if (rowLength == 0) {
            return answerList;
        }
        int colLength = matrix[0].length;
        boolean[][] dp = new boolean[rowLength][colLength];
        //方向向量
        int dirx[] = {0, 1, 0, -1}, diry[] = {1, 0, -1, 0};

        int x = 0, y = 0, dir = 0;
        for (int i = 0; i < rowLength * colLength; i++) {
            answerList.add(matrix[x][y]);
            dp[x][y] = true;
            int a = x + dirx[dir];
            int b = y + diry[dir];

            //每走一步就判断一次四个方向
            for (int j = 0; j < 4; j++) {
                //若第一个方向没有走进去，那么就不会判断下一个方向，即四次循环结果是一样的，若第一次能走进去，则循环才有意义
                if (a < 0 || a >= rowLength || b < 0 || b >= colLength || dp[a][b]) {
                    //用这个来实现 dir+1的情况，一语双关  我去
                    dir = (dir + 1) % 4;
                    a = x + dirx[dir];
                    b = y + diry[dir];
                }
            }
            x = a;
            y = b;
        }


        return answerList;
    }


    public static int reverse(int x) {
        StringBuilder stringBuffer = new StringBuilder();
        String s = String.valueOf(x);
        if (x < 0) {
            stringBuffer.append("-");
            for (int i = s.length() - 1; i > 0; i--) {
                char c = s.charAt(i);
                stringBuffer.append(c);
            }
        } else {
            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);
                stringBuffer.append(c);
            }
        }


        try {
            return Integer.parseInt(stringBuffer.toString());
        } catch (Exception e) {
            return 0;
        }
    }


    public static String convert(String s, int numRows) {
        String[] stringArray = new String[numRows];
        Arrays.fill(stringArray, "");
        int law = numRows * 2 - 2;
        if (law <= 0) {
            return s;
        }
        char[] charList = s.toCharArray();
        for (int i = 0; i < charList.length; i++) {
            int mod = i % law;
            if (mod < numRows) {
                stringArray[mod] += String.valueOf(charList[i]);
            } else {
                stringArray[law - mod] += String.valueOf(charList[i]);
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (String s1 : stringArray) {
            stringBuffer.append(s1);
        }
        return stringBuffer.toString();
    }

    //采用动态规划算法，自底向上
    public static String longestPalindrome(String s) {
        int length = s.length();
        //两个字符是否相同
        boolean dp[][] = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        //边界，若只有一个，则就是回文子串
        if (length <= 1) {
            return s;
        }

        //i为两个数值判断是间隔
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < length; j++) {
                //比较的两个值
                int left, right;
                left = j;
                right = j + i;
                if (right >= length) {
                    break;
                }
                if (s.charAt(left) == s.charAt(right)) {
                    if (i >= 2) {
                        dp[left][right] = dp[left + 1][right - 1];
                    } else {
                        dp[left][right] = true;
                    }
                } else {
                    dp[left][right] = false;
                    dp[right][left] = false;
                }
            }

        }

        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j < length; j++) {
                if (j + i >= length) {
                    break;
                }
                if (dp[j][j + i]) {
                    return s.substring(j, j + i + 1);
                }
            }

        }
        return null;
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] totalNums = mergeArray(nums1, nums2);
        int length = totalNums.length;
        if (length % 2 == 0) {
            int i = length / 2;
            int number = totalNums[i - 1] + totalNums[i];
            return 1.0 * number / 2;
        } else {
            return totalNums[length];
        }
    }

    private static int[] mergeArray(int[] nums1, int[] nums2) {
        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        int totalLength = nums1Length + nums2Length;
        int index = 0;
        int[] resultNums = new int[totalLength];
        int i = 0, j = 0;
        while (i < nums1Length && j < nums2Length) {
            if (nums1[i] <= nums2[j]) {
                resultNums[index] = nums1[i];
                index++;
                i++;
            } else {
                resultNums[index] = nums2[j];
                index++;
                j++;
            }
        }
        while (i < nums1Length) {
            resultNums[index] = nums1[i];
            index++;
            i++;
        }

        while (j < nums2Length) {
            resultNums[index] = nums2[j];
            index++;
            j++;
        }
        return resultNums;
    }
}
