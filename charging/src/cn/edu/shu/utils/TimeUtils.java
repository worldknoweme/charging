package cn.edu.shu.utils;

import java.util.Calendar;

public class TimeUtils {
	public static String getTime() {
		String endD;
		Calendar c= Calendar.getInstance();////
		int yeare=c.get(Calendar.YEAR);
			endD=""+yeare;
		int monthe=c.get(Calendar.MONTH)+1;//月份0-11月
		if(monthe<=9)
			endD=endD+"0";
		endD=endD+monthe;
		int daye=c.get(Calendar.DATE);
		if(daye<=9)
			endD=endD+"0";
			endD=endD+daye;
		int houre=c.get(Calendar.HOUR_OF_DAY);
		if(houre<=9)
			endD=endD+"0";
			endD=endD+houre;
		int minutee=c.get(Calendar.MINUTE);
		if(minutee<=9)
			endD=endD+"0";
			endD=endD+minutee;
		int seconde=c.get(Calendar.SECOND);
		if(seconde<=9)
			endD=endD+"0";
			endD=endD+seconde;
		int milliseconde=c.get(Calendar.MILLISECOND);
		if(milliseconde<=9)
			endD=endD+"00";
		if(milliseconde>=10&&milliseconde<=99)
			endD=endD+"0";
			endD=endD+milliseconde;
		return endD;
	}
}
