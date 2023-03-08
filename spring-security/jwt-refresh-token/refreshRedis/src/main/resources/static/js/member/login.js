class LoginController extends UtilController {
    constructor() {
        super();
        this.loginForm = document.getElementById("loginForm");
        this.loginButton = document.getElementById("login_form_button");
    }

    initLoginController() {
        this.loginButton.addEventListener("click", evt => {
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "/member/login");
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.addEventListener("loadend", event => {
                let status = event.target.status;
                const responseValue = JSON.parse(event.target.responseText);

                if ((status >= 400 && status <= 500) || (status > 500)) {
                    this.showToastMessage(responseValue["message"]);
                } else {
                    this.setLocalStorage("Authorization", responseValue["grantType"] + responseValue["accessToken"]);
                    window.location = document.referrer;
                }
            });

            xhr.addEventListener("error", event => {
                this.showToastMessage('로그인에 실패하였습니다.');
            });

            let data = new FormData(this.loginForm);
            let jsonData = {};

            for (let [key, value] of data) {
                jsonData[key] = value;
            }

            xhr.send(JSON.stringify(jsonData));
        });
    }
}

// Execute all functions
document.addEventListener("DOMContentLoaded", () => {
    const loginController = new LoginController();
    loginController.initLoginController();
});