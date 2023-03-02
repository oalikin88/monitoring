/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


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
optionsPrinterMap.set("Samsung ML-3051ND", "samsung-ml-3051nd");
optionsPrinterMap.set("Xerox Phaser 3330", "xerox-pheser-3330");

// Справочник картриджей

let optionsCartridgeMap = new Map();
optionsCartridgeMap.set("MLT-D203U", "mlt-d203u");
optionsCartridgeMap.set("CF259XC", "cf259xc");

// Справочник типов картриджей

let optionsCartridgeTypeMap = new Map();
optionsCartridgeTypeMap.set("Оригинальный", "ORIGINAL");
optionsCartridgeTypeMap.set("Совместимый", "ANALOG");
optionsCartridgeTypeMap.set("Стартовый", "START");

// Добавить Select

function addSelect(name, id, clname, map, location) {
    label = document.createElement("label");
    label.className = "form-label";
    label.innerText = name;
    select = document.createElement("select");
     select.className = "form-select";
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

// Выпадание списка для ввода данных принтера или картриджа

document.querySelector('#selectObjectBuing').oninput = () => {
    
    console.log(document.querySelector('#selectObjectBuing').value);


           // Если из справочника выбран принтер 
    if(document.querySelector('#selectObjectBuing').value === 'printer') {
        if(document.querySelector('.cartridge') !== null) {
            console.log(document.querySelector('.printer'));          
             input.removeChild(input.lastChild);
             input.removeChild(input.lastChild);
             input.removeChild(input.lastChild);
        }
        addSelect("Модель:", "modelPrinter", "printer", optionsPrinterMap, input);
        addInput("Серийный номер:", "serialNumberPrinter", "printer", input);
        addInput("Инвентарный номер:", "inventoryNumberPrinter", "printer", input);
        addSwitch("Картридж в комплекте", "isSwitched", "printer", input);
        
        document.querySelector("#isSwitched").addEventListener("change", function() {
            if(document.querySelector("#isSwitched").checked === true) {
                document.querySelector("#isSwitched").value = true;
                let switchBlock = document.createElement("div");
                switchBlock.id = "switchBlock";   
                addSelect("Тип:", "typeCartridge", "printer", optionsCartridgeTypeMap, input);
                addSelect("Модель:", "modelCartridge", "printer", optionsCartridgeMap, input);
                addInput("Номинальный ресурс:", "nominalResource", "printer", input);
            } else {
                document.querySelector("#isSwitched").value = false;
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
            }
           console.log(event.target); 
        });
               
        // Если из справочника выбран картридж
    } else if (document.querySelector('#selectObjectBuing').value === 'cartridge') {
        if(document.querySelector('.printer') !== null ) {
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            if(isSwitch) {
                document.querySelector("#isSwitched").value = false;
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
                
            }
        }
        
        addSelect("Тип:", "typeCartridge", optionsCartridgeTypeMap, input);
        addSelect("Модель:", "modelCartridge", "cartridge", optionsCartridgeMap, input);
        addInput("Номинальный ресурс:", "nominalResource", "printer", input);
        
        // Если ничего не выбрано
    } else if(document.querySelector('#selectObjectBuing').value === 'Выбрать из списка') {
        if(document.querySelector('.printer') !== null) {
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
            if(isSwitched) {
                isSwitched = false;
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
                input.removeChild(input.lastChild);
             
            }
        } else {
            input.removeChild(input.lastChild);
            input.removeChild(input.lastChild);
        }
            console.log(document.querySelector('.printer'));
            console.log(document.querySelector('.cartridge'));
    }
    
    if(document.querySelector("#modelPrinter") !== null) {
        document.querySelector("#modelPrinter").addEventListener("change", function() {
            document.querySelector("#modelPrinter").value = document.querySelector("#modelPrinter").options[document.querySelector("#modelPrinter").selectedIndex].value;
            console.log(document.querySelector("#modelPrinter").options[document.querySelector("#modelPrinter").selectedIndex].value);
            
        });
    }
    //document.querySelector("#modelPrinter").options[document.querySelector("#modelPrinter").selectedIndex].value 
    
}; 




