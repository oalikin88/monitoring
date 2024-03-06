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
    let cartridgeSelectInput = document.querySelector('#cartridgeSelect');
    let locationbtn = document.querySelector('#locationbtn');
    let locationSubmit = document.querySelector('#locationSubmit');
    let serialSubmit = document.querySelector('#serialSubmit');
    let inventarySubmit = document.querySelector('#inventarySubmit');
    let startDateContract = document.getElementsByClassName('startDateContract')[0];
    let endDateContract = document.getElementsByClassName('endDateContract')[0];
    let nameFromOneCSubmit = document.querySelector('#nameFromeOneCSubmit');
    let nameFromOneCBtn = document.querySelector('#nameFromOneCBtn');
    let numberContractDiv = document.getElementById('numberContract');
    let cartridgeSelectOkBtn = document.getElementById('printerInnerCartridgeSubmit');
    let inventaryBtn = document.querySelector('#inventaryBtn');
    let serialBtn = document.querySelector('#serialBtn');
    let printerStatusBtn = document.querySelector('#printerStatusBtn');
    
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
        searchField: ["id", "name"],
        load: function (query, callback) {
            $.ajax({
                url: '/locations',
                type: 'GET',
                async: false,
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
            $('#locationSelect')[0].selectize.setValue( $('#locationSelect')[0].selectize.search(input.locationId).items[0].id);
        }

    });
    
    
    
    printerStatusBtn.addEventListener('click', function() {

        switch (input.printerStatus) {
            case "Исправен":
                $('#optionOk').attr("selected", "true");
                break;
            case "Неисправен":
                $('#optionDefective').attr("selected", "true");
                break;
            case "Ремонт":
                $('#optionRepear').attr("selected", "true");
                break;
            case "Списание":
                $('#optionUtilization').attr("selected", "true");
                break;
            case "Утилизация":
                $('#optionUtilization').attr("selected", "true");
                break;
            case "Снят с учёта":
                $('#optionDelete').attr("selected", "true");
                break;
        }

    });

    printerStatusSubmit = document.querySelector('#printerStatusSubmit');
    
    printerStatusSubmit.addEventListener('click', function() {
        $.ajax({
            type: "POST",
            async:false,
            url: "/changestatus",
            data: {id: input.id, status: $('#printerStatusSelect')[0].value},
            dataType: "text",
            success: function (data) {
                
                $('#printerStatusModal').modal('hide');
                
            }
        });
         window.location.reload();
    });
    
    inventaryBtn.addEventListener('click', function() {
        let inventaryNumberInput = document.querySelector('#inventaryNumberInput');
        inventaryNumberInput.value = document.querySelector('#inventaryRefresh').innerHTML;
        inventaryNumberInput.addEventListener('input', function() {
            
            if(inventaryNumberInput.value == '') {
                inventarySubmit.disabled = true;
            } else {
                inventarySubmit.disabled = false;
            }
            
        });
        
    });

    inventarySubmit.addEventListener('click', function () {
        div = $('#inventaryRefresh')[0];
        $.ajax({
            type: "POST",
            async:false,
            url: "/editprinterinventary",
            data: {id: input.id, inventaryNumber: $('#inventaryNumberInput')[0].value},
            dataType: "text",
            success: function (data) {
                $('#inventarynumberModal').modal('hide');
                div.innerText = $('#inventaryNumberInput')[0].value;
            }
        });
    });
    
    nameFromOneCBtn.addEventListener('click', function() {
        
        if(document.querySelector('#nameFromOneC').innerHTML != 'Отсутствует') {
        document.querySelector('#nameFromOneCInput').value = document.querySelector('#nameFromOneC').innerHTML;
        
    } else {
        nameFromOneCSubmit.disabled = true;
    }
    
        document.querySelector('#nameFromOneCInput').addEventListener('input', function() {
             if(document.querySelector('#nameFromOneCInput').value.length == 0) {
            nameFromOneCSubmit.disabled = true;
        } else {
            nameFromOneCSubmit.disabled = false;
        }
        });
       
    });
 
    nameFromOneCSubmit.addEventListener('click', function() { 
        div = $('#nameFromOneC')[0];
        $.ajax({
            type: "POST",
            async: false,
            url: "/editprinternamefrom1c",
            data: {id: input.id, value: $('#nameFromOneCInput')[0].value},
            dataType: "text",
            success: function (data) {
                div.innerText = $('#nameFromOneCInput')[0].value;
                $('#nameFromOneCModal').modal('hide');
            }
        });
    });

    serialBtn.addEventListener('click', function() {
            let serialInput = document.querySelector('#serialNumberInput');
            serialInput.value = document.querySelector('#serialRefresh').innerHTML;
            serialInput.addEventListener('input', function() {
                if(serialInput.value == '') {
                    serialSubmit.disabled = true;
                } else {
                    serialSubmit.disabled = false;
                }
            });
    });

    serialSubmit.addEventListener('click', function () {
        div = $('#serialRefresh')[0];
        $.ajax({
            type: "POST",
            async: false,
            url: "/editprinterserial",
            data: {id: input.id, serialNumber: $('#serialNumberInput')[0].value},
            dataType: "text",
            success: function (data) {
                div.innerText = $('#serialNumberInput')[0].value;
                $('#serialnumberModal').modal('hide');
            }
        });
    });


    locationSubmit.addEventListener('click', function () {
        var div = $('#locationRefresh')[0];
        $.ajax({
            type: "POST",
            url: "/editprinterlocation",
            data: {id: input.id, location: $('#locationSelect')[0].selectize.getValue()},
            dataType: "text",
            success: function (data) {
                div.innerText = $('#locationSelect')[0].selectize.getValue();
                $('#staticBackdrop').modal('hide');
                window.location.reload();

            }
        });
        $('#locationSelect')[0].selectize.clear();
    });

    let cartridgeUse = document.getElementById("cartridgeUseRefresh");

    if (Object.keys(input.cartridge).length >= 0) {
        for (i = 0; i < input.cartridge.length; i++) {
            if (input.cartridge[i].usePrinter == true) {
                var dateStartParse = Date.parse(input.cartridge[i].dateStartExploitation);
                var dateStartProc = new Date(dateStartParse);
                var dateStartFormat = dateStartProc.toLocaleString('ru');
                var link = document.createElement('a');
                link.setAttribute('href', '/editcartridge?idCartridge=' + input.cartridge[i].id);
                link.innerText = input.cartridge[i].model + " от " + dateStartFormat;
                cartridgeUse.appendChild(link);
            }
        }

        let inputDiv = document.getElementsByClassName('contentInnerDiv1')[0];

        inputEmployeeToDoWork = document.createElement('select');
        inputEmployeeToDoWork.className = 'form-select mt-3';
        inputEmployeeToDoWork.type = 'text';
        inputEmployeeToDoWork.placeholder = 'Выполнил работу';
        inputEmployeeToDoWork.id = 'inputEmployeeToDoWork';
        inputDiv.appendChild(inputEmployeeToDoWork);

        inputEmployeeToSetDevice = document.createElement('select');
        inputEmployeeToSetDevice.className = 'form-select mt-3';
        inputEmployeeToSetDevice.type = 'text';
        inputEmployeeToSetDevice.placeholder = 'Сотрудник, за кем закреплено оборудование';
        inputEmployeeToSetDevice.id = 'inputEmployeeToSetDevice';
        inputDiv.appendChild(inputEmployeeToSetDevice);

        inputEmployeeMOL = document.createElement('select');
        inputEmployeeMOL.className = 'form-select mt-3';
        inputEmployeeMOL.type = 'text';
        inputEmployeeMOL.placeholder = 'Согласовал';
        inputEmployeeMOL.id = 'inputEmployeeMOL';
        inputDiv.appendChild(inputEmployeeMOL);

        if (input.cartridge.length >= 1) {
            inputValue = document.createElement('input');
            inputValue.className = 'form-control mt-3';
            inputValue.type = 'text';
            inputValue.placeholder = 'Счётчик напечатанных страниц';
            inputValue.id = 'countPage';
            inputDiv.appendChild(inputValue);
        }


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

        thTheadDateInstall = document.createElement('th');
        thTheadDateInstall.setAttribute('scope', 'col');
        thTheadDateInstall.innerText = 'Дата установки';


        thTheadCartridgeCountPage = document.createElement('th');
        thTheadCartridgeCountPage.setAttribute('scope', 'col');
        thTheadCartridgeCountPage.innerText = 'Отпечатал страниц';
        
        
        thTheadCartridgeNominal = document.createElement('th');
        thTheadCartridgeNominal.setAttribute('scope', 'col');
        thTheadCartridgeNominal.innerText = 'Номинальный ресурс';

        thTheadCartridgeDayWork = document.createElement('th');
        thTheadCartridgeDayWork.setAttribute('scope', 'col');
        thTheadCartridgeDayWork.innerText = 'Отработал дней';


        thTheadCartridgeEmployeeToDoWork = document.createElement('th');
        thTheadCartridgeEmployeeToDoWork.setAttribute('scope', 'col');
        thTheadCartridgeEmployeeToDoWork.innerText = 'Выполнил работу';

        thTheadCartridgeEmployeeToSetDevice = document.createElement('th');
        thTheadCartridgeEmployeeToSetDevice.setAttribute('scope', 'col');
        thTheadCartridgeEmployeeToSetDevice.innerText = 'Сотрудник, за кем закреплено оборудование';

        thTheadCartridgeEmployeeMOL = document.createElement('th');
        thTheadCartridgeEmployeeMOL.setAttribute('scope', 'col');
        thTheadCartridgeEmployeeMOL.innerText = 'Согласовал';


        thTheadActInstall = document.createElement('th');
        thTheadActInstall.setAttribute('scope', 'col');
        thTheadActInstall.innerText = 'Акт установки';

        theadCartridges.appendChild(trTheadCartridges);
        trTheadCartridges.appendChild(thTheadCartridgesCount);
        trTheadCartridges.appendChild(thTheadCartridgeModel);
        trTheadCartridges.appendChild(thTheadDateInstall);
        trTheadCartridges.appendChild(thTheadCartridgeCountPage);
        trTheadCartridges.appendChild(thTheadCartridgeNominal);
        trTheadCartridges.appendChild(thTheadCartridgeDayWork);
        trTheadCartridges.appendChild(thTheadCartridgeEmployeeToDoWork);
        trTheadCartridges.appendChild(thTheadCartridgeEmployeeToSetDevice);
        trTheadCartridges.appendChild(thTheadCartridgeEmployeeMOL);
        trTheadCartridges.appendChild(thTheadActInstall);

        tbodyCartridge = document.createElement('tbody');
        tableCartridges.appendChild(tbodyCartridge);


        for (i = 0; i < input.cartridge.length; i++) {
           
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
                dateStartContractParse = Date.parse(input.cartridge[i].startContract);
                dateStartContractProc = new Date(dateStartContractParse);
                dateStartContractFormat = dateStartContractProc.toLocaleDateString();
                link.innerText = input.cartridge[i].model + ", контракт № " + input.cartridge[i].contractNumber + " от " +  dateStartContractFormat;
                tdCartridgeModel.appendChild(link);


                tdDateInstall = document.createElement('td');
                tdDateInstall.setAttribute('class', 'dateInstallCart');
                startDateCart = new Date(Date.parse(input.cartridge[i].dateStartExploitation));
                startDateCartFormat = startDateCart.toLocaleDateString('ru');
                tdDateInstall.innerText = startDateCartFormat;
                trCartridge.appendChild(tdDateInstall);
                
                if(input.cartridge[i].usePrinter == false) {
                tdCartridgeCountPage = document.createElement('td');
                tdCartridgeCountPage.setAttribute('class', 'tdCountPage');
                tdCartridgeCountPage.innerText = input.cartridge[i].count;
               
                dateEndParse = Date.parse(input.cartridge[i].dateEndExploitation);
                dateStartParse = Date.parse(input.cartridge[i].dateStartExploitation);

                dayOfWork = getNumberOfDays(dateStartParse, dateEndParse);

                tdCartridgeDayWork = document.createElement('td');
                tdCartridgeDayWork.setAttribute('class', 'tdDayWork');
                tdCartridgeDayWork.innerText = dayOfWork;
                
                
            } else {
                
                tdCartridgeCountPage = document.createElement('td');
                tdCartridgeCountPage.setAttribute('class', 'tdCountPage');
                tdCartridgeCountPage.innerText = "Используется";


                dateEndParse = new Date();
                dateStartParse = Date.parse(input.cartridge[i].dateStartExploitation);

                dayOfWork = getNumberOfDays(dateStartParse, dateEndParse);

                tdCartridgeDayWork = document.createElement('td');
                tdCartridgeDayWork.setAttribute('class', 'tdDayWork');
                tdCartridgeDayWork.innerText = dayOfWork;
                
            }
            
                tdCartridgeNominal = document.createElement('td');
                tdCartridgeNominal.setAttribute('class', 'cartridgeNominal');
                tdCartridgeNominal.innerText = input.cartridge[i].resource;
            
                trCartridge.appendChild(tdCartridgeCountPage);
                trCartridge.appendChild(tdCartridgeNominal);
                trCartridge.appendChild(tdCartridgeDayWork);
            
               

                tdCartridgeEmployeeToDoWork = document.createElement('td');
                tdCartridgeEmployeeToDoWork.setAttribute('class', 'employeeToDoWork');
                tdCartridgeEmployeeToDoWork.innerText = input.cartridge[i].employeeToDoWork;
                trCartridge.appendChild(tdCartridgeEmployeeToDoWork);

                tdCartridgeEmployeeToSetDevice = document.createElement('td');
                tdCartridgeEmployeeToSetDevice.setAttribute('class', 'employeeToSetDevice');
                tdCartridgeEmployeeToSetDevice.innerText = input.cartridge[i].employeeToSetDevice;
                trCartridge.appendChild(tdCartridgeEmployeeToSetDevice);

                tdCartridgeEmployeeMOL = document.createElement('td');
                tdCartridgeEmployeeMOL.setAttribute('class', 'employeeMOL');
                tdCartridgeEmployeeMOL.innerText = input.cartridge[i].employeeMOL;
                trCartridge.appendChild(tdCartridgeEmployeeMOL);

                tdCartridgeActInstall = document.createElement('td');
                tdCartridgeActInstall.setAttribute('class', 'actInstall');
                tdCartridgeActInstall.setAttribute('actId', input.cartridge[i].listenerId);

                linkActInstall = document.createElement('a');
                linkActInstall.setAttribute('href', '#!');

                linkActInstall.innerText = "скачать";


                linkActInstall.onclick = function () {
                    var id = this.parentElement.attributes[1].value;
                    actInstallReport(id);

                };

                tdCartridgeActInstall.appendChild(linkActInstall);
                trCartridge.appendChild(tdCartridgeActInstall);
           
        }

    } else {
        cartridgeUse.innerHTML = "Пусто";
    }

    let cartridgeUseBtn = document.getElementById("catridgeUsesbtn");
    cartridgeUseBtn.addEventListener('click', function () {
        
        cartridgeSelectOkBtn.disabled = true;
        
        employeeToDoWorkSelect = document.querySelector('#inputEmployeeToDoWork');
        employeeToSetDeviceSelect = document.querySelector('#inputEmployeeToSetDevice');
        employeeMOLSelect = document.querySelector('#inputEmployeeMOL');
        selectCartridge = false;
        selectEmpWork = false;
        selectEmpSetDev = false;
        selectMOL = false;
        

        $('#cartridgeSelect').selectize({
            preload: true,
            placeholder: 'Выберите картридж',
            valueField: 'id',
            labelField: "name",
            searchField: "name",

            load: function (query, callback) {
                $.ajax({
                    url: '/showcartridgesforchoice',
                    type: 'GET',
                    dataType: 'json',
                    data: {idPrinter: input.id,
                        locationId: input.locationId},
                    error: callback,
                    success: callback
                });
            },
            onChange: function(val) {
                if(val !== '') {
                    selectCartridge = true;
                    if(selectCartridge && selectEmpWork && selectEmpSetDev && selectMOL) {
                        cartridgeSelectOkBtn.disabled = false;
                    } else {
                        cartridgeSelectOkBtn.disabled = true;
                    }
                } else {
                        cartridgeSelectOkBtn.disabled = true;
                    }
            }

        });

        $('#inputEmployeeToDoWork').selectize({
            preload: true,
            placeholder: 'Выполнил работу',
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
            onChange: function(val) {
                if(val !== '') {
                    selectEmpWork = true;
                    if(selectCartridge && selectEmpWork && selectEmpSetDev && selectMOL) {
                        cartridgeSelectOkBtn.disabled = false;
                    } else {
                        cartridgeSelectOkBtn.disabled = true;
                    }
                } else {
                        cartridgeSelectOkBtn.disabled = true;
                    }
            }

        });

        $('#inputEmployeeToSetDevice').selectize({
            preload: true,
            placeholder: 'Сотрудник, за которым закреплено оборудование',
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
            onChange: function(val) {
                if(val !== '') {
                    selectEmpSetDev = true;
                    if(selectCartridge && selectEmpWork && selectEmpSetDev && selectMOL) {
                        cartridgeSelectOkBtn.disabled = false;
                    } else {
                        cartridgeSelectOkBtn.disabled = true;
                    }
                } else {
                        cartridgeSelectOkBtn.disabled = true;
                    }
            }

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
            onChange: function(val) {
                if(val !== '') {
                    selectMOL = true;
                    if(selectCartridge && selectEmpWork && selectEmpSetDev && selectMOL) {
                        cartridgeSelectOkBtn.disabled = false;
                    } else {
                        cartridgeSelectOkBtn.disabled = true;
                    }
                } else {
                        cartridgeSelectOkBtn.disabled = true;
                    }
            }

        });

        cartridgeSelectOkBtn.addEventListener('click', function () {
            var countPage = 0;
            if ($('#countPage')[0] != null) {
                countPage = $('#countPage')[0].value;
            }
            var emplToDoWork = '';
            var emplToSetDevice = '';
            var emplMOL = '';

            if ($('#inputEmployeeToDoWork')[0].value) {
                emplToDoWork = $('#inputEmployeeToDoWork')[0].value;
            }
            if ($('#inputEmployeeToSetDevice')[0].value) {
                emplToSetDevice = $('#inputEmployeeToSetDevice')[0].value;
            }
            if ($('#inputEmployeeMOL')[0].value) {
                emplMOL = $('#inputEmployeeMOL')[0].value;
            }

            $.ajax({
                type: "POST",
                url: "/installcart",
                data: {idPrinter: input.id,
                    idCartridge: $('#cartridgeSelect')[0].selectize.items[0],
                    count: countPage,
                    employeeToDoWork: emplToDoWork,
                    employeeToSetDevice: emplToSetDevice,
                    employeeMOL: emplMOL},
                success: function () {
                    $('#modalCartridgeUses').modal('hide');
                    window.location.reload();
                }
            });
        });
    });

    let backBtn = document.querySelector('#backBtn');
    backBtn.addEventListener('click', function () {
        location.href = document.referrer;
    });

});

window.onload = function() {
    
    
    setInterval('AJAXPing()', 28000);
    
};

function actInstallReport(input) {
    var fileDownloadManager = new FileDownloadManager({autoOpen: true});

    $.get('/report/act?id=' + input, function (data) {
        fileDownloadManager.createFileBlock(data, 'Акт' + data + '.xlsx');
    });

}
         