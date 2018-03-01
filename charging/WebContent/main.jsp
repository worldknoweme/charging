<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>监控</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<script type="text/javascript" src="js/main.js">	
</script>

<body  onload =sendRequest() >
<jsp:include page="/top.jsp" flush="true"/>
<div class="body">

			<div  class=" content clearfix" id="chart">  
			
			  	 <div class="voltage">	
			  	 	<jsp:plugin code="cn.edu.shu.applet.RealTimePanelApplet.class"  codebase="./plugin"  type="applet"  height="100%" width="100%"   >	
			 		<jsp:fallback>
  						<p>Unable to load applet</p>
					</jsp:fallback>
			 	</jsp:plugin>	
			 	</div> 
		
			 	<div class="current">	
			 		<jsp:plugin code="cn.edu.shu.applet.RealTimeCurrApplet.class" codebase="./plugin" type="applet"  height="100%" width="100%"> 		 
		 		 	<jsp:fallback>
  						<p>Unable to load applet</p>
					</jsp:fallback>
					</jsp:plugin>		
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
 
</body>
</html>