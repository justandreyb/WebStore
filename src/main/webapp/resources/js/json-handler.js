function checkAuthorisation(jsonObj) {
    if (jsonObj.success != null) {
        reloadPage();
    } else {
        printError(jsonObj);
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
            checkAuthorisation(jsonObject);
        }
    } else {
        printDefaultError(block);
    }
}

function reloadPage() {
    location.reload();
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

function printError(jsonObject) {
    var signBlock = $("#signing");
    signBlock.fadeOut(250);
    signBlock.modal('toggle');

    var block = $("#action-block-inner");
    var error = jsonObject.error;
    if (error != null) {
        alert(error);
        reloadPage();
    } else {
        printDefaultError(block);
    }

    var actionBlock = $("#action-block");
    actionBlock.fadeIn(250);
    actionBlock.modal('show');
}

function printDefaultError(block) {
    block = $("#action-block-inner");

    block.html("Something went wrong. Please, try again");
    var actionBlock = $("#action-block");

    actionBlock.fadeIn(250);
    actionBlock.modal('show');
}

function printErrorWithSign(errorMessage) {
    var actionBlock = $("#action-block");

    if (!actionBlock.isShown) {
        actionBlock.fadeIn(300);
    }

    $("#action-block-inner").html(errorMessage);
}