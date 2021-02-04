<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Admin Page</title>
</head>
<body>
  <h1>Admin Page</h1>
  <h2>Welcome: ${pageContext.request.userPrincipal.name}</h2>
  <a href="<c:url value="/user" />">User Page</a>
  <br/><br/>
  <form action="<c:url value="/j_spring_security_logout" />" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="Logout" />
  </form>
</body>
</html>