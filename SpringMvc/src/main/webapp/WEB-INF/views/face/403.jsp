<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>403</title>
</head>
<body>
  <h1>403</h1>
  <span>Hi: ${pageContext.request.userPrincipal.name} you do not have permission to access this page</span>
  <a href="<c:url value="/user" />">User Page</a>
  <br/><br/>
  <form action="<c:url value="/j_spring_security_logout" />" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="Logout" />
  </form>
</body>
</html>