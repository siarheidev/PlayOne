function doPost (){
        var responseForm = $("#formResponse").serializeArray();
        var responseJson = JSON.stringify(responseForm);

        $.ajax({
                url : 'response',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                mimeType: 'application/json',
                data :  responseJson,
                success: function (data) {
                        alert("OK.");
                },
                failure: function(errorMsg) {
                        alert("Error!");
                }
        });


}

