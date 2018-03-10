<%@page import="cn.edu.shu.service.impl.UserServiceImpl"%>
<%@page import="cn.edu.shu.service.IUserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="cn.edu.shu.entity.*,java.util.* ,cn.edu.shu.service.impl.DataAcqService" %>
<% 
	int currentPage = 1;
	int pageSize = 10;
	String username = "";
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	username = request.getParameter("username");
	
	//首先获取所有用户数据
	IUserService userService = new UserServiceImpl();
	List<User> datas = userService.getUserList(username, currentPage, pageSize);
	//获取数据总数
	int countData = userService.count(username);
	int totalSize = countData%pageSize==0?countData/pageSize:countData/pageSize+1;
	
%>
<html>
<head>
<title>用户信息</title>
<script type="text/javascript">
 function search(){
	 //输入的用户名，用以查询
	 //获取页面元素的时候，一定要记得使用双引号
	 var usernamestring = document.getElementById("username").value;
	 window.location.href='./UserList.jsp?username='+usernamestring;
 }
</script>
</head>

<body>
<jsp:include page="./top.jsp" flush="true"/>

<table  width="100%"  border="1"  cellspacing="0" cellpadding="0" >
  <tr valign="top">
    <td>
      <table width="100%" cellspacing="0" cellpadding="0" height="30">
        <tr>
          <td height="20"><font size="4"><b>查询数据</b></font>(格式:用户名)</td>
          <td  align="center" ><font size="4"><b>用户名：</b></font></td>
          <td  align="left" ><input type="text" name="username" id="username" size="6" ></td>
          <td width="10%" height="20"><input type="button" onclick="search()" value=" 查询 " ></td>
           <%
				   		if(session.getAttribute("pri").equals("0")){
				   			
				   		
				   %>
          <td width="10%" height="20"><input type="button" value="添加" onclick="window.location.href='./UserAdd.jsp';"></td>       	
        <%} %>
        </tr>
      </table>
    </td>
  </tr>
  <tr valign="top"  id="totop">
    <td>
	  <table border=“1”  width=100%   cellspacing="0" cellpadding="0" >
	  
	  <tr bgColor=#7D7DFF height=30 align=center>
	  <td width="4%">用户名</td>
	  <td width="17%">真实姓名</td>
	  <td width="17%">职务</td>
	  <td width="17%">电话号码</td>
	  <td width="17%">邮箱</td>
	  <td width="17%">权限</td>
	   <%
			if(session.getAttribute("pri").equals("0")){
				   %>
	  <td width="17%">操作</td>
	  <%} %>
	  </tr>
	 
	<%
			if(datas != null){  
				for(User d:datas){//遍历
				%>
				 <tr bgColor=#C7C7E2 align=center>
				 <td width="4%"><%=d.getUsername()%></td>
				 <td width="17%"><%=d.getRealname() %></td>
				 <td width="17%"><%=d.getUnit()%></td>
				 <td width="17%"><%=d.getPhone() %></td>
				  <td width="17%"><%=d.getEmail()%></td>
				   <td width="17%"><%=d.getPriority().equals("0")?"管理员":"普通用户" %></td>
				   <%
				   		if(session.getAttribute("pri").equals("0")){
				   			
				   		
				   %>
				 <td width="17%">
				 <a href="UserEdit.jsp?id=<%=d.getId()%>">编辑</a>
				 <a href="DelUserServlet?id=<%=d.getId()%>">删除</a>
				 </td>
				 <%} %>
				 </tr>
				<%}	
			}
	%>
<tr>
  			<td colspan="8" align="center">
  				当前<%=currentPage %>页     &nbsp;&nbsp;		
  				<a href="${pageContext.request.contextPath }/UserList.jsp?currentPage=1">首页</a>
  				<a href="${pageContext.request.contextPath }/UserList.jsp?currentPage=<%=currentPage-1%>">上一页 </a>
  				<a href="${pageContext.request.contextPath }/UserList.jsp?currentPage=<%=currentPage+1%>">下一页 </a>
  				<a href="${pageContext.request.contextPath }/UserList.jsp?currentPage=<%=totalSize%>">末页</a>   &nbsp;&nbsp;	
  				
  			</td>
  		</tr>
	</table>
  </td>
</tr>
</table>

</body>
</html>