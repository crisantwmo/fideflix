package com.mycompany.fideflix;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Cliente para manejar la comunicación con el Servidor.
 */
public class Cliente {
    private static final String IP_SERVIDOR = "127.0.0.1"; // Localhost
    private static final int PUERTO = 5001;
    private static final Logger LOGGER = Logger.getLogger(Cliente.class.getName());

    /**
     * Envía una petición al servidor y espera la respuesta.
     * @param peticion La petición (CREAR_USUARIO o INICIAR_SESION)
     * @return La respuesta del servidor (Peticion con resultado y mensaje)
     */
    public Peticion enviarPeticion(Peticion peticion) {
        // Se utiliza try-with-resources solo para el Socket, ya que los ObjectStreams
        // deben crearse secuencialmente DENTRO del try.
        try (Socket socket = new Socket(IP_SERVIDOR, PUERTO)) {

            // 1. Crear el ObjectOutputStream (Salida) y enviar la petición
            // ESTO DEBE SER LO PRIMERO QUE HAGA EL CLIENTE
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            salida.writeObject(peticion);
            salida.flush(); 

            // 2. Crear el ObjectInputStream (Entrada) justo antes de leer la respuesta
            // ESTO DEBE SER LO SEGUNDO QUE HAGA EL CLIENTE
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

            // 3. Recibir la respuesta
            Object objRespuesta = entrada.readObject();
            
            // Los flujos (salida y entrada) se cierran implícitamente al cerrar el socket.
            // Si el servidor funciona correctamente, esta lectura tendrá éxito.

            if (objRespuesta instanceof Peticion) {
                return (Peticion) objRespuesta;
            } else {
                return new Peticion(false, "Respuesta del servidor no válida.");
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error de conexión o E/S con el servidor.", e);
            return new Peticion(false, "No se pudo conectar al servidor: Asegúrese de que esté activo y en puerto " + PUERTO);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al deserializar la respuesta del servidor.", e);
            return new Peticion(false, "Error interno de protocolo: Clase no encontrada.");
        }
    }
}