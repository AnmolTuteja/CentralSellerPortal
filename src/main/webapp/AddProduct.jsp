<%@ page import="com.acms.CentralSellerPortal.Controllers.SpringSessionController" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/main.css">



</head>
<body>
<% long id = Long.parseLong(request.getParameter("id")); %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="font-size: medium; background-color: #2A2A2A ; font-family: Ubuntu">
    <div class="container">
        <div class="navbar-header">
            <form class ="form-view" id="view-form-head"   method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <a  type="submit"   href="/SellerDashboard.jsp?id=<%=id%>" style="color: whitesmoke" class="btn"> Central Seller Portal</a>
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
                <h4 class="card-title mt-2">Add New Product</h4>
            </header>
            <article class="card-body">

                <form class ="form-addProduct" id="addProduct-form" action = "/products/postProduct/<%=id%>" method="post">
                    <form :hidden path="id"/>
                    <div class="form-row">
                        <div class="col form-group">
                            <label>Product Name </label>
                            <input type="text" id="p_name" name="p_name" class="form-control" placeholder="">
                        </div> <!-- form-group end.// -->
                        <!-- form-group end.// -->
                    </div> <!-- form-row end.// -->

                    <div class="form-group">
                        <label>Description</label>
                        <input type="text" id="p_description" name="p_description" class="form-control" placeholder="">

                    </div>
                    <!-- form-group end.// -->

                    <div class="form-group">
                        <label>Price</label>
                        <input id="p_price" name="p_price" class="form-control" type="number">
                    </div> <!-- form-group end.// -->

                    <div class="form-group">
                        <label>Discount</label>
                        <input id="p_discount" name="p_discount" class="form-control" type="number">
                    </div> <!-- form-group end.// -->

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Save </button>
                    </div> <!-- form-group// -->

                </form>
            </article> <!-- card-body end .// -->

        </div> <!-- card.// -->
    </div> <!-- col.//-->

</div> <!-- row.//-->


<!--container end.//-->

<br><br>

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>