$(document).ready(initProductsPage());

function initProductsPage() {
    getProducts();
    getNavigationBox();
}

/* ---------------------------- */

function getProducts() {
    showSpin();

    var url = window.location.pathname;

    return $.ajax({
        url: url,
        method: 'GET',
        data: {
            command: 'GET_ENTITIES',
            entity: 'Product'
        },
        success: function(data) {
            var tempProducts = $("#products").html();
            generateProductsPage(tempProducts, data);
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function generateProductsPage(code, collectedData) {
    var products = JSON.parse(collectedData).products;

    var element = '<div class="col-sm-4">#IMG<p>#PROD</p></div>';
    var elemImg = '<img src="#SRC_IMG" class="img-responsive" style="width:100%" alt="Image">';
    var elemLink = '<a href="/product?command=show&entity=product&id=#PROD_ID">#PROD_NAME</a>';

    var generatedCode = "";
    products.forEach(function(product) {
        var currImg = "";
        if (product.photos != null && product.photos.length > 0) {
            currImg = elemImg.replace("#SRC_IMG", product.photos[0].href);
        } else {
            currImg = elemImg.replace("#SRC_IMG", "https://placehold.it/255x135?text=No image");
        }

        var currElement = element.replace("#IMG", currImg);
        var currLink = elemLink.replace("#PROD_ID", product.id);
        currLink = currLink.replace("#PROD_NAME", product.name);
        currElement = currElement.replace("#PROD", currLink);
        generatedCode = generatedCode + "\n" + currElement;
    });
    code = code.replace("#PRODUCTS" , generatedCode);
    printProductsPage(code);
}

function printProductsPage(code) {
    stopSpin();
    var block = $("#products");
    block.html("");

    if (code != null && code != "") {
        block.html(code);
    }
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

    var element = '<li><a href="/search?entity=account&command=search&searchType=brand&searchString=#BRAND_NAME">#BRAND_NAME</a></li>';

    var generatedCode = "";
    brands.forEach(function (brand) {
        generatedCode = generatedCode + element.replace(/#BRAND_NAME/g, brand.name);
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

    var element = '<li><a href="/search?entity=account&command=search&searchType=category&searchString=#CATEGORY_NAME">#CATEGORY_NAME</a></li>';

    var generatedCode = "";
    categories.forEach(function (category) {
        generatedCode = generatedCode + element.replace(/#CATEGORY_NAME/g, category.name);
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