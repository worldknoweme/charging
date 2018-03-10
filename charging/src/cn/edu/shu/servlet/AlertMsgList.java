package cn.edu.shu.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.shu.entity.AlertData;
import cn.edu.shu.entity.Data;
import cn.edu.shu.service.impl.DataAcqService;
import cn.edu.shu.utils.PageBean;

public class AlertMsgList extends HttpServlet {

	private DataAcqService das=new DataAcqService();
	private String uri="";
		private static final long serialVersionUID = 1L;
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			try {
				//set current page
				String currentPage=request.getParameter("currentPage");
				//set pages shown on every page
//				String pageCount=request.getParameter("pageCount");
				//System.out.println("page:"+pageCount);
				//写session
				HttpSession session=request.getSession();
//				String id=(String)session.getAttribute("id");
//				session.setAttribute("id", id);
				
				String startD=(String) session.getAttribute("startD");
				String endD=(String) session.getAttribute("endD");
				String pageCount=(String) session.getAttribute("pageCount");//set pages shown on every page
//				System.out.println("1.startD:"+startD);
//				System.out.println("1.endD:"+endD);
				//判空
				if(currentPage==null||"".equals(currentPage)){
					currentPage="1";
				}
				int curPage=Integer.parseInt(currentPage);
				PageBean <AlertData> pageBean=new PageBean<AlertData>();
				pageBean.setCurrentPage(curPage);
				//获取当前时间即可
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				if(startD==null||"".equals(startD)){
					startD="20180301000001";
					session.setAttribute("startD", startD);
				}
				if(endD==null||"".equals(endD)){
					endD=formatter.format(new Date());
					session.setAttribute("endD", endD);		
				}
				if(pageCount==null||"".equals(pageCount)){
					pageCount="20";  //每页默认显示20行
					session.setAttribute("pageCount", pageCount);
				}
				pageBean.setPageCount(Integer.parseInt(pageCount));
				pageBean.setEndD(endD);
				pageBean.setStartD(startD);
				das.getHistroyAlert(pageBean);
				request.setAttribute("pageBean", pageBean);
				uri="/WEB-INF/alertMsgList.jsp";
			} catch (NumberFormatException e) {
				e.printStackTrace();
				uri="/WEB-INF/error.jsp";
			}
			request.getRequestDispatcher(uri).forward(request, response);
		}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 doGet(request,response);
	}

}
