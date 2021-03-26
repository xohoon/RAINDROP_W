$(function(){
	console.log("invest_common jsFile");
	var whatDrop = $("#whatDrop").val();
	var round = $("#numRound").val();
	var user_email = $("#userCheck").text();
	lastNumSave_chk(whatDrop, round, user_email);
});
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
