const memberService = (() => {
    const checkEmail = async (memberEmail, callback) => {
        const response = await fetch(`/member/check-email?memberEmail=${memberEmail}`)
        const isAvaliable = await response.text() === "true"

        if(callback){
            callback(isAvaliable);
        }
    }

    return {checkEmail: checkEmail};
})()

