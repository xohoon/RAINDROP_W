$(function(){
	console.log("raindrop jsFile");
});

// RAINDROP 저장
$('#rainSaveBtn').on('click', function() {
	var CoinCheck = $("#CoinCheck").val(); // 코인개수
	var whatDrop = $("#whatDrop").val(); // 페이지 상태 체크
	var numCount = $('#numCount').val(); // 받을 번호 개수
	var numRound = $('#numRound').val(); // 회차
	// 로그인 상태 체크
	userCheck();
	// 코인 개수 체크
	if(CoinCheck == 0) {
		alert("코인이 부족합니다. 모의로또 먼저 도전하세요!");
		location.href="/invest/droptop";
		return false;
	}else if(numCount > CoinCheck) {
		alert("코인이 부족합니다."+CoinCheck+"개 이하로 입력해주세요.");
		return false;
	}else if(numCount > 10) {
		alert("너무 많은 도박은 해롭습니다. 매주 10코인 이하로 가능합니다.");
		return false;
	}
	if(!numRound) {
		alert("회차를 선택해 주세요.");
		return false;
	}else {
		$.ajax({
			type : 'POST',
			url : '/invest/list_saving',
			dataType : 'JSON',
			data : {
				numCount : numCount,
				numRound : numRound,
				whatDrop : whatDrop
			},
			success : view,
			beforeSend:function() {
				$('.wrap-loading').removeClass('display-none');
			},
			complete:function() {
				$('.wrap-loading').addClass('display-none');
			},
			error : function(result) {
				console.log('ERROR');
			}
		});
	}
});
