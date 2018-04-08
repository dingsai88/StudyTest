package com.ding.jvm;

/**
 * 方法区-运营时常量池-6和6以后有差别。intern更费时间更省内存
 * @author daniel 2018-1-8 0008.
 */
public class Study5RuntimeConstantPoolOOM {

    /**
     *http://blog.csdn.net/seu_calvin/article/details/52291082
     * http://blog.csdn.net/bigtree_3721/article/details/74907670
     *常量池jdk7以前独立存储，所以比较的是常量池的地址和new对象的地址
     * jdk7以后常量池放在堆里，所以地址一样。a、c这样的字母
     * 当存在上述代码的类被JVM加载时，字面值常量 a, b, c 就会被加载到 String 常量池中（注意，这是在编译阶段就确定了的常量字符串）。
     * 所以比较C的时候一个是在堆的常量池里，一个是新new的对象。
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        String str1=new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);

        String str2=new StringBuilder("c").toString();
        System.out.println(str2.intern()==str2);


    }

}
