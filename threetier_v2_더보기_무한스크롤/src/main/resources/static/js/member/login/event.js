const kakaoLoginButton = document.getElementById("kakao-login");
kakaoLoginButton.addEventListener("click", (e) =>{
    location.href = "https://kauth.kakao.com/oauth/authorize?client_id=6c9664c00ac5573fa3d8f1caf80e67f3&redirect_uri=http://localhost:10000/kakao/login&response_type=code";
});

// 로그인 실패
if(login) {
    document.getElementById("login-fail-message").classList.add("on");
}