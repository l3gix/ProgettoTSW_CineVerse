const openSide = document.getElementById("openSide");
const closeSide = document.getElementById("closeSide");
const side = document.getElementById("side");

openSide.addEventListener("click", function () {
    side.classList.add("active");
});

closeSide.addEventListener("click", function () {
    side.classList.remove("active");
});