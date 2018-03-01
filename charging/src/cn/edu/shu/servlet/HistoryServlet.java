package cn.edu.shu.servlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.shu.entity.Data;
import cn.edu.shu.service.impl.DataAcqService;
import cn.edu.shu.utils.PageBean;
public class HistoryServlet extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataAcqService das=new DataAcqService();
	PageBean <Data> pageBean=new PageBean<Data>();
	private String uri="";
	public HistoryServlet(){
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/xml; charset=UTF-8") ;
		//response.setHeader("Cache-Control", "no-cache") ;
		try {
			String currentPage=request.getParameter("currentPage");
			String startD = request.getParameter("startD");
			String endD = request.getParameter("endD");
			String pageCount = request.getParameter("pageCount");
			HttpSession session=request.getSession();	
//			System.out.println("get currentPage:"+currentPage);
			//填数据
			pageBean.setCurrentPage(Integer.parseInt(currentPage));
			pageBean.setPageCount(Integer.parseInt(pageCount));
			pageBean.setEndD(endD);
			pageBean.setStartD(startD);
			das.getH5yDataPage(pageBean);
			//System.out.println(pageBean);//打印pagebean
			session.setAttribute("pageBean", pageBean);
			session.setAttribute("startD", startD);
			session.setAttribute("endD", endD);
			session.setAttribute("pageCount", pageCount);
			session.setAttribute("totalPage", pageBean.getTotalPage());
//			request.setAttribute("pageBean", pageBean);
			
			try{	
					OutputStream outstr = response.getOutputStream();
					ObjectOutputStream oos=new ObjectOutputStream(outstr);
					ArrayList<Data> datas=(ArrayList<Data>) pageBean.getPageData();
					if(datas!=null)
						{
							oos.writeObject(datas);
							oos.flush();
						}
				    oos.close();
				    outstr.close();
				    datas.clear();
			
				}catch(Exception e)
				   {
				    e.printStackTrace();
				   }
//			uri="/historyChart.jsp";
		} catch (NumberFormatException e) {
			uri="/WEB-INF/error.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
			e.printStackTrace();
		}
		//java.lang.IllegalStateException: Cannot forward after response has been committed
//				request.getRequestDispatcher(uri).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}

