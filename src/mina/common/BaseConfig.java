package mina.common;

import java.util.HashSet;

import java.util.Set;

import java.util.HashMap;

import org.apache.mina.core.session.IoSession;

import java.util.Map;

/**
 * 公共基础
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 下午5:51:07
 */
public class BaseConfig {
    //服务器端口
    public static final int PORT = 9123;
    //所有客户端
    public static Map<String,IoSession> map=new HashMap<String,IoSession>();
    //存放客户端列表
    public static Set<IoSession>set=new HashSet<IoSession>();
}
