function showSpin() {
    if ($("#spinner-block") != null) {
        stopSpin();
    }

    var target = document.getElementById('action-block');
    var div = document.createElement('div');
    div.id = "spinner-block";
    target.appendChild(div);

    $("#action-block").modal('show');
}

function stopSpin() {
   $("#spinner-block").remove();
}