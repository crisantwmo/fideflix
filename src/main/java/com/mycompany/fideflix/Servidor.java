package com.mycompany.fideflix;

import GUI.VentanaServidor; 
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase principal que ejecuta el socket servidor y acepta conexiones.
 */
public class Servidor extends Thread {
    private static final int PUERTO = 5001;
    private static final Logger LOGGER = Logger.getLogger(Servidor.class.getName());
    private VentanaServidor ventana; // Referencia a la GUI

    public Servidor(VentanaServidor ventana) {
        this.ventana = ventana;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            LOGGER.log(Level.INFO, "Servidor iniciado en puerto {0}. Esperando clientes...", PUERTO);
            // Aquí puedes llamar a un método de la GUI para actualizar el estado
            // ventana.agregarLog("Servidor iniciado..."); 

            while (!isInterrupted()) {
                Socket socketCliente = serverSocket.accept();
                LOGGER.log(Level.INFO, "Cliente conectado desde: {0}", socketCliente.getInetAddress());

                // LÍNEA CORREGIDA: Pasa el socket Y la referencia a la ventana
                HiloCliente hilo = new HiloCliente(socketCliente, ventana);
                hilo.start();
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al iniciar o ejecutar el servidor de sockets.", e);
        }
    }
}