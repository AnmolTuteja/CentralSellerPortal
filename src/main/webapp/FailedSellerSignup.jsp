<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Signup Failed !</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Central Seller Portal</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">

        </div>
    </div>
</nav>
<div class="container">
    <div class="row text-center">
        <div class="col-sm-6 col-sm-offset-3">
            <br><br> <h2 style="color: red">Failed to Signup</h2>
            <br>
           <p  style="color: green; font-size: large; font-family: Arial"><%
                if (session.getAttribute("sellerContactNo") != null)
                out.println("Contact No :" + session.getAttribute("sellerContactNo")+ " already exists");
             %>   </p>
            <p  style="color: green; font-size: large; font-family: Arial"><%
               if (session.getAttribute("sellerEmailId") != null)
                out.println("Email Id :" + session.getAttribute("sellerEmailId")+ " already exists");
            %>

            <p style="color: green; font-size: large; font-family: Arial">Please try with different values!!</p><br><br>
            <a href="SignUpAsSeller.jsp" action="SignUpAsSeller.jsp" class="btn btn-primary" style="font-size: medium"> Return to Signup </a>
            <br><br>
        </div>

    </div>
</div>
</body>
</html>