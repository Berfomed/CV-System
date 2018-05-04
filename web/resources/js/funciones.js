var divHeight;
var logo = document.all('logo');
var logo_texto = document.all('logo_texto');
var logo_img = document.all('logo_img');

function establecer_contenido() {
    var obj = document.getElementsByClassName('cont_carrusel')[0];
    if (obj.offsetHeight)
        divHeight = obj.offsetHeight;
    else if (obj.style.pixelHeight)
        divHeight = obj.style.pixelHeight;

    document.getElementById('contenido').style.paddingTop = divHeight + 'px';
    if ($('body').width() < 768)
        $('.art').addClass('order-first');
    else
        $('.art').removeClass('order-first');
    if ($('body').width() < 992) {
        $('#art1').removeClass('scrollflow -slide-right my-5');
        $('#art2').removeClass('scrollflow -slide-left my-5');
        $('#art3').removeClass('scrollflow -slide-right my-5');
    } else {
        $('#art1').addClass('scrollflow -slide-right my-5');
        $('#art2').addClass('scrollflow -slide-left my-5');
        $('#art3').addClass('scrollflow -slide-right my-5');
    }
}

setInterval((e) => {
    if (logo_img.style.opacity === '0') {
        logo_img.style.opacity = '1';
        logo_img.style.transform = 'none';
        logo_texto.style.opacity = '0';
        logo_texto.style.transform = 'translate3d(0, -100%, 0)';
    } else {
        logo_texto.style.opacity = '1';
        logo_texto.style.transform = 'none';
        logo_img.style.opacity = '0';
        logo_img.style.transform = 'translate3d(0, -100%, 0)';
    }
}, 5000);


$(document).ready(() => {
    $('.btn_subir').click(() => {
        $('body, html').animate({
            scrollTop: '0px'
        }, 2000);
    });
    $('.btn_nosotros').click(() => {
        $('body, html').animate({
            scrollTop: (divHeight + 110) + 'px'
        }, 1200);
    });
    $('.btn_i').click((e) => {
        setTimeout(() => {
            $('#modal_registro').modal('show');
        }, 500);
    });
    $('.btn_r').click((e) => {
        setTimeout(() => {
            $('#modal_inicio').modal('show');
        }, 500);
    });
});

window.onload = establecer_contenido;