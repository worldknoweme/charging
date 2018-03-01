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

public class RealTimePanelApplet extends Applet implements Runnable{
	private static final long serialVersionUID = 1L;
	Thread t2;
	private XYSeries xySeries;
	private XYSeries xySeries2;
	private double RealCurrent1=0;
	private double RealCurrent2=0;
	private boolean runFlag;
	 ArrayList<Double> data3=new ArrayList<Double>();
	 ArrayList<Double> data4=new ArrayList<Double>();
	
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
		data4.clear();
		 data3.clear();
	}

/**构建JFreeChart对象*/
	JFreeChart createChart(){
		xySeries=new XYSeries("电压1:输入");
		xySeries2=new XYSeries("电压2:输出");
		//xySeries.setMaximumItemCount(100);//自动清除旧数据，防止内存泄露
		//xySeries2.setMaximumItemCount(100);//
		//getRealData();
		
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xySeries);
		xyseriescollection.addSeries(xySeries2);
		JFreeChart jfreechart=ChartFactory.createXYLineChart("实时电压对比图", "时间（秒）", "电压(v)", xyseriescollection, PlotOrientation.VERTICAL,true, true, false);
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
					Thread.sleep(310);//////睡眠1ms	
					} 
			  
				catch (InterruptedException e) {
					e.printStackTrace();
					runFlag=false;
					System.out.println("closed");
				}finally{
//					xySeries.clear();
//				xySeries2.clear();
				}
		}
	}	
	
	/**终止线程*/
	public void stopThread(){
		runFlag=false;
		data4.clear();
		 data3.clear();
	}
	
	/**取得实时数据的线程
	 * @throws ClassNotFoundException */
	//private class getRealData implements Runnable
	public void getRealData() throws ClassNotFoundException{
		double voltage1=0;
		double voltage2=0;
		double uint=0;
	    //public void run() 
		try {     
		
			  String url = "http://intellcontrol.shu.edu.cn/charging/RemoteServlet";
			  URL servletURL = new URL(url);
			  URLConnection servletConnection= servletURL.openConnection();  
			  InputStream in=servletConnection.getInputStream();
			  ObjectInputStream ois2=new ObjectInputStream(in);
			
				  data3=(ArrayList<Double>) ois2.readObject();
				  data4=(ArrayList<Double>) ois2.readObject();
		
				  ois2.close();
				  ois2.close();
				  in.close();
		       } catch (IOException e) {
			  e.printStackTrace();  
		       }
			   	    	
	    	//System.out.println("getrealdata");
		for(int i=0;i<data3.size();i++){
			
		    voltage1= data3.get(i);
			voltage2= data4.get(i);
			xySeries.addOrUpdate(new Double(uint), new Double(voltage1));
			xySeries2.addOrUpdate(new Double(uint), new Double(voltage2));
			uint+=0.1;
		}
			
			
	 }
}
