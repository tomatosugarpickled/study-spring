const tbody = document.querySelector("#post-list tbody");
const pageWrap = document.getElementById("page-wrap");
// ################################# 검색 #################################
const searchButton = document.getElementById("search-button");
const type = document.querySelector("select[name=type]");
const keyword = document.querySelector("input[name=keyword]");
const buttons = document.querySelectorAll(".tag-button");
// ################################# 태그 #################################
buttons.forEach((button) => {
    button.addEventListener("click", async (e) => {
        page = 1;
        criteria = await postService.getList(page, {type: type.value, keyword: keyword.value, tagName: e.target.textContent}, postLayout.showList)
    });
});

// ################################# 검색 #################################
let criteria = {hasMore: true}

searchButton.addEventListener("click", async (e) => {
    page = 1;
    criteria = await postService.getList(page, {type: type.value, keyword: keyword.value}, postLayout.showList)
});

// ################################# 무한 스크롤 페이징 처리 #################################
let page = 1;
let checkScroll = true;

postService.getList(page, {type: type.value, keyword: keyword.value}, postLayout.showList)

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
        criteria = await postService.getList(++page, {type: type.value, keyword: keyword.value}, postLayout.showList);
    }

    setTimeout(() => {
        checkScroll = true;
    }, 1000)
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












