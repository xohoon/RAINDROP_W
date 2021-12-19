function userAjax(email) {
	$.ajax({
		type : 'GET',
		url : '/member/user_info',
		dataType : "JSON",
		data : {
			email : email
		},
		success : function(result, data) {
			var userCash = numberCommas(result.userCash);
			var userPoint = numberCommas(result.userPoint);
			$("#userCoin").text(result.userCoin);
			$("#userPoint").text(userPoint);
			$("#userCash").text(userCash);
			$("#coinCheck").val(result.userCoin);
		},
		error : function(data) {
			console.log("ERROR::" + data);
		}
	});
}
function userCheck() {
	var userCheck = $("#userCheck").text();
	var path = window.location.pathname;
	if(!userCheck || userCheck == "" || userCheck.length < 5) {
		console.log("PATH::" + path);
		if(path == "/main" || path == "/member/signin" || path == "/member/signup") {
			return false;
		}else {
			location.href="/member/signin";
			alert("로그인이 만료되었습니다. 로그인 페이지로 이동합니다.");
			return false;
		}
	}
}

// 숫자 콤마
function numberCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// 콤마 제거
function removeCommas(x) {
	return x.replace(/,/g, "");
}