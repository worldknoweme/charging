package cn.edu.shu.service;

import java.util.List;

import cn.edu.shu.entity.Device;

public interface IDeviceService {

	//分页查询充电桩信息
	public List<Device> getDeviceList(String code,int currentPage,int pageSize);
	//根据充电桩编号获取充电桩总数
	public int count(String code);
	//插入或者更新充电桩信息
	public void saveOrUpdateDevice(Device device);
}
