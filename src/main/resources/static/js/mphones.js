/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */



let btnSave;
const modalWindowContent = document.querySelector('#modalContent-model');
const modalAdd = document.getElementById('addBtnModal');
const modalError = document.getElementById('modalError');
const modalErrorParent = document.getElementById('modalErrorContent');

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

let handleClickUpdateBtn = function (name, id) {
    let requestLink = "/" + name + "upd";
    let dto = {
        id: id,
        model: $("#model")[0].value,
    };
    
    if(attribute == "os") {
        dto.license = $("#licenseFlag")[0].checked;
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


let handleClickSaveBtn = function (name) {
    let requestLink = "/" + name + "/";
     let dto = {
            model : document.querySelector('#model').value
        };
        
     if(document.querySelector('#coreAmount') != null) {
         dto.core = dto.core = document.querySelector('#coreAmount').value;
     }
     if(document.querySelector('#freq') != null) {
         dto.freq = document.querySelector('#freq').value;
     }
     if(document.querySelector('#capacity') != null) {
         dto.capacity = document.querySelector('#capacity').value;
     }
     if(dto.unit = document.querySelector('#unit') != null) {
         dto.unit = document.querySelector('#unit').value;
     }
     if(document.querySelector('#serialNumber') != null) {
          dto.serialNumber = document.querySelector('#serialNumber').value;
     }
     if(document.querySelector('#inventaryNumber') != null) {
         dto.inventaryNumber = document.querySelector('#inventaryNumber').value;
     }
     if($("#licenseFlag")[0] != null) {
         dto.license = $("#licenseFlag")[0].checked;
     }
     
//        switch (attribute) {
//            case "mphones":
//                link = "/mphones/";
//                break;
//            case "mmonitors":
//                link = "/mmonitors/";
//                break;
//            case "mupsbat":
//                link = "/mupsbat/";
//                delete dto.model;
//                dto.type = document.querySelector('#modelNameInput').value;
//                break;
//            case "mcpu":
//                link = "/mcpu/";
//                dto.core = document.querySelector('#coreAmount').value;
//                dto.freq = document.querySelector('#freq').value;
//                break;
//            case "mram":
//                link = "/mram/";
//                dto.capacity = document.querySelector('#capacity').value;
//                break;
//            case "mhdd":
//                link = "/mhdd/";
//                dto.capacity = document.querySelector('#capacity').value;
//                dto.unit = document.querySelector('#unit').value;
//                dto.serialNumber = document.querySelector('#serialNumber').value;
//                dto.inventaryNumber = document.querySelector('#inventaryNumber').value;
//                break;
//             case "mhdd":
//                link = "/mvideo/";
//                break;
//             case "mcddrive":
//                link = "/mcddrive/";
//                break;
//            case "mscard":
//                link = "/mscard/";
//                break;
//            case "mlcard":
//                link = "/mlcard/";
//                break;
//            case "mkeyboard":
//                link = "/mkeyboard/";
//                break;
//            case "mmouse":
//                link = "/mmouse/";
//                break;
//            case "mspeakers":
//                link = "/mspeakers/";
//                break;
//            case "mscanner":
//                link = "/mscanner/";
//                break;
//            case "os":
//                link = "/os/";
//                delete dto.model;
//                dto.name = document.querySelector('#modelNameInput').value;
//                dto.license = $("#licenseFlag")[0].checked;
//                break;
//            case "mserver":
//                link = "/mserver/";
//                break;
//            case "mswitch":
//                link = "/mswitch/";
//                break;
//            case "mrouter":
//                link = "/mrouter/";
//                break;
//             case "mats":
//                link = "/mats/";
//                break;
//            case "mconditioner":
//                link = "/mconditioner/";
//                break;
//            case "mterminal":
//                link = "/mterminal/";
//                break;
//            case "mthermoprinter":
//                link = "/mthermoprinter/";
//                break;
//            case "mdisplay":
//                link = "/mdisplay/";
//                break;
//            case "mswunit":
//                link = "/mswunit/";
//                break;
//            case "msubdisplay":
//                link = "/msubdisplay/";
//                break;
//        } 
                $.ajax({
        type: "POST",
        url: requestLink,
        data: JSON.stringify(dto),
        async: false,
        success: function () {
            window.location.reload();
        },
        error: function(callback) {
            beep();
            getModalError(callback.responseText);
  }, 
        processData: false,
        contentType: 'application/json'
    });
    
};


let handleClickArchivedBtn = function(name, id) {
  let requestLink = "/" + name + "archived";  
  let dto = {
      archived: true,
      id: id,
  };
  
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


let modalContentLoad = function(eventReason, dto) {
    let requestLink;
    let titleAction;
    // Сборка header
    let divModalHeader = document.createElement("div");
    divModalHeader.className = "modal-header modalHeaderContent";
    let titleModal = document.createElement("h5");
    titleModal.className = "modal-title";
    if (null != dto) {
        titleAction = "Редактировать";
        titleModal.innerText = titleAction + " " + namePage[0].toLowerCase() + namePage.slice(1) + ": " + dto.model;
        
    } else {
        titleAction = "Добавить";
        titleModal.innerText = titleAction + " " + namePage;
    }
    
    divModalHeader.appendChild(titleModal);
    
    let headerCloseBtn = document.createElement("button");
    headerCloseBtn.className = "btn-close btn-close-white";
    headerCloseBtn.setAttribute("data-bs-dismiss", "modal");
    headerCloseBtn.setAttribute("aria-label", "Close");
    headerCloseBtn.id = "closeBtn";
    divModalHeader.appendChild(headerCloseBtn);
    modalWindowContent.appendChild(divModalHeader);
    //сборка боди
    let divModalBody = document.createElement("div");
    divModalBody.className = "modal-body";
    divModalBody.style = "min-height: 200px";
    let divContainerBody = document.createElement("div");
    divContainerBody.className = "container";
    divContainerBody.id = "modalContent";
    divContainerBody.innerHTML = '<div class="row mt-2" id="modelRow">' +
            '<div class="col">Наименование</div>' +
            '<div class="col">' +
            '<input class="form-control form-control-sm" type="text" placeholder="введите наименование" aria-label="model" id="model">' +
            '</div></div>';
 
    divModalBody.appendChild(divContainerBody);
    
    if(attribute == "os") {
        let divLicenceRow = document.createElement("div");
        divLicenceRow.className = "row mt-2";
        let licenceLabelCol = document.createElement("div");
        licenceLabelCol.className = "col";
        let divLicenceFormCheck = document.createElement("div");
        divLicenceFormCheck.className = "form-check";
        let inputLicenceFormCheck = document.createElement("input");
        inputLicenceFormCheck.className = "form-check-input";
        inputLicenceFormCheck.type = "checkbox";
        inputLicenceFormCheck.value = "";
        inputLicenceFormCheck.id="licenseFlag";
        let labelLicenceFormCheck = document.createElement("label");
        labelLicenceFormCheck.className = "";
        labelLicenceFormCheck.for = "licenseFlag";
        labelLicenceFormCheck.innerHTML = "Лицензия";
        
        divLicenceFormCheck.appendChild(inputLicenceFormCheck);
        divLicenceFormCheck.appendChild(labelLicenceFormCheck);
        licenceLabelCol.appendChild(divLicenceFormCheck);
        divLicenceRow.appendChild(licenceLabelCol);
        divContainerBody.appendChild(divLicenceRow);
        if(dto != null) {
            inputLicenceFormCheck.checked = dto.license;
        }
    }
    
           
    modalWindowContent.appendChild(divModalBody);
    
    //сборка футера
    let divModalFooter = document.createElement("div");
    divModalFooter.className = "modal-footer svtObjModalFooter";
    
    if(dto != null) {
        let footerBtnDelete = document.createElement("button");
        footerBtnDelete.className = "btn btn-danger btn-sm";
        footerBtnDelete.setAttribute("data-bs-dismiss", "modal");
        footerBtnDelete.innerText = "Удалить";
        footerBtnDelete.id = "archivedBtn";
        divModalFooter.appendChild(footerBtnDelete);
        
        footerBtnDelete.addEventListener("click", function() {
            handleClickArchivedBtn(attribute, dto.id);
        });
    }
    
    let footerBtnCancel = document.createElement("button");
    footerBtnCancel.className = "btn btn-secondary btn-sm";
    footerBtnCancel.setAttribute("data-bs-dismiss", "modal");
    footerBtnCancel.innerText = "Отменить";
    divModalFooter.appendChild(footerBtnCancel);
    let footerBtnSave = document.createElement("button");
    footerBtnSave.className = "btn btn-primary btn-sm";
    footerBtnSave.id = "btnSave";
    footerBtnSave.innerText = "Сохранить";
    
    divModalFooter.appendChild(footerBtnSave);
    modalWindowContent.appendChild(divModalFooter);
    
        if(dto != null) {
        $("#model")[0].value = dto.model;
    }
    
    
    btnSave = document.querySelector('#btnSave');
    
       btnSave.addEventListener('click', function() {
    
        if(dto != null) {
            handleClickUpdateBtn(attribute, dto.id);
        } else {
            
            handleClickSaveBtn(attribute);
        }
    
    
    
});
};






$(document).ready(function() {
    for(i = 0; i < document.getElementsByClassName('element').length; i++) {
        document.getElementsByClassName('element')[i].addEventListener('click', function() {
            modalContentLoad($(this)[0].className, dtoes.find(item => item.id == $(this)[0].id));
 
        });
    }
    
    $("#addBtn")[0].addEventListener('click', function() {
        modalContentLoad();
    });
    
    
     modalAdd.addEventListener('hidden.bs.modal', function (event) {
         modalWindowContent.innerHTML = "";
     });
    
 
});