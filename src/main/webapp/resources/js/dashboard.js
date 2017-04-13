'use strict'

function getForm(entity, formType, collectedData) {
    var url = '/entity/'.concat(entity.toLowerCase());

    $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_FORM',
            action: formType,
            collectedData: collectedData
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

function isCorrectId(id) {
    if (id != null) {
        return id != "Not selected";
    } else {
        return false;
    }
}

function getEntityById(id, entity) {
    showSpin();

    var url = window.location.pathname;

    $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITY',
            entity: entity,
            id: id
        },
        success: function(data) {
            return data;
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function getEntities(entity) {
    showSpin();

    var url = window.location.pathname;

    $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITIES',
            entity: entity
        },
        success: function(data) {
            return data;
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function handleDeleteEntity(entity) {
    var data;
    var id = $("#change-".concat(entity.toLowerCase())).val();

    if (isCorrectId(id)) {
        data = {
            entity: entity,
            command: 'delete',

            id: id
        };

        sendRequest(entity, data);
    }
}

/* ---------------- Brand ---------------- */

            /* ---- Forms ----- */

function getBrandAddingForm() {
    getForm("Brand", "add-brand", null);
}

function getBrandEditingForm() {
    var entity = "Brand";
    var id = $("#change-brand").val();
    if (isCorrectId(id)) {
        var collectedData = getEntityById(id, entity);
        getForm(entity, "edit-brand", collectedData);
    }
}

function getBrandChangingForm() {
    var entity = "Brand";
    var collectedData = getEntities(entity);
    getForm(entity, "change-brand", collectedData);
}

            /* ---- Handle ---- */

function handleAddBrand() {
    var data;
    var entity = 'Brand';
    data = {
        entity: entity,
        command: 'add',

        name: $("#add-brand-name").val(),
        description: $("#add-brand-description").val()
    };

    sendRequest(entity, data);
}

function handleEditBrand() {
    var data;
    var entity = 'Brand';
    data = {
        command: entity.toUpperCase(),
        action: 'edit'.concat(entity),

        id: $("#edit-brand-id").val(),
        name: $("#edit-brand-name").val(),
        description: $("#edit-brand-description").val()
    };

    sendRequest(entity, data);
}

function handleDeleteBrand() {
    handleDeleteEntity("Brand");
}

/* ----------------- END ----------------- */


/* -------------- Account --------------- */

            /* ---- Forms ----- */

function getAccountChangingForm() {
    var entity = "Account";
    var collectedData = getEntities(entity);
    getForm(entity, "change-account", collectedData);
}

function getAccountChangeRoleForm() {
    var id = $("#change-account").val();
    if (isCorrectId(id)) {
        var collectedData = {
            id: id,
            roles: getEntities("role")
        };
        getForm("Account", "block-account", collectedData);
    }
}

            /* ---- Handle ---- */

function handleChangeAccountRole() {
    var data;
    var entity = 'Account';

    var roleId = $("#role-id").val();

    if (isCorrectId(roleId)) {
        data = {
            entity: entity,
            command: 'change_role',

            account_id: $("#account-id").val(),
            role_id: roleId
        };
    }

    sendRequest(entity, data);
}

function handleBlockAccount() {
    var data;
    var entity = 'Account';

    var id = $("#change-account").val();

    if (isCorrectId(id)) {
        data = {
            entity: entity,
            command: 'block',

            id: id
        };

        sendRequest(entity, data);
    }
}

function handleDeleteAccount() {
    handleDeleteEntity("Account");
}

/* ----------------- END ----------------- */


/* -------------- Category --------------- */

            /* ---- Forms ----- */

function getCategoryAddingForm() {
    getForm("Category", "add-category", null);
}

function getCategoryEditingForm() {
    var id = $("#change-category").val();
    var entity = "Category";
    if (isCorrectId(id)) {
        var collectedData = getEntityById(id, entity);
        getForm(entity, "edit-category", collectedData);
    }
}

function getCategoryChangingForm() {
    var entity = "Category";
    var collectedData = getEntities(entity);
    getForm(entity, "change-category", collectedData);
}

            /* ---- Handle ---- */

function handleAddCategory() {
    var data;
    var entity = 'Category';
    data = {
        entity: entity,
        command: 'add',

        name: $("#add-category-name").val(),
        description: $("#add-category-description").val()
    };

    sendRequest(entity, data);
}

function handleEditCategory() {
    var data;
    var entity = 'Category';
    data = {
        command: entity.toUpperCase(),
        action: 'edit'.concat(entity),

        id: $("#edit-category-id").val,
        name: $("#edit-category-name").val(),
        description: $("#edit-category-description").val()
    };

    sendRequest(entity, data);
}

function handleDeleteCategory() {
    handleDeleteEntity("Category");
}

/* ----------------- END ----------------- */


/* ---------------- Thing ---------------- */

            /* ---- Forms ----- */

function getThingAddingForm() {
    var collectedData = {
        categories: getEntities("Category"),
        brands: getEntities("Brand")
    };
    getForm("Thing", "add-thing", collectedData);
}

function getThingEditingForm() {
    var id = $("#change-thing").val();
    var entity = "Thing";
    if (isCorrectId(id)) {
        var collectedData = {
            thing: getEntityById(id, entity),
            categories: getEntities("Category"),
            brands: getEntities("Brand")
        };
        getForm(entity, "edit-thing", collectedData);
    }
}

function getThingChangingForm() {
    var entity = "Thing";
    var collectedData = getEntities(entity);
    getForm(entity, "change-thing", collectedData);
}

            /* ---- Handle ---- */

function handleAddThing() {
    var entity = 'Thing';
    var data = {
        entity: entity,
        command: 'add',

        name: $("#add-thing-name").val(),
        category: $("#add-thing-category").val(),
        description: $("#add-thing-description").val(),
        creationDate: $("#add-thing-creation-date").val(),
        brand: $("#add-thing-brand").val()
    };

    sendRequest(entity, data);
}

function handleEditThing() {
    var entity = 'Thing';
    var data = {
        entity: entity,
        command: 'edit',

        name: $("#edit-thing-name").val(),
        category: $("#edit-thing-category").val(),
        description: $("#edit-thing-description").val(),
        creationDate: $("#edit-thing-creation-date").val(),
        brand: $("#edit-thing-brand").val()
    };

    sendRequest(entity, data);
}

function handleDeleteThing() {
    handleDeleteEntity("Thing");
}

/* ----------------- END ----------------- */


/* ---------------- Review --------------- */

            /* ---- Forms ----- */

function getReviewAddingForm() {
    var thingId = $("#change-thing").val();
    if (isCorrectId(thingId)) {
        getForm("Review", "add-review", thingId);
    }
}

function getReviewDeletingForm() {
    var entity = "Review";
    var collectedData = getEntities(entity);
    getForm(entity, "delete-review", collectedData);
}

            /* ---- Handle ---- */

function handleAddReview() {
    var entity = 'Review';
    var data = {
        entity: entity,
        command: 'add',

        thing: $("#add-review-thing").val(),
        text: $("#add-review-text").val()
    };

    sendRequest(entity, data);
}

function handleDeleteReview() {
    handleDeleteEntity("Review");
}

/* ----------------- END ----------------- */


/* ---------------- Photo ---------------- */

            /* ---- Forms ----- */

function getPhotoAddingForm() {
    var thingId = $("#change-thing").val();
    var productId = $("#change-product").val();
    var collectedData = null;
    var entity = "Image";

    if (isCorrectId(thingId)) {
        collectedData = {
            thingId: thingId
        };
        getForm(entity, "add-image", collectedData);
    } else if (isCorrectId(productId)) {
        collectedData = {
            productId: productId
        };
        getForm(entity, "add-image", collectedData);
    }
}

function getPhotoDeletingForm() {
    var images = null;
    var collectedData = null;

    var productId = $("#change-product").val();
    var thingId = $("#change-thing").val();

    if (isCorrectId(productId)) {
        var product = getEntityById(productId, "Product");
        images = product.images;

        collectedData = {
            product: product,
            images: images
        };
        getForm("Image", "delete-image", collectedData);
    } else if (isCorrectId(thingId)) {
        var thing = getEntityById(thingId, "Thing");
        images = thing.images;

        collectedData = {
            thing: thing,
            images: images
        };
        getForm("Image", "delete-image", collectedData);
    }
}

            /* ---- Handle ---- */

function handleAddPhoto() {
    var entity = 'Image';
    var thing = $("#add-image-thing").val();
    var product = $("#add-image-product").val();
    var data = null;

    if (thing != null) {
        data = {
            entity: entity,
            command: 'add',

            thing: thing,
            href: $("#add-image-href").val(),
            realName: $("#add-image-real-name").val()
        };
    } else if (product != null) {
        data = {
            entity: entity,
            command: 'add',

            product: product,
            href: $("#add-image-href").val(),
            realName: $("#add-image-real-name").val()
        };
    }

    sendRequest(entity, data);
}

function handleDeletePhoto() {
    var entity = 'Image';
    var data = null;

    var imageId = $("#delete-image-image").val();


    if (isCorrectId(imageId)) {
        data = {
            entity: entity,
            command: 'delete',

            id: imageId
        };

        sendRequest(entity, data);
    }
}

/* ----------------- END ----------------- */


/* --------------- Product --------------- */

            /* ---- Forms ----- */

function getProductAddingForm() {
    var collectedData = {
        categories: getEntities("Category"),
        brands: getEntities("Brand"),
        discounts: getEntities("Discount")
    };
    getForm("Product", "add-product", collectedData);
}

function getProductEditingForm() {
    var id = $("#change-product").val();
    var entity = "Product";
    if (isCorrectId(id)) {
        var collectedData = {
            product: getEntityById(id, entity),
            categories: getEntities("Category"),
            brands: getEntities("Brand"),
            discount: getEntities("Discount")
        };
        getForm(entity, "edit-product", collectedData);
    }
}

function getProductChangingForm() {
    var entity = "Product";
    var collectedData = getEntities(entity);
    getForm(entity, "change-product", collectedData);
}

function getThingAddingToProductForm() {
    var collectedData = {
        things: getEntities("Things"),
        products: getEntities("Products")
    };
    getForm("Thing_Product", "add-thing-to-product", collectedData);
}

function getThingDeletingFromProductForm() {
    var id = $("#change-product").val();
    if (isCorrectId(id)) {
        var product = getEntityById(id, "Product");
        var things = product.things;

        var collectedData = {
            product: product,
            things: things
        };
        getForm("Thing_Product", "delete-thing-from-product", collectedData);
    }
}

            /* ---- Handle ---- */

function handleAddProduct() {
    var entity = 'Product';
    var data = {
        entity: entity,
        command: 'add',

        name: $("#add-product-name").val(),
        category: $("#add-product-category").val(),
        price: $("#add-product-price").val(),
        brand: $("#add-product-brand").val()
    };

    sendRequest(entity, data);
}

function handleEditProduct() {
    var entity = 'Product';
    var data = {
        entity: entity,
        command: 'edit',

        name: $("#edit-product-name").val(),
        category: $("#edit-product-category").val(),
        price: $("#edit-product-price").val(),
        brand: $("#edit-product-brand").val()
    };

    sendRequest(entity, data);
}

function handleDeleteProduct() {
    handleDeleteEntity("Product")
}

function handleAddThingToProduct() {
    var entity = 'Thing';
    var productId = $("#add-to-product-product").val();
    var thingId = $("#add-to-product-thing").val();

    if (isCorrectId(productId) && isCorrectId(thingId)) {
        var data = {
            entity: entity,
            command: 'add_to_product',

            product: productId,
            thing: thingId
        };

        sendRequest(entity, data);
    }
}

function handleDeleteThingFromProduct() {
    var entity = 'Thing';
    var thingId = $("#delete-from-product-thing").val();
    if (isCorrectId(thingId)) {
        var data = {
            entity: entity,
            command: 'delete_from_product',

            product: $("#delete-from-product-product").val(),
            thing: thingId
        };

        sendRequest(entity, data);
    }
}

/* ----------------- END ----------------- */


/* --------------- Discount -------------- */

            /* ---- Forms ----- */

function getDiscountAddingForm() {
    getForm("Discount", "add-discount", null);
}

function getDiscountEditingForm() {
    var entity = "Discount";
    var id = $("#change-discount").val();
    if (isCorrectId(id)) {
        var collectedData = getEntityById(id, entity);
        getForm(entity, "edit-discount", collectedData);
    }
}

function getDiscountChangingForm() {
    var entity = "Discount";
    var collectedData = getEntities(entity);
    getForm(entity, "change-discount", collectedData);
}

            /* ---- Handle ---- */

function handleAddDiscount() {
    var entity = 'Discount';
    var data = {
        entity: entity,
        command: 'add',

        value: $("#add-discount-value").val(),
        startDate: $("#add-discount-start-date").val(),
        finishDate: $("#add-discount-finish-date").val()
    };

    sendRequest(entity, data);
}

function handleEditDiscount() {
    var entity = 'Discount';
    var data = {
        entity: entity,
        command: 'edit',

        value: $("#edit-discount-value").val(),
        startDate: $("#edit-discount-start-date").val(),
        finishDate: $("#edit-discount-finish-date").val()
    };

    sendRequest(entity, data);
}

function handleDeleteDiscount() {
    handleDeleteEntity("Discount");
}

/* ----------------- END ----------------- */


/* ---------------- Rating --------------- */

            /* ---- Forms ----- */

function getRatingSettingForm() {
    //TODO: Collected data
    getForm("Rating", "set-rating", null);
}

            /* ---- Handle ---- */

function handleSetRating() {
    //TODO : Get UserId in command
    var thingId = $("#thing-id").val();
    var ratingValue = $("#rating-value").val();

    if (isCorrectId(thingId) && isCorrectRating(ratingValue)) {
        var entity = 'Rating';
        var data = {
            entity: entity,
            command: 'set',

            thing: thingId,
            value: ratingValue
        };

        sendRequest(entity, data);
    }
}

function isCorrectRating(ratingValue) {
    //TODO: Write rating check
    return true;
}

/* ----------------- END ----------------- */


// http://stackoverflow.com/questions/14028959/why-does-jquery-or-a-dom-method-such-as-getelementbyid-not-find-the-element