let searchModel = false;
window.onload = function () {

    input2sort = Object.entries(input2).sort();
    input1sort = Object.entries(input).sort();
    let parent = document.getElementsByClassName('inventoriesContent')[0];

    let tableContainer = document.getElementById('tableContainer');

    if (input1sort.length > 0) {
        // Вывод таблицы
        let table = document.createElement('table');
        table.id = "table";
        table.className = "table table-hover table-striped table-bordered";
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
        tableContainer.appendChild(table);
        table.appendChild(thead);
        thead.appendChild(trThead);
        trThead.appendChild(thThead1);
        trThead.appendChild(thThead2);
        let tbody = document.createElement('tbody');
        let tr = document.createElement('tr');
        table.appendChild(tbody);
        tbody.appendChild(tr);

        // Вывод шапки таблицы




        for (let i = 0; i < 1; i++) {

            for (let j = 0; j < input2sort.length; j++) {
                let th = document.createElement('th');
                th.setAttribute('scope', 'col');
                th.setAttribute('colspan', '4');
                th.id = "idModel_" + input2sort[j][1][0].idModel;
                th.innerText = input2sort[j][0];
                trThead.appendChild(th);

            }

            for (let k = 0; k < 1; k++) {
                let subtrThead = document.createElement('tr');
                thead.appendChild(subtrThead);

                for (let j = 0; j < input2sort.length; j++) {
                    thTheadCount = document.createElement('th');
                    thTheadCount.innerText = "кол-во принтеров";
                    thTheadCount2 = document.createElement('th');
                    thTheadCount2.innerText = "кол-во картриджей";
                    thTheadCount3 = document.createElement('th');
                    thTheadCount3.innerText = "Списано";
                    thTheadCount4 = document.createElement('th');
                    thTheadCount4.innerText = "Необходимо";
                    subtrThead.appendChild(thTheadCount);
                    subtrThead.appendChild(thTheadCount2);
                    subtrThead.appendChild(thTheadCount3);
                    subtrThead.appendChild(thTheadCount4);
                }
            }
        }



        // Вывод тела таблицы


        for (inInput1sort = 0; inInput1sort < input1sort.length; inInput1sort++) {
            tr = document.createElement('tr');
            tbody.appendChild(tr);
            tdCount = document.createElement('td');
            tdCount.innerText = inInput1sort + 1;
            tr.appendChild(tdCount);
            tdLocation = document.createElement('td');
            tdLocation.setAttribute('id', JSON.parse(input1sort[inInput1sort][0]).id);
            tdLocation.setAttribute('class', 'location');
            tdLocation.innerText = JSON.parse(input1sort[inInput1sort][0]).name;
            tdLocation.style.wordBreak = "break-all";
            tr.appendChild(tdLocation);


            for (i = 0; i < input2sort.length; i++) {
                searchModel = false;
                var amountCartridge = new Set();
                var amountPrinters = new Set();


                for (innerInInput1Sort = 0; innerInInput1Sort < input1sort[inInput1sort][1].length; innerInInput1Sort++) {



                    for (inCartridgeModelCount = 0; inCartridgeModelCount < input1sort[inInput1sort][1][innerInInput1Sort].modelsPrinter.length; inCartridgeModelCount++) {
                        if (input2sort[i][1][0].model === input1sort[inInput1sort][1][innerInInput1Sort].modelsPrinter[inCartridgeModelCount].model) {
                            for (vbn = 0; vbn < input1sort[inInput1sort][1][innerInInput1Sort].printersID.length; vbn++) {
                                amountPrinters.add(input1sort[inInput1sort][1][innerInInput1Sort].printersID[vbn]);
                            }
                            for (vbc = 0; vbc < input1sort[inInput1sort][1][innerInInput1Sort].cartridgesId.length; vbc++) {
                                amountCartridge.add(input1sort[inInput1sort][1][innerInInput1Sort].cartridgesId[vbc]);
                            }

                            searchModel = true;
                            break;
                        }

                    }




                }


                tdPrintSuccess = document.createElement('td');
                tdPrintSuccess.setAttribute('id', 'location_' + JSON.parse(input1sort[inInput1sort][0]).id + '_' + 'model_' + input2sort[i][1][0].idModel);
                tdPrintSuccess.setAttribute('class', 'model');
                tdPrintSuccess.style.wordBreak = "break-all";
                linkPrinter = document.createElement('a');
                var temp = "";
                for (count = 0; count < input2sort[i][1].length; count++) {
                    temp += '&idModel=' + input2sort[i][1][count].idModel;
                }
                linkPrinter.setAttribute('href', '/printersbylocation?idLocation=' + JSON.parse(input1sort[inInput1sort][0]).id
                        + temp);
                linkPrinter.innerText = amountPrinters.size;

                tr.appendChild(tdPrintSuccess);
                tdPrintSuccess.appendChild(linkPrinter);



                tdCart = document.createElement('td');
                //        tdCart.setAttribute('id', 'location_' + JSON.parse(input1sort[inInput1sort][0]).id + '_' + 'model_' + input1sort[inInput1sort][1][innerInInput1Sort].id + '_cart');
                tdCart.setAttribute('class', 'cart');
                tdCart.style.wordBreak = "break-all";
                link = document.createElement('a');
                link.setAttribute('href', '/getcartridgesbymodel?idPrinter=' + input2sort[i][1][0].idModel
                        + '&location=' + JSON.parse(input1sort[inInput1sort][0]).name);
                link.innerText = amountCartridge.size;

                tr.appendChild(tdCart);
                tdCart.appendChild(link);



                for(h = 0; h < input3.length; h++) {
                    
                }

            }



        }
    } else {
        h3AttentionAddLocation = document.createElement('h3');
        h3AttentionAddLocation.className = 'fw-bold text-center';
        h3AttentionAddLocation.innerText = "Добавьте локацию Склад";
        h3AttentionAddLocation.id = 'attentionLocation';
        parent.appendChild(h3AttentionAddLocation);
    }

    if (input2sort.length == 0) {
        h3AttentionAddModelPrinter = document.createElement('h3');
        h3AttentionAddModelPrinter.className = 'fw-bold text-center';
        h3AttentionAddModelPrinter.innerText = "Добавьте модель принтера";
        h3AttentionAddModelPrinter.id = 'attentionModelPrinter';
        parent.appendChild(h3AttentionAddModelPrinter);
    }
};