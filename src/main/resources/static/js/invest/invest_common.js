function lastNumSave_chk(whatDrop, round) {
	var chkDrop;
	if(whatDrop == "raindrop") {

	}else if(whatDrop == "droptop") {
		
	}
	$.ajax({
		type : 'GET',
		url : '/invest/dropCheck',
		dataType : "JSON",
		data : {
			whatDrop : whatDrop
		},
		success : function(result, data) {
			if(result.chk == "pass") {
				chkDrop = "pass";
				$("#dropCheck").val(chkDrop);
			}else if(result.chk == "block") {
				chkDrop = "block";
				$("#dropCheck").val(chkDrop);
			}else {
				chkDrop = "error";
				$("#dropCheck").val(chkDrop);
			}
		},
		error : function(data) {
			console.log("ERROR::" + data);
		}
	});
}
