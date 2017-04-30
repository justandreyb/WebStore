'use strict';

function getForm(entity, formType, collectedData, generateForm) {
    var url = '/entity/'.concat(entity.toLowerCase());

    $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_FORM',
            action: formType
        },
        success: function(data) {
            printForm(data, collectedData, generateForm);
            //handleSuccess(data);
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function sendRequest(inputFields) {
    var url = window.location.pathname;
    $.ajax({
        url: url,
        method: 'POST',
        data: inputFields,
        success: function (data) {
            handleActionSuccess(data);
        }
    });
}

function isCorrectId(id) {
    if (id != null) {
        return id != "Not selected" && !id.includes("#");
    } else {
        return false;
    }
}

function getEntityById(id, entity, formType, getForm, generateForm) {
    showSpin();

    var url = window.location.pathname;

    return $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITY',
            entity: entity,
            id: id
        },
        success: function(data) {
            getForm(entity, formType, data, generateForm);
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function getEntities(entity, formType, getForm, generateForm) {
    showSpin();

    var url = window.location.pathname;

    return $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITIES',
            entity: entity
        },
        success: function(data) {
            getForm(entity, formType, data, generateForm);
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

        sendRequest(data);
    }
}

/* ---------------- Brand ---------------- */

            /* ---- Forms ----- */

function getBrandAddingForm() {
    getForm("Brand", "add-brand", null, doNothingWithParam);
}

function getBrandEditingForm() {
    var entity = "Brand";
    var id = $("#change-brand").val();
    if (isCorrectId(id)) {
        getEntityById(id, entity,  "edit-brand", getForm, generateBrandEditingForm);
    }
}

function getBrandChangingForm() {
    getEntities("Brand", "change-brand", getForm, generateBrandChangingForm);
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

    sendRequest(data);
}

function handleEditBrand() {
    var data;
    var entity = 'Brand';
    data = {
        entity: entity,
        command: 'edit',

        id: $("#edit-brand-id").val(),
        name: $("#edit-brand-name").val(),
        description: $("#edit-brand-description").val()
    };

    sendRequest(data);
}

function handleDeleteBrand() {
    handleDeleteEntity("Brand");
}

            /* ---- Generate ---- */

function generateBrandEditingForm(code, collectedData) {
    var brand = JSON.parse(collectedData);

    var currElement = code.replace("#NAME", brand.name);
    currElement = currElement.replace("#ID", brand.id);
    currElement = currElement.replace("#DESCRIPTION", brand.description);

    return currElement;
}

function generateBrandChangingForm(code, collectedData) {
    var element = '<option value="#VALUE">#NAME</option>';

    var brands = JSON.parse(collectedData).brands;
    var generatedCode = "";
    brands.forEach(function(brand) {
        var currElement = element.replace("#NAME", brand.name);
        currElement = currElement.replace("#VALUE", brand.id);
        generatedCode = generatedCode + "\n" + currElement;
    });

    return code.replace("#BRANDS" , generatedCode);
}

/* ----------------- END ----------------- */


/* -------------- Account --------------- */

function getChangeRoleData(id, entity, formType, getForm, generateForm) {
    showSpin();

    var url = window.location.pathname;

    return $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITIES',
            entity: "Role"
        },
        success: function(roles) {
            var collectedData = {
                id: id,
                roles: roles
            };
            getForm(entity, formType, collectedData, generateForm);
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

            /* ---- Forms ----- */

function getAccountChangingForm() {
    getEntities("Account", "change-account", getForm, generateAccountChangingForm);
}

function getAccountChangeRoleForm() {
    var id = $("#change-account").val();
    if (isCorrectId(id)) {
        getChangeRoleData(id, "Account", "change-role", getForm, generateAccountChangeRoleForm);
    }
}

            /* ---- Handle ---- */

function handleChangeAccountRole() {
    var data;
    var entity = 'Account';

    var roleId = $("#change-role-role-id").val();

    if (isCorrectId(roleId)) {
        data = {
            entity: entity,
            command: 'change_role',

            accountId: $("#change-role-id").val(),
            roleId: roleId
        };
    }

    sendRequest(data);
}

function handleBlockAccount() {
    var data;
    var entity = 'Account';

    var id = $("#change-account").val();

    if (isCorrectId(id)) {
        data = {
            entity: entity,
            command: 'block',

            accountId: id
        };

        sendRequest(data);
    }
}

            /* ---- Generate ---- */

function generateAccountChangeRoleForm(code, collectedData) {
    var account = collectedData.id;

    var roles = JSON.parse(collectedData.roles).roles;
    var element = '<option value="#ROLE_ID">#ROLE_VALUE</option>';
    var generatedCode = "";
    roles.forEach(function(role) {
        var currElement = element.replace("#ROLE_VALUE", role.value);
        currElement = currElement.replace("#ROLE_ID", role.id);
        generatedCode = generatedCode + "\n" + currElement;
    });

    var newCode = code.replace("#ID", account);
    newCode = newCode.replace("#ROLES", generatedCode);

    return newCode;
}

function generateAccountChangingForm(code, collectedData) {
    var element = '<option value="#VALUE">#EMAIL | #FIRST_NAME</option>';

    var accounts = JSON.parse(collectedData).accounts;
    var generatedCode = "";
    accounts.forEach(function(account) {
        var currElement = element.replace("#EMAIL", account.email);
        currElement = currElement.replace("#FIRST_NAME", account.firstName);
        currElement = currElement.replace("#VALUE", account.id);
        generatedCode = generatedCode + "\n" + currElement;
    });

    return code.replace("#ACCOUNTS" , generatedCode);
}

/* ----------------- END ----------------- */


/* -------------- Category --------------- */

            /* ---- Forms ----- */

function getCategoryAddingForm() {
    getForm("Category", "add-category", null, doNothingWithParam);
}

function getCategoryEditingForm() {
    var id = $("#change-category").val();
    if (isCorrectId(id)) {
        getEntityById(id, "Category", "edit-category", getForm, generateCategoryEditForm);
    }
}

function getCategoryChangingForm() {
    getEntities("Category", "change-category", getForm, generateCategoryChangingForm);
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

    sendRequest(data);
}

function handleEditCategory() {
    var data;
    var entity = 'Category';
    data = {
        command: 'edit',
        entity: entity,

        categoryId: $("#edit-category-id").val(),
        name: $("#edit-category-name").val(),
        description: $("#edit-category-description").val()
    };

    sendRequest(data);
}

function handleDeleteCategory() {
    handleDeleteEntity("Category");
}

            /* ---- Generate ---- */

function generateCategoryEditForm(code, collectedData) {
    var category = JSON.parse(collectedData);

    var currElement = code.replace("#NAME", category.name);
    currElement = currElement.replace("#DESCRIPTION", category.description);
    currElement = currElement.replace("#ID", category.id);

    return currElement;
}

function generateCategoryChangingForm(code, collectedData) {
    var element = '<option value="#VALUE">#NAME</option>';

    var categories = JSON.parse(collectedData).categories;
    var generatedCode = "";
    categories.forEach(function(category) {
        var currElement = element.replace("#NAME", category.name);
        currElement = currElement.replace("#VALUE", category.id);
        generatedCode = generatedCode + "\n" + currElement;
    });

    return code.replace("#CATEGORIES" , generatedCode);
}

/* ----------------- END ----------------- */


/* ---------------- Thing ---------------- */

function getThingAddingData(entity, formType, generateForm) {
    showSpin();

    var url = window.location.pathname;

    $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITIES',
            entity: "Category"
        },
        success: function(categories) {
            $.ajax({
                url: url,
                method: 'GET',
                data: {
                    command: 'GET_ENTITIES',
                    entity: "Brand"
                },
                success: function (brands) {
                    var collectedData = {
                        categories: categories,
                        brands: brands
                    };
                    getForm(entity, formType, collectedData, generateForm);
                },
                error: function () {
                    var errorMessage = "Something went wrong.. Try again";
                    handleError(errorMessage);
                }
            });
        },
        error: function () {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function getThingEditingData(id, entity, formType, generateForm) {
    showSpin();

    var url = window.location.pathname;

    $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITIES',
            entity: "Category"
        },
        success: function(categories) {
            $.ajax({
                url: url,
                method: 'GET',
                data: {
                    command: 'GET_ENTITIES',
                    entity: "Brand"
                },
                success: function (brands) {
                    $.ajax({
                        url: url,
                        method: 'GET',
                        data: {
                            command: 'GET_ENTITY',
                            entity: "Thing",
                            id: id
                        },
                        success: function (thing) {
                            var collectedData = {
                                categories: categories,
                                brands: brands,
                                thing: thing
                            };
                            getForm(entity, formType, collectedData, generateForm);
                        },
                        error: function () {
                            var errorMessage = "Something went wrong.. Try again";
                            handleError(errorMessage);
                        }
                    });
                },
                error: function () {
                    var errorMessage = "Something went wrong.. Try again";
                    handleError(errorMessage);
                }
            });
        },
        error: function () {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

            /* ---- Forms ----- */

function getThingAddingForm() {
    getThingAddingData("Thing", "add-thing", generateThingAddingForm);
}

function getThingEditingForm() {
    var id = $("#change-thing").val();
    if (isCorrectId(id)) {
        getThingEditingData(id, "Thing", "edit-thing", generateThingEditForm);
    }
}

function getThingChangingForm() {
    var entity = "Thing";
    getEntities(entity, "change-thing", getForm, generateThingChangingForm);
}

            /* ---- Handle ---- */

function handleAddThing() {
    var entity = 'Thing';
    var data = {
        entity: entity,
        command: 'add',

        name: $("#add-thing-name").val(),
        category: $("#add-thing-category-id").val(),
        description: $("#add-thing-description").val(),
        creationDate: $("#add-thing-creation-date").val(),
        brand: $("#add-thing-brand-id").val()
    };

    sendRequest(data);
}

function handleEditThing() {
    var entity = 'Thing';
    var data = {
        entity: entity,
        command: 'edit',

        id: $("#edit-thing-id").val(),
        name: $("#edit-thing-name").val(),
        category: $("#edit-thing-category-id").val(),
        description: $("#edit-thing-description").val(),
        creationDate: $("#edit-thing-creation-date").val(),
        brand: $("#edit-thing-brand-id").val()
    };

    sendRequest(data);
}

function handleDeleteThing() {
    handleDeleteEntity("Thing");
}

                /* ---- Generate ---- */

function generateThingEditForm(code, collectedData) {
    var thing = JSON.parse(collectedData.thing);

    var categories = JSON.parse(collectedData.categories).categories;
    var optionElement = '<option value="#VALUE">#NAME</option>';
    var categoriesCode = "";

    categories.forEach(function (category) {
        var currentElement = "";
        if (category.name == thing.category) {
            currentElement = optionElement.replace("#NAME", category.name);
            currentElement = currentElement.replace("value=", "selected value=");
        } else {
            currentElement = optionElement.replace("#NAME", category.name);
        }
        currentElement = currentElement.replace("#VALUE", category.id);
        categoriesCode += "\n" + currentElement;
    });

    var brands = JSON.parse(collectedData.brands).brands;
    var brandsCode = "";

    brands.forEach(function (brand) {
        var currentElement = "";
        if (brand.name == thing.brand) {
            currentElement = optionElement.replace("#NAME", brand.name);
            currentElement = currentElement.replace("value=", "selected value=");
        } else {
            currentElement = optionElement.replace("#NAME", brand.name);
        }
        currentElement = currentElement.replace("#VALUE", brand.id);
        brandsCode += "\n" + currentElement;
    });

    var newCode = code.replace("#NAME", thing.name);
    newCode = newCode.replace("#ID", thing.id);
    newCode = newCode.replace("#CATEGORIES", categoriesCode);
    newCode = newCode.replace("#DESCRIPTION", thing.description);
    newCode = newCode.replace("#CREATION_DATE", thing.creationDate);
    newCode = newCode.replace("#BRANDS", brandsCode);
    newCode = newCode.replace("#ADDRESS", thing.address);

    return newCode;
}

function generateThingChangingForm(code, collectedData) {
    var element = '<option value="#VALUE">#NAME | #CATEGORY</option>';

    var things = JSON.parse(collectedData).things;
    var generatedCode = "";
    things.forEach(function(thing) {
        var currElement = element.replace("#NAME", thing.name);
        currElement = currElement.replace("#CATEGORY", thing.category);
        currElement = currElement.replace("#VALUE", thing.id);
        generatedCode += "\n" + currElement;
    });

    return code.replace("#THINGS" , generatedCode);
}

function generateThingAddingForm(code, collectedData) {
    var element = '<option value="#VALUE">#NAME</option>';

    var brands = JSON.parse(collectedData.brands).brands;
    var categories = JSON.parse(collectedData.categories).categories;

    var categoriesCode = "";
    categories.forEach(function(category) {
        var currElement = element.replace("#NAME", category.name);
        currElement = currElement.replace("#VALUE", category.id);
        categoriesCode += "\n" + currElement;
    });

    var brandsCode = "";
    brands.forEach(function(brand) {
        var currElement = element.replace("#NAME", brand.name);
        currElement = currElement.replace("#VALUE", brand.id);
        brandsCode += "\n" + currElement;
    });

    code = code.replace("#CATEGORIES", categoriesCode);
    return code.replace("#BRANDS" , brandsCode);
}

/* ----------------- END ----------------- */


/* ---------------- Review --------------- */

            /* ---- Forms ----- */

function getReviewAddingForm() {
    var thingId = $("#change-thing").val();
    if (isCorrectId(thingId)) {
        getForm("Review", "add-review", thingId, generateReviewAddingForm);
    }
}

            /* ---- Handle ---- */

function handleAddReview() {
    var entity = 'Review';
    var data = {
        entity: entity,
        command: 'add',

        thingId: $("#add-review-thing-id").val(),
        text: $("#add-review-text").val()
    };

    sendRequest(data);
}

            /* ---- Generate ---- */

function generateReviewAddingForm(code, collectedData) {
    return code.replace("#THING_ID", collectedData);
}

/* ----------------- END ----------------- */


/* ---------------- Photo ---------------- */

function getPhotoDeletingData(id, entity, formType, generateForm) {
    showSpin();

    var url = window.location.pathname;

    return $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITY',
            entity: entity,
            id: id
        },
        success: function(data) {
            getForm("Image", formType, data, generateForm);
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

            /* ---- Forms ----- */

function getPhotoAddingForm() {
    var thingId = $("#change-thing").val();
    var productId = $("#change-product").val();
    var entity = "Image";

    if (isCorrectId(thingId)) {
        getForm(entity, "add-image", thingId, generatePhotoToThingAddingForm);
    } else if (isCorrectId(productId)) {
        getForm(entity, "add-image", productId, generatePhotoToProductAddingForm);
    }
}

function getPhotoDeletingForm() {
    var productId = $("#change-product").val();
    var thingId = $("#change-thing").val();

    if (isCorrectId(productId)) {
        getPhotoDeletingData(productId, "Product", "delete-image", generatePhotoFormProductDeletingForm);
    } else if (isCorrectId(thingId)) {
        getPhotoDeletingData(thingId, "Thing", "delete-image", generatePhotoFormThingDeletingForm);
    }
}

            /* ---- Handle ---- */

function handleAddPhoto() {
    var entity = 'Photo';
    var thing = $("#add-image-thing-id").val();
    var product = $("#add-image-product-id").val();
    var data = null;

    if (isCorrectId(thing)) {
        data = {
            entity: entity,
            command: 'add',

            thingId: thing,
            href: $("#add-image-href").val(),
            realName: $("#add-image-real-name").val()
        };
    } else if (isCorrectId(product)) {
        data = {
            entity: entity,
            command: 'add',

            productId: product,
            href: $("#add-image-href").val(),
            realName: $("#add-image-real-name").val()
        };
    }

    sendRequest(data);
}

function handleDeletePhoto() {
    var entity = 'Photo';
    var data = null;
    var imageId = $("#delete-image-image-id").val();

    if (isCorrectId(imageId)) {
        data = {
            entity: entity,
            command: 'delete',

            id: imageId,
        };

        sendRequest(data);
    }
}

            /* ---- Generate ---- */

function generatePhotoToProductAddingForm(code, collectedData) {
    return code.replace("#PRODUCT_ID", collectedData);
}

function generatePhotoToThingAddingForm(code, collectedData) {
    return code.replace("#THING_ID", collectedData);
}

function generatePhotoFormProductDeletingForm(code, collectedData) {
    var element = '<option value="#PHOTO_ID">#HREF</option>';
    var photos = JSON.parse(collectedData).photos;
    var generatedCode = "";
    if (photos != null) {
        photos.forEach(function (photo) {
            var currElement = element.replace("#PHOTO_ID", photo.id);
            currElement = currElement.replace("#HREF", photo.href);
            generatedCode = generatedCode + "\n" + currElement;
        });
        code = code.replace("#PHOTOS", generatedCode)
    }

    var productId = JSON.parse(collectedData).id;
    return code.replace("#PRODUCT_ID", productId);
}

function generatePhotoFormThingDeletingForm(code, collectedData) {
    var element = '<option value="#PHOTO_ID">#HREF</option>';
    var photos = JSON.parse(collectedData).photos;
    var generatedCode = "";
    if (photos != null) {
        photos.forEach(function (photo) {
            var currElement = element.replace("#PHOTO_ID", photo.id);
            currElement = currElement.replace("#HREF", photo.href);
            generatedCode = generatedCode + "\n" + currElement;
        });
        code = code.replace("#PHOTOS", generatedCode)
    }

    var thingId = JSON.parse(collectedData).id;
    return code.replace("#THING_ID", thingId);
}

/* ----------------- END ----------------- */


/* --------------- Product --------------- */

function getProductAddingData(entity, formType, generateForm) {
    showSpin();

    var url = window.location.pathname;

    $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITIES',
            entity: "Category"
        },
        success: function(categories) {
            $.ajax({
                url: url,
                method: 'GET',
                data: {
                    command: 'GET_ENTITIES',
                    entity: "Discount"
                },
                success: function (discounts) {
                    var collectedData = {
                        categories: categories,
                        discounts: discounts
                    };
                    getForm(entity, formType, collectedData, generateForm);
                },
                error: function () {
                    var errorMessage = "Something went wrong.. Try again";
                    handleError(errorMessage);
                }
            });
        },
        error: function () {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function getProductEditingData(id, entity, formType, generateForm) {
    showSpin();

    var path = window.location.pathname;

    $.ajax({
        url: path,
        method: 'GET',
        data: {
            command: 'GET_ENTITIES',
            entity: "Category"
        },
        success: function(categories) {
            $.ajax({
                url: path,
                method: 'GET',
                data: {
                    command: 'GET_ENTITIES',
                    entity: "Discount"
                },
                success: function (discounts) {
                    $.ajax({
                        url: path,
                        method: 'GET',
                        data: {
                            command: 'GET_ENTITY',
                            entity: "Product",
                            id: id
                        },
                        success: function (product) {
                            var collectedData = {
                                categories: categories,
                                discounts: discounts,
                                product: product
                            };
                            getForm(entity, formType, collectedData, generateForm);
                        },
                        error: function () {
                            var errorMessage = "Something went wrong.. Try again";
                            handleError(errorMessage);
                        }
                    });
                },
                error: function () {
                    var errorMessage = "Something went wrong.. Try again";
                    handleError(errorMessage);
                }
            });
        },
        error: function () {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function getThingAddingToProductData(id, entity, formType, generateForm) {
    showSpin();

    var url = window.location.pathname;

    $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITIES',
            entity: "Thing"
        },
        success: function (things) {
            $.ajax({
                url: url,
                method: 'GET',
                data: {
                    command: 'GET_ENTITY',
                    entity: "Product",
                    id: id
                },
                success: function (product) {
                    var collectedData = {
                        product: id,
                        things: things,
                        usedThings: JSON.parse(product).things
                    };
                    getForm(entity, formType, collectedData, generateForm);
                },
                error: function () {
                    var errorMessage = "Something went wrong.. Try again";
                    handleError(errorMessage);
                }
            });
        },
        error: function () {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function getThingDeletingFromProductData(id, entity, formType, generateForm) {
    showSpin();

    var url = window.location.pathname;

    $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITY',
            entity: "Product",
            id: id
        },
        success: function (product) {
            var collectedData = {
                product: id,
                things: JSON.parse(product).things
            };
            getForm(entity, formType, collectedData, generateForm);
        },
        error: function () {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });

}

            /* ---- Forms ----- */

function getProductAddingForm() {
    getProductAddingData("Product", "add-product", generateProductAddingForm);
}

function getProductEditingForm() {
    var id = $("#change-product").val();
    if (isCorrectId(id)) {
        getProductEditingData(id, "Product", "edit-product", generateProductEditingForm);
    }
}

function getProductChangingForm() {
    getEntities("Product", "change-product", getForm, generateProductChangingForm);
}

function getThingAddingToProductForm() {
    var id = $("#edit-product-id").val();
    if (isCorrectId(id)) {
        getThingAddingToProductData(id, "Thing_Product", "add-thing-to-product", generateThingAddingToProductForm);
    }
}

function getThingDeletingFromProductForm() {
    var id = $("#edit-product-id").val();
    if (isCorrectId(id)) {
        getThingDeletingFromProductData(id, "Thing_Product", "delete-thing-from-product", generateThingDeletingFromProductForm);
    }
}

            /* ---- Handle ---- */

function handleAddProduct() {
    var entity = 'Product';
    var data = {
        entity: entity,
        command: 'add',

        name: $("#add-product-name").val(),
        category: $("#add-product-category-id").val(),
        price: $("#add-product-price").val(),
        discount: $("#add-product-discount-id").val()
    };

    sendRequest(data);
}

function handleEditProduct() {
    var entity = 'Product';
    var discountId = $("#edit-product-discount-id").val();
    if (discountId != null && discountId == "Not selected") {
        discountId = 0;
    }
    var data = {
        entity: entity,
        command: 'edit',

        id: $("#edit-product-id").val(),
        name: $("#edit-product-name").val(),
        category: $("#edit-product-category-id").val(),
        price: $("#edit-product-price").val(),
        discount: discountId
    };
    sendRequest(data);
}

function handleDeleteProduct() {
    handleDeleteEntity("Product")
}

function handleAddThingToProduct() {
    var entity = 'Thing';
    var productId = $("#add-thing-to-product-product").val();
    var thingId = $("#add-thing-to-product-thing").val();

    if (isCorrectId(productId) && isCorrectId(thingId)) {
        var data = {
            entity: entity,
            command: 'add_to_product',

            product: productId,
            thing: thingId
        };

        sendRequest(data);
    }
}

function handleDeleteThingFromProduct() {
    var entity = 'Thing';
    var thingId = $("#delete-thing-from-product-thing").val();
    if (isCorrectId(thingId)) {
        var data = {
            entity: entity,
            command: 'delete_from_product',

            product: $("#delete-thing-from-product-product").val(),
            thing: thingId
        };

        sendRequest(data);
    }
}

            /* ---- Generate ---- */

function generateProductAddingForm(code, collectedData) {
    var optionElement = '<option value="#VALUE">#NAME</option>';

    var categories = JSON.parse(collectedData.categories).categories;
    var categoriesCode = "";
    categories.forEach(function (category) {
        var currentElement = optionElement.replace("#NAME", category.name);
        currentElement = currentElement.replace("#VALUE", category.id);
        categoriesCode += "\n" + currentElement;
    });

    var discounts = JSON.parse(collectedData.discounts).discounts;
    var discountsCode = "";
    discounts.forEach(function (discount) {
        var currentElement = optionElement.replace("#NAME", discount.value);
        currentElement = currentElement.replace("#VALUE", discount.id);
        discountsCode += "\n" + currentElement;
    });

    code = code.replace("#CATEGORIES", categoriesCode);
    return code.replace("#DISCOUNTS", discountsCode);
}

function generateProductEditingForm(code, collectedData) {
    var product = JSON.parse(collectedData.product);
    code = code.replace("#ID", product.id);
    code = code.replace("#NAME", product.name);

    var optionElement = '<option value="#VALUE">#NAME</option>';

    var categories = JSON.parse(collectedData.categories).categories;
    var categoriesCode = "";
    categories.forEach(function (category) {
        var currentElement = "";
        if (category.name == product.category) {
            currentElement = optionElement.replace("#NAME", category.name);
            currentElement = currentElement.replace("value=", "selected value=");
        } else {
            currentElement = optionElement.replace("#NAME", category.name);
        }
        currentElement = currentElement.replace("#VALUE", category.id);
        categoriesCode = categoriesCode + "\n" + currentElement;
    });

    var discounts = JSON.parse(collectedData.discounts).discounts;
    var discountsCode = "";
    discounts.forEach(function (discount) {
        var currentElement = "";
        if (discount.value == product.discount) {
            currentElement = optionElement.replace("#NAME", discount.value);
            currentElement = currentElement.replace("value=", "selected value=");
        } else {
            currentElement = optionElement.replace("#NAME", discount.value);
        }
        currentElement = currentElement.replace("#VALUE", discount.id);
        discountsCode = discountsCode + "\n" + currentElement;
    });

    code = code.replace("#CATEGORIES", categoriesCode);
    code = code.replace("#DISCOUNTS", discountsCode);
    return code.replace("#PRICE", product.price);
}

function generateProductChangingForm(code, collectedData) {
    var element = '<option value="#VALUE">#NAME | #PRICE</option>';

    var products = JSON.parse(collectedData).products;
    var generatedCode = "";
    products.forEach(function(product) {
        var currElement = element.replace("#NAME", product.name);
        currElement = currElement.replace("#VALUE", product.id);
        currElement = currElement.replace("#PRICE", product.price);
        generatedCode = generatedCode + "\n" + currElement;
    });

    return code.replace("#PRODUCTS" , generatedCode);
}

function generateThingAddingToProductForm(code, collectedData) {
    var element = '<option value="#VALUE">#NAME</option>';

    var things = JSON.parse(collectedData.things).things;
    var usedThings = collectedData.usedThings;

    var showingThings = [];

    var thingsCode = "";
    things.forEach(function(thing) {
        var isUsed = false;
        if (usedThings != null) {
            usedThings.forEach(function (usedThing) {
                if (usedThing.name == thing.name) {
                    isUsed = true;
                }
            });
        }
        if (!isUsed) {
           showingThings.push(thing);
        }
    });

    showingThings.forEach(function(thing) {
        var currElement = element.replace("#NAME", thing.name);
        currElement = currElement.replace("#VALUE", thing.id);
        thingsCode = thingsCode + "\n" + currElement;
    });

    var product = collectedData.product;

    code = code.replace("#PRODUCT_ID", product);
    return code.replace("#THINGS" , thingsCode);
}

function generateThingDeletingFromProductForm(code, collectedData) {
    var productId = collectedData.product;
    code = code.replace("#PRODUCT_ID", productId);

    var things = collectedData.things;

    var generatedCode = "";
    if (things != null) {
        var element = '<option value="#VALUE">#NAME</option>';
        things.forEach(function (things) {
            var currElement = element.replace("#NAME", things.name);
            currElement = currElement.replace("#VALUE", things.id);
            generatedCode = generatedCode + "\n" + currElement;
        });
    }

    return code.replace("#THINGS" , generatedCode);
}

/* ----------------- END ----------------- */


/* --------------- Discount -------------- */

            /* ---- Forms ----- */

function getDiscountAddingForm() {
    getForm("Discount", "add-discount", null, doNothingWithParam);
}

function getDiscountEditingForm() {
    var id = $("#change-discount").val();
    if (isCorrectId(id)) {
        getEntityById(id, "Discount", "edit-discount", getForm, generateDiscountEditForm);
    }
}

function getDiscountChangingForm() {
    getEntities("Discount", "change-discount", getForm, generateDiscountChangingForm);
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

    sendRequest(data);
}

function handleEditDiscount() {
    var entity = 'Discount';
    var data = {
        entity: entity,
        command: 'edit',

        id: $("#edit-discount-id").val(),
        value: $("#edit-discount-value").val(),
        startDate: $("#edit-discount-start-date").val(),
        finishDate: $("#edit-discount-finish-date").val()
    };

    sendRequest(data);
}

function handleDeleteDiscount() {
    handleDeleteEntity("Discount");
}

                /* ---- Generate ---- */

function generateDiscountEditForm(code, collectedData) {
    var discount = JSON.parse(collectedData);
    code = code.replace("#ID", discount.id);
    code = code.replace("#VALUE", discount.value);
    code = code.replace("#START_DATE", discount.startDate);
    return code.replace("#FINISH_DATE", discount.finishDate);
}

function generateDiscountChangingForm(code, collectedData) {
    var element = '<option value="#ID">#VALUE | (#START_DATE - #FINISH_DATE)</option>';

    var discounts = JSON.parse(collectedData).discounts;
    var generatedCode = "";
    discounts.forEach(function(discount) {
        var currElement = element.replace("#VALUE", discount.value);
        currElement = currElement.replace("#ID", discount.id);
        currElement = currElement.replace("#START_DATE", discount.startDate);
        currElement = currElement.replace("#FINISH_DATE", discount.finishDate);
        generatedCode = generatedCode + "\n" + currElement;
    });

    return code.replace("#DISCOUNTS" , generatedCode);
}

/* ----------------- END ----------------- */


/* ---------------- Rating --------------- */

function isCorrectRating(ratingValue) {
    return ratingValue <= 100 && ratingValue > 0;
}

            /* ---- Forms ----- */

function getRatingSettingForm() {
    var thingId = $("#thing-id");
    getForm("Rating", "set-rating", thingId, generateRatingSettingForm);
}

            /* ---- Handle ---- */

function handleSetRating() {
    //TODO : Get UserId in command
    var thingId = $("#set-rating-thing-id").val();
    var ratingValue = $("#set-rating-value").val();

    if (isCorrectId(thingId) && isCorrectRating(ratingValue)) {
        var entity = 'Rating';
        var data = {
            entity: entity,
            command: 'set',

            thing: thingId,
            value: ratingValue
        };

        sendRequest(data);
    }
}

            /* ---- Generate ---- */

function generateRatingSettingForm(code, collectedData) {
    return code.replace("#THING_ID", collectedData);
}

/* ----------------- END ----------------- */

function doNothingWithParam(parameter1, parameter2) {
    return parameter1;
}

// http://stackoverflow.com/questions/14028959/why-does-jquery-or-a-dom-method-such-as-getelementbyid-not-find-the-element