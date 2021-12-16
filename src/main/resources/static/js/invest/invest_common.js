$(function(){
	console.log("invest_common jsFile");
});

// 최근 추첨 여부 확인
function lastNumSave_chk(whatDrop, round) {
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
				dropSave(whatDrop);
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
function dropSave(whatDrop) {
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
		success: function() {
			if(whatDrop == "raindrop") {
				moveRaindrop();
			}else if(whatDrop == "droptop") {
				moveDroptop();
			}
		},
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
	var lastRound = $('#lastRound').val();
	// 로그인 상태 체크
	userCheck();
	if(!rankRound) {
		alert('회차를 입력해주세요');
		return false;
	}else if(whatDrop == "raindrop" && parseInt(rankRound) == parseInt(lastRound)) {
		alert("해당 회차는 결과 발표 전 입니다.");
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
			success : function() {
				if(whatDrop == "raindrop") {
					moveRaindrop();
				}else if(whatDrop == "droptop") {
					moveDroptop();
				}
			},
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

// MODAL CODE
var myModal = document.getElementById('detailModal')
var round = "";
myModal.addEventListener('show.bs.modal', function (event) {
	var whatDrop = $("#whatDrop").val();
		round = $(event.relatedTarget).data('round');
	console.log("MODAL?::"+round);
	userCheck();
	modalAjax(round, whatDrop);
});

// data send and result
function modalAjax(data, whatDrop) {
	$.ajax({
		type: 'GET',
		url: '/invest/modalData',
		dataType: 'JSON',
		data: {
			round : data,
			whatDrop : whatDrop
		},
		success : modalResult,
		error: function (result) {
			console.log('ERROR');
		}
	});
}

// modal result html
function modalResult(data) {
	console.log("111");
	var html = '';
	html = '<table class="table table-bordered">' +
		'<thead>' +
		'    <tr>' +
		'      <th scope="col">#</th>' +
		'      <th scope="col">회차</th>' +
		'      <th scope="col">추첨 번호</th>' +
		'      <th scope="col">추첨 날짜</th>' +
		'    </tr>' +
		'  </thead>' +
		' <tbody>';
	$.each(data, function(idx, val) {
		html +=
			'    <tr>' +
			'      <th scope="row">'+val.round_id+'</th>' +
			'      <td>'+val.round+'</td>' +
			'      <td>'+val.num1+', '+val.num2+', '+val.num3+', '+val.num4+', '+val.num5+', '+val.num6+'</td>' +
			'      <td>'+val.in_date+'</td>' +
			'    </tr>';
	});
	html += '</tbody></table>';
	$('#modalBodyView').html(html);
}

function moveDroptop() {
	location.href="/invest/droptop";
}

function moveRaindrop() {
	location.href="/invest/raindrop";
}



/*
* 폐기 코드
* */
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