package me.liuhu.study.leetcode.q242;

import org.junit.Assert;
import org.junit.Test;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/6/27
 **/
public class SolutionTest {

    @Test
    public void run_solution1() {
        Solution solution = new Solution1();
        runTest(solution);
    }


    private void runTest(Solution solution) {
        //Assert.assertTrue(solution.isAnagram("anagram", "nagaram"));
        //Assert.assertFalse(solution.isAnagram("ac", "bb"));
        Assert.assertFalse(solution.isAnagram("ja", "fe"));

    }
}
