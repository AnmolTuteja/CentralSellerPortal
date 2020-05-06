<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="../../css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="../../css/main.css">
    <script src="../../js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    <title>Update Seller Profile</title>
    <style>
        body{
            font-family: Ubuntu;
        }
    </style>
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

                <h4 class="card-title mt-2">Update Seller Profile</h4>
            </header>
            <article class="card-body">

                <form class ="form-update" id="updation-form" action = "/postUpdatedSeller/<%=id%>" method="post">
                    <form :hidden path="id"/>
                    <div class="form-row">
                        <div class="col form-group">
                            <label>Name </label>
                            <input type="text" id="s_name" name="s_name" class="form-control" value=${sellerName} placeholder=${sellerName}>
                        </div> <!-- form-group end.// -->
                        <!-- form-group end.// -->
                    </div> <!-- form-row end.// -->
                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" id="s_address" name="s_address"  class="form-control" value=${sellerAddress} placeholder=${sellerAddress}>

                    </div>
                    <div class="form-group">
                        <label>Mobile No.</label>
                        <input type="number" id="s_mobile" name="s_mobile"  class="form-control" value=${sellerContactNo} placeholder=${sellerContactNo}>

                    </div>
                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" id="s_mail" name="s_mail" class="form-control"  value= ${sellerEmailId} placeholder=${sellerEmailId}>
                    </div> <!-- form-group end.// -->
                    <div class="form-group">
                        <label>Store Name</label>
                        <input type="text" id="s_shop" name="s_shop"  class="form-control" value=${shopName} placeholder=${shopName}>

                    </div>


                    <div class="form-group">
                        <label>Password</label>
                        <input class="form-control" id="s_password" name="s_password"  value=${sellerPassword} placeholder=${sellerPassword} type="password" >
                    </div> <!-- form-group end.// -->

                    <br>
                    <div class="form-group">
                        <button  type="submit"  class="btn btn-primary btn-block"> Save Changes</button>
                    </div> <!-- form-group// -->

                </form>
            </article> <!-- card-body end .// -->
            <!--<div class="border-top card-body text-center">Have an account? <a href="">Log In</a></div>-->
        </div> <!-- card.// -->
    </div> <!-- col.//-->

</div> <!-- row.//-->

<br><br>

</body>
</html>