<%@page import="com.sun.org.apache.bcel.internal.generic.LNEG"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.shu.entity.Device"%>
<%@page import="cn.edu.shu.service.impl.DeviceServiceImpl"%>
<%@page import="cn.edu.shu.service.IDeviceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
html,body{
	height: 100%;
}
	#allmap {width: 100%;height: 90%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=	SdgXo8Xp06aa6KmrYkKBLKEpGOOjcUs4"></script>
<title>查看所有充电桩信息</title>
</head>
<body>
<jsp:include page="./top.jsp" flush="true"/>
<div style="text-align: center;width: 100%;height: 5%">
	<button onclick="window.history.back()">返回</button>
</div>
<%
	//获取所有的充电桩
	IDeviceService deviceService =new  DeviceServiceImpl();
	List<Device> list = deviceService.getDeviceAll();

%>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
//编写自定义函数,创建标注
function addMarker(point,code,name,address,person){
  var marker = new BMap.Marker(point);
  map.addOverlay(marker);
  var opts = {
		  width : 300,     // 信息窗口宽度
		  height: 150,     // 信息窗口高度
		  title :  "<strong style=\"font-size:16px;font-weight:bold\">充电桩信息</strong>" , // 信息窗口标题
		  enableMessage:true,//设置允许信息窗发送短息
		  message:""
		}
  var info = "编号："+code+"<br/>"+"名称："+name+"<br>"+"地址："+address+"<br>"+"负责人："+person;
		var infoWindow = new BMap.InfoWindow(info, opts);  // 创建信息窗口对象 
	marker.addEventListener("click", function(){          
	map.openInfoWindow(infoWindow,point); //开启信息窗口
}); 
}
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	
	map.centerAndZoom("上海", 15);  // 初始化地图,设置中心点坐标和地图级别
	  
	map.setCurrentCity("上海");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	<%
		for(Device d:list){

	%>
	var point = new BMap.Point(<%=d.getLng()%>,<%=d.getLat()%>);
	addMarker(point,<%=d.getCode()%>,<%=d.getName()%>,<%=d.getAddress()%>,<%=d.getPerson()%>);
	 
	<%}%>
</script>