<%@ page import="com.acms.CentralSellerPortal.Entities.Seller" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
<%@ page import="com.acms.CentralSellerPortal.Controllers.SellerController" %>
<%@ page import="org.springframework.web.bind.annotation.RequestMapping" %>
<%--<%@ page import="org.springframework.web.bind.annotation.RequestBody" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listed Products</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="../css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="../css/main.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
    <style>
        body{
            font-family: Ubuntu;
        }
    </style>


</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" method="get" style="color:gray ; font-size: 18px">Central Seller Portal</a>
        </div>
    </div>
</nav>
<br>
<div class="container">
    <div class="row">


        <div class="col-md-12">
            <h4>${seller_name}, Here are your Listed Products</h4>
            <div class="table-responsive">


                <table id="mytable" class="table table-bordred table-striped">

                    <thead>


                    <th>ProductID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price(Rs.)</th>
                    <th>Discount(%)</th>
                    <th>Edit</th>
                    <th>Delete</th>
                    </thead>
                    <tbody>

                    <tr>
                        <!--                            <td><input type="checkbox" class="checkthis" /></td>-->
                        <td>1</td>
                        <td>Ball</td>
                        <td>Sport Equipment</td>
                        <td>30</td>
                        <td>5</td>
                        <td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" ><i class="fas fa-pencil-square-o"></i></button></p></td>
                        <td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><i class="fas fa-trash"></i></button></p></td>
                    </tr>

                    </tbody>

                </table>

                <div class="clearfix"></div>

            </div>

        </div>
    </div>
</div>
<dev class="container">
    <a id="add_row" class="btn btn-primary float-right" style="color: white; margin-right: 30px ;margin-bottom: 30px ; margin-top: 10px">Add Product</a>
</dev>
<br>

</body>
</html>