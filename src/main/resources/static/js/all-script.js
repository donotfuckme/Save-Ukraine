  //Burger
document.getElementById("burger-menu").onclick = (event) => {
  document.getElementById("burger-menu").classList.toggle('open')
  document.getElementById("main-menu").classList.toggle('active')
  document.getElementsByTagName('header')[0].classList.toggle('transperent')
  document.getElementsByTagName('body')[0].classList.toggle('hidden')
}

//Scroll+fixed menu
window.onscroll = function() {
  checkMarginToTop();
}

const nav = document.getElementById("menu-stiky");

let sticky = nav.offsetTop;

function checkMarginToTop() {
  if (window.pageYOffset > sticky) {
    nav.classList.add("menu-sticky");
  } else {
    nav.classList.remove("menu-sticky");
  }
}

//Show submenu mobile version
const arrows = document.getElementsByClassName("arrow-submenu");

for (let i = 0; i < arrows.length; i++) {
  arrows[i].onclick = (event) => {
    event.target.classList.toggle("init")

    let panel = event.target.nextElementSibling
    if (panel.style.maxHeight){
      panel.style.maxHeight = null
    } else {
      panel.style.maxHeight = `${panel.scrollHeight}px`
    } 
  }
}

//Anchor to top
// document.getElementById('anchor-top').onclick = (event) => {
//   function up () {
//     let top = Math.max(document.body.scrollTop, document.documentElement.scrollTop)

//     if(top > 0) {
//      window.scrollBy(0, ((top+100)/-10))

//       const t = setTimeout(() => up(), 20)
//     } else clearTimeout(t)
//       return false
//     }
// }

$(document).ready(function(){
       $('#scroller').hide();  
      //fade in/out based on scrollTop value
      $(window).scroll(function() {
        if ($(window).scrollTop() > 100) {
          $('#scroller').fadeIn();
        }else{
          $('#scroller').fadeOut();
          // alert ('hello word');
        }
      });
     
      // scroll body to 0px on click
      $('#scroller').click(function () {
        $('body,html').animate({
          scrollTop: 0
        }, 400);
        return false;
      });
    });