<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
<title>监控系统——登录界面</title>
</head>
<script type="text/javascript">
function checkform(Frm)
{
	var flag=true ;
	if(Frm.id.value=="" || Frm.id.value==null)
	{
		alert("用户名不能为空！") ;
		Frm.id.focus() ;
		flag=false ;
	}
    if(Frm.password.value=="" || Frm.password.value==null)
	{
		alert("密码不能为空！") ;
		Frm.password.focus() ;
		flag=false ;
	}
	return flag ;
}
</script>
<body>
<div class="content clearfix" id="infobox">
		<div class="top">
		充电桩监控系统
		</div>
		
		<div class="middle">
		
			<div  class="login_">
			<%if(request.getParameter("sign")!=null){
		                       if(request.getParameter("sign").equals("false")){
	                                     %>		
			  				<div  class="login_tip">
			  				密码或用户名错误！
			  				</div>
			  				<%}
	                     }%>			    
			           <div class="head">
			  				用户登录</div>			  		
			           <form class="body" method="post" action="/charging/Login" onSubmit="return checkform(this)">
			    			<div  class="user">
			  				用户名：<input type="text" id="id" name="id">
			  				</div>
			  				<div class="password">
			  				密 码：<input type="password" id="password" name="password">
			  				</div>
			  				<div>
			  				<input type="submit" id="submit"  value="登陆">
			  				<input type="reset" id="reset" value="重置">
			  				</div>
			            </form>
			     </div>
		</div>
		
		<div class="footer">
        说明:请不要屏蔽弹出窗口
		</div>
</div>
</body>
</html>