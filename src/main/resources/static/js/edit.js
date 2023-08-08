/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


 $(document).ready(function () {
     
     let locationbtn = document.querySelector('#locationbtn');
     let locationSubmit = document.querySelector('#locationSubmit');
     let serialSubmit = document.querySelector('#serialSubmit');
     let inventarySubmit = document.querySelector('#inventarySubmit');
     
     $('#locationSelect').selectize({
                  preload: true,
                  valueField: 'name',
                  labelField: 'name',
                  searchField: "name",
                  load: function (query, callback) {
                    $.ajax({
                        url: 'http://localhost:8080/locations',
                        type: 'GET',
                        dataType: 'json',
                        data: {model:query},
                        error: callback,
                        success: callback
                         });
                }
         
     });
     

     
          inventarySubmit.addEventListener('click', function() {
         
         div = $('#inventaryRefresh')[0];
         
         console.log("click");
          $.ajax({
            type: "POST",
            url: "/editprinterinventary",
            data: {id: input.id, inventaryNumber: $('#inventaryNumberInput')[0].value},
            dataType: "text",
            success: function(data) {
                div.innerText = $('#inventaryNumberInput')[0].value;
                
                console.log(data);
                
                $('#inventarynumberModal').modal('hide');
              
            }
        });
     });
     
     
     
     
     serialSubmit.addEventListener('click', function() {
         
         div = $('#serialRefresh')[0];
         
         console.log("click");
          $.ajax({
            type: "POST",
            url: "/editprinterserial",
            data: {id: input.id, serialNumber: $('#serialNumberInput')[0].value},
            dataType: "text",
            success: function(data) {
                div.innerText = $('#serialNumberInput')[0].value;
                
                console.log(data);
                
                $('#serialnumberModal').modal('hide');
              
            }
        });
     });
     
     
     
     locationbtn.addEventListener('click', function() {
        $('#locationSelect')[0].selectize.setValue(Object.entries($('#locationSelect')[0].selectize.options)[0][1].name); 
     });
     
    locationSubmit.addEventListener('click', function() {
         var div = $('#locationRefresh')[0];
           
            
            
        $.ajax({
            type: "POST",
            url: "/editprinterlocation",
            data: {id: input.id, location: $('#locationSelect')[0].selectize.getValue()},
            dataType: "text",
            success: function(data) {
                div.innerText = $('#locationSelect')[0].selectize.getValue();
                
                console.log(data);
                
                $('#staticBackdrop').modal('hide');
              
            }
        });
        
        
    });
     
 });
 
 
