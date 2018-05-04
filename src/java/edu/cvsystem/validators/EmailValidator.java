package edu.cvsystem.validators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    Pattern pattern = Pattern.compile("[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            Matcher matcher = pattern.matcher(value.toString());

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cvsystem", "root", "");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Usuarios WHERE correo_electronico = '" + String.valueOf(value) + "'");

            FacesMessage msg;
            if (!matcher.matches()) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debes ingresar un e-mail correcto");
                throw new ValidatorException(msg);
            } else if (rs.next()) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El correo ingresado ya se encuentra registrado");
                throw new ValidatorException(msg);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "¡Oh oh!", "Se ha presentado un error, prueba más tarde"));
        }
    }
}
