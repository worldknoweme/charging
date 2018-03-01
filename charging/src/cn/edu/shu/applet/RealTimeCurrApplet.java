package cn.edu.shu.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.*;
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


public class RealTimeCurrApplet extends Applet implements Runnable{
	private static final long serialVersionUID = 1L;
	Thread t2;
	private XYSeries xySeries;
	private XYSeries xySeries2;
	private double RealCurrent1=0;
	private double RealCurrent2=0;
	private boolean runFlag;
	 private Socket socket=null;
	ArrayList<Double> data1=new ArrayList<Double>();
	 ArrayList<Double> data2=new ArrayList<Double>();
	
	public void init(){
		ChartPanel cp=new ChartPanel(this.createChart());	
		cp.setPreferredSize(new java.awt.Dimension(600, 270));
        setLayout(new BorderLayout());
        add(cp,BorderLayout.CENTER);
        runFlag=true;
	    t2=new Thread(this);
		t2.start(); 
	}

	

	public void start() {
		super.start();
	}


	public void destroy(){
		xySeries.clear();
		xySeries2.clear();
		runFlag=false;
		data2.clear();
		 data1.clear();
	}

/**构建JFreeChart对象*/
	JFreeChart createChart(){
		xySeries=new XYSeries("电流1:输入");
		xySeries2=new XYSeries("电流2:输出");
		//xySeries.setMaximumItemCount(100);//自动清除旧数据，防止内存泄露
		//xySeries2.setMaximumItemCount(100);//
		//getRealData();
		
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xySeries);
		xyseriescollection.addSeries(xySeries2);
		JFreeChart jfreechart=ChartFactory.createXYLineChart("实时电流对比图", "时间（秒）", "电流(A)", xyseriescollection, PlotOrientation.VERTICAL,true, true, false);
		jfreechart.setBackgroundPaint(Color.white);
		
		XYPlot xyplot = (XYPlot)jfreechart.getPlot(); //获得 plot : XYPlot!!
		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setAxisOffset(new RectangleInsets(5.0D,5.0D,5.0D,5.0D));
		xyplot.setDomainGridlinePaint(Color.white);
		xyplot.setRangeGridlinePaint(Color.white);
		
		XYLineAndShapeRenderer xylasr=(XYLineAndShapeRenderer)xyplot.getRenderer();
		xylasr.setBaseShapesVisible(false);//节点是否显示
		
		
		ValueAxis valueaxs=xyplot.getDomainAxis();
		valueaxs.setAutoRange(true);
		valueaxs=xyplot.getRangeAxis();
		valueaxs.setAutoRange(true);
		return jfreechart;
	}


	/**运行此线程*/
	public void run() {	
		
		while(runFlag){
			
			try {
				getRealData();
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			}
		    
			
			  try {
				  //System.out.println("sleep5000ms");
					Thread.sleep(300);//////睡眠1ms	
					} 
			  
				catch (InterruptedException e) {
					e.printStackTrace();
					runFlag=false;
					System.out.println("closed");
				}finally{
//				xySeries.clear();
//					xySeries2.clear();
				}
		}
	}	
	
	/**终止线程*/
	public void stopThread(){
		runFlag=false;
		data2.clear();
		 data1.clear();
	}
	
	/**取得实时数据的线程
	 * @throws ClassNotFoundException */
	//private class getRealData implements Runnable
	public void getRealData() throws ClassNotFoundException{
		double current1=0;
		double current2=0;
		double uint=0.0;
	    //public void run() 
		try {     
		
			  String url = "http://intellcontrol.shu.edu.cn/charging/RealTimeCurrServlet";////待修改，192.168.1.100   10.99.39.68  ?startD="+startD+"&endD="+endD
			  URL servletURL = new URL(url);
			  URLConnection servletConnection= servletURL.openConnection();  
			  InputStream in=servletConnection.getInputStream();
			  ObjectInputStream ois2=new ObjectInputStream(in);
			
			  data1=(ArrayList<Double>) ois2.readObject();
			  data2=(ArrayList<Double>) ois2.readObject();
				  ois2.close();
				  ois2.close();
				  in.close();
		       } catch (IOException e) {
			  e.printStackTrace();  
		       }
		 uint=0.0;	
	    	//System.out.println("getrealdata");
		 for(int i=0;i<data1.size();i++){
				
			    current1= data1.get(i);
				current2= data2.get(i);
				xySeries.addOrUpdate(new Double(uint), new Double(current1));
				xySeries2.addOrUpdate(new Double(uint), new Double(current2));
				uint+=0.1;
			}
			
	 }
}
