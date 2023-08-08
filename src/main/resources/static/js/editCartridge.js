/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


$(document).ready(function () {
    
    let locationbtn = document.querySelector('#locationbtn');
     let locationSubmit = document.querySelector('#locationSubmit');
     let utilSubmit = document.querySelector('#utilSubmit');
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
     
     
          locationbtn.addEventListener('click', function() {
        $('#locationSelect')[0].selectize.setValue(Object.entries($('#locationSelect')[0].selectize.options)[0][1].name); 
     });
     
    utilSubmit.addEventListener('click', function() {
         if(utilSelect.value === "true") {
             console.log("true");
            $.ajax({
            type: "POST",
            url: "/utilCartridge",
            data: {id: input.id},
            dataType: "text",
            success: function(data) {
                $('#utilModal').modal('hide');
            }
        });
         } else {
             $('#utilModal').modal('hide');
         }  
            
            
    
        
        
    });
    
    
        locationSubmit.addEventListener('click', function() {
         var div = $('#locationRefresh')[0];
           
            
            
        $.ajax({
            type: "POST",
            url: "/editcartridgelocation",
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