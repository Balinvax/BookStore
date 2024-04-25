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


document.addEventListener("DOMContentLoaded", function() {
    const addToCartButtons = document.querySelectorAll(".sale-item button");

    addToCartButtons.forEach(button => {
        button.addEventListener("click", function() {
            const saleItem = this.closest(".sale-item");
            const title = saleItem.querySelector("h2").innerText;
            const price = saleItem.querySelector("p").innerText;

            addToCart(title, price);
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
});




window.onscroll = function() {myFunction()};


var header = document.getElementById("myHeader");


var sticky = header.offsetTop;


function myFunction() {
    if (window.pageYOffset >= sticky) {
        header.classList.add("sticky");
    } else {
        header.classList.remove("sticky");
    }
}