$(document).ready(() => {
    // --------------------- Perfil --------------------- //
    $('#btn_cambiar_img').click(() => {
        $('.inFotoPerfil').click();
    });
    $('#icono_info').mouseenter(() => {
        $('#msg_reload').css('opacity', '1');
    });
    $('#icono_info').mouseleave(() => {
        $('#msg_reload').css('opacity', '0');
    });
    $('#nav-tabHistorial .carousel-inner .carousel-item:first-child').toggleClass('active');
    $('.carousel-catalogo .carousel-item:first-child').toggleClass('active');
});