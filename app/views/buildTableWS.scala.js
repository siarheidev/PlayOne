$(function(){

    var count = $('#tableResponses th').size();
    var arr = new Array();
    for(i = 0; i < count; i++){
        arr[i] = $('#tableResponses').find('th:eq(' + i + ')').text();
    }

    var WS = window['MozWebSocket'] ? window['MozWebSocket'] : WebSocket;
    var socket = new WS('@routes.ResponseController.wsResponses().webSocketURL(request)');

    var writeMessages = function(event){
        var obj = $.parseJSON(event.data);
            var row = '<tr>';
            for(i = 0; i < arr.length; i++){
                if(arr[i] in obj){
                    row = row + '<td>' + obj[arr[i]] + '</td>';
                }else{
                    row = row + '<td>N/A</td>';
                }
            }
            row += '</tr>';
            $('#tableResponses').append(row);
    }

    socket.onmessage = writeMessages;

});