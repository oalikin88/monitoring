/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


let addPlaceBtn = document.querySelector('#addPlaceBtn');
let btnSavePlace = document.querySelector('#btnSavePlace');

addPlaceBtn.addEventListener('click', function() {
   
    $('#locationSelect').selectize({
        preload: true,
        valueField: 'id',
        labelField: 'name',
        searchField: ["id", "name"],
        load: function (query, callback) {
            $.ajax({
                url: '/locations',
                type: 'GET',
                async: false,
                dataType: 'json',
                error: callback,
                success: callback
            });
           // $('#locationSelect')[0].selectize.setValue( $('#locationSelect')[0].selectize.search(input.locationId).items[0].id);
        }

    });
    
     $('#departmentSelect').selectize({
        preload: true,
        valueField: 'code',
        labelField: 'name',
        searchField: ["code", "name"],
        load: function (query, callback) {
            $.ajax({
                url: '/departments',
                type: 'GET',
                async: false,
                dataType: 'json',
                error: callback,
                success: callback
            });
           // $('#locationSelect')[0].selectize.setValue( $('#locationSelect')[0].selectize.search(input.locationId).items[0].id);
        }

    });
    
    
    $('#employeeSelect').selectize({
        preload: true,
        valueField: 'name',
        labelField: 'name',
        searchField: ["code", "name"],
        load: function (query, callback) {
            $.ajax({
                url: '/getinfooo',
                type: 'GET',
                async: false,
                dataType: 'json',
                error: callback,
                success: callback
            });
           // $('#locationSelect')[0].selectize.setValue( $('#locationSelect')[0].selectize.search(input.locationId).items[0].id);
        }

    });
    
    btnSavePlace.addEventListener('click', function() {
        let dto = {
            placeType : document.querySelector('#placeTypeSelect').value,
            locationId : document.querySelector('#locationSelect').value,
            departmentCode : document.querySelector('#departmentSelect').value,
            department : $('#departmentSelect')[0].selectize.$control[0].innerText,
            username : document.querySelector('#employeeSelect').value
        };
                $.ajax({
        type: "POST",
        url: "/places/",
        data: JSON.stringify(dto),
        async: false,
        success: function () {
            
            window.location.reload();
        },
        error: function(callback) {
            if($('#exceptionContainer').length == 0) {
             $('#modalBody').append(callback.responseText);
         } else {
             
             $('#exceptionContainer').replaceWith(callback.responseText);
            
         }

            new bootstrap.Modal(document.getElementById('modalError')).show();
           // $('#resultInfo').append(callback.responseText);
  }, 
        processData: false,
        contentType: 'application/json'
    

    });
    });
    
  window.addEventListener('scroll', function() {
  var stickyAside = document.getElementById('sticky-aside');
  var container = stickyAside.closest('.container');
  var containerTop = container.offsetTop;
  var containerBottom = containerTop + container.offsetHeight;
  var asideHeight = stickyAside.offsetHeight;

  if (window.pageYOffset > containerTop && window.pageYOffset < containerBottom - asideHeight) {
    stickyAside.classList.add('sticky');
  } else {
    stickyAside.classList.remove('sticky');
  }
});

    
});