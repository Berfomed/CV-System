package edu.cvsystem.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("edadValidator")
public class EdadValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        try {
            int edad = Integer.parseInt(String.valueOf(o));
            if (edad < 18 || edad > 60) {
                throw new ValidatorException(new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "", "Edad no permitida (18 - 60)"));
            }
        } catch (NumberFormatException e) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "¡Oh oh!", "Se ha presentado un error, prueba más tarde"));
        }
    }
}
