/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

const content = document.querySelector('#content');

$(document).ready(function () {

    let wrapper = document.createElement('div');
    wrapper.className = 'wrapper';
    content.appendChild(wrapper);

    let title = document.createElement('h3');
    title.className = "text-center titleLocation fw-bold";
    title.innerText = JSON.parse(Object.keys(input)[0]).name;
    wrapper.appendChild(title);


  
   

    if (Object.values(input)[0].length > 0) {
    
    let btnAfterTitle = document.createElement('div');
    wrapper.appendChild(btnAfterTitle);
    let choiceBtn = document.createElement('button');
    choiceBtn.className = 'btn btn-secondary';
    choiceBtn.type = 'button';
    choiceBtn.innerText = 'Выбрать';
    btnAfterTitle.appendChild(choiceBtn);
        
        
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



        for (i = 0; i < Object.values(input)[0].length; i++) {

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
            contentRowPrinterName.id = "idCartridge_" + Object.values(input)[0][i].id;
            contentRow.appendChild(contentRowPrinterName);


            link = document.createElement('a');
            link.setAttribute('href', '/editcartridge?idCartridge=' + Object.values(input)[0][i].id);
            link.innerText = Object.values(input)[0][i].model;
            contentRowPrinterName.appendChild(link);


            contentRowContractNumber = document.createElement('div');
            contentRowContractNumber.className = 'col';
            contentRowContractNumber.innerText = Object.values(input)[0][i].contractNumber;
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
                btnAfterTitle.appendChild(btnTransfer);
   
                locations = Object.entries($('.selectLocation')[0].selectize.options);
                for(i = 0; i < locations.length; i++) {
                    if(locations[i][1].id == JSON.parse(Object.entries(input)[0][0]).id ) {
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

    btnCancel = document.createElement('input');
    btnCancel.setAttribute('type', 'button');
    btnCancel.className = 'btn';
    btnCancel.value = "Назад";
    wrapper.appendChild(btnCancel);

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
                        url: 'http://localhost:8080/locations',
                        type: 'GET',
                        dataType: 'json',
                        data: {model:query},
                        error: callback,
                        success:  callback
                        
                    });
                }
         
     });

     modalFooterSubmitBtn.addEventListener('click', function() {
        let arr = new Array();
        let contentRows = $('.contentRow');
         for(i = 0; i < contentRows.length; i++) {
             if($('.contentRow')[i].children[0].children[0].checked) {
                 cartridge = $('.contentRow')[i].children[2].id.split('_')[1];
                 arr.push(cartridge);
             }
         }
         
          $.ajax({
                url: 'http://localhost:8080/editcartridgeslocation',
                type: 'POST',
                data: {idCartridge: arr, location:selectLocation.value},
                success: function () {
                    console.log("локация успешно изменена");
                    window.location.reload();
                },
                error: function(callback) {
                    console.log(callback);
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
};