// document.addEventListener("click", (e) => {
//     smsService.send("01012341234", (code) => {
//         const inputCode = document.getElementById("input-code").value;
//         if(code === inputCode){
//         //     인증 성공
//         }else {
//         //     인증 실패
//         }
//     });
// })

document.getElementById("send").addEventListener("click", async (e) => {
    const phone = document.getElementById("phone").value;
    const code = await smsService.send(phone);
    console.log(code);
//     할 거 하기
})










