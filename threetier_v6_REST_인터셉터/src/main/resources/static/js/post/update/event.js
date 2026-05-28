const tagListDIV = document.getElementById("tag-list");
const tagInput = document.getElementById("tag-input");
const sendButton = document.getElementById("send");
const files = document.querySelectorAll(".file");
const originalFiles = document.querySelectorAll("#image-wrap img");

let arTag = [];
const tagIdsToDelete = [];
const fileIdsToDelete = [];

tagInput.addEventListener("keyup", (e) => {
    if(e.key === "Enter"){
        if(e.target.value) {
            tagListDIV.innerHTML += `<span class="tag">${e.target.value}</span>`
            arTag.push(e.target.value);
        }
        e.target.value = "";
    }
});

tagListDIV.addEventListener("click", (e) => {
    if(e.target.classList.contains("tag")){
        arTag = arTag.filter((tag) => e.target.textContent !== tag);
        tagIdsToDelete.push(e.target.id);
        e.target.remove();
    }
});

sendButton.addEventListener("click", (e) => {
    const form = document["update-form"];
    arTag.forEach((tag, i) => {
        const input = document.createElement("input");
        input.setAttribute("name", `tags[${i}].tagName`)
        input.value = tag;

        form.appendChild(input);
    });

    console.log(tagIdsToDelete);
    console.log(fileIdsToDelete);

    tagIdsToDelete.forEach((tagId, i) => {
        const input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", `tagIdsToDelete`)
        input.value = tagId;

        form.appendChild(input);
    });

    fileIdsToDelete.forEach((fileId, i) => {
        const input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", `fileIdsToDelete`)
        input.value = fileId;

        form.appendChild(input);
    });

    form.submit();
});

// ########################### 파일 ###########################

originalFiles.forEach((img) => {
    img.addEventListener("click", (e) => {
        fileIdsToDelete.push(e.target.id);
        e.target.remove();
    });
});


FileList.prototype.forEach = Array.prototype.forEach;

files.forEach((file) => {
    file.addEventListener("change", (e) => {
        e.target.files.forEach((file) => {
            if(file.size / 1024 / 1024 > 10){
                //     파일 용량 초과
            }
        })
    })
})










