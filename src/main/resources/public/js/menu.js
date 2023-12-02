
function toggleSideBar(){
    let sideBar = document.querySelector(".sideBar");
    let homeSection = document.querySelector(".home-section");
    sideBar.classList.toggle("close");
    homeSection.classList.toggle("close");
}


let sideBar = document.querySelector(".sideBar");
let sidebarBtn = document.querySelector(".bx-menu");
let homeSection = document.querySelector(".home-section");
console.log(sideBar);

sidebarBtn.addEventListener("click", ()=>{
    sideBar.classList.toggle("close")
    homeSection.classList.toggle("close");
});