const baseURL = "http://localhost:8080"

const finalElement = document.getElementById("total")
const shoppingCartTable = document.getElementById("shopping-cart")
const deleteBtn = document.getElementsByTagName("button")

function getShoppingCart(){
    const id = localStorage.getItem("shoppingCartId")
    getCart(id)
}

const getCart = (id) => {
    axios.get(`${baseURL}/api/shopping-cart/${id}`)
    .then(({ data }) => {
        cart = data
        displayCart()
    })
}

const displayCart = () => {
    shoppingCartTable.innerHTML = ""
    console.log("display ",cart);
    cart.cartRows.forEach(row => {
        shoppingCartTable.innerHTML += makeItem(row)
    });
    finalElement.innerText = '$' + cart.totalPrice
}

const makeItem = (item) => {
    return `
        <tr>
            <td>${item.name}</td>
            <td align="center">${item.quantity}</td>
            <td align="center">${item.price}</td>
        </tr>`
}

window.onload = getShoppingCart();