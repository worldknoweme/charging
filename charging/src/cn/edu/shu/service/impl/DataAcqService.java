package cn.edu.shu.service.impl;

import java.util.ArrayList;
import java.util.Map;

import cn.edu.shu.dao.impl.DataAcquisitionDao;
import cn.edu.shu.entity.AlertData;
import cn.edu.shu.entity.Data;
import cn.edu.shu.entity.RealData;
import cn.edu.shu.service.IDataAcqService;
import cn.edu.shu.utils.PageBean;

public class DataAcqService implements IDataAcqService {
	DataAcquisitionDao dad=new DataAcquisitionDao();

/**
 * 确认报警
 */
	@Override
	public void confirmAlert() {
		dad.confirmAlert();
		
	}

	@Override
	/**
	 * 获取实时支路数据(service)
	 */
	public RealData getRealTimeData(String deviceID) {
		return dad.getRealTimeData(deviceID);
	}
	
	@Override
	/**
	 * 获取历史支路数据
	 */
	public ArrayList<Data> getHistroyData(String startD, String endD) {
	
		return dad.getHistroyData(startD, endD);
	}
	
	@Override
	/**
	 * 更新有效数据(service)
	 */
	public boolean updateRealData(double vol1, double vol2, double cur1, double cur2) {

		return dad.updateRealData(vol1, vol2, cur1, cur2);
	}

	@Override
	public void setHistoryData(Object[][] objArray) {

		dad.setHistoryData(objArray);
	}


	@Override
	/**
	 * 验证用户
	 */
	public Map<String, String> checkUser(String username, String password) {

		return dad.checkUser(username, password);
	}

	@Override
	/**
	 * 修改实时数据将其设置为已记录入报警(service)
	 */
	public boolean setCurrentdata(int id) {

		return dad.setCurrentdata(id);
	}

	@Override
	/**
	 * 查找最新报警信息(service)
	 */
	public AlertData getNewAlert() {

		return dad.getNewAlert();
	}

	@Override
	public boolean insertIntoAlertmsg(String alertM,String datime,int zlNo) {

		return dad.insertIntoAlertmsg(alertM,datime,zlNo);
	}

	@Override
	public void getH5yDataPage(PageBean<Data> pb) {
		String startD=pb.getStartD();
		String endD=pb.getEndD();
		if(Long.parseLong(startD)>Long.parseLong(endD)){
			System.out.println("***input time wrong*** ");
		}
		else{
			dad.getH5yDataPage(pb);
		}
		
		
	}

	@Override
	public int getTotalCount(String table, String startD, String endD) {
	
		return dad.getTotalCount(table, startD, endD);
	}

	@Override
	public int getBeginCount(String table, String startD) {
	
		return dad.getBeginCount(table, startD);
	}

	@Override
	public void getHistroyAlert(PageBean<AlertData> pb) {
		String startD=pb.getStartD();
		String endD=pb.getEndD();
		if(Long.parseLong(startD)>Long.parseLong(endD)){
			System.out.println("***input time wrong*** ");
		}
		else{
		    dad.getHistroyAlert(pb);
		}
   
		
	}
public ArrayList<AlertData> getHistroyAlert(String startD, String endD) {
		
		return dad.getHistroyAlert(startD, endD);
	}



}
