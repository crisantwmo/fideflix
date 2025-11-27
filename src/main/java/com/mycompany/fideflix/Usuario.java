package com.mycompany.fideflix;

import java.io.Serializable;

public class Usuario implements Serializable, Comparable<Usuario> {
    private String nombre;
    private String contrasena;
    private int edad;

    // Se recomienda agregar el serialVersionUID
    private static final long serialVersionUID = 1L; 

    public Usuario(String nombre, String contrasena, int edad) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }

    @Override
    public int compareTo(Usuario otro) {
        return this.nombre.compareTo(otro.nombre);
    }
}