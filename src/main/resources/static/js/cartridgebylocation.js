/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

const content = document.querySelector('#content');
window.onload = function() {
    
  let wrapper = document.createElement('div');
  wrapper.className = 'wrapper';
  content.appendChild(wrapper);
  
  let title = document.createElement('h3');
  title.className = "text-center titleLocation fw-bold";
  title.innerText = JSON.parse(Object.keys(input)[0]).name;
  wrapper.appendChild(title);
  
   if(Object.values(input)[0].length > 0) {
      
      headTable = document.createElement('div');
      headTable.className = "row fw-bold";
      wrapper.appendChild(headTable);
      
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
      

      
      for(i = 0; i < Object.values(input)[0].length; i ++) {
      
      contentRow = document.createElement('div');
      contentRow.className = "row contentRow pb-2 mb-2 mt-2 pt-2";
      wrapper.appendChild(contentRow);
      
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
    
    btnCancel.addEventListener('click' , function() {
        history.back();
    });
  
  
    
};