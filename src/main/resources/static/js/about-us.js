const include = (url) => {
  const script = document.createElement('script')

  script.src = url;

  document.getElementsByTagName('head')[0].appendChild(script);
}

include('all-script.js')

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
  const swiper = new Swiper(".statutory-documents__slider", {
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
  });
}
let currentWidth = window.innerWidth
window.onresize = function () {
  if (window.innerWidth <= 650 && currentWidth > 650) {
    location.reload()
  } else if (window.innerWidth > 650 && currentWidth <= 650)
    location.reload()
}
