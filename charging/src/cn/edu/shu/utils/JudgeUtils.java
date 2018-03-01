package cn.edu.shu.utils;

import java.util.List;

public class JudgeUtils {
	public static List<Double> isEmptyOperation(List<Double> list){
		if(list.isEmpty())
		{			  			 
			  	list.add(0.0);			  	
		}
		return list;
	}	
}
