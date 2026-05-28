const tbody = document.querySelector("#post-list tbody");
const pageWrap = document.getElementById("page-wrap");
// ################################# 무한 스크롤 페이징 처리 #################################
let page = 1;
let checkScroll = true;
let criteria = {hasMore: true}

postService.getList(page, postLayout.showList)

window.addEventListener("scroll", async (e) => {
    if(!checkScroll || !criteria.hasMore){
        return;
    }
    // 현재 스크롤 위치
    const scrollCurrentPosition = window.scrollY;
    // 화면 높이
    const windowHeight = window.innerHeight;
    // 문서 높이
    const documentHeight = document.documentElement.scrollHeight;

    // 바닥에 닿았을 때
    if(scrollCurrentPosition + windowHeight >= documentHeight - 1) {
        checkScroll = false;
        criteria = await postService.getList(++page, postLayout.showList);
        console.log(criteria);
    }

    setTimeout(() => {
        checkScroll = true;
    }, 500)
});
// ################################# 더보기 페이징 처리 #################################
// let page = 1;
// postService.getList(page, postLayout.showList);
//
// const moreButton = document.getElementById("more-button");
// moreButton.addEventListener("click", (e) => {
//     postService.getList(++page, postLayout.showList);
// });

// ################################# 기본 페이징 처리 #################################
// let text = ``;
// posts.forEach((post) => {
//     text += `
//         <tr>
//             <td>${post.id}</td>
//             <td>${post.postTitle}</td>
//             <td>
//          `
//     post.tags.forEach((tag) => {
//         text += `${tag.tagName}, `
//     });
//     text = text.substring(0, text.length - 2);
//     text += `
//         </td>
//         <td>${post.memberName}</td>
//         <td>${post.createdDatetime}</td>
//         <td>${post.postReadCount}</td>
//         <td>
//     `
//     if(post.postFiles.length === 0){
//         text += `
//             <img src="/image/no-image.png" width="100px">
//         `
//     }else{
//         text += `
//             <img src="/api/files/display?filePath=${post.postFiles[0].filePath}&fileName=${post.postFiles[0].fileName}" width="100px">
//         `
//     }
//     text += `</td>`;
// });
//
// tbody.innerHTML = text;

// text = ``;
// if(criteria.startPage > 1){
//     text = `<a href="/post/list/${criteria.startPage - 1}">[이전]</a>`;
// }
//
// for(let i = criteria.startPage; i <= criteria.endPage; i++){
//     if(criteria.page === i){
//         text += `
//             ${i}
//         `;
//         continue;
//     }
//     text += `
//         <a class="paging" href="/post/list/${i}">${i}</a>
//     `;
// }
//
// if(criteria.endPage !== criteria.realEnd) {
//     text += `<a href="/post/list/${criteria.endPage + 1}">[다음]</a>`;
// }
//
// pageWrap.innerHTML = text;












