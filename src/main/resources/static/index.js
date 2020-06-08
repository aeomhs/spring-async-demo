
const clientId = document.getElementById("clientId").value;
const roomCode = document.getElementById("roomCode").textContent;

// <------- Room init ------->
(function(){

    const input = document.createElement("input");
    input.type = "text";
    input.id = "result";
    input.disabled = true;

    const button = document.createElement("button");
    button.id = "btn";
    button.innerText = "Submit";
    button.addEventListener('click', ()=>handler(roomCode, clientId));

    document.getElementById("container").append(input, button);
})();

// <------------------------->

const handler = function (roomCode, clientId) {
    document.getElementById("btn").disabled = true;
    const resultBox = document.getElementById("result");
    request(roomCode, clientId).then((value => resultBox.value = value));
};

const request = function (roomCode, clientId) {
    const url = "/"+roomCode+"/submit/"+clientId;
    const option = {
        method: 'POST',
        body: "Async Request!!",
    };
    return fetch(url, option).then((res)=>res.text());
};

