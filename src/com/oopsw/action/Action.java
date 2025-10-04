package com.oopsw.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface Action {
	/**사용자 입력한 모든 정보를 전달, 실행 결과 이동할 페이지 리턴 */
	String execute(HttpServletRequest request)
			throws ServletException, IOException;
}
