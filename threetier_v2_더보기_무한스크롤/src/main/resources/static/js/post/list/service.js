const postService = (() => {
    const getList = async (page, {type, keyword, tagName}, callback) => {
        page = page || 1;
        tagName = tagName || "";
        const response = await fetch(`/api/posts/list/${page}?type=${type}&keyword=${keyword}&tagName=${tagName}`)
        const postWithPaging = await response.json();
        if(callback){
            return callback(postWithPaging);
        }
    }

    return {getList: getList};
}) ();