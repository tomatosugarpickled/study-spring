const postLayout = (() => {
    const showList = (postWithPaging) => {
        const tbody = document.querySelector("#post-list tbody");
        // const moreButton = document.getElementById("more-button");

        const posts = postWithPaging.posts;
        const criteria = postWithPaging.criteria;

        let text = ``;
        posts.forEach((post) => {
            text += `
                <tr>
                    <td>${post.id}</td>
                    <td>${post.postTitle}</td>
                    <td>
                 `
                    post.tags.forEach((tag) => {
                        text += `${tag.tagName}, `
                    });
                    text = text.substring(0, text.length - 2);
                    text += `
                </td>
                <td>${post.memberName}</td>
                <td>${post.createdDatetime}</td>
                <td>${post.postReadCount}</td>
                <td>
            `
            // if(post.postFiles.length === 0){
            //     text += `
            //         <img src="/image/no-image.png" width="100px">
            //     `
            // }else{
            //     text += `
            //         <img src="/api/files/display?filePath=${post.postFiles[0].filePath}&fileName=${post.postFiles[0].fileName}" width="100px">
            //     `
            // }
            text += `</td>`;
        });
        console.log(criteria.page);
        if(criteria.page === 1) {
            tbody.innerHTML = text;
        }else{
            tbody.innerHTML += text;
        }
        // 더보기 버튼
        // moreButton.disabled = !criteria.hasMore;
        return criteria;
    }

    return {showList: showList};
})();










