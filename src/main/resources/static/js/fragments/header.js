$(function(){
	var email = $('#userCheck').text();
	console.log('TEST11::'+email);
	if(email || email.length > 5) {
		userAjax(email);
	}
	
});

function userAjax(email) {
	console.log('TEST22::'+email);
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
			console.log("SUCCESS::" + result.userPoint);
			console.log("SUCCESS::" + result.userCoin);
		},
		error : function(data) {
			console.log("ERROR::" + data);
		}
	});
}
