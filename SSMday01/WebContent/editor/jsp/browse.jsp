<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.editor.*"%>
<%
	String sAction = request.getParameter("action");
	sAction = sAction.trim().toUpperCase();
	Browse.InitParam(request);
	if (sAction.equalsIgnoreCase("FILE")) {
		out.println(Browse.OutScript(Browse.GetFileList(request)));
	} else {
		sAction = "FOLDER";
		out.println(Browse.OutScript(Browse.GetFolderList()));
	}
%>