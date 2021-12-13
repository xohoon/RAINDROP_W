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
			$("#CoinCheck").val(result.userCoin);
		},
		error : function(data) {
			console.log("ERROR::" + data);
		}
	});
}
function userCheck() {
	var userCheck = $("#userCheck").text();
	if(!userCheck || userCheck == "" || userCheck.length < 5) {
		alert("로그인이 만료되었습니다. 로그인 페이지로 이동합니다.");
		location.href="/member/signin";
		return false;
	}
}

