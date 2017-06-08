$(document).ready(initSearchPage);

function initSearchPage() {
    getNavigationBox();
}

/* --------------------------------- */

function getEntities(entity, generateBlock) {
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
            generateBlock(data);
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function getNavigationBox() {
    getEntities("Brand", generateBrandsBlock);
    getEntities("Category", generateCategoriesBlock);
}


function generateBrandsBlock(brandsJSON) {
    var brands = JSON.parse(brandsJSON).brands;

    var element = '<li><a href="/search?brand=#BRAND_NAME">#BRAND_NAME</a></li>';

    var generatedCode = "";
    brands.forEach(function (brand) {
        generatedCode = generatedCode + element.replace("#BRAND_NAME", brand.name);
    });

    printBrandsBlock(generatedCode);
}

function printBrandsBlock(code) {
    stopSpin();
    var block = $("#navigation-box");
    var currentCode = block.html();

    if (code != null && code != "") {
        currentCode = currentCode.replace("#BRANDS", code);
    }

    block.html(currentCode);
}


function generateCategoriesBlock(categoriesJSON) {
    var categories = JSON.parse(categoriesJSON).categories;

    var element = '<li><a href="/search?category=#CATEGORY_NAME">#CATEGORY_NAME</a></li>';

    var generatedCode = "";
    categories.forEach(function (category) {
        generatedCode = generatedCode + element.replace("#CATEGORY_NAME", category.name);
    });

    printCategoryBlock(generatedCode);
}

function printCategoryBlock(code) {
    stopSpin();
    var block = $("#navigation-box");
    var currentCode = block.html();

    if (code != null && code != "") {
        currentCode = currentCode.replace("#CATEGORIES", code);
    }

    block.html(currentCode);
}