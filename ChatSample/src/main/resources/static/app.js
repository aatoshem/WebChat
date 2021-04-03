//test
var stompClient = null;
var chatId;
var chatTopicSave;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function renderMessage(message){
    let originatorDisp = '';
    if (message.originator == 'C'){
        originatorDisp = 'Customer';
    }
    else{
        originatorDisp = 'Agent';
    }
    $("#chatMsgTable").append("<tr><td>"+originatorDisp +
        "</td><td>"+message.timestamp.toLocaleString()+"</td><td>"+
        message.message+"</td></tr>")
}

function subscribeToCustomerTopic(chatTopic){
    chatTopicSave = chatTopic;
    var socket = new SockJS('/gs-guide-websocket');
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe(chatTopic, function (rawData) {
            let parsedData = JSON.parse(rawData.body);
            chatId = parsedData.chatId;

            switch(parsedData.messageType){
                case "I":
                    $("#dialog").dialog({
                        width: 500,
                        height: 500
                    });

                    stompClientToAgent = Stomp.over(new SockJS('/gs-guide-websocket'));
                 case "C":
                    renderMessage(parsedData)

                    break;
                case "T":
                     if (stompClient !== null) {
                            stompClient.disconnect();
                     }
                    $("#dialog").dialog().close();
                    break;
            }
        });
    });


}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            let parsedData = JSON.parse(greeting.body);
            showGreeting(parsedData.content);
            subscribeToCustomerTopic(parsedData.chatTopicToCustomer)
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello1/blah", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });


});

function sendMessageToAgent(){
    if ('' ==  $('#messageToAgent').val()){
        return;
    }
     let message = {originator:'C', messageType:'C', timestamp: new Date(), message: $('#messageToAgent').val()};
                    stompClient.send(chatTopicSave,{},
                        JSON.stringify(message));

     $('#messageToAgent').val('')


}