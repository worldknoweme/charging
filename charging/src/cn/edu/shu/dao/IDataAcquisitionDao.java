package cn.edu.shu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.shu.entity.AlertData;
import cn.edu.shu.entity.Data;
import cn.edu.shu.entity.Device;
import cn.edu.shu.entity.RealData;
import cn.edu.shu.utils.PageBean;

/**
 * 数据操作Dao接口
 * @param <T>*/

public interface IDataAcquisitionDao {
	//确认报警
	public void confirmAlert();
	//获取实时数据
	public RealData getRealTimeData();
	//获取历史数据
	public ArrayList<Data> getHistroyData(String startD,String endD);
	//存放实时数据
	public boolean updateRealData(double vol1,double vol2,double cur1,double cur2);
	//存放历史数据
	//public void  setHistoryData(String addtime,ArrayList<Double>cur1,ArrayList<Double>cur2,ArrayList<Double>vol1,ArrayList<Double>vol2)
	public void  setHistoryData(Object[][] objArray);
	//存放报警信息
	public void getHistroyAlert(PageBean<AlertData> pb);
	//用户登录验证
	public Map<String, String> checkUser(String username,String password);
	//修改实时数据将其设置为已记录入报警
	public boolean setCurrentdata(int id);
	//查找最新报警信息
	public AlertData getNewAlert();
	//添加数据入报警数据表
	public boolean insertIntoAlertmsg(String alertM,String datime,int zlNo);
	//public boolean insertIntoAlertmsg(String[][] objArray);
	//获取分页历史数据
	public void getH5yDataPage(PageBean<Data> pb);
	//get total record
	public int getTotalCount(String table,String startD,String endD);
	//get start record
	public int getBeginCount(String table,String startD);
	ArrayList<AlertData> getHistroyAlert(String startD, String endD);
	
	//根据编号分页获取充电桩信息
	public List<Device> getDeviceListByCodeAndLimit(String code,int start,int end);
	//根据编号查询充电桩总数
	public int countDevice(String code);
	//插入或者更新一条充电桩信息
	public void saveOrUpdateDevice(Device device);
	
	//根据id进行充电桩的查询
	public Device getDataByID(int id);
	
	//删除一条充电桩数据
	public void delDeviceById(int id);
	
	//获取所有充电桩信息
	public List<Device> getDeviceAll();
}
