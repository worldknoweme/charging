package cn.edu.shu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.dao.impl.DataAcquisitionDao;
import cn.edu.shu.entity.AlertData;
import cn.edu.shu.entity.RealData;
import cn.edu.shu.service.impl.DataAcqService;
import cn.edu.shu.utils.TimeUtils;
import cn.edu.shu.utils.ToObjArrayUtil;

/**
 * 实时数据及报警信息显示，数据报警判断
 */ 
public class RealTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml; charset=UTF-8") ;
		response.setHeader("Cache-Control", "no-cache") ;
		
		
		PrintWriter out = response.getWriter() ; ///获取输出流进行输出
		out.print("<response>") ;
		
		Calendar c = Calendar.getInstance() ;
		int year = c.get(Calendar.YEAR) ;
		int month = c.get(Calendar.MONTH)+1 ;/////月份是0~11算起的，所以要加1就是1~12
		int day = c.get(Calendar.DAY_OF_MONTH) ;
		int hour = c.get(Calendar.HOUR_OF_DAY) ;
		int minute = c.get(Calendar.MINUTE) ;
		int second = c.get(Calendar.SECOND) ;
		int millisecond= c.get(Calendar.MILLISECOND);
/**取得实时数据，判断故障，并写入数据库*/
		DataAcqService das=new DataAcqService();
		RealData array=das.getRealTimeData();
		if(array!=null){
			int id=array.getId();
			out.println("<vol>"+array.getVoltage1()+"</vol>") ;
			out.println("<vol2>"+array.getVoltage2()+"</vol2>") ;
			out.println("<cur1>"+array.getCurrent1()+"</cur1>") ;
			out.println("<cur2>"+array.getCurrent2()+"</cur2>") ;
			out.println("<zlNo>"+array.getZlNo()+"</zlNo>") ;
			out.println("<year>"+year+"</year>") ;
			out.println("<month>"+month+"</month>") ;
			out.println("<day>"+day+"</day>") ;
			out.println("<hour>"+hour+"</hour>") ;
			out.println("<minute>"+minute+"</minute>") ;
			out.println("<second>"+second+"</second>") ;
			out.println("<millisecond>"+millisecond+"</millisecond>") ;
			String alertM=null ;
			if(array.getRe()==0){//未判断记录入报警表     
					if(array.getVoltage1()<10){
					     alertM = "电压1值过低："+array.getVoltage1()+"V。" ;
					}
					if(array.getVoltage1()>320){
					     alertM = "电压1值过高："+array.getVoltage1()+"V。" ;
					}
					if(array.getVoltage2()<10){
						if(alertM==null){
							alertM="";
						}
					     alertM =""+ alertM+"电压2值过低："+array.getVoltage2()+"V。" ;
					}
					if(array.getVoltage2()>320){
						if(alertM==null){
							alertM="";
						}
					     alertM =""+ alertM+"电压2值过高："+array.getVoltage2()+"V。" ;
					}
					if(array.getCurrent1()<1){
						if(alertM==null){
							alertM="";
						}
					     alertM=""+alertM+"电流1(CS010GT)值过低："+array.getCurrent1()+"A。" ;
					}
					if(array.getCurrent1()>12){
						if(alertM==null){
							alertM="";
						}
					     alertM=""+alertM+"电流1(CS010GT)的值过高："+array.getCurrent1()+"A。" ;
					}
					if(array.getCurrent2()<1){
						if(alertM==null){
							alertM="";
						}
					     alertM= ""+alertM+"电流2的值过低："+array.getCurrent2()+"A。" ;
					}
					if(array.getCurrent2()>12){
						if(alertM==null){
							alertM="";
						}
					     alertM= ""+alertM+"电流2的值过高："+array.getCurrent2()+"A。";
					}
			//System.out.println(alertM);
					if(alertM!=null){
						String smonth = "" ;
						String sday = "" ;
						String shour = "" ;
						String sminute = "" ;
						String ssecond = "" ;
						String smillisecond = "" ;
						if(month < 10)
					         smonth = "0"+month ;
					     else
					    	 smonth = ""+month ;
					     if(day <10)
					    	 sday = "0"+day ;
					     else
					    	 sday = ""+day ;
					     if(hour<10)
					    	 shour = "0"+hour ;
					     else
					    	 shour = ""+hour ;
					     if(minute < 10)
					    	 sminute = "0"+minute ;
					     else
					    	 sminute = ""+minute ;
					     if(second < 10)
					    	 ssecond = "0"+second ;
					     else
					    	 ssecond = ""+second ;
					     if(millisecond <10)
					    	 smillisecond = "00"+millisecond ;
					     else if(millisecond <100)
					    	 smillisecond = "0"+millisecond ;
					     else 
					    	 smillisecond = ""+millisecond ;
					     
					String datime = ""+year+smonth+sday+shour+sminute+ssecond+smillisecond ;
					   
					int zlNo = array.getZlNo();
					//System.out.println("准备插入报警信息："+alertM);
					das.insertIntoAlertmsg(alertM, datime, zlNo) ;		
					das.setCurrentdata(id);//
		
					}
			  }
		}
/**查找最新的故障信息*/
		AlertData alertData=new AlertData();
		alertData= das.getNewAlert() ;
		if(alertData!=null&&alertData.getConfirm()==0){
			out.println("<alert>"+alertData.getMessage()+"</alert>") ;
			//System.out.println("读取报警信息："+alertData.getMessage() ) ;
			out.println("<zl>"+alertData.getZlNo()+"</zl>") ;
			out.println("<alertime>"+alertData.getAddtime()+"</alertime>") ;
		}
		else{
			out.println("<alert>无报警信息</alert>") ;
			//System.out.println("报警信息：" ) ;
			out.println("<zl>0</zl>") ;
			out.println("<alertime>0</alertime>");
		}
		out.println("</response>") ;
		out.close() ;
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request,response);
	}

}
