$(function(){
	console.log("invest_common jsFile");
});

// 최근 추첨 여부 확인
function lastNumSave_chk(whatDrop, round) {
	var whatDrop = $("#whatDrop").val();
	var round = $("#numRound").val();
	$.ajax({
		type : 'GET',
		url : '/invest/dropCheck',
		dataType : "JSON",
		data : {
			whatDrop : whatDrop,
			round : round
		},
		success : function(result, data) {
			if(result.chk == "pass") {
				console.log("check::"+result.chk);
				dropSave();
			}else if(result.chk == "block") {
				console.log("check::"+result.chk);
				alert(round + "회차 모의 투자는 이미 완료되었습니다.");
				return false;
			}
		},
		error : function(data) {
			console.log("ERROR::" + data);
		}
	});
}

// 모의투자 저장 ajax
function dropSave() {
	var whatDrop = $("#whatDrop").val(); // 페이지 상태 체크
	var numCount = $('#numCount').val(); // 받을 번호 개수
	var numRound = $('#numRound').val(); // 회차
	$.ajax({
		type: 'GET',
		url: '/invest/list_saving',
		dataType: 'JSON',
		data: {
			numCount : numCount,
			numRound : numRound,
			whatDrop : whatDrop
		},
		success: moveDroptop,
		beforeSend: function () {
			$('.wrap-loading').removeClass('display-none');
		},
		complete: function () {
			$('.wrap-loading').addClass('display-none');
		},
		error: function (result) {
			console.log('ERROR');
		}
	});
}

// 랭크 저장
$('#rankBtn').on('click', function() {
	var whatDrop = $("#whatDrop").val();
	var rankRound = $('#rankRound').val();
	// 로그인 상태 체크
	userCheck();
	if(!rankRound) {
		alert('회차를 입력해주세요');
		return false;
	}else {
		$.ajax({
			type : 'GET',
			url : '/invest/myRank',
			dataType : 'JSON',
			data : {
				rankRound : rankRound,
				whatDrop : whatDrop
			},
			success : moveDroptop
			,
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

function moveDroptop() {
	location.href="/invest/droptop";
}

// 번호 추출 결과 뷰
function view(data) {
	var html = '';
	$.each(data, function(idx, val) {
		if(val.luck == 0) {
			html = '<div>해당 회차는 모의투표가 이미 진행되었습니다</div>';
		}else {
			html += '<br /><div>';
			html += '<h4>' + val.member +'회원님</h4>';
			html += '<h4>' + val.luck + '조합 성공</h4>';
			html += '</div>';
		}
	});
	$('#returnList').html(html);
	html = '';
	$("#saveBtn").attr('disabled', 'true');
}

// 당첨 확인 뷰
function rankView(data) {
	var html = '';
	$.each(data, function(idx, val) {
		if(val.gameResult == "true") {
			html += '<br /><div>';
			html += '<h4>' + val.round + '회 총 ' + val.total + '게임 결과</h4>';
			html += '<h4>1등 :: ' + val.rank1 +'</h4>';
			html += '<h4>2등 :: ' + val.rank2 + '</h4>';
			html += '<h4>3등 :: ' + val.rank3 + '</h4>';
			html += '<h4>4등 :: ' + val.rank4 + '</h4>';
			html += '<h4>5등 :: ' + val.rank5 + '</h4>';
			html += '<h4>당첨 총 금액 :: ' + val.revenue_total + '</h4>';
			html += '</div>';
		}else if(val.gameResult == "false") {
			html += '<div>게임한적없다</div>';
		}
	});
	$('#returnRank').html(html);
}

//최신회차 가져오기
/*
 * 오류로 인해 패스
function lastRound() {
	$.ajax({
		type : 'GET',
		url : '/invest/getRound',
		dataType : "JSON",
		success : function(result, data) {
			console.log("??"+result.getRound);
			$("#numRound").val(result.getRound+1);
			$(".in_lastRound").val(result.getRound+1);
			$(".in_lastRound").text(result.getRound+1);
		},
		error : function(data) {
			console.log("ERROR?::" + data);
		}
	});
}
*/