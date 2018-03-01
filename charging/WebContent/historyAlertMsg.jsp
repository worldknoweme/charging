<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="cn.edu.shu.entity.AlertData,java.util.* ,cn.edu.shu.service.impl.*" %>
<%
ArrayList<AlertData> datas=null; 
String startD ="2016-01-01-00-00-00" ;
String endD = "2016-12-31-10-00-00" ;
String startDatime = "" ;
String endDatime = "" ;
String str_sDate = "" ;
String str_eDate = "" ; 

if(request.getParameter("startD")!=null&&request.getParameter("startD")!=""&&request.getParameter("endD")!=null&&request.getParameter("endD")!=""){
startD = request.getParameter("startD") ;
String[] sDate = startD.split("-") ;
endD = request.getParameter("endD") ;
String[] eDate = endD.split("-") ;
if(sDate.length == 6&&eDate.length== 6)
{	
for(int i=0;i<sDate.length;i++)
str_sDate += sDate[i] ;
str_sDate +="000";
startDatime =  sDate[0]+"/"+sDate[1]+"/"+sDate[2]+"/"+sDate[3]+":"+sDate[4]+":"+sDate[5];

for(int i=0;i<eDate.length;i++)
	str_eDate += eDate[i] ;
str_eDate +="000";
endDatime =  eDate[0]+"/"+eDate[1]+"/"+eDate[2]+"/"+eDate[3]+":"+eDate[4]+":"+eDate[5];
DataAcqService das=new DataAcqService();
 datas=das.getHistroyAlert(str_sDate, str_eDate);
}
}
%>
<html>
<head>
<title>报警信息</title>
</head>
<script type="text/javascript">
function submit2(str1,str2){
	var startD="";
	var endD="";
	if(str1==null||str1==""||str2==null||str2==""){
		alert("请输入查询时间");return false;
	}
	else{
		var starttime=str1.split("-");
		var endtime=str2.split("-");
		if(starttime.length== 6&&endtime.length== 6){
			for(var i=0;i<6;i++){
				return true;
			}
		}
		else{alert("输入时间格式不正确"); return false;}
	}
	
}
</script> 
<body>
<jsp:include page="./top.jsp" flush="true"/>
  <!-- <embed src="Lemon tree.mp3" hidden="true"> -->
<form action="historyAlertMsg.jsp">
<table  width="100%"  border="1" bordercolor=blue cellspacing="0" cellpadding="0" >
  <tr>
    <td>

      <table width="100%"  cellspacing="0" cellpadding="0">
        <tr>
          <td ><font color=black><b>查询历史报警信息</b></font>(格式:年-月-日-时-分-秒(2016-01-01-00-00-00))</td>
          <td  align="center"><font color=black>起始时间</font></td>
          <td  align="left"><input type="text" name="startD" id="startD" size=18 value=<%=startD%>></td>
          <td align="center"><font color=black>截止时间</font></td>
          <td  align="left"><input type="text" name="endD" id="endD" size=18 value=<%=endD %>></td>
          <td  ><input type="submit" value="查询" onclick='return submit2((document.getElementById("startD")).value,(document.getElementById("endD")).value)'></td> 
        </tr>
      </table>

    </td>
  </tr>
  <tr>
    <td>
	  <table border=1 width=100%>
	<% if(startDatime!=null&startDatime!=""){%>
	  <tr height=30><td colspan="7" align=center><font color=red><%=startDatime %></font>到<font color=red><%=endDatime %></font>间电压的历史数据</td></tr>
	  <%}else { %>
	  <tr height=30><td colspan="7" align=center><font color=red>请输入查询时间</font></td></tr>
	  <%}%>
	  <tr bgColor=#0080FF  align=center><td >支路</td><td >报警信息</td><td>报警时间</td></tr>
	 
	<%
	if(datas != null){  
		for(AlertData d:datas){
			String time = d.getAddtime() ;
			if(time != null){
				String timeD = time.substring(0,4)+"/"+time.substring(4,6)+"/"+time.substring(6,8)+"/"+time.substring(8,10)+":"+time.substring(10,12)+":"+time.substring(12,14)+":"+time.substring(14,17); 	
		%>
		 <tr><td  bgColor=#C7C7E2  align=center ><%=d.getZlNo()%></td><td bgColor=#C7C7E2 align=left><%=d.getMessage() %></td><td  bgColor=#C7C7E2  align=center><%=timeD %></td></tr>
		<%}
		}
	}
	%>

	</table>
  </td>
</tr>
</table>
</form>
</body>
</html>