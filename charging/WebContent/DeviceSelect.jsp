<%@page import="cn.edu.shu.entity.Device"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.shu.service.impl.DeviceServiceImpl"%>
<%@page import="cn.edu.shu.service.IDeviceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请指定一个查看的充电桩</title>
<%
	//查询所有的充电桩
	IDeviceService deviceService =new  DeviceServiceImpl();
	List<Device> list = deviceService.getDeviceAll();
	
%>
</head>
<body>
<jsp:include page="./top.jsp" flush="true"/>
<form action="historyChart.jsp" method="post" >
<table  width="100%"  border="1"  cellspacing="0" cellpadding="0" >
 <tr valign="top">
 <td>
请指定一个充电桩：
 </td>
 <td>
 	<select name="deviceid">
 		<%
 			for(Device device:list){
 		%>
 			<option value="<%=device.getId()%>"><%=device.getName() %></option>
 		<%
 			}
 		%>
 	</select>
 </td>
 </tr>
 
 <tr>
 <td><input type="submit" value="确定"></td>
 <td><input type="button" value="取消"></td>
 </tr>
</table>

</form>
</body>
</html>
