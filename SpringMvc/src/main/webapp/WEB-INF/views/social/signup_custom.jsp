<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link rel="stylesheet" href="resources/css/bootstrap.css" />
      <title>Sign up social</title>
   </head>
   <body>
      <section>
         <div class="jumbotron">
            <div class="container">
               <h1>Register</h1>
               <p>Sign up with Google</p>
            </div>
         </div>
      </section>
      <section class="container">
         <form:form method="POST"  modelAttribute="myForm" class="form-horizontal" enctype="multipart/form-data">
            <fieldset>
            <form:hidden path="id" />
            <form:hidden path="signInProvider" />  
               <legend>Register new account</legend>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">User Name</label> 
                  <div class="col-lg-10">
                     <form:input id="username" path="userName" type="text" class="form:input-large"/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">Email</label> 
                  <div class="col-lg-10">
                     <form:input id="email" path="email" type="text" class="form:input-large"/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">First Name</label> 
                  <div class="col-lg-10">
                     <form:input id="firstname" path="firstName" type="text" class="form:input-large"/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">Last Name</label> 
                  <div class="col-lg-10">
                     <form:input id="lastname" path="lastName" type="text" class="form:input-large"/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">Password</label> 
                  <div class="col-lg-10">
                     <form:input id="password" path="password" type="text" class="form:input-large"/>
                  </div>
               </div>
               
               <div class="form-group">
                  <div class="col-lg-offset-2 col-lg-10"> <input type="submit" id="btnAdd" class="btn btn-primary" value ="Register"/> </div>
               </div>
            </fieldset>
         </form:form>
      </section>
   </body>
</html>

