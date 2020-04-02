<%-- 
    Document   : shop
    Created on : Apr 2, 2020, 10:15:43 AM
    Author     : resha
--%>
<%@page import="com.shop.domain.UserDetails"%>
<%
	//clear the cache and redirect to the login page when session is not available
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("expires", 0);
	if(session.getAttribute("user") == null){
		response.sendRedirect("login/");
	}else{
%>
<% 
	//get user object from session attribute
	UserDetails userDetails = (UserDetails)session.getAttribute("user"); 
        
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Register Your SHOP</title>

    <!-- Icons font CSS-->
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="css/main.css" rel="stylesheet" media="all">
</head>

<body>
    <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
            <div class="card card-4">
                <div class="card-body">
                    <h2 class="title">Shop Registration Form</h2>
                    <h4>Hi, <%= userDetails.getFisrtName() %></h4><br>
                    <h6>(* required)</h6><br>
                    <form method="POST" id="shopForm">
                         <input type="text" id="txtUser" name="txtUser" value="<%= userDetails.getId() %>" hidden>
                        <div class="input-group">
                            <div class="">
                                <div class="input-group">
                                    <label class="label">Shop Name <span style="text-transform: lowercase">(as on</span> BR)*</label>
                                    <input class="input--style-4" type="text" name="txtShopName" id="txtShopName" placeholder="Example Shop">
                                </div>
                            </div>
                            
                        </div>
                        <div class="input-group">
                            <div class="">
                                <div class="input-group">
                                    <label class="label">Other Name</label>
                                    <input class="input--style-4" type="text" name="txtOtherName" id="txtOtherName" placeholder="Example Shop">
                                </div>
                            </div>
                            
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Business Registration Number*</label>
                                    <input class="input--style-4" type="text" name="txtBR" id="txtBR" placeholder="NPVS43127">
                                </div>
                            </div>
                            <div class="col-2">
                                <label class="label">Category*</label>
                                <div class="rs-select2 js-select-simple">
                                    <select name="cmbCategory" id="cmbCategory">
                                        <option disabled="disabled" selected="selected">Choose option</option>
                                    </select>
                                    <div class="select-dropdown"></div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <label class="label">Province*</label>
                                <div class="rs-select2 js-select-simple">
                                    <select name="cmbProvince" id="cmbProvince">
                                        <option disabled="disabled" selected="selected">Choose option</option>
                                        
                                    </select>
                                    <div class="select-dropdown"></div>
                                </div>
                            </div>
                            <div class="col-2">
                                <label class="label">District*</label>
                                <div class="rs-select2 js-select-simple">
                                    <select name="cmbDistrict" id="cmbDistrict">
                                        <option disabled="disabled" selected="selected">Choose option</option>
                                        
                                    </select>
                                    <div class="select-dropdown"></div>
                                </div>
                            </div>
                        </div><br>
                        <div class="row row-space">
                            <div class="col-2">
                                <label class="label">Area*</label>
                                <div class="rs-select2 js-select-simple">
                                    <select name="cmbArea" id="cmbArea">
                                        <option disabled="disabled" selected="selected">Choose option</option>
                                        
                                    </select>
                                    <div class="select-dropdown"></div>
                                </div>
                            </div>
                        </div><br>
                        <div class="input-group">
                            <div class="">
                                <div class="input-group">
                                    <label class="label">Address*</label>
                                    <input class="input--style-4" type="text" name="txtAddress" id="txtAddress" placeholder="">
                                </div>
                            </div>
                        </div>
<!--                        <div class="row row-space">
                            <div class="col-1">
                                <div class="input-group">
                                    <label class="label">Is 24 Hour</label>
                                    <input class="input--style-4" type="checkbox" name="chk24" id="chk24">
                                </div>
                            </div>
                        </div>-->
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Opening Hours(From)</label>
                                    <input class="input--style-4" type="time" name="txtFrom" id="txtFrom" placeholder="">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Opening Hours(To)</label>
                                    <input class="input--style-4" type="time" name="txtTo" id="txtTo" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Contact Number 1*</label>
                                    <input class="input--style-4" type="text" name="txtCon1" id="txtCon1" placeholder="07########" maxlength="10">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Contact Number 2</label>
                                    <input class="input--style-4" type="text" name="txtCon2" id="txtCon2" placeholder="011#######" maxlength="10">
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">WhatsApp/Viber Number</label>
                                    <input class="input--style-4" type="text" name="txtWhatsapp" id="txtWhatsapp" placeholder="07########" maxlength="10">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">email</label>
                                    <input class="input--style-4" type="email" name="txtEmail" id="txtEmail" placeholder="example@abc.com">
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Minimum Delivery Time(Hours)*</label>
                                    <input class="input--style-4" type="number" name="txtDelMin" id="txtDelMin" placeholder="">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Maximum Delivery Time(Hours)*</label>
                                    <input class="input--style-4" type="number" name="txtDelMax" id="txtDelMax" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Delivery Area(KM)*</label>
                                    <input class="input--style-4" type="number" name="txtDelArea" id="txtDelArea" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Website Address</label>
                                    <input class="input--style-4" type="text" name="txtWeb" id="txtWeb" placeholder="">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Facebook Page Link</label>
                                    <input class="input--style-4" type="text" name="txtFb" id="txtFb" placeholder="">
                                </div>
                            </div>
                        </div>
                        
                        
                        
                        
                        
                        
                        
                        
<!--                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Birthday</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4 js-datepicker" type="text" name="birthday">
                                        <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Gender</label>
                                    <div class="p-t-10">
                                        <label class="radio-container m-r-45">Male
                                            <input type="radio" checked="checked" name="gender">
                                            <span class="checkmark"></span>
                                        </label>
                                        <label class="radio-container">Female
                                            <input type="radio" name="gender">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Email</label>
                                    <input class="input--style-4" type="email" name="email">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Phone Number</label>
                                    <input class="input--style-4" type="text" name="phone">
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <label class="label">Subject</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                <select name="subject">
                                    <option disabled="disabled" selected="selected">Choose option</option>
                                    <option>Subject 1</option>
                                    <option>Subject 2</option>
                                    <option>Subject 3</option>
                                </select>
                                <div class="select-dropdown"></div>
                            </div>
                        </div>-->
                        <div class="p-t-15">
                            <button class="btn btn--radius-2 btn--blue" type="button" id="add" name="add">Register</button>
                            <button class="btn btn--radius-2 btn--blue" type="reset" id="reset" name="reset">Reset</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Jquery JS-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/datepicker/moment.min.js"></script>
    <script src="vendor/datepicker/daterangepicker.js"></script>
 <!--sweet alerts-->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
    <!-- Main JS-->
    <script src="js/global.js"></script>
    <script src="js/custom.js"></script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->
<% } %>