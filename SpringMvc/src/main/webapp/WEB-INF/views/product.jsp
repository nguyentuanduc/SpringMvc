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
                    <h1>Products</h1> </div>
            </div>
        </section>
        <section class="container">
            <div class="row">
                <div class="col-md-5">
                    <h3>${product.name}</h3>
                    <p><strong>Description</strong> : ${product.description}</p>
                    <p> <strong>Item Code : </strong><span class="label label warning">${product.id} </span>
                    </p>
                    <p> <strong>Publish</strong> :
	                     <c:forEach items="${product.publishs}" var="publish">
	                       ${publish.name}
	                     </c:forEach> 
	                 </p>
                    <p> <strong>Category</strong> : 
                        <c:forEach items="${product.categorys}" var="category">
                           <span class="btn btn-primary"> ${category.category_id}  </span>
                         </c:forEach> 
                    </p>
                    <p>
                        <strong>Available units in stock </strong> : ${product.unitsInStock} </p>
                    <h4> <strong>Unit Price</strong> : ${product.unitPrice} USD</h4>
                    <p>
                        <a href="#" class="btn btn-warning btn-large"> <span class="glyphicon-shopping-cart glyphicon"> </span> Order Now </a>
                    </p>
                </div>
                <div class="col-md-5">
                <div class="thumbnail">
                <img src="${path}/resources/img/${product.id}.jpg" > 
                </div>
                 </div>
            </div>
        </section>
    </body>

    </html>