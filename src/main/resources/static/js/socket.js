document.addEventListener("DOMContentLoaded", function () {
    WebSocket.init();
});

let WebSocket = (function () {
    const SERVER_SOCKET_API = "/chat";
    const ENTER_KEY = 13;
    let stompClient;
    let chatArea = document.getElementById("chatOutput");
    let chatId = document.getElementById("chatId");
    let inputElm = document.getElementById("chatInput");

    function connect() {
        let socket = new SockJS(SERVER_SOCKET_API);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
            stompClient.subscribe('/topic/roomId', function (msg) {
                printMessage(JSON.parse(msg.body).user,
                    JSON.parse(msg.body).message,
                    JSON.parse(msg.body).datetime);
            });
        });
    }

    function printMessage(user, message, date) {
        var child = '';
        child += '<div class="d-flex w-100 justify-content-between">';
        child += '    <h6 class="mb-1 p-1 font-weight-bold">' + user + '</h6>';
        child += '</div>';
        child += '<p class="mb-1 p-1 rounded bg-warning">' + message + '</p>';
        child += '<small>' + date + '</small>';

        chatArea.innerHTML += child;
        chatArea.scrollTop =  chatArea.scrollHeight;
        chatArea.scroll
    }

    function chatKeyDownHandler(e) {
        if (e.which === ENTER_KEY && inputElm.value.trim() !== "") {
            sendMessage(chatId.value, inputElm.value);
            claer(inputElm);
        }
    }

    function claer(input) {
        input.value = "";
    }

    function sendMessage(user, text) {
        stompClient.send("/app/hello", {}, JSON.stringify({
            'user': user,
            'message': text
        }));
    }

    function init() {
        connect();
        inputElm.addEventListener("keydown", chatKeyDownHandler);
    }

    return {
        init: init
    }
})();