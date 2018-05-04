package edu.cvsystem.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("contrasenaValidator")
public class ContrasenaValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        HtmlInputSecret input = (HtmlInputSecret) fc.getViewRoot().findComponent("form_contrasena:inp_contrasena2");
        if (input == null) {
            input = (HtmlInputSecret) fc.getViewRoot().findComponent("formulario_registro:inVContrasenaRegistro");
        }
        String con = String.valueOf(o);
        String con2 = String.valueOf(input.getSubmittedValue());

        if (con.length() < 6) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Minimo (6) caracteres"));
        } else if (!con.equals(con2)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Las contraseñas deben coincidir"));
        }
    }
}
