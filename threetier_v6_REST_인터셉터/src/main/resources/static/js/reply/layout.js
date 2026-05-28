const replyLayout = (() => {

    const showList = ({replies, criteria}) => {
        const replyContainer = document.getElementById("replies");
        let text = ``;
        replies.forEach((reply) => {
            text += `
                <div class="reply reply-wrap${reply.id}">
                    <span class="span${reply.id}">${reply.replyContent}</span>/
                    ${reply.memberName}/ 
                    ${reply.createdDatetime}/
                    <a href="${reply.id}" id="update-ready">수정/</a>
                    <a style="display: none;" href="${reply.id}" id="update-ok">수정완료/</a>
                    <a href="${reply.id}" id="delete-ok">삭제</a>
                </div>
            `;
        });
        // ##########################################################################
        if(criteria.startPage > 1){
            text += `<a class="paging" href="${criteria.startPage - 1}">[이전]</a>`;
        }

        for(let i = criteria.startPage; i <= criteria.endPage; i++){
            if(criteria.page === i){
                text += `
                    ${i}
                `;
                continue;
            }
            text += `
                <a class="paging" href="${i}">${i}</a>
            `;
        }

        if(criteria.endPage !== criteria.realEnd) {
            text += `<a class="paging" href="${criteria.endPage + 1}">[다음]</a>`;
        }


        replyContainer.innerHTML = text;
    }

    return {showList: showList}
})();
















