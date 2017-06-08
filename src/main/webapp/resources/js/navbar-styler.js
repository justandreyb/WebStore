var wnd = $(window),
    opacityControl = $(document.getElementsByClassName("navbar navbar-default navbar-fixed-top"));

wnd.scroll(function ( ) {
   // wnd.resize(function() {
        if (wnd.width() >= 768) {
            var top = wnd.scrollTop(),
                opacity = top > 250 ? 1 : top * 2 / 500;

            opacityControl.css({
                'background-color': 'rgba(40, 96, 144, '+ opacity +')'
            });
        } else {
            opacityControl.css({
                'background-color': 'rgba(40, 96, 144, 0.95)'
            });
        }
    // });

});
