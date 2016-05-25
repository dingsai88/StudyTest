package mina.server;

import mina.common.BaseConfig;

import java.util.Scanner;

import java.util.Date;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 下午5:48:10
 */
public class TimeServerHandler implements IoHandler {
	private static final String TAG = "TimeServerHandler";

	/**
	 * 错误信息
	 */
	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {
		arg1.printStackTrace();

	}

	/**
	 * 接受到的信息
	 */
	public void messageReceived(IoSession session, Object message) throws Exception {
		String str = message.toString();
		System.err.println(TAG + "接受到的消息:" + str);
		if (str.trim().equalsIgnoreCase("quit")) {
			session.close(true);
			return;
		}

		/*
		 * Scanner sc = new Scanner(System.in);
		 * 
		 * boolean quit = false;
		 * 
		 * while(!quit){
		 * 
		 * str = sc.next(); System.err.println(TAG+"您发送了:" + str);
		 * if(str.equalsIgnoreCase("quit")){ quit = true; } session.write(str);
		 * }
		 */
		
/*		Date date = new Date();
		session.write(date.toString());
		System.out.println(TAG + "Message written...");*/
	}

	/**
	 * 发送信息给客户端
	 */
	public void messageSent(IoSession arg0, Object arg1) throws Exception {
 		// System.out.println(TAG+"发送信息:" + arg1.toString());
	}

	/**
	 * 断开连接
	 */
	public void sessionClosed(IoSession session) throws Exception {
 		// System.out.println(TAG+"IP:" + session.getRemoteAddress().toString()
		// + "断开连接");
	}

	/**
	 * 创建完成session
	 */
	public void sessionCreated(IoSession session) throws Exception {
		BaseConfig.set.add(session);
 		// System.out.println(TAG+"IP:" +
		// session.getRemoteAddress().toString());
	}

	/**
	 * 心跳检测
	 */
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
 		// System.out.println(TAG+".sessionIdle " +
		// session.getIdleCount(status));

	}

	/**
	 * 打开session
	 */
	public void sessionOpened(IoSession arg0) throws Exception {
 		// System.out.println(TAG+".sessionOpened " );

	}
}