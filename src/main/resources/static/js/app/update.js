var main = {
    init : function () {
        var _this = this;
        $('#btn-update').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title : $('#title').val(),
            content : $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/update/{id}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            location.href='/';
        }).fail(function (error) {
            alert(error);
        });
    }
}

main.init();