package cn.edu.shu.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.RectangleInsets;

import cn.edu.shu.entity.Data;


public class HistoryCurrentApplet extends Applet {
	
	private static final long serialVersionUID = 1L;
	private String startD="";
	private String endD="";
	private String pageCount="";
	private String currentPage="";
	private XYSeries xySeries;
	private XYSeries xySeries2;
	private ArrayList<Data> datas=null;
	
	public void init() {
				startD=getParameter("startD");
				endD=getParameter("endD");
				pageCount=getParameter("pageCount");
				currentPage=getParameter("currentPage");
				ChartPanel cp=new ChartPanel(createChart());//先添加数据  再画图		
				
				cp.setPreferredSize(new Dimension(600, 270));
		        setLayout(new BorderLayout());
		        add(cp,BorderLayout.CENTER) ;
		 }
	public void start(){
	}
	public void destroy(){
		xySeries.clear();
		xySeries2.clear();
		datas.clear();
	}

	private JFreeChart createChart(){
		xySeries = new XYSeries("电流1:输入");
		xySeries2 = new XYSeries("电流2:输出");
		
		try {
			addHistoryData();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}//添加历史数据/
		
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xySeries);
		xyseriescollection.addSeries(xySeries2);
		JFreeChart jfreechart = ChartFactory.createXYLineChart("电流对比图","时间","电流（A）",xyseriescollection,PlotOrientation.VERTICAL,true,true,false);
		jfreechart.setBackgroundPaint(Color.white);
		
		
		XYPlot xyplot = (XYPlot)jfreechart.getPlot(); 
		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setAxisOffset(new RectangleInsets(5.0D,5.0D,5.0D,5.0D));
		xyplot.setDomainGridlinePaint(Color.white);
		//xyplot.setDomainGridlinesVisible(false);
		//xyplot.setRangeGridlinesVisible(false);
		xyplot.setRangeGridlinePaint(Color.white);
		
		
		XYLineAndShapeRenderer xylasr=(XYLineAndShapeRenderer)xyplot.getRenderer();
		xylasr.setBaseShapesVisible(false);//节点是否显示
		//xylasr.setBaseShapesFilled(true);
		
		ValueAxis valueAxis=xyplot.getDomainAxis();
		valueAxis.setAutoRange(true);  //自动设置数据轴数据范围
		valueAxis=xyplot.getRangeAxis();
		valueAxis.setAutoRange(true);
		
//		NumberAxis numberaxis =(NumberAxis) xyplot.getRangeAxis();
//		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return jfreechart;	
		
	}
	private void addHistoryData() throws ClassNotFoundException {//////
		double value1=0;//历史电流1
		double value2=0;//历史电流2
		double i=0.0;
		try {     ////待修改，192.168.1.100服务器的地址10.89.35.157   10.99.39.68?startD="+startD+"&endD="+endD     
			  String url = "http://intellcontrol.shu.edu.cn/charging/HistoryCurServlet?startD="+startD+"&endD="+endD+"&pageCount="+pageCount+"&currentPage="+currentPage;
			  //String ul="./DiscussServlet?action=diss&type="+type+"&id="+id+"&contents="+contents;
			  URL servletURL = new URL(url);
			  URLConnection servletConnection= servletURL.openConnection();  
			  //servletConnection.setDoOutput(true);		
				//BufferedInputStream bis=new BufferedInputStream(servletConnection.getInputStream());
			  InputStream in=servletConnection.getInputStream();
				ObjectInputStream ois=new ObjectInputStream(in);
			     datas=(ArrayList<Data>) ois.readObject();
			    // System.out.println(ois.readObject());
			     ois.close();
			     in.close();
			     //datas.clear();
		 } catch (IOException e) {
		  e.printStackTrace();
		 }
		
		if(datas!=null){
		for(Data d:datas){
			/*value1=d.getCurrent1();
			value2=d.getCurrent2();*/
			value1=d.getCurrent2();
			value2=d.getCurrent1();
			
			xySeries.add(new Double(i), new Double(value1));
			xySeries2.add(new Double(i), new Double(value2));
			i+=0.1;
			//
		}
		
		}
		else{
			System.out.println(-1);
			datas.clear();
		}
		
	}
}
