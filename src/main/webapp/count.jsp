<%@page import="testBoard.TestDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		TestDAO dao = new TestDAO();
		dao.count( request.getParameter("num") );  
		response.sendRedirect("list.jsp");
	%>

</body>
</html>