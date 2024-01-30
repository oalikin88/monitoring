let parent = document.querySelector('#tabContent');
let models = new Map(Object.entries(modelsInput));
let locations = new Map(Object.entries(locationsInput));





let searchModel = false;
window.onload = function () {
    setInterval('AJAXPing()', 28000);


    let tableContainer = document.getElementById('tableContainer');

    if (input.length > 0) {
        // Вывод таблицы
        let table = document.createElement('table');
        table.id = "table";
        table.className = "table align-middle table-hover table-scroll table-striped table-bordered";
        let thead = document.createElement('thead');
        thead.className = "text-center fixedThead";
        let trThead = document.createElement('tr');
        let thThead1 = document.createElement('th');
        thThead1.setAttribute('scope', 'col');
        thThead1.setAttribute('rowspan', '2');
        thThead1.innerText = '#';
        let thThead2 = document.createElement('th');
        thThead2.setAttribute('scope', 'col');
        thThead2.setAttribute('rowspan', '2');
        thThead2.className = 'background';
        // thThead2.innerText = 'Модель принтера \ Локация';

        let thThead2Div = document.createElement('div');
        let thThead2SpanFirst = document.createElement('span');
        thThead2SpanFirst.className = 'bottom';
        thThead2SpanFirst.innerText = 'Локация';
        let thThead2SpanSecond = document.createElement('span');
        thThead2SpanSecond.className = 'top';
        thThead2SpanSecond.innerText = 'Модель \n принтера';
        let thThead2Line = document.createElement('div');
        thThead2Line.className = 'line';


        tableContainer.appendChild(table);
        table.appendChild(thead);
        thead.appendChild(trThead);
        trThead.appendChild(thThead1);
        trThead.appendChild(thThead2);
        thThead2.appendChild(thThead2Div);
        thThead2Div.appendChild(thThead2SpanFirst);
        thThead2Div.appendChild(thThead2SpanSecond);
        thThead2Div.appendChild(thThead2Line);
        let tbody = document.createElement('tbody');
        let tr = document.createElement('tr');
        table.appendChild(tbody);
        tbody.appendChild(tr);

        // Вывод шапки таблицы




        for (let i = 0; i < 1; i++) {

            for (let entry of models) {
                let th = document.createElement('th');
                th.setAttribute('scope', 'col');
                th.className = 'tHeader';
                th.setAttribute('colspan', '2');
                th.id = "idModel_" + entry[0];
                th.innerText = entry[1];
                trThead.appendChild(th);

            }

            for (let k = 0; k < 1; k++) {
                let subtrThead = document.createElement('tr');
                thead.appendChild(subtrThead);

                for (let j = 0; j < models.size; j++) {
                    thTheadCount = document.createElement('th');
                    thTheadCount.className = 'tHeader';
                    thTheadCount.innerText = "Количество \n принтеров";
                    thTheadCount2 = document.createElement('th');
                    thTheadCount2.className = 'tHeader';
                    thTheadCount2.innerText = "Количество \n картриджей";
                    subtrThead.appendChild(thTheadCount);
                    subtrThead.appendChild(thTheadCount2);
                }
            }
        }



        // Вывод тела таблицы

        let c = 0;
        for (let locat of locations) {
            tr = document.createElement('tr');
            tbody.appendChild(tr);
            tdCount = document.createElement('td');
            tdCount.innerText = c += 1;
            tr.appendChild(tdCount);
            tdLocation = document.createElement('td');
            tdLocation.setAttribute('id', locat[0]);
            tdLocation.className = 'location text-wrap';
            let locLink = document.createElement('a');
            if(window.location.href.indexOf("PRINTER") >= 0) {
               locLink.href = "/inventories/" + locat[0] + "?deviceType=PRINTER";
            } else if(window.location.href.indexOf("MFU") >= 0) {
                locLink.href = "/inventories/" + locat[0] + "?deviceType=MFU";
            } else {
                locLink.href = "/inventories/" + locat[0] + "?deviceType=ALL";
            }
        //    locLink.href = "/inventories/" + locat[0];
            locLink.innerText = locat[1];
            tdLocation.appendChild(locLink);
            tr.appendChild(tdLocation);


            for (let modIterate of models) {
                searchModel = false;
                var amountCartridge = new Set();
                var amountPrinters = new Set();

                    for(b = 0; b < input.length; b++) {
                         
                         if (input[b].modelId == modIterate[0] && locat[0] == input[b].locationId) {
                             
                             tdPrintSuccess = document.createElement('td');
                            tdPrintSuccess.setAttribute('id', 'location_' + locat[0] + '_' + 'model_' + modIterate[0]);
                            tdPrintSuccess.setAttribute('class', 'model');
                            tdPrintSuccess.style.wordBreak = "break-all";
                            linkPrinter = document.createElement('a');
                           
                            linkPrinter.setAttribute('href', '/printersbylocation?location=' + locat[0]
                                    + '&idModel=' + input[b].modelId);
                            linkPrinter.innerText = input[b].countPrinter;
                            tr.appendChild(tdPrintSuccess);
                            tdPrintSuccess.appendChild(linkPrinter);

                            tdCart = document.createElement('td');
                            tdCart.setAttribute('class', 'cart');
                            tdCart.style.wordBreak = "break-all";
                            link = document.createElement('a');
                            link.setAttribute('href', '/getcartridgesbymodel?idModel=' + input[b].modelId
                                    + '&location=' + locat[0]);
                            link.innerText = input[b].countCartridge;
                            tr.appendChild(tdCart);
                            tdCart.appendChild(link);
                             
                         } 
                        
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

    if (models.size == 0) {
        h3AttentionAddModelPrinter = document.createElement('h3');
        h3AttentionAddModelPrinter.className = 'fw-bold text-center';
        h3AttentionAddModelPrinter.innerText = "Добавьте модель принтера";
        h3AttentionAddModelPrinter.id = 'attentionModelPrinter';
        parent.appendChild(h3AttentionAddModelPrinter);
    }





    // Модальное окно с редактированием локаций
    modalLocationsWrapper = document.createElement('div');
    modalLocationsWrapper.className = 'modal fade';
    modalLocationsWrapper.id = 'modalLocations';
    modalLocationsWrapper.setAttribute('data-bs-backdrop', 'static');
    modalLocationsWrapper.setAttribute('data-bs-keyboard', 'false');
    modalLocationsWrapper.setAttribute('tabindex', '-1');
    parent.appendChild(modalLocationsWrapper);

    modalDialogCenteredDiv = document.createElement('div');
    modalDialogCenteredDiv.className = 'modal-dialog modal-dialog-centered';
    modalLocationsWrapper.appendChild(modalDialogCenteredDiv);

    modalContentDiv = document.createElement('div');
    modalContentDiv.className = 'modal-content';
    modalDialogCenteredDiv.appendChild(modalContentDiv);

    modalHeaderDiv = document.createElement('div');
    modalHeaderDiv.className = 'modal-header';
    modalContentDiv.appendChild(modalHeaderDiv);

    modalTitleH1 = document.createElement('h1');
    modalTitleH1.className = 'modal-title fs-5';
    modalTitleH1.id = 'modalLocationsLabel';
    modalTitleH1.innerText = 'Список локаций';
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

    modalButtonsRow = document.createElement('div');
    modalButtonsRow.className = 'row';
    modalBodyDiv.appendChild(modalButtonsRow);

    modalBtnAddLocation = document.createElement('button');
    modalBtnAddLocation.className = 'btn btn-secondary';
    modalBtnAddLocation.type = 'button';
    modalBtnAddLocation.setAttribute('data-bs-target', '#modalLocations2');
    modalBtnAddLocation.setAttribute('data-bs-toggle', 'modal');
    modalBtnAddLocation.innerText = 'Добавить локацию';
    modalButtonsRow.appendChild(modalBtnAddLocation);

    modalFooterDiv = document.createElement('div');
    modalFooterDiv.className = 'modal-footer';
    modalContentDiv.appendChild(modalFooterDiv);

    modalFooterCloseBtn = document.createElement('button');
    modalFooterCloseBtn.className = 'btn btn-secondary';
    modalFooterCloseBtn.type = 'button';
    modalFooterCloseBtn.setAttribute('data-bs-dismiss', 'modal');
    modalFooterCloseBtn.innerText = 'Закрыть';
    modalFooterCloseBtn.id = "modalWindow1BtnClose";
    modalFooterDiv.appendChild(modalFooterCloseBtn);

    modalLocationWrapper2 = document.createElement('div');
    modalLocationWrapper2.className = 'modal fade';
    modalLocationWrapper2.id = 'modalLocations2';
    modalLocationWrapper2.setAttribute('aria-hidden', 'true');
    modalLocationWrapper2.setAttribute('tabindex', '-1');
    parent.appendChild(modalLocationWrapper2);

    modalDialogCenteredDiv2 = document.createElement('div');
    modalDialogCenteredDiv2.className = 'modal-dialog modal-dialog-centered';
    modalLocationWrapper2.appendChild(modalDialogCenteredDiv2);

    modalContentDiv2 = document.createElement('div');
    modalContentDiv2.className = 'modal-content';
    modalDialogCenteredDiv2.appendChild(modalContentDiv2);

    modalHeaderDiv2 = document.createElement('div');
    modalHeaderDiv2.className = 'modal-header';
    modalContentDiv2.appendChild(modalHeaderDiv2);

    modalTitle2H1 = document.createElement('h1');
    modalTitle2H1.className = 'modal-title fs-5';
    modalTitle2H1.id = 'modalLocationsLabel';
    modalTitle2H1.innerText = 'Добавить локацию';
    modalHeaderDiv2.appendChild(modalTitle2H1);

    modalBtnClose2 = document.createElement('button');
    modalBtnClose2.className = 'btn-close';
    modalBtnClose2.type = 'button';
    modalBtnClose2.setAttribute('data-bs-dismiss', 'modal');
    modalBtnClose2.setAttribute('aria-label', 'close');
    modalHeaderDiv2.appendChild(modalBtnClose2);

    modalBodyDiv2 = document.createElement('div');
    modalBodyDiv2.className = 'modal-body';
    modalContentDiv2.appendChild(modalBodyDiv2);

    modalInputLocation = document.createElement('input');
    modalInputLocation.className = 'form-control';
    modalInputLocation.type = 'text';
    modalInputLocation.id = 'modalLocationInput';
    modalInputLocation.placeholder = 'название локации';
    modalBodyDiv2.appendChild(modalInputLocation);

    modalFooterDiv2 = document.createElement('div');
    modalFooterDiv2.className = 'modal-footer';
    modalContentDiv2.appendChild(modalFooterDiv2);

    modalFooterCloseBtn2 = document.createElement('button');
    modalFooterCloseBtn2.className = 'btn btn-secondary';
    modalFooterCloseBtn2.type = 'button';
    modalFooterCloseBtn2.setAttribute('data-bs-dismiss', 'modal');
    modalFooterCloseBtn2.innerText = 'Закрыть';
    modalFooterDiv2.appendChild(modalFooterCloseBtn2);

    modalFooterOKBtn2 = document.createElement('button');
    modalFooterOKBtn2.className = 'btn btn-primary';
    modalFooterOKBtn2.type = 'button';
    modalFooterOKBtn2.setAttribute('data-bs-toggle', 'modal');
    modalFooterOKBtn2.setAttribute('data-bs-target', '#modalLocations');
    modalFooterOKBtn2.innerText = 'Добавить';
    modalFooterDiv2.appendChild(modalFooterOKBtn2);



    modalBtnClose.addEventListener('click', function() {
        $('#modalLocations').empty();
        window.location.reload();
    });
    modalFooterCloseBtn.addEventListener('click', function () {
        $('#modalLocations').empty();
        window.location.reload();

    });

    modalFooterOKBtn2.addEventListener('click', function () {

        $.ajax({
            type: "POST",
            url: "/locations",
            data: {nameLocation: $('#modalLocationInput')[0].value},
            dataType: "text",
            success: function (data) {
                $('#staticBackdrop').modal('hide');
                $.ajax({
                    url: '/locations',
                    type: 'GET',
                    dataType: 'json',
                    success: function (result) {
                        child = document.getElementById('tableModal');
                        parent = document.getElementById('modalContentInner');
                        parent.removeChild(child);
                        result.sort();

                        table = document.createElement('table');
                        table.id = "tableModal";
                        table.className = "table table-striped table-bordered";
                        modalBodyContentInner.appendChild(table);

                        thead = document.createElement('thead');
                        table.appendChild(thead);
                        trThead = document.createElement('tr');
                        thThead1 = document.createElement('th');

                        thThead1.setAttribute('scope', 'col');
                        thThead1.innerText = '#';
                        thThead2 = document.createElement('th');
                        thThead2.setAttribute('scope', 'col');
                        thThead2.innerText = 'Локация';
                        thead.appendChild(trThead);
                        trThead.appendChild(thThead1);
                        trThead.appendChild(thThead2);
                        tbody = document.createElement('tbody');
                        table.appendChild(tbody);


                        for (i = 0; i < result.sort().length; i++) {
                            tr = document.createElement('tr');
                            tbody.appendChild(tr);
                            tdCount = document.createElement('td');
                            tdCount.innerText = i + 1;
                            tr.appendChild(tdCount);

                            tdLocation = document.createElement('td');
                            tdLocation.setAttribute('id', result.sort()[i].id);
                            tdLocation.setAttribute('class', 'location');
                            tdLocation.innerText = result.sort()[i].name;
                            tr.appendChild(tdLocation);

                        }
                    }


                });

            },
            error: function (callback) {
                if ($('#exceptionContainer').length == 0) {
                    $('#modalBody').append(callback.responseText);
                } else {

                    $('#exceptionContainer').replaceWith(callback.responseText);

                }

                new bootstrap.Modal(document.getElementById('modalError')).show();
                // $('#resultInfo').append(callback.responseText);
            }

        });
        document.getElementById("modalLocationInput").value = "";
    });

    // Модальное окно с моделями принтеров
    modalModelsPrinterWrapper = document.createElement('div');
    modalModelsPrinterWrapper.className = 'modal fade';
    modalModelsPrinterWrapper.id = 'modalModelsPrinter';
    modalModelsPrinterWrapper.setAttribute('data-bs-backdrop', 'static');
    modalModelsPrinterWrapper.setAttribute('data-bs-keyboard', 'false');
    modalModelsPrinterWrapper.setAttribute('tabindex', '-1');
    parent.appendChild(modalModelsPrinterWrapper);

    modalDialogCenteredModelsPrinterDiv = document.createElement('div');
    modalDialogCenteredModelsPrinterDiv.className = 'modal-dialog modal-dialog-centered';
    modalModelsPrinterWrapper.appendChild(modalDialogCenteredModelsPrinterDiv);

    modalContentModelsPrinterDiv = document.createElement('div');
    modalContentModelsPrinterDiv.className = 'modal-content';
    modalDialogCenteredModelsPrinterDiv.appendChild(modalContentModelsPrinterDiv);

    modalHeaderModelsPrinterDiv = document.createElement('div');
    modalHeaderModelsPrinterDiv.className = 'modal-header';
    modalContentModelsPrinterDiv.appendChild(modalHeaderModelsPrinterDiv);

    modalTitleModelsPrinterH1 = document.createElement('h1');
    modalTitleModelsPrinterH1.className = 'modal-title fs-5';
    modalTitleModelsPrinterH1.id = 'modalModelsPrinterLabel';
    modalTitleModelsPrinterH1.innerText = 'Список моделей';
    modalHeaderModelsPrinterDiv.appendChild(modalTitleModelsPrinterH1);

    modalModelsPrinterBtnClose = document.createElement('button');
    modalModelsPrinterBtnClose.className = 'btn-close';
    modalModelsPrinterBtnClose.type = 'button';
    modalModelsPrinterBtnClose.setAttribute('data-bs-dismiss', 'modal');
    modalModelsPrinterBtnClose.setAttribute('aria-label', 'close');
    modalHeaderModelsPrinterDiv.appendChild(modalModelsPrinterBtnClose);

    modalModelsPrinterBodyDiv = document.createElement('div');
    modalModelsPrinterBodyDiv.className = 'modal-body ml-3 mr-3';
    modalContentModelsPrinterDiv.appendChild(modalModelsPrinterBodyDiv);

    modalModelsPrinterBodyContentInner = document.createElement('div');
    modalModelsPrinterBodyContentInner.className = 'modalContentInner';
    modalModelsPrinterBodyContentInner.id = 'modalModelsPrinterContentInner';
    modalModelsPrinterBodyDiv.appendChild(modalModelsPrinterBodyContentInner);

    //Content

    modalModelsPrinterButtonsRow = document.createElement('div');
    modalModelsPrinterButtonsRow.className = 'row';
    modalModelsPrinterBodyDiv.appendChild(modalModelsPrinterButtonsRow);

    modalModelsPrinterBtnAdd = document.createElement('button');
    modalModelsPrinterBtnAdd.className = 'btn btn-secondary ';
    modalModelsPrinterBtnAdd.type = 'button';
    modalModelsPrinterBtnAdd.setAttribute('data-bs-target', '#modalModelsPrinter2');
    modalModelsPrinterBtnAdd.setAttribute('data-bs-toggle', 'modal');
    modalModelsPrinterBtnAdd.id = 'addModelPrinterModal';
    modalModelsPrinterBtnAdd.innerText = 'Добавить модель';
    modalModelsPrinterButtonsRow.appendChild(modalModelsPrinterBtnAdd);

    modalModelsPrinterFooterDiv = document.createElement('div');
    modalModelsPrinterFooterDiv.className = 'modal-footer';
    modalContentModelsPrinterDiv.appendChild(modalModelsPrinterFooterDiv);

    modalModelsPrinterFooterCloseBtn = document.createElement('button');
    modalModelsPrinterFooterCloseBtn.className = 'btn btn-secondary';
    modalModelsPrinterFooterCloseBtn.type = 'button';
    modalModelsPrinterFooterCloseBtn.setAttribute('data-bs-dismiss', 'modal');
    modalModelsPrinterFooterCloseBtn.innerText = 'Закрыть';
    modalModelsPrinterFooterCloseBtn.id = "modalMOdelsPrinterWindow1BtnClose";
    modalModelsPrinterFooterDiv.appendChild(modalModelsPrinterFooterCloseBtn);



    // Модальное окно с добавлением моделей принтеров

    modalModelsPrinterWrapper2 = document.createElement('div');
    modalModelsPrinterWrapper2.className = 'modal fade';
    modalModelsPrinterWrapper2.id = 'modalModelsPrinter2';
    modalModelsPrinterWrapper2.setAttribute('aria-hidden', 'true');
    modalModelsPrinterWrapper2.setAttribute('tabindex', '-1');
    parent.appendChild(modalModelsPrinterWrapper2);

    modalModelsPrinterDialogCenteredDiv2 = document.createElement('div');
    modalModelsPrinterDialogCenteredDiv2.className = 'modal-dialog modal-dialog-centered modal-xl';
    modalModelsPrinterWrapper2.appendChild(modalModelsPrinterDialogCenteredDiv2);

    modalModelsPrinterContentDiv2 = document.createElement('div');
    modalModelsPrinterContentDiv2.className = 'modal-content';
    modalModelsPrinterDialogCenteredDiv2.appendChild(modalModelsPrinterContentDiv2);

    modalModelsPrinterHeaderDiv2 = document.createElement('div');
    modalModelsPrinterHeaderDiv2.className = 'modal-header';
    modalModelsPrinterContentDiv2.appendChild(modalModelsPrinterHeaderDiv2);

    modalModelsPrinterTitle2H1 = document.createElement('h1');
    modalModelsPrinterTitle2H1.className = 'modal-title fs-5';
    modalModelsPrinterTitle2H1.id = 'modalModelsPrinterLabel2';
    modalModelsPrinterTitle2H1.innerText = 'Добавить модель принтера';
    modalModelsPrinterHeaderDiv2.appendChild(modalModelsPrinterTitle2H1);

    modalModelsPrinterBtnClose2 = document.createElement('button');
    modalModelsPrinterBtnClose2.className = 'btn-close';
    modalModelsPrinterBtnClose2.type = 'button';
    modalModelsPrinterBtnClose2.setAttribute('data-bs-dismiss', 'modal');
    modalModelsPrinterBtnClose2.setAttribute('aria-label', 'close');
    modalModelsPrinterHeaderDiv2.appendChild(modalModelsPrinterBtnClose2);

    modalModelsPrinterBodyDiv2 = document.createElement('div');
    modalModelsPrinterBodyDiv2.className = 'modal-body ml-3 mr-3';
    modalModelsPrinterContentDiv2.appendChild(modalModelsPrinterBodyDiv2);


    modalModelsPrinterContentInnerRow1 = document.createElement('div');
    modalModelsPrinterContentInnerRow1.className = 'row modalManufacturerModelRow mt-3';
    modalModelsPrinterBodyDiv2.appendChild(modalModelsPrinterContentInnerRow1);

    modalModalsPrinterContentInnerManufacturerDiv1 = document.createElement('div');
    modalModalsPrinterContentInnerManufacturerDiv1.className = 'col';
    modalModalsPrinterContentInnerManufacturerDiv1.id = 'modalManufacturerDiv';
    modalModelsPrinterContentInnerRow1.appendChild(modalModalsPrinterContentInnerManufacturerDiv1);

    modalModalsPrinterContentInnerManufacturerDiv2 = document.createElement('div');
    modalModalsPrinterContentInnerManufacturerDiv2.className = 'col modalModelDiv';
    modalModalsPrinterContentInnerManufacturerDiv2.id = 'modalModelDiv';
    modalModelsPrinterContentInnerRow1.appendChild(modalModalsPrinterContentInnerManufacturerDiv2);
    
    modalModalsPrinterContentInnerManufacturerDiv3 = document.createElement('div');
    modalModalsPrinterContentInnerManufacturerDiv3.className = 'col modalDeviceTypeDiv';
    modalModalsPrinterContentInnerManufacturerDiv3.id = 'modalDeviceTypeDiv';
    modalModelsPrinterContentInnerRow1.appendChild(modalModalsPrinterContentInnerManufacturerDiv3);

    modalModelsPrinterContentInnerManufacturerSelect = document.createElement('select');
    modalModelsPrinterContentInnerManufacturerSelect.className = 'form-select modalManufacturerSelect';
    modalModelsPrinterContentInnerManufacturerSelect.id = 'modalManufacturerSelect';
    modalModelsPrinterContentInnerManufacturerSelect.value = 'Производитель';
    modalModalsPrinterContentInnerManufacturerDiv1.appendChild(modalModelsPrinterContentInnerManufacturerSelect);

    modalModelsPrinterContentInnerModelSelect = document.createElement('select');
    modalModelsPrinterContentInnerModelSelect.className = 'form-select modalModelInput';
    modalModelsPrinterContentInnerModelSelect.type = 'text';
    modalModelsPrinterContentInnerModelSelect.id = 'modalModelInput';
    // modalModelsPrinterContentInnerModelInput.placeholder = 'Модель';
    modalModalsPrinterContentInnerManufacturerDiv2.appendChild(modalModelsPrinterContentInnerModelSelect);
    
    modalModelsPrinterContentInnerDeviceTypeSelect = document.createElement('select');
    modalModelsPrinterContentInnerDeviceTypeSelect.className = 'form-select modalDeviceTypeInput';
    modalModelsPrinterContentInnerDeviceTypeSelect.type = 'text';
    modalModelsPrinterContentInnerDeviceTypeSelect.id = 'modalDeviceTypeInput';
    modalModelsPrinterContentInnerDeviceTypeSelect.placeholder = 'Тип оборудования';
    modalModelsPrinterContentInnerDeviceTypeSelect.style.color = 'gray';
    modalModalsPrinterContentInnerManufacturerDiv3.appendChild(modalModelsPrinterContentInnerDeviceTypeSelect);
    
    optionPrinter = document.createElement('option');
    optionPrinter.value = '';
    optionPrinter.innerHTML = 'Тип оборудования';
    optionPrinter.selected = true;
    modalModelsPrinterContentInnerDeviceTypeSelect.appendChild(optionPrinter);
    
    optionPrinter = document.createElement('option');
    optionPrinter.value = 'PRINTER';
    optionPrinter.innerHTML = 'Принтер';
    modalModelsPrinterContentInnerDeviceTypeSelect.appendChild(optionPrinter);
    
    optionPrinter = document.createElement('option');
    optionPrinter.value = 'MFU';
    optionPrinter.innerHTML = 'МФУ';
    modalModelsPrinterContentInnerDeviceTypeSelect.appendChild(optionPrinter);

    modalModelsPrinterContentInnerRow2 = document.createElement('div');
    modalModelsPrinterContentInnerRow2.className = 'row mt-3';
    modalModelsPrinterBodyDiv2.appendChild(modalModelsPrinterContentInnerRow2);

    modalModalsPrinterContentInnerPrintColorTypeDiv = document.createElement('div');
    modalModalsPrinterContentInnerPrintColorTypeDiv.className = 'col modalPrintColorTypeDiv';
    modalModalsPrinterContentInnerPrintColorTypeDiv.id = 'modalPrintColorTypeDiv';
    modalModelsPrinterContentInnerRow2.appendChild(modalModalsPrinterContentInnerPrintColorTypeDiv);

    modalModalsPrinterContentInnerPrintColorTypeSelect = document.createElement('select');
    modalModalsPrinterContentInnerPrintColorTypeSelect.className = 'form-select modalPrintColorTypeSelect';
    modalModalsPrinterContentInnerPrintColorTypeSelect.id = 'modalPrintColorTypeSelect';
    modalModalsPrinterContentInnerPrintColorTypeSelect.value = 'Цветность печати';
    modalModalsPrinterContentInnerPrintColorTypeDiv.appendChild(modalModalsPrinterContentInnerPrintColorTypeSelect);



    modalModalsPrinterContentInnerPrintFormatTypeDiv = document.createElement('div');
    modalModalsPrinterContentInnerPrintFormatTypeDiv.className = 'col';
    modalModalsPrinterContentInnerPrintFormatTypeDiv.id = 'modalPrintFormatTypeDiv';
    modalModelsPrinterContentInnerRow2.appendChild(modalModalsPrinterContentInnerPrintFormatTypeDiv);

    modalModalsPrinterContentInnerPrintFormatTypeSelect = document.createElement('select');
    modalModalsPrinterContentInnerPrintFormatTypeSelect.className = 'form-select modalPrintFormatTypeSelect';
    modalModalsPrinterContentInnerPrintFormatTypeSelect.id = 'modalPrintFormatTypeSelect';
    modalModalsPrinterContentInnerPrintFormatTypeSelect.value = 'Формат печати';
    modalModalsPrinterContentInnerPrintFormatTypeDiv.appendChild(modalModalsPrinterContentInnerPrintFormatTypeSelect);

    modalModalsPrinterContentInnerPrintSpeedDiv = document.createElement('div');
    modalModalsPrinterContentInnerPrintSpeedDiv.className = 'col';
    modalModalsPrinterContentInnerPrintSpeedDiv.id = 'modalPrintSpeedDiv';
    modalModelsPrinterContentInnerRow2.appendChild(modalModalsPrinterContentInnerPrintSpeedDiv);

    modalModalsPrinterContentInnerPrintSpeedInput = document.createElement('input');
    modalModalsPrinterContentInnerPrintSpeedInput.className = 'form-control';
    modalModalsPrinterContentInnerPrintSpeedInput.type = 'text';
    modalModalsPrinterContentInnerPrintSpeedInput.id = 'modalPrintSpeedSelect';
    modalModalsPrinterContentInnerPrintSpeedInput.placeholder = 'Скорость печати';
    modalModalsPrinterContentInnerPrintSpeedDiv.appendChild(modalModalsPrinterContentInnerPrintSpeedInput);

    modalModelsPrinterFooterDiv2 = document.createElement('div');
    modalModelsPrinterFooterDiv2.className = 'modal-footer';
    modalModelsPrinterContentDiv2.appendChild(modalModelsPrinterFooterDiv2);

    modalModelsPrinterFooterCloseBtn2 = document.createElement('button');
    modalModelsPrinterFooterCloseBtn2.className = 'btn btn-secondary';
    modalModelsPrinterFooterCloseBtn2.type = 'button';
    modalModelsPrinterFooterCloseBtn2.setAttribute('data-bs-toggle', 'modal');
    modalModelsPrinterFooterCloseBtn2.setAttribute('data-bs-target', '#modalModelsPrinter');
    modalModelsPrinterFooterCloseBtn2.innerText = 'Закрыть';
    modalModelsPrinterFooterDiv2.appendChild(modalModelsPrinterFooterCloseBtn2);

    modalModelsPrinterFooterOKBtn2 = document.createElement('button');
    modalModelsPrinterFooterOKBtn2.className = 'btn btn-primary';
    modalModelsPrinterFooterOKBtn2.type = 'button';
    modalModelsPrinterFooterOKBtn2.setAttribute('data-bs-toggle', 'modal');
    modalModelsPrinterFooterOKBtn2.setAttribute('data-bs-target', '#modalModelsPrinter');
    modalModelsPrinterFooterOKBtn2.innerText = 'Добавить';
    modalModelsPrinterFooterDiv2.appendChild(modalModelsPrinterFooterOKBtn2);

    deviceTypeSelect = document.querySelector('#modalDeviceTypeInput');
    deviceTypeSelect.addEventListener('change', function() {
        if(deviceTypeSelect.value !== '') {
            deviceTypeSelect.style.color = 'black';
        } else {
            deviceTypeSelect.style.color = 'gray';
        }
    });
    
    modalModelsPrinterBtnAdd.addEventListener('click', function () {
        let manufacturerChoice;
        $('#modalManufacturerSelect').selectize({
            create: true,
            preload: 'focus',
            placeholder: "Производитель",
            valueField: 'manufacturer',
            labelField: 'manufacturer',
            searchField: "manufacturer",
            showAddOptionOnCreate: true,
            load: function (query, callback) {
                $.ajax({
                    url: '/manufacturers',
                    type: 'GET',
                    dataType: 'json',
                    data: {manufacturer: query},
                    error: callback,
                    success: callback
                });
            },
            onChange: function (value) {
                if (value !== '') {
                    //    $(this.$control_input[0].closest('.printer')).find('.printerSwitch')[0].children[0].disabled = false;
                    manufacturerValueFromSelectize = value;
                    selectizeModelFromChoisesManufacturer = $(this.$control_input[0].closest('.modalManufacturerModelRow')).find('.modalModelInput')[0];
                    $.ajax({
                        url: "/models/" + encodeURIComponent(value),
                        type: 'GET',
                        dataType: 'json',
                        data: {manufacturer: manufacturerChoice},
                        success: function (res) {
                            let keys = Object.keys(selectizeModelFromChoisesManufacturer.selectize.options);
                            for (let i = 0; i < keys.length; i++) {
                                selectizeModelFromChoisesManufacturer.selectize.removeOption(keys[i]);
                            }
                            res.forEach(model => {
                                selectizeModelFromChoisesManufacturer.selectize.addOption(model);
                                selectizeModelFromChoisesManufacturer.selectize.addItem(model);
                            });

                            selectizeModelFromChoisesManufacturer.selectize.refreshOptions();
                            selectizeModelFromChoisesManufacturer.selectize.clear();
                            selectizeModelFromChoisesManufacturer.selectize.enable();
                        }
                    });
                }
            }
        });


        $('#modalModelInput').selectize({
            create: true,
            preload: 'focus',
            valueField: 'model',
            labelField: 'model',
            searchField: "model",
            showAddOptionOnCreate: true,
            placeholder: 'Модель',
            load: function (query, callback) {
                manufacturerChoice = $(this.$control_input[0].closest('.modalManufacturerModelRow')).find('.modalManufacturerSelect')[0].innerText;
                $.ajax({
                    url: '/models/' + manufacturerChoice,
                    type: 'GET',
                    dataType: 'json',
                    error: callback,
                    success: callback
                });
            },

        });


        $('.modalPrintColorTypeSelect').selectize({
            placeholder: "Цветность печати",
            valueField: 'colorType',
            labelField: 'colorType',
            searchField: "colorType",
            options: [
                {colorType: "чёрно-белый"},
                {colorType: "цветной"}
            ]
        });


        $('.modalPrintFormatTypeSelect').selectize({
            placeholder: "Формат печати",
            valueField: 'formatType',
            labelField: 'formatType',
            searchField: "formatType",
            options: [
                {formatType: "A4"},
                {formatType: "A3"}
            ]
        });


        modalModelsPrinterFooterOKBtn2.addEventListener("click", function () {

            $.ajax({
                url: '/models',
                type: 'POST',
                data: {model: $('#modalModelInput')[0].innerText,
                    manufacturer: $('#modalManufacturerSelect')[0].innerText,
                    printColorType: $('#modalPrintColorTypeSelect')[0].innerText,
                    printFormatType: $('#modalPrintFormatTypeSelect')[0].innerText,
                    printSpeed: $('#modalPrintSpeedSelect')[0].value,
                    deviceType: $('#modalDeviceTypeInput')[0].value},
                success: function (result) {
                    parent = document.getElementById('modalModelsPrinterContentInner');
                    child = document.getElementById('modalTableModelsPrinter');
                    parent.removeChild(child);

                    tableModelsPrinter = document.createElement('table');
                    tableModelsPrinter.id = "modalTableModelsPrinter";
                    tableModelsPrinter.className = "table table-striped table-bordered";
                    modalModelsPrinterBodyContentInner.appendChild(tableModelsPrinter);

                    theadModelsPrinter = document.createElement('thead');
                    tableModelsPrinter.appendChild(theadModelsPrinter);
                    trTheadModelsPrinter = document.createElement('tr');
                    thTheadModelsPrinter1 = document.createElement('th');

                    thTheadModelsPrinter1.setAttribute('scope', 'col');
                    thTheadModelsPrinter1.innerText = '#';
                    thTheadModelsPrinter2 = document.createElement('th');
                    thTheadModelsPrinter2.setAttribute('scope', 'col');
                    thTheadModelsPrinter2.innerText = 'Модель принтера';
                    theadModelsPrinter.appendChild(trTheadModelsPrinter);
                    trTheadModelsPrinter.appendChild(thTheadModelsPrinter1);
                    trTheadModelsPrinter.appendChild(thTheadModelsPrinter2);
                    tbodyModelPrinter = document.createElement('tbody');
                    tableModelsPrinter.appendChild(tbodyModelPrinter);

                    $.ajax({
                        url: '/models',
                        type: 'GET',
                        dataType: 'json',
                        success: function (data) {
                            arr = data.sort();
                            for (i = 0; i < arr.length; i++) {
                                trModelsPrinter = document.createElement('tr');
                                tbodyModelPrinter.appendChild(trModelsPrinter);
                                tdCountModelsPrinter = document.createElement('td');
                                tdCountModelsPrinter.innerText = i + 1;
                                trModelsPrinter.appendChild(tdCountModelsPrinter);
                                tdModel = document.createElement('td');
                                tdModel.setAttribute('id', arr[i].idModel);
                                tdModel.setAttribute('class', 'model');
                                tdModel.innerText = arr[i].manufacturer + " " + arr[i].model;
                                trModelsPrinter.appendChild(tdModel);
                            }
                        }
                    });

                    $('#modalModelsPrinter2').modal('hide');

                },
                error: function (callback) {
                    if ($('#exceptionContainer').length == 0) {
                        $('#modalBody').append(callback.responseText);
                    } else {

                        $('#exceptionContainer').replaceWith(callback.responseText);

                    }

                    new bootstrap.Modal(document.getElementById('modalError')).show();
                    // $('#resultInfo').append(callback.responseText);
                }
            });
            $('#modalManufacturerSelect')[0].selectize.clear();
            $('#modalModelInput')[0].selectize.clear();
            $('#modalPrintColorTypeSelect')[0].value = "";
            $('#modalPrintFormatTypeSelect')[0].value = "";
            $('#modalPrintSpeedSelect')[0].value = "";
        });

       

    });
    
     modalModelsPrinterFooterCloseBtn.addEventListener("click", function () {
            $('#modalModelsPrinter').empty();
            window.location.reload();

        });

        modalModelsPrinterBtnClose.addEventListener('click', function() {
            $('#modalModelsPrinter').empty();
            window.location.reload();
        });

    // Модальное окно с моделями картриджей


    modalModelsCartridgeWrapper = document.createElement('div');
    modalModelsCartridgeWrapper.className = 'modal fade';
    modalModelsCartridgeWrapper.id = 'modalModelsCartridge';
    modalModelsCartridgeWrapper.setAttribute('data-bs-backdrop', 'static');
    modalModelsCartridgeWrapper.setAttribute('data-bs-keyboard', 'false');
    modalModelsCartridgeWrapper.setAttribute('tabindex', '-1');
    parent.appendChild(modalModelsCartridgeWrapper);

    modalDialogCenteredModelsCartridgeDiv = document.createElement('div');
    modalDialogCenteredModelsCartridgeDiv.className = 'modal-dialog modal-dialog-centered';
    modalModelsCartridgeWrapper.appendChild(modalDialogCenteredModelsCartridgeDiv);

    modalContentModelsCartridgeDiv = document.createElement('div');
    modalContentModelsCartridgeDiv.className = 'modal-content';
    modalDialogCenteredModelsCartridgeDiv.appendChild(modalContentModelsCartridgeDiv);

    modalHeaderModelsCartridgeDiv = document.createElement('div');
    modalHeaderModelsCartridgeDiv.className = 'modal-header';
    modalContentModelsCartridgeDiv.appendChild(modalHeaderModelsCartridgeDiv);

    modalTitleModelsCartridgeH1 = document.createElement('h1');
    modalTitleModelsCartridgeH1.className = 'modal-title fs-5';
    modalTitleModelsCartridgeH1.id = 'modalModelsCartridgeLabel';
    modalTitleModelsCartridgeH1.innerText = 'Список моделей картриджей';
    modalHeaderModelsCartridgeDiv.appendChild(modalTitleModelsCartridgeH1);

    modalModelsCartridgeBtnClose = document.createElement('button');
    modalModelsCartridgeBtnClose.className = 'btn-close';
    modalModelsCartridgeBtnClose.type = 'button';
    modalModelsCartridgeBtnClose.setAttribute('data-bs-dismiss', 'modal');
    modalModelsCartridgeBtnClose.setAttribute('aria-label', 'close');
    modalHeaderModelsCartridgeDiv.appendChild(modalModelsCartridgeBtnClose);

    modalModelsCartridgeBodyDiv = document.createElement('div');
    modalModelsCartridgeBodyDiv.className = 'modal-body ml-3 mr-3';
    modalContentModelsCartridgeDiv.appendChild(modalModelsCartridgeBodyDiv);

    modalModelsCartridgeBodyContentInner = document.createElement('div');
    modalModelsCartridgeBodyContentInner.className = 'modalContentInner';
    modalModelsCartridgeBodyContentInner.id = 'modalModelsCartridgeContentInner';
    modalModelsCartridgeBodyDiv.appendChild(modalModelsCartridgeBodyContentInner);

    //Content

    modalModelsCartridgeButtonsRow = document.createElement('div');
    modalModelsCartridgeButtonsRow.className = 'row';
    modalModelsCartridgeBodyDiv.appendChild(modalModelsCartridgeButtonsRow);

    modalModelsCartridgeBtnAdd = document.createElement('button');
    modalModelsCartridgeBtnAdd.className = 'btn btn-secondary ';
    modalModelsCartridgeBtnAdd.type = 'button';
    modalModelsCartridgeBtnAdd.setAttribute('data-bs-target', '#modalModelsCartridge2');
    modalModelsCartridgeBtnAdd.setAttribute('data-bs-toggle', 'modal');
    modalModelsCartridgeBtnAdd.innerText = 'Добавить модель';
    modalModelsCartridgeButtonsRow.appendChild(modalModelsCartridgeBtnAdd);

    modalModelsCartridgeFooterDiv = document.createElement('div');
    modalModelsCartridgeFooterDiv.className = 'modal-footer';
    modalContentModelsCartridgeDiv.appendChild(modalModelsCartridgeFooterDiv);

    modalModelsCartridgeFooterCloseBtn = document.createElement('button');
    modalModelsCartridgeFooterCloseBtn.className = 'btn btn-secondary';
    modalModelsCartridgeFooterCloseBtn.type = 'button';
    modalModelsCartridgeFooterCloseBtn.setAttribute('data-bs-dismiss', 'modal');
    modalModelsCartridgeFooterCloseBtn.innerText = 'Закрыть';
    modalModelsCartridgeFooterCloseBtn.id = "modalModelsCartridgeWindow1BtnClose";
    modalModelsCartridgeFooterDiv.appendChild(modalModelsCartridgeFooterCloseBtn);



    // Модальное окно с добавлением моделей принтеров

    modalModelsCartridgeWrapper2 = document.createElement('div');
    modalModelsCartridgeWrapper2.className = 'modal fade';
    modalModelsCartridgeWrapper2.id = 'modalModelsCartridge2';
    modalModelsCartridgeWrapper2.setAttribute('aria-hidden', 'true');
    modalModelsCartridgeWrapper2.setAttribute('tabindex', '-1');
    parent.appendChild(modalModelsCartridgeWrapper2);

    modalModelsCartridgeDialogCenteredDiv2 = document.createElement('div');
    modalModelsCartridgeDialogCenteredDiv2.className = 'modal-dialog modal-dialog-centered modal-xl';
    modalModelsCartridgeWrapper2.appendChild(modalModelsCartridgeDialogCenteredDiv2);

    modalModelsCartridgeContentDiv2 = document.createElement('div');
    modalModelsCartridgeContentDiv2.className = 'modal-content';
    modalModelsCartridgeDialogCenteredDiv2.appendChild(modalModelsCartridgeContentDiv2);

    modalModelsCartridgeHeaderDiv2 = document.createElement('div');
    modalModelsCartridgeHeaderDiv2.className = 'modal-header';
    modalModelsCartridgeContentDiv2.appendChild(modalModelsCartridgeHeaderDiv2);

    modalModelsCartridgeTitle2H1 = document.createElement('h1');
    modalModelsCartridgeTitle2H1.className = 'modal-title fs-5';
    modalModelsCartridgeTitle2H1.id = 'modalModelsCartridgeLabel2';
    modalModelsCartridgeTitle2H1.innerText = 'Добавить модель картриджа';
    modalModelsCartridgeHeaderDiv2.appendChild(modalModelsCartridgeTitle2H1);

    modalModelsCartridgeBtnClose2 = document.createElement('button');
    modalModelsCartridgeBtnClose2.className = 'btn-close';
    modalModelsCartridgeBtnClose2.type = 'button';
    modalModelsCartridgeBtnClose2.setAttribute('data-bs-dismiss', 'modal');
    modalModelsCartridgeBtnClose2.setAttribute('aria-label', 'close');
    modalModelsCartridgeHeaderDiv2.appendChild(modalModelsCartridgeBtnClose2);

    modalModelsCartridgeBodyDiv2 = document.createElement('div');
    modalModelsCartridgeBodyDiv2.className = 'modal-body ml-3 mr-3';
    modalModelsCartridgeContentDiv2.appendChild(modalModelsCartridgeBodyDiv2);


    modalModelsCartridgeContentInnerRow1 = document.createElement('div');
    modalModelsCartridgeContentInnerRow1.className = 'row modalModelCartridgeRow mt-3';
    modalModelsCartridgeBodyDiv2.appendChild(modalModelsCartridgeContentInnerRow1);

    modalModelsCartridgeContentInnerDiv1 = document.createElement('div');
    modalModelsCartridgeContentInnerDiv1.className = 'col';
    modalModelsCartridgeContentInnerDiv1.id = 'modalModelCartridgeDiv';
    modalModelsCartridgeContentInnerRow1.appendChild(modalModelsCartridgeContentInnerDiv1);
    
    modalModelsCartridgeContentInnerDiv5 = document.createElement('div');
    modalModelsCartridgeContentInnerDiv5.className = 'col modalManufacturerDiv';
    modalModelsCartridgeContentInnerDiv5.id = 'modalManufacturerDiv';
    modalModelsCartridgeContentInnerRow1.appendChild(modalModelsCartridgeContentInnerDiv5);

    modalModelsCartridgeContentInnerDiv2 = document.createElement('div');
    modalModelsCartridgeContentInnerDiv2.className = 'col modalModelDiv';
    modalModelsCartridgeContentInnerDiv2.id = 'modalModelDiv';
    modalModelsCartridgeContentInnerRow1.appendChild(modalModelsCartridgeContentInnerDiv2);
    
    

    modalModelsCartridgeContentInnerTypeSelect = document.createElement('select');
    modalModelsCartridgeContentInnerTypeSelect.className = 'form-select modalModelTypeInput';
    modalModelsCartridgeContentInnerTypeSelect.type = 'text';
    modalModelsCartridgeContentInnerTypeSelect.id = 'modalModelTypeInput';
    modalModelsCartridgeContentInnerDiv1.appendChild(modalModelsCartridgeContentInnerTypeSelect);
    
    modalManufacturerCartridgeContentInnerModelSelect = document.createElement('select');
    modalManufacturerCartridgeContentInnerModelSelect.className = 'form-select modalManufacturerCartridgeSelect';
    modalManufacturerCartridgeContentInnerModelSelect.id = 'modalManufacturerCartridgeSelect';
    modalManufacturerCartridgeContentInnerModelSelect.value = 'Производитель';
    modalModelsCartridgeContentInnerDiv5.appendChild(modalManufacturerCartridgeContentInnerModelSelect);
    
    // modalModelsPrinterContentInnerModelInput.placeholder = 'Модель';
    modalModelsCartridgeContentInnerModelSelect = document.createElement('select');
    modalModelsCartridgeContentInnerModelSelect.className = 'form-select modalModelCartridgeSelect';
    modalModelsCartridgeContentInnerModelSelect.id = 'modalModelCartridgeSelect';
    modalModelsCartridgeContentInnerModelSelect.value = 'Модель';
    modalModelsCartridgeContentInnerDiv2.appendChild(modalModelsCartridgeContentInnerModelSelect);

    modalModelsPrinterDiv = document.createElement('div');
    modalModelsPrinterDiv.className = 'col modalPrinterDiv';
    modalModelsPrinterDiv.id = 'modalPrinterDiv';
    modalModelsCartridgeContentInnerRow1.appendChild(modalModelsPrinterDiv);

    modalModelsPrinterSelect = document.createElement('select');
    modalModelsPrinterSelect.className = 'form-select modalModelsPrinterSelect';
    modalModelsPrinterSelect.id = 'modalModelsPrinterSelect';
    modalModelsPrinterSelect.value = 'Принтер';
    modalModelsPrinterDiv.appendChild(modalModelsPrinterSelect);

    modalNominalResourceDiv = document.createElement('div');
    modalNominalResourceDiv.className = 'col';
    modalNominalResourceDiv.id = 'modalPrintSpeedDiv';
    modalModelsCartridgeContentInnerRow1.appendChild(modalNominalResourceDiv);

    modalNominalResourceInput = document.createElement('input');
    modalNominalResourceInput.className = 'form-control';
    modalNominalResourceInput.type = 'text';
    modalNominalResourceInput.id = 'modalNominalResourceInput';
    modalNominalResourceInput.placeholder = 'Номинальный ресурс';
    modalNominalResourceDiv.appendChild(modalNominalResourceInput);

    modalModelsCartridgeFooterDiv2 = document.createElement('div');
    modalModelsCartridgeFooterDiv2.className = 'modal-footer';
    modalModelsCartridgeContentDiv2.appendChild(modalModelsCartridgeFooterDiv2);

    modalModelsCartridgeFooterCloseBtn2 = document.createElement('button');
    modalModelsCartridgeFooterCloseBtn2.className = 'btn btn-secondary';
    modalModelsCartridgeFooterCloseBtn2.type = 'button';
    modalModelsCartridgeFooterCloseBtn2.setAttribute('data-bs-toggle', 'modal');
    modalModelsCartridgeFooterCloseBtn2.setAttribute('data-bs-target', '#modalModelsCartridge');
    modalModelsCartridgeFooterCloseBtn2.innerText = 'Закрыть';
    modalModelsCartridgeFooterDiv2.appendChild(modalModelsCartridgeFooterCloseBtn2);

    modalModelsCartridgeFooterOKBtn2 = document.createElement('button');
    modalModelsCartridgeFooterOKBtn2.className = 'btn btn-primary';
    modalModelsCartridgeFooterOKBtn2.type = 'button';
    modalModelsCartridgeFooterOKBtn2.setAttribute('data-bs-toggle', 'modal');
    modalModelsCartridgeFooterOKBtn2.setAttribute('data-bs-target', '#modalModelsCartridge');
    modalModelsCartridgeFooterOKBtn2.innerText = 'Добавить';
    modalModelsCartridgeFooterDiv2.appendChild(modalModelsCartridgeFooterOKBtn2);

    const type = document.getElementById('modalModelTypeInput');
    const model = document.getElementById('modalModelCartridgeSelect');
    const modelPrinters = document.getElementById('modalModelsPrinterSelect');
    const resource = document.getElementById('modalNominalResourceInput');

    let typeChoice;
    $('.modalModelTypeInput').selectize({
        create: false,
        maxItems: 1,
        placeholder: "тип картриджа",
        valueField: 'type',
        labelField: 'type',
        searchField: "type",
        options: [{type: "Оригинальный"},
            {type: "Аналог"},
            {type: "Стартовый"}],
        onChange: function (value) {
            if (value !== '') {
                selectizeManufacturerFromChoisesTypeCartridge = $(this.$control_input[0].closest('.modalModelCartridgeRow')).find('.modalManufacturerCartridgeSelect')[0];
                typeChoice = $(this.$control_input[0].closest('.modalModelCartridgeRow')).find('.modalModelTypeInput')[0].innerText;
                selectizeManufacturerFromChoisesTypeCartridge.selectize.enable();
                typeValueFromSelectize = value;
                $.ajax({
                    url: "/cartridge/" + typeChoice + "/",
                    type: 'GET',
                    dataType: 'json', // added data type
                    success: function (res) {
                        let keys = Object.keys(selectizeManufacturerFromChoisesTypeCartridge.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeManufacturerFromChoisesTypeCartridge.selectize.removeOption(keys[i]);
                        }
                        res.forEach(manufacturer => {
                            selectizeManufacturerFromChoisesTypeCartridge.selectize.addOption(manufacturer);
                            selectizeManufacturerFromChoisesTypeCartridge.selectize.addItem(manufacturer);
                        });

                        selectizeManufacturerFromChoisesTypeCartridge.selectize.refreshOptions();
                        selectizeManufacturerFromChoisesTypeCartridge.selectize.clear();
                        selectizeManufacturerFromChoisesTypeCartridge.selectize.enable();
                    }
                });
            }
        }
    });

    
    // Selectize производитель картриджа

    $('.modalManufacturerCartridgeSelect').selectize({
        placeholder: "Производитель",
        valueField: 'manufacturer',
        labelField: 'manufacturer',
        searchField: "manufacturer",
        preload: 'focus',
        create: true,
//        load: function (query, callback) {
//            $.ajax({
//                url: '/cartridge/' + encodeURIComponent(typeChoice),
//                type: 'GET',
//                dataType: 'json',
//                data: {manufacturer: query},
//                error: callback,
//                success: callback
//            });
//        },
         onChange: function (value) {
            if (value !== '') {
                selectizeModelFromChoisesTypeCartridge = $(this.$control_input[0].closest('.modalModelCartridgeRow')).find('.modalModelCartridgeSelect')[0];
                typeChoice = $(this.$control_input[0].closest('.modalModelCartridgeRow')).find('.modalManufacturerCartridgeSelect')[0].innerText;
                selectizeModelFromChoisesTypeCartridge.selectize.enable();
                typeValueFromSelectize = value;
                $.ajax({
                    url: "/cartridge/" + type.value + "/" + encodeURIComponent(value),
                    type: 'GET',
                    dataType: 'json', // added data type
                    success: function (res) {
                        let keys = Object.keys(selectizeModelFromChoisesTypeCartridge.selectize.options);
                        for (let i = 0; i < keys.length; i++) {
                            selectizeModelFromChoisesTypeCartridge.selectize.removeOption(keys[i]);
                        }
                        res.forEach(model => {
                            selectizeModelFromChoisesTypeCartridge.selectize.addOption(model);
                            selectizeModelFromChoisesTypeCartridge.selectize.addItem(model);
                        });

                        selectizeModelFromChoisesTypeCartridge.selectize.refreshOptions();
                        selectizeModelFromChoisesTypeCartridge.selectize.clear();
                        selectizeModelFromChoisesTypeCartridge.selectize.enable();
                    }
                });
            }
        }
    });

    $(".modalModelsPrinterSelect").selectize({
        plugins: ["remove_button"],
        delimiter: ",",
        valueField: 'idModel',
        labelField: 'model',
        searchField: 'model',
        persist: false,
        maxItems: null,
        placeholder: "модель принтера",
        preload: 'focus',
        load: function (query, callback) {
            $.ajax({
                url: '/models/',
                type: 'GET',
                dataType: 'json',
                error: callback,
                success: callback
            });
        },
        create: false
//        create: function (input) {
//            return {
//                value: input,
//                text: input,
//            };
//        }
    });


    // Selectize модель картриджа

    $('.modalModelCartridgeSelect').selectize({
        placeholder: "модель картриджа",
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        preload: 'focus',
        create: true,
        load: function (query, callback) {
            $.ajax({
                url: '/cartridge/' + encodeURIComponent(typeChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        }
    });

//    $(".modalModelsPrinterSelect").selectize({
//        plugins: ["remove_button"],
//        delimiter: ",",
//        valueField: 'idModel',
//        labelField: 'model',
//        searchField: 'model',
//        persist: false,
//        maxItems: null,
//        placeholder: "модель принтера",
//        preload: 'focus',
//        load: function (query, callback) {
//            $.ajax({
//                url: '/models/',
//                type: 'GET',
//                dataType: 'json',
//                error: callback,
//                success: callback
//            });
//        },
//        create: function (input) {
//            return {
//                value: input,
//                text: input,
//            };
//        }
//    });

    modalModelsCartridgeFooterOKBtn2.addEventListener('click', function () {

        $.ajax({
            url: "/addmodelcart/",
            type: 'POST',
            data: {model: $('.modalModelCartridgeSelect')[0].innerText,
                type: $(".modalModelTypeInput")[0].innerText,
                resource: $('#modalNominalResourceInput')[0].value,
                idModel: $(".modalModelsPrinterSelect")[0].selectize.items,
                manufacturer: $('.modalManufacturerCartridgeSelect')[0].innerText
            },
            success: function (result) {
                parent = document.getElementById('modalModelsCartridgeContentInner');
                child = document.getElementById('modalTableModelsCartridge');
                parent.removeChild(child);


                tableModelsCartridge = document.createElement('table');
                tableModelsCartridge.id = "modalTableModelsCartridge";
                tableModelsCartridge.className = "table table-striped table-bordered";
                modalModelsCartridgeBodyContentInner.appendChild(tableModelsCartridge);

                theadModelsCartridge = document.createElement('thead');
                tableModelsCartridge.appendChild(theadModelsCartridge);
                trTheadModelsCartridge = document.createElement('tr');
                thTheadModelsCartridge1 = document.createElement('th');

                thTheadModelsCartridge1.setAttribute('scope', 'col');
                thTheadModelsCartridge1.innerText = '#';
                thTheadModelsCartridge2 = document.createElement('th');
                thTheadModelsCartridge2.setAttribute('scope', 'col');
                thTheadModelsCartridge2.innerText = 'Модель картриджа';
                theadModelsCartridge.appendChild(trTheadModelsCartridge);
                trTheadModelsCartridge.appendChild(thTheadModelsCartridge1);
                trTheadModelsCartridge.appendChild(thTheadModelsCartridge2);
                tbodyModelCartridge = document.createElement('tbody');
                tableModelsCartridge.appendChild(tbodyModelCartridge);

                $.ajax({
                    url: '/cartridge',
                    type: 'GET',
                    dataType: 'json',
                    success: function (data) {
                        arr = data.sort();
                        for (i = 0; i < arr.length; i++) {
                            trModelsCartridge = document.createElement('tr');
                            tbodyModelCartridge.appendChild(trModelsCartridge);
                            tdCountModelsCartridge = document.createElement('td');
                            tdCountModelsCartridge.innerText = i + 1;
                            trModelsCartridge.appendChild(tdCountModelsCartridge);
                            tdModelCartridge = document.createElement('td');
                            tdModelCartridge.setAttribute('id', arr[i].id);
                            tdModelCartridge.setAttribute('class', 'model');
                            tdModelCartridge.innerText = arr[i].model;
                            trModelsCartridge.appendChild(tdModelCartridge);
                            tdModelsCartridgeBtn = document.createElement('td');
                            
                            
                        }


                    }
                });

            },
            error: function (callback) {
                if ($('#exceptionContainer').length == 0) {
                    $('#modalBody').append(callback.responseText);
                } else {

                    $('#exceptionContainer').replaceWith(callback.responseText);

                }

                new bootstrap.Modal(document.getElementById('modalError')).show();
                // $('#resultInfo').append(callback.responseText);
            }

        });
        $("#modalModelTypeInput")[0].selectize.clear();
        $('#modalModelCartridgeSelect')[0].selectize.clear();
        $('#modalNominalResourceInput')[0].value = "";
        $("#modalModelsPrinterSelect")[0].selectize.clear();
    });

    modalModelsCartridgeFooterCloseBtn.addEventListener('click', function () {
        $('#modalModelsCartridge').empty();
        window.location.reload();
    });
    modalModelsCartridgeBtnClose.addEventListener('click', function() {
        $('#modalModelsCartridge').empty();
        window.location.reload();
    });





    //Модальное окно с планированием закупок

    divModalPaningBuy = document.createElement('div');
    divModalPaningBuy.className = 'modal fade';
    divModalPaningBuy.id = 'modalPlaning';
    divModalPaningBuy.setAttribute('tabindex', '-1');
    parent.appendChild(divModalPaningBuy);

    divModalDialogPlaningBuy = document.createElement('div');
    divModalDialogPlaningBuy.className = 'modal-dialog modal-xl';
    divModalPaningBuy.appendChild(divModalDialogPlaningBuy);

    divModalContentPlaningBuy = document.createElement('div');
    divModalContentPlaningBuy.className = 'modal-content';
    divModalDialogPlaningBuy.appendChild(divModalContentPlaningBuy);

    divModalHeaderPlaningBuy = document.createElement('div');
    divModalHeaderPlaningBuy.className = 'modal-header';
    divModalContentPlaningBuy.appendChild(divModalHeaderPlaningBuy);

    titleModalPlaningBuy = document.createElement('h1');
    titleModalPlaningBuy.className = 'modal-title fs-5';
    titleModalPlaningBuy.id = 'titleModalPlaningBuy';
    titleModalPlaningBuy.innerText = 'Показать статистику за период';

    btnCloseModalPlaningBuy = document.createElement('button');
    btnCloseModalPlaningBuy.className = 'btn-close';
    btnCloseModalPlaningBuy.setAttribute('data-bs-dismiss', 'modal');
    btnCloseModalPlaningBuy.setAttribute('aria-label', 'Close');
    divModalHeaderPlaningBuy.appendChild(titleModalPlaningBuy);
    divModalHeaderPlaningBuy.appendChild(btnCloseModalPlaningBuy);

    divModalBodyPlaningBuy = document.createElement('div');
    divModalBodyPlaningBuy.className = 'modal-body';
    divModalContentPlaningBuy.appendChild(divModalBodyPlaningBuy);

    divModalContainerInnerPlaningBuy = document.createElement('div');
    divModalContainerInnerPlaningBuy.className = 'container-fluid mt-4';
    divModalBodyPlaningBuy.appendChild(divModalContainerInnerPlaningBuy);

    divContentRowCalendars = document.createElement('div');
    divContentRowCalendars.className = 'row rowModalContent';
    divModalContainerInnerPlaningBuy.appendChild(divContentRowCalendars);

    divBeginDate = document.createElement('div');
    divBeginDate.className = 'col';

    divEndDate = document.createElement('div');
    divEndDate.className = 'col';

    divContentRowCalendars.appendChild(divBeginDate);
    divContentRowCalendars.appendChild(divEndDate);

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


    rowContentBtns = document.createElement('div');
    rowContentBtns.className = 'row rowModalContent text-center';
    divModalContainerInnerPlaningBuy.appendChild(rowContentBtns);

    divBtnApply = document.createElement('div');
    divBtnApply.className = 'col mt-5';
    rowContentBtns.appendChild(divBtnApply);

    let btnApplyPlaning = document.createElement('button');
    btnApplyPlaning.className = 'btn btn-secondary';
    btnApplyPlaning.type = 'button';
    btnApplyPlaning.id = 'btnApplyPlaning';
    btnApplyPlaning.innerText = 'Применить';
    divBtnApply.appendChild(btnApplyPlaning);

    //Контент ...



    divModalFooterPlaningBuy = document.createElement('div');
    divModalFooterPlaningBuy.className = 'modal-footer';
    divModalContentPlaningBuy.appendChild(divModalFooterPlaningBuy);

    btnFooterClosePlaningBuy = document.createElement('button');
    btnFooterClosePlaningBuy.className = 'btn btn-secondary';
    btnFooterClosePlaningBuy.type = 'button';
    btnFooterClosePlaningBuy.setAttribute('data-bs-dismiss', 'modal');
    btnFooterClosePlaningBuy.innerText = 'Закрыть';
    divModalFooterPlaningBuy.appendChild(btnFooterClosePlaningBuy);


    btnCloseModalPlaningBuy.addEventListener('click', function() {
        $('#modalPlaning').empty();
        window.location.reload();
    });
    
    btnFooterClosePlaningBuy.addEventListener('click', function() {
        $('#modalPlaning').empty();
        window.location.reload();
    });

    btnApplyPlaning.addEventListener('click', function () {


        dateBegin = $('#dateBeginInput')[0].value;
        dateEnd = $('#dateEndInput')[0].value;

        //loc = $('#selectLocationForPlaning')[0].selectize.$input[0].selectedOptions[0].value;
        var dateEndParse = Date.parse(dateEnd);
        var dateEndParseB = new Date(dateEndParse);
        var dateEndFormat = dateEndParseB.toLocaleDateString('ru');

        var nowDate = new Date();
        var nowDateFormat = nowDate.toLocaleDateString('ru');

        if (dateEndFormat <= nowDateFormat) {
            window.location.href = "/planing?dateBegin=" + dateBegin + "&dateEnd=" + dateEnd;
        } else {
            alert("Вы ввели неправильную дату");
        }





    });


    modalError = document.createElement('div');
    modalError.className = 'modal fade';
    modalError.id = 'modalError';
    modalError.setAttribute("tabindex", -1);
    modalError.setAttribute("aria-labelledby", "modalError");
    modalError.setAttribute("aria-hidden", true);
    modalDialog = document.createElement('div');
    modalDialog.className = 'modal-dialog modal-lg';
    modalContent = document.createElement('div');
    modalContent.className = 'modal-content';
    modalHeader = document.createElement('div');
    modalHeader.className = 'modal-header';
    titleModal = document.createElement('h1');
    titleModal.className = 'modal-title fs-5';
    titleModal.id = 'titleModal';
    titleModal.innerText = 'Ошибка!';
    closeHeaderButton = document.createElement('button');
    closeHeaderButton.className = 'btn-close';
    closeHeaderButton.setAttribute("data-bs-dismiss", "modal");
    closeHeaderButton.setAttribute('aria-label', 'Закрыть');
    $('body').append(modalError);
    modalError.appendChild(modalDialog);
    modalDialog.appendChild(modalContent);
    modalContent.appendChild(modalHeader);
    modalHeader.appendChild(titleModal);
    modalHeader.appendChild(closeHeaderButton);

    modalBody = document.createElement('div');
    modalBody.className = 'modal-body';
    modalBody.id = 'modalBody';



    modalContent.append(modalBody);
    //  modalBody.appendChild(modalContainer);
    modalFooter = document.createElement('div');
    modalFooter.className = 'modal-footer';
    footerBtnClose = document.createElement('button');
    footerBtnClose.className = 'btn btn-secondary';
    footerBtnClose.setAttribute('data-bs-dismiss', 'modal');
    footerBtnClose.innerText = 'Ok';

    modalContent.appendChild(modalFooter);
    modalFooter.appendChild(footerBtnClose);

    let addModelCartridgeMenu = document.querySelector('#addModelCartridgeMenu');
    addModelCartridgeMenu.addEventListener('click', function () {
        tableModelsCartridge = document.createElement('table');
        tableModelsCartridge.id = "modalTableModelsCartridge";
        tableModelsCartridge.className = "table table-striped table-bordered";
        modalModelsCartridgeBodyContentInner.appendChild(tableModelsCartridge);

        theadModelsCartridge = document.createElement('thead');
        tableModelsCartridge.appendChild(theadModelsCartridge);
        trTheadModelsCartridge = document.createElement('tr');
        thTheadModelsCartridge1 = document.createElement('th');

        thTheadModelsCartridge1.setAttribute('scope', 'col');
        thTheadModelsCartridge1.innerText = '#';
        thTheadModelsCartridge2 = document.createElement('th');
        thTheadModelsCartridge2.setAttribute('scope', 'col');
        thTheadModelsCartridge2.innerText = 'Модель картриджа';
        theadModelsCartridge.appendChild(trTheadModelsCartridge);
        trTheadModelsCartridge.appendChild(thTheadModelsCartridge1);
        trTheadModelsCartridge.appendChild(thTheadModelsCartridge2);
        tbodyModelCartridge = document.createElement('tbody');
        tableModelsCartridge.appendChild(tbodyModelCartridge);

        $.ajax({
            url: '/cartridge',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                arr = data.sort();
                for (i = 0; i < arr.length; i++) {
                    trModelsCartridge = document.createElement('tr');
                    tbodyModelCartridge.appendChild(trModelsCartridge);
                    tdCountModelsCartridge = document.createElement('td');
                    tdCountModelsCartridge.innerText = i + 1;
                    trModelsCartridge.appendChild(tdCountModelsCartridge);
                    tdModelCartridge = document.createElement('td');
                    tdModelCartridge.setAttribute('id', arr[i].id);
                    tdModelCartridge.setAttribute('class', 'model');
                    tdModelCartridge.innerText = arr[i].model;
                    trModelsCartridge.appendChild(tdModelCartridge);
                }
            }
        });
    });


    let addModelPrinterMenu = document.querySelector('#addModelPrinterMenu');
    addModelPrinterMenu.addEventListener('click', function () {
        tableModelsPrinter = document.createElement('table');
        tableModelsPrinter.id = "modalTableModelsPrinter";
        tableModelsPrinter.className = "table table-striped table-bordered";
        modalModelsPrinterBodyContentInner.appendChild(tableModelsPrinter);

        theadModelsPrinter = document.createElement('thead');
        tableModelsPrinter.appendChild(theadModelsPrinter);
        trTheadModelsPrinter = document.createElement('tr');
        thTheadModelsPrinter1 = document.createElement('th');

        thTheadModelsPrinter1.setAttribute('scope', 'col');
        thTheadModelsPrinter1.innerText = '#';
        thTheadModelsPrinter2 = document.createElement('th');
        thTheadModelsPrinter2.setAttribute('scope', 'col');
        thTheadModelsPrinter2.innerText = 'Модель принтера';
        theadModelsPrinter.appendChild(trTheadModelsPrinter);
        trTheadModelsPrinter.appendChild(thTheadModelsPrinter1);
        trTheadModelsPrinter.appendChild(thTheadModelsPrinter2);
        tbodyModelPrinter = document.createElement('tbody');
        tableModelsPrinter.appendChild(tbodyModelPrinter);

        $.ajax({
            url: '/models',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                arr = data.sort();
                for (i = 0; i < arr.length; i++) {
                    trModelsPrinter = document.createElement('tr');
                    tbodyModelPrinter.appendChild(trModelsPrinter);
                    tdCountModelsPrinter = document.createElement('td');
                    tdCountModelsPrinter.innerText = i + 1;
                    trModelsPrinter.appendChild(tdCountModelsPrinter);
                    tdModel = document.createElement('td');
                    tdModel.setAttribute('id', arr[i].idModel);
                    tdModel.setAttribute('class', 'model');
                    tdModel.innerText = arr[i].manufacturer + " " + arr[i].model;
                    trModelsPrinter.appendChild(tdModel);
                }
            }
        });
    });


    let addLocationMenu = document.querySelector('#addLocationMenu');
    addLocationMenu.addEventListener('click', function () {
        if ($('#tableModal').length == 0) {
            table = document.createElement('table');
            table.id = "tableModal";
            table.className = "table table-striped table-bordered";
            modalBodyContentInner.appendChild(table);

            thead = document.createElement('thead');
            table.appendChild(thead);
            trThead = document.createElement('tr');
            thThead1 = document.createElement('th');

            thThead1.setAttribute('scope', 'col');
            thThead1.innerText = '#';
            thThead2 = document.createElement('th');
            thThead2.setAttribute('scope', 'col');
            thThead2.innerText = 'Локация';
            thead.appendChild(trThead);
            trThead.appendChild(thThead1);
            trThead.appendChild(thThead2);
            tbody = document.createElement('tbody');
            table.appendChild(tbody);

            $.ajax({
                url: '/locations',
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    arr = data.sort();
                    for (i = 0; i < arr.length; i++) {
                        tr = document.createElement('tr');
                        tbody.appendChild(tr);
                        tdCount = document.createElement('td');
                        tdCount.innerText = i + 1;
                        tr.appendChild(tdCount);
                        tdLocation = document.createElement('td');
                        tdLocation.setAttribute('id', arr[i].id);
                        tdLocation.setAttribute('class', 'location');
                        tdLocation.innerText = arr[i].name;
                        tr.appendChild(tdLocation);
                    }
                }
            });
        }
    });


   selectDeviceType = document.querySelector('#selectDeviceType');
   
   
   
   selectDeviceType.addEventListener('change', function() {
       if(selectDeviceType.value === "PRINTER") {
           window.location.href = "/inventories?deviceType=PRINTER";
       } else if(selectDeviceType.value === "MFU") {
           window.location.href = "/inventories?deviceType=MFU";
       } else {
           window.location.href = "/inventories";
       }
   });

};

$(document).ready(function () {
   selectDeviceType = document.querySelector('#selectDeviceType');
   
    if(window.location.href.indexOf("PRINTER") >= 0) {
        selectDeviceType.children[1].selected = true;
    } else if(window.location.href.indexOf("MFU") >= 0) {
        selectDeviceType.children[2].selected = true;
    } else {
        selectDeviceType.children[0].selected = true;
    }
    
});