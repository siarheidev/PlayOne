function test (){
    var form = $("#formField").serializeArray();
    var jForm = JSON.stringify(form);

    $.ajax({
        url : 'addfield',
        type: 'POST',
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        mimeType: 'application/json',
        data :  jForm,
        success: function () {
            window.location.href = "/fields";
        },
        failure: function(errorMsg) {
            alert("Ошибка.");
        }
    });

}