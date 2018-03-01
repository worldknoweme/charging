package cn.edu.shu.entity;

public class AlertData {
	private String message ;
	private String addtime ;
	private int zlNo ;
	private int id ;
	private int confirm ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public String getMessage(){
		return message ;
	}
	public void setMessage(String message){
		this.message = message ;
	}
	
	public String getAddtime(){
		return addtime ;
	}
	public void setAddtime(String addtime){
		this.addtime = addtime ;
	}
	
	public int getZlNo(){
		return zlNo ;
	}
	public void setZlNo(int zlNo){
		this.zlNo = zlNo ;
	}
}
