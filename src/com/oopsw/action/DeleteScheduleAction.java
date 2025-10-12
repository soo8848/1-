package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.model.ScheduleVO;

public class DeleteScheduleAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		// HttpSession session = request.getSession();
		// String employeeId = (String) session.getAttribute("employeeId");
		String scheduleNoStr = request.getParameter("scheduleNo");


		int scheduleNo = Integer.parseInt(scheduleNoStr);
		
	    ScheduleDAO dao = new ScheduleDAO();
	    boolean result = dao.deleteSchedule(scheduleNo);

		request.setAttribute("result", result);
		return "Json/scheduleReultCheck.jsp";
	}

}
