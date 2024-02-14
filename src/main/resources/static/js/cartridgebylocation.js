/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

const content = document.querySelector('#wrap');
const pagin = document.querySelector('#content');
const buttonsRow = document.querySelector("#buttonsRow");
let pageBuf = 25;

const iconFilter = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-filter-square" viewBox="0 0 16 16"> <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/> <path d="M6 11.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5z"/> </svg>';
let arrRequest = window.location.search
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

var pageParam = window.location.search
        .replace('?', '')
        .split('&');


$(document).ready(function () {

    let wrapper = document.createElement('div');
    wrapper.className = 'wrapper';
    content.appendChild(wrapper);

    let title = document.createElement('h3');
    title.className = "text-center titleLocation fw-bold";
    title.innerText = locationInfo.name;
    wrapper.append(title);

    if (input.length > 0) {

        let btnAfterTitleRow = document.createElement('div');
        btnAfterTitleRow.className = 'row buttonsRow mb-5';
        wrapper.appendChild(btnAfterTitleRow);

        btnAfterTitleCol1 = document.createElement('div');
        btnAfterTitleCol1.className = 'col col-4';
        
        

        btnAfterTitleCol2 = document.createElement('div');
        btnAfterTitleCol2.className = 'col col-4';

        btnAfterTitleCol3 = document.createElement('div');
        btnAfterTitleCol3.className = 'col col-4';
        
        // Отображение записей на странице
        
        var pageSizeNav = document.createElement('nav');
        var pageSizeNavUl = document.createElement('ul');
        pageSizeNavUl.className = 'pagination mt-0';
        btnAfterTitleCol3.appendChild(pageSizeNav);
        pageSizeNav.appendChild(pageSizeNavUl);
        
      
        
        var paginationTitle = document.createElement('li');
        paginationTitle.className = 'page-item disabled';
        paginationTitleLink = document.createElement('a');
        paginationTitleLink.className = 'page-link';
        paginationTitleLink.href = "#";
        paginationTitleLink.innerText = "Записей на странице";
        var spanPaginationTitleAria = document.createElement('span');
        spanPaginationTitleAria.setAttribute('aria-hidden', true);
        paginationTitle.appendChild(paginationTitleLink);
        paginationTitleLink.appendChild(spanPaginationTitleAria);
        pageSizeNavUl.appendChild(paginationTitle); 
        
        
        
        for(let j = 0; j < 3; j++) {
            var paginationPageSizeEl = document.createElement('li');
            if(pageable.pageSize == pageBuf) {
                paginationPageSizeEl.className = 'page-item active';
            } else {
                 paginationPageSizeEl.className = 'page-item';
            }
            paginationPageSizeLink = document.createElement('a');
            paginationPageSizeLink.className = 'page-link';
            
            paginationPageSizeLink.href = getLinkForPageSize(pageParam, pageBuf);
            paginationPageSizeLink.innerText = pageBuf;
            pageBuf = pageBuf * 2;
            paginationPageSizeEl.appendChild(paginationPageSizeLink);
            pageSizeNavUl.appendChild(paginationPageSizeEl);
        }    
        
        pageBuf = pageable.pageSize;
        
        
        let choiceBtn = document.createElement('button');
        choiceBtn.className = 'btn btn-secondary';
        choiceBtn.type = 'button';
        choiceBtn.innerText = 'Выбрать';
        btnAfterTitleCol1.appendChild(choiceBtn);

        btnAfterTitleRow.appendChild(btnAfterTitleCol1);
        btnAfterTitleRow.appendChild(btnAfterTitleCol2);
        btnAfterTitleRow.appendChild(btnAfterTitleCol3);

        filterContainer = document.createElement('div');
        filterContainer.id = 'filterContainer';
        btnAfterTitleCol1.appendChild(filterContainer);
        
        formContainerSearch = document.createElement('form');
        formContainerSearch.setAttribute('th:action', '@{/getcartridgesbymodel}');
        formContainerSearch.setAttribute('action', '/getcartridgesbymodel');
        formContainerSearch.setAttribute('th:object', '${dto}');
        formContainerSearch.setAttribute('method', 'GET');
        formContainerSearch.id = 'formContainerSearch';
        
        filterContainer.appendChild(formContainerSearch);
        
        
        inputGroupFilter = document.createElement('div');
        inputGroupFilter.className = 'input-group';
        inputGroupFilter.id = 'inputGroupFilter';
        formContainerSearch.appendChild(inputGroupFilter);
        
        inputFilter = document.createElement('input');
        inputFilter.className = 'form-control';
        inputFilter.id = 'inputFilter';
        inputFilter.placeholder = 'поиск по номенклатурному коду';
        inputFilter.name = 'itemCode';
        inputFilter.setAttribute('th:field', '*{itemCode}');
        inputFilter.type = 'text';
        inputGroupFilter.appendChild(inputFilter);
        
        selectDropdown = document.createElement('select');
        selectDropdown.className = 'form-select';
        selectDropdown.id = 'selectSearch';
        inputGroupFilter.appendChild(selectDropdown);
        
        itemCodeOption = document.createElement('option');
        itemCodeOption.value = 'itemCode';
        itemCodeOption.innerHTML = 'номенклатурный код';
        itemCodeOption.selected = true;
        selectDropdown.appendChild(itemCodeOption);
        
        modelCartridgeOption = document.createElement('option');
        modelCartridgeOption.value = 'modelCartridge';
        modelCartridgeOption.innerHTML = 'модель';
        selectDropdown.appendChild(modelCartridgeOption);
        
        
        filterBtnApply = document.createElement('button');
        filterBtnApply.id = 'filterBtnApply';
        filterBtnApply.type = 'submit';
        filterBtnApply.className = 'btn btn-outline-secondary btn-search';
        inputGroupFilter.appendChild(filterBtnApply);
        
        imgInner = document.createElement('img');
        imgInner.src = '/img/search.svg?height=16';
        filterBtnApply.appendChild(imgInner);
        
     
        
        searchFilterHideFieldLocation = document.createElement('input');
        searchFilterHideFieldLocation.className = 'form-control';
        searchFilterHideFieldLocation.setAttribute('th:field', '*{location}');
        searchFilterHideFieldLocation.name = 'location';
        searchFilterHideFieldLocation.type = 'hidden';
        searchFilterHideFieldLocation.value = locationInfo.id;


        searchFilterHideFieldPrinterId = document.createElement('input');
        searchFilterHideFieldPrinterId.className = 'form-control';
        searchFilterHideFieldPrinterId.setAttribute('th:field', '*{idModel}');
        searchFilterHideFieldPrinterId.name = 'idModel';
        searchFilterHideFieldPrinterId.type = 'hidden';
        if(arrRequest.idModel) {
             searchFilterHideFieldPrinterId.value = arrRequest.idModel;
        } else {
        searchFilterHideFieldPrinterId.value = null;
        }

        
        
        inputGroupFilter.appendChild(searchFilterHideFieldLocation);
        inputGroupFilter.appendChild(searchFilterHideFieldPrinterId);

        btnAfterTitleCol2.appendChild(filterContainer);

       

        headTable = document.createElement('div');
        headTable.className = "row fw-bold";
        wrapper.appendChild(headTable);

        headTableChoice = document.createElement('div');
        headTableChoice.className = "col col-md-1 my-auto mx-auto text-start checkboxDiv";
        headTableChoice.hidden = true;
        headTable.appendChild(headTableChoice);

        checkboxAll = document.createElement('input');
        checkboxAll.className = 'form-check-input';
        checkboxAll.id = 'checkboxAll';
        checkboxAll.type = 'checkbox';
        headTableChoice.appendChild(checkboxAll);

        headTableNumber = document.createElement('div');
        headTableNumber.className = "col col-md-1 my-auto mx-auto text-start";
        headTableNumber.innerText = "№";
        headTable.appendChild(headTableNumber);

        headTableModel = document.createElement('div');
        headTableModel.className = "col my-auto mx-auto text-start";
        headTable.appendChild(headTableModel);

        linkSortingModel = document.createElement('a');
        linkSortingModel.href = '#';
        linkSortingModel.id = 'modelSortLink';
        imgInnerModel = document.createElement('img');
        imgInnerModel.src = '/img/sorting.png';
        imgInnerModel.alt = 'Сортировка';
        imgInnerModel.className = 'imgSorting';
        imgInnerModel.id = 'modelSort';
        linkSortingModel.appendChild(imgInnerModel);
        headTableModel.appendChild(linkSortingModel);
        textModel = document.createElement('span');
        textModel.innerText = "Модель";
        headTableModel.appendChild(textModel);
        
        headTableItemCode = document.createElement('div');
        headTableItemCode.className = "col my-auto mx-auto text-start";
        headTable.appendChild(headTableItemCode);

        linkSortingItemCode = document.createElement('a');
        linkSortingItemCode.href = '#';
        linkSortingItemCode.id = 'itemCodeSortLink';
        imgInnerItemCode = document.createElement('img');
        imgInnerItemCode.src = '/img/sorting.png';
        imgInnerItemCode.alt = 'Сортировка';
        imgInnerItemCode.className = 'imgSorting';
        imgInnerItemCode.id = 'itemCodeSort';
        linkSortingItemCode.appendChild(imgInnerItemCode);
        headTableItemCode.appendChild(linkSortingItemCode);
        textItemCode = document.createElement('span');
        textItemCode.innerText = "Номенклатурный код";
        headTableItemCode.appendChild(textItemCode);
        
        

        headTableContractNumber = document.createElement('div');
        headTableContractNumber.className = "col my-auto mx-auto text-start";

        linkSortingContractNumber = document.createElement('a');
        linkSortingContractNumber.href = '#';
        linkSortingContractNumber.id = 'contractSortLink';
        imgInnerContractNumber = document.createElement('img');
        imgInnerContractNumber.src = '/img/sorting.png';
        imgInnerContractNumber.alt = 'Сортировка';
        imgInnerContractNumber.className = 'imgSorting';
        imgInnerContractNumber.id = 'contractSort';
        linkSortingContractNumber.appendChild(imgInnerContractNumber);
        headTableContractNumber.appendChild(linkSortingContractNumber);
        textContractNumber = document.createElement('span');
        textContractNumber.innerText = "Контракт";
        headTableContractNumber.appendChild(textContractNumber);

        headTable.appendChild(headTableContractNumber);


        headTableDate = document.createElement('div');
        headTableDate.className = "col my-auto mx-auto text-start";

        linkSortingDate = document.createElement('a');
        linkSortingDate.href = '#';
        linkSortingDate.id = 'dateSortLink';
        imgInnerDate = document.createElement('img');
        imgInnerDate.src = '/img/sorting.png';
        imgInnerDate.alt = 'Сортировка';
        imgInnerDate.className = 'imgSorting';
        imgInnerDate.id = 'dateSort';
        linkSortingDate.appendChild(imgInnerDate);
        headTableDate.appendChild(linkSortingDate);
        textDate = document.createElement('span');
        textDate.innerText = "Дата";
        headTableDate.appendChild(textDate);

        //headTableDate.innerText = "Дата";
        headTable.appendChild(headTableDate);
        checkboxAll.addEventListener('click', function () {
            toggle(this);
        });

        for (i = 0; i < input.length; i++) {

            contentRow = document.createElement('div');
            contentRow.className = "row contentRow pb-2 mb-2 mt-2 pt-2";
            wrapper.appendChild(contentRow);

            contentChoiceDiv = document.createElement('div');
            contentChoiceDiv.className = "col col-md-1 my-auto mx-auto text-start checkboxDiv";
            contentChoiceDiv.hidden = true;
            contentRow.appendChild(contentChoiceDiv);

            checkboxContent = document.createElement('input');
            checkboxContent.className = 'form-check-input';
            checkboxContent.type = 'checkbox';
            contentChoiceDiv.appendChild(checkboxContent);

            contentRowCount = document.createElement('div');
            contentRowCount.className = "col col-md-1 my-auto";
            contentRowCount.innerText = i + 1;
            contentRow.appendChild(contentRowCount);

            contentRowPrinterName = document.createElement('div');
            contentRowPrinterName.className = 'col';
            contentRowPrinterName.id = "idCartridge_" + input[i].id;
            contentRow.appendChild(contentRowPrinterName);


            link = document.createElement('a');
            link.setAttribute('href', '/editcartridge?idCartridge=' + input[i].id);
            link.innerText = input[i].manufacturer + " " + input[i].model;
            contentRowPrinterName.appendChild(link);
            
            contentRowItemCode = document.createElement('div');
            contentRowItemCode.className = 'col';
            contentRowItemCode.innerText = input[i].itemCode;
            contentRow.appendChild(contentRowItemCode);


            contentRowContractNumber = document.createElement('div');
            contentRowContractNumber.className = 'col';

            contentRowContractNumber.innerText = input[i].contractNumber;
            contentRow.appendChild(contentRowContractNumber);


            contentRowDateContract = document.createElement('div');
            contentRowDateContract.className = 'col';
            startContractDate = new Date(input[i].startContract);
            startContractDateFormat = startContractDate.toLocaleDateString('ru');
            contentRowDateContract.innerText = startContractDateFormat;
            contentRow.appendChild(contentRowDateContract);
        }

        choiceBtn.addEventListener('click', function () {
            if ($('#btnTransfer').length == 0) {
                btnTransfer = document.createElement('button');
                btnTransfer.className = "btn btn-secondary";
                btnTransfer.id = 'btnTransfer';
                btnTransfer.type = 'button';
                btnTransfer.innerText = 'Переместить';
                btnTransfer.setAttribute('data-bs-toggle', 'modal');
                btnTransfer.setAttribute('data-bs-target', '#modalTransfer');
                btnAfterTitleCol1.appendChild(btnTransfer);


                    $('.selectLocation').selectize({
        preload: true,
        valueField: 'id',
        labelField: 'name',
        searchField: "name",
        placeholder: 'локация',
        load: function (query, callback) {
            $.ajax({
                url: '/locations',
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback

            });
        }
    });

                locations = Object.entries($('.selectLocation')[0].selectize.options);
                for (i = 0; i < locations.length; i++) {
                    if (locations[i][1].id == loc.id) {
                        $('.selectLocation')[0].selectize.removeOption(locations[i][0]);
                    }
                }

            }

            let checkboxes = document.querySelectorAll('.checkboxDiv');

            for (i = 0; i < checkboxes.length; i++) {
                checkboxes[i].hidden = false;
            }
        });

    } else {

        message = document.createElement('h4');
        message.innerText = "картриджи отсутствуют";
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


    btnCol = document.createElement('div');
    btnCol.className = 'col';

    btnCancel = document.createElement('input');
    btnCancel.setAttribute('type', 'button');
    btnCancel.className = 'btn';
    btnCancel.value = "Назад";
    btnCol.appendChild(btnCancel);
    buttonsRow.appendChild(btnCol);
    btnCancel.addEventListener('click', function () {
        history.back();
    });
    modalTransfer = document.createElement('div');
    modalTransfer.className = 'modal fade';
    modalTransfer.id = 'modalTransfer';
    modalTransfer.setAttribute('data-bs-backdrop', 'static');
    modalTransfer.setAttribute('data-bs-keyboard', 'false');
    modalTransfer.setAttribute('tabindex', '-1');
    wrapper.appendChild(modalTransfer);
    modalDialogCenteredDiv = document.createElement('div');
    modalDialogCenteredDiv.className = 'modal-dialog modal-dialog-centered';
    modalTransfer.appendChild(modalDialogCenteredDiv);
    modalContentDiv = document.createElement('div');
    modalContentDiv.className = 'modal-content';
    modalDialogCenteredDiv.appendChild(modalContentDiv);
    modalHeaderDiv = document.createElement('div');
    modalHeaderDiv.className = 'modal-header';
    modalContentDiv.appendChild(modalHeaderDiv);
    modalTitleH1 = document.createElement('h1');
    modalTitleH1.className = 'modal-title fs-5';
    modalTitleH1.id = 'modalLocationsLabel';
    modalTitleH1.innerText = 'Перемещение картриджей';
    modalHeaderDiv.appendChild(modalTitleH1);
    modalBtnClose = document.createElement('button');
    modalBtnClose.className = 'btn-close';
    modalBtnClose.type = 'button';
    modalBtnClose.setAttribute('data-bs-dismiss', 'modal');
    modalBtnClose.setAttribute('aria-label', 'close');
    modalHeaderDiv.appendChild(modalBtnClose);
    modalBodyDiv = document.createElement('div');
    modalBodyDiv.className = 'modal-body';
    modalContentDiv.appendChild(modalBodyDiv);
    modalBodyContentInner = document.createElement('div');
    modalBodyContentInner.className = 'modalContentInner';
    modalBodyContentInner.id = 'modalContentInner';
    modalBodyDiv.appendChild(modalBodyContentInner);
    contentInnerRow = document.createElement('div');
    contentInnerRow.className = 'row';
    modalBodyContentInner.appendChild(contentInnerRow);
    labelContentInnerDiv = document.createElement('div');
    labelContentInnerDiv.className = 'col';
    labelContentInnerDiv.innerHTML = 'Выберите расположение';
    selectContentInnerDiv = document.createElement('div');
    selectContentInnerDiv.className = 'col';
    contentInnerRow.appendChild(labelContentInnerDiv);
    contentInnerRow.appendChild(selectContentInnerDiv);
    selectLocation = document.createElement('select');
    selectLocation.className = 'form-select selectLocation';
    selectLocation.id = 'selectLocation';
    selectLocation.name = "selectLocation";
    selectContentInnerDiv.appendChild(selectLocation);
    modalFooterDiv = document.createElement('div');
    modalFooterDiv.className = 'modal-footer';
    modalContentDiv.appendChild(modalFooterDiv);
    modalFooterCloseBtn = document.createElement('button');
    modalFooterCloseBtn.className = 'btn btn-secondary';
    modalFooterCloseBtn.type = 'button';
    modalFooterCloseBtn.setAttribute('data-bs-dismiss', 'modal');
    modalFooterCloseBtn.innerText = 'Отмена';
    modalFooterCloseBtn.id = "modalWindow1BtnClose";
    modalFooterDiv.appendChild(modalFooterCloseBtn);
    let modalFooterSubmitBtn = document.createElement('button');
    modalFooterSubmitBtn.className = 'btn btn-secondary';
    modalFooterSubmitBtn.type = 'button';
    modalFooterSubmitBtn.innerText = 'Применить';
    modalFooterCloseBtn.setAttribute('data-bs-dismiss', 'modal');
    modalFooterCloseBtn.id = "modalWindow1BtnOk";
    modalFooterDiv.appendChild(modalFooterSubmitBtn);

    // Модальное окно выбора параметров скачивания актов
    
    divModalActsParameters = document.createElement('div');
    divModalActsParameters.className = 'modal fade';
    divModalActsParameters.id = 'modalActsParameters';
    divModalActsParameters.setAttribute("data-bs-backdrop", "static");
    divModalActsParameters.setAttribute("data-bs-keyboard", false);
    divModalActsParameters.setAttribute('tabindex', '-1');
    wrapper.appendChild(divModalActsParameters);

    divModalDialogActsParameters = document.createElement('div');
    divModalDialogActsParameters.className = 'modal-dialog modal-xl';
    divModalActsParameters.appendChild(divModalDialogActsParameters);

    divModalContentActsParameters = document.createElement('div');
    divModalContentActsParameters.className = 'modal-content';
    divModalDialogActsParameters.appendChild(divModalContentActsParameters);

    divModalHeaderActsParameters = document.createElement('div');
    divModalHeaderActsParameters.className = 'modal-header';
    divModalContentActsParameters.appendChild(divModalHeaderActsParameters);

    titleModalActsParameters = document.createElement('h1');
    titleModalActsParameters.className = 'modal-title fs-5';
    titleModalActsParameters.id = 'titleModalActsParameters';
    titleModalActsParameters.innerText = 'Сформировать список установленных картриджей за период';

    btnCloseModalActsParameters = document.createElement('button');
    btnCloseModalActsParameters.className = 'btn-close';
    btnCloseModalActsParameters.setAttribute('data-bs-dismiss', 'modal');
    btnCloseModalActsParameters.setAttribute('aria-label', 'Close');
    btnCloseModalActsParameters.id = "btnCloseModalHeader";
    divModalHeaderActsParameters.appendChild(titleModalActsParameters);
    divModalHeaderActsParameters.appendChild(btnCloseModalActsParameters);

    divModalBodyActsParameters = document.createElement('div');
    divModalBodyActsParameters.className = 'modal-body';
    divModalContentActsParameters.appendChild(divModalBodyActsParameters);

    divModalContainerInnerActsParameters = document.createElement('div');
    divModalContainerInnerActsParameters.className = 'container-fluid mt-4';
    divModalContainerInnerActsParameters.id = "containerInnerModal";
    divModalBodyActsParameters.appendChild(divModalContainerInnerActsParameters);

    divContentRowCalendars = document.createElement('div');
    divContentRowCalendars.className = 'row rowModalContent';
    divModalContainerInnerActsParameters.appendChild(divContentRowCalendars);

    divBeginDate = document.createElement('div');
    divBeginDate.className = 'col';

    divEndDate = document.createElement('div');
    divEndDate.className = 'col';

    divContentRowCalendars.appendChild(divBeginDate);
    divContentRowCalendars.appendChild(divEndDate);
    
    divContentRowEmployeeSelect = document.createElement('div');
    divContentRowEmployeeSelect.className = 'row rowModalContent text-center';
    divModalContainerInnerActsParameters.appendChild(divContentRowEmployeeSelect);
    divEmployeeSelectColBuf1 = document.createElement('div');
    divEmployeeSelectColBuf1.className = 'col-2';
    divContentRowEmployeeSelect.appendChild(divEmployeeSelectColBuf1);
    divEmployeeSelectCol = document.createElement('div');
    divEmployeeSelectCol.className = 'col-8';
    divContentRowEmployeeSelect.appendChild(divEmployeeSelectCol);
    divEmployeeSelectColBuf2 = document.createElement('div');
    divEmployeeSelectColBuf2.className = 'col-2';
    divContentRowEmployeeSelect.appendChild(divEmployeeSelectColBuf2);
    
    divFormGroupDateBegin = document.createElement('div');
    divFormGroupDateBegin.className = 'input-group';
    divBeginDate.appendChild(divFormGroupDateBegin);


    labelForDateBegin = document.createElement('span');
    labelForDateBegin.innerText = 'Дата начала периода';
    labelForDateBegin.className = 'input-group-text';
    inputDateBegin = document.createElement('input');
    inputDateBegin.type = 'date';
    inputDateBegin.className = 'form-control';
    inputDateBegin.id = 'dateBeginInput';

    divFormGroupDateBegin.appendChild(labelForDateBegin);
    divFormGroupDateBegin.appendChild(inputDateBegin);
    
    divFormGroupDateEnd = document.createElement('div');
    divFormGroupDateEnd.className = 'input-group';
    divEndDate.appendChild(divFormGroupDateEnd);

    labelForDateEnd = document.createElement('span');
    labelForDateEnd.innerText = 'Дата окончания периода';
    labelForDateEnd.className = 'input-group-text';

    inputDateEnd = document.createElement('input');
    inputDateEnd.type = 'date';
    inputDateEnd.className = 'form-control';
    inputDateEnd.id = 'dateEndInput';

    divFormGroupDateEnd.appendChild(labelForDateEnd);
    divFormGroupDateEnd.appendChild(inputDateEnd);
    
    inputGroupEmployeeSelect = document.createElement('div');
    inputGroupEmployeeSelect.className = 'input-group';
    inputGroupEmployeeSelect.id = 'inputGroupEmployeeSelect';
    divEmployeeSelectCol.appendChild(inputGroupEmployeeSelect);
    
    labelForEmployeeMOL = document.createElement('span');
    labelForEmployeeMOL.innerText = 'МОЛ';
    labelForEmployeeMOL.className = 'input-group-text';
    
    inputEmployeeMOL = document.createElement('select');
    inputEmployeeMOL.className = 'form-select';
    inputEmployeeMOL.type = 'text';
    inputEmployeeMOL.placeholder = 'Согласовал';
    inputEmployeeMOL.id = 'inputEmployeeMOL';
    inputGroupEmployeeSelect.appendChild(labelForEmployeeMOL);
    inputGroupEmployeeSelect.appendChild(inputEmployeeMOL);
    
    
    rowContentBtns = document.createElement('div');
    rowContentBtns.className = 'row rowModalContent text-center';
    divModalContainerInnerActsParameters.appendChild(rowContentBtns);

    divBtnApply = document.createElement('div');
    divBtnApply.className = 'col mt-5';
    rowContentBtns.appendChild(divBtnApply);

    let btnApplyActsParameters = document.createElement('button');
    btnApplyActsParameters.className = 'btn btn-secondary';
    btnApplyActsParameters.type = 'button';
    btnApplyActsParameters.id = 'btnApplyActsParameters';
    btnApplyActsParameters.innerText = 'Скачать';
    divBtnApply.appendChild(btnApplyActsParameters);

    //Контент ...



    divModalFooterActsParameters = document.createElement('div');
    divModalFooterActsParameters.className = 'modal-footer';
    divModalContentActsParameters.appendChild(divModalFooterActsParameters);

    btnFooterCloseActsParameters = document.createElement('button');
    btnFooterCloseActsParameters.className = 'btn btn-secondary';
    btnFooterCloseActsParameters.type = 'button';
    btnFooterCloseActsParameters.id = 'btnCloseModalFooter';
    btnFooterCloseActsParameters.setAttribute('data-bs-dismiss', 'modal');
    btnFooterCloseActsParameters.innerText = 'Закрыть';
    divModalFooterActsParameters.appendChild(btnFooterCloseActsParameters);


    btnCloseModalActsParameters.addEventListener('click', function() {
       $('#dateBeginInput')[0].value = '';
        $('#dateEndInput')[0].value = '';
        $('#modalActsParameters').modal('hide');
        
    });

    btnFooterCloseActsParameters.addEventListener('click', function() {
        $('#dateBeginInput')[0].value = '';
        $('#dateEndInput')[0].value = '';
        $('#modalActsParameters').modal('hide');
    });

    btnApplyActsParameters.addEventListener('click', function () {


        dateBegin = $('#dateBeginInput')[0].value;
        dateEnd = $('#dateEndInput')[0].value;

        var dateEndParse = Date.parse(dateEnd);
        var dateEndParseB = new Date(dateEndParse);
        var dateEndFormat = dateEndParseB.toLocaleDateString('ru');
        
        var dateBeginParse = Date.parse(dateBegin);
        var dateBeginParseB = new Date(dateBeginParse);
        var dateBeginFormat = dateBeginParseB.toLocaleString('ru');
        
        var nowDate = new Date();
        var nowDateFormat = nowDate.toLocaleDateString('ru');

        if($('#inputEmployeeMOL')[0].value) {
        if (dateBeginFormat <= dateEndFormat && $('#dateBeginInput')[0].value && dateBeginFormat <= nowDateFormat) {
           
            
               var fileDownloadManager = new FileDownloadManager({autoOpen: true});
                var dto  =  {
                        location: input[0].idLocation,
                        startDate: dateBegin,
                        endDate: dateEnd,
                        mol: $('#inputEmployeeMOL')[0].value
                    };
                
                   
                    $.ajax({
                            url: '../../report/acts',
                            type: 'POST',
                            async: false,
                            data: dto,
                            success: function (data) {
                                fileDownloadManager.createFileBlock(data, 'Акт' + data + '.xlsx');
                }
            });
                
            
    
    
        } else {
            alert("Вы ввели неправильную дату");
        }
    } else {
        alert("Поле \"МОЛ\" не может быть пустым");
    }
    

    });
    
    

    modalFooterSubmitBtn.addEventListener('click', function () {
        let arr = new Array();
        let contentRows = $('.contentRow');
        for (i = 0; i < contentRows.length; i++) {
            if ($('.contentRow')[i].children[0].children[0].checked) {
                cartridge = $('.contentRow')[i].children[2].id.split('_')[1];
                arr.push(cartridge);
            }
        }

        $.ajax({
            url: '/editcartridgeslocation',
            type: 'POST',
            data: {idCartridge: arr, location: selectLocation.value, fromLocation: input[0].idLocation},
            success: function () {
                window.location.reload();
            },
            error: function (callback) {
            }
        });
    });

    let contractSort = document.querySelector('#contractSort');
    contractSort.addEventListener('click', function () {
      if(window.location.pathname.indexOf('inventories') > 0) {
          sortBy("contract");
      } else {
          sortBy("contr.contract_number");
      }
    });


    let modelSort = document.querySelector('#modelSortLink');
    modelSort.addEventListener('click', function(){
        
        
        if(window.location.pathname.indexOf('inventories') > 0) {
          sortBy("modelCartridgeManufacturer");
      } else {
          sortBy("model.model");
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
        
    let itemCodeSort = document.querySelector('#itemCodeSortLink');
    itemCodeSort.addEventListener('click', function () {
          if(window.location.pathname.indexOf('inventories') > 0) {
          sortBy("itemCode");
      } else {
          sortBy("item_code");
      }
        });
    
  let selectCategory = document.querySelector('#selectSearch');  
    
    selectCategory.addEventListener('change', function() {
            console.log(event.target.value);
            if(event.target.value === 'itemCode') {
                document.querySelector('#inputFilter').placeholder = 'поиск по номенклатурному коду';
            } else {
                document.querySelector('#inputFilter').placeholder = 'поиск по модели картриджа';
            }
    });
    
    //let searchApplyBtn = document.querySelector('#filterBtnApply');
    formContainerSearch.addEventListener('submit', function(e) {
        e.preventDefault();
        if(selectCategory.value === 'itemCode') {
        inputFilter.setAttribute('th:field', '*{itemCode}');
        inputFilter.name = 'itemCode';
    } else {
        inputFilter.setAttribute('th:field', '*{modelName}');
        inputFilter.name = 'modelName';
    }
    formContainerSearch.submit();
    });
    
 
           $('#inputEmployeeMOL').selectize({
            preload: true,
            placeholder: 'Согласовал',
            valueField: 'code',
            labelField: 'name',
            searchField: "name",

            load: function (query, callback) {
                $.ajax({
                    url: '/getinfooo',
                    type: 'GET',
                    error: callback,
                    success: callback
                });
            },
            

        });
    
    
});

function toggle(source) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i] != source) {
            checkboxes[i].checked = source.checked;
        }
    }
};

function getLinkForPage(inputHref, target) {
    getLink = '';
    pageInLinFlag = false;
    for (j = 0; j < inputHref.length; j++) {
         endStr = pageParam[j].indexOf('=');
          str = pageParam[j].substring(0, endStr);
        if ('page' === str) {
        
            pageParam[j] = "page=" + target;
            pageInLinFlag = true;
        }
        if('pageSize' === str) {
            pageParam[j] = "pageSize=" + pageBuf;
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

function getLinkForPageSize(inputHref, target) {
    getLink = '';
    pageInLinFlag = false;
    for (j = 0; j < inputHref.length; j++) {
          endStr = pageParam[j].indexOf('=');
          str = pageParam[j].substring(0, endStr);
        if ('pageSize' === str) {
          
            pageParam[j] = "pageSize=" + target;
            pageInLinFlag = true;
        }
        if (j == 0) {
            getLink += "?" + pageParam[j];
        } else {
            getLink += "&" + pageParam[j];
        }
    }
    if (pageInLinFlag == false) {
        getLink += "&pageSize=" + target;
    }
    return getLink;

}

function sortBy (link) {
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
    
    
    function actInstallReport(input) {
    var fileDownloadManager = new FileDownloadManager({autoOpen: true});
    if(input.length > 2) {
        $.get({
                url: '../../report/acts?location=' + input[0] + '&startDate=' + input[1] + "&endDate=" + input[2],
                success: function (data) {
                    console.log("success");
                    fileDownloadManager.createFileBlock(data, 'Акт' + data + '.xlsx');
    }
});
    } else {
          $.get('../../report/acts?location=' + input[0] + '&startDate=' + input[1], function (data) {
        fileDownloadManager.createFileBlock(data, 'Акт' + data + '.xlsx');
    });
    }
    };
  