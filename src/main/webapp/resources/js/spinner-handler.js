function showSpin() {

    var target = document.getElementById('action-block');
    var div = document.createElement('div');
    div.id = "spinner-block";
    target.appendChild(div);

    $("#action-block").modal('show');
}

function stopSpin() {
   /*/!* var spinner = Spinner;

    if (spinner != null) {*!/
        var element = document.getElementsByClassName('spinner-block');
        element.parentNode.removeChild(element);
    // }*/
   $("#spinner-block").remove();
}