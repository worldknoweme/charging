package cn.edu.shu.utils;

import java.util.ArrayList;
import java.util.List;


public class CountRealData {
	
	
	
	public  static double RealData(List<Double> list)
	{
		
		//List<Double> list=new ArrayList<Double>();
			/**
			 * 判断周期、计算数据有效值
			 */
			//int contj=0;
			int olen=0;
		//	boolean flag=false;
//			int zeroDot[]=new int[20];
			ArrayList<Integer>zeroDot=new ArrayList<Integer>();//存储零点的索引
			double realdata=0.0;
	
			for(int i=0;i<list.size()-1;i++){		
//				if(flag)
//				cont2++;		
				if(list.get(i)*list.get(i+1)<=0){			
					zeroDot.add(i+1);	
					//contj++;				
//					if(flag&&(cont2<30||cont2>60)){//周期异常应舍弃
//						if(list.get(i)<=0)
//						flag=true;
//					}							
				}				
			}
	
	
	if((olen=zeroDot.size())>2)//判断至少一个周期
			{
ArrayList<Double> realDa=new ArrayList<Double>();
					double sum=0;
					int len=0;
				
					if(olen%2!=0){		
							len=(olen-1)/2;
					 }
					else{
							len=(olen-2)/2;
					 }
					while((len--)!=0){
							double Nlen=(double)(zeroDot.get(2+len)-zeroDot.get(len));
							for(int i=zeroDot.get(len);i<zeroDot.get(2+len);i++){	//len=len--	
								sum+=list.get(i)*list.get(i);
							}
							sum/=Nlen;
							realDa.add(Math.sqrt(sum));
					}
					for(double i:realDa){
						realdata+=i;
					}
					realdata=realdata/realDa.size();
					//System.out.println(realdata);
			}
	return realdata;
	}
}
