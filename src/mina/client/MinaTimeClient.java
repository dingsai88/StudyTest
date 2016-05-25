package mina.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

import mina.common.BaseConfig;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * 客户端调用
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 下午5:51:21
 */
public class MinaTimeClient {

	public static void main(String[] args) {
	
		/**
		 * 客户端创建连接器
		 */
		IoConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		//设置字符集等
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));
		connector.setHandler(new TimeClientHander());
		//配置服务端IP和端口
		ConnectFuture connectFuture = connector.connect(new InetSocketAddress("127.0.0.1", BaseConfig.PORT));
		// 等待建立连接
		connectFuture.awaitUninterruptibly();

		IoSession session = connectFuture.getSession();
		//获取控制台输入
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		System.out.println("客户端已启动:");

		while (!quit) {
			String str = sc.next();
			//如果输入的是quit则结束
			if (str.equalsIgnoreCase("quit")) {
				quit = true;
			}
			//发送信息到服务端
			session.write(str);
		}

		// 关闭
		if (session != null) {
			if (session.isConnected()) {
				session.getCloseFuture().awaitUninterruptibly();
			}
			connector.dispose(true);
		}

	}

}