/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */



let btnSave;
const modalWindowContent = document.querySelector('#modalContent-model');
const modalAdd = document.getElementById('addBtnModal');
const modalError = document.getElementById('modalError');
const modalErrorParent = document.getElementById('modalErrorContent');
let manufacturerChoise;
let modelLink;
const models = $(".models")[0];
const elements = [...document.querySelectorAll('.element')];

let searchModel = function () {
    let query = $("#searchModelInput")[0].value;
    models.innerHTML = "";
    count = 1;
    elements.forEach(item => {
        if (item.childNodes[3].innerText.toLowerCase().includes(query)) {

            let el = document.createElement("div");
            el.className = "row element pb-2 mb-2 mt-2 pt-2";
            item.childNodes[1].innerHTML = count;
            el = item;
            models.appendChild(el);
            count++;
        }
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
}
;

let getModalError = function (textError) {

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
    let dto;
    if (attribute == "mupsbat") {

        dto = {
            id: id,
            type: document.querySelector('#batteryTypeInput').value,
        };

    } else if (attribute == "programSoftware") {
        dto = {
            id: id,
            name: $("#po")[0].innerText,
            version: $("#po_ver")[0].value,
        };
    } else {

        if ($("#manufacturer")[0] != null) {
            dto = {
                id: id,
                model: $("#model")[0].innerText,
                manufacturer: $("#manufacturer")[0].value, };

        } else {
            dto = {
                id: id,
                model: $("#model")[0].innerText, };
        }

    }

    switch (attribute) {
        case "os":
            dto.license = $("#licenseFlag")[0].checked;
            break;
        case "mhdd":
            dto.capacity = $("#capacity")[0].value;
            dto.unit = $("#unit")[0].value;
            dto.serialNumber = $("#serialNumber")[0].value;
            dto.inventaryNumber = $("#inventaryNumber")[0].value;
            break;
        case "mram":
            dto.capacity = $("#capacity")[0].value;
            break;
        case "mcpu":
            dto.core = $("#core")[0].value;
            dto.freq = $("#freq")[0].value;
            break;
        case "mups":
            dto.batteryType = $('#batteryType')[0].selectize.getValue();
            dto.manufacturerId = $('#manufacturer')[0].selectize.getValue();
            dto.manufacturer = document.querySelector('#manufacturer').innerText;
            dto.batteryAmount = document.querySelector('#batteryAmount').value;
            break;
        case "mmonitors":
        case "mfax":
        case "mphones":
        case "mscanner":
        case "mswitch":
        case "mrouter":
        case "mats":
        case "mconditioner":
        case "minfomat":
        case "mserver":
        case "msysblock":
            dto.manufacturerName = document.querySelector('#manufacturer').innerText;
            dto.manufacturerId = $('#manufacturer')[0].selectize.getValue();
            break;
        case "mupsbat":

            break;
        case "mterminalDisplay":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal-display";
            break;
        case "mterminalSensor":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal-sensor";
            break;
        case "mterminalPrinter":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal-printer";
            break;
        case "mterminalUps":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal-ups";
            break;
        case "mterminalServer":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal-server";
            break;
        case "mterminal":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal";
            break;
        case "mhub":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mhub";
            break;
       case "mprinters":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            dto.printColorType = $("#printColorType")[0].value;
            dto.printFormat = $("#printFormatType")[0].value;
            dto.printSpeed = $("#printSpeedInput")[0].value;
            break;
        case "programSoftware":
            dto.name = $("#po")[0].innerText;
            dto.version = $("#po_ver")[0].value;
            requestLink = "/po";
            break;
        case "mdisplay":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mdisplay";
            break;
        case "msubdisplay":
            requestLink = "msubdisplay";
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


let handleClickSaveBtn = function (name) {
    let requestLink = "/" + name + "/";
    let dto = new Object();

    if (attribute != "programSoftware") {
        dto.model = document.querySelector('#model').value;
    } else {
        dto.name = $('#po')[0].innerText;
    }

    if (document.querySelector('#coreAmount') != null) {
        dto.core = dto.core = document.querySelector('#coreAmount').value;
    }
    if (document.querySelector('#freq') != null) {
        dto.freq = document.querySelector('#freq').value;
    }
    if (document.querySelector('#capacity') != null) {
        dto.capacity = document.querySelector('#capacity').value;
    }
    if (dto.unit = document.querySelector('#unit') != null) {
        dto.unit = document.querySelector('#unit').value;
    }
    if (document.querySelector('#serialNumber') != null) {
        dto.serialNumber = document.querySelector('#serialNumber').value;
    }
    if (document.querySelector('#inventaryNumber') != null) {
        dto.inventaryNumber = document.querySelector('#inventaryNumber').value;
    }
    if ($("#licenseFlag")[0] != null) {
        dto.license = $("#licenseFlag")[0].checked;
    }

    switch (attribute) {
        case "mphones":
        case "mfax":
        case "mmonitors":
        case "mscanner":
        case "mswitch":
        case "mrouter":
        case "mats":
        case "mconditioner":
        case "minfomat":
        case "mserver":
        case "msysblock":
            dto.manufacturerName = document.querySelector('#manufacturer').innerText;
            dto.manufacturerId = $('#manufacturer')[0].selectize.getValue();
            break;
        case "mupsbat":
            link = "/mupsbat/";
            delete dto.model;
            dto.type = document.querySelector('#batteryTypeInput')[0].value;
            break;
        case "mups":
            //      link = "/mups/";

            dto.batteryType = $('#batteryType')[0].selectize.getValue();
            dto.manufacturerId = $('#manufacturer')[0].selectize.getValue();
            dto.manufacturer = document.querySelector('#manufacturer').innerText;
            dto.batteryAmount = document.querySelector('#batteryAmount').value;

            break;
        case "mcpu":
            link = "/mcpu/";
            dto.core = document.querySelector('#core').value;
            dto.freq = document.querySelector('#freq').value;
            break;
        case "mram":
            link = "/mram/";
            dto.capacity = document.querySelector('#capacity').value;
            break;
        case "mhdd":
            link = "/mhdd/";
            dto.capacity = document.querySelector('#capacity').value;
            dto.unit = document.querySelector('#unit').value;
            dto.serialNumber = document.querySelector('#serialNumber').value;
            dto.inventaryNumber = document.querySelector('#inventaryNumber').value;
            break;
        case "mhdd":
            link = "/mvideo/";
            break;
        case "mcddrive":
            link = "/mcddrive/";
            break;
        case "mscard":
            link = "/mscard/";
            break;
        case "mlcard":
            link = "/mlcard/";
            break;
        case "mkeyboard":
            link = "/mkeyboard/";
            break;
        case "mmouse":
            link = "/mmouse/";
            break;
        case "mspeakers":
            link = "/mspeakers/";
            break;
        case "mscanner":
            link = "/mscanner/";
            break;
        case "os":
            link = "/os/";
            delete dto.model;
            dto.model = document.querySelector('#model').value;
            dto.license = $("#licenseFlag")[0].checked;
            break;
        case "mswitch":
            link = "/mswitch/";
            break;
        case "mhub":
            link = "/mhub/";
            break;
        case "mrouter":
            link = "/mrouter/";
            break;
        case "mats":
            link = "/mats/";
            break;
        case "mconditioner":
            link = "/mconditioner/";
            break;
        case "mdisplay":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            link = "/mdisplay/";
            break;
        case "msubdisplay":
            link = "/msubdisplay/";
            break;
        case "mterminalSensor":
            requestLink = "/mterminal-sensor/";
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            break;
        case "mterminalDisplay":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal-display/";
            break;
        case "mterminalServer":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal-server/";
            break;
        case "mterminalPrinter":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal-printer/";
            break;
        case "mterminalUps":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal-ups/";
            break;
        case "mterminal":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mterminal";
            break;
        case "mhub":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            requestLink = "/mhub";
            break;
        case "mprinters":
            dto.manufacturerId = $("#manufacturer")[0].selectize.getValue();
            dto.manufacturerName = $("#manufacturer")[0].innerText;
            dto.printColorType = $("#printColorType")[0].value;
            dto.printFormat = $("#printFormatType")[0].value;
            dto.printSpeed = $("#printSpeedInput")[0].value;
            break;
        case "programSoftware":
            dto.name = $("#po")[0].innerText;
            dto.version = $("#po_ver")[0].value;
            requestLink = "/po";
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


let handleClickArchivedBtn = function (name, id) {
    let requestLink = "/" + name + "archived";
    let dto = {
        archived: true,
        id: id,
    };
    
    switch (attribute) {
        case "programSoftware":
            requestLink = "/po";
            break;
            
  
    }

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


let modalContentLoad = function (eventReason, dto) {
    let requestLink;
    let titleAction;
    // Сборка header
    let divModalHeader = document.createElement("div");
    divModalHeader.className = "modal-header modalHeaderContent";
    let titleModal = document.createElement("h5");
    titleModal.className = "modal-title";
    if (null != dto) {
        titleAction = "Редактировать";
        if (attribute == "mupsbat") {
            titleModal.innerText = titleAction + " " + namePage[0].toLowerCase() + namePage.slice(1) + ": " + dto.type;
        } else if (attribute == "programSoftware") {
            titleModal.innerText = titleAction + " " + namePage[0].toLowerCase() + namePage.slice(1) + ": " + dto.name;
        } else {
            titleModal.innerText = titleAction + " " + namePage[0].toLowerCase() + namePage.slice(1) + ": " + dto.model;
        }


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
    switch (attribute) {
        case "mphones":
        case "mfax":
        case "mmonitors":
        case "mscanner":
        case "mswitch":
        case "mhub":
        case "mrouter":
        case "mats":
        case "mconditioner":
        case "minfomat":
        case "mserver":
        case "msysblock":
            divContainerBody.innerHTML = '<div class="row mt-2" id="manufacturerRow">' +
                    '<div class="col">Производитель</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm"  placeholder="выберите производителя" aria-label="model" id="manufacturer">' +
                    '</select></div></div>' +
                    '<div class="row mt-2" id="modelRow">' +
                    '<div class="col">Наименование</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm" placeholder="выберите модель" aria-label="model" id="model">' +
                    '</select></div>';
            break;
        case "mterminalDisplay":
            divContainerBody.innerHTML = '<div class="row mt-2" id="manufacturerRow">' +
                    '<div class="col">Производитель</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm"  placeholder="выберите производителя" aria-label="model" id="manufacturer">' +
                    '</select></div></div>' +
                    '<div class="row mt-2" id="modelRow">' +
                    '<div class="col">Наименование</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm" placeholder="выберите модель" aria-label="model" id="model">' +
                    '</select></div></div>';
            break;
        case "mterminalPrinter":
        case "mterminalServer":
        case "mterminalSensor":
        case "mterminalUps":
        case "mterminal":
        case "mdisplay":
            divContainerBody.innerHTML = '<div class="row mt-2" id="manufacturerRow">' +
                    '<div class="col">Производитель</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm"  placeholder="выберите производителя" aria-label="model" id="manufacturer">' +
                    '</select></div></div>' +
                    '<div class="row mt-2" id="modelRow">' +
                    '<div class="col">Наименование</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm" placeholder="выберите модель" aria-label="model" id="model">' +
                    '</select></div></div>' +
                    '</div>';
            break;
        case "mups":
            divContainerBody.innerHTML = '<div class="row mt-2" id="manufacturerRow">' +
                    '<div class="col">Производитель</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm"  placeholder="выберите производителя" aria-label="model" id="manufacturer">' +
                    '</select></div></div>' +
                    '<div class="row mt-2" id="modelRow">' +
                    '<div class="col">Наименование</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm" placeholder="выберите модель" aria-label="model" id="model">' +
                    '</select></div></div>' +
                    '<div class="row mt-2 battery-type-row">' +
                    '<div class="col">Тип батареи</div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm">' +
                    '<select class="form-select form-select-sm" id="batteryType" placeholder="тип батареи"></select>' +
                    '</div>' +
                    '</div></div>' +
                    '<div class="row mt-2 battery-amount-row">' +
                    '<div class="col">Количество батарей</div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm">' +
                    '<input class="form-control form-control-sm" id="batteryAmount" type="number" value="1" min="1" max="99" placeholder="количество батарей"/>' +
                    '</div></div>' +
                    '</div>';

            break;
        case "mprinters":
            divContainerBody.innerHTML = '<div class="row mt-2" id="manufacturerRow">' +
                    '<div class="col">Производитель</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm"  placeholder="выберите производителя" aria-label="model" id="manufacturer">' +
                    '</select></div></div>' +
                    '<div class="row mt-2" id="modelRow">' +
                    '<div class="col">Наименование</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm" placeholder="выберите модель" aria-label="model" id="model">' +
                    '</select></div></div>' +
                    '<div class="row mt-2" id="printColorTypeRow">' +
                    '<div class="col">Цветность печати</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm" placeholder="выберите из списка" aria-label="print-color-type" id="printColorType">' +
                    '<option value="BLACK">черно-белая</option>' +
                    '<option value="COLOR">цветная</option>' +
                    '</select></div></div>' +
                    '<div class="row mt-2" id="printFormatTypeRow">' +
                    '<div class="col">Максимальный формат</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm" placeholder="выберите из списка" aria-label="print-format-type" id="printFormatType">' +
                    '<option value="A4">A4</option>' +
                    '<option value="A3">A3</option>' +
                    '</select></div></div>' +
                     '<div class="row mt-2 printSpeedRow">' +
                    '<div class="col"><span>Скорость печати </span><span style="font-size:9px; font-color=696969;">(стр/мин)</span></div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm">' +
                    '<input class="form-control form-control-sm" id="printSpeedInput" type="number" value="1" min="1" max="999" placeholder="укажите скорость печати стр/мин"/>' +
                    '</div></div>' +
                    '</div>' +
                    '<div class="row mt-2" id="modelCartridgeRow">' +
                    '<div class="col">Совместимые модели картриджей</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm" placeholder="выберите из списка или начните вводить" aria-label="model-cartridge" id="modelCartridge">' +
                    '</select></div></div>';
            break;
        case "mupsbat":
            divContainerBody.innerHTML = '<div class="row mt-2" id="modelRow">' +
                    '<div class="col">Наименование</div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm">' +
                    '<input class="form-control form-control-sm" id="batteryTypeInput" type="text"  placeholder="Тип батареи"/>' +
                    '</div></div></div>';
            break;
        case "programSoftware":
            divContainerBody.innerHTML = '<div class="row mt-2" id="modelRow">' +
                    '<div class="col">Наименование ПО</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm" placeholder="выберите из списка" aria-label="po" id="po">' +
                    '</select></div></div>' +
                    '<div class="row mt-2 po-row">' +
                    '<div class="col">Версия ПО</div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm">' +
                    '<input class="form-control form-control-sm" id="po_ver" type="text" placeholder="укажите версию"/>' +
                    '</div></div>' +
                    '</div>';
            break;
        default :
            divContainerBody.innerHTML = '<div class="row mt-2" id="modelRow">' +
                    '<div class="col">Наименование</div>' +
                    '<div class="col">' +
                    '<select class="form-select form-select-sm" placeholder="выберите модель" aria-label="model" id="model">' +
                    '</select></div>';

            break;


    }


    divModalBody.appendChild(divContainerBody);


    switch (attribute) {
        case "mprinters":
            if(dto != null) {
                $(divContainerBody).find('#printColorType')[0].value = dto.printColorType;
                $(divContainerBody).find("#printFormatType")[0].value = dto.printFormat;
                $(divContainerBody).find("#printSpeedInput")[0].value = dto.printSpeed;
            }
            break;
        case "os":
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
            inputLicenceFormCheck.id = "licenseFlag";
            let labelLicenceFormCheck = document.createElement("label");
            labelLicenceFormCheck.className = "";
            labelLicenceFormCheck.for = "licenseFlag";
            labelLicenceFormCheck.innerHTML = "Лицензия";
            divLicenceFormCheck.appendChild(inputLicenceFormCheck);
            divLicenceFormCheck.appendChild(labelLicenceFormCheck);
            licenceLabelCol.appendChild(divLicenceFormCheck);
            divLicenceRow.appendChild(licenceLabelCol);
            divContainerBody.appendChild(divLicenceRow);
            if (dto != null) {
                inputLicenceFormCheck.checked = dto.license;
            }
            break;
        case 'mram':
            let divCapacityRamRow = document.createElement("div");
            divCapacityRamRow.className = "row mt-2";
            let capacityRamLabelCol = document.createElement("div");
            capacityRamLabelCol.className = "col";
            capacityRamLabelCol.innerHTML = "Объём, ГБ";
            let capacityRamInputCol = document.createElement("div");
            capacityRamInputCol.className = "col";
            let capacityRamInput = document.createElement("input");
            capacityRamInput.type = "number";
            capacityRamInput.className = "form-control form-control-sm";
            capacityRamInput.id = "capacity";
            divCapacityRamRow.appendChild(capacityRamLabelCol);
            divCapacityRamRow.appendChild(capacityRamInputCol);
            capacityRamInputCol.appendChild(capacityRamInput);
            divContainerBody.appendChild(divCapacityRamRow);
            break;
        case "mhdd":
            divContainerBody.insertAdjacentHTML('beforeend',
                    ' <div class="row mt-3">' +
                    '<div class="col">Объём</div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm mb-3">' +
                    '<input type="number" class="form-control" id="capacity" placeholder="объём ОЗУ" aria-label="объём ОЗУ" aria-describedby="ram-capacity" min="1" value="1">' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="row">' +
                    '<div class="col">Единица измерения</div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm mb-3">' +
                    '<select class="form-select form-select-sm" aria-label="Default select example" id="unit">' +
                    '<option value="GB" selected>ГБ</option>' +
                    '<option value="MB">МБ</option>' +
                    '<option value="TB">ТБ</option>' +
                    '</select>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="row">' +
                    '<div class="col">Серийный номер</div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm mb-3">' +
                    '<input type="text" class="form-control" id="serialNumber" placeholder="серийный номер" aria-label="серийный номер" aria-describedby="serial-number">' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="row">' +
                    '<div class="col">Учётный номер</div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm mb-3">' +
                    '<input type="text" class="form-control" id="inventaryNumber" placeholder="учётный номер" aria-label="учётный номер" aria-describedby="inventary-number">' +
                    '</div>' +
                    '</div>' +
                    '</div>'
                    );

            break;
        case "mcpu":
            divContainerBody.insertAdjacentHTML('beforeend',
                    '  <div class="row mt-3">' +
                    '<div class="col">Количество ядер</div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm mb-3">' +
                    '<input type="number" class="form-control" id="core" placeholder="количество ядер" aria-label="количество ядер" aria-describedby="core-amount">' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="row">' +
                    '<div class="col">Частота</div>' +
                    '<div class="col">' +
                    '<div class="input-group input-group-sm mb-3">' +
                    '<input type="number" class="form-control" id="freq" placeholder="частота процессора" aria-label="частота процессора" aria-describedby="freq-core">' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>'

                    );
            break;
    }


    modalWindowContent.appendChild(divModalBody);

    //сборка футера
    let divModalFooter = document.createElement("div");
    divModalFooter.className = "modal-footer svtObjModalFooter";

    if (dto != null) {
        let footerBtnDelete = document.createElement("button");
        footerBtnDelete.className = "btn btn-danger btn-sm";
        footerBtnDelete.setAttribute("data-bs-dismiss", "modal");
        footerBtnDelete.innerText = "Удалить";
        footerBtnDelete.id = "archivedBtn";
        divModalFooter.appendChild(footerBtnDelete);

        footerBtnDelete.addEventListener("click", function () {
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
    
    if (dto != null) {
        switch (attribute) {
            case 'mram':
                $("#capacity")[0].value = dto.capacity;
                break;
            case 'mhdd':
                $("#capacity")[0].value = dto.capacity;
                $('#unit')[0].value = dto.unit;
                $('#serialNumber')[0].value = dto.serialNumber;
                $('#inventaryNumber')[0].value = dto.inventaryNumber;
                break;
            case "mcpu":
                $("#core")[0].value = dto.core;
                $("#freq")[0].value = dto.freq;
                break;
            case "mups":
                $("#batteryAmount")[0].value = dto.batteryAmount;
                break;
            case "mupsbat":
                $("#batteryTypeInput")[0].value = dto.type;
                break;
            case "programSoftware":
                $("#po_ver")[0].value = dto.version;
                break;


        }
    }

    if ($("#manufacturer").length > 0) {
        $("#manufacturer").selectize({
            preload: true,
            persist: true,
            create: function (input, callback) {
                $.ajax({
                    url: manufacturersSaveLink,
                    type: "POST",
                    data: {name: input},
                    success: callback,
                    error: function (res) {
                        getModalError(res.responseText);
                    }
                });
            },
            placeholder: "выберите из списка или начните вводить",
            valueField: 'id',
            labelField: 'name',
            sortField: 'name',
            searchField: ["id", "name"],
            onInitialize: function () {
                $.ajax({
                    url: manufacturersLink,
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    success: function (res) {
                        res.forEach(model => {
                            $('#manufacturer')[0].selectize.addOption(model);
                            $('#manufacturer')[0].selectize.addItem(model);
                        });
                        if (null != dto) {
                            manufacturerChoise = $('#manufacturer')[0].selectize.search(dto.manufacturerId).items[0].id;
                            $('#manufacturer')[0].selectize.setValue($('#manufacturer')[0].selectize.search(dto.manufacturerId).items[0].id);
                        } else {
                            if($('#manufacturer')[0].selectize.setValue($('#manufacturer')[0].selectize.search("не указано").items[0]) != null) {
                                manufacturerChoise = $('#manufacturer')[0].selectize.search("не указано").items[0].id;
                                $('#manufacturer')[0].selectize.setValue($('#manufacturer')[0].selectize.search("не указано").items[0].id);
                            }
                            
                        }

                    }
                });
            },
            onChange: function (value) {
                if (value != '' && value != manufacturerChoise) {
                    $.ajax({
                        url: modelsByManufacturerLink + $('#manufacturer')[0].selectize.getValue(),
                        type: 'GET',
                        async: false,
                        dataType: 'json',
                        success: function (res) {

                            let keys = Object.keys($('#model')[0].selectize.options);
                            for (let i = 0; i < keys.length; i++) {
                                $('#model')[0].selectize.removeOption(keys[i]);
                            }

                            res.forEach(model => {
                                $('#model')[0].selectize.addOption(model);
                                $('#model')[0].selectize.addItem(model);
                            });

                            $('#model')[0].selectize.setValue($('#model')[0].selectize.search(0).items[0].id);

                        }
                    });
                    manufacturerChoise = value;
                }
            }
        });
    }


    if($("#modelCartridge").length > 0) {
        $("#modelCartridge").selectize({
            preload: true,
            persist: true,
            create: false,
            maxItems: null,
            placeholder: "выберите из списка или начните вводить",
            valueField: 'id',
            labelField: 'model',
            sortField: 'model',
            searchField: ["id", "model"],
        
        
        });
    }

    if ($("#model").length > 0) {

        $("#model").selectize({
            preload: true,
            persist: true,
            create: true,
            placeholder: "выберите из списка или начните вводить",
            valueField: 'id',
            labelField: 'model',
            sortField: 'model',
            searchField: ["id", "model"],
            onInitialize: function () {

                if ($('#manufacturer')[0] != null && dto != null) {
                    modelLink = modelsByManufacturerLink + dto.manufacturerId;
                } else {

                    switch (attribute) {
                        case "mlcard":
                            modelLink = "/modlcard/";
                            break;
                        case "mmotherboard":
                            modelLink = "/modmotherboard/";
                            break;
                        case "mcpu":
                            modelLink = "/modcpu/";
                            break;
                        case "mram":
                            modelLink = "/modram/";
                            break;
                        case "mhdd":
                            modelLink = "/modhdd/";
                            break;
                        case "mvideo":
                            modelLink = "/modvideo/";
                            break;
                        case "mcddrive":
                            modelLink = "/modcddrive/";
                            break;
                        case "programSoftware":
                            modelLink = "/get-all-po/";
                            break;
                        case "msubdisplay":
                            modelLink = "/modsubdisplay";
                            break;
//                        case "mdisplay":
//                            modelLink = "/";
                    }

                }
                $.ajax({
                    url: modelLink,
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    success: function (res) {
                        res.forEach(model => {
                            $('#model')[0].selectize.addOption(model);
                            $('#model')[0].selectize.addItem(model);
                        });

                    },
                    onChange: function(value) {
                        console.log(value);
                    }
                });

                if (null != dto) {
                    $('#model')[0].selectize.setValue($('#model')[0].selectize.search(dto.id).items[0].id);
                } else {
                    if ($('#model')[0].selectize.search("не указано").items.length > 0) {
                        $('#model')[0].selectize.setValue($('#model')[0].selectize.search("не указано").items[0].id);
                    }
                }
            },
        });
        
       
       
    }




    if ($("#po").length > 0) {

        $("#po").selectize({
            preload: true,
            persist: true,
            create: true,
            placeholder: "выберите из списка",
            valueField: 'id',
            labelField: 'name',
            sortField: 'name',
            searchField: ["id", "name"],
            onInitialize: function () {
                $.ajax({
                    url: "/get-all-po",
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    success: function (res) {
                        res.forEach(model => {
                            $('#po')[0].selectize.addOption(model);
                            $('#po')[0].selectize.addItem(model);
                        });

                    }
                });

                if (null != dto) {
                    $('#po')[0].selectize.setValue($('#po')[0].selectize.search(dto.id).items[0].id);
                } else {
                    if($('#po')[0].selectize.search("не указано").items.length > 0) {
                        $('#po')[0].selectize.setValue($('#po')[0].selectize.search("не указано").items[0].id);
                    }
                    
                }
            },
        });
    }




    if ($("#batteryType").length > 0) {
        $("#batteryType").selectize({
            preload: true,
            persist: true,
            create: true,
            placeholder: "выберите тип батареи",
            valueField: 'type',
            labelField: 'type',
            sortField: 'type',
            searchField: ["id", "type"],
            onInitialize: function () {
                $.ajax({
                    url: '/batterytype',
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    success: function (res) {
                        res.forEach(model => {
                            $('#batteryType')[0].selectize.addOption(model);
                            $('#batteryType')[0].selectize.addItem(model);
                        });
                        if (null != dto) {
                            $('#batteryType')[0].selectize.setValue($('#batteryType')[0].selectize.search(dto.batteryTypeId).items[0].id);
                        } else {
                            $('#batteryType')[0].selectize.setValue($('#batteryType')[0].selectize.search("не указано").items[0].id);
                        }
                    }
                });
            },
        });
    }
    btnSave = document.querySelector('#btnSave');
    btnSave.addEventListener('click', function () {
        if (dto != null) {
            handleClickUpdateBtn(attribute, dto.id);
        } else {
            handleClickSaveBtn(attribute);
        }

    });
};


$(document).ready(function () {

    $("#searchSvtObjBtn")[0].addEventListener("click", searchModel);

    $("#searchModelInput")[0].addEventListener("keypress", function (e) {

        if (13 === e.keyCode) {
            searchModel();
        }

    });

    for (i = 0; i < document.getElementsByClassName('element').length; i++) {
        document.getElementsByClassName('element')[i].addEventListener('click', function () {
            modalContentLoad($(this)[0].className, dtoes.find(item => item.id == $(this)[0].id));

        });
    }

    $("#addBtn")[0].addEventListener('click', function () {
        modalContentLoad();
    });


    modalAdd.addEventListener('hidden.bs.modal', function (event) {
        modalWindowContent.innerHTML = "";
    });

    modalError.addEventListener('hidden.bs.modal', function (event) {
        modalWindowContent.innerHTML = "";
        modalErrorParent.innerHTML = "";
        modalContentLoad();
    });

});