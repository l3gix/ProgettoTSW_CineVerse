const menuButton = document.querySelector(".menu-button");
const burgerMenu = document.querySelector(".burger");
const icons = document.querySelectorAll(".icon > svg");
menuButton.addEventListener("click", () => {
    burgerMenu.classList.toggle("active");
    menuButton.classList.toggle("active");
    icons.forEach(icon => {
        icon.classList.toggle("hidden");
    });

});