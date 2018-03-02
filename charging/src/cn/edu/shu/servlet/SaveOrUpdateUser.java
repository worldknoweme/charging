package cn.edu.shu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.entity.Device;
import cn.edu.shu.entity.User;
import cn.edu.shu.service.IDeviceService;
import cn.edu.shu.service.IUserService;
import cn.edu.shu.service.impl.DeviceServiceImpl;
import cn.edu.shu.service.impl.UserServiceImpl;

/**
 * 
 * @author 
 * 新增或者更新用户信息对应的servlet
 */
public class SaveOrUpdateUser extends HttpServlet{

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
		String username = req.getParameter("username");
		int id = Integer.parseInt(req.getParameter("id"));
		String password = req.getParameter("password");
		String realname = req.getParameter("realname");
		String unit = req.getParameter("unit");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String priority = req.getParameter("priority");
		IUserService userService = new UserServiceImpl();
		User user = new User(id, username, password, realname, unit, phone, email, priority);
		userService.saveOrUpdateUser(user);
		resp.sendRedirect("./UserList.jsp");
	}
	
	

}
