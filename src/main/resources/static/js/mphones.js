/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */



const btnSaveModelPhone = document.querySelector('#btnSaveModelPhone');

btnSaveModelPhone.addEventListener('click', function() {
    
     let dto = {
            model : document.querySelector('#modelNameInput').value
        };
        let link;
        switch (attribute) {
            case "mphones":
                link = "/mphones/";
                break;
            case "mmonitors":
                link = "/mmonitors/";
                break;
            case "mupsbat":
                link = "/mupsbat/";
                delete dto.model;
                dto.type = document.querySelector('#modelNameInput').value;
                break;
            case "mcpu":
                link = "/mcpu/";
                dto.core = document.querySelector('#coreAmount').value;
                dto.freq = document.querySelector('#freq').value;
                break;
            case "mram":
                link = "/mram/";
                dto.capacity = document.querySelector('#capacity').value;
                break;
            case "mhdd":
                link = "/mhdd/";
                dto.capacity = document.querySelector('#capacity').value;
                dto.unit = document.querySelector('#unit').value;
                dto.serialNumber = document.querySelector('#serialNumber').value;
                dto.inventaryNumber = document.querySelector('#inventaryNumber').value;
                break;
             case "mhdd":
                link = "/mvideo/";
                break;
             case "mcddrive":
                link = "/mcddrive/";
                break;
            case "mscard":
                link = "/mscard/";
                break;
            case "mlcard":
                link = "/mlcard/";
                break;
            case "mkeyboard":
                link = "/mkeyboard/";
                break;
            case "mmouse":
                link = "/mmouse/";
                break;
            case "mspeakers":
                link = "/mspeakers/";
                break;
            case "mscanner":
                link = "/mscanner/";
                break;
            case "os":
                link = "/os/";
                delete dto.model;
                dto.name = document.querySelector('#modelNameInput').value;
                dto.license = $("#licenseFlag")[0].checked;
                break;
            case "mserver":
                link = "/mserver/";
                break;
            case "mswitch":
                link = "/mswitch/";
                break;
            case "mrouter":
                link = "/mrouter/";
                break;
        } 
                $.ajax({
        type: "POST",
        url: link,
        data: JSON.stringify(dto),
        async: false,
        success: function () {
            window.location.reload();
        },
        error: function(callback) {
            if($('#exceptionContainer').length == 0) {
             $('#modalBody').append(callback.responseText);
         } else {
             $('#exceptionContainer').replaceWith(callback.responseText);
         }
            new bootstrap.Modal(document.getElementById('modalError')).show();
  }, 
        processData: false,
        contentType: 'application/json'
    

    });
});