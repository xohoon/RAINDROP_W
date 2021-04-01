$(function(){
	console.log("invest_common jsFile");
	var whatDrop = $("#whatDrop").val();
	var round = $("#numRound").val();
	var user_email = $("#userCheck").text();
	lastNumSave_chk(whatDrop, round, user_email);
//	lastRound();
});

var numCount = 0;
var html = '';
var rankRound = 0;

// 최근 추첨 여부 확인
function lastNumSave_chk(whatDrop, round, user_email) {
	console.log("TEST::"+whatDrop+"::"+round);
	$.ajax({
		type : 'GET',
		url : '/invest/dropCheck',
		dataType : "JSON",
		data : {
			whatDrop : whatDrop,
			round : round,
			user_email : user_email
		},
		success : function(result, data) {
			if(result.chk == "pass") {
				$("#dropCheck").val("pass");
			}else if(result.chk == "block") {
				$("#dropCheck").val("block");
			}else {
				$("#dropCheck").val("error");
			}
		},
		error : function(data) {
			console.log("ERROR::" + data);
		}
	});
}

// 저장
$('#saveBtn').on('click', function() {
	var userCheck = $("#userCheck").text();
	var CoinCheck = $("#CoinCheck").val();
	var whatDrop = $("#whatDrop").val();
	numCount = $('#numCount').val();
	numRound = $('#numRound').val();
	user_email = userCheck;
	var dropCheck = $("#dropCheck").val();
	if(!userCheck || userCheck == "" || userCheck.length < 5) {
		alert("로그인이 만료되었습니다. 로그인 페이지로 이동합니다.");
		location.href="/member/signin";
		return false;
	}
	if(whatDrop == "raindrop") {
		if(numCount > CoinCheck) {
			alert("코인이 부족합니다.1"+CoinCheck+"개 이하로 입력해주세요.");
			return false;
		}else if(numCount > 10) {
			alert("너무 많은 도박은 해롭습니다. 매주 10코인 이하로 가능합니다.");
			return false;
		}
		numCount = numCount*5;
	}
	if(dropCheck == "pass") {
		if(!numCount) {
			alert('갯수를 입력해주세요');
			return false;
		}else {
			$.ajax({
				type : 'GET',
				url : '/invest/list_saving',
				dataType : 'JSON',
				data : {
					numCount : numCount,
					numRound : numRound, 
					user_email : user_email,
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
	}else if(dropCheck == "block") {
		alert("이번주 추첨은 이미 완료되었습니다.");
		return false;
	}else if(dropCheck == "error") {
		alert("왜 에러냐");
		return false;
	}
});

// 결과
function view(data) {
	$.each(data, function(idx, val) {
		html += '<br /><div>';
	  	html += '<h4>' + val.member +'회원님</h4>';
	  	html += '<h4>' + val.luck + '조합 성공</h4>';
	  	html += '</div>';
	});
	$('#returnList').html(html);
	html = '';
	$("#saveBtn").attr('disabled', 'true');
}

// 랭크 저장
$('#rankBtn').on('click', function() {
	var userCheck = $("#userCheck").text();
	var whatDrop = $("#whatDrop").val();
	rankRound = $('#rankRound').val();
	user_email = userCheck
	if(!userCheck || userCheck == "" || userCheck.length < 5) {
		alert("로그인이 만료되었습니다. 로그인 페이지로 이동합니다.");
		location.href="/member/signin";
		return false;
	}
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
				user_email : user_email,
				whatDrop : whatDrop
				},
			success : rankView
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

// 랭크 뷰
function rankView(data) {
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
	html = '';
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