let parent = document.querySelector('#tabContent');
let models = new Map(Object.entries(modelsInput));
let locations = new Map(Object.entries(locationsInput));
let oldModelIdValue;
let oldModelPrinterIdValue;




let searchModel = false;
window.onload = function () {
    setInterval('AJAXPing()', 28000);


    let tableContainer = document.getElementById('tableContainer');

    if (input.length > 0) {
        // Вывод таблицы
        let table = document.createElement('table');
        table.id = "inventoriesTable";
        table.className = "table align-middle table-hover table-scroll table-striped table-bordered inventoriesTable";
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
        tbody.id = 'inventoriesTbody';
        tbody.className = 'inventoriesTbody';
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
                    thTheadCount.innerText = "принтеров";
                    thTheadCount2 = document.createElement('th');
                    thTheadCount2.className = 'tHeader';
                    thTheadCount2.innerText = "картриджей";
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
    modalDialogCenteredModelsPrinterDiv.className = 'modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable';
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
    
    modalHeaderForNavModelsPrinterDiv = document.createElement('div');
    modalHeaderForNavModelsPrinterDiv.className = 'modal-header';
    modalHeaderForNavModelsPrinterDiv.id = 'modalHeaderPrinterForNav';
    modalContentModelsPrinterDiv.appendChild(modalHeaderForNavModelsPrinterDiv);
    
    modalHeaderForNavModelsPrinterDiv.innerHTML = '<nav id="modelPrinterNav"><div class="nav nav-tabs" id="nav-tab-modelsPrinter" role="tablist"> ' +
                                                 '<button class="nav-link active" id="actualModelsPrinter" data-bs-toggle="tab" data-bs-target="#activePrinterModelList" type="button" role="tab" aria-controls="nav-home" aria-selected="true">Актуальные</button> ' +
                                                 '<button class="nav-link" id="archivedModelsPrinter" data-bs-toggle="tab" data-bs-target="#archivedModelPrinterList" type="button" role="tab" aria-controls="nav-contact" aria-selected="false">В архиве</button></div></nav> ' + 
                                                 '<div class="input-group mb-3" id="searchModelPrinter">' +
                                                  '<input type="text" class="form-control" placeholder="Поиск" aria-label="SearchModel" aria-describedby="basic-addon1">' +
                                                  '<span class="input-group-text" id="basic-addon12"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">' +
                                                  '<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>' +
                                                    '</svg></span>' +
                                                  '</div>';


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
    modalModelsPrinterBodyContentInner.className = 'tab-content modalContentInner';
    modalModelsPrinterBodyContentInner.id = 'modalModelsPrinterContentInner';
    modalModelsPrinterBodyDiv.appendChild(modalModelsPrinterBodyContentInner);

    //Content


    modalModelsPrinterFooterDiv = document.createElement('div');
    modalModelsPrinterFooterDiv.className = 'modal-footer';
    modalContentModelsPrinterDiv.appendChild(modalModelsPrinterFooterDiv);


    modalModelsPrinterBtnAdd = document.createElement('button');
    modalModelsPrinterBtnAdd.className = 'btn btn-secondary ';
    modalModelsPrinterBtnAdd.type = 'button';
    modalModelsPrinterBtnAdd.setAttribute('data-bs-target', '#modalModelsPrinter2');
    modalModelsPrinterBtnAdd.setAttribute('data-bs-toggle', 'modal');
    modalModelsPrinterBtnAdd.id = 'addModelPrinterModal';
    modalModelsPrinterBtnAdd.innerText = 'Добавить модель';
    modalModelsPrinterFooterDiv.appendChild(modalModelsPrinterBtnAdd);
    
    modalModelsPrinterFooterCloseBtn = document.createElement('button');
    modalModelsPrinterFooterCloseBtn.className = 'btn btn-secondary';
    modalModelsPrinterFooterCloseBtn.type = 'button';
    modalModelsPrinterFooterCloseBtn.setAttribute('data-bs-dismiss', 'modal');
    modalModelsPrinterFooterCloseBtn.innerText = 'Закрыть';
    modalModelsPrinterFooterCloseBtn.id = "modalMOdelsPrinterWindow1BtnClose";
    modalModelsPrinterFooterDiv.appendChild(modalModelsPrinterFooterCloseBtn);
    
    
    // Модальное окно с редактированием моделей принтеров
    
    
    modalEditModelPrinterWrapper = document.createElement('div');
    modalEditModelPrinterWrapper.className = 'modal fade';
    modalEditModelPrinterWrapper.id = 'modalEditModelPrinter';
    modalEditModelPrinterWrapper.setAttribute('data-bs-backdrop', 'static');
    modalEditModelPrinterWrapper.setAttribute('data-bs-keyboard', 'false');
    modalEditModelPrinterWrapper.setAttribute('tabindex', '-1');
    parent.appendChild(modalEditModelPrinterWrapper);

    modalDialogCenteredEditModelPrinterDiv = document.createElement('div');
    modalDialogCenteredEditModelPrinterDiv.className = 'modal-dialog modal-dialog-centered modal-lg';
    modalEditModelPrinterWrapper.appendChild(modalDialogCenteredEditModelPrinterDiv);

    modalContentEditModelPrinterDiv = document.createElement('div');
    modalContentEditModelPrinterDiv.className = 'modal-content';
    modalDialogCenteredEditModelPrinterDiv.appendChild(modalContentEditModelPrinterDiv);

    modalHeaderEditModelPrinterDiv = document.createElement('div');
    modalHeaderEditModelPrinterDiv.className = 'modal-header';
    modalContentEditModelPrinterDiv.appendChild(modalHeaderEditModelPrinterDiv);

    modalTitleEditModelPrinterH1 = document.createElement('h1');
    modalTitleEditModelPrinterH1.className = 'modal-title fs-5';
    modalTitleEditModelPrinterH1.id = 'modalEditModelPrinterTitle';
    modalTitleEditModelPrinterH1.innerText = 'Редактирование модели принтера';
    modalHeaderEditModelPrinterDiv.appendChild(modalTitleEditModelPrinterH1);

    modalEditdModelPrinterBtnClose = document.createElement('button');
    modalEditdModelPrinterBtnClose.className = 'btn-close';
    modalEditdModelPrinterBtnClose.type = 'button';
    modalEditdModelPrinterBtnClose.id = 'modalEditModelPrinterCloseBtnHeader';
    modalEditdModelPrinterBtnClose.setAttribute('data-bs-toggle', 'modal');
    modalEditdModelPrinterBtnClose.setAttribute('data-bs-target', '#modalModelsPrinter');
    modalEditdModelPrinterBtnClose.setAttribute('aria-label', 'close');
    modalHeaderEditModelPrinterDiv.appendChild(modalEditdModelPrinterBtnClose);

    modalEditModelPrinterBodyDiv = document.createElement('div');
    modalEditModelPrinterBodyDiv.className = 'modal-body ml-3 mr-3';
    modalEditModelPrinterBodyDiv.id = 'modalEditModelPrinterBody';
    modalContentEditModelPrinterDiv.appendChild(modalEditModelPrinterBodyDiv);

    modalEditModelPrinterBodyContentInner = document.createElement('div');
    modalEditModelPrinterBodyContentInner.className = 'modalEditModelPrinterContentInner';
    modalEditModelPrinterBodyContentInner.id = 'modalEditModelPrinterContentInner';
    modalEditModelPrinterBodyDiv.appendChild(modalEditModelPrinterBodyContentInner);
    
    modalEditModelPrinterFooterDiv = document.createElement('div');
    modalEditModelPrinterFooterDiv.className = 'modal-footer';
    modalContentEditModelPrinterDiv.appendChild(modalEditModelPrinterFooterDiv);
    
    
    modalEditModelPrinterBtnRemove = document.createElement('button');
    modalEditModelPrinterBtnRemove.className = 'btn btn-danger';
    modalEditModelPrinterBtnRemove.type = 'button';
    modalEditModelPrinterBtnRemove.innerText = 'Удалить';
    modalEditModelPrinterBtnRemove.id = 'btnRemovePrinterModel';
    modalEditModelPrinterFooterDiv.appendChild(modalEditModelPrinterBtnRemove);
    
    
    modalEditModelPrinterBtnSave = document.createElement('button');
    modalEditModelPrinterBtnSave.className = 'btn btn-success';
    modalEditModelPrinterBtnSave.type = 'button';
    modalEditModelPrinterBtnSave.innerText = 'Применить';
    modalEditModelPrinterBtnSave.id = 'btnSavePrinterModel';
    modalEditModelPrinterFooterDiv.appendChild(modalEditModelPrinterBtnSave);
    
    modalEditModelPrinterFooterCloseBtn = document.createElement('button');
    modalEditModelPrinterFooterCloseBtn.className = 'btn btn-secondary';
    modalEditModelPrinterFooterCloseBtn.type = 'button';
    modalEditModelPrinterFooterCloseBtn.setAttribute('data-bs-toggle', 'modal');
    modalEditModelPrinterFooterCloseBtn.setAttribute('data-bs-target', '#modalModelsPrinter');
    modalEditModelPrinterFooterCloseBtn.innerText = 'Отмена';
    modalEditModelPrinterFooterCloseBtn.id = "modalEditModelPrinterWindow1BtnClose"; 
    modalEditModelPrinterFooterDiv.appendChild(modalEditModelPrinterFooterCloseBtn);
    


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
    modalDialogCenteredModelsCartridgeDiv.className = 'modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable';
    modalModelsCartridgeWrapper.appendChild(modalDialogCenteredModelsCartridgeDiv);

    modalContentModelsCartridgeDiv = document.createElement('div');
    modalContentModelsCartridgeDiv.className = 'modal-content';
    modalDialogCenteredModelsCartridgeDiv.appendChild(modalContentModelsCartridgeDiv);

    modalHeaderModelsCartridgeDiv = document.createElement('div');
    modalHeaderModelsCartridgeDiv.className = 'modal-header';
    modalContentModelsCartridgeDiv.appendChild(modalHeaderModelsCartridgeDiv);
    
    modalHeaderForNavModelsCartridgeDiv = document.createElement('div');
    modalHeaderForNavModelsCartridgeDiv.className = 'modal-header';
    modalHeaderForNavModelsCartridgeDiv.id = 'modalHeaderForNav';
    modalContentModelsCartridgeDiv.appendChild(modalHeaderForNavModelsCartridgeDiv);
    modalTitleModelsCartridgeRow = document.createElement('div');
    modalTitleModelsCartridgeRow.id = 'modalTitleModelsCartridgeRow';
    modalTitleModelsCartridgeRow.className = 'row text-center';
    modalHeaderModelsCartridgeDiv.appendChild(modalTitleModelsCartridgeRow);
    
    modalTitleModelsCartridgeH1 = document.createElement('h1');
    modalTitleModelsCartridgeH1.className = 'modal-title fs-5 text-center';
    modalTitleModelsCartridgeH1.id = 'modalModelsCartridgeLabel';
    modalTitleModelsCartridgeH1.innerText = 'Список моделей картриджей';
    modalTitleModelsCartridgeRow.appendChild(modalTitleModelsCartridgeH1);
    


    modalHeaderForNavModelsCartridgeDiv.innerHTML = '<nav id="modelCartridgeNav"><div class="nav nav-tabs" id="nav-tab" role="tablist"> ' +
                                                 '<button class="nav-link active" id="actualModels" data-bs-toggle="tab" data-bs-target="#activeCartridgeList" type="button" role="tab" aria-controls="nav-home" aria-selected="true">Актуальные</button> ' +
                                                 '<button class="nav-link" id="archivedModels" data-bs-toggle="tab" data-bs-target="#archivedCartridgeList" type="button" role="tab" aria-controls="nav-contact" aria-selected="false">В архиве</button></div></nav> ' + 
                                                 '<div class="input-group mb-3" id="searchModelCart">' +
                                                  '<input type="text" class="form-control" placeholder="Поиск" aria-label="SearchModel" aria-describedby="basic-addon1">' +
                                                  '<span class="input-group-text" id="basic-addon1"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">' +
                                                  '<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>' +
                                                    '</svg></span>' +
                                                  '</div>';

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
    modalModelsCartridgeBodyContentInner.className = 'tab-content modalContentInner';
    modalModelsCartridgeBodyContentInner.id = 'modalModelsCartridgeContentInner';
    modalModelsCartridgeBodyDiv.appendChild(modalModelsCartridgeBodyContentInner);


    //Content



    

    modalModelsCartridgeFooterDiv = document.createElement('div');
    modalModelsCartridgeFooterDiv.className = 'modal-footer';
    modalContentModelsCartridgeDiv.appendChild(modalModelsCartridgeFooterDiv);
    
    modalModelsCartridgeBtnAdd = document.createElement('button');
    modalModelsCartridgeBtnAdd.className = 'btn btn-secondary ';
    modalModelsCartridgeBtnAdd.type = 'button';
    modalModelsCartridgeBtnAdd.setAttribute('data-bs-target', '#modalModelsCartridge2');
    modalModelsCartridgeBtnAdd.setAttribute('data-bs-toggle', 'modal');
    modalModelsCartridgeBtnAdd.innerText = 'Добавить модель';
    modalModelsCartridgeBtnAdd.id = 'addCartridgeModel';
    modalModelsCartridgeFooterDiv.appendChild(modalModelsCartridgeBtnAdd);
    
    modalModelsCartridgeFooterCloseBtn = document.createElement('button');
    modalModelsCartridgeFooterCloseBtn.className = 'btn btn-secondary';
    modalModelsCartridgeFooterCloseBtn.type = 'button';
    modalModelsCartridgeFooterCloseBtn.setAttribute('data-bs-dismiss', 'modal');
    modalModelsCartridgeFooterCloseBtn.innerText = 'Закрыть';
    modalModelsCartridgeFooterCloseBtn.id = "modalModelsCartridgeWindow1BtnClose";
    modalModelsCartridgeFooterDiv.appendChild(modalModelsCartridgeFooterCloseBtn);
    

    
        // Модальное окно с редактированием модели картриджа


    modalEditModelCartridgeWrapper = document.createElement('div');
    modalEditModelCartridgeWrapper.className = 'modal fade';
    modalEditModelCartridgeWrapper.id = 'modalEditModelCartridge';
    modalEditModelCartridgeWrapper.setAttribute('data-bs-backdrop', 'static');
    modalEditModelCartridgeWrapper.setAttribute('data-bs-keyboard', 'false');
    modalEditModelCartridgeWrapper.setAttribute('tabindex', '-1');
    parent.appendChild(modalEditModelCartridgeWrapper);

    modalDialogCenteredEditModelCartridgeDiv = document.createElement('div');
    modalDialogCenteredEditModelCartridgeDiv.className = 'modal-dialog modal-dialog-centered modal-lg';
    modalEditModelCartridgeWrapper.appendChild(modalDialogCenteredEditModelCartridgeDiv);

    modalContentEditModelCartridgeDiv = document.createElement('div');
    modalContentEditModelCartridgeDiv.className = 'modal-content';
    modalDialogCenteredEditModelCartridgeDiv.appendChild(modalContentEditModelCartridgeDiv);

    modalHeaderEditModelCartridgeDiv = document.createElement('div');
    modalHeaderEditModelCartridgeDiv.className = 'modal-header';
    modalContentEditModelCartridgeDiv.appendChild(modalHeaderEditModelCartridgeDiv);

    modalTitleEditModelCartridgeH1 = document.createElement('h1');
    modalTitleEditModelCartridgeH1.className = 'modal-title fs-5';
    modalTitleEditModelCartridgeH1.id = 'modalEditModelCartridgeTitle';
    modalTitleEditModelCartridgeH1.innerText = 'Редактирование модели картриджа';
    modalHeaderEditModelCartridgeDiv.appendChild(modalTitleEditModelCartridgeH1);

    modalEditdModelCartridgeBtnClose = document.createElement('button');
    modalEditdModelCartridgeBtnClose.className = 'btn-close';
    modalEditdModelCartridgeBtnClose.type = 'button';
    modalEditdModelCartridgeBtnClose.id = 'modalEditModelCartridgeCloseBtnHeader';
    modalEditdModelCartridgeBtnClose.setAttribute('data-bs-toggle', 'modal');
    modalEditdModelCartridgeBtnClose.setAttribute('data-bs-target', '#modalModelsCartridge');
    modalEditdModelCartridgeBtnClose.setAttribute('aria-label', 'close');
    modalHeaderEditModelCartridgeDiv.appendChild(modalEditdModelCartridgeBtnClose);

    modalEditModelCartridgeBodyDiv = document.createElement('div');
    modalEditModelCartridgeBodyDiv.className = 'modal-body ml-3 mr-3';
    modalEditModelCartridgeBodyDiv.id = 'modalEditModelCartridgeBody';
    modalContentEditModelCartridgeDiv.appendChild(modalEditModelCartridgeBodyDiv);

    modalEditModelCartridgeBodyContentInner = document.createElement('div');
    modalEditModelCartridgeBodyContentInner.className = 'modalEditModelContentInner';
    modalEditModelCartridgeBodyContentInner.id = 'modalEditModelCartridgeContentInner';
    modalEditModelCartridgeBodyDiv.appendChild(modalEditModelCartridgeBodyContentInner);


    //Content

    modalEditModelCartridgeButtonsRow = document.createElement('div');
    modalEditModelCartridgeButtonsRow.className = 'row btnSaveCartridgeModel mt-5 mx-3 px-3 text-center';
    modalEditModelCartridgeBodyDiv.appendChild(modalEditModelCartridgeButtonsRow);
    
    modalEditColBtnSave = document.createElement('div');
    modalEditColBtnSave.className = 'col-6';
    
    modalEditColBtnRemove = document.createElement('div');
    modalEditColBtnRemove.className = 'col-6';
    
    modalEditModelCartridgeButtonsRow.appendChild(modalEditColBtnRemove);
    modalEditModelCartridgeButtonsRow.appendChild(modalEditColBtnSave);
    
    modalEditModelCartridgeBtnSave = document.createElement('button');
    modalEditModelCartridgeBtnSave.className = 'btn btn-success';
    modalEditModelCartridgeBtnSave.type = 'button';

    modalEditModelCartridgeBtnSave.innerText = 'Применить';
    modalEditModelCartridgeBtnSave.id = 'btnSaveCartridgeModel';
    
    modalEditModelCartridgeBtnRemove = document.createElement('button');
    modalEditModelCartridgeBtnRemove.className = 'btn btn-danger';
    modalEditModelCartridgeBtnRemove.type = 'button';

    modalEditModelCartridgeBtnRemove.innerText = 'Удалить';
    modalEditModelCartridgeBtnRemove.id = 'btnRemoveCartridgeModel';
    modalEditColBtnSave.appendChild(modalEditModelCartridgeBtnSave);
    modalEditColBtnRemove.appendChild(modalEditModelCartridgeBtnRemove);
    
    // Удаление модели картриджа
    
    modalEditModelCartridgeBtnRemove.addEventListener('click', function(){
        $.ajax({
        type: "DELETE",
        url: "/editModelCartridge/",
        data: {id: oldModelIdValue},
        async: false,
        success: function () {
            $('#modalEditModelCartridge').modal('hide');
            document.querySelector('#modalEditModelCartridgeContentInner').innerHTML = '';
            $('#addModelCartridgeMenu').click();
            $('body').append('<div id="delete-alert-cartridge" class="alert alert-success alert-dismissible fade" style="position: fixed; bottom: 15px; right: 25px; max-width: 360px; margin: 0 auto; z-index: 1055;">Модель успешно удалена<button type="button" class="btn-close" data-bs-dismiss="alert"></button></div>');
            document.querySelector('#delete-alert-cartridge').classList.add('show');
            $("#delete-alert-cartridge").fadeTo(5000, 500).slideUp(500, function(){
                                        $("#delete-alert-cartridge").slideUp(500);
                                    });
        },
        error: function(callback) {
            alert("Ошибка");
  }, 
    

    });
        
    });
    
    modalEditModelCartridgeFooterDiv = document.createElement('div');
    modalEditModelCartridgeFooterDiv.className = 'modal-footer';
    modalContentEditModelCartridgeDiv.appendChild(modalEditModelCartridgeFooterDiv);

    modalEditModelCartridgeFooterCloseBtn = document.createElement('button');
    modalEditModelCartridgeFooterCloseBtn.className = 'btn btn-secondary';
    modalEditModelCartridgeFooterCloseBtn.type = 'button';
    modalEditModelCartridgeFooterCloseBtn.setAttribute('data-bs-toggle', 'modal');
    modalEditModelCartridgeFooterCloseBtn.setAttribute('data-bs-target', '#modalModelsCartridge');
    modalEditModelCartridgeFooterCloseBtn.innerText = 'Отмена';
    modalEditModelCartridgeFooterCloseBtn.id = "modalEditModelCartridgeWindow1BtnClose"; 
    modalEditModelCartridgeFooterDiv.appendChild(modalEditModelCartridgeFooterCloseBtn);
    
    modalEditModelCartridgeFooterCloseBtn.addEventListener('click', function() {
        document.querySelector('#modalEditModelCartridgeContentInner').innerHTML = '';
    });
    modalEditdModelCartridgeBtnClose.addEventListener('click', function() {
        document.querySelector('#modalEditModelCartridgeContentInner').innerHTML = '';
    });
    
        /*
         * Обработчик нажатия кнопки "Применить"
         * Получение изменений с полей формы
         */
    modalEditModelCartridgeBtnSave.addEventListener('click', function() {
        let options = Object.entries($('#editModelCartridgeManufacturer')[0].selectize.options);
        let target = $('#editModelCartridgeManufacturer')[0].selectize.getValue();
        let idManufacturer;
        let modelName;
        let typeModelCartridge;
        let modelCartridgeResource;
        let idModelsPrinter = $('#selectModelPrinter')[0].selectize.getValue();
        for(let el = 0; el < options.length; el++) {
            if(target.indexOf(options[el][0]) === 0) {
                idManufacturer = options[el][1].idManufacturer;
                break;
            }
        }
        typeModelCartridge = $('#modelCartridgeEditType')[0].value;
        modelName = $('#editModelCartridgeModel')[0].selectize.getValue();
        modelCartridgeResource = $('#modelCartridgeEditResource')[0].value;
        let dto = {
            id: oldModelIdValue,
            model: modelName,
            idManufacturer: idManufacturer,
            type: typeModelCartridge,
            resource: modelCartridgeResource,
            idModel: idModelsPrinter
        }
        
            $.ajax({
        type: "POST",
        url: "/updatecartridgebymodel/",
        data: dto,
        async: false,
        success: function () {
            $('#modalEditModelCartridge').modal('hide');
            document.querySelector('#modalEditModelCartridgeContentInner').innerHTML = '';
            $('#addModelCartridgeMenu').click();
            alert("Успешно сохранен");
            
        },
        error: function(callback) {
            alert("Ошибка");
  }, 
    

    });
        
    });

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

            $('#modalModelTypeInput')[0].selectize.setValue();
            $('#modalManufacturerCartridgeSelect')[0].selectize.setValue();
            $('#modalModelCartridgeSelect')[0].selectize.setValue();
            $('#modalModelsPrinterSelect')[0].selectize.setValue();
            $('#modalNominalResourceInput')[0].value = '';
            $('#modalModelsCartridge2').modal('hide');
            $('#addModelCartridgeMenu').click();

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
        modalModelsCartridgeBodyContentInner.innerHTML = '';
        
        cartridgeListContainer = document.createElement('div');
        cartridgeListContainer.id = "activeCartridgeList";
        cartridgeListContainer.className = "tab-pane fade show active cartridgeList";
        cartridgeListContainer.role = 'tabpanel';
        cartridgeListContainer.setAttribute('aria-labelledby', 'activeCartridgeList-tab');
        cartridgeListContainer.setAttribute('tabindex', 0);
        modalModelsCartridgeBodyContentInner.appendChild(cartridgeListContainer);
        
        headRow = document.createElement('div');
        headRow.className = 'row fw-bold pt-2 pb-3 mx-3 mb-3 px-3';
        headRow.style = 'border-bottom: solid rgb(43, 43, 43);';
        cartridgeListContainer.appendChild(headRow);
        headLabelCount = document.createElement('div');
        headLabelCount.className = 'col-1';
        headLabelCount.innerText = '#';
        headRow.appendChild(headLabelCount);
        headLabelManufacturerCartridge = document.createElement('div');
        headLabelManufacturerCartridge.className = 'col-5';
        headLabelManufacturerCartridge.innerText = 'Производитель';
        headRow.appendChild(headLabelManufacturerCartridge);
        headLabelModelCartridge = document.createElement('div');
        headLabelModelCartridge.className = 'col-5';
        headLabelModelCartridge.innerText = 'Модель';
        headRow.appendChild(headLabelModelCartridge);
        headLabelModelCartridgeId = document.createElement('div');
        headLabelModelCartridgeId.className = 'col-1';
        headRow.appendChild(headLabelModelCartridgeId);

        $.ajax({
            url: '/cartridge',
            type: 'GET',
            async: false,
            dataType: 'json',
            success: function (data) {
                //printerRows.sort((a, b) => (a.childNodes[0].innerText - b.childNodes[0].innerText));
                let arr = data.sort((a, b) => a.manufacturer > b.manufacturer ? 1 : -1);
                modelCartridgesContainer = document.createElement('div');
                modelCartridgesContainer.id = 'modelCartridgesContainer';
                cartridgeListContainer.appendChild(modelCartridgesContainer);
                for (i = 0; i < arr.length; i++) {
                    if(i == arr.length - 1) {
                        nextRow = document.createElement('div');
                        nextRow.className = 'row modelCartridgeRow mx-3 px-3 pb-3';
                        modelCartridgesContainer.appendChild(nextRow);
                    }else {
                            nextRow = document.createElement('div');
                            nextRow.className = 'row modelCartridgeRow mx-3 px-3';
                            nextRow.style = 'border-bottom: 0.1rem solid rgb(195, 195, 200);';
                            modelCartridgesContainer.appendChild(nextRow);
                        }
                    countCartridge = document.createElement('div');
                    countCartridge.className = 'col-1';
                    countCartridge.innerText = i + 1;
                    
                    manufacturerCartridge = document.createElement('div');
                    manufacturerCartridge.className = 'col-5 manufacturer';
                    manufacturerCartridge.innerText = arr[i].manufacturer;
                    
                    modelCartridge = document.createElement('div');
                    modelCartridge.className = 'col-5 model';
                    modelCartridge.innerText = arr[i].model;
                    
                    modelCartridgeId = document.createElement('div');
                    modelCartridgeId.className = 'col-1 idModel';
                    
                    linkCartridgeModel = document.createElement('a');
                    linkCartridgeModel.href = '#';
                    linkCartridgeModel.id = "idModel=" + arr[i].id;
                    linkCartridgeModel.setAttribute("arrIndex", i);
                    linkCartridgeModel.setAttribute("data-bs-toggle", "modal");
                    linkCartridgeModel.setAttribute("data-bs-target", "#modalEditModelCartridge");
                    imgInner = document.createElement('img');
                    imgInner.src = '/img/pencil-square.svg?height=16';
                    linkCartridgeModel.appendChild(imgInner);
                    modelCartridgeId.appendChild(linkCartridgeModel);
                    
                    nextRow.appendChild(countCartridge);
                    nextRow.appendChild(manufacturerCartridge);
                    nextRow.appendChild(modelCartridge);
                    nextRow.appendChild(modelCartridgeId);
                    linkCartridgeModel.addEventListener('click', function(event) {
                        
                        // Вызов модального окна редактирования модели картриджа
                        let modelCart = arr[event.target.parentElement.getAttribute('arrIndex')];
                        modalInner = document.querySelector('#modalEditModelCartridgeContentInner');
                        rowName = document.createElement('div');
                        rowName.className = 'row mb-3 mt-3';
                        rowName.id = 'rowName';
                        modalInner.appendChild(rowName);
                        divColManufNameLabel = document.createElement('div');
                        divColManufNameLabel.className = 'col-2 mt-1';
                        divColManufNameLabel.innerText = 'Производитель';
                        divColManufNameValue = document.createElement('div');
                        divColManufNameValue.className = 'col-4';
                        divColManufNameValue.id = 'editModelCartridgeManufacturerDiv';
                        //divColManufNameValue.innerText = modelCart.manufacturer;
                        selectManufacturer = document.createElement('select');
                        selectManufacturer.className = 'form-select';
                        selectManufacturer.id = 'editModelCartridgeManufacturer';
                        divColManufNameValue.appendChild(selectManufacturer);
                        rowName.appendChild(divColManufNameLabel);
                        rowName.appendChild(divColManufNameValue);
                        divColModelLabel = document.createElement('div');
                        divColModelLabel.className = 'col-1 mt-1';
                        divColModelLabel.innerText = 'Модель';
                        divColModelValue = document.createElement('div');
                        divColModelValue.className = 'col-5';
                       // divColModelValue.innerText = modelCart.model;
                        selectModel = document.createElement('select');
                        selectModel.className = 'form-select';
                        selectModel.id = 'editModelCartridgeModel';
                        divColModelValue.appendChild(selectModel);
                        rowName.appendChild(divColModelLabel);
                        rowName.appendChild(divColModelValue);
                        rowModelPrinter = document.createElement('div');
                        rowModelPrinter.className = 'row mb-3 mt-3';
                        rowModelPrinter.id = 'rowModelPrinter';
                        modalInner.appendChild(rowModelPrinter);
                        divColModelPrinterLabel = document.createElement('div');
                        divColModelPrinterLabel.className = 'col-3 mt-1';
                        divColModelPrinterLabel.innerText = 'Модель принтера';
                        rowModelPrinter.appendChild(divColModelPrinterLabel);
                        divColModelPrinterValue = document.createElement('div');
                        divColModelPrinterValue.className = 'col-9';
                        selectModelPrinter = document.createElement('select');
                        selectModelPrinter.className = 'form-select';
                        selectModelPrinter.id = 'selectModelPrinter';
                        divColModelPrinterValue.appendChild(selectModelPrinter);
                        rowModelPrinter.appendChild(divColModelPrinterValue);
                        rowProperties = document.createElement('div');
                        rowProperties.className = 'row mt-3 mb-5';
                        rowProperties.id = 'rowProperties';
                        modalInner.appendChild(rowProperties);
                        divColTypeModelLabel = document.createElement('div');
                        divColTypeModelLabel.className = 'col mt-1';
                        divColTypeModelLabel.innerText = 'Тип';
                        rowProperties.appendChild(divColTypeModelLabel);
                        divColTypeModelValue = document.createElement('div');
                        divColTypeModelValue.className = 'col';
                        selectTypeModel = document.createElement('select');
                        selectTypeModel.className = 'form-select';
                        selectTypeModel.id = 'modelCartridgeEditType';
                        divColTypeModelValue.appendChild(selectTypeModel);
                        optionOriginal = document.createElement('option');
                        optionOriginal.value = 'ORIGINAL';
                        optionOriginal.innerHTML = 'Оригинальный';
                        selectTypeModel.appendChild(optionOriginal);
                        optionAnalog = document.createElement('option');
                        optionAnalog.value = 'ANALOG';
                        optionAnalog.innerHTML = 'Аналог';
                        selectTypeModel.appendChild(optionAnalog);
                        optionStart = document.createElement('option');
                        optionStart.value = 'START';
                        optionStart.innerHTML = 'Стартовый';
                        selectTypeModel.appendChild(optionStart);
                        switch (modelCart.type) {
                            case "Оригинальный":
                                optionOriginal.selected = true;
                                break;
                            case "Аналог":
                                optionAnalog.selected = true;
                                break;
                            case "Стартовый":
                                optionStart.selected = true;
                                break;
                        }
                        rowProperties.appendChild(divColTypeModelValue);
                        divColModelResourceLabel = document.createElement('div');
                        divColModelResourceLabel.className = 'col mt-1';
                        divColModelResourceLabel.innerText = "Номинальный ресурс";
                        rowProperties.appendChild(divColModelResourceLabel);
                        divColModelResourceValue = document.createElement('div');
                        divColModelResourceValue.className = 'col';
                        inputModelResource = document.createElement('input');
                        inputModelResource.className = 'form-control';
                        inputModelResource.id = 'modelCartridgeEditResource';
                        inputModelResource.type = 'text';
                        inputModelResource.value = modelCart.resource;
                        divColModelResourceValue.appendChild(inputModelResource);
                        rowProperties.appendChild(divColModelResourceValue);
                        
                        // Выбор производителя и модели картриджа
                        
                         $('#editModelCartridgeManufacturer').selectize({
                            placeholder: "Производитель",
                            valueField: 'manufacturer',
                            labelField: 'manufacturer',
                            searchField: "manufacturer",
                            preload: true,
                             load: function (value, callback) {
                                    $.ajax({
                                        url: "/cartridge/",
                                        type: 'GET',
                                        async: false,
                                        dataType: 'json', 
                                        error: callback,
                                        success: callback
                                    });
                                     $('#editModelCartridgeManufacturer')[0].selectize.setValue($('#editModelCartridgeManufacturer')[0].selectize.search(modelCart.manufacturer).items[0].id);
                            }
                        });

                        $("#selectModelPrinter").selectize({
                            plugins: ["remove_button"],
                            create: false,
                            delimiter: ",",
                            valueField: 'idModel',
                            labelField: 'model',
                            searchField: ['model','idModel'],
                            persist: false,
                            maxItems: null,
                            placeholder: "модель принтера",
                            preload: true,
                            load: function (query, callback) {
                                $.ajax({
                                    url: '/models/',
                                    async: false,
                                    type: 'GET',
                                    dataType: 'json',
                                    error: callback,
                                    success: callback
                                });
                                forModelPrinter = new Array();
                                for(let r = 0; r < modelCart.idModel.length; r++) {
                                    forModelPrinter.push($('#selectModelPrinter')[0].selectize.search(modelCart.idModel[r]).items[0].id);
                                }
                                $('#selectModelPrinter')[0].selectize.setValue(forModelPrinter);
                            },
                            
                        });
                        
                        // Selectize модель картриджа
                        $('#editModelCartridgeModel').selectize({
                            create: true,
                            persist: false,
                            placeholder: "модель картриджа",
                            valueField: 'model',
                            labelField: 'model',
                            searchField: "model",
                            preload: true,
                            load: function (query, callback) {
                                $.ajax({
                                    url: '/cartridge/',
                                    async: false,
                                    type: 'GET',
                                    dataType: 'json',
                                    error: callback,
                                    success: callback
                                });
                                if(query.length < 1) {
                                   
                                 $('#editModelCartridgeModel')[0].selectize.setValue($('#editModelCartridgeModel')[0].selectize.search(modelCart.model).items[0].id);
                                 let curOptions = Object.entries($('#editModelCartridgeModel')[0].selectize.options);
                                 for(let el = 0; el < curOptions.length; el++) {
                                     if(modelCart.model.indexOf(curOptions[el][0]) === 0) {
                                         oldModelIdValue = curOptions[el][1].id;
                                     }
                                 }
                             }
                            }
                        });
                    });
                }
            }
        });
        
        // archived cartridge tab
        
        archivedCartridgeListContainer = document.createElement('div');
        archivedCartridgeListContainer.id = "archivedCartridgeList";
        archivedCartridgeListContainer.className = "tab-pane fade cartridgeList";
        archivedCartridgeListContainer.role = 'tabpanel';
        archivedCartridgeListContainer.setAttribute('aria-labelledby', 'archivedCartridgeList-tab');
        archivedCartridgeListContainer.setAttribute('tabindex', 0);
        modalModelsCartridgeBodyContentInner.appendChild(archivedCartridgeListContainer);
        
        headRow = document.createElement('div');
        headRow.className = 'row fw-bold pt-2 pb-3 mx-3 mb-3 px-3';
        headRow.style = 'border-bottom: solid rgb(43, 43, 43);';
        archivedCartridgeListContainer.appendChild(headRow);
        headLabelCount = document.createElement('div');
        headLabelCount.className = 'col-1';
        headLabelCount.innerText = '#';
        headRow.appendChild(headLabelCount);
        headLabelManufacturerCartridge = document.createElement('div');
        headLabelManufacturerCartridge.className = 'col-5';
        headLabelManufacturerCartridge.innerText = 'Производитель';
        headRow.appendChild(headLabelManufacturerCartridge);
        headLabelModelCartridge = document.createElement('div');
        headLabelModelCartridge.className = 'col-5';
        headLabelModelCartridge.innerText = 'Модель';
        headRow.appendChild(headLabelModelCartridge);
        headLabelModelCartridgeId = document.createElement('div');
        headLabelModelCartridgeId.className = 'col-1';
        headRow.appendChild(headLabelModelCartridgeId);

        $.ajax({
            url: '/cartridge?archived=true',
            type: 'GET',
            async: false,
            dataType: 'json',
            success: function (data) {
               modelCartridgesContainer = document.createElement('div');
                modelCartridgesContainer.id = 'modelArchivedCartridgesContainer';
                archivedCartridgeListContainer.appendChild(modelCartridgesContainer);
                arr = data.sort((a, b) => a.manufacturer > b.manufacturer ? 1 : -1);
                for (i = 0; i < arr.length; i++) {
                    if(i == arr.length - 1) {
                        nextRow = document.createElement('div');
                        nextRow.className = 'row modelArchivedCartridgeRow mx-3 px-3 pb-3';
                        modelCartridgesContainer.appendChild(nextRow);
                    }else {
                            nextRow = document.createElement('div');
                            nextRow.className = 'row modelArchivedCartridgeRow mx-3 px-3';
                            nextRow.style = 'border-bottom: 0.1rem solid rgb(195, 195, 200);';
                            modelCartridgesContainer.appendChild(nextRow);
                        }
                    countCartridge = document.createElement('div');
                    countCartridge.className = 'col-1';
                    countCartridge.innerText = i + 1;
                    
                    manufacturerCartridge = document.createElement('div');
                    manufacturerCartridge.className = 'col-5 manufacturer';
                    manufacturerCartridge.innerText = arr[i].manufacturer;
                    
                    modelCartridge = document.createElement('div');
                    modelCartridge.className = 'col-5 model';
                    modelCartridge.innerText = arr[i].model;
                    
                    modelCartridgeId = document.createElement('div');
                    modelCartridgeId.className = 'col-1 idModel';
                    
                    linkCartridgeModel = document.createElement('a');
                    linkCartridgeModel.href = '#';
                    linkCartridgeModel.id = "idModel=" + arr[i].id;
                    linkCartridgeModel.setAttribute("arrIndex", i);
                    linkCartridgeModel.setAttribute("data-bs-toggle", "modal");
                    linkCartridgeModel.setAttribute("data-bs-target", "#modalRepearModel");
                    imgInner = document.createElement('img');
                    imgInner.src = '/img/wrench.svg?height=16';
                    linkCartridgeModel.appendChild(imgInner);
                    modelCartridgeId.appendChild(linkCartridgeModel);
                    nextRow.appendChild(countCartridge);
                    nextRow.appendChild(manufacturerCartridge);
                    nextRow.appendChild(modelCartridge);
                    nextRow.appendChild(modelCartridgeId);
                    linkCartridgeModel.addEventListener('click', function(event) {
                        
                        $('#innr')[0].innerHTML = 'Вы действительно хотите восстановить ' + event.target.parentElement.parentElement.parentElement.childNodes[1].innerText +
                               ' ' + event.target.parentElement.parentElement.parentElement.childNodes[2].innerText + '?';
                       idModelCartridge = event.delegateTarget.id;
                       arrId = idModelCartridge.split('=');
                       $('#repearModelCartridgeBtn')[0].addEventListener('click', function() {
                           $.ajax({
                            url: '/cartridge',
                            type: 'POST',
                            async: false,
                            data: {id: arrId[1], archived: true},
                            success: function (callback) {
                                 $('#modalRepearModel').modal('hide');
                                document.querySelector('#modalEditModelCartridgeContentInner').innerHTML = '';
                                $('#addModelCartridgeMenu').click();
                                $('#activeCartridgeList').removeClass('show active');
                                $('#archivedCartridgeList').addClass('show active');
                                $('body').append('<div id="recovery-alert-model-cartridge" class="alert alert-success alert-dismissible fade" style="position: fixed; bottom: 15px; right: 25px; max-width: 360px; margin: 0 auto; z-index: 1055;">Модель успешно восстановлена<button type="button" class="btn-close" data-bs-dismiss="alert"></button></div>');
                                document.querySelector('#recovery-alert-model-cartridge').classList.add('show');
                                $("#recovery-alert-model-cartridge").fadeTo(5000, 500).slideUp(500, function(){
                                        $("#recovery-alert-model-cartridge").slideUp(500);
                                    });
                            }
                        });
                       });
                       
                    });
                }
            }
        });
        
        let actualModelsRows = [...document.querySelectorAll('.modelCartridgeRow')];
        let archivedModelsRows = [...document.querySelectorAll('.modelArchivedCartridgeRow')];
        $('#searchModelCart')[0].addEventListener('input', function(input) {
            if($('#actualModels')[0].ariaSelected === "true") {
           $('#modelCartridgesContainer')[0].innerHTML = '';
           resultArray = new Array();
            let count = 1;
            for(kkk=0; kkk < actualModelsRows.length; kkk++){
               
                target = actualModelsRows[kkk].childNodes[1].innerHTML + " " + actualModelsRows[kkk].childNodes[2].innerHTML;
                if(target.toLowerCase().search($('#searchModelCart').find('input')[0].value.toLowerCase()) > -1){
                    actualModelsRows[kkk].childNodes[0].innerHTML = count;
                    resultArray.push(actualModelsRows[kkk]);
                    $('#modelCartridgesContainer')[0].appendChild(actualModelsRows[kkk]);
                    count++;
                    }
                }
            } else {
                $('#modelArchivedCartridgesContainer')[0].innerHTML = '';
           resultArray = new Array();
            let count = 1;
            for(kkk=0; kkk < archivedModelsRows.length; kkk++){
               
                target = archivedModelsRows[kkk].childNodes[1].innerHTML + " " + archivedModelsRows[kkk].childNodes[2].innerHTML;
                if(target.toLowerCase().search($('#searchModelCart').find('input')[0].value.toLowerCase()) > -1){
                    archivedModelsRows[kkk].childNodes[0].innerHTML = count;
                    resultArray.push(archivedModelsRows[kkk]);
                    $('#modelArchivedCartridgesContainer')[0].appendChild(archivedModelsRows[kkk]);
                    count++;
                    }
                }
            }
        });
        
    });

        // Вызов модального окна списка моделей принтеров
 
    let addModelPrinterMenu = document.querySelector('#addModelPrinterMenu');
    
        addModelPrinterMenu.addEventListener('click', function () {
            
          modalModelsCartridgeBodyContentInner.innerHTML = '';
        
        printerModelListContainer = document.createElement('div');
        printerModelListContainer.id = "activePrinterModelList";
        printerModelListContainer.className = "tab-pane fade show active printerModelList";
        printerModelListContainer.role = 'tabpanel';
        printerModelListContainer.setAttribute('aria-labelledby', 'actualModelsPrinter');
        printerModelListContainer.setAttribute('tabindex', 0);
        modalModelsPrinterBodyContentInner.appendChild(printerModelListContainer);
            
        
        headRow = document.createElement('div');
        headRow.className = 'row fw-bold pt-2 pb-3 mx-3 mb-3 px-3';
        headRow.style = 'border-bottom: solid rgb(43, 43, 43);';
        printerModelListContainer.appendChild(headRow);
        
        
        headLabelCount = document.createElement('div');
        headLabelCount.className = 'col-1';
        headLabelCount.innerText = '#';
        headRow.appendChild(headLabelCount);
        headLabelManufacturerPrinter = document.createElement('div');
        headLabelManufacturerPrinter.className = 'col-5';
        headLabelManufacturerPrinter.innerText = 'Производитель';
        headRow.appendChild(headLabelManufacturerPrinter);
        headLabelModelPrinter = document.createElement('div');
        headLabelModelPrinter.className = 'col-5';
        headLabelModelPrinter.innerText = 'Модель';
        headRow.appendChild(headLabelModelPrinter);
        headLabelModelPrinterId = document.createElement('div');
        headLabelModelPrinterId.className = 'col-1';
        headRow.appendChild(headLabelModelPrinterId);

        $.ajax({
            url: '/models',
            type: 'GET',
            async: false,
            dataType: 'json',
            success: function (data) {
                arr = data.sort((a, b) => a.manufacturer > b.manufacturer ? 1 : -1);
                 modelPrinterContainer = document.createElement('div');
                 modelPrinterContainer.id = 'modelPrinterContainer';
                 printerModelListContainer.appendChild(modelPrinterContainer);
                for (i = 0; i < arr.length; i++) {
                   
                    if(i == arr.length - 1) {
                        nextRow = document.createElement('div');
                        nextRow.className = 'row modelPrinterRow mx-3 px-3 pb-3';
                        modelPrinterContainer.appendChild(nextRow);
                    }else {
                            nextRow = document.createElement('div');
                            nextRow.className = 'row modelPrinterRow mx-3 px-3';
                            nextRow.style = 'border-bottom: 0.1rem solid rgb(195, 195, 200);';
                            modelPrinterContainer.appendChild(nextRow);
                        }
                    
                    countPrinter = document.createElement('div');
                    countPrinter.className = 'col-1';
                    countPrinter.innerText = i + 1;
                    nextRow.appendChild(countPrinter);
                    
                    manufacturerPrinter = document.createElement('div');
                    manufacturerPrinter.className = 'col-5 manufacturer';
                    manufacturerPrinter.innerText = arr[i].manufacturer;
                    nextRow.appendChild(manufacturerPrinter);
                    
                    modelPrinter = document.createElement('div');
                    modelPrinter.className = 'col-5 model';
                    modelPrinter.innerText = arr[i].model;
                    nextRow.appendChild(modelPrinter);
                    
                    modelPrinterId = document.createElement('div');
                    modelPrinterId.className = 'col-1 idModelPrinter';
                    
                    linkPrinterModel = document.createElement('a');
                    linkPrinterModel.href = '#';
                    linkPrinterModel.id = 'idModelPrinter=' + arr[i].idModel;
                    linkPrinterModel.setAttribute("arrIndex", i);
                    linkPrinterModel.setAttribute("data-bs-toggle", "modal");
                    linkPrinterModel.setAttribute("data-bs-target", "#modalEditModelPrinter");
                    
                    imgInner = document.createElement('img');
                    imgInner.src = '/img/pencil-square.svg?height=16';
                    linkPrinterModel.appendChild(imgInner);
                    modelPrinterId.appendChild(linkPrinterModel);
                    
                    nextRow.appendChild(countPrinter);
                    nextRow.appendChild(manufacturerPrinter);
                    nextRow.appendChild(modelPrinter);
                    nextRow.appendChild(modelPrinterId);
                    
                    linkPrinterModel.addEventListener('click', function(event) {
                        
                        // Вызов модального окна редактирования модели принтера
                        let modelPrinterEd = arr[event.target.parentElement.getAttribute('arrIndex')];
                        modalEditModelPrinterInner = document.querySelector('#modalEditModelPrinterContentInner'); // Изменить на актуальный
                        modalEditModelPrinterInner.innerHTML = '<div class="row modalEditModelPrinterRow mt-3"><div class="col" id="modalEditModelPrinterManufacturerDiv">' +
                                                                '<select class="form-select" id="modalEditModelPrinterManufacturerSelect"></select></div>' + 
                                                                '<div class="col" id="modalEditModelPrinterModelDiv">' +
                                                                '<select class="form-select" id="modalEditModelPrinterModelSelect"></select></div>' + 
                                                                '<div class="col" id="modalEditModelPrinterDeviceTypeDiv">' +
                                                                '<select class="form-select" id="modalEditModelPrinterDeviceTypeSelect"><option value="PRINTER" id="printerOption">Принтер</option><option value="MFU" id="mfuOption">МФУ</option></select></div></div>' +
                                                                '<div class="row modalEditModelPrinterRow mt-3"><div class="col" id="modalEditModelPrinterColorDiv">' + 
                                                                '<select class="form-select" id="modalEditModelPrinterColorSelect"><option value="BLACKANDWHITE" id="blackTypeOption">Чернобелый</option><option value="COLOR" id="colorTypeOption">Цветной</option></select></div>' + 
                                                                '<div class="col" id="modalEditModelPrinterFormatDiv">' + 
                                                                '<select class="form-select" id="modalEditModelPrinterFormatSelect"><option value="A4" id="a4FormatOption">A4</option><option value="A3" id="a3FormatOption">A3</option></select></div>' + 
                                                                '<div class="col" id="modalEditModelPrinterSpeedDiv">' + 
                                                                '<input class="form-control" type="text" id="modalEditModelPrinterSpeedInput" placeholder="Скорость печати"></div></div>'
                    switch (modelPrinterEd.deviceType) {
                        case "МФУ":
                            $('#mfuOption')[0].selected = true;
                            break;
                        case "Принтер":
                            $('#printerOption')[0].selected = true;
                            break;
                    }
                    
                    switch (modelPrinterEd.printColorType) {
                        case "Чернобелый":
                            $('#blackTypeOption')[0].selected = true;
                            break;
                        case "Цветной":
                            $('#colorTypeOption')[0].selected = true;
                            break;
                    }
                    
                    switch (modelPrinterEd.printFormatType) {
                        case "A4":
                            $('#a4FormatOption')[0].selected = true;
                            break;
                        case "A3":
                            $('#a3FormatOption')[0].selected = true;
                            break;
                    }
                    
                   $('#modalEditModelPrinterSpeedInput')[0].value = modelPrinterEd.printSpeed;
                    
                    $('#modalEditModelPrinterManufacturerSelect').selectize({
                        placeholder: "Производитель",
                        valueField: 'manufacturer',
                        labelField: 'manufacturer',
                        searchField: "manufacturer",
                        preload: true,
                        load: function (value, callback) {
                                $.ajax({
                                    url: "/models",
                                    type: 'GET',
                                    async: false,
                                    dataType: 'json', 
                                    error: callback,
                                    success: callback
                                });
                                $('#modalEditModelPrinterManufacturerSelect')[0].selectize.setValue($('#modalEditModelPrinterManufacturerSelect')[0].selectize.search(modelPrinterEd.manufacturer).items[0].id);
                        }
                    });
                    
                    $('#modalEditModelPrinterModelSelect').selectize({
                        create: true,
                        persist: false,
                        placeholder: "Модель",
                        valueField: 'model',
                        labelField: 'model',
                        searchField: "model",
                        preload: true,
                        load: function (query, callback) {
                                $.ajax({
                                    url: "/models",
                                    type: 'GET',
                                    async: false,
                                    dataType: 'json', 
                                    error: callback,
                                    success: callback
                                });
                                if(query.length < 1) {
                                   
                                 $('#modalEditModelPrinterModelSelect')[0].selectize.setValue($('#modalEditModelPrinterModelSelect')[0].selectize.search(modelPrinterEd.model).items[0].id);
                                 let curOptions = Object.entries($('#modalEditModelPrinterModelSelect')[0].selectize.options);
                                 for(let el = 0; el < curOptions.length; el++) {
                                     if(modelPrinterEd.model.indexOf(curOptions[el][0]) === 0) {
                                         oldModelPrinterIdValue = curOptions[el][1].id;
                                     }
                                 }
                             }
                                
                        }
                    });
                        
                        $('#btnRemovePrinterModel')[0].addEventListener('click', function() {
                            $.ajax({
                                type: "DELETE",
                                url: "/models/",
                                data: {id: modelPrinterEd.idModel},
                                async: false,
                                success: function () {
                                    $('#modalEditModelPrinter').modal('hide');
                                    $('#modalModelsPrinterContentInner')[0].innerHTML = '';
                                    $('#addModelPrinterMenu').click();
                                    $('body').append('<div id="delete-alert-model-printer" class="alert alert-success alert-dismissible fade" style="position: fixed; bottom: 15px; right: 25px; max-width: 360px; margin: 0 auto; z-index: 1055;">Модель успешно удалена<button type="button" class="btn-close" data-bs-dismiss="alert"></button></div>');
                                    document.querySelector('#delete-alert-model-printer').classList.add('show');
                                    $("#delete-alert-model-printer").fadeTo(5000, 500).slideUp(500, function(){
                                        $("#delete-alert-model-printer").slideUp(500);
                                    });
                                },
                                error: function(callback) {
                                    alert("Ошибка");
                          }, 


                            });
                        });
                        
                         $('#btnSavePrinterModel')[0].addEventListener('click', function() {
                            let dto = {
                                idModel: modelPrinterEd.idModel,
                                model: $('#modalEditModelPrinterModelSelect')[0].value,
                                manufacturer: $('#modalEditModelPrinterManufacturerSelect')[0].value,
                                printColorType: $('#modalEditModelPrinterColorSelect')[0].value,
                                printFormatType: $('#modalEditModelPrinterFormatSelect')[0].value,
                                printSpeed: $('#modalEditModelPrinterSpeedInput')[0].value,
                                deviceType: $('#modalEditModelPrinterDeviceTypeSelect')[0].value
                            }
                            
                                 $.ajax({
                                    type: "POST",
                                    url: "/models/",
                                    data: dto,
                                    async: false,
                                    success: function () {
                                        $('#modalEditModelCartridge').modal('hide');
                                        document.querySelector('#modalEditModelCartridgeContentInner').innerHTML = '';
                                        $('#addModelCartridgeMenu').click();
                                        alert("Успешно сохранен");
                                    },
                                    error: function(callback) {
                                        alert("Ошибка");
                              }, 


                                });
                            
                        });
                        
                    });
                    
                }
            }
        });
        
        //archivedModelsPrinter tab
        
        archivedModelPrinterListContainer = document.createElement('div');
        archivedModelPrinterListContainer.id = "archivedModelPrinterList";
        archivedModelPrinterListContainer.className = "tab-pane fade printerModelList";
        archivedModelPrinterListContainer.role = 'tabpanel';
        archivedModelPrinterListContainer.setAttribute('aria-labelledby', 'archivedModelsPrinter');
        archivedModelPrinterListContainer.setAttribute('tabindex', 0);
        modalModelsPrinterBodyContentInner.appendChild(archivedModelPrinterListContainer);
        
        archivedModelPrinterListContainer.innerHTML = '<div class="row fw-bold pt-2 pb-3 mx-3 mb-3 px-3" style="border-bottom: solid rgb(43, 43, 43);">' + 
                                                        '<div class="col-1">#</div><div class="col-5">Производитель</div><div class="col-5">Модель</div><div class="col-1"></div></div>';

        
        $.ajax({
            url: '/models?archived=true',
            type: 'GET',
            async: false,
            dataType: 'json',
            success: function (data) {
                 let arr = data.sort((a, b) => a.manufacturer > b.manufacturer ? 1 : -1);
                 modelPrinterContainer = document.createElement('div');
                 modelPrinterContainer.id = 'archivedModelPrinterContainer';
                 archivedModelPrinterListContainer.appendChild(modelPrinterContainer);
                for (i = 0; i < arr.length; i++) {
                   
                    if(i == arr.length - 1) {
                        nextRow = document.createElement('div');
                        nextRow.className = 'row archivedModelPrinterRow mx-3 px-3 pb-3';
                        modelPrinterContainer.appendChild(nextRow);
                    }else {
                            nextRow = document.createElement('div');
                            nextRow.className = 'row archivedModelPrinterRow mx-3 px-3';
                            nextRow.style = 'border-bottom: 0.1rem solid rgb(195, 195, 200);';
                            modelPrinterContainer.appendChild(nextRow);
                        }
                    
                    countPrinter = document.createElement('div');
                    countPrinter.className = 'col-1';
                    countPrinter.innerText = i + 1;
                    nextRow.appendChild(countPrinter);
                    
                    manufacturerPrinter = document.createElement('div');
                    manufacturerPrinter.className = 'col-5 manufacturer';
                    manufacturerPrinter.innerText = arr[i].manufacturer;
                    nextRow.appendChild(manufacturerPrinter);
                    
                    modelPrinter = document.createElement('div');
                    modelPrinter.className = 'col-5 model';
                    modelPrinter.innerText = arr[i].model;
                    nextRow.appendChild(modelPrinter);
                    
                    modelPrinterId = document.createElement('div');
                    modelPrinterId.className = 'col-1 idModelPrinter';
                    
                    linkPrinterModel = document.createElement('a');
                    linkPrinterModel.href = '#';
                    linkPrinterModel.id = 'idModelPrinter=' + arr[i].idModel;
                    linkPrinterModel.setAttribute("arrIndex", i);
                    linkPrinterModel.setAttribute("data-bs-toggle", "modal");
                    linkPrinterModel.setAttribute("data-bs-target", "#modalRepearModelPrinter");
                    
                    imgInner = document.createElement('img');
                    imgInner.src = '/img/wrench.svg?height=16';
                    linkPrinterModel.appendChild(imgInner);
                    modelPrinterId.appendChild(linkPrinterModel);
                    nextRow.appendChild(countPrinter);
                    nextRow.appendChild(manufacturerPrinter);
                    nextRow.appendChild(modelPrinter);
                    nextRow.appendChild(modelPrinterId);
                    
                    // вызов модального окна восстановить модель принтера
                    
                    linkPrinterModel.addEventListener('click', function(event) {
                        
                        $('#innrModelPrinter')[0].innerHTML = 'Вы действительно хотите восстановить ' + event.target.parentElement.parentElement.parentElement.childNodes[1].innerText +
                               ' ' + event.target.parentElement.parentElement.parentElement.childNodes[2].innerText + '?';
                       idModelPrinter = event.delegateTarget.id;
                       arrId = idModelPrinter.split('=');
                       $('#repearModelPrinterBtn')[0].addEventListener('click', function() {
                           $.ajax({
                            url: '/recovery-model',
                            type: 'POST',
                            async: false,
                            data: {id: arrId[1], archived: true},
                            success: function (callback) {
                                 $('#modalRepearModelPrinter').modal('hide');
                                document.querySelector('#modalModelsPrinterContentInner').innerHTML = '';
                                $('#addModelPrinterMenu').click();
                                $('#activePrinterModelList').removeClass('show active');
                                $('#archivedModelPrinterList').addClass('show active');
                                $('body').append('<div id="recovery-alert-model-printer" class="alert alert-success alert-dismissible fade" style="position: fixed; bottom: 15px; right: 25px; max-width: 360px; margin: 0 auto; z-index: 1055;">Модель успешно восстановлена<button type="button" class="btn-close" data-bs-dismiss="alert"></button></div>');
                                document.querySelector('#recovery-alert-model-printer').classList.add('show');
                                $("#recovery-alert-model-printer").fadeTo(5000, 500).slideUp(500, function(){
                                        $("#recovery-alert-model-printer").slideUp(500);
                                    });
                            }
                        });
                       });
                        
                    });
                    
                }
            }
        });
        
        // Поиск по списку моделей принтеров в модальном окне
        
        let actualModelsPrinterRows = [...document.querySelectorAll('.modelPrinterRow')];
        let archivedModelsPrinterRows = [...document.querySelectorAll('.archivedModelPrinterRow')];
        
        $('#searchModelPrinter')[0].addEventListener('input', function(input) {
            if($('#actualModelsPrinter')[0].ariaSelected === "true") {
           $('#modelPrinterContainer')[0].innerHTML = '';
           resultArray = new Array();
            let count = 1;
            for(kkk=0; kkk < actualModelsPrinterRows.length; kkk++){
               
                target = actualModelsPrinterRows[kkk].childNodes[1].innerHTML + " " + actualModelsPrinterRows[kkk].childNodes[2].innerHTML;
                if(target.toLowerCase().search($('#searchModelPrinter').find('input')[0].value.toLowerCase()) > -1){
                    actualModelsPrinterRows[kkk].childNodes[0].innerHTML = count;
                    resultArray.push(actualModelsPrinterRows[kkk]);
                    $('#modelPrinterContainer')[0].appendChild(actualModelsPrinterRows[kkk]);
                    count++;
                    }
                }
            } else {
                $('#archivedModelPrinterContainer')[0].innerHTML = '';
           resultArray = new Array();
            let count = 1;
            for(kkk=0; kkk < archivedModelsPrinterRows.length; kkk++){
               
                target = archivedModelsPrinterRows[kkk].childNodes[1].innerHTML + " " + archivedModelsPrinterRows[kkk].childNodes[2].innerHTML;
                if(target.toLowerCase().search($('#searchModelPrinter').find('input')[0].value.toLowerCase()) > -1){
                    archivedModelsPrinterRows[kkk].childNodes[0].innerHTML = count;
                    resultArray.push(archivedModelsPrinterRows[kkk]);
                    $('#archivedModelPrinterContainer')[0].appendChild(archivedModelsPrinterRows[kkk]);
                    count++;
                    }
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
   
                        modalRepearModel = document.createElement('div');
                        modalRepearModel.className = 'modal fade';
                        modalRepearModel.id = 'modalRepearModel';
                        modalRepearModel.setAttribute('tabindex', -1);
                        modalRepearModel.setAttribute('aria-labelledby', 'exampleModalLabel');
                        modalRepearModel.setAttribute('aria-hidden', 'true');
                        modalRepearModel.innerHTML = '<div class="modal-dialog"> ' +
                                                        '<div class="modal-content">' +
                                                          '<div class="modal-header">' +
                                                            '<h1 class="modal-title fs-5" id="exampleModalLabel">Восстановление модели картриджа</h1>' +
                                                            '<button type="button" class="btn-close" data-bs-toggle="modal" data-bs-target="#modalModelsCartridge" aria-label="Закрыть"></button>' +
                                                          '</div>' +
                                                          '<div class="modal-body">' +
                                                            '<div id="innr"></div>' +
                                                          '</div>' +
                                                          '<div class="modal-footer">' +
                                                            '<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalModelsCartridge">Отмена</button>' +
                                                            '<button type="button" id="repearModelCartridgeBtn" class="btn btn-primary">Восстановить</button>' +
                                                          '</div>' +
                                                        '</div>' +
                                                      '</div>';
                        parent.appendChild(modalRepearModel);
                        
                        
                        modalRepearModelPrinter = document.createElement('div');
                        modalRepearModelPrinter.className = 'modal fade';
                        modalRepearModelPrinter.id = 'modalRepearModelPrinter';
                        modalRepearModelPrinter.setAttribute('tabindex', -1);
                        modalRepearModelPrinter.setAttribute('aria-labelledby', 'exampleModalLabel');
                        modalRepearModelPrinter.setAttribute('aria-hidden', 'true');
                        modalRepearModelPrinter.innerHTML = '<div class="modal-dialog"> ' +
                                                        '<div class="modal-content">' +
                                                          '<div class="modal-header">' +
                                                            '<h1 class="modal-title fs-5" id="exampleModalLabel">Восстановление модели принтера</h1>' +
                                                            '<button type="button" class="btn-close" data-bs-toggle="modal" data-bs-target="#modalModelsPrinter" aria-label="Закрыть"></button>' +
                                                          '</div>' +
                                                          '<div class="modal-body">' +
                                                            '<div id="innrModelPrinter"></div>' +
                                                          '</div>' +
                                                          '<div class="modal-footer">' +
                                                            '<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalModelsPrinter">Отмена</button>' +
                                                            '<button type="button" id="repearModelPrinterBtn" class="btn btn-primary">Восстановить</button>' +
                                                          '</div>' +
                                                        '</div>' +
                                                      '</div>';
                        parent.appendChild(modalRepearModelPrinter);


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

