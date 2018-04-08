package com.ding.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args:-Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
 * Created by 老丁 on 2018-1-5 0005.
 * 说明:
 * 堆大小设置为20m 不可拓展(xms和xmx一样避免拓展)，设置heapDumpOnOutOfMemoryError让内存溢出时DUMP出当前内存堆转储快照
 */
public class Study1HeapOOM {
    static class OOMObject{

    }

    /**
     * java.lang.OutOfMemoryError:java heap space
     * 堆放对象溢出
     * @param args
     */
    public static void main(String [] args){
        List<OOMObject> list=new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }

    }

}
