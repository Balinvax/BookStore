document.addEventListener('DOMContentLoaded', function () {
    const bannerContainer = document.querySelector('.banner-carousel');
    const banners = document.querySelectorAll('.banner');

    let currentBannerIndex = 0;

    function showNextBanner() {
        const nextBannerIndex = (currentBannerIndex + 1) % banners.length;
        banners[currentBannerIndex].style.animation = ''; // Скидаємо попередню анімацію
        banners[nextBannerIndex].style.animation = 'bannerAnimation 10s infinite'; // Запускаємо анімацію для наступного банера
        currentBannerIndex = nextBannerIndex;
    }

    setInterval(showNextBanner, 10000); // Показуємо наступний банер кожні 10 секунд
});
