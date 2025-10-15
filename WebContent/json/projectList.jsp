<%@page contentType="application/json; charset=UTF-8" language="java" %>
<%@page import="java.util.List, java.util.Map, java.util.ArrayList, java.util.HashMap" %>
<%@page import="com.google.gson.Gson, com.oopsw.model.ProjectVO" %>

<%
    List<ProjectVO> projectList = (List<ProjectVO>) request.getAttribute("projectList");

    List<Map<String, Object>> projectCardList = new ArrayList<>();
    
    for (ProjectVO vo : projectList) {
        Map<String, Object> map = new HashMap<>();
        map.put("projectNo", vo.getProjectNo());
        map.put("projectName", vo.getProjectName());
        map.put("client", vo.getClient());
        map.put("description", vo.getDescription());
        projectCardList.add(map);
    }
    Gson gson = new Gson();
    String json = gson.toJson(projectCardList);
    out.print(json);
%>