<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户注册_问卷网</title>
    <link href="../css/reg.css" rel="stylesheet" />
    <link rel="icon" href="../images/title.ico" type="image/x-icon" />
</head>
<body>
    <div class="reg_main">
        <a class="me_logo" href="/">
            <img src="../images/logo_v2.png" />
        </a>
        <div class="me_con_wrap r_content">
            <ul class="title tab_btn_wrap">
                <!--<li class="active first_title">手机注册</li>-->
                <li>邮箱注册</li>
            </ul>
            <div class="form_wrap">
                <div class="form_list">
                        <ul>
                            <li>
                                <input id="email" type="text" class="usermail required_r" placeholder="邮箱" autocomplete="off" />                        
                            </li>
                            <li>
                                <input id="password" type="password"  class="password required_r" placeholder="密码" autocomplete="off" />
                            </li>
                            <li>
                                <input id="surePassword" type="password"  class="password required_r" placeholder="确定密码" autocomplete="off" />
                            </li>
                            <li class="get_code">
                                <input id="identifyCode" type="text"  placeholder="验证码" class="mobile_code required_r" name="mobile_code" autocomplete="off" />
                                <span onclick="GetIdentifyCode()" class="btn_get_mobile_code">获取验证码</span>
                            </li>
                        </ul>
                        <p class="agree">
                				<span class="checked"><i></i>我接受</span>
                				<a target="_blank" href="/register/protocol">问卷网服务协议</a>
            			</p>
            			<button onclick="Register()" class="submit bt_css" >立即注册</button>
                </div>
            </div>
            <p class="have_account">已有帐号？<a href="../index.jsp" title="立即登入" class="login_dj_cq">立即登入</a></p>
        </div>
<!--         <div class="me_con_wrap r_info"> -->
<!--             <h2 class="r_title">邮箱验证</h2> -->
<!--             <div class="r_main"> -->
<!--                 <p>感谢注册，验证邮件已发送至您的邮箱：</p> -->
<!--                 <p class="r_email"></p> -->
<!--                 <p>点击邮件中的链接即可完成邮箱验证。</p> -->
<!--             </div> -->
<!--             <div class="sub_email bt_css">登录邮箱</div> -->
<!--             <div class="email_info"> -->
<!--                 <p>邮箱填写有误，您可以<a class="old_show_bt" href="javascript:;">更换邮箱</a></p> -->
<!--                 <p>未收到邮件，您可以在垃圾邮件、广告邮件中查找</p> -->
<!--                 <p>若仍未收到验证邮件，请尝试<a class="time_bt" href="javascript:;">59秒后重新发送</a></p> -->
<!--             </div> -->
<!--         </div> -->
    </div>
    <div class="me_copyright">
        <a target="_blank">
            <img src="../images/bq_logo.png" />
        </a>
    </div>
      <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/user.js"></script>
</body>
</html>
