package application.utils;

import java.io.IOException;
import java.net.*;

/*
 * TcpUdpConnectUtil:提供TCP、UCP的方法
 */
public class TcpUdpConnectUtil {
	//创建一个TCP服务端口
	public static ServerSocket createServerSocket(final int port) throws IOException{
		ServerSocket serverSocket = null;
		serverSocket = new ServerSocket(port);
		return serverSocket;
	}
	
	//创建一个TCP连接套接字
	public static Socket createSocket(final String ip,final int port) throws UnknownHostException, IOException{
		Socket socket = null;
		socket = new Socket(ip, port);
		return socket;
	}
	
	//创建一个DatagramSocket服务udp
	public static DatagramSocket createDatagramSocket(final int port) throws SocketException{
		return new DatagramSocket(port);
	}
	
	//创建一个DatagramSocket的client udp
	public static DatagramSocket createClientDatagramSockt() throws SocketException{
		return new DatagramSocket();
	}
}
