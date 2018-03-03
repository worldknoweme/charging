package cn.edu.shu.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.edu.shu.dao.IDataAcquisitionDao;
import cn.edu.shu.dao.impl.DataAcquisitionDao;
import cn.edu.shu.entity.Data;
import cn.edu.shu.service.IHistoryDataService;

public class HistoryDataServiceImpl implements IHistoryDataService{
	private IDataAcquisitionDao dataAcquisitionDao = new DataAcquisitionDao<Data>();
	@Override
	public List<Data> getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Data> getHistroyDataByDeviceID(String deviceID) {
		// TODO Auto-generated method stub
		return dataAcquisitionDao.getHistroyDataByDeviceID(deviceID);
	}

}
