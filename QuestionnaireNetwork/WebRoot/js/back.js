$(document).ready(function () {
	GetMyPaperList();
});

function GetMyPaperList(){
	 $.ajax({
	        type: 'get',
	        url: '/QuestionnaireNetwork/servlets/BackServlet',
	        success: function (data) {
	        	  $("#listHz").html(data);
	        },
	        error: function (data) {
	            alert("发送失败!!!");
	        }
	    });
}