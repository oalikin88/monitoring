/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

$(document).ready(function () {
    
    $(document).ready(function () {
   selectDeviceType = document.querySelector('#selectDeviceType');
   
   printerLink = document.querySelector('#printerLink');
   cartridgeLink = document.querySelector('#cartridgeLink');
   printerLink.addEventListener('click', function(event) {
            console.log(event.target);
          
           var t = window.location.href.indexOf("?");
           
           var d = window.location.href.substr(0, t);
           var n = window.location.href.substr(t, window.location.href.length);
           var device = "/printer";
           
           
           
               window.location.href = d + device + n;
            
   });
   
    cartridgeLink.addEventListener('click', function(event) {
            console.log(event.target);
          
           var t = window.location.href.indexOf("?");
           
           var d = window.location.href.substr(0, t);
           var n = window.location.href.substr(t, window.location.href.length);
           var device = "/cartridge";
           
           
           
               window.location.href = d + device + n;
            
   });
   
//    if(window.location.href.indexOf("PRINTER") >= 0) {
//        selectDeviceType.children[1].selected = true;
//    } else if(window.location.href.indexOf("MFU") >= 0) {
//        selectDeviceType.children[2].selected = true;
//    } else {
//        selectDeviceType.children[0].selected = true;
//    }
    
});
    
});

