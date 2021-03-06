package com.sincetimes.website.core.common.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTool {
	public static final int HOUR_SECONDS = 3600;
	public static final int DAY_SECONDS = HOUR_SECONDS * 24;
	public static final int WEEK_SECONDS = DAY_SECONDS * 7;
	
	/**sdf有全局变量线程不安全,用ThreadLocal提供线程安全的sdf*/
	public static final ThreadLocal<DateFormat> SDF = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		}
	};
	
	public static boolean isSameWeek(long a, long b){
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(a);
		Calendar cb = Calendar.getInstance();
		cb.setTimeInMillis(b);
		
		if(ca.get(Calendar.YEAR) != cb.get(Calendar.YEAR)){
			return false;
		}
		return ca.get(Calendar.WEEK_OF_YEAR) == cb.get(Calendar.WEEK_OF_YEAR);
	}
	/**
	 * 汉语习惯周日为一周的最后一天
	 * @param time0
	 * @param time1
	 * @return
	 */
	public static boolean isSameSinoWeek(long time0, long time1){
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(time0);
		ca.add(Calendar.DAY_OF_YEAR, -1);
		Calendar cb = Calendar.getInstance();
		cb.setTimeInMillis(time1);
		cb.add(Calendar.DAY_OF_YEAR, -1);
		if(ca.get(Calendar.YEAR) != cb.get(Calendar.YEAR)){
			return false;
		}
		return ca.get(Calendar.WEEK_OF_YEAR) == cb.get(Calendar.WEEK_OF_YEAR);
	}
	public static boolean isSameDay(long ta, long tb) {
		return formatTime(ta, "yyyyMMdd").equals(formatTime(tb, "yyyyMMdd"));
	}
	public static String formatTime(long timestamp, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date(timestamp));
	}
	public static long parseAndGetTime(String s, String pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(s).getTime();
	}
	/**
	 * formatTime(System.currentTimeMillis(), "yyyy-MM-dd-HH:mm:ss");会new 一个format
	 * @return
	 */
	public static String getLocalTime(){
		return SDF.get().format(new Date());
	}
}
