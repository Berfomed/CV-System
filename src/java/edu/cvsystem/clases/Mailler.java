package edu.cvsystem.clases;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailler {

    // Enviar correos de solo texto
    public static void enviarCorreo(String titulo, String contenido, String... destinatarios) {
        try {
            Session sesion = Session.getDefaultInstance(getProperties());
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setSubject(titulo);
            mensaje.setText(contenido, "utf-8", "html");
            mensaje.setFrom(new InternetAddress(correo, nombre));
            InternetAddress[] correos = new InternetAddress[destinatarios.length];
            for (int i = 0; i < destinatarios.length; i++) {
                correos[i] = new InternetAddress(destinatarios[i]);
            }
            mensaje.setRecipients(Message.RecipientType.TO, correos);
            Transport t = sesion.getTransport();
            t.connect(correo, contrasena);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
        } catch (UnsupportedEncodingException | MessagingException e) {
            System.err.println(e.getMessage());
        }
    }

    // Enviar correos con adjuntos y los nombres de dichos adjuntos
    public static void enviarCorreo(String titulo, String contenido,
            String[] destinatarios, String[] archivos, String[] nombres) {
        try {
            Session sesion = Session.getDefaultInstance(getProperties());
            MimeBodyPart texto = new MimeBodyPart();
            texto.setText(contenido, "utf-8", "html");
            MimeMultipart mp = new MimeMultipart();
            mp.addBodyPart(texto);
            for (int i = 0; i < archivos.length; i++) {
                MimeBodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(new DataHandler(new FileDataSource(new File(archivos[i]))));
                adjunto.setFileName(nombres[i]);
                mp.addBodyPart(adjunto);
            }
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setSubject(titulo);
            mensaje.setContent(mp);
            mensaje.setFrom(new InternetAddress(correo, nombre));
            InternetAddress[] correos = new InternetAddress[destinatarios.length];
            for (int i = 0; i < destinatarios.length; i++) {
                correos[i] = new InternetAddress(destinatarios[i]);
            }
            mensaje.setRecipients(Message.RecipientType.TO, correos);
            Transport t = sesion.getTransport();
            t.connect(correo, contrasena);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
        } catch (UnsupportedEncodingException | MessagingException e) {
            System.err.println(e.getMessage());
        }
    }

    // Conseguir las propiedades de sesión especificas para gmail
    public static Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        return props;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        Mailler.correo = correo;
    }

    public static String getContrasena() {
        return contrasena;
    }

    public static void setContrasena(String contrasena) {
        Mailler.contrasena = contrasena;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Mailler.nombre = nombre;
    }

    // Valores por defecto
    private static String correo = "cvsystem2@gmail.com";
    private static String contrasena = "compraventa";
    private static String nombre = "CVSYSTEM";
}
