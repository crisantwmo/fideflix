package com.mycompany.fideflix;

import javax.swing.JOptionPane;

/**
 *
 * @author keny
 */
public class Usuario {
    private String nombreUsuario;
    private String contrasena;

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Método estático requerido
    public static void iniciarSesion(Usuario u) {
        JOptionPane.showMessageDialog(null, "¡Bienvenido " + u.getNombreUsuario() + "!");
    }
}
