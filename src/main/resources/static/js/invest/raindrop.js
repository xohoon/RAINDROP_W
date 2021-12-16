$(function(){
	console.log("raindrop jsFile");
});

// RAINDROP 저장
$('#rainSaveBtn').on('click', function() {
	var coinCheck = $("#CoinCheck").val(); // 코인개수
	var whatDrop = $("#whatDrop").val(); // 페이지 상태 체크
	var numCount = $('#numCount').val(); // 받을 번호 개수
	var numRound = $('#numRound').val(); // 회차
	// 로그인 상태 체크
	userCheck();
	// 코인 개수 체크
	if(!numCount) {
		alert("개수를 입력하세요.");
		return false;
	}
	if(coinCheck == 0) {
		if(confirm("코인이 부족합니다. 모의로또 페이지로 이동합니다.") == true) {
			location.href="/invest/droptop";
		}else {
			return false;
		}
	}else if(parseInt(numCount) > parseInt(coinCheck)) {
		console.log("???" + numCount + "??" + coinCheck);
		alert("코인이 부족합니다."+coinCheck+"개 이하로 입력해주세요.");
		return false;
	}else if(parseInt(numCount) > 10) {
		alert("너무 많은 도박은 해롭습니다. 매주 10코인 이하로 가능합니다.");
		return false;
	}
	if(!numRound) {
		alert("회차를 선택해 주세요.");
		return false;
	}else {
		lastNumSave_chk(whatDrop, numRound);
	}
});
