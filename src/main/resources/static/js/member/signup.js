$(document).ready(function() {
	console.log("signup jsFile");
	pass_key();
});
var token = $("input[name='_csrf']").val();
var header = "X-CSRF-TOKEN";
$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
});

var email_reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
var password_reg = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
var blank_reg = new RegExp('\\s');
var emailCode1 = "";
var emailCode2 = "";

//회원가입 유효성 검사
function signupSubmit() {
	var form = document.fm;
	var name = $('#name').val();
	var email = $('#email').val();
	var password = $('#password').val();
	var phone = $('#phone').val();
	
//	console.log("/"+name+"/"+email+"/"+password+"/"+phone);
	
	if(!name) {
		alert("이름을 입력해주세요");
		return false;
	}else if($("input:checkbox[id='CodeCheck']").is(":checked") == false) {
		alert("메일 인증을  완료해주세요.");
		return false;
	}else if(isNullOrBlank(password)) {
		alert("비밀번호는 공백을 포함 할 수 없습니다.");
		return false;
	}else if(password_reg.test(password) == false) {
		alert('비밀번호는 영문,숫자,특수문자 각 최소 1자 이상 8~15 자 입니다.');
		return false;
	}else if($('#pin_check').text() != "일치"){
		console.log("conde"+$('#pin_check').text());
		alert("비밀번호를 일치시켜주세요");
		return false;
	}else if(isNullOrBlank(phone)) {
		alert("휴대전화 번호는 -기호 없이 나란히 입력해주세요.");
		return false;
	}else if(phone.length != 11) {
		alert("휴대전화번호를 총 11자리입니다.");
		return false;
	}
};


// 공백 및 null 값 확인
function isNullOrBlank(value) {
	if (typeof value == "undefined" || value == null || value == "" || blank_reg.test(value)) {
		return true;
	} else {
		return false;
	}
}

// 숫자만 입력가능
function SetNum(obj) {
	val = obj.value;
	re=/[^0-9]/gi;
	obj.value = val.replace(re,"");
}

// 메일전송
$('#send_email').on('click', function() {
	var email = $('#email').val();
	console.log(">>??"+email);
	if(isNullOrBlank(email)) {
		alert("이메일은 공백을 포함할 수 없습니다.");
		return false;
	}else if(email_reg.test(email) == false) {
		alert("올바른 이메일 형식이 아닙니다. 확인해 주세요.");
		return false;
	}else {
		alert("메일이 전송되었습니다. 잠시만 기다려주세요.");
		$.ajax({
			type : 'POST',
			url : '/member/emailSend',
			dataType : "JSON",
			data : {
				email : email
			},
			success : function(result, data) {
				if(String(result.check) == "overlap") {
					alert('중복된 이메일입니다.');
				}else {
					var emailCode1 = String(result.check);
					console.log(">>인증번호" + emailCode1);
					$('#secretCode').val(emailCode1);
					alert('메일로 인증번호가 전송되었습니다.');	
				}
			},
			error : function(data) {
				console.log("error" + data);
			}
		});
	}
});

// 전송된 인정번호 일치 여부 확인
$('#emailCodeBtn').on('click', function() {
	emailCode2 = $('#emailCode').val();
	emailCode1 = $('#secretCode').val();
 	if(emailCode1 != "" && emailCode1 == emailCode2) {
		alert("인증이 완료되었습니다.");
		$("#CodeCheck").prop("checked", true);
		$("#CodeCheck").css("display", 'block');
//		$("#emailCodeBtn").css('display', 'none');
		$("#emailCodeBtn").attr('disabled', 'true');
	}else {
		alert("인증번호를 다시 확인해주세요.");
		return false;
	}
	
});

// 비밀번호 확인 일치 여부 실시간 확인
function pass_key() {
	$("#password2").keyup(function() {
		var pw1 = $('#password').val();
		var pw2 = $('#password2').val();
		if(pw1 == pw2) {
			$('#pin_check').text('');
			$('#pin_check').html("일치");
			document.getElementById("pin_check").setAttribute("color", "blue");
		}else {
			$('#pin_check').text('');
			$('#pin_check').html("불일치");
			document.getElementById("pin_check").setAttribute("color", "red");
		}
	});
}

