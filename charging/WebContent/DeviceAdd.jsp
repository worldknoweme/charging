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
	#allmap {width: 100%;height: 50%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=	SdgXo8Xp06aa6KmrYkKBLKEpGOOjcUs4"></script>
<title>充电桩信息新增</title>
</head>
<body>
<jsp:include page="./top.jsp" flush="true"/>
<form action="SaveOrUpdateDevice" method="post" >
<table  width="100%"  border="1"  cellspacing="0" cellpadding="0" >
 <tr valign="top">
 <td>
 设备编号：
 </td>
 <td><input type="hidden" name="id" value="0"><input type="text" name="code"></td>
 </tr>
 <tr valign="top">
 <td>
 设备名称：
 </td>
 <td><input type="text" name="name"></td>
 </tr>
  <tr valign="top">
 <td>
 设备地址：
 </td>
 <td><input type="text" name="address"></td>
 </tr>
   <tr valign="top">
 <td>
 维护人：
 </td>
 <td><input type="text" name="person"></td>
 </tr>
 <tr>
 <td>经度：</td>
 <td><input type="text" id="lng" name="lng" readonly="readonly"></td>
 </tr>
 <tr>
 <td>纬度：</td>
 <td><input id="lat" type="text" name="lat" readonly="readonly"></td>
 </tr>
 <tr>
 	<td>描述：</td>
 	<td><textarea name="des"></textarea></td>
 </tr>
 <tr>
 <td><input type="submit" value="确定"></td>
 <td><input type="button" value="取消"></td>
 </tr>
</table>

</form>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom("上海", 15);  // 初始化地图,设置中心点坐标和地图级别
	//添加地图类型控件
	map.addControl(new BMap.MapTypeControl({
		mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));	  
	map.setCurrentCity("上海");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	//单击获取点击的经纬度
    map.addEventListener("click",function(e){
    	//先将上一个覆盖物删除
    	map.clearOverlays();
        var jing_du_value = e.point.lng ;
        var wei_du_value =  e.point.lat;
        //alert(e.point.lng + "," + e.point.lat);
        var jing_du = document.getElementById("lng");
        var wei_du = document.getElementById("lat");
        jing_du.value= jing_du_value;
        wei_du.value= wei_du_value;
       
        var marker = new BMap.Marker(new BMap.Point(jing_du_value, wei_du_value));;  // 创建标注
        map.addOverlay(marker);               // 将标注添加到地图中
      //  marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    });
</script>