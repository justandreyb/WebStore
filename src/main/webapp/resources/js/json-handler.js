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

function handleActionSuccess(data) {
    stopSpin();

    var block = $("#action-block-inner");

    var actionBlock = $("#action-block");

    if (data != null && data != "") {
        actionBlock.fadeOut(500);
        actionBlock.modal('toggle');
        block.html("");
        alert(data);
    } else {
        printDefaultError(block);
    }
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
        actionBlock.fadeIn(500);
        actionBlock.modal('show')
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

}

function printForm(code, collectedData, generatePage) {
    stopSpin();
    var block = $("#action-block-inner");
    block.html("");

    if (code != null && code != "") {
        code = generatePage(code, collectedData);
        block.html(code);
    } else {
        printDefaultError(block);
    }
    var actionBlock = $("#action-block");

    actionBlock.fadeIn(250);
    actionBlock.modal('show');
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
        block.fadeIn(300);
    }

    $("#action-block-inner").innerHTML = errorMessage;
}