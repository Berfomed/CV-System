$(document).ready(() => {
    var btn_guardar = $('#btn_guardar');
    btn_guardar.click(() => {
        if (btn_guardar.text() === 'Editar') {
            btn_guardar.text('Guardar');
            btn_guardar.removeClass('btn-outline-primary');
            btn_guardar.addClass('btn-outline-success');
            $('.capa_input').css('display', 'none');
        } else {
            $('.cmb_guardar').click();
        }
    });
    $('#bstp_close, #btn_cancelar').click(() => {
        if (btn_guardar.text() === 'Guardar') {
            btn_guardar.text('Editar');
            btn_guardar.removeClass('btn-outline-success');
            btn_guardar.addClass('btn-outline-primary');
            $('.capa_input').css('display', 'block');
        }
    });
});