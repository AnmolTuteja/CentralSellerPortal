<%@ page import="com.acms.CentralSellerPortal.Controllers.SpringSessionController" %>
<%@ page import="com.acms.CentralSellerPortal.Entities.Seller" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.acms.CentralSellerPortal.Entities.Product" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
<%@ page import="com.acms.CentralSellerPortal.Controllers.SellerController" %>
<%@ page import="com.acms.CentralSellerPortal.Controllers.ProductController" %>
<%@ page import="org.springframework.web.bind.annotation.RequestMapping" %>
<%--<%@ page import="org.springframework.web.bind.annotation.RequestBody" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Sellers</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="../css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="../css/main.css">

    <%--    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
    <style>
        body{
            font-family: Ubuntu;
            font-size: medium;
        }
    </style>

</head>
<body>
<% long e_id = Long.parseLong(request.getParameter("e_id")); %>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="font-size: large; background-color: #2A2A2A ; font-family: Ubuntu">
    <div class="container" style="margin-left: 0px; margin-right: 10px; margin-bottom: 3px" >

        <div class="navbar-header">
            <form class ="form-view" id="view-form-head"   method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <a  type="submit"  href="/EcommDashboard.jsp?e_id=<%=e_id%>" style=" margin-right:40px ;margin-left:0px;margin-top:5px;font-size: medium;color: whitesmoke" class="btn"> Central Seller Portal</a>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
        <div class="navbar-header">
            <form class ="form-view" id="view-form-viewprofile"  action = "/ecomm/viewEcommerce/<%=e_id%>" method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <button id = "view-profile" type="submit"   style="margin-right:40px ;margin-left:25px;margin-top:5px;font-size: medium;color: whitesmoke; background-color: #2A2A2A" class="btn"> View Your Profile</button>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
        <div class="navbar-header">
            <form class ="form-view" id="view-form-update-ecomm"  method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <a class="btn" href="/UpdateEcommProfile.jsp?e_id=<%=e_id%>" style="margin-right:40px ;margin-left:25px;margin-top:5px;font-size: medium;color: whitesmoke; background-color: #2A2A2A" action="UpdateEcommProfile.jsp" role="button">Update Profile</a>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
        <div class="navbar-header">
            <form class ="form-view" id="view-form-view-sellers" action="/sellers/<%=e_id%>" method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <button id = "view-sellers" type="submit"   style="margin-right:40px ;margin-left:25px;margin-top:5px;font-size: medium;color: whitesmoke; background-color: #2A2A2A" class="btn"> View All Sellers</button>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
        <div class="navbar-header">
            <form class ="form-view" id="view-form-view-notifivation" action="/getAllNotification/<%=e_id%>" method="get">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <button id = "view-notification" type="submit"   style="margin-right:40px ;margin-left:25px;margin-top:5px;font-size: medium;color: whitesmoke; background-color: #2A2A2A" class="btn"> Notifications</button>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>

        <div class="navbar-header">
            <form class ="form-view" id="view-form" action="/destroy" method="post">
                <form :hidden path="id"/>
                <div class="form-row">
                    <div class="form-group">
                        <button class="btn" type="submit" style="margin-right:0px ;margin-left:25px;margin-top:5px;font-size: medium;color: whitesmoke; background-color: #2A2A2A" role="button">Logout</button>
                    </div> <!-- form-group// -->
                    <!-- form-group end.// -->
                </div>
            </form>
        </div>
    </div>
</nav>
<br>
<div id="product-table" class="container" >
    <div class="row">
        <div class="col-md-12">
            <br><br>
            <h3> ${ecommName}, Here are All Sellers</h3>
            <div class="table-responsive">
                <div class="container" style="margin-top: 10px">
                    <div class="form-group">
                        <select name="state" id="maxRows" class="form-control" style="width: 120px ;height: 30px; ">
                            <option value="5000">Show All</option>
                            <option value="2">2</option>
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="20">20</option>
                        </select>
                    </div>
                </div>
                <table id="mytable" class="table table-bordred table-striped">
                    <thead>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Shop Name</th>
                    <th>Contact No.</th>
                    <th>Email</th>
                    <th>View Products</th>
                    <%--                    <th>Delete</th>--%>
                    </thead>
                    <tbody>
                    <c:forEach var="s" items="${sellerList}">
                        <tr>
                            <td>${s.sellerName}</td>
                            <td>${s.sellerAddress}</td>
                            <td>${s.shopName}</td>
                            <td>${s.sellerContactNo}</td>
                            <td>${s.sellerEmailId}</td>
                            <td>
                                <div class="navbar-header">
                                    <form class ="form-view" id="view-seller"  action = "/products/viewProductsBySeller/${s.sellerId}/<%=e_id%>" method="get">
                                        <form :hidden path="id"/>
                                        <div class="form-row">
                                            <div class="form-group">
                                                <button id = "view-this-seller" type="submit"   style="color: whitesmoke; background-color: #2A2A2A" class="btn">View</button>
                                            </div> <!-- form-group// -->
                                            <!-- form-group end.// -->
                                        </div>
                                    </form>
                                </div>

                            </td>
                                <%--                            <td><a data-placement="top" data-toggle="tooltip" href="/DeleteListedProduct.jsp?p_id=${e.productId}&id=<%=id%>"  action="DeleteListedProduct.jsp" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><i class="fas fa-trash"></i></button></a></td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="pagination-container">
                    <nav>
                        <ul class="pagination"></ul>
                    </nav>
                </div>

            </div>
            <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
            <script>
                var table = '#mytable'
                $('#maxRows').on('change', function () {
                    $('.pagination').html('')
                    var trnum = 0
                    var maxRows = parseInt($(this).val())
                    var totalRows = $(table+' tbody tr').length
                    $(table+' tr:gt(0)').each(function () {
                        trnum++
                        if(trnum > maxRows){
                            $(this).hide()
                        }
                        if (trnum <= maxRows){
                            $(this).show()
                        }
                    })
                    if (totalRows > maxRows){
                        var pagenum = Math.ceil(totalRows/maxRows)
                        for (var i = 1; i <= pagenum;) {
                            $('.pagination').append('<li data-page="' + i + '">\<span>' + i++ + '<span class="sr-only">(current)</span></span>\</li>').show()
                        }
                    }
                    $('.pagination li:first-child').addClass('active')
                    $('.pagination li').on('click', function () {
                        var pageNum = $(this).attr('data-page')
                        var trIndex = 0
                        $('.pagination li').removeClass('active')
                        $(this).addClass('active')
                        $(table+ ' tr:gt(0)').each(function () {
                            trIndex++
                            if (trIndex > (maxRows*pageNum) || trIndex <= (maxRows*pageNum)-maxRows ){
                                $(this).hide()
                            }else {
                                $(this).show()
                            }

                        })
                    })
                })
                $(function () {
                    $('table tr:eq(0)').prepend('<th>S.No</th>')
                    var id = 0;
                    $('table tr:gt(0)').each(function () {
                        id++
                        $(this).prepend('<td>'+ id + '</td>')
                    })
                })
            </script>
        </div>
    </div>
</div>

<br>


</body>
</html>