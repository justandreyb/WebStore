function handleSuccess(data) {
    stopSpin();

    var jsonObject;
    var block = $("#action-block-inner");

    if (data != null && data != "") {
        // jsonObject = JSON.parse(data);
        // if (jsonObject != null) {
        //     printResult(block, jsonObject);
        // } else {
        //     printDefaultError(block);
        // }

        block.html(data);

    } else {
        printDefaultError(block);
    }
    $("#action-block").modal('show');
}

function handleAuthorisationSuccess(data) {
    stopSpin();

    var jsonObject;
    var block = $("#action-block-inner");

    if (data != null && data != "") {
        jsonObject = JSON.parse(data);
        if (jsonObject != null) {
            reloadPage(block, jsonObject);
        } else {
            printDefaultError(block);
        }
    } else {
        printDefaultError(block);
    }
    $("#action-block").modal('show');
}

function reloadPage(block, jsonObject) {
    if (jsonObject.success != null) {
        // location.reload();
        alert("GJ")
    } else {
        printError(block, jsonObject);
    }
}

function printResult(block, jsonObject) {
    if (jsonObject.data != null) {
        block.innerHTML = jsonObject.data;
    } else {
        printError(block, jsonObject);
    }
}

function printError(block, jsonObject) {
    if (jsonObject.error != null) {
        block.innerHTML = jsonObject.error;
    } else {
        printDefaultError(block);
    }
}

function printDefaultError(block) {
    block.innerHTML = "Something went wrong. Please, try again";
}

function printErrorWithSign(errorMessage) {
    var block = $("#action-block");

    if (!block.isShown) {
        block.show();
    }

    $("#action-block-inner").innerHTML = errorMessage;
}