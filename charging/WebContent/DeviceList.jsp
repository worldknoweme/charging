<%@page import="cn.edu.shu.service.impl.DeviceServiceImpl"%>
<%@page import="cn.edu.shu.service.IDeviceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="cn.edu.shu.entity.*,java.util.* ,cn.edu.shu.service.impl.DataAcqService" %>
<% 
	int currentPage = 1;
	int pageSize = 10;
	String code = "";
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	code = request.getParameter("code");
	
	//首先获取所有充电桩数据
	IDeviceService deviceService = new DeviceServiceImpl();
	List<Device> datas = deviceService.getDeviceList(code,currentPage,pageSize);
	//获取数据总数
	int countData = deviceService.count(code);
	int totalSize = countData%pageSize==0?countData/pageSize:countData/pageSize+1;
	
%>
<html>
<head>
<title>数据信息</title>
</head>

<body>
<jsp:include page="./top.jsp" flush="true"/>
<form action="DeviceList.jsp" >
<table  width="100%"  border="1"  cellspacing="0" cellpadding="0" >
  <tr valign="top">
    <td>
      <table width="100%" cellspacing="0" cellpadding="0" height="30">
        <tr>
          <td height="20"><font size="4"><b>查询历史数据</b></font>(格式:设备编号)</td>
          <td  align="center" ><font size="4"><b>设备编号：</b></font></td>
          <td  align="left" ><input type="text" name="code" id="code" size="6" ></td>
          <td width="10%" height="20"><input type="submit" value=" 查询 " ></td>
          <td width="10%" height="20"><input type="button" value="添加" onclick="window.location.href='./DeviceAdd.jsp';"></td>
       	   <td width="10%" height="20"><input type="button" value="地图查看" onclick="window.location.href='./DeviceAdd.jsp';"></td>
       	
        </tr>
      </table>
    </td>
  </tr>
  <tr valign="top"  id="totop">
    <td>
	  <table border=“1”  width=100%   cellspacing="0" cellpadding="0" >
	  
	  <tr bgColor=#7D7DFF height=30 align=center>
	  <td width="4%">设备编号</td>
	  <td width="17%">设备名称</td>
	  <td width="17%">设备地址</td>
	  <td width="17%">维护人</td>
	  <td width="17%">操作</td>
	  </tr>
	 
	<%
			if(datas != null){  
				for(Device d:datas){//遍历
				%>
				 <tr bgColor=#C7C7E2 align=center>
				 <td width="4%"><%=d.getCode()%></td>
				 <td width="17%"><%=d.getName() %></td>
				 <td width="17%"><%=d.getAddress()%></td>
				 <td width="17%"><%=d.getPerson() %></td>
				 <td width="17%"><a href="#">编辑</a><a href="#">删除</a></td>
				 </tr>
				<%}	
			}
	%>
<tr>
  			<td colspan="8" align="center">
  				当前<%=currentPage %>页     &nbsp;&nbsp;		
  				<a href="${pageContext.request.contextPath }/DeviceList.jsp?currentPage=1">首页</a>
  				<a href="${pageContext.request.contextPath }/DeviceList.jsp?currentPage=<%=currentPage-1%>">上一页 </a>
  				<a href="${pageContext.request.contextPath }/DeviceList.jsp?currentPage=<%=currentPage+1%>">下一页 </a>
  				<a href="${pageContext.request.contextPath }/DeviceList.jsp?currentPage=<%=totalSize%>">末页</a>   &nbsp;&nbsp;	
  				
  			</td>
  		</tr>
	</table>
  </td>
</tr>
</table>
</form>
</body>
</html>