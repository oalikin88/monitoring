/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


const content = document.querySelector('#wrap');
const pagin = document.querySelector('#content');
const buttonsRow = document.querySelector("#buttonsRow");
const iconFilter = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-filter-square" viewBox="0 0 16 16"> <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/> <path d="M6 11.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5z"/> </svg>';
const pageParam = window.location.search
        .replace('?', '')
        .split('&');

let arrRequest = null; 


if(document.readyState == 'loading') {
    arrRequest = window.location.search
        .replace('?', '')
        .split('&')
        .reduce(
                function (p, e) {
                    var a = e.split('=');
                    p[ decodeURIComponent(a[0])] = decodeURIComponent(a[1]);
                    return p;
                },
                 {}
        );
}

window.onload = function () {
  
  
  let wrapper = document.createElement('div');
  wrapper.className = 'wrapper';
  content.appendChild(wrapper);
  
  let title = document.createElement('h3');
  title.className = "text-center titleLocation fw-bold";
  title.innerText = locationInfo.name;
  wrapper.appendChild(title);
  
  if(input.length > 0) {
      
      
        let filterRow = document.createElement('div');
        filterRow.className = 'row mb-3 text-end';
        wrapper.appendChild(filterRow);
        
        let bufferCol = document.createElement('div');
        bufferCol.className = 'col col-8';
        filterRow.appendChild(bufferCol);
        
        
        let filterCol = document.createElement('div');
        filterCol.className = 'col col-4';
        filterRow.appendChild(filterCol);
        
        
        let formContainer = document.createElement('form');
        formContainer.setAttribute('th:action', '@{/printersbylocation}');
        formContainer.setAttribute('action', '/printersbylocation');
        formContainer.setAttribute('th:object', '${dto}');
        formContainer.setAttribute('method', 'GET');
        formContainer.id = 'formFilter';

        filterCol.appendChild(formContainer);

        


        inputGroupFilterDiv = document.createElement('div');
        inputGroupFilterDiv.className = 'input-group';

        inputGroupFilterLabel = document.createElement('span');
        inputGroupFilterLabel.className = 'input-group-text';
        inputGroupFilterLabel.id = 'filterLabel';
        inputGroupFilterLabel.innerHTML = iconFilter;

        filterSubmit = document.createElement('button');
        filterSubmit.type = 'submit';
        filterSubmit.id = 'submitFilter';
        filterSubmit.className = 'btn btn-light';
        
        imgInner = document.createElement('img');
        imgInner.src = '/img/search.svg?height=16';
        filterSubmit.appendChild(imgInner);
        
        
        inputGroupFilterInput = document.createElement('input');
        inputGroupFilterInput.className = 'form-control';
        inputGroupFilterInput.setAttribute('th:field', '*{inventaryNumber}');
        inputGroupFilterInput.name = 'inventaryNumber';
        inputGroupFilterInput.placeholder = 'поиск по инвентарному номеру';

        inputGroupFilterHideFieldLocation = document.createElement('input');
        inputGroupFilterHideFieldLocation.className = 'form-control';
        inputGroupFilterHideFieldLocation.setAttribute('th:field', '*{location}');
        inputGroupFilterHideFieldLocation.name = 'location';
        inputGroupFilterHideFieldLocation.type = 'hidden';
        inputGroupFilterHideFieldLocation.value = locationInfo.id;


        inputGroupFilterHideFieldPrinterId = document.createElement('input');
        inputGroupFilterHideFieldPrinterId.className = 'form-control';
        inputGroupFilterHideFieldPrinterId.setAttribute('th:field', '*{idModel}');
        inputGroupFilterHideFieldPrinterId.name = 'idModel';
        inputGroupFilterHideFieldPrinterId.type = 'hidden';
        if(arrRequest) {
            inputGroupFilterHideFieldPrinterId.value = null;
        } else {
            inputGroupFilterHideFieldPrinterId.value = arrRequest.idModel;
        }
        


        formContainer.append(inputGroupFilterDiv);
        inputGroupFilterDiv.appendChild(inputGroupFilterHideFieldLocation);
        inputGroupFilterDiv.appendChild(inputGroupFilterHideFieldPrinterId);
        inputGroupFilterDiv.appendChild(inputGroupFilterLabel);
        inputGroupFilterDiv.appendChild(inputGroupFilterInput);
        inputGroupFilterDiv.appendChild(filterSubmit);
        
      
      
      headTable = document.createElement('div');
      headTable.className = "row fw-bold";
      wrapper.appendChild(headTable);
      
      headTableNumber = document.createElement('div');
      headTableNumber.className = "col col-1 my-auto mx-auto text-start";
      headTableNumber.innerText = "№";
      headTable.appendChild(headTableNumber);
      
    
      headTablePrinterName = document.createElement('div');
      headTablePrinterName.className = "col col-2 my-auto mx-auto text-start";
      
        linkSortingModel = document.createElement('a');
        linkSortingModel.href = '#';
        linkSortingModel.id = 'modelSortLink';
        imgInnerModel = document.createElement('img');
        imgInnerModel.src = '/img/sorting.png';
        imgInnerModel.alt = 'Сортировка';
        imgInnerModel.className = 'imgSorting';
        imgInnerModel.id = 'modelSort';
        linkSortingModel.appendChild(imgInnerModel);
        headTablePrinterName.appendChild(linkSortingModel);
      
      textModel = document.createElement('span');
        textModel.innerText = "Модель";
        headTablePrinterName.appendChild(textModel);
      headTable.appendChild(headTablePrinterName);
      
      headTableInventoryNumber = document.createElement('div');
      headTableInventoryNumber.className = "col col-3 my-auto mx-auto text-start";
      
        linkSortInventaryNumber = document.createElement('a');
        linkSortInventaryNumber.href = '#';
        linkSortInventaryNumber.id = 'inventarySortLink';
        imgInnerSortInventaryNumber = document.createElement('img');
        imgInnerSortInventaryNumber.src = '/img/sorting.png';
        imgInnerSortInventaryNumber.alt = 'Сортировка';
        imgInnerSortInventaryNumber.className = 'imgSorting';
        imgInnerSortInventaryNumber.id = 'inventarySort';
        linkSortInventaryNumber.appendChild(imgInnerSortInventaryNumber);
        headTableInventoryNumber.appendChild(linkSortInventaryNumber);
      
        textModel = document.createElement('span');
        textModel.innerText = "Инвентарный номер";
        headTableInventoryNumber.appendChild(textModel);
      
      headTable.appendChild(headTableInventoryNumber);
      
      headTableSerialNumber = document.createElement('div');
      headTableSerialNumber.className = "col col-2 my-auto mx-auto text-start";
      
      
        linkSortSerialNumber = document.createElement('a');
        linkSortSerialNumber.href = '#';
        linkSortSerialNumber.id = 'serialSortLink';
        imgInnerSortSerialNumber = document.createElement('img');
        imgInnerSortSerialNumber.src = '/img/sorting.png';
        imgInnerSortSerialNumber.alt = 'Сортировка';
        imgInnerSortSerialNumber.className = 'imgSorting';
        imgInnerSortSerialNumber.id = 'serialSort';
        linkSortSerialNumber.appendChild(imgInnerSortSerialNumber);
        headTableSerialNumber.appendChild(linkSortSerialNumber);
      
        textModel = document.createElement('span');
        textModel.innerText = "Серийный номер";
        headTableSerialNumber.appendChild(textModel);
      
      headTable.appendChild(headTableSerialNumber);
      
      headTableContractNumber = document.createElement('div');
      headTableContractNumber.className = "col col-2 my-auto mx-auto text-start";
      
        linkSortContractNumber = document.createElement('a');
        linkSortContractNumber.href = '#';
        linkSortContractNumber.id = 'contractNumberSortLink';
        imgInnerSortContractNumber = document.createElement('img');
        imgInnerSortContractNumber.src = '/img/sorting.png';
        imgInnerSortContractNumber.alt = 'Сортировка';
        imgInnerSortContractNumber.className = 'imgSorting';
        imgInnerSortContractNumber.id = 'contractNumberSort';
        linkSortContractNumber.appendChild(imgInnerSortContractNumber);
        headTableContractNumber.appendChild(linkSortContractNumber);
      
        textModel = document.createElement('span');
        textModel.innerText = "Контракт";
        headTableContractNumber.appendChild(textModel);
      
      headTable.appendChild(headTableContractNumber);
      
      headTableContractDate = document.createElement('div');
      headTableContractDate.className = "col col-2 my-auto mx-auto text-start";
      
        linkSortDate = document.createElement('a');
        linkSortDate.href = '#';
        linkSortDate.id = 'dateSortLink';
        imgInnerSortDate = document.createElement('img');
        imgInnerSortDate.src = '/img/sorting.png';
        imgInnerSortDate.alt = 'Сортировка';
        imgInnerSortDate.className = 'imgSorting';
        imgInnerSortDate.id = 'dateSort';
        linkSortDate.appendChild(imgInnerSortDate);
        headTableContractDate.appendChild(linkSortDate);
      
        textModel = document.createElement('span');
        textModel.innerText = "Дата";
        headTableContractDate.appendChild(textModel);
      
      headTable.appendChild(headTableContractDate);
      
      for(i = 0; i < input.length; i ++) {
      
      contentRow = document.createElement('div');
      contentRow.className = "row contentRow pb-2 mb-2 mt-2 pt-2 text-left text-break";
      wrapper.appendChild(contentRow);
      
      contentRowCount = document.createElement('div');
      contentRowCount.className = "col col-1 my-auto";
      contentRowCount.innerText = i + 1;
      contentRow.appendChild(contentRowCount);
      
      contentRowPrinterName = document.createElement('div');
      contentRowPrinterName.className = 'col col-2';
      contentRowPrinterName.id = "idModel_" + input[i].id;
      contentRow.appendChild(contentRowPrinterName);
      
      
      link = document.createElement('a');
      link.setAttribute('href', '/editprinter?idPrinter=' + input[i].id);
      link.innerText = input[i].manufacturer + " " + input[i].model;
      contentRowPrinterName.appendChild(link);
      
      contentRowInventoryNumber = document.createElement('div');
      contentRowInventoryNumber.className = 'col col-3';
      contentRowInventoryNumber.innerText = input[i].inventaryNumber;
      contentRow.appendChild(contentRowInventoryNumber);
      
      contentRowSerialNumber = document.createElement('div');
      contentRowSerialNumber.className = 'col col-2';
      contentRowSerialNumber.innerText = input[i].serialNumber;
      contentRow.appendChild(contentRowSerialNumber);
      
      contentRowContractNumber = document.createElement('div');
      contentRowContractNumber.className = 'col col-2';
      contentRowContractNumber.innerText = input[i].contractNumber;
      contentRow.appendChild(contentRowContractNumber);
      
      contentRowContractDate = document.createElement('div');
      contentRowContractDate.className = 'col col-2';
      
      startContractDate = new Date(input[i].startContract);
      startContractDateFormat = startContractDate.toLocaleDateString('ru');
      
      contentRowContractDate.innerText = startContractDateFormat;
      contentRow.appendChild(contentRowContractDate);
      
        }
      
  } else {
      
      message = document.createElement('h4');
      message.innerText = "принтеры отсутствуют";
      wrapper.appendChild(message);
      
  }
  
  if (pages > 0) {

        var paginationNav = document.createElement('nav');
        var paginationUl = document.createElement('ul');
        paginationUl.className = 'pagination';
        content.appendChild(paginationNav);
        paginationNav.appendChild(paginationUl);


        if (pageable.pageNumber == 0) {
            var paginationPreviousLi = document.createElement('li');
            paginationPreviousLi.className = 'page-item disabled';
            previousPageLink = document.createElement('a');
            previousPageLink.className = 'page-link';
            previousPageLink.href = "#";
            previousPageLink.innerText = "Предыдущая";
            var spanPreviuosAria = document.createElement('span');
            spanPreviuosAria.setAttribute('aria-hidden', true);

            paginationPreviousLi.appendChild(previousPageLink);
            previousPageLink.appendChild(spanPreviuosAria);
            paginationUl.appendChild(paginationPreviousLi);
        } else {
            var paginationPreviousLi = document.createElement('li');
            paginationPreviousLi.className = 'page-item';
            previousPageLink = document.createElement('a');
            previousPageLink.className = 'page-link';
            previousPageLink.href = getLinkForPage(pageParam, pageable.pageNumber - 1);
            previousPageLink.innerText = "Предыдущая";
            spanPreviuosAria = document.createElement('span');
            spanPreviuosAria.setAttribute('aria-hidden', true);

            paginationPreviousLi.appendChild(previousPageLink);
            previousPageLink.appendChild(spanPreviuosAria);
            paginationUl.appendChild(paginationPreviousLi);
        }
        for (let i = 0; i < pages; i++) {

            if (pageable.pageNumber == i) {
                var paginationElLi = document.createElement('li');
                paginationElLi.className = 'page-item active';
                var paginationElLink = document.createElement('a');
                paginationElLink.className = 'page-link';
                paginationElLink.href = "#";
                paginationElLink.innerText = i + 1;
                paginationElLi.appendChild(paginationElLink);
                paginationUl.appendChild(paginationElLi);
            } else {
                var paginationElLi = document.createElement('li');
                paginationElLi.className = 'page-item';
                paginationElLink = document.createElement('a');
                paginationElLink.className = 'page-link';
                paginationElLink.href = getLinkForPage(pageParam, i);
                paginationElLink.innerText = i + 1;
                paginationElLi.appendChild(paginationElLink);
                paginationUl.appendChild(paginationElLi);
            }
        }
        if (pageable.pageNumber == (pages - 1)) {
            var paginationNextLi = document.createElement('li');
            paginationNextLi.className = 'page-item disabled';
            var paginationNextLink = document.createElement('a');
            paginationNextLink.className = 'page-link';
            paginationNextLink.href = "#";
            paginationNextLink.innerText = "Следующая";
            var spanNextAria = document.createElement('span');
            spanNextAria.setAttribute('aria-hidden', true);

            paginationNextLi.appendChild(paginationNextLink);
            paginationNextLink.appendChild(spanNextAria);
            paginationUl.appendChild(paginationNextLi);
        } else {
            var paginationNextLi = document.createElement('li');
            paginationNextLi.className = 'page-item';
            var paginationNextLink = document.createElement('a');
            paginationNextLink.className = 'page-link';
            paginationNextLink.href = getLinkForPage(pageParam, pageable.pageNumber + 1);
            paginationNextLink.innerText = "Следущая";
            spanNextAria = document.createElement('span');
            spanNextAria.setAttribute('aria-hidden', true);

            paginationNextLi.appendChild(paginationNextLink);
            paginationNextLink.appendChild(spanNextAria);
            paginationUl.appendChild(paginationNextLi);
        }
    }
  
    btnRow = document.createElement('div');
    btnRow.className = 'row rowButtons';
  
    btnDiv = document.createElement('div');
    btnDiv.className = 'col';
  
    btnCancel = document.createElement('input');
    btnCancel.setAttribute('type', 'button');
    btnCancel.className = 'btn';
    btnCancel.value = "Назад";
    
    buttonsRow.appendChild(btnRow);
    btnRow.appendChild(btnDiv);
    btnDiv.appendChild(btnCancel);
    
    btnCancel.addEventListener('click' , function() {
         location.href = document.referrer;
    });
    
    let modelSort = document.querySelector('#modelSortLink');
    modelSort.addEventListener('click', function(){
        
        if(window.location.pathname.indexOf('inventories') > 0) {
          sortBy("model");
      } else {
          sortBy("model.name");
      }
        
      });
    
    let dateSort = document.querySelector('#dateSortLink');
    dateSort.addEventListener('click', function () {
        
         if(window.location.pathname.indexOf('inventories') > 0) {
          sortBy("contract.dateStartContract");
      } else {
          sortBy("contr.date_start_contract");
      }
  });
    
    let contractSort = document.querySelector('#contractNumberSortLink');
    contractSort.addEventListener('click', function () {
        if(window.location.pathname.indexOf('inventories') > 0) {
          sortBy("contract.contractNumber");
      } else {
          sortBy("contr.contract_number");
      }
    });
    
    let inventaryNumberSort = document.querySelector('#inventarySortLink');
    inventaryNumberSort.addEventListener('click', function() {
          if(window.location.pathname.indexOf('inventories') > 0) {
          sortBy("inventoryNumber");
      } else {
          sortBy("inventory_number");
      }
       
    });
    
    let serialNumberSort = document.querySelector('#serialSortLink');
    serialNumberSort.addEventListener('click', function() {
        if(window.location.pathname.indexOf('inventories') > 0) {
          sortBy("serialNumber");
      } else {
          sortBy("serial_number");
      }
    });
    
    setInterval('AJAXPing()', 28000);
    
    

    
};




function getLinkForPage(inputHref, target) {
    getLink = '';
    pageInLinFlag = false;
    for (j = 0; j < inputHref.length; j++) {
        if (pageParam[j].indexOf('page') >= 0) {
            pageParam[j] = "page=" + target;
            pageInLinFlag = true;
        }
        if (j == 0) {
            getLink += "?" + pageParam[j];
        } else {
            getLink += "&" + pageParam[j];
        }
    }
    if (pageInLinFlag == false) {
        getLink += "&page=" + target;
    }
    return getLink;

}

function sortBy (link) { //Разобраться почему подставляет запрос sortBy 2 раза
        let request = false;
        let directionFlag = false;

        let pagePar = window.location.search
                .replace('?', '')
                .split('&');

        for (let i = 0; i < pagePar.length; i++) {
            if (pagePar[i].indexOf('sortBy=') >= 0) {
                request = true;
                pagePar[i] = "sortBy=" + link;
            }
            if ((pagePar[i].indexOf('direction')) >= 0) {
                directionFlag = true;
                if (direction == "up") {
                    pagePar[i] = "direction=down";
                } else {
                    pagePar[i] = "direction=up";
                }
            }
        }
        let adress = '';
        for (let i = 0; i < pagePar.length; i++) {
            if (i == 0) {
                adress += "?" + pagePar[i];
            } else {
                adress += "&" + pagePar[i];
            }
        }
        if (request == false) {
            adress += "&sortBy=" + link;
        }
        if (directionFlag == false) {
            adress += "&direction=up";
        }
        this.event.target.parentElement.href = window.location.pathname + adress;
        this.event.target.parentElement.click();
    };