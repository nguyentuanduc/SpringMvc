<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link rel="stylesheet" href="../resources/css/bootstrap.css" />
      
      <title>Products</title>
   </head>
   <body>
      <section>
         <div class="jumbotron">
            <div class="container">
               <h1>Products</h1>
               <p>Add products</p>
            </div>
         </div>
      </section>
      <section class="container">
         <form:form method="POST" action="/SpringMvc/product/update" modelAttribute="updateProduct" class="form-horizontal" enctype="multipart/form-data">
            <fieldset>
               <legend>Add new product</legend>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">Name</label> 
                   <div class="col-lg-10">
                     <form:input id="id" path="id" type="hidden" />
                  </div>
                  <div class="col-lg-10">
                     <form:input id="name" path="name" type="text" class="form:input-large"/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">UnitPrice</label> 
                  <div class="col-lg-10">
                     <form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">Manufacturer</label> 
                  <div class="col-lg-10">
                     <form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">Category</label> 
                  <div class="col-lg-10">
                     <form:input id="category" path="category" type="text" class="form:input-large"/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">UnitsInStock</label> 
                  <div class="col-lg-10">
                     <form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large" value=""/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">UnitsInOrder</label> 
                  <div class="col-lg-10">
                     <form:input id="unitsInOrder" path="unitsInOrder" type="text" class="form:input-large" value=""/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="description">Description</label> 
                  <div class="col-lg-10">
                     <form:textarea id="description" path="description" rows = "2"/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="discontinued">Discontinued</label> 
                  <div class="col-lg-10">
                     <form:checkbox id="discontinued" path="discontinued"/>
                  </div>
               </div>
               <div class="form-group">
                  <label class="control-label col-lg-2" for="condition">Condition</label> 
                  <div class="col-lg-10">
                     <form:radiobutton path="condition" value="New" />
                     New 
                     <form:radiobutton path="condition" value="Old" />
                     Old 
                     <form:radiobutton path="condition" value="Refurbished" />
                     Refurbished 
                  </div>
               </div>
               <div class="form-group">
                     <label class="control-label col-lg-2" for="productImage">
                        product image
                     </label>
                     <div class="col-lg-10">
                           <form:input id="productImage" path="productImage" type="file" class="form:input-large" />
                     </div>
               </div>
               <div class="form-group">
                  <div class="col-lg-offset-2 col-lg-10"> <input type="submit" id="btnAdd" class="btn btn-primary" value ="update"/> </div>
               </div>
            </fieldset>
         </form:form>
      </section>
   </body>
</html>

