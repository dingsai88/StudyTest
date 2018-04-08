package com.ding.jvm;


/**
 * VM args:-Xss128k
 * @author daniel 2018-1-5 0005.
 * 说明:hotspot
 */
public class Study2JavaVMStackSOF {

    private int stackLength = 1;

    public void statckLeak() {
        stackLength++;
        statckLeak();
    }

    /**
     * java.lang.StackOverflowError
     * stack length:970
     * hotspot不区分虚拟机栈和本地方法栈。-Xss参数。
     * 线程请求栈深度大于虚拟机允许的最大深度抛出StackOverflowError异常
     * 如果申请足够内存抛出OutOfMemoryError
     *
     * @param args
     */
    public static void main(String[] args) {
        Study2JavaVMStackSOF oom=new Study2JavaVMStackSOF();

        try {
            oom.statckLeak();
        }catch (Throwable e){
            System.out.println("stack length:"+oom.stackLength);
            e.printStackTrace();
        }


    }
}
