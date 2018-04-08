package com.ding.jvm;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**Ö±½ÓÄÚ´ædirect
 * VM args:-Xmx2M -XX:MaxDirectMemorySize=1M
 * @author daniel 2018-1-8 0008.
 */
public class Study7DirectMemoryOOM {

    private static final int _1BM=1024*1024;
    public static void main(String[] args) throws Throwable {

        Field unsafeField= Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe=(Unsafe)unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1BM);
        }


    }
}
