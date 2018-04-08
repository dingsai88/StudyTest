package com.ding.jvm;


/**
 * VM args:-Xss2M
 * @author daniel 2018-1-8 0008.
 */
public class Study3JavaVMStackOOM {
    private void dontStop() {
        while (true) {

        }
    }


    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    /**
     * OutOfMemoryError:unable to create new native thread
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        new Study3JavaVMStackOOM().stackLeakByThread();

    }


}
