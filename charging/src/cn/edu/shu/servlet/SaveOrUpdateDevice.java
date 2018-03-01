package cn.edu.shu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.shu.entity.Device;
import cn.edu.shu.service.IDeviceService;
import cn.edu.shu.service.impl.DeviceServiceImpl;

/**
 * 
 * @author 
 * 新增或者更新充电桩对应的servlet
 */
public class SaveOrUpdateDevice extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		//获取数据
		String code = req.getParameter("code");
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String person = req.getParameter("person");
		String lat = req.getParameter("lat");
		String lng = req.getParameter("lng");
		String des = req.getParameter("des");
		IDeviceService deviceService = new DeviceServiceImpl();
		Device device = new Device(id, code, name, address, person, lat, lng, des);
		deviceService.saveOrUpdateDevice(device);
		resp.sendRedirect("./DeviceList.jsp");
	}
	
	

}
