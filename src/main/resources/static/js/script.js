function include (url) {
  const script = document.createElement('script')

  script.src = url;

  document.getElementsByTagName('head')[0].appendChild(script);
}

include('js/all-script.js')

//Sliders
if (document.querySelector('.slider-main-ukrain')){
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
      el: '.swiper-pagination',
    },
    navigation: {
      nextEl: '.slider-main-ukrain .slider-arrows-next',
      prevEl: '.slider-main-ukrain .slider-arrows-prev',
    },
  })
}

if (document.querySelector('.slider-main-building')){
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
    // autoplay: true,
    // pagination: {
    //   el: '.swiper-pagination',
    // },
    navigation: {
      nextEl: '.building-arrows__next',
      prevEl: '.building-arrows__prev',
    },
  })
}


