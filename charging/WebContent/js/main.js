var XMLHttpReq;
	 var i = 0 ;
    function createXMLHttpRequest() {
    	   	
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					
				}
			}
		}	
		
	}
	//发送请求函数
	function sendRequest() {  
		createXMLHttpRequest();
		
        var url = "./RealTime";
       
		XMLHttpReq.open("GET", url, true);  //创建一个新的http请求
		XMLHttpReq.onreadystatechange = processResponse;//指定响应函数，服务器返回信息后该函数进行响应
		XMLHttpReq.send(null);  // 发送请求  
	}
	// 处理返回信息函数
    function processResponse() {
    	// window.alert(XMLHttpReq.readyState);
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    	
       			if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
       				 //	window.alert(XMLHttpReq.status);
       				
      		 	 if(XMLHttpReq.responseXML.getElementsByTagName("zlNo")&&XMLHttpReq.responseXML.getElementsByTagName("zl")&&XMLHttpReq.responseXML.getElementsByTagName("alertime"))
       			{ 
      		 		/* window.alert("yichang"); */
       		 		DisplayHot();
       		 
          	 	 } else { 
            
               		 window.alert("您所请求的页面有异常");
           		 }  
           		/* if(XMLHttpReq.responseXML.getElementsByTagName("zlNo")&&XMLHttpReq.responseXML.getElementsByTagName("zl")&&XMLHttpReq.responseXML.getElementsByTagName("alertime"))
           		window.alert( XMLHttpReq.responseXML.getElementsByTagName("alertime")[0].firstChild.nodeValue); */
       		    setTimeout("sendRequest()", 1000); //
       		 	
       			 }
    	}
    	//else  window.alert(XMLHttpReq.readyState);
}
	
    function DisplayHot() {
    
	    var vol = XMLHttpReq.responseXML.getElementsByTagName("vol")[0].firstChild.nodeValue;	
	    var vol2 = XMLHttpReq.responseXML.getElementsByTagName("vol2")[0].firstChild.nodeValue;	 
	    var cur1 = XMLHttpReq.responseXML.getElementsByTagName("cur1")[0].firstChild.nodeValue;
	    var cur2 = XMLHttpReq.responseXML.getElementsByTagName("cur2")[0].firstChild.nodeValue;;
	    var zlNo = XMLHttpReq.responseXML.getElementsByTagName("zlNo")[0].firstChild.nodeValue;
	    
	    var year = XMLHttpReq.responseXML.getElementsByTagName("year")[0].firstChild.nodeValue;
	    var month = XMLHttpReq.responseXML.getElementsByTagName("month")[0].firstChild.nodeValue;
	    var day = XMLHttpReq.responseXML.getElementsByTagName("day")[0].firstChild.nodeValue;
	    var hour = XMLHttpReq.responseXML.getElementsByTagName("hour")[0].firstChild.nodeValue;
	    var minute = XMLHttpReq.responseXML.getElementsByTagName("minute")[0].firstChild.nodeValue;
	    var second = XMLHttpReq.responseXML.getElementsByTagName("second")[0].firstChild.nodeValue;
	    var millisecond = XMLHttpReq.responseXML.getElementsByTagName("millisecond")[0].firstChild.nodeValue;
	    
	     var alert = XMLHttpReq.responseXML.getElementsByTagName("alert")[0].firstChild.nodeValue;
	     var alertime = XMLHttpReq.responseXML.getElementsByTagName("alertime")[0].firstChild.nodeValue;
	     var zl = XMLHttpReq.responseXML.getElementsByTagName("zl")[0].firstChild.nodeValue;
	     /* var state = XMLHttpReq.responseXML.getElementsByTagName("state")[0].firstChild.nodeValue; */
	   	/* window.alert(zl);  */
	 	 document.getElementById("realvol1").innerHTML = vol;	
		document.getElementById("realvol2").innerHTML = vol2 ;	
		document.getElementById("realcur1").innerHTML = cur1 ;	
		document.getElementById("realcur2").innerHTML = cur2;	 
		/* window.alert("1:"+vol);
		window.alert("2:"+vol2); 
		window.alert("3:"+cur1); 
		window.alert("4:"+cur2); 
		window.alert(zl); */
	/* 	document.getElementById("state").innerHTML = state ;	 */
	     if(zl!=0){
	    	
	     document.getElementById("message").innerHTML = alertime.substring(0,4)+"/"+alertime.substring(4,6)+"/"+alertime.substring(6,8)+"/  "+alertime.substring(8,10)+":"
	     +alertime.substring(10,12)+":"+alertime.substring(12,14)+":"+alertime.substring(14,17)+"   <"+zl+">支路报警——"+alert; 
	   /*   document.getElementById("message").innerHTML ="报警——断路故障"; */
	     document.getElementById("message").style.background = "white" ;
	     document.getElementById("message").style.color = "red" ;
	     document.getElementById("button").style.color = "red" ;
	  
	     document.getElementById("music").innerHTML = "<embed src='Lemon tree.mp3' hidden='true'>" ;	  
	   /*  document.getElementById("alertmessage").appendChild('<embed src="Lemon tree.mp3" hidden="true">'); */  
	     /* setInterval("blink()",100) ; */
	      blink(); 
	     //window.alert("zl!=0");
	     }
	     else{
	    		
/* 	    	 document.getElementById("message").style.background = "#ffffff" ; */
	    	/* document.getElementById("message").innerHTML=alert; */ 
	    	 document.getElementById("message").innerHTML="漏电故障";
	    	 document.getElementById("message").style.color="red";
	    	 /* window.alert("zl=0"); */
	     }
		
		 
	}
     var colorArray=["blue","red"];
    var j=0;

    function blink(){ 	
    	 document.getElementById("button").style.color  = colorArray[j] ;
    	   document.getElementById("button").style.background = colorArray[1-j] ;
    	j++;
    	if(j>1)j=0;
    }  
