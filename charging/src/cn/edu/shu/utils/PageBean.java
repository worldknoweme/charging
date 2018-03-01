package cn.edu.shu.utils;

import java.util.List;

/**
 * encapsulate paging parameters
 * 
 */
public class PageBean<T> {
	private int currentPage = 1; // current page or default page
	private int pageCount = 1000;   // number of every row
	private int totalCount;      //  total record
	private int totalPage;       //  totalCount/pageCount(or +1)
	private List<T> pageData;       // data for searching
	private String startD;
	private String code;//对应设备维护的设备编号
	public String getStartD() {
		return startD;
	}
	public void setStartD(String startD) {
		this.startD = startD;
	}
	public String getEndD() {
		return endD;
	}
	public void setEndD(String endD) {
		this.endD = endD;
	}
	private String endD;
	
	public int getTotalPage() {
		if (totalCount % pageCount == 0) {
			totalPage = totalCount / pageCount;
		} else {
			totalPage = totalCount / pageCount + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
