package com.mycompany.fideflix;

import java.io.Serializable;

/**
 * Define los tipos de comandos que el Cliente puede enviar al Servidor.
 * Debe ser Serializable para viajar por la red.
 */
public enum TipoPeticion implements Serializable {
    CREAR_USUARIO,
    INICIAR_SESION,
}