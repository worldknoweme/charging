package cn.edu.shu.utils;

import java.util.ArrayList;

import cn.edu.shu.entity.DataBuffer;
import cn.edu.shu.service.impl.DataAcqService;
public class WriteDbUtil {

	public static void writeToDb(DataBuffer daf){
	 ArrayList<Double> data1=new ArrayList<Double>();
	 ArrayList<Double> data2=new ArrayList<Double>();
	 ArrayList<Double> data3=new ArrayList<Double>();
	 ArrayList<Double> data4=new ArrayList<Double>();
	 double voltg1 =0.0;
	 double voltg2 =0.0;
	 double currt1 =0.0;
	 double currt2 =0.0;
	 
		/**
		 * 将数据存入数据库，由管理人员在页面操作，点击存取数据触发
		 */
				if(daf!=null){
						data1=daf.getCurrt1();
						data2=daf.getCurrt2();
						data3=daf.getVolt1();			
						data4=daf.getVolt2();	
				}else{
					data1.clear();
					data2.clear();
					data3.clear();
					data4.clear();
				}
						
						DataAcqService das=new DataAcqService();
						if(!(data1.isEmpty())&&!(data2.isEmpty())&&!(data3.isEmpty())&&!(data4.isEmpty())){
							//System.out.println("准备存储数据。。。。。");	
								 currt1 = CountRealData.RealData(data1);
								 currt2 = CountRealData.RealData(data2);
								 voltg1 = CountRealData.RealData(data3);
							     voltg2 = CountRealData.RealData(data4); 					
							   //voltage,currency1,currency2,addtime,voltage2
								das.setHistoryData(ToObjArrayUtil.ArrayListtoObjArray(data3, data1, data2, TimeUtils.getTime(),data4));						
								// System.out.println("*1*"+voltg1+"*2*"+voltg2+"*3*"+currt1+"*4*"+currt2);	
								//voltg1=0.1;voltg2=0.1;currt1=-0.1;currt2=0.1;
								das.updateRealData(voltg1,voltg2,currt1,currt2);
						}
							
						data1.clear();
						data2.clear();
						data3.clear();
						data4.clear();
	}
		 	
}
