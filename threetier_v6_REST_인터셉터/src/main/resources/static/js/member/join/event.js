const emailInput = document.querySelector("input[name=memberEmail]")
const emailMessage = document.getElementById("email-message");
const button = document.querySelector("button[type=button]");
let check = false;

emailInput.addEventListener("blur", (e) => {
    memberService.checkEmail(e.target.value, (isAvailable) => {
        check = isAvailable;
        emailMessage.classList.toggle("on", isAvailable);
        emailMessage.textContent = isAvailable ? "사용 가능한 이메일입니다." : "중복된 이메일입니다.";
    });
});

button.addEventListener("click", (e) => {
    if(check) {
        document.joinForm.submit();
    }
});
