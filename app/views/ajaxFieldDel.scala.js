$(function(){

        $('.delete').bind( 'click', deleteField);

});

function deleteField(){

    var row = $(this).closest("tr");
    var id = row.attr("id");

    var jForm = JSON.stringify(id);

    alert(jForm);

    if (confirm("Del?")) {
        $.ajax({
            url : 'delfield',
            type: 'POST',
            dataType: 'text',
            contentType: 'application/json; charset=utf-8',
            mimeType: 'application/json',
            data :  jForm,
            success: function (data) {
                row.remove();
            },
            failure: function(errorMsg) {
                alert("Error!");
            }
        });
    }




}