/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


function getDateFromInput(dateFromInput, fullDate) {
    
     parseDate = Date.parse(dateFromInput);
     startDate = new Date(parseDate);
     let dateFormat;
     if(fullDate == true) {
         dateFormat = startDate.toLocaleString('ru');
     } else {
         dateFormat = startDate.toLocaleDateString('ru');
     }
     return dateFormat;
    
}


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
     let utilSubmit = document.querySelector('#utilSubmit');
     let inventarySubmit = document.querySelector('#inventarySubmit');
     let titlePage = document.getElementsByClassName('titleCartridge')[0];
     let startDateContract = document.getElementsByClassName('startDateContract')[0];
     let endDateContract = document.getElementsByClassName('endDateContract')[0];
     let locationBtn = document.getElementById('locationbtn');
     let parent = document.getElementsByClassName('wrapper')[0];
     let contractNumberDiv = document.getElementById('contractNumber');
     
     link = document.createElement('a');
     link.setAttribute('href', '/contract?idContract=' + input.contract);
     link.innerText = input.contractNumber;
     
     contractNumberDiv.appendChild(link);
     
     startDateFormat = getDateFromInput(input.startContract, false);
     endDateFormat = getDateFromInput(input.endContract, false);

     startDateContract.innerHTML = startDateFormat;
     endDateContract.innerHTML = endDateFormat;
     
     
     parseStartDate = Date.parse(input.startContract);
     startDate = new Date(parseStartDate);
     startDateFormat = startDate.toLocaleDateString('ru');
     startDateContract.innerHTML = startDateFormat;
     
     
     parseEndDate = Date.parse(input.endContract);
     endDate = new Date(parseEndDate);
     endDateFormat = endDate.toLocaleDateString('ru');
     endDateContract.innerHTML = endDateFormat;
    
     
     if(input.usePrinter == true) {
         
         dateStartUseRow = document.createElement('div');
         dateStartUseRow.className = 'row mb-3';
         parent.appendChild(dateStartUseRow);
         
         dateStartUseDefinitionDiv = document.createElement('div');
         dateStartUseDefinitionDiv.className = 'col';
         dateStartUseDefinitionDiv.innerHTML = 'Дата начала эксплуатации';
         dateStartUseRow.appendChild(dateStartUseDefinitionDiv);
         
         dateStartUseCart = getDateFromInput(input.dateStartExploitation, true);
         
         dateStartUseValueDiv = document.createElement('div');
         dateStartUseValueDiv.className = 'col';
         dateStartUseValueDiv.innerHTML = dateStartUseCart;
         dateStartUseRow.appendChild(dateStartUseValueDiv);
         
         
         var status = document.querySelector('.status');
         var mes = document.createElement('p');
         var span = document.createElement('span');
         mes.className = 'message';
         mes.id = 'mes_use';
         span.innerHTML = 'Используется';
         status.appendChild(mes);
         mes.appendChild(span);
         locationBtn.disabled = true;
     } else if (input.util == true) {
         
            // Отпечатал страниц
         
         procentResource = (input.count / input.resource) * 100;
         
         countPrintRow = document.createElement('div');
         countPrintRow.className = 'row mb-3';
         parent.appendChild(countPrintRow);
         
         countPrintDefinitionDiv = document.createElement('div');
         countPrintDefinitionDiv.className = 'col';
         countPrintDefinitionDiv.innerHTML = 'Отпечатал страниц';
         countPrintRow.appendChild(countPrintDefinitionDiv);
         
         countPrintValueDiv = document.createElement('div');
         countPrintValueDiv.className = 'col';
         countPrintValueDiv.innerHTML = input.count + " (" + procentResource + "% от заявленного ресурса)";
         countPrintRow.appendChild(countPrintValueDiv);
         
         
         // Дата начала эксплуатации
         
         dateStartUseRow = document.createElement('div');
         dateStartUseRow.className = 'row mb-3';
         parent.appendChild(dateStartUseRow);
         
         dateStartUseDefinitionDiv = document.createElement('div');
         dateStartUseDefinitionDiv.className = 'col';
         dateStartUseDefinitionDiv.innerHTML = 'Дата начала эксплуатации';
         dateStartUseRow.appendChild(dateStartUseDefinitionDiv);
         
         dateStartUseCart = getDateFromInput(input.dateStartExploitation, true);
         
         dateStartUseValueDiv = document.createElement('div');
         dateStartUseValueDiv.className = 'col';
         dateStartUseValueDiv.innerHTML = dateStartUseCart;
         dateStartUseRow.appendChild(dateStartUseValueDiv);
         
         // Дата окончания эксплуатации
         
         dateEndUseRow = document.createElement('div');
         dateEndUseRow.className = 'row mb-3';
         parent.appendChild(dateEndUseRow);
         
         dateEndUseDefinitionDiv = document.createElement('div');
         dateEndUseDefinitionDiv.className = 'col';
         dateEndUseDefinitionDiv.innerHTML = 'Дата окончания эксплуатации';
         dateEndUseRow.appendChild(dateEndUseDefinitionDiv);
         
         dateEndUseCart = getDateFromInput(input.dateEndExploitation, true);
         
         dateEndUseValueDiv = document.createElement('div');
         dateEndUseValueDiv.className = 'col';
         dateEndUseValueDiv.innerHTML = dateEndUseCart;
         dateEndUseRow.appendChild(dateEndUseValueDiv);
         
           // Отработал дней
         
         dayOfWorkRow = document.createElement('div');
         dayOfWorkRow.className = 'row mb-3';
         parent.appendChild(dayOfWorkRow);
         
         dayOfWorkDefinitionDiv = document.createElement('div');
         dayOfWorkDefinitionDiv.className = 'col';
         dayOfWorkDefinitionDiv.innerHTML = 'Отработал дней';
         dayOfWorkRow.appendChild(dayOfWorkDefinitionDiv);
         
         dayOfWork = getNumberOfDays(input.dateStartExploitation, input.dateEndExploitation);
         
         dayOfWorkValueDiv = document.createElement('div');
         dayOfWorkValueDiv.className = 'col';
         dayOfWorkValueDiv.innerHTML = dayOfWork;
         dayOfWorkRow.appendChild(dayOfWorkValueDiv);
         
         
         var status = document.querySelector('.status');
         var mes = document.createElement('p');
         var span = document.createElement('span');
         mes.className = 'message';
         mes.id = 'mes_util';
         span.innerHTML = 'Списан';
         status.appendChild(mes);
         mes.appendChild(span);
         locationBtn.disabled = true;
     } else if(input.util == false && input.usePrinter == false) {
         var status = document.querySelector('.status');
         var mes = document.createElement('p');
         var span = document.createElement('span');
         mes.className = 'message';
         mes.id = 'mes_ready';
         span.innerHTML = 'Готов к установке';
         status.appendChild(mes);
         mes.appendChild(span);
     }
     
     
     
     
         $('.locationSelect').selectize({
                  preload: true,
                  valueField: 'name',
                  labelField: 'name',
                  searchField: "name",
                  load: function (query, callback) {
                    $.ajax({
                        url: '/locations',
                        type: 'GET',
                        dataType: 'json',
                        data: {model:query},
                        error: callback,
                        success: callback
                         });
                }
         
     });
     
     
          locationbtn.addEventListener('click', function() {
        $('#locationSelect')[0].selectize.setValue(Object.entries($('#locationSelect')[0].selectize.options)[0][1].name); 
     });
     
    utilSubmit.addEventListener('click', function() {
         if(utilSelect.value === "true") {
             console.log("true");
            $.ajax({
            type: "POST",
            url: "/utilCartridge",
            data: {id: input.id},
            dataType: "text",
            success: function(data) {
                $('#utilModal').modal('hide');
            }
        });
         } else {
             $('#utilModal').modal('hide');
         }  
            
            
    
        
        
    });
    
    
        locationSubmit.addEventListener('click', function() {
         var div = $('#locationRefresh')[0];
           
            
            
        $.ajax({
            type: "POST",
            url: "/editcartridgelocation",
            data: {id: input.id, location: $('#locationSelect')[0].selectize.getValue()},
            dataType: "text",
            success: function(data) {
                div.innerText = $('#locationSelect')[0].selectize.getValue();
                
                console.log(data);
                
                $('#staticBackdrop').modal('hide');
              
            }
        });
        
        
    });
    
    
    backBtn = document.getElementById('back');
    backBtn.addEventListener('click', function() {
        window.location.href = document.referrer;
    });
});