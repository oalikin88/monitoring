

const addPlaceBtn = document.querySelector('#addPlaceBtn');
const modalError = document.getElementById('modalError');
const modalErrorParent = document.getElementById('modalErrorContent');
const pathSvg = document.createElementNS('http://www.w3.org/2000/svg',  'path');
let parent = document.querySelector('.tree');
const modalAddPhone = document.getElementById('addPlaceModal');
const modalRepair = document.getElementById('repairModal');
const modalTransfer = document.getElementById('transferModal');
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
let modalRepairParent = document.querySelector("#modalRepairContent");
let modalTransferParent = document.querySelector("#modalTransferContent");
let portAmount;
let switchHubType;
let innerConnectionAnalog;
let innerConnectionIp;
let cityNumberAmount;
let outerConnectionType;
let conditionerTypeSelect;
let splitSystem;
let winterKit;
let havePomp;
let price;
let description;
let switchListId;
let switchList;
let terminalId;
let thermoprinterId;
let subDisplayModelId;
let switchingUnitId;
let subDisplayAmount;
let displayId;
let fff = window.location.href;
var curDep;
var curLoc;






let handleDeleteRepair = function (svtObjId) {
    let deleteRequest = new XMLHttpRequest();
    deleteRequest.open('DELETE', '/repairs?id=' +  $(event.target).closest('.row').attr("repairId"), false);
    deleteRequest.send();
    if (deleteRequest.status != 200) {
        alert(deleteRequest.status + ': ' + deleteRequest.statusText);
    } else {
        console.log(deleteRequest.response);

    }
    reloadModalRepair(svtObjId);
};


let handleDeleteTransfer = function (svtObjId) {
    let deleteRequest = new XMLHttpRequest();
    deleteRequest.open('DELETE', '/transfers?id=' +  $(event.target).closest('.row').attr("transferId"), false);
    deleteRequest.send();
    if (deleteRequest.status != 200) {
        alert(deleteRequest.status + ': ' + deleteRequest.statusText);
    } else {
        console.log(deleteRequest.response);

    }
    reloadModalTransfer(svtObjId);
};


let handleEditRepair = function (svtObjId) {
    console.log(this);



    let datepick = document.createElement("input");
    datepick.className = "form-control form-control-sm";
    datepick.type = "date";
    datepick.id = "dateEdit";
    let getDate = $(event.target).closest('.repairRow').find('.col')[1].innerHTML.split(".");
    datepick.value = getDate[2] + "-" + getDate[1] + "-" + getDate[0];
    $(event.target).closest('.repairRow').find('.col')[1].innerHTML = "";
    $(event.target).closest('.repairRow').find('.col')[1].appendChild(datepick);

    let docNumberEdit = document.createElement("input");
    docNumberEdit.className = "form-control form-control-sm";
    docNumberEdit.type = "text";
    docNumberEdit.id = "docNumberEdit";
    docNumberEdit.value = $(event.target).closest('.repairRow').find('.col')[2].innerHTML;
    $(event.target).closest('.repairRow').find('.col')[2].innerHTML = "";
    $(event.target).closest('.repairRow').find('.col')[2].appendChild(docNumberEdit);

    let definitionEdit = document.createElement("textarea");
    definitionEdit.className = "form-control form-control-sm";
    definitionEdit.id = "definitionEdit";
    definitionEdit.value = $(event.target).closest('.repairRow').find('.col')[3].innerHTML;
    $(event.target).closest('.repairRow').find('.col')[3].innerHTML = "";
    $(event.target).closest('.repairRow').find('.col')[3].appendChild(definitionEdit);

    let saveResultLink = document.createElement("button");
    saveResultLink.style = "border: none; background: transparent;";
    saveResultLink.className = "px-1";
    saveResultLink.id = "saveEdit-repair-btn";
    saveResultLink.setAttribute("data-tooltip", "Сохранить внесённые изменения");


    let saveResultIcon = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    saveResultIcon.setAttribute("class", "bi bi-check-lg saveEdit-icon");
    saveResultIcon.setAttribute("width", "16");
    saveResultIcon.setAttribute("height", "16");
    saveResultIcon.setAttribute("viewbox", "0 0 16 16");

    let iconSaveEditPath1 = document.createElementNS("http://www.w3.org/2000/svg", "path");
    iconSaveEditPath1.setAttribute("d", "M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425z");

    saveResultIcon.appendChild(iconSaveEditPath1);


    saveResultLink.appendChild(saveResultIcon);
    $(event.target).closest('.repairRow').find('.col')[4].appendChild(saveResultLink);


    let addBut = document.getElementById("add-repair-btn");
    addBut.parentNode.removeChild(addBut);
    let delBtns = document.getElementsByClassName('deleteLink');
    let editBtns = document.getElementsByClassName('editLink');
    for (i = delBtns.length - 1; i >= 0; i--) {
        delBtns[i].parentNode.removeChild(delBtns[i]);
    }
    for (i = editBtns.length - 1; i >= 0; i--) {
        editBtns[i].parentNode.removeChild(editBtns[i]);
    }


    $("#saveEdit-repair-btn")[0].addEventListener("click", function () {
        handleSaveEditRepair(svtObjId);
    });

};


let handleEditTransfer = function (svtObjId) {
    console.log(this);

    let datepick = document.createElement("input");
    datepick.className = "form-control form-control-sm";
    datepick.type = "date";
    datepick.id = "dateEdit";
    let getDate = $(event.target).closest('.transferRow').find('.col')[1].innerHTML.split(".");
    datepick.value = getDate[2] + "-" + getDate[1] + "-" + getDate[0];
    $(event.target).closest('.transferRow').find('.col')[1].innerHTML = "";
    $(event.target).closest('.transferRow').find('.col')[1].appendChild(datepick);


    let transferInventaryNumberOldInput = document.createElement("input");
    transferInventaryNumberOldInput.className = "form-control form-control-sm";
    transferInventaryNumberOldInput.type = "text";
    transferInventaryNumberOldInput.id = "transferInventaryNumberOldInput";
    transferInventaryNumberOldInput.value = $(event.target).closest('.transferRow').find('.col')[2].innerHTML;
    $(event.target).closest('.transferRow').find('.col')[2].innerHTML = "";
    $(event.target).closest('.transferRow').find('.col')[2].appendChild(transferInventaryNumberOldInput);
    
    let transferInventaryNumberNewInput = document.createElement("input");
    transferInventaryNumberNewInput.className = "form-control form-control-sm";
    transferInventaryNumberNewInput.type = "text";
    transferInventaryNumberNewInput.id = "transferInventaryNumberNewInput";
    transferInventaryNumberNewInput.value = $(event.target).closest('.transferRow').find('.col')[3].innerHTML;
    $(event.target).closest('.transferRow').find('.col')[3].innerHTML = "";
    $(event.target).closest('.transferRow').find('.col')[3].appendChild(transferInventaryNumberNewInput);
    
    let transferFromInput = document.createElement("input");
    transferFromInput.className = "form-control form-control-sm";
    transferFromInput.type = "text";
    transferFromInput.id = "transferFromInput";
    transferFromInput.value = $(event.target).closest('.transferRow').find('.col')[4].innerHTML;
    $(event.target).closest('.transferRow').find('.col')[4].innerHTML = "";
    $(event.target).closest('.transferRow').find('.col')[4].appendChild(transferFromInput);
    
    let transferToInput = document.createElement("input");
    transferToInput.className = "form-control form-control-sm";
    transferToInput.type = "text";
    transferToInput.id = "transferToInput";
    transferToInput.value = $(event.target).closest('.transferRow').find('.col')[5].innerHTML;
    $(event.target).closest('.transferRow').find('.col')[5].innerHTML = "";
    $(event.target).closest('.transferRow').find('.col')[5].appendChild(transferToInput);

    let docNumberEdit = document.createElement("input");
    docNumberEdit.className = "form-control form-control-sm";
    docNumberEdit.type = "text";
    docNumberEdit.id = "docNumberEdit";
    docNumberEdit.value = $(event.target).closest('.transferRow').find('.col')[6].innerHTML;
    $(event.target).closest('.transferRow').find('.col')[6].innerHTML = "";
    $(event.target).closest('.transferRow').find('.col')[6].appendChild(docNumberEdit);

 

    let saveResultLink = document.createElement("button");
    saveResultLink.style = "border: none; background: transparent;";
    saveResultLink.className = "px-1";
    saveResultLink.id = "saveEdit-transfer-btn";
    saveResultLink.setAttribute("data-tooltip", "Сохранить внесённые изменения");


    let saveResultIcon = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    saveResultIcon.setAttribute("class", "bi bi-check-lg saveEdit-icon");
    saveResultIcon.setAttribute("width", "16");
    saveResultIcon.setAttribute("height", "16");
    saveResultIcon.setAttribute("viewbox", "0 0 16 16");

    let iconSaveEditPath1 = document.createElementNS("http://www.w3.org/2000/svg", "path");
    iconSaveEditPath1.setAttribute("d", "M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425z");

    saveResultIcon.appendChild(iconSaveEditPath1);
    saveResultLink.appendChild(saveResultIcon);
    $(event.target).closest('.transferRow').find('.col')[7].appendChild(saveResultLink);


    let addBut = document.getElementById("add-transfer-btn");
    addBut.parentNode.removeChild(addBut);
    let delBtns = document.getElementsByClassName('deleteLink');
    let editBtns = document.getElementsByClassName('editLink');
    for (i = delBtns.length - 1; i >= 0; i--) {
        delBtns[i].parentNode.removeChild(delBtns[i]);
    }
    for (i = editBtns.length - 1; i >= 0; i--) {
        editBtns[i].parentNode.removeChild(editBtns[i]);
    }


    $("#saveEdit-transfer-btn")[0].addEventListener("click", function () {
        handleSaveEditTransfer(svtObjId);
    });

};

let handleSaveEditTransfer = function (svtObjId) {
    console.log("click");
    var xhr = new XMLHttpRequest();

    var json = JSON.stringify({
        id: $(event.target).closest('.row').attr("transferid"),
        dateTransfer: $("#dateEdit")[0].value,
        inventaryNumberOld: $("#transferInventaryNumberOldInput")[0].value,
        inventaryNumberNew: $("#transferInventaryNumberNewInput")[0].value,
        transferFrom: $("#transferFromInput")[0].value,
        transferTo: $("#transferToInput")[0].value,
        documentNumber: $("#docNumberEdit")[0].value,
        idObjectBuing: svtObjId
    });

    xhr.open("PUT", '/transfers', false);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {                  // если запрос завершен
            if (xhr.status == 200) {                // если код ответа 200
                console.log(xhr.statusText);
               
            }
            
        }
    };
     xhr.send(json);
    reloadModalTransfer(svtObjId);
};

let handleSaveEditRepair = function (svtObjId) {
    console.log("click");
    var xhr = new XMLHttpRequest();

    var json = JSON.stringify({
        id: $(event.target).closest('.row').attr("repairId"),
        dateRepair: $("#dateEdit")[0].value,
        documentNumber: $("#docNumberEdit")[0].value,
        definition: $("#definitionEdit")[0].value,
        idObjectBuing: svtObjId
    });

    xhr.open("PUT", '/repairs', false);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {                  // если запрос завершен
            if (xhr.status == 200) {                // если код ответа 200
                console.log(xhr.statusText);
               
            }
            
        }
    };
     xhr.send(json);
    reloadModalRepair(svtObjId);
};


let reloadModalTransfer = function(svtObjId) {
    
     $("#containerTransferContent")[0].innerHTML = "";
            
            //перерисовка модального окна 
            
            let getTransfers = new XMLHttpRequest();
            getTransfers.open('GET', '/transfers?id=' + svtObjId, false);
            getTransfers.send();
            let transfersResult;
            if(getTransfers.status != 200) {
                alert(getTransfers.status + ': ' + getTransfers.statusText);
            } else {
                console.log(getTransfers.response);
                transfersResult = JSON.parse(getTransfers.response);
            }
            
             if(transfersResult.length > 0) {
            let transferLabelRow = document.createElement("div");
            transferLabelRow.className = "row fw-bold mb-3";
           
            let transferLabelNum = document.createElement("div");
            transferLabelNum.className = "col col-1";
            transferLabelNum.innerText = "№";
           
            let transferLabelDate = document.createElement("div");
            transferLabelDate.className = "col col-2";
            transferLabelDate.innerText = "Дата";
            
            
            let transferLabelInventaryOld = document.createElement("div");
            transferLabelInventaryOld.className = "col col-2";
            transferLabelInventaryOld.innerText = "Инвентарный №, старый";
            
            let transferLabelInventaryNew = document.createElement("div");
            transferLabelInventaryNew.className = "col col-2";
            transferLabelInventaryNew.innerText = "Инвентарный №, новый";
            
            let transferLabelFrom = document.createElement("div");
            transferLabelFrom.className = "col col-1";
            transferLabelFrom.innerText = "Откуда";
            
            let transferLabelTo = document.createElement("div");
            transferLabelTo.className = "col col-1";
            transferLabelTo.innerText = "Куда";
            
            let transferLabelDocumentNumber = document.createElement("div");
            transferLabelDocumentNumber.className = "col col-2";
            transferLabelDocumentNumber.innerText = "№ док-та";
            
            let transferLabelBtn = document.createElement("div");
            transferLabelBtn.className = "col col-1";
            
            transferLabelRow.appendChild(transferLabelNum);
            transferLabelRow.appendChild(transferLabelDate);
            transferLabelRow.appendChild(transferLabelInventaryOld);
            transferLabelRow.appendChild(transferLabelInventaryNew);
            transferLabelRow.appendChild(transferLabelFrom);
            transferLabelRow.appendChild(transferLabelTo);
            transferLabelRow.appendChild(transferLabelDocumentNumber);
            transferLabelRow.appendChild(transferLabelBtn);
            

            $("#containerTransferContent")[0].appendChild(transferLabelRow);
        
        for(i = 0; i < transfersResult.length; i++) {
//            let getDate = this.parentNode.parentNode.childNodes[1].innerHTML.split(".");
//                    datepick.value = getDate[2] + "-" + getDate[1] + "-" + getDate[0];
            let parseDate = Date.parse(transfersResult[i].dateTransfer);
            let dateTransferParsed = new Date(parseDate);
            let dateTransferFormat = dateTransferParsed.toLocaleDateString('ru');
            
            
             let transferRow = document.createElement("div");
            transferRow.className = "row transferRow mt-3";
            transferRow.setAttribute("transferId", transfersResult[i].id);
            let transferNum = document.createElement("div");
            transferNum.className = "col col-1";
            transferNum.innerText = i + 1;
            
            let transferDate = document.createElement("div");
            transferDate.className = "col col-2";
            transferDate.innerText = dateTransferFormat;
            
            let transferInventaryNumberOld = document.createElement("div");
            transferInventaryNumberOld.className = "col col-2";
            transferInventaryNumberOld.innerText = transfersResult[i].inventaryNumberOld;
            
            let transferInventaryNumberNew = document.createElement("div");
            transferInventaryNumberNew.className = "col col-2";
            transferInventaryNumberNew.innerText = transfersResult[i].inventaryNumberNew;
            
            let transferFrom = document.createElement("div");
            transferFrom.className = "col col-1";
            transferFrom.innerText = transfersResult[i].transferFrom;
            
            let transferTo = document.createElement("div");
            transferTo.className = "col col-1";
            transferTo.innerText = transfersResult[i].transferTo;
            
            let transferDocumentNumber = document.createElement("div");
            transferDocumentNumber.className = "col col-2";
            transferDocumentNumber.innerText = transfersResult[i].documentNumber;
            
            transferRow.appendChild(transferNum);
            transferRow.appendChild(transferDate);
            transferRow.appendChild(transferInventaryNumberOld);
            transferRow.appendChild(transferInventaryNumberNew);
            transferRow.appendChild(transferFrom);
            transferRow.appendChild(transferTo);
            transferRow.appendChild(transferDocumentNumber);

            
            let deleteBtnLink = document.createElement("button");
            deleteBtnLink.className = "deleteLink px-1";
            deleteBtnLink.style = "border: none; background: transparent;";
            deleteBtnLink.setAttribute("data-tooltip", "Удалить сведения о перемещении");
            
            let deleteTransferIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
            deleteTransferIcon.setAttribute("class","bi bi-trash delete-icon");
            deleteTransferIcon.setAttribute("width", "16");
            deleteTransferIcon.setAttribute("height", "16");
            deleteTransferIcon.setAttribute("viewbox", "0 0 16 16");

            let iconDeletePath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconDeletePath1.setAttribute("d", "M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z");
            let iconDeletePath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconDeletePath2.setAttribute("fill-rule","evenodd");
            iconDeletePath2.setAttribute("d", "M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z");
            deleteTransferIcon.appendChild(iconDeletePath1);
            deleteTransferIcon.appendChild(iconDeletePath2);
            deleteBtnLink.appendChild(deleteTransferIcon);
            
            
            
            let editBtnLink = document.createElement("button");
            editBtnLink.className = "editLink px-1";
            editBtnLink.style = "border: none; background: transparent;";
            editBtnLink.setAttribute("data-tooltip", "Редактировать запись о перемещении");
            
            let editBtnIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
            editBtnIcon.setAttribute("class", "bi bi-pencil-square edit-icon");
            editBtnIcon.setAttribute("width", "16");
            editBtnIcon.setAttribute("height", "16");
            editBtnIcon.setAttribute("viewbox", "0 0 16 16");
            
            let iconEditPath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconEditPath1.setAttribute("d", "M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z");
            let iconEditPath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconEditPath2.setAttribute("fill-rule","evenodd");
            iconEditPath2.setAttribute("d", "M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z");
            
            let transferBtnsCol = document.createElement("div");
            transferBtnsCol.className = "col col-1";
            
            editBtnIcon.appendChild(iconEditPath1);
            editBtnIcon.appendChild(iconEditPath2);
            editBtnLink.appendChild(editBtnIcon);
           transferBtnsCol.appendChild(deleteBtnLink);
            transferBtnsCol.appendChild(editBtnLink);
            
            
     
            transferRow.appendChild(transferBtnsCol);
            editBtnLink.addEventListener("click", function(){
             handleEditTransfer(svtObjId);
         });
         deleteBtnLink.addEventListener("click", function() {
                handleDeleteTransfer(svtObjId);
         });
            if(i == transfersResult.length - 1) {
               
                let addNewTransferLink = document.createElement("button");
                addNewTransferLink.style = "border: none; background: transparent;";
                addNewTransferLink.setAttribute("data-tooltip", "Внести сведения о перемещении");
                addNewTransferLink.id = "add-transfer-btn";
                addNewTransferLink.className = " px-1";
                let addNewTransferIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
                addNewTransferIcon.id="add-icon";
                addNewTransferIcon.className = "bi bi-plus-circle";
                addNewTransferIcon.setAttribute("width", "16");
                addNewTransferIcon.setAttribute("height", "16");
                addNewTransferIcon.setAttribute("viewbox", "0 0 16 16");
                let iconPath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
                iconPath1.setAttribute("d", "M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z");
                let iconPath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
                iconPath2.setAttribute("d", "M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z");
                addNewTransferIcon.appendChild(iconPath1);
                addNewTransferIcon.appendChild(iconPath2);
                addNewTransferLink.appendChild(addNewTransferIcon);
                transferBtnsCol.appendChild(addNewTransferLink);
            }
            $("#containerTransferContent")[0].appendChild(transferRow);
        }
        
    } else {
      
            $("#containerTransferContent")[0].innerHTML = ' <div class="row mt-2"><div class="col"><div>Перемещений не было.</div></div><div class="row mt-3 text-center"><div class="col"><button data-tooltip="Внести сведения о ремонте" id="add-transfer-btn" style = "border: none; background: transparent;">' +
       ' <svg id="add-icon" class="bi bi-plus-circle" width="42" height="42" viewBox="0 0 16 16">' +
          '  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>' +
           ' <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>' +
       ' </svg>' +
    ' </button></div></div></div>';

        }
        
        
         $("#add-transfer-btn")[0].addEventListener("click",  function(){
             handleAddTransferBtn(svtObjId);
         });
 
};

let reloadModalRepair = function(svtObjId) {
    
     $("#containerRepairContent")[0].innerHTML = "";
            
            //перерисовка модального окна 
            
            let getRepairs = new XMLHttpRequest();
            getRepairs.open('GET', '/repairs?id=' + svtObjId, false);
            getRepairs.send();
            let repairsResult;
            if(getRepairs.status != 200) {
                alert(getRepairs.status + ': ' + getRepairs.statusText);
            } else {
                console.log(getRepairs.response);
                repairsResult = JSON.parse(getRepairs.response);
            }
            
             if(repairsResult.length > 0) {
            let repairLabelRow = document.createElement("div");
            repairLabelRow.className = "row fw-bold mb-3";
           
            let repairLabelNum = document.createElement("div");
            repairLabelNum.className = "col col-1";
            repairLabelNum.innerText = "№";
           
            let repairLabelDate = document.createElement("div");
            repairLabelDate.className = "col col-3";
            repairLabelDate.innerText = "Дата";
            
            let repairLabelDocumentNumber = document.createElement("div");
            repairLabelDocumentNumber.className = "col col-3";
            repairLabelDocumentNumber.innerText = "№ док-та";
            
            
            let repairLabelDefinition = document.createElement("div");
            repairLabelDefinition.className = "col col-4";
            repairLabelDefinition.innerText = "Перечень работ";
            repairLabelRow.appendChild(repairLabelNum);
            repairLabelRow.appendChild(repairLabelDate);
            repairLabelRow.appendChild(repairLabelDocumentNumber);
            repairLabelRow.appendChild(repairLabelDefinition);
            $("#containerRepairContent")[0].appendChild(repairLabelRow);
        
        for(i = 0; i < repairsResult.length; i++) {
//            let getDate = this.parentNode.parentNode.childNodes[1].innerHTML.split(".");
//                    datepick.value = getDate[2] + "-" + getDate[1] + "-" + getDate[0];
            let parseDate = Date.parse(repairsResult[i].dateRepair);
            let dateRepairParsed = new Date(parseDate);
            let dateRepairFormat = dateRepairParsed.toLocaleDateString('ru');
            
            let repairRow = document.createElement("div");
            repairRow.className = "row repairRow mt-3";
            repairRow.setAttribute("repairId", repairsResult[i].id);
            let repairNum = document.createElement("div");
            repairNum.className = "col col-1";
            repairNum.innerText = i + 1;
            
            let repairDate = document.createElement("div");
            repairDate.className = "col col-3";
            repairDate.innerText = dateRepairFormat;
            let repairDocumentNumber = document.createElement("div");
            repairDocumentNumber.className = "col col-3";
            repairDocumentNumber.innerText = repairsResult[i].documentNumber;
            let repairDefinition = document.createElement("div");
            repairDefinition.className = "col col-4";
            repairDefinition.innerText = repairsResult[i].definition;
            
            
            let deleteBtnLink = document.createElement("button");
            deleteBtnLink.className = "deleteLink px-1";
            deleteBtnLink.style = "border: none; background: transparent;";
            deleteBtnLink.setAttribute("data-tooltip", "Удалить сведения о ремонте");
            
            let deleteRepairIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
            deleteRepairIcon.setAttribute("class","bi bi-trash delete-icon");
            deleteRepairIcon.setAttribute("width", "16");
            deleteRepairIcon.setAttribute("height", "16");
            deleteRepairIcon.setAttribute("viewbox", "0 0 16 16");

            let iconDeletePath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconDeletePath1.setAttribute("d", "M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z");
            let iconDeletePath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconDeletePath2.setAttribute("fill-rule","evenodd");
            iconDeletePath2.setAttribute("d", "M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z");
            deleteRepairIcon.appendChild(iconDeletePath1);
            deleteRepairIcon.appendChild(iconDeletePath2);
            deleteBtnLink.appendChild(deleteRepairIcon);
            
            
            
            let editBtnLink = document.createElement("button");
            editBtnLink.className = "editLink px-1";
            editBtnLink.style = "border: none; background: transparent;";
            editBtnLink.setAttribute("data-tooltip", "Редактировать запись о ремонте");
            
            let editBtnIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
            editBtnIcon.setAttribute("class", "bi bi-pencil-square edit-icon");
            editBtnIcon.setAttribute("width", "16");
            editBtnIcon.setAttribute("height", "16");
            editBtnIcon.setAttribute("viewbox", "0 0 16 16");
            
            let iconEditPath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconEditPath1.setAttribute("d", "M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z");
            let iconEditPath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconEditPath2.setAttribute("fill-rule","evenodd");
            iconEditPath2.setAttribute("d", "M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z");
            
            let repairBtnsCol = document.createElement("div");
            repairBtnsCol.className = "col col-1";
            
            editBtnIcon.appendChild(iconEditPath1);
            editBtnIcon.appendChild(iconEditPath2);
            editBtnLink.appendChild(editBtnIcon);
           repairBtnsCol.appendChild(deleteBtnLink);
            repairBtnsCol.appendChild(editBtnLink);
            
            
            repairRow.appendChild(repairNum);
            repairRow.appendChild(repairDate);
            repairRow.appendChild(repairDocumentNumber);
            repairRow.appendChild(repairDefinition);
            repairRow.appendChild(repairBtnsCol);
            editBtnLink.addEventListener("click", function(){
             handleEditRepair(svtObjId);
         });
         deleteBtnLink.addEventListener("click", function() {
                handleDeleteRepair(svtObjId);
         });
            if(i == repairsResult.length - 1) {
               
                let addNewRepairLink = document.createElement("button");
                addNewRepairLink.style = "border: none; background: transparent;";
                addNewRepairLink.setAttribute("data-tooltip", "Внести сведения о ремонте");
                addNewRepairLink.id = "add-repair-btn";
                addNewRepairLink.className = " px-1";
                let addNewRepairIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
                addNewRepairIcon.id="add-icon";
                addNewRepairIcon.className = "bi bi-plus-circle";
                addNewRepairIcon.setAttribute("width", "16");
                addNewRepairIcon.setAttribute("height", "16");
                addNewRepairIcon.setAttribute("viewbox", "0 0 16 16");
                let iconPath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
                iconPath1.setAttribute("d", "M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z");
                let iconPath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
                iconPath2.setAttribute("d", "M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z");
                addNewRepairIcon.appendChild(iconPath1);
                addNewRepairIcon.appendChild(iconPath2);
                addNewRepairLink.appendChild(addNewRepairIcon);
                repairBtnsCol.appendChild(addNewRepairLink);
            }
            $("#containerRepairContent")[0].appendChild(repairRow);
        }
        
    } else {
      
            $("#containerRepairContent")[0].innerHTML = ' <div class="row mt-2"><div class="col"><div>В ремонте не был.</div></div><div class="row mt-3 text-center"><div class="col"><button  data-tooltip="Внести сведения о ремонте" id="add-repair-btn" style = "border: none; background: transparent;">' +
       ' <svg id="add-icon" class="bi bi-plus-circle" width="42" height="42" viewBox="0 0 16 16">' +
          '  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>' +
           ' <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>' +
       ' </svg>' +
    ' </button></div></div></div>';

        }
        
        
         $("#add-repair-btn")[0].addEventListener("click",  function(){
             handleAddBtn(svtObjId);
         });
         
      
         
};


let handleAddTransferBtn = function (svtObjId) {

    console.log("click");

    //$('[data-bs-toggle="tooltip"]').tooltip('hide');

    if ($(".transferRow").length == 0) {
        $("#containerTransferContent")[0].innerHTML = "";
        $("#containerTransferContent").append('<div class="row fw-bold mb-3">' +
                '<div class="col col-1">№</div>' +
                '<div class="col col-2">Дата</div>' +
                '<div class="col col-2">Инвентарный номер, старый</div>' +
                '<div class="col col-2">Инвентарный номер, новый</div>' +
                '<div class="col col-1">Откуда</div>' +
                '<div class="col col-1">Куда</div>' +
                '<div class="col col-2">№ док-та</div>');
    }
    
    $("#containerTransferContent").append('</div><div class="row mt-3 inputRow">' +
            '<div class="col col-1">' + ($('.transferRow').length + 1) + '</div>' +
            '<div class="col col-2"><input type="date" class="form-control form-control-sm" name="transfer-date" id="transfer-date"></div>' +
            '<div class="col col-2"><input type="text" class="form-control form-control-sm" name = "transfer-inventaryNumberOld" id="transfer-inventaryNumberOld"></div>' +
            '<div class="col col-2"><input type="text" class="form-control form-control-sm" name="transfer-inventaryNumberNew" id="transfer-inventaryNumberNew"></div>' +
            '<div class="col col-1"><input type="text" class="form-control form-control-sm" name="transfer-from" id="transfer-from"></div>' +
            '<div class="col col-1"><input type="text" class="form-control form-control-sm" name ="transfer-to" id="transfer-to"></div>' +
            '<div class="col col-2"><input type="text" class="form-control form-control-sm" name="transfer-document" id="transfer-document"></div>' +
            '</div>');

    if ($(".inputRow").length > 1) {
        let addBut = document.getElementById("add-transfer-btn");
        addBut.parentNode.removeChild(addBut);
        let delBtns = document.getElementsByClassName('deleteLink');
        let editBtns = document.getElementsByClassName('editLink');
        for (i = delBtns.length - 1; i >= 0; i--) {
            delBtns[i].parentNode.removeChild(delBtns[i]);
        }
        for (i = editBtns.length - 1; i >= 0; i--) {
            editBtns[i].parentNode.removeChild(editBtns[i]);
        }
       
    }
     $("#transferFooter").append('<button class="btn btn-primary btn-sm" id="btnTransferSave">Сохранить</button>');
    $("#btnTransferSave")[0].addEventListener("click", function (event) {
        console.log("send");
        var xhr = new XMLHttpRequest();

        var json = JSON.stringify({
            id: null,
            dateTransfer: $("#transfer-date")[0].value,
            inventaryNumberOld: $("#transfer-inventaryNumberOld")[0].value,
            inventaryNumberNew: $("#transfer-inventaryNumberNew")[0].value,
            transferFrom: $("#transfer-from")[0].value,
            transferTo: $("#transfer-to")[0].value,
            documentNumber: $("#transfer-document")[0].value,
            idObjectBuing: svtObjId
        });

        xhr.open("POST", '/transfers', false);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4) {                  // если запрос завершен
                if (xhr.status == 200) {                // если код ответа 200
                    console.log(xhr.responseText);      // выводим полученный ответ на консоль браузера
                    $("#containerTransferContent")[0].innerHTML = "";

                    //перерисовка модального окна 

                    reloadModalTransfer(svtObjId);


                } else {                                // иначе выводим текст статуса
                    console.log("Server response: ", xhr.statusText);
                    alert("Упс, что-то пошло не так..");
                }

            }
        };

        xhr.send(json);
        let btnSave = $("#btnTransferSave")[0];
        btnSave.parentNode.removeChild(btnSave);
    });


};


let handleAddBtn = function(svtObjId) {
             
        console.log("click");
        
          //$('[data-bs-toggle="tooltip"]').tooltip('hide');
          
        if($(".repairRow").length == 0) {
        $("#containerRepairContent")[0].innerHTML = "";
        $("#containerRepairContent").append('<div class="row fw-bold mb-3">' +
                '<div class="col col-1">№</div>' + 
                '<div class="col col-3">Дата</div>' +
                '<div class="col col-3">№ док-та</div>' +
                '<div class="col col-4">Перечень работ</div>');
    }
        $("#containerRepairContent").append('</div><div class="row mt-3 inputRow" id="inputRow">' +
                '<div class="col col-1">'+ ($('.repairRow').length + 1) +'</div>' +
                '<div class="col col-3"><input type="date" class="form-control form-control-sm" id="repair-date"></div>' +
                '<div class="col col-3"><input type="text" class="form-control form-control-sm" id="repair-document"></div>' +
                '<div class="col col-4"><textarea class="form-control form-control-sm" rows="3" id="repair-definition"></textarea></div>' +
                '</div>');
        
        if($(".inputRow").length > 1) {
          let addBut = document.getElementById("add-repair-btn");
          addBut.parentNode.removeChild(addBut);
          let delBtns = document.getElementsByClassName('deleteLink');
          let editBtns = document.getElementsByClassName('editLink');
          for(i = delBtns.length - 1; i >= 0; i--) {
              delBtns[i].parentNode.removeChild(delBtns[i]);
          }
          for(i = editBtns.length - 1; i >= 0; i--) {
              editBtns[i].parentNode.removeChild(editBtns[i]);
          }
          
        }
        $("#repairFooter").append('<button class="btn btn-primary btn-sm" id="btnRepairSave">Сохранить</button>');
        $("#btnRepairSave")[0].addEventListener("click", function(event) {
            console.log("send");
            var xhr = new XMLHttpRequest();

            var json = JSON.stringify({
              id: null,
              dateRepair: $("#repair-date")[0].value,
              documentNumber: $("#repair-document")[0].value,
              definition: $("#repair-definition")[0].value,
              idObjectBuing: svtObjId
            });

            xhr.open("POST", '/repairs', false);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

            xhr.onreadystatechange = () => {
    if (xhr.readyState == 4) {                  // если запрос завершен
        if (xhr.status == 200) {                // если код ответа 200
            console.log(xhr.responseText);      // выводим полученный ответ на консоль браузера
            $("#containerRepairContent")[0].innerHTML = "";
            
            //перерисовка модального окна 
            
        reloadModalRepair(svtObjId);

            
        } else {                                // иначе выводим текст статуса
            console.log("Server response: ", xhr.statusText);
            alert("Упс, что-то пошло не так..");
        }
        
                }
            };

            xhr.send(json);
            let btnSave = $("#btnRepairSave")[0];
            btnSave.parentNode.removeChild(btnSave);
        });
        
        
    };

function beep() {
   
    var context = new AudioContext();
    var oscillator = context.createOscillator();
    oscillator.type = "sine";
    oscillator.frequency.value = 600;
    oscillator.connect(context.destination);
    oscillator.start();
    // Beep for 500 milliseconds
    setTimeout(function () {
        oscillator.stop();
    }, 500); 
};

let linkCreate = function(attribute, toLink) {
    var result = '/';
    result = result + attribute + toLink;
    return result;
    
}

let getModalError = function(textError) {
      
    //if you have another AudioContext class use that one, as some browsers have a limit
    modalHeader = document.createElement('div');
    modalHeader.className = 'modal-header modalHeaderError';
    titleModal = document.createElement('h1');
    titleModal.className = 'modal-title fs-5';
    titleModal.id = 'titleModal';
    titleModal.innerText = 'Ошибка!';
    closeHeaderButton = document.createElement('button');
    closeHeaderButton.className = 'btn-close btn-close-white';
    closeHeaderButton.setAttribute("data-bs-dismiss", "modal");
    closeHeaderButton.setAttribute('aria-label', 'Закрыть');

    modalErrorParent.appendChild(modalHeader);
    modalHeader.appendChild(titleModal);
    modalHeader.appendChild(closeHeaderButton);

    modalBody = document.createElement('div');
    modalBody.className = 'modal-body';
    modalBody.id = 'modalBody';

    modalErrorParent.append(modalBody);
    modalFooter = document.createElement('div');
    modalFooter.className = 'modal-footer';
    footerBtnClose = document.createElement('button');
    footerBtnClose.className = 'btn btn-secondary btn-sm';
    footerBtnClose.setAttribute('data-bs-dismiss', 'modal');
    footerBtnClose.innerText = 'Закрыть';

    modalErrorParent.appendChild(modalFooter);
    modalFooter.appendChild(footerBtnClose);
    $("#modalBody").append(textError);
    return new bootstrap.Modal(modalError).show();
};

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

let getConditionerType = function(input) {
    var result;
    switch (input) {
        case "WINDOW":
            result = "Оконный";
            break;
        case "WALL":
            result = "Настенный";
            break;
        case "CEILING":
            result = "Потолочный";
            break;
        case "FLOOR":
            result = "Напольный";
            break;
    }
    return result;
};


let handleClickArchivedBtn = function () {
    let requestLink = linkCreate(attrib, "archived");
    let dto = {
        archived: true,
        id: idSvtObj,
    };
    
    $.ajax({
        type: "DELETE",
        url: requestLink,
        data: JSON.stringify(dto),
        async: false,
        success: function () {
            window.location.reload();
        },
        error: function (callback) {
            beep();
            getModalError(callback.responseText);
        },
        processData: false,
        contentType: 'application/json'
    });
};

let handleClickSendToStorageBtn = function () {
    let requestLink;
    let dto = {
        placeId: document.querySelector('#placeSelect').value,
        id: idSvtObj,
        locationId: locationId,
    };
        if(attrib != "asuo") {
        dto.model = document.querySelector('#modelSelect').innerText;
        dto.modelId = document.querySelector('#modelSelect').value;
        dto.status = document.querySelector('#statusSelect').value;
        dto.inventaryNumber = document.querySelector('#inventaryNumber').value;
        dto.serialNumber = document.querySelector('#serialNumber').value;
        
    }
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
            dto.numberRoom = $("#numberRoom")[0].value;
            requestLink = "/monitortostor";
            break;
        case "ups":
            requestLink = "/upstostor";
            dto.batteryTypeId = $("#batteryTypeSelect")[0].selectize.getValue();
            dto.batteryAmount = document.querySelector("#batteryAmount").value;
            dto.yearReplacement = document.querySelector("#dateReplaceSelect").value;
            break;
        case "upsforserver":
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
        case "ats":
            requestLink = "/atstostor";
            dto.cityNumberAmount = $("#cityNumberAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.innerConnectionIp = $("#innerConnectionIp")[0].value;
            dto.innerConnectionAnalog = $("#innerConnectionAnalog")[0].value;
            dto.outerConnectionType = $("#outerConnectionType")[0].value;
            break;
        case "conditioner":
            requestLink = "/conditionertostor";
            dto.conditionerType = $("#conditionerTypeSelect")[0].value;
            dto.splitSystem = $("#splitSystemTrue")[0].checked;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.description = $("#descriptionInput")[0].value;
            dto.winterKit = $("#winterKitTrue")[0].checked;
            dto.havePomp = $("#pompTrue")[0].checked;
            dto.price = $("#price")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            break;
        case "infomat":
            requestLink = "/infomattostor";
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            break;
        case "terminal":
            requestLink = "/terminaltostor";
            break;
        case "thermoprinter":
            requestLink = "/thermoprintertostor";
            break;
        case "display":
            requestLink = "/displaytostor";
            break;
        case "swunit":
            requestLink = "/swunittostor";
            break;
        case "asuo":
            requestLink = "/asuotostor";
            dto.displayId = $("#displaySelect")[0].selectize.getValue();
            dto.terminalId = $("#terminalSelect")[0].selectize.getValue();
            dto.thermoprinterId = $("#thermoprinterSelect")[0].selectize.getValue();
            dto.subDisplayModelId = $("#subDisplaySelect")[0].selectize.getValue();
            dto.switchingUnitId = $("#switchingUnitSelect")[0].selectize.getValue();
            dto.switchId = $("#switchSelect")[0].selectize.getValue();
            dto.subDisplayAmount = $("#subDisplayAmount")[0].value;
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
            beep();
            getModalError(callback.responseText);
        },
        processData: false,
        contentType: 'application/json'
    });
};

let handleClickUpdateBtn = function () {
    let requestLink;
    let dto = {
        placeId: document.querySelector('#placeSelect').value,
        id: idSvtObj,
    };
        if(attrib != "asuo") {
        dto.model = document.querySelector('#modelSelect').innerText;
        dto.modelId = document.querySelector('#modelSelect').value;
        dto.status = document.querySelector('#statusSelect').value;
        dto.inventaryNumber = document.querySelector('#inventaryNumber').value;
        dto.serialNumber = document.querySelector('#serialNumber').value;
    }
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
        case "fax":
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            requestLink = "/updfax";
            break;
        case "monitors":
            dto.nameFromOneC = document.querySelector('#nameFromOneC').value;
            dto.baseType = document.querySelector('#baseTypeSelect').value;
            dto.numberRoom = $("#numberRoom")[0].value;
            requestLink = "/updmonitor";
            break;
        case "ups":
            requestLink = "/updups";

            dto.yearReplacement = document.querySelector("#dateReplaceSelect").value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            break;
        case "upsforserver":
            requestLink = "/updupsforserver";

            dto.yearReplacement = document.querySelector("#dateReplaceSelect").value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
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
        case "ats":
            requestLink = "/updats";
            dto.cityNumberAmount = $("#cityNumberAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.innerConnectionIp = $("#innerConnectionIp")[0].value;
            dto.innerConnectionAnalog = $("#innerConnectionAnalog")[0].value;
            dto.outerConnectionType = $("#outerConnectionType")[0].value;
            break;
        case "conditioner":
            requestLink = "/updconditioner";
            dto.conditionerType = $("#conditionerTypeSelect")[0].value;
            dto.splitSystem = $("#splitSystemTrue")[0].checked;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.description = $("#descriptionInput")[0].value;
            dto.winterKit = $("#winterKitTrue")[0].checked;
            dto.havePomp = $("#pompTrue")[0].checked;
            dto.price = $("#price")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            break;
        case "infomat":
            requestLink = "/updinfomat";
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            break;
        case "terminal":
            requestLink = "/updterminal";
            break;
        case "thermoprinter":
            requestLink = "/updthermoprinter";
            break;
        case "display":
            requestLink = "/upddisplay";
            break;
        case "swunit":
            requestLink = "/updswunit";
            break;
        case "asuo":
            requestLink = "/updasuo";
            dto.displayId = $("#displaySelect")[0].selectize.getValue();
            dto.terminalId = $("#terminalSelect")[0].selectize.getValue();
            dto.thermoprinterId = $("#thermoprinterSelect")[0].selectize.getValue();
            dto.subDisplayModelId = $("#subDisplaySelect")[0].selectize.getValue();
            dto.switchingUnitId = $("#switchingUnitSelect")[0].selectize.getValue();
            dto.switchId = $("#switchSelect")[0].selectize.getValue();
            dto.subDisplayAmount = $("#subDisplayAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            break;
    }
    $.ajax({
        type: "PUT",
        url: requestLink,
        data: JSON.stringify(dto),
        async: false,
        success: function () {
           window.location.reload();
        },
        error: function (callback) {
            beep();
            getModalError(callback.responseText);
        },
        processData: false,
        contentType: 'application/json'
    });
};
let handleClickSearchSvtObject = function (field, input) {
    let request;
    switch (attrib) {
        case "phones":
            request = "/phones?" + field + "=";
            break;
        case "fax":
            requestLink = "/fax?" + field + "=";
            break;
        case "systemblock":
            request = "/sysblocks?" + field + "=";
            break;
        case "monitors":
            request = "/monitors?" + field + "=";
            break;
        case "ups":
            request = "/ups?" + field + "=";
            break;
        case "upsforserver":
            request = "/upsforserver?" + field + "=";
            break;
        case "scanner":
            request = "/scanner?" + field + "=";
            break;
        case "server":
            request = "/server?" + field + "=";
            break;
        case "switch":
            request = "/switch?" + field + "=";
            break;
        case "router":
            request = "/router?" + field + "=";
            break;
        case "ats":
            request = "/ats?" + field + "=";
            break;
        case "conditioner":
            request = "/conditioner?" + field + "=";
            break;
        case "infomat":
            request = "/infomat?" + field + "=";
            break;
        case "terminal":
            request = "/terminal?" + field + "=";
            break;
        case "thermoprinter":
            request = "/thermoprinter?" + field + "=";
            break;
        case "display":
            request = "/display?" + field + "=";
            break;
        case "swunit":
            request = "/swunit?" + field + "=";
            break;
        case "asuo":
            request = "/asuo?" + field + "=";
            break;
    }
    window.location.href = request + input;
};

let handleClickSavePhoneBtn = function () {
    let requestLink;
    let dto = {
        placeId: document.querySelector('#placeSelect').value,
        id: idSvtObj,
    };
    if(attrib != "asuo") {
        dto.model = document.querySelector('#modelSelect').innerText;
        dto.modelId = document.querySelector('#modelSelect').value;
        dto.status = document.querySelector('#statusSelect').value;
        dto.inventaryNumber = document.querySelector('#inventaryNumber').value;
        dto.serialNumber = document.querySelector('#serialNumber').value;
    }
    
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
        case "fax":
            dto.ipAdress = $("#ipAdress")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            requestLink = "/fax";
            break;
        case "asuo":
            requestLink = "/asuo";
            dto.displayId = $("#displaySelect")[0].selectize.getValue();
            dto.terminalId = $("#terminalSelect")[0].selectize.getValue();
            dto.thermoprinterId = $("#thermoprinterSelect")[0].selectize.getValue();
            dto.subDisplayModelId = $("#subDisplaySelect")[0].selectize.getValue();
            dto.switchingUnitId = $("#switchingUnitSelect")[0].selectize.getValue();
            dto.switchId = $("#switchSelect")[0].selectize.getValue();
            dto.subDisplayAmount = $("#subDisplayAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            break;
        case "monitors":
            dto.nameFromOneC = document.querySelector('#nameFromOneC').value;
            dto.baseType = document.querySelector('#baseTypeSelect').value;
            dto.numberRoom = $("#numberRoom")[0].value;
            requestLink = "/monitors";
            break;
        case "ups":
            requestLink = "/ups";
            dto.yearReplacement = document.querySelector("#dateReplaceSelect").value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            break;
        case "upsforserver":
            requestLink = "/upsforserver";
            dto.yearReplacement = document.querySelector("#dateReplaceSelect").value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            break;
        case "upsforserver":
            requestLink = "/upsforserver";
            dto.batteryTypeId = $("#batteryTypeSelect")[0].selectize.getValue();
            dto.batteryAmount = document.querySelector("#batteryAmount").value;
            dto.yearReplacement = document.querySelector("#dateReplaceSelect").value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
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
        case "ats":
            requestLink = "/ats";
            dto.cityNumberAmount = $("#cityNumberAmount")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.innerConnectionIp = $("#innerConnectionIp")[0].value;
            dto.innerConnectionAnalog = $("#innerConnectionAnalog")[0].value;
            dto.outerConnectionType = $("#outerConnectionType")[0].value;
            break;
        case "conditioner":
            requestLink = "/conditioner";
            dto.conditionerType = $("#conditionerTypeSelect")[0].value;
            dto.splitSystem = $("#splitSystemTrue")[0].checked;
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.description = $("#descriptionInput")[0].value;
            dto.winterKit = $("#winterKitTrue")[0].checked;
            dto.havePomp = $("#pompTrue")[0].checked;
            dto.price = $("#price")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            break;
        case "infomat":
            requestLink = "/infomat";
            dto.numberRoom = $("#numberRoom")[0].value;
            dto.nameFromOneC = $("#nameFromOneC")[0].value;
            break;
        case "terminal":
            requestLink = "/terminal";
            break;
        case "thermoprinter":
            requestLink = "/thermoprinter";
            break;
        case "display":
            requestLink = "/display";
            break;
        case "swunit":
            requestLink = "/swunit";
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
            beep();
            getModalError(callback.responseText);
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
                    '<span  data-tooltip="У вас нет склада в этом районе"><button type="button" class="btn btn-warning btn-sm" id="sendToStorageBtn">Отправить на склад</button></span>' +
                    '<button type="button" class="btn btn-secondary btn-sm"  data-bs-dismiss="modal">Отменить</button>' +
                    '<button type="button" class="btn btn-primary btn-sm" id="btnSave" >Применить</button>');
            $('#sendToStorageBtn').addClass("disabled");
//            tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
//            tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
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
    

    if($("#getReport")[0] != null) {
    if(dtoes.length == 0) {
        $("#getReport")[0].disabled = true;
    } else {
    $("#getReport")[0].addEventListener("click", function() {
        let fileName;
        let lin;
        switch(attrib) {
            case "ups":
                lin = fff.replace("/ups", "/get-doc/get-ups");
                fileName = "docReportUps.xlsx";    
                break;
            case "upsforserver":
                lin = fff.replace("/upsforserver", "/get-doc/upsforserver");
                fileName = "docReportUpsForServer.xlsx";    
                break;
            case "systemblock": 
                lin = fff.replace("/sysblocks", "/get-doc/sysblocks");
                fileName = "docReportSysBlocks.xlsx";    
                break;
            case "monitors": 
                lin = fff.replace("/monitors", "/get-doc/monitors");
                fileName = "docReportMonitors.xlsx";    
                break;
            case "scanner": 
                lin = fff.replace("/scanner", "/get-doc/scanner");
                fileName = "docReportScanners.xlsx";    
                break;
            case "phones": 
                lin = fff.replace("/phones", "/get-doc/phones");
                fileName = "docReportPhones.xlsx";    
                break;
            case "server": 
                lin = fff.replace("/server", "/get-doc/server");
                fileName = "docReportServers.xlsx";    
                break;
            case "switch": 
                lin = fff.replace("/switch", "/get-doc/switch");
                fileName = "docReportSwitches.xlsx";    
                break;
            case "router": 
                lin = fff.replace("/router", "/get-doc/router");
                fileName = "docReportSwitches.xlsx";    
                break;
            case "upsforserver": 
                lin = fff.replace("/upsforserver", "/get-doc/upsforserver");
                fileName = "docReportUpsForServer.xlsx";    
                break;
            case "ats": 
                lin = fff.replace("/ats", "/get-doc/ats");
                fileName = "docReportAts.xlsx";    
                break;
            case "conditioner": 
                lin = fff.replace("/conditioner", "/get-doc/conditioner");
                fileName = "docReportConditioners.xlsx";    
                break;
            case "fax": 
                lin = fff.replace("/fax", "/get-doc/fax");
                fileName = "docReportFaxes.xlsx";    
                break;
            case "infomat": 
                lin = fff.replace("/infomat", "/get-doc/infomat");
                fileName = "docReportInfomat.xlsx";    
                break;
        }
    

        
         $.ajax({
        url: lin,
        cache: false,
        xhr: function () {
            var xhr = new XMLHttpRequest();
            
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 2) {
                    if (xhr.status == 200) {
                        xhr.responseType = "blob";
                    } else {
                        xhr.responseType = "text";
                    }
                }
            };
            return xhr;
        },
        success: function (data) {
            var blob = new Blob([data], { type: "application/octetstream" });

            var isIE = false || !!document.documentMode;
            if (isIE) {
                window.navigator.msSaveBlob(blob, fileName);
            } else {
                var url = window.URL || window.webkitURL;
                link = url.createObjectURL(blob);
                var a = $("<a />");
                a.attr("download", fileName);
                a.attr("href", link);
                $("body").append(a);
                a[0].click();
                $("body").remove(a);
            }
        }
    });
    
    });
}
}
     
     $("#searchChoise")[0].addEventListener("change", function() {
         switch($("#searchChoise")[0].value) {
             
             case "fio":
                $("#searchSvtObjInput")[0].placeholder = "поиск по ФИО";
                break;
            case "inventaryNumber":
                $("#searchSvtObjInput")[0].placeholder = "поиск по инвентарному номеру";
                break;
             
         }
     });
     
      $('#searchSvtObjInput').keyup(function(event) {
    if (event.keyCode === 13) {
       $("#searchSvtObjBtn")[0].click();
    }
});

   if($('#filter-btn').length > 0) {
       

    
    
       
        $('#filter-btn')[0].addEventListener('click', function() {
            

            
    window.location.href = window.location.pathname + "?model=" +  document.querySelector('#filter-model').value + 
            "&status=" + document.querySelector('#filter-status').value + "&yearCreatedOne=" + document.querySelector('#dateBegin').value + 
            "&yearCreatedTwo=" + document.querySelector('#dateEnd').value + "&location=" + document.querySelector('#filter-location').value;

       
   });
   }
  
   
   $('#filter-location').selectize({
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
                    break;
                    case "officeequipment":
                    $.ajax({
                        url: '/locplacetype?placeType=OFFICEEQUIPMENT',
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: callback,
                        success: callback
                    });
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
                    break;
            }
             

        }
        });
   
    $('#filter-model').selectize({
        preload: true,
        valueField: 'id',
        labelField: 'model',
        searchField: ["model"],
        load: function (query, callback) {
            let requestLink;
            switch (attrib) {
                case "phones":
                    requestLink = "/modphones";
                    break;
                case "fax":
                    requestLink = "/modfax";
                    break;
                case "monitors":
                    requestLink = "/modmonitors";
                    break;
                case "ups":
                    requestLink = "/modups";
                    break;
                case "upsforserver":
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
                case "ats":
                    requestLink = "/modats";
                    break;
                case "conditioner":
                    requestLink = "/modconditioner";
                    break;
                case "infomat":
                    requestLink = "/modinfomat";
                    break;
                case "terminal":
                    requestLink = "/modterminal";
                    break;
                case "thermoprinter":
                    requestLink = "/modthermoprinter";
                    break;
                case "display":
                    requestLink = "/moddisplay";
                    break;
                case "swunit":
                    requestLink = "/modswunit";
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
          
        }
    });
   
    $("#searchSvtObjBtn")[0].addEventListener("click", function () {
        handleClickSearchSvtObject($("#searchChoise")[0].value, $("#searchSvtObjInput")[0].value);
    });
    let elem = document.querySelectorAll('.element');
    addPlaceBtn.addEventListener('click', function () {
       
        modalContentLoad($(this)[0].className);
        if(attrib == "asuo") {
            if($("#modelRow")[0] != null) {
                $("#modelRow")[0].remove();
                $("#inventaryNumberRow")[0].remove();
                $("#serialNumberRow")[0].remove();
                $("#statusRow")[0].remove();
            }
        }
    });
    for (let i = 0; i < elem.length; i++) {
        elem[i].addEventListener("click", function (event) {
            modalContentLoad($(this)[0].className, $(this)[0].id);
            if(attrib == "asuo") {
            if($("#modelRow")[0] != null) {
                $("#modelRow")[0].remove();
                $("#inventaryNumberRow")[0].remove();
                $("#serialNumberRow")[0].remove();
                $("#statusRow")[0].remove();
            }
        }
      });
    }
    
     modalError.addEventListener('hidden.bs.modal', function (event) {
         modalErrorParent.innerHTML = "";
     });      
    
};
let modalTransferContentLoad = function(svtObjId, title) {
    if(modalTransferParent.childNodes.length > 1) {
        modalTransferParent.innerHTML = "";
    }
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/transfers?id=' + svtObjId, false);
    xhr.send();
    let requestResult;
    if(xhr.status != 200) {
        alert(xhr.status + ': ' + xhr.statusText);
    } else {
        console.log(xhr.response);
        requestResult = JSON.parse(xhr.response);
    }
    let divModalHeader = document.createElement("div");
    divModalHeader.className = "modal-header modalHeaderContent";
    let titleModal = document.createElement("h5");
    titleModal.className = "modal-title";
    titleModal.id = "modalTransferTitle";
    titleModal.innerText = "Перемещения " + title;
    divModalHeader.appendChild(titleModal);
    let headerCloseBtn = document.createElement("button");
    headerCloseBtn.className = "btn-close btn-close-white";
    headerCloseBtn.setAttribute("data-bs-dismiss", "modal");
    headerCloseBtn.setAttribute("aria-label", "Close");
    headerCloseBtn.id = "closeTransferBtn";
    divModalHeader.appendChild(headerCloseBtn);
    modalTransferParent.appendChild(divModalHeader);
    //сборка боди
    let divModalBody = document.createElement("div");
    divModalBody.className = "modal-body";
    let divContainerBody = document.createElement("div");
    divContainerBody.className = "container-fluid";
    divContainerBody.id = "containerTransferContent";
    
    if(requestResult.length > 0) {
            let transferLabelRow = document.createElement("div");
            transferLabelRow.className = "row fw-bold mb-3";
            transferLabelRow.id = "rowTransferLabels";
           
            let transferLabelNum = document.createElement("div");
            transferLabelNum.className = "col col-1";
            transferLabelNum.innerText = "№";
           
            let transferLabelDate = document.createElement("div");
            transferLabelDate.className = "col col-2";
            transferLabelDate.innerText = "Дата";
            
            let transferLabelInventaryOld = document.createElement("div");
            transferLabelInventaryOld.className = "col col-2";
            transferLabelInventaryOld.innerText = "Инвентарный №, старый";
            
            let transferLabelInventaryNew = document.createElement("div");
            transferLabelInventaryNew.className = "col col-2";
            transferLabelInventaryNew.innerText = "Инвентарный №, новый";
            
            let transferLabelFrom = document.createElement("div");
            transferLabelFrom.className = "col col-1";
            transferLabelFrom.innerText = "Откуда";
            
            let transferLabelTo = document.createElement("div");
            transferLabelTo.className = "col col-1";
            transferLabelTo.innerText = "Куда";
            
            let transferLabelDocumentNumber = document.createElement("div");
            transferLabelDocumentNumber.className = "col col-2";
            transferLabelDocumentNumber.innerText = "№ док-та";
            
            let transferLabelBtn = document.createElement("div");
            transferLabelBtn.className = "col col-1";
            
            transferLabelRow.appendChild(transferLabelNum);
            transferLabelRow.appendChild(transferLabelDate);
            transferLabelRow.appendChild(transferLabelInventaryOld);
            transferLabelRow.appendChild(transferLabelInventaryNew);
            transferLabelRow.appendChild(transferLabelFrom);
            transferLabelRow.appendChild(transferLabelTo);
            transferLabelRow.appendChild(transferLabelDocumentNumber);
            transferLabelRow.appendChild(transferLabelBtn);
           
            divContainerBody.appendChild(transferLabelRow);
        
        for(i = 0; i < requestResult.length; i++) {
            let parseDate = Date.parse(requestResult[i].dateTransfer);
            let dateTransferParsed = new Date(parseDate);
            let dateTransferFormat = dateTransferParsed.toLocaleDateString('ru');
            
            let transferRow = document.createElement("div");
            transferRow.className = "row transferRow mt-3";
            transferRow.setAttribute("transferId", requestResult[i].id);
            let transferNum = document.createElement("div");
            transferNum.className = "col col-1";
            transferNum.innerText = i + 1;
            
            let transferDate = document.createElement("div");
            transferDate.className = "col col-2";
            transferDate.innerText = dateTransferFormat;
            
            let transferInventaryNumberOld = document.createElement("div");
            transferInventaryNumberOld.className = "col col-2";
            transferInventaryNumberOld.innerText = requestResult[i].inventaryNumberOld;
            
            let transferInventaryNumberNew = document.createElement("div");
            transferInventaryNumberNew.className = "col col-2";
            transferInventaryNumberNew.innerText = requestResult[i].inventaryNumberNew;
            
            let transferFrom = document.createElement("div");
            transferFrom.className = "col col-1";
            transferFrom.innerText = requestResult[i].transferFrom;
            
            let transferTo = document.createElement("div");
            transferTo.className = "col col-1";
            transferTo.innerText = requestResult[i].transferTo;
            
            let transferDocumentNumber = document.createElement("div");
            transferDocumentNumber.className = "col col-2";
            transferDocumentNumber.innerText = requestResult[i].documentNumber;
            
            transferRow.appendChild(transferNum);
            transferRow.appendChild(transferDate);
            transferRow.appendChild(transferInventaryNumberOld);
            transferRow.appendChild(transferInventaryNumberNew);
            transferRow.appendChild(transferFrom);
            transferRow.appendChild(transferTo);
            transferRow.appendChild(transferDocumentNumber);
            let addNewTransferCol = document.createElement("div");
            addNewTransferCol.className = "col col-1";
            
            let deleteBtnLink = document.createElement("button");
            deleteBtnLink.className = "deleteLink px-1";
            deleteBtnLink.style = "border: none; background: transparent;";
            deleteBtnLink.setAttribute("data-tooltip", "Удалить сведения о перемещении");
            
            let deleteTransferIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
            deleteTransferIcon.setAttribute("class","bi bi-trash delete-icon");
            deleteTransferIcon.setAttribute("width", "16");
            deleteTransferIcon.setAttribute("height", "16");
            deleteTransferIcon.setAttribute("viewbox", "0 0 16 16");

            let iconDeletePath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconDeletePath1.setAttribute("d", "M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z");
            let iconDeletePath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconDeletePath2.setAttribute("fill-rule","evenodd");
            iconDeletePath2.setAttribute("d", "M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z");
            deleteTransferIcon.appendChild(iconDeletePath1);
            deleteTransferIcon.appendChild(iconDeletePath2);
            deleteBtnLink.appendChild(deleteTransferIcon);
            addNewTransferCol.appendChild(deleteBtnLink);
            
            
            let editBtnLink = document.createElement("button");
            editBtnLink.className = "editLink px-1";
            editBtnLink.style = "border: none; background: transparent;";
            editBtnLink.setAttribute("data-tooltip", "Редактировать запись о перемещении");
            
            let editBtnIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
            editBtnIcon.setAttribute("class", "bi bi-pencil-square edit-icon");
            editBtnIcon.setAttribute("width", "16");
            editBtnIcon.setAttribute("height", "16");
            editBtnIcon.setAttribute("viewbox", "0 0 16 16");
            
            let iconEditPath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconEditPath1.setAttribute("d", "M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z");
            let iconEditPath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconEditPath2.setAttribute("fill-rule","evenodd");
            iconEditPath2.setAttribute("d", "M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z");
            
            editBtnIcon.appendChild(iconEditPath1);
            editBtnIcon.appendChild(iconEditPath2);
            editBtnLink.appendChild(editBtnIcon);
            addNewTransferCol.appendChild(editBtnLink);
            transferRow.appendChild(addNewTransferCol);
                deleteBtnLink.addEventListener("click", function() {
                    handleDeleteTransfer(svtObjId);
                });
                
                editBtnLink.addEventListener("click", function () {
                    handleEditTransfer(svtObjId);
                });
                if(i == requestResult.length - 1) {
              
                let addNewTransferLink = document.createElement("button");
                addNewTransferLink.style = "border: none;  background: transparent;";
                addNewTransferLink.className = "px-1";
                addNewTransferLink.id = "add-transfer-btn";
                addNewTransferLink.setAttribute("data-tooltip", "Внести сведения о перемещении");
                
              
                let addNewTransferIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
                addNewTransferIcon.setAttribute("class","bi bi-plus-circle add-icon");
                addNewTransferIcon.setAttribute("width", "16");
                addNewTransferIcon.setAttribute("height", "16");
                addNewTransferIcon.setAttribute("viewbox", "0 0 16 16");
                
                let iconPath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
                iconPath1.setAttribute("d", "M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z");
                let iconPath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
                iconPath2.setAttribute("d", "M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z");
                addNewTransferIcon.appendChild(iconPath1);
                addNewTransferIcon.appendChild(iconPath2);
               
                addNewTransferLink.appendChild(addNewTransferIcon);
                
                addNewTransferCol.appendChild(addNewTransferLink);
                transferRow.appendChild(addNewTransferCol);
                
            }
            divContainerBody.appendChild(transferRow);
        }
    } else {
      
        divContainerBody.innerHTML = ' <div class="row mt-2"><div class="col"><div>Перемещений не было.</div></div><div class="row mt-3 text-center"><div class="col"><button  data-tooltip="Внести сведения о перемещении" id="add-transfer-btn" style="border: none; background: transparent;">' +
   ' <svg id="add-icon" class="bi bi-plus-circle" width="42" height="42" viewBox="0 0 16 16">' +
      '  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>' +
       ' <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>' +
   ' </svg>' +
' </button></div></div></div>';
    

    
    }
    divModalBody.appendChild(divContainerBody);
    modalTransferParent.appendChild(divModalBody);
    

         $("#add-transfer-btn")[0].addEventListener("click",  function(){
             handleAddTransferBtn(svtObjId);
         });

    //сборка футера
    let divModalFooter = document.createElement("div");
    divModalFooter.className = "modal-footer svtObjModalFooter";
    divModalFooter.id = "transferFooter";
    let footerBtnCancel = document.createElement("button");
    footerBtnCancel.className = "btn btn-secondary btn-sm";
    footerBtnCancel.setAttribute("data-bs-target", "#addPlaceModal");
    footerBtnCancel.setAttribute("data-bs-toggle", "modal");
    footerBtnCancel.innerText = "Назад";
    footerBtnCancel.id = "backToModal";
//    let footerBtnSave = document.createElement("button");
//    footerBtnSave.className = "btn btn-primary btn-sm";
//    footerBtnSave.id = "btnTransferSave";
//    footerBtnSave.innerText = "Сохранить";
    
    divModalFooter.appendChild(footerBtnCancel);
  //  divModalFooter.appendChild(footerBtnSave);
    modalTransferParent.appendChild(divModalFooter);
};

let modalRepairContentLoad = function (svtObjId, title) {
    if(modalRepairParent.childNodes.length > 1) {
        modalRepairParent.innerHTML = "";
    }
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/repairs?id=' + svtObjId, false);
    xhr.send();
    let requestResult;
    if(xhr.status != 200) {
        alert(xhr.status + ': ' + xhr.statusText);
    } else {
        console.log(xhr.response);
        requestResult = JSON.parse(xhr.response);
    }
    let divModalHeader = document.createElement("div");
    divModalHeader.className = "modal-header modalHeaderContent";
    let titleModal = document.createElement("h5");
    titleModal.className = "modal-title";
    titleModal.id = "modalRepairTitle";
    titleModal.innerText = "Ремонт " + title;
    divModalHeader.appendChild(titleModal);
    let headerCloseBtn = document.createElement("button");
    headerCloseBtn.className = "btn-close btn-close-white";
    headerCloseBtn.setAttribute("data-bs-dismiss", "modal");
    headerCloseBtn.setAttribute("aria-label", "Close");
    headerCloseBtn.id = "closeRepairBtn";
    divModalHeader.appendChild(headerCloseBtn);
    modalRepairParent.appendChild(divModalHeader);
    //сборка боди
    let divModalBody = document.createElement("div");
    divModalBody.className = "modal-body";
    let divContainerBody = document.createElement("div");
    divContainerBody.className = "container";
    divContainerBody.id = "containerRepairContent";
    
    if(requestResult.length > 0) {
            let repairLabelRow = document.createElement("div");
            repairLabelRow.className = "row fw-bold mb-3";
            repairLabelRow.id = "rowRepairLabels";
           
            let repairLabelNum = document.createElement("div");
            repairLabelNum.className = "col col-1";
            repairLabelNum.innerText = "№";
           
            let repairLabelDate = document.createElement("div");
            repairLabelDate.className = "col col-3";
            repairLabelDate.innerText = "Дата";
            
            let repairLabelDocumentNumber = document.createElement("div");
            repairLabelDocumentNumber.className = "col col-3";
            repairLabelDocumentNumber.innerText = "№ док-та";
            
            
            let repairLabelDefinition = document.createElement("div");
            repairLabelDefinition.className = "col col-4";
            repairLabelDefinition.innerText = "Перечень работ";
            repairLabelRow.appendChild(repairLabelNum);
            repairLabelRow.appendChild(repairLabelDate);
            repairLabelRow.appendChild(repairLabelDocumentNumber);
            repairLabelRow.appendChild(repairLabelDefinition);
            divContainerBody.appendChild(repairLabelRow);
        
        for(i = 0; i < requestResult.length; i++) {
            let parseDate = Date.parse(requestResult[i].dateRepair);
            let dateRepairParsed = new Date(parseDate);
            let dateRepairFormat = dateRepairParsed.toLocaleDateString('ru');
            
            let repairRow = document.createElement("div");
            repairRow.className = "row repairRow mt-3";
            repairRow.setAttribute("repairId", requestResult[i].id);
            let repairNum = document.createElement("div");
            repairNum.className = "col col-1";
            repairNum.innerText = i + 1;
            
            let repairDate = document.createElement("div");
            repairDate.className = "col col-3";
            repairDate.innerText = dateRepairFormat;
            let repairDocumentNumber = document.createElement("div");
            repairDocumentNumber.className = "col col-3";
            repairDocumentNumber.innerText = requestResult[i].documentNumber;
            let repairDefinition = document.createElement("div");
            repairDefinition.className = "col col-4";
            repairDefinition.innerText = requestResult[i].definition;
            repairRow.appendChild(repairNum);
            repairRow.appendChild(repairDate);
            repairRow.appendChild(repairDocumentNumber);
            repairRow.appendChild(repairDefinition);
            let addNewRepairCol = document.createElement("div");
            addNewRepairCol.className = "col col-1";
            
            let deleteBtnLink = document.createElement("button");
            deleteBtnLink.className = "deleteLink px-1";
            deleteBtnLink.style = "border: none; background: transparent;";
            deleteBtnLink.setAttribute("data-tooltip", "Удалить сведения о ремонте");
            
            let deleteRepairIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
            deleteRepairIcon.setAttribute("class","bi bi-trash delete-icon");
            deleteRepairIcon.setAttribute("width", "16");
            deleteRepairIcon.setAttribute("height", "16");
            deleteRepairIcon.setAttribute("viewbox", "0 0 16 16");

            let iconDeletePath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconDeletePath1.setAttribute("d", "M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z");
            let iconDeletePath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconDeletePath2.setAttribute("fill-rule","evenodd");
            iconDeletePath2.setAttribute("d", "M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z");
            deleteRepairIcon.appendChild(iconDeletePath1);
            deleteRepairIcon.appendChild(iconDeletePath2);
            deleteBtnLink.appendChild(deleteRepairIcon);
            addNewRepairCol.appendChild(deleteBtnLink);
            
            
            let editBtnLink = document.createElement("button");
            editBtnLink.style = "border: none; background: transparent;";
            editBtnLink.className = "editLink px-1";
            editBtnLink.setAttribute("data-tooltip", "Редактировать запись о ремонте");
            
            let editBtnIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
            editBtnIcon.setAttribute("class", "bi bi-pencil-square edit-icon");
            editBtnIcon.setAttribute("width", "16");
            editBtnIcon.setAttribute("height", "16");
            editBtnIcon.setAttribute("viewbox", "0 0 16 16");
            
            let iconEditPath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconEditPath1.setAttribute("d", "M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z");
            let iconEditPath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
            iconEditPath2.setAttribute("fill-rule","evenodd");
            iconEditPath2.setAttribute("d", "M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z");
            
            editBtnIcon.appendChild(iconEditPath1);
            editBtnIcon.appendChild(iconEditPath2);
            editBtnLink.appendChild(editBtnIcon);
            addNewRepairCol.appendChild(editBtnLink);
            repairRow.appendChild(addNewRepairCol);
                deleteBtnLink.addEventListener("click", function() {
                    handleDeleteRepair(svtObjId);
                });
                
                editBtnLink.addEventListener("click", function () {
                    handleEditRepair(svtObjId);
                });
                if(i == requestResult.length - 1) {
              
                let addNewRepairLink = document.createElement("button");
                addNewRepairLink.className = "px-1";
                addNewRepairLink.style = "border: none; background: transparent;";
                addNewRepairLink.id = "add-repair-btn";
                addNewRepairLink.setAttribute("data-tooltip", "Внести сведения о ремонте");
                
              
                let addNewRepairIcon = document.createElementNS("http://www.w3.org/2000/svg","svg");
                addNewRepairIcon.setAttribute("class","bi bi-plus-circle add-icon");
                addNewRepairIcon.setAttribute("width", "16");
                addNewRepairIcon.setAttribute("height", "16");
                addNewRepairIcon.setAttribute("viewbox", "0 0 16 16");
                
                let iconPath1 = document.createElementNS("http://www.w3.org/2000/svg","path");
                iconPath1.setAttribute("d", "M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z");
                let iconPath2 = document.createElementNS("http://www.w3.org/2000/svg","path");
                iconPath2.setAttribute("d", "M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z");
                addNewRepairIcon.appendChild(iconPath1);
                addNewRepairIcon.appendChild(iconPath2);
               
                addNewRepairLink.appendChild(addNewRepairIcon);
                
                addNewRepairCol.appendChild(addNewRepairLink);
                repairRow.appendChild(addNewRepairCol);
                
            }
            divContainerBody.appendChild(repairRow);
        }
    } else {
      
        divContainerBody.innerHTML = ' <div class="row mt-2"><div class="col"><div>В ремонте не был.</div></div><div class="row mt-3 text-center"><div class="col"><button  data-tooltip="Внести сведения о ремонте" id="add-repair-btn" style = "border: none; background: transparent;">' +
   ' <svg id="add-icon" class="bi bi-plus-circle" width="42" height="42" viewBox="0 0 16 16">' +
      '  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>' +
       ' <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>' +
   ' </svg>' +
' </button></div></div></div>';
    

    
    }
    divModalBody.appendChild(divContainerBody);
    modalRepairParent.appendChild(divModalBody);
    

         $("#add-repair-btn")[0].addEventListener("click",  function(){
             handleAddBtn(svtObjId);
         });

    //сборка футера
    let divModalFooter = document.createElement("div");
    divModalFooter.className = "modal-footer svtObjModalFooter";
    divModalFooter.id = "repairFooter";
    let footerBtnCancel = document.createElement("button");
    footerBtnCancel.className = "btn btn-secondary btn-sm";
    footerBtnCancel.setAttribute("data-bs-target", "#addPlaceModal");
    footerBtnCancel.setAttribute("data-bs-toggle", "modal");
    footerBtnCancel.innerText = "Назад";
    footerBtnCancel.id = "backToModal";
//    let footerBtnSave = document.createElement("button");
//    footerBtnSave.className = "btn btn-primary btn-sm";
//    footerBtnSave.id = "btnRepairSave";
//    footerBtnSave.innerText = "Сохранить";
    
    divModalFooter.appendChild(footerBtnCancel);
  //  divModalFooter.appendChild(footerBtnSave);
    modalRepairParent.appendChild(divModalFooter);

};

// Модальное окно добавления/редактирования телефона
let modalContentLoad = function (eventReason, svtObjId) {
    
    if(modalParent.childNodes.length > 1) {
        modalParent.innerHTML = "";
    }
    let requestLink;
    let titleAction;
    // Сборка header
    let divModalHeader = document.createElement("div");
    divModalHeader.className = "modal-header modalHeaderContent";
    let titleModal = document.createElement("h5");
    titleModal.className = "modal-title";
    titleModal.id = "modalTitle";
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
        case "upsforserver":
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
        case "ats":
            titleModal.innerText = titleAction + " АТС";
            break;
        case "conditioner":
            titleModal.innerText = titleAction + " кондиционер";
            break;
        case "infomat":
            titleModal.innerText = titleAction + " инфомат";
            break;
        case "terminal":
            titleModal.innerText = titleAction + " терминал";
            break;
         case "thermoprinter":
            titleModal.innerText = titleAction + " термопринтер";
            break;
        case "display":
            titleModal.innerText = titleAction + " главное табло";
            break;
        case "swunit":
            titleModal.innerText = titleAction + " блок коммутации";
            break;
        case "asuo":
            titleModal.innerText = titleAction + " электронная очередь";
            break;
        case "fax":       
            titleModal.innerText = titleAction + " факс";
        
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
    divContainerBody.id = "containerContent";
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
            '<div class="row mt-2" id="modelRow">' +
            '<div class="col">Модель</div>' +
            '<div class="col">' +
            '<select class="form-select form-select-sm" id="modelSelect" aria-label="Default select example" ></select>' +
            '</div></div>' +
            '<div class="row mt-2" id="statusRow">' +
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
            '<div class="row mt-2" id="inventaryNumberRow">' +
            '<div class="col">Инвентарный номер</div>' +
            '<div class="col">' +
            '<input class="form-control form-control-sm" type="text" placeholder="введите инвентарный номер" aria-label="inventaryNumber" id="inventaryNumber">' +
            '</div></div>' +
            '<div class="row mt-2" id="serialNumberRow">' +
            '<div class="col">Серийный номер</div>' +
            '<div class="col">' +
            '<input class="form-control form-control-sm" type="text" placeholder="введите серийный номер" aria-label="serialNumber" id="serialNumber">' +
            '</div></div>';
    
    switch (attrib) {
        case "switch":
            
            break;
        case "router":
            
        break;
        break;
        case "terminal":
            
        break;
        case "thermoprinter":
            
        break;
        case "display":
            
        break;
        case "swunit":
            
        break;
        
        case "ats":
            let divRowYearExplAts = document.createElement("div");
            divRowYearExplAts.className = "row mt-2";
            let divColLabelYearExplAts = document.createElement("div");
            divColLabelYearExplAts.className = "col";
            divColLabelYearExplAts.innerText = "Год выпуска";
            let divColSelectYearExplAts = document.createElement("div");
            divColSelectYearExplAts.className = "col";
            let selectYearExplAts = document.createElement("select");
            selectYearExplAts.className = "form-select form-select-sm";
            selectYearExplAts.id = "dateCreateSelect";
            selectYearExplAts.setAttribute("aria-label", "dateCreate");
            divColSelectYearExplAts.appendChild(selectYearExplAts);
            divRowYearExplAts.appendChild(divColLabelYearExplAts);
            divRowYearExplAts.appendChild(divColSelectYearExplAts);
            divContainerBody.appendChild(divRowYearExplAts);
            break;
             case "conditioner":
            let divRowYearExplConditioner = document.createElement("div");
            divRowYearExplConditioner.className = "row mt-2";
            let divColLabelYearExplConditioner = document.createElement("div");
            divColLabelYearExplConditioner.className = "col";
            divColLabelYearExplConditioner.innerText = "Год выпуска";
            let divColSelectYearExplConditioner = document.createElement("div");
            divColSelectYearExplConditioner.className = "col";
            let selectYearExplConditioner = document.createElement("select");
            selectYearExplConditioner.className = "form-select form-select-sm";
            selectYearExplConditioner.id = "dateCreateSelect";
            selectYearExplConditioner.setAttribute("aria-label", "dateCreate");
            divColSelectYearExplConditioner.appendChild(selectYearExplConditioner);
            divRowYearExplConditioner.appendChild(divColLabelYearExplConditioner);
            divRowYearExplConditioner.appendChild(divColSelectYearExplConditioner);
            divContainerBody.appendChild(divRowYearExplConditioner);
            break;
             case "infomat":
            let divRowYearExplInfomat = document.createElement("div");
            divRowYearExplInfomat.className = "row mt-2";
            let divColLabelYearExplInfomat = document.createElement("div");
            divColLabelYearExplInfomat.className = "col";
            divColLabelYearExplInfomat.innerText = "Год выпуска";
            let divColSelectYearExplInfomat = document.createElement("div");
            divColSelectYearExplInfomat.className = "col";
            let selectYearExplInfomat = document.createElement("select");
            selectYearExplInfomat.className = "form-select form-select-sm";
            selectYearExplInfomat.id = "dateCreateSelect";
            selectYearExplInfomat.setAttribute("aria-label", "dateCreate");
            divColSelectYearExplInfomat.appendChild(selectYearExplInfomat);
            divRowYearExplInfomat.appendChild(divColLabelYearExplInfomat);
            divRowYearExplInfomat.appendChild(divColSelectYearExplInfomat);
            divContainerBody.appendChild(divRowYearExplInfomat);
            let divRowNumberRoomInfomat = document.createElement("div");
            divRowNumberRoomInfomat.className = "row mt-2";
            let divColLabelNumberRoomInfomat = document.createElement("div");
            divColLabelNumberRoomInfomat.className = "col";
            divColLabelNumberRoomInfomat.innerText = "Кабинет";
            let divColInputNumberRoomInfomat = document.createElement("div");
            divColInputNumberRoomInfomat.className = "col";
            let inputNumberRoomInfomat = document.createElement("input");
            inputNumberRoomInfomat.className = "form-control form-control-sm";
            inputNumberRoomInfomat.type = "text";
            inputNumberRoomInfomat.placeholder = "укажите расположение";
            inputNumberRoomInfomat.id = "numberRoom";
            inputNumberRoomInfomat.name = "numberRoom";
            divColInputNumberRoomInfomat.appendChild(inputNumberRoomInfomat);
            divRowNumberRoomInfomat.appendChild(divColLabelNumberRoomInfomat);
            divRowNumberRoomInfomat.appendChild(divColInputNumberRoomInfomat);
            divContainerBody.appendChild(divRowNumberRoomInfomat);
            let divRowNameFromOneCInfomat = document.createElement("div");
            divRowNameFromOneCInfomat.className = "row mt-2";
            let divColLabelNameFromOneCInfomat = document.createElement("div");
            divColLabelNameFromOneCInfomat.className = "col";
            divColLabelNameFromOneCInfomat.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCInfomat = document.createElement("div");
            divColInputNameFromOneCInfomat.className = "col";
            let inputNameFromOneCInfomat = document.createElement("textarea");
            inputNameFromOneCInfomat.className = "form-control form-control-sm";
            inputNameFromOneCInfomat.placeholder = "введите наименование";
            inputNameFromOneCInfomat.id = "nameFromOneC";
            inputNameFromOneCInfomat.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCInfomat.appendChild(inputNameFromOneCInfomat);
            divRowNameFromOneCInfomat.appendChild(divColLabelNameFromOneCInfomat);
            divRowNameFromOneCInfomat.appendChild(divColInputNameFromOneCInfomat);
            divContainerBody.appendChild(divRowNameFromOneCInfomat);
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
            let divRowNumberRoomMonitor = document.createElement("div");
            divRowNumberRoomMonitor.className = "row mt-2";
            let divColLabelNumberRoomMonitor = document.createElement("div");
            divColLabelNumberRoomMonitor.className = "col";
            divColLabelNumberRoomMonitor.innerText = "Кабинет";
            let divColInputNumberRoomMonitor = document.createElement("div");
            divColInputNumberRoomMonitor.className = "col";
            let inputNumberRoomMonitor = document.createElement("input");
            inputNumberRoomMonitor.className = "form-control form-control-sm";
            inputNumberRoomMonitor.type = "text";
            inputNumberRoomMonitor.placeholder = "укажите расположение";
            inputNumberRoomMonitor.id = "numberRoom";
            inputNumberRoomMonitor.name = "numberRoom";
            divColInputNumberRoomMonitor.appendChild(inputNumberRoomMonitor);
            divRowNumberRoomMonitor.appendChild(divColLabelNumberRoomMonitor);
            divRowNumberRoomMonitor.appendChild(divColInputNumberRoomMonitor);
            divContainerBody.appendChild(divRowNumberRoomMonitor);
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
            let divRowNumberRoomUps = document.createElement("div");
            divRowNumberRoomUps.className = "row mt-2";
            let divColLabelNumberRoomUps = document.createElement("div");
            divColLabelNumberRoomUps.className = "col";
            divColLabelNumberRoomUps.innerText = "Кабинет";
            let divColInputNumberRoomUps = document.createElement("div");
            divColInputNumberRoomUps.className = "col";
            let inputNumberRoomUps = document.createElement("input");
            inputNumberRoomUps.className = "form-control form-control-sm";
            inputNumberRoomUps.type = "text";
            inputNumberRoomUps.placeholder = "укажите расположение";
            inputNumberRoomUps.id = "numberRoom";
            inputNumberRoomUps.name = "numberRoom";
            divColInputNumberRoomUps.appendChild(inputNumberRoomUps);
            divRowNumberRoomUps.appendChild(divColLabelNumberRoomUps);
            divRowNumberRoomUps.appendChild(divColInputNumberRoomUps);
            divContainerBody.appendChild(divRowNumberRoomUps);
            let divRowNameFromOneCUps = document.createElement("div");
            divRowNameFromOneCUps.className = "row mt-2";
            let divColLabelNameFromOneCUps = document.createElement("div");
            divColLabelNameFromOneCUps.className = "col";
            divColLabelNameFromOneCUps.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCUps = document.createElement("div");
            divColInputNameFromOneCUps.className = "col";
            let inputNameFromOneCUps = document.createElement("textarea");
            inputNameFromOneCUps.className = "form-control form-control-sm";
            inputNameFromOneCUps.placeholder = "введите наименование";
            inputNameFromOneCUps.id = "nameFromOneC";
            inputNameFromOneCUps.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCUps.appendChild(inputNameFromOneCUps);
            divRowNameFromOneCUps.appendChild(divColLabelNameFromOneCUps);
            divRowNameFromOneCUps.appendChild(divColInputNameFromOneCUps);
            divContainerBody.appendChild(divRowNameFromOneCUps);
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
           
            break;
            case "upsforserver":
            let divRowNumberRoomUpsForServer = document.createElement("div");
            divRowNumberRoomUpsForServer.className = "row mt-2";
            let divColLabelNumberRoomUpsForServer = document.createElement("div");
            divColLabelNumberRoomUpsForServer.className = "col";
            divColLabelNumberRoomUpsForServer.innerText = "Кабинет";
            let divColInputNumberRoomUpsForServer = document.createElement("div");
            divColInputNumberRoomUpsForServer.className = "col";
            let inputNumberRoomUpsForServer = document.createElement("input");
            inputNumberRoomUpsForServer.className = "form-control form-control-sm";
            inputNumberRoomUpsForServer.type = "text";
            inputNumberRoomUpsForServer.placeholder = "укажите расположение";
            inputNumberRoomUpsForServer.id = "numberRoom";
            inputNumberRoomUpsForServer.name = "numberRoom";
            divColInputNumberRoomUpsForServer.appendChild(inputNumberRoomUpsForServer);
            divRowNumberRoomUpsForServer.appendChild(divColLabelNumberRoomUpsForServer);
            divRowNumberRoomUpsForServer.appendChild(divColInputNumberRoomUpsForServer);
            divContainerBody.appendChild(divRowNumberRoomUpsForServer);
            let divRowNameFromOneCUpsForServer = document.createElement("div");
            divRowNameFromOneCUpsForServer.className = "row mt-2";
            let divColLabelNameFromOneCUpsForServer = document.createElement("div");
            divColLabelNameFromOneCUpsForServer.className = "col";
            divColLabelNameFromOneCUpsForServer.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCUpsForServer = document.createElement("div");
            divColInputNameFromOneCUpsForServer.className = "col";
            let inputNameFromOneCUpsForServer = document.createElement("textarea");
            inputNameFromOneCUpsForServer.className = "form-control form-control-sm";
            inputNameFromOneCUpsForServer.placeholder = "введите наименование";
            inputNameFromOneCUpsForServer.id = "nameFromOneC";
            inputNameFromOneCUpsForServer.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCUpsForServer.appendChild(inputNameFromOneCUpsForServer);
            divRowNameFromOneCUpsForServer.appendChild(divColLabelNameFromOneCUpsForServer);
            divRowNameFromOneCUpsForServer.appendChild(divColInputNameFromOneCUpsForServer);
            divContainerBody.appendChild(divRowNameFromOneCUpsForServer);
            let divRowDateReplaceBatteryUpsForServer = document.createElement("div");
            divRowDateReplaceBatteryUpsForServer.className = "row mt-2";
            let divColLabelDateReplaceBatteryUpsForServer = document.createElement("div");
            divColLabelDateReplaceBatteryUpsForServer.className = "col";
            divColLabelDateReplaceBatteryUpsForServer.innerText = "Год замены батареи";
            let divColDateReplaceBatterySelectUpsForServer = document.createElement("div");
            divColDateReplaceBatterySelectUpsForServer.className = "col";
            let selectDateReplaceBatteryUpsForServer = document.createElement("select");
            selectDateReplaceBatteryUpsForServer.className = "form-select form-select-sm";
            selectDateReplaceBatteryUpsForServer.id = "dateReplaceSelect";
            selectDateReplaceBatteryUpsForServer.setAttribute("aria-label", "dateReplaceSelect");
            divColDateReplaceBatterySelectUpsForServer.appendChild(selectDateReplaceBatteryUpsForServer);
            divRowDateReplaceBatteryUpsForServer.appendChild(divColLabelDateReplaceBatteryUpsForServer);
            divRowDateReplaceBatteryUpsForServer.appendChild(divColDateReplaceBatterySelectUpsForServer);
            divContainerBody.appendChild(divRowDateReplaceBatteryUpsForServer);
            
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
            
            
            let accordionFlushSysBlock = document.createElement("div");
            accordionFlushSysBlock.className = "accordion accordion-flush mt-3";
            accordionFlushSysBlock.id = "accordionFlushExample";
            let accordionItemSysBlock = document.createElement("div");
            accordionItemSysBlock.className = "accordion-item";
            let accordionHeaderSysBlock = document.createElement("h2");
            accordionHeaderSysBlock.className = "accordion-header";
            let accordionButtonSysBlock = document.createElement("button");
            accordionButtonSysBlock.className = "accordion-button collapsed";
            accordionButtonSysBlock.type = "button";
            accordionButtonSysBlock.setAttribute("data-bs-toggle", "collapse");
            accordionButtonSysBlock.setAttribute("data-bs-target", "#flush-collapseOne");
            accordionButtonSysBlock.setAttribute("aria-expanded", "false");
            accordionButtonSysBlock.setAttribute("aria-controls", "flush-collapseOne");
            let buttonSpanTitleSysBlock = document.createElement("span");
            buttonSpanTitleSysBlock.id = "buttonAccordionTitleSysBlock";
            buttonSpanTitleSysBlock.innerText = "Дополнительная информация:";
            let accordionFlushCollapseSysBlock = document.createElement("div");
            accordionFlushCollapseSysBlock.className = "accordion-collapse collapse";
            accordionFlushCollapseSysBlock.id = "flush-collapseOne";
            accordionFlushCollapseSysBlock.setAttribute("data-bs-parent", "#accordionFlushExample");
            let accordionBodySysBlock = document.createElement("div");
            accordionBodySysBlock.className = "accordion-body";
            accordionBodySysBlock.id = "accordionBodySysBlock";
            accordionFlushSysBlock.appendChild(accordionItemSysBlock);
            accordionItemSysBlock.appendChild(accordionHeaderSysBlock);
            accordionHeaderSysBlock.appendChild(accordionButtonSysBlock);
            accordionButtonSysBlock.appendChild(buttonSpanTitleSysBlock);
            accordionItemSysBlock.appendChild(accordionFlushCollapseSysBlock);
            accordionFlushCollapseSysBlock.appendChild(accordionBodySysBlock);
            divContainerBody.appendChild(accordionFlushSysBlock);
            
            
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
            accordionBodySysBlock.appendChild(divRowVideoCardSelect);
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
            accordionBodySysBlock.appendChild(divRowSoundCardSelect);
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
            accordionBodySysBlock.appendChild(divRowLanCardSelect);
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
            accordionBodySysBlock.appendChild(divRowCdDriveSelect);
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
            accordionBodySysBlock.appendChild(divRowKeyboardSelect);
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
            accordionBodySysBlock.appendChild(divRowMouseSelect);
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
            accordionBodySysBlock.appendChild(divRowSpeakersSelect);
            
         
            break;
        case "fax":
            let divRowNumberRoomFax = document.createElement("div");
            divRowNumberRoomFax.className = "row mt-2";
            let divColLabelNumberRoomFax = document.createElement("div");
            divColLabelNumberRoomFax.className = "col";
            divColLabelNumberRoomFax.innerText = "Кабинет";
            let divColInputNumberRoomFax = document.createElement("div");
            divColInputNumberRoomFax.className = "col";
            let inputNumberRoomFax = document.createElement("input");
            inputNumberRoomFax.className = "form-control form-control-sm";
            inputNumberRoomFax.type = "text";
            inputNumberRoomFax.placeholder = "укажите расположение";
            inputNumberRoomFax.id = "numberRoom";
            inputNumberRoomFax.name = "numberRoom";
            divColInputNumberRoomFax.appendChild(inputNumberRoomFax);
            divRowNumberRoomFax.appendChild(divColLabelNumberRoomFax);
            divRowNumberRoomFax.appendChild(divColInputNumberRoomFax);
            divContainerBody.appendChild(divRowNumberRoomFax);
            let divRowNameFromOneCFax = document.createElement("div");
            divRowNameFromOneCFax.className = "row mt-2";
            let divColLabelNameFromOneCFax = document.createElement("div");
            divColLabelNameFromOneCFax.className = "col";
            divColLabelNameFromOneCFax.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCFax = document.createElement("div");
            divColInputNameFromOneCFax.className = "col";
            let inputNameFromOneCFax = document.createElement("textarea");
            inputNameFromOneCFax.className = "form-control form-control-sm";
            inputNameFromOneCFax.placeholder = "введите наименование";
            inputNameFromOneCFax.id = "nameFromOneC";
            inputNameFromOneCFax.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCFax.appendChild(inputNameFromOneCFax);
            divRowNameFromOneCFax.appendChild(divColLabelNameFromOneCFax);
            divRowNameFromOneCFax.appendChild(divColInputNameFromOneCFax);
            divContainerBody.appendChild(divRowNameFromOneCFax);
            var divRowIpAdressFax = document.createElement("div");
            divRowIpAdressFax.className = "row mt-2";
            var divColLabelIpAdressFax = document.createElement("div");
            divColLabelIpAdressFax.className = "col";
            divColLabelIpAdressFax.innerText = "ip адрес";
            var divColInputIpAdressFax = document.createElement("div");
            divColInputIpAdressFax.className = "col";
            var inputIpAdressFax = document.createElement("input");
            inputIpAdressFax.className = "form-control form-control-sm";
            inputIpAdressFax.type = "text";
            inputIpAdressFax.name = "ipAdress";
            inputIpAdressFax.placeholder = "xxx.xxx.xxx.xxx";
            inputIpAdressFax.pattern = "^([0-9]{1,3}\.){3}[0-9]{1,3}$";
            inputIpAdressFax.id = "ipAdress";
            divColInputIpAdressFax.appendChild(inputIpAdressFax);
            divRowIpAdressFax.appendChild(divColLabelIpAdressFax);
            divRowIpAdressFax.appendChild(divColInputIpAdressFax);
            divContainerBody.appendChild(divRowIpAdressFax);
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
            
            let yearCreatedSwitchRow = document.createElement("div");
            yearCreatedSwitchRow.className = "row mt-2";
            let divColLabelSwitchYearCreated = document.createElement("div");
            divColLabelSwitchYearCreated.className = "col";
            divColLabelSwitchYearCreated.innerText = "Год выпуска";
            let divColInputSwitchYearCreated = document.createElement("div");
            divColInputSwitchYearCreated.className = "col";
            let inputSwitchYearCreated = document.createElement("input");
            inputSwitchYearCreated.className = "form-control form-control-sm";
            inputSwitchYearCreated.type = "number";
            inputSwitchYearCreated.min = 2000;
            inputSwitchYearCreated.value = 2000;
            inputSwitchYearCreated.id = "dateCreateSelect";
            divColInputSwitchYearCreated.appendChild(inputSwitchYearCreated);
            yearCreatedSwitchRow.appendChild(divColLabelSwitchYearCreated);
            yearCreatedSwitchRow.appendChild(divColInputSwitchYearCreated);
            divContainerBody.appendChild(yearCreatedSwitchRow);
            
            
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
            
            let yearCreatedRouterRow = document.createElement("div");
            yearCreatedRouterRow.className = "row mt-2";
            let divColLabelRouterYearCreated = document.createElement("div");
            divColLabelRouterYearCreated.className = "col";
            divColLabelRouterYearCreated.innerText = "Год выпуска";
            let divColInputRouterYearCreated = document.createElement("div");
            divColInputRouterYearCreated.className = "col";
            let inputRouterYearCreated = document.createElement("input");
            inputRouterYearCreated.className = "form-control form-control-sm";
            inputRouterYearCreated.type = "number";
            inputRouterYearCreated.min = 2000;
            inputRouterYearCreated.value = 2000;
            inputRouterYearCreated.id = "dateCreateSelect";
            divColInputRouterYearCreated.appendChild(inputRouterYearCreated);
            yearCreatedRouterRow.appendChild(divColLabelRouterYearCreated);
            yearCreatedRouterRow.appendChild(divColInputRouterYearCreated);
            divContainerBody.appendChild(yearCreatedRouterRow);
            
            
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
            case "ats":
             let divRowNumberRoomAts = document.createElement("div");
            divRowNumberRoomAts.className = "row mt-2";
            let divColLabelNumberRoomAts = document.createElement("div");
            divColLabelNumberRoomAts.className = "col";
            divColLabelNumberRoomAts.innerText = "Кабинет";
            let divColInputNumberRoomAts = document.createElement("div");
            divColInputNumberRoomAts.className = "col";
            
            let inputNumberRoomAts = document.createElement("input");
            inputNumberRoomAts.className = "form-control form-control-sm";
            inputNumberRoomAts.type = "text";
            inputNumberRoomAts.placeholder = "укажите расположение";
            inputNumberRoomAts.id = "numberRoom";
            inputNumberRoomAts.name = "numberRoom";
            divColInputNumberRoomAts.appendChild(inputNumberRoomAts);
            divRowNumberRoomAts.appendChild(divColLabelNumberRoomAts);
            divRowNumberRoomAts.appendChild(divColInputNumberRoomAts);
            divContainerBody.appendChild(divRowNumberRoomAts);
            
              let divRowNameFromOneCAts = document.createElement("div");
            divRowNameFromOneCAts.className = "row mt-2";
            let divColLabelNameFromOneCAts = document.createElement("div");
            divColLabelNameFromOneCAts.className = "col";
            divColLabelNameFromOneCAts.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCAts = document.createElement("div");
            divColInputNameFromOneCAts.className = "col";
            let inputNameFromOneCAts = document.createElement("textarea");
            inputNameFromOneCAts.className = "form-control form-control-sm";
            inputNameFromOneCAts.placeholder = "введите наименование";
            inputNameFromOneCAts.id = "nameFromOneC";
            inputNameFromOneCAts.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCAts.appendChild(inputNameFromOneCAts);
            divRowNameFromOneCAts.appendChild(divColLabelNameFromOneCAts);
            divRowNameFromOneCAts.appendChild(divColInputNameFromOneCAts);
            divContainerBody.appendChild(divRowNameFromOneCAts);
            
            let divRowAtsOuterTypeConnectionsSelect = document.createElement("div");
            divRowAtsOuterTypeConnectionsSelect.className = "row mt-2";
            let divColLabelAtsOuterTypeConnectionsSelect = document.createElement("div");
            divColLabelAtsOuterTypeConnectionsSelect.className = "col";
            divColLabelAtsOuterTypeConnectionsSelect.innerText = "Внешние подключения (тип)";
            let divColAtsOuterTypeConnectionsSelect = document.createElement("div");
            divColAtsOuterTypeConnectionsSelect.className = "col";
            let selectAtsOuterTypeConnections = document.createElement("select");
            selectAtsOuterTypeConnections.className = "form-select form-select-sm";
            selectAtsOuterTypeConnections.id = "outerConnectionType";
            selectAtsOuterTypeConnections.setAttribute("aria-label", "outerConnectionType");
            
            let optionSipOuterTypeConnection = document.createElement("option");
            optionSipOuterTypeConnection.value = "SIP";
            optionSipOuterTypeConnection.innerText = "SIP";
            
            let optionE1OuterTypeConnection = document.createElement("option");
            optionE1OuterTypeConnection.value = "E1";
            optionE1OuterTypeConnection.innerText = "E1";
            
            selectAtsOuterTypeConnections.appendChild(optionSipOuterTypeConnection);
            selectAtsOuterTypeConnections.appendChild(optionE1OuterTypeConnection);
            
            divColAtsOuterTypeConnectionsSelect.appendChild(selectAtsOuterTypeConnections);
            divRowAtsOuterTypeConnectionsSelect.appendChild(divColLabelAtsOuterTypeConnectionsSelect);
            divRowAtsOuterTypeConnectionsSelect.appendChild(divColAtsOuterTypeConnectionsSelect);
            divContainerBody.appendChild(divRowAtsOuterTypeConnectionsSelect);
            
            let cityNumbersAmountAtsRow = document.createElement("div");
            cityNumbersAmountAtsRow.className = "row mt-2";
            let divColLabelCityNumbersAmountAts = document.createElement("div");
            divColLabelCityNumbersAmountAts.className = "col";
            divColLabelCityNumbersAmountAts.innerText = "Количество городских номеров";
            let divColInputCityNumbersAmountAts = document.createElement("div");
            divColInputCityNumbersAmountAts.className = "col";
            let inputCityNumbersAmountAts = document.createElement("input");
            inputCityNumbersAmountAts.className = "form-control form-control-sm";
            inputCityNumbersAmountAts.type = "number";
            inputCityNumbersAmountAts.min = 1;
            inputCityNumbersAmountAts.max = 99;
            inputCityNumbersAmountAts.value = 1;
            inputCityNumbersAmountAts.id = "cityNumberAmount";
            divColInputCityNumbersAmountAts.appendChild(inputCityNumbersAmountAts);
            cityNumbersAmountAtsRow.appendChild(divColLabelCityNumbersAmountAts);
            cityNumbersAmountAtsRow.appendChild(divColInputCityNumbersAmountAts);
            divContainerBody.appendChild(cityNumbersAmountAtsRow);
            
            let innerConnectionsAmountRow = document.createElement("div");
            innerConnectionsAmountRow.className = "row mt-2";
            
            let divColLabelConnectionsAmount = document.createElement("div")
            divColLabelConnectionsAmount.className = "col-3";
            divColLabelConnectionsAmount.innerText = "Внутренние подключения";
            
            
            let divColConteinerForRowsInputs = document.createElement("div");
            divColConteinerForRowsInputs.className = "col-9";
            
            
            let innerConnectionsIpAmountRow = document.createElement("div");
            innerConnectionsIpAmountRow.className = "row";
            
            let divColLabelInnerConnectionsIpAmount = document.createElement("div");
            divColLabelInnerConnectionsIpAmount.className = "col-4";
            divColLabelInnerConnectionsIpAmount.innerText = "ip";
            
            let divColInputInnerConnectionsIpAmount = document.createElement("div");
            divColInputInnerConnectionsIpAmount.className = "col-8";
            
            let inputInnerConnectionsIpAmount = document.createElement("input");
            inputInnerConnectionsIpAmount.className = "form-control form-control-sm";
            inputInnerConnectionsIpAmount.type = "number";
            inputInnerConnectionsIpAmount.min = 1;
            inputInnerConnectionsIpAmount.max = 999;
            inputInnerConnectionsIpAmount.value = 1;
            inputInnerConnectionsIpAmount.id = "innerConnectionIp";
            
            let innerConnectionsAnalogAmountRow = document.createElement("div");
            innerConnectionsAnalogAmountRow.className = "row mt-2";
            
            let divColLabelInnerConnectionsAnalogAmount = document.createElement("div");
            divColLabelInnerConnectionsAnalogAmount.className = "col-4";
            divColLabelInnerConnectionsAnalogAmount.innerText = "аналоговые";
            
            let divColInputInnerConnectionsAnalogAmount = document.createElement("div");
            divColInputInnerConnectionsAnalogAmount.className = "col-8";
            
            let inputInnerConnectionsAnalogAmount = document.createElement("input");
            inputInnerConnectionsAnalogAmount.className = "form-control form-control-sm";
            inputInnerConnectionsAnalogAmount.type = "number";
            inputInnerConnectionsAnalogAmount.id = "innerConnectionAnalog";
            inputInnerConnectionsAnalogAmount.min = 1;
            inputInnerConnectionsAnalogAmount.max = 999;
            inputInnerConnectionsAnalogAmount.value = 1;
            
            divColInputInnerConnectionsIpAmount.appendChild(inputInnerConnectionsIpAmount);
            divColInputInnerConnectionsAnalogAmount.appendChild(inputInnerConnectionsAnalogAmount);
            innerConnectionsAnalogAmountRow.appendChild(divColLabelInnerConnectionsAnalogAmount);
            innerConnectionsAnalogAmountRow.appendChild(divColInputInnerConnectionsAnalogAmount);
            innerConnectionsIpAmountRow.appendChild(divColLabelInnerConnectionsIpAmount);
            innerConnectionsIpAmountRow.appendChild(divColInputInnerConnectionsIpAmount);
            divColConteinerForRowsInputs.appendChild(innerConnectionsIpAmountRow);
            divColConteinerForRowsInputs.appendChild(innerConnectionsAnalogAmountRow)
            innerConnectionsAmountRow.appendChild(divColLabelConnectionsAmount);
            innerConnectionsAmountRow.appendChild(divColConteinerForRowsInputs);
            divContainerBody.appendChild(innerConnectionsAmountRow);
            break;
        case "asuo":
            
            let divRowNumberRoomAsuo = document.createElement("div");
            divRowNumberRoomAsuo.className = "row mt-2";
            let divColLabelNumberRoomAsuo = document.createElement("div");
            divColLabelNumberRoomAsuo.className = "col";
            divColLabelNumberRoomAsuo.innerText = "Кабинет";
            let divColInputNumberRoomAsuo = document.createElement("div");
            divColInputNumberRoomAsuo.className = "col";
            let inputNumberRoomAsuo = document.createElement("input");
            inputNumberRoomAsuo.className = "form-control form-control-sm";
            inputNumberRoomAsuo.type = "text";
            inputNumberRoomAsuo.placeholder = "укажите расположение";
            inputNumberRoomAsuo.id = "numberRoom";
            inputNumberRoomAsuo.name = "numberRoom";
            divColInputNumberRoomAsuo.appendChild(inputNumberRoomAsuo);
            divRowNumberRoomAsuo.appendChild(divColLabelNumberRoomAsuo);
            divRowNumberRoomAsuo.appendChild(divColInputNumberRoomAsuo);
            divContainerBody.appendChild(divRowNumberRoomAsuo);
            let divRowNameFromOneCAsuo = document.createElement("div");
            divRowNameFromOneCAsuo.className = "row mt-2";
            let divColLabelNameFromOneCAsuo = document.createElement("div");
            divColLabelNameFromOneCAsuo.className = "col";
            divColLabelNameFromOneCAsuo.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCAsuo = document.createElement("div");
            divColInputNameFromOneCAsuo.className = "col";
            let inputNameFromOneCAsuo = document.createElement("textarea");
            inputNameFromOneCAsuo.className = "form-control form-control-sm";
            inputNameFromOneCAsuo.placeholder = "введите наименование";
            inputNameFromOneCAsuo.id = "nameFromOneC";
            inputNameFromOneCAsuo.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCAsuo.appendChild(inputNameFromOneCAsuo);
            divRowNameFromOneCAsuo.appendChild(divColLabelNameFromOneCAsuo);
            divRowNameFromOneCAsuo.appendChild(divColInputNameFromOneCAsuo);
            divContainerBody.appendChild(divRowNameFromOneCAsuo);
            
            let divRowDisplaySelect = document.createElement("div");
            divRowDisplaySelect.className = "row mt-2";
            let divColLabelDisplaySelect = document.createElement("div");
            divColLabelDisplaySelect.className = "col";
            divColLabelDisplaySelect.innerText = "Главное табло";
            let divColDisplaySelect = document.createElement("div");
            divColDisplaySelect.className = "col";
            let selectDisplay = document.createElement("select");
            selectDisplay.className = "form-select form-select-sm";
            selectDisplay.id = "displaySelect";
            selectDisplay.setAttribute("aria-label", "displaySelect");
            divColDisplaySelect.appendChild(selectDisplay);
            divRowDisplaySelect.appendChild(divColLabelDisplaySelect);
            divRowDisplaySelect.appendChild(divColDisplaySelect);
            divContainerBody.appendChild(divRowDisplaySelect);
            
            let divRowTerminalSelect = document.createElement("div");
            divRowTerminalSelect.className = "row mt-2";
            let divColLabelTerminalSelect = document.createElement("div");
            divColLabelTerminalSelect.className = "col";
            divColLabelTerminalSelect.innerText = "Терминал";
            let divColTerminalSelect = document.createElement("div");
            divColTerminalSelect.className = "col";
            let selectTerminal = document.createElement("select");
            selectTerminal.className = "form-select form-select-sm";
            selectTerminal.id = "terminalSelect";
            selectTerminal.setAttribute("aria-label", "terminalSelect");
            divColTerminalSelect.appendChild(selectTerminal);
            divRowTerminalSelect.appendChild(divColLabelTerminalSelect);
            divRowTerminalSelect.appendChild(divColTerminalSelect);
            divContainerBody.appendChild(divRowTerminalSelect);
            
            let divRowThermoprinterSelect = document.createElement("div");
            divRowThermoprinterSelect.className = "row mt-2";
            let divColLabelThermoprinterSelect = document.createElement("div");
            divColLabelThermoprinterSelect.className = "col";
            divColLabelThermoprinterSelect.innerText = "Термопринтер";
            let divColThermoprinterSelect = document.createElement("div");
            divColThermoprinterSelect.className = "col";
            let selectThermoprinter = document.createElement("select");
            selectThermoprinter.className = "form-select form-select-sm";
            selectThermoprinter.id = "thermoprinterSelect";
            selectThermoprinter.setAttribute("aria-label", "thermoprinterSelect");
            divColThermoprinterSelect.appendChild(selectThermoprinter);
            divRowThermoprinterSelect.appendChild(divColLabelThermoprinterSelect);
            divRowThermoprinterSelect.appendChild(divColThermoprinterSelect);
            divContainerBody.appendChild(divRowThermoprinterSelect);
            
            
            let divRowSwitchSelect = document.createElement("div");
            divRowSwitchSelect.className = "row mt-2";
            let divColLabelSwitchSelect = document.createElement("div");
            divColLabelSwitchSelect.className = "col";
            divColLabelSwitchSelect.innerText = "Свитч";
            let divColSwitchSelect = document.createElement("div");
            divColSwitchSelect.className = "col";
            let selectSwitch = document.createElement("select");
            selectSwitch.className = "form-select form-select-sm";
            selectSwitch.id = "switchSelect";
            selectSwitch.setAttribute("aria-label", "switchSelect");
            divColSwitchSelect.appendChild(selectSwitch);
            divRowSwitchSelect.appendChild(divColLabelSwitchSelect);
            divRowSwitchSelect.appendChild(divColSwitchSelect);
            divContainerBody.appendChild(divRowSwitchSelect);
            
            let divRowSwitchingUnitSelect = document.createElement("div");
            divRowSwitchingUnitSelect.className = "row mt-2";
            let divColLabelSwitchingUnitSelect = document.createElement("div");
            divColLabelSwitchingUnitSelect.className = "col";
            divColLabelSwitchingUnitSelect.innerText = "Блок коммутации";
            let divColSwitchingUnitSelect = document.createElement("div");
            divColSwitchingUnitSelect.className = "col";
            let selectSwitchingUnit = document.createElement("select");
            selectSwitchingUnit.className = "form-select form-select-sm";
            selectSwitchingUnit.id = "switchingUnitSelect";
            selectSwitchingUnit.setAttribute("aria-label", "switchingUnitSelect");
            divColSwitchingUnitSelect.appendChild(selectSwitchingUnit);
            divRowSwitchingUnitSelect.appendChild(divColLabelSwitchingUnitSelect);
            divRowSwitchingUnitSelect.appendChild(divColSwitchingUnitSelect);
            divContainerBody.appendChild(divRowSwitchingUnitSelect);
            
            let divRowSubDisplaySelect = document.createElement("div");
            divRowSubDisplaySelect.className = "row mt-2";
            let divColLabelSubDisplaySelect = document.createElement("div");
            divColLabelSubDisplaySelect.className = "col-6";
            divColLabelSubDisplaySelect.innerText = "Электронное табло на кабинки и кабинеты";
            let divColSubDisplaySelect = document.createElement("div");
            divColSubDisplaySelect.className = "col-4";
            let selectSubDisplay = document.createElement("select");
            selectSubDisplay.className = "form-select form-select-sm";
            selectSubDisplay.id = "subDisplaySelect";
            selectSubDisplay.setAttribute("aria-label", "subDisplaySelect");
            let divColSubDisplayAmount = document.createElement('div');
            divColSubDisplayAmount.className = 'col-2';
            let inputSubDisplayAmount = document.createElement("input");
            inputSubDisplayAmount.className = "form-control form-control-sm";
            inputSubDisplayAmount.type = "number";
            inputSubDisplayAmount.min = 1;
            inputSubDisplayAmount.max = 99;
            inputSubDisplayAmount.step = 1;
            inputSubDisplayAmount.value = 1;
            inputSubDisplayAmount.id = "subDisplayAmount";
            divColSubDisplayAmount.appendChild(inputSubDisplayAmount);
            
            divColSubDisplaySelect.appendChild(selectSubDisplay);
            divRowSubDisplaySelect.appendChild(divColLabelSubDisplaySelect);
            divRowSubDisplaySelect.appendChild(divColSubDisplaySelect);
            divRowSubDisplaySelect.appendChild(divColSubDisplayAmount);
            divContainerBody.appendChild(divRowSubDisplaySelect);
            
          
            break;
        case "conditioner":
            let divRowNameFromOneCConditioner = document.createElement("div");
            divRowNameFromOneCConditioner.className = "row mt-2";
            let divColLabelNameFromOneCConditioner = document.createElement("div");
            divColLabelNameFromOneCConditioner.className = "col";
            divColLabelNameFromOneCConditioner.innerText = "Наименование в ведомости ОС";
            let divColInputNameFromOneCConditioner = document.createElement("div");
            divColInputNameFromOneCConditioner.className = "col";
            let inputNameFromOneCConditioner = document.createElement("input");
            inputNameFromOneCConditioner.className = "form-control form-control-sm";
            inputNameFromOneCConditioner.type = "text";
            inputNameFromOneCConditioner.placeholder = "введите наименование";
            inputNameFromOneCConditioner.id = "nameFromOneC";
            inputNameFromOneCConditioner.setAttribute("aria-label", "nameFromOneC");
            divColInputNameFromOneCConditioner.appendChild(inputNameFromOneCConditioner);
            divRowNameFromOneCConditioner.appendChild(divColLabelNameFromOneCConditioner);
            divRowNameFromOneCConditioner.appendChild(divColInputNameFromOneCConditioner);
            divContainerBody.appendChild(divRowNameFromOneCConditioner);
            
             let divRowNumberRoomConditioner = document.createElement("div");
            divRowNumberRoomConditioner.className = "row mt-2";
            let divColLabelNumberRoomConditioner = document.createElement("div");
            divColLabelNumberRoomConditioner.className = "col";
            divColLabelNumberRoomConditioner.innerText = "Кабинет";
            let divColInputNumberRoomConditioner = document.createElement("div");
            divColInputNumberRoomConditioner.className = "col";
            
            let inputNumberRoomConditioner = document.createElement("input");
            inputNumberRoomConditioner.className = "form-control form-control-sm";
            inputNumberRoomConditioner.type = "text";
            inputNumberRoomConditioner.placeholder = "укажите расположение";
            inputNumberRoomConditioner.id = "numberRoom";
            inputNumberRoomConditioner.name = "numberRoom";
            divColInputNumberRoomConditioner.appendChild(inputNumberRoomConditioner);
            divRowNumberRoomConditioner.appendChild(divColLabelNumberRoomConditioner);
            divRowNumberRoomConditioner.appendChild(divColInputNumberRoomConditioner);
            divContainerBody.appendChild(divRowNumberRoomConditioner);
            
            let divRowConditionerTypeSelect = document.createElement("div");
            divRowConditionerTypeSelect.className = "row mt-2";
            let divColLabelConditionerTypeSelect = document.createElement("div");
            divColLabelConditionerTypeSelect.className = "col";
            divColLabelConditionerTypeSelect.innerText = "Тип";
            let divColConditionerTypeSelect = document.createElement("div");
            divColConditionerTypeSelect.className = "col";
            let selectConditionerType = document.createElement("select");
            selectConditionerType.className = "form-select form-select-sm";
            selectConditionerType.id = "conditionerTypeSelect";
            selectConditionerType.setAttribute("aria-label", "conditionerType");
            
            let optionConditionerTypeWindow = document.createElement("option");
            optionConditionerTypeWindow.value = "WINDOW";
            optionConditionerTypeWindow.innerText = "Оконный";
            
            let optionConditionerTypeWall = document.createElement("option");
            optionConditionerTypeWall.value = "WALL";
            optionConditionerTypeWall.innerText = "Настенный";
            
            let optionConditionerTypeCeiling = document.createElement("option");
            optionConditionerTypeCeiling.value = "CEILING";
            optionConditionerTypeCeiling.innerText = "Потолочный";
            
            let optionConditionerTypeFloor = document.createElement("option");
            optionConditionerTypeFloor.value = "FLOOR";
            optionConditionerTypeFloor.innerText = "Напольный";
            
            selectConditionerType.appendChild(optionConditionerTypeWindow);
            selectConditionerType.appendChild(optionConditionerTypeWall);
            selectConditionerType.appendChild(optionConditionerTypeCeiling);
            selectConditionerType.appendChild(optionConditionerTypeFloor);
            
            divColConditionerTypeSelect.appendChild(selectConditionerType);
            divRowConditionerTypeSelect.appendChild(divColLabelConditionerTypeSelect);
            divRowConditionerTypeSelect.appendChild(divColConditionerTypeSelect);
            divContainerBody.appendChild(divRowConditionerTypeSelect);
            
            let divSplitSystemRow = document.createElement("div");
            divSplitSystemRow.className = "row mt-2";
            let divColLabelSplitSystem = document.createElement("div");
            divColLabelSplitSystem.className = "col-6";
            divColLabelSplitSystem.innerText = "Сплит система";
            let divColSplitSystemTrue = document.createElement("div");
            divColSplitSystemTrue.className = "col-3";
            let divColSplitSystemFalse = document.createElement("div");
            divColSplitSystemFalse.className = "col-3";
            let radioSplitSystemTrue = document.createElement("div");
            radioSplitSystemTrue.className = "form-check checkRadio";
            radioSplitSystemTrue.innerHTML = '<input class="form-check-input" type="radio" name="splitSystemRadio" id="splitSystemTrue" >' +
                                          '<label class="form-check-label" for="splitSystemTrue">Да</label></div>';
            let radioSplitSystemFalse = document.createElement("div");
            radioSplitSystemFalse.className = "form-check";
            radioSplitSystemFalse.innerHTML = '<input class="form-check-input" type="radio" name="splitSystemRadio" id="splitSystemFalse" checked>' +
                                          '<label class="form-check-label" for="splitSystemFalse">Нет</label></div>';
            divColSplitSystemTrue.appendChild(radioSplitSystemTrue);
            divColSplitSystemFalse.appendChild(radioSplitSystemFalse);
            divSplitSystemRow.appendChild(divColLabelSplitSystem);
            divSplitSystemRow.appendChild(divColSplitSystemTrue);
            divSplitSystemRow.appendChild(divColSplitSystemFalse);
            divContainerBody.appendChild(divSplitSystemRow);
            
             let divRowConditionerDescription = document.createElement("div");
            divRowConditionerDescription.className = "row mt-2";
            let divColLabelConditionerDescription = document.createElement("div");
            divColLabelConditionerDescription.className = "col";
            divColLabelConditionerDescription.innerText = "Описание";
            let divColInputConditionerDescription = document.createElement("div");
            divColInputConditionerDescription.className = "col";
            let inputConditionerDescription = document.createElement("textarea");
            inputConditionerDescription.className = "form-control form-control-sm";
            inputConditionerDescription.placeholder = "поле для ввода описания";
            inputConditionerDescription.id = "descriptionInput";
            inputConditionerDescription.setAttribute("aria-label", "descriptionInput");
            divColInputConditionerDescription.appendChild(inputConditionerDescription);
            divRowConditionerDescription.appendChild(divColLabelConditionerDescription);
            divRowConditionerDescription.appendChild(divColInputConditionerDescription);
            divContainerBody.appendChild(divRowConditionerDescription);
            
             let divWinterKitRow = document.createElement("div");
            divWinterKitRow.className = "row mt-2";
            let divColLabelWinterKit = document.createElement("div");
            divColLabelWinterKit.className = "col-6";
            divColLabelWinterKit.innerText = "Зимний комплект";
            let divColWinterKitTrue = document.createElement("div");
            divColWinterKitTrue.className = "col-3";
            let divColWinterKitFalse = document.createElement("div");
            divColWinterKitFalse.className = "col-3";
            let radioWinterKitTrue = document.createElement("div");
            radioWinterKitTrue.className = "form-check checkRadio";
            radioWinterKitTrue.innerHTML = '<input class="form-check-input" type="radio" name="winterKitRadio" id="winterKitTrue" >' +
                                          '<label class="form-check-label" for="winterKitTrue">Да</label></div>';
            let radioWinterKitFalse = document.createElement("div");
            radioWinterKitFalse.className = "form-check";
            radioWinterKitFalse.innerHTML = '<input class="form-check-input" type="radio" name="winterKitRadio" id="winterKitFalse" checked>' +
                                          '<label class="form-check-label" for="winterKitFalse">Нет</label></div>';
            divColWinterKitTrue.appendChild(radioWinterKitTrue);
            divColWinterKitFalse.appendChild(radioWinterKitFalse);
            divWinterKitRow.appendChild(divColLabelWinterKit);
            divWinterKitRow.appendChild(divColWinterKitTrue);
            divWinterKitRow.appendChild(divColWinterKitFalse);
            divContainerBody.appendChild(divWinterKitRow);
            
             let divPompRow = document.createElement("div");
            divPompRow.className = "row mt-2";
            let divColLabelPomp = document.createElement("div");
            divColLabelPomp.className = "col-6";
            divColLabelPomp.innerText = "Помпа";
            let divColPompTrue = document.createElement("div");
            divColPompTrue.className = "col-3";
            let divColPompFalse = document.createElement("div");
            divColPompFalse.className = "col-3";
            let radioPompTrue = document.createElement("div");
            radioPompTrue.className = "form-check checkRadio";
            radioPompTrue.innerHTML = '<input class="form-check-input" type="radio" name="pompRadio" id="pompTrue" >' +
                                          '<label class="form-check-label" for="pompTrue">Да</label></div>';
            let radioPompFalse = document.createElement("div");
            radioPompFalse.className = "form-check";
            radioPompFalse.innerHTML = '<input class="form-check-input" type="radio" name="pompRadio" id="pompFalse" checked>' +
                                          '<label class="form-check-label" for="pompFalse">Нет</label></div>';
            divColPompTrue.appendChild(radioPompTrue);
            divColPompFalse.appendChild(radioPompFalse);
            divPompRow.appendChild(divColLabelPomp);
            divPompRow.appendChild(divColPompTrue);
            divPompRow.appendChild(divColPompFalse);
            divContainerBody.appendChild(divPompRow);
            
            
            let conditionerPriceRow = document.createElement("div");
            conditionerPriceRow.className = "row mt-2";
            let divColLabelConditionerPrice = document.createElement("div");
            divColLabelConditionerPrice.className = "col";
            divColLabelConditionerPrice.innerText = "Балансовая стоимость";
            let divColInputConditionerPrice = document.createElement("div");
            divColInputConditionerPrice.className = "col";
            let inputConditionerPrice = document.createElement("input");
            inputConditionerPrice.className = "form-control form-control-sm";
            inputConditionerPrice.type = "number";
            inputConditionerPrice.min = 0.00;
            inputConditionerPrice.max = 9999999.99;
            inputConditionerPrice.step = 0.01;
            inputConditionerPrice.value = 0.00;
            inputConditionerPrice.id = "price";
            divColInputConditionerPrice.appendChild(inputConditionerPrice);
            conditionerPriceRow.appendChild(divColLabelConditionerPrice);
            conditionerPriceRow.appendChild(divColInputConditionerPrice);
            divContainerBody.appendChild(conditionerPriceRow);
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
            case "fax":
                requestLink = "/getfax?faxId=";
                break;
            case "monitors":
                requestLink = "/getmonitor?monitorId=";
                break;
            case "ups":
                requestLink = "/getups?upsId=";
                break;
            case "upsforserver":
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
            case "ats":
                requestLink = "/getats?atsId=";
                break;
            case "conditioner":
                requestLink = "/getconditioner?conditionerId=";
                break;
            case "infomat":
                requestLink = "/getinfomat?infomatId=";
                break;
            case "terminal":
                requestLink = "/getterminal?terminalId=";
                break;
            case "thermoprinter":
                requestLink = "/getthermoprinter?thermoprinterId=";
                break;
            case "display":
                requestLink = "/getdisplay?displayId=";
                break;
            case "swunit":
                requestLink = "/getswunit?swunitId=";
                break;
            case "asuo":
                requestLink = "/getasuo?asuoId=";
                break;
        }
        $.ajax({
            url: requestLink + svtObjId,
            type: 'GET',
            async: false,
            dataType: 'json',
            success: function (callback) {
                $("#modalTitle")[0].innerText =  $("#modalTitle")[0].innerText + ": " + callback.model;
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
                numberRoom = callback.numberRoom;
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
                    dateUpgrade = callback.dateUpgrade;
                } else if(attrib == "fax") {
                    ipAdress = callback.ipAdress;
                } else if (attrib == "scanner") {
                    ipAdress = callback.ipAdress;
                } else if(attrib == "asuo") {
                  
                    switchList = callback.switches;
                    terminalId = callback.terminalId;
                    displayId = callback.displayId;
                    thermoprinterId = callback.thermoprinterId;
                    subDisplayModelId = callback.subDisplayModelId;
                    subDisplayAmount = callback.subDisplayAmount;
                    switchingUnitId = callback.switchingUnitId;
                    
                } else if (attrib == "server") {
                    operationSystemId = callback.operationSystemId;
                    cpuId = callback.cpuId;
                    cpuAmount = callback.cpuAmount;
                    ramId = callback.ramId;
                    hddListId = callback.hddIdList;
                    ipAdress = callback.ipAdress;
                    dateUpgrade = callback.dateUpgrade;
                }else if (attrib == "switch") {
                    portAmount = callback.portAmount;
                    switchHubType = callback.switchHubType;
                } else if (attrib == "router") {
                    portAmount = callback.portAmount;
                } else if (attrib == "ats") {
                    innerConnectionAnalog = callback.innerConnectionAnalog;
                    innerConnectionIp = callback.innerConnectionIp;
                    cityNumberAmount = callback.cityNumberAmount;
                    outerConnectionType = callback.outerConnectionType;
                } else if (attrib == "conditioner") {
                    conditionerTypeSelect = callback.conditionerType;
                    description = callback.description;
                    splitSystem = callback.splitSystem;
                    winterKit = callback.winterKit;
                    havePomp = callback.havePomp;
                    price = callback.price;
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
            case "fax":
                requestLink = "/getfax?faxId=";
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#ipAdress")[0].value = ipAdress;
                break;
            case "monitors":
                requestLink = "/getmonitor?monitorId=";
                $("#numberRoom")[0].value = numberRoom;
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#baseTypeSelect")[0].value = baseType;
                break;
            case "ups":
                requestLink = "/getups?upsId=";
                $("#dateReplaceSelect")[0].value = dateReplaceBattery;
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                break;
            case "upsforserver":
                requestLink = "/getups?upsId=";
                $("#dateReplaceSelect")[0].value = dateReplaceBattery;
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
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
            case "ats":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#innerConnectionAnalog")[0].value = innerConnectionAnalog;
                $("#innerConnectionIp")[0].value = innerConnectionIp;
                $("#cityNumberAmount")[0].value = cityNumberAmount;
                $("#outerConnectionType")[0].value = outerConnectionType;
                break;
             case "conditioner":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#descriptionInput")[0].value = description;
                $("#price")[0].value = price;
                $("#conditionerTypeSelect")[0].value = conditionerTypeSelect;
                if(splitSystem) {
                    $("#splitSystemTrue")[0].checked = true;
                }
                if(winterKit) {
                    $("#winterKitTrue")[0].checked = true;
                }
                if(havePomp) {
                    $("#pompTrue")[0].checked = true;
                }
                break;
                case "infomat":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
               
                break;
            case "asuo":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#subDisplayAmount")[0].value = subDisplayAmount;
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
                     case "asuo":
               
                $("#numberRoom")[0].disabled = true;
                $("#nameFromOneC")[0].disabled = true;
                $("#subDisplayAmount")[0].disabled = true;
            
            break;
                case "ups":
                    $("#dateReplaceSelect")[0].disabled = true;
                    $("#batteryAmount")[0].disabled = true;
                    break;
                 case "upsforserver":
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
                case "ats":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#innerConnectionAnalog")[0].value = innerConnectionAnalog;
                $("#innerConnectionIp")[0].value = innerConnectionIp;
                $("#cityNumberAmount")[0].value = cityNumberAmount;
                $("#outerConnectionType")[0].value = outerConnectionType;
                break;
                case "conditioner":
                $("#nameFromOneC")[0].value = nameFromOneC;
                $("#numberRoom")[0].value = numberRoom;
                $("#descriptionInput")[0].value = description;
                $("#price")[0].value = price;
                $("#conditionerTypeSelect")[0].value = conditionerTypeSelect;
                if(splitSystem) {
                    $("#splitSystemTrue")[0].checked = true;
                }
                if(winterKit) {
                    $("#winterKitTrue")[0].checked = true;
                }
                if(havePomp) {
                    $("#havePompTrue")[0].checked = true;
                }
                break;
            }
        }
    }
    $('#locationSelect').selectize({
        preload: true,
        persist: true,
        valueField: 'id',
        labelField: 'name',
        sortField: 'name',
        searchField: ["id", "name"],
        placeholder: 'выберите район',
        onInitialize: function () {
            let placeType;

            switch (placeAttrib) {
                case "serverroom":
                    placeType = "SERVERROOM";
                    break;

                case "officeequipment":
                    placeType = "OFFICEEQUIPMENT";


                    break;
                default:
                    placeType = "EMPLOYEE";
                    break;
            }

            $.ajax({
                url: '/locplacetype?placeType=' + placeType,
                type: 'GET',
                async: false,
                dataType: 'json',
                error: function (res) {
                    console.log(res);
                },
                success: function (res) {
                    let keys = Object.keys($('#locationSelect')[0].selectize.options);
                    for (let i = 0; i < keys.length; i++) {
                        $('#locationSelect')[0].selectize.removeOption(keys[i]);
                    }
                    res.forEach(model => {
                        $('#locationSelect')[0].selectize.addOption(model);
                        $('#locationSelect')[0].selectize.addItem(model);
                    });
                    if (null != svtObjId) {
                        curLoc = $('#locationSelect')[0].selectize.search(locationId).items[0].id;
                        $('#locationSelect')[0].selectize.setValue($('#locationSelect')[0].selectize.search(locationId).items[0].id);
                    } else {
                        curLoc = $('#locationSelect')[0].selectize.search(0).items[0].id;
                        $('#locationSelect')[0].selectize.setValue($('#locationSelect')[0].selectize.search(0).items[0].id);
                    }
                }
            });

            if (eventReason.indexOf("storage") >= 0) {
                $('#locationSelect')[0].selectize.disable();
            } else {
                $('#locationSelect')[0].selectize.enable();
            }
        },
        onChange: function (value) {
            if (value !== '' && value != curLoc) {
                let placeType = "EMPLOYEE";

                switch (placeAttrib) {
                    case "serverroom":
                        placeType = "SERVERROOM";
                        break;
                    case "officeequipment":
                        placeType = "OFFICEEQUIPMENT";
                        break;
                    default:
                        placeType = "EMPLOYEE";
                        break;
                }
                $.ajax({
                    url: '/deplocplacetype?placeType=' + placeType + '&idLocation=' + $("#locationSelect")[0].selectize.getValue(),
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
                        curDep = $('#departmentSelect')[0].selectize.search(0).items[0].id;
                        $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(0).items[0].id);
                    }
                });

                $.ajax({
                    url: '/placelocdepplacetype?placeType=' + placeType + '&idLocation=' + $("#locationSelect")[0].selectize.getValue() + '&departmentCode=' + $("#departmentSelect")[0].selectize.getValue(),
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
                        $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                    }

                });


                curLoc = value;
            }

        }
    });

    if (eventReason.indexOf("storage") >= 0) {
        $('#locationSelect')[0].selectize.disable();
    } else {
        $('#locationSelect')[0].selectize.enable();
    }
    
    $('#departmentSelect').selectize({
        preload: true,
        persist: true,
        valueField: 'code',
        labelField: 'name',
        sortField: 'name',
        searchField: ["code", "name"],
        placeholder: 'выберите отдел',
        onInitialize: function () {
            
            
             let placeType = "EMPLOYEE";

                switch (placeAttrib) {
                    case "serverroom":
                        placeType = "SERVERROOM";
                        break;
                    case "officeequipment":
                        placeType = "OFFICEEQUIPMENT";
                        break;
                    default:
                        placeType = "EMPLOYEE";
                        break;
                }
                $.ajax({
                    url: '/deplocplacetype?placeType=' + placeType + '&idLocation=' + $("#locationSelect")[0].selectize.getValue(),
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
                        if(null != svtObjId) {
                            curDep = $('#departmentSelect')[0].selectize.search(codeDepartment).items[0].id;
                        $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(codeDepartment).items[0].id);
                        } else {
                        curDep = $('#departmentSelect')[0].selectize.search(0).items[0].id;
                        $('#departmentSelect')[0].selectize.setValue($('#departmentSelect')[0].selectize.search(0).items[0].id);
                    }
                    }
                });

        },
        onChange: function (value) {
            if ((value !== '' && value != curDep) || (value !== '' && value != curDep && $('#locationSelect')[0].selectize.getValue() != curLoc)) {
            let urlReq = "placebydepandloc";
            let placetype = "EMPLOYEE";

            switch (placeAttrib) {
                case 'serverroom':
                    urlReq = "placeserverbydepandloc";
                    placetype = "SERVERROOM";
                    break;
                case 'officeequipment':
                    urlReq = "placeserverbydepandloc";
                    placetype = "OFFICEEQUIPMENT";
                    break;
                default:
                    urlReq = "placebydepandloc";
                    placetype = "EMPLOYEE";
                    break;
            }
            $.ajax({
                url: '/' + urlReq + '?locationId=' + $('#locationSelect')[0].selectize.getValue() + '&departmentCode=' + $('#departmentSelect')[0].selectize.getValue() + '&placetype=' + placetype,
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

                    $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                }
            });
        }
    }

    });
        if (eventReason.indexOf("storage") >= 0) {
            $('#departmentSelect')[0].selectize.disable();
        } else {
            $('#departmentSelect')[0].selectize.enable();
        }
    

    
    $('#placeSelect').selectize({
        valueField: 'placeId',
        labelField: 'username',
        searchField: ["placeId", "username"],
        persist: true,
        sortField: 'username',
        placeholder: 'выберите рабочее место',
        onInitialize: function () {
            let urlReq = "placebydepandloc";
            let placetype = "EMPLOYEE";

            switch (placeAttrib) {
                case 'serverroom':
                    urlReq = "placeserverbydepandloc";
                    placetype = "SERVERROOM";
                    break;
                case 'officeequipment':
                    urlReq = "placeserverbydepandloc";
                    placetype = "OFFICEEQUIPMENT";
                    break;
                default:
                    urlReq = "placebydepandloc";
                    placetype = "EMPLOYEE";
                    break;
            }

            $.ajax({
                url: '/' + urlReq + '?locationId=' + $('#locationSelect')[0].selectize.getValue() + '&departmentCode=' + $('#departmentSelect')[0].selectize.getValue() + '&placetype=' + placetype,
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
                                    $('#placeSelect')[0].selectize.addOption(callback);
                                }
                            });
                        }
                    
                    if (null != svtObjId) {
                        $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(idPlace).items[0].id);
                    } else {
                        $('#placeSelect')[0].selectize.setValue($('#placeSelect')[0].selectize.search(0).items[0].id);
                    }
                }
            });
      
        },
    });

  
        if (eventReason.indexOf("storage") >= 0) {
            $('#placeSelect')[0].selectize.disable();
        } else {
            $('#placeSelect')[0].selectize.enable();
        }
    
    
    if(attrib != "asuo") {
        
    $('#modelSelect').selectize({
        preload: true,
        persist: true,
        placeholder: "выберите модель",
        valueField: 'id',
        labelField: 'model',
        sortField: 'model',
        searchField: ["id", "model"],
        onInitialize: function () {
            let requestLink;
            switch (attrib) {
                case "phones":
                    requestLink = "/modphones";
                    break;
                case "fax":
                    requestLink = "/modfax";
                    break;
                case "monitors":
                    requestLink = "/modmonitors";
                    break;
                case "ups":
                    requestLink = "/modups";
                    break;
                case "upsforserver":
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
                case "ats":
                    requestLink = "/modats";
                    break;
                case "conditioner":
                    requestLink = "/modconditioner";
                    break;
                case "infomat":
                    requestLink = "/modinfomat";
                    break;
                case "terminal":
                    requestLink = "/modterminal";
                    break;
                case "thermoprinter":
                    requestLink = "/modthermoprinter";
                    break;
                case "display":
                    requestLink = "/moddisplay";
                    break;
                case "swunit":
                    requestLink = "/modswunit";
                    break;
            }
            $.ajax({
                url: requestLink,
                type: 'GET',
                async: false,
                dataType: 'json',
                success: function(res) {
                    
                            res.forEach(model => {
                                $('#modelSelect')[0].selectize.addOption(model);
                                $('#modelSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                            $('#modelSelect')[0].selectize.setValue($('#modelSelect')[0].selectize.search(modelId).items[0].id);
                        } else {
                            $('#modelSelect')[0].selectize.setValue($('#modelSelect')[0].selectize.search("не указано").items[0].id);
                        }
                }
            });
        },
        
    });
        if (eventReason.indexOf("storage") >= 0) {
            $('#modelSelect')[0].selectize.disable();
        } else {
            $('#modelSelect')[0].selectize.enable();
        }
}
    switch (attrib) {
        case "asuo":
            let displayLink = "/getalldisplay";
            let terminalLink = "/getallterminal";
            let thermoprinterLink = "/getallthermoprinter";
            let swunitLink = "/getallswunit";
            let switchLink = "/getallswitch";
            if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                displayLink = "/getdisplays";
                terminalLink = "/getterminals";
                thermoprinterLink = "/getthermoprinters";
                swunitLink = "/getswunits";
                switchLink = "/getswitches";
                
                
                switchListId = new Array();
                switchList.forEach(item => {
                switchListId.push(item.id);
            });
                
            }

            $("#displaySelect").selectize({
                placeholder: "Выберите из списка",
                preload: true,
                valueField: 'id',
                labelField: "model",
                searchField: ["id", "model"],
                load: function (query, callback) {
                    $.ajax({
                        url: displayLink,
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: callback,
                        success: callback
                    });
                       if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#displaySelect')[0].selectize.setValue($('#displaySelect')[0].selectize.search(displayId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#displaySelect')[0].selectize.disable();
                        } else {
                            $('#displaySelect')[0].selectize.enable();
                        }
                    }
                },
                render: {
                    option: function (item, escape) {
                        return '<div style="margin-left:2px; padding-left:10px; margin-right:2px;padding-right:10px; border-radius:5px;">'
                                + '<strong>'
                                + escape(item.model) + ' - '
                                + '</strong>'
                                + ' инв №: '
                                + escape(item.serialNumber) + ', сер №: '
                                + escape(item.inventaryNumber)
                                + '</div>';
                    },
                    item: function (item, escape) {
                        return '<div>'
                                + escape(item.model) + ', инв №:'
                                + escape(item.serialNumber) + ', сер №:'
                                + escape(item.inventaryNumber)
                                + '</div>';
                    }
                }
    
            });
       
       if ($('#displaySelect')[0].selectize.getValue() == "" && $('#displaySelect')[0].selectize.order > 0) {
                $('#displaySelect')[0].selectize.setValue($('#displaySelect')[0].selectize.search(displayId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#displaySelect')[0].selectize.disable();
                } else {
                    $('#displaySelect')[0].selectize.enable();
                }
            }
            
            $("#terminalSelect").selectize({
                placeholder: "Выберите из списка",
                preload: true,
                valueField: 'id',
                labelField: "model",
                searchField: ["id", "model"],
                load: function (query, callback) {
                    $.ajax({
                        url: terminalLink,
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: callback,
                        success: callback
                    });
                         if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#terminalSelect')[0].selectize.setValue($('#terminalSelect')[0].selectize.search(terminalId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#terminalSelect')[0].selectize.disable();
                        } else {
                            $('#terminalSelect')[0].selectize.enable();
                        }
                    }
                },
                render: {
                    option: function (item, escape) {
                        return '<div style="margin-left:2px; padding-left:10px; margin-right:2px;padding-right:10px; border-radius:5px;">'
                                + '<strong>'
                                + escape(item.model) + ' - '
                                + '</strong>'
                                + ' инв №: '
                                + escape(item.serialNumber) + ', сер №: '
                                + escape(item.inventaryNumber)
                                + '</div>';
                    },
                    item: function (item, escape) {
                        return '<div>'
                                + escape(item.model) + ', инв №:'
                                + escape(item.serialNumber) + ', сер №:'
                                + escape(item.inventaryNumber)
                                + '</div>';
                    }
                }
            });
            
            if ($('#terminalSelect')[0].selectize.getValue() == "" && $('#terminalSelect')[0].selectize.order > 0) {
                $('#terminalSelect')[0].selectize.setValue($('#terminalSelect')[0].selectize.search(terminalId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#terminalSelect')[0].selectize.disable();
                } else {
                    $('#terminalSelect')[0].selectize.enable();
                }
            }
            
            $("#thermoprinterSelect").selectize({
                placeholder: "Выберите из списка",
                preload: true,
                valueField: 'id',
                labelField: "model",
                searchField: ["id", "model"],
                load: function (query, callback) {
                    $.ajax({
                        url: thermoprinterLink,
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: callback,
                        success: callback
                    });
                          if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#thermoprinterSelect')[0].selectize.setValue($('#thermoprinterSelect')[0].selectize.search(thermoprinterId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#thermoprinterSelect')[0].selectize.disable();
                        } else {
                            $('#thermoprinterSelect')[0].selectize.enable();
                        }
                    }
                },
                render: {
                    option: function (item, escape) {
                        return '<div style="margin-left:2px; padding-left:10px; margin-right:2px;padding-right:10px; border-radius:5px;">'
                                + '<strong>'
                                + escape(item.model) + ' - '
                                + '</strong>'
                                + ' инв №: '
                                + escape(item.serialNumber) + ', сер №: '
                                + escape(item.inventaryNumber)
                                + '</div>';
                    },
                    item: function (item, escape) {
                        return '<div>'
                                + escape(item.model) + ', инв №:'
                                + escape(item.serialNumber) + ', сер №:'
                                + escape(item.inventaryNumber)
                                + '</div>';
                    }
                }
            });
            
            
             if ($('#thermoprinterSelect')[0].selectize.getValue() == "" && $('#thermoprinterSelect')[0].selectize.order > 0) {
                $('#thermoprinterSelect')[0].selectize.setValue($('#thermoprinterSelect')[0].selectize.search(thermoprinterId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#thermoprinterSelect')[0].selectize.disable();
                } else {
                    $('#thermoprinterSelect')[0].selectize.enable();
                }
            }
            $("#switchingUnitSelect").selectize({
                placeholder: "Выберите из списка",
                preload: true,
                valueField: 'id',
                labelField: "model",
                searchField: ["id", "model"],
                load: function (query, callback) {
                    $.ajax({
                        url: swunitLink,
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: callback,
                        success: callback
                    });
                            if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#switchingUnitSelect')[0].selectize.setValue($('#switchingUnitSelect')[0].selectize.search(switchingUnitId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#switchingUnitSelect')[0].selectize.disable();
                        } else {
                            $('#switchingUnitSelect')[0].selectize.enable();
                        }
                    }
                },
                render: {
                    option: function (item, escape) {
                        return '<div style="margin-left:2px; padding-left:10px; margin-right:2px;padding-right:10px; border-radius:5px;">'
                                + '<strong>'
                                + escape(item.model) + ' - '
                                + '</strong>'
                                + ' инв №: '
                                + escape(item.serialNumber) + ', сер №: '
                                + escape(item.inventaryNumber)
                                + '</div>';
                    },
                    item: function (item, escape) {
                        return '<div>'
                                + escape(item.model) + ', инв №:'
                                + escape(item.serialNumber) + ', сер №:'
                                + escape(item.inventaryNumber)
                                + '</div>';
                    }
                }
            });
            if ($('#switchingUnitSelect')[0].selectize.getValue() == "" && $('#switchingUnitSelect')[0].selectize.order > 0) {
                $('#switchingUnitSelect')[0].selectize.setValue($('#switchingUnitSelect')[0].selectize.search(switchingUnitId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#switchingUnitSelect')[0].selectize.disable();
                } else {
                    $('#switchingUnitSelect')[0].selectize.enable();
                }
            }
            $("#subDisplaySelect").selectize({
                placeholder: "Выберите из списка",
                preload: true,
                valueField: 'id',
                labelField: "model",
                searchField: ["id", "model"],
                load: function (query, callback) {
                    $.ajax({
                        url: "/modsubdisplay",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: callback,
                        success: callback
                    });
                         if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        $('#subDisplaySelect')[0].selectize.setValue($('#subDisplaySelect')[0].selectize.search(subDisplayModelId).items[0].id);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#subDisplaySelect')[0].selectize.disable();
                        } else {
                            $('#subDisplaySelect')[0].selectize.enable();
                        }
                    }
                }
                
            });
            if ($('#subDisplaySelect')[0].selectize.getValue() == "" && $('#subDisplaySelect')[0].selectize.order > 0) {
                $('#subDisplaySelect')[0].selectize.setValue($('#subDisplaySelect')[0].selectize.search(subDisplayModelId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#subDisplaySelect')[0].selectize.disable();
                } else {
                    $('#subDisplaySelect')[0].selectize.enable();
                }
            }
            $("#switchSelect").selectize({
                plugins: ["remove_button"],
                delimiter: ",",
                persist: true,
                maxItems: null,
                placeholder: "Выберите из списка",
                preload: true,
                valueField: 'id',
                labelField: "model",
                searchField: ["id", "model"],
                load: function (query, callback) {
                    $.ajax({
                        url: switchLink,
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: callback,
                        success: callback
                    });
                  if (eventReason.indexOf("element") >= 0 || eventReason.indexOf("storage") >= 0) {
                        forSwitchArray = new Array();
                        for (let r = 0; r < switchListId.length; r++) {
                            forSwitchArray.push($('#switchSelect')[0].selectize.search(switchListId[r]).items[0].id);
                        }
                        $('#switchSelect')[0].selectize.setValue(forSwitchArray);
                        if (eventReason.indexOf("storage") >= 0) {
                            $('#switchSelect')[0].selectize.disable();
                        } else {
                            $('#switchSelect')[0].selectize.enable();
                        }
                    }
                },
                render: {
                    option: function (item, escape) {
                        return '<div style="margin-left:3px; padding-left:10px; margin-right:3px;padding-right:10px; border-radius:5px;">'
                                + '<strong>'
                                + escape(item.model) + '- '
                                + '</strong>' + ' инв: ' +
                                + escape(item.inventaryNumber) + ', сер: '
                                + escape(item.serialNumber) 
                                + '</div>';
                    },
                    item: function (item, escape) {
                        return '<div>'
                                + escape(item.model) + ', инв:'
                                + escape(item.inventaryNumber) + ', сер: '
                                + escape(item.serialNumber)
                                + '</div>';
                    }
                }
            });
            
            if ($('#switchSelect')[0].selectize.getValue() == "" && $('#switchSelect')[0].selectize.order > 0) {
                forSwitchArray = new Array();
                for (let r = 0; r < switchListId.length; r++) {
                    forSwitchArray.push($('#switchSelect')[0].selectize.search(switchListId[r]).items[0].id);
                }
                if (eventReason.indexOf("storage") >= 0) {
                    $('#switchSelect')[0].selectize.disable();
                } else {
                    $('#switchSelect')[0].selectize.enable();
                }
            }
            
            break;
            
        case "ups":
//            $('#batteryTypeSelect').selectize({
//                persist: true,
//                valueField: 'id',
//                sortField: 'type',
//                labelField: 'type',
//                searchField: ["id", "type"],
//                onInitialize: function () {
//                    $.ajax({
//                        url: "/typebatups",
//                        type: 'GET',
//                        async: false,
//                        dataType: 'json',
//                        error: function(res) {
//                            console.log(res);
//                        },
//                        success: function(res) {
//                          
//                            res.forEach(model => {
//                                $('#batteryTypeSelect')[0].selectize.addOption(model);
//                                $('#batteryTypeSelect')[0].selectize.addItem(model);
//                            });
//                             if (null != svtObjId) {
//                            $('#batteryTypeSelect')[0].selectize.setValue($('#batteryTypeSelect')[0].selectize.search(batteryTypeId).items[0].id);
//                        } else {
//                            $('#batteryTypeSelect')[0].selectize.setValue($('#batteryTypeSelect')[0].selectize.search(0).items[0].id);
//                        }
//                        }
//                    });
//
//                        if (eventReason.indexOf("storage") >= 0) {
//                            $('#batteryTypeSelect')[0].selectize.disable();
//                        } else {
//                            $('#batteryTypeSelect')[0].selectize.enable();
//                        }
//                }
//            });
     
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
             if (eventReason.indexOf("storage") >= 0) {
                $("#nameFromOneC")[0].disabled = true;
                $("#numberRoom")[0].disabled = true;
            } else {
                $("#nameFromOneC")[0].disabled = false;
                $("#numberRoom")[0].disabled = false;
            }
            break;
            case "upsforserver":
            $('#batteryTypeSelect').selectize({
                persist: true,
                valueField: 'id',
                sortField: 'type',
                labelField: 'type',
                searchField: ["id", "type"],
                onInitialize: function () {
                    $.ajax({
                        url: "/typebatups",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                            res.forEach(model => {
                                $('#batteryTypeSelect')[0].selectize.addOption(model);
                                $('#batteryTypeSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                            $('#batteryTypeSelect')[0].selectize.setValue($('#batteryTypeSelect')[0].selectize.search(batteryTypeId).items[0].id);
                        } else {
                            $('#batteryTypeSelect')[0].selectize.setValue($('#batteryTypeSelect')[0].selectize.search(0).items[0].id);
                        }
                        }
                    });

                        if (eventReason.indexOf("storage") >= 0) {
                            $('#batteryTypeSelect')[0].selectize.disable();
                        } else {
                            $('#batteryTypeSelect')[0].selectize.enable();
                        }
                }
            });

            let dateReplaceSelectUpsForServer = document.querySelector('#dateReplaceSelect');
            option = document.createElement('option');
            option.innerHTML = "Нет";
            option.value = "";
            dateReplaceSelectUpsForServer.appendChild(option);
            for (i = currentYear; i >= 2000; i--) {
                option = document.createElement('option');
                option.value = i;
                option.innerHTML = i;
                dateReplaceSelectUpsForServer.appendChild(option);
            }
            if (dateReplaceSelectUpsForServer > 0) {
                dateReplaceSelect.value = dateReplaceSelectUpsForServer;
            }
             if (eventReason.indexOf("storage") >= 0) {
                $("#nameFromOneC")[0].disabled = true;
                $("#numberRoom")[0].disabled = true;
            } else {
                $("#nameFromOneC")[0].disabled = false;
                $("#numberRoom")[0].disabled = false;
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
                persist: true,
                maxItems: null,
                placeholder: "Выберите из списка",
                preload: true,
                valueField: 'id',
                labelField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modos",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                              res.forEach(model => {
                                $('#osSelect')[0].selectize.addOption(model);
                                $('#osSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                 
                                if (operationSystemId.length == 0) {
                                    $('#osSelect')[0].selectize.setValue($('#osSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    forOs = new Array();
                                    for (let r = 0; r < operationSystemId.length; r++) {
                                        forOs.push($('#osSelect')[0].selectize.search(operationSystemId[r]).items[0].id);
                                    }
                                    $('#osSelect')[0].selectize.setValue(forOs);
                                }
                            } else {
                                $('#osSelect')[0].selectize.setValue($('#osSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

                }});
           
            
              
             
                if (eventReason.indexOf("storage") >= 0) {
                    $('#osSelect')[0].selectize.disable();
                } else {
                    $('#osSelect')[0].selectize.enable();
                }
                
                
            
            $("#motherboardSelect").selectize({
                preload: true,
                persist: true,
                placeholder: "выберите модель мат.платы",
                sortField: 'model',
                valueField: 'id',
                labelField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modmotherboard",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                            res.forEach(model => {
                                $('#motherboardSelect')[0].selectize.addOption(model);
                                $('#motherboardSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                if (null == motherboardId) {
                                    $('#motherboardSelect')[0].selectize.setValue($('#motherboardSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#motherboardSelect')[0].selectize.setValue($('#motherboardSelect')[0].selectize.search(motherboardId).items[0].id);
                                }
                            } else {
                                $('#motherboardSelect')[0].selectize.setValue($('#motherboardSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });


                }});
            if ($('#motherboardSelect')[0].selectize.getValue() == "" && $('#motherboardSelect')[0].selectize.order > 0) {
               // $('#motherboardSelect')[0].selectize.setValue($('#motherboardSelect')[0].selectize.search(motherboardId).items[0].id);
                if (eventReason.indexOf("storage") >= 0) {
                    $('#motherboardSelect')[0].selectize.disable();
                } else {
                    $('#motherboardSelect')[0].selectize.enable();
                }
            }
            $("#cpuSelect").selectize({
                preload: true,
                persist: true,
                placeholder: "выберите модель процессора",
                sortField: 'model',
                valueField: 'id',
                labelField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modcpu",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                            res.forEach(model => {
                                $('#cpuSelect')[0].selectize.addOption(model);
                                $('#cpuSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                if (null == cpuId) {
                                    $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search(cpuId).items[0].id);
                                }
                            } else {
                                $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                        
                    });

                }});

                if (eventReason.indexOf("storage") >= 0) {
                    $('#cpuSelect')[0].selectize.disable();
                } else {
                    $('#cpuSelect')[0].selectize.enable();
                }
            
            $("#ramSelect").selectize({
                preload: true,
                persist: true,
                placeholder: "выберите оперативную память",
                sortField: 'model',
                valueField: 'id',
                labelField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modram",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                            res.forEach(model => {
                                $('#ramSelect')[0].selectize.addOption(model);
                                $('#ramSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                if (null == ramId) {
                                    $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search(ramId).items[0].id);
                                }
                            } else {
                                $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

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

                if (eventReason.indexOf("storage") >= 0) {
                    $('#ramSelect')[0].selectize.disable();
                } else {
                    $('#ramSelect')[0].selectize.enable();
                }
            
            
            $("#hddSelect").selectize({
                plugins: ["remove_button"],
                delimiter: ",",
                persist: true,
                maxItems: null,
                placeholder: "Выберите из списка",
                preload: true,
                sortField: 'model',
                valueField: 'id',
                labelField: "model",
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modhdd",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                              res.forEach(model => {
                                $('#hddSelect')[0].selectize.addOption(model);
                                $('#hddSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                 
                                if (hddListId.length == 0) {
                                    $('#hddSelect')[0].selectize.setValue($('#hddSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    forHdd = new Array();
                                    for (let r = 0; r < hddListId.length; r++) {
                                        forHdd.push($('#hddSelect')[0].selectize.search(hddListId[r]).items[0].id);
                                    }
                                    $('#hddSelect')[0].selectize.setValue(forHdd);
                                }
                            } else {
                                $('#hddSelect')[0].selectize.setValue($('#hddSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

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
         
          
                if (eventReason.indexOf("storage") >= 0) {
                    $('#hddSelect')[0].selectize.disable();
                } else {
                    $('#hddSelect')[0].selectize.enable();
                }
            
            
            
            
            $("#videoCardSelect").selectize({
                preload: true,
                persist: true,
                valueField: 'id',
                labelField: 'model',
                sortField: 'model',
                placeholder: "выберите видеокарту",
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modvideo",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                             res.forEach(model => {
                                $('#videoCardSelect')[0].selectize.addOption(model);
                                $('#videoCardSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                if (null == videoCardId) {
                                    $('#videoCardSelect')[0].selectize.setValue($('#videoCardSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#videoCardSelect')[0].selectize.setValue($('#videoCardSelect')[0].selectize.search(videoCardId).items[0].id);
                                }
                            } else {
                                $('#videoCardSelect')[0].selectize.setValue($('#videoCardSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

                }});
                if (eventReason.indexOf("storage") >= 0) {
                    $('#videoCardSelect')[0].selectize.disable();
                } else {
                    $('#videoCardSelect')[0].selectize.enable();
                }
            
            $("#soundCardSelect").selectize({
                preload: true,
                persist: true,
                placeholder: "выберите звуковую карту",
                valueField: 'id',
                labelField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modscard",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                               res.forEach(model => {
                                $('#soundCardSelect')[0].selectize.addOption(model);
                                $('#soundCardSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                if (null == soundCardId) {
                                    $('#soundCardSelect')[0].selectize.setValue($('#soundCardSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#soundCardSelect')[0].selectize.setValue($('#soundCardSelect')[0].selectize.search(soundCardId).items[0].id);
                                }
                            } else {
                                $('#soundCardSelect')[0].selectize.setValue($('#soundCardSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

                }});

   
                if (eventReason.indexOf("storage") >= 0) {
                    $('#soundCardSelect')[0].selectize.disable();
                } else {
                    $('#soundCardSelect')[0].selectize.enable();
                }
            
            $("#lanCardSelect").selectize({
                preload: true,
                persist: true,
                placeholder: "выберите сетевую карту",
                valueField: 'id',
                labelField: 'model',
                sortField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modlcard",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                            res.forEach(model => {
                                $('#lanCardSelect')[0].selectize.addOption(model);
                                $('#lanCardSelect')[0].selectize.addItem(model);
                            });
                            if (null != svtObjId) {
                                if (null == lanCardId) {
                                    $('#lanCardSelect')[0].selectize.setValue($('#lanCardSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#lanCardSelect')[0].selectize.setValue($('#lanCardSelect')[0].selectize.search(lanCardId).items[0].id);
                                }
                            } else {
                                $('#lanCardSelect')[0].selectize.setValue($('#lanCardSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });


                }});

                if (eventReason.indexOf("storage") >= 0) {
                    $('#lanCardSelect')[0].selectize.disable();
                } else {
                    $('#lanCardSelect')[0].selectize.enable();
                }
            
            
            $("#cdDriveSelect").selectize({
                preload: true,
                persist: true,
                placeholder: "выберите привод",
                valueField: 'id',
                labelField: 'model',
                sortField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modcddrive",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                            res.forEach(model => {
                                $('#cdDriveSelect')[0].selectize.addOption(model);
                                $('#cdDriveSelect')[0].selectize.addItem(model);
                            });
                            if (null != svtObjId) {
                                if (null == cdDriveId) {
                                    $('#cdDriveSelect')[0].selectize.setValue($('#cdDriveSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#cdDriveSelect')[0].selectize.setValue($('#cdDriveSelect')[0].selectize.search(cdDriveId).items[0].id);
                                }
                            } else {
                                $('#cdDriveSelect')[0].selectize.setValue($('#cdDriveSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

                }});

                if (eventReason.indexOf("storage") >= 0) {
                    $('#cdDriveSelect')[0].selectize.disable();
                } else {
                    $('#cdDriveSelect')[0].selectize.enable();
                }
            
            $("#keyboardSelect").selectize({
                preload: true,
                persist: true,
                placeholder: "выберите клавиатуру",
                valueField: 'id',
                labelField: 'model',
                sortField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modkeyboard",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                            res.forEach(model => {
                                $('#keyboardSelect')[0].selectize.addOption(model);
                                $('#keyboardSelect')[0].selectize.addItem(model);
                            });
                            if (null != svtObjId) {
                                if (null == keyboardId) {
                                    $('#keyboardSelect')[0].selectize.setValue($('#keyboardSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#keyboardSelect')[0].selectize.setValue($('#keyboardSelect')[0].selectize.search(keyboardId).items[0].id);
                                }
                            } else {
                                $('#keyboardSelect')[0].selectize.setValue($('#keyboardSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

                }});

                if (eventReason.indexOf("storage") >= 0) {
                    $('#keyboardSelect')[0].selectize.disable();
                } else {
                    $('#keyboardSelect')[0].selectize.enable();
                }
            
            $("#mouseSelect").selectize({
                preload: true,
                persist: true,
                placeholder: "выберите мышь",
                valueField: 'id',
                labelField: 'model',
                sortField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modmouse",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                               res.forEach(model => {
                                $('#mouseSelect')[0].selectize.addOption(model);
                                $('#mouseSelect')[0].selectize.addItem(model);
                            });
                            if (null != svtObjId) {
                                if (null == mouseId) {
                                    $('#mouseSelect')[0].selectize.setValue($('#mouseSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#mouseSelect')[0].selectize.setValue($('#mouseSelect')[0].selectize.search(mouseId).items[0].id);
                                }
                            } else {
                                $('#mouseSelect')[0].selectize.setValue($('#mouseSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

                }});

                if (eventReason.indexOf("storage") >= 0) {
                    $('#mouseSelect')[0].selectize.disable();
                } else {
                    $('#mouseSelect')[0].selectize.enable();
                }
            
            $("#speakersSelect").selectize({
                preload: true,
                persist: true,
                placeholder: "выберите колонки",
                sortField: 'model',
                valueField: 'id',
                labelField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modspeakers",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                               res.forEach(model => {
                                $('#speakersSelect')[0].selectize.addOption(model);
                                $('#speakersSelect')[0].selectize.addItem(model);
                            });
                            if (null != svtObjId) {
                                if (null == speakersId) {
                                    $('#speakersSelect')[0].selectize.setValue($('#speakersSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#speakersSelect')[0].selectize.setValue($('#speakersSelect')[0].selectize.search(speakersId).items[0].id);
                                }
                            } else {
                                $('#speakersSelect')[0].selectize.setValue($('#speakersSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

                }});

                if (eventReason.indexOf("storage") >= 0) {
                    $('#speakersSelect')[0].selectize.disable();
                } else {
                    $('#speakersSelect')[0].selectize.enable();
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
                persist: true,
                maxItems: null,
                placeholder: "Выберите из списка",
                preload: true,
                valueField: 'id',
                labelField: 'model',
                sortField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modos",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                              res.forEach(model => {
                                $('#osSelect')[0].selectize.addOption(model);
                                $('#osSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                 
                                if (operationSystemId.length == 0) {
                                    $('#osSelect')[0].selectize.setValue($('#osSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    forOs = new Array();
                                    for (let r = 0; r < operationSystemId.length; r++) {
                                        forOs.push($('#osSelect')[0].selectize.search(operationSystemId[r]).items[0].id);
                                    }
                                    $('#osSelect')[0].selectize.setValue(forOs);
                                }
                            } else {
                                $('#osSelect')[0].selectize.setValue($('#osSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

                }});
           
                
                if (eventReason.indexOf("storage") >= 0) {
                    $('#osSelect')[0].selectize.disable();
                } else {
                    $('#osSelect')[0].selectize.enable();
                }
            
            
            
            $("#cpuSelect").selectize({
                preload: true,
                persist: true,
                placehder: "выберите процессор",
                sortField: 'model',
                valueField: 'id',
                labelField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                  $.ajax({
                        url: "/modcpu",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                            res.forEach(model => {
                                $('#cpuSelect')[0].selectize.addOption(model);
                                $('#cpuSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                if (null == cpuId) {
                                    $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search(cpuId).items[0].id);
                                }
                            } else {
                                $('#cpuSelect')[0].selectize.setValue($('#cpuSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                        
                    });
                }});
            
                if (eventReason.indexOf("storage") >= 0) {
                    $('#cpuSelect')[0].selectize.disable();
                } else {
                    $('#cpuSelect')[0].selectize.enable();
                }
            
            $("#ramSelect").selectize({
                preload: true,
                persist: true,
                placeholder: "выберите оперативную память",
                valueField: 'id',
                labelField: 'model',
                sortField: 'model',
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modram",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                            res.forEach(model => {
                                $('#ramSelect')[0].selectize.addOption(model);
                                $('#ramSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                if (null == ramId) {
                                    $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search(ramId).items[0].id);
                                }
                            } else {
                                $('#ramSelect')[0].selectize.setValue($('#ramSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

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
           
               
                if (eventReason.indexOf("storage") >= 0) {
                    $('#ramSelect')[0].selectize.disable();
                } else {
                    $('#ramSelect')[0].selectize.enable();
                }
            
            
            $("#hddSelect").selectize({
                plugins: ["remove_button"],
                delimiter: ",",
                persist: true,
                maxItems: null,
                sortField: 'model',
                placeholder: "Выберите из списка",
                preload: true,
                valueField: 'id',
                labelField: "model",
                searchField: ["id", "model"],
                onInitialize: function () {
                    $.ajax({
                        url: "/modhdd",
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        error: function(res) {
                            console.log(res);
                        },
                        success: function(res) {
                              res.forEach(model => {
                                $('#hddSelect')[0].selectize.addOption(model);
                                $('#hddSelect')[0].selectize.addItem(model);
                            });
                             if (null != svtObjId) {
                                 
                                if (hddListId.length == 0) {
                                    $('#hddSelect')[0].selectize.setValue($('#hddSelect')[0].selectize.search("не указано").items[0].id);
                                } else {
                                    forHdd = new Array();
                                    for (let r = 0; r < hddListId.length; r++) {
                                        forHdd.push($('#hddSelect')[0].selectize.search(hddListId[r]).items[0].id);
                                    }
                                    $('#hddSelect')[0].selectize.setValue(forHdd);
                                }
                            } else {
                                $('#hddSelect')[0].selectize.setValue($('#hddSelect')[0].selectize.search("не указано").items[0].id);
                            }
                        }
                    });

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
          
              
                if (eventReason.indexOf("storage") >= 0) {
                    $('#hddSelect')[0].selectize.disable();
                } else {
                    $('#hddSelect')[0].selectize.enable();
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
        case "ats":
            if (eventReason.indexOf("storage") >= 0) {
                $("#numberRoom")[0].disabled = true;
                $("#cityNumberAmount")[0].disabled = true;
                $("#nameFromOneC")[0].disabled = true;
                $("#innerConnectionIp")[0].disabled = true;
                $("#innerConnectionAnalog")[0].disabled = true;
                $("#outerConnectionType")[0].disabled = true;
            } else {
                $("#numberRoom")[0].disabled = false;
                $("#cityNumberAmount")[0].disabled = false;
                $("#nameFromOneC")[0].disabled = false;
                $("#innerConnectionIp")[0].disabled = false;
                $("#innerConnectionAnalog")[0].disabled = false;
                $("#outerConnectionType")[0].disabled = false;
            }
            break;
        case "conditioner":
            if (eventReason.indexOf("storage") >= 0) {
                $("#numberRoom")[0].disabled = true;
                $("#conditionerTypeSelect")[0].disabled = true;
                $("#nameFromOneC")[0].disabled = true;
                $("#splitSystemTrue")[0].disabled = true;
                $("#splitSystemFalse")[0].disabled = true;
                $("#price")[0].disabled = true;
                $("#descriptionInput")[0].disabled = true;
                $("#winterKitTrue")[0].disabled = true;
                $("#winterKitFalse")[0].disabled = true;
                $("#pompTrue")[0].disabled = true;
                $("#pompFalse")[0].disabled = true;
            } else {
                $("#numberRoom")[0].disabled = false;
                $("#conditionerTypeSelect")[0].disabled = false;
                $("#nameFromOneC")[0].disabled = false;
                $("#splitSystemTrue")[0].disabled = false;
                $("#splitSystemFalse")[0].disabled = false;
                $("#price")[0].disabled = false;
                $("#descriptionInput")[0].disabled = false;
                $("#winterKitTrue")[0].disabled = false;
                $("#winterKitFalse")[0].disabled = false;
                $("#pompTrue")[0].disabled = false;
                $("#pompFalse")[0].disabled = false;
            }
            break;
            case "infomat":
            if (eventReason.indexOf("storage") >= 0) {
                $("#numberRoom")[0].disabled = true;
                $("#nameFromOneC")[0].disabled = true;
            } else {
                $("#numberRoom")[0].disabled = false;
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
         $('#btnSave')[0].addEventListener('click', handleClickUpdateBtn);
        $("#containerContent").append('<div class="row mt-3"><div class="col"><button class="btn btn-primary btn-sm" id="repairBtn"  data-bs-toggle="modal" data-bs-target="#repairModal">' + 
                '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-wrench" viewBox="0 0 16 16">' +
                  '<path d="M.102 2.223A3.004 3.004 0 0 0 3.78 5.897l6.341 6.252A3.003 3.003 0 0 0 13 16a3 3 0 1 0-.851-5.878L5.897 3.781A3.004 3.004 0 0 0 2.223.1l2.141 2.142L4 4l-1.757.364zm13.37 9.019.528.026.287.445.445.287.026.529L15 13l-.242.471-.026.529-.445.287-.287.445-.529.026L13 15l-.471-.242-.529-.026-.287-.445-.445-.287-.026-.529L11 13l.242-.471.026-.529.445-.287.287-.445.529-.026L13 11z"/>' +
                    '</svg>' +
                ' Ремонт</button>' + " " + '<button class="btn btn-primary btn-sm" id="transferBtn"  data-bs-toggle="modal" data-bs-target="#transferModal">Перемещения</button></div><div class="col"></div></div>');
          $("#repairBtn")[0].addEventListener("click", function () {
           
            modalRepairContentLoad(svtObjId, document.querySelector('#modelSelect').innerText);
            
            modalRepair.addEventListener('hidden.bs.modal', function (event) {
                modalRepairParent.innerHTML = "";
                modalParent.innerHTML = "";
                modalContentLoad("element", svtObjId);
            });
        });
        
        
         $("#transferBtn")[0].addEventListener("click", function () {
           
            modalTransferContentLoad(svtObjId, document.querySelector('#modelSelect').innerText);
            
            modalTransfer.addEventListener('hidden.bs.modal', function (event) {
                
                modalTransferParent.innerHTML = "";
                modalParent.innerHTML = "";
                modalContentLoad("element", svtObjId);
                
            });
        });
        
        
        if (eventReason.indexOf("storage") >= 0) {
            if(null != $("#dateCreateSelect")[0]) {
                dateCreate.disabled = true;
            }
            
           
            // $('#archivedBtn')[0].removeEventListener('click', handleClickArchivedBtn);
            $('.svtObjModalFooter')[0].innerHTML = "";
            $('.svtObjModalFooter').append('<button type="button" class="btn btn-danger btn-sm" id="archivedBtn"  data-bs-dismiss="modal">Удалить</button> ' +
                    '<button type="button" class="btn btn-warning btn-sm" id="backFromStorageBtn" data-bs-target="#confirmationModal" data-bs-toggle="modal" >Вернуть со склада</button>' +
                    '<button type="button" class="btn btn-secondary btn-sm"  data-bs-dismiss="modal">Отменить</button>' +
                    '<button type="button" class="btn btn-primary btn-sm" id="btnSave" >Применить</button>');
            $('#btnSave')[0].disabled = true;
            
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
                    if(attrib == "asuo") {
                    if($("#modelRow")[0] != null) {
                        $("#modelRow")[0].remove();
                        $("#inventaryNumberRow")[0].remove();
                        $("#serialNumberRow")[0].remove();
                        $("#statusRow")[0].remove();
                        }
                    }
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
                        case "upsforserver":
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
         $('#btnSave')[0].addEventListener('click', handleClickSavePhoneBtn);
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
        innerConnectionAnalog = null;
        innerConnectionIp = null;
        cityNumberAmount = null;
        outerConnectionType = null;
        conditionerTypeSelect = null;
        splitSystem = null;
        winterKit = null;
        havePomp = null;
        price = null;
        description = null;
        modalParent.innerHTML = "";
    });
    //$('#btnSave')[0].addEventListener('click', handleClickSavePhoneBtn);
    stor = false;
  
};

document.addEventListener("DOMContentLoaded", function () {
    let treeLocations = [...document.querySelectorAll('.location')];
  
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
                    case "upsforserver":
                        dynamicLabel = "Год замены";
                        break;
                        }
                
                
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
                           case "ats":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Год ввода в экспл</div>' +
                            '<div class="col">Кол-во город. номеров</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                        case "conditioner":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Год ввода в экспл</div>' +
                            '<div class="col">Тип</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                         case "infomat":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Кабинет</div>' +
                            '<div class="col">Год выпуска</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                        case "terminal":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                        case "thermoprinter":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                        case "display":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                           case "swunit":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                    case "asuo":
                        headerElement.innerHTML = '<div class="col">Наименование в ведомости ОС</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Главное табло</div>' +
                            '<div class="col">Терминал</div>' +
                            '<div class="col">Термопринтер</div>' +
                            '<div class="col">Блок коммутации</div>' +
                            '<div class="col">Свитч</div>' +
                            '<div class="col">Электронное табло на кабинки и кабинеты</div>';
                    break;
                    default:
                       headerElement.className = "row fw-bold mt-3 mb-3";
                if (dynamicLabel != null) {
                    headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Дата ввода в экспл</div>' +
                            '<div class="col">Состояние</div>' +
                            '<div class="col">Год выпуска</div>' +
                            '<div class="col">' + dynamicLabel + '</div>';
                } else {
                    headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Дата ввода в экспл</div>' +
                            '<div class="col">Состояние</div>' +
                            '<div class="col">Год выпуска</div>';
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
                        dynamicLabel = "Внутренний номер";
                        dynamicField = storageDtoes[j].departments[d].dtoes[t].phoneNumber;
                        break;
                    case "monitors":
                        dynamicLabel = "По ведомости ОС";
                        if(storageDtoes[j].departments[d].dtoes[t].baseType == "SINGLE") {
                            dynamicField = "Отдельно";
                        } else {
                            dynamicField = "В составе АРМ";
                        }
                        break;
                    case "ups":
                        dynamicLabel = "Год замены";
                        if(storageDtoes[j].departments[d].dtoes[t].yearReplacement == 0) {
                          dynamicField = "нет";  
                        } else {
                            dynamicField = storageDtoes[j].departments[d].dtoes[t].yearReplacement;
                        }
                        
                        break;
                      case "upsforserver":
                        dynamicLabel = "Год замены";
                        if(storageDtoes[j].departments[d].dtoes[t].yearReplacement == 0) {
                          dynamicField = "нет";  
                        } else {
                            dynamicField = storageDtoes[j].departments[d].dtoes[t].yearReplacement;
                        }
                        
                        break;
                }
                        
                        
                        switch (attrib) {


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
                                case "ats":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].cityNumberAmount + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "conditioner":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '<div class="col">' + getConditionerType(storageDtoes[j].departments[d].dtoes[t].conditionerType) + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "infomat":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].numberRoom + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "terminal":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "thermoprinter":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "display":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "swunit":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                 case "asuo":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].nameFromOneC + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].displayModel + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].terminalModel + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].thermoprinterModel + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].switchingUnitModel + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].switches.length + ' ед.' + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].subDisplayModel + '</div>' +
                                    '</div>';
                                break;
                                default:
                                if(dynamicLabel != null) {
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + formatedDate + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '<div class="col">' + dynamicField + '</div>' +
                                    '</div>';
                        }
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
                             case "upsforserver":
                                dynamicLabel = "Год замены";
                                break;
                        }
            
            switch (attrib) {
                case "switch":
                    headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Тип</div>' +
                            '<div class="col">Кол-во портов</div>' +
                            '<div class="col">Год выпуска</div>' +
                            '<div class="col">Состояние</div>';
                    break;
                case "router":
                    headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Расположение</div>' +
                            '<div class="col">Кол-во портов</div>' +
                            '<div class="col">Год выпуска</div>' +
                            '<div class="col">Состояние</div>';
                    break;
                case "ats":
                    headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Год ввода в экспл</div>' +
                            '<div class="col">Кол-во город. номеров</div>' +
                            '<div class="col">Состояние</div>';
                    break;
                    case "conditioner":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Год ввода в экспл</div>' +
                            '<div class="col">Тип</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                        case "infomat":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Кабинет</div>' +
                            '<div class="col">Год выпуска</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                         case "terminal":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                        case "thermoprinter":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                        case "display":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                         case "swunit":
                     headerElement.innerHTML = '<div class="col">Модель</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Серийный номер</div>' +
                            '<div class="col">Инвентарный номер</div>' +
                            '<div class="col">Состояние</div>';
                        break;
                        case "asuo":
                        headerElement.innerHTML = '<div class="col">Наименование в ведомости ОС</div>' +
                            '<div class="col">ФИО</div>' +
                            '<div class="col">Главное табло</div>' +
                            '<div class="col">Терминал</div>' +
                            '<div class="col">Термопринтер</div>' +
                            '<div class="col">Блок коммутации</div>' +
                            '<div class="col">Свитч</div>' +
                            '<div class="col">Электронное табло на кабинки и кабинеты</div>';
                    break;
                default:
                    headerElement.className = "row fw-bold mt-3 mb-3";
                    if (dynamicLabel != null) {
                        headerElement.innerHTML = '<div class="col">Модель</div>' +
                                '<div class="col">ФИО</div>' +
                                '<div class="col">Серийный номер</div>' +
                                '<div class="col">Инвентарный номер</div>' +
                                '<div class="col">Дата ввода в экспл</div>' +
                                '<div class="col">Состояние</div>' +
                                '<div class="col">Год выпуска</div>' +
                                '<div class="col">' + dynamicLabel + '</div>';
                    } else {
                        headerElement.innerHTML = '<div class="col">Модель</div>' +
                                '<div class="col">ФИО</div>' +
                                '<div class="col">Серийный номер</div>' +
                                '<div class="col">Инвентарный номер</div>' +
                                '<div class="col">Дата ввода в экспл</div>' +
                                '<div class="col">Состояние</div>' +
                                '<div class="col">Год выпуска</div>';
                    }
                    break;
            }
            liItem.childNodes[0].append(headerElement);
            for (d = 0; d < storageDtoes[j].departments.length; d++) {
                
                for (t = 0; t < storageDtoes[j].departments[d].dtoes.length; t++) {
                    
                   switch (attrib) {
                    case "phones":
                        dynamicLabel = "Внутренний номер";
                        dynamicField = storageDtoes[j].departments[d].dtoes[t].phoneNumber;
                        break;
                    case "monitors":
                        dynamicLabel = "По ведомости ОС";
                        if(storageDtoes[j].departments[d].dtoes[t].baseType == "SINGLE") {
                            dynamicField = "Отдельно";
                        } else {
                            dynamicField = "В составе АРМ";
                        }
                        break;
                    case "ups":
                        dynamicLabel = "Год замены";
                        if(storageDtoes[j].departments[d].dtoes[t].yearReplacement == 0) {
                          dynamicField = "нет";  
                        } else {
                            dynamicField = storageDtoes[j].departments[d].dtoes[t].yearReplacement;
                        }
                        
                        break;
                    case "upsforserver":
                        dynamicLabel = "Год замены";
                        if(storageDtoes[j].departments[d].dtoes[t].yearReplacement == 0) {
                          dynamicField = "нет";  
                        } else {
                            dynamicField = storageDtoes[j].departments[d].dtoes[t].yearReplacement;
                        }
                        
                        break;
                }
                    
                    count = t + 1;
                    elDepartment = document.createElement("li");
                    elDepartment.className = "element storage";
                    elDepartment.id = storageDtoes[j].departments[d].dtoes[t].id;
                    elDepartment.setAttribute("data-bs-toggle", "modal");
                    elDepartment.setAttribute("data-bs-target", "#addPlaceModal");
                    let dateBegin = new Date(storageDtoes[j].departments[d].dtoes[t].dateExploitationBegin);
                    let formatedDate = dateBegin.toLocaleDateString('ru');
                    switch (attrib) {

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
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
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
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "ats":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].cityNumberAmount + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "conditioner":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '<div class="col">' + getConditionerType(storageDtoes[j].departments[d].dtoes[t].conditionerType) + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "infomat":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].numberRoom + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                   case "terminal":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "thermoprinter":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "display":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                case "swunit":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '</div>';
                                break;
                                 case "asuo":
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].nameFromOneC + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].displayModel + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].terminalModel + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].thermoprinterModel + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].switchingUnitModel + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].switches.length + ' ед.' + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].subDisplayModel + '</div>' +
                                    '</div>';
                                break;
                            default:
                                if(dynamicLabel != null) {
                                elDepartment.innerHTML = '<div class="row mb-2 d-flex align-items-center text-start">' +
                                    '<div class="col">' + count + '. ' + storageDtoes[j].departments[d].dtoes[t].model + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].placeName + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].serialNumber + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].inventaryNumber + '</div>' +
                                    '<div class="col">' + formatedDate + '</div>' +
                                    '<div class="col">' + getStatus(storageDtoes[j].departments[d].dtoes[t].status) + '</div>' +
                                    '<div class="col">' + storageDtoes[j].departments[d].dtoes[t].yearCreated + '</div>' +
                                    '<div class="col">' + dynamicField + '</div>' +
                                    '</div>';
                        }
                                break;
                    }
                    liItem.childNodes[0].append(elDepartment);
                }
            }
        }
    }

     // Получение строки о результате поиска или фильтра
     
     if(null != filterLocation || null != filterModel || null != filterStatus || null != filterDateBegin || null != filterDateEnd || null != searchFIO || null != searchInventary) {
     let arr = new Array();
     if(null != searchFIO || null != searchInventary) {
       arr.push("показан результат поиска по ");
       if(null != searchFIO && searchFIO != "") {
           arr.push("фамилии: " + searchFIO);
       }
       if(null != searchInventary && searchInventary != "") {
           arr.push("инвентарному номеру: " + searchInventary);
       }
         
     }
     if(null != filterLocation || null != filterModel || null != filterStatus || null != filterDateBegin || null != filterDateEnd) {
         
         arr.push("показан результат фильтра по ");
         if(null != filterLocation && filterLocation != ""){
             arr.push("локации: " + filterLocation);
         }
         if(null != filterModel && filterModel != "") {
             if(arr.length > 1) {
                 arr.push(", ");
             }
             arr.push("модели: " + filterModel);
         }
         if(null != filterStatus && filterStatus != "") {
             if(arr.length > 1) {
                 arr.push(", ");
             }
             arr.push("статусу: " + filterStatus);
         }
          if((null != filterDateBegin && filterDateBegin != "") && (null != filterDateEnd && filterDateEnd != "")) {
              if(arr.length > 1) {
                 arr.push(", ");
             }
             arr.push("году выпуска с " + filterDateBegin + ", до " + filterDateEnd);
         }
         if(null != filterDateBegin && filterDateBegin != "") {
             if(arr.length > 1) {
                 arr.push(", ");
             }
             arr.push("году выпуска с " + filterDateBegin);
         }
         if(null != filterDateEnd && filterDateEnd != "") {
             if(arr.length > 1) {
                 arr.push(", ");
             }
             arr.push("году выпуска до " + filterDateEnd);
             
             
         }
      
         
         
     }
     
        arr.push(", найдено " + amountDevice + " записей.");
         
         
         $("#mesage")[0].style = "margin-left: 40px; margin-bottom: 15px; font-weight: 800;";
         let result = "";
         for(i = 0; i < arr.length; i++) {
             result = result + arr[i];
         }
         $("#mesage")[0].innerHTML = result;
     
     }


    

});











