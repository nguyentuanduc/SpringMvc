<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>User Page</title>
</head>
<body>
  <h1>User Page</h1>
  <h2>Welcome: ${pageContext.request.userPrincipal.name}</h2>
  <a href="<c:url value="/admin" />">Admin Page</a>
  <br/><br/>
  <form action="<c:url value="/j_spring_security_logout" />" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="Logout" />
  </form>
</body>
</html>