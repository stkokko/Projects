function registerForm() {
  let toggleBtn = document.querySelectorAll(".input-group");
  let btn = document.querySelector(".btn");
  toggleBtn[1].style.left = "450px";
  toggleBtn[0].style.left = "50px";
  btn.style.left = "0px";
}//end of function registerForm

function loginForm() {
  let toggleBtn = document.querySelectorAll(".input-group");
  let btn = document.querySelector(".btn");
  toggleBtn[1].style.left = "50px";
  toggleBtn[0].style.left = "450px";
  btn.style.left = "110px";
}//end of function loginForm

let passwordField = document.querySelector("#passwordRegForm");
let confirmPasswordField = document.querySelector("#confirmPasswordRegForm");

passwordField.addEventListener("keyup", function (e) {
  let timeout = null;
  clearTimeout(timeout);

  timeout = setTimeout(function () {
      if (passwordField.value.length >= 6) {
          passwordField.style.borderBottom = "2px solid green";
      } else {
          passwordField.style.borderBottom = "2px solid red";
      } //end of if/else

      if (passwordField.value.length > 0) {
        if (passwordField.value == confirmPasswordField.value) {
            confirmPasswordField.style.borderBottom = "2px solid green";
        } else {
            confirmPasswordField.style.borderBottom = "2px solid red";
        } //end of if/else
      }

      if (passwordField.value.length == 0) {
        passwordField.style.borderBottom = "1px solid #999";
        if (confirmPasswordField.value.length == 0) confirmPasswordField.style.borderBottom = "1px solid #999";
      }
  },1000);

});

confirmPasswordField.addEventListener("keyup", function (e) {
  let timeout = null;
  clearTimeout(timeout);

  timeout = setTimeout(function () {

      if (passwordField.value == confirmPasswordField.value) {
           confirmPasswordField.style.borderBottom = "2px solid green";
      } else {
           confirmPasswordField.style.borderBottom = "2px solid red";
      } //end of if/else

      if (confirmPasswordField.value.length == 0) confirmPasswordField.style.borderBottom = "1px solid #999";
  },1000);

});
