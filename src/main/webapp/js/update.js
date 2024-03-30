function saveChanges() {
    var userid = document.getElementById("userid").value;
    var form = document.getElementById("viewForm");
    form.action.value = "update";
    var formData = new FormData(form);

    // Check if the user confirms the update before making the call
    if (confirm("수정하시겠습니까?")) {
        // myFetch call to save the data
        myFetch("user.do", formData, json => {
            if (json.status === 0) {
                // Data save successful, alert and redirect
                alert("정상적으로 수정되었습니다.");
                window.location.href = "user.do?action=read&userid=" + userid;
            } else {
                // If there was an error in saving data, alert the error
                alert(json.statusMessage);
            }
        });
    } else {
        // If the user cancels, do nothing
        console.log("User cancelled the update.");
    }
}


function saveFunc() {
    var userid = document.getElementById("userid").value;
    var formId = "viewForm";
    
    // Check if the user confirms the update before making the call
    if (confirm("수정하시겠습니까?")) {
        // myFetch call to save the data
        myFetch("user.do", formId, json => {
            if (json.status === 0) {
                // Data save successful, alert and redirect
                alert("정상적으로 수정되었습니다.");
                window.location.href = "user.do?action=mainPage";
            } else {
                // If there was an error in saving data, alert the error
                alert(json.statusMessage);
            }
        });
    } else {
        // If the user cancels, do nothing
        console.log("User cancelled the update.");
    }
}

const viewForm = document.getElementById("viewForm");
viewForm.addEventListenr("submit", e =>  {
    var userid = document.getElementById("userid").value;
    var form = document.getElementById("viewForm");
    form.action.value = "update";
    var formData = new FormData(form);

    // Check if the user confirms the update before making the call
    if (confirm("수정하시겠습니까?")) {
        // myFetch call to save the data
        myFetch("user.do", "viewForm", json => {
        	
            if (json.status === 0) {
                // Data save successful, alert and redirect
                alert("정상적으로 수정되었습니다.");
                window.location.href = "user.do?action=read&userid=" + userid;
            } else {
                // If there was an error in saving data, alert the error
                alert(json.statusMessage);
            }
        });
    } else {
        // If the user cancels, do nothing
        console.log("User cancelled the update.");
    }
})