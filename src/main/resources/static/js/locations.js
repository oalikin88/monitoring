/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


window.onload = function () {
    const parent = document.getElementsByClassName('locationsContent')[0];
    let table = document.createElement('table');
    table.id = "table";
    table.className = "table table-striped table-bordered";
    parent.appendChild(table);
   
    let thead = document.createElement('thead');
     table.appendChild(thead);
    let trThead = document.createElement('tr');
    let thThead1 = document.createElement('th');
    
    thThead1.setAttribute('scope', 'col');
    thThead1.innerText = '#';
    let thThead2 = document.createElement('th');
    thThead2.setAttribute('scope', 'col');
    thThead2.innerText = 'Локация';
    thead.appendChild(trThead);
    trThead.appendChild(thThead1);
    trThead.appendChild(thThead2);
    let tbody = document.createElement('tbody');
   
    table.appendChild(tbody);
   
    
    inputSort = Object.values(input).sort();
    for(i = 0; i < inputSort.length; i++) {
          tr = document.createElement('tr');
            tbody.appendChild(tr);
            tdCount = document.createElement('td');
            tdCount.innerText = i + 1;
            tr.appendChild(tdCount);
            
            tdLocation = document.createElement('td');
            tdLocation.setAttribute('id', inputSort[i].id);
            tdLocation.setAttribute('class', 'location');
            tdLocation.innerText = inputSort[i].name;
            tr.appendChild(tdLocation);

    }
    
    buttonsDivRow = document.createElement('div');
    buttonsDivRow.className = 'row';
    
    btnBackDiv = document.createElement('div');
    btnBackDiv.className = 'col';
    
    btnCancel = document.createElement('input');
    btnCancel.setAttribute('type', 'button');
    btnCancel.className = 'btn';
    btnCancel.value = "Назад";
    parent.appendChild(buttonsDivRow);
    buttonsDivRow.appendChild(btnBackDiv);
   
    btnBackDiv.appendChild(btnCancel);
    
    btnCancel.addEventListener('click' , function() {
        history.back();
    });
    
    
    btnAddLocationDiv = document.createElement('div');
    btnAddLocationDiv.className = 'col text-end';
     buttonsDivRow.appendChild(btnAddLocationDiv);
    btnAddLocation = document.createElement('input');
    btnAddLocation.className = 'btn';
    btnAddLocation.setAttribute('type', 'button');
    btnAddLocation.value = 'Добавить';
    
    btnAddLocationDiv.appendChild(btnAddLocation);
    
    
    
    
    
 };