/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


const hamburger = document.querySelector('#toggle-btn');

hamburger.addEventListener("click", function() {
    document.querySelector('#sidebar').classList.toggle("expand");
});