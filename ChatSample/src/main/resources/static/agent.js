var stompClient = null;
var chatTopicSave;
var baseUrl = null;
//function connectAgent(chatId) {
//    var socket = new SockJS('/gs-guide-websocket');
//    stompClient = Stomp.over(socket);
//    stompClient.connect({}, function (frame) {
//        stompClient.subscribe('/topic/MessageToAgent/'+chatId, function (dataItem) {
//            addCustomerData(JSON.parse(dataItem.body))
//        });
//    });
//}
//


function addCustomerMessage(message){

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

function connectAgent() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/newUserRequest', function (dataItem) {
            addData(JSON.parse(dataItem.body))
        });
    });
}

function grab(id){
    $.ajax({
                url: window.location.origin+"/grab/"+id
            }).then(function(data) {
               $("#agentDialog").dialog({
                                            width: 500,
                                            height: 500
                                        });

                chatTopicSave='/topic/MessageToCustomer/'+id;
                stompClient.disconnect();

                var socket = new SockJS('/gs-guide-websocket');
                stompClient = Stomp.over(socket);
                stompClient.connect({}, function (frame) {
                    stompClient.subscribe(chatTopicSave, function (dataItem) {
                        addCustomerMessage(JSON.parse(dataItem.body))
                    });
                });



            });
}

function addData(dataItem) {
    let aTr = $("#chatList").append("<tr><td>"+dataItem.timestamp+"</td><td><a href=# onclick=grab('"+dataItem.chatId+"')>"
                +dataItem.chatId + "</a></td><td>"+dataItem.userName +"</td></tr>")
}
$(function () {

     connectAgent();
     $.ajax({
            url: window.location.origin+"/customerRequests"
        }).then(function(data) {
            for (let i=0; i<data.length; i++){
                addData(data[i]);
            }

        });
});

function sendMessageToCustomer(){
    if ('' ==  $('#messageToCustomer').val()){
        return;
    }
     let message = {originator:'A', messageType:'C', timestamp: new Date(), message: $('#messageToCustomer').val()};
                    stompClient.send(chatTopicSave,{},
                        JSON.stringify(message));
     $('#messageToCustomer').val('')

}