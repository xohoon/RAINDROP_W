$(function(){
    console.log("info jsFile");
});

function modifyFunc() {
    var modifyKey = $("#modifyKey").val();
    if(modifyKey == "close") {
        $("#setName").attr("readonly", false);
        $("#setEmail").attr("readonly", false);
        $("#setPhone").attr("readonly", false);
        $("#modifyKey").val("open");
        $("#modifyBtn").text("수정완료");
        $("#modifyBtn").removeClass("btn-success");
        $("#modifyBtn").addClass("btn-outline-danger");
    }else if (modifyKey == "open") {
        var setName = $("#setName").val();
        var setEmail = $("#setEmail").val();
        var setPhone = $("#setPhone").val();
        if(!setName || !setEmail || !setPhone) {
            alert("정보를 입력해주세요.");
            return false;
        }else {
            updateInfo(setName, setEmail, setPhone);
        }
    }
}

function updateInfo(setName, setEmail, setPhone) {
    $.ajax({
        type: 'GET',
        url: '/member/updateInfo',
        dataType: 'JSON',
        data: {
            setName: setName,
            setPhone: setPhone,
            setEmail: setEmail
        },
        success: function (result, data) {
            if (result.chk == "success") {
                alert("수정 완료되었습니다.");
                location.reload();
            } else if (result.chk == "fail") {
                alert("수정에 문제가 발생했습니다. 다시 시도해주세요.");
                return false;
            }
        },
        error: function (result) {
            console.log('ERROR');
        }
    });
}
