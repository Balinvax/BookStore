//АНІМАЦІЯ БАНЕРІВ

document.addEventListener("DOMContentLoaded", function() {
    const banners = document.querySelectorAll(".banner");
    let currentIndex = 0;

    function showBanner(index) {
        banners.forEach(function(banner, i) {
            if (i === index) {
                banner.style.transform = "translateX(0)";
            } else {
                banner.style.transform = "translateX(100%)";
            }
        });
    }

    function nextBanner() {
        currentIndex = (currentIndex + 1) % banners.length;
        showBanner(currentIndex);
    }

    setInterval(nextBanner, 3000);
});

//ФІКСАЦІЯ ХЕДЕРА

window.onscroll = function() { myFunction() };

var header = document.getElementById("myHeader");

var sticky = header.offsetTop;

function myFunction() {
    if (window.pageYOffset >= sticky) {
        header.classList.add("sticky");
        document.body.style.paddingTop = header.offsetHeight + 'px'; // Додати додатковий відступ до верхнього краю сторінки, щоб уникнути перекриття контенту фіксованим хедером
    } else {
        header.classList.remove("sticky");
        document.body.style.paddingTop = 0; // Зняти відступ, коли хедер не фіксований
    }
}




//ДОДАВАННЯ ТОВАРІВ У КОШИК
//ІНДИКАТОР КІЛЬКОСТІ ТОВАРІВ У КОШИКУ

document.addEventListener("DOMContentLoaded", function() {
    const addToCartButtons = document.querySelectorAll(".book-item .add-to-cart-btn, .sale-item .add-to-cart-btn");

    addToCartButtons.forEach(button => {
        button.addEventListener("click", function() {
            const parentElement = this.closest(".book-item, .sale-item");
            if (!parentElement) {
                return; // Перевіряємо, чи кнопка знаходиться в book-item або sale-item
            }

            const titleElement = parentElement.querySelector("h2");
            const priceElement = parentElement.querySelector("p");

            if (!titleElement || !priceElement) {
                return; // Перевіряємо, чи знайдено всі необхідні елементи
            }

            const title = titleElement.innerText;
            const price = priceElement.innerText;

            addToCart(title, price);
            updateCartQuantity(); // Оновлюємо кількість товарів у кошику
        });
    });

    function addToCart(title, price) {
        let cart = JSON.parse(localStorage.getItem("cart")) || [];
        let existingItem = cart.find(item => item.title === title);

        if (existingItem) {
            existingItem.quantity = (existingItem.quantity || 1) + 1;
        } else {
            cart.push({ title: title, price: price, quantity: 1 });
        }

        localStorage.setItem("cart", JSON.stringify(cart));
    }

    // Функція для оновлення кількості товарів у червоному кружку
    function updateCartQuantity() {
        // Отримуємо дані з localStorage та підраховуємо кількість товарів
        let cartItems = JSON.parse(localStorage.getItem("cart")) || [];
        let totalQuantity = cartItems.reduce((total, item) => total + item.quantity, 0);

        // Знаходимо елемент, в який будемо вставляти кількість товарів
        let cartQuantityElement = document.getElementById("cart-quantity");

        // Оновлюємо вміст елементу з кількістю товарів
        cartQuantityElement.textContent = totalQuantity.toString(); // або String(totalQuantity);

        // Відображаємо або приховуємо червоний кружок в залежності від кількості товарів
        if (totalQuantity > 0) {
            cartQuantityElement.style.display = "block";
        } else {
            cartQuantityElement.style.display = "none";
        }
    }

    // Додаємо обробник події для оновлення кількості при завантаженні сторінки
    updateCartQuantity();

    // Додаємо обробник події для оновлення кількості при зміні кошика
    window.addEventListener("storage", updateCartQuantity);
});

