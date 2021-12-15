$(function(){
	console.log("droptop jsFile");
});

// 저장
$('#dropSaveBtn').on('click', function() {
	var whatDrop = $("#whatDrop").val(); // 페이지 상태 체크
	var numRound = $('#numRound').val(); // 회차
	// 로그인 상태 체크
	userCheck();
	if(numRound == 0) {
		alert("회차를 선택해 주세요.");
		return false;
	}else if(numRound != 0) {
		lastNumSave_chk(whatDrop, numRound);
	}
});

// MODAL CODE
var myModal = document.getElementById('dropDetailModal')
var round = "";
myModal.addEventListener('show.bs.modal', function (event) {
	round = $(event.relatedTarget).data('round');
	console.log("MODAL?::"+round);
	dropModalAjax(round);
});

function exchangeAjax(data, point) {
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
					moveDroptop()
				}else if(result.chk == "fail") {
					console.log("FAIL");
					return false;
				}
			},
			error: function (result) {
				console.log('ERROR');
			}
		});
	}
}

// data send and result
function dropModalAjax(data) {
	$.ajax({
		type: 'GET',
		url: '/invest/modalData',
		dataType: 'JSON',
		data: {
			round : data
		},
		success:modalResult,
		error: function (result) {
			console.log('ERROR');
		}
	});
}

// modal result html
function modalResult(data) {
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