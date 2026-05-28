window.onload = () => {
    const logoutLink = document.querySelector("a.nav-link");

    memberService.info((member) => {
        const span = document.querySelector("span.nav-user");
        span.innerText = `${member.memberName}님`;
    }).catch(e => console.log(e));

    logoutLink.addEventListener("click", async (e) => {
        e.preventDefault();
        await memberService.logout()
        location.href = "/member/login";
    });
}

