package cn.edu.shu.service.serversocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.shu.service.ICurrentDataService;
import cn.edu.shu.service.impl.CurrentServiceImpl;


/**
 * serversocket线程
 * @author use
 *
 */
public class ServerThread extends Thread{

	private ICurrentDataService currentDataService = new CurrentServiceImpl();
	Socket clientSocket = null;//表示用户连接上来的socket实例
	BufferedReader input;//输入流
	PrintWriter output;//输出流
	InputStream inputstream;
	
	//构造函数
	public ServerThread(Socket s){
		this.clientSocket = s;
		InputStreamReader reader;
		
		OutputStreamWriter writer;
		try{
			//初始化输入输出流
			reader = new InputStreamReader(clientSocket.getInputStream());
			inputstream = clientSocket.getInputStream();
			writer = new OutputStreamWriter(clientSocket.getOutputStream(),"utf8");
			input = new BufferedReader(reader);
			output = new PrintWriter(writer,true);
		}catch(IOException e){
			e.printStackTrace();
			
		}
		//服务端输出内容，提示客户端已经成功连接
//		output.println("client link success!");
//		output.flush();
	}
	
	
	@Override
	public void run() {
		// 线程执行方法
		String command = null;
		String str = null;
		//非长链接  不需要持续监听
	//	while(true){
			try{
				/**
				 * 受到换行符的影响，考虑换一种读取客户端报文的方法
				 */
//				str = input.readLine();
				byte[] buf = new byte[1024];
				System.out.println("InputStream==="+inputstream);
				if(inputstream!=null){
					int len= inputstream.read(buf);
					if(len>0){
						str = new String(buf, 0, len);
						System.out.println("获取到的发送报文为："+str);
						//下面进行报文处理，报文格式“设备id，输入电流，输入电压，输出电流，输出电压”
						//解析数据之后，将数据存入实时数据表里面
						String[] arr = str.split(",");
						String inputVol = arr[0];
						String inputCurrent = arr[1];
						String outVol = arr[2];
						String outCurrent = arr[3];
						String deviceID = arr[4];
						currentDataService.insertData(inputVol, inputCurrent, outVol, outCurrent, deviceID);
					}else{
						inputstream=null;
					}					
					output.println("hello");
					output.flush();
					//规定为短连接，每次请求应答会话结束之后需要关闭socket
					output.close();
					this.clientSocket.close();
				}
			}catch(IOException e){
//				e.printStackTrace();
				
			}

//	}
	}
	
}
