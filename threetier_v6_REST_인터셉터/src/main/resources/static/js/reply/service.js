const replyService = (() => {

    // 추가
    const write = async (reply) => {
        await fetch("/api/replies/write", {
            method: "POST",
            body: JSON.stringify(reply),
            headers: {
                "Content-Type": "application/json"
            }
        });
    }

    // 목록
    const getList = async (page, postId, callback) => {
        const response = await fetch(`/api/replies/list/${page}?postId=${postId}`);
        const replies = await response.json();
        if(callback){
            callback(replies);
        }
    }

    // 수정
    const update = async (reply) => {
        await fetch(`/api/replies/${reply.id}`, {
            method: "PUT",
            body: JSON.stringify(reply),
            headers: {
                "Content-Type": "application/json"
            }
        })
    }

    // 삭제
    const remove = async (id) => {
        await fetch(`/api/replies/${id}`, {
            method: "DELETE"
        });
    }

    return {write: write, getList: getList, update: update, remove: remove};
})();












