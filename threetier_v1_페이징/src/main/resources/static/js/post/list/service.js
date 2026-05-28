const postService = (() => {
    const getList = async (page, callback) => {
        page = page || 1;
        const response = await fetch(`/api/posts/list/${page}`)
        const postWithPaging = await response.json();
        if(callback){
            return callback(postWithPaging);
        }
    }

    return {getList: getList};
}) ();