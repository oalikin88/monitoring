/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


let parent = document.getElementById('content');

window.onload = function () {
  
    if(input.length > 0) {
        
        // Вывод шапки 
        
        titleDiv = document.createElement('div');
        titleDiv.className = 'title';
        
        title = document.createElement('h4');
        title.className = 'text-left fw-bold';
        title.innerText = 'Контракты:';
        
        parent.appendChild(titleDiv);
        titleDiv.appendChild(title);
        
        rowLabels = document.createElement('div');
        rowLabels.className = 'row fw-bold rowLabels';
        
        divCountLabel = document.createElement('div');
        divCountLabel.className = 'col-md-1';
        divCountLabel.innerHTML = '№';
        
        divContractNumberLabel = document.createElement('div');
        divContractNumberLabel.className = 'col';
        divContractNumberLabel.innerHTML = 'Номер контракта';
        
        divContractStartDateLabel = document.createElement('div');
        divContractStartDateLabel.className = 'col';
        divContractStartDateLabel.innerHTML = 'Дата начала контракта';
        
        divContractEndDateLabel = document.createElement('div');
        divContractEndDateLabel.className = 'col';
        divContractEndDateLabel.innerHTML = 'Дата окончания контракта';
        
        divContractItemsLabes = document.createElement('div');
        divContractItemsLabes.className = 'col';
        divContractItemsLabes.innerHTML = 'Содержимое';
        
        parent.appendChild(rowLabels);
        rowLabels.appendChild(divCountLabel);
        rowLabels.appendChild(divContractNumberLabel);
        rowLabels.appendChild(divContractStartDateLabel);
        rowLabels.appendChild(divContractEndDateLabel);
        rowLabels.appendChild(divContractItemsLabes);
        
        
        // Вывод контрактов
        
        for(i = 0; i < input.length; i++) {
            
            contentRow = document.createElement('div');
            contentRow.className = 'row contentRow pb-2 mb-2 mt-2 pt-2';
            
            divCountContent = document.createElement('div');
            divCountContent.className = 'col-md-1';
            divCountContent.innerHTML = i + 1;
            
            divContractNumberContent = document.createElement('div');
            divContractNumberContent.className = 'col';
            
            linkContract = document.createElement('a');
            linkContract.setAttribute('href', '/contract?idContract=' + input[i].id);
            linkContract.innerText = input[i].contractNumber;
            
            parseStartDate = Date.parse(input[i].dateStartContract);
            startDate = new Date(parseStartDate);
            startDateFormat = startDate.toLocaleDateString('ru');
            
            divContractStartDateContent = document.createElement('div');
            divContractStartDateContent.className = 'col';
            divContractStartDateContent.innerHTML = startDateFormat;
            
            parseEndDate = Date.parse(input[i].dateEndContract);
            endDate = new Date(parseEndDate);
            endDateFormat = endDate.toLocaleDateString('ru');
            
            divContractEndDateContent = document.createElement('div');
            divContractEndDateContent.className = 'col';
            divContractEndDateContent.innerHTML = endDateFormat;
            
            divContractItemsContent = document.createElement('div');
            divContractItemsContent.className = 'col';
            divContractItemsContent.innerHTML = input[i].items;
            
            parent.appendChild(contentRow);
            contentRow.appendChild(divCountContent);
            contentRow.appendChild(divContractNumberContent);
            divContractNumberContent.appendChild(linkContract);
            contentRow.appendChild(divContractStartDateContent);
            contentRow.appendChild(divContractEndDateContent);
            contentRow.appendChild(divContractItemsContent);
            
        }
        
        
        
    } else {
        
        containerRowMes = document.createElement('div');
        containerRowMes.className = 'row messageRow';
        containerMes = document.createElement('div');
        containerMes.className = 'col text-center';
        message = document.createElement('h4');
        message.innerHTML = 'У вас ещё нет контрактов';
        
        parent.appendChild(containerRowMes);
        containerRowMes.appendChild(containerMes);
        containerMes.appendChild(message);
        
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
    
    setInterval('AJAXPing()', 28000);
    
};