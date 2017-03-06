var wnd = $(window),
    opacityControl = $(document.getElementsByClassName("masthead clearfix navigation-bar")),
    buttonControl = $(document.getElementById("account-button"));

wnd.scroll(function(){
    var top = wnd.scrollTop(),
        opacity = top > 250 ? 1 : top * 2 / 500;

    opacityControl.css({
        'background-color': 'rgba(40, 96, 144, '+ opacity +')'
    });

    changeColorForButton(top);
});

function changeColorForButton(top) {

    if (top > 50) {
        buttonControl.css({
            'color': 'rgba(255, 255, 255, 0.75)'
        })
    } else {
        buttonControl.css({
            'color': 'rgba(255, 83, 0, 1)'
        })
    }
}
