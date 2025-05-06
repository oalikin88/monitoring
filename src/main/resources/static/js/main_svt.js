

const hamburger = document.querySelector('#toggle-btn');
let sidebar = document.getElementById('sidebar');
let sidebar_content = document.getElementById('wrap');
hamburger.addEventListener("click", function() {
    document.querySelector('#sidebar').classList.toggle("expand");
});

//document.getElementById('sticky-aside').addEventListener('wheel', function(event) {
//    // Получаем текущее значение скролла 
//   const currentScroll = this.scrollTop; 
//   //// Определяем направление вращения колеса (вверх/вниз) 
//   this.scrollTop = currentScroll + 100; 
//    
//    // Отменяем стандартное поведение браузера (например, страницу целиком) event.preventDefault(); 
//    });
