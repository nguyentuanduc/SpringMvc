<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link rel="stylesheet" href="${path}/resources/css/bootstrap.css" />
      <title>User information</title>
   </head>

    <body>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>User information</h1> </div>
            </div>
        </section>
        <section class="container">
            <div class="row">
                <div class="col-md-5">
                    <h3> <strong> User Name </strong>: ${userInfo.userName}</h3>
                    <p> <strong> First Name </strong>: ${userInfo.firstName} </p>
                    <p> <strong> Last Name </strong>: ${userInfo.lastName} </p>
                    <p> <strong>Email</strong> : ${userInfo.email} </p>
                    <p>
                        <a href="#" class="btn btn-warning btn-large"> <span class="glyphicon-shopping-cart glyphicon"> </span> Update user </a>
                    </p>
                </div>
                
            </div>
        </section>
    </body>

    </html>