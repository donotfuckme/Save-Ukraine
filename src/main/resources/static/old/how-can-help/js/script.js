function include (url) {
  const script = document.createElement('script')

  script.src = url;

  document.getElementsByTagName('head')[0].appendChild(script);
}

include('../js/all-script.js')

//Tabs
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
}

//Check Input
const inputPayCard = document.getElementById('pay-card')
const inputPayRequisites = document.getElementById('pay-requisites')
const inputsPay = [inputPayCard, inputPayRequisites]

const btnPayCard = document.getElementById('btn-card')
const btnPayRequisites = document.getElementById('btn-requisites')
const btnsPay = [btnPayCard, btnPayRequisites]

// const checkInputValue = () => {
//   for (let input of inputsPay) {
//     let valueInput = parseFloat(input.value) || 0

//     console.log(input)
//     console.log(input.value)
//   }
// }
// checkInputValue()


const checkValue = (event, btnId) => {
  let inputValue = parseFloat(event.target.value) || 0

  if (inputValue > 0) {
    document.getElementById(btnId).disabled = false
    event.target.nextElementSibling.innerText = ''
  } else {
    document.getElementById(btnId).disabled = true
    event.target.nextElementSibling.innerText = 'Невірний формат суми'
  }

  (event.target.value === '') && (event.target.nextElementSibling.innerText = '')
}

//Insert sum
const sumListOfCard = document.getElementsByClassName('pay-card')

for (let sumOfCard of sumListOfCard) {
  sumOfCard.onclick = (event) => {
    for (let sumOfCard of sumListOfCard) {
      sumOfCard.classList.remove('sum-active')
    }
console.log(event.target)
    event.target.classList.add('sum-active')
    const valueSumCard = event.target.innerText

console.log(valueSumCard)
    inputPayCard.setAttribute("value", valueSumCard)
  }
}

const sumListOfRequisites = document.getElementsByClassName('pay-requisites')

for (let sumOfRequisites of sumListOfRequisites) {
  sumOfRequisites.onclick = (event) => {
    for (let sumOfRequisites of sumListOfRequisites) {
      sumOfRequisites.classList.remove('sum-active')
    }

    event.target.classList.add('sum-active')
    const sumValueofRequisites = event.target.innerText

    inputPayRequisites.setAttribute("value", sumValueofRequisites)
  }
}
