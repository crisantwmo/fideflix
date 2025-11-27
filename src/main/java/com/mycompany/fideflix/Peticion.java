package com.mycompany.fideflix;

import java.io.Serializable;

/**
 * Clase que encapsula la solicitud del cliente y la respuesta del servidor.
 */
public class Peticion implements Serializable {
    // Campos de la Petición
    private TipoPeticion tipo;
    private String nombreUsuario;
    private String contrasena;
    private int edad;
    
    // Campos de la Respuesta
    private boolean resultado; // true si fue exitoso, false si falló
    private String mensaje;    // Mensaje de éxito o error

    // Constructor para SOLICITUDES del Cliente (Ejemplo: Crear Usuario)
    public Peticion(TipoPeticion tipo, String nombreUsuario, String contrasena, int edad) {
        this.tipo = tipo;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.edad = edad;
        // Los campos de respuesta se dejan nulos para la solicitud
    }
    
    // Constructor para RESPUESTAS del Servidor
    public Peticion(boolean resultado, String mensaje) {
        this.resultado = resultado;
        this.mensaje = mensaje;
        // Los campos de solicitud se dejan nulos para la respuesta
    }
    
    // Getters necesarios (debes añadir los setters si el servidor los necesita)

    public TipoPeticion getTipo() {
        return tipo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isResultado() {
        return resultado;
    }

    public String getMensaje() {
        return mensaje;
    }
}