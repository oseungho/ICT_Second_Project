


window.addEventListener('scroll', function(){
    let h1Text = document.querySelector(".h1_first");
    let h2Text = document.querySelector(".h1_second");
    let value = window.scrollY;
    console.log("scrollY",value);

    if(value > 120){
        h1Text.style.animation='disappear1 1s ease-out forwards';
        h2Text.style.animation='disappear2 1s ease-out forwards';
    }else{
        h1Text.style.animation='slide1 1s ease-out';
        h2Text.style.animation='slide2 1.5s ease-out';
    }
});