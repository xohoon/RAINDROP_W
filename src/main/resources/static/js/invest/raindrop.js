$(function(){
	console.log("raindrop jsFile");
});

var html = '';
var numCount = 0;
var rankRound = 0;

$('#rainBtn').on('click', function() {
	var userCheck = $("#userCheck").text();
	var CoinCheck = $("#CoinCheck").val();
	var whatDrop = $("#whatDrop").val();
	if(!userCheck || userCheck == "" || userCheck.length < 5) {
		alert("로그인이 만료되었습니다. 로그인 페이지로 이동합니다.");
		location.href="/member/signin";
		return false;
	}
	if(CoinCheck || CoinCheck > 0) {
		numCount = $('#numCount').val();
		numRound = $('#numRound').val();
		user_email = userCheck;
		var dropCheck = $("#dropCheck").val();
		if(dropCheck == "pass") {
			if(!numCount) {
				alert('갯수를 입력해주세요');
				return false;
			}else if(numCount > 10) {
				alert("너무 많은 도박은 해롭습니다. 매주 10코인 이하로 가능합니다.");
				return false;
			}else if(numCount > CoinCheck) {
				alert("코인이 부족합니다.1"+CoinCheck+"개 이하로 입력해주세요.");
				return false;
			}else {
				numCount = numCount*5;
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
		
	}else {
		alert("코인이 부족합니다.2");
		return false;
	}
});

function view(data) {
	$.each(data, function(idx, val) {
		html += '<br /><div>';
	  	html += '<h4>' + val.member +'회원님</h4>';
	  	html += '<h4>' + val.luck + '조합 성공</h4>';
	  	html += '</div>';
	});
	$('#ajaxReturn1').html(html);
	html = '';
	$("#getBtn").attr('disabled', 'true');
}

$('#rankBtn').on('click', function() {
	var userCheck = $("#userCheck").text();
	if(!userCheck || userCheck == "" || userCheck.length > 5) {
		alert("로그인이 만료되었습니다. 로그인 페이지로 이동합니다.");
		location.href="/member/signin";
		return false;
	}
	rankRound = $('#rankRound').val();
	user_email = $('#log_email').text();
	if(!rankRound) {
		alert('회차를 입력해주세요');
		return false;
	}
	$.ajax({
		type : 'GET',
		url : '/invest/myRank',
		dataType : 'JSON',
		data : {
			rankRound : rankRound,
			user_email : user_email
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
		html += '<br /><div>';
		html += '<h4>' + val.round + '회 총 ' + val.total + '게임 결과</h4>';
	  	html += '<h4>1등 :: ' + val.rank1 +'</h4>';
	  	html += '<h4>2등 :: ' + val.rank2 + '</h4>';
	  	html += '<h4>3등 :: ' + val.rank3 + '</h4>';
	  	html += '<h4>4등 :: ' + val.rank4 + '</h4>';
	  	html += '<h4>5등 :: ' + val.rank5 + '</h4>';
	  	html += '<h4>당첨 총 금액 :: ' + val.revenue_total + '</h4>';
	  	html += '<h4>세금 제외금액 :: ' + val.tax + '</h4>';
	  	html += '</div>';
	});
	$('#ajaxReturn2').html(html);
	html = '';
}