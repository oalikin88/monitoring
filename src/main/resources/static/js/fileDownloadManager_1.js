class FileDownloadManager {
    _notificationService = 'http://sa04100uitwa243.0041.pfr.ru:8012/notification-service/getStatusByUID/?uid=${uid}';
    _fileDownloadService = 'http://sa04100uitwa243.0041.pfr.ru:8012/file-chest-service/getFile/${filename}?duid=${duid}';
    _containerSelector = '#requestedFiles';
    _failCounter = new Map();
    _stopList = new Map();
    _timerRate = 2000;
    _autoOpen = false;
    constructor(options) {
        this._failCounter = new Map();
        if (options !== undefined) {
            if (options.notificationServiceUrl !== undefined) {
                this._notificationService = options.notificationServiceUrl;
            }
            if (options.fileDownloadServiceUrl !== undefined) {
                this._fileDownloadService = options.fileDownloadServiceUrl;
            }
            this._containerSelector = options.containerSelector === undefined ? '#requestedFiles' : options.containerSelector;
            if (options.autoOpen) {
                this._autoOpen = options.autoOpen;
            }
        }
    }

    createFileBlock(uid, text, additionalFunc) {
        let modalBody = $('#containerInnerModal');
        let btn = $('#btnApplyActsParameters');
        let closeBtnHeader = $('#btnCloseModalHeader');
        closeBtnHeader.addClass("disabled");
        let dateBeginInput = $('#dateBeginInput')[0];
        let dateEndInput = $('#dateEndInput')[0];
        dateEndInput.disabled = true;
        dateBeginInput.disabled = true;
        $('#inputEmployeeMOL')[0].selectize.disable();
        let closeBtnFooter = $('#btnCloseModalFooter');
        closeBtnFooter.addClass("disabled");
        btn.addClass('disabled');
        btn.append($('<span id="spanBreake"> </span>'));
        btn.append($('<span id="spanSpinner" class="spinner-border spinner-border-sm" aria-hidden="true"></span>'));
        modalBody.append($('<div id="inform">Формируется акт</div>'));
        
        this._tick(uid, text, this, additionalFunc);
        if (this._autoOpen) {
            $(this._containerSelector).closest('.modal').modal('open');
        }
    }

    _tick(uidInput, filename, obj, additionalFunc) {
        let uid = uidInput;
        $.get(eval(`\`${obj._notificationService}\``), (data) => {
            obj._parseStatus(uid, data);
            if (data.notificationStatus !== 'RECEIVED') {
                setTimeout(obj._tick, obj._timerRate, uid, filename, obj, additionalFunc);
            } else {
                obj._activateDownload(uid, filename);
                if (additionalFunc !== undefined) {
                    additionalFunc(uid);
                }
            }
        }).fail(() => {
            var counter = obj._failCounter.get(uid) === undefined ? 0 : obj._failCounter.get(uid);
            counter++;
            obj._failCounter.set(uid, counter);
            if (counter <= 10) {
                setTimeout(obj._tick, obj._timerRate, uid, filename, obj, additionalFunc);
            } else {
                $('.file-to-download-status[data-id="' + uid + '"]').html('Потеряна связь с приложением');
            }
        });
    }

    _activateDownload(duid, filename) {
//        window.open('http://sa04100uitwa243.0041.pfr.ru:8012/file-chest-service/getFile/' + filename + '?duid=' + duid);
        var downloadHyperLink = document.createElement('a');
        downloadHyperLink.setAttribute('href', 'http://sa04100uitwa243.0041.pfr.ru:8012/file-chest-service/getFile/' + filename + '?duid=' + duid);
         $('#inform').remove();
        let modalActsParameters = $('#modalActsParameters');
         modalActsParameters.modal('hide');
        let dateBeginInput = $('#dateBeginInput')[0];
        let dateEndInput = $('#dateEndInput')[0];
        dateEndInput.disabled = false;
        dateEndInput.value = '';
        dateBeginInput.disabled = false;
        dateBeginInput.value = '';
        $('#inputEmployeeMOL')[0].selectize.enable();
        $('#inputEmployeeMOL')[0].selectize.setValue();
        let closeBtnHeader = $('#btnCloseModalHeader');
        closeBtnHeader.removeClass('disabled');
        let closeBtnFooter = $('#btnCloseModalFooter');
        closeBtnFooter.removeClass('disabled');
        let btn = $('#btnApplyActsParameters');
        btn.removeClass('disabled');
        $('#spanBreake').empty();
        $('#spanSpinner').remove();
        if(document.querySelector('#infoDate') != null) {
                   $('#infoDate').remove();
               }
       
        downloadHyperLink.click();
       
        
    }

    _removeFile(uid) {
        $("i[data-id='" + uid + "']").closest('li').remove();
        //@TODO  сделать остановку обработки
    }

    _parseStatus(UID, data) {
        if (data.contents === undefined) {
            return;
        }
        if (data.notificationStatus === "SENDED" && data.type === "START" && data.contents.message === undefined) {
            updateStatusValue(UID, 'Документ отправлен на формирование');
        }
        if (data.notificationStatus === "SENDED" && data.type === "START" && data.contents.message !== undefined) {
            updateStatusValue(UID, data.contents.message);
        }
        if (data.type === "PROGRESS") {
            updateStatusValue(UID, 'Обработано ' + data.contents.current + ' записей из ' + data.contents.total + '. Готовность ' + data.contents.percent + '%');
        }
        if (data.notificationStatus === "RECEIVED" && data.type === "DOCUMENT") {
         this._activateDownload(UID, "filename");
        }
        if (data.notificationStatus === "ERROR") {
            updateStatusValue(UID, 'Ошибка: ' + data.contents.message, 'red');
        }
    }
}