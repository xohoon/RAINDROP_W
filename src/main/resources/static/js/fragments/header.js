$(function(){
	var email = $('#userCheck').text();
	if(email || email.length > 5) {
		userAjax(email);
	}
});

function userAjax(email) {
	$.ajax({
		type : 'GET',
		url : '/member/user_info',
		dataType : "JSON",
		data : {
			email : email
		},
		success : function(result, data) {
			$("#userPoint").text(result.userPoint+"P");
			$("#userCoin").text(result.userCoin+"Coin");
		},
		error : function(data) {
			console.log("ERROR::" + data);
		}
	});
}
