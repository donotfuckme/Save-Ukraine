//Burger
document.getElementById('burger-menu').onclick = (event) => {
  document.getElementById('burger-menu').classList.toggle('open')
  document.getElementById('main-menu').classList.toggle('active')
  document.getElementsByTagName('header')[0].classList.toggle('transperent')
  document.getElementsByTagName('body')[0].classList.toggle('hidden')
  document.getElementById('content-header').classList.toggle('active-contacts')
  document.getElementsByClassName('content-header__contacts')[0].classList.toggle('active-contacts')
}

//Scroll+fixed menu
const nav = document.getElementById('menu-stiky')

let sticky = nav.offsetTop;

function checkMarginToTop () {
  window.pageYOffset > sticky
    ? nav.classList.add('menu-sticky')
    : nav.classList.remove('menu-sticky')
}

//Scroll to top
const scrollElem = document.getElementById('scrollToTop')

window.onscroll = (event) => {

  checkMarginToTop() //stiky menu

  window.scrollY > 200
    ? scrollElem.style.opacity = 1
    : scrollElem.style.opacity = 0
}

function goUp () {
  const top = Math.max(document.body.scrollTop, document.documentElement.scrollTop)

  let timeOut

  if(top > 0) {
    window.scrollBy(0, -100)

    timeOut = setTimeout(() => goUp(), 20)

  } else clearTimeout(timeOut)
}

scrollElem.onclick = (event) => {

  event.preventDefault()

  goUp()
}
