package cn.edu.shu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.edu.shu.service.serversocket.Server;
/**
 * 
 * @author 
 *创建监听，随着tomcat启动一起启动
 */
public class PortListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/***
	 * serversocket
	 */
	public void StartServer() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Server();
			}
		}).start();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("我已经随着项目启动而启动了");
		//开启线程，创建serversocket进行端口监听
		StartServer();
	}

}
