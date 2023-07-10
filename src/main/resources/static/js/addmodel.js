/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


const type = document.getElementById('type');
const model = document.getElementById('model');
const modelPrinters = document.getElementById('printer');
const resource = document.getElementById('resource');


$('#sendForm').submit(function(e) {
    e.preventDefault();
    const formData = new FormData();
    formData.append('type', type.value);
    formData.append('model', model.value);
    formData.append('modelPrinters', modelPrinters.selectize.getValue());
    formData.append('resource', resource.value);
    $.ajax({
        url: "http://localhost:8080/addmodelcart/",
        data: formData,
        type: 'POST',
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(data);
            alert("formData успешно отправлена");
        },
        error: function (err) {
            alert(err.responseText);
            console.log(err);
        }
        
        
    });
});


$(document).ready(function () {

    let typeChoice;
    $('.cartridgeTypeSelect').selectize({
        create: false,
        placeholder: "Выберите из списка",
        valueField: 'type',
        labelField: 'type',
        searchField: "type",
        options: [{type: "Оригинальный"},
            {type: "Совместимый"},
            {type: "Стартовый"}],
        onChange: function (value) {
            if (value !== '') {
                selectizeModelFromChoisesTypeCartridge = $(this.$control_input[0].closest('.rowAddModelCartridge')).find('.cartridgeModelSelect')[0];
                typeChoice = $(this.$control_input[0].closest('.rowAddModelCartridge')).find('.cartridgeTypeSelect')[0].innerText;
                selectizeModelFromChoisesTypeCartridge.selectize.enable();
                typeValueFromSelectize = value;
                $.ajax({
                    url: "http://localhost:8080/cartridge/" + encodeURIComponent(value),
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

    // Selectize модель картриджа

    $('.cartridgeModelSelect').selectize({
        create: false,
        placeholder: "Выберите из списка",
        valueField: 'model',
        labelField: 'model',
        searchField: "model",
        preload: 'focus',
        create: true,
        load: function (query, callback) {
            $.ajax({
                url: 'http://localhost:8080/cartridge/' + encodeURIComponent(typeChoice),
                type: 'GET',
                dataType: 'json',
                data: {model: query},
                error: callback,
                success: callback
            });
        }
    });

    $(".printerSelect").selectize({
        plugins: ["remove_button"],
        delimiter: ",",
        valueField: 'model',
            labelField: 'model',
            searchField: 'model',
        persist: false,
         maxItems: null,
        placeholder: "Выберите из списка",
        preload: 'focus',
         load: function (query, callback) {
            $.ajax({
                url: 'http://localhost:8080/models/',
                type: 'GET',
                dataType: 'json',
                error: callback,
                success: callback
                 });
        },
        create: function (input) {
            return {
                value: input,
                text: input,
            };
        }
    });
});

