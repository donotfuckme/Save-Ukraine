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

function checkMarginToTop() {
  window.pageYOffset > sticky
    ? nav.classList.add('menu-sticky')
    : nav.classList.remove('menu-sticky')
}

//Scroll to top
const scrollElem = document.getElementById('scrollToTop')

window.onscroll = (event) => {

  checkMarginToTop() //init stiky menu

  window.scrollY > 200
    ? scrollElem.style.opacity = 1
    : scrollElem.style.opacity = 0
}

const goUp = () => {
  const top = Math.max(document.body.scrollTop, document.documentElement.scrollTop)

  let timeOut

  if (top > 0) {
    window.scrollBy(0, -100)

    timeOut = setTimeout(() => goUp(), 20)

  } else clearTimeout(timeOut)
}

scrollElem.onclick = (event) => {

  event.preventDefault()

  goUp()
} //end scroll to top

//Sliders
//Main page slider Ukraine
if (document.querySelector('.slider-main-ukrain')) {
  const swiperUkrain = new Swiper('.slider-main-ukrain', {
    observer: true,
    observerParents: true,
    slidesPerView: 1,
    spaceBetween: 32,
    effect: "fade",
    watchOverflow: true,
    speed: 1200,
    loop: true,
    loopAdditionalSlides: 5,
    preloadImages: false,
    parallax: true,
    autoplay: true,
    pagination: {
      el: '.swiper-pagination'
    },
    navigation: {
      nextEl: '.slider-main-ukrain .slider-arrows-next',
      prevEl: '.slider-main-ukrain .slider-arrows-prev',
    },
  })
}

//Main page slider building
if (document.querySelector('.slider-main-building')) {
  const swiperBuilding = new Swiper('.slider-main-building', {
    observer: true,
    observerParents: true,
    slidesPerView: 1,
    spaceBetween: 32,
    watchOverflow: true,
    speed: 1200,
    loop: true,
    loopAdditionalSlides: 5,
    preloadImages: false,
    parallax: true,
    // pagination: {
    //   el: '.swiper-pagination',
    // },
    navigation: {
      nextEl: '.building-arrows__next',
      prevEl: '.building-arrows__prev',
    },
  })
}

//Page about-us slider documents
if (document.querySelector('.statutory-documents__slider')) {
  let isMobile = {
    Android: function () {
      return navigator.userAgent.match(/Android/i);
    },
    BlackBerry: function () {
      return navigator.userAgent.match(/BlackBerry/i);
    },
    iOS: function () {
      return navigator.userAgent.match(/iPhone|iPad|iPod/i);
    },
    Opera: function () {
      return navigator.userAgent.match(/Opera Mini/i);
    },
    Windows: function () {
      return navigator.userAgent.match(/IEMobile/i);
    },
    any: function () {
      return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
    }
  }

  if (window.innerWidth < 650 && isMobile.any()) {
    const swiper = new Swiper('.statutory-documents__slider', {
      ggrabCursor: true,
      effect: "creative",
      creativeEffect: {
        prev: {
          shadow: true,
          translate: ["-135%", 0, -500],
        },
        next: {
          shadow: true,
          translate: ["135%", 0, -500],
        },
      },
      loop: true,
    })
  }
} //End Page about-us slider documents

//Page donation change Tab
const showPage = (event, numberPage) => {
  const navTabLinks = document.querySelectorAll('.nav-tabs__item')

  for (let item of navTabLinks) {
    item.classList.remove('tabs-active')
  }
  const pages = document.querySelectorAll('.tabs-donation__page')

  for (let page of pages) {
    page.classList.remove('tabs-active')
  }

  document.getElementById(numberPage).classList.add('tabs-active')
  event.target.classList.add('tabs-active')
} //End change Tab

//Page donation check Input
const checkValue = (prop, btnId) => {
  let inputValue = parseFloat(prop.value.trim()) || 0

  console.log(inputValue, '<===inputValue')

  console.log((inputValue > 0), '<===inputValue < 0')

  if (inputValue > 0) {
    Object.assign(prop.nextElementSibling, {
      innerText: ''
    })
  } else {
    Object.assign(prop.nextElementSibling, {
      innerText: 'Невірний формат суми'
    })
  }

  (prop.value === '') && (prop.nextElementSibling.innerText = '')

  Object.assign(btnId, {
    disabled: !(inputValue > 0)
  })
} //End check input

//Page donation Insert sum
const inputPayCard = document.getElementById('pay-card')
const inputPayRequisites = document.getElementById('pay-requisites')

const btnPayCard = document.getElementById('btn-card')
const btnPayRequisites = document.getElementById('btn-requisites')

inputPayCard.oninput = (event) => checkValue(event.target, btnPayCard)

inputPayRequisites.oninput = (event) => checkValue(event.target, btnPayRequisites)


const sumListOfCard = document.getElementsByClassName('pay-card')
const sumListOfRequisites = document.getElementsByClassName('pay-requisites')

for (let sumOfCard of sumListOfCard) {
  sumOfCard.onclick = (event) => {
    insertValueCard(event.target, sumListOfCard)
  }
}

for (let sumOfRequisites of sumListOfRequisites) {
  sumOfRequisites.onclick = (event) => {
    insertValueRequisites(event.target, sumListOfRequisites)
  }
}
const toggleSumActive = (itemActive, sumList) => {
  for (let sumItem of sumList) {
    sumItem.classList.remove('sum-active')
  }

  itemActive.classList.add('sum-active')
}

const insertValueCard = (property, sumList) => {
  toggleSumActive(property, sumList)

  console.log(property.textContent)

  Object.assign(inputPayCard, {
    value: property.textContent
  })
}

const insertValueRequisites = (property, sumList) => {
  toggleSumActive(property, sumList)

  console.log(property.textContent)

  Object.assign(inputPayRequisites, {
    value: property.textContent
  })
} //End page donation Insert value

//Init map
function initMap() {
  let posLatLng = {lat: 49.2260640, lng: 28.4059510}
  let myElem = document.getElementById('map')
  let myOptions = {
    zoom: 16,
    center: posLatLng,
    styles: [
      {
        "stylers": [
          {"hue": "#007fff"},
          {"saturation": 89}]
      },
      {
        "featureType": "water",
        "stylers": [
          {"color": "#ffffff"}]
      },
      {
        "featureType": "administrative.country",
        "elementType": "labels",
        "stylers": [
          {"visibility": "off"}]
      }
    ]
  }

  const myMap = new google.maps.Map(myElem, myOptions)

  const marker = new google.maps.Marker({
    position: posLatLng,
    map: myMap,
    title: 'БФ "Врятувати Україну"',
    icon: 'image/Marker.svg',
    animation: google.maps.Animation.BOUNCE
  })
} //End Init map