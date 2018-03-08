package cn.edu.shu.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.shu.dao.IDataAcquisitionDao;
import cn.edu.shu.dao.impl.DataAcquisitionDao;

public class BatchTask {
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssS");

	private static IDataAcquisitionDao dataAcquisitionDao = new DataAcquisitionDao<>();
	public static void main(String args[]){
		
		System.out.println("开始执行");
		System.out.println(formatter.format(new Date()));
		dataAcquisitionDao.batchCurrentData();
	}
}
