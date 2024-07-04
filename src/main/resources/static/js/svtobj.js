/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

const addPlaceBtn = document.querySelector('#addPlaceBtn');
let parent = document.querySelector('.tree');
const modalAddPhone = document.getElementById('addPlaceModal');
let dateCreate = document.querySelector('#dateCreateSelect');
var currentYear = new Date().getFullYear();
var departmentSelect;
var locationId;
var oldPlaceId;
var changedPlaceId;
var oldLocationId;
var changedLocationId;
var oldDepartment;
var innerCallNumber;
let idSvtObj;
let idPlace;
let codeDepartment;
let modelId;
let status;
let inventary;
let serial;
let yearCreated;
let nameFromOneC;
let baseType;
let startExploitation;
let parseStartDate;
let toggle = false;
let phoneRows = [...document.querySelectorAll('.phone')];
let locationRows = [...document.querySelectorAll('.location')];
let departmentRows = [...document.querySelectorAll('.department')];
let dateReplaceBattery;
let batteryAmount;
let dynamicLabel;
let dynamicField;
let batteryTypeId;
let eventReasonGlobal;
let backtoStorageFlag;
let stor;

let handleClickArchivedBtn = function() {
    let requestLink;
       let dto = {
                    archived: true,
                    id: idSvtObj,

                };
                
        switch (attrib) {
        case "phones":
            requestLink = "/phonearchived";
            break;
        case "monitors":
            requestLink = "/monitorarchived";
            break;
        case "ups":
            requestLink = "/upsarchived";
            break;
    }
        $.ajax({
                    type: "POST",
                    url: requestLink,
                    data: JSON.stringify(dto),
                    async: false,
                    success: function () {
                        
                        window.location.reload();
                        
                    },
                    error: function (callback) {
                        if ($('#exceptionContainer').length == 0) {
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
}


let handleClickSendToStorageBtn = function () {
               let requestLink;
           
                let dto = {
                    model: document.querySelector('#modelSelect').innerText,
                    status: document.querySelector('#statusSelect').value,
                    inventaryNumber: document.querySelector('#inventaryNumber').value,
                    serialNumber: document.querySelector('#serialNumber').value,
                    yearCreated: document.querySelector('#dateCreateSelect').value,
                    dateExploitationBegin: document.querySelector('#startExploitation').value,
                    placeId: document.querySelector('#placeSelect').value,
                    id: idSvtObj,
                    modelId: document.querySelector('#modelSelect').value,
                    locationId: locationId,
                };
                
                    switch (attrib) {
        case "phones":
            dto.phoneNumber = $("#innerCallNumber")[0].value;
            requestLink = "/phonetostor";
            break;
        case "monitors":
            dto.nameFromOneC = document.querySelector('#nameFromOneC').value;
            dto.baseType = document.querySelector('#baseTypeSelect').value;
            requestLink = "/monitortostor";
            break;
        case "ups":
            
            requestLink = "/upstostor";
            dto.batteryTypeId = $("#batteryTypeSelect")[0].selectize.getValue();
            dto.batteryAmount = document.querySelector("#batteryAmount").value;
            dto.yearReplacement = document.querySelector("#dateReplaceSelect").value;
            break;
    }

                $.ajax({
                    type: "POST",
                    url: requestLink,
                    data: JSON.stringify(dto),
                    async: false,
                    success: function () {
                        
                        window.location.reload();
                        
                    },
                    error: function (callback) {
                        if ($('#exceptionContainer').length == 0) {
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
                
            };
            
            
            
let handleClickBackToStorageBtn = function () {
                   let requestLink;
           
                let dto = {
                    model: document.querySelector('#modelSelect').innerText,
                    status: document.querySelector('#statusSelect').value,
                    inventaryNumber: document.querySelector('#inventaryNumber').value,
                    serialNumber: document.querySelector('#serialNumber').value,
                    yearCreated: document.querySelector('#dateCreateSelect').value,
                    dateExploitationBegin: document.querySelector('#startExploitation').value,
                    placeId: document.querySelector('#placeSelect').value,
                    id: idSvtObj,
                    modelId: document.querySelector('#modelSelect').value,
                    locationId: locationId,
                };
                
                    switch (attrib) {
        case "phones":
            dto.phoneNumber = $("#innerCallNumber")[0].value;
            requestLink = "/phonebackstor";
            break;
        case "monitors":
            dto.nameFromOneC = document.querySelector('#nameFromOneC').value;
            dto.baseType = document.querySelector('#baseTypeSelect').value;
            requestLink = "/monitorbackstor";
            break;
        case "ups":
            requestLink = "/upsbackstor";
            dto.batteryTypeId = $("#batteryTypeSelect")[0].selectize.getValue();
            dto.batteryAmount = document.querySelector("#batteryAmount").value;
            dto.yearReplacement = document.querySelector("#dateReplaceSelect").value;
            break;
    }

                $.ajax({
                    type: "POST",
                    url: requestLink,
                    data: JSON.stringify(dto),
                    async: false,
                    success: function () {
                        
                        window.location.reload();
                        
                    },
                    error: function (callback) {
                        if ($('#exceptionContainer').length == 0) {
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

            };


let handleClickUpdateBtn = function () {
    let requestLink;
    
    let dto = {
        model: document.querySelector('#modelSelect').innerText,
        status: document.querySelector('#statusSelect').value,
        inventaryNumber: document.querySelector('#inventaryNumber').value,
        serialNumber: document.querySelector('#serialNumber').value,
        yearCreated: document.querySelector('#dateCreateSelect').value,
        dateExploitationBegin: document.querySelector('#startExploitation').value,
        placeId: document.querySelector('#placeSelect').value,
        id: idSvtObj,
        modelId: document.querySelector('#modelSelect').value,
        
    };
    switch (attrib) {
        case "phones":
            dto.phoneNumber = $("#innerCallNumber")[0].value;
            requestLink = "/updphone";
            break;
        case "monitors":
            dto.nameFromOneC = document.querySelector('#nameFromOneC').value;
            dto.baseType = document.querySelector('#baseTypeSelect').value;
            requestLink = "/updmonitor";
            break;
        case "ups":
            requestLink = "/updups";
            dto.batteryTypeId = $("#batteryTypeSelect")[0].selectize.getValue();
            dto.batteryAmount = document.querySelector("#batteryAmount").value;
            dto.yearReplacement = document.querySelector("#dateReplaceSelect").value;
            break;
    }
    $.ajax({
        type: "POST",
        url: requestLink,
        data: JSON.stringify(dto),
        async: false,
        success: function () {

            window.location.reload();
        },
        error: function (callback) {
            if ($('#exceptionContainer').length == 0) {
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



};




let handleClickSavePhoneBtn = function () {
    let requestLink;
    
    let dto = {
        model: document.querySelector('#modelSelect').innerText,
        status: document.querySelector('#statusSelect').value,
        inventaryNumber: document.querySelector('#inventaryNumber').value,
        serialNumber: document.querySelector('#serialNumber').value,
        yearCreated: document.querySelector('#dateCreateSelect').value,
        dateExploitationBegin: document.querySelector('#startExploitation').value,
        placeId: document.querySelector('#placeSelect').value,
        id: idSvtObj,
        modelId: document.querySelector('#modelSelect').value,
        
    };
    switch (attrib) {
        case "phones":
            dto.phoneNumber = $("#innerCallNumber")[0].value;
            requestLink = "/phones";
            break;
        case "monitors":
            dto.nameFromOneC = document.querySelector('#nameFromOneC').value;
            dto.baseType = document.querySelector('#baseTypeSelect').value;
            requestLink = "/monitors";
            break;
        case "ups":
            requestLink = "/ups";
            dto.batteryTypeId = $("#batteryTypeSelect")[0].selectize.getValue();
            dto.batteryAmount = document.querySelector("#batteryAmount").value;
            dto.yearReplacement = document.querySelector("#dateReplaceSelect").value;
            break;
        case "systemblock":
            requestLink = "/sysblocks";
            dto.license = 
            dto.motherboardId = $("#motherboardSelect")[0].selectize.getValue();
            dto.cpuId = $("#cpuSelect")[0].selectize.getValue();
            dto.hddId = $("#hddSelect")[0].selectize.getValue();
            dto.ramId = $("#ramSelect")[0].selectize.getValue();
            dto.cdDriveId = $("#cdDriveSelect")[0].selectize.getValue();
            dto.soundCardId = $("#soundCardSelect")[0].selectize.getValue();
            dto.videoCardId = $("#videoCardSelect")[0].selectize.getValue();
            dto.lanCardId = $("#lanCardSelect")[0].selectize.getValue();
            dto.keyboardId = $("#keyboardSelect")[0].selectize.getValue();
            dto.mouseId = $("#mouseSelect")[0].selectize.getValue();
            dto.speakersId = $("#speakersSelect")[0].selectize.getValue();
            dto.operationSystemId = $("#osSelect")[0].selectize.getValue();
            break;
    }
    $.ajax({
        type: "POST",
        url: requestLink,
        data: JSON.stringify(dto),
        async: false,
        success: function () {

            window.location.reload();
        },
        error: function (callback) {
            if ($('#exceptionContainer').length == 0) {
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



};



// Запрос на активацию/деактивацию кнопки "Отправить на склад"
let requestToEnableStorage = function () {
    
    
    
   
    $('.svtObjModalFooter')[0].innerHTML = "";
    $.ajax({
        url: '/getstor?locationId=' + locationId,
        type: 'GET',
        async: false,
        dataType: 'json',
        error: function (callback) {
            console.log(callback);
            $('.svtObjModalFooter').append('<button type="button" class="btn btn-danger btn-sm" id="archivedBtn"  data-bs-dismiss="modal">Удалить</button> ' +
                    '<span class="d-inline-block" tabindex="0" data-bs-toggle="tooltip" title="У вас нет склада в этом районе"><button type="button" class="btn btn-warning btn-sm" id="sendToStorageBtn">Отправить на склад</button></span>' +
                    '<button type="button" class="btn btn-secondary btn-sm"  data-bs-dismiss="modal">Отменить</button>' +
                    '<button type="button" class="btn btn-primary btn-sm" id="btnSave" >Применить</button>');
            $('#sendToStorageBtn').addClass("disabled");
            
            tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
            tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
            $('#btnSave')[0].removeEventListener('click', handleClickUpdateBtn);
            $('#archivedBtn')[0].removeEventListener('click', handleClickArchivedBtn);

        },
        success: function (callback) {
            console.log(callback);
            $('.svtObjModalFooter').append('<button type="button" class="btn btn-danger btn-sm" id="archivedBtn" data-bs-dismiss="modal">Удалить</button> ' +
                    '<button type="button" class="btn btn-warning btn-sm" data-bs-dismiss="modal" id="sendToStorageBtn">Отправить на склад</button>' +
                    '<button type="button" class="btn btn-secondary btn-sm"  data-bs-dismiss="modal">Отменить</button>' +
                    '<button type="button" class="btn btn-primary btn-sm" id="btnSave" >Применить</button>');
            $('#sendToStorageBtn')[0].addEventListener('click', handleClickSendToStorageBtn);
        }
    });
        $('#btnSave')[0].addEventListener('click', handleClickUpdateBtn);
        $('#archivedBtn')[0].addEventListener('click', handleClickArchivedBtn);
};


window.onload = function () {

    let elem = document.querySelectorAll('.element');

    addPlaceBtn.addEventListener('click', function () {
        modalContentLoad($(this)[0].className);

    });

    for (let i = 0; i < elem.length; i++) {
        elem[i].addEventListener("click", function (event) {
            modalContentLoad($(this)[0].className, $(this)[0].id);

        });
    }


//$('#btnSave')[0].addEventListener('click', handleClickUpdateBtn);

// Поиск по всем полям телефона


//    $('#searchSvtObj')[0].addEventListener('input', function (input) {
//
//        $('.tree')[0].innerHTML = '';
//        
//        
//        phoneArray = new Array();
//        phoneTarget = "";
//        departmentTarget = "";
//        let count = 0;
//        
//        for (i = 0; i < locationRows.length; i++) {
//            let target = "";
//            locationArray = new Array();
//             departmentArray = new Array();
//            let locationTarget = locationRows[i].childNodes[1].childNodes[1].innerText;
//            
//            
//            for (j = 0; j < locationRows[i].childNodes[1].childNodes[3].childNodes.length; j++) {
//                
//                if(locationRows[i].childNodes[1].childNodes[3].childNodes[j].tagName == "LI") {
//                    for(p = 0; p < locationRows[i].childNodes[1].childNodes[3].childNodes[j].childNodes.length; p++) {
//                       
//                        if(locationRows[i].childNodes[1].childNodes[3].childNodes[j].childNodes[p].tagName == "DETAILS") {
//                    departmentArray.push(locationRows[i].childNodes[1].childNodes[3].childNodes[j]);
//                    
//                    target = locationRows[i].childNodes[1].childNodes[3].childNodes[j].childNodes[p].childNodes[1].innerText;
//                    if(target.toLowerCase().search($('#searchSvtObj').find('input')[0].value.toLowerCase()) > -1) {
//                        departmentArray.push(locationRows[i].childNodes[1].childNodes[3].childNodes[j].childNodes[p]);
//                       
//                console.log("target: " + target + "\n**********");
//            }
//                    
//                }
////                    for (k = 0; k < locationRows[i].childNodes[1].childNodes[3].childNodes[j].childNodes[0].childNodes.length; k++) {
////                        if(locationRows[i].childNodes[1].childNodes[3].childNodes[j].childNodes[0].childNodes[k].tagName == "LI") {
////                            
////                            console.log("phone: " + locationRows[i].childNodes[1].childNodes[3].childNodes[j]);
////                    }
////                }
//                
//
//
//
//
////                    target = target + " " + locationRows[i].childNodes[1].childNodes[1].innerText;
////                    if (target.toLowerCase().search($('#searchSvtObj').find('input')[0].value.toLowerCase()) > -1) {
////
////                      //  resultArray.push(phoneRows[i]);
////                     //   $('.tree')[0].appendChild(phoneRows[i]);
////                       
////
////                    }
//                    if(locationTarget.toLowerCase().search($('#searchSvtObj').find('input')[0].value.toLowerCase()) > -1) {
//                locationArray.push(locationRows[i]);
//            }
//               
//                
//            }
//            
//            }
//        }
//          
//            for(h = 0; h < locationArray.length; h++) {
//                $('.tree')[0].append(locationArray[h]);
//                   
//            }
//            
//            
//             for(s = 0; s < departmentArray.length; s++) {
//                 if(null == document.querySelector('.tree').closest("li")) {
//                $('.tree')[0].append(departmentArray[s].parentElement.parentElement.parentElement);
//                
//            }
//                      //  $('.tree')[0].append(departmentArray[s]);
//                    } 
//        }
//          
//
//    });



};



// Модальное окно добавления/редактирования телефона

let modalContentLoad = function (eventReason, svtObjId) {
   let requestLink; 
    

    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
        
        switch (attrib) {
            case "phones":
                requestLink = "/getphone?phoneId=";
                $("#exampleModalLabel")[0].innerText = "Редактирование телефона";
                break;
            case "monitors":
                requestLink = "/getmonitor?monitorId=";
                $("#exampleModalLabel")[0].innerText = "Редактирование монитора";
                break;
            case "ups":
                requestLink = "/getups?upsId=";
                $("#exampleModalLabel")[0].innerText = "Редактирование ИБП";
                break;
            case "systemblock":
                requestLink = "/getsystemblock?systemblockId=";
                $("#exampleModalLabel")[0].innerText = "Редактирование системного блока";
                break;
        }
        
        

        $.ajax({
            url: requestLink + svtObjId,
            type: 'GET',
            async: false,
            dataType: 'json',
            success: function (callback) {
                console.log(callback);
                idPlace = callback.placeId;
                codeDepartment = callback.departmentCode;
                modelId = callback.modelId;
                status = callback.status;
                inventary = callback.inventaryNumber;
                serial = callback.serialNumber;
                yearCreated = callback.yearCreated;
                parseStartDate = Date.parse(callback.dateExploitationBegin);
                idSvtObj = callback.id;
                innerCallNumber = callback.phoneNumber;
                locationId = callback.locationId;
                nameFromOneC = callback.nameFromOneC;
                baseType = callback.baseType;
                batteryAmount = callback.batteryAmount;
                dateReplaceBattery = callback.yearReplacement;
                batteryTypeId = callback.batteryTypeId;
            }
        });

        startDate = new Date(parseStartDate);
        startExploitation = startDate.toISOString().slice(0, 10);

        switch (status) {
            case "Ремонт":
                $("#statusSelect")[0].value = "REPAIR";
                break;
            case "Списание":
                $("#statusSelect")[0].value = "MONITORING";
                break;
            case "Утилизирован":
                $("#statusSelect")[0].value = "UTILIZATION";
                break;
            case "Исправен":
                $("#statusSelect")[0].value = "OK";
                break;
            case "Неисправен":
                $("#statusSelect")[0].value = "DEFECTIVE";
                break;
        }

        $("#inventaryNumber")[0].value = inventary;
        $("#serialNumber")[0].value = serial;
        $("#startExploitation")[0].value = startExploitation;
        switch (attrib) {
            case "phones":
                requestLink = "/getphone?phoneId=";
                $("#innerCallNumber")[0].value = innerCallNumber;
                break;
            case "monitors":
                requestLink = "/getmonitor?monitorId=";
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#baseTypeSelect")[0].value = baseType;
                break;
            case "ups":
                requestLink = "/getups?upsId=";
                $("#dateReplaceSelect")[0].value = dateReplaceBattery;
                $("#batteryAmount")[0].value = batteryAmount;
                // тут поля для ИБП
                break;
        }
        

        if (eventReason.indexOf("storage") >= 0) {
            stor = true;
            $('#statusSelect')[0].disabled = true;
            $("#inventaryNumber")[0].disabled = true;
            $("#serialNumber")[0].disabled = true;
            $("#startExploitation")[0].disabled = true;
            switch (attrib) {
                case "phones":
                    $("#innerCallNumber")[0].disabled = true;    
                    break;
                case "monitors":
                    $("#nameFromOneC")[0].disabled = true;
                    $("#baseTypeSelect")[0].disabled = true;
                    break;
                case "ups":
                    $("#dateReplaceSelect")[0].disabled = true;
                    $("#batteryAmount")[0].disabled = true;
                    break;
            }
            
        }
        

    }

    $('#placeSelect').selectize({
        preload: true,
        valueField: 'placeId',
        labelField: 'username',
        searchField: ["placeId", "username"],
        load: function (query, callback) {
            $.ajax({
                url: '/placesel',
                type: 'GET',
                async: false,
                dataType: 'json',
                error: callback,
                success: callback
            });
            if (eventReason.indexOf("storage") >= 0) {
                $.ajax({
                    url: '/placebyid?placeId=' + idPlace,
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    error: function (callback) {
                        console.log(callback);
                    },
                    success: function (callback) {
                        console.log(callback);
                        $('#placeSelect')[0].selectize.addOption(callback);
                    }
                });
            }
            if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                oldPlaceId = $('#placeSelect')[0].selectize.search(idPlace).items[0].id;
                $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(idPlace).items[0].id);

                if (eventReason.indexOf("storage") >= 0) {
                    $('#placeSelect')[0].selectize.disable();
                } else {
                    $('#placeSelect')[0].selectize.enable();
                }

            } else {
                oldPlaceId = $('#placeSelect')[0].selectize.search(0).items[0].id;
                $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
            }
        },

        onChange: function (value) {
            if (value !== '') {
                if (oldPlaceId != value) {
                    $.ajax({
                        url: '/placebyid?placeId=' + $('#placeSelect')[0].selectize.getValue(),
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        success: function (callback) {
                            departmentSelect = callback.departmentCode;
                            locationId = callback.locationId;
                            oldPlaceId = callback.placeId;
                            if(!stor) {
                            $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(departmentSelect).items[0].id);
                            $('#locationSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.options[$('#placeSelect')[0].selectize.items[0]].locationId);
                        }
                        }
                    });


                } 
                else {
                     $.ajax({
                        url: '/placebyid?placeId=' + value,
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        success: function (callback) {
                            departmentSelect = callback.departmentCode;
                            locationId = callback.locationId;
                            oldPlaceId = callback.placeId;
                           // $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(departmentSelect).items[0].id);
                        }
                    });
                }
            }
        }
    });

    if($('#placeSelect')[0].selectize.getValue() == "" && $('#placeSelect')[0].selectize.order > 0) {
     


                if (eventReason.indexOf("storage") >= 0) {
             
                    $('#placeSelect')[0].selectize.disable();
                    
                } else {
                    $('#placeSelect')[0].selectize.enable();
                }
                
                
               
               
    }


    $('#departmentSelect').selectize({
        preload: true,
        valueField: 'code',
        labelField: 'name',
        searchField: ["code", "name"],

        load: function (query, callback) {
            $.ajax({
                url: '/depbyplaces',
                type: 'GET',
                async: false,
                dataType: 'json',
                error: callback,
                success: callback
            });
            $.ajax({
                url: '/placebyid?placeId=' + $('#placeSelect')[0].selectize.getValue(),
                type: 'GET',
                async: false,
                dataType: 'json',
                error: callback,
                success: function (callback) {
                    departmentSelect = callback.departmentCode;
                    locationId = callback.locationId;
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        oldDepartment = $('#departmentSelect')[0].selectize.search(codeDepartment).items[0].id;
                        $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(codeDepartment).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#departmentSelect')[0].selectize.disable();
                        } else {
                            $('#departmentSelect')[0].selectize.enable();
                        }
                    } else {
                        oldDepartment = $('#departmentSelect')[0].selectize.search(departmentSelect).items[0].id;
                        $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(departmentSelect).items[0].id);
                    }
                }
            });

        },
        onChange: function (value) {
            if (value !== '') {
                if (oldDepartment != value) {

                    $.ajax({
                        url: '/placebydepandloc?locationId=' + locationId + '&departmentCode=' + value,
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        success: function (res) {
                            let keys = Object.keys($('#placeSelect')[0].selectize.options);
                            for (let i = 0; i < keys.length; i++) {
                                $('#placeSelect')[0].selectize.removeOption(keys[i]);
                            }
                            res.forEach(model => {
                                $('#placeSelect')[0].selectize.addOption(model);
                                $('#placeSelect')[0].selectize.addItem(model);
                            });


                            oldDepartment = $('#departmentSelect')[0].selectize.getValue();
                            oldPlaceId = $('#placeSelect')[0].selectize.search(0).items[0].id;
                            $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                        }
                    });

                }
                if (eventReason.indexOf("storage") < 0 && eventReason.indexOf("addPlaceBtn") < 0) {
                    requestToEnableStorage();
                   
                }
            }

        }

    });
    
    if($('#departmentSelect')[0].selectize.getValue() == "" && $('#departmentSelect')[0].selectize.order > 0) {
        

                
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#departmentSelect')[0].selectize.disable();
                        } else {
                            $('#departmentSelect')[0].selectize.enable();
                        }
                        
    }

    $('#locationSelect').selectize({
        preload: true,
        valueField: 'id',
        labelField: 'name',
        searchField: ["id", "name"],
        load: function (query, callback) {
            $.ajax({
                url: '/loc',
                type: 'GET',
                async: false,
                dataType: 'json',
                error: callback,
                success: callback
            });

            oldLocationId = $('#locationSelect')[0].selectize.search(locationId).items[0].id;
            $('#locationSelect')[0].selectize.setValue($('#locationSelect')[0].selectize.search(locationId).items[0].id);
            if (eventReason.indexOf("storage") >= 0) {
                $('#locationSelect')[0].selectize.disable();
            } else {
                $('#locationSelect')[0].selectize.enable();
            }
        },
        onChange: function (value) {
            if (value !== '') {
                if (oldLocationId !== value) {
                    console.log(value);
                    locationId = value;


                    $.ajax({
                        url: "/placebyloc?locationId=" + locationId,
                        type: 'GET',
                        async: false,
                        dataType: 'json', // added data type
                        success: function (res) {
                            let keys = Object.keys($('#placeSelect')[0].selectize.options);
                            for (let i = 0; i < keys.length; i++) {
                                $('#placeSelect')[0].selectize.removeOption(keys[i]);
                            }
                            res.forEach(model => {
                                $('#placeSelect')[0].selectize.addOption(model);
                                $('#placeSelect')[0].selectize.addItem(model);
                            });

                            oldLocationId = $('#placeSelect')[0].selectize.search(0).items[0].id;
                        }
                    });

                    $.ajax({
                        url: '/placebyid?placeId=' + $('#placeSelect')[0].selectize.search(0).items[0].id,
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        success: function (callback) {
                            departmentSelect = callback.departmentCode;
                            locationId = callback.locationId;
                        }
                    });

                    $.ajax({
                        url: '/depbyloc?locationId=' + locationId,
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        success: function (res) {
                            let keys = Object.keys($('#departmentSelect')[0].selectize.options);
                            for (let i = 0; i < keys.length; i++) {
                                $('#departmentSelect')[0].selectize.removeOption(keys[i]);
                            }
                            res.forEach(model => {
                                $('#departmentSelect')[0].selectize.addOption(model);
                                $('#departmentSelect')[0].selectize.addItem(model);
                            });


                            oldLocationId = $('#departmentSelect')[0].selectize.search(0).items[0].id;
                            $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(0).items[0].id);
                        }
                    });

                    $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                    oldDepartment = $('#departmentSelect')[0].selectize.search(departmentSelect).items[0].id;
                    $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(departmentSelect).items[0].id);
                } else {
                $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(idPlace).items[0].id);
            }
            } 



        }

    });
    
    if($('#locationSelect')[0].selectize.getValue() == "" && $('#locationSelect')[0].selectize.order > 0) {
       
       $('#locationSelect')[0].selectize.setValue($('#locationSelect')[0].selectize.search(locationId).items[0].id);
        
             locationId = $('#locationSelect')[0].selectize.getValue();
        
            if (eventReason.indexOf("storage") >= 0) {
                        $.ajax({
                    url: '/placebyid?placeId=' + idPlace,
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    error: function (callback) {
                        console.log(callback);
                    },
                    success: function (callback) {
                        console.log(callback);
                        $('#placeSelect')[0].selectize.addOption(callback);
                        $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(idPlace).items[0].id);
                        
                    }
                });
               
                
                  
                $('#locationSelect')[0].selectize.disable();
            } else {
                $('#locationSelect')[0].selectize.enable();
            }
           // $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(idPlace).items[0].id);
          
       
    }

    $('#modelSelect').selectize({
        preload: true,
        valueField: 'id',
        labelField: 'model',
        searchField: ["id", "model"],
        load: function (query, callback) {
            let requestLink;
            switch (attrib) {
                case "phones":
                    requestLink = "/modphones";   
                    break;
                case "monitors":
                    requestLink = "/modmonitors";   
                    break;    
                case "ups":
                    requestLink = "/modups";
                    break;
                case "systemblock":
                    requestLink = "/modsysblock";
                    break;
            }
            $.ajax({
                url: requestLink,
                type: 'GET',
                async: false,
                dataType: 'json',
                error: callback,
                success: callback
            });
            if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                $('#modelSelect')[0].selectize.setValue($('#modelSelect')[0].selectize.search(modelId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#modelSelect')[0].selectize.disable();
                } else {
                    $('#modelSelect')[0].selectize.enable();
                }
            } else {
                $('#modelSelect')[0].selectize.setValue($('#modelSelect')[0].selectize.search(0).items[0].id);
            }
        }
    });
    
    
    if($('#modelSelect')[0].selectize.getValue() == "" && $('#modelSelect')[0].selectize.order > 0) {
        $('#modelSelect')[0].selectize.setValue($('#modelSelect')[0].selectize.search(modelId).items[0].id);
        if (eventReason.indexOf("storage") >= 0) {
                    $('#modelSelect')[0].selectize.disable();
                } else {
                    $('#modelSelect')[0].selectize.enable();
                }
    }
    
    switch (attrib) {
        case "ups":
                $('#batteryTypeSelect').selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'type',
                    searchField: ["id", "type"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/typebatups",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                        if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                            $('#batteryTypeSelect')[0].selectize.setValue($('#batteryTypeSelect')[0].selectize.search(batteryTypeId).items[0].id);
                            if (eventReason.indexOf("storage") >= 0) {
                                $('#batteryTypeSelect')[0].selectize.disable();
                            } else {
                                $('#batteryTypeSelect')[0].selectize.enable();
                            }
                        } else {
                            $('#batteryTypeSelect')[0].selectize.setValue($('#batteryTypeSelect')[0].selectize.search(0).items[0].id);
                        }
                    }
                });
                if ($('#batteryTypeSelect')[0].selectize.getValue() == "" && $('#batteryTypeSelect')[0].selectize.order > 0) {
                    $('#batteryTypeSelect')[0].selectize.setValue($('#batteryTypeSelect')[0].selectize.search(batteryTypeId).items[0].id);
                    if (eventReason.indexOf("storage") >= 0) {
                        $('#batteryTypeSelect')[0].selectize.disable();
                    } else {
                        $('#batteryTypeSelect')[0].selectize.enable();
                    }
                }

                let dateReplaceSelect = document.querySelector('#dateReplaceSelect');
                for (i = currentYear; i >= 2000; i--) {
                    option = document.createElement('option');
                    option.value = i;
                    option.innerHTML = i;
                    dateReplaceSelect.appendChild(option);
                }
                if (dateReplaceBattery) {
                    dateReplaceSelect.value = dateReplaceBattery;
                } else {
                    dateReplaceSelect.value = currentYear;
                }
            break;
            
        case "monitors":
            
            if (eventReason.indexOf("storage") >= 0) {
                        $("#nameFromOneC")[0].disabled = true;
                        $("#baseTypeSelect")[0].disabled = true;
                    } else {
                        $("#nameFromOneC")[0].disabled = false;
                        $("#baseTypeSelect")[0].disabled = false;
                    }
                
            
            break;
            
        case "systemblock":
            
            $("#osSelect").selectize({
                    plugins: ["remove_button"],
                    delimiter: ",",
                    persist: false,
                    maxItems: null,
                    placeholder: "Выберите из списка",
                    preload: true,
                    valueField: 'id',
                    labelField: 'name',
                    searchField: ["id", "name"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modos",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    }});
            
            $("#motherboardSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'model',
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modmotherboard",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    }});
                
                
                $("#cpuSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'model',
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modcpu",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    }});
                
            
                
                 $("#ramSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'model',
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modram",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    },
                      render: {
                      option: function(item, escape) {
                        return '<div>'
                          + '<strong>'       
                          + escape(item.model) + '- ' 
                          + '</strong>' 
                          + escape(item.capacity) + ' ГБ' 
                          + '</div>';
                      },
                      item: function(item, escape){
                        return '<div>'
                          + escape(item.model) + ', '
                          + escape(item.capacity) + ' ГБ' 
                          + '</div>';
                      }
                    }
            });
                
                    $("#hddSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: "model",
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modhdd",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    },
                    render: {
                      option: function(item, escape) {
                        return '<div>'
                          + '<strong>'       
                          + escape(item.model) + '- ' 
                          + '</strong>' 
                          + escape(item.capacity) + ' ' 
                          + escape(item.unit)  
                          + '</div>';
                      },
                      item: function(item, escape){
                        return '<div>'
                          + escape(item.model) + ', '
                          + escape(item.capacity) + ' ' 
                          + escape(item.unit) 
                          + '</div>';
                      }
                    }
            
            });
            
            $("#videoCardSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'model',
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modvideo",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    }});
                
                $("#soundCardSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'model',
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modscard",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    }});
                
                $("#lanCardSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'model',
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modlcard",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    }});
                
                $("#cdDriveSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'model',
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modcddrive",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    }});
                
                $("#keyboardSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'model',
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modkeyboard",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    }});
                
                $("#mouseSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'model',
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modmouse",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    }});
                
                $("#speakersSelect").selectize({
                    preload: true,
                    valueField: 'id',
                    labelField: 'model',
                    searchField: ["id", "model"],
                    load: function (query, callback) {

                        $.ajax({
                            url: "/modspeakers",
                            type: 'GET',
                            async: false,
                            dataType: 'json',
                            error: callback,
                            success: callback
                        });
                    }});
            
            break;
    }
 

    for (i = 2000; i <= currentYear; i++) {
        option = document.createElement('option');
        option.value = i;
        option.innerHTML = i;
        dateCreate.appendChild(option);
    }
    if(yearCreated) {
    dateCreate.value = yearCreated;
    } else {
        dateCreate.value = 2000;
    }
    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
        
       
        if (eventReason.indexOf("storage") >= 0) {
            dateCreate.disabled = true;
             $('#btnSave')[0].removeEventListener('click', handleClickSavePhoneBtn);
            // $('#archivedBtn')[0].removeEventListener('click', handleClickArchivedBtn);
             $('.svtObjModalFooter')[0].innerHTML = "";
            $('.svtObjModalFooter').append('<button type="button" class="btn btn-danger btn-sm" id="archivedBtn"  data-bs-dismiss="modal">Удалить</button> ' +
                    '<button type="button" class="btn btn-warning btn-sm" id="backFromStorageBtn" data-bs-target="#confirmationModal" data-bs-toggle="modal" >Вернуть со склада</button>' +
                    '<button type="button" class="btn btn-secondary btn-sm"  data-bs-dismiss="modal">Отменить</button>' +
                    '<button type="button" class="btn btn-primary btn-sm" id="btnSave" >Применить</button>');
            $('#btnSave')[0].disabled = true;
            $('#btnSave')[0].addEventListener('click', handleClickSavePhoneBtn);
            $('#archivedBtn')[0].addEventListener('click', handleClickArchivedBtn);
            




            //логика после нажатия кнопки "Вернуть со склада"

            $('#backFromStorageBtn')[0].addEventListener('click', function () {
                toggle = true;
                
                $('#placeSelect')[0].selectize.enable();
                $('#modelSelect')[0].selectize.enable();
                $('#locationSelect')[0].selectize.enable();
                $('#departmentSelect')[0].selectize.enable();
                $('#btnSave')[0].disabled = false;
                $('#statusSelect')[0].disabled = false;
                $("#inventaryNumber")[0].disabled = false;
                $("#serialNumber")[0].disabled = false;
                $("#startExploitation")[0].disabled = false;
                
                switch (attrib) {
                    case "phones":
                        $("#innerCallNumber")[0].disabled = false;
                        
                        break;
                    case "monitors":
                        $("#nameFromOneC")[0].disabled = false;
                        $("#baseTypeSelect")[0].disabled = false;
                        break;
                    case "ups":
                        $('#batteryTypeSelect')[0].selectize.enable();
                        $("#dateReplaceSelect")[0].disabled = false;
                        $("#batteryAmount")[0].disabled = false;
                        break;
                }
                dateCreate.disabled = false;
                $('#btnSave')[0].removeEventListener('click', handleClickSavePhoneBtn);
                $('#archivedBtn')[0].removeEventListener('click', handleClickArchivedBtn);
                $('.svtObjModalFooter')[0].innerHTML = "";
                $('.svtObjModalFooter').append('<button type="button" class="btn btn-danger btn-sm" id="archivedBtn" data-bs-dismiss="modal">Удалить</button> ' +
                        '<button type="button" class="btn btn-warning btn-sm" data-bs-dismiss="modal" id="sendToStorageBtn">Отправить на склад</button>' +
                        '<button type="button" class="btn btn-secondary btn-sm"  data-bs-dismiss="modal">Отменить</button>' +
                        '<button type="button" class="btn btn-primary btn-sm" id="btnSave">Применить</button>');

            //    oldPlaceId = $('#placeSelect')[0].selectize.search(0).items[0].id;
               

                $('#sendToStorageBtn')[0].addEventListener('click', handleClickSendToStorageBtn);
                $('#btnSave')[0].addEventListener('click', handleClickBackToStorageBtn);
                $('#archivedBtn')[0].addEventListener('click', handleClickArchivedBtn);
                
             // Подтверждение вернуть со склада
             
               $("#backFromStorageConfirmBtn")[0].addEventListener('click', function (event) {
                    Object.entries($('#placeSelect')[0].selectize.options).forEach(option => {
                        if (option[1].placeType == "Склад") {
                            $('#placeSelect')[0].selectize.removeOption(option[0]);
                        }
                    });
                     $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                    $('#modelSelect')[0].selectize.setValue($('#modelSelect')[0].selectize.search(modelId).items[0].id);
                    if(attrib == "ups") {
                        $('#batteryTypeSelect')[0].selectize.setValue($('#batteryTypeSelect')[0].selectize.search(batteryTypeId).items[0].id);
                    } 
                });

            });
        } else {
            requestToEnableStorage();
        }  
    } else {
        
        $('.svtObjModalFooter')[0].innerHTML = "";
                $('.svtObjModalFooter').append(
                        '<button type="button" class="btn btn-secondary btn-sm"  data-bs-dismiss="modal">Отменить</button>' +
                        '<button type="button" class="btn btn-primary btn-sm" id="btnSave" >Сохранить</button>');
    }

    
    modalAddPhone.addEventListener('hidden.bs.modal', function (event) {

        if (!toggle) {
            if($('#placeSelect')[0].selectize) {
                $('#placeSelect')[0].selectize.clear();
            }
            if($('#departmentSelect')[0].selectize) {
                $('#departmentSelect')[0].selectize.clear();
            }
            if($('#locationSelect')[0].selectize) {
                $('#locationSelect')[0].selectize.clear();
            }
            if($('#modelSelect')[0].selectize) {
                $('#modelSelect')[0].selectize.clear();
            }
            if($('#batteryTypeSelect')[0]) {
                if($('#batteryTypeSelect')[0].selectize) {
                    $('#batteryTypeSelect')[0].selectize.clear();
                }
        }
         
        }
        
         if($("#statusSelect")[0].disabled) {
                $("#statusSelect")[0].disabled = false;
            }
            if($("#inventaryNumber")[0].disabled) {
                $("#inventaryNumber")[0].disabled = false;
            }
            if($("#serialNumber")[0].disabled) {
                $("#serialNumber")[0].disabled = false;
            }
            if($("#dateCreateSelect")[0].disabled) {
                $("#dateCreateSelect")[0].disabled = false;
            }
            if($("#startExploitation")[0].disabled) {
                $("#startExploitation")[0].disabled = false;
            }
             
             if($("#innerCallNumber")[0]) {
                 if($("#innerCallNumber")[0].disabled) {
                     $("#innerCallNumber")[0].disabled = false;
                 }
             }
        toggle = false;
    });



    $('#btnSave')[0].addEventListener('click', handleClickSavePhoneBtn);
            //  $('#archivedBtn')[0].addEventListener('click', handleClickArchivedBtn);

   stor = false;


};




document.addEventListener("DOMContentLoaded", function () {

    let treeLocations = [...document.querySelectorAll('.location')];
    
    switch (attrib) {
                    case "phones":
                        dynamicLabel = "Внутренний номер";
                        
                        break;
                    case "monitors":
                        dynamicLabel = "По ведомости ОС";
                        break;
                    case "ups":
                        
                        dynamicLabel = "Год замены";
                        break;
                }
        for (j = 0; j < storageDtoes.length; j++) {
            let addDepartmentFlag = false;
            for (i = 0; i < treeLocations.length; i++) {
            if (treeLocations[i].childNodes[1].innerText == storageDtoes[j].locationName) {
                liItem = document.createElement('li');
                liItem.className = "department";
                liItem.innerHTML = "<details> <summary>Склад</summary>";
                treeLocations[i].childNodes[1].childNodes[3].append(liItem);
                headerElement = document.createElement('div');
                headerElement.className = "row fw-bold mt-3 mb-3";
                headerElement.innerHTML = '<div class="col">Модель</div>' +
                        '<div class="col">ФИО</div>' +
                        '<div class="col">Серийный номер</div>' +
                        '<div class="col">Инвентарный номер</div>' +
                        '<div class="col">Дата ввода в экспл</div>' +
                        '<div class="col">Состояние</div>' +
                        '<div class="col">Дата выпуска</div>' +
                        '<div class="col">' + dynamicLabel + '</div>';
                liItem.childNodes[0].append(headerElement);
                for (d = 0; d < storageDtoes[j].departments.length; d++) {
                    for (t = 0; t < storageDtoes[j].departments[d].dtoes.length; t++) {
                        count = t + 1;
                        elDepartment = document.createElement("li");
                        elDepartment.className = "element storage";
                        elDepartment.id = storageDtoes[j].departments[d].dtoes[t].id;
                        elDepartment.setAttribute("data-bs-toggle", "modal");
                        elDepartment.setAttribute("data-bs-target", "#addPlaceModal");
                        let dateBegin = new Date(storageDtoes[j].departments[d].dtoes[t].dateExploitationBegin);
                        let formatedDate = dateBegin.toLocaleDateString('ru');
                        switch (attrib) {
                            case "phones":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                '<div class="col">' + formatedDate + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].status + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].phoneNumber + '</div>' +
                                '</div>';

                                break;
                            case "monitors":
                                 elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                '<div class="col">' + formatedDate + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].status + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].baseType + '</div>' +
                                '</div>';
                                break;
                            case "ups":

                                 elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                '<div class="col">' + formatedDate + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].status + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearReplacement + '</div>' +
                                '</div>';
                                break;
                        }
                        
                        liItem.childNodes[0].append(elDepartment);
                    }
                }
                addDepartmentFlag = true;
            }
        }
        
        if(!addDepartmentFlag) {
        
        // Сюда вставляем родительский элемент списка, в котором склад
         if(!parent) {
             parent = document.createElement('ul');
             parent.className = "tree";
             document.querySelector(".main").append(parent);
             
         }
         liLocation = document.createElement('li');
         liLocation.className = "location";
         liLocation.innerHTML = "<details> <summary>" + storageDtoes[j].locationName + "</summary>";
         parent.append(liLocation);
         
         ulItem = document.createElement('ul');
         ulItem.className = "departments";
         liLocation.childNodes[0].append(ulItem);
         
         liItem = document.createElement('li');
         liItem.className = "department";
         liItem.innerHTML = "<details> <summary>Склад</summary>";
         liLocation.childNodes[0].childNodes[2].append(liItem);
         headerElement = document.createElement('div');
         headerElement.className = "row fw-bold mt-3 mb-3";
         headerElement.innerHTML = '<div class="col">Модель</div>' +
                '<div class="col">ФИО</div>' +
                '<div class="col">Серийный номер</div>' +
                '<div class="col">Инвентарный номер</div>' +
                '<div class="col">Дата ввода в экспл</div>' +
                '<div class="col">Состояние</div>' +
                '<div class="col">Дата выпуска</div>' +
                '<div class="col">' + dynamicLabel + '</div>';
         liItem.childNodes[0].append(headerElement);
          for (d = 0; d < storageDtoes[j].departments.length; d++) {
                    for (t = 0; t < storageDtoes[j].departments[d].dtoes.length; t++) {
                        count = t + 1;
                        elDepartment = document.createElement("li");
                        elDepartment.className = "element storage";
                        elDepartment.id = storageDtoes[j].departments[d].dtoes[t].id;
                        elDepartment.setAttribute("data-bs-toggle", "modal");
                        elDepartment.setAttribute("data-bs-target", "#addPlaceModal");
                        let dateBegin = new Date(storageDtoes[j].departments[d].dtoes[t].dateExploitationBegin);
                        let formatedDate = dateBegin.toLocaleDateString('ru');
                        switch (attrib) {
                            case "phones":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                '<div class="col">' + formatedDate + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].status + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].phoneNumber + '</div>' +
                                '</div>';

                                break;
                            case "monitors":
                                 elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                '<div class="col">' + formatedDate + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].status + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].baseType + '</div>' +
                                '</div>';
                                break;
                            case "ups":

                                 elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                '<div class="col">' + formatedDate + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].status + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearReplacement + '</div>' +
                                '</div>';
                                break;
                        }
                        liItem.childNodes[0].append(elDepartment);
                    }
                }
            }
    }
    
    // сортировка списка
    
            treeLocations = [...document.querySelectorAll('.location')];
           arr = treeLocations.sort((a, b) => (a.childNodes[0].innerText > b.childNodes[0].innerText) ? 1 : ((b.childNodes[0].innerText > a.childNodes[0].innerText) ? -1 : 0));
            parent.innerHTML = '';
            for(let item of arr) {
               
            parent.appendChild(item);
        }

});







