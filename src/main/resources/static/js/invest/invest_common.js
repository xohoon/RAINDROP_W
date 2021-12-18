$(function(){
	console.log("invest_common jsFile");
	var email = $('#userCheck').text();
	if(email || email.length > 5) {
		userAjax(email);
	}else {
		userCheck(); // head.js
	}
});

// 최근 추첨 여부 확인
function lastNumSave_chk(whatDrop, numRound, numCount) {
	$.ajax({
		type : 'GET',
		url : '/invest/dropCheck',
		dataType : "JSON",
		data : {
			whatDrop : whatDrop,
			round : numRound
		},
		success : function(result, data) {
			if(result.chk == "pass") {
				console.log("check::"+result.chk);
				dropSave(whatDrop, numRound, numCount);
			}else if(result.chk == "block") {
				console.log("check::"+result.chk);
				alert(numRound + "회차 모의 투자는 이미 완료되었습니다.");
				return false;
			}
		},
		error : function(data) {
			console.log("ERROR::" + data);
		}
	});
}

// 모의투자 저장 ajax
function dropSave(whatDrop, numRound, numCount) {
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
	if(!rankRound || rankRound == 0) {
		alert('회차를 입력 및 선택해주세요.');
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

// 상세보기 MODAL CODE
var detailModal = document.getElementById('detailModal')
detailModal.addEventListener('show.bs.modal', function (event) {
	var whatDrop = $("#whatDrop").val();
	var round = $(event.relatedTarget).data('round');
	console.log("MODAL?::"+round);
	userCheck();
	detailModalAjax(round, whatDrop);
});

// data send and result
function detailModalAjax(data, whatDrop) {
	$.ajax({
		type: 'GET',
		url: '/invest/modalData',
		dataType: 'JSON',
		data: {
			round : data,
			whatDrop : whatDrop
		},
		success : detailResult,
		error: function (result) {
			console.log('ERROR');
		}
	});
}

// modal result html
function detailResult(data) {
	console.log("111");
	var html = '';
	html = '<table class="table table-bordered">' +
		'<thead>' +
		'    <tr class="table-secondary">' +
		'      <th scope="col">#</th>' +
		'      <th scope="col">회차</th>' +
		'      <th scope="col">추첨 번호</th>' +
		'      <th scope="col">등수</th>' +
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
			'      <td>'+val.confirm_rank+'</td>' +
			'      <td>'+val.in_date.substring(0,10)+'</td>' +
			'    </tr>';
	});
	html += '</tbody></table>';
	$('#detailModalView').html(html);
}

// 환전 MODAL CODE
var exchangeModal = document.getElementById('exchangePoint')
exchangeModal.addEventListener('show.bs.modal', function (event) {
	var point = $("#userPoint").text();
	console.log("EXCHANGE::" + point);
	userCheck();
	exchangeResult(point);
});

// 환전 모달 view
function exchangeResult(point) {
	var possibleCoin = parseInt(point)/1000;
	possibleCoin = Math.floor(possibleCoin);
	var html = "";
	html =
		'<table class="table table-bordered">' +
		'	<thead>' +
		'		<tr>' +
		'			<th scope="col">현재 포인트</th>' +
		'			<th scope="col">충전 가능 코인</th>' +
		'			<th scope="col">코인 입력</th>' +
		'			<th scope="col">충전</th>' +
		'		</tr>' +
		'	</thead>' +
		'	<tbody>' +
		'		<tr>' +
		'			<th scope="row">'+point+'</th>' +
		'			<td>'+possibleCoin+'</td>' +
		'			<td><input class="form-control form-control-sm" type="text" id="changeCoin"></td>' +
		'			<td><button class="btn btn-success btn-sm" type="button" onclick="chargeCoin('+point+', '+possibleCoin+')">충전</button></td>' +
		'		</tr>'+
		'	</tbody>' +
		'</table>';
	$('#exchangeModalView').html(html);
}

// 충전 버튼 클릭시
function chargeCoin(point, possibleCoin) {
	var changeCoin = $('#changeCoin').val();
	if(isNaN(changeCoin)) {
		alert("숫자만 입력 가능합니다.");
		return false;
	}else if(!changeCoin || parseInt(changeCoin) < 1) {
		alert("1이상의 숫자를 입력해주세요.");
		return false;
	}else if (parseInt(changeCoin) > parseInt(possibleCoin)) {
		alert("충전 가능한 코인을 초과하였습니다.");
		return false;
	}else {
		exchangeAjax(changeCoin);
	}
}

// 충전 ajax
function exchangeAjax(changeCoin) {
	$.ajax({
		type: 'GET',
		url: '/invest/exchangeResult',
		dataType: 'JSON',
		data: {
			coin : changeCoin
		},
		success: function(result, data) {
			if(result.chk == "success") {
				alert("환전이 완료되었습니다.");
				location.reload();
			}else if(result.chk == "fail") {
				alert("환전에 문제가 발생했습니다. 잠시 후 다시 시도해주세요.");
				return false;
			}
		},
		error: function (result) {
			console.log('ERROR');
		}
	});
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