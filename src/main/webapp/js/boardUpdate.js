/*
document.addEventListener("DOMContentLoaded", function() {
    const updateForm = document.getElementById("updateForm");
    if (updateForm) {
        updateForm.addEventListener("submit", e => {
            e.preventDefault();

            // Check if the user confirms the update before making the call
            if (confirm("수정하시겠습니까?")) {
                // myFetch call to save the data
                myFetch("board.do", formData, json => {
                    if (json.status === 0) {
                        // Data save successful, alert and redirect
                        alert("정상적으로 수정되었습니다.");
                        window.location.href = "board.do?action=view&boardid=" + formData.get("boardid");
                    } else {
                        // If there was an error in saving data, alert the error
                        alert(json.statusMessage);
                    }
                });
            } else {
                // If the user cancels, do nothing
                console.log("User cancelled the update.");
            }
        });
    }
});
*/
/*
document.addEventListener("DOMContentLoaded", function() {
    const updateForm = document.getElementById("updateForm");
    if (updateForm) {
        updateForm.addEventListener("submit", e =>  {
            var boardid = document.getElementById("boardid").value;
            var form = document.getElementById("updateForm");
            form.action.value = "update";
            var formData = new FormData(form);
            e.preventDefault();
            if (confirm("수정하시겠습니까?")) {
                // myFetch call to save the data
                myFetch("board.do", "updateForm", json => {
                    
                    if (json.status === 0) {
                        // Data save successful, alert and redirect
                        alert("정상적으로 수되었습니다.");
                        window.location.href = "board.do?action=view&boardid=" + boardid;
                    } else {
                        // If there was an error in saving data, alert the error
                        alert(json.statusMessage);
                    }
                });
            } else {
                // If the user cancels, do nothing
                console.log("User cancelled the update.");
            }
        });
    } else {
        console.log("viewForm not found");
    }
});
*/
document.addEventListener("DOMContentLoaded", function() {
    const updateForm = document.getElementById("updateForm");

    if (updateForm) {
		    var boardid = document.getElementById("boardid").value;
            var form = document.getElementById("updateForm");
            form.action.value = "update";
            var formData = new FormData(form);
            updateForm.addEventListener("submit", function(e) {
            e.preventDefault();
         
            // Check if the user confirms the update before making the call
            if (confirm("수정하시겠습니까?")) {
                // myFetch call to save the data
                myFetch("board.do", "updateForm", json => {
                    if (json.status === 0) {
                        // Data save successful, alert and redirect
                        alert("정상적으로 수정되었습니다.");
                        window.location.href = "board.do?action=view&boardid=" + formData.get("boardid");
                    } else {
                        // If there was an error in saving data, alert the error
                        alert(json.statusMessage);
                    }
                });
            } else {
                // If the user cancels, do nothing
                console.log("User cancelled the update.");
            }
        });
    }
});

