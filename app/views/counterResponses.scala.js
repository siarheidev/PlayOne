$(function(){

    var WS = window['MozWebSocket'] ? window['MozWebSocket'] : WebSocket;
    var socket = new WS('@routes.ResponseController.newConnections().webSocketURL(request)');

    var writeMessages = function(event){

        $("#counter").html("Responses (" + event.data + ")");

    }

    socket.onmessage = writeMessages;

});