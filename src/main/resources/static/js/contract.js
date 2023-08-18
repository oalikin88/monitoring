/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

let parent = document.getElementById('content');


window.onload = function () {
    
    let startDateDiv = document.getElementById('contractStartDate');
    let endDateDiv = document.getElementById('contractEndDate');
    
    parseStartDate = Date.parse(input.dateStartContract);
    startDate = new Date(parseStartDate);
    startDateFormat = startDate.toLocaleDateString('ru');
    startDateDiv.innerHTML = startDateFormat;
    
    
    parseEndDate = Date.parse(input.dateEndContract);
    endDate = new Date(parseEndDate);
    endDateFormat = endDate.toLocaleDateString('ru');
    endDateDiv.innerHTML = endDateFormat;
    
    
    if(input.printers.length > 0) {
        rowForTitle = document.createElement('div');
        rowForTitle.className = 'row';
        
        divForTitle = document.createElement('div');
        divForTitle.className = 'col';
        
        
        titleForPrinter = document.createElement('h5');
        titleForPrinter.className = 'fw-bold mb-5';
        titleForPrinter.innerText = 'Принтеры закупленные по контракту:';
        
        parent.appendChild(rowForTitle);
        rowForTitle.appendChild(divForTitle);
        divForTitle.appendChild(titleForPrinter);
        
        
        // labels 
        
        rowLabels = document.createElement('div');
        rowLabels.className = 'row fw-bold mb-3';
        
        divCountLabel = document.createElement('div');
        divCountLabel.className = 'col-md-1';
        divCountLabel.innerHTML = '№';
        
        divNameLabel = document.createElement('div');
        divNameLabel.className = 'col';
        divNameLabel.innerHTML = 'Наименование';
        
        divSerialNumberLabel = document.createElement('div');
        divSerialNumberLabel.className = 'col';
        divSerialNumberLabel.innerHTML = 'Серийный номер';
        
        divInvLabel = document.createElement('div');
        divInvLabel.className = 'col';
        divInvLabel.innerHTML = 'Инвентарный номер';
        
        divLocationLabel = document.createElement('div');
        divLocationLabel.className = 'col';
        divLocationLabel.innerHTML = 'Локация';
        
        
        parent.appendChild(rowLabels);
        rowLabels.appendChild(divCountLabel);
        rowLabels.appendChild(divNameLabel);
        rowLabels.appendChild(divSerialNumberLabel);
        rowLabels.appendChild(divInvLabel);
        rowLabels.appendChild(divLocationLabel);
        
        for(i = 0; i < input.printers.length; i++) {
            
            rowContent = document.createElement('div');
            rowContent.className = 'row contentItemContract';
            
            divCountContent = document.createElement('div');
            divCountContent.className = 'col-md-1';
            divCountContent.innerHTML = i + 1;
            
            divNamePrinter = document.createElement('div');
            divNamePrinter.className = 'col';
            
            link = document.createElement('a');
            link.setAttribute('href', '/editprinter?idPrinter=' + input.printers[i].id);
            link.innerText = input.printers[i].manufacturer + " " + input.printers[i].model;
            
            divSerialNumberContent = document.createElement('div');
            divSerialNumberContent.className = 'col';
            divSerialNumberContent.innerHTML = input.printers[i].serialNumber;
            
            divInvContent = document.createElement('div');
            divInvContent.className = 'col';
            divInvContent.innerHTML = input.printers[i].inventaryNumber;
            
            divLocationContent = document.createElement('div');
            divLocationContent.className = 'col';
            divLocationContent.innerHTML = input.printers[i].location;
            
            parent.appendChild(rowContent);
            rowContent.appendChild(divCountContent);
            rowContent.appendChild(divNamePrinter);
            divNamePrinter.appendChild(link);
            rowContent.appendChild(divSerialNumberContent);
            rowContent.appendChild(divInvContent);
            rowContent.appendChild(divLocationContent);
            
        }
        
    }
    
    if(input.cartridges.length > 0) {
        
        rowForTitle = document.createElement('div');
        rowForTitle.className = 'row';
        
        divForTitle = document.createElement('div');
        divForTitle.className = 'col';
        
        
        titleForPrinter = document.createElement('h5');
        titleForPrinter.className = 'fw-bold mt-5 mb-5';
        titleForPrinter.innerText = 'Картриджи закупленные по контракту:';
        
        parent.appendChild(rowForTitle);
        rowForTitle.appendChild(divForTitle);
        divForTitle.appendChild(titleForPrinter);
        
        
        rowLabels = document.createElement('div');
        rowLabels.className = 'row fw-bold mb-3';
        
        divCountLabel = document.createElement('div');
        divCountLabel.className = 'col-md-1';
        divCountLabel.innerHTML = '№';
        
        divNameLabel = document.createElement('div');
        divNameLabel.className = 'col';
        divNameLabel.innerHTML = 'Наименование';
        
        divItemCodeLabel = document.createElement('div');
        divItemCodeLabel.className = 'col';
        divItemCodeLabel.innerHTML = 'Номенклатурный код';
        
        divTypeLabel = document.createElement('div');
        divTypeLabel.className = 'col';
        divTypeLabel.innerHTML = 'Тип';
        
        
        divStatusLabel = document.createElement('div');
        divStatusLabel.className = 'col';
        divStatusLabel.innerHTML = 'Статус';
        
        
        divLocationLabel = document.createElement('div');
        divLocationLabel.className = 'col';
        divLocationLabel.innerHTML = 'Локация';
        
        parent.appendChild(rowLabels);
        rowLabels.appendChild(divCountLabel);
        rowLabels.appendChild(divNameLabel);
        rowLabels.appendChild(divItemCodeLabel);
        rowLabels.appendChild(divTypeLabel);
        rowLabels.appendChild(divStatusLabel);
        rowLabels.appendChild(divLocationLabel);
        
        
        for(i = 0; i < input.cartridges.length > 0; i++) {
            
            rowContent = document.createElement('div');
            rowContent.className = 'row contentItemContract';
            
            divCountContent = document.createElement('div');
            divCountContent.className = 'col-md-1';
            divCountContent.innerHTML = i + 1;
            
            divNameCartridge = document.createElement('div');
            divNameCartridge.className = 'col';
            
            link = document.createElement('a');
            link.setAttribute('href', '/editcartridge?idCartridge=' + input.cartridges[i].id);
            link.innerText = input.cartridges[i].model;
            
            divItemCodeContent = document.createElement('div');
            divItemCodeContent.className = 'col';
            divItemCodeContent.innerHTML = input.cartridges[i].itemCode;
            
            divTypeContent = document.createElement('div');
            divTypeContent.className = 'col';
            divTypeContent.innerHTML = input.cartridges[i].type;
            
            divStatusContent = document.createElement('div');
            divStatusContent.className = 'col';
            
            if(input.cartridges[i].usePrinter == true && input.cartridges[i].util == true) {
                 var mes = document.createElement('p');
                var span = document.createElement('span');
                mes.className = 'message';
                mes.id = 'mes_use';
                span.innerHTML = 'используется';
                divStatusContent.appendChild(mes);
                mes.appendChild(span);
            } else if(input.cartridges[i].usePrinter == false && input.cartridges[i].util == true) {
                var mes = document.createElement('p');
                var span = document.createElement('span');
                mes.className = 'message';
                mes.id = 'mes_util';
                span.innerHTML = 'списан';
                divStatusContent.appendChild(mes);
                mes.appendChild(span);
            } else if(input.cartridges[i].usePrinter == false && input.cartridges[i].util == false) {
                var mes = document.createElement('p');
                var span = document.createElement('span');
                mes.className = 'message';
                mes.id = 'mes_ready';
                span.innerHTML = 'готов к установке';
                divStatusContent.appendChild(mes);
                mes.appendChild(span);
            }
           
            
            
            divLocationContent = document.createElement('div');
            divLocationContent.className = 'col';
            divLocationContent.innerHTML = input.cartridges[i].location;
            
            parent.appendChild(rowContent);
            rowContent.appendChild(divCountContent);
            rowContent.appendChild(divNameCartridge);
            divNameCartridge.appendChild(link);
            rowContent.appendChild(divItemCodeContent);
            rowContent.appendChild(divTypeContent);
            rowContent.appendChild(divStatusContent);
            rowContent.appendChild(divLocationContent);
            
        }
        
        
    }
    
    
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