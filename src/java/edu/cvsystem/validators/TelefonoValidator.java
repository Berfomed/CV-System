package edu.cvsystem.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("telefonoValidator")
public class TelefonoValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Oh oh!", "Debes ingresar un número valido (7 digitos)");
        try {
            Long.parseLong(String.valueOf(o));
            if (String.valueOf(o).length() != 7) {
                throw new ValidatorException(msg);
            }
        } catch (NumberFormatException e) {
            throw new ValidatorException(msg);
        }
    }
}
