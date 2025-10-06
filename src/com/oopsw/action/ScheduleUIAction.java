package com.oopsw.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class ScheduleUIAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String url = "schedule.jsp";
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH)+1;
		
		if (request.getParameter("year") != null)
			year = Integer.parseInt(request.getParameter("year"));
		
		if (request.getParameter("month") != null)
			month = Integer.parseInt(request.getParameter("month"));
		
	     Map<String, Object> calendarMap = CalenderUtil.getCalendar(year, month);
	     
	     int prevYear = year;
	     int prevMonth = month - 1;
	     if (prevMonth == 0) {
	         prevMonth = 12;
	         prevYear--;
	     }

	     int nextYear = year;
	     int nextMonth = month + 1;
	     if (nextMonth == 13) {
	         nextMonth = 1;
	         nextYear++;
	     }

	     calendarMap.put("prevYear", prevYear);
	     calendarMap.put("prevMonth", prevMonth);
	     calendarMap.put("nextYear", nextYear);
	     calendarMap.put("nextMonth", nextMonth);
	     
	        request.setAttribute("calendar", calendarMap);

		
		return url;
	}

}
