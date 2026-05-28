const smsService = (() => {
    // const send = async (phone, callback) => {
    //     const response = await fetch(`/api/messages/send`, {
    //         method: "POST",
    //         body: JSON.stringify({phone: phone}),
    //         headers: {
    //             "Content-Type": "application/json; utf-8"
    //         }
    //     });
    //
    //     const code = response.text();
    //     if(callback) {
    //         callback(code);
    //     }
    // };

    // (1)
    const send = async (phone) => {
        const response = await fetch(`/api/messages/send`, {
            method: "POST",
            body: phone,
            headers: {
                "Content-Type": "text/plain"
            }
        });

        const code = response.text();
        return code;
    };

    // (2)
    // const send = async (phone) => {
    //     const response = await fetch(`/api/messages/send`, {
    //         method: "POST",
    //         body: JSON.stringify({phone: phone}),
    //         headers: {
    //             "Content-Type": "application/json; utf-8"
    //         }
    //     });
    //
    //     const code = response.text();
    //     return code;
    // };
    return {send: send};
})();














