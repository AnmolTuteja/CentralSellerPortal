<%@ page import="com.acms.CentralSellerPortal.Controllers.SpringSessionController" %>
<%@ page import="com.acms.CentralSellerPortal.Entities.Seller" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.acms.CentralSellerPortal.Entities.Product" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
<%@ page import="com.acms.CentralSellerPortal.Controllers.SellerController" %>
<%@ page import="com.acms.CentralSellerPortal.Controllers.ProductController" %>
<%@ page import="org.springframework.web.bind.annotation.RequestMapping" %>
<%@ page import="java.util.List" %>
<%--<%@ page import="org.springframework.web.bind.annotation.RequestBody" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Seller Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="../css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="../css/main.css">

    <%--    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>

</head>
<body>
<% long id = Long.parseLong(request.getParameter("id")); %>
<% long p_id = Long.parseLong(request.getParameter("p_id")); %>
<% String p_name = request.getParameter("p_name"); %>
<% String p_desc = request.getParameter("p_desc"); %>
<% long p_disc= Long.parseLong(request.getParameter("p_disc")); %>
<% long p_price = Long.parseLong(request.getParameter("p_price")); %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="font-size: medium; background-color: #2A2A2A ; font-family: Ubuntu">
    <div class="container">
        <div class="navbar-header">
            <form class ="form-view" id="view-form-head"   method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <a  type="submit"  href="/SellerDashboard.jsp?id=<%=id%>" style="color: whitesmoke" class="btn"> Central Seller Portal</a>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
        <div class="navbar-header">
            <form class ="form-view" id="view-form-viewprofile"  action = "/getSellerById/<%=id%>" method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <button id = "view-profile" type="submit"   style="color: whitesmoke; background-color: #2A2A2A" class="btn"> View Your Profile</button>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
        <div class="navbar-header">
            <form class ="form-view" id="view-form-updateseller"  method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <a class="btn" href="/UpdateSellerProfile.jsp?id=<%=id%>" style="color: whitesmoke; background-color: #2A2A2A" action="UpdateSellerProfile.jsp" role="button">Update Profile</a>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
        <div class="navbar-header">
            <form class ="form-view" id="view-form-addproduct"   method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <a  id = "view-profile-addproduct" type="submit" href="/AddProduct.jsp?id=<%=id%>"  action="AddProduct.jsp"  style="color: whitesmoke; background-color: #2A2A2A" class="btn"> Add Product</a>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
        <div class="navbar-header">
            <form class ="form-view" id="view-form-viewproduct"  action = "/products/getBySellerId/<%=id%>" method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <button  id = "view-profile-viewproduct" type="submit"  style="color: whitesmoke; background-color: #2A2A2A" class="btn"> View Products</button>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
        <div class="navbar-header">
            <form class ="form-view" id="view-form" action="/destroy" method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <button class="btn" href="/index.html" type="submit" style="color: whitesmoke; background-color: #2A2A2A" role="button">Logout</button>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
    </div>
</nav>
<br>

<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="card">
            <header class="card-header">

                <h4 class="card-title mt-2">Edit Product Details</h4>
            </header>
            <article class="card-body">

                <form class ="form-editProduct" id="editProduct-form" action = "/products/postUpdatedProduct/<%=p_id%>/<%=id%>" method="post">
                    <form :hidden path="p_id"/>
                    <div class="form-row">
                        <div class="col form-group">
                            <label>Name </label>
                            <input type="text" id="ep_name" name="ep_name" class="form-control"  value="<%=p_name%>">
                        </div> <!-- form-group end.// -->
                        <!-- form-group end.// -->
                    </div> <!-- form-row end.// -->
                    <div class="form-group">
                        <label>Description</label>
                        <input type="text" id="ep_description" name="ep_description" class="form-control" value="<%=p_desc%>">

                    </div>

                    <!-- form-group end.// -->
                    <div class="form-group">
                        <label>Price</label>
                        <input id="ep_price" name="ep_price" type="text" class="form-control" value="<%=p_price%>">

                    </div>


                    <div class="form-group">
                        <label>Discount</label>
                        <input class="form-control"  id="ep_discount" name="ep_discount" type="number" value="<%=p_disc%>">
                    </div> <!-- form-group end.// -->

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Save Changes</button>
                    </div> <!-- form-group// -->

                </form>
            </article> <!-- card-body end .// -->
            <!--<div class="border-top card-body text-center">Have an account? <a href="">Log In</a></div>-->
        </div> <!-- card.// -->
    </div> <!-- col.//-->

</div> <!-- row.//-->



<!--container end.//-->





<br>


</body>
</html>