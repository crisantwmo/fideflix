package com.mycompany.fideflix;

import java.util.ArrayList;
import java.util.Collections;
import GUI.VentanaInicioSesion; 

public class Fideflix {
    public static void main(String[] args) {
        // 1. Crear colección de usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        usuarios.add(new Usuario("Alex", "pass1", 22));
        usuarios.add(new Usuario("Jonathan", "pass2", 25));
        usuarios.add(new Usuario("Maria", "pass3", 30));
        usuarios.add(new Usuario("Carlos", "pass4", 19));
        usuarios.add(new Usuario("Sofia", "pass5", 27));
        usuarios.add(new Usuario("Pedro", "pass6", 21));
        usuarios.add(new Usuario("Laura", "pass7", 28));
        usuarios.add(new Usuario("Diego", "pass8", 24));
        usuarios.add(new Usuario("Ana", "pass9", 20));
        usuarios.add(new Usuario("Felipe", "pass10", 23));

        // 2. Eliminar un usuario
        try {
            Usuario aEliminar = new Usuario("Pedro", "pass6", 21);
            // El 'removeIf' usa el nombre para la búsqueda.
            if (!usuarios.removeIf(u -> u.getNombre().equals(aEliminar.getNombre()))) {
                 // Nota: Si 'UsuarioNoEncontradoException' no existe, usará una excepción genérica
                 throw new RuntimeException("El usuario " + aEliminar.getNombre() + " no existe en la colección.");

            } else {
                System.out.println("Usuario eliminado: " + aEliminar.getNombre());
            }
        } catch (Exception e) { // Catch genérico para evitar errores de clase no encontrada
            System.out.println(e.getMessage());
        }

        // 3. Ordenar usuarios
        Collections.sort(usuarios);
        System.out.println("\nUsuarios ordenados:");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
        
        // -- Lógica de Audiovisuales --
        
        // 7. Destrucción de objetos
        usuarios.clear();
        System.out.println("\nUsuarios eliminados de la colección.");
        
        // 8. Lanzar la Interfaz Gráfica de Usuario (GUI)
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaInicioSesion().setVisible(true);
        });
    }
}