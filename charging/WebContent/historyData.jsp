<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="cn.edu.shu.entity.*,java.util.* ,cn.edu.shu.service.impl.DataAcqService" %>
<% 
	ArrayList<Data> datas=null;
    String startD = "2016-11-01-00-00-00" ;
    String endD = "2016-12-31-00-00-00";
    String startDatime = "" ;
    String endDatime = "" ;
    if(request.getParameter("startD")!=null&&request.getParameter("startD")!=""&&request.getParameter("endD")!=null&&request.getParameter("endD")!=""){
    startD = request.getParameter("startD") ; 
    String[] sDate = startD.split("-") ;
    endD = request.getParameter("endD") ;
    String[] eDate = endD.split("-") ;
    
    if(sDate.length ==6&&eDate.length ==6){
    String str_sDate = "" ;
    for(int i=0;i<sDate.length;i++)
    	str_sDate += sDate[i] ;
    str_sDate+="000";
    startDatime =  sDate[0]+"/"+sDate[1]+"/"+sDate[2]+"/"+sDate[3]+":"+sDate[4]+":"+sDate[5];
    String str_eDate = "" ; 
    for(int i=0;i<eDate.length;i++)
    	str_eDate += eDate[i] ;
    str_eDate +="000";
    endDatime =  eDate[0]+"/"+eDate[1]+"/"+eDate[2]+"/"+eDate[3]+":"+eDate[4]+":"+eDate[5];
 
	DataAcqService da=new DataAcqService();
	datas=da.getHistroyData(str_sDate, str_eDate);
    }	} 
%>
<html>
<head>
<title>数据信息</title>
</head>
<script type="text/javascript" > 
function submit1(str1,str2){
	var startD="";
	var endD="";
	if(str1==null||str1==""||str2==null||str2==""){
		alert("请输入查询时间");return false;
	}
	else{
		var starttime=str1.split("-");
		var endtime=str2.split("-");
		if(starttime.length== 6&&endtime.length==6){
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
<form action="historyData.jsp" >
<table  width="100%"  border="1"  cellspacing="0" cellpadding="0" >
  <tr valign="top">
    <td>
      <table width="100%" cellspacing="0" cellpadding="0" height="30">
        <tr>
          <td height="20"><font size="4"><b>查询历史数据</b></font>(格式:年-月-日-时-分-秒-毫秒(2016-01-01-00-00-00))</td>
          <td  align="center" ><font size="4"><b>起始时间：</b></font></td>
          <td  align="left" ><input type="text" name="startD" id="startD" size="20" value=<%=startD%>></td>
          <td  align="center"><font size="4"><b>截止时间：</b></font></td>
          <td  align="left" ><input type="text" name="endD" id="endD" size="20"  value=<%=endD%>></td>
          <td width="10%" height="20"><input type="submit" value=" 查询 "  onclick='return submit1((document.getElementById("startD")).value,(document.getElementById("endD")).value)'></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr valign="top"  id="totop">
    <td>
	  <table border=“1”  width=100%   cellspacing="0" cellpadding="0" >
	  <% if(startDatime!=null&startDatime!=""){%>
	  <tr height=30><td colspan="7" align=center><font color=red><%=startDatime %></font>到<font color=red><%=endDatime %></font>间电压的历史数据
	  <%}else { %>
	  <tr height=30><td bgColor=#C7C7E2 colspan="7" align=center><font color=red>请输入查询时间</font>
	  <%}%>
	  <tr bgColor=#7D7DFF height=30 align=center><td width="4%">支路</td><td width="17%">电压1(V)</td><td width="17%">电压2(V)</td><td width="17%">电流1CS010GT(A)</td><td width="17%">电流2CS030EK(A)</td><td>时间</td></tr>
	 
	<%
			if(datas != null){  
				for(Data d:datas){//遍历
					String time = d.getAddtime() ;
					if(time != null){
						String timeD = time.substring(0,4)+"/"+time.substring(4,6)+"/"+time.substring(6,8)+"/"+time.substring(8,10)+":"+time.substring(10,12)+":"+time.substring(12,14)+":"+time.substring(14,17);
				%>
				 <tr bgColor=#C7C7E2 align=center><td width="4%"><%=d.getZl() %></td><td width="17%"><%=d.getVoltage1() %></td><td width="17%"><%=d.getVoltage2() %></td><td width="17%"><%=d.getCurrent1() %></td><td width="17%"><%=d.getCurrent2() %></td><td ><%=timeD %></td></tr>
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