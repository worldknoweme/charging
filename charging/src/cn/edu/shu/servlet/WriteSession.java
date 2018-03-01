package cn.edu.shu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.shu.entity.Data;
import cn.edu.shu.utils.PageBean;

public class WriteSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String uri="";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String pageFlag=request.getParameter("pageFlag");
		String startD=request.getParameter("startD");
		String endD=request.getParameter("endD");
		String pageCount=request.getParameter("pageCount");
		String id=request.getParameter("id");
//		String startAD=request.getParameter("startAD");
//		String endAD=request.getParameter("endAD");
		/**
		 * 需要重新获取session中的id用户名  session中的属性不知为何不存在了？？？？（）
		 */
		HttpSession session=request.getSession();
		if(session.getAttribute("id")==null)
		{session.setAttribute("id", id);}
		session.setAttribute("startD", startD);
		session.setAttribute("endD", endD);
		session.setAttribute("pageCount", pageCount);
		//System.out.println( session.getAttribute("id"));
//		System.out.println( session.getAttribute("pageCount"));
		try {
			if("HistoryDataList".equals(pageFlag)){
				uri=request.getContextPath()+"/HistoryDataList";
//				request.getRequestDispatcher(uri).forward(request, response);
			}else if("AlertMsgList".equals(pageFlag)){
				uri=request.getContextPath()+"/AlertMsgList";
//				request.getRequestDispatcher(uri).forward(request, response);
			}else{
//				response.sendRedirect(request.getContextPath()+"/historyChart.jsp");
				uri="/historyChart.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			uri="/WEB-INF/error.jsp";
//			request.getRequestDispatcher(uri).forward(request, response);
		}
		

		//		System.out.println( session.getAttribute("id"));
//		request.getRequestDispatcher(request.getContextPath()+"/HistoryDataList").forward(request, response);
		request.getRequestDispatcher(uri).forward(request, response);
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	doGet( request,response);
	}

}
