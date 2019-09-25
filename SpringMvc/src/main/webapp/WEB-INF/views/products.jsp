<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link rel="stylesheet" href="${path}/resources/css/bootstrap.css" />
      <title>Products</title>
   </head>
   <body>
      <section>
         <div class="jumbotron">
            <div class="container">
               <h1>Products</h1>
               <p>All the available products in our store</p>
            </div>
         </div>
      </section>
      <section class="container">
         <div class="row">
            <c:forEach items="${products}" var="product">
               <div class="col-sm-6 col-md-3">
                  <div class="thumbnail">
                  <img src="<c:url value="resources/img/${product.id}.png"> 
                  </c:url>" alt="image" style = "width:100%; height:250px;" />
                     <div class="caption">
                        <h3>${product.name}</h3>
                        <p>${product.description}</p>
                        <p>$${product.unitPrice}</p>
                        <p>Available ${product.unitsInStock} units in stock</p>
                     </div>
                     <p>
                        <a href="product?id=${product.id}" class="btn  btn-primary btn-large"> <span class="glyphicon-shopping-cart glyphicon"> </span> Detail</a>
                    </p>
                  </div>
                  
               </div>
            </c:forEach>
         </div>
      </section>
   </body>
</html>

    