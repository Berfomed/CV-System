
package edu.cvsystem.clases;


import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnvioCorreos {
    public static void send(String para,String sujeto,String mensaje) throws UnsupportedEncodingException{

final String user="cvsystem2@gmail.com";//cambiará en consecuencia al servidor utilizado
final String pass="compraventa";
String nuevoMensaje="<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Correo de Prueba" + "</h1>" + "<img src='https://lh3.googleusercontent.com/-oO-7N-Wpc3Q/AAAAAAAAAAI/AAAAAAAAAAw/fycSKvFah3c/s125-c-k-no/photo.jpg'/ style=\"float: left;\"><p>" +mensaje+ "<br>\n"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "</p> \n"
                    + "<br>\n"
                    + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad. </p> ";
//1st paso) Obtener el objeto de sesión

Properties props = new Properties();
props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");



Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, pass);
    }
});



//2nd paso)componer message*/configurar el mensaje, destinatario, remitente y la carreta*/
try {
    //Archivos adjuntos
BodyPart texto=new MimeBodyPart();/*configuramos las partes del correo eso se hace con el BodyPart*/
texto.setContent(nuevoMensaje,"text/html");/*el mensaje que se va a cargar, y se configura el set para que reciba etiquetas de html*/
BodyPart adjunto = new MimeBodyPart(); /*es este caso usamos el BodyPart para configurar un posible adjunto*/
adjunto.setDataHandler(new DataHandler(new FileDataSource("d:/cartagena.txt")));
adjunto.setFileName("cartagena.txt");
    
/*aqui cojemos los BodyPart y se crea el correo completo*/
MimeMultipart multiparte=new MimeMultipart();
multiparte.addBodyPart(texto);
//multiparte.addBodyPart(adjunto);

 MimeMessage message = new MimeMessage(session);	/*me permite establecer los encabezados, direcciones a quien le quiero enviar y a quien copiar*/
 message.setFrom(new InternetAddress(user,"CV System"));/*de quien envia el mensaje*/
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(para));/*a quien envio el mensaje Cc CcO */
 message.setSubject(sujeto);
 message.setContent(multiparte,"text/html; charset=utf-8");

 //3rd paso)send message
 Transport.send(message);

 System.out.println("Done");

 } catch (MessagingException e) {
	throw new RuntimeException(e);
 }
	
}
    
}
