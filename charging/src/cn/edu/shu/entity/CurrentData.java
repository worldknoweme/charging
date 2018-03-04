package cn.edu.shu.entity;
/**
 * 实时数据实体类
 * @author 
 *
 */
public class CurrentData {
	private int id;
	private int re ;
	private double voltage1 ;
	private double voltage2 ;
	private double current1 ;
	private double current2 ;
	private int zlNo ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRe() {
		return re;
	}
	public void setRe(int re) {
		this.re = re;
	}
	public double getVoltage1() {
		return voltage1;
	}
	public void setVoltage1(double voltage1) {
		this.voltage1 = voltage1;
	}
	public double getVoltage2() {
		return voltage2;
	}
	public void setVoltage2(double voltage2) {
		this.voltage2 = voltage2;
	}
	public double getCurrent1() {
		return current1;
	}
	public void setCurrent1(double current1) {
		this.current1 = current1;
	}
	public double getCurrent2() {
		return current2;
	}
	public void setCurrent2(double current2) {
		this.current2 = current2;
	}
	public int getZlNo() {
		return zlNo;
	}
	public void setZlNo(int zlNo) {
		this.zlNo = zlNo;
	}
}
