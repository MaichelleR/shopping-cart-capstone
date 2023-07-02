const baseURL = "http://localhost:8080"

const errMsg = document.getElementById("errMesg")
const form = document.querySelector("form")
const list = document.getElementById("ol-list")
const deleteBtn = document.getElementsByTagName("button")

let items = new Array()

const getItems = () => {
    axios.get(`${baseURL}/api/items`)
    .then(({ data }) => {
        items = data
        displayArr()
    })
}
const submitHandler = (e) => {
    e.preventDefault()
    const userInput = document.querySelector("#userInput")
    let body = {
        taskName: userInput.value,
       isDone: false

    }
    createName(body)
    userInput.value = ""
}

const displayArr = () => {
    list.innerHTML = ""
    console.log("display ",items);
    items.forEach(e => {
        list.innerHTML += makeItem(e)
    });
}
const addToCart = (e,sku) => {
    let body = {
        sku:sku,quantity:1
    }
    const id = localStorage.getItem('shoppingCartId');
    axios.post(`${baseURL}/api/shopping-cart/${id}`, body)
    .then(({ data }) =>{})
}
const makeItem = (item) => {
    let msg
    item.isDone? msg="completed":msg="not completed"
    return `
    <div class="imgContainer">
    <div>
        <p> <font color="#d7a6d7"> ${item.name} :</font><font color="#d7a6d7"> $${item.price} </font></p>
    </div>
    <div>
        <img src="${item.url}" alt = "${item.description}" ref="${item.description}" width="100px" height="100px"/>
    </div>
    <div class="imgButton">
        <button onclick="addToCart(this, ${item.sku})">Add to Cart</button>
        </div>
    </div>`
}

window.onload = function() {
    localStorage.setItem('shoppingCartId',1);
 }

const makeCart = (item) => {

}

getItems()