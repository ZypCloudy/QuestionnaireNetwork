<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>问卷网</title>
    <meta charset="utf-8" />
    <link rel="icon" href="images/title.ico" type="image/x-icon" />
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
	<link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/style.css">

     <script src="js/modernizr-2.6.2.min.js"></script>
</head>
<body>
    <div class="gtco-loader"></div>
    <div id="page">
        <nav class="gtco-nav" role="navigation">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-xs-2">
                        <div id="gtco-logo">
                            <a href="index.jsp">
                                <img src="images/logo_v2.png" />
                            </a>
                        </div>
                    </div>
                    <div class="col-xs-7 text-center menu-1">
                        <ul>
                            <li class="active"><a href="index.jsp">主页</a></li>
                            <li><a href="views/allQuestionnaire.jsp">问卷调查</a></li>
                            <li class="has-dropdown">
                                <a href="#">模板库</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-xs-3 text-right hidden-xs menu-2">
<%
	String sessionValues=(String)request.getSession().getAttribute("userName");  
	if(sessionValues==null){
%>
						<ul>
                            <li class="btn-cta"><button data-toggle="modal" data-target="#LoginModal">登录</button></li>
                            <li class="btn-cta"><a href="views/register.jsp"><span>注册</span></a></li>
                        </ul>
<%} else{%>
							<ul>
	                            <li class="btn-cta">
						<a href="views/back.jsp"><span>进入后台</span></a>	                            
	                            </li>
                            </ul>
<%} %>                      
                    </div>
                </div>
            </div>
        </nav>
        <header id="gtco-header" class="gtco-cover" role="banner" style="background-image: url('images/index1.jpg');">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center">
                        <div class="display-t">
                            <div class="display-tc animate-box" data-animate-effect="fadeIn">
                                <h1>倾 听 你 在 乎 的</h1>
                                <h2>Listen to what you care</h2>
<%
	if(sessionValues==null){
%>
                                <p><a href="views/register.jsp" class="btn btn-default">立即注册</a></p>
<%} %>
                                <div class="number">
                                    <span>1234</span>个用户使用问卷网收集了<span>123</span>份数据
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <footer id="gtco-footer" role="contentinfo">
            <div class="gtco-container">
                <div class="row row-pb-md">
                    <div class="col-md-4 gtco-widget">
                        <h3>重庆理工大学</h3>
                        <p><a href="#">Learn More</a></p>
                    </div>
                    <div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
                        <ul class="gtco-footer-links">
                            <li><a href="#">About</a></li>
                            <li><a href="#">Help</a></li>
                            <li><a href="#">Contact</a></li>
                            <li><a href="#">Terms</a></li>
                            <li><a href="#">Meetups</a></li>
                        </ul>
                    </div>

                    <div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
                        <ul class="gtco-footer-links">
                            <li><a href="#">Shop</a></li>
                            <li><a href="#">Privacy</a></li>
                            <li><a href="#">Testimonials</a></li>
                            <li><a href="#">Handbook</a></li>
                            <li><a href="#">Held Desk</a></li>
                        </ul>
                    </div>

                    <div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
                        <ul class="gtco-footer-links">
                            <li><a href="#">Find Designers</a></li>
                            <li><a href="#">Find Deelopers</a></li>
                            <li><a href="#">Teams</a></li>
                            <li><a href="#">Advertise</a></li>
                            <li><a href="#">API</a></li>
                        </ul>
                    </div>
                </div>

                <div class="row copyright">
                    <div class="col-md-12">
                        <p class="pull-left">
                            <small class="block">&copy; 重庆理工大学.</small>
                        </p>
                        <p class="pull-right">
                            <ul class="gtco-social-icons pull-right">
                                <li><a href="#"><i class="icon-twitter"></i></a></li>
                                <li><a href="#"><i class="icon-facebook"></i></a></li>
                                <li><a href="#"><i class="icon-linkedin"></i></a></li>
                                <li><a href="#"><i class="icon-dribbble"></i></a></li>
                            </ul>
                        </p>
                    </div>
                </div>

            </div>
        </footer>
    </div>

    <div class="gototop js-top">
        <a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
    </div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="margin-top:20%">

			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				&times;
			</button>
	
			<div class="modal-body">
			    <div class="tc_login_cq">
			        <div class="login_center_cq">
				        <div class="img">
				        	<img src="images/logo_tc.png" />
				        </div>
			            <div class="error"></div>
			            <div class="login_name">
			                <i></i><input id="email" type="text" class="txt" id="loginwin_emailorusername" placeholder="邮箱" />
			            </div>
			            <div class="login_password">
			                <i></i>
			                <input id="password" type="password" class="txt" id="loginwin_password" placeholder="密码" />
			            </div>
			            <div class="login_forget">
			                <label><input type="checkbox" value="1" name="saveme" checked="checked" class="a_radio" />&nbsp;记住我</label>
			                <a href="/forgotpwd">忘记密码？</a>
			            </div>
			            <div>
			            	<button class="loginbut"  onclick="Login()">登 录</button>
			            </div>		            
			        </div>
			    </div>
			</div>
			
		</div>
	</div>
</div>


    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.waypoints.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/user.js"></script>
</body>
</html>
