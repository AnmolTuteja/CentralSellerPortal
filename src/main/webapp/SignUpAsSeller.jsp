<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
    <title>Seller Registration</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="../../css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="../../css/main.css">
    <script src="../../js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
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
                   <a class="navbar-brand" href="#" style="color:gray ; font-size: 18px">Central Seller Portal</a>
            </div>
        </div>
    </nav>
<br>


    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <header class="card-header">
                    <a href="/" action="index.html" class="float-right btn btn-outline-primary mt-1">Have an Account, Log in</a>
                    <h4 class="card-title mt-2">Seller Sign up</h4>
                </header>
                <article class="card-body">
                    <form class ="form-signin" id="admission-form" action = "/postSeller" method="post" onsubmit="return Validate()" >
                        <form :hidden path="id"/>
                        <div class="form-row">
                            <div class="col form-group">
                                <label>Name </label>
                                <input type="text" id="s_name" name="s_name" class="form-control" placeholder=""  maxlength="50" required>

                            </div> <!-- form-group end.// -->
                            <!-- form-group end.// -->
                        </div> <!-- form-row end.// -->
                        <div class="form-group">
                            <label>Address</label>
                            <input type="text" id="s_address" name="s_address" class="form-control" placeholder="" maxlength="100" required>

                        </div>
                        <div class="form-group">
                            <label>Mobile No.</label>
                            <input type="text" id="s_mobile" name="s_mobile" class="form-control" pattern="[1-9]{1}[0-9]{9}" placeholder=""  required>
                            <small class="form-text text-muted">Please ensure its 10 digit number</small>
                            <p  style="color: red;  font-family: Arial"><%
                                if (session.getAttribute("sellerContactNo") != null)
                                    out.println( session.getAttribute("sellerContactNo")+ " already exists");
                            %>   </p>
                        </div>
                        <div class="form-group">
                            <label>Email address</label>
                            <input type="email" id="s_mail" name="s_mail" class="form-control" maxlength="30" placeholder="" required>
                            <small class="form-text text-muted">We'll never share your email with anyone else.</small>
                            <p  style="color: red;  font-family: Arial"><%
                                if (session.getAttribute("sellerEmailId") != null)
                                    out.println( session.getAttribute("sellerEmailId")+ " already exists");
                            %>  </p>

                        </div> <!-- form-group end.// -->
                        <div class="form-group">
                            <label>Store Name</label>
                            <input type="text" id="s_shop" name="s_shop" class="form-control"  maxlength="50" placeholder="" required>

                        </div>


                        <div class="form-group">
                            <label>Create password</label>
                            <input class="form-control" id="s_password" name="s_password" type="password"  minlength="6"  maxlength="20" required>
                            <small class="text-muted">Password should contain 1 digit,1 special character,1 lower case and 1 upper case letter.</small>

                            <!--pattern="(?=.*\d)(?=.*[a-z])(?=.*[@#$%^&+=])(?=.*[A-Z]).{6,}"
                            //title="Must contain at least one number and one uppercase and lowercase letter, 1 special character and at least 6 or more characters  -->

                        </div> <!-- form-group end.// -->
                        <div class="form-group">
                            <label>Confirm password</label>
                            <input class="form-control"  id="s_pass" name="s_pass" type="password" minlength="6" maxlength="20" required>
                        </div>
                    <p id ="pass-match" style="color:crimson ; font-family: Arial" ></p>

                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
                        <script>
                            $('#s_password, #s_pass').on('keyup', function () {
                            if ($('#s_password').val() == $('#s_pass').val()) {
                            $('#pass-match').html('Passwords match :D').css('color', 'green');
                            } else
                            $('#pass-match').html('Passwords do not match').css('color', 'red');

                            });
                        </script>


                        <h2  style="color: red; font-size: large; font-family: Arial ; text-align:center;"><%
                            if (session.getAttribute("sellerContactNo") != null || session.getAttribute("sellerEmailId") != null)
                                out.println("Failed to Signup!");
                        %>   </h2>

                        <script type="text/javascript">

                            function Validate() {
                                var password = document.getElementById("s_password").value;
                                var confirmPassword = document.getElementById("s_pass").value;
                                var email= document.getElementById("s_mail").value;
                                var pattern= /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{6,}$/;
                                var email_pattern=/^(\D)+(\w)*((\.(\w)+)?)+@(\D)+(\w)*((\.(\D)+(\w)*)+)?(\.)[a-z]{2,}$/;

                                if (password != confirmPassword) {
                                    alert("Passwords do not match.");
                                    return false;
                                }

                                if(!pattern.test(password)){
                                    alert("Password should contain 1 digit,1 special character,1 lower case and 1 upper case letter.");
                                    return false;
                                }

                                if(!email_pattern.test(email)){
                                    alert("Please follow standard email format");
                                    return false;
                                }

                               return true;
                            }
                        </script>


                        <div class="form-group">
                            <button id="sub-btn" type="submit" class="btn btn-primary btn-block"> Register  </button>
                        </div>
                        <small class="text-muted">By clicking the 'Register' button, you confirm that you accept our <br> Terms of use and Privacy Policy.</small>
                    </form>
                </article>
            </div>
        </div>

    </div>

<br><br>

</body>
</html>
