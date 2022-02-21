$(function(){
	console.log("droptop jsFile");
});

// 저장
$('#dropSaveBtn').on('click', function() {
	var whatDrop = $("#whatDrop").val(); // 페이지 상태 체크
	var numRound = $('#numRound').val(); // 회차
	var numCount = $('#numCount').val(); // 횟수
	// 로그인 상태 체크
	userCheck();
	if(numRound == 0) {
		alert("회차를 선택해 주세요.");
		return false;
	}else if(numRound != 0) {
		lastNumSave_chk(whatDrop, numRound, numCount);
	}
});

function exchangePoint(data, point) {
	if(point == 0) {
		alert("금액이 부족합니다.");
		return false;
	}else {
		$.ajax({
			type: 'GET',
			url: '/invest/exchangePoint',
			dataType: 'JSON',
			data: {
				round : data,
				point : point
			},
			success: function(result, data) {
				if(result.chk == "success") {
					alert("정산이 완료되었습니다.");
					moveDroptop();
				}else if(result.chk == "fail") {
					alert("정산에 문제가 생겼습니다. 잠시 후 시도해주세요.");
					return false;
				}
			},
			error: function (result) {
				console.log('ERROR');
			}
		});
	}
}
