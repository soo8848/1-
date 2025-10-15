package com.oopsw.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.model.FileBoxDAO;
import com.oopsw.model.ProjectVO;

public class fileBoxAction implements Action {
	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		FileBoxDAO dao = new FileBoxDAO();
		List<ProjectVO> projectList = dao.getProjects();
		request.setAttribute("projectList", projectList);
		return "Json/projectList.jsp";
	}

}
