package cn.edu.shu.dao.impl;

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
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.shu.dao.IDataAcquisitionDao;
import cn.edu.shu.entity.AlertData;
import cn.edu.shu.entity.Data;
import cn.edu.shu.entity.Device;
import cn.edu.shu.entity.RealData;
import cn.edu.shu.entity.User;
import cn.edu.shu.utils.JdbcUtils;
import cn.edu.shu.utils.PageBean;

public class DataAcquisitionDao<T> implements IDataAcquisitionDao {

	private QueryRunner qr = JdbcUtils.getQueryRuner();

	@Override
	/**
	 * 确认报警
	 */
	public void confirmAlert() {
		int id = 1;
		String sql1 = "select id from alertmsg where id in (select max(id)from alertmsg )";//
		String sql2 = "update alertmsg set confirm=1 where id =?";
		try {
			id = qr.query(sql1, new ResultSetHandler<Integer>() {
				@Override
				public Integer handle(ResultSet arg0) throws SQLException {
					if (arg0.next())
						return arg0.getInt("id");
					return null;
				}

			});
			qr.update(sql2, id);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	/**
	 * 获取实时支路数据
	 */
	public RealData getRealTimeData(String deviceID) {

		String sql = "SELECT * FROM currentdata WHERE id IN (SELECT MAX(id) FROM currentdata)";
		//假如设备ID不为空，则需要在查询条件里面加上此条限制
		if(deviceID!=null && !deviceID.equals("")){
			sql ="SELECT * FROM currentdata WHERE id IN (SELECT MAX(id) FROM currentdata where zlNo="+deviceID+")";
		}
		RealData realData = null;
		try {
			realData = qr.query(sql, new BeanHandler<RealData>(RealData.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return realData;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 获取历史支路数据
	 */
	public ArrayList<Data> getHistroyData(String startD, String endD) {
		ArrayList<Data> datas = new ArrayList<Data>();
		String sql = "SELECT * FROM historydata WHERE addtime >=? and addtime <=?";
		try {
			datas = (ArrayList<Data>) qr.query(sql, new BeanListHandler<Data>(
					Data.class), startD, endD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datas;
	}

	@Override
	/**
	 * 存入支路数据
	 */
	public void setHistoryData(Object[][] objArray) {
		String sql = "INSERT INTO historydata (voltage1,current1,current2,addtime,voltage2) VALUES(?,?,?,?,?)";
		try {
			qr.batch(sql, objArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * 验证用户
	 */
	public Map<String, String> checkUser(String username, String password) {
		Map<String, String> map = new HashMap<String, String>();// 标志和权限
		// System.out.println("user"+username);
		String sql1 = "SELECT * FROM userdata WHERE username=? AND password=?";

		try {
			map = qr.query(sql1, new ResultSetHandler<Map<String, String>>() {
				@Override
				public Map<String, String> handle(ResultSet rs)
						throws SQLException {
					Map<String, String> map = new HashMap<String, String>();// 初始化创建对象
					if (rs.next()) {
						int pri = rs.getInt("priority");
						java.util.Date m_date = new java.util.Date();// 系统时间
						String lastTime = m_date.toString();
						String sql2 = "UPDATE userdata SET logontimes =logontimes+1,lasttime=? WHERE username=?";
						qr.update(sql2, lastTime, username);

						map.put("priority", "" + pri);
						map.put("flag", "true");
					} else {
						map.put("priority", "0");
						map.put("flag", "false");
					}
					try {
						if (rs != null) {
							rs.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("close rs or stat failed");
					}

					return map;
				}
			}, username, password);
		} catch (SQLException e) {
			System.err.println("error.CheckUser:" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

	@Override
	/**
	 * 查找最新报警信息
	 */
	public AlertData getNewAlert() {

		AlertData ad = null;
		String sql = "SELECT * FROM alertmsg WHERE id IN (SELECT MAX(id)FROM alertmsg )";
		try {
			ad = qr.query(sql, new BeanHandler<AlertData>(AlertData.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ad;
	}

	@Override
	/**
	 * 更新有效数据
	 */
	public boolean updateRealData(double vol1, double vol2, double cur1,
			double cur2) {
		int id = 1;
		int result = 0;
		// id最大值为2
		String sql1 = "select id from currentdata where id in (select max(id)from currentdata )";//
		String sql2 = "UPDATE currentdata SET voltage1=?,voltage2=?,current1=?,current2=?,re=0 WHERE id=?";

		try {
			id = qr.query(sql1, new ResultSetHandler<Integer>() {
				@Override
				public Integer handle(ResultSet arg0) throws SQLException {
					if (arg0.next())
						return arg0.getInt("id");
					return null;
				}
			});
			result = qr.update(sql2, vol1, vol2, cur1, cur2, id);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (result == 0)
			return false;
		else
			return true;
	}

	@Override
	/**
	 * 修改实时数据将其设置为已记录入报警
	 */
	public boolean setCurrentdata(int id) {
		int result = 0;
		String sql = "update currentdata set re=1 where id=?";
		try {
			result = qr.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result == 0)
			return false;
		else
			return true;
	}

	@Override
	/**
	 * 添加数据入报警数据表
	 */
	public boolean insertIntoAlertmsg(String alertM, String addtime, int zlNo) {

		boolean flag = false;
		// System.out.println("插入报警信息："+alertM);
		String sql = "insert into alertmsg (message,addtime,zlNo) values(?,?,?)";
		try {
			qr.update(sql, alertM, addtime, zlNo);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void getH5yDataPage(PageBean<Data> pb) {
		String startD = pb.getStartD();
		String endD = pb.getEndD();
		ArrayList<Data> datas = new ArrayList<Data>();
		int totalCount = this.getTotalCount("historydata", startD, endD);
		pb.setTotalCount(totalCount);
		// 1=<currentpage<=totalpage
		if (pb.getCurrentPage() > pb.getTotalPage()) {
			pb.setCurrentPage(pb.getTotalPage());
		} else if (pb.getCurrentPage() <= 0) {
			pb.setCurrentPage(1);
		}

		int currentPage = pb.getCurrentPage();
		int index = (currentPage - 1) * pb.getPageCount()
				+ this.getBeginCount("historydata", startD); // 查询的起始行
		int count = pb.getPageCount(); // rows for each page
		if (index < 0) {
			index = this.getBeginCount("historydata", startD);
		}
		String sql = "select * from historydata WHERE addtime >=? AND addtime <=? limit ?,?";
		try {
			datas = (ArrayList<Data>) qr.query(sql, new BeanListHandler<Data>(
					Data.class), startD, endD, index, count);
			pb.setPageData(datas);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * get total record the type should be Long
	 */
	@Override
	public int getTotalCount(String table, String startD, String endD) {
		String sql = "select count(*) from " + table
				+ " WHERE addtime >=? AND addtime <=?";
		try {
			Long count = qr.query(sql, new ScalarHandler<Long>(), startD, endD);
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * get begin record the type should be Integer
	 */
	@Override
	public int getBeginCount(String table, String startD) {
		String sql = "select id from " + table + " WHERE addtime =?";
		// Long count= 0L;
		try {
			Integer count = qr.query(sql, new ScalarHandler<Integer>(), startD);
			if (count != null) {
				return (count.intValue() - 1);
			} else
				return 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	/**
	 * 获取历史报警数据
	 */
	public void getHistroyAlert(PageBean<AlertData> pb) {
		String startD = pb.getStartD();
		String endD = pb.getEndD();
		ArrayList<AlertData> datas = new ArrayList<AlertData>();
		int totalCount = this.getTotalCount("alertmsg", startD, endD);
		pb.setTotalCount(totalCount);
		// 1=<currentpage<=totalpage
		if (pb.getCurrentPage() > pb.getTotalPage()) {
			pb.setCurrentPage(pb.getTotalPage());
		} else if (pb.getCurrentPage() <= 0) {
			pb.setCurrentPage(1);
		}

		int currentPage = pb.getCurrentPage();
		int index = (currentPage - 1) * pb.getPageCount()
				+ this.getBeginCount("alertmsg", startD); // 查询的起始行
		int count = pb.getPageCount(); // rows for each page
		if (index < 0) {
			index = this.getBeginCount("alertmsg", startD);
		}
		String sql = "select * from alertmsg WHERE addtime >=? AND addtime <=? limit ?,?";
		try {
			datas = (ArrayList<AlertData>) qr.query(sql,
					new BeanListHandler<AlertData>(AlertData.class), startD,
					endD, index, count);
			pb.setPageData(datas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * 获取历史报警数据
	 */
	public ArrayList<AlertData> getHistroyAlert(String startD, String endD) {
		List<AlertData> list = null;
		String sql = "SELECT * FROM alertmsg WHERE addtime >=? AND addtime <=?";
		try {
			list = qr.query(sql,
					new BeanListHandler<AlertData>(AlertData.class), startD,
					endD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList<AlertData>) list;
	}

	/**
	 * 根据设备编号和起始号获取数据 起始号为分页时候对应的开始和结束编号
	 */
	@Override
	public List<Device> getDeviceListByCodeAndLimit(String code, int start,
			int end) {
		List<Device> list = null;
		//进行模糊查询 like
		String sql = "SELECT * FROM device WHERE code like ?  limit ?,?";
		if (code == null || code.equals("")) {
			sql = "SELECT * FROM device  limit ?,?";
		}
		try {
			if (code == null || code.equals("")) {
				list = qr.query(sql, new BeanListHandler<Device>(Device.class),
						start, end);
			} else {
				//注意模糊查询的赋值需要放到此处 非sql语句部分
				list = qr.query(sql, new BeanListHandler<Device>(Device.class),
						"%"+code+"%", start, end);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList<Device>) list;
	}

	@Override
	public int countDevice(String code) {
		String sql = "select count(*) from device WHERE code like ?";
		if (code == null || code.equals("")) {
			sql = "select count(*) from device";
		}
		try {
			Long count;
			if (code == null || code.equals("")) {
				count = qr.query(sql, new ScalarHandler<Long>());
			} else {
				count = qr.query(sql, new ScalarHandler<Long>(), "%"+code+"%");
			}

			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void saveOrUpdateDevice(Device device) {
		String sql = "UPDATE device SET code=?,name=?,address=?,person=?,lat=?,lng=?,des=? WHERE id=?";
		try {
			if (device.getId() != 0) {
				//此时数据存在，进行数据的更新
				qr.update(sql,device.getCode(), device.getName(),
						device.getAddress(), device.getPerson(),
						device.getLat(), device.getLng(),device.getDes(),device.getId());
			} else {
				sql = "insert into device(code,name ,address,person,lat,lng,des) values(?,?,?,?,?,?,?)";

				qr.update(sql, device.getCode(), device.getName(),
						device.getAddress(), device.getPerson(),
						device.getLat(), device.getLng(),device.getDes());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Device getDataByID(int id) {
		Device device = null;
		String sql = "SELECT * FROM device WHERE id ="+id;
		try {
			device = qr.query(sql, new BeanHandler<Device>(Device.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return device;
	}

	@Override
	public void delDeviceById(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from device where id ="+id;
		try {
			qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Device> getDeviceAll() {
	
		String	sql = "SELECT * FROM device";

		List<Device> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<Device>(Device.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return (ArrayList<Device>) list;
	}

	@Override
	public List<User> getUserListByUsernameAndLimit(String username,
			int start, int end) {
		List<User> list = null;
		String sql = "SELECT * FROM userdata WHERE username like ?  limit ?,?";
		if (username == null || username.equals("")) {
			sql = "SELECT * FROM userdata  limit ?,?";
		}
		try {
			if (username == null || username.equals("")) {
				list = qr.query(sql, new BeanListHandler<User>(User.class),
						start, end);
			} else {
				list = qr.query(sql, new BeanListHandler<User>(User.class),
						"%"+username+"%", start, end);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList<User>) list;
	}

	@Override
	public int countUser(String username) {
		String sql = "select count(*) from userdata WHERE username like ?";
		if (username == null || username.equals("")) {
			sql = "select count(*) from userdata";
		}
		try {
			Long count;
			if (username == null || username.equals("")) {
				count = qr.query(sql, new ScalarHandler<Long>());
			} else {
				count = qr.query(sql, new ScalarHandler<Long>(), "%"+username+"%");
			}

			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void saveOrUpdateUser(User user) {
		String sql = "UPDATE userdata SET username=?,password=?,realname=?,unit=?,phone=?,email=?,priority=? WHERE Id=?";
		try {
			if (user.getId() != 0) {
				//此时数据存在，进行数据的更新
				qr.update(sql,user.getUsername(), user.getPassword(),
						user.getRealname(), user.getUnit(),
						user.getPhone(), user.getEmail(),user.getPriority(),user.getId());
			} else {
				sql = "insert into userdata(username,password ,realname,unit,phone,email,priority) values(?,?,?,?,?,?,?)";

				qr.update(sql, user.getUsername(), user.getPassword(),
						user.getRealname(), user.getUnit(),
						user.getPhone(), user.getEmail(),user.getPriority());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User getUserByID(int id) {
		User user = null;
		String sql = "SELECT * FROM userdata WHERE id ="+id;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void delUserById(int id) {
		// TODO Auto-generated method stub
				String sql = "delete from userdata where id ="+id;
				try {
					qr.update(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

	@Override
	public List<User> getUserAll() {
		String	sql = "SELECT * FROM userdata";

		List<User> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return (ArrayList<User>) list;
	}

	@Override
	public ArrayList<Data> getHistroyDataByDeviceID(String deviceID) {
		String	sql = "SELECT * FROM historydata where zl="+deviceID;

		List<Data> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<Data>(Data.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return (ArrayList<Data>) list;
	}

	@Override
	public void insertData(String inputVol, String inputCurrent, String outVol,
			String outCurrent, String deviceID) {
		
		String sql = "insert into currentdata(voltage1,current1,voltage2,current2,zlNo) values('"+inputVol+"','"+inputCurrent+"','"+outVol+"','"+outCurrent+"',"+deviceID+")";
		try {
			qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
