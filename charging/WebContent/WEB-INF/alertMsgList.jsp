<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/alertMsgList.css">
<style type="text/css"></style>
<title>报警数据查询</title>
</head>
<script type="text/javascript" > 
function submit1(str1,str2){
	var startD="";
	var endD="";
	/* alert("输入时间格式不正确"); */
		if(str1==null||str1==""||str2==null||str2==""){
			alert("请输入查询时间");return false;
		}
		else{
			var starttime=str1.split("");
			var endtime=str2.split("");
			if(starttime.length== 14&&endtime.length==14){
				for(var i=0;i<14;i++){
					if(starttime[i]==" "||endtime==" ")
						return false;	
				}
				return true;
			}
			else{alert("输入时间格式不正确"); return false;}
		}
		
}
</script> 
<body id="dataTop">
<jsp:include page="../top.jsp" flush="true"/>
  	
<!-- 	锚链接<div class="linkMao">
	<div class="datatop"><a href="#dataTop">顶部^</a></div>
	<div class="databottom"><a href="#dataBottom">底部v</a></div>
	</div> -->
	<table>
			 <tr>
		  			<td colspan="8" align="center" class="utils">
		  				<a href="#dataTop">顶端</a>&nbsp;&nbsp;	
		  				<a href="#dataBottom">底端</a> &nbsp;&nbsp;	
		  				<a href="#">当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页</a>     &nbsp;&nbsp;		
		  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=1">首页</a>&nbsp;&nbsp;	
		  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>&nbsp;&nbsp;	
		  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>&nbsp;&nbsp;	
		  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=${requestScope.pageBean.totalPage}">末页</a>  	  				
		  			</td>
		  		</tr>
  		</table>
  	<table  width="80%"  border="1"  cellspacing="0" cellpadding="4" align="center"  >
		  <tr valign="top">
		    <td colspan="8">>
			    <form action="WriteSession" >
			    <!-- 传递session -->
			    <input type="hidden" id="id" name="id" value="${sessionScope.id}">
			    <input type="hidden" id="pageFlag" name="pageFlag" value="AlertMsgList">
				      <table width="100%" cellspacing="0" cellpadding="0" height="30">
				        <tr>
				          <td height="20"><font size="4"><b>查询报警数据</b></font>(格式:年月日时分秒)</td>
				          <td  align="center" ><font size="4"><b>起始时间：</b></font></td>
				          <td  align="left" ><input type="text" name="startD" id="startD" size="20"  value="${sessionScope.startD}"></td>
				          <td  align="center"><font size="4"><b>截止时间：</b></font></td>
				          <td  align="left" ><input type="text" name="endD" id="endD" size="20" value="${sessionScope.endD}"></td>
				          <td width="10%" height="20"><input type="submit" value=" 查询 "  onclick='return submit1((document.getElementById("startD")).value,(document.getElementById("endD")).value)'></td>
				        <td>每页<input size="3"  type="text" name="pageCount" value="${sessionScope.pageCount }">行</td>
				        </tr>
				      </table>
			      </form>
		    </td>
		  </tr>
		  	<tr>
  			<td colspan="8" align="center">
  				当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;		
  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=1">首页</a>
  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>
  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>
  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=${requestScope.pageBean.totalPage}">末页</a>   &nbsp;&nbsp;	
  				<a href="#dataTop">顶端</a>
  				<a href="#dataBottom">底端</a>
  			</td>
  		</tr>
  		<tr align=center>
  			<td width="7%"><b>序号</b></td>
  			<td width="10%"><b>id</b></td>
  			<td width="4%"><b>支路</b></td>
  			<td width="50%"><b>报警信息</b></td>
  			<td width="14%"><b>报警时间</b></td>
  			 <td width="14%"><b>信息确认(0/1)</b></td>
  			<!--<td width="14%"><b>电压2VSM200D/(V)</b></td>
  			<td width="14%"><b>时间</b></td> -->
  		</tr>
  		<!-- 迭代数据 -->
  		<c:choose>
  			<c:when test="${not empty requestScope.pageBean.pageData}">
  				<c:forEach var="data" items="${requestScope.pageBean.pageData}" varStatus="vs">
  					<tr align=center>
  						<td>${vs.count }</td>
  						<td>${data.id }</td>
  						<td>${data.zlNo }</td>
  						<td>${data.message }</td>
  						<td>${data.addtime }</td>
  						<td>${data.confirm }</td>
  						<%-- <td>${data.current2 }</td>
  						<td>${data.addtime }</td> --%>
  					</tr>
  				</c:forEach>
  			</c:when>
  			<c:otherwise>
  				<tr>
  					<td colspan="8">对不起，没有你要找的数据</td>
  				</tr>
  			</c:otherwise>
  		</c:choose>
  		
  		<tr>
  			<td colspan="8" align="center">
  				当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;		
  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=1">首页</a>
  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>
  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>
  				<a href="${pageContext.request.contextPath }/AlertMsgList?currentPage=${requestScope.pageBean.totalPage}">末页</a>   &nbsp;&nbsp;	
  				<a href="#dataTop">顶端</a>
  				<a href="#dataBottom">底端</a>
  			</td>
  		</tr>
  	</table>
  	<div id="dataBottom"></div>
</body >
</html>