'use strict'

function getForm(entity, formType) {
    showSpin();

    var url = '/entity/'.concat(entity.toLowerCase());

    $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_FORM',
            action: formType
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

/* ---------------- Brand ---------------- */

            /* ---- Forms ----- */

function getBrandAddingForm() {
    getForm("Brand", "add-brand");
}

function getBrandEditingForm() {
    getForm("Brand", "edit-brand");
}

function getBrandChangingForm() {
    getForm("Brand", "change-brand");
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

function getAccountChangingForm() {
    getForm("Account", "change-account");
}

function getAccountBlockingForm() {
    getForm("Account", "block-account");
}

function getAccountChangeRoleForm() {
    getForm("Account", "block-account");
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
    getForm("Category", "add-category");
}

function getCategoryEditingForm() {
    getForm("Category", "edit-category");
}

function getCategoryChangingForm() {
    getForm("Category", "change-category");
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
    getForm("Thing", "add-thing");
}

function getThingEditingForm() {
    getForm("Thing", "edit-thing");
}

function getThingChangingForm() {
    getForm("Thing", "change-thing");
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
    getForm("Review", "add-review");
}

function getReviewDeletingForm() {
    getForm("Review", "delete-review");
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
    getForm("Photo", "add-image");
}

function getPhotoDeletingForm() {
    getForm("Photo", "delete-image");
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
    getForm("Product", "add-product");
}

function getProductEditingForm() {
    getForm("Product", "edit-product");
}

function getProductChangingForm() {
    getForm("Product", "change-product");
}

function getThingAddingToProductForm() {
    getForm("Thing_Product", "add-thing-to-product");
}

function getThingDeletingFromProductForm() {
    getForm("Thing_Product", "delete-thing-from-product");
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
    getForm("Discount", "add-discount");
}

function getDiscountEditingForm() {
    getForm("Discount", "edit-discount");
}

function getDiscountChangingForm() {
    getForm("Discount", "change-discount");
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
    getForm("Discount", "set-rating");
}

            /* ---- Handle ---- */

function handleSetRating() {

}

/* ----------------- END ----------------- */


// http://stackoverflow.com/questions/14028959/why-does-jquery-or-a-dom-method-such-as-getelementbyid-not-find-the-element