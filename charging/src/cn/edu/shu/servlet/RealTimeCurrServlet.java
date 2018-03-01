package cn.edu.shu.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.net.Socket;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.edu.shu.entity.DataBuffer;
import cn.edu.shu.utils.JudgeUtils;

public class RealTimeCurrServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	 ArrayList<Double> data1=new ArrayList<Double>();
	 ArrayList<Double> data2=new ArrayList<Double>();
	 DataBuffer daf = new DataBuffer();
	 //boolean bufFlag=true;
	 //ArrayList<Double>data2;单实例多线程，成员变量为多线程共享资源
	// ArrayList<Double>data1;使用共享资源需要同步，保证线程安全，并且要尽量缩小代码
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/xml; charset=UTF-8") ;
				ServletContext context=this.getServletContext();
				 
			    daf=(DataBuffer)context.getAttribute("datas");
				if(daf!=null)
				{ data1=daf.getCurrt1();
				  data2=daf.getCurrt2();
				}
		 try{	
				OutputStream outstr2 = response.getOutputStream();
				//DataOutputStream oos = new DataOutputStream(outstr);///传输基本数据类型
				ObjectOutputStream oos2=new ObjectOutputStream(outstr2);
				/**
				 * 从域中取数据
				 * */	
				
				data1=(ArrayList<Double>)JudgeUtils.isEmptyOperation(data1);
				data2=(ArrayList<Double>)JudgeUtils.isEmptyOperation(data2);
			    oos2.writeObject(data1);  
			    oos2.writeObject(data2);
		      
			    oos2.flush();			 
		        oos2.close();
		        outstr2.close();
				data1.clear();
				data2.clear();
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
