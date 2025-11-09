package com.mycompany.fideflix;

import java.io.*;
import java.util.ArrayList;

public class GestorUsuarios {

    // Define el nombre del archivo donde se guardarán los objetos.
    private static final String NOMBRE_ARCHIVO = "usuarios.dat";

    // Método 1: Guardar toda la lista de usuarios (Serialización)
    public static void guardarUsuarios(ArrayList<Usuario> usuarios) {
        // ObjectOutputStream: Convierte el objeto Java a bytes para guardarlo.
        // FileOutputStream: Escribe esos bytes en el archivo.
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(NOMBRE_ARCHIVO))) {
            
            // Escribir el objeto completo (la lista) en el archivo
            oos.writeObject(usuarios);
            
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método 2: Cargar la lista de usuarios (Deserialización)
    public static ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(NOMBRE_ARCHIVO);

        // 1. Manejar el caso de que el archivo no exista o esté vacío
        if (!archivo.exists() || archivo.length() == 0) {
            return usuarios; 
        }

        // ObjectInputStream: Lee los bytes del archivo y los convierte de vuelta a objeto Java.
        // FileInputStream: Lee los bytes desde el archivo.
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(archivo))) {
            
            // 2. Leer el objeto completo (la lista) del archivo
            Object obj = ois.readObject();
            
            // 3. Verificación de tipo y asignación
            if (obj instanceof ArrayList) {
                // Se hace el "casting" seguro (conversión) al tipo esperado
                usuarios = (ArrayList<Usuario>) obj;
            }
            
        } catch (FileNotFoundException e) {
            // Este error está cubierto por el chequeo inicial, pero es buena práctica mantenerlo.
            System.err.println("Archivo de usuarios no encontrado.");
        } catch (IOException | ClassNotFoundException e) {
            // IOException: Error de lectura/escritura. ClassNotFound: Si la clase Usuario ha cambiado.
            System.err.println("Error al cargar usuarios: " + e.getMessage());
            e.printStackTrace();
        }
        
        return usuarios;
    }
}