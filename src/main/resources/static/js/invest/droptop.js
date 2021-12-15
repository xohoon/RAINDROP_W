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

var myModal = document.getElementById('dropDetailModal')
var round = "";
myModal.addEventListener('show.bs.modal', function (event) {
	round = $(event.relatedTarget).data('round');
	console.log("MODAL?::"+round);

});
