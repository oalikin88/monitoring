/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
let manufacturePrinterSelect;
let modelCartridgeSelect;
let manuf;
let cartridgesMap = new Map();
let targetSwitch;
let bufPrinterCount;
let bufCartridgeCount;
let finalPrintersList;
let finalCartridgesList;
let selectizeManufacturerIdChoiseArray;
let selectizeModelFromChoisesManufacturer;
let manufacturerValueFromSelectize;
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
let printersArray;
let cartridgesArray;
let printersPlusCartridges = new Array();
let contract = new Object();

let formData = new FormData();
let optionsManufacturerMap = new Map();
let printers = new Map();
let amountCartridges = document.querySelector('input[id="amountCartridgeInput"]');
let modelPrinterChoice;
// Справочник принтеров


// Отключение клавиши enter на форме
document.addEventListener('keypress', function (e) {
    if (e.keyCode === 13 || e.which === 13) {
        e.preventDefault();
        return false;
    }

});

function removeRow() {
    this.closest('.pane').remove();
}


document.addEventListener('submit', function (event) {
    let tempArray1 = new Array();
    tempArray1.push(contract);
    if (printersArray && cartridgesArray) {
        let tempArray2 = new Array();
        tempArray2 = tempArray1.concat(printersArray);
        printersPlusCartridges = tempArray2.concat(cartridgesArray);
    } else if (!cartridgesArray && printersArray) {
        printersPlusCartridges = tempArray1.concat(printersArray);
    } else if (!printersArray && cartridgesArray) {
        printersPlusCartridges = tempArray1.concat(cartridgesArray);
    }
    event.preventDefault();
    $.ajax({
        type: "POST",
        url: "/main",
        data: JSON.stringify(printersPlusCartridges),
        success: function () {
            alert("Контракт №" + contract.numberContract + " успешно добавлен в базу данных.")
            event.target.reset();
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
    switch (currentStep) {
        case 0:

            if (!$('#checkboxPrinter')[0].checked) {
                tabPanels[currentStep + 2].classList.remove('hidden');
                tabTargets[currentStep + 2].classList.add('active');
                currentStep += 2;
            } else {
                tabPanels[currentStep + 1].classList.remove('hidden');
                tabTargets[currentStep + 1].classList.add('active');
                currentStep += 1;
            }
            break;
        case 1:
            getContractPrinterDetails();

            if (!$('#checkboxCartridge')[0].checked) {
                tabPanels[currentStep + 2].classList.remove('hidden');
                tabTargets[currentStep + 2].classList.add('active');
                currentStep += 2;
            } else {
                tabPanels[currentStep + 1].classList.remove('hidden');
                tabTargets[currentStep + 1].classList.add('active');
                currentStep += 1;
            }
            getContract();
            break;
        case 2:
            getContractCartridgeDetails();
            tabPanels[currentStep + 1].classList.remove('hidden');
            tabTargets[currentStep + 1].classList.add('active');
            currentStep += 1;
            getContract();
            break;
    }


    validateEntry();
    getContentForTablist();
    updateStatusDisplay();

});


previousButton.addEventListener('click', (event) => {
    event.preventDefault();
    // Hide current tab
    tabPanels[currentStep].classList.add('hidden');
    tabTargets[currentStep].classList.remove('active');
    switch (currentStep) {
        case 3:

            if (!contract.amountCartridges) {
                finalPrintersList = document.getElementsByClassName('printersPane')[0].children.length;
                printersArray = [];
                for (i = 0; i < finalPrintersList; i++) {
                    document.getElementsByClassName('printersPane')[0].removeChild(document.getElementsByClassName('printersPane')[0].lastChild);
                }
                tabPanels[currentStep - 2].classList.remove('hidden');
                tabTargets[currentStep - 2].classList.add('active');
                currentStep -= 2;
            } else {
                finalCartridgesList = document.getElementsByClassName('cartridgesPane')[0].children.length;
                cartridgesArray = [];
                for (i = 0; i < finalCartridgesList; i++) {
                    document.getElementsByClassName('cartridgesPane')[0].removeChild(document.getElementsByClassName('cartridgesPane')[0].lastChild);
                }

                tabPanels[currentStep - 1].classList.remove('hidden');
                tabTargets[currentStep - 1].classList.add('active');
                currentStep -= 1;

            }
            break;
        case 2:
            bufCartridgeCount = $('.independentCartridge').length;
            if (!contract.amountPrinters) {
                tabPanels[currentStep - 2].classList.remove('hidden');
                tabTargets[currentStep - 2].classList.add('active');
                currentStep -= 2;
            } else {
                printersArray = [];
                finalPrintersList = document.getElementsByClassName('printersPane')[0].children.length;
                for (i = 0; i < finalPrintersList; i++) {
                    document.getElementsByClassName('printersPane')[0].removeChild(document.getElementsByClassName('printersPane')[0].lastChild);
                }
                tabPanels[currentStep - 1].classList.remove('hidden');
                tabTargets[currentStep - 1].classList.add('active');
                currentStep -= 1;
            }
            break;
        case 1:

            bufPrinterCount = $('.printer').length;
            tabPanels[currentStep - 1].classList.remove('hidden');
            tabTargets[currentStep - 1].classList.add('active');
            currentStep -= 1;
            break;
    }
    nextButton.removeAttribute('disabled');
    updateStatusDisplay();
});


function getFinalPage() {

    let input = tabPanels[0].querySelector('.form-control');


    if (!document.getElementById('contractInfo')) {

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
       
      
        respage.appendChild(contractInfo);
        respage.appendChild(objectBuingRowTitle);
        respage.appendChild(objectBuingRow);
        contractInfo.appendChild(labelNumberContract);
        contractInfo.appendChild(labelDateStartContract);
        contractInfo.appendChild(labelDateEndContract);
        objectBuingRowTitle.appendChild(objectBuing);

        

    }

//    if (input.form.elements.amountPrinterInput.value) {
//        containerForPrinters = document.createElement("div");
//        containerForPrinters.className = "col contentPrinters";
//        labelPrintersAmount.innerText = "Принтеры: " + input.form.elements.amountPrinterInput.value;
//        objectBuingRow.appendChild(labelPrintersAmount);
//
//    }



    if (contract.amountPrinters > 0 && document.getElementsByClassName('contentPrinters').length < 1) {
        containerForPrinters = document.createElement("div");
        containerForPrinters.className = "col contentPrinters";
        printerTitleRow = document.createElement("div");
        labelPrintersAmount = document.createElement("div");
        labelPrintersAmount.className = "col text-center";
        labelPrintersAmount.innerText = "Принтеры: " + getAmountPrinters();
        objectBuingRow.appendChild(labelPrintersAmount);
        printerTitleRow.className = "row";
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
        content = document.createElement("div");
        content.className = "printersPane";

        respage.appendChild(containerForPrinters);
        containerForPrinters.appendChild(printerTitleRow);
        printerTitleRow.appendChild(printerTitle);
        containerForPrinters.appendChild(labelsPrinterRow);
        labelsPrinterRow.appendChild(labelCountPrinter);
        labelsPrinterRow.appendChild(labelManufacturerPrinter);
        labelsPrinterRow.appendChild(labelModelPrinter);
        labelsPrinterRow.appendChild(labelSerialNumberPrinter);
        labelsPrinterRow.appendChild(labelInventoryNumberPrinter);
        labelsPrinterRow.appendChild(labelCartridgeIncludePrinter);
        containerForPrinters.appendChild(content);


    }

    if ($('#checkboxCartridge')[0].checked && $('.contentCartridges')[0] == null) {

        labelCartridgeAmount = document.createElement("div");
        labelCartridgeAmount.className = "col text-center";
        labelCartridgeAmount.innerText = "Картриджи: " + getAmount();
        objectBuingRow.appendChild(labelCartridgeAmount);
        containerForCartridges = document.createElement("div");
        containerForCartridges.className = "contentCartridges";

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
        labelIndependedCartridgeType.className = "col-md-2 d-flex align-items-center justify-content-center";
        labelIndependedCartridgeType.innerText = "Тип";
        labelIndependedCartridgeModel = document.createElement("div");
        labelIndependedCartridgeModel.className = "col-md-2 d-flex align-items-center justify-content-center";
        labelIndependedCartridgeModel.innerText = "Модель";

        labelIndependedCartridgeItemCode = document.createElement("div");
        labelIndependedCartridgeItemCode.className = "col-md-2 d-flex align-items-center justify-content-center";
        labelIndependedCartridgeItemCode.innerText = "Номенклатурный номер";

        labelIndependedCartridgeNameMaterial = document.createElement("div");
        labelIndependedCartridgeNameMaterial.className = "col-md-3 d-flex align-items-center justify-content-center";
        labelIndependedCartridgeNameMaterial.innerText = "Наименование расходного материала";

        labelIndependedCartridgeAmount = document.createElement("div");
        labelIndependedCartridgeAmount.className = "col-md-1 d-flex align-items-center justify-content-center";
        labelIndependedCartridgeAmount.innerText = "Количество";

        contentPaneForCartridges = document.createElement("div");
        contentPaneForCartridges.className = "cartridgesPane";

        respage.appendChild(containerForCartridges);

        containerForCartridges.appendChild(independedCartridgeTitleRow);
        independedCartridgeTitleRow.appendChild(independedCartridgeTitle);
        containerForCartridges.appendChild(labelsIndependedCartridgesRow);
        labelsIndependedCartridgesRow.appendChild(labelCountIndependedCartridge);
        labelsIndependedCartridgesRow.appendChild(labelIndependedCartridgeType);
        labelsIndependedCartridgesRow.appendChild(labelIndependedCartridgeModel);
        labelsIndependedCartridgesRow.appendChild(labelIndependedCartridgeItemCode);
        labelsIndependedCartridgesRow.appendChild(labelIndependedCartridgeNameMaterial);
        labelsIndependedCartridgesRow.appendChild(labelIndependedCartridgeAmount);
        containerForCartridges.appendChild(contentPaneForCartridges);

    }








    //Раздел принтеры



    // Добавление принтеров на страницу
    if (document.getElementsByClassName('printersPane')[0]) {

        if (document.getElementsByClassName('printersPane')[0].children.length == 0) {

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
                content.appendChild(paneObject);
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

                    //cartridgeIncludeModel
                    paneObject.appendChild(cartridgeTitle);
                    cartridgeTitle.appendChild(cartridgeTitleInner);
                    paneObject.appendChild(cartridgeIncludeRow);
                    cartridgeIncludeRow.appendChild(cartridgeIncludeTypeLabel);
                    cartridgeIncludeRow.appendChild(cartridgeIncludeTypeValue);
                    cartridgeIncludeRow.appendChild(cartridgeIncludeModelLabel);
                    cartridgeIncludeRow.appendChild(cartridgeIncludeModelValue);

                } else {
                    includeCartridgePrinter.innerText = "нет";
                }

                printerRow.appendChild(includeCartridgePrinter);
            }
        }

    }


    // Добавление картриджей на страницу

    if (document.getElementsByClassName('cartridgesPane').length > 0) {
        for (i = 0; i < cartridgesArray.length; i++) {

            paneObjectCartridge = document.createElement("div");
            paneObjectCartridge.className = "paneFinal mb-3";
            independedCartridgeRow = document.createElement("div");
            independedCartridgeRow.className = "row mb-3 pt-3 pb-3";
            countEachIndependedCartridge = document.createElement("div");
            countEachIndependedCartridge.className = "col-md-1 d-flex align-items-center justify-content-center";
            countEachIndependedCartridge.innerText = i + 1;
            typeEachIndependedCartridge = document.createElement("div");
            typeEachIndependedCartridge.className = "col-md-2 d-flex align-items-center justify-content-center";
            typeEachIndependedCartridge.innerText = cartridgesArray[i].type;
            modelEachIndependedCartridge = document.createElement("div");
            modelEachIndependedCartridge.className = "col-md-2 d-flex align-items-center justify-content-center";
            modelEachIndependedCartridge.innerText = cartridgesArray[i].model;

            itemCodeEachIndependedCartridge = document.createElement("div");
            itemCodeEachIndependedCartridge.className = "col-md-2 d-flex align-items-center justify-content-center";
            itemCodeEachIndependedCartridge.innerText = cartridgesArray[i].itemCode;

            nameMaterialEachIndependedCartridge = document.createElement("div");
            nameMaterialEachIndependedCartridge.className = "col-md-3 d-flex align-items-center justify-content-center";
            nameMaterialEachIndependedCartridge.innerText = cartridgesArray[i].nameMaterial;

            amountEachIndependedCartridge = document.createElement("div");
            amountEachIndependedCartridge.className = "col-md-1 d-flex align-items-center justify-content-center";
            amountEachIndependedCartridge.innerText = cartridgesArray[i].amount + " шт.";

            contentPaneForCartridges.appendChild(paneObjectCartridge);
            paneObjectCartridge.appendChild(independedCartridgeRow);
            independedCartridgeRow.appendChild(countEachIndependedCartridge);
            independedCartridgeRow.appendChild(typeEachIndependedCartridge);
            independedCartridgeRow.appendChild(modelEachIndependedCartridge);
            independedCartridgeRow.appendChild(itemCodeEachIndependedCartridge);
            independedCartridgeRow.appendChild(nameMaterialEachIndependedCartridge);
            independedCartridgeRow.appendChild(amountEachIndependedCartridge);



        }


    }
}


function getContract() {

    contract = {
        numberContract: document.getElementsByClassName('contract')[0].children.item(0).firstElementChild.value,
        dateStartContract: document.getElementsByClassName('contract')[0].children.item(1).firstElementChild.value,
        dateEndContract: document.getElementsByClassName('contract')[0].children.item(2).firstElementChild.value,
        amountPrinters: getAmountPrinters(),
        amountCartridges: getAmount()
    }



}

let addRow = document.querySelector('#addBtn');
addRow.addEventListener('click', function () {
    count = $('.independentCartridge').length;
    addCartridges(++count, cartrLoc);
});

let addRowPrinter = document.querySelector('#addBtnPrinter');

addRowPrinter.addEventListener('click', function() {
    count = $('.pane').length;
    addPrinter(++count, tt);
})


function getContentForTablist() {
    let input = tabPanels[0].querySelector('.form-control');
    if (currentStep == 1) {
        if($('.printer')[0] == null) {
        addPrintersInfo(tt);
    }
    } else if (currentStep == 2) {
        if ($('.independentCartridge')[0] == null) {
            addCartridgesInfo(cartrLoc);
        }





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
function addPrintersInfo(location) {

    if (!document.getElementById('printerLabel')) {

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
        pane.className = "pane panePrinter";
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
        divcol5.className = "col-md-1 d-flex align-items-center justify-content-center";
        divcol5.style = "height:50px";
        flex.appendChild(divcol5);
        divcol5.appendChild(label5);
        label5 = document.createElement("label");
        label5.className = "form-label";
        label5.innerText = "";
        divcol5 = document.createElement("div");
        divcol5.className = "col-md-1 d-flex align-items-center justify-content-center";
        divcol5.style = "height:50px";
        flex.appendChild(divcol5);
        divcol5.appendChild(label5);

    }



    id2 = Math.floor(Math.random() * 10000 * 25654);
    flex = document.createElement("div");
    pane = document.createElement("div");
    count = document.createElement("div");
    count.className = "col-md-1 mb-2 d-flex align-items-center justify-content-center";
    count.innerText = 1;
    pane.className = "pane mt-3 mb-3";
    select = document.createElement("select");
    select.className = "form-select text-start";
    select.id = "manufacturer_" + id2;
    select.name = "manufacturer";

    divcol1 = document.createElement("div");
    flex.className = "row printer mb-2 mt-2 px-3 text-end";
    flex.id = "row_printer_" + 1 + "_" + id2;
    divcol1.className = "col-md-2 mb-2 manufacturer";
    location.appendChild(pane);
    pane.appendChild(flex);
    flex.appendChild(count);
    flex.appendChild(divcol1);
    divcol1.appendChild(select);
    select2 = document.createElement("select");
    select2.className = "form-select text-start modelPrinter";
    select2.id = "modelPrinter_" + id2;
    select2.name = "modelPrinter";
    select2.disabled = true;
    divcol2 = document.createElement("div");
    divcol2.className = "col-md-2 mb-2 model";
    flex.appendChild(divcol2);
    divcol2.appendChild(select2);
    inp = document.createElement("input");
    inp.type = "text";
    inp.className = "form-control";
    inp.name = "serialNumber";
    inp.id = "serialNumber_" + id2;
    divcol3 = document.createElement("div");
    divcol3.className = "col-md-2 mb-2 serialNumber";
    flex.appendChild(divcol3);
    divcol3.appendChild(inp);
    inp2 = document.createElement("input");
    inp2.type = "text";
    inp2.className = "form-control";
    inp2.name = "inventoryNumber";
    inp2.id = "inventoryNumber_" + id2;
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
    inp3.disabled = true;
    divcol5 = document.createElement("div");
    divcol6 = document.createElement("div");
    divcol5.className = "col-md-1 mb-2";
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
    selectModelCartridge.className = "form-select text-start cartridgeModelChoice";
    selectModelCartridge.id = "cartridgeModel_" + id2;
    selectModelCartridge.name = "cartridgeModel";
    selectModelCartridge.disabled = true;
    flexCartridge.appendChild(divcolModelLabel);
    flexCartridge.appendChild(divcolModelSelect);
    divcolModelSelect.appendChild(selectModelCartridge);

    let typeChoice;
    $(typeCartridgeSelect).selectize({
        create: false,
        valueField: 'type',
        labelField: 'type',
        searchField: "type",
        placeholder: "Выберите из списка",
        options: [{type: "Оригинальный"},
            {type: "Совместимый"},
            {type: "Стартовый"}],
        onChange: function (value) {
            if (value !== '') {
                selectizeModelFromChoisesTypeCartridge = $(this.$control_input[0].closest('.cartridgeInclude')).find('.cartridgeModelChoice')[0];
                typeChoice = $(this.$control_input[0].closest('.cartridgeInclude')).find('.cartridgeType')[0].children[0].innerText;
                selectizeModelFromChoisesTypeCartridge.selectize.enable();
                $(this.$control_input[0].closest('.cartridgeInclude')).find('.cartridgeModelChoice')[0].disabled = false;
                typeValueFromSelectize = value;
                $.ajax({
                    url: "/cartridgebymodelprinter/" + encodeURIComponent(value),
                    type: 'GET',
                    dataType: 'json', // added data type
                    data:{model: modelPrinterChoice},
                    success: function (res) {
                        let keys = Object.keys(selectizeModelFromChoisesTypeCartridge.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeModelFromChoisesTypeCartridge.selectize.removeOption(keys[i]);
                        }
                        res.forEach(model => {
                            selectizeModelFromChoisesTypeCartridge.selectize.addOption(model);
                            selectizeModelFromChoisesTypeCartridge.selectize.addItem(model);
                        });

                        selectizeModelFromChoisesTypeCartridge.selectize.refreshOptions();
                        selectizeModelFromChoisesTypeCartridge.selectize.clear();
                        selectizeModelFromChoisesTypeCartridge.selectize.enable();
                    }
                });
            }
        }
    });

    $(modelCartridgeSelect).selectize({
        create: true,
//                    showAddOptionOnCreate: true,
        placeholder: "Выберите из списка",
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        preload: 'focus',
        create: false,
       
        load: function (query, callback) {
            $.ajax({
                url: '/cartridgebymodelprinter/' + encodeURIComponent(typeChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        }
    });


    let manufacturerChoice;
    $('.model').children('select').selectize({
        preload: 'focus',
        create: function (input, callback) {
            manufacturerChoice = $(this.$control_input[0].closest('.printer')).find('.manufacturerChoice')[0].innerText;
            selectizeManufacturerFromChoisesManufacturer = $(this.$control_input[0].closest('.printer')).find('.modelPrinter')[0];
            $.ajax({
                url: '/models',
                type: 'POST',
                data: {target: input,
                    manufacturer: manufacturerChoice},
                success: function () {
                    selectizeManufacturerFromChoisesManufacturer.selectize.addOption({value: input, text: input});
                    selectizeManufacturerFromChoisesManufacturer.selectize.addItem(input);
                    selectizeManufacturerFromChoisesManufacturer.selectize.refreshOptions();
                    callback({model: input});
                }
            });
        },
        load: function (query, callback) {
            $.ajax({
                url: '/models/' + encodeURIComponent(manufacturerChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        },
         onChange: function(value) {
            modelPrinterChoice = value;
        },
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        showAddOptionOnCreate: true,
        placeholder: 'Выберите из списка',
        options: []

    });
    
    


    $('.manufacturer').children('select').selectize({
        create: function (input, callback) {
            selectizeManufacturerFromChoisesManufacturer = $(this.$control_input[0].closest('.printer')).find('.manufacturerChoice')[0];
            $.ajax({
                url: '/manufacturers',
                type: 'POST',
                data: {value: input},
                success: function () {
                    selectizeManufacturerFromChoisesManufacturer.selectize.addOption({value: input, text: input});
                    selectizeManufacturerFromChoisesManufacturer.selectize.addItem(input);
                    selectizeManufacturerFromChoisesManufacturer.selectize.refreshOptions();
                    callback({manufacturer: input});
                }
            });
        },
        preload: 'focus',
        placeholder: "Выберите из списка",
        valueField: 'manufacturer',
        labelField: 'manufacturer',
        searchField: "manufacturer",
        showAddOptionOnCreate: true,
        load: function (query, callback) {
            $.ajax({
                url: '/manufacturers',
                type: 'GET',
                dataType: 'json',
                data: {manufacturer: query},
                error: callback,
                success: callback
            });
        },
        onChange: function (value) {
            if (value !== '') {
                $(this.$control_input[0].closest('.printer')).find('.printerSwitch')[0].children[0].disabled = false;
                manufacturerValueFromSelectize = value;
                selectizeModelFromChoisesManufacturer = $(this.$control_input[0].closest('.printer')).find('.modelPrinter')[0];
                $.ajax({
                    url: "/models/" + encodeURIComponent(value),
                    type: 'GET',
                    dataType: 'json', // added data type
                    data: {manufacturer: value},
                    success: function (res) {
                        let keys = Object.keys(selectizeModelFromChoisesManufacturer.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeModelFromChoisesManufacturer.selectize.removeOption(keys[i]);
                        }
                        res.forEach(model => {
                            selectizeModelFromChoisesManufacturer.selectize.addOption(model);
                            selectizeModelFromChoisesManufacturer.selectize.addItem(model);
                        });

                        selectizeModelFromChoisesManufacturer.selectize.refreshOptions();
                        selectizeModelFromChoisesManufacturer.selectize.clear();
                        selectizeModelFromChoisesManufacturer.selectize.enable();
                    }
                });
            }
        }
    });





}



if (document.getElementsByName('cartridgeType') !== null) {
    typeCartridgeSelect = document.getElementsByName('cartridgeType');
}

if (document.getElementsByName('cartridgeModel') !== null) {
    modelCartridgeSelect = document.getElementsByName('cartridgeModel');
}



$(document).ready(function () {









    let typeChoice;
    $(typeCartridgeSelect).selectize({
        create: false,
        valueField: 'type',
        labelField: 'type',
        searchField: "type",
        placeholder: "Выберите из списка",
        options: [{type: "Оригинальный"},
            {type: "Совместимый"},
            {type: "Стартовый"}],
        onChange: function (value) {
            if (value !== '') {
                selectizeModelFromChoisesTypeCartridge = $(this.$control_input[0].closest('.cartridgeInclude')).find('.cartridgeModelChoice')[0];
                typeChoice = $(this.$control_input[0].closest('.cartridgeInclude')).find('.cartridgeType')[0].children[0].innerText;
                selectizeModelFromChoisesTypeCartridge.selectize.enable();
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeModelInput')[0].disabled = false;
                typeValueFromSelectize = value;
                $.ajax({
                    url: "/cartridge/" + encodeURIComponent(value),
                    type: 'GET',
                    dataType: 'json', // added data type
                    success: function (res) {
                        let keys = Object.keys(selectizeModelFromChoisesTypeCartridge.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeModelFromChoisesTypeCartridge.selectize.removeOption(keys[i]);
                        }
                        res.forEach(model => {
                            selectizeModelFromChoisesTypeCartridge.selectize.addOption(model);
                            selectizeModelFromChoisesTypeCartridge.selectize.addItem(model);
                        });

                        selectizeModelFromChoisesTypeCartridge.selectize.refreshOptions();
                        selectizeModelFromChoisesTypeCartridge.selectize.clear();
                        selectizeModelFromChoisesTypeCartridge.selectize.enable();
                    }
                });
            }
        }
    });

    $(modelCartridgeSelect).selectize({
        create: true,
//                    showAddOptionOnCreate: true,
        placeholder: "Выберите из списка",
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        preload: 'focus',
        create: false,
        load: function (query, callback) {
            $.ajax({
                url: '/cartridge/' + encodeURIComponent(typeChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        }
    });

    let manufacturerChoice;
    $('.model').children('select').selectize({
        preload: 'focus',
        create: function (input, callback) {
            manufacturerChoice = $(this.$control_input[0].closest('.printer')).find('.manufacturerChoice')[0].innerText;
            selectizeManufacturerFromChoisesManufacturer = $(this.$control_input[0].closest('.printer')).find('.modelPrinter')[0];
            $.ajax({
                url: '/models',
                type: 'POST',
                data: {target: input,
                    manufacturer: manufacturerChoice},
                success: function () {
                    selectizeManufacturerFromChoisesManufacturer.selectize.addOption({value: input, text: input});
                    selectizeManufacturerFromChoisesManufacturer.selectize.addItem(input);
                    selectizeManufacturerFromChoisesManufacturer.selectize.refreshOptions();
                    callback({model: input});
                }
            });
        },
        load: function (query, callback) {
            $.ajax({
                url: '/models/' + encodeURIComponent(manufacturerChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        },
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        showAddOptionOnCreate: true,
        placeholder: 'Выберите из списка',
        options: []

    });

    $('.manufacturer').children('select').selectize({
        create: function (input, callback) {
            selectizeManufacturerFromChoisesManufacturer = $(this.$control_input[0].closest('.printer')).find('.manufacturerChoice')[0];
            $.ajax({
                url: '/manufacturers',
                type: 'POST',
                data: {value: input},
                success: function () {
                    selectizeManufacturerFromChoisesManufacturer.selectize.addOption({value: input, text: input});
                    selectizeManufacturerFromChoisesManufacturer.selectize.addItem(input);
                    selectizeManufacturerFromChoisesManufacturer.selectize.refreshOptions();
                    callback({manufacturer: input});
                }
            });
        },
        preload: 'focus',
        placeholder: "Выберите из списка",
        valueField: 'manufacturer',
        labelField: 'manufacturer',
        searchField: "manufacturer",
        showAddOptionOnCreate: true,
        load: function (query, callback) {
            $.ajax({
                url: '/manufacturers',
                type: 'GET',
                dataType: 'json',
                data: {manufacturer: query},
                error: callback,
                success: callback
            });
        },
        onChange: function (value) {
            if (value !== '') {
                $(this.$control_input[0].closest('.printer')).find('.printerSwitch')[0].children[0].disabled = false;
                manufacturerValueFromSelectize = value;
                selectizeModelFromChoisesManufacturer = $(this.$control_input[0].closest('.printer')).find('.modelPrinter')[0];
                $.ajax({
                    url: "/models/" + encodeURIComponent(value),
                    type: 'GET',
                    dataType: 'json', // added data type
                    data: {manufacturer: value},
                    success: function (res) {
                        let keys = Object.keys(selectizeModelFromChoisesManufacturer.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeModelFromChoisesManufacturer.selectize.removeOption(keys[i]);
                        }
                        res.forEach(model => {
                            selectizeModelFromChoisesManufacturer.selectize.addOption(model);
                            selectizeModelFromChoisesManufacturer.selectize.addItem(model);
                        });

                        selectizeModelFromChoisesManufacturer.selectize.refreshOptions();
                        selectizeModelFromChoisesManufacturer.selectize.clear();
                        selectizeModelFromChoisesManufacturer.selectize.enable();
                    }
                });
            }
        }
    });
});


function addPrinter(amount, location) {





    id2 = Math.floor(Math.random() * 10000 * 25654);
    flex = document.createElement("div");
    pane = document.createElement("div");
    count = document.createElement("div");
    count.className = "col-md-1 mb-2 d-flex align-items-center justify-content-center";
    count.innerText = amount;
    pane.className = "pane mt-3 mb-3";
    select = document.createElement("select");
    select.className = "form-select text-start";
    select.id = "manufacturer_" + id2;
    select.name = "manufacturer";

    divcol1 = document.createElement("div");
    flex.className = "row printer mb-2 mt-2 px-3 text-end";
    flex.id = "row_printer_" + amount + "_" + id2;
    divcol1.className = "col-md-2 mb-2 manufacturer";
    location.appendChild(pane);
    pane.appendChild(flex);
    flex.appendChild(count);
    flex.appendChild(divcol1);
    divcol1.appendChild(select);
    select2 = document.createElement("select");
    select2.className = "form-select text-start modelPrinter";
    select2.id = "modelPrinter_" + id2;
    select2.name = "modelPrinter";
    select2.disabled = true;
    divcol2 = document.createElement("div");
    divcol2.className = "col-md-2 mb-2 model";
    flex.appendChild(divcol2);
    divcol2.appendChild(select2);
    inp = document.createElement("input");
    inp.type = "text";
    inp.className = "form-control";
    inp.name = "serialNumber";
    inp.id = "serialNumber_" + id2;
    divcol3 = document.createElement("div");
    divcol3.className = "col-md-2 mb-2 serialNumber";
    flex.appendChild(divcol3);
    divcol3.appendChild(inp);
    inp2 = document.createElement("input");
    inp2.type = "text";
    inp2.className = "form-control";
    inp2.name = "inventoryNumber";
    inp2.id = "inventoryNumber_" + id2;
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
    inp3.disabled = true;
    divcol5 = document.createElement("div");
    divcol6 = document.createElement("div");
    divcol5.className = "col-md-1 mb-2";
    divcol6.className = "form-check form-switch printerSwitch text-center";
    flex.appendChild(divcol5);
    divcol5.appendChild(divcol6);
    divcol6.appendChild(inp3);
    
    btnRem = document.createElement("button");
    btnRem.className = "btn btn-secondary";
    btnRem.type = "button";
    btnRem.innerText = "Удалить";
    divcol7 = document.createElement("div");
    divcol7.className = "col-md-1 mb-2";
    flex.appendChild(divcol7);
    divcol7.appendChild(btnRem);
    
    btnRem.addEventListener('click', removeRow);
    

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
    selectModelCartridge.className = "form-select text-start cartridgeModelChoice";
    selectModelCartridge.id = "cartridgeModel_" + id2;
    selectModelCartridge.name = "cartridgeModel";
    selectModelCartridge.disabled = true;
    flexCartridge.appendChild(divcolModelLabel);
    flexCartridge.appendChild(divcolModelSelect);
    divcolModelSelect.appendChild(selectModelCartridge);

    let typeChoice;
    $(typeCartridgeSelect).selectize({
        create: false,
        valueField: 'type',
        labelField: 'type',
        searchField: "type",
        placeholder: "Выберите из списка",
        options: [{type: "Оригинальный"},
            {type: "Совместимый"},
            {type: "Стартовый"}],
        onChange: function (value) {
            if (value !== '') {
                selectizeModelFromChoisesTypeCartridge = $(this.$control_input[0].closest('.cartridgeInclude')).find('.cartridgeModelChoice')[0];
                typeChoice = $(this.$control_input[0].closest('.cartridgeInclude')).find('.cartridgeType')[0].children[0].innerText;
                selectizeModelFromChoisesTypeCartridge.selectize.enable();
                $(this.$control_input[0].closest('.cartridgeInclude')).find('.cartridgeModelChoice')[0].disabled = false;
                typeValueFromSelectize = value;
                $.ajax({
                     url: "/cartridgebymodelprinter/" + encodeURIComponent(value),
                    type: 'GET',
                    dataType: 'json', // added data type
                    data:{model: modelPrinterChoice},
                    success: function (res) {
                        let keys = Object.keys(selectizeModelFromChoisesTypeCartridge.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeModelFromChoisesTypeCartridge.selectize.removeOption(keys[i]);
                        }
                        res.forEach(model => {
                            selectizeModelFromChoisesTypeCartridge.selectize.addOption(model);
                            selectizeModelFromChoisesTypeCartridge.selectize.addItem(model);
                        });

                        selectizeModelFromChoisesTypeCartridge.selectize.refreshOptions();
                        selectizeModelFromChoisesTypeCartridge.selectize.clear();
                        selectizeModelFromChoisesTypeCartridge.selectize.enable();
                    }
                });
            }
        }
    });

    $(modelCartridgeSelect).selectize({
        create: true,
//                    showAddOptionOnCreate: true,
        placeholder: "Выберите из списка",
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        preload: 'focus',
        create: false,
        load: function (query, callback) {
            $.ajax({
                url: '/cartridgebymodelprinter/' + encodeURIComponent(typeChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        }
    });


    let manufacturerChoice;
    $('.model').children('select').selectize({
        preload: 'focus',
        create: function (input, callback) {
            manufacturerChoice = $(this.$control_input[0].closest('.printer')).find('.manufacturerChoice')[0].innerText;
            selectizeManufacturerFromChoisesManufacturer = $(this.$control_input[0].closest('.printer')).find('.modelPrinter')[0];
            $.ajax({
                url: '/models',
                type: 'POST',
                data: {target: input,
                    manufacturer: manufacturerChoice},
                success: function () {
                    selectizeManufacturerFromChoisesManufacturer.selectize.addOption({value: input, text: input});
                    selectizeManufacturerFromChoisesManufacturer.selectize.addItem(input);
                    selectizeManufacturerFromChoisesManufacturer.selectize.refreshOptions();
                    callback({model: input});
                }
            });
        },
         onChange: function(value) {
            modelPrinterChoice = value;
        },
        load: function (query, callback) {
            $.ajax({
                url: '/models/' + encodeURIComponent(manufacturerChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        },
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        showAddOptionOnCreate: true,
        placeholder: 'Выберите из списка',
        options: []

    });

    $('.manufacturer').children('select').selectize({
        create: function (input, callback) {
            selectizeManufacturerFromChoisesManufacturer = $(this.$control_input[0].closest('.printer')).find('.manufacturerChoice')[0];
            $.ajax({
                url: '/manufacturers',
                type: 'POST',
                data: {value: input},
                success: function () {
                    selectizeManufacturerFromChoisesManufacturer.selectize.addOption({value: input, text: input});
                    selectizeManufacturerFromChoisesManufacturer.selectize.addItem(input);
                    selectizeManufacturerFromChoisesManufacturer.selectize.refreshOptions();
                    callback({manufacturer: input});
                }
            });
        },
        preload: 'focus',
        placeholder: "Выберите из списка",
        valueField: 'manufacturer',
        labelField: 'manufacturer',
        searchField: "manufacturer",
        showAddOptionOnCreate: true,
        load: function (query, callback) {
            $.ajax({
                url: '/manufacturers',
                type: 'GET',
                dataType: 'json',
                data: {manufacturer: query},
                error: callback,
                success: callback
            });
        },
        onChange: function (value) {
            if (value !== '') {
                $(this.$control_input[0].closest('.printer')).find('.printerSwitch')[0].children[0].disabled = false;
                manufacturerValueFromSelectize = value;
                selectizeModelFromChoisesManufacturer = $(this.$control_input[0].closest('.printer')).find('.modelPrinter')[0];
                $.ajax({
                    url: "/models/" + encodeURIComponent(value),
                    type: 'GET',
                    dataType: 'json', // added data type
                    data: {manufacturer: value},
                    success: function (res) {
                        let keys = Object.keys(selectizeModelFromChoisesManufacturer.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeModelFromChoisesManufacturer.selectize.removeOption(keys[i]);
                        }
                        res.forEach(model => {
                            selectizeModelFromChoisesManufacturer.selectize.addOption(model);
                            selectizeModelFromChoisesManufacturer.selectize.addItem(model);
                        });

                        selectizeModelFromChoisesManufacturer.selectize.refreshOptions();
                        selectizeModelFromChoisesManufacturer.selectize.clear();
                        selectizeModelFromChoisesManufacturer.selectize.enable();
                    }
                });
            }
        }
    });





}




function addCartridges(count, location) {



    id2 = Math.floor(Math.random() * 10000 * 25654);
    independentCartridgeRow = document.createElement("div"); // Строка
    independentCartridgeRow.className = "row independentCartridge mb-2 mt-2";
    independentCartridgeRow.id = "row_independentCartridge_" + count + "_" + id2;
    paneCartridge = document.createElement("div");
    paneCartridge.className = "pane mt-3 mb-3";
    countEachCartridge = document.createElement("div");
    countEachCartridge.className = "col-md-1 mb-2 d-flex align-items-center justify-content-center";
    countEachCartridge.innerText = count;
    divTypeIndependentCartridge = document.createElement("div");
    divTypeIndependentCartridge.className = "col-md-2 mb-2 independentCartridgeType";
    divTypeIndependentCartridge.id = "independentCartridgeType_" + id2;
    selectTypeIndependentCartridge = document.createElement("select");
    selectTypeIndependentCartridge.className = "form-select text-start independentCartridgeTypeSelect";
    selectTypeIndependentCartridge.name = "independentCartridgeType";
    divModelIndependentCartridge = document.createElement("div");
    divModelIndependentCartridge.className = "col-md-2 mb-2 independentCartridgeModel";
    divModelIndependentCartridge.id = "independentCartridgeModel_" + id2;
    selectModelIndependentCartridge = document.createElement("select");
    selectModelIndependentCartridge.className = "form-select text-start independentCartridgeModelSelect";
    selectModelIndependentCartridge.id = "independentCartridgeModel_" + id2;
    selectModelIndependentCartridge.name = "independentCartridgeModel";
    selectModelIndependentCartridge.disabled = true;

    divItemCodeIndependentCartridge = document.createElement("div");
    divItemCodeIndependentCartridge.className = "col-md-2 mb-2 independentCartridgeItemCode";
    divItemCodeIndependentCartridge.id = "independentCartridgeItemCode_" + id2;
    codeItemIndependentCartridgeInput = document.createElement("input");
    codeItemIndependentCartridgeInput.className = "form-control text-start independentCartridgeItemCodeInput";
    codeItemIndependentCartridgeInput.type = "text";
    codeItemIndependentCartridgeInput.id = "independentCartridgeItemCodeInput_" + id2;
    codeItemIndependentCartridgeInput.name = "independentCartridgeItemCodeInput";
    codeItemIndependentCartridgeInput.disabled = true;


    divNameMaterialIndependentCartridge = document.createElement("div");
    divNameMaterialIndependentCartridge.className = "col-md-3 mb-2 independentCartridgeNameMaterial";
    divNameMaterialIndependentCartridge.id = "independentCartridgeNameMaterial_" + id2;
    nameMaterialIndependentCartridgeInput = document.createElement("textarea");
    nameMaterialIndependentCartridgeInput.className = "form-control text-start independentCartridgeNameMaterialInput";
    nameMaterialIndependentCartridgeInput.id = "independentCartridgeNameMaterialInput_" + id2;
    nameMaterialIndependentCartridgeInput.name = "independentCartridgeNameMaterialInput";
    nameMaterialIndependentCartridgeInput.disabled = true;


    divAmountIndependentCartridge = document.createElement("div");
    divAmountIndependentCartridge.className = "col-md-1 mb-2 independentCartridgeAmount";
    divAmountIndependentCartridge.id = "independentCartridgeModel_" + id2;
    amountIndependentCartridgeInput = document.createElement("input");
    amountIndependentCartridgeInput.className = "form-control text-start independentCartridgeModelInput";
    amountIndependentCartridgeInput.type = "number";
    amountIndependentCartridgeInput.id = "independentCartridgeModel_" + id2;
    amountIndependentCartridgeInput.name = "independentCartridgeModel";
    amountIndependentCartridgeInput.setAttribute('min', '0');
    amountIndependentCartridgeInput.value = '1';
    amountIndependentCartridgeInput.disabled = true;


    independentCartridgeRemoveRow = document.createElement("div");
    independentCartridgeRemoveRow.className = "col-md-1 mb-2 independentCartridgeRemoveRow";
    independentCartridgeRemoveRow.id = "independentCartridgeRemoveRow_" + id2;


    independentCartridgeRemoveBtn = document.createElement('button');
    independentCartridgeRemoveBtn.type = 'button';
    independentCartridgeRemoveBtn.className = 'btn btn-secondary independentCartridgeRemoveBtn';
    independentCartridgeRemoveBtn.innerText = 'Удалить';


    location.appendChild(paneCartridge);
    paneCartridge.appendChild(independentCartridgeRow);
    independentCartridgeRow.appendChild(countEachCartridge);
    independentCartridgeRow.appendChild(divTypeIndependentCartridge);
    divTypeIndependentCartridge.appendChild(selectTypeIndependentCartridge);
    independentCartridgeRow.appendChild(divModelIndependentCartridge);
    divModelIndependentCartridge.appendChild(selectModelIndependentCartridge);
    independentCartridgeRow.appendChild(divItemCodeIndependentCartridge);
    divItemCodeIndependentCartridge.appendChild(codeItemIndependentCartridgeInput);
    independentCartridgeRow.appendChild(divNameMaterialIndependentCartridge);
    divNameMaterialIndependentCartridge.appendChild(nameMaterialIndependentCartridgeInput);
    independentCartridgeRow.appendChild(divAmountIndependentCartridge);
    divAmountIndependentCartridge.appendChild(amountIndependentCartridgeInput);
    independentCartridgeRow.appendChild(independentCartridgeRemoveRow);
    independentCartridgeRemoveRow.appendChild(independentCartridgeRemoveBtn);

    amountIndependentCartridgeInput.addEventListener('input', function () {
        if (amountIndependentCartridgeInput.value <= 0) {
            amountIndependentCartridgeInput.style.backgroundColor = "rgba(230, 0, 0, 0.75)";
        } else {
            amountIndependentCartridgeInput.style.backgroundColor = "";
        }
    });


    independentCartridgeRemoveBtn.addEventListener('click', removeRow);


    // Selectize тип картриджа 

    let typeChoice;
    $('.independentCartridgeType').children('select').selectize({
        create: false,
        placeholder: "Выберите из списка",
        valueField: 'type',
        labelField: 'type',
        searchField: "type",
        options: [{type: "Оригинальный"},
            {type: "Совместимый"},
            {type: "Стартовый"}],
        onChange: function (value) {
            if (value !== '') {
                selectizeModelFromChoisesTypeCartridge = $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeModelSelect')[0];
                typeChoice = $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeTypeSelect')[0].innerText;
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeModelInput')[0].disabled = false;
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeItemCodeInput')[0].disabled = false;
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeNameMaterialInput')[0].disabled = false;
                selectizeModelFromChoisesTypeCartridge.selectize.enable();
                typeValueFromSelectize = value;
                $.ajax({
                    url: "/cartridge/" + encodeURIComponent(value),
                    type: 'GET',
                    dataType: 'json', // added data type
                    success: function (res) {
                        let keys = Object.keys(selectizeModelFromChoisesTypeCartridge.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeModelFromChoisesTypeCartridge.selectize.removeOption(keys[i]);
                        }
                        res.forEach(model => {
                            selectizeModelFromChoisesTypeCartridge.selectize.addOption(model);
                            selectizeModelFromChoisesTypeCartridge.selectize.addItem(model);
                        });

                        selectizeModelFromChoisesTypeCartridge.selectize.refreshOptions();
                        selectizeModelFromChoisesTypeCartridge.selectize.clear();
                        selectizeModelFromChoisesTypeCartridge.selectize.enable();
                    }
                });
            }
        }
    });

    // Selectize модель картриджа

    $('.independentCartridgeModel').children('select').selectize({
        placeholder: "Выберите из списка",
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        preload: 'focus',
        create: false,

        load: function (query, callback) {
            $.ajax({
                url: '/cartridge/' + encodeURIComponent(typeChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        }
    });

}
;


function addCartridgesInfo(location) {

    if (!document.getElementById('printerCartridge')) {

        labelCount = document.createElement("div");
        labelCount.className = "col-md-1 d-flex align-items-center justify-content-center countRowCartridge";
        labelCount.innerText = "№";
        label = document.createElement("label");
        label.className = "form-label";
        label.innerText = "Тип";
        flex = document.createElement("div");
        flex.className = "row mb-4 mt-5";
        flex.id = "printerCartridge";
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
        label3.innerText = "Номенклатурный номер";
        divcol3 = document.createElement("div");
        divcol3.className = "col-md-2 d-flex align-items-center justify-content-center";
        divcol3.style = "height:50px";
        flex.appendChild(divcol3);
        divcol3.appendChild(label3);

        label4 = document.createElement("label");
        label4.className = "form-label";
        label4.innerText = "Наименование расходного материала";
        divcol4 = document.createElement("div");
        divcol4.className = "col-md-3 d-flex align-items-center justify-content-center";
        divcol4.style = "height:50px";
        flex.appendChild(divcol4);
        divcol4.appendChild(label4);


        label5 = document.createElement("label");
        label5.className = "form-label";
        label5.innerText = "Количество";
        divcol5 = document.createElement("div");
        divcol5.className = "col-md-1 d-flex align-items-center justify-content-center";
        divcol5.style = "height:50px";
        flex.appendChild(divcol5);
        divcol5.appendChild(label5);

        label6 = document.createElement("label");
        label6.className = "form-label";
        label6.innerText = "";
        divcol6 = document.createElement("div");
        divcol6.className = "col-md-1 d-flex align-items-center justify-content-center";
        divcol6.style = "height:50px";
        flex.appendChild(divcol6);
        divcol6.appendChild(label6);
    }


    id2 = Math.floor(Math.random() * 10000 * 25654);
    independentCartridgeRow = document.createElement("div"); // Строка
    independentCartridgeRow.className = "row independentCartridge mb-2 mt-2";
    independentCartridgeRow.id = "row_independentCartridge_" + 1 + "_" + id2;
    paneCartridge = document.createElement("div");
    paneCartridge.className = "pane mt-3 mb-3";
    countEachCartridge = document.createElement("div");
    countEachCartridge.className = "col-md-1 mb-2 d-flex align-items-center justify-content-center independentCartridgeCount";
    countEachCartridge.innerText = 1;
    divTypeIndependentCartridge = document.createElement("div");
    divTypeIndependentCartridge.className = "col-md-2 mb-2 independentCartridgeType";
    divTypeIndependentCartridge.id = "independentCartridgeType_" + id2;
    selectTypeIndependentCartridge = document.createElement("select");
    selectTypeIndependentCartridge.className = "form-select text-start independentCartridgeTypeSelect";
    selectTypeIndependentCartridge.name = "independentCartridgeType";
    divModelIndependentCartridge = document.createElement("div");
    divModelIndependentCartridge.className = "col-md-2 mb-2 independentCartridgeModel";
    divModelIndependentCartridge.id = "independentCartridgeModel_" + id2;
    divInnerModelRow = document.createElement('div');
    divInnerModelRow.className = 'row';
    divInnerModel1 = document.createElement('div');
    divInnerModel1.className = 'col-10';
    divInnerModel2 = document.createElement('div');
    divInnerModel2.className = 'col-2';
    selectModelIndependentCartridge = document.createElement("select");
    selectModelIndependentCartridge.className = "form-select text-start independentCartridgeModelSelect";
    selectModelIndependentCartridge.id = "independentCartridgeModel_" + id2;
    selectModelIndependentCartridge.name = "independentCartridgeModel";
    selectModelIndependentCartridge.disabled = true;
    
    iconLink = document.createElement('a');
    iconLink.setAttribute('href', '#');
    iconSvg = document.createElement('img');
    iconSvg.setAttribute('src', '/img/info-circle-fill.svg');
    

    divItemCodeIndependentCartridge = document.createElement("div");
    divItemCodeIndependentCartridge.className = "col-md-2 mb-2 independentCartridgeItemCode";
    divItemCodeIndependentCartridge.id = "independentCartridgeItemCode_" + id2;
    codeItemIndependentCartridgeInput = document.createElement("input");
    codeItemIndependentCartridgeInput.className = "form-control text-start independentCartridgeItemCodeInput";
    codeItemIndependentCartridgeInput.type = "text";
    codeItemIndependentCartridgeInput.id = "independentCartridgeItemCodeInput_" + id2;
    codeItemIndependentCartridgeInput.name = "independentCartridgeItemCodeInput";
    codeItemIndependentCartridgeInput.disabled = true;


    divNameMaterialIndependentCartridge = document.createElement("div");
    divNameMaterialIndependentCartridge.className = "col-md-3 mb-2 independentCartridgeNameMaterial";
    divNameMaterialIndependentCartridge.id = "independentCartridgeNameMaterial_" + id2;
    nameMaterialIndependentCartridgeInput = document.createElement("textarea");
    nameMaterialIndependentCartridgeInput.className = "form-control text-start independentCartridgeNameMaterialInput";
    nameMaterialIndependentCartridgeInput.id = "independentCartridgeNameMaterialInput_" + id2;
    nameMaterialIndependentCartridgeInput.name = "independentCartridgeNameMaterialInput";
    nameMaterialIndependentCartridgeInput.disabled = true;


    divAmountIndependentCartridge = document.createElement("div");
    divAmountIndependentCartridge.className = "col-md-1 mb-2 independentCartridgeAmount";
    divAmountIndependentCartridge.id = "independentCartridgeModel_" + id2;
    amountIndependentCartridgeInput = document.createElement("input");
    amountIndependentCartridgeInput.className = "form-control text-start independentCartridgeModelInput";
    amountIndependentCartridgeInput.type = "number";
    amountIndependentCartridgeInput.id = "independentCartridgeModel_" + id2;
    amountIndependentCartridgeInput.name = "independentCartridgeModel";
    amountIndependentCartridgeInput.setAttribute('min', '1');
    amountIndependentCartridgeInput.value = '1';
    amountIndependentCartridgeInput.disabled = true;

    location.appendChild(paneCartridge);
    paneCartridge.appendChild(independentCartridgeRow);
    independentCartridgeRow.appendChild(countEachCartridge);
    independentCartridgeRow.appendChild(divTypeIndependentCartridge);
    divTypeIndependentCartridge.appendChild(selectTypeIndependentCartridge);
    independentCartridgeRow.appendChild(divModelIndependentCartridge);
    divModelIndependentCartridge.appendChild(selectModelIndependentCartridge);
//    divInnerModelRow.appendChild(divInnerModel1);
//    divInnerModelRow.appendChild(divInnerModel2);
//    divInnerModel1.appendChild(selectModelIndependentCartridge);
//    divInnerModel2.appendChild(iconLink);
//    iconLink.appendChild(iconSvg);
    
    
    independentCartridgeRow.appendChild(divItemCodeIndependentCartridge);
    divItemCodeIndependentCartridge.appendChild(codeItemIndependentCartridgeInput);
    independentCartridgeRow.appendChild(divNameMaterialIndependentCartridge);
    divNameMaterialIndependentCartridge.appendChild(nameMaterialIndependentCartridgeInput);
    independentCartridgeRow.appendChild(divAmountIndependentCartridge);
    divAmountIndependentCartridge.appendChild(amountIndependentCartridgeInput);

    amountIndependentCartridgeInput.addEventListener('input', function () {
        if (amountIndependentCartridgeInput.value <= 0) {
            amountIndependentCartridgeInput.style.backgroundColor = "rgba(230, 0, 0, 0.75)";
        } else {
            amountIndependentCartridgeInput.style.backgroundColor = "";
        }
    });
    // Selectize тип картриджа 

    let typeChoice;
    $('.independentCartridgeType').children('select').selectize({
        create: false,
        placeholder: "Выберите из списка",
        valueField: 'type',
        labelField: 'type',
        searchField: "type",
        options: [{type: "Оригинальный"},
            {type: "Совместимый"},
            {type: "Стартовый"}],
        onChange: function (value) {
            if (value !== '') {
                selectizeModelFromChoisesTypeCartridge = $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeModelSelect')[0];
                typeChoice = $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeTypeSelect')[0].innerText;
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeModelInput')[0].disabled = false;
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeItemCodeInput')[0].disabled = false;
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeNameMaterialInput')[0].disabled = false;
                selectizeModelFromChoisesTypeCartridge.selectize.enable();
                typeValueFromSelectize = value;
                $.ajax({
                    url: "/cartridge/" + encodeURIComponent(value),
                    type: 'GET',
                    dataType: 'json', // added data type
                    success: function (res) {
                        let keys = Object.keys(selectizeModelFromChoisesTypeCartridge.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeModelFromChoisesTypeCartridge.selectize.removeOption(keys[i]);
                        }
                        res.forEach(model => {
                            selectizeModelFromChoisesTypeCartridge.selectize.addOption(model);
                            selectizeModelFromChoisesTypeCartridge.selectize.addItem(model);
                        });

                        selectizeModelFromChoisesTypeCartridge.selectize.refreshOptions();
                        selectizeModelFromChoisesTypeCartridge.selectize.clear();
                        selectizeModelFromChoisesTypeCartridge.selectize.enable();
                    }
                });
            }
        }
    });

    // Selectize модель картриджа

   $('.independentCartridgeModel').find('select').selectize({
        placeholder: "Выберите из списка",
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        preload: 'focus',
        create: false,

        load: function (query, callback) {
            $.ajax({
                url: '/cartridge/' + encodeURIComponent(typeChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        },
     
    });
    





}



$(document).ready(function () {

    // Selectize тип картриджа 

    let typeChoice;
    $('.independentCartridgeType').children('select').selectize({
        create: false,
        placeholder: "Выберите из списка",
        valueField: 'type',
        labelField: 'type',
        searchField: "type",
        options: [{type: "Оригинальный"},
            {type: "Совместимый"},
            {type: "Стартовый"}],
        onChange: function (value) {
            if (value !== '') {
                selectizeModelFromChoisesTypeCartridge = $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeModelSelect')[0];
                typeChoice = $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeTypeSelect')[0].innerText;
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeModelInput')[0].disabled = false;
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeItemCodeInput')[0].disabled = false;
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeNameMaterialInput')[0].disabled = false;
                $(this.$control_input[0].closest('.independentCartridge')).find('.independentCartridgeAddBtn')[0].disabled = false;
                selectizeModelFromChoisesTypeCartridge.selectize.enable();
                typeValueFromSelectize = value;
                $.ajax({
                    url: "/cartridge/" + encodeURIComponent(value),
                    type: 'GET',
                    dataType: 'json', // added data type
                    success: function (res) {
                        let keys = Object.keys(selectizeModelFromChoisesTypeCartridge.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeModelFromChoisesTypeCartridge.selectize.removeOption(keys[i]);
                        }
                        res.forEach(model => {
                            selectizeModelFromChoisesTypeCartridge.selectize.addOption(model);
                            selectizeModelFromChoisesTypeCartridge.selectize.addItem(model);
                        });

                        selectizeModelFromChoisesTypeCartridge.selectize.refreshOptions();
                        selectizeModelFromChoisesTypeCartridge.selectize.clear();
                        selectizeModelFromChoisesTypeCartridge.selectize.enable();
                    }
                });
            }
        }
    });

    // Selectize модель картриджа

    $('.independentCartridgeModel').children('select').selectize({
        placeholder: "Выберите из списка",
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        preload: 'focus',
        create: false,

        load: function (query, callback) {
            $.ajax({
                url: '/cartridge/' + encodeURIComponent(typeChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        }
    });

});





function getContractPrinterDetails() {
    let printer;
    printersArray = new Array();
    for (let i = 0; i < document.getElementsByClassName('printer').length; i++) {
        printer = new Object();
        printer = {
            printer: i + 1,
            manufacturer: document.getElementsByClassName('printer')[i].children.item(1).firstChild.value,
            model: document.getElementsByClassName('printer')[i].children.item(2).firstChild.value,
            serialNumber: document.getElementsByClassName('printer')[i].children.item(3).firstChild.value,
            inventoryNumber: document.getElementsByClassName('printer')[i].children.item(4).firstChild.value,
            cartridgeIncluded: document.getElementsByClassName('printer')[i].children.item(5).firstChild.children.item(0).checked,
            cartridgeIncludedType: document.getElementsByClassName('cartridgeInclude')[i].children.item(1).firstChild.value.split(" ")[0],
            cartridgeIncludeModel: document.getElementsByClassName('cartridgeInclude')[i].children.item(3).firstChild.value

        };

        if (printersArray.length == 0) {
            printersArray.push(printer);
        } else {

            if (printersArray.findIndex(el => el.serialNumber == printer.serialNumber) == -1) {
                printersArray.push(printer);
            }
        }
    }









}


function getContractCartridgeDetails() {
    let cartridge;
    cartridgesArray = new Array();
    for (i = 0; i < document.getElementsByClassName('independentCartridge').length; i++) {
        let cartridge = new Object();
        cartridge = {
            cartridge: i + 1,
            type: document.getElementsByClassName('independentCartridge')[i].children.item(1).firstChild.value,
            model: document.getElementsByClassName('independentCartridge')[i].children.item(2).firstChild.value,
            itemCode: document.getElementsByClassName('independentCartridge')[i].children.item(3).firstChild.value,
            nameMaterial: document.getElementsByClassName('independentCartridge')[i].children.item(4).firstChild.value,
            amount: document.getElementsByClassName('independentCartridge')[i].children.item(5).firstChild.value

        };
        cartridgesArray.push(cartridge);
    }
}
$(document).ready(function () {
    $('#manufacturer').selectize({
        create: true,
        preload: true,
        showAddOptionOnCreate: true


    });
});




function getAmount() {

    arr = $('.independentCartridge').find('.independentCartridgeModelInput');
    let amount = 0;
    for (i = 0; i < arr.length; i++) {
        temp = $('.independentCartridge').find('.independentCartridgeModelInput')[i].value;
        amount = +amount + +temp;
    }

    return amount;
}





function getAmountPrinters() {
    let amount = 0;
    if($('#checkboxPrinter')[0].checked) {
        amount = $('.printer').length;
    }

    return amount;
}