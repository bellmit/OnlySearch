package application.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	//获取时间
	public static String getTime(long times){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时MM分ss秒");  
        Date date = new Date(times);
        return sdf.format(date);
	}
	
	//获取年月日
	public static String getYear_Month_Day(long times){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");  
        Date date = new Date(times);
        return sdf.format(date);
	}
}
