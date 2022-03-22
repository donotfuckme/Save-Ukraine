function include (url) {
  const script = document.createElement('script')

  script.src = url;

  document.getElementsByTagName('head')[0].appendChild(script);
}

include('../js/all-script.js')