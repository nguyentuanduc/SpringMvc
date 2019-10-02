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
                    <p>${product.description}</p>
                    <p> <strong>Item Code : </strong><span class="label label warning">${product.id} </span>
                    </p>
                    <p> <strong>manufacturer</strong> : ${product.manufacturer} </p>
                    <p> <strong>category</strong> : ${product.category} </p>
                    <p>
                        <strong>Availble units in stock </strong> : ${product.unitsInStock} </p>
                    <h4>${product.unitPrice} USD</h4>
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