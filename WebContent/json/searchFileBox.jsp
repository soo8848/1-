<%@page import="com.oopsw.model.SearchFileBoxVO"%>
<%@page contentType="application/json; charset=UTF-8" language="java"%>
<%@page
	import="java.util.List, java.util.Map, java.util.ArrayList, java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>

<%
	List<SearchFileBoxVO> searchFileBoxList = (List<SearchFileBoxVO>) request.getAttribute("searchFileBoxList");

	List<Map<String, Object>> searchList = new ArrayList<>();

	for (SearchFileBoxVO vo : searchFileBoxList) {
		Map<String, Object> map = new HashMap<>();

		map.put("projectName", vo.getProjectName());
		map.put("taskName", vo.getTaskName());
		map.put("commentFile", vo.getCommentFile());
		map.put("commentPath", vo.getCommentPath());
		map.put("replyFile", vo.getReplyFile());
		map.put("replyPath", vo.getReplyPath());
		searchList.add(map);
	}
	Gson gson = new Gson();
	String json = gson.toJson(searchList);
	out.print(json);
%>