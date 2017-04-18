function handleSuccess(data) {
    stopSpin();

    var block = $("#action-block-inner");

    if (data != null && data != "") {
        printForm(block, data);
    } else {
        printDefaultError(block);
    }
    var actionBlock = $("#action-block");
    if (!actionBlock.modal.isShown) {
        actionBlock.modal('show');
    }
}

function analyzeReturnedJSON(block, jsonObject) {

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
    var actionBlock = $("#action-block");
    if (!actionBlock.modal.isShown) {
        actionBlock.modal('show');
    }
}

function reloadPage(block, jsonObject) {
    if (jsonObject.success != null) {
        // location.reload();
        alert("GJ")
    } else {
        printError(block, jsonObject);
    }
}

function handleError(errorMessage) {
    stopSpin();
    var block = $("#action-block-inner");
    block.html(errorMessage);
}

function printForm(block, data) {
    block.html(data);
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