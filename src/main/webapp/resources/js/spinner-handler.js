function showSpin() {
    stopSpin();

    var target = document.getElementById('spinner-block');
    var div = document.createElement('div');
    div.id = "spinner-element";
    target.appendChild(div);

    $("#spinner-block").modal('show');
}

function stopSpin() {
    var spinner = $("#spinner-element");
    if (typeof spinner.html() != 'undefined') {
        spinner.remove();
        $("#spinner-block").modal('toggle');
    }
}