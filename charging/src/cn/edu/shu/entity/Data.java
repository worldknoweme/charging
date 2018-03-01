package cn.edu.shu.entity;

import java.io.Serializable;

public class Data  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int zl ;//充电桩编号
	private double voltage1 ;//输入电压
	private double voltage2 ;//输出电压
	private double current1 ;//输入电流
	private double current2 ;//输出电流
	private String addtime ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getZl(){
		return zl ;
	}
	public void setZl(int zl){
		this.zl = zl ;
	}
	
	public double getVoltage1(){
		return voltage1 ;
	}
	public void setVoltage1(double volume){
		this.voltage1 = volume;
	}
	public double getVoltage2(){
		return voltage2 ;
	}
	public void setVoltage2(double volume2){
		this.voltage2 = volume2 ;
	}
	
	public double getCurrent1(){
		return current1 ;
	}
	public void setCurrent1(double current1){
		this.current1 = current1 ;
	}
	
	public double getCurrent2(){
		return current2 ;
	}
	public void setCurrent2(double current2){
		this.current2 = current2 ;
	}
	
	
	public String getAddtime(){
		return addtime ;
	}
	public void setAddtime(String addtime){
		this.addtime = addtime ;
	}
}
