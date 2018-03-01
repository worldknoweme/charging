package cn.edu.shu.entity;

import java.io.Serializable;
/**
 * 
 * @author 
 * 充电桩实体类
 */
public class Device implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	id主键
	private int id;
	//充电桩编号
	private String code;
	//充电桩名称
	private String name;
	//充电桩地址
	private String address;
	//充电桩负责人
	private String person;
	//充电桩所在维度
	private String lat;
	//充电桩所在经度
	private String lng;
	//充电桩描述
	private String des;
	//扩展字段1
	private String des1;
	//扩展字段2
	private String des2;
	//扩展字段3
	private String des3;
	
	
	public Device(int id, String code, String name, String address,
			String person, String lat, String lng, String des) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.address = address;
		this.person = person;
		this.lat = lat;
		this.lng = lng;
		this.des = des;
	}
	public Device(int id, String code, String name, String address,
			String person, String lat, String lng, String des, String des1,
			String des2, String des3) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.address = address;
		this.person = person;
		this.lat = lat;
		this.lng = lng;
		this.des = des;
		this.des1 = des1;
		this.des2 = des2;
		this.des3 = des3;
	}
	public Device() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getDes1() {
		return des1;
	}
	public void setDes1(String des1) {
		this.des1 = des1;
	}
	public String getDes2() {
		return des2;
	}
	public void setDes2(String des2) {
		this.des2 = des2;
	}
	public String getDes3() {
		return des3;
	}
	public void setDes3(String des3) {
		this.des3 = des3;
	}
	
}
