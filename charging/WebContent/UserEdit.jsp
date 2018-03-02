<%@page import="cn.edu.shu.entity.User"%>
<%@page import="cn.edu.shu.service.impl.UserServiceImpl"%>
<%@page import="cn.edu.shu.service.IUserService"%>
<%@page import="cn.edu.shu.entity.Device"%>
<%@page import="cn.edu.shu.service.impl.DeviceServiceImpl"%>
<%@page import="cn.edu.shu.service.IDeviceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息编辑</title>
</head>
<body>
<jsp:include page="./top.jsp" flush="true"/>
<%
	//根据id获取用户信息
	IUserService userService =new  UserServiceImpl();
	int id = Integer.parseInt(request.getParameter("id"));
	User user = userService.getUserByID(id);
	
%>
<script type="text/javascript">
var pro = <%=user.getPriority()%>
document.getElementByName('priority').value=pro;
</script>
<form action="SaveOrUpdateUser" method="post" >
<table  width="100%"  border="1"  cellspacing="0" cellpadding="0" >
<tr>
 <td>
 用户名：
 </td>
 <td><input type="hidden" name="id" value="<%=user.getId()%>"><input type="text" name="username" value="<%=user.getUsername()%>"></td>
 </tr>
  <tr valign="top">
 <td>
密码：
 </td>
 <td><input type="password" name="password" value="<%= user.getPassword()%>"></td>
 </tr>
 <tr valign="top">
 <td>
 真实姓名：
 </td>
 <td><input type="text" name="realname" value="<%=user.getRealname()%>"></td>
 </tr>
  <tr valign="top">
 <td>
职务：
 </td>
 <td><input type="text" name="unit" value="<%=user.getUnit()%>"></td>
 </tr>
   <tr valign="top">
 <td>
电话号码：
 </td>
 <td><input type="text" name="phone" value="<%=user.getPhone()%>"></td>
 </tr>
 <tr>
 <td>邮箱：</td>
 <td><input type="text" id="email" name="email"  value="<%=user.getEmail()%>"></td>
 </tr>
 <tr>
 <td>权限：</td>
 <td><select name="priority">
 	<option value="1" >普通用户</option>
 	<option value="0">管理员</option>
 </select></td>
 </tr>
 <tr>
 <td><input type="submit" value="确定"></td>
 <td><input type="button" onclick="window.history.back()" value="取消"></td>
 </tr>
</table>

</form>
</body>
</html>
