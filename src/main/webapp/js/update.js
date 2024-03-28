function saveChanges() {
	var userid = document.getElementById("userid").value;
	document.getElementById("action").value = "update";

	// 첫 번째 myFetch 호출: 데이터 저장
	myFetch("user.do", "viewForm", json => {
		if (json.status === 0) {
			// 데이터 저장 성공, 두 번째 myFetch 호출: 페이지 이동
			myFetch("user.do?action=list&userid=" + userid, "viewForm", json => {
				if (json.status === 0) {
					alert("회원정보를 수정하고 페이지를 이동합니다.");
					window.location.href = "user.do?action=read&userid=" + userid;
				} else {
					// 두 번째 myFetch 실패
					alert(json.statusMessage);
				}
			});
		} else {
			// 첫 번째 myFetch 실패
			alert(json.statusMessage);
		}
	});
}
function hobbyRead() {

}