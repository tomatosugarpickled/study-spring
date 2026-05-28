window.onload = () => {
    const button = document.querySelector('.signup-button');
    button.addEventListener('click', async () => {
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const remember = document.querySelector("input[name='remember']");
        const result = await memberService.login({
            memberEmail: email,
            memberPassword: password,
            remember: remember.checked
        });
        if (result.accessToken) {
            location.href = '/post/list/1';
        }
    });

    const kakaoLoginButton = document.getElementById("kakao-login");
    kakaoLoginButton.addEventListener("click", (e) => {
        location.href = "/oauth2/authorization/kakao"
    });

    const naverLoginButton = document.getElementById("naver-login");
    naverLoginButton.addEventListener("click", (e) => {
        location.href = "/oauth2/authorization/naver"
    });
}













