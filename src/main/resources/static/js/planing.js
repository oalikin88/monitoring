let utilledInput = Object.entries(utilled).sort();
let purchasedInput = Object.entries(purchased).sort();
let models = Object.entries(amountModels).sort();
let balanceInput = Object.entries(balance).sort();

window.onload = function () {

    let parent = document.getElementsByClassName('inventoriesContent')[0];

    let amountUtilledCartridges = utilled.length;

    var header = document.createElement('header');
    var titleContent = document.createElement('h3');
    titleContent.className = 'fw-bold';
    titleContent.innerHTML = 'Отчёт по расходным материалам за период: ' + convertDate(startDate) + ' - ' + convertDate(endDate);

    parent.appendChild(header);
    header.appendChild(titleContent);

    var allUtil = 0;
    var allPurchased = 0;
    var allBalance = 0;
    
    // Вывод таблицы
    
    let tableContainer = document.createElement('div');
    tableContainer.className = 'table-responsive';
    tableContainer.id = "tableContainer";
    
    parent.appendChild(tableContainer);
    
    if(models.length > 0) {
        
        let table = document.createElement('table');
        table.id = "table";
        table.className = "table align-middle table-hover table-striped table-bordered";
        let thead = document.createElement('thead');
        thead.className = "text-center";
        let trThead = document.createElement('tr');
        let thThead1 = document.createElement('th');
        thThead1.setAttribute('scope', 'col');
        thThead1.innerText = 'Модель принтера';
      
        tableContainer.appendChild(table);
        table.appendChild(thead);
        thead.appendChild(trThead);
        trThead.appendChild(thThead1);
    
        let tbody = document.createElement('tbody');
        let tr1 = document.createElement('tr');
        table.appendChild(tbody);
        tbody.appendChild(tr1);
        amountColums = 0;
        for(i = 0; i < models.length; i++) {
            amountColums = amountColums + models[i][1].length;
        }
        
        for(i = 0; i < models.length; i++) {
            
            thTheadEl = document.createElement('th');
            thTheadEl.setAttribute('scope', 'col');
            if(models[i][1].length > 1) {
                thTheadEl.setAttribute('colspan', models[i][1].length);
            }
            thTheadEl.innerText = models[i][0];
            trThead.appendChild(thTheadEl);
        }
        
        tdAmountPrinters = document.createElement('td');
        tdAmountPrinters.innerText = 'Количество';
        tr1.appendChild(tdAmountPrinters);
        
        for(i = 0; i < models.length; i++) {
            tdAmountPrintersValue = document.createElement('td');
            tdAmountPrintersValue.setAttribute('scope', 'col');
            tdAmountPrintersValue.className = 'amountPrinters text-center';
            if(models[i][1].length > 1) {
                tdAmountPrintersValue.setAttribute('colspan', models[i][1].length);
            }
            tdAmountPrintersValue.innerText = models[i][1][0].idModel.length;
            tr1.appendChild(tdAmountPrintersValue);
        }
        
        tr2 = document.createElement('tr');
        tbody.appendChild(tr2);
        
        tdModelCartridgeName = document.createElement('td');
        tdModelCartridgeName.innerText = 'Модель картриджа';
        tr2.appendChild(tdModelCartridgeName);
        
        for(i = 0; i < models.length; i++) {
            for(inner = 0; inner < models[i][1].length; inner++) {
            tdModelCartridgeCol = document.createElement('td');
            tdModelCartridgeCol.className = 'modelCartridgeValue text-center';
            tdModelCartridgeCol.setAttribute('scope', 'col');
            tdModelCartridgeCol.innerText = models[i][1][inner].model;
            tr2.appendChild(tdModelCartridgeCol);
        }
    }
    
        tr3 = document.createElement('tr');
        tbody.appendChild(tr3);
        
        tdTypeCartridgeName = document.createElement('td');
        tdTypeCartridgeName.innerText = 'Тип';
        tr3.appendChild(tdTypeCartridgeName);
        
        for(i = 0; i < models.length; i++) {
            for(inner = 0; inner < models[i][1].length; inner++) {
            tdTypeCartridgeCol = document.createElement('td');
            tdTypeCartridgeCol.className = 'typeCartridgeValue text-center';
            tdTypeCartridgeCol.setAttribute('scope', 'col');
            tdTypeCartridgeCol.innerText = models[i][1][inner].type;
            tr3.appendChild(tdTypeCartridgeCol);
        }
    }
        
        tr4 = document.createElement('tr');
        tbody.appendChild(tr4);
        
        tdPurchaseCartName = document.createElement('td');
        tdPurchaseCartName.innerText = 'Закуплено';
        tr4.appendChild(tdPurchaseCartName);
        
        for(i = 0; i < models.length; i++) {
            for(inner = 0; inner < models[i][1].length; inner++) {
            var modelSearchPurchased = false;
            for (t = 0; t < purchasedInput.length; t++) {
                if (purchasedInput[t][0].indexOf(models[i][1][inner].model) === 0) {
                    tdPurchaseCartValue = document.createElement('td');
                    tdPurchaseCartValue.setAttribute('scope', 'col');
                    tdPurchaseCartValue.innerText = purchasedInput[t][1][0].incoming;
                    tr4.appendChild(tdPurchaseCartValue);
                    allPurchased = allPurchased + purchasedInput[t][1][0].incoming;
                    modelSearchPurchased = true;
                    
                    
                }
                
                if (t == purchasedInput.length - 1 && modelSearchPurchased == false) {
                    tdPurchaseCartValue = document.createElement('td');
                    tdPurchaseCartValue.setAttribute('scope', 'col');
                    tdPurchaseCartValue.innerText = 0;
                    tr4.appendChild(tdPurchaseCartValue);
                }
            }
        }
        }
        
        tr5 = document.createElement('tr');
        tbody.appendChild(tr5);
        
        tdUtilledName = document.createElement('td');
        tdUtilledName.innerText = "Израсходовано";
        tr5.appendChild(tdUtilledName);
        
         for(i = 0; i < models.length; i++) {
            for(inner = 0; inner < models[i][1].length; inner++) {
            var modelSearchUtilled = false;
            for (j = 0; j < utilledInput.length; j++) {
                if (utilledInput[j].indexOf(models[i][1][inner].model) === 0) {
                    tdUtilledCartValue = document.createElement('td');
                    tdUtilledCartValue.setAttribute('scope', 'col');
                    tdUtilledCartValue.innerText = utilledInput[j][1].length;
                    tr5.appendChild(tdUtilledCartValue);
                    allUtil = allUtil + utilledInput[j][1].length;
                    modelSearchUtilled = true;
                    
                    
                }
                
                if (j == utilledInput.length - 1 && modelSearchUtilled == false) {
                    tdUtilledCartValue = document.createElement('td');
                    tdUtilledCartValue.setAttribute('scope', 'col');
                    tdUtilledCartValue.innerText = 0;
                    tr5.appendChild(tdUtilledCartValue);
                }
            }
        }
        }
        
        tr6 = document.createElement('tr');
        tbody.appendChild(tr6);
        
        tdBalanceCartridgeName = document.createElement('td');
        tdBalanceCartridgeName.innerText = 'Остаток';
        tr6.appendChild(tdBalanceCartridgeName);
        
        for(i = 0; i < models.length; i++) {
            for(inner = 0; inner < models[i][1].length; inner++) {
            var modelSearchBalance = false;
            for (y = 0; y < balanceInput.length; y++) {
                if (balanceInput[y].indexOf(models[i][1][inner].model) === 0) {
                    tdBalanceCartValue = document.createElement('td');
                    tdBalanceCartValue.setAttribute('scope', 'col');
                    tdBalanceCartValue.innerText = balanceInput[y][1].balance;
                    tr6.appendChild(tdBalanceCartValue);
                    allBalance = allBalance + balanceInput[y][1].balance;
                    modelSearchBalance = true;
                    
                    
                }
                
                if (y == balanceInput.length - 1 && modelSearchBalance == false) {
                    tdBalanceCartValue = document.createElement('td');
                    tdBalanceCartValue.setAttribute('scope', 'col');
                    tdBalanceCartValue.innerText = 0;
                    tr6.appendChild(tdBalanceCartValue);
                }
            }
        }
        }
        
        tr7 = document.createElement('tr');
        tbody.appendChild(tr7);
        
        tdFinal = document.createElement('td');
        tdFinal.className = 'tdFinal fw-bold';
        tdFinal.setAttribute('colspan', amountColums + 1);
        tdFinal.innerText = 'Итого';
        tr7.appendChild(tdFinal);
        
        tr8 = document.createElement('tr');
        tbody.appendChild(tr8);
        
        tdPurchasedFinalName = document.createElement('td');
        tdPurchasedFinalName.innerText = 'Закуплено';
        tr8.appendChild(tdPurchasedFinalName);
        
        tdPurchasedFinalValue = document.createElement('td');
        tdPurchasedFinalValue.setAttribute('colspan', amountColums);
        tdPurchasedFinalValue.innerText = allPurchased;
        tr8.appendChild(tdPurchasedFinalValue);
        
        tr9 = document.createElement('tr');
        tbody.appendChild(tr9);
        
        tdUtilledFinalName = document.createElement('td');
        tdUtilledFinalName.innerText = "Израсходовано";
        tr9.appendChild(tdUtilledFinalName);
        
        tdUtilledFinalValue = document.createElement('td');
        tdUtilledFinalValue.setAttribute('colspan', amountColums);
        tdUtilledFinalValue.innerText = allUtil;
        tr9.appendChild(tdUtilledFinalValue);
        
        tr10 = document.createElement('tr');
        tbody.appendChild(tr10);
        
        tdBalanceFinal = document.createElement('td');
        tdBalanceFinal.innerText = 'Остаток';
        tr10.appendChild(tdBalanceFinal);
        
        tdBalanceFinalValue = document.createElement('td');
        tdBalanceFinalValue.setAttribute('colspan', amountColums);
        tdBalanceFinalValue.innerText = allBalance;
        tr10.appendChild(tdBalanceFinalValue);
        
        
        
    } else {
        
        noContent = document.createElement('div');
        noContent.className = 'row';
        noContentCol = document.createElement('div');
        noContentCol.className = 'col text-center';
        
        mes = document.createElement('h3');
        mes.innerText = "Отсутствуют данные для отчёта";
        
        parent.appendChild(noContent);
        noContent.appendChild(noContentCol);
        noContentCol.appendChild(mes);
    }
    
    
        // Блок строки с кнопками

    buttonsDivRow = document.createElement('div');
    buttonsDivRow.className = 'row rowButtons';

    btnBackDiv = document.createElement('div');
    btnBackDiv.className = 'col';

    btnCancel = document.createElement('input');
    btnCancel.setAttribute('type', 'button');
    btnCancel.className = 'btn';
    btnCancel.value = "Назад";
    parent.appendChild(buttonsDivRow);
    buttonsDivRow.appendChild(btnBackDiv);

    btnBackDiv.appendChild(btnCancel);

    btnCancel.addEventListener('click', function () {
        history.back();
    });
    

};

function compare(a, b) {
    if (a.model > b.model)
        return 1;
    if (a.model == b.model)
        return 0;
    if (a.model < b.model)
        return -1;
}


function convertDate(date) {
    inputDate = new Date(date);
    outDate = inputDate.toLocaleDateString('ru');
    return outDate;
}