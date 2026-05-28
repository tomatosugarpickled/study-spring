const replyTextArea = document.getElementById("reply-content");
const memberIdInput = document.getElementById("member-id");
let memberId = null;
const replyWriteButton = document.getElementById("write");
const replyContainer = document.getElementById("replies");

let page = 1;

if(memberIdInput){
    memberId = memberIdInput.value;
}

replyService.getList(page, postId, replyLayout.showList);

if(replyWriteButton) {
    replyWriteButton.addEventListener("click", async (e) => {
        await replyService.write({replyContent: replyTextArea.value, postId: postId, memberId: memberId});
        await replyService.getList(page, postId, replyLayout.showList);
    });
}


replyContainer.addEventListener("click", async (e) => {
    e.preventDefault();
    const replyId = e.target.getAttribute("href")

    if(e.target.classList.contains("paging")){
        page = e.target.getAttribute("href");
        await replyService.getList(page, postId, replyLayout.showList);

    }else if(e.target.id === "update-ready") { // 수정 버튼 클릭

        const replyWrap = document.querySelector(`div.reply-wrap${replyId}`);
        const span = document.querySelector(`span.span${replyId}`);
        const textarea = document.createElement("textarea");

        textarea.classList.add(`reply-content${replyId}`)
        textarea.rows = 2;
        textarea.cols = 50;
        textarea.value = span.textContent;

        replyWrap.prepend(textarea);
        span.remove();

        e.target.style.display = "none";
        e.target.nextElementSibling.style.display = "inline-block";

    }else if(e.target.id === "update-ok") { // 수정 완료 버튼 클릭
        const replyContentTextArea = document.querySelector(`textarea.reply-content${replyId}`);
        await replyService.update({id: replyId, replyContent: replyContentTextArea.value});
        await replyService.getList(page, postId, replyLayout.showList);

    }else if(e.target.id === "delete-ok") { // 삭제 버튼 클릭
        const rowCount = document.querySelectorAll(".reply").length;
        await replyService.remove(replyId);
        // 현재 페이지에 댓글이 1개만 있을 경우,
        // 삭제 후 이전 페이지로 이동해준다.
        rowCount === 1 && (page--);
        await replyService.getList(page, postId, replyLayout.showList);
    }
});














