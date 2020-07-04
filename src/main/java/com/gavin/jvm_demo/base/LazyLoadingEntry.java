package com.gavin.jvm_demo.base;

/**
 *
 * 展现懒加载的具体表现
 *
 */
public class LazyLoadingEntry {
    public static void main(String[] args) {
        //    可以轮流注释了查看不同语句下的不同情况
        P p;
        System.out.println(S.i);
        System.out.println(S.j);
    }

    public static class P{
        final  static int i = 1;
        static int j = 9;
        static{
            System.out.println("P");
        }
    }

    public static class S extends P{
        static{
            System.out.println("S");
        }
    }
}
