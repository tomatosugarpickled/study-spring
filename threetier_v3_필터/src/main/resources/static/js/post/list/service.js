const postService = (() => {
    const getList = async (page, {type, keyword, tagNames}, callback) => {
        page = page || 1;

        let queryString = `?type=${type}`;
        queryString += `&keyword=${keyword}`
        if(tagNames){
            tagNames.forEach((tagName) => {
                queryString += `&tagNames=${tagName}`
            });
        }

        const response = await fetch(`/api/posts/list/${page}${queryString}`)
        const postWithPaging = await response.json();
        if(callback){
            return callback(postWithPaging);
        }
    }

    return {getList: getList};
}) ();