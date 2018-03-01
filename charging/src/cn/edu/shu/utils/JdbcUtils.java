package cn.edu.shu.utils;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 工具类
 * 1. 初始化C3P0连接池
 * 2. 创建DbUtils核心工具类对象
 *
 */
public class JdbcUtils {

	/**
	 *  1. 初始化C3P0连接池
	 */
	private static  DataSource dataSource;
	static {
//		创建对象后会自动加载src下的配置文件【c3p0-config.xml】
//		dataSource = new ComboPooledDataSource();//加载默认配置
		dataSource=new ComboPooledDataSource("mysql_config");//加载已命名配置
	}
	
	/**
	 * 2. 创建DbUtils核心工具类对象
	 */
	public static QueryRunner getQueryRuner(){
		// 创建QueryRunner对象，传入连接池对象
		// 在创建QueryRunner对象的时候，如果传入了数据源对象；
		// 那么在使用QueryRunner对象方法的时候，就不需要传入连接对象；
		// 会自动从数据源中获取连接(不用关闭连接)
		return new QueryRunner(dataSource);
	}
}








