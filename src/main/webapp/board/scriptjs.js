function writeSave() {
	if(document.writeForm.writer.value ==""){
	alert("??????? 뭐해 ? 작성자 안써? ");
		document.writeForm.writer.focus();
		return false; 
	}
	
	if(document.writeForm.email.value ==""){
	alert("??????? 뭐해 ? 이메일 안써? ");
		document.writeForm.email.focus();
		return false; 
	}

	if(document.writeForm.subject.value ==""){
	alert("??????? 뭐해 ? 제목 안써? ");
		document.writeForm.subject.focus();
		return false; 
	}

	if(document.writeForm.content.value ==""){
	alert("??????? 뭐해 ? 내용 안써? ");
		document.writeForm.content.focus();
		return false; 
	}

	if(document.writeForm.pass.value ==""){
	alert("??????? 뭐해 ? 비밀번호 안써? ");
		document.writeForm.pass.focus();
		return false; 
	}

}

	function deleteSave() {
		if(document.delForm.pass.value ==""){
			alert("비밀번호를 입력해주세요.");
			document.delForm.pass.focus();
			return false;
	}
}