<%@ page contentType="application/json; charset=UTF-8" %>
<%@ page import="com.google.gson.Gson, java.util.*" %>
<%@ page import="com.oopsw.model.ScheduleVO" %>

<%
    List<ScheduleVO> scheduleList = (List<ScheduleVO>) request.getAttribute("scheduleList");
    String json = new Gson().toJson(scheduleList);
    out.print(json);
%> 