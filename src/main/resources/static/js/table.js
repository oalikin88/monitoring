/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

let searchModel = false;
window.onload = function () {

    console.log(input);
    console.log(input2);

    let parent = document.getElementsByClassName('inventoriesContent')[0];
    let table = document.createElement('table');
    table.className = "table table-striped table-bordered";
    let thead = document.createElement('thead');
    let trThead = document.createElement('tr');
    let thThead1 = document.createElement('th');
    thThead1.setAttribute('scope', 'col');
    thThead1.setAttribute('rowspan', '2');
    thThead1.innerText = '#';
    let thThead2 = document.createElement('th');
    thThead2.setAttribute('scope', 'col');
    thThead2.setAttribute('rowspan', '2');
    thThead2.innerText = 'Модель принтера \ Локация';
    parent.appendChild(table);
    table.appendChild(thead);
    thead.appendChild(trThead);
    trThead.appendChild(thThead1);
    trThead.appendChild(thThead2);
    let tbody = document.createElement('tbody');
    let tr = document.createElement('tr');
    table.appendChild(tbody);
    tbody.appendChild(tr);

    // Вывод шапки таблицы
    
    
    input2sort = Object.entries(input2).sort();
    input1sort = Object.entries(input).sort();

    for (let i = 0; i < 1; i++) {

        for (let j = 0; j < input2sort.length; j++) {
            let th = document.createElement('th');
            th.setAttribute('scope', 'col');
            th.setAttribute('colspan', '2');
            th.className = "model";
            th.id = "idModel_" + input2sort[j][1][0].idModel;
            th.innerText = input2sort[j][0];
            trThead.appendChild(th);
            
        }

        for (let k = 0; k < 1; k++) {
            let subtrThead = document.createElement('tr');
            thead.appendChild(subtrThead);

            for (let j = 0; j < input2sort.length; j++) {
                thTheadCount = document.createElement('th');
                thTheadCount.innerText = "Количество принтеров";
                thTheadCount2 = document.createElement('th');
                thTheadCount2.innerText = "Количество картриджей";
                subtrThead.appendChild(thTheadCount);
                subtrThead.appendChild(thTheadCount2);
            }
        }
    }


    
    // Вывод тела таблицы
    
    
for(inInput1sort = 0; inInput1sort < input1sort.length; inInput1sort++) {
            tr = document.createElement('tr');
            tbody.appendChild(tr);
            tdCount = document.createElement('td');
            tdCount.innerText = inInput1sort + 1;
            tr.appendChild(tdCount);
            tdLocation = document.createElement('td');
            tdLocation.setAttribute('id', JSON.parse(input1sort[inInput1sort][0]).id);
            tdLocation.setAttribute('class', 'location');
            tdLocation.innerText = JSON.parse(input1sort[inInput1sort][0]).name;
            tr.appendChild(tdLocation);
            
            
                 for (i = 0; i < input2sort.length; i++) {
                     searchModel = false;
    
                
                    for(innerInInput1Sort = 0; innerInInput1Sort < input1sort[inInput1sort][1].length; innerInInput1Sort++) {
                        
                        for(inCartridgeModelCount = 0; inCartridgeModelCount < input1sort[inInput1sort][1][innerInInput1Sort].modelsPrinter.length; inCartridgeModelCount++) {
                            if(input2sort[i][1][0].model === input1sort[inInput1sort][1][innerInInput1Sort].modelsPrinter[inCartridgeModelCount].model) {
                                console.log(input2sort[i][1][0].model + "==" + input1sort[inInput1sort][1][innerInInput1Sort].modelsPrinter[inCartridgeModelCount].model);
                                
                                tdPrintSuccess = document.createElement('td');
                                tdPrintSuccess.innerText = input1sort[inInput1sort][1][innerInInput1Sort].printersID.length;
                                tdPrintSuccess.setAttribute('id', 'location_' + JSON.parse(input1sort[i][0]).id + '_' + 'model_' + input2sort[i][1][0].idModel);
                                tdPrintSuccess.setAttribute('class', 'model');
                                tr.appendChild(tdPrintSuccess);

                                tdCart = document.createElement('td');
                                tdCart.setAttribute('id', 'location_' + JSON.parse(input1sort[i][0]).id + '_' + 'model_' + input2sort[i][1][0].idModel + '_cart');
                                tdCart.setAttribute('class', 'cart');
                                tdCart.innerText =  input1sort[inInput1sort][1][innerInInput1Sort].cartridgesId.length;
                                tr.appendChild(tdCart);

                                searchModel = true;
                                break;
                            }
                        }
                        
                        
                    
                        
                    }
                        if (searchModel === false) {
                        tdPrint = document.createElement('td');
                        tdPrint.innerText = "0";
                        tdPrint.setAttribute('id', 'location_' + JSON.parse(input1sort[inInput1sort][0]).id + '_' + 'model_' + input2sort[inInput1sort][1][0].idModel);
                        tdPrint.setAttribute('class', 'model');
                        tr.appendChild(tdPrint);
                        
                        tdCart = document.createElement('td');
                        tdCart.setAttribute('id', 'location_' + JSON.parse(input1sort[inInput1sort][0]).id + '_' + 'model_' + input2sort[inInput1sort][1][0].idModel + '_cart');
                        tdCart.setAttribute('class', 'cart');
                        tdCart.innerText =  "0";
                        tr.appendChild(tdCart);
                        
                        }
                }
            
            
        
    }






//   for (let i = 0; i < input1sort.length; i++) {
//
//        let tr = document.createElement('tr');
//        tbody.appendChild(tr);
//        let tdCount = document.createElement('td');
//        tdCount.innerText = i + 1;
//        tr.appendChild(tdCount);
//        let tdLocation = document.createElement('td');
//        tdLocation.setAttribute('id', JSON.parse(input1sort[i][0]).id);
//        tdLocation.setAttribute('class', 'location');
//        tdLocation.innerText = JSON.parse(input1sort[i][0]).name;
//        tr.appendChild(tdLocation);
//        
//        
//        for(innerLocation = 0; innerLocation < input2sort.length; innerLocation++) {
//            searchModel = false;
//            for(modelCartridgeCount = 0; modelCartridgeCount < Object.values(input1sort[0][1]).sort().length; modelCartridgeCount++) {
//                for(modelPrinterInner = 0; modelPrinterInner < Object.values(input1sort[0][1]).sort()[modelCartridgeCount].modelsPrinter.length;) {
//                    for(modelPrinterInnerInput2 = 0; modelPrinterInnerInput2 < input2sort[innerLocation][1].length; modelPrinterInnerInput2++) {
//                    if(Object.values(input1sort[0][1]).sort()[modelCartridgeCount].modelsPrinter[modelPrinterInner].model === input2sort[innerLocation][1][modelPrinterInnerInput2].model) {
//                        tdPrintSuccess = document.createElement('td');
//                        tdPrintSuccess.innerText = Object.values(input1sort[0][1]).sort()[modelCartridgeCount].modelsPrinter[modelPrinterInner].printersID.length;
//                        tdPrintSuccess.setAttribute('id', 'location_' + JSON.parse(input1sort[i][0]).id + '_' + 'model_' + input2sort[innerLocation][1].idModel);
//                        tdPrintSuccess.setAttribute('class', 'model');
//                        tr.appendChild(tdPrintSuccess);
//                        
//                        tdCart = document.createElement('td');
//                        tdCart.setAttribute('id', 'location_' + JSON.parse(input1sort[i][0]).id + '_' + 'model_' + input2sort[innerLocation][1].idModel + '_cart');
//                        tdCart.setAttribute('class', 'cart');
//                        tdCart.innerText =  Object.values(input1sort[0][1]).sort()[modelCartridgeCount].modelsPrinter[modelPrinterInner].cartridgesId.length;
//                        tr.appendChild(tdCart);
//                        
//                        searchModel = true;
//                        break;
//                    }
//            }
//        }
//        }
//          if (searchModel === false) {
//                tdPrint = document.createElement('td');
//                tdPrint.innerText = "0";
//                tdPrintSuccess.setAttribute('id', 'location_' + JSON.parse(input1sort[i][0]).id + '_' + 'model_' + input2sort[innerLocation][1].idModel);
//                tdPrint.setAttribute('class', 'model');
//                tr.appendChild(tdPrint);
//                
//                tdCart = document.createElement('td');
//                tdCart.setAttribute('id', 'location_' + JSON.parse(input1sort[i][0]).id + '_' + 'model_' + input2sort[innerLocation][1].idModel + '_cart');
//                tdCart.setAttribute('class', 'cart');
//                tdCart.innerText =  "0";
//                tr.appendChild(tdCart);
//            }
        
        
//        dd = Object.values(input1sort[i][1]).sort();
//        for (let j = 0; j < cc.length; j++) {
//            searchModel = false;
//            for (let g = 0; g < Object.entries(input2).length; g++) {
//                if (JSON.parse(ws[i][0]).name === JSON.parse(Object.keys(input2)[g]).name) {
//                    for (let innerPrinterArray = 0; innerPrinterArray < Object.entries(input2)[g][1].length; innerPrinterArray++) {
//                        if (Object.entries(input2)[g][1][innerPrinterArray].length > 0) {
//                            if (Object.entries(input2)[g][1][innerPrinterArray][0].model === JSON.parse(cc[j][0]).model) {
//                                tdPrintSuccess = document.createElement('td');
//                                tdPrintSuccess.innerText = Object.entries(input2)[g][1][innerPrinterArray].length;
//                                tdPrintSuccess.setAttribute('id', 'location_' + JSON.parse(ws[i][0]).id + '_' + 'model_' + JSON.parse(cc[j][0]).idModel);
//                                tdPrintSuccess.setAttribute('class', 'model');
//                                tr.appendChild(tdPrintSuccess);
//                                searchModel = true;
//                                break;
//
//                            }
//                        }
//                    }
//                }
//            }
//            if (searchModel === false) {
//                tdPrint = document.createElement('td');
//                tdPrint.innerText = "0";
//                tdPrint.setAttribute('id', 'location_' + JSON.parse(ws[i][0]).id + '_' + 'model_' + JSON.parse(cc[j][0]).idModel);
//                tdPrint.setAttribute('class', 'model');
//                tr.appendChild(tdPrint);
//            }
//            
//            tdCart = document.createElement('td');
//            tdCart.setAttribute('id', 'location_' + JSON.parse(ws[i][0]).id + '_' + 'model_' + JSON.parse(cc[j][0]).idModel + '_cart');
//            tdCart.setAttribute('class', 'cart');
//            tdCart.innerText = cc[j][1].length;
//            tr.appendChild(tdCart);
//        }
 //   }
  // }

    let follow = document.getElementsByClassName('model');
    
       // follow.onClick()



};