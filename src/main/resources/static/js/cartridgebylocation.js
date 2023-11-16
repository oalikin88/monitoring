/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

const content = document.querySelector('#wrap');
const pagin = document.querySelector('#content');
const buttonsRow = document.querySelector("#buttonsRow");
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
$(document).ready(function () {



         

    let wrapper = document.createElement('div');
    wrapper.className = 'wrapper';
    content.appendChild(wrapper);

    let title = document.createElement('h3');
    title.className = "text-center titleLocation fw-bold";
    title.innerText = loc.name;
    wrapper.append(title);




    if (input.length > 0) {

        let btnAfterTitleRow = document.createElement('div');
        btnAfterTitleRow.className = 'row buttonsRow';
        wrapper.appendChild(btnAfterTitleRow);

        btnAfterTitleCol1 = document.createElement('div');
        btnAfterTitleCol1.className = 'col';

        btnAfterTitleCol2 = document.createElement('div');
        btnAfterTitleCol2.className = 'col';

        let choiceBtn = document.createElement('button');
        choiceBtn.className = 'btn btn-secondary';
        choiceBtn.type = 'button';
        choiceBtn.innerText = 'Выбрать';
        btnAfterTitleCol1.appendChild(choiceBtn);

        btnAfterTitleRow.appendChild(btnAfterTitleCol1);
        btnAfterTitleRow.appendChild(btnAfterTitleCol2);


        let formContainer = document.createElement('form');
        formContainer.setAttribute('th:action', '@{getcartridgesbymodel}');
        formContainer.setAttribute('action', 'getcartridgesbymodel');
        // formContainer.setAttribute('enctype',  'multipart/form-data');
        formContainer.setAttribute('th:object', '${dto}');
        formContainer.setAttribute('method', 'GET');
        formContainer.id = 'formFilter';




        btnAfterTitleCol2.appendChild(formContainer);

        inputgroupRow = document.createElement('div');
        inputgroupRow.className = 'row';

        inputgroupColInput = document.createElement('div');
        inputgroupColInput.className = 'col text-end';

        inputgroupColBtn = document.createElement('div');
        inputgroupColBtn.className = 'col';


        inputGroupFilterDiv = document.createElement('div');
        inputGroupFilterDiv.className = 'input-group';

        inputGroupFilterLabel = document.createElement('span');
        inputGroupFilterLabel.className = 'input-group-text';
        inputGroupFilterLabel.id = 'filterLabel';
        inputGroupFilterLabel.innerHTML = iconFilter;

        filterSubmit = document.createElement('button');
        filterSubmit.type = 'submit';
        filterSubmit.id = 'submitFiler';
        filterSubmit.className = 'btn btn-light';
        filterSubmit.innerText = 'Применить';




        inputGroupFilterInput = document.createElement('input');
        inputGroupFilterInput.className = 'form-control';
        inputGroupFilterInput.setAttribute('th:field', '*{contractNumber}');
        inputGroupFilterInput.name = 'contractNumber';
        inputGroupFilterInput.placeholder = 'фильтр по номеру контракта';


        inputGroupFilterHideFieldLocation = document.createElement('input');
        inputGroupFilterHideFieldLocation.className = 'form-control';
        inputGroupFilterHideFieldLocation.setAttribute('th:field', '*{location}');
        inputGroupFilterHideFieldLocation.name = 'location';
        inputGroupFilterHideFieldLocation.type = 'hidden';
        //   inputGroupFilterHideFieldLocation.id = 'location';
        inputGroupFilterHideFieldLocation.value = loc.id;


        inputGroupFilterHideFieldPrinterId = document.createElement('input');
        inputGroupFilterHideFieldPrinterId.className = 'form-control';
        inputGroupFilterHideFieldPrinterId.setAttribute('th:field', '*{idPrinter}');
        inputGroupFilterHideFieldPrinterId.name = 'idPrinter';
        // inputGroupFilterHideFieldPrinterId.id = 'idPrinter';
        inputGroupFilterHideFieldPrinterId.type = 'hidden';
        inputGroupFilterHideFieldPrinterId.value = arrRequest.idPrinter;


        formContainer.append(inputgroupRow);
        inputGroupFilterDiv.appendChild(inputGroupFilterHideFieldLocation);
        inputGroupFilterDiv.appendChild(inputGroupFilterHideFieldPrinterId);
        inputgroupRow.appendChild(inputgroupColInput);
        inputgroupRow.appendChild(inputgroupColBtn);
        inputgroupColInput.appendChild(inputGroupFilterDiv);
        inputGroupFilterDiv.appendChild(inputGroupFilterLabel);
        inputGroupFilterDiv.appendChild(inputGroupFilterInput);
        inputgroupColBtn.appendChild(filterSubmit);


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
        headTableModel.innerText = "Модель";
        headTable.appendChild(headTableModel);

        headTableContractNumber = document.createElement('div');
        headTableContractNumber.className = "col my-auto mx-auto text-start";
        headTableContractNumber.innerText = "Контракт";
        headTable.appendChild(headTableContractNumber);



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
            link.innerText = input[i].model;
            contentRowPrinterName.appendChild(link);


            contentRowContractNumber = document.createElement('div');
            contentRowContractNumber.className = 'col';
            startContractDate = new Date(input[i].startContract);
            startContractDateFormat = startContractDate.toLocaleDateString('ru');
            contentRowContractNumber.innerText = input[i].contractNumber + " от " + startContractDateFormat;
            contentRow.appendChild(contentRowContractNumber);
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
    
       let paginationNav = document.createElement('nav');
            let paginationUl = document.createElement('ul');
            paginationUl.className = 'pagination';
            content.appendChild(paginationNav);
            paginationNav.appendChild(paginationUl);
           

            if (window.location.search.indexOf('page') != -1) {
                paginationPreviousLi = document.createElement('li');
                paginationPreviousLi.className = 'page-item';
                previousPageLink = document.createElement('a');
                previousPageLink.href = "#"
                spanPreviuosAria = document.createElement('span');
                spanPreviuosAria.setAttribute('aria-hidden', true);

                paginationPreviousLi.appendChild(previousPageLink);
                previousPageLink.appendChild(spanPreviuosAria);
                paginationUl.appendChild(paginationPreviousLi);
            }
            for(i = 0; i < pages; i++) {
                paginationElLi = document.createElement('li');
                paginationElLi.className = 'page-item';
                paginationElLink = document.createElement('a');
                paginationElLink.className = 'page-link';
                paginationElLink.href = window.location.pathname + window.location.search.split('&')[0] + "&" + window.location.search.split('&')[1] + "&" + window.location.search.split('&')[2] + '&page=' + i;
                paginationElLink.innerText = i + 1;
                paginationElLi.appendChild(paginationElLink);
                paginationUl.appendChild(paginationElLi);
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
            data: {idCartridge: arr, location: selectLocation.value},
            success: function () {
                window.location.reload();
            },
            error: function (callback) {
            }
        });



    });

});

function toggle(source) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i] != source) {
            checkboxes[i].checked = source.checked;
        }
    }
}
;