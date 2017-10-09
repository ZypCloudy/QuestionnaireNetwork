//发送验证码
function GetIdentifyCode(){
	var email =  $("#email").val();
	if(email==""){
		$("#email").parent().append("<div class='error'>请输入邮箱！</div>");
		return;
	}else{
		if(!(email.indexOf("@")>0 && email.indexOf(".com")>0)){
			$("#email").parent().append("<div class='error'>请输入正确的邮箱！</div>");
			return;
		}
	}
	 $.ajax({
	        type: 'get',
	        url: '/QuestionnaireNetwork/servlets/RegisterServlet',
	        data: {"targetEmail": email},
	        success: function (data) {
	        	if(data != -1)
	        		alert("发送成功!!!");
	        	else{
		        	alert("发送失败!!!");
	        	}
	        },
	        error: function (data) {
	            alert("发送失败!!!");
	        }
	    });
}
//注册
function Register(){
	var email =  $("#email").val();
	var password = $("#password").val();
	var surePassword = $("#surePassword").val();
	var identifyCode = $("#identifyCode").val();
	$(".error").remove();
	if(email==""){
		$("#email").parent().append("<div class='error'>请输入邮箱！</div>");
		return;
	}else{
		if(!(email.indexOf("@")>0 && email.indexOf(".com")>0)){
			$("#email").parent().append("<div class='error'>请输入正确的邮箱！</div>");
			return;
		}
	}
	if(identifyCode==""){
		$("#identifyCode").parent().append("<div class='error'>请输入验证码！</div>");
		return;
	}
	if(password ==""){
		$("#password").parent().append("<div class='error'>请输入密码！</div>");
	}else{
		if(password !=surePassword){
			$("#surePassword").parent().append("<div class='error'>两次密码不一样！</div>");
		}else{
			 $.ajax({
			        type: 'post',
			        url: '/QuestionnaireNetwork/servlets/RegisterServlet',
			        data: {"email": email,"password":password,"identifyCode":identifyCode},
			        success: function (data) {
			        	if(data==0)
			        		alert("邮箱已被注册!!!");
			        	else{
			        		if(data==1){
			        			alert("验证码错误!!!");
			        		}
			        		if(data==3){
			        			window.location="/QuestionnaireNetwork/index.jsp";
			        		}
			        	}
			        },
			        error: function (data) {
			            alert("发送失败!!!");
			        }
			    });
		}
	}
}
//登录
function Login(){
	var email =  $("#email").val();
	var password =  $("#password").val();
	$.ajax({
        type: 'post',
        url: '/QuestionnaireNetwork/servlets/LoginServlet',
        data: {"email": email,"password":password},
        success: function (data) {
        	    if(data==1){
//        	    	alert("登录成功!!!");
        	    	window.location="/QuestionnaireNetwork/views/back.jsp";
        	    }else{
        	    	alert("登录失败!!!");    
        	    }	
        },
        error: function (data) {
            alert("登录失败!!!");
        }
    });
}
