/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

let parent = document.getElementById('content');
 let cartridgeRows;
 let sortedPrinterCount = false;
 let sortedPrinterName = false;
 let sortedPrinterSerialNumber = false;
 let sortedPrinterInventaryNumber = false;
 let sortedPrinterLocation = false;
 let sortedCartridgeCount = false;
 let sortedCartridgeNamesUp = false;
 let sortedCartridgeItemCode = false;
 let sortedCartridgeType = false;
 let sortedCartridgeStatus = false;
 let sortedCartridgeLocation = false;

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
        
        linkSortingPrinterCount = document.createElement('a');
        linkSortingPrinterCount.href = '#';
        linkSortingPrinterCount.id = 'printerCountSortLink';
        imgInnerPrinterCount = document.createElement('img');
        imgInnerPrinterCount.src = '/img/sorting.png';
        imgInnerPrinterCount.alt = 'Сортировка';
        imgInnerPrinterCount.className = 'imgSorting';
        imgInnerPrinterCount.id = 'printerCountSort';

        spanPrinterCount = document.createElement('span');
        spanPrinterCount.innerHTML = '№';
        
        divNameLabel = document.createElement('div');
        divNameLabel.className = 'col';
        
        linkSortingPrinterName = document.createElement('a');
        linkSortingPrinterName.href = '#';
        linkSortingPrinterName.id = 'printerNameSortLink';
        imgInnerPrinterName = document.createElement('img');
        imgInnerPrinterName.src = '/img/sorting.png';
        imgInnerPrinterName.alt = 'Сортировка';
        imgInnerPrinterName.className = 'imgSorting';
        imgInnerPrinterName.id = 'printerNameSort';

        spanPrinterName = document.createElement('span');
        spanPrinterName.innerHTML = 'Наименование';
        
        
        divSerialNumberLabel = document.createElement('div');
        divSerialNumberLabel.className = 'col';
        
        linkSortingPrinterSerialNumber = document.createElement('a');
        linkSortingPrinterSerialNumber.href = '#';
        linkSortingPrinterSerialNumber.id = 'printerSerialNumberSortLink';
        imgInnerPrinterSerialNumber = document.createElement('img');
        imgInnerPrinterSerialNumber.src = '/img/sorting.png';
        imgInnerPrinterSerialNumber.alt = 'Сортировка';
        imgInnerPrinterSerialNumber.className = 'imgSorting';
        imgInnerPrinterSerialNumber.id = 'printerSerialNumberSort';

        spanPrinterSerialNumber = document.createElement('span');
        spanPrinterSerialNumber.innerHTML = 'Серийный номер';
        
        
        divInvLabel = document.createElement('div');
        divInvLabel.className = 'col';
        
        linkSortingPrinterInventaryNumber = document.createElement('a');
        linkSortingPrinterInventaryNumber.href = '#';
        linkSortingPrinterInventaryNumber.id = 'printerInventaryNumberSortLink';
        imgInnerPrinterInventaryNumber = document.createElement('img');
        imgInnerPrinterInventaryNumber.src = '/img/sorting.png';
        imgInnerPrinterInventaryNumber.alt = 'Сортировка';
        imgInnerPrinterInventaryNumber.className = 'imgSorting';
        imgInnerPrinterInventaryNumber.id = 'printerInventaryNumberSort';

        spanPrinterInventaryNumber = document.createElement('span');
        spanPrinterInventaryNumber.innerHTML = 'Инвентарный номер';
        
        divLocationLabel = document.createElement('div');
        divLocationLabel.className = 'col';
        
        linkSortingPrinterLocation = document.createElement('a');
        linkSortingPrinterLocation.href = '#';
        linkSortingPrinterLocation.id = 'printerLocationSortLink';
        imgInnerPrinterLocation = document.createElement('img');
        imgInnerPrinterLocation.src = '/img/sorting.png';
        imgInnerPrinterLocation.alt = 'Сортировка';
        imgInnerPrinterLocation.className = 'imgSorting';
        imgInnerPrinterLocation.id = 'printerLocationSort';

        spanPrinterLocation = document.createElement('span');
        spanPrinterLocation.innerHTML = 'Локация';
        
        
        parent.appendChild(rowLabels);
        rowLabels.appendChild(divCountLabel);
        
        divCountLabel.appendChild(linkSortingPrinterCount);
        linkSortingPrinterCount.appendChild(imgInnerPrinterCount);
        divCountLabel.appendChild(spanPrinterCount);
        
        rowLabels.appendChild(divNameLabel);
        divNameLabel.appendChild(linkSortingPrinterName);
        linkSortingPrinterName.appendChild(imgInnerPrinterName);
        divNameLabel.appendChild(spanPrinterName);
        
        rowLabels.appendChild(divSerialNumberLabel);
        divSerialNumberLabel.appendChild(linkSortingPrinterSerialNumber);
        linkSortingPrinterSerialNumber.appendChild(imgInnerPrinterSerialNumber);
        divSerialNumberLabel.appendChild(spanPrinterSerialNumber);
        
        rowLabels.appendChild(divInvLabel);
        divInvLabel.appendChild(linkSortingPrinterInventaryNumber);
        linkSortingPrinterInventaryNumber.appendChild(imgInnerPrinterInventaryNumber);
        divInvLabel.appendChild(spanPrinterInventaryNumber);
        
        rowLabels.appendChild(divLocationLabel);
        divLocationLabel.appendChild(linkSortingPrinterLocation);
        linkSortingPrinterLocation.appendChild(imgInnerPrinterLocation);
        divLocationLabel.appendChild(spanPrinterLocation);
        
        divPrintersContainer = document.createElement('div');
        divPrintersContainer.className = 'printersContainer';
        parent.appendChild(divPrintersContainer);
        
        for(i = 0; i < input.printers.length; i++) {
            
            rowContent = document.createElement('div');
            rowContent.className = 'row contentItemPrinterContract';
            
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
            
            divPrintersContainer.appendChild(rowContent);
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
        
        linkSortingCartridgeCount = document.createElement('a');
        linkSortingCartridgeCount.href = '#';
        linkSortingCartridgeCount.id = 'cartridgeCountSortLink';
        imgInnerCartridgeCount = document.createElement('img');
        imgInnerCartridgeCount.src = '/img/sorting.png';
        imgInnerCartridgeCount.alt = 'Сортировка';
        imgInnerCartridgeCount.className = 'imgSorting';
        imgInnerCartridgeCount.id = 'cartridgeCountImg';
        
        spanCartridgeCount = document.createElement('span');
        spanCartridgeCount.innerHTML = '№';
        
        linkSortingName = document.createElement('a');
        linkSortingName.href = '#';
        linkSortingName.id = 'nameSortLink';
        imgInnerName = document.createElement('img');
        imgInnerName.src = '/img/sorting.png';
        imgInnerName.alt = 'Сортировка';
        imgInnerName.className = 'imgSorting';
        imgInnerName.id = 'nameSort';
        
        spanName = document.createElement('span');
        spanName.innerHTML = 'Наименование';
        
        divNameLabel = document.createElement('div');
        divNameLabel.className = 'col';
        
        linkSortingName.appendChild(imgInnerName);
        divNameLabel.appendChild(linkSortingName);
        divNameLabel.appendChild(spanName);
        
        divItemCodeLabel = document.createElement('div');
        divItemCodeLabel.className = 'col';
        
        linkSortingItemCode = document.createElement('a');
        linkSortingItemCode.href = '#';
        linkSortingItemCode.id = 'itemCodeSortLink';
        imgInnerItemCode = document.createElement('img');
        imgInnerItemCode.src = '/img/sorting.png';
        imgInnerItemCode.alt = 'Сортировка';
        imgInnerItemCode.className = 'imgSorting';
        imgInnerItemCode.id = 'itemCodeSort';
        
        spanItemCode = document.createElement('span');
        spanItemCode.innerHTML = 'Номенклатурный код';
        
        
        
        
        divTypeLabel = document.createElement('div');
        divTypeLabel.className = 'col cartridgeType';
        
        linkSortingCartridgeType = document.createElement('a');
        linkSortingCartridgeType.href = '#';
        linkSortingCartridgeType.id = 'cartridgeTypeSortLink';
        imgInnerCartridgeType = document.createElement('img');
        imgInnerCartridgeType.src = '/img/sorting.png';
        imgInnerCartridgeType.alt = 'Сортировка';
        imgInnerCartridgeType.className = 'imgSorting';
        imgInnerCartridgeType.id = 'cartridgeTypeSort';
        
        spanCartridgeType = document.createElement('span');
        spanCartridgeType.innerHTML = 'Тип';
        
        
        divStatusLabel = document.createElement('div');
        divStatusLabel.className = 'col cartridgeStatus';
        
        linkSortingCartridgeStatus = document.createElement('a');
        linkSortingCartridgeStatus.href = '#';
        linkSortingCartridgeStatus.id = 'cartridgeStatusSortLink';
        imgInnerCartridgeStatus = document.createElement('img');
        imgInnerCartridgeStatus.src = '/img/sorting.png';
        imgInnerCartridgeStatus.alt = 'Сортировка';
        imgInnerCartridgeStatus.className = 'imgSorting';
        imgInnerCartridgeStatus.id = 'cartridgeStatus';
        
        spanCartridgeStatus = document.createElement('span');
        spanCartridgeStatus.innerHTML = 'Статус';
        
        
        divLocationLabel = document.createElement('div');
        divLocationLabel.className = 'col';
        
        linkSortingCartridgeLocation = document.createElement('a');
        linkSortingCartridgeLocation.href = '#';
        linkSortingCartridgeLocation.id = 'cartridgeLocationSortLink';
        imgInnerCartridgeLocation = document.createElement('img');
        imgInnerCartridgeLocation.src = '/img/sorting.png';
        imgInnerCartridgeLocation.alt = 'Сортировка';
        imgInnerCartridgeLocation.className = 'imgSorting';
        imgInnerCartridgeLocation.id = 'cartridgeLocation';
        
        spanCartridgeLocation = document.createElement('span');
        spanCartridgeLocation.innerHTML = 'Локация';
        
        
        parent.appendChild(rowLabels);
        rowLabels.appendChild(divCountLabel);
        divCountLabel.appendChild(linkSortingCartridgeCount);
        linkSortingCartridgeCount.appendChild(imgInnerCartridgeCount);
        divCountLabel.appendChild(spanCartridgeCount);
        
        rowLabels.appendChild(divNameLabel);
        rowLabels.appendChild(divItemCodeLabel);
        divItemCodeLabel.appendChild(linkSortingItemCode);
        linkSortingItemCode.appendChild(imgInnerItemCode);
        divItemCodeLabel.appendChild(spanItemCode);
        rowLabels.appendChild(divTypeLabel);
        divTypeLabel.appendChild(linkSortingCartridgeType);
        linkSortingCartridgeType.appendChild(imgInnerCartridgeType);
        divTypeLabel.appendChild(spanCartridgeType);
        
        
        rowLabels.appendChild(divStatusLabel);
        divStatusLabel.appendChild(linkSortingCartridgeStatus);
        linkSortingCartridgeStatus.appendChild(imgInnerCartridgeStatus);
        divStatusLabel.appendChild(spanCartridgeStatus);
        
        
        rowLabels.appendChild(divLocationLabel);
        divLocationLabel.appendChild(linkSortingCartridgeLocation);
        linkSortingCartridgeLocation.appendChild(imgInnerCartridgeLocation);
        divLocationLabel.appendChild(spanCartridgeLocation);
        
        divCartridgesContainer = document.createElement('div');
        divCartridgesContainer.className = 'cartridgesContainer';
        parent.appendChild(divCartridgesContainer);
        
        for(i = 0; i < input.cartridges.length > 0; i++) {
            
            rowContent = document.createElement('div');
            rowContent.className = 'row contentItemCartridgeContract';
            
            divCountContent = document.createElement('div');
            divCountContent.className = 'col-md-1';
            divCountContent.innerHTML = i + 1;
            
            divNameCartridge = document.createElement('div');
            divNameCartridge.className = 'col cartridgeName';
            
            link = document.createElement('a');
            link.setAttribute('href', '/editcartridge?idCartridge=' + input.cartridges[i].id);
            link.innerText = input.cartridges[i].model;
            
            divItemCodeContent = document.createElement('div');
            divItemCodeContent.className = 'col itemCode';
            divItemCodeContent.innerHTML = input.cartridges[i].itemCode;
            
            divTypeContent = document.createElement('div');
            divTypeContent.className = 'col';
            divTypeContent.innerHTML = input.cartridges[i].type;
            
            divStatusContent = document.createElement('div');
            divStatusContent.className = 'col';
            
            if(input.cartridges[i].usePrinter == true && input.cartridges[i].util == false) {
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
            
            divCartridgesContainer.appendChild(rowContent);
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
    if($('#printerCountSortLink')[0] != null) {
    linkSortingPrinterCount.addEventListener('click', function() {
        printerRows = [...document.querySelectorAll('.contentItemPrinterContract')];
        if(sortedPrinterCount == false) {
        printerRows.sort((a, b) => (a.childNodes[0].innerText - b.childNodes[0].innerText));
        divPrintersContainer.innerHTML = '';
        for(let item of printerRows) {
            divPrintersContainer.appendChild(item);
        }
        sortedPrinterCount = true;
        } else {
            printerRows.sort((a, b) => (b.childNodes[0].innerText - a.childNodes[0].innerText));
        divPrintersContainer.innerHTML = '';
        for(let item of printerRows) {
            divPrintersContainer.appendChild(item);
        }
        sortedPrinterCount = false; 
        }
    });
    
    linkSortingPrinterName.addEventListener('click', function() {
        printerRows = [...document.querySelectorAll('.contentItemPrinterContract')];
        if(sortedPrinterName == false) {
            printerRows.sort((a, b) => (a.childNodes[1].innerText > b.childNodes[1].innerText) ? 1 : ((b.childNodes[1].innerText > a.childNodes[1].innerText) ? -1 : 0));
            divPrintersContainer.innerHTML = '';
            let count = 1;
            for(let item of printerRows) {
                item.childNodes[0].innerHTML = count;
            divPrintersContainer.appendChild(item);
            count++;
        }
        sortedPrinterName = true;
        } else {
            printerRows.sort((a, b) => (a.childNodes[1].innerText < b.childNodes[1].innerText) ? 1 : ((b.childNodes[1].innerText < a.childNodes[1].innerText) ? -1 : 0));
            divPrintersContainer.innerHTML = '';
            let count = 1;
            for(let item of printerRows) {
            item.childNodes[0].innerHTML = count;
            divPrintersContainer.appendChild(item);
            count++;
        }
        sortedPrinterName = false;
        }
    });
    
    
    linkSortingPrinterSerialNumber.addEventListener('click', function() {
        printerRows = [...document.querySelectorAll('.contentItemPrinterContract')];
        if(sortedPrinterSerialNumber == false) {
            printerRows.sort((a, b) => (a.childNodes[2].innerText > b.childNodes[2].innerText) ? 1 : ((b.childNodes[2].innerText > a.childNodes[2].innerText) ? -1 : 0));
            divPrintersContainer.innerHTML = '';
            let count = 1;
            for(let item of printerRows) {
            item.childNodes[0].innerHTML = count;
            divPrintersContainer.appendChild(item);
            count++;
        }
        sortedPrinterSerialNumber = true;
        } else {
            printerRows.sort((a, b) => (a.childNodes[2].innerText < b.childNodes[2].innerText) ? 1 : ((b.childNodes[2].innerText < a.childNodes[2].innerText) ? -1 : 0));
            divPrintersContainer.innerHTML = '';
            let count = 1;
            for(let item of printerRows) {
            item.childNodes[0].innerHTML = count;
            divPrintersContainer.appendChild(item);
            count++;
        }
        sortedPrinterSerialNumber = false;
        }
    });
    
    linkSortingPrinterInventaryNumber.addEventListener('click', function() {
        printerRows = [...document.querySelectorAll('.contentItemPrinterContract')];
        if(sortedPrinterInventaryNumber == false) {
            printerRows.sort((a, b) => (a.childNodes[3].innerText > b.childNodes[3].innerText) ? 1 : ((b.childNodes[3].innerText > a.childNodes[3].innerText) ? -1 : 0));
            divPrintersContainer.innerHTML = '';
            let count = 1;
            for(let item of printerRows) {
            item.childNodes[0].innerHTML = count;
            divPrintersContainer.appendChild(item);
            count++;
        }
        sortedPrinterInventaryNumber = true;
        } else {
            printerRows.sort((a, b) => (a.childNodes[3].innerText < b.childNodes[3].innerText) ? 1 : ((b.childNodes[3].innerText < a.childNodes[3].innerText) ? -1 : 0));
            divPrintersContainer.innerHTML = '';
            let count = 1;
            for(let item of printerRows) {
            item.childNodes[0].innerHTML = count;
            divPrintersContainer.appendChild(item);
            count++;
        }
        sortedPrinterInventaryNumber = false;
        }
    });
    
    linkSortingPrinterLocation.addEventListener('click', function() {
        printerRows = [...document.querySelectorAll('.contentItemPrinterContract')];
        if(sortedPrinterLocation == false) {
            printerRows.sort((a, b) => (a.childNodes[4].innerText > b.childNodes[4].innerText) ? 1 : ((b.childNodes[4].innerText > a.childNodes[4].innerText) ? -1 : 0));
            divPrintersContainer.innerHTML = '';
            let count = 1;
            for(let item of printerRows) {
            item.childNodes[0].innerHTML = count;
            divPrintersContainer.appendChild(item);
            count++;
        }
        sortedPrinterLocation = true;
        } else {
            printerRows.sort((a, b) => (a.childNodes[4].innerText < b.childNodes[4].innerText) ? 1 : ((b.childNodes[4].innerText < a.childNodes[4].innerText) ? -1 : 0));
            divPrintersContainer.innerHTML = '';
            let count = 1;
            for(let item of printerRows) {
            item.childNodes[0].innerHTML = count;
            divPrintersContainer.appendChild(item);
            count++;
        }
        sortedPrinterLocation = false;
        }
    });
}
    if($('#cartridgeCountSortLink')[0] != null) {
    linkSortingCartridgeCount.addEventListener('click', function() {
        cartridgeRows = [...document.querySelectorAll('.contentItemCartridgeContract')];
        if(sortedCartridgeCount == false) {
        cartridgeRows.sort((a, b) => (a.childNodes[0].innerText - b.childNodes[0].innerText));
        divCartridgesContainer.innerHTML = '';
        for(let item of cartridgeRows) {
            divCartridgesContainer.appendChild(item);
        }
        sortedCartridgeCount = true;
    } else {
        cartridgeRows.sort((a, b) => (b.childNodes[0].innerText - a.childNodes[0].innerText));
        divCartridgesContainer.innerHTML = '';
        for(let item of cartridgeRows) {
            divCartridgesContainer.appendChild(item);
        }
        sortedCartridgeCount = false; 
    }
    });
    
    linkSortingName.addEventListener('click', function() {
        cartridgeRows = [...document.querySelectorAll('.contentItemCartridgeContract')];
        if(sortedCartridgeNamesUp == false) {
        cartridgeRows.sort((a, b) => (a.childNodes[1].innerText > b.childNodes[1].innerText) ? 1 : ((b.childNodes[1].innerText > a.childNodes[1].innerText) ? -1 : 0));
        divCartridgesContainer.innerHTML = '';
        let count = 1;
        for(let item of cartridgeRows) {
            item.childNodes[0].innerHTML = count;
            divCartridgesContainer.appendChild(item);
            count++;
        }
        sortedCartridgeNamesUp = true;
    } else {
        cartridgeRows.sort((a, b) => (a.childNodes[1].innerText < b.childNodes[1].innerText) ? 1 : ((b.childNodes[1].innerText < a.childNodes[1].innerText) ? -1 : 0));
        divCartridgesContainer.innerHTML = '';
        let count = 1;
        for(let item of cartridgeRows) {
            item.childNodes[0].innerHTML = count;
            divCartridgesContainer.appendChild(item);
            count++;
        }
        sortedCartridgeNamesUp = false;
    }
        
    });
    
    linkSortingItemCode.addEventListener('click', function() {
        cartridgeRows = [...document.querySelectorAll('.contentItemCartridgeContract')];
        if(sortedCartridgeItemCode == false) {
            cartridgeRows.sort((a, b) => (a.childNodes[2].innerText > b.childNodes[2].innerText) ? 1 : ((b.childNodes[2].innerText > a.childNodes[2].innerText) ? -1 : 0));
        divCartridgesContainer.innerHTML = '';
        let count = 1;
        for(let item of cartridgeRows) {
            item.childNodes[0].innerHTML = count;
            divCartridgesContainer.appendChild(item);
            count++;
        }
        sortedCartridgeItemCode = true;
        } else {
            cartridgeRows.sort((a, b) => (a.childNodes[2].innerText < b.childNodes[2].innerText) ? 1 : ((b.childNodes[2].innerText < a.childNodes[2].innerText) ? -1 : 0));
        divCartridgesContainer.innerHTML = '';
        let count = 1;
        for(let item of cartridgeRows) {
            item.childNodes[0].innerHTML = count;
            divCartridgesContainer.appendChild(item);
            count++;
        }
        sortedCartridgeItemCode = false;
        }
    });
    
    linkSortingCartridgeType.addEventListener('click', function() {
         cartridgeRows = [...document.querySelectorAll('.contentItemCartridgeContract')];
         if(sortedCartridgeType == false) {
             cartridgeRows.sort((a, b) => (a.childNodes[3].innerText > b.childNodes[3].innerText) ? 1 : ((b.childNodes[3].innerText > a.childNodes[3].innerText) ? -1 : 0));
        divCartridgesContainer.innerHTML = '';
        let count = 1;
        for(let item of cartridgeRows) {
            item.childNodes[0].innerHTML = count;
            divCartridgesContainer.appendChild(item);
            count++;
        }
        sortedCartridgeType = true;
         } else {
             cartridgeRows.sort((a, b) => (a.childNodes[3].innerText < b.childNodes[3].innerText) ? 1 : ((b.childNodes[3].innerText < a.childNodes[3].innerText) ? -1 : 0));
        divCartridgesContainer.innerHTML = '';
        let count = 1;
        for(let item of cartridgeRows) {
            item.childNodes[0].innerHTML = count;
            divCartridgesContainer.appendChild(item);
            count++;
        }
        sortedCartridgeType = false;
         }
    });
    
    linkSortingCartridgeStatus.addEventListener('click', function() {
        cartridgeRows = [...document.querySelectorAll('.contentItemCartridgeContract')];
        if(sortedCartridgeStatus == false) {
            cartridgeRows.sort((a, b) => (a.childNodes[4].innerText > b.childNodes[4].innerText) ? 1 : ((b.childNodes[4].innerText > a.childNodes[4].innerText) ? -1 : 0));
        divCartridgesContainer.innerHTML = '';
        let count = 1;
        for(let item of cartridgeRows) {
            item.childNodes[0].innerHTML = count;
            divCartridgesContainer.appendChild(item);
            count++;
        }
        sortedCartridgeStatus = true;
        } else {
            cartridgeRows.sort((a, b) => (a.childNodes[4].innerText < b.childNodes[4].innerText) ? 1 : ((b.childNodes[4].innerText < a.childNodes[4].innerText) ? -1 : 0));
        divCartridgesContainer.innerHTML = '';
        let count = 1;
        for(let item of cartridgeRows) {
            item.childNodes[0].innerHTML = count;
            divCartridgesContainer.appendChild(item);
            count++;
        }
        sortedCartridgeStatus = false;
        }
    });
    
    
    linkSortingCartridgeLocation.addEventListener('click', function() {
         cartridgeRows = [...document.querySelectorAll('.contentItemCartridgeContract')];
        if(sortedCartridgeLocation == false) {
            cartridgeRows.sort((a, b) => (a.childNodes[5].innerText > b.childNodes[5].innerText) ? 1 : ((b.childNodes[5].innerText > a.childNodes[5].innerText) ? -1 : 0));
        divCartridgesContainer.innerHTML = '';
        let count = 1;
        for(let item of cartridgeRows) {
            item.childNodes[0].innerHTML = count;
            divCartridgesContainer.appendChild(item);
            count++;
        }
        sortedCartridgeLocation = true;
        } else {
            cartridgeRows.sort((a, b) => (a.childNodes[5].innerText < b.childNodes[5].innerText) ? 1 : ((b.childNodes[5].innerText < a.childNodes[5].innerText) ? -1 : 0));
        divCartridgesContainer.innerHTML = '';
        let count = 1;
        for(let item of cartridgeRows) {
            item.childNodes[0].innerHTML = count;
            divCartridgesContainer.appendChild(item);
            count++;
        }
        sortedCartridgeLocation = false;
        }
    });
    
    }
};