/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
let manufacturePrinterSelect;
let modelCartridgeSelect;
let manuf;
let printers = new Map();
let cartridgesMap = new Map();


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



const input = document.querySelector('#sendData');
let isSwitch;
// добавить поле ввода

function addInput(name, id, clname, location) {

    label = document.createElement("label");
    label.className = "form-label";
    label.innerText = name;
    inp = document.createElement("input");
    inp.type = "text";
    inp.className = "form-control";
    inp.name = id;
    inp.setAttribute("th:field", "*{" + id + "}");

    flex = document.createElement("div");
    divcol1 = document.createElement("div");
    divcol2 = document.createElement("div");
    flex.className = "row mb-2 text-end" + " " + clname;
    divcol1.className = "col mb-6";
    divcol2.className = "col mb-6";

    location.appendChild(flex);
    flex.appendChild(divcol1);
    divcol1.appendChild(label);
    flex.appendChild(divcol2);
    divcol2.appendChild(inp);

}

// Справочник принтеров

let optionsPrinterMap = new Map();
optionsPrinterMap.set("выбрать из списка", "");







// Справочник картриджей

let optionsCartridgeMap = new Map();
optionsCartridgeMap.set("выбрать из списка", "");
//optionsCartridgeMap.set("MLT-D203U", "mlt-d203u");

// Справочник типов картриджей

let optionsCartridgeTypeMap = new Map();
//optionsCartridgeTypeMap.set("Оригинальный", "ORIGINAL");
//optionsCartridgeTypeMap.set("Совместимый", "ANALOG");
//optionsCartridgeTypeMap.set("Стартовый", "START");




let optionsManufacturerMap = new Map();
optionsManufacturerMap.set("выбрать из списка", "");



//



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



// Добавить Select

function addSelect(name, id, clname, map, location) {
    label = document.createElement("label");
    label.className = "form-label";
    label.innerText = name;
    select = document.createElement("select");
    select.className = "form-select text-start";
    select.setAttribute("th:field", "*{" + id + "}");
    select.id = id;
    select.name = id;
    map.forEach((value, key) => {
        let opt = new Option(key, value);
        select.append(opt);
    });

    flex = document.createElement("div");
    divcol1 = document.createElement("div");
    divcol2 = document.createElement("div");
    flex.className = "row mb-2 text-end" + " " + clname;
    divcol1.className = "col mb-6";
    divcol2.className = "col mb-6";
    location.appendChild(flex);
    flex.appendChild(divcol1);
    divcol1.appendChild(label);
    flex.appendChild(divcol2);
    divcol2.appendChild(select);

}

// Добавить чекбокс - переключатель

function addSwitch(name, id, clname, location) {

    flex = document.createElement("div");
    divcol1 = document.createElement("div");
    divcol2 = document.createElement("div");
    label = document.createElement("label");
    label.className = "form-check-label";
    label.innerText = name;
    inp = document.createElement("input");
    inp.className = "form-check-input";
    inp.name = id;
    inp.type = "checkbox";
    inp.id = id;
    inp.setAttribute("role", "switch");
    inp.setAttribute("th:field", "*{" + id + "}");
    flex.className = "row mb-2 text-end" + " " + clname;
    divcol1.className = "col mb-6 form-check form-switch";
    divcol2.className = "col mb-6 form-check form-switch";
    location.appendChild(flex);
    flex.appendChild(divcol1);
    flex.appendChild(divcol2);
    divcol1.appendChild(label);
    divcol2.appendChild(inp);

}

function addPrinter() {

    addSelect("Производитель:", "manufacturer", "printer", optionsManufacturerMap, input);
    addSelect("Модель:", "modelPrinter", "printer", optionsPrinterMap, input);
    addInput("Серийный номер:", "serialNumberPrinter", "printer", input);
    addInput("Инвентарный номер:", "inventoryNumberPrinter", "printer", input);
    addSwitch("Картридж в комплекте", "isSwitched", "printer", input);
}

// Выпадание списка для ввода данных принтера или картриджа

document.querySelector('#selectObjectBuing').oninput = () => {




    // Если из справочника выбран принтер 
    if (document.querySelector('#selectObjectBuing').value === 'printer') {

        if (document.querySelector('.cartridge') !== null) {
            console.log(document.querySelector('.printer'));
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
        }

        addPrinter();

        if (document.querySelector("#modelPrinter") !== null) {
            modelPrinterSelect = document.querySelector("#modelPrinter");
        }

        document.querySelector("#isSwitched").addEventListener("change", function () {
            if (document.querySelector("#isSwitched").checked === true) {
                isSwitch = true;
                let switchBlock = document.createElement("div");
                switchBlock.id = "switchBlock";
                addSelect("Тип:", "typeCartridge", "printer", optionsCartridgeTypeMap, input);
                addSelect("Модель:", "modelCartridge", "printer", optionsCartridgeMap, input);
                addInput("Номинальный ресурс:", "nominalResource", "printer", input);

                if (document.querySelector("#typeCartridge") !== null) {
                    typeCartridgeSelect = document.querySelector("#typeCartridge");
                }

                if (document.querySelector("#modelCartridge") !== null) {
                    modelCartridgeSelect = document.querySelector("#modelCartridge");
                }

                $(document).ready(function () {
                    $('#typeCartridge').selectize({
                        create: true,
                        showAddOptionOnCreate: true,
                        addPrecedence: true,
                        loadingClass: 'loading',
                        onChange: function (value) {
                            var target = value.split(" ");
                            modelCartridgeSelect.selectize.clear();
                            modelCartridgeSelect.selectize.clearOptions();
                            for (var r of optionsCartridgeMap) {
                                if (target[0] === r[1])
                                    modelCartridgeSelect.selectize.addOption({value: r[0], text: r[0]});
                                modelCartridgeSelect.selectize.addItem(r[0]);
                            }

                            modelCartridgeSelect.selectize.setValue("", false);
                        }
                    });
                });
                $(document).ready(function () {
                    $('#modelCartridge').selectize({
                        create: true,
                        showAddOptionOnCreate: true,
                        addPrecedence: true
                    });
                });

            } else {
                isSwitch = false;
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
            }
            console.log(event.target);
        });



        // Если из справочника выбран картридж
    } else if (document.querySelector('#selectObjectBuing').value === 'cartridge') {
        if (document.querySelector('.printer') !== null) {
            if (isSwitch) {
                isSwitch = false;
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
            }
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);

        }

        addSelect("Тип:", "typeCartridge", "cartridge", optionsCartridgeTypeMap, input);
        addSelect("Модель:", "modelCartridge", "cartridge", optionsCartridgeMap, input);
        addInput("Номинальный ресурс:", "nominalResource", "cartridge", input);

        if (document.querySelector("#typeCartridge") !== null) {
            typeCartridgeSelect = document.querySelector("#typeCartridge");
        }


        $(document).ready(function () {
            $('#typeCartridge').selectize({
                create: true,
                showAddOptionOnCreate: true,
                addPrecedence: true,
                loadingClass: 'loading',
                onChange: function (value) {
                    var target = value.split(" ");
                    modelCartridgeSelect.selectize.clear();
                    modelCartridgeSelect.selectize.clearOptions();
                    for (var r of optionsCartridgeMap) {
                        if (target[0] === r[1])
                            modelCartridgeSelect.selectize.addOption({value: r[0], text: r[0]});
                        modelCartridgeSelect.selectize.addItem(r[0]);
                    }

                    modelCartridgeSelect.selectize.setValue("", false);
                }

            });
        });



        $(document).ready(function () {
            $('#modelCartridge').selectize({
                create: true,
                showAddOptionOnCreate: true,
                addPrecedence: true


            });
        });

        // Если ничего не выбрано
    } else if (document.querySelector('#selectObjectBuing').value === "") {
        if (document.querySelector('.printer') !== null) {
            if (isSwitched) {
                isSwitch = false;
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
            }
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);

        } else {
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
        }
        console.log(document.querySelector('.printer'));
        console.log(document.querySelector('.cartridge'));
    }

    if (document.querySelector("#modelPrinter") !== null) {
        document.querySelector("#modelPrinter").addEventListener("change", function () {
            document.querySelector("#modelPrinter").value = document.querySelector("#modelPrinter").options[document.querySelector("#modelPrinter").selectedIndex].value;
            console.log(document.querySelector("#modelPrinter").options[document.querySelector("#modelPrinter").selectedIndex].value);

        });
    }

    const sendForm = document.querySelector("#sendForm");
    const email = document.querySelector("#email");
    sendForm.addEventListener("#addBtn", function (evt) {
        evt.preventDefault();
        if (document.querySelector('#selectObjectBuing').value === "") {
            alert('Выберите объект закупки');
            return;
        }

        this.submit();
    });

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
        $('#modelPrinter').selectize({
            create: true,
            showAddOptionOnCreate: true,
            addPrecedence: true


        });
    });

};









