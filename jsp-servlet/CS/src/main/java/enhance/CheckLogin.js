/**
 * 
 */

const form = document.getElementById("loginForm");
const username = document.getElementById("username");
const password = document.getElementById("password");
const errorLabel = document.getElementById("error");

const MESSAGES = {
  emptyUser: "Vui lòng nhập tên đăng nhập.",
  emptyPass: "Vui lòng nhập mật khẩu.",
  invalid: "Tên đăng nhập hoặc mật khẩu không đúng.",
};

form.addEventListener("submit", (e) => {
  e.preventDefault();
  let message = "";

  if (username.value.trim() === "") {
    message = MESSAGES.emptyUser;
  } else if (password.value.trim() === "") {
    message = MESSAGES.emptyPass;
  } else {
    // Giả lập kiểm tra sai
    message = MESSAGES.invalid;
  }

  // Hiển thị lỗi (nếu không có lỗi thì hiện khoảng trắng để giữ layout)
  errorLabel.textContent = message || "\u00A0";
});