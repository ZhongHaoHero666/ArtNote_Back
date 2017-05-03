<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User list</title>
</head>
<body>
  	<h3>UserList</h3>
 
	<table border="1" width="70%">
   		<tr>
   			<td>Id</td>
   			<td>Name</td>
   			<td>Age</td>
   			<td>Address</td>
   			
   		</tr>
   		<c:forEach items="${userlist}" var="user">
   		<tr>
   			<td>${user.id }</td>
   			<td>${user.name }</td>
   			<td>${user.age }</td>
   			<td>${user.address }</td>
   		
   		</tr>
   		</c:forEach>
   </table>
   
</body>
</html>