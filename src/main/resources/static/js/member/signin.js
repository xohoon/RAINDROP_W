$(document).ready(function() {
	console.log("jsp???");
});
var mail_reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
var password_reg = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
var blank_reg = new RegExp('\\s');
var email = "";
var password = "";
//공백 및 null 값 확인
function isNullOrBlank(value) {
	if (typeof value == "undefined" || value == null || value == "" || blank_reg.test(value)) {
		return true;
	} else {
		return false;
	}
}
function SigninSubmit() {
	var type = $(':radio[name="type"]:checked').val();
	type = Number(type);
	email = $('#email').val();
	password = $('#password').val();
	if(!type) {
		alert("회원님의 타입을 선택해 주세요");
		return false;
	}else if(isNullOrBlank(email)) {
		alert("이메일은 공백을 포함할 수 없습니다.");
		return false;
	}else if(mail_reg.test(email) == false) {
		alert("올바른 이메일 형식이 아닙니다.");
		return false;
	}else if(isNullOrBlank(password)) {
		alert("비밀번호는 공백을 포함 할 수 없습니다.");
		return false;
	}
	/* 
	else if(password_reg.test(password) == false) {
		alert('비밀번호는 영문,숫자,특수문자 각 최소 1자 이상 8~15 자 입니다.');
		return false;
	}
	 */
}