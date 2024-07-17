/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


let addPlaceBtn = document.querySelector('#addPlaceBtn');
let btnSavePlace = document.querySelector('#btnSavePlace');
const modalPlace = document.getElementById("addPlaceModal");
let modalParent = document.querySelector("#modalContent");
let dto = new Object();

let handleClickSearchSvtObject = function(input) {
    let request = "/places?username=";
    
        window.location.href = request + input;
}

let modalContentLoad = function (idPlace) {
    
    
    if(null != idPlace) {
        
        $.ajax({
            url: "/placebyid?placeId=" + idPlace,
            type: 'GET',
            async: false,
            dataType: 'json',
            success: function (callback) {
                dto.placeType = callback.placeType;
                dto.username = callback.username;
                dto.locationId = callback.locationId;
                dto.department = callback.department;
                dto.departmentCode = callback.departmentCode;
                dto.placeId = callback.placeId;
            }
        });
    }
    
    //сборка хеадер
    let divModalHeader = document.createElement("div");
    divModalHeader.className = "modal-header";
    
    let titleModalHeader = document.createElement("h5");
    titleModalHeader.className = "modal-title fs-5";
    titleModalHeader.id = "exampleModalLabel";
    if (null != idPlace) {
    titleModalHeader.innerText = "Редактирование рабочего места";
    } else {
        titleModalHeader.innerText = "Добавление рабочего места";
    }
    let closeBtnHeader = document.createElement("button");
    closeBtnHeader.className = "btn-close btn-close-white";
    closeBtnHeader.setAttribute("data-bs-dismiss", "modal");
    closeBtnHeader.setAttribute("aria-label", "Close");
    closeBtnHeader.type = "button";
    
    divModalHeader.appendChild(titleModalHeader);
    divModalHeader.appendChild(closeBtnHeader);
    modalParent.appendChild(divModalHeader);
    
    //сборка боди
    
    let modalBody = document.createElement("div");
    modalBody.className = "modal-body";
    
    let modalBodyContainer = document.createElement("div");
    modalBodyContainer.className = "container";
    
    let divRowPlaceType = document.createElement("div");
    divRowPlaceType.className = "row";
    
    let divColLabelPlaceType = document.createElement("div");
    divColLabelPlaceType.className = "col";
    divColLabelPlaceType.innerText = "Тип рабочего места";
    
    let divColSelectPlaceType = document.createElement("div");
    divColSelectPlaceType.className = "col";
    
    let selectPlaceType = document.createElement("select");
    selectPlaceType.className = "form-select form-select-sm";
    selectPlaceType.id = "placeTypeSelect";
    
    
    let optionPlaceTypeEmployee = document.createElement("option");
    optionPlaceTypeEmployee.value = "Сотрудник";
    optionPlaceTypeEmployee.innerText = "Сотрудник";
   // optionPlaceTypeEmployee.selected = true;
    
    let optionPlaceTypeServerRoom = document.createElement("option");
    optionPlaceTypeServerRoom.value = "Серверная";
    optionPlaceTypeServerRoom.innerText = "Серверная";
    
    let optionPlaceTypeOfficceEquipment = document.createElement("option");
    optionPlaceTypeOfficceEquipment.value = "Оргтехника";
    optionPlaceTypeOfficceEquipment.innerText = "Оргтехника";
    
    let optionPlaceTypeStorage = document.createElement("option");
    optionPlaceTypeStorage.value = "Склад";
    optionPlaceTypeStorage.innerText = "Склад";
    
    selectPlaceType.appendChild(optionPlaceTypeEmployee);
    selectPlaceType.appendChild(optionPlaceTypeServerRoom);
    selectPlaceType.appendChild(optionPlaceTypeOfficceEquipment);
    selectPlaceType.appendChild(optionPlaceTypeStorage);
    
    
    if (null != idPlace) {
        switch (dto.placeType) {
            case "Сотрудник":
                optionPlaceTypeEmployee.selected = true;
                break;
            case "Серверная":
                optionPlaceTypeServerRoom.selected = true    
                break;
            case "Оргтехника":
                optionPlaceTypeOfficceEquipment.selected = true;
                break;
            case "Склад":
               optionPlaceTypeStorage.selected = true;
                break;
        }
    } else {
        optionPlaceTypeEmployee.selected = true;
    }
    
    divColSelectPlaceType.appendChild(selectPlaceType);
    divRowPlaceType.appendChild(divColLabelPlaceType);
    divRowPlaceType.appendChild(divColSelectPlaceType);
    modalBodyContainer.appendChild(divRowPlaceType);
    
    let divRowLocation = document.createElement("div");
    divRowLocation.className = "row mt-2";
    
    let divColLabelLocation = document.createElement("div");
    divColLabelLocation.className = "col";
    divColLabelLocation.innerText = "Район"
    
    let divColLocationSelect = document.createElement("div");
    divColLocationSelect.className = "col";
    
    let locationSelect = document.createElement("select");
    locationSelect.className = "form-select form-select-sm";
    locationSelect.id = "locationSelect";
    
    divColLocationSelect.appendChild(locationSelect);
    divRowLocation.appendChild(divColLabelLocation);
    divRowLocation.appendChild(divColLocationSelect);
    modalBodyContainer.appendChild(divRowLocation);
    let divRowDepartment = document.createElement("div");
    divRowDepartment.className = "row mt-2";
    
    let divColDepartmentLabel = document.createElement("div");
    divColDepartmentLabel.className = "col";
    divColDepartmentLabel.innerText = "Отдел";
    
    let divColDepartmentSelect = document.createElement("div");
    divColDepartmentSelect.className = "col";
    
    let departmentSelect = document.createElement("select");
    departmentSelect.className = "form-select form-select-sm";
    departmentSelect.id = "departmentSelect";
    
    divColDepartmentSelect.appendChild(departmentSelect);
    divRowDepartment.appendChild(divColDepartmentLabel);
    divRowDepartment.appendChild(divColDepartmentSelect);
    modalBodyContainer.appendChild(divRowDepartment);
    let divRowEmployee = document.createElement("div");
    divRowEmployee.className = "row mt-2";
    
    let divColEmployeeLabel = document.createElement("div");
    divColEmployeeLabel.className = "col";
    divColEmployeeLabel.innerText = "ФИО";
    
    let divColEmployeeSelect = document.createElement("div");
    divColEmployeeSelect.className = "col";
    
    let employeeSelect = document.createElement("select");
    employeeSelect.className = "form-select form-select-sm";
    employeeSelect.id = "employeeSelect";
    
    
    divColEmployeeSelect.appendChild(employeeSelect);
    divRowEmployee.appendChild(divColEmployeeLabel);
    divRowEmployee.appendChild(divColEmployeeSelect);
    modalBodyContainer.appendChild(divRowEmployee);
    modalBody.appendChild(modalBodyContainer);
    modalParent.appendChild(modalBody);
    //сборка футера
    
    let modalFooter = document.createElement("div");
    modalFooter.className = "modal-footer";
    
    if(null != idPlace) {
        let btnDelete = document.createElement("button");
        btnDelete.className = "btn btn-danger btn-sm";
        btnDelete.id = "archivedBtn";
        btnDelete.type = "button";
        btnDelete.innerText = "Удалить";
        
        modalFooter.appendChild(btnDelete);
        
        let btnCancel = document.createElement("button");
        btnCancel.className = "btn btn-secondary btn-sm";
        btnCancel.type = "button";
        btnCancel.setAttribute("data-bs-dismiss", "modal");
        btnCancel.innerText = "Отменить";
        
        modalFooter.appendChild(btnCancel);
        
        let btnConfirm = document.createElement("button");
        btnConfirm.className = "btn btn-primary btn-sm";
        btnConfirm.id = "btnConfirm";
        btnConfirm.type = "button";
        btnConfirm.innerText = "Применить";
        
        modalFooter.appendChild(btnConfirm);
    } else {
        let btnCancel = document.createElement("button");
        btnCancel.className = "btn btn-secondary btn-sm";
        btnCancel.type = "button";
        btnCancel.setAttribute("data-bs-dismiss", "modal");
        btnCancel.innerText = "Отменить";
        
        modalFooter.appendChild(btnCancel);
        
        let btnSave = document.createElement("button");
        btnSave.className = "btn btn-primary btn-sm";
        btnSave.id = "btnSave";
        btnSave.type = "button";
        btnSave.innerText = "Сохранить";
        
        modalFooter.appendChild(btnSave);
        
    }
    
    modalParent.appendChild(modalFooter);
    
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
            if(null != idPlace) {
                
            $('#locationSelect')[0].selectize.setValue( $('#locationSelect')[0].selectize.search(dto.locationId).items[0].id);
            } else {
            
            $('#locationSelect')[0].selectize.setValue( $('#locationSelect')[0].selectize.search(0).items[0].id);
            }
            
           // 
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
            if(null != idPlace) {
                
                $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(dto.departmentCode).items[0].id);
            } else {
                $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(0).items[0].id);
            }
            
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
            if(null != idPlace) {
            $('#employeeSelect')[0].selectize.setValue($('#employeeSelect')[0].selectize.search(dto.username).items[0].id);
            } else{
               $('#employeeSelect')[0].selectize.setValue($('#employeeSelect')[0].selectize.search(0).items[0].id);
            }
            
        }

    });
    
    if($("#archivedBtn")[0] != null) {
        $("#archivedBtn")[0].addEventListener("click", function() {
            
            let outDto = {
            placeId: idPlace,
            placeType : document.querySelector('#placeTypeSelect').value,
            locationId : document.querySelector('#locationSelect').value,
            departmentCode : document.querySelector('#departmentSelect').value,
            department : $('#departmentSelect')[0].selectize.$control[0].innerText,
            username : document.querySelector('#employeeSelect').value
        };
        
        
         $.ajax({
        type: "POST",
        url: "/placetoarchive",
        data: JSON.stringify(outDto),
        async: false,
        success: function () {
            
                    window.location.reload();
        },
        error: function(callback) {
                    console.log(callback);
  }, 
        processData: false,
        contentType: 'application/json'
    

    });
            
            
        });
    }
    
    if($("#btnConfirm")[0] != null) {
        $("#btnConfirm")[0].addEventListener("click", function() {
            let outDto = {
            placeId: idPlace,
            placeType : document.querySelector('#placeTypeSelect').value,
            locationId : document.querySelector('#locationSelect').value,
            departmentCode : document.querySelector('#departmentSelect').value,
            department : $('#departmentSelect')[0].selectize.$control[0].innerText,
            username : document.querySelector('#employeeSelect').value
        };
                $.ajax({
        type: "POST",
        url: "/places/",
        data: JSON.stringify(outDto),
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
    }
    
    if($("#btnSave")[0] != null) {
        
        $("#btnSave")[0].addEventListener("click", function() {
            
        let outDto = {
            placeType : document.querySelector('#placeTypeSelect').value,
            locationId : document.querySelector('#locationSelect').value,
            departmentCode : document.querySelector('#departmentSelect').value,
            department : $('#departmentSelect')[0].selectize.$control[0].innerText,
            username : document.querySelector('#employeeSelect').value
        };
                $.ajax({
        type: "POST",
        url: "/places/",
        data: JSON.stringify(outDto),
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
        
    }
    
};


//addPlaceBtn.addEventListener('click', function() {
   
//    $('#locationSelect').selectize({
//        preload: true,
//        valueField: 'id',
//        labelField: 'name',
//        searchField: ["id", "name"],
//        load: function (query, callback) {
//            $.ajax({
//                url: '/locations',
//                type: 'GET',
//                async: false,
//                dataType: 'json',
//                error: callback,
//                success: callback
//            });
//           // $('#locationSelect')[0].selectize.setValue( $('#locationSelect')[0].selectize.search(input.locationId).items[0].id);
//        }
//
//    });
//    
//     $('#departmentSelect').selectize({
//        preload: true,
//        valueField: 'code',
//        labelField: 'name',
//        searchField: ["code", "name"],
//        load: function (query, callback) {
//            $.ajax({
//                url: '/departments',
//                type: 'GET',
//                async: false,
//                dataType: 'json',
//                error: callback,
//                success: callback
//            });
//           // $('#locationSelect')[0].selectize.setValue( $('#locationSelect')[0].selectize.search(input.locationId).items[0].id);
//        }
//
//    });
//    
//    
//    $('#employeeSelect').selectize({
//        preload: true,
//        valueField: 'name',
//        labelField: 'name',
//        searchField: ["code", "name"],
//        load: function (query, callback) {
//            $.ajax({
//                url: '/getinfooo',
//                type: 'GET',
//                async: false,
//                dataType: 'json',
//                error: callback,
//                success: callback
//            });
//           // $('#locationSelect')[0].selectize.setValue( $('#locationSelect')[0].selectize.search(input.locationId).items[0].id);
//        }
//
//    });
    
//    btnSavePlace.addEventListener('click', function() {
//        let dto = {
//            placeType : document.querySelector('#placeTypeSelect').value,
//            locationId : document.querySelector('#locationSelect').value,
//            departmentCode : document.querySelector('#departmentSelect').value,
//            department : $('#departmentSelect')[0].selectize.$control[0].innerText,
//            username : document.querySelector('#employeeSelect').value
//        };
//                $.ajax({
//        type: "POST",
//        url: "/places/",
//        data: JSON.stringify(dto),
//        async: false,
//        success: function () {
//            
//            window.location.reload();
//        },
//        error: function(callback) {
//            if($('#exceptionContainer').length == 0) {
//             $('#modalBody').append(callback.responseText);
//         } else {
//             
//             $('#exceptionContainer').replaceWith(callback.responseText);
//            
//         }
//
//            new bootstrap.Modal(document.getElementById('modalError')).show();
//           // $('#resultInfo').append(callback.responseText);
//  }, 
//        processData: false,
//        contentType: 'application/json'
//    
//
//    });
//    });
    

    
//});

window.onload = function() {
    let elem = document.querySelectorAll('.element');
    $("#searchSvtObjBtn")[0].addEventListener("click", function() {
        handleClickSearchSvtObject($("#searchSvtObjInput")[0].value);
    });
    
    addPlaceBtn.addEventListener("click", function() {
        modalContentLoad();
    });
    
    for (let i = 0; i < elem.length; i++) {
        elem[i].addEventListener("click", function (event) {
            modalContentLoad($(this)[0].id);

        });
    }
    
    modalPlace.addEventListener('hidden.bs.modal', function(event) {
        modalParent.innerHTML = "";
    });
    
//   window.addEventListener('scroll', function() {
//  var stickyAside = document.getElementById('sticky-aside');
//  var container = stickyAside.closest('.container');
//  var containerTop = container.offsetTop;
//  var containerBottom = containerTop + container.offsetHeight;
//  var asideHeight = stickyAside.offsetHeight;
//
//  if (window.pageYOffset > containerTop && window.pageYOffset < containerBottom - asideHeight) {
//    stickyAside.classList.add('sticky');
//  } else {
//    stickyAside.classList.remove('sticky');
//  }
//});

};