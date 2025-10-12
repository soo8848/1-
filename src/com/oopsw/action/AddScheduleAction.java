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
		String title = request.getParameter("title"); // "ȸ��"
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

		// response.setContentType("text/plain; charset=UTF-8"); <- Action�������̽���
		// response����� �߰��ؾߵǱ� ������ Post�� �ϴ°� �Ұ���
		// response.getWriter().print(result ? "OK" : "FAIL");
		request.setAttribute("result", result);
		return "Json/scheduleReultCheck.jsp"; // �޸𸮷� �Ѿ�°� ������ jsp�� �������ߵ�. <- �̰� ������ �������� ���� ���θ� �ľ��� �� ����.

	}
}
