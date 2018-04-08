package com.ding.jvm;

import java.util.ArrayList;
import java.util.List;


/**
 * VM args:-XX:PermSize=2M -XX:MaxPermSize=2M
 * @author daniel 2018-1-8 0008.
 */
public class Study4RuntimeConstantPoolOOM {

    /**
     *java.lang.OutOfMemoryError: PermGen space
     *intern 1.6如果常量池不存在则放入常量池，如果存在则复用
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
     List<String> list=new ArrayList<String>();
        int i=0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}

