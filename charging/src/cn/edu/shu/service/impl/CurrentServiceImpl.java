package cn.edu.shu.service.impl;

import cn.edu.shu.dao.IDataAcquisitionDao;
import cn.edu.shu.dao.impl.DataAcquisitionDao;
import cn.edu.shu.entity.CurrentData;
import cn.edu.shu.service.ICurrentDataService;

public class CurrentServiceImpl implements ICurrentDataService{

	private IDataAcquisitionDao dataAcquisitionDao = new DataAcquisitionDao<CurrentData>();
	@Override
	public void insertData(String inputVol, String inputCurrent, String outVol,
			String outCurrent, String deviceID) {
		// TODO Auto-generated method stub
		dataAcquisitionDao.insertData(inputVol, inputCurrent, outVol, outCurrent, deviceID);
	}

}
