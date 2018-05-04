package edu.cvsystem.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("celularValidator")
public class CelularValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Oh oh!", "Debes ingresar un número valido (10) digitos");
        try {
            Long.parseLong(String.valueOf(o));
            if (String.valueOf(o).length() != 10 || String.valueOf(o).charAt(0) != '3') {
                throw new ValidatorException(msg);
            }
        } catch (NumberFormatException e) {
            throw new ValidatorException(msg);
        }
    }
}
