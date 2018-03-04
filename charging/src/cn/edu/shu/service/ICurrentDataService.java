package cn.edu.shu.service;

public interface ICurrentDataService {
	/**
	 * 存入实时数据表
	 * @param inputVol 输入电流
	 * @param inputCurrent 输入电压
	 * @param outVol 输出电流
	 * @param outCurrent 输出电压
	 * @param deviceID 设备id
	 */
	public void insertData(String inputVol,String inputCurrent,String outVol,String outCurrent,String deviceID);
}
