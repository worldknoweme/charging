package cn.edu.shu.test;

import org.junit.Test;

import cn.edu.shu.dao.impl.DataAcquisitionDao;
import cn.edu.shu.entity.AlertData;
import cn.edu.shu.entity.Data;
import cn.edu.shu.utils.PageBean;

public class testTearPage {
	/**
	 * test historydatalist
	 */
	@Test
	public void testH5yPage(){
			String start="20161208192905928";
			String end="20161208192906222";
			PageBean<Data> pb=new PageBean<Data>();
			pb.setCurrentPage(2);
			pb.setStartD(start);
			pb.setEndD(end);
			DataAcquisitionDao dao=new DataAcquisitionDao();
			dao.getH5yDataPage(pb);
			System.out.println(pb.getPageData());
			System.out.println(pb.getTotalCount());
			System.out.println(pb.getTotalPage());
	}
//		@Test
		public void testgetBeginCount(){
			 String startD="20161208192906222";
			 String table="historydata";
				DataAcquisitionDao dao=new DataAcquisitionDao();
				int begin=dao.getBeginCount(table, startD);
			System.out.println(begin);
		}
		@Test
		public void testgetTotalCount(){
			String start="20161208192905928";
			String end="20161208192906222";
			 String table="historydata";
				DataAcquisitionDao dao=new DataAcquisitionDao();
				int total=dao.getTotalCount(table, start,end);
			System.out.println(total);
		}
		/**
		 * test to get alertmsg
		 */
		@Test
		public void getHistroyAlert(){
			String start="20160523222034001";
			String end="20160928181256783";
			PageBean<AlertData> pb=new PageBean<AlertData>();
			pb.setPageCount(28);
			pb.setCurrentPage(2);
			pb.setStartD(start);
			pb.setEndD(end);
			DataAcquisitionDao dao=new DataAcquisitionDao();
			dao.getHistroyAlert(pb);
			System.out.println(pb.getPageData());
			System.out.println(pb.getTotalCount());
			System.out.println(pb.getTotalPage());
		}
}
