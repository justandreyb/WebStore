function signIn() {
    showSpin();

    var uri = window.location.pathname;

    $.ajax({
        url: uri,
        method: 'POST',
        data: {
            entity: "account",
            command: 'SIGN_IN',
            email: $("#login-email").val(),
            password: $("#login-password").val()
        },
        success: function(data) {
            handleAuthorisationSuccess(data, "/welcome");
        },
        error: function () {
            var errorMessage = "Something went wrong while signing in.. Try again";
            printErrorWithSign(errorMessage);
        }
    });
}

function signUp() {
    showSpin();

    var uri = window.location.pathname;

    $.ajax({
        url: uri,
        method: 'POST',
        data: {
            entity: "account",
            command: 'SIGN_UP',
            email: $("#register-email").val(),
            password: $("#register-password").val(),
            firstName: $("#register-first-name").val(),
            lastName: $("#register-last-name").val(),
            language: $("#register-language").val(),
            gender: $("#register-gender").val(),
            address: $("#register-address").val(),
            phone: $("#register-phone").val(),
            locale: $("#register-locale").val()
        },
        success: function (data) {
            handleAuthorisationSuccess(data, "/welcome");
        },
        error: function () {
            var errorMessage = "Something went wrong while signing up.. Try again";
            printErrorWithSign(errorMessage);
        }
    });
}

function signOut() {
    showSpin();

    var uri = window.location.pathname;

    $.ajax({
        url: uri,
        method: 'POST',
        data: {
            entity: "account",
            command: 'SIGN_OUT'
        },
        success: function (data) {
            handleAuthorisationSuccess(data, "/welcome");
        },
        error: function () {
            var errorMessage = "Something went wrong while signing out.. Try again";
            printErrorWithSign(errorMessage);
        }
    });
}