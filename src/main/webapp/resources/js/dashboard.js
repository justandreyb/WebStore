'use strict'

function getForm(entity, formType) {
    showSpin();
    /*
     $('#' + entity.toLowerCase() + '-block').css({
     'hidden': 'false',
     'background-color': '#fff',
     'position': 'relative'
     });
     */
    var url = '/entity/'.concat(entity.toLowerCase()).concat('/').concat(formType);

    $.ajax({
        url: url,
        method: 'GET',
        success: function(data) {
            handleSuccess(data);
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function closeForm(entity, formType) {

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

function simpleAJAXRequest() {
    showSpin();
    $.ajax({
        url: '/dashboard',
        method: 'POST',
        data: {
            command: 'brand',
            value: 'smt'
        },
        success: function (data) {
            handleSuccess(data);
        }
    });
}

/* ---------------- Brand ---------------- */

            /* ---- Forms ----- */

function getBrandAddingForm() {
    getForm("Brand", "add");
}

function getBrandEditingForm() {
    getForm("Brand", "edit");
}

function getBrandDeletingForm() {
    getForm("Brand", "delete");
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


/* -------------- Account --------------- */

            /* ---- Forms ----- */

function getAccountChangingRoleForm() {
    getForm("Account", "changeRole");
}

function getAccountBlockingForm() {
    getForm("Account", "edit");
}

function getAccountDeletingForm() {
    getForm("Account", "delete");
}

            /* ---- Handle ---- */

function handleChangeAccountRole() {
    var data;
    var entity = 'Account';
    data = {
        command: entity.toUpperCase(),
        action: 'change'.concat(entity).concat("Role"),

        name: $("#film-name-add").val(),
    };

    sendRequest(entity, data);
}

function handleBlockAccount() {
    var data;
    var entity = 'Account';
    data = {
        command: entity.toUpperCase(),
        action: 'block'.concat(entity),

        name: $("#film-name-add").val(),
    };

    sendRequest(entity, data);
}

function handleDeleteAccount() {
    var data;
    var entity = 'Account';
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
    getForm("Category", "add");
}

function getCategoryEditingForm() {
    getForm("Category", "edit");
}

function getCategoryDeletingForm() {
    getForm("Category", "delete");
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
    getForm("Thing", "add");
}

function getThingEditingForm() {
    getForm("Thing", "edit");
}

function getThingDeletingForm() {
    getForm("Thing", "delete");
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
    getForm("Review", "add");
}

function getReviewDeletingForm() {
    getForm("Review", "delete");
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
    getForm("Photo", "add");
}

function getPhotoDeletingForm() {
    getForm("Photo", "delete");
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
    getForm("Product", "add");
}

function getProductEditingForm() {
    getForm("Product", "edit");
}

function getProductDeletingForm() {
    getForm("Product", "delete");
}

function getThingAddingToProductForm() {
    getForm("Thing", "addToProduct");
}

function getThingDeletingFromProductForm() {
    getForm("Thing", "deleteFromProduct");
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
    getForm("Discount", "add");
}

function getDiscountEditingForm() {
    getForm("Discount", "edit");
}

function getDiscountDeletingForm() {
    getForm("Discount", "delete");
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
    getForm("Discount", "set");
}

            /* ---- Handle ---- */

function handleSetRating() {

}

/* ----------------- END ----------------- */

function reg1istration() {
    $.ajax({
        url: '/signUp',
        method: 'POST',
        data: {
            email: $("#signUp-email").val(),
            password: $("#signUp-password").val(),
            repeat_password: $("#signUp-password-repeat").val(),
            command: 'REGISTRATION'
        },
        success: function(data) {
            var jsonObject;
            jsonObject = JSON.parse(data);
            if ('redirect' in jsonObject) {
                window.location = jsonObject.redirect;
            } else if ('errorMessage' in jsonObject) {
                $("#signUp-password").val("");
                $("#signUp-password-repeat").val("");
                $("#Err").text(jsonObject.errorMessage);
                $("#error-signUp-message").fadeIn(200);
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


// http://stackoverflow.com/questions/14028959/why-does-jquery-or-a-dom-method-such-as-getelementbyid-not-find-the-element