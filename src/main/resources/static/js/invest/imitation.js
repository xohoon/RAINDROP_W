$(function(){
	console.log("imitation jsFile");
});

// 마지막 회차 추가
$('#lastSaveBtn').on('click', function() {
	userCheck();
	$.ajax({
		type : 'GET',
		url : '/case/save_last',
		dataType : 'JSON',
		success : function(result, data) {
			alert(result.result);
			location.reload();
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
});

// 포인트 및 코인 보너스
$('#setBonusBtn').on('click', function() {
	console.log("뭔데");
	userCheck();
	var target_id = $("#memberSelect").val();
	var type = $("#selectType").val();
	var setNum = $("#setNum").val();
	if(parseInt(target_id) <= 0) {
		alert("회원을 선택해주세요.");
		return false;
	}else if (type == "type") {
		alert("타입을 선택해주세요.");
		return false;
	}else {
		$.ajax({
			type : 'GET',
			url : '/admin/setBonus',
			dataType : 'JSON',
			data : {
				target_id : target_id,
				setNum : setNum,
				type : type
			},
			success : function(result, data) {
				alert(result.result);
				location.reload();
			},
			error : function(result) {
				console.log('ERROR');
			}
		});
	}
});


/*
* 폐기 코드
* */
/*
// 번호 추출
$('#numBtn').on('click', function() {
	userCheck();
	var admin = "admin";
	var numRound = $('#numRound').val();
	var numCount = $('#numCount').val();
	if(numCount >= 150) {
		alert('150개 이하로 입력 가능합니다.');
		return false;
	}
	if(!numRound) {
		alert('회차를 입력해주세요.');
		return false;
	}else if(!numCount) {
		alert('개수를 입력해주세요.');
		return false;
	}
	$.ajax({
		type : 'GET',
		url : '/invest/imi',
		dataType : 'JSON',
		data : {
			admin : admin,
			numCount : numCount,
			numRound : numRound
		},
		success : numView,
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
});

function numView(data) {
	var html = '';
	html += '<div>';
	html += '<h4>' + data.testData + '조합 성공</h4>';
	html += '</div>';
	$('#ajaxReturn1').html(html);
	html = '';
}

// 등수 확인
$('#rankBtn').on('click', function() {
	userCheck();
	var rankRound = $('#rankRound').val();
	if(!rankRound) {
		alert('회차를 입력해주세요.');
		return false;
	}
	$.ajax({
		type : 'GET',
		url : '/invest/rankCount',
		dataType : 'JSON',
		data : {
			rankRound : rankRound
		},
		success : rankView,
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
});

function rankView(data) {
	$.each(data, function(idx, val) {
		html += '<div>';
		html += '<h3>1등 :: ' + val.rank1 +'</h3>';
		html += '<h3>2등 :: ' + val.rank2 + '</h3>';
		html += '<h3>3등 :: ' + val.rank3 + '</h3>';
		html += '<h3>4등 :: ' + val.rank4 + '</h3>';
		html += '<h3>5등 :: ' + val.rank5 + '</h3>';
		html += '<h3>당첨 총 금액 :: ' + val.total + '</h3>';
		html += '<h3>세금 제외금액 :: ' + val.tax + '</h3>';
		html += '</div>';
	});
	$('#ajaxReturn2').html(html);
	html = '';
}

	$.each(data, function(idx, val) {
		var last = val.last;
		html += '<div>';
		if(last){
			html += '<h3>' + val.last +'회차 저장 완료</h3>';
		}
	  	html += '<h3>' + val.result +'</h3>';
	  	html += '</div>';
	});
	html = '<div>' + data.result + '</div>';
	$('#ajaxReturn3').html(html);
	html = '';
}


// 전체 회차 추가
$('#all_save').on('click', function() {
	userCheck();
	$.ajax({
		type : 'GET',
		url : '/case/save_all',
		dataType : 'JSON',
		data : {
			},
		success : all_view,
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
});

function all_view(data) {
	/!*
	$.each(data, function(idx, val) {
		html += '<div>';
	  	html += '<h3>' + val.result +'</h3>';
	  	html += '</div>';
	});
	*!/
	html = '<div>' + data.result + '</div>';
	$('#ajaxReturn4').html(html);
	html = '';
}
*/
