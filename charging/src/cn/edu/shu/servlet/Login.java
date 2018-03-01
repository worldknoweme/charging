package cn.edu.shu.servlet;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.shu.service.impl.DataAcqService;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String id;
	String password;
	Map<String,String> map;
    public Login() {  	
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException {
		request.setCharacterEncoding("UTF-8");	
		//response.setContentType("text/xml; charset=UTF-8") ;
		id=request.getParameter("id");//用户名
		password=request.getParameter("password");//密码
		//System.out.println("id:"+id+"password:"+password);
		DataAcqService das=new DataAcqService();
		map=das.checkUser(id, password);
		//System.out.println("flag:"+map.get("flag")+",priority:"+map.get("priority"));
		
		if("true".equals(map.get("flag"))){
		HttpSession session = request.getSession();
		session.setAttribute("id",id);//用户名
		session.setAttribute("password", password);
		session.setAttribute("pri", map.get("priority"));//权限
		try {
			response.sendRedirect("./main.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		else if("false".equals(map.get("flag"))){
			//out.println("<script>");
			//out.println("alert('用户名或密码错误！');");
			//out.println("</script>");
			response.sendRedirect("./login.jsp?sign=false");
		}
	}

}
