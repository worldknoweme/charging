package cn.edu.shu.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.edu.shu.dao.IDataAcquisitionDao;
import cn.edu.shu.entity.AlertData;
import cn.edu.shu.entity.Data;
import cn.edu.shu.entity.RealData;
import cn.edu.shu.utils.JdbcUtils;

public class TestDataAcquisitionDao {
	
	private QueryRunner qr=JdbcUtils.getQueryRuner(); 
	
	/**
	 * 确认报警
	 */
	public void confirmAlert() {
		int id = 1;
		String sql1="select id from alertmsg where id in (select max(id)from alertmsg )";//
		String sql2="update alertmsg set confirm=1 where id =?";
				try {
					 id=qr.query(sql1, new ResultSetHandler<Integer>(){		
						@Override
						public Integer handle(ResultSet arg0) throws SQLException {
							if(arg0.next())
							return arg0.getInt("id");
							return null;
						}
						
					});
					 qr.update(sql2,id);
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				
	}
	
	
	/**
	 * 获取实时支路数据
	 */
	public RealData getRealTimeData() {
		
		String sql = "SELECT * FROM currentdata WHERE id IN (SELECT MAX(id) FROM currentdata)" ; 
		RealData realData=null;
		try {
			realData = qr.query(sql, new BeanHandler<RealData>(RealData.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return realData;
	}

	@SuppressWarnings("unchecked")
	
	/**
	 * 获取历史支路数据
	 */
	
	/**
	 * 存入支路数据
	 */
	public void setHistoryData(Object[][] objArray) {
		String sql = "INSERT INTO historydata (voltage,currency1,currency2,addtime,voltage2) VALUES(?,?,?,?,?)";
		try {
			qr.batch(sql, objArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 获取历史报警数据
	 */
	public ArrayList<AlertData> getHistroyAlert(String startD, String endD) {
		List<AlertData> list=null;
			String sql = "SELECT * FROM alertmsg WHERE addtime >=? AND addtime <=?" ;
		try {
			list=qr.query(sql, new BeanListHandler<AlertData>(AlertData.class), startD,endD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList<AlertData>)list;
	}

	
	/**
	 * 验证用户
	 */
	@Test
	public void checkUser() {
		String username="黄";String password="444";
//		String username="huang";String password="211";
		Map<String,String> map=new HashMap<String, String>();//标志和权限
	      //System.out.println("user"+username);
		String sql1="SELECT * FROM userdata WHERE username=? AND password=?";
		
		try {
			 map=qr.query(sql1, new ResultSetHandler<Map<String,String>>(){
				@Override
				public Map<String, String> handle(ResultSet rs) throws SQLException {
					Map<String,String> map=new HashMap<String, String>();//初始化创建对象
							if(rs.next()){
								int pri=rs.getInt("priority");
								java.util.Date m_date = new java.util.Date();//系统时间
								String lastTime = m_date.toString();
								String sql2="UPDATE userdata SET logontimes =logontimes+1,lasttime=? WHERE username=?";
								qr.update(sql2,lastTime,username);
								
								map.put("priority", ""+pri);
								map.put("flag","true");
							}else {
								map.put("priority", "0");
								map.put("flag","false");
							}
					            try {
					                if(rs != null) {
					                    rs.close();
					                }					          
					            } catch(SQLException e) {
					            	e.printStackTrace();
					                System.out.println("close rs or stat failed");
					            }
					            System.out.println(map);
									return map;
				}	
			},username,password);
		} catch (SQLException e) {
			System.err.println("error.CheckUser:"+e.getMessage()) ;
			e.printStackTrace();
		}      
	
	}
	
	/**
	 * 测试
	 */
	@Test
	public void test(){
		QueryRunner qr=JdbcUtils.getQueryRuner(); 
	String sql="SELECT  username FROM userdata where id=2"; 
	String re="";
	try {
		re=qr.query(sql, new ResultSetHandler<String>(){

			@Override
			public String handle(ResultSet rs) throws SQLException {
				if(rs.next())
				{
					return rs.getString(0);
				// System.out.println(arg0.getString(1));
				}
				else return "false";
			}
			
		});
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	System.out.println(re);
	}
	
	
	/**
	 * 查找最新报警信息
	 */
	public AlertData getNewAlert() {

		AlertData ad=null;
		String sql="SELECT * FROM alertmsg WHERE id IN (SELECT MAX(id)FROM alertmsg )";
		try {
			ad=qr.query(sql, new BeanHandler<AlertData>(AlertData.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ad;
	}
	
	
	/**
	 * 更新有效数据
	 */
	public boolean updateRealData(double vol1, double vol2, double cur1, double cur2) {
		int id=1;
		int result=0;
		//id最大值为2
		String sql1="select id from currentdata where id in (select max(id)from currentdata )";//
		String sql2="UPDATE currentdata SET voltage=?,voltage2=?,currency1=?,currency2=?,re=0 WHERE id=?" ; 

		try {
			 id=qr.query(sql1, new ResultSetHandler<Integer>(){		
				@Override
				public Integer handle(ResultSet arg0) throws SQLException {
					if(arg0.next())
					return arg0.getInt("id");
					return null;
				}		
			});
			 result=qr.update(sql2,vol1,vol2,cur1,cur2,id);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		if(result==0) return false;
		else return true;
	}

	
	/**
	 * 修改实时数据将其设置为已记录入报警
	 */
	public boolean setCurrentdata(int id) {
		int result=0;
		String sql="update currentdata set re=1 where id=?";
		try {
			result=qr.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result==0) return false;
		else return true;
	}

	
	/**
	 * 添加数据入报警数据表
	 */
	public boolean insertIntoAlertmsg(String alertM,String addtime,int zlNo) {
		
		boolean flag=false;
		System.out.println("插入报警信息："+alertM);
		String sql="insert into alertmsg (message,addtime,zlNo) values(?,?,?)";
		try {
			qr.update(sql, alertM,addtime,zlNo);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



}
