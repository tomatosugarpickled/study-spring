const tagListDIV = document.getElementById("tag-list");
const tagInput = document.getElementById("tag-input");
const sendButton = document.getElementById("send");
const files = document.querySelectorAll(".file");

let arTag = [];

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
        e.target.remove();
    }
});

sendButton.addEventListener("click", (e) => {
    const form = document["write-form"];
   arTag.forEach((tag, i) => {
       const input = document.createElement("input");
       input.setAttribute("name", `tags[${i}].tagName`)
       input.value = tag;

       form.appendChild(input);
   });

    form.submit();
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










