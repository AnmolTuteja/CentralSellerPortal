<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Company Registration</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
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
                    <a class="navbar-brand" href="index.html" style="color:gray ; font-size: 18px">Central Seller Portal</a>

                </div>
            </div>
        </nav>
        <br>


        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <header class="card-header">
                        <a href="index.html" class="float-right btn btn-outline-primary mt-1">Have an Account, Log in</a>
                        <h4 class="card-title mt-2">Company Sign up</h4>
                    </header>
                    <article class="card-body">
                        <form class ="form-signin" id="ecomm-admission-form" action = "/ecomm/postEcommerce" method="post" onsubmit="return Validate()">
                            <form :hidden path="id"/>

                            <div class="form-row">
                                <div class="col form-group">
                                    <label>Company Name </label>   
                                    <input type="text"  id="cName" name="cName" class="form-control" maxlength="100" placeholder="" required>
                                </div> <!-- form-group end.// -->
                                <!-- form-group end.// -->
                            </div> <!-- form-row end.// -->

                            <div class="form-group">
                                <label>Email address</label>
                                <input type="email" id="cEmailId" name="cEmailId" class="form-control" maxlength="100" placeholder="" required>
                                <small class="form-text text-muted">We'll never share your email with anyone else.</small>
                            </div>

                            <!-- form-group end.// -->



                            <div class="form-group">
                                <label>Create password</label>
                                <input class="form-control" name="cPassword"  id="cPassword" type="password" minlength="4" maxlength="20" required>
                            </div> <!-- form-group end.// -->
                            <div class="form-group">
                                <label>Confirm password</label>
                                <input class="form-control" name="cPass" id="cPass" type="password" minlength="4" maxlength="20" required>
                            </div>
                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
                            <p id ="c-pass-match" style="color:crimson ; font-family: Arial" ></p>

                            <script>
                                $('#cPassword, #cPass').on('keyup', function () {
                                    if ($('#cPassword').val() == $('#cPass').val()) {
                                        $('#c-pass-match').html('Passwords match :D').css('color', 'green');
                                    } else
                                        $('#c-pass-match').html('Passwords dont match').css('color', 'red');

                                });
                            </script>
                            <script type="text/javascript">
                                function Validate() {
                                    var password = document.getElementById("cPassword").value;
                                    var confirmPassword = document.getElementById("cPass").value;
                                    if (password != confirmPassword) {
                                        alert("Passwords do not match.");
                                        return false;
                                    }
                                    return true;
                                }
                            </script>


                            <div class="form-group">
                                <button type="submit" id="c-sub-btn" name="c-sub-btn" class="btn btn-primary btn-block"> Register  </button>
                            </div> <!-- form-group// -->      
                            <small class="text-muted">By clicking the 'Register' button, you confirm that you accept our <br> Terms of use and Privacy Policy.</small>
                        </form>
                    </article> <!-- card-body end .// -->
                    <!--<div class="border-top card-body text-center">Have an account? <a href="">Log In</a></div>-->
                </div> <!-- card.// -->
            </div> <!-- col.//-->

        </div> <!-- row.//-->


        <!--container end.//-->

        <br><br>
    </body>
</html>
