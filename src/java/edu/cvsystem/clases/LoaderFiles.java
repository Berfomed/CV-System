package edu.cvsystem.clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;

public class LoaderFiles {

    // Copiar un archivo con un stream (inputStream) en otra ruta (pathDestino con nombre de archivo)
    public static void copiarArchivo(InputStream inputStream, String pathDestino) {
        File archivo = new File(pathDestino);
        if (archivo.exists()) {
            archivo.delete();
        }
        try (InputStream stream = inputStream) {
            Files.copy(stream, new File(pathDestino).toPath());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Copiar un archivo (pathOrigen) en otra ruta (pathDestino con nombre de archivo)
    public static void copiarArchivo(String pathOrigen, String pathDestino) {
        if (existe(pathOrigen)) {
            try (FileInputStream stream = new FileInputStream(new File(pathOrigen))) {
                Files.copy(stream, new File(pathDestino).toPath());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // Copiar un archivo (pathOrigen) en otra ruta (pathDestino) con un nombre (nombreArchivo)
    public static void copiarArchivo(String pathOrigen, String pathDestino, String nombreArchivo) {
        copiarArchivo(pathOrigen, new File(pathDestino, nombreArchivo).getPath());
    }

    // Verificar si un archivo o directorio (path) existe
    public static boolean existe(String path) {
        return new File(path).exists();
    }

    // Leer en un arreglo, los bytes de un archivo (path con nombre de archivo)
    public static byte[] leerArchivo(String path) {
        if (existe(path)) {
            try (FileInputStream stream = new FileInputStream(new File(path))) {
                byte[] bytes = new byte[stream.available()];
                stream.read(bytes);
                return bytes;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    // Escribir los bytes de un archivo (bytes) en una ruta (path con nombre de archivo)
    public static void escribirArchivo(String path, byte[] bytes) {
        try (FileOutputStream stream = new FileOutputStream(new File(path))) {
            stream.write(bytes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Crear una nueva carpeta en una ruta (path) con un nombre (nombreCarpeta)
    public static void crearCarpeta(String path, String nombreCarpeta) {
        new File(path, nombreCarpeta).mkdir();
    }

    // Listar los nombres de los archivos de una carpeta (pathCarpeta)
    public static String[] listarArchivos(String pathCarpeta) {
        return new File(pathCarpeta).list();
    }

    // Listar los archivos de una carpeta (pathCarpeta)
    public static File[] getArchivos(String pathCarpeta) {
        return new File(pathCarpeta).listFiles();
    }
}
