
let slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    let i;
    let slides = document.getElementsByClassName("mySlides");
    if (n > slides.length) {slideIndex = 1}    
    if (n < 1) {slideIndex = slides.length}
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";  
    }
    slides[slideIndex-1].style.display = "block";  
}

setInterval(function() {
    plusSlides(1);
}, 5000);



let button = document.querySelectorAll("#giorni button");


const giorni = [
    "dom",
    "lun",
    "mar",
    "mer",
    "gio",
    "ven",
    "sab"
]

for(let i = 0 ; i < button.length - 1 ; i++)
{
    let data = new Date();
    data.setDate(data.getDate() + i);

    if(i == 0) button[i].innerHTML = "Oggi";
    else if(i == 1)button[i].innerHTML = "Domani";
    else 
    {
        button[i].innerHTML = giorni[data.getDay()];
    }
}


