document.addEventListener("DOMContentLoaded", function() {
    const cartItemsDiv = document.getElementById("cart-items");
    const totalQuantityElement = document.getElementById("total-quantity");
    const totalPriceElement = document.getElementById("total-price");
    const checkoutBtn = document.getElementById("checkout-btn");
    let cart = [];

    function loadCart() {
        const cartData = localStorage.getItem("cart");
        if (cartData) {
            cart = JSON.parse(cartData);
            renderCart();
        }
    }

    function renderCart() {
        cartItemsDiv.innerHTML = "";
        let totalQuantity = 0;
        let totalPrice = 0;

        cart.forEach((item) => {
            const cartItem = document.createElement("div");
            cartItem.classList.add("cart-item");
            cartItem.innerHTML = `
            <h3>${item.title}</h3>
            <p>${item.price}</p>
            <label for="quantity${item.title}">Кількість:</label>
            <input type="number" id="quantity${item.title}" class="quantity-input" value="${item.quantity}" min="1">
            <button class="remove-btn" data-title="${item.title}">Видалити</button>
        `;
            cartItemsDiv.appendChild(cartItem);

            totalQuantity += item.quantity;
            // Перевіряємо, чи присутній текст у ціні
            if (item.price.includes("Ціна зі знижкою: ")) {
                // Якщо присутній текст, видаляємо його та конвертуємо ціну у число
                const priceString = item.price.replace("Ціна зі знижкою: ", "");
                totalPrice += parseFloat(priceString) * item.quantity;
            } else {
                // Якщо тексту немає, просто додаємо ціну
                totalPrice += parseFloat(item.price) * item.quantity;
            }
        });

        totalQuantityElement.textContent = totalQuantity;
        totalPriceElement.textContent = totalPrice.toFixed(2);
    }


    loadCart();

    // Обробник кнопки видалення товару з кошика
    cartItemsDiv.addEventListener("click", function(event) {
        if (event.target.classList.contains("remove-btn")) {
            const title = event.target.dataset.title;
            cart = cart.filter(item => item.title !== title);
            localStorage.setItem("cart", JSON.stringify(cart));
            renderCart();
        }
    });

    // Обробник зміни кількості товару
    cartItemsDiv.addEventListener("input", function(event) {
        if (event.target.classList.contains("quantity-input")) {
            const title = event.target.id.replace("quantity", "");
            const newQuantity = parseInt(event.target.value);
            cart.forEach(item => {
                if (item.title === title) {
                    item.quantity = newQuantity;
                }
            });
            localStorage.setItem("cart", JSON.stringify(cart));
            renderCart(); // Оновлення відображення кошика після зміни кількості товару
        }
    });

});
