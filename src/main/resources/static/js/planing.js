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

    for (i = 0; i < models.length; i++) {

        var modelTitleRow = document.createElement('div');
        modelTitleRow.className = 'row mt-3';

        var modelTitleDiv = document.createElement('div');
        modelTitleDiv.className = 'col fw-bold';
        modelTitleDiv.innerHTML = models[i][0];
        parent.appendChild(modelTitleRow);
        modelTitleRow.appendChild(modelTitleDiv);
        
        
        var printersAmountRow = document.createElement('div');
        printersAmountRow.className = 'row';
        var printerAmountDiv = document.createElement('div');
        printerAmountDiv.className = 'div';
        printerAmountDiv.innerHTML = 'Количество: ' + models[i][1][0].idModel.length;
        
        parent.appendChild(printersAmountRow);
        printersAmountRow.appendChild(printerAmountDiv);
        
        for(inner = 0; inner < models[i][1].length; inner++) {
            
        
        var modelCartridgeRow = document.createElement('div');
        modelCartridgeRow.className = 'row fw-bold';
        var modelCartridgeDiv = document.createElement('div');
        modelCartridgeDiv.className = 'col';
        modelCartridgeDiv.innerHTML = models[i][1][inner].model;
        
        parent.appendChild(modelCartridgeRow);
        modelCartridgeRow.appendChild(modelCartridgeDiv);
        
        var typeCartridgeRow = document.createElement('div');
        typeCartridgeRow.className = 'row';
        var typeCartridgeDiv = document.createElement('div');
        typeCartridgeDiv.className = 'col';
        typeCartridgeDiv.innerHTML = 'Тип: ' + models[i][1][inner].type;
        
        parent.appendChild(typeCartridgeRow);
        typeCartridgeRow.appendChild(typeCartridgeDiv);
        
        
        
        var modelSearchUtilled = false;
        var modelSearchPurchased = false;
        var modelSearchBalance = false;

       
       
        for (t = 0; t < purchasedInput.length; t++) {
            

            if (purchasedInput[t][0].indexOf(models[i][1][inner].model) === 0) {

                var purchasedRow = document.createElement('div');
                purchasedRow.className = 'row';
                var purchasedDiv = document.createElement('div');
                purchasedDiv.className = 'col';
                purchasedDiv.innerHTML = 'Закуплено: ' + purchasedInput[t][1][0].incoming;
                allPurchased = allPurchased + purchasedInput[t][1][0].incoming;
                modelSearchPurchased = true;
            }

            if (purchasedInput.length - 1 && modelSearchPurchased == false) {
                var purchasedRow = document.createElement('div');
                purchasedRow.className = 'row';
                var purchasedDiv = document.createElement('div');
                purchasedDiv.className = 'col';
                purchasedDiv.innerHTML = 'Закуплено: ' + 0;

            }
        

        }

        parent.appendChild(purchasedRow);
        purchasedRow.appendChild(purchasedDiv);
       

              
                
                
       
       

        for (j = 0; j < utilledInput.length; j++) {

            if (utilledInput[j].indexOf(models[i][1][inner].model) === 0) {

                var utilsRow = document.createElement('div');
                utilsRow.className = 'row';
                var utilsDiv = document.createElement('div');
                utilsDiv.className = 'col';
                utilsDiv.innerHTML = 'Израсходовано: ' + utilledInput[j][1].length;
                allUtil = allUtil + utilledInput[j][1].length;
                modelSearchUtilled = true;
            }

            if (utilledInput.length - 1 && modelSearchUtilled == false) {
                var utilsRow = document.createElement('div');
                utilsRow.className = 'row';

                var utilsDiv = document.createElement('div');
                utilsDiv.className = 'col';
                utilsDiv.innerHTML = 'Израсходовано: ' + 0;

            }

        }
        parent.appendChild(utilsRow);
        utilsRow.appendChild(utilsDiv);

       


        for (y = 0; y < balanceInput.length; y++) {
            if (balanceInput[y].indexOf(models[i][1][inner].model) === 0) {
                var balanceRow = document.createElement('div');
                balanceRow.className = 'row';
                var balanceDiv = document.createElement('div');
                balanceDiv.className = 'col';
                balanceDiv.innerHTML = 'Остаток: ' + balanceInput[y][1].balance;
                allBalance = allBalance + balanceInput[y][1].balance;
                modelSearchBalance = true;
            }
            if (balanceInput.length - 1 && modelSearchBalance == false) {
                var balanceRow = document.createElement('div');
                balanceRow.className = 'row';
                var balanceDiv = document.createElement('div');
                balanceDiv.className = 'col';
                balanceDiv.innerHTML = 'Остаток: ' + 0;

            }
        }


        parent.appendChild(balanceRow);
        balanceRow.appendChild(balanceDiv);
        }
    }

    let allStatisticTitleRow = document.createElement('div');
    allStatisticTitleRow.className = 'row fw-bold text-center';

    let allStatisticTitleDiv = document.createElement('div');
    allStatisticTitleDiv.className = 'col';
    allStatisticTitleDiv.innerHTML = 'Итого за период';

    parent.appendChild(allStatisticTitleRow);
    allStatisticTitleRow.appendChild(allStatisticTitleDiv);
    
 
    
    let allPurchasedRow = document.createElement('div');
    allPurchasedRow.className = 'row';
    let allPurchasedDiv = document.createElement('div');
    allPurchasedDiv.className = 'col';
    allPurchasedDiv.innerHTML = "Закуплено: " + allPurchased;
    
    parent.appendChild(allPurchasedRow);
    allPurchasedRow.appendChild(allPurchasedDiv);
    
    let allUtilledRow = document.createElement('div');
    allUtilledRow.className = 'row';
    let allUtilledDiv = document.createElement('div');
    allUtilledDiv.className = 'div';
    allUtilledDiv.innerHTML = "Израсходовано: " + allUtil;
    
    parent.appendChild(allUtilledRow);
    allUtilledRow.appendChild(allUtilledDiv);
    
    let allBalanceRow = document.createElement('div');
    allBalanceRow.className = 'row';
    let allBalanceDiv = document.createElement('div');
    allBalanceDiv.className = 'col';
    allBalanceDiv.innerHTML = 'Остаток: ' + allBalance;
    
    parent.appendChild(allBalanceRow);
    allBalanceRow.appendChild(allBalanceDiv);
    

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