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
<body  onload =sendRequest() >
<jsp:include page="/top.jsp" flush="true"/>
<div class="body">

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
 //电流
var dom = document.getElementById("container");
 //电压
var dom1 = document.getElementById("container1");
 
//图标的绘制和更新
//eachdom表示对于的div，type类型 0表示输入 1表示输出
function showChart(eachdom,type){
	var textString = "输入状态";
	var legendData = ['输入电压', '输入电流'];
	var typeName = "输入电流";
	var typeName1="输入电压";
	if(type=="1"){
		textString = "输出状态";
		legendData = ['输出电压', '输出电流'];
		name = "输出电流";
		name1 = "输出电压";
	}
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
	            name: '电压',
	            max: 30,
	            min: 0,
	            boundaryGap: [0.2, 0.2]
	        },
	        {
	            type: 'value',
	            scale: true,
	            name: '电流',
	            max: 1200,
	            min: 0,
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
	                var len = 10;
	                while (len--) {
	                    res.push(Math.round(Math.random() * 1000));
	                }
	                return res;
	            })()
	        },
	        {
	            name:typeName,
	            type:'line',
	            data:(function (){
	                var res = [];
	                var len = 0;
	                while (len < 10) {
	                    res.push((Math.random()*10 + 5).toFixed(1) - 0);
	                    len++;
	                }
	                return res;
	            })()
	        }
	    ]
	};

	app.count = 11;
	setInterval(function (){
	    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');

	    var data0 = option.series[0].data;
	    var data1 = option.series[1].data;
	    data0.shift();
	    data0.push(Math.round(Math.random() * 1000));
	    data1.shift();
	    data1.push((Math.random() * 10 + 5).toFixed(1) - 0);

	    option.xAxis[0].data.shift();
	    option.xAxis[0].data.push(axisData);
	    option.xAxis[1].data.shift();
	    option.xAxis[1].data.push(app.count++);

	    myChart.setOption(option);
	}, 2100);
	;
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
}

		//绘制dom1
		showChart(dom,0);
		showChart(dom1,1);
       </script>
</body>
</html>