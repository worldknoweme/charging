<%@page import="cn.edu.shu.entity.Data"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.shu.service.impl.HistoryDataServiceImpl"%>
<%@page import="cn.edu.shu.service.IHistoryDataService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>历史曲线</title>
<link rel="stylesheet" type="text/css" href="css/historyChart.css">

</head>

<body>
<jsp:include page="./top.jsp" flush="true"/>
<%
//获取查询的设备id
	String deviceid = request.getParameter("deviceid");
	IHistoryDataService historyDataService = new HistoryDataServiceImpl();
	//查询该设备对应的所有历史数据
	List<Data> list = historyDataService.getHistroyDataByDeviceID(deviceid);
	//展示时间
	String time="";
	//输入电流
	String inVol = "";
	//输入电压
	String inCurrent = "";
	//输出电流
	String outVol = "";
	//输出电压
	String outCurrent = "";
	for(Data data:list){
		//获取历史数据信息并处理时间
		String thisTime = data.getAddtime();
		thisTime = thisTime.substring(0, 4)+"-"+thisTime.substring(4,6)+"-"+thisTime.substring(6,8);
		time+="'"+thisTime+"',";
		inVol+=data.getVoltage1()+",";
		inCurrent+=data.getCurrent1()+",";
		outVol+=data.getVoltage2()+",";
		outCurrent+=data.getCurrent2()+",";
	}
%>
<!-- <embed src="Lemon tree.mp3" hidden="true"> -->
 <div id="container" style="height: 100%"></div>
<!--导入echart所依赖js  -->
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
<!-- 完成echart对应js的导入 -->
<script type="text/javascript">

	function timer(){
		
	}

	function myrefresh(){
		window.location.reload();
	}
	
	var dom = document.getElementById("container");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	option = {
	    title: {
	        text: '历史曲线图'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            label: {
	                backgroundColor: '#6a7985'
	            }
	        }
	    },
	    legend: {
	        data:['输入电流','输入电压','输出电流','输出电压']
	    },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : [<%=time%>]
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'输入电流',
	            type:'line',
	            stack: '安培',
	            areaStyle: {normal: {}},
	            data:[<%=inVol%>]
	        },
	        {
	            name:'输入电压',
	            type:'line',
	            stack: '伏特',
	            areaStyle: {normal: {}},
	            data:[<%=inCurrent%>]
	        },
	        {
	            name:'输出电流',
	            type:'line',
	            stack: '安培',
	            areaStyle: {normal: {}},
	            data:[<%=outVol%>]
	        },
	        {
	            name:'输出电压',
	            type:'line',
	            stack: '伏特',
	            areaStyle: {normal: {}},
	            data:[<%=outCurrent%>]
	        }
	    ]
	};
	;
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
</script> 
</body>

</html>