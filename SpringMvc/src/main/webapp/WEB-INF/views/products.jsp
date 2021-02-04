<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<html>
   <head>   
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
           <title>Products</title>
       <script type="text/javascript" src="${path}/resources/js/jquery.js"></script>
      <link rel="stylesheet" href="${path}/resources/css/bootstrap.css" />
       <script type="text/javascript" src="${path}/resources/js/bootstrap.min.js"></script>
    </head>
   <body>
   <nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/SpringMvc/product/all">Home</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <!-- <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul> -->
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
       <!--  <li><a href="#">Link</a></li> -->
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Hello ${username} <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${path}/logout">Log out</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
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
                  <img src="<c:url value="/resources/img/${product.id}.jpg"> 
                  </c:url>" alt="image" style = "width:100%; height:250px;" />
                     <div class="caption">
                        <h3>${product.name}</h3>
                        <p>${product.description}</p>
                        <p>$${product.unitPrice}</p>
                        <p>Available ${product.unitsInStock} units in stock</p>
                     </div>
                     <p>
                        <a href="${path}/product?id=${product.id}" class="btn  btn-info btn-sm"> <span class="glyphicon-shopping-cart glyphicon"> </span> Detail</a>
                    
                        <a href="${path}/product/update?id=${product.id}" class="btn  btn-primary btn-sm"> <span class="glyphicon-shopping-cart glyphicon"> </span> Update</a>
                    
                        <a href="${path}/product/delete?id=${product.id}" class="btn  btn-danger btn-sm"> <span class="glyphicon-shopping-cart glyphicon"> </span> Delete</a>
                    </p>
                  </div>
                  
               </div>
            </c:forEach>
         </div>
      </section>
       <section>
      </section>



      <script type="text/javascript">
       $(document).ready(function ()
       { $(".caption h3").each(function(i){
              var len=$(this).text().trim().length;
              if(len>16)
              {
                  $(this).text($(this).text().substr(0,16)+'...');
              }
          });
       });
      
      </script>

   </body>
</html>

    