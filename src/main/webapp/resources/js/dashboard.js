'use strict'

function handleSuccess(data) {
    var jsonObject;
    jsonObject = JSON.parse(data);
    $("#action-block").val(jsonObject);
}

function handleError(errorMessage) {
    // $("#action-block").val(createErrorBlock(errorMessage));
    alert("Error");
}

function getForm(entity, formType) {
    showSpin();
    $.ajax({
        url: '/'.concat(entity.toLowerCase()),
        method: 'POST',
        data: {
            command: entity.toUpperCase(),
            action: "get".concat(entity).concat(formType),
        },
        success: function(data) {
            handleSuccess(data);
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function sendRequest(entity, inputFields) {
    $.ajax({
        url: '/'.concat(entity.toLowerCase()),
        method: 'POST',
        data: inputFields,
        success: function (data) {
            handleSuccess(data);
        }
    });
}

/* ---------------- Brand ---------------- */

/* ---- Forms ----- */

function getBrandAddingForm() {
    getForm("Brand", "AddingForm");
}

function getBrandEditingForm() {
    getForm("Brand", "EditingForm");
}

function getBrandDeletingForm() {
    getForm("Brand", "DeletingForm");
}

/* ---- Handle ---- */

function handleAddBrand() {
    var data;
    var entity = 'Brand';
    data = {
        command: entity.toUpperCase(),
        action: 'add'.concat(entity),

        name: $("#film-name-add").val(),
    };

    sendRequest(entity, data);
}

function handleEditBrand() {
    var data;
    var entity = 'Brand';
    data = {
        command: entity.toUpperCase(),
        action: 'edit'.concat(entity),

        name: $("#film-name-add").val(),
    };

    sendRequest(entity, data);
}

function handleDeleteBrand() {
    var data;
    var entity = 'Brand';
    data = {
        command: entity.toUpperCase(),
        action: 'delete'.concat(entity),

        name: $("#film-name-add").val(),
    };

    sendRequest(entity, data);
}

/* ----------------- END ----------------- */


/* -------------- Category --------------- */

/* ---- Forms ----- */

function getCategoryAddingForm() {
    getForm("Category", "AddingForm");
}

function getCategoryEditingForm() {
    getForm("Category", "EditingForm");
}

function getCategoryDeletingForm() {
    getForm("Category", "DeletingForm");
}

/* ---- Handle ---- */

function handleAddCategory() {
    var data;
    var entity = 'Category';
    data = {
        command: entity.toUpperCase(),
        action: 'add'.concat(entity),

        name: $("#film-name-add").val(),
    };

    sendRequest(entity, data);
}

function handleEditCategory() {
    var data;
    var entity = 'Category';
    data = {
        command: entity.toUpperCase(),
        action: 'edit'.concat(entity),

        name: $("#film-name-add").val(),
    };

    sendRequest(entity, data);
}

function handleDeleteCategory() {
    var data;
    var entity = 'Category';
    data = {
        command: entity.toUpperCase(),
        action: 'delete'.concat(entity),

        name: $("#film-name-add").val(),
    };

    data.add()

    sendRequest(entity, data);
}

/* ----------------- END ----------------- */


/* ---------------- Thing ---------------- */

/* ---- Forms ----- */

function getThingAddingForm() {
    getForm("Thing", "AddingForm");
}

function getThingEditingForm() {
    getForm("Thing", "EditingForm");
}

function getThingDeletingForm() {
    getForm("Thing", "DeletingForm");
}

/* ---- Handle ---- */

function handleAddThing() {

}

function handleEditThing() {

}

function handleDeleteThing() {

}

/* ----------------- END ----------------- */


/* ---------------- Review --------------- */

/* ---- Forms ----- */

function getReviewAddingForm() {
    getForm("Review", "AddingForm");
}

function getReviewDeletingForm() {
    getForm("Review", "DeletingForm");
}

/* ---- Handle ---- */

function handleAddReview() {

}

function handleDeleteReview() {

}

/* ----------------- END ----------------- */


/* ---------------- Photo ---------------- */

/* ---- Forms ----- */

function getPhotoAddingForm() {
    getForm("Photo", "AddingForm");
}

function getPhotoDeletingForm() {
    getForm("Photo", "DeletingForm");
}

/* ---- Handle ---- */

function handleAddPhoto() {

}

function handleDeletePhoto() {

}

/* ----------------- END ----------------- */


/* --------------- Product --------------- */

/* ---- Forms ----- */

function getProductAddingForm() {
    getForm("Product", "AddingForm");
}

function getProductEditingForm() {
    getForm("Product", "EditingForm");
}

function getProductDeletingForm() {
    getForm("Product", "DeletingForm");
}

function getThingAddingToProductForm() {
    getForm("Thing", "AddingToProductForm");
}

function getThingDeletingFromProductForm() {
    getForm("Thing", "DeletingFromProductForm");
}

/* ---- Handle ---- */

function handleAddProduct() {

}

function handleEditProduct() {

}

function handleDeleteProduct() {

}

function handleAddThingToProduct() {

}

function handleDeleteThingFromProduct() {

}

/* ----------------- END ----------------- */


/* --------------- Discount -------------- */

/* ---- Forms ----- */

function getDiscountAddingForm() {
    getForm("Discount", "AddingForm");
}

function getDiscountEditingForm() {
    getForm("Discount", "EditingForm");
}

function getDiscountDeletingForm() {
    getForm("Discount", "DeletingForm");
}

/* ---- Handle ---- */

function handleAddDiscount() {

}

function handleEditDiscount() {

}

function handleDeleteDiscount() {

}

/* ----------------- END ----------------- */


/* ---------------- Rating --------------- */

/* ---- Forms ----- */

function getRatingSettingForm() {
    getForm("Discount", "SettingForm");
}

/* ---- Handle ---- */

function handleSetRating() {

}

/* ----------------- END ----------------- */

function registration() {
    $.ajax({
        url: '/registration',
        method: 'POST',
        data: {
            email: $("#registration-email").val(),
            password: $("#registration-password").val(),
            repeat_password: $("#registration-password-repeat").val(),
            command: 'REGISTRATION'
        },
        success: function(data) {
            var jsonObject;
            jsonObject = JSON.parse(data);
            if ('redirect' in jsonObject) {
                window.location = jsonObject.redirect;
            } else if ('errorMessage' in jsonObject) {
                $("#registration-password").val("");
                $("#registration-password-repeat").val("");
                $("#Err").text(jsonObject.errorMessage);
                $("#error-registration-message").fadeIn(200);
            }

        }
    });
}

function signIn() {
    $.ajax({
        url: '/main',
        method: 'POST',
        data: {
            email: $("#sign-in-email").val(),
            password: $("#sign-in-password").val(),
            command: 'SIGN_IN'
        },
        success: function(data) {
            var jsonObject;
            jsonObject = JSON.parse(data);
            if ('redirect' in jsonObject) {
                window.location = jsonObject.redirect;
            } else if ('errorMessage' in jsonObject) {
                $("#messageErr").text(jsonObject.errorMessage);
                $("#sign-in-password").val("");
                $("#error-sign-in-message").fadeIn(200);
            }
        }
    });
}

function addFilm() {
    $.ajax({
        url: '/admin',
        method: 'POST',
        data: {
            name: $("#film-name-add").val(),
            release_year: $("#film-year-add").val(),
            average_mark: $("#film-mark-add").val(),
            country: $("#film-country-add").val(),
            description: $("#film-description-add").val(),
            id_age_restriction: $("#film-id-age-restriction-add").val(),
            producer: $("#film-producer-add").val(),
            image: $("#film-image-add").val(),
            trailer: $("#film-trailer-add").val(),
            budget: $("#film-budget-add").val(),
            box_office: $("#film-box-office-add").val(),
            command: 'ADD_PRODUCT'
        },
        success: function (data) {
            var jsonObject;
            jsonObject = JSON.parse(data);
            if ('redirect' in jsonObject) {
                window.location = jsonObject.redirect;
            } else if ('errorMessage' in jsonObject) {
                $("#messageEditErr").text(jsonObject.errorMessage);
                $("#error-edit-message").fadeIn(200);
            }
        }
    });
}

function editFilm() {
    $.ajax({
        url: '/admin',
        method: 'POST',
        data: {
            name: $("#film-name-add").val(),
            release_year: $("#film-year-add").val(),
            average_mark: $("#film-mark-add").val(),
            country: $("#film-country-add").val(),
            description: $("#film-description-add").val(),
            id_age_restriction: $("#film-id-age-restriction-add").val(),
            producer: $("#film-producer-add").val(),
            image: $("#film-image-add").val(),
            trailer: $("#film-trailer-add").val(),
            budget: $("#film-budget-add").val(),
            box_office: $("#film-box-office-add").val(),
            change_id: $("#edit-id").val(),
            command: 'EDIT_PRODUCT'
        },
        success: function (data) {
            var jsonObject;
            jsonObject = JSON.parse(data);
            if ('redirect' in jsonObject) {
                window.location = jsonObject.redirect;
            } else if ('errorMessage' in jsonObject) {
                $("#messageEditErr").text(jsonObject.errorMessage);
                $("#error-edit-message").fadeIn(200);
            }
        }
    });
}

function getBlockHashValue() {
    var value = "&r=";
    value.concat(Math.random());
    return value;
}

function collectFormData() {

}

function showSpin() {
    var opts = {
        lines: 16, // Число линий для рисования
        length: 0, // Длина каждой линии
        width: 10, // Толщина линии
        radius: 30, // Радиус внутреннего круга
        corners: 1, // Скругление углов (0..1)
        rotate: 0, // Смещение вращения
        direction: 1, // 1: по часовой стрелке, -1: против часовой стрелки
        color: '#FF4500', // #rgb или #rrggbb или массив цветов
        speed: 1.2, // Кругов в секунду
        trail: 15, // Послесвечение
        shadow: false, // Тень(true - да; false - нет)
        hwaccel: false, // Аппаратное ускорение
        className: 'spinner', // CSS класс
        zIndex: 2e9, // z-index (по-умолчанию 2000000000)
        top: '50%', // Положение сверху относительно родителя
        left: '50%' // Положение слева относительно родителя
    };
    var target = document.getElementById('action-block');
    var spinner = new Spinner(opts).spin(target);
}

function stopSpin() {
    var target = document.getElementById('action-block');
    target.innerHTML = "Start";
}