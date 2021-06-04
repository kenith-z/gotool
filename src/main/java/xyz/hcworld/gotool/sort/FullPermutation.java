package xyz.hcworld.gotool.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列工具类
 * @ClassName: FullPermutation
 * @Author: 张红尘
 * @Date: 2021-03-23
 * @Version： 1.0
 */
public class FullPermutation<T> {
    /**
     * 结果集
     */
    private final List<List<T>> all = new ArrayList<>();

    /**
     * 默认构造方法
     */
    public FullPermutation(){

    }

    /**
     * 直接执行的构造方法;
     * @param array
     */
    public FullPermutation(List<T> array){
        fullPermutation(array,0);
    }

    /**
     * 使用归并完成全排序
     * @param array 要排序的数字
     * @param start 开始位置
     */
    public  void fullPermutation(List<T> array, int start) {
        if (start == array.size()) {
            List<T> temp = new ArrayList<>(array);
            all.add(temp);
            return;
        }
        for (int i = start; i < array.size(); i++) {
            swap(array,i,start);
            fullPermutation(array, start + 1);
            swap(array,i,start);
        }
    }

    /**
     * 互换元素
     * @param array 数组
     * @param i 要交换的数字下标
     * @param j 要交换的数字下标
     */
    private void swap(List<T> array,int i, int j) {
        T temp = array.get(i);
        array.set(i,array.get(j));
        array.set(j,temp);
    }

    /**
     * 结果
     * @return 全排序结果
     */
    public List<List<T>> result() {
        return all;
    }


}
