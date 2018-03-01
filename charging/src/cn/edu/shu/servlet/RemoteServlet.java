package cn.edu.shu.servlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.entity.DataBuffer;
import cn.edu.shu.utils.JudgeUtils;

public class RemoteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	 ArrayList<Double> data3=new ArrayList<Double>();
	 ArrayList<Double> data4=new ArrayList<Double>();
	 DataBuffer daf = new DataBuffer();
	 //ArrayList<Double>data4;单实例多线程，成员变量为多线程共享资源
	// ArrayList<Double>data3;使用共享资源需要同步，保证线程安全，并且要尽量缩小代码
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
				response.setContentType("text/xml; charset=UTF-8") ;
				ServletContext context=this.getServletContext();
			    daf=(DataBuffer)context.getAttribute("datas");
				if(daf!=null)
				{ data3=daf.getVolt1();
				  data4=daf.getVolt2();
				}
				
		 try{	
				OutputStream outstr2 = response.getOutputStream();
				//DataOutputStream oos = new DataOutputStream(outstr);///传输基本数据类型
				ObjectOutputStream oos2=new ObjectOutputStream(outstr2);
									
				data3=(ArrayList<Double>)JudgeUtils.isEmptyOperation(data3);
				data4=(ArrayList<Double>)JudgeUtils.isEmptyOperation(data4);
				
				oos2.writeObject(data3);  
				oos2.writeObject(data4);
							
				oos2.flush();				 
				oos2.close();
				outstr2.close();
				data3.clear();
				data4.clear();
				
			}
			catch(Exception e)
			   {
			    e.printStackTrace();
			   }
			
		}
		 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	
}
