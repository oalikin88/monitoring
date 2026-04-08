/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


function AJAXPing() {
    $.post('AJAXPing', function (data) {
        if (data !== 0) {
            alert(data);
        }
    }
    );
};