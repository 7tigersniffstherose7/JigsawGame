package com.util;

import java.util.Random;

public class RandomPicture {
    public static void main(String[] args) {
        //把0-15打乱存入二维数组

        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //打乱
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //变成二维
        int[][] data = new int[4][4];
        /*法一
        int n = 0;
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 4; ++j) {
                data[i][j] = tempArr[n++];
            }*/
        //法二：
        for (int i = 0; i < tempArr.length; i++) {
            data[i / 4][i % 4] = tempArr[i];
        }

    }
}
