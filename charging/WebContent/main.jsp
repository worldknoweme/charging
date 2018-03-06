<%@page import="cn.edu.shu.dao.impl.DataAcquisitionDao"%>
<%@page import="cn.edu.shu.dao.IDataAcquisitionDao"%>
<%@page import="cn.edu.shu.entity.Device"%>
<%@page import="java.util.List"%>
<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>监控</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<script type="text/javascript" src="js/main.js">	
</script>
<!-- echars所需js和css -->
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
<%
	//查询出所有的设备
	IDataAcquisitionDao dataAcquisitionDao = new DataAcquisitionDao<Device>();
	List<Device> deviceList = dataAcquisitionDao.getDeviceAll();
	//获取最后一个设备即为最新添加的那一个设备
	Device currentDevice = deviceList.get(deviceList.size()-1);
	System.out.println(currentDevice.getId());
%>
<body  onload ="sendRequest(<%=currentDevice.getId() %>) ">
<jsp:include page="/top.jsp" flush="true"/>
<div class="body">
			<div style="text-align: center; color: red" >
				默认查询最新添加的设备实时情况，如需查看其它设备，请选择并查询
			</div>
			<div style="text-align: center">
				设备名称：
				<select id="devicelist">
					<%
						for(Device d:deviceList){
							
					%>
					<option value="<%=d.getId()%>"><%=d.getName() %></option>
					<%} %>
					
					
				</select>
				<button onclick="search()">查询</button>
			</div>
			<div  class=" content clearfix" id="chart">  
				
			  	 <div class="voltage" id="container">	
			  	 
<%-- 			  	 	<jsp:plugin code="cn.edu.shu.applet.RealTimePanelApplet.class"  codebase="./plugin"  type="applet"  height="100%" width="100%"   >	 --%>
<%-- 			 		<jsp:fallback> --%>
<!--   						<p>Unable to load applet</p> -->
<%-- 					</jsp:fallback> --%>
<%-- 			 	</jsp:plugin>	 --%>
			 	</div> 
		
			 	<div class="current" id="container1">	
<%-- 			 		<jsp:plugin code="cn.edu.shu.applet.RealTimeCurrApplet.class" codebase="./plugin" type="applet"  height="100%" width="100%"> 		  --%>
<%-- 		 		 	<jsp:fallback> --%>
<!--   						<p>Unable to load applet</p> -->
<%-- 					</jsp:fallback> --%>
<%-- 					</jsp:plugin>		 --%>
		 		 </div>
		 		
		 		<div class="data">
		 		    	 	
		 		<div id="datatop">当前状态</div>
		 		
			 		<div id=dataBody>
			 			 
					 			<div class="realvol1">输入电压：<div id="realvol1"></div>(V)</div>	
					 			<div class="realvol2">输出电压：<div id="realvol2"></div>(V)</div>
					 			<div class="realcur1">输入电流：<div id="realcur1"></div>(A)</div>
					 			<div class="realcur2">输出电流：<div id="realcur2"></div>(A)</div>
			 		</div>
		 		
		 	</div>
		 	
		 	<div id="alertmessage">
		 		
		 		<div id="alertTop">报警信息</div>
		 		<div id="message" ></div>
		 		<form action="./CONFIRM" method="post"><input type="submit" id="button"value="确认报警" onclick="javascript:confrim()" ></input></form>
		 		<div id="music"></div> 
		 	</div>
		 </div>	 	
 </div>
 <script type="text/javascript">
 //实时数据查询
 function search(){
	 //获取选择值
	 var deviceID = document.getElementById("devicelist").value;
	// alert(deviceID);
	//请求实时数据
	 sendRequest(deviceID);
 }
 //输入图表
var dom = document.getElementById("container");
 //输出图表
var dom1 = document.getElementById("container1");
 
//绘制输入图表
function showInChart(eachdom){
	var textString = "输入状态";
	var legendData = ['输入电压', '输入电流'];
	var typeName = "输入电流";
	var typeName1="输入电压";

	var myChart = echarts.init(eachdom);
	var app = {};
	option = null;
	option = {
	    title: {
	        text: textString,
	        subtext: ''
	    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            label: {
	                backgroundColor: '#283b56'
	            }
	        }
	    },
	    legend: {
	        data:legendData
	    },
	    toolbox: {
	        show: true,
	        feature: {
	            dataView: {readOnly: false},
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    dataZoom: {
	        show: false,
	        start: 0,
	        end: 100
	    },
	    xAxis: [
	        {
	            type: 'category',
	            boundaryGap: true,
	            data: (function (){
	                var now = new Date();
	                var res = [];
	                var len = 10;
	                while (len--) {
	                    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
	                    now = new Date(now - 2000);
	                }
	                return res;
	            })()
	        },
	        {
	            type: 'category',
	            boundaryGap: true,
	            data: (function (){
	                var res = [];
	                var len = 10;
	                while (len--) {
	                    res.push(len + 1);
	                }
	                return res;
	            })()
	        }
	    ],
	    yAxis: [
	        
	        {
	            type: 'value',
	            scale: true,
	            name: '电流',
	            max: 12,
	            min: 1,
	            boundaryGap: [0.2, 0.2]
	        },
	        {
	            type: 'value',
	            scale: true,
	            name: '电压',
	            max: 320,
	            min: 10,
	            boundaryGap: [0.2, 0.2]
	        }
	    ],
	    series: [
	        {
	            name:typeName1,
	            type:'bar',
	            xAxisIndex: 1,
	            yAxisIndex: 1,
	            data:(function (){
	                var res = [];
	                //获取电压
	                var dy = document.getElementById("realvol1").innerHTML;
	        
	                //var len = 10;
	                //while (len--) {
	                    res.push(dy);
	                //}
	                return res;
	            })()
	        },
	        {
	            name:typeName,
	            type:'line',
	            data:(function (){
	                var res = [];
	              // var len = 0;
	                //电流
	                
	        	    var dl =  document.getElementById("realcur1").innerHTML;

	                //while (len < 10) {
	                    res.push(dl);
	                   // len++;
	               // }
	                return res;
	            })()
	        }
	    ]
	};

	app.count = 8;
	setInterval(function (){
	    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
	    //获取电流
        var dl = document.getElementById("realcur1").innerHTML;
	    //获取电压
	   	var dy = document.getElementById("realvol1").innerHTML;	
	    var data0 = option.series[0].data;
	    var data1 = option.series[1].data;
	    if(option.series[0].data.length>8){
	    	 data0.shift();
	    }
	   if(dy!=""){
		   data0.push(dy);
	   }
	   
	    if(option.series[1].data.length>8){
	    	 data1.shift();
	    }
	   if(dl!=""){
		   data1.push(dl);
	   }
	   

	    option.xAxis[0].data.shift();
	    option.xAxis[0].data.push(axisData);
	    option.xAxis[1].data.shift();
	    option.xAxis[1].data.push(app.count++);

	    myChart.setOption(option);
	}, 2100);
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
}



//绘制输出图表
function showOutChart(eachdom){
	var textString = "输出状态";
	var legendData =  ['输出电压', '输出电流'];
	var typeName = "输出电流";
	var typeName1="输出电压";
	
	var myChart = echarts.init(eachdom);
	var app = {};
	option1 = null;
	option1 = {
	    title: {
	        text: textString,
	        subtext: ''
	    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            label: {
	                backgroundColor: '#283b56'
	            }
	        }
	    },
	    legend: {
	        data:legendData
	    },
	    toolbox: {
	        show: true,
	        feature: {
	            dataView: {readOnly: false},
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    dataZoom: {
	        show: false,
	        start: 0,
	        end: 100
	    },
	    xAxis: [
	        {
	            type: 'category',
	            boundaryGap: true,
	            data: (function (){
	                var now = new Date();
	               
	                var res = [];
	                var len = 10;
	                while (len--) {
	                    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
	                    now = new Date(now - 2000);
	                }
	                return res;
	            })()
	        },
	        {
	            type: 'category',
	            boundaryGap: true,
	            data: (function (){
	                var res = [];
	                var len = 10;
	                while (len--) {
	                    res.push(len + 1);
	                }
	                return res;
	            })()
	        }
	    ],
	    yAxis: [
	      
	        {
	            type: 'value',
	            scale: true,
	            name: '电流',
	            max: 12,
	            min: 1,
	            boundaryGap: [0.2, 0.2]
	        },
	        {
	            type: 'value',
	            scale: true,
	            name: '电压',
	            max: 320,
	            min: 10,
	            boundaryGap: [0.2, 0.2]
	        }
	    ],
	    series: [
	        {
	            name:typeName1,
	            type:'bar',
	            xAxisIndex: 1,
	            yAxisIndex: 1,
	            data:(function (){
	                var res = [];
	                //输出电压
	                 var dy = document.getElementById("realcur2").innerHTML;
	               // var len = 10;
	              //  while (len--) {
	                    res.push(dy);
	             //   }
	                return res;
	            })()
	        },
	        {
	            name:typeName,
	            type:'line',
	            data:(function (){
	                var res = [];
	                var dl = document.getElementById("realvol2").innerHTML;
	                //var len = 0;
	               // while (len < 10) {
	                    res.push(dl);
	                   // len++;
	              //  }
	                return res;
	            })()
	        }
	    ]
	};

	app.count =8;
	setInterval(function (){
	    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
	    //获取电压
	    var dy = document.getElementById("realvol2").innerHTML;
	    //获取电流
	    var dl = document.getElementById("realcur2").innerHTML;
	    var data0 = option1.series[0].data;
	    var data1 = option1.series[1].data;
	    if(option.series[0].data.length>8){
	    	 data0.shift();
	    }
	   if(dy!=""){
		   data0.push(dy);
	   }
	   
	    if(option.series[1].data.length>8){
	    	 data1.shift();
	    }
	   if(dl!=""){
		   data1.push(dl);
	   }

	    option1.xAxis[0].data.shift();
	    option1.xAxis[0].data.push(axisData);
	    option1.xAxis[1].data.shift();
	    option1.xAxis[1].data.push(app.count++);

	    myChart.setOption(option1);
	}, 2100);
	if (option1 && typeof option1 === "object") {
	    myChart.setOption(option1, true);
	}
}

		//绘制dom1
		showInChart(dom);
		showOutChart(dom1);
       </script>
</body>
</html>