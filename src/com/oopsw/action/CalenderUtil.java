//package com.oopsw.action;
//
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CalenderUtil {
//	public static Map<String, Object> getCalendar(int year, int month) {
//		int mondayStartDayOfWeek = 0;
//		Map<String, Object> map = new HashMap<>();
//		
//		Calendar cal = Calendar.getInstance(); // 오늘 날짜가 들어가 있음.
//		cal.set(year, month -1, 1);
//		
//		int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
//		
//		if(startDayOfWeek == 1) {
//			mondayStartDayOfWeek = 7;
//		} else {
//			mondayStartDayOfWeek = startDayOfWeek -1;
//		}
//		
//		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//		
//		
//		map.put("mondayStartDayOfWeek", mondayStartDayOfWeek);
//		map.put("lastDay", lastDay);
//		map.put("year", year);
//		map.put("month", month);
//		
//		return map;
//	}
//}
