function CreatePaper(){
	var title =$("#title").val();
	if(title==null || title==""){
		alert("标题不能为空!!!");
		return ;
	}
	var type =$(".main ul .active").val().toString();
	if(type=="" || type==null){
		alert("请选择类别!!!");
		return ;
	}
	$("#type").val(type);
	$("#form1").submit();
}
function CreateRadio(){
	 var paperId = $("#paperId").val();
	 $.ajax({
	        type: 'get',
	        url: '/QuestionnaireNetwork/servlets/CreateServlet',
	        data:{"questionType":"createRadio","paperId":paperId},
	        success: function (data) {
	        	  $(".right-block").append(data);
	        },
	        error: function (data) {
	            alert("发送失败!!!");
	        }
	    });
}
function CreateChecked(){
	 var paperId = $("#paperId").val();
	 $.ajax({
	        type: 'get',
	        url: '/QuestionnaireNetwork/servlets/CreateServlet',
	        data:{"questionType":"createChecked","paperId":paperId},
	        success: function (data) {
	        	$(".right-block").append(data);
	        },
	        error: function (data) {
	            alert("发送失败!!!");
	        }
	    });
}
function CreateCompletion(){
	 var paperId = $("#paperId").val();
	 $.ajax({
	        type: 'get',
	        url: '/QuestionnaireNetwork/servlets/CreateServlet',
	        data:{"questionType":"createCompletion","paperId":paperId},
	        success: function (data) {
		         $(".right-block").append(data);
	        },
	        error: function (data) {
	            alert("发送失败!!!");
	        }
	    });
}
function subPaper(){
	var title = $("#title").val();
	var paperId = $("#paperId").val();
	var questionInputs = new Array();
	var questionIds = new Array();
    var optionInputs = new Array();
    var optionIds = new Array();
//    var checkedInputs = new Array();
//    var checkedIds = new Array();
    var completionInputs= new Array();
    $(".question input").each(function(){
    	questionIds.push(this.id);
        questionInputs.push($(this).val());
    });
    $(".list-item .right .option .inputs").each(function(){
    	optionIds.push(this.id);
    	optionInputs.push($(this).val());
    });
    $.ajax({
        type:'post',
         url:'/QuestionnaireNetwork/servlets/CreateServlet',
         data:{"paperId":paperId,"title":title,"questionInputs":questionInputs,"questionIds":questionIds,"optionInputs":optionInputs,"optionIds":optionIds,"completionInputs":completionInputs},
         success:function(data){ 
        	 alert("成功");
         },
         error:function(data){
        	 alert("发送错误!!!");
         }
    });
}
function subFilled(){
    var choosed = new Array();
    var paperId = $("#paperId").val();
	 $(".list-item .right  input:checked").each(function(){
		    choosed.push($(this).val());
	    });
	 if(choosed.length==0){
		 alert("请填写完整信息!!!");
		 return;
	 }
	 $.ajax({
	        type:'post',
	         url:'/QuestionnaireNetwork/servlets/FillQuestionnaireServlet',
	         data:{"paperId":paperId,"choosed":choosed},
	         success:function(data){ 
	        	 if(data==0){
	        		 alert("相同ip只能投一次!!!");
	        	 }else{
	        		 if(data == 2){
		        		 alert("成功!!!");   
		        	 }else{
		        		 alert("发生错误了!!!");
		        	 }
	        	 }
	         },
	         error:function(data){
	        	 alert("投票失败!!!");
	         }
	    });
}