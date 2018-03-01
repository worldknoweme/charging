package cn.edu.shu.service.impl;

import java.util.List;

import cn.edu.shu.dao.IDataAcquisitionDao;
import cn.edu.shu.dao.impl.DataAcquisitionDao;
import cn.edu.shu.entity.Device;
import cn.edu.shu.service.IDeviceService;

public class DeviceServiceImpl implements IDeviceService{
	//这里是有接口去new，传入实体类
	IDataAcquisitionDao dataAcquisitionDao = new DataAcquisitionDao<Device>();

	@Override
	public List<Device> getDeviceList(String code,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
	
		int start = (currentPage-1)*pageSize;
		
		int end = currentPage*pageSize;
		return dataAcquisitionDao.getDeviceListByCodeAndLimit(code, start, end);
		
	}

	@Override
	public int count(String code) {
		// TODO Auto-generated method stub
		return dataAcquisitionDao.countDevice(code);
	}

	@Override
	public void saveOrUpdateDevice(Device device) {
		// TODO Auto-generated method stub
		dataAcquisitionDao.saveOrUpdateDevice(device);
	}
	

}
