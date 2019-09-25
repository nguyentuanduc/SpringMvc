<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/bootstrap.css" />
<title>list all contacts</title>
</head>
<body>
    <table class="table table-hover">
          <tr>
		    <th>Firstname</th>
		    <th>Lastname</th>
		    <th>Age</th>
		  </tr>

    <c:forEach items="${contacts}" var="contact">
        <tr>
            <td>${contact.first_name}</td>
            <td>${contact.last_name}</td>
            <td>${contact.birth_date}</td>
        </tr>
    </c:forEach>

    </table>
</body>
</html>