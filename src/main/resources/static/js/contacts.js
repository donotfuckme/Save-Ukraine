const include = (url) => {
  const script = document.createElement('script')

  script.src = url;

  document.getElementsByTagName('head')[0].appendChild(script);
}

include('../js/all-script.js')

function initMap() {
  let pos = {lat: 49.2260640, lng: 28.4059510}
  let myElem = document.getElementById('map')
  let myOptions = {
    zoom: 16,
    center: pos,
    styles: [
      {
        "stylers": [
          {
            "hue": "#007fff"
          },
          {
            "saturation": 89
          }
        ]
      },
      {
        "featureType": "water",
        "stylers": [
          {
            "color": "#ffffff"
          }
        ]
      },
      {
        "featureType": "administrative.country",
        "elementType": "labels",
        "stylers": [
          {
            "visibility": "off"
          }
        ]
      }
    ]
  }

  const myMap = new google.maps.Map(myElem, myOptions)

  const marker = new google.maps.Marker({
    position: pos,
    map: myMap,
    title: 'БФ "Врятувати Україну"',
    icon: 'img/Marker.svg',
    animation: google.maps.Animation.BOUNCE
  })
}