package cn.edu.shu.utils;

import java.util.ArrayList;

public class ToObjArrayUtil {
	//alertM,datime,zlNo
	public static String[][] SSItoObjArray(String alertM,String datime,String zlNo){
		
		String[][] objArray={{alertM,datime,zlNo}};
		
		return  objArray;
	}
	//voltage,currency1,currency2,addtime,voltage2
	public static Object[][] ArrayListtoObjArray(ArrayList<Double>vol1,ArrayList<Double>cur1,
			ArrayList<Double>cur2,String addTime,ArrayList<Double>vol2){
		Object[][] objArray=new Object[cur1.size()][5];
		for(int i=0;i<cur1.size();i++){					
			 objArray[i][0]=vol1.get(i);			 
			 objArray[i][1]=cur1.get(i);
			 objArray[i][2]=cur2.get(i);
			 objArray[i][3]=addTime; 
			 objArray[i][4]=vol2.get(i); 	
		}
		return objArray;
	}

}
