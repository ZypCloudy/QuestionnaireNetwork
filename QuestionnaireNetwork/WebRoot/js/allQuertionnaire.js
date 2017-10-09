/**
 * allQuertionnaire
 */
$(document).ready(function () {
	GetAllQuertionnaire();
});

function GetAllQuertionnaire(){
	 $.ajax({
	        type: 'get',
	        url: '/QuestionnaireNetwork/servlets/AllQuertionnaireServlet',
	        success: function (data) {
	        	  $("#proj_list").html(data);
	        },
	        error: function (data) {
	            alert("发送失败!!!");
	        }
	    });
}
function SearchQuertionnaire(){
	var search = $("#search").val();
	 $.ajax({
	        type: 'get',
	        data:{"search":search},
	        url: '/QuestionnaireNetwork/servlets/SearchServlet',
	        success: function (data) {
	        	  $("#proj_list").html(data);
	        },
	        error: function (data) {
	            alert("发送失败!!!");
	        }
	    });
}