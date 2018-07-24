package com.yanlei.test;

import java.util.Arrays;
import java.util.Comparator;
/**
 * @Author: x
 * @Date: Created in 10:50 2018/6/22
 */
public class MergeArrayAndOrder
{
    public static void main ( String[] args )
    {
        int[] arr1 = { 1, 3, 5, 7 };
        int[] arr2 = { 1, 2, 6 ,8 };
        String temp = Arrays.toString (arr1) + Arrays.toString (arr2);
        temp = temp.replaceAll ("\\]\\[", ",").replaceAll ("\\s", "").replaceAll ("[\\[\\]]", "");
        String[] result = temp.split ("\\,");
        System.out.println (Arrays.toString (result));
        Arrays.sort (result, new Comparator<String> ()
        {
            @Override
            public int compare ( String o1, String o2 )
            {
                int a = Integer.parseInt (o1), b = Integer.parseInt (o2);
                if (a > b)
                {
                    return 1;
                }
                else if (a < b)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        });
        System.out.println (Arrays.toString (result));
    }
}