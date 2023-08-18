/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


function getNumberOfDays(start, end) {
    const date1 = new Date(start);
    const date2 = new Date(end);

    const oneDay = 1000 * 60 * 60 * 24;

    const diffInTime = date2.getTime() - date1.getTime();

    const diffInDays = Math.round(diffInTime / oneDay);

    return diffInDays;
}

 $(document).ready(function () {
     
     let locationbtn = document.querySelector('#locationbtn');
     let locationSubmit = document.querySelector('#locationSubmit');
     let serialSubmit = document.querySelector('#serialSubmit');
     let inventarySubmit = document.querySelector('#inventarySubmit');
     let startDateContract = document.getElementsByClassName('startDateContract')[0];
     let endDateContract = document.getElementsByClassName('endDateContract')[0];
     
     
     let numberContractDiv = document.getElementById('numberContract');
     
     link = document.createElement('a');
     link.setAttribute('href', '/contract?idContract=' + input.contractId);
     link.innerText = input.contractNumber;
     numberContractDiv.appendChild(link);
     
     
     parseStartDate = Date.parse(input.startContract);
     startDate = new Date(parseStartDate);
     startDateFormat = startDate.toLocaleDateString('ru');
     startDateContract.innerHTML = startDateFormat;
     
     
     parseEndDate = Date.parse(input.endContract);
     endDate = new Date(parseEndDate);
     endDateFormat = endDate.toLocaleDateString('ru');
     endDateContract.innerHTML = endDateFormat;
     
     
     $('#locationSelect').selectize({
                  preload: true,
                  valueField: 'name',
                  labelField: 'name',
                  searchField: "name",
                  load: function (query, callback) {
                    $.ajax({
                        url: 'http://localhost:8080/locations',
                        type: 'GET',
                        dataType: 'json',
                        data: {model:query},
                        error: callback,
                        success: callback
                         });
                }
         
     });
     

     
          inventarySubmit.addEventListener('click', function() {
         
         div = $('#inventaryRefresh')[0];
         
         console.log("click");
          $.ajax({
            type: "POST",
            url: "/editprinterinventary",
            data: {id: input.id, inventaryNumber: $('#inventaryNumberInput')[0].value},
            dataType: "text",
            success: function(data) {
                div.innerText = $('#inventaryNumberInput')[0].value;
                
                console.log(data);
                
                $('#inventarynumberModal').modal('hide');
              
            }
        });
     });
     
     
     
     
     serialSubmit.addEventListener('click', function() {
         
         div = $('#serialRefresh')[0];
         
         
          $.ajax({
            type: "POST",
            url: "/editprinterserial",
            data: {id: input.id, serialNumber: $('#serialNumberInput')[0].value},
            dataType: "text",
            success: function(data) {
                div.innerText = $('#serialNumberInput')[0].value;
                
                console.log(data);
                
                $('#serialnumberModal').modal('hide');
              
            }
        });
     });
     
     
     
     locationbtn.addEventListener('click', function() {
        $('#locationSelect')[0].selectize.setValue(Object.entries($('#locationSelect')[0].selectize.options)[0][1].name); 
     });
     
    locationSubmit.addEventListener('click', function() {
         var div = $('#locationRefresh')[0];
           
            
            
        $.ajax({
            type: "POST",
            url: "/editprinterlocation",
            data: {id: input.id, location: $('#locationSelect')[0].selectize.getValue()},
            dataType: "text",
            success: function(data) {
                div.innerText = $('#locationSelect')[0].selectize.getValue();
                
                console.log(data);
                
                $('#staticBackdrop').modal('hide');
              
            }
        });
        
        
    });
    
    let cartridgeUse = document.getElementById("cartridgeUseRefresh");
    
    if(Object.keys(input.cartridge).length > 0) {
        for(i = 0; i < input.cartridge.length; i++) {
            if(input.cartridge[i].usePrinter == true) {
                var dateStartParse = Date.parse(input.cartridge[i].dateStartExploitation);
                var dateStartProc = new Date(dateStartParse);
                var dateStartFormat = dateStartProc.toLocaleString('ru');
                var link = document.createElement('a');
                link.setAttribute('href', '/editcartridge?idCartridge=' + input.cartridge[i].id);
                link.innerText = input.cartridge[i].model + " от " +  dateStartFormat;
                cartridgeUse.appendChild(link);
            }
        }
       
        
        let inputDiv = document.getElementsByClassName('contentInnerDiv1')[0];
        let inputValue = document.createElement('input');
        inputValue.className = 'form-control mt-3';
        inputValue.type = 'text';
        inputValue.placeholder = 'Счётчик напечатанных страниц';
        inputValue.id = 'countPage';
        inputDiv.appendChild(inputValue);
        
        
        if(input.cartridge.length > 1) {
            let contentHistoryCartridgeUse = document.getElementsByClassName('contentInnerTable')[0];
            
            // Заголовок
            let titleHistory = document.createElement('h5');
            titleHistory.className = 'fw-bold text-center mt-3';
            titleHistory.innerHTML = 'История установленных картриджей';
            contentHistoryCartridgeUse.appendChild(titleHistory);
            
            // Таблица с историей использования картриджей
            
             tableCartridges = document.createElement('table');
            tableCartridges.id = "modalTableModelsCartridge";
            tableCartridges.className = "table table-striped table-bordered";
            contentHistoryCartridgeUse.appendChild(tableCartridges);

            theadCartridges = document.createElement('thead');
            tableCartridges.appendChild(theadCartridges);
            
            trTheadCartridges = document.createElement('tr');
            
            thTheadCartridgesCount = document.createElement('th');
            thTheadCartridgesCount.setAttribute('scope', 'col');
            thTheadCartridgesCount.innerText = '#';
            
            thTheadCartridgeModel = document.createElement('th');
            thTheadCartridgeModel.setAttribute('scope', 'col');
            thTheadCartridgeModel.innerText = 'Модель';
            
            thTheadCartridgeCountPage = document.createElement('th');
            thTheadCartridgeCountPage.setAttribute('scope', 'col');
            thTheadCartridgeCountPage.innerText = 'Отпечатал страниц';
            
            thTheadCartridgeDayWork = document.createElement('th');
            thTheadCartridgeDayWork.setAttribute('scope', 'col');
            thTheadCartridgeDayWork.innerText = 'Отработал дней';
            
            theadCartridges.appendChild(trTheadCartridges);
            trTheadCartridges.appendChild(thTheadCartridgesCount);
            trTheadCartridges.appendChild(thTheadCartridgeModel);
            trTheadCartridges.appendChild(thTheadCartridgeCountPage);
            trTheadCartridges.appendChild(thTheadCartridgeDayWork);
            
            tbodyCartridge = document.createElement('tbody');
            tableCartridges.appendChild(tbodyCartridge);
            
             for (i = 0; i < input.cartridge.length; i++) {
                 if(input.cartridge[i].usePrinter == false) {
                        trCartridge = document.createElement('tr');
                        tbodyCartridge.appendChild(trCartridge);
                        tdCountCartridge = document.createElement('td');
                        tdCountCartridge.innerText = i + 1;
                        trCartridge.appendChild(tdCountCartridge);
                        
                        tdCartridgeModel = document.createElement('td');
                        tdCartridgeModel.setAttribute('class', 'model');
                        trCartridge.appendChild(tdCartridgeModel);
                        
                        link = document.createElement('a');
                        link.setAttribute('href', '/editcartridge?idCartridge=' + input.cartridge[i].id);
                        link.innerText = input.cartridge[i].model;
                        tdCartridgeModel.appendChild(link);
                        
                        
                        
                        tdCartridgeCountPage = document.createElement('td');
                        tdCartridgeCountPage.setAttribute('class', 'tdCountPage');
                        tdCartridgeCountPage.innerText = input.cartridge[i].count;
                        trCartridge.appendChild(tdCartridgeCountPage);
                        
                        
                        dateEndParse = Date.parse(input.cartridge[i].dateEndExploitation);
                        dateStartParse = Date.parse(input.cartridge[i].dateStartExploitation);
                        
                        dayOfWork = getNumberOfDays(dateStartParse, dateEndParse);
                        
                        tdCartridgeDayWork = document.createElement('td');
                        tdCartridgeDayWork.setAttribute('class', 'tdCountPage');
                        tdCartridgeDayWork.innerText = dayOfWork;
                        trCartridge.appendChild(tdCartridgeDayWork);
                    }
                }
            
        }
        
        
    } else {
        cartridgeUse.innerHTML = "Пусто";
    }
    
    let cartridgeUseBtn = document.getElementById("catridgeUsesbtn");
     cartridgeUseBtn.addEventListener('click', function() {
         
         
         
         
         
        console.log("click");
         $('#cartridgeSelect').selectize({
                  preload: true,
                  placeholder: 'Выберите картридж',
                  valueField: 'id',
                  labelField: 'model',
                  searchField: "model",
                  
                  load: function (query, callback) {
                    $.ajax({
                        url: 'http://localhost:8080/showcartridgesbymodel',
                        type: 'GET',
                        dataType: 'json',
                        data: { idPrinter: input.id,
                          location: input.location },
                        error: callback,
                        success: callback
                         });
                }
         
     });
         
         
         let cartridgeSelectOkBtn = document.getElementById('printerInnerCartridgeSubmit');
         cartridgeSelectOkBtn.addEventListener('click', function() {
             var countPage = 0;
             if($('#countPage')[0] != null) {
                 countPage = $('#countPage')[0].value;
             }
             
             $.ajax({
            type: "POST",
            url: "/installcart",
            data: { idPrinter: input.id,
                    idCartridge: $('#cartridgeSelect')[0].selectize.items[0],
                    count:  countPage},
            dataType: "json",
            success: function (data) {
               
            }
             });
              $('#modalCartridgeUses').modal('hide');
                window.location.reload();
             
         });
         
         
     });
 });
 
 
