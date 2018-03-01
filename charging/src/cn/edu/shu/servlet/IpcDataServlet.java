package cn.edu.shu.servlet;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.entity.DataBuffer;
public class IpcDataServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	 ArrayList<Double>data1=new ArrayList<Double>();
	 ArrayList<Double>data2=new ArrayList<Double>();
	 
	 ArrayList<Double>data3=new ArrayList<Double>();
	 ArrayList<Double>data4=new ArrayList<Double>();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/xml; charset=UTF-8") ;
		InputStream is=request.getInputStream();
		BufferedInputStream bs=new BufferedInputStream(is);
		ObjectInputStream ois=new ObjectInputStream(bs);
		DataBuffer dbf = new DataBuffer();
		
		ServletContext context=this.getServletContext();
		
			try {
						data1=(ArrayList<Double>)ois.readObject();
						data2=(ArrayList<Double>)ois.readObject();
						data3=(ArrayList<Double>)ois.readObject();
						data4=(ArrayList<Double>)ois.readObject();
					} catch (ClassNotFoundException e) {
						data1.clear();data2.clear();data3.clear();data4.clear();
						e.printStackTrace();
					}


					
						dbf.setCurrt1(data1);
						dbf.setCurrt2(data2);
						dbf.setVolt1(data3);
						dbf.setVolt2(data4);
						//data1.clear();data2.clear();data3.clear();data4.clear();
						context.setAttribute("datas", dbf);
				
					//System.out.println("将对象存入域中");
		
		
	}
		
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}

