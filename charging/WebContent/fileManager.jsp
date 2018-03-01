<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件管理</title>
</head>
<body>
<jsp:include page="/top.jsp" flush="true"/>
<form action="${pageContext.request.contextPath }/FileManagerServlet?method=upload" method="post" enctype="multipart/form-data">
<table align="center">
<tr>
<th>读取文件：</th>
<td><input type="file" name="file1"></td><td><input type="submit" value="上传文件"></td>
</tr>
<tr></tr>
</table>
</form>
<hr>
<br>
<form action="${pageContext.request.contextPath }/FileManagerServlet?method=downlist" method="post">
<table align="center">
<tr>
<th>所有文件：</th>
<td><input type="submit" value="文件下载"></td>
</tr>
<tr></tr>
</table>
</form>
</body>
</html>