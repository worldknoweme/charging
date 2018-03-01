package cn.edu.shu.entity;

import java.util.ArrayList;

public class DataBuffer {
	ArrayList<Double>currt2=new ArrayList<Double>();
	ArrayList<Double>currt1=new ArrayList<Double>();
	ArrayList<Double>volt1=new ArrayList<Double>();
	ArrayList<Double>volt2=new ArrayList<Double>();
	
	
		

	public ArrayList<Double> getCurrt2() {
		return currt2;
	}
	public void setCurrt2(ArrayList<Double> currt2) {
		this.currt2 = currt2;
	}
	public ArrayList<Double> getCurrt1() {
		return currt1;
	}
	public void setCurrt1(ArrayList<Double> currt1) {
		this.currt1 = currt1;
	}
	public ArrayList<Double> getVolt1() {
		return volt1;
	}
	public void setVolt1(ArrayList<Double> volt1) {
		this.volt1 = volt1;
	}
	public ArrayList<Double> getVolt2() {
		return volt2;
	}
	public void setVolt2(ArrayList<Double> volt2) {
		this.volt2 = volt2;
	}
	
}
