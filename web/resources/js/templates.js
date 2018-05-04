$(document).ready(() => {
    var drop_menu = $(".drop_menu");
    $('.cn, footer').click(() => {
        if (drop_menu.css('opacity') !== '0')
            drop_menu.animate({marginTop: '-500px', opacity: '0'}, 'slow');
    });
    $('.btn_menu').click(() => {
        if (drop_menu.css('opacity') === '0')
            drop_menu.animate({marginTop: '2px', opacity: '1'}, 'slow');
        else
            drop_menu.animate({marginTop: '-500px', opacity: '0'}, 'slow');
    });
    $('.btn_subir').click(() => {
        $('body, html').animate({scrollTop: 0}, 1000);
    });
});