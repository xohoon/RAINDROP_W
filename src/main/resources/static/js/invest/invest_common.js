$(function(){
	console.log("invest_common jsFile");
	var whatDrop = $("#whatDrop").val();
	var round = $("#numRound").val();
	var user_email = $("#userCheck").text();
	lastNumSave_chk(whatDrop, round, user_email);
//	lastRound();
});
// 최신회차 가져오기
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

