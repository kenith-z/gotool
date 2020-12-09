package xyz.hcworld.gotool.net;

import java.net.InetAddress;
/**
 * @classDesc: 功能描述：(网络测试 ICMP ping)
 * @author : 张红尘
 * @date 创建时间：2020年12月2日 上午12:17:28
 * @version 1.0
 */
public class Ping {
	/**
	 * 默认要ping的主机
	 */
	private static final String HOST = "baidu.com";
	/**
	 * 默认超时时间
	 */
	private static final int TIME_OUT = 5000;
	/**
	 * ICMP ping方法
	 * 
	 * @param host ip或者域名
	 * @param timeOut 超时时间
	 * @return 通畅：true <br/>
	 *         无连接：false
	 */
	public static boolean ping(String host,int timeOut) {
		boolean status = false;
		try {
			status = InetAddress.getByName(host).isReachable(timeOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * ICMP ping方法(无参数时默认ping百度，超时时间为5秒)
	 *
	 * @return 通畅：true <br/>
	 *         无连接：false
	 */
	public static boolean ping() {
		return ping(HOST,TIME_OUT);
	}
	/**
	 * ICMP ping方法(自定义要ping的主机，超时时间为5秒)
	 * @param host 目标主机，ip或者域名
	 * @return 通畅：true <br/>
	 *         无连接：false
	 */
	public static boolean ping(String host) {
		return ping(host,TIME_OUT);
	}
	/**
	 * ICMP ping方法(ping百度，超时时间自定义)
	 * 	 * @param timeOut 超时时间
	 * @return 通畅：true <br/>
	 *         无连接：false
	 */
	public static boolean ping(int timeOut) {
		return ping(HOST,timeOut);
	}
}
