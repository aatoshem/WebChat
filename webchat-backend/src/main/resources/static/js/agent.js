var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var chatTopicSave;
var baseUrl = null;
var chatId = null;
var agent = null;

function addCustomerMessage(message){
    $("#chatMsgTable").append("<tr><td>"+message.sender +
        "</td><td>" + message.content +"</td></tr>")
}

function connectAgent() {
    var socket = new SockJS('/wsChat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function onConnected(){
    stompClient.subscribe('/topic/customerChats', function (dataItem) {
            addData(JSON.parse(dataItem.body))
        });
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function grab(id){
//    $.ajax({ url: window.location.origin+"/grab/" + id }).then(function(data) {
       $("#agentDialog").dialog({ width: 500, height: 500 });

        chatTopicSave='/topic/private/chat/' + id;
        stompClient.disconnect();
        chatId = id;
        var socket = new SockJS('/wsChat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe(chatTopicSave, function (dataItem) {
                addCustomerMessage(JSON.parse(dataItem.body))
            });
        }, onError);
        // send join message to customer
//    });
}

function addData(dataItem) {
    let aTr = $("#chatList").append("<tr><td>"+dataItem.timestamp+"</td><td><a href=# onclick=grab('"+dataItem.chatId+"')>"
                +dataItem.chatId + "</a></td><td>"+dataItem.sender +"</td></tr>")
}
$(function () {
     connectAgent();
     $.ajax({ url: window.location.origin + "/customerRequests" }).then(function(data) {
        for (let i=0; i<data.length; i++){
            addData(data[i]);
        }
    });
    $.ajax({ url: window.location.origin + "/agent/retrieve" }).then(function(data) {
        agent = data;
    });
});

function sendMessage(event) {
    var messageContent = $('#messageToCustomer').val().trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: agent.agentName,
            content: messageContent,
            type: 'CHAT',
            chatId: chatId
        };
        stompClient.send("/app/chat/sendMessage", {}, JSON.stringify(chatMessage));
        $('#messageToCustomer').val() = '';
    }
    event.preventDefault();
}