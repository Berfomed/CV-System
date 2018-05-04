package edu.cvsystem.beans;

import edu.cvsystem.clases.EnvioCorreos;
import java.io.UnsupportedEncodingException;
import javax.enterprise.inject.Model;

@Model
public class CorreoMasivo {

    public CorreoMasivo() {
    }
    
    public void enviarCorreos() {
      String[] mails = {"jafp93@hotmail.com" , "msaa1003jm@gmail.com", "ae1msavila1asad3@misena.edu.co"};
      try {
        EnvioCorreos.send("jafp93@hotmail.com", "Titulo", "Hola");
      } catch (UnsupportedEncodingException ex) {
        System.err.println(ex.getMessage());
      }
//      Mailler.enviarCorreo("Alerta", "El servidor se encuentra en mantenimiento", mails);
    }
}
