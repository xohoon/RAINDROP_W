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

// data send and result
function dropModalAjax(round) {
	$.ajax({
		type: 'GET',
		url: '/invest/modalData',
		dataType: 'JSON',
		data: {
			round: round
		},
		success:modalResult,

		/*
		function(data) {
			$(data).each(function() {
				console.log(this.num1);
			});
		},
		beforeSend: function () {
			$('.wrap-loading').removeClass('display-none');
		},
		complete: function () {
			$('.wrap-loading').addClass('display-none');
		},
		*/
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