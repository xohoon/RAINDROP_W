$(function(){
	console.log("droptop jsFile");
});

// 저장
$('#dropSaveBtn').on('click', function() {
	var user_email = $("#userCheck").text(); // 회원
	var whatDrop = $("#whatDrop").val(); // 페이지 상태 체크
	var numCount = $('#numCount').val(); // 받을 번호 개수
	var numRound = $('#numRound').val(); // 회차
	// 로그인 상태 체크
	userCheck();
	if(numRound == 0) {
		alert("회차를 선택해 주세요.");
		return false;
	}else if(numRound != 0) {
		lastNumSave_chk(whatDrop, numRound, user_email);
	}
});

