

const addPlaceBtn = document.querySelector('#addPlaceBtn');
let parent = document.querySelector('.tree');
const modalAddPhone = document.getElementById('addPlaceModal');
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
let operationSystemId;
let cpuId;
let ramId;
let hddId;
let hddListId;
let videoCardId;
let soundCardId;
let lanCardId;
let cdDriveId;
let keyboardId;
let mouseId;
let speakersId;
let ipAdress;
let numberRoom;
let dateUpgrade;
let cpuAmount;
let modalParent = document.querySelector("#modalContent");
let portAmount;
let switchHubType;

let getStatus = function(input) {
    var result;
    switch (input) {
            case "REPAIR":
                result = "Ремонт";
                break;
            case "MONITORING":
                result = "Списание";
                break;
            case "UTILIZATION":
                result = "Утилизирован";
                break;
            case "OK":
                result = "Исправен";
                break;
            case "DEFECTIVE":
                result = "Неисправен";
                break;
        }
        return result;
} 


let handleClickArchivedBtn = function () {
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
        case "systemblock":
            requestLink = "/sysblocksarchived";
            break;
        case "scanner":
            requestLink = "/scannerarchived";
            break;
        case "server":
            requestLink = "/serverarchived";
            break;
        case "switch":
            requestLink = "/switcharchived";
            break;
        case "router":
            requestLink = "/routerarchived";
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
        placeId: document.querySelector('#placeSelect').value,
        id: idSvtObj,
        modelId: document.querySelector('#modelSelect').value,
        locationId: locationId,
    };
     if(null != $('#startExploitation')[0]) {
        dto.dateExploitationBegin = document.querySelector('#startExploitation').value;
    }
    if(null != $('#dateCreateSelect')[0]) {
        dto.yearCreated = document.querySelector('#dateCreateSelect').value;
    }
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
        case "systemblock":
            requestLink = "/sysblockstostor";
            dto.motherboardId = $("#motherboardSelect")[0].selectize.getValue();
            dto.cpuId = $("#cpuSelect")[0].selectize.getValue();
            dto.hddIdList = $("#hddSelect")[0].selectize.getValue();
            dto.ramId = $("#ramSelect")[0].selectize.getValue();
            dto.cdDriveId = $("#cdDriveSelect")[0].selectize.getValue();
            dto.soundCardId = $("#soundCardSelect")[0].selectize.getValue();
            dto.videoCardId = $("#videoCardSelect")[0].selectize.getValue();
            dto.lanCardId = $("#lanCardSelect")[0].selectize.getValue();
            dto.keyboardId = $("#keyboardSelect")[0].selectize.getValue();
            dto.mouseId = $("#mouseSelect")[0].selectize.getValue();
            dto.speakersId = $("#speakersSelect")[0].selectize.getValue();
            dto.operationSystemId = $("#osSelect")[0].selectize.getValue();
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.dateUpgrade = $("#dateUpgrade")[0].value;
            break;
        case "server":
            requestLink = "/servertostor";
            dto.cpuId = $("#cpuSelect")[0].selectize.getValue();
            dto.cpuAmount = $("#cpuAmount")[0].value;
            dto.hddIdList = $("#hddSelect")[0].selectize.getValue();
            dto.ramId = $("#ramSelect")[0].selectize.getValue();
            dto.operationSystemId = $("#osSelect")[0].selectize.getValue();
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.dateUpgrade = $("#dateUpgrade")[0].value;
            break;
        case "scanner":
            requestLink = "/scannertostor";
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            break;
        case "switch":
            requestLink = "/switchtostor";
            dto.portAmount = $("#portAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            if($("#switchRadio")[0].checked) {
               dto.switchHubType = "SWITCH";
            } else {
                dto.switchHubType = "HUB";
            }
            break;
        case "router":
            requestLink = "/routertostor";
            dto.portAmount = $("#portAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
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
        placeId: document.querySelector('#placeSelect').value,
        id: idSvtObj,
        modelId: document.querySelector('#modelSelect').value,
        locationId: locationId,
    };
     if(null != $('#startExploitation')[0]) {
        dto.dateExploitationBegin = document.querySelector('#startExploitation').value;
    }
    if(null != $('#dateCreateSelect')[0]) {
        dto.yearCreated = document.querySelector('#dateCreateSelect').value;
    }
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
        case "systemblock":
            requestLink = "/sysblocksbackstor";
            dto.motherboardId = $("#motherboardSelect")[0].selectize.getValue();
            dto.cpuId = $("#cpuSelect")[0].selectize.getValue();
            dto.hddIdList = $("#hddSelect")[0].selectize.getValue();
            dto.ramId = $("#ramSelect")[0].selectize.getValue();
            dto.cdDriveId = $("#cdDriveSelect")[0].selectize.getValue();
            dto.soundCardId = $("#soundCardSelect")[0].selectize.getValue();
            dto.videoCardId = $("#videoCardSelect")[0].selectize.getValue();
            dto.lanCardId = $("#lanCardSelect")[0].selectize.getValue();
            dto.keyboardId = $("#keyboardSelect")[0].selectize.getValue();
            dto.mouseId = $("#mouseSelect")[0].selectize.getValue();
            dto.speakersId = $("#speakersSelect")[0].selectize.getValue();
            dto.operationSystemId = $("#osSelect")[0].selectize.getValue();
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.dateUpgrade = $("#dateUpgrade")[0].value;
            break;
        case "server":
            requestLink = "/serverbackstor";
            dto.cpuId = $("#cpuSelect")[0].selectize.getValue();
            dto.cpuAmount = $("#cpuAmount")[0].value;
            dto.hddIdList = $("#hddSelect")[0].selectize.getValue();
            dto.ramId = $("#ramSelect")[0].selectize.getValue();
            dto.operationSystemId = $("#osSelect")[0].selectize.getValue();
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.dateUpgrade = $("#dateUpgrade")[0].value;
            break;
        case "scanner":
            requestLink = "/scannerbackstor";
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            break;
        case "switch":
            requestLink = "/switchtostor";
            dto.portAmount = $("#portAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            if($("#switchRadio")[0].checked) {
               dto.switchHubType = "SWITCH";
            } else {
                dto.switchHubType = "HUB";
            }
            break;
        case "router":
            requestLink = "/routertostor";
            dto.portAmount = $("#portAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
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
        placeId: document.querySelector('#placeSelect').value,
        id: idSvtObj,
        modelId: document.querySelector('#modelSelect').value,
    };
    if(null != $('#startExploitation')[0]) {
        dto.dateExploitationBegin = document.querySelector('#startExploitation').value;
    }
    if(null != $('#dateCreateSelect')[0]) {
        dto.yearCreated = document.querySelector('#dateCreateSelect').value;
    }
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
        case "systemblock":
            requestLink = "/updsysblocks";
            dto.motherboardId = $("#motherboardSelect")[0].selectize.getValue();
            dto.cpuId = $("#cpuSelect")[0].selectize.getValue();
            dto.hddIdList = $("#hddSelect")[0].selectize.getValue();
            dto.ramId = $("#ramSelect")[0].selectize.getValue();
            dto.cdDriveId = $("#cdDriveSelect")[0].selectize.getValue();
            dto.soundCardId = $("#soundCardSelect")[0].selectize.getValue();
            dto.videoCardId = $("#videoCardSelect")[0].selectize.getValue();
            dto.lanCardId = $("#lanCardSelect")[0].selectize.getValue();
            dto.keyboardId = $("#keyboardSelect")[0].selectize.getValue();
            dto.mouseId = $("#mouseSelect")[0].selectize.getValue();
            dto.speakersId = $("#speakersSelect")[0].selectize.getValue();
            dto.operationSystemId = $("#osSelect")[0].selectize.getValue();
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.dateUpgrade = $("#dateUpgrade")[0].value;
            break;
        case "server":
            requestLink = "/updserver";
            dto.cpuId = $("#cpuSelect")[0].selectize.getValue();
            dto.cpuAmount = $("#cpuAmount")[0].value;
            dto.hddIdList = $("#hddSelect")[0].selectize.getValue();
            dto.ramId = $("#ramSelect")[0].selectize.getValue();
            dto.operationSystemId = $("#osSelect")[0].selectize.getValue();
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.dateUpgrade = $("#dateUpgrade")[0].value;
            break;
        case "scanner":
            requestLink = "/updscanner";
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            break;
        case "switch":
            requestLink = "/updswitch";
            dto.portAmount = $("#portAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            if($("#switchRadio")[0].checked) {
               dto.switchHubType = "SWITCH";
            } else {
                dto.switchHubType = "HUB";
            }
            break;
        case "router":
            requestLink = "/updrouter";
            dto.portAmount = $("#portAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
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
        },
        processData: false,
        contentType: 'application/json'
    });
};
let handleClickSearchSvtObject = function (input) {
    let request;
    switch (attrib) {
        case "phones":
            request = "/phones?username=";
            break;
        case "systemblock":
            request = "/sysblocks?username=";
            break;
        case "monitors":
            request = "/monitors?username=";
            break;
        case "ups":
            request = "/ups?username=";
            break;
        case "scanner":
            request = "/scanner?username=";
            break;
        case "server":
            request = "/server?username=";
            break;
        case "switch":
            request = "/switch?username=";
            break;
        case "router":
            request = "/router?username=";
            break;
    }
    window.location.href = request + input;
}
let handleClickSavePhoneBtn = function () {
    let requestLink;

    let dto = {
        model: document.querySelector('#modelSelect').innerText,
        status: document.querySelector('#statusSelect').value,
        inventaryNumber: document.querySelector('#inventaryNumber').value,
        serialNumber: document.querySelector('#serialNumber').value,
        placeId: document.querySelector('#placeSelect').value,
        id: idSvtObj,
        modelId: document.querySelector('#modelSelect').value,
    };
    if(null != $('#startExploitation')[0]) {
        dto.dateExploitationBegin = document.querySelector('#startExploitation').value;
    }
    if(null != $('#dateCreateSelect')[0]) {
        dto.yearCreated = document.querySelector('#dateCreateSelect').value;
    }
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
            dto.motherboardId = $("#motherboardSelect")[0].selectize.getValue();
            dto.cpuId = $("#cpuSelect")[0].selectize.getValue();
            dto.hddIdList = $("#hddSelect")[0].selectize.getValue();
            dto.ramId = $("#ramSelect")[0].selectize.getValue();
            dto.cdDriveId = $("#cdDriveSelect")[0].selectize.getValue();
            dto.soundCardId = $("#soundCardSelect")[0].selectize.getValue();
            dto.videoCardId = $("#videoCardSelect")[0].selectize.getValue();
            dto.lanCardId = $("#lanCardSelect")[0].selectize.getValue();
            dto.keyboardId = $("#keyboardSelect")[0].selectize.getValue();
            dto.mouseId = $("#mouseSelect")[0].selectize.getValue();
            dto.speakersId = $("#speakersSelect")[0].selectize.getValue();
            dto.operationSystemId = $("#osSelect")[0].selectize.getValue();
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.dateUpgrade = $("#dateUpgrade")[0].value;
            break;
        case "server":
            requestLink = "/server";
            dto.cpuId = $("#cpuSelect")[0].selectize.getValue();
            dto.cpuAmount = $("#cpuAmount")[0].value;
            dto.hddIdList = $("#hddSelect")[0].selectize.getValue();
            dto.ramId = $("#ramSelect")[0].selectize.getValue();
            dto.operationSystemId = $("#osSelect")[0].selectize.getValue();
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.dateUpgrade = $("#dateUpgrade")[0].value;
            break;
        case "scanner":
            requestLink = "/scanner";
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            break;
        case "switch":
            requestLink = "/switch";
            dto.portAmount = $("#portAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            if($("#switchRadio")[0].checked) {
               dto.switchHubType = "SWITCH";
            } else {
                dto.switchHubType = "HUB";
            }
            break;
        case "router":
            requestLink = "/router";
            dto.portAmount = $("#portAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
           
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
    $("#searchSvtObjBtn")[0].addEventListener("click", function () {
        handleClickSearchSvtObject($("#searchSvtObjInput")[0].value);
    });
    let elem = document.querySelectorAll('.element');
    addPlaceBtn.addEventListener('click', function () {
       
        modalContentLoad($(this)[0].className);
    });
    for (let i = 0; i < elem.length; i++) {
        elem[i].addEventListener("click", function (event) {
            modalContentLoad($(this)[0].className, $(this)[0].id);
        });
    }
};
// Модальное окно добавления/редактирования телефона
let modalContentLoad = function (eventReason, svtObjId) {
    let requestLink;
    let titleAction;
    // Сборка header
    let divModalHeader = document.createElement("div");
    divModalHeader.className = "modal-header";
    let titleModal = document.createElement("h5");
    titleModal.className = "modal-title fs-5";
    if (null != svtObjId) {
        titleAction = "Редактировать";
    } else {
        titleAction = "Добавить";
    }
    switch (attrib) {
        case "phones":
            titleModal.innerText = titleAction + " телефон";
            break;
        case "monitors":
            titleModal.innerText = titleAction + " монитор";
            break;
        case "ups":
            titleModal.innerText = titleAction + " ИБП";
            break;
        case "scanner":
            titleModal.innerText = titleAction + " сканер";
            break;
        case "systemblock":
            titleModal.innerText = titleAction + " системный блок";
            break;
        case "server":
            titleModal.innerText = titleAction + " сервер";
            break;
         case "switch":
            titleModal.innerText = titleAction + " коммутатор/концентратор";
            break;
        case "router":
            titleModal.innerText = titleAction + " маршрутизатор";
            break;
    }
    divModalHeader.appendChild(titleModal);
    let headerCloseBtn = document.createElement("button");
    headerCloseBtn.className = "btn-close btn-close-white";
    headerCloseBtn.setAttribute("data-bs-dismiss", "modal");
    headerCloseBtn.setAttribute("aria-label", "Close");
    headerCloseBtn.id = "closeBtn";
    divModalHeader.appendChild(headerCloseBtn);
    modalParent.appendChild(divModalHeader);
    //сборка боди
    let divModalBody = document.createElement("div");
    divModalBody.className = "modal-body";
    let divContainerBody = document.createElement("div");
    divContainerBody.className = "container";
    divContainerBody.id = "modalContent";
    divContainerBody.innerHTML = ' <div class="row mt-2">' +
            '<div class="col">Район</div>' +
            '<div class="col">' +
            '<select class="form-select form-select-sm" id="locationSelect" aria-label="Default select example" ></select>' +
            '</div></div>' +
            '<div class="row mt-2">' +
            '<div class="col">Отдел</div>' +
            '<div class="col">' +
            '<select class="form-select form-select-sm" id="departmentSelect" aria-label="Default select example" ></select>' +
            '</div></div>' +
            '<div class="row mt-2">' +
            '<div class="col">ФИО</div>' +
            '<div class="col">' +
            '<select class="form-select form-select-sm" id="placeSelect" aria-label="Default select example" ></select>' +
            '</div></div>' +
            '<div class="row mt-2">' +
            '<div class="col">Модель</div>' +
            '<div class="col">' +
            '<select class="form-select form-select-sm" id="modelSelect" aria-label="Default select example" ></select>' +
            '</div></div>' +
            '<div class="row mt-2">' +
            '<div class="col">Состояние</div>' +
            '<div class="col">' +
            '<select class="form-select form-select-sm" id="statusSelect" aria-label="Default select example" >' +
            '<option value="OK" selected="">Исправен</option>' +
            '<option value="REPAIR">Ремонт</option>' +
            '<option value="MONITORING">Списание</option>' +
            '<option value="DEFECTIVE">Неисправен</option>' +
            '<option value="UTILIZATION">Утилизирован</option>' +
            '</select>' +
            '</div></div>' +
            '<div class="row mt-2">' +
            '<div class="col">Инвентарный номер</div>' +
            '<div class="col">' +
            '<input class="form-control form-control-sm" type="text" placeholder="введите инвентарный номер" aria-label="inventaryNumber" id="inventaryNumber">' +
            '</div></div>' +
            '<div class="row mt-2">' +
            '<div class="col">Серийный номер</div>' +
            '<div class="col">' +
            '<input class="form-control form-control-sm" type="text" placeholder="введите серийный номер" aria-label="serialNumber" id="serialNumber">' +
            '</div></div>';
    switch (attrib) {
        case "switch":
            
            break;
        case "router":
            
        break;
            
        default:
            let divRowYearCreated = document.createElement("div");
            divRowYearCreated.className = "row mt-2";
            let divColLabelYearCreated = document.createElement("div");
            divColLabelYearCreated.className = "col";
            divColLabelYearCreated.innerText = "Год выпуска";
            let divColSelectYearCreated = document.createElement("div");
            divColSelectYearCreated.className = "col";
            let selectYearCreated = document.createElement("select");
            selectYearCreated.className = "form-select form-select-sm";
            selectYearCreated.id = "dateCreateSelect";
            selectYearCreated.setAttribute("aria-label", "dateCreate");
            divColSelectYearCreated.appendChild(selectYearCreated);
            divRowYearCreated.appendChild(divColLabelYearCreated);
            divRowYearCreated.appendChild(divColSelectYearCreated);
            divContainerBody.appendChild(divRowYearCreated);
            
            let divRowDateStartExp = document.createElement("div");
            divRowDateStartExp.className = "row mt-2";
            let divColLabelDateStartExp = document.createElement("div");
            divColLabelDateStartExp.className = "col";
            divColLabelDateStartExp.innerText = "Дата ввода в эксплуатацию";
            let divColInputDateStartExp = document.createElement("div");
            divColInputDateStartExp.className = "col";
            let inputDateStartExp = document.createElement("input");
            inputDateStartExp.className = "form-control form-control-sm";
            inputDateStartExp.id = "startExploitation";
            inputDateStartExp.type = "date";
            inputDateStartExp.name = "startExploitation";
            inputDateStartExp.placeholder = "date";
            divColInputDateStartExp.appendChild(inputDateStartExp);
            divRowDateStartExp.appendChild(divColLabelDateStartExp);
            divRowDateStartExp.appendChild(divColInputDateStartExp);
            divContainerBody.appendChild(divRowDateStartExp);
            break;
    }
    switch (attrib) {
        case "phones":
            let divRowPhones = document.createElement("div");
            divRowPhones.className = "row mt-2";
            let divColLabelPhones = document.createElement("div");
            divColLabelPhones.className = "col";
            divColLabelPhones.innerText = "Внутренний номер";
            let divColInputPhones = document.createElement("div");
            divColInputPhones.className = "col";
            let inputPhones = document.createElement("input");
            inputPhones.className = "form-control form-control-sm";
            inputPhones.type = "text";
            inputPhones.placeholder = "введите внутренний номер";
            inputPhones.id = "innerCallNumber";
            inputPhones.setAttribute("aria-label", "innerCallNumber");
            divColInputPhones.appendChild(inputPhones);
            divRowPhones.appendChild(divColLabelPhones);
            divRowPhones.appendChild(divColInputPhones);
            divContainerBody.appendChild(divRowPhones);
            break;
        case "monitors":
            let divRowNameFromOneC = document.createElement("div");
            divRowNameFromOneC.className = "row mt-2";
            let divColLabelNameFromOneC = document.createElement("div");
            divColLabelNameFromOneC.className = "col";
            divColLabelNameFromOneC.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneC = document.createElement("div");
            divColInputNameFromOneC.className = "col";
            let inputNameFromOneC = document.createElement("input");
            inputNameFromOneC.className = "form-control form-control-sm";
            inputNameFromOneC.type = "text";
            inputNameFromOneC.placeholder = "введите наименование";
            inputNameFromOneC.id = "nameFromOneC";
            inputNameFromOneC.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneC.appendChild(inputNameFromOneC);
            divRowNameFromOneC.appendChild(divColLabelNameFromOneC);
            divRowNameFromOneC.appendChild(divColInputNameFromOneC);
            divContainerBody.appendChild(divRowNameFromOneC);
            let divRowBaseTypeSelect = document.createElement("div");
            divRowBaseTypeSelect.className = "row mt-2";
            let divColLabelBaseTypeSelect = document.createElement("div");
            divColLabelBaseTypeSelect.className = "col";
            divColLabelBaseTypeSelect.innerText = "По ведомости ОС";
            let divColBaseTypeSelect = document.createElement("div");
            divColBaseTypeSelect.className = "col";
            let selectBaseType = document.createElement("select");
            selectBaseType.className = "form-select form-select-sm";
            selectBaseType.id = "baseTypeSelect";
            selectBaseType.setAttribute("aria-label", "nameFromOneC");
            let optionArm = document.createElement("option");
            optionArm.value = "ARM";
            optionArm.innerText = "В составе ПК, АРМ";
            optionArm.selected = true;
            let optionSingle = document.createElement("option");
            optionSingle.value = "SINGLE";
            optionSingle.innerText = "Отдельно";
            selectBaseType.appendChild(optionArm);
            selectBaseType.appendChild(optionSingle);
            divColBaseTypeSelect.appendChild(selectBaseType);
            divRowBaseTypeSelect.appendChild(divColLabelBaseTypeSelect);
            divRowBaseTypeSelect.appendChild(divColBaseTypeSelect);
            divContainerBody.appendChild(divRowBaseTypeSelect);
            break;
        case "ups":
            let divRowDateReplaceBattery = document.createElement("div");
            divRowDateReplaceBattery.className = "row mt-2";
            let divColLabelDateReplaceBattery = document.createElement("div");
            divColLabelDateReplaceBattery.className = "col";
            divColLabelDateReplaceBattery.innerText = "Год замены батареи";
            let divColDateReplaceBatterySelect = document.createElement("div");
            divColDateReplaceBatterySelect.className = "col";
            let selectDateReplaceBattery = document.createElement("select");
            selectDateReplaceBattery.className = "form-select form-select-sm";
            selectDateReplaceBattery.id = "dateReplaceSelect";
            selectDateReplaceBattery.setAttribute("aria-label", "dateReplaceSelect");
            divColDateReplaceBatterySelect.appendChild(selectDateReplaceBattery);
            divRowDateReplaceBattery.appendChild(divColLabelDateReplaceBattery);
            divRowDateReplaceBattery.appendChild(divColDateReplaceBatterySelect);
            divContainerBody.appendChild(divRowDateReplaceBattery);
            let divRowBatteryType = document.createElement("div");
            divRowBatteryType.className = "row mt-2";
            let divColLabelBatteryType = document.createElement("div");
            divColLabelBatteryType.className = "col";
            divColLabelBatteryType.innerText = "Тип батареи";
            let divColBatteryTypeSelect = document.createElement("div");
            divColBatteryTypeSelect.className = "col";
            let selectBatteryType = document.createElement("select");
            selectBatteryType.className = "form-select form-select-sm";
            selectBatteryType.id = "batteryTypeSelect";
            selectBatteryType.setAttribute("aria-label", "batteryTypeSelect");
            divColBatteryTypeSelect.appendChild(selectBatteryType);
            divRowBatteryType.appendChild(divColLabelBatteryType);
            divRowBatteryType.appendChild(divColBatteryTypeSelect);
            divContainerBody.appendChild(divRowBatteryType);
            let divRowAmountBatteries = document.createElement("div");
            divRowAmountBatteries.className = "row mt-2";
            let divColLabelAmountBatteries = document.createElement("div");
            divColLabelAmountBatteries.className = "col";
            divColLabelAmountBatteries.innerText = "Количество батарей";
            let divColInputAmountBatteries = document.createElement("div");
            divColInputAmountBatteries.className = "col";
            let inputAmountBatteries = document.createElement("input");
            inputAmountBatteries.className = "form-control form-control-sm";
            inputAmountBatteries.type = "number";
            inputAmountBatteries.min = "1";
            inputAmountBatteries.max = "100";
            inputAmountBatteries.value = "1";
            inputAmountBatteries.id = "batteryAmount";
            inputAmountBatteries.setAttribute("aria-label", "battery-amount");
            divColInputAmountBatteries.appendChild(inputAmountBatteries);
            divRowAmountBatteries.appendChild(divColLabelAmountBatteries);
            divRowAmountBatteries.appendChild(divColInputAmountBatteries);
            divContainerBody.appendChild(divRowAmountBatteries);
            break;
        case "scanner":
            let divRowIpAdress = document.createElement("div");
            divRowIpAdress.className = "row mt-2";
            let divColLabelIpAdress = document.createElement("div");
            divColLabelIpAdress.className = "col";
            divColLabelIpAdress.innerText = "ip адрес";
            let divColInputIpAdress = document.createElement("div");
            divColInputIpAdress.className = "col";
            let inputIpAdress = document.createElement("input");
            inputIpAdress.className = "form-control form-control-sm";
            inputIpAdress.type = "text";
            inputIpAdress.name = "ipAdress";
            inputIpAdress.placeholder = "xxx.xxx.xxx.xxx";
            inputIpAdress.pattern = "^([0-9]{1,3}\.){3}[0-9]{1,3}$";
            inputIpAdress.id = "ipAdress";
            divColInputIpAdress.appendChild(inputIpAdress);
            divRowIpAdress.appendChild(divColLabelIpAdress);
            divRowIpAdress.appendChild(divColInputIpAdress);
            divContainerBody.appendChild(divRowIpAdress);
            let divRowNumberRoom = document.createElement("div");
            divRowNumberRoom.className = "row mt-2";
            let divColLabelNumberRoom = document.createElement("div");
            divColLabelNumberRoom.className = "col";
            divColLabelNumberRoom.innerText = "Кабинет";
            let divColInputNumberRoom = document.createElement("div");
            divColInputNumberRoom.className = "col";
            let inputNumberRoom = document.createElement("input");
            inputNumberRoom.className = "form-control form-control-sm";
            inputNumberRoom.type = "text";
            inputNumberRoom.placeholder = "укажите расположение";
            inputNumberRoom.id = "numberRoom";
            inputNumberRoom.name = "numberRoom";
            divColInputNumberRoom.appendChild(inputNumberRoom);
            divRowNumberRoom.appendChild(divColLabelNumberRoom);
            divRowNumberRoom.appendChild(divColInputNumberRoom);
            divContainerBody.appendChild(divRowNumberRoom);
            let divRowNameFromOneCScanner = document.createElement("div");
            divRowNameFromOneCScanner.className = "row mt-2";
            let divColLabelNameFromOneCScanner = document.createElement("div");
            divColLabelNameFromOneCScanner.className = "col";
            divColLabelNameFromOneCScanner.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCScanner = document.createElement("div");
            divColInputNameFromOneCScanner.className = "col";
            let inputNameFromOneCScanner = document.createElement("textarea");
            inputNameFromOneCScanner.className = "form-control form-control-sm";
            inputNameFromOneCScanner.placeholder = "введите наименование";
            inputNameFromOneCScanner.id = "nameFromOneC";
            inputNameFromOneCScanner.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCScanner.appendChild(inputNameFromOneCScanner);
            divRowNameFromOneCScanner.appendChild(divColLabelNameFromOneCScanner);
            divRowNameFromOneCScanner.appendChild(divColInputNameFromOneCScanner);
            divContainerBody.appendChild(divRowNameFromOneCScanner);
            break;
        case "systemblock":
            let divRowNumberRoomSystemBlock = document.createElement("div");
            divRowNumberRoomSystemBlock.className = "row mt-2";
            let divColLabelNumberRoomSystemBlock = document.createElement("div");
            divColLabelNumberRoomSystemBlock.className = "col";
            divColLabelNumberRoomSystemBlock.innerText = "Кабинет";
            let divColInputNumberRoomSystemBlock = document.createElement("div");
            divColInputNumberRoomSystemBlock.className = "col";
            let inputNumberRoomSystemBlock = document.createElement("input");
            inputNumberRoomSystemBlock.className = "form-control form-control-sm";
            inputNumberRoomSystemBlock.type = "text";
            inputNumberRoomSystemBlock.placeholder = "укажите расположение";
            inputNumberRoomSystemBlock.id = "numberRoom";
            inputNumberRoomSystemBlock.name = "numberRoom";
            divColInputNumberRoomSystemBlock.appendChild(inputNumberRoomSystemBlock);
            divRowNumberRoomSystemBlock.appendChild(divColLabelNumberRoomSystemBlock);
            divRowNumberRoomSystemBlock.appendChild(divColInputNumberRoomSystemBlock);
            divContainerBody.appendChild(divRowNumberRoomSystemBlock);
            let divRowNameFromOneCSysblock = document.createElement("div");
            divRowNameFromOneCSysblock.className = "row mt-2";
            let divColLabelNameFromOneCSysblock = document.createElement("div");
            divColLabelNameFromOneCSysblock.className = "col";
            divColLabelNameFromOneCSysblock.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCSysblock = document.createElement("div");
            divColInputNameFromOneCSysblock.className = "col";
            let inputNameFromOneCSysblock = document.createElement("textarea");
            inputNameFromOneCSysblock.className = "form-control form-control-sm";
            inputNameFromOneCSysblock.placeholder = "введите наименование";
            inputNameFromOneCSysblock.id = "nameFromOneC";
            inputNameFromOneCSysblock.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCSysblock.appendChild(inputNameFromOneCSysblock);
            divRowNameFromOneCSysblock.appendChild(divColLabelNameFromOneCSysblock);
            divRowNameFromOneCSysblock.appendChild(divColInputNameFromOneCSysblock);
            divContainerBody.appendChild(divRowNameFromOneCSysblock);
            let divRowOsSelect = document.createElement("div");
            divRowOsSelect.className = "row mt-2";
            let divColLabelOsSelect = document.createElement("div");
            divColLabelOsSelect.className = "col";
            divColLabelOsSelect.innerText = "Операционная система";
            let divColOsSelect = document.createElement("div");
            divColOsSelect.className = "col";
            let selectOs = document.createElement("select");
            selectOs.className = "form-select form-select-sm";
            selectOs.id = "osSelect";
            selectOs.setAttribute("aria-label", "osSelect");
            divColOsSelect.appendChild(selectOs);
            divRowOsSelect.appendChild(divColLabelOsSelect);
            divRowOsSelect.appendChild(divColOsSelect);
            divContainerBody.appendChild(divRowOsSelect);
            var divRowIpAdressSystemBlock = document.createElement("div");
            divRowIpAdressSystemBlock.className = "row mt-2";
            var divColLabelIpAdressSystemBlock = document.createElement("div");
            divColLabelIpAdressSystemBlock.className = "col";
            divColLabelIpAdressSystemBlock.innerText = "ip адрес";
            var divColInputIpAdressSystemBlock = document.createElement("div");
            divColInputIpAdressSystemBlock.className = "col";
            var inputIpAdressSystemBlock = document.createElement("input");
            inputIpAdressSystemBlock.className = "form-control form-control-sm";
            inputIpAdressSystemBlock.type = "text";
            inputIpAdressSystemBlock.name = "ipAdress";
            inputIpAdressSystemBlock.placeholder = "xxx.xxx.xxx.xxx";
            inputIpAdressSystemBlock.pattern = "^([0-9]{1,3}\.){3}[0-9]{1,3}$";
            inputIpAdressSystemBlock.id = "ipAdress";
            divColInputIpAdressSystemBlock.appendChild(inputIpAdressSystemBlock);
            divRowIpAdressSystemBlock.appendChild(divColLabelIpAdressSystemBlock);
            divRowIpAdressSystemBlock.appendChild(divColInputIpAdressSystemBlock);
            divContainerBody.appendChild(divRowIpAdressSystemBlock);
            let divRowDateUpgrade = document.createElement("div");
            divRowDateUpgrade.className = "row mt-2";
            let divColLabelDateUpgrade = document.createElement("div");
            divColLabelDateUpgrade.className = "col";
            divColLabelDateUpgrade.innerText = "Дата модернизации";
            let divColInputDateUpgrade = document.createElement("div");
            divColInputDateUpgrade.className = "col";
            let inputDateUpgrade = document.createElement("input");
            inputDateUpgrade.className = "form-control form-control-sm";
            inputDateUpgrade.type = "date";
            inputDateUpgrade.name = "dateUpgrade";
            inputDateUpgrade.placeholder = "дата модернизации";
            inputDateUpgrade.id = "dateUpgrade";
            divColInputDateUpgrade.appendChild(inputDateUpgrade);
            divRowDateUpgrade.appendChild(divColLabelDateUpgrade);
            divRowDateUpgrade.appendChild(divColInputDateUpgrade);
            divContainerBody.appendChild(divRowDateUpgrade);
            let divRowComponentsTitle = document.createElement("div");
            divRowComponentsTitle.className = "row mt-2";
            let divColComponentsTitle = document.createElement("div");
            divColComponentsTitle.className = "col text-center fw-bold";
            divColComponentsTitle.innerText = "Перечень комплектующих";
            divRowComponentsTitle.appendChild(divColComponentsTitle);
            divContainerBody.appendChild(divRowComponentsTitle);
            let divRowMotherboardSelect = document.createElement("div");
            divRowMotherboardSelect.className = "row mt-2";
            let divColLabelMotherboardSelect = document.createElement("div");
            divColLabelMotherboardSelect.className = "col";
            divColLabelMotherboardSelect.innerText = "Материнская плата";
            let divColMotherboardSelect = document.createElement("div");
            divColMotherboardSelect.className = "col";
            let selectMotherboard = document.createElement("select");
            selectMotherboard.className = "form-select form-select-sm";
            selectMotherboard.id = "motherboardSelect";
            selectMotherboard.setAttribute("aria-label", "motherboardSelect");
            divColMotherboardSelect.appendChild(selectMotherboard);
            divRowMotherboardSelect.appendChild(divColLabelMotherboardSelect);
            divRowMotherboardSelect.appendChild(divColMotherboardSelect);
            divContainerBody.appendChild(divRowMotherboardSelect);
            let divRowCpuSelect = document.createElement("div");
            divRowCpuSelect.className = "row mt-2";
            let divColLabelCpuSelect = document.createElement("div");
            divColLabelCpuSelect.className = "col";
            divColLabelCpuSelect.innerText = "Процессор";
            let divColCpuSelect = document.createElement("div");
            divColCpuSelect.className = "col";
            let selectCpu = document.createElement("select");
            selectCpu.className = "form-select form-select-sm";
            selectCpu.id = "cpuSelect";
            selectCpu.setAttribute("aria-label", "cpuSelect");
            divColCpuSelect.appendChild(selectCpu);
            divRowCpuSelect.appendChild(divColLabelCpuSelect);
            divRowCpuSelect.appendChild(divColCpuSelect);
            divContainerBody.appendChild(divRowCpuSelect);
            let divRowRamSelect = document.createElement("div");
            divRowRamSelect.className = "row mt-2";
            let divColLabelRamSelect = document.createElement("div");
            divColLabelRamSelect.className = "col";
            divColLabelRamSelect.innerText = "ОЗУ";
            let divColRamSelect = document.createElement("div");
            divColRamSelect.className = "col";
            let selectRam = document.createElement("select");
            selectRam.className = "form-select form-select-sm";
            selectRam.id = "ramSelect";
            selectRam.setAttribute("aria-label", "ramSelect");
            divColRamSelect.appendChild(selectRam);
            divRowRamSelect.appendChild(divColLabelRamSelect);
            divRowRamSelect.appendChild(divColRamSelect);
            divContainerBody.appendChild(divRowRamSelect);
            let divRowHddSelect = document.createElement("div");
            divRowHddSelect.className = "row mt-2";
            let divColLabelHddSelect = document.createElement("div");
            divColLabelHddSelect.className = "col";
            divColLabelHddSelect.innerText = "НЖМД";
            let divColHddSelect = document.createElement("div");
            divColHddSelect.className = "col";
            let selectHdd = document.createElement("select");
            selectHdd.className = "form-select form-select-sm";
            selectHdd.id = "hddSelect";
            selectHdd.setAttribute("aria-label", "hddSelect");
            divColHddSelect.appendChild(selectHdd);
            divRowHddSelect.appendChild(divColLabelHddSelect);
            divRowHddSelect.appendChild(divColHddSelect);
            divContainerBody.appendChild(divRowHddSelect);
            let divRowVideoCardSelect = document.createElement("div");
            divRowVideoCardSelect.className = "row mt-2";
            let divColLabelVideoCardSelect = document.createElement("div");
            divColLabelVideoCardSelect.className = "col";
            divColLabelVideoCardSelect.innerText = "Видеоадаптер";
            let divColVideoCardSelect = document.createElement("div");
            divColVideoCardSelect.className = "col";
            let selectVideoCard = document.createElement("select");
            selectVideoCard.className = "form-select form-select-sm";
            selectVideoCard.id = "videoCardSelect";
            selectVideoCard.setAttribute("aria-label", "videoCardSelect");
            divColVideoCardSelect.appendChild(selectVideoCard);
            divRowVideoCardSelect.appendChild(divColLabelVideoCardSelect);
            divRowVideoCardSelect.appendChild(divColVideoCardSelect);
            divContainerBody.appendChild(divRowVideoCardSelect);
            let divRowSoundCardSelect = document.createElement("div");
            divRowSoundCardSelect.className = "row mt-2";
            let divColLabelSoundCardSelect = document.createElement("div");
            divColLabelSoundCardSelect.className = "col";
            divColLabelSoundCardSelect.innerText = "Звуковая карта";
            let divColSoundCardSelect = document.createElement("div");
            divColSoundCardSelect.className = "col";
            let selectSoundCard = document.createElement("select");
            selectSoundCard.className = "form-select form-select-sm";
            selectSoundCard.id = "soundCardSelect";
            selectSoundCard.setAttribute("aria-label", "soundCardSelect");
            divColSoundCardSelect.appendChild(selectSoundCard);
            divRowSoundCardSelect.appendChild(divColLabelSoundCardSelect);
            divRowSoundCardSelect.appendChild(divColSoundCardSelect);
            divContainerBody.appendChild(divRowSoundCardSelect);
            let divRowLanCardSelect = document.createElement("div");
            divRowLanCardSelect.className = "row mt-2";
            let divColLabelLanCardSelect = document.createElement("div");
            divColLabelLanCardSelect.className = "col";
            divColLabelLanCardSelect.innerText = "Сетевая карта";
            let divColLanCardSelect = document.createElement("div");
            divColLanCardSelect.className = "col";
            let selectLanCard = document.createElement("select");
            selectLanCard.className = "form-select form-select-sm";
            selectLanCard.id = "lanCardSelect";
            selectLanCard.setAttribute("aria-label", "lanCardSelect");
            divColLanCardSelect.appendChild(selectLanCard);
            divRowLanCardSelect.appendChild(divColLabelLanCardSelect);
            divRowLanCardSelect.appendChild(divColLanCardSelect);
            divContainerBody.appendChild(divRowLanCardSelect);
            let divRowCdDriveSelect = document.createElement("div");
            divRowCdDriveSelect.className = "row mt-2";
            let divColLabelCdDriveSelect = document.createElement("div");
            divColLabelCdDriveSelect.className = "col";
            divColLabelCdDriveSelect.innerText = "Привод";
            let divColCdDriveSelect = document.createElement("div");
            divColCdDriveSelect.className = "col";
            let selectCdDrive = document.createElement("select");
            selectCdDrive.className = "form-select form-select-sm";
            selectCdDrive.id = "cdDriveSelect";
            selectCdDrive.setAttribute("aria-label", "cdDriveSelect");
            divColCdDriveSelect.appendChild(selectCdDrive);
            divRowCdDriveSelect.appendChild(divColLabelCdDriveSelect);
            divRowCdDriveSelect.appendChild(divColCdDriveSelect);
            divContainerBody.appendChild(divRowCdDriveSelect);
            let divRowKeyboardSelect = document.createElement("div");
            divRowKeyboardSelect.className = "row mt-2";
            let divColLabelKeyboardSelect = document.createElement("div");
            divColLabelKeyboardSelect.className = "col";
            divColLabelKeyboardSelect.innerText = "Клавиатура";
            let divColKeyboardSelect = document.createElement("div");
            divColKeyboardSelect.className = "col";
            let selectKeyboard = document.createElement("select");
            selectKeyboard.className = "form-select form-select-sm";
            selectKeyboard.id = "keyboardSelect";
            selectKeyboard.setAttribute("aria-label", "keyboardSelect");
            divColKeyboardSelect.appendChild(selectKeyboard);
            divRowKeyboardSelect.appendChild(divColLabelKeyboardSelect);
            divRowKeyboardSelect.appendChild(divColKeyboardSelect);
            divContainerBody.appendChild(divRowKeyboardSelect);
            let divRowMouseSelect = document.createElement("div");
            divRowMouseSelect.className = "row mt-2";
            let divColLabelMouseSelect = document.createElement("div");
            divColLabelMouseSelect.className = "col";
            divColLabelMouseSelect.innerText = "Мышь";
            let divColMouseSelect = document.createElement("div");
            divColMouseSelect.className = "col";
            let selectMouse = document.createElement("select");
            selectMouse.className = "form-select form-select-sm";
            selectMouse.id = "mouseSelect";
            selectMouse.setAttribute("aria-label", "mouseSelect");
            divColMouseSelect.appendChild(selectMouse);
            divRowMouseSelect.appendChild(divColLabelMouseSelect);
            divRowMouseSelect.appendChild(divColMouseSelect);
            divContainerBody.appendChild(divRowMouseSelect);
            let divRowSpeakersSelect = document.createElement("div");
            divRowSpeakersSelect.className = "row mt-2";
            let divColLabelSpeakersSelect = document.createElement("div");
            divColLabelSpeakersSelect.className = "col";
            divColLabelSpeakersSelect.innerText = "Колонки";
            let divColSpeakersSelect = document.createElement("div");
            divColSpeakersSelect.className = "col";
            let selectSpeakers = document.createElement("select");
            selectSpeakers.className = "form-select form-select-sm";
            selectSpeakers.id = "speakersSelect";
            selectSpeakers.setAttribute("aria-label", "speakersSelect");
            divColSpeakersSelect.appendChild(selectSpeakers);
            divRowSpeakersSelect.appendChild(divColLabelSpeakersSelect);
            divRowSpeakersSelect.appendChild(divColSpeakersSelect);
            divContainerBody.appendChild(divRowSpeakersSelect);
            break;
        case "server":
            let divRowNumberRoomServer = document.createElement("div");
            divRowNumberRoomServer.className = "row mt-2";
            let divColLabelNumberRoomServer = document.createElement("div");
            divColLabelNumberRoomServer.className = "col";
            divColLabelNumberRoomServer.innerText = "Кабинет";
            let divColInputNumberRoomServer = document.createElement("div");
            divColInputNumberRoomServer.className = "col";
            let inputNumberRoomServer = document.createElement("input");
            inputNumberRoomServer.className = "form-control form-control-sm";
            inputNumberRoomServer.type = "text";
            inputNumberRoomServer.placeholder = "укажите расположение";
            inputNumberRoomServer.id = "numberRoom";
            inputNumberRoomServer.name = "numberRoom";
            divColInputNumberRoomServer.appendChild(inputNumberRoomServer);
            divRowNumberRoomServer.appendChild(divColLabelNumberRoomServer);
            divRowNumberRoomServer.appendChild(divColInputNumberRoomServer);
            divContainerBody.appendChild(divRowNumberRoomServer);
            let divRowNameFromOneCServer = document.createElement("div");
            divRowNameFromOneCServer.className = "row mt-2";
            let divColLabelNameFromOneCServer = document.createElement("div");
            divColLabelNameFromOneCServer.className = "col";
            divColLabelNameFromOneCServer.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCServer = document.createElement("div");
            divColInputNameFromOneCServer.className = "col";
            let inputNameFromOneCServer = document.createElement("textarea");
            inputNameFromOneCServer.className = "form-control form-control-sm";
            inputNameFromOneCServer.placeholder = "введите наименование";
            inputNameFromOneCServer.id = "nameFromOneC";
            inputNameFromOneCServer.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCServer.appendChild(inputNameFromOneCServer);
            divRowNameFromOneCServer.appendChild(divColLabelNameFromOneCServer);
            divRowNameFromOneCServer.appendChild(divColInputNameFromOneCServer);
            divContainerBody.appendChild(divRowNameFromOneCServer);
            let divRowServerOsSelect = document.createElement("div");
            divRowServerOsSelect.className = "row mt-2";
            let divColLabelServerOsSelect = document.createElement("div");
            divColLabelServerOsSelect.className = "col";
            divColLabelServerOsSelect.innerText = "Операционная система";
            let divColServerOsSelect = document.createElement("div");
            divColServerOsSelect.className = "col";
            let selectServerOs = document.createElement("select");
            selectServerOs.className = "form-select form-select-sm";
            selectServerOs.id = "osSelect";
            selectServerOs.setAttribute("aria-label", "osSelect");
            divColServerOsSelect.appendChild(selectServerOs);
            divRowServerOsSelect.appendChild(divColLabelServerOsSelect);
            divRowServerOsSelect.appendChild(divColServerOsSelect);
            divContainerBody.appendChild(divRowServerOsSelect);
            var divRowIpAdressServer = document.createElement("div");
            divRowIpAdressServer.className = "row mt-2";
            var divColLabelIpAdressServer = document.createElement("div");
            divColLabelIpAdressServer.className = "col";
            divColLabelIpAdressServer.innerText = "ip адрес";
            var divColInputIpAdressServer = document.createElement("div");
            divColInputIpAdressServer.className = "col";
            var inputIpAdressServer = document.createElement("input");
            inputIpAdressServer.className = "form-control form-control-sm";
            inputIpAdressServer.type = "text";
            inputIpAdressServer.name = "ipAdress";
            inputIpAdressServer.placeholder = "xxx.xxx.xxx.xxx";
            inputIpAdressServer.pattern = "^([0-9]{1,3}\.){3}[0-9]{1,3}$";
            inputIpAdressServer.id = "ipAdress";
            divColInputIpAdressServer.appendChild(inputIpAdressServer);
            divRowIpAdressServer.appendChild(divColLabelIpAdressServer);
            divRowIpAdressServer.appendChild(divColInputIpAdressServer);
            divContainerBody.appendChild(divRowIpAdressServer);
            let divRowDateUpgradeServer = document.createElement("div");
            divRowDateUpgradeServer.className = "row mt-2";
            let divColLabelDateUpgradeServer = document.createElement("div");
            divColLabelDateUpgradeServer.className = "col";
            divColLabelDateUpgradeServer.innerText = "Дата модернизации";
            let divColInputDateUpgradeServer = document.createElement("div");
            divColInputDateUpgradeServer.className = "col";
            let inputDateUpgradeServer = document.createElement("input");
            inputDateUpgradeServer.className = "form-control form-control-sm";
            inputDateUpgradeServer.type = "date";
            inputDateUpgradeServer.name = "dateUpgrade";
            inputDateUpgradeServer.placeholder = "дата модернизации";
            inputDateUpgradeServer.id = "dateUpgrade";
            divColInputDateUpgradeServer.appendChild(inputDateUpgradeServer);
            divRowDateUpgradeServer.appendChild(divColLabelDateUpgradeServer);
            divRowDateUpgradeServer.appendChild(divColInputDateUpgradeServer);
            divContainerBody.appendChild(divRowDateUpgradeServer);
            let divRowComponentsServerTitle = document.createElement("div");
            divRowComponentsServerTitle.className = "row mt-2";
            let divColComponentsServerTitle = document.createElement("div");
            divColComponentsServerTitle.className = "col text-center fw-bold";
            divColComponentsServerTitle.innerText = "Перечень комплектующих";
            divRowComponentsServerTitle.appendChild(divColComponentsServerTitle);
            divContainerBody.appendChild(divRowComponentsServerTitle);
            let divRowServerCpuSelect = document.createElement("div");
            divRowServerCpuSelect.className = "row mt-2";
            let divColLabelServerCpuSelect = document.createElement("div");
            divColLabelServerCpuSelect.className = "col";
            divColLabelServerCpuSelect.innerText = "Процессор";
            let divColServerCpuSelect = document.createElement("div");
            divColServerCpuSelect.className = "col";
            let selectServerCpu = document.createElement("select");
            selectServerCpu.className = "form-select form-select-sm";
            selectServerCpu.id = "cpuSelect";
            selectServerCpu.setAttribute("aria-label", "cpuSelect");
            divColServerCpuSelect.appendChild(selectServerCpu);
            divRowServerCpuSelect.appendChild(divColLabelServerCpuSelect);
            divRowServerCpuSelect.appendChild(divColServerCpuSelect);
            divContainerBody.appendChild(divRowServerCpuSelect);
            // CpuAmountInput here
            let divRowServerCpuAmount = document.createElement("div");
            divRowServerCpuAmount.className = "row mt-2";
            let divColLabelServerCpuAmount = document.createElement("div");
            divColLabelServerCpuAmount.className = "col";
            divColLabelServerCpuAmount.innerText = "Количество процессоров";
            let divColInputServerCpuAmount = document.createElement("div");
            divColInputServerCpuAmount.className = "col";
            let inputServerCpuAmount = document.createElement("input");
            inputServerCpuAmount.className = "form-control form-control-sm";
            inputServerCpuAmount.type = "number";
            inputServerCpuAmount.min = 1;
            inputServerCpuAmount.max = 99;
            inputServerCpuAmount.value = 1;
            inputServerCpuAmount.id = "cpuAmount";
            divColInputServerCpuAmount.appendChild(inputServerCpuAmount);
            divRowServerCpuAmount.appendChild(divColLabelServerCpuAmount);
            divRowServerCpuAmount.appendChild(divColInputServerCpuAmount);
            divContainerBody.appendChild(divRowServerCpuAmount);
            let divRowServerRamSelect = document.createElement("div");
            divRowServerRamSelect.className = "row mt-2";
            let divColLabelServerRamSelect = document.createElement("div");
            divColLabelServerRamSelect.className = "col";
            divColLabelServerRamSelect.innerText = "ОЗУ";
            let divColServerRamSelect = document.createElement("div");
            divColServerRamSelect.className = "col";
            let selectServerRam = document.createElement("select");
            selectServerRam.className = "form-select form-select-sm";
            selectServerRam.id = "ramSelect";
            selectServerRam.setAttribute("aria-label", "ramSelect");
            divColServerRamSelect.appendChild(selectServerRam);
            divRowServerRamSelect.appendChild(divColLabelServerRamSelect);
            divRowServerRamSelect.appendChild(divColServerRamSelect);
            divContainerBody.appendChild(divRowServerRamSelect);
            let divRowServerHddSelect = document.createElement("div");
            divRowServerHddSelect.className = "row mt-2";
            let divColLabelServerHddSelect = document.createElement("div");
            divColLabelServerHddSelect.className = "col";
            divColLabelServerHddSelect.innerText = "НЖМД";
            let divColServerHddSelect = document.createElement("div");
            divColServerHddSelect.className = "col";
            let selectServerHdd = document.createElement("select");
            selectServerHdd.className = "form-select form-select-sm";
            selectServerHdd.id = "hddSelect";
            selectServerHdd.setAttribute("aria-label", "hddSelect");
            divColServerHddSelect.appendChild(selectServerHdd);
            divRowServerHddSelect.appendChild(divColLabelServerHddSelect);
            divRowServerHddSelect.appendChild(divColServerHddSelect);
            divContainerBody.appendChild(divRowServerHddSelect);
            break;
        case "switch":
            let divSwitchHubRow = document.createElement("div");
            divSwitchHubRow.className = "row mt-2";
            let divColSwitch = document.createElement("div");
            divColSwitch.className = "col";
            let divColHub = document.createElement("div");
            divColHub.className = "col";
            let divSwitchHubRadio = document.createElement("div");
            divSwitchHubRadio.className = "form-check";
            divSwitchHubRadio.innerHTML = '<input class="form-check-input" type="radio" name="switchHubRadio" id="switchRadio" checked>' +
                                          '<label class="form-check-label" for="switchRadio">Коммутатор</label></div>';
            let divSwitchHubRadio2 = document.createElement("div");
            divSwitchHubRadio2.className = "form-check";
            divSwitchHubRadio2.innerHTML = '<input class="form-check-input" type="radio" name="switchHubRadio" id="hubRadio" >' +
                                          '<label class="form-check-label" for="hubRadio">Концентратор</label></div>';
            divColSwitch.appendChild(divSwitchHubRadio);
            divColHub.appendChild(divSwitchHubRadio2);
            divSwitchHubRow.appendChild(divColSwitch);
            divSwitchHubRow.appendChild(divColHub);
            divContainerBody.appendChild(divSwitchHubRow);
            
            let divRowNumberRoomSwitch = document.createElement("div");
            divRowNumberRoomSwitch.className = "row mt-2";
            let divColLabelNumberRoomSwitch = document.createElement("div");
            divColLabelNumberRoomSwitch.className = "col";
            divColLabelNumberRoomSwitch.innerText = "Кабинет";
            let divColInputNumberRoomSwitch = document.createElement("div");
            divColInputNumberRoomSwitch.className = "col";
            
            let inputNumberRoomSwitch = document.createElement("input");
            inputNumberRoomSwitch.className = "form-control form-control-sm";
            inputNumberRoomSwitch.type = "text";
            inputNumberRoomSwitch.placeholder = "укажите расположение";
            inputNumberRoomSwitch.id = "numberRoom";
            inputNumberRoomSwitch.name = "numberRoom";
            divColInputNumberRoomSwitch.appendChild(inputNumberRoomSwitch);
            divRowNumberRoomSwitch.appendChild(divColLabelNumberRoomSwitch);
            divRowNumberRoomSwitch.appendChild(divColInputNumberRoomSwitch);
            divContainerBody.appendChild(divRowNumberRoomSwitch);
            
            let portAmountSwitchRow = document.createElement("div");
            portAmountSwitchRow.className = "row mt-2";
            let divColLabelSwitchPortAmount = document.createElement("div");
            divColLabelSwitchPortAmount.className = "col";
            divColLabelSwitchPortAmount.innerText = "Количество портов";
            let divColInputSwitchPortAmount = document.createElement("div");
            divColInputSwitchPortAmount.className = "col";
            let inputSwitchPortAmount = document.createElement("input");
            inputSwitchPortAmount.className = "form-control form-control-sm";
            inputSwitchPortAmount.type = "number";
            inputSwitchPortAmount.min = 1;
            inputSwitchPortAmount.max = 99;
            inputSwitchPortAmount.value = 1;
            inputSwitchPortAmount.id = "portAmount";
            divColInputSwitchPortAmount.appendChild(inputSwitchPortAmount);
            portAmountSwitchRow.appendChild(divColLabelSwitchPortAmount);
            portAmountSwitchRow.appendChild(divColInputSwitchPortAmount);
            divContainerBody.appendChild(portAmountSwitchRow);
             let divRowNameFromOneCSwitch = document.createElement("div");
            divRowNameFromOneCSwitch.className = "row mt-2";
            let divColLabelNameFromOneCSwitch = document.createElement("div");
            divColLabelNameFromOneCSwitch.className = "col";
            divColLabelNameFromOneCSwitch.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCSwitch = document.createElement("div");
            divColInputNameFromOneCSwitch.className = "col";
            let inputNameFromOneCSwitch = document.createElement("textarea");
            inputNameFromOneCSwitch.className = "form-control form-control-sm";
            inputNameFromOneCSwitch.placeholder = "введите наименование";
            inputNameFromOneCSwitch.id = "nameFromOneC";
            inputNameFromOneCSwitch.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCSwitch.appendChild(inputNameFromOneCSwitch);
            divRowNameFromOneCSwitch.appendChild(divColLabelNameFromOneCSwitch);
            divRowNameFromOneCSwitch.appendChild(divColInputNameFromOneCSwitch);
            divContainerBody.appendChild(divRowNameFromOneCSwitch);
            break;
        case "router":
             let divRowNumberRoomRouter = document.createElement("div");
            divRowNumberRoomRouter.className = "row mt-2";
            let divColLabelNumberRoomRouter = document.createElement("div");
            divColLabelNumberRoomRouter.className = "col";
            divColLabelNumberRoomRouter.innerText = "Кабинет";
            let divColInputNumberRoomRouter = document.createElement("div");
            divColInputNumberRoomRouter.className = "col";
            
            let inputNumberRoomRouter = document.createElement("input");
            inputNumberRoomRouter.className = "form-control form-control-sm";
            inputNumberRoomRouter.type = "text";
            inputNumberRoomRouter.placeholder = "укажите расположение";
            inputNumberRoomRouter.id = "numberRoom";
            inputNumberRoomRouter.name = "numberRoom";
            divColInputNumberRoomRouter.appendChild(inputNumberRoomRouter);
            divRowNumberRoomRouter.appendChild(divColLabelNumberRoomRouter);
            divRowNumberRoomRouter.appendChild(divColInputNumberRoomRouter);
            divContainerBody.appendChild(divRowNumberRoomRouter);
            
            let portAmountRouterRow = document.createElement("div");
            portAmountRouterRow.className = "row mt-2";
            let divColLabelRouterPortAmount = document.createElement("div");
            divColLabelRouterPortAmount.className = "col";
            divColLabelRouterPortAmount.innerText = "Количество портов";
            let divColInputRouterPortAmount = document.createElement("div");
            divColInputRouterPortAmount.className = "col";
            let inputRouterPortAmount = document.createElement("input");
            inputRouterPortAmount.className = "form-control form-control-sm";
            inputRouterPortAmount.type = "number";
            inputRouterPortAmount.min = 1;
            inputRouterPortAmount.max = 99;
            inputRouterPortAmount.value = 1;
            inputRouterPortAmount.id = "portAmount";
            divColInputRouterPortAmount.appendChild(inputRouterPortAmount);
            portAmountRouterRow.appendChild(divColLabelRouterPortAmount);
            portAmountRouterRow.appendChild(divColInputRouterPortAmount);
            divContainerBody.appendChild(portAmountRouterRow);
             let divRowNameFromOneCRouter = document.createElement("div");
            divRowNameFromOneCRouter.className = "row mt-2";
            let divColLabelNameFromOneCRouter = document.createElement("div");
            divColLabelNameFromOneCRouter.className = "col";
            divColLabelNameFromOneCRouter.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCRouter = document.createElement("div");
            divColInputNameFromOneCRouter.className = "col";
            let inputNameFromOneCRouter = document.createElement("textarea");
            inputNameFromOneCRouter.className = "form-control form-control-sm";
            inputNameFromOneCRouter.placeholder = "введите наименование";
            inputNameFromOneCRouter.id = "nameFromOneC";
            inputNameFromOneCRouter.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCRouter.appendChild(inputNameFromOneCRouter);
            divRowNameFromOneCRouter.appendChild(divColLabelNameFromOneCRouter);
            divRowNameFromOneCRouter.appendChild(divColInputNameFromOneCRouter);
            divContainerBody.appendChild(divRowNameFromOneCRouter);
            break;
    }
    divModalBody.appendChild(divContainerBody);
    modalParent.appendChild(divModalBody);
    //сборка футера
    let divModalFooter = document.createElement("div");
    divModalFooter.className = "modal-footer svtObjModalFooter";
    let footerBtnCancel = document.createElement("button");
    footerBtnCancel.className = "btn btn-secondary btn-sm";
    footerBtnCancel.setAttribute("data-bs-dismiss", "modal");
    footerBtnCancel.innerText = "Отменить";
    let footerBtnSave = document.createElement("button");
    footerBtnSave.className = "btn btn-primary btn-sm";
    footerBtnSave.id = "btnSave";
    divModalFooter.appendChild(footerBtnCancel);
    divModalFooter.appendChild(footerBtnSave);
    modalParent.appendChild(divModalFooter);
    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
        switch (attrib) {
            case "phones":
                requestLink = "/getphone?phoneId=";
                break;
            case "monitors":
                requestLink = "/getmonitor?monitorId=";
                break;
            case "ups":
                requestLink = "/getups?upsId=";
                break;
            case "systemblock":
                requestLink = "/getsystemblock?systemblockId=";
                break;
            case "scanner":
                requestLink = "/getscanner?scannerId=";
                break;
            case "server":
                requestLink = "/getserver?serverId=";
                break;
            case "switch":
                requestLink = "/getswitch?switchId=";
                break;
            case "router":
                requestLink = "/getrouter?routerId=";
                break;
        }
        $.ajax({
            url: requestLink + svtObjId,
            type: 'GET',
            async: false,
            dataType: 'json',
            success: function (callback) {
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
                if (attrib == "systemblock") {
                    operationSystemId = callback.operationSystemId;
                    motherboardId = callback.motherboardId;
                    cpuId = callback.cpuId;
                    ramId = callback.ramId;
                    hddListId = callback.hddIdList;
                    videoCardId = callback.videoCardId;
                    soundCardId = callback.soundCardId;
                    lanCardId = callback.lanCardId;
                    cdDriveId = callback.cdDriveId;
                    keyboardId = callback.keyboardId;
                    mouseId = callback.mouseId;
                    speakersId = callback.speakersId;
                    ipAdress = callback.ipAdress;
                    numberRoom = callback.numberRoom;
                    dateUpgrade = callback.dateUpgrade;
                } else if (attrib == "scanner") {
                    ipAdress = callback.ipAdress;
                    numberRoom = callback.numberRoom;
                } else if (attrib == "server") {
                    operationSystemId = callback.operationSystemId;
                    cpuId = callback.cpuId;
                    cpuAmount = callback.cpuAmount;
                    ramId = callback.ramId;
                    hddListId = callback.hddIdList;
                    ipAdress = callback.ipAdress;
                    numberRoom = callback.numberRoom;
                    dateUpgrade = callback.dateUpgrade;
                }else if (attrib == "switch") {
                    portAmount = callback.portAmount;
                    numberRoom = callback.numberRoom;
                    switchHubType = callback.switchHubType;
                } else if (attrib == "router") {
                    portAmount = callback.portAmount;
                    numberRoom = callback.numberRoom;
                }
            }
        });
        if(parseStartDate) {
        startDate = new Date(parseStartDate);
        startExploitation = startDate.toISOString().slice(0, 10);
    }
        switch (status) {
            case "REPAIR":
                $("#statusSelect")[0].value = "REPAIR";
                break;
            case "MONITORING":
                $("#statusSelect")[0].value = "MONITORING";
                break;
            case "UTILIZATION":
                $("#statusSelect")[0].value = "UTILIZATION";
                break;
            case "OK":
                $("#statusSelect")[0].value = "OK";
                break;
            case "DEFECTIVE":
                $("#statusSelect")[0].value = "DEFECTIVE";
                break;
        }
        $("#inventaryNumber")[0].value = inventary;
        $("#serialNumber")[0].value = serial;
        if(null != $("#startExploitation")[0]) {
            $("#startExploitation")[0].value = startExploitation;
        }
        
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
            case "systemblock":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#ipAdress")[0].value = ipAdress;
                if (dateUpgrade != null) {
                    let parseDateUpgrade = new Date(dateUpgrade);
                    let dateUpgradeParsed = parseDateUpgrade.toISOString().slice(0, 10);
                    $("#dateUpgrade")[0].value = dateUpgradeParsed;
                }
                break;
            case "scanner":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#ipAdress")[0].value = ipAdress;
                break;
            case "server":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#ipAdress")[0].value = ipAdress;
                $("#cpuAmount")[0].value = cpuAmount;
                if (dateUpgrade != null) {
                    let parseDateUpgrade = new Date(dateUpgrade);
                    let dateUpgradeParsed = parseDateUpgrade.toISOString().slice(0, 10);
                    $("#dateUpgrade")[0].value = dateUpgradeParsed;
                }
                break;
            case "switch":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#portAmount")[0].value = portAmount;
                switch (switchHubType) {
                    case "SWITCH":
                        $("#switchRadio")[0].checked = true;    
                        break;
                    case "HUB":
                        $("#hubRadio")[0].checked = true;
                        break;
                }
                break;
            case "router":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#portAmount")[0].value = portAmount;
                break;
        }
        if (eventReason.indexOf("storage") >= 0) {
            stor = true;
            $('#statusSelect')[0].disabled = true;
            $("#inventaryNumber")[0].disabled = true;
            $("#serialNumber")[0].disabled = true;
            if(null != $("#startExploitation")[0]) {
                $("#startExploitation")[0].disabled = true;
            }
            
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
                case "systemblock":
                    $("#ipAdress")[0].disabled = true;
                    $("#numberRoom")[0].disabled = true;
                    $("#nameFromOneC")[0].disabled = true;
                    $("#dateUpgrade")[0].disabled = true;
                    break;
                case "server":
                    $("#ipAdress")[0].disabled = true;
                    $("#numberRoom")[0].disabled = true;
                    $("#nameFromOneC")[0].disabled = true;
                    $("#cpuAmount")[0].disabled = true;
                    $("#dateUpgrade")[0].disabled = true;
                    break;
                case "switch":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#portAmount")[0].value = portAmount;
                switch (switchHubType) {
                    case "SWITCH":
                        $("#switchRadio")[0].checked = true;    
                        break;
                    case "HUB":
                        $("#hubRadio")[0].checked = true;
                        break;
                }
                break;
                case "router":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#portAmount")[0].value = portAmount;
                break;
            }
        }
    }
    $('#locationSelect').selectize({
        preload: true,
        valueField: 'id',
        labelField: 'name',
        searchField: ["id", "name"],
        load: function (query, callback) {
            switch (placeAttrib) {
                case "serverroom":
                    $.ajax({
                        url: '/locplacetype?placeType=SERVERROOM',
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: callback,
                        success: callback
                    });
                    if(null != locationId) {
                        oldLocationId = $('#locationSelect')[0].selectize.search(locationId).items[0].id;
                        $('#locationSelect')[0].selectize.setValue($('#locationSelect')[0].selectize.search(locationId).items[0].id);
                    } else {
                        oldLocationId = $('#locationSelect')[0].selectize.search(0).items[0].id;
                        $('#locationSelect')[0].selectize.setValue($('#locationSelect')[0].selectize.search(0).items[0].id);
                    }
                    
                    break;
                    
                default:
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
                    break;
            }
             
            
           
            if (eventReason.indexOf("storage") >= 0) {
                $('#locationSelect')[0].selectize.disable();
            } else {
                $('#locationSelect')[0].selectize.enable();
            }
        },
        onChange: function (value) {
            if (value !== '') {
                if (oldLocationId !== value) {
                    oldLocationId = value;
                    switch (placeAttrib) {
                        case "serverroom":
                                   $.ajax({
                    url: '/deplocplacetype?placeType=SERVERROOM&idLocation=' + $("#locationSelect")[0].selectize.getValue(),
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
                            oldDepartment = $('#departmentSelect')[0].selectize.search(0).items[0].id;
                            $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(0).items[0].id);
                        }
                    });
                    
                    $.ajax({
                    url: '/placelocdepplacetype?placeType=SERVERROOM&idLocation=' + $("#locationSelect")[0].selectize.getValue() + '&departmentCode=' + $("#departmentSelect")[0].selectize.getValue(),
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    success: function(res) {
                         let keys = Object.keys($('#placeSelect')[0].selectize.options);
                            for (let i = 0; i < keys.length; i++) {
                                $('#placeSelect')[0].selectize.removeOption(keys[i]);
                            }
                            res.forEach(model => {
                                $('#placeSelect')[0].selectize.addOption(model);
                                $('#placeSelect')[0].selectize.addItem(model);
                            });
                            oldPlaceId = $('#placeSelect')[0].selectize.search(0).items[0].id;
                            $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                    }
                    
                });
                    
                            break;
                            
                        default:
                             $.ajax({
                        url: '/depbyloc?locationId=' + oldLocationId,
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
                            oldDepartment = $('#departmentSelect')[0].selectize.search(0).items[0].id;
                            $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(0).items[0].id);
                        }
                    });
                    let urlReq = "placebydepandloc";
                    if (placeAttrib == "serverroom") {
                        urlReq = "placeserverbydepandloc";
                    }
                    $.ajax({
                        url: '/' + urlReq + '?locationId=' + oldLocationId + '&departmentCode=' + oldDepartment,
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
                            break;
                    }
                   

                } else {
                    switch (placeAttrib) {
                        case "serverroom":
                            $.ajax({
                    url: '/deplocplacetype?placeType=SERVERROOM&idLocation=' + $("#locationSelect")[0].selectize.getValue(),
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
                            oldDepartment = $('#departmentSelect')[0].selectize.search(0).items[0].id;
                            $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(0).items[0].id);
                        }
                    });
 
                            break;
                            
                        default:
                            $.ajax({
                        url: '/depbyloc?locationId=' + $('#locationSelect')[0].selectize.getValue(),
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
                            oldDepartment = $('#departmentSelect')[0].selectize.search(0).items[0].id;
                            $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(0).items[0].id);
                        }
                    });
                            break;
                    }
                    
                }
            }
        }
    });

    if ($('#locationSelect')[0].selectize.getValue() == "" && $('#locationSelect')[0].selectize.order > 0) {

        $('#locationSelect')[0].selectize.disable();
    } else {
        $('#locationSelect')[0].selectize.enable();
    }
    $('#departmentSelect').selectize({
        preload: true,
        valueField: 'code',
        labelField: 'name',
        searchField: ["code", "name"],
        load: function (query, callback) {
            switch (placeAttrib) {
                case "serverroom":
                    $.ajax({
                    url: '/deplocplacetype?placeType=SERVERROOM&idLocation=' + $("#locationSelect")[0].selectize.getValue(),
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    error: callback,
                    success: callback
                    });
                    if (eventReason.indexOf("storage") >= 0) {
                        $('#departmentSelect')[0].selectize.disable();
                    } else {
                        $('#departmentSelect')[0].selectize.enable();
                    }
                    break;
                default:
                    if (null != svtObjId && eventReason.indexOf("storage") < 0) {
                $.ajax({
                    url: '/depbyloc?locationId=' + locationId,
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    error: callback,
                    success: callback
                });
                $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(codeDepartment).items[0].id)
            } else if (eventReason.indexOf("storage") >= 0) {
                $.ajax({
                    url: '/depbyloc?locationId=' + locationId,
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    error: callback,
                    success: callback
                });
                $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(codeDepartment).items[0].id)
                $('#departmentSelect')[0].selectize.disable();
            } else {
                $.ajax({
                    url: '/depbyplaces',
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    error: callback,
                    success: callback
                });
            }
                    break;
            }
            
        },
        onChange: function (value) {
            if (value !== '') {
                if (oldDepartment != value) {
                    let urlReq = "placebydepandloc";
                    if (placeAttrib == "serverroom") {
                        urlReq = "placeserverbydepandloc";
                    }
                    $.ajax({
                        url: '/' + urlReq + '?locationId=' + $("#locationSelect")[0].selectize.getValue() + '&departmentCode=' + value,
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
    if ($('#departmentSelect')[0].selectize.getValue() == "" && $('#departmentSelect')[0].selectize.order > 0) {

        if (eventReason.indexOf("storage") >= 0) {
            $('#departmentSelect')[0].selectize.disable();
        } else {
            $('#departmentSelect')[0].selectize.enable();
        }
    }
    $('#placeSelect').selectize({
        preload: true,
        valueField: 'placeId',
        labelField: 'username',
        searchField: ["placeId", "username"],
        load: function (query, callback) {
            switch (placeAttrib) {
                case "serverroom":
                    $.ajax({
                    url: '/placelocdepplacetype?placeType=SERVERROOM&idLocation=' + $("#locationSelect")[0].selectize.getValue() + '&departmentCode=' + $("#departmentSelect")[0].selectize.getValue(),
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
                if (eventReason.indexOf("backtostor") >= 0) {
                    $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                } else {
                    oldPlaceId = $('#placeSelect')[0].selectize.search(idPlace).items[0].id;
                    $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(idPlace).items[0].id);
                }
                if (eventReason.indexOf("storage") >= 0) {
                    $('#placeSelect')[0].selectize.disable();
                } else {
                    $('#placeSelect')[0].selectize.enable();
                }

            }else {
                oldPlaceId = $('#placeSelect')[0].selectize.search(0).items[0].id; 
                $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
            }
                    break;
                    
                default:
                    if (null != svtObjId && eventReason.indexOf("storage") < 0) {
                let urlReq = "placebydepandloc";
                if (placeAttrib == "serverroom") {
                    urlReq = "placeserverbydepandloc";
                }
                $.ajax({
                    url: '/' + urlReq + '?locationId=' + locationId + '&departmentCode=' + codeDepartment,
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    error: callback,
                    success: callback
                });
                if (null != $('#placeSelect')[0].selectize.search(idPlace).items[0]) {
                    $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(idPlace).items[0].id);
                } else {
                    $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                }
            } else {
                let urlReq = "placesel";
                if (placeAttrib == "serverroom") {
                    urlReq = "placeserver";
                }
                $.ajax({
                    url: '/' + urlReq,
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    error: callback,
                    success: callback
                });
            }
            if (eventReason.indexOf("storage") >= 0) {
                $.ajax({
                    url: '/placebyid?placeId=' + idPlace,
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    error: function (callback) {
                    },
                    success: function (callback) {
                        $('#placeSelect')[0].selectize.addOption(callback);
                    }
                });
            }
            if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                if (eventReason.indexOf("backtostor") >= 0) {
                    $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                } else {
                    oldPlaceId = $('#placeSelect')[0].selectize.search(idPlace).items[0].id;
                    $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(idPlace).items[0].id);
                }
                if (eventReason.indexOf("storage") >= 0) {
                    $('#placeSelect')[0].selectize.disable();
                } else {
                    $('#placeSelect')[0].selectize.enable();
                }

            } else {

                $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                oldPlaceId = $('#placeSelect')[0].selectize.search(0).items[0].id;
            }
                    break;
            }
            
        }
    });

    if ($('#placeSelect')[0].selectize.getValue() == "" && $('#placeSelect')[0].selectize.order > 0) {
        if (eventReason.indexOf("storage") >= 0) {

            $('#placeSelect')[0].selectize.disable();

        } else {
            $('#placeSelect')[0].selectize.enable();
        }
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
                case "scanner":
                    requestLink = "/modscanner";
                    break;
                case "server":
                    requestLink = "/modserver";
                    break;
                case "switch":
                    requestLink = "/modswitch";
                    break;
                case "router":
                    requestLink = "/modrouter";
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


    if ($('#modelSelect')[0].selectize.getValue() == "" && $('#modelSelect')[0].selectize.order > 0) {
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
            option = document.createElement('option');
            option.innerHTML = "Нет";
            option.value = "";
            dateReplaceSelect.appendChild(option);
            for (i = currentYear; i >= 2000; i--) {
                option = document.createElement('option');
                option.value = i;
                option.innerHTML = i;
                dateReplaceSelect.appendChild(option);
            }
            if (dateReplaceBattery > 0) {
                dateReplaceSelect.value = dateReplaceBattery;
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
            $("#ipAdress")[0].value = "";
            if (ipAdress) {
                $("#ipAdress")[0].value = ipAdress;
            }
            $('#ipAdress').mask('0ZZ.0ZZ.0ZZ.0ZZ', {translation: {'Z': {pattern: /[0-9]/, optional: true}}});
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        forOs = new Array();
                        for (let r = 0; r < operationSystemId.length; r++) {
                            forOs.push($('#osSelect')[0].selectize.search(operationSystemId[r]).items[0].id);
                        }

                        $('#osSelect')[0].selectize.setValue(forOs);

                        if (eventReason.indexOf("storage") >= 0) {
                            $('#osSelect')[0].selectize.disable();
                        } else {
                            $('#osSelect')[0].selectize.enable();
                        }
                    }

                }});
            if ($('#osSelect')[0].selectize.getValue() == "" && $('#osSelect')[0].selectize.order > 0) {
                forOs = new Array();
                for (let r = 0; r < operationSystemId.length; r++) {
                    forOs.push($('#osSelect')[0].selectize.search(operationSystemId[r]).items[0].id);
                }
                $('#osSelect')[0].selectize.setValue(forOs);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#osSelect')[0].selectize.disable();
                } else {
                    $('#osSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#motherboardSelect')[0].selectize.setValue($('#motherboardSelect')[0].selectize.search(motherboardId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#motherboardSelect')[0].selectize.disable();
                        } else {
                            $('#motherboardSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#motherboardSelect')[0].selectize.setValue($('#motherboardSelect')[0].selectize.search(0).items[0].id);
                    }

                }});
            if ($('#motherboardSelect')[0].selectize.getValue() == "" && $('#motherboardSelect')[0].selectize.order > 0) {
                $('#motherboardSelect')[0].selectize.setValue($('#motherboardSelect')[0].selectize.search(motherboardId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#motherboardSelect')[0].selectize.disable();
                } else {
                    $('#motherboardSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search(cpuId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#cpuSelect')[0].selectize.disable();
                        } else {
                            $('#cpuSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search(0).items[0].id);
                    }
                }});
            if ($('#cpuSelect')[0].selectize.getValue() == "" && $('#cpuSelect')[0].selectize.order > 0) {
                $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search(cpuId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#cpuSelect')[0].selectize.disable();
                } else {
                    $('#cpuSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search(ramId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#ramSelect')[0].selectize.disable();
                        } else {
                            $('#ramSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search(0).items[0].id);
                    }
                },
                render: {
                    option: function (item, escape) {
                        return '<div>'
                                + '<strong>'
                                + escape(item.model) + '- '
                                + '</strong>'
                                + escape(item.capacity) + ' ГБ'
                                + '</div>';
                    },
                    item: function (item, escape) {
                        return '<div>'
                                + escape(item.model) + ', '
                                + escape(item.capacity) + ' ГБ'
                                + '</div>';
                    }
                }
            });
            if ($('#ramSelect')[0].selectize.getValue() == "" && $('#ramSelect')[0].selectize.order > 0) {
                $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search(ramId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#ramSelect')[0].selectize.disable();
                } else {
                    $('#ramSelect')[0].selectize.enable();
                }
            }
            $("#hddSelect").selectize({
                plugins: ["remove_button"],
                delimiter: ",",
                persist: false,
                maxItems: null,
                placeholder: "Выберите из списка",
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        forHdd = new Array();
                        for (let r = 0; r < hddListId.length; r++) {
                            forHdd.push($('#hddSelect')[0].selectize.search(hddListId[r]).items[0].id);
                        }
                        $('#hddSelect')[0].selectize.setValue(forHdd);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#hddSelect')[0].selectize.disable();
                        } else {
                            $('#hddSelect')[0].selectize.enable();
                        }
                    }
                },
                render: {
                    option: function (item, escape) {
                        return '<div style="margin-left:2px; padding-left:10px; margin-right:2px;padding-right:10px; border-radius:5px;">'
                                + '<strong>'
                                + escape(item.model) + '- '
                                + '</strong>'
                                + escape(item.capacity) + ' '
                                + escape(item.unit)
                                + '</div>';
                    },
                    item: function (item, escape) {
                        return '<div>'
                                + escape(item.model) + ', '
                                + escape(item.capacity) + ' '
                                + escape(item.unit)
                                + '</div>';
                    }
                }
            });
            if ($('#hddSelect')[0].selectize.getValue() == "" && $('#hddSelect')[0].selectize.order > 0) {
                forHdd = new Array();
                for (let r = 0; r < hddListId.length; r++) {
                    forHdd.push($('#hddSelect')[0].selectize.search(hddListId[r]).items[0].id);
                }
                if (eventReason.indexOf("storage") >= 0) {
                    $('#hddSelect')[0].selectize.disable();
                } else {
                    $('#hddSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#videoCardSelect')[0].selectize.setValue($('#videoCardSelect')[0].selectize.search(videoCardId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#videoCardSelect')[0].selectize.disable();
                        } else {
                            $('#videoCardSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#videoCardSelect')[0].selectize.setValue($('#videoCardSelect')[0].selectize.search(0).items[0].id);
                    }
                }});
            if ($('#videoCardSelect')[0].selectize.getValue() == "" && $('#videoCardSelect')[0].selectize.order > 0) {
                $('#videoCardSelect')[0].selectize.setValue($('#videoCardSelect')[0].selectize.search(videoCardId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#videoCardSelect')[0].selectize.disable();
                } else {
                    $('#videoCardSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#soundCardSelect')[0].selectize.setValue($('#soundCardSelect')[0].selectize.search(soundCardId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#soundCardSelect')[0].selectize.disable();
                        } else {
                            $('#soundCardSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#soundCardSelect')[0].selectize.setValue($('#soundCardSelect')[0].selectize.search(0).items[0].id);
                    }
                }});
            if ($('#soundCardSelect')[0].selectize.getValue() == "" && $('#soundCardSelect')[0].selectize.order > 0) {
                $('#soundCardSelect')[0].selectize.setValue($('#soundCardSelect')[0].selectize.search(soundCardId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#soundCardSelect')[0].selectize.disable();
                } else {
                    $('#soundCardSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#lanCardSelect')[0].selectize.setValue($('#lanCardSelect')[0].selectize.search(lanCardId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#lanCardSelect')[0].selectize.disable();
                        } else {
                            $('#lanCardSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#lanCardSelect')[0].selectize.setValue($('#lanCardSelect')[0].selectize.search(0).items[0].id);
                    }

                }});
            if ($('#lanCardSelect')[0].selectize.getValue() == "" && $('#lanCardSelect')[0].selectize.order > 0) {
                $('#lanCardSelect')[0].selectize.setValue($('#lanCardSelect')[0].selectize.search(lanCardId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#lanCardSelect')[0].selectize.disable();
                } else {
                    $('#lanCardSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#cdDriveSelect')[0].selectize.setValue($('#cdDriveSelect')[0].selectize.search(cdDriveId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#cdDriveSelect')[0].selectize.disable();
                        } else {
                            $('#cdDriveSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#cdDriveSelect')[0].selectize.setValue($('#cdDriveSelect')[0].selectize.search(0).items[0].id);
                    }
                }});
            if ($('#cdDriveSelect')[0].selectize.getValue() == "" && $('#cdDriveSelect')[0].selectize.order > 0) {
                $('#cdDriveSelect')[0].selectize.setValue($('#cdDriveSelect')[0].selectize.search(cdDriveId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#cdDriveSelect')[0].selectize.disable();
                } else {
                    $('#cdDriveSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#keyboardSelect')[0].selectize.setValue($('#keyboardSelect')[0].selectize.search(keyboardId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#keyboardSelect')[0].selectize.disable();
                        } else {
                            $('#keyboardSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#keyboardSelect')[0].selectize.setValue($('#keyboardSelect')[0].selectize.search(0).items[0].id);
                    }
                }});
            if ($('#keyboardSelect')[0].selectize.getValue() == "" && $('#keyboardSelect')[0].selectize.order > 0) {
                $('#keyboardSelect')[0].selectize.setValue($('#keyboardSelect')[0].selectize.search(keyboardId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#keyboardSelect')[0].selectize.disable();
                } else {
                    $('#keyboardSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#mouseSelect')[0].selectize.setValue($('#mouseSelect')[0].selectize.search(mouseId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#mouseSelect')[0].selectize.disable();
                        } else {
                            $('#mouseSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#mouseSelect')[0].selectize.setValue($('#mouseSelect')[0].selectize.search(0).items[0].id);
                    }
                }});
            if ($('#mouseSelect')[0].selectize.getValue() == "" && $('#mouseSelect')[0].selectize.order > 0) {
                $('#mouseSelect')[0].selectize.setValue($('#mouseSelect')[0].selectize.search(mouseId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#mouseSelect')[0].selectize.disable();
                } else {
                    $('#mouseSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#speakersSelect')[0].selectize.setValue($('#speakersSelect')[0].selectize.search(speakersId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#speakersSelect')[0].selectize.disable();
                        } else {
                            $('#speakersSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#speakersSelect')[0].selectize.setValue($('#speakersSelect')[0].selectize.search(0).items[0].id);
                    }
                }});
            if ($('#speakersSelect')[0].selectize.getValue() == "" && $('#speakersSelect')[0].selectize.order > 0) {
                $('#speakersSelect')[0].selectize.setValue($('#speakersSelect')[0].selectize.search(speakersId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#speakersSelect')[0].selectize.disable();
                } else {
                    $('#speakersSelect')[0].selectize.enable();
                }
            }
            break;
        case "scanner":
            if (eventReason.indexOf("storage") >= 0) {
                $("#ipAdress")[0].disabled = true;
                $("#numberRoom")[0].disabled = true;
                $("#nameFromOneC")[0].disabled = true;
            } else {
                $("#ipAdress")[0].disabled = false;
                $("#numberRoom")[0].disabled = false;
                $("#nameFromOneC")[0].disabled = false;
            }
            break;
        case "server":
            $("#ipAdress")[0].value = "";
            if (ipAdress) {
                $("#ipAdress")[0].value = ipAdress;
            }
            $('#ipAdress').mask('0ZZ.0ZZ.0ZZ.0ZZ', {translation: {'Z': {pattern: /[0-9]/, optional: true}}});
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        forOs = new Array();
                        for (let r = 0; r < operationSystemId.length; r++) {
                            forOs.push($('#osSelect')[0].selectize.search(operationSystemId[r]).items[0].id);
                        }
                        $('#osSelect')[0].selectize.setValue(forOs);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#osSelect')[0].selectize.disable();
                        } else {
                            $('#osSelect')[0].selectize.enable();
                        }
                    }
                }});
            if ($('#osSelect')[0].selectize.getValue() == "" && $('#osSelect')[0].selectize.order > 0) {
                forOs = new Array();
                for (let r = 0; r < operationSystemId.length; r++) {
                    forOs.push($('#osSelect')[0].selectize.search(operationSystemId[r]).items[0].id);
                }
                $('#osSelect')[0].selectize.setValue(forOs);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#osSelect')[0].selectize.disable();
                } else {
                    $('#osSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search(cpuId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#cpuSelect')[0].selectize.disable();
                        } else {
                            $('#cpuSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search(0).items[0].id);
                    }
                }});
            if ($('#cpuSelect')[0].selectize.getValue() == "" && $('#cpuSelect')[0].selectize.order > 0) {
                $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search(cpuId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#cpuSelect')[0].selectize.disable();
                } else {
                    $('#cpuSelect')[0].selectize.enable();
                }
            }
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search(ramId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#ramSelect')[0].selectize.disable();
                        } else {
                            $('#ramSelect')[0].selectize.enable();
                        }
                    } else {
                        $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search(0).items[0].id);
                    }
                },
                render: {
                    option: function (item, escape) {
                        return '<div>'
                                + '<strong>'
                                + escape(item.model) + '- '
                                + '</strong>'
                                + escape(item.capacity) + ' ГБ'
                                + '</div>';
                    },
                    item: function (item, escape) {
                        return '<div>'
                                + escape(item.model) + ', '
                                + escape(item.capacity) + ' ГБ'
                                + '</div>';
                    }
                }
            });
            if ($('#ramSelect')[0].selectize.getValue() == "" && $('#ramSelect')[0].selectize.order > 0) {
                $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search(ramId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#ramSelect')[0].selectize.disable();
                } else {
                    $('#ramSelect')[0].selectize.enable();
                }
            }
            $("#hddSelect").selectize({
                plugins: ["remove_button"],
                delimiter: ",",
                persist: false,
                maxItems: null,
                placeholder: "Выберите из списка",
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
                    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        forHdd = new Array();
                        for (let r = 0; r < hddListId.length; r++) {
                            forHdd.push($('#hddSelect')[0].selectize.search(hddListId[r]).items[0].id);
                        }
                        $('#hddSelect')[0].selectize.setValue(forHdd);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#hddSelect')[0].selectize.disable();
                        } else {
                            $('#hddSelect')[0].selectize.enable();
                        }
                    }
                },
                render: {
                    option: function (item, escape) {
                        return '<div style="margin-left:2px; padding-left:10px; margin-right:2px;padding-right:10px; border-radius:5px;">'
                                + '<strong>'
                                + escape(item.model) + '- '
                                + '</strong>'
                                + escape(item.capacity) + ' '
                                + escape(item.unit)
                                + '</div>';
                    },
                    item: function (item, escape) {
                        return '<div>'
                                + escape(item.model) + ', '
                                + escape(item.capacity) + ' '
                                + escape(item.unit)
                                + '</div>';
                    }
                }
            });
            if ($('#hddSelect')[0].selectize.getValue() == "" && $('#hddSelect')[0].selectize.order > 0) {
                forHdd = new Array();
                for (let r = 0; r < hddListId.length; r++) {
                    forHdd.push($('#hddSelect')[0].selectize.search(hddListId[r]).items[0].id);
                }
                if (eventReason.indexOf("storage") >= 0) {
                    $('#hddSelect')[0].selectize.disable();
                } else {
                    $('#hddSelect')[0].selectize.enable();
                }
            }
            break;
        case "switch":
            if (eventReason.indexOf("storage") >= 0) {
                $("#numberRoom")[0].disabled = true;
                $("#portAmount")[0].disabled = true;
                $("#nameFromOneC")[0].disabled = true;
                $("#hubRadio")[0].disabled = true;
                $("#switchRadio")[0].disabled = true;
            } else {
                $("#numberRoom")[0].disabled = false;
                $("#portAmount")[0].disabled = false;
                $("#nameFromOneC")[0].disabled = false;
                $("#hubRadio")[0].disabled = false;
                $("#switchRadio")[0].disabled = false;
            }
            break;
          case "router":
            if (eventReason.indexOf("storage") >= 0) {
                $("#numberRoom")[0].disabled = true;
                $("#portAmount")[0].disabled = true;
                $("#nameFromOneC")[0].disabled = true;
            } else {
                $("#numberRoom")[0].disabled = false;
                $("#portAmount")[0].disabled = false;
                $("#nameFromOneC")[0].disabled = false;
            }
            break;
    }
    if(null != $("#dateCreateSelect")[0]) {
        var dateCreate = $("#dateCreateSelect")[0];
    for (i = 2000; i <= currentYear; i++) {
        option = document.createElement('option');
        option.value = i;
        option.innerHTML = i;
        dateCreate.appendChild(option);
    }
    if (yearCreated) {
        dateCreate.value = yearCreated;
    } else {
        dateCreate.value = 2000;
    }
    }
    
    if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
        if (eventReason.indexOf("storage") >= 0) {
            if(null != $("#dateCreateSelect")[0]) {
                dateCreate.disabled = true;
            }
            
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

                // Подтверждение вернуть со склада

                $("#backFromStorageConfirmBtn")[0].addEventListener('click', function (event) {
                    if (modalParent.innerHTML !== '') {
                        modalParent.innerHTML = '';
                    }
                    modalContentLoad("element backtostor", svtObjId);
                    $('#placeSelect')[0].selectize.enable();
                    $('#modelSelect')[0].selectize.enable();
                    $('#locationSelect')[0].selectize.enable();
                    $('#departmentSelect')[0].selectize.enable();
                    $('#btnSave')[0].disabled = false;
                    $('#statusSelect')[0].disabled = false;
                    $("#inventaryNumber")[0].disabled = false;
                    $("#serialNumber")[0].disabled = false;
                    if(null != $("#startExploitation")[0]) {
                        $("#startExploitation")[0].disabled = false;
                    }
                    
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
                        case "systemblock":
                            $("#ipAdress")[0].disabled = false;
                            $("#numberRoom")[0].disabled = false;
                            $("#nameFromOneC")[0].disabled = false;
                            $("#dateUpgrade")[0].disabled = false;
                            $("#osSelect")[0].selectize.enable();
                            $("#motherboardSelect")[0].selectize.enable();
                            $("#cpuSelect")[0].selectize.enable();
                            $("#ramSelect")[0].selectize.enable();
                            $("#hddSelect")[0].selectize.enable();
                            $("#videoCardSelect")[0].selectize.enable();
                            $("#soundCardSelect")[0].selectize.enable();
                            $("#lanCardSelect")[0].selectize.enable();
                            $("#cdDriveSelect")[0].selectize.enable();
                            $("#keyboardSelect")[0].selectize.enable();
                            $("#mouseSelect")[0].selectize.enable();
                            $("#speakersSelect")[0].selectize.enable();
                            break;
                    }
                    if(null != $("#dateCreateSelect")[0]) {
                        dateCreate.disabled = false;
                    }
                    
                    Object.entries($('#placeSelect')[0].selectize.options).forEach(option => {
                        if (option[1].placeType == "Склад") {
                            $('#placeSelect')[0].selectize.removeOption(option[0]);
                        }
                    });
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
        idSvtObj = null;
        innerCallNumber = null;
        idPlace = null;
        codeDepartment = null;
        modelId = null;
        status = null;
        inventary = null;
        serial = null;
        yearCreated = null;
        nameFromOneC = null;
        baseType = null;
        startExploitation = null;
        parseStartDate = null;
        dateReplaceBattery = null;
        batteryAmount = null;
        batteryTypeId = null;
        operationSystemId = null;
        cpuId = null;
        ramId = null;
        hddId = null;
        hddListId = null;
        videoCardId = null;
        soundCardId = null;
        lanCardId = null;
        cdDriveId = null;
        keyboardId = null;
        mouseId = null;
        speakersId = null;
        ipAdress = null;
        numberRoom = null;
        dateUpgrade = null;
        cpuAmount = null;
        portAmount = null;
        switchHubType = null;
        modalParent.innerHTML = "";
    });
    $('#btnSave')[0].addEventListener('click', handleClickSavePhoneBtn);
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
                switch(attrib) {
                    case "switch":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Тип</div>' +
                            '<div class="col">Кол-во портов</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                        case "router":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Расположение</div>' +
                            '<div class="col">Кол-во портов</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                    case "*":
                       headerElement.className = "row fw-bold mt-3 mb-3";
                if (dynamicLabel != null) {
                    headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Дата ввода в экспл</div>' +
                            '<div class="col">Состояние</div>' +
                            '<div class="col">Дата выпуска</div>' +
                            '<div class="col">' + dynamicLabel + '</div>';
                } else {
                    headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Дата ввода в экспл</div>' +
                            '<div class="col">Состояние</div>' +
                            '<div class="col">Дата выпуска</div>';
                }
                        break;
                }
                
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
                                        '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
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
                                        '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
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
                                        '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                        '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                        '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearReplacement + '</div>' +
                                        '</div>';
                                break;
                            case "systemblock":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                        '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                        '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                        '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                        '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                        '<div class="col">' + formatedDate + '</div>' +
                                        '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                        '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                        '</div>';
                                break;
                            case "scanner":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                        '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                        '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                        '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                        '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                        '<div class="col">' + formatedDate + '</div>' +
                                        '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                        '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                        '</div>';
                                break;
                            case "server":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + formatedDate + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '</div>';
                                break;
                                case "switch":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].switchHubType + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].portAmount + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "router":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].numberRoom + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].portAmount + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                
                        }
                        liItem.childNodes[0].append(elDepartment);
                    }
                }
                addDepartmentFlag = true;
            }
        }
        if (!addDepartmentFlag) {
            // Сюда вставляем родительский элемент списка, в котором склад
            if (!parent) {
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
            switch(attrib) {
                    case "switch":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Тип</div>' +
                            '<div class="col">Кол-во портов</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                        case "router":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Расположение</div>' +
                            '<div class="col">Кол-во портов</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                    case "*":
                       headerElement.className = "row fw-bold mt-3 mb-3";
                if (dynamicLabel != null) {
                    headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Дата ввода в экспл</div>' +
                            '<div class="col">Состояние</div>' +
                            '<div class="col">Дата выпуска</div>' +
                            '<div class="col">' + dynamicLabel + '</div>';
                } else {
                    headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Дата ввода в экспл</div>' +
                            '<div class="col">Состояние</div>' +
                            '<div class="col">Дата выпуска</div>';
                }
                        break;
                }
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
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
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
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
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
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearReplacement + '</div>' +
                                    '</div>';
                            break;
                        case "systemblock":
                            elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + formatedDate + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '</div>';
                            break;
                        case "scanner":
                            elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + formatedDate + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '</div>';
                            break;
                            case "server":
                            elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + formatedDate + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '</div>';
                            break;
                            case "switch":
                                
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].switchHubType + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].portAmount + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "router":
                                
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].numberRoom + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].portAmount + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                    }
                    liItem.childNodes[0].append(elDepartment);
                }
            }
        }
    }


});







