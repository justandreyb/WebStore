$(document).ready(initWelcomePage());

function initWelcomePage() {
    getTopProducts();
    getNavigationBox();
}

function getTopProducts() {
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
            var temp = $("#topProducts").html();
            generateTopProductsBlock(temp, data);
        },
        error: function() {
            var errorMessage = "Something went wrong.. Try again";
            handleError(errorMessage);
        }
    });
}

function analyzeTopProducts(products) {
    var topProducts = [];
    var minimalRating = 90;
    var maxAmount = 5;
    var index = 0;

    products.forEach(function (product) {
        var things = product.things;

        var idx = 0;
        var value = 0;
        things.forEach(function (thing) {
            value = value + thing.rating;
            idx++;
        });
        if (idx > 0) {
            value = value / idx;
        }

        if (value >= minimalRating && index < maxAmount) {
            topProducts.push(product);
            index++;
        }
    });

    return topProducts;
}

function generateTopProductsBlock(code, collectedData) {
    var products = JSON.parse(collectedData).products;

    products = analyzeTopProducts(products);

    var elemsNav = '<ol class="carousel-indicators">#ELEMS</ol>';
    var elemNav = '<li data-target="#topProducts" data-slide-to="#NAV_IDX" #ACTIVE></li>';
    var emptyElemNav = '<li data-target="#topProducts" data-slide-to="0" class="active"></li>';
    var generatedElemNavCode = "";

    var slides = '<div class="carousel-inner" role="listbox">#SLIDES</div>'
    var slide = '<div class="#ITEM">#SLIDE_IMG<div class="carousel-caption">#PRODUCT</div></div>';
    var slideImg = '<img src="#SRC_IMG" alt="Image">';
    var slideProduct = '<h3>#PROD</h3><p>#PROD_PRICE</p>';
    var productLink = '<a href="/product?command=show&entity=product&id=#PROD_ID">#PROD_NAME</a>';
    var emptySlide = '<div class="item active"><img src="https://placehold.it/2560x300?text=No photo" alt="Image"><div class="carousel-caption"><h3>Here is nothing to show</h3></div></div>';
    var generatedSlidesCode = "";

    var idx = 0;
    products.forEach(function(product) {
        var currElemNav = "";
        if (idx == 0) {
            currElemNav = elemNav.replace('#ACTIVE', 'class="active"');
        } else {
            currElemNav = elemNav.replace('#ACTIVE', '');
        }
        currElemNav = currElemNav.replace('#NAV_IDX', idx.toString());
        generatedElemNavCode = generatedElemNavCode + "\n" + currElemNav;

        var currElement = "";
        if (idx == 0) {
            currElement = slide.replace("#ITEM", "item active");
        } else {
            currElement = slide.replace("#ITEM", "item");
        }
        if (product.photos != null && product.photos.length > 0) {
            currElement = currElement.replace("#SLIDE_IMG", slideImg.replace('#SRC_IMG', product.photos[0]));
        } else {
            currElement = currElement.replace("#SLIDE_IMG", slideImg.replace('#SRC_IMG', 'https://placehold.it/2560x300?text=No photo'));
        }
        var currProd = productLink.replace("#PROD_NAME", product.name);
        currProd = currProd.replace("#PROD_ID", product.id);
        currProd = slideProduct.replace("#PROD", currProd);

        currProd = currProd.replace("#PROD_PRICE", product.price);

        currElement = currElement.replace("#PRODUCT", currProd);
        generatedSlidesCode = generatedSlidesCode + "\n" + currElement;

        idx++;
    });

    if (generatedElemNavCode != "") {
        elemsNav = elemsNav.replace("#ELEMS", generatedElemNavCode);
    } else {
        elemsNav = elemsNav.replace("#ELEMS", emptyElemNav);
    }
    if (generatedSlidesCode != "") {
        slides = slides.replace("#SLIDES", generatedSlidesCode);
    } else {
        slides = slides.replace("#SLIDES", emptySlide);
    }

    code = code.replace("#NAV_ELEMS" , elemsNav);
    code = code.replace("#SLIDES" , slides);
    printTopProducts(code);
}

function printTopProducts(code) {
    stopSpin();
    var block = $("#topProducts");
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