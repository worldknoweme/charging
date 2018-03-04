package cn.edu.shu.service.serversocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	final int PORT = 9090;//serversocket监听的端口号
	//构造函数
	public Server(){
		ServerSocket rSocket = null;//serversocket实例
		Socket request = null;//用户请求socket
		Thread serverThread = null;
		try{
			rSocket = new ServerSocket(PORT);
			System.out.println("The Port is:"+PORT);
			while(true){
				//等待客户端连接
				request = rSocket.accept();
				//接收客户机连接请求
				serverThread = new ServerThread(request);
				//生成serverThread实例
				serverThread.start();
				//启动线程
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
