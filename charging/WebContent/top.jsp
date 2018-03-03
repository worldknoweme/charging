<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>top</title>
<link rel="stylesheet" type="text/css" href="css/top.css">
</head>
<script type="text/javascript">
function fun1(){
	//alert("test!");
	var falg;
	if(confirm("确定退出吗?")){
		//alert("test!");
		flag=true;
	}		
	else{
		flag=false;
	}
	return flag;
		
}
</script>
<body>
		<div class="nav">
		        <div class="welcome">
				 <%if(session.getAttribute("id")!=null){ %><a href="welcomme.jsp" title="返回欢迎页面" class="hello">Hello，${sessionScope.id }!</a> <%} %>
				 <a href="./Logout" onclick="return fun1()" class="logout" title=返回登陆>注 销</a>	
				 <a class="main" href="./main.jsp" title=首页>首 页</a>		
				 <a class="devicedata" href="./DeviceList.jsp" title="充电桩维护">充电桩维护</a>			
				<a class="historydata" href="./HistoryDataList" title="历史数据">历史数据</a>
				<a class="historychart"href="./DeviceSelect.jsp" title=历史曲线>历史曲线</a>
				<a class="alertmessage" href="./AlertMsgList"  title=报警信息>报警信息</a>
				<a class="filemanager" href="./fileManager.jsp" title=文件的上传与下载等>文件管理</a>
				<a class="filemanager" href="./UserList.jsp" title="用户管理">用户管理</a>
				
				 </div>	
		
		</div>
</body>
</html>