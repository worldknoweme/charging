package cn.edu.shu.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

import cn.edu.shu.dao.IDataAcquisitionDao;
import cn.edu.shu.dao.impl.DataAcquisitionDao;

/**
 * 在 TimerManager 这个类里面，大家一定要注意 时间点的问题。如果你设定在凌晨2点执行任务。但你是在2点以后
 *发布的程序或是重启过服务，那这样的情况下，任务会立即执行，而不是等到第二天的凌晨2点执行。为了，避免这种情况
 *发生，只能判断一下，如果发布或重启服务的时间晚于定时执行任务的时间，就在此基础上加一天。
 * @author 
 *
 */
public class NFDFlightDataTimerTask extends TimerTask {
	//获取时间格式
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
	private IDataAcquisitionDao dataAcquisitionDao = new DataAcquisitionDao<>();
	@Override
    public void run() {
        try {
             //在这里写你要执行的内容
            System.out.println("执行当前时间"+formatter.format(Calendar.getInstance().getTime()));
            //将实时数据表里面的数据插入到历史数据表
            //目前实时数据表里面没有创建时间字段  很伤
            //暂定方案  加上上报时间字段，在数据模拟插入的时候将创建时间入库
            //20180308
            dataAcquisitionDao.batchCurrentData();
        } catch (Exception e) {
            System.out.println("-------------解析信息发生异常--------------");
        }
    }
     
}
