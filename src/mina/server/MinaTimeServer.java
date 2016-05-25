package mina.server;

import org.apache.mina.core.session.IoSession;
import java.util.Scanner;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import mina.common.BaseConfig;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * Mina服务端
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 下午5:45:43
 */
public class MinaTimeServer {
	// 配置端口
	private static final int PORT = BaseConfig.PORT;

	public static void main(String[] args) throws IOException {
		// 创建服务端acceptor
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		// 设置字符集
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));
		// 加入timeHandler
		acceptor.setHandler(new TimeServerHandler());
		// 设置缓冲
		acceptor.getSessionConfig().setReadBufferSize(2048);

		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		// 设置端口
		acceptor.bind(new InetSocketAddress(PORT));

		// 等待输入
		Scanner sc = new Scanner(System.in);

		boolean quit = false;

		System.out.println("服务端已启动:");
		while (!quit) {

			String str = sc.next();
			if (str.equalsIgnoreCase("quit")) {
				quit = true;
			}
			for (IoSession session : BaseConfig.set) {
				session.write("我是服务端:" + str);
			}

		}
	}
}
