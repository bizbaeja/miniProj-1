document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById("loginForm");
    if (loginForm) {
        loginForm.addEventListener("submit", e => {
            e.preventDefault();
            
            console.log()
            if (confirm("로그인하시겠습니까?")) {
                myFetch("user.do", "loginForm", json => { // 여기서 formData는 JSON 문자열이어야 함
                    if (json.status === 0) {
                        alert("정상적으로 로그인되었습니다.");
                        window.location.href = "user.do?action=read&userid=" + json.userid;
                    } else {
						console.log("else +:" + json);
						console.log("else end");
                        alert(json.statusMessage + "else구");
                    }
                });
            } else {
                console.log("User cancelled the login.");
            }
        });
    } else {
        console.log("loginForm not found");
    }
});
