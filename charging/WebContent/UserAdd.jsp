<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>用户信息新增</title>
</head>
<body>
<jsp:include page="./top.jsp" flush="true"/>
<form action="SaveOrUpdateUser" method="post" >
<table  width="100%"  border="1"  cellspacing="0" cellpadding="0" >
 <tr valign="top">
 <td>
 用户名：
 </td>
 <td><input type="hidden" name="id" value="0"><input type="text" name="username"></td>
 </tr>
  <tr valign="top">
 <td>
密码：
 </td>
 <td><input type="password" name="password"></td>
 </tr>
 <tr valign="top">
 <td>
 真实姓名：
 </td>
 <td><input type="text" name="realname"></td>
 </tr>
  <tr valign="top">
 <td>
职务：
 </td>
 <td><input type="text" name="unit"></td>
 </tr>
   <tr valign="top">
 <td>
电话号码：
 </td>
 <td><input type="text" name="phone"></td>
 </tr>
 <tr>
 <td>邮箱：</td>
 <td><input type="text" id="email" name="email" ></td>
 </tr>
 <tr>
 <td>权限：</td>
 <td><select name="priority">
 	<option value="1">普通用户</option>
 	<option value="0">管理员</option>
 </select></td>
 </tr>
 
 <tr>
 <td><input type="submit" value="确定"></td>
 <td><input type="button" value="取消"></td>
 </tr>
</table>

</form>
</body>
</html>
