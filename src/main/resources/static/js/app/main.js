var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            location.reload();
        }).fail(function (error) {
            console.log(JSON.stringify(data));
            alert(error);
        });
    },

    update : function () {
        var data = {
            title : $('#title').val(),
            content : $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/update/' + $('#id').val(),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            location.href='/';
        }).fail(function (error) {
            console.log(JSON.stringify(data));
            alert(error);
        });
    }

};

main.init();