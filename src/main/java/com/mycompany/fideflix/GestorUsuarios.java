package com.mycompany.fideflix;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorUsuarios {

    private static final String NOMBRE_ARCHIVO = "usuarios.dat";
    private static final Logger LOGGER = Logger.getLogger(GestorUsuarios.class.getName());

    public static void guardarUsuarios(ArrayList<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(NOMBRE_ARCHIVO))) {
            
            oos.writeObject(usuarios);
            
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al guardar usuarios.", e);
        }
    }

    public static ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(NOMBRE_ARCHIVO);

        if (!archivo.exists() || archivo.length() == 0) {
            return usuarios; 
        }

        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            Object obj = ois.readObject();
            
            if (obj instanceof ArrayList) {
                usuarios = (ArrayList<Usuario>) obj;
            }
            
        } catch (FileNotFoundException e) {
             // El archivo existe, pero FileInputStream falló (improbable después de la comprobación).
             LOGGER.log(Level.WARNING, "Archivo de usuarios no encontrado, retornando lista vacía.", e);
        } catch (EOFException e) {
            // Archivo vacío o escritura interrumpida.
            LOGGER.log(Level.INFO, "Archivo de usuarios vacío o corrupto (EOF), se ignorará el contenido.", e);
        } catch (IOException | ClassNotFoundException e) {
            // Error de lectura/serialización.
            LOGGER.log(Level.SEVERE, "Error al cargar usuarios.", e);
        }
        
        return usuarios;
    }
    
    public static boolean crearUsuario(Usuario nuevoUsuario) {
        ArrayList<Usuario> usuarios = cargarUsuarios();

        boolean existe = usuarios.stream()
                                  .anyMatch(u -> u.getNombre().equals(nuevoUsuario.getNombre()));

        if (existe) {
            return false;
        }

        usuarios.add(nuevoUsuario);
        guardarUsuarios(usuarios);

        return true;
    }
    
    public static boolean validarCredenciales(String nombreUsuario, String contrasena) {
        ArrayList<Usuario> usuarios = cargarUsuarios();
        
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombreUsuario) && u.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
}