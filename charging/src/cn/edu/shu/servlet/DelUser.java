package cn.edu.shu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.entity.Device;
import cn.edu.shu.service.IDeviceService;
import cn.edu.shu.service.IUserService;
import cn.edu.shu.service.impl.DeviceServiceImpl;
import cn.edu.shu.service.impl.UserServiceImpl;

/**
 * 
 * @author 
 * 删除用户对应的servlet
 */
public class DelUser extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		//获取数据
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		IUserService userService = new UserServiceImpl();
		
		userService.deleteUser(id);
		resp.sendRedirect("./UserList.jsp");
	}
	
	

}
