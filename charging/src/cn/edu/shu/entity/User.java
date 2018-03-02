package cn.edu.shu.entity;

import java.io.Serializable;

/**
 * 
 * @author 
 * 用户实体类
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1539651710936270085L;
	
	private int id;
	//用户名
	private String username;
	//密码
	private String password;
	//真实姓名
	private String realname;
	//职务
	private String unit;
	//电话
	private String phone;
	//电子邮箱
	private String email;
	//登录时间
	private String logontimes;
	//最后登录时间
	private String lasttime;
	//权限
	private String priority;
	
	
	public User() {
		super();
	}
	public User(int id, String username, String password, String realname,
			String unit, String phone, String email, String priority) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.unit = unit;
		this.phone = phone;
		this.email = email;
		this.logontimes = logontimes;
		this.lasttime = lasttime;
		this.priority = priority;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogontimes() {
		return logontimes;
	}
	public void setLogontimes(String logontimes) {
		this.logontimes = logontimes;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	

}
