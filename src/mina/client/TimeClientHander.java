package mina.client;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 监听触发
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 下午5:54:45
 */

public class TimeClientHander implements IoHandler {
	private static final String TAG="";
    /**
     * 发生异常
     */
    public void exceptionCaught(IoSession arg0, Throwable arg1)
            throws Exception {
         arg1.printStackTrace();
    }

    /**
     * 接受到的信息
     */
    public void messageReceived(IoSession arg0, Object message) throws Exception {
         System.err.println(TAG+"client接受信息:"+message.toString());
    }

   /**
    * 发送信息给服务端
    */
    public void messageSent(IoSession arg0, Object message) throws Exception {
     // System.out.println(TAG+"client发送信息"+message.toString());
    }

    /**
     * 连接关闭
     */
    public void sessionClosed(IoSession session) throws Exception {
        // System.out.println(TAG+"client与:"+session.getRemoteAddress().toString()+"断开连接");
    }

/**
 * 创建完成连接
 */
    public void sessionCreated(IoSession session) throws Exception {
       //  System.out.println(TAG+"client与:"+session.getRemoteAddress().toString()+"建立连接");
    }

   /**
    * 心跳检测
    */
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
      //   System.out.println( TAG+" sessionIdle IDLE " + session.getIdleCount( status ));
    }

 
    public void sessionOpened(IoSession arg0) throws Exception {
        // System.out.println(TAG+"打开连接");
    }

}