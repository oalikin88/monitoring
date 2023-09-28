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
        let li = $('<li/>').addClass('collection-item avatar');
        $('<i/>').addClass('material-icons circle blue file-to-download-avatar').html('assessment').attr('data-id', uid).appendTo(li);
        $('<span class="title"/>').html(text).appendTo(li);
        let div = $('<div/>').addClass('secondary-content');
        $('<p/>').html('Статус загрузки файла').addClass('file-to-download-status').attr('data-id', uid).appendTo(li);
        $('<a/>').addClass('file-to-download grey-text').attr('data-id', uid).attr('href', '#').append($('<em class="material-icons"/>').html("file_download")).appendTo(div);
        $('<a/>').addClass('delete-file-to-download red-text text-lighten-1')
                .attr('data-id', uid).attr('href', '#')
                .click(() => this._removeFile(uid))
                .append($('<em class="material-icons"/>').html("delete")).appendTo(div);
        div.appendTo(li);
        li.appendTo($(this._containerSelector));
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
        var downloadHyperLink = $('.file-to-download[data-id="' + duid + '"]');
        downloadHyperLink.removeClass('grey-text');
        downloadHyperLink.attr('href', eval(`\`${this._fileDownloadService}\``));
        downloadHyperLink.attr('target', '_blank');
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
            updateStatusValue(UID, 'Документ готов к скачиванию', 'green');
        }
        if (data.notificationStatus === "ERROR") {
            updateStatusValue(UID, 'Ошибка: ' + data.contents.message, 'red');
        }
    }
}