/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
let manufacturePrinterSelect;
let modelCartridgeSelect;
let manuf;
let cartridgesMap = new Map();
let targetSwitch;

const previousButton = document.querySelector('#prev');
const nextButton = document.querySelector('#next');
const submitButton = document.querySelector('#submit');
const tabTargets = document.querySelectorAll('.tab');
const tabPanels = document.querySelectorAll('.tabpanel');
const isEmpty = (str) => !str.trim().length;
const tt = document.querySelector('#printersInfo');
const respage = document.querySelector('#resultInfo');
const cartrLoc = document.querySelector('#cartridgesInfo');
let currentStep = 0;

let printersArray = new Array();
let cartridgesArray = new Array();
let printersPlusCartridges = new Array();
let mainPageArray = new Array();

let contract = new Object();
let manufact = new URL("http://localhost:8080/manufacturers"); // адрес контроллера с которого загружаем список производителей и моделей принтеров
let cartridgesUrl = new URL("http://localhost:8080/showcartridges"); //  адрес контроллера с которого загружаем список картриджей
let xhr = new XMLHttpRequest();
xhr.open("GET", manufact, true);
xhr.responseType = "json";
xhr.send();
let cartridgesXhr = new XMLHttpRequest();
cartridgesXhr.open("GET", cartridgesUrl, true);
cartridgesXhr.responseType = "json";
cartridgesXhr.send();
let formData = new FormData();

let optionsManufacturerMap = new Map();
optionsManufacturerMap.set("выбрать из списка", "");

let printers = new Map();
printers.set("выбрать из списка", "");

// Справочник принтеров

let optionsPrinterMap = new Map();
optionsPrinterMap.set("выбрать из списка", "");


let optionsCartridgeTypeMap = new Map();
optionsCartridgeTypeMap.set("выбрать из списка", "");



let optionsCartridgeMap = new Map();
optionsCartridgeMap.set("выбрать из списка", "");


xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
        xhr.response.forEach((element) => {
            optionsManufacturerMap.set(element.manufacturer, element.manufacturer);
            printers.set(element.model, element.manufacturer);
        });
    }
};

cartridgesXhr.onreadystatechange = function () {
    if (cartridgesXhr.readyState === 4) {
        cartridgesXhr.response.forEach((element) => {
            optionsCartridgeTypeMap.set(element.type, element.type + " " + element.modelValue + " " + element.model);
            optionsCartridgeMap.set(element.modelValue, element.type);
            cartridgesMap.set(element.modelValue, element.resource);
        });
    }
};






document.addEventListener('submit', function (event) {

    let tempArray = new Array();
    tempArray = mainPageArray.concat(printersArray);
    printersPlusCartridges = tempArray.concat(cartridgesArray);
    event.preventDefault();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/main",
        data: JSON.stringify(printersPlusCartridges),
        success: function () {
            window.location.reload();
        },
        error: function (e) {
            console.log(e);
        },
        processData: false,
        contentType: 'application/json'


    });
    console.log(printersPlusCartridges);

});



document.addEventListener('click', function (e) {
    if (currentStep > 0) {
        if (hasClass(e.target, 'form-check-input')) {
            console.log(e.target);
            targetSwitch = e.target;
            idTarget = targetSwitch.id.split("_")[1];
            if (targetSwitch.checked === true) {
                document.querySelector("#row_cartridge_" + targetSwitch.id.split("_")[1]).hidden = false;
            } else {
                document.querySelector("#row_cartridge_" + targetSwitch.id.split("_")[1]).hidden = true;
            }

        } else if (hasClass(e.target, 'test')) {
            // .test clicked
            // Do your other thing
        }
    }
}, false);


function hasClass(elem, className) {
    return elem.classList.contains(className);
}

// Next: Change UI relative to the current step and account for button permissions
nextButton.addEventListener("click", (event) => {
    // Prevent default on links
    event.preventDefault();
    // Hide current tab
    tabPanels[currentStep].classList.add('hidden');
    tabTargets[currentStep].classList.remove('active');
    // Show next tab
    tabPanels[currentStep + 1].classList.remove('hidden');
    tabTargets[currentStep + 1].classList.add('active');
    currentStep += 1;


    if (currentStep == 1) {
        getContract();
        if (mainPageArray.length > 0) {
            mainPageArray.forEach(element => {
                if (!element.amountPrinters) {
                    currentStep += 1;
                }
            });
        }
    } else if (currentStep == 2) {
        getContractPrinterDetails();
    } else if (currentStep == 3) {
        getContractCartridgeDetails();
    }

    if (mainPageArray.length > 0) {
        mainPageArray.forEach(element => {
            if (!element.amountPrinters) {
                console.log("Принтеры не выбраны");
                // Show next tab
                tabPanels[currentStep].classList.remove('hidden');
                tabTargets[currentStep].classList.add('active');
            }
        });
    }
    validateEntry();
    getContentForTablist();
    updateStatusDisplay();


});




function getFinalPage() {

    let input = tabPanels[0].querySelector('.form-control');

    contractInfo = document.createElement("div");
    contractInfo.className = "row mb-4 mt-5";
    contractInfo.id = "contractInfo";

    objectBuingRowTitle = document.createElement("div");
    objectBuingRowTitle.className = "row mb-4 mt-5";
    objectBuingRowTitle.id = "objectBuingTitle";

    objectBuingRow = document.createElement("div");
    objectBuingRow.className = "row mb-4 mt-5";

    labelNumberContract = document.createElement("div");
    labelNumberContract.className = "col-md-4";
    labelNumberContract.innerText = "Номер контракта: " + contract.numberContract;


    labelDateStartContract = document.createElement("div");
    labelDateStartContract.className = "col-md-4";
    labelDateStartContract.innerText = "Дата начала контракта: " + contract.dateStartContract;


    labelDateEndContract = document.createElement("div");
    labelDateEndContract.className = "col-md-4";
    labelDateEndContract.innerText = "Дата окончания контракта: " + contract.dateEndContract;


    objectBuing = document.createElement("div");
    objectBuing.className = "col-md-12 text-center text-uppercase fw-bold";
    objectBuing.innerText = "Объект закупки";

    labelPrintersAmount = document.createElement("div");
    labelPrintersAmount.className = "col-md-6 text-center";
    labelPrintersAmount.innerText = "Принтеры: " + input.form.elements.amountPrinterInput.value;

    labelCartridgeAmount = document.createElement("div");
    labelCartridgeAmount.className = "col-md-6 text-center";
    labelCartridgeAmount.innerText = "Картриджи: " + input.form.elements.amountCartridgeInput.value;


    //Раздел принтеры


    printerTitle = document.createElement("div");
    printerTitle.className = "col-md-12 text-center text-uppercase fw-bold mb-3 mt-5";
    printerTitle.innerText = "Принтеры";

    // Строка с шапкой принтеров

    labelsPrinterRow = document.createElement("div");
    labelsPrinterRow.className = "row mb-4 fw-bold";


    labelCountPrinter = document.createElement("div");
    labelCountPrinter.className = "col-md-1 d-flex align-items-center justify-content-center";
    labelCountPrinter.innerText = "№";

    labelManufacturerPrinter = document.createElement("div");
    labelManufacturerPrinter.className = "col-md-2 d-flex align-items-center justify-content-center";
    labelManufacturerPrinter.innerText = "Производитель";


    labelModelPrinter = document.createElement("div");
    labelModelPrinter.className = "col-md-2 d-flex align-items-center justify-content-center";
    labelModelPrinter.innerText = "Модель";

    labelSerialNumberPrinter = document.createElement("div");
    labelSerialNumberPrinter.className = "col-md-2 d-flex align-items-center justify-content-center";
    labelSerialNumberPrinter.innerText = "Серийный номер";

    labelInventoryNumberPrinter = document.createElement("div");
    labelInventoryNumberPrinter.className = "col-md-3 d-flex align-items-center justify-content-center";
    labelInventoryNumberPrinter.innerText = "Инвентарный номер";


    labelCartridgeIncludePrinter = document.createElement("div");
    labelCartridgeIncludePrinter.className = "col-md-2 d-flex align-items-center justify-content-center";
    labelCartridgeIncludePrinter.innerText = "Картридж в комплекте";



    respage.appendChild(contractInfo);
    respage.appendChild(objectBuingRowTitle);
    respage.appendChild(objectBuingRow);
    respage.appendChild(printerTitle);
    respage.appendChild(labelsPrinterRow);
    contractInfo.appendChild(labelNumberContract);
    contractInfo.appendChild(labelDateStartContract);
    contractInfo.appendChild(labelDateEndContract);
    objectBuingRowTitle.appendChild(objectBuing);
    objectBuingRow.appendChild(labelPrintersAmount);
    objectBuingRow.appendChild(labelCartridgeAmount);

    labelsPrinterRow.appendChild(labelCountPrinter);
    labelsPrinterRow.appendChild(labelManufacturerPrinter);
    labelsPrinterRow.appendChild(labelModelPrinter);
    labelsPrinterRow.appendChild(labelSerialNumberPrinter);
    labelsPrinterRow.appendChild(labelInventoryNumberPrinter);
    labelsPrinterRow.appendChild(labelCartridgeIncludePrinter);



    // Добавление принтеров на страницу

    for (i = 0; i < printersArray.length; i++) {

        paneObject = document.createElement("div");
        paneObject.className = "paneFinal mb-3";

        printerRow = document.createElement("div");
        printerRow.className = "row mb-3 pt-3 pb-3";
        printerRow.id = "printer_row_" + (i + 1);

        countEachPrinter = document.createElement("div");
        countEachPrinter.className = "col-md-1 d-flex align-items-center justify-content-center";
        countEachPrinter.innerText = i + 1;

        manufacturerEachPrinter = document.createElement("div");
        manufacturerEachPrinter.className = "col-md-2 d-flex align-items-center justify-content-center";
        manufacturerEachPrinter.innerText = printersArray[i].manufacturer;

        modelEachPrinter = document.createElement("div");
        modelEachPrinter.className = "col-md-2 d-flex align-items-center justify-content-center";
        modelEachPrinter.innerText = printersArray[i].model;

        serialNumberEachPrinter = document.createElement("div");
        serialNumberEachPrinter.className = "col-md-2 d-flex align-items-center justify-content-center";
        serialNumberEachPrinter.innerText = printersArray[i].serialNumber;

        inventoryNumberEachPrinter = document.createElement("div");
        inventoryNumberEachPrinter.className = "col-md-3 d-flex align-items-center justify-content-center";
        inventoryNumberEachPrinter.innerText = printersArray[i].inventoryNumber;

        includeCartridgePrinter = document.createElement("div");
        includeCartridgePrinter.className = "col-md-2 d-flex align-items-center justify-content-center";




        respage.appendChild(paneObject);
        paneObject.appendChild(printerRow);
        printerRow.appendChild(countEachPrinter);
        printerRow.appendChild(manufacturerEachPrinter);
        printerRow.appendChild(modelEachPrinter);
        printerRow.appendChild(serialNumberEachPrinter);
        printerRow.appendChild(inventoryNumberEachPrinter);

        if (printersArray[i].cartridgeIncluded === true) {
            includeCartridgePrinter.innerText = "есть";

            cartridgeTitle = document.createElement("div");
            cartridgeTitle.className = "row";
            cartridgeTitle.id = "cartridgeTitle";

            cartridgeTitleInner = document.createElement("div");
            cartridgeTitleInner.className = "col-md-12 text-start fw-bold";
            cartridgeTitleInner.innerText = "Картридж в комплекте:";
            cartridgeTitleInner.id = "cartridgeTitleInner";

            cartridgeIncludeRow = document.createElement("div");
            cartridgeIncludeRow.className = "row mt-2 pb-3";
            cartridgeIncludeRow.id = "cartridgeIncludeRow";


            cartridgeIncludeTypeLabel = document.createElement("div");
            cartridgeIncludeTypeLabel.className = "col-md-2 text-end";
            cartridgeIncludeTypeLabel.innerText = "тип: ";

            cartridgeIncludeTypeValue = document.createElement("div");
            cartridgeIncludeTypeValue.className = "col-md-2 text-start";
            cartridgeIncludeTypeValue.innerText = printersArray[i].cartridgeIncludedType;

            cartridgeIncludeModelLabel = document.createElement("div");
            cartridgeIncludeModelLabel.className = "col-md-2 text-end";
            cartridgeIncludeModelLabel.innerText = "модель: ";

            cartridgeIncludeModelValue = document.createElement("div");
            cartridgeIncludeModelValue.className = "col-md-2 text-start";
            cartridgeIncludeModelValue.innerText = printersArray[i].cartridgeIncludeModel;

            cartridgeIncludeResourceLabel = document.createElement("div");
            cartridgeIncludeResourceLabel.className = "col-md-2 text-end";
            cartridgeIncludeResourceLabel.innerText = "ном. ресурс: ";

            cartridgeIncludeResourceValue = document.createElement("div");
            cartridgeIncludeResourceValue.className = "col-md-2 text-start";
            cartridgeIncludeResourceValue.innerText = printersArray[i].cartridgeIncludeResource;
            //cartridgeIncludeModel
            paneObject.appendChild(cartridgeTitle);
            cartridgeTitle.appendChild(cartridgeTitleInner);
            paneObject.appendChild(cartridgeIncludeRow);
            cartridgeIncludeRow.appendChild(cartridgeIncludeTypeLabel);
            cartridgeIncludeRow.appendChild(cartridgeIncludeTypeValue);
            cartridgeIncludeRow.appendChild(cartridgeIncludeModelLabel);
            cartridgeIncludeRow.appendChild(cartridgeIncludeModelValue);
            cartridgeIncludeRow.appendChild(cartridgeIncludeResourceLabel);
            cartridgeIncludeRow.appendChild(cartridgeIncludeResourceValue);

        } else {
            includeCartridgePrinter.innerText = "нет";
        }

        printerRow.appendChild(includeCartridgePrinter);

    }
    // Раздел картриджи
    independedCartridgeTitleRow = document.createElement("div");
    independedCartridgeTitleRow.className = "row";

    independedCartridgeTitle = document.createElement("div");
    independedCartridgeTitle.className = "col-md-12 text-center text-uppercase fw-bold mb-3 mt-5";
    independedCartridgeTitle.innerText = "Картриджи";

    // Строка с шапкой принтеров

    labelsIndependedCartridgesRow = document.createElement("div");
    labelsIndependedCartridgesRow.className = "row mb-4 fw-bold";


    labelCountIndependedCartridge = document.createElement("div");
    labelCountIndependedCartridge.className = "col-md-1 d-flex align-items-center justify-content-center";
    labelCountIndependedCartridge.innerText = "№";

    labelIndependedCartridgeType = document.createElement("div");
    labelIndependedCartridgeType.className = "col-md-3 d-flex align-items-center justify-content-center";
    labelIndependedCartridgeType.innerText = "Тип";


    labelIndependedCartridgeModel = document.createElement("div");
    labelIndependedCartridgeModel.className = "col-md-3 d-flex align-items-center justify-content-center";
    labelIndependedCartridgeModel.innerText = "Модель";

    labelIndependedCartridgeResource = document.createElement("div");
    labelIndependedCartridgeResource.className = "col-md-4 d-flex align-items-center justify-content-center";
    labelIndependedCartridgeResource.innerText = "Номинальный ресурс";



    respage.appendChild(independedCartridgeTitleRow);
    independedCartridgeTitleRow.appendChild(independedCartridgeTitle);
    respage.appendChild(labelsIndependedCartridgesRow);
    labelsIndependedCartridgesRow.appendChild(labelCountIndependedCartridge);
    labelsIndependedCartridgesRow.appendChild(labelIndependedCartridgeType);
    labelsIndependedCartridgesRow.appendChild(labelIndependedCartridgeModel);
    labelsIndependedCartridgesRow.appendChild(labelIndependedCartridgeResource);



    // Добавление картриджей на страницу
    for (i = 0; i < cartridgesArray.length; i++) {

        paneObjectCartridge = document.createElement("div");
        paneObjectCartridge.className = "paneFinal mb-3";

        independedCartridgeRow = document.createElement("div");
        independedCartridgeRow.className = "row mb-3 pt-3 pb-3";
        printerRow.id = "printer_row_" + (i + 1);

        countEachIndependedCartridge = document.createElement("div");
        countEachIndependedCartridge.className = "col-md-1 d-flex align-items-center justify-content-center";
        countEachIndependedCartridge.innerText = i + 1;

        typeEachIndependedCartridge = document.createElement("div");
        typeEachIndependedCartridge.className = "col-md-3 d-flex align-items-center justify-content-center";
        typeEachIndependedCartridge.innerText = cartridgesArray[i].type;

        modelEachIndependedCartridge = document.createElement("div");
        modelEachIndependedCartridge.className = "col-md-3 d-flex align-items-center justify-content-center";
        modelEachIndependedCartridge.innerText = cartridgesArray[i].model;

        resourceEachIndependedCartridge = document.createElement("div");
        resourceEachIndependedCartridge.className = "col-md-4 d-flex align-items-center justify-content-center";
        resourceEachIndependedCartridge.innerText = cartridgesArray[i].resource;


        respage.appendChild(paneObjectCartridge);
        paneObjectCartridge.appendChild(independedCartridgeRow);
        independedCartridgeRow.appendChild(countEachIndependedCartridge);
        independedCartridgeRow.appendChild(typeEachIndependedCartridge);
        independedCartridgeRow.appendChild(modelEachIndependedCartridge);
        independedCartridgeRow.appendChild(resourceEachIndependedCartridge);

    }

}


function getContract() {

    contract = {
        numberContract: document.getElementsByClassName('contract')[0].children.item(0).firstElementChild.value,
        dateStartContract: document.getElementsByClassName('contract')[0].children.item(1).firstElementChild.value,
        dateEndContract: document.getElementsByClassName('contract')[0].children.item(2).firstElementChild.value,
        amountPrinters: document.getElementsByClassName('objectBuingRow')[0].children.item(0).childNodes[3].children.item(1).value,
        amountCartridges: document.getElementsByClassName('objectBuingRow')[0].children.item(1).childNodes[3].children.item(1).value,
    }

    mainPageArray.push(contract);

}

function getContentForTablist() {
    let input = tabPanels[0].querySelector('.form-control');
    if (currentStep == 1) {

        addPrintersInfo(input.form.elements.amountPrinterInput.value, tt);



    } else if (currentStep == 2) {
        addCartridgesInfo(input.form.elements.amountCartridgeInput.value, cartrLoc);
    } else if (currentStep == 3) {
        getFinalPage();
    }
}


function updateStatusDisplay() {
    // If on the last step, hide the next button and show submit
    if (currentStep === tabTargets.length - 1) {
        nextButton.classList.add('hidden');
        previousButton.classList.remove('hidden');
        submitButton.classList.remove('hidden');
        validateEntry();
        // If it's the first step, hide the previous button
    } else if (currentStep == 0) {
        nextButton.classList.remove('hidden');
        previousButton.classList.add('hidden');
        submitButton.classList.add('hidden');
        // In all other instances, display both buttons
    } else {
        nextButton.classList.remove('hidden');
        previousButton.classList.remove('hidden');
        submitButton.classList.add('hidden');
    }
}
;

function validateEntry() {
    // Query for the current panel's Textarea input
    let input = tabPanels[0].querySelector('.form-control');
    // Start by disabling the continue and submit buttons
    nextButton.setAttribute('disabled', true);
    submitButton.setAttribute('disabled', true);
    // Validate on initial function fire
    setButtonPermissions(input);
    // Validate on input
    input.addEventListener('input', () => setButtonPermissions(input));
    // Validate if blurring from input
    input.addEventListener('blur', () => setButtonPermissions(input));



}
;

function setButtonPermissions(input) {

    if (isEmpty(input.value)) {
        nextButton.setAttribute('disabled', true);
        submitButton.setAttribute('disabled', true);
    } else {
        nextButton.removeAttribute('disabled');
        submitButton.removeAttribute('disabled');
    }
}
;

function addPrintersInfo(amount, location) {
    labelCount = document.createElement("div");
    labelCount.className = "col-md-1 d-flex align-items-center justify-content-center";
    labelCount.innerText = "№";
    label = document.createElement("label");
    label.className = "form-label";
    label.innerText = "Производитель";
    flex = document.createElement("div");
    flex.className = "row mb-4 mt-5";
    flex.id = "printerLabel";
    pane = document.createElement("div");
    pane.className = "pane";
    divcol1 = document.createElement("div");
    divcol1.className = "col-md-2 d-flex align-items-center justify-content-center";
    divcol1.style = "height:50px";
    location.appendChild(flex);
    flex.appendChild(labelCount);
    flex.appendChild(divcol1);
    divcol1.appendChild(label);

    label2 = document.createElement("label");
    label2.className = "form-label";
    label2.innerText = "Модель";
    divcol2 = document.createElement("div");
    divcol2.className = "col-md-2 d-flex align-items-center justify-content-center";
    divcol2.style = "height:50px";
    flex.appendChild(divcol2);
    divcol2.appendChild(label2);

    label3 = document.createElement("label");
    label3.className = "form-label";
    label3.innerText = "Серийный номер";
    divcol3 = document.createElement("div");
    divcol3.className = "col-md-2 d-flex align-items-center justify-content-center";
    divcol3.style = "height:50px";
    flex.appendChild(divcol3);
    divcol3.appendChild(label3);

    label4 = document.createElement("label");
    label4.className = "form-label";
    label4.innerText = "Инвентарный номер";
    divcol4 = document.createElement("div");
    divcol4.className = "col-md-3 d-flex align-items-center justify-content-center";
    divcol4.style = "height:50px";
    flex.appendChild(divcol4);
    divcol4.appendChild(label4);

    label5 = document.createElement("label");
    label5.className = "form-label";
    label5.innerText = "Картридж в комплекте";
    divcol5 = document.createElement("div");
    divcol5.className = "col-md-2 d-flex align-items-center justify-content-center";
    divcol5.style = "height:50px";
    flex.appendChild(divcol5);
    divcol5.appendChild(label5);

    for (let i = 0; i < amount; i++) {

        id2 = Math.floor(Math.random() * 10000 * 25654);

        flex = document.createElement("div");
        pane = document.createElement("div");
        count = document.createElement("div");
        count.className = "col-md-1 mb-2 d-flex align-items-center justify-content-center";
        count.innerText = i + 1;
        pane.className = "pane mt-3 mb-3";
        select = document.createElement("select");
        select.className = "form-select text-start";
//   select.setAttribute("th:field", "*{" + id + "}");
        select.id = "manufacturer_" + id2;
        select.name = "manufacturer";

        optionsManufacturerMap.forEach((value, key) => {
            let opt = new Option(key, value);
            select.append(opt);
        });

        divcol1 = document.createElement("div");
        flex.className = "row printer mb-2 mt-2 px-3 text-end";
        flex.id = "row_printer_" + (i + 1) + "_" + id2;
        divcol1.className = "col-md-2 mb-2 manufacturer";

        location.appendChild(pane);
        pane.appendChild(flex);
        flex.appendChild(count);
        flex.appendChild(divcol1);
        divcol1.appendChild(select);

        select2 = document.createElement("select");
        select2.className = "form-select text-start";
        select2.id = "modelPrinter_" + id2;
        select2.name = "modelPrinter";

        printers.forEach((value, key) => {
            let opt = new Option(key, value);
            select2.append(opt);
        });

        divcol2 = document.createElement("div");
        divcol2.className = "col-md-2 mb-2 model";
        flex.appendChild(divcol2);
        divcol2.appendChild(select2);


        inp = document.createElement("input");
        inp.type = "text";
        inp.className = "form-control";
        inp.name = "serialNumber";
        inp.id = "serialNumber_" + id2;
        //    inp.setAttribute("th:field", "*{" + id + "}");
        divcol3 = document.createElement("div");
        divcol3.className = "col-md-2 mb-2 serialNumber";
        flex.appendChild(divcol3);
        divcol3.appendChild(inp);

        inp2 = document.createElement("input");
        inp2.type = "text";
        inp2.className = "form-control";
        inp2.name = "inventoryNumber";
        inp2.id = "inventoryNumber_" + id2;
        //    inp.setAttribute("th:field", "*{" + id + "}");
        divcol4 = document.createElement("div");
        divcol4.className = "col-md-3 mb-2 inventoryNumber";
        flex.appendChild(divcol4);
        divcol4.appendChild(inp2);


        inp3 = document.createElement("input");
        inp3.className = "form-check-input";
        inp3.name = "switch";
        inp3.type = "checkbox";
        inp3.id = "switch_" + id2;
        inp3.setAttribute("role", "switch");
        //    inp.setAttribute("th:field", "*{" + id + "}");
        divcol5 = document.createElement("div");
        divcol6 = document.createElement("div");
        divcol5.className = "col-md-2 mb-2";
        divcol6.className = "form-check form-switch printerSwitch text-center";
        flex.appendChild(divcol5);
        divcol5.appendChild(divcol6);
        divcol6.appendChild(inp3);

        //Добавляю картриджи
        //Тип картриджа

        flexCartridge = document.createElement("div");
        flexCartridge.className = "row cartridgeInclude mb-2 px-3";
        flexCartridge.id = "row_cartridge_" + id2;
        flexCartridge.hidden = true;
        divcolTypeLabel = document.createElement("div");
        divcolTypeLabel.className = "col-md-2 text-end";
        divcolTypeLabel.innerText = "Тип:";

        selectTypeCartridge = document.createElement("select");
        selectTypeCartridge.className = "form-select text-start";
//   selectTypeCartridge.setAttribute("th:field", "*{" + id + "}");
        selectTypeCartridge.id = "cartridgeType_" + id2;
        selectTypeCartridge.name = "cartridgeType";
        divcolTypeSelect = document.createElement("div");
        divcolTypeSelect.className = "col-md-2 mb-2 cartridgeType";


        location.appendChild(pane);
        pane.appendChild(flexCartridge);
        flexCartridge.appendChild(divcolTypeLabel);
        flexCartridge.appendChild(divcolTypeSelect);
        divcolTypeSelect.appendChild(selectTypeCartridge);

        // Модель

        divcolModelLabel = document.createElement("div");
        divcolModelLabel.className = "col-md-2 text-end";
        divcolModelLabel.innerText = "Модель:";

        divcolModelSelect = document.createElement("div");
        divcolModelSelect.className = "col-md-2 mb-2 cartridgeModel";
        selectModelCartridge = document.createElement("select");
        selectModelCartridge.className = "form-select text-start";
//   selectModelCartridge.setAttribute("th:field", "*{" + id + "}");
        selectModelCartridge.id = "cartridgeModel_" + id2;
        selectModelCartridge.name = "cartridgeModel";

        optionsCartridgeMap.forEach((value, key) => {
            let opt = new Option(key, value);
            selectModelCartridge.append(opt);
        });

        flexCartridge.appendChild(divcolModelLabel);
        flexCartridge.appendChild(divcolModelSelect);
        divcolModelSelect.appendChild(selectModelCartridge);

        optionsCartridgeTypeMap.forEach((value, key) => {
            let opt = new Option(key, value);
            selectTypeCartridge.append(opt);
        });


        // Номинальный ресурс

        divcolCartridgeResourceLabel = document.createElement("div");
        divcolCartridgeResourceLabel.className = "col-md-2 text-end";
        divcolCartridgeResourceLabel.innerText = "Ном. ресурс:";
        inputCartridgeResource = document.createElement("input");
        inputCartridgeResource.type = "text";
        inputCartridgeResource.className = "form-control";
        inputCartridgeResource.name = "inputCartridgeResource";
        inputCartridgeResource.id = "inputCartridgeResource_" + id2;
        divcolCartridgeResource = document.createElement("div");
        divcolCartridgeResource.className = "col-md-2 mb-2";
        flexCartridge.appendChild(divcolCartridgeResourceLabel);
        flexCartridge.appendChild(divcolCartridgeResource);
        divcolCartridgeResource.appendChild(inputCartridgeResource);

    }


    if (document.getElementsByName('cartridgeType') !== null) {
        typeCartridgeSelect = document.getElementsByName('cartridgeType');
    }

    if (document.getElementsByName('cartridgeModel') !== null) {
        modelCartridgeSelect = document.getElementsByName('cartridgeModel');
    }

    $(document).ready(function () {
        $(typeCartridgeSelect).selectize({
            create: true,
            showAddOptionOnCreate: true,
            addPrecedence: true,
            onChange: function (value) {
                let target = value.split(" ")[0];
                let currentModel;
                let idModel = this.$input.parents()[1].id.split("_")[2];
                for (let eachModel of modelCartridgeSelect) {
                    if (idModel === eachModel.id.split("_")[1]) {
                        currentModel = eachModel;
                    }
                }
                currentModel.selectize.clearOptions();
                for (let yy of optionsCartridgeMap) {
                    if (target === yy[1]) {
                        currentModel.selectize.addOption({value: yy[0], text: yy[0]});
                        currentModel.selectize.addItem(yy[0]);
                        currentModel.selectize.setValue("", false);

                    }
                }
            }

        });
    });

    $(document).ready(function () {
        $(modelCartridgeSelect).selectize({
            create: false,
            showAddOptionOnCreate: true,
            addPrecedence: true
        });
    });

    // Подключение Selectize
    // производитель



    // Связанные списки производитель и модель принтера

    $(document).ready(function () {

        $('.manufacturer').children('select').selectize({
            create: true,
            showAddOptionOnCreate: true,
            addPrecedence: true,

            onChange: function (value) {
                var id;
                let m;
                for (var el of printers) {
                    if (value === el[1]) {
                        id = this.$wrapper.parent().get(0).children[0].id.split("_")[1];
                        for (m of $('.model').children('select')) {
                            if (id === m.id.split("_")[1]) {
                                m.selectize.clearOptions();
                                m.selectize.addOption({value: el[0], text: el[0]});
                                m.selectize.addItem(el[0]);
                                m.selectize.setValue("", false);
                                m.selectize.refreshOptions('option');
                            }
                        }
                    }
                }
            },
            onInput: function (e) {
                console.log(e.target);
            }});
    });


    $(document).ready(function () {
        $('.model').children('select').selectize({
            create: true,
            showAddOptionOnCreate: true,
            addPrecedence: true
        });
    });




}


function addCartridgesInfo(amount, location) {

    labelCount = document.createElement("div");
    labelCount.className = "col-md-1 d-flex align-items-center justify-content-center";
    labelCount.innerText = "№";
    label = document.createElement("label");
    label.className = "form-label";
    label.innerText = "Тип";
    flex = document.createElement("div");
    flex.className = "row mb-4 mt-5";
    flex.id = "printerLabel";
    pane = document.createElement("div");
    pane.className = "pane";
    divcol1 = document.createElement("div");
    divcol1.className = "col-md-3 d-flex align-items-center justify-content-center";
    divcol1.style = "height:50px";
    location.appendChild(flex);
    flex.appendChild(labelCount);
    flex.appendChild(divcol1);
    divcol1.appendChild(label);

    label2 = document.createElement("label");
    label2.className = "form-label";
    label2.innerText = "Модель";
    divcol2 = document.createElement("div");
    divcol2.className = "col-md-4 d-flex align-items-center justify-content-center";
    divcol2.style = "height:50px";
    flex.appendChild(divcol2);
    divcol2.appendChild(label2);

    label3 = document.createElement("label");
    label3.className = "form-label";
    label3.innerText = "Номинальный ресурс";
    divcol3 = document.createElement("div");
    divcol3.className = "col-md-4 d-flex align-items-center justify-content-center";
    divcol3.style = "height:50px";
    flex.appendChild(divcol3);
    divcol3.appendChild(label3);

    for (let i = 0; i < amount; i++) {

        id2 = Math.floor(Math.random() * 10000 * 25654);

        independentCartridgeRow = document.createElement("div"); // Строка
        independentCartridgeRow.className = "row independentCartridge mb-2 mt-2";
        independentCartridgeRow.id = "row_independentCartridge_" + (i + 1) + "_" + id2;

        paneCartridge = document.createElement("div");
        paneCartridge.className = "pane mt-3 mb-3";

        countEachCartridge = document.createElement("div");
        countEachCartridge.className = "col-md-1 mb-2 d-flex align-items-center justify-content-center";
        countEachCartridge.innerText = i + 1;

        divTypeIndependentCartridge = document.createElement("div");
        divTypeIndependentCartridge.className = "col-md-3 mb-2 independentCartridgeType";
        divTypeIndependentCartridge.id = "independentCartridgeType_" + id2;

        selectTypeIndependentCartridge = document.createElement("select");
        selectTypeIndependentCartridge.className = "form-select text-start";
//   selectTypeCartridge.setAttribute("th:field", "*{" + id + "}");
        selectTypeIndependentCartridge.name = "independentCartridgeType";


        optionsCartridgeTypeMap.forEach((value, key) => {
            let opt = new Option(key, value);
            selectTypeIndependentCartridge.append(opt);
        });

        divModelIndependentCartridge = document.createElement("div");
        divModelIndependentCartridge.className = "col-md-4 mb-2 independentCartridgeModel";
        divModelIndependentCartridge.id = "independentCartridgeModel_" + id2;

        selectModelIndependentCartridge = document.createElement("select");
        selectModelIndependentCartridge.className = "form-select text-start";
//   selectModelCartridge.setAttribute("th:field", "*{" + id + "}");
        selectModelIndependentCartridge.id = "independentCartridgeModel_" + id2;
        selectModelIndependentCartridge.name = "independentCartridgeModel";

        optionsCartridgeMap.forEach((value, key) => {
            let opt = new Option(key, value);
            selectModelIndependentCartridge.append(opt);
        });

        divIndependentCartridgeResource = document.createElement("div");
        divIndependentCartridgeResource.className = "col-md-4 mb-2";
        divIndependentCartridgeResource.id = "IndependentCartridgeResource_" + id2;

        inputIndependentCartridgeResource = document.createElement("input");
        inputIndependentCartridgeResource.type = "text";
        inputIndependentCartridgeResource.className = "form-control";
        inputIndependentCartridgeResource.name = "inputIndependentCartridgeResource";
        inputIndependentCartridgeResource.id = "inputIndependentCartridgeResource_" + id2;



        location.appendChild(paneCartridge);
        paneCartridge.appendChild(independentCartridgeRow);
        independentCartridgeRow.appendChild(countEachCartridge);
        independentCartridgeRow.appendChild(divTypeIndependentCartridge);
        divTypeIndependentCartridge.appendChild(selectTypeIndependentCartridge);
        independentCartridgeRow.appendChild(divModelIndependentCartridge);
        divModelIndependentCartridge.appendChild(selectModelIndependentCartridge);
        independentCartridgeRow.appendChild(divIndependentCartridgeResource);
        divIndependentCartridgeResource.appendChild(inputIndependentCartridgeResource);

    }

    $(document).ready(function () {
        $('.independentCartridgeType').children('select').selectize({
            create: true,
            showAddOptionOnCreate: true,
            addPrecedence: true,
            onChange: function (value) {
                let target = value.split(" ")[0];
                let currentModel;
                let idModel = this.$input.parents()[1].id.split("_")[3];
                for (let eachModel of $('.independentCartridgeModel').children('select')) {
                    if (idModel === eachModel.id.split("_")[1]) {
                        currentModel = eachModel;
                    }
                }
                currentModel.selectize.clearOptions();
                for (let yy of optionsCartridgeMap) {
                    if (target === yy[1]) {
                        currentModel.selectize.addOption({value: yy[0], text: yy[0]});
                        currentModel.selectize.addItem(yy[0]);
                        currentModel.selectize.setValue("", false);

                    }
                }
            }
        });
    });

    $(document).ready(function () {
        $('.independentCartridgeModel').children('select').selectize({
            create: true,
            showAddOptionOnCreate: true,
            addPrecedence: true
        });
    });

}


previousButton.addEventListener('click', (event) => {
    event.preventDefault();
    // Hide current tab
    tabPanels[currentStep].classList.add('hidden');
    tabTargets[currentStep].classList.remove('active');
    // Show the previous tab
    tabPanels[currentStep - 1].classList.remove('hidden');
    tabTargets[currentStep - 1].classList.add('active');
    currentStep -= 1;
    nextButton.removeAttribute('disabled');
    updateStatusDisplay();
});


function getContractPrinterDetails() {

    for (i = 0; i < document.getElementsByClassName('printer').length; i++) {
        let printer = new Object();

        printer = {
            printer: i + 1,
            manufacturer: document.getElementsByClassName('printer')[i].children.item(1).firstChild.value,
            model: document.getElementsByClassName('printer')[i].children.item(2).firstChild.value,
            serialNumber: document.getElementsByClassName('printer')[i].children.item(3).firstChild.value,
            inventoryNumber: document.getElementsByClassName('printer')[i].children.item(4).firstChild.value,
            cartridgeIncluded: document.getElementsByClassName('printer')[i].children.item(5).firstChild.children.item(0).checked,
            cartridgeIncludedType: document.getElementsByClassName('cartridgeInclude')[i].children.item(1).firstChild.value.split(" ")[0],
            cartridgeIncludeModel: document.getElementsByClassName('cartridgeInclude')[i].children.item(3).firstChild.value,
            cartridgeIncludeResource: document.getElementsByClassName('cartridgeInclude')[i].children.item(5).firstChild.value
        };
        printersArray.push(printer);
    }
}


function getContractCartridgeDetails() {

    for (i = 0; i < document.getElementsByClassName('independentCartridge').length; i++) {

        let cartridge = new Object();

        cartridge = {
            cartridge: i + 1,
            type: document.getElementsByClassName('independentCartridge')[i].children.item(1).firstChild.value.split(" ")[0],
            model: document.getElementsByClassName('independentCartridge')[i].children.item(2).firstChild.value,
            resource: document.getElementsByClassName('independentCartridge')[i].children.item(3).firstChild.value
        };

        cartridgesArray.push(cartridge);
    }
}



// const input = document.querySelector('#sendData');
// let isSwitch;
// добавить поле ввода

// function addInput(name, id, clname, location) {

//     label = document.createElement("label");
//     label.className = "form-label";
//     label.innerText = name;
//     inp = document.createElement("input");
//     inp.type = "text";
//     inp.className = "form-control";
//     inp.name = id;
//     inp.setAttribute("th:field", "*{" + id + "}");

//     flex = document.createElement("div");
//     divcol1 = document.createElement("div");
//     divcol2 = document.createElement("div");
//     flex.className = "row mb-2 text-end" + " " + clname;
//     divcol1.className = "col mb-6";
//     divcol2.className = "col mb-6";

//     location.appendChild(flex);
//     flex.appendChild(divcol1);
//     divcol1.appendChild(label);
//     flex.appendChild(divcol2);
//     divcol2.appendChild(inp);

// }











// Добавить label принтер location - input

// function addLabelPrinter(location) {

//     label = document.createElement("label");
//     label.className = "form-label";
//     label.innerText = "Производитель";
//     flex = document.createElement("div");
//     flex.className = "row mb-4 mt-5";
//     flex.id = "printerLabel";
//     divcol1 = document.createElement("div");
//     divcol1.className = "col-md-2 d-flex align-items-center justify-content-center";
//     divcol1.style = "height:50px";
//     location.appendChild(flex);
//     flex.appendChild(divcol1);
//     divcol1.appendChild(label);

//     label2 = document.createElement("label");
//     label2.className = "form-label";
//     label2.innerText = "Модель";
//     divcol2 = document.createElement("div");
//     divcol2.className = "col-md-2 d-flex align-items-center justify-content-center";
//     divcol2.style = "height:50px";
//     flex.appendChild(divcol2);
//     divcol2.appendChild(label2);

//     label3 = document.createElement("label");
//     label3.className = "form-label";
//     label3.innerText = "Серийный номер";
//     divcol3 = document.createElement("div");
//     divcol3.className = "col-md-3 d-flex align-items-center justify-content-center";
//     divcol3.style = "height:50px";
//     flex.appendChild(divcol3);
//     divcol3.appendChild(label3);

//     label4 = document.createElement("label");
//     label4.className = "form-label";
//     label4.innerText = "Инвентарный номер";
//     divcol4 = document.createElement("div");
//     divcol4.className = "col-md-3 d-flex align-items-center justify-content-center";
//     divcol4.style = "height:50px";
//     flex.appendChild(divcol4);
//     divcol4.appendChild(label4);

//     label5 = document.createElement("label");
//     label5.className = "form-label";
//     label5.innerText = "Картридж в комплекте";
//     divcol5 = document.createElement("div");
//     divcol5.className = "col-md-2 d-flex align-items-center justify-content-center";
//     divcol5.style = "height:50px";
//     flex.appendChild(divcol5);
//     divcol5.appendChild(label5);

// }


// function addPrinter(location) {
//     flex = document.createElement("div");
//     select = document.createElement("select");
//     select.className = "form-select text-start";
// //    select.setAttribute("th:field", "*{" + id + "}");
//     select.id = "manufacturer";
//     select.name = "manufacturer";

//    map.forEach((value, key) => {
//        let opt = new Option(key, value);
//        select.append(opt);
//    });

//     divcol1 = document.createElement("div");
//     flex.className = "row mb-2 text-end";
//     divcol1.className = "col-md-2 mb-2";
//     location.appendChild(flex);
//     flex.appendChild(divcol1);
//     divcol1.appendChild(select);

//     select2 = document.createElement("select");
//     select2.className = "form-select text-start";
//     select2.id = "modelPrinter";
//     select2.name = "modelPrinter";
//     divcol2 = document.createElement("div");
//     divcol2.className = "col-md-2 mb-2";
//     flex.appendChild(divcol2);
//     divcol2.appendChild(select2);


//     inp = document.createElement("input");
//     inp.type = "text";
//     inp.className = "form-control";
//     inp.name = "serialNumber";
//     inp.id = "serialNumber";
// //    inp.setAttribute("th:field", "*{" + id + "}");
//     divcol3 = document.createElement("div");
//     divcol3.className = "col-md-3 mb-2";
//     flex.appendChild(divcol3);
//     divcol3.appendChild(inp);

//     inp2 = document.createElement("input");
//     inp2.type = "text";
//     inp2.className = "form-control";
//     inp2.name = "inventoryNumber";
// //    inp.setAttribute("th:field", "*{" + id + "}");
//     divcol4 = document.createElement("div");
//     divcol4.className = "col-md-3 mb-2";
//     flex.appendChild(divcol4);
//     divcol4.appendChild(inp2);


//     inp3 = document.createElement("input");
//     inp3.className = "form-check-input";
//     inp3.name = "switch";
//     inp3.type = "checkbox";
//     inp3.id = "switch";
//     inp3.setAttribute("role", "switch");
// //    inp.setAttribute("th:field", "*{" + id + "}");
//     divcol5 = document.createElement("div");
//     divcol6 = document.createElement("div");
//     divcol5.className = "col-md-2 mb-2";
//     divcol6.className = "form-check form-switch text-center";
//     flex.appendChild(divcol5);
//     divcol5.appendChild(divcol6);
//     divcol6.appendChild(inp3);

// }


// Добавить Select

// function addSelect(name, id, clname, map, location) {
//     label = document.createElement("label");
//     label.className = "form-label";
//     label.innerText = name;
//     select = document.createElement("select");
//     select.className = "form-select text-start";
//     select.setAttribute("th:field", "*{" + id + "}");
//     select.id = id;
//     select.name = id;
//     map.forEach((value, key) => {
//         let opt = new Option(key, value);
//         select.append(opt);
//     });

//     flex = document.createElement("div");
//     divcol1 = document.createElement("div");
//     divcol2 = document.createElement("div");
//     flex.className = "row mb-2 text-end" + " " + clname;
//     divcol1.className = "col mb-6";
//     divcol2.className = "col mb-6";
//     location.appendChild(flex);
//     flex.appendChild(divcol1);
//     divcol1.appendChild(label);
//     flex.appendChild(divcol2);
//     divcol2.appendChild(select);

// };

// // Добавить чекбокс - переключатель

// function addSwitch(name, id, clname, location) {

//     flex = document.createElement("div");
//     divcol1 = document.createElement("div");
//     divcol2 = document.createElement("div");
//     label = document.createElement("label");
//     label.className = "form-check-label";
//     label.innerText = name;
//     inp = document.createElement("input");
//     inp.className = "form-check-input";
//     inp.name = id;
//     inp.type = "checkbox";
//     inp.id = id;
//     inp.setAttribute("role", "switch");
//     inp.setAttribute("th:field", "*{" + id + "}");
//     flex.className = "row mb-2 text-end" + " " + clname;
//     divcol1.className = "col mb-6 form-check form-switch";
//     divcol2.className = "col mb-6 form-check form-switch";
//     location.appendChild(flex);
//     flex.appendChild(divcol1);
//     flex.appendChild(divcol2);
//     divcol1.appendChild(label);
//     divcol2.appendChild(inp);

// };

//function addPrinter() {
//
//    addSelect("Производитель:", "manufacturer", "printer", optionsManufacturerMap, input);
//    addSelect("Модель:", "modelPrinter", "printer", optionsPrinterMap, input);
//    addInput("Серийный номер:", "serialNumberPrinter", "printer", input);
//    addInput("Инвентарный номер:", "inventoryNumberPrinter", "printer", input);
//    addSwitch("Картридж в комплекте", "isSwitched", "printer", input);
//}

// Выпадание списка для ввода данных принтера или картриджа

//document.querySelector('#selectObjectBuing').oninput = () => {
//
//
//
//
//    // Если из справочника выбран принтер 
//    if (document.querySelector('#selectObjectBuing').value === 'printer') {

//        divContainer = document.createElement("div");
//        divContainer.className = "container";
//        input.appendChild(divContainer);
//        addLabelPrinter(input);
//        addPrinter(input);



//        if (document.querySelector('.cartridge') !== null) {
//            console.log(document.querySelector('.printer'));
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//        }
//
//        addPrinter();
//
//        if (document.querySelector("#modelPrinter") !== null) {
//            modelPrinterSelect = document.querySelector("#modelPrinter");
//        }
//
//        document.querySelector("#isSwitched").addEventListener("change", function () {
//            if (document.querySelector("#isSwitched").checked === true) {
//                isSwitch = true;
//                let switchBlock = document.createElement("div");
//                switchBlock.id = "switchBlock";
//                addSelect("Тип:", "typeCartridge", "printer", optionsCartridgeTypeMap, input);
//                addSelect("Модель:", "modelCartridge", "printer", optionsCartridgeMap, input);
//                addInput("Номинальный ресурс:", "nominalResource", "printer", input);
//
//                if (document.querySelector("#typeCartridge") !== null) {
//                    typeCartridgeSelect = document.querySelector("#typeCartridge");
//                }
//
//                if (document.querySelector("#modelCartridge") !== null) {
//                    modelCartridgeSelect = document.querySelector("#modelCartridge");
//                }
//
//                $(document).ready(function () {
//                    $('#typeCartridge').selectize({
//                        create: true,
//                        showAddOptionOnCreate: true,
//                        addPrecedence: true,
//                        loadingClass: 'loading',
//                        onChange: function (value) {
//                            var target = value.split(" ");
//                            modelCartridgeSelect.selectize.clear();
//                            modelCartridgeSelect.selectize.clearOptions();
//                            for (var r of optionsCartridgeMap) {
//                                if (target[0] === r[1])
//                                    modelCartridgeSelect.selectize.addOption({value: r[0], text: r[0]});
//                                modelCartridgeSelect.selectize.addItem(r[0]);
//                            }
//
//                            modelCartridgeSelect.selectize.setValue("", false);
//                        }
//                    });
//                });
//                $(document).ready(function () {
//                    $('#modelCartridge').selectize({
//                        create: true,
//                        showAddOptionOnCreate: true,
//                        addPrecedence: true
//                    });
//                });
//
//            } else {
//                isSwitch = false;
//                input.removeChild(input.lastChild);
//                input.removeChild(input.lastChild);
//                input.removeChild(input.lastChild);
//            }
//            console.log(event.target);
//        });



// Если из справочника выбран картридж
//    } else if (document.querySelector('#selectObjectBuing').value === 'cartridge') {
//        if (document.querySelector('.printer') !== null) {
//            if (isSwitch) {
//                isSwitch = false;
//                input.removeChild(input.lastChild);
//                input.removeChild(input.lastChild);
//                input.removeChild(input.lastChild);
//            }
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//
//        }
//
//        addSelect("Тип:", "typeCartridge", "cartridge", optionsCartridgeTypeMap, input);
//        addSelect("Модель:", "modelCartridge", "cartridge", optionsCartridgeMap, input);
//        addInput("Номинальный ресурс:", "nominalResource", "cartridge", input);
//
//        if (document.querySelector("#typeCartridge") !== null) {
//            typeCartridgeSelect = document.querySelector("#typeCartridge");
//        }

//
//        $(document).ready(function () {
//            $('#typeCartridge').selectize({
//                create: true,
//                showAddOptionOnCreate: true,
//                addPrecedence: true,
//                loadingClass: 'loading',
//                onChange: function (value) {
//                    var target = value.split(" ");
//                    modelCartridgeSelect.selectize.clear();
//                    modelCartridgeSelect.selectize.clearOptions();
//                    for (var r of optionsCartridgeMap) {
//                        if (target[0] === r[1])
//                            modelCartridgeSelect.selectize.addOption({value: r[0], text: r[0]});
//                        modelCartridgeSelect.selectize.addItem(r[0]);
//                    }
//
//                    modelCartridgeSelect.selectize.setValue("", false);
//                }
//
//            });
//        });
//
//
//
//        $(document).ready(function () {
//            $('#modelCartridge').selectize({
//                create: true,
//                showAddOptionOnCreate: true,
//                addPrecedence: true
//
//
//            });
//        });

// Если ничего не выбрано
//    } else if (document.querySelector('#selectObjectBuing').value === "") {
//        if (document.querySelector('.printer') !== null) {
//            if (isSwitched) {
//                isSwitch = false;
//                input.removeChild(input.lastChild);
//                input.removeChild(input.lastChild);
//                input.removeChild(input.lastChild);
//            }
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//
//        } else {
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//            input.removeChild(input.lastChild);
//        }
//        console.log(document.querySelector('.printer'));
//        console.log(document.querySelector('.cartridge'));
//    }

//    if (document.querySelector("#modelPrinter") !== null) {
//        document.querySelector("#modelPrinter").addEventListener("change", function () {
//            document.querySelector("#modelPrinter").value = document.querySelector("#modelPrinter").options[document.querySelector("#modelPrinter").selectedIndex].value;
//            console.log(document.querySelector("#modelPrinter").options[document.querySelector("#modelPrinter").selectedIndex].value);
//
//        });
//    }

//    const sendForm = document.querySelector("#sendForm");
//    const email = document.querySelector("#email");
//    sendForm.addEventListener("#addBtn", function (evt) {
//        evt.preventDefault();
//        if (document.querySelector('#selectObjectBuing').value === "") {
//            alert('Выберите объект закупки');
//            return;
//        }
//
//        this.submit();
//    });

// Связанные списки производитель и модель принтера

$(document).ready(function () {
    $('#manufacturer').selectize({
        create: true,
        showAddOptionOnCreate: true,
        addPrecedence: true,
        loadingClass: 'loading',
        onChange: function (value) {
            modelPrinterSelect.selectize.clear();
            modelPrinterSelect.selectize.clearOptions();
            for (var el of printers) {
                if (value === el[1])
                    modelPrinterSelect.selectize.addOption({value: el[0], text: el[0]});
                modelPrinterSelect.selectize.addItem(el[0]);
            }

            modelPrinterSelect.selectize.setValue("", false);
        }

    });
});




$(document).ready(function () {
    if (document.getElementById('checkboxPrinter') !== null) {
        chbxPrinter = document.getElementById('checkboxPrinter');
        inpAmountPrinter = document.getElementById('amountPrinterInput');
        chbxPrinter.addEventListener('change', function () {

            if (chbxPrinter.checked === true) {
                inpAmountPrinter.disabled = false;
            } else {
                inpAmountPrinter.value = "";
                inpAmountPrinter.disabled = true;
            }

        });
    }

    if (document.getElementById('checkboxCartridge') !== null) {

        chbxCartridge = document.getElementById('checkboxCartridge');
        inpAmountCartridge = document.getElementById('amountCartridgeInput');
        chbxCartridge.addEventListener('change', function () {

            if (chbxCartridge.checked === true) {
                inpAmountCartridge.disabled = false;
            } else {
                inpAmountCartridge.value = "";
                inpAmountCartridge.disabled = true;
            }

        });

    }

});







