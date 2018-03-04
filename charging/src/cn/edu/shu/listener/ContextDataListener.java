package cn.edu.shu.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;


import cn.edu.shu.entity.DataBuffer;
import cn.edu.shu.utils.WriteDbUtil;

public class ContextDataListener implements ServletContextAttributeListener {

	public void attributeAdded(ServletContextAttributeEvent arg0) {
		DataBuffer daf=(DataBuffer)arg0.getServletContext().getAttribute("datas");
		WriteDbUtil.writeToDb(daf);
		//System.out.println("====数据缓存创建====");
	}

	public void attributeRemoved(ServletContextAttributeEvent arg0) {
				
	}

	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		DataBuffer daf=(DataBuffer)arg0.getServletContext().getAttribute("datas");
		WriteDbUtil.writeToDb(daf);
		//System.out.println("====数据替换====");
		
	}
	
	

}
