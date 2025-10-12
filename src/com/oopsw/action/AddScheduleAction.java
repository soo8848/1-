package com.oopsw.action;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.model.ScheduleVO;

public class AddScheduleAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		// HttpSession session = request.getSession();
		// String employeeId = (String) session.getAttribute("employeeId");
		String employeeId = "1004014";
		String date = request.getParameter("scheduleDate"); // "2025-10-13"
		String scheduleType = request.getParameter("scheduleType"); // "red"
		String title = request.getParameter("title"); // "회의"
		String start = request.getParameter("startTime"); // "09:30"
		String end = request.getParameter("endTime"); // "11:00"
		
		System.out.println(date);
		System.out.println(scheduleType);
		System.out.println(title);
		System.out.println(start);
		System.out.println(end);

		Date scheduleDate = Date.valueOf(date);
		Timestamp startTime = Timestamp.valueOf(date + " " + start + ":00");
		Timestamp endTime = Timestamp.valueOf(date + " " + end + ":00");

		boolean result = false;
		try {
			new ScheduleDAO()
					.addSchedule(new ScheduleVO(employeeId, scheduleDate, scheduleType, title, startTime, endTime));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}

		// response.setContentType("text/plain; charset=UTF-8"); <- Action인터페이스에
		// response사용을 추가해야되기 때문에 Post로 하는건 불가능
		// response.getWriter().print(result ? "OK" : "FAIL");
		request.setAttribute("result", result);
		return "Json/scheduleReultCheck.jsp"; // 메모리로 넘어가는건 무조건 jsp로 만들어줘야됨. <- 이게 없으면 브라우저가 성공 여부를 파악할 수 없음.

	}
}
