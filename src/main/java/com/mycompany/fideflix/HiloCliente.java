package com.mycompany.fideflix;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.VentanaServidor;

/**
 * Hilo que atiende las peticiones de un cliente específico en el servidor.
 */
public class HiloCliente extends Thread {
    private Socket socketCliente;
    private VentanaServidor ventanaServidor; 
    private static final Logger LOGGER = Logger.getLogger(HiloCliente.class.getName());

    public HiloCliente(Socket socketCliente, VentanaServidor ventanaServidor) { 
        this.socketCliente = socketCliente;
        this.ventanaServidor = ventanaServidor;
    }

    @Override
    public void run() {
        Peticion respuesta = null;
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;
        
        // Obtener la IP antes de que el socket se cierre en el finally
        String ipCliente = (socketCliente != null) ? socketCliente.getInetAddress().getHostAddress() : "Desconocida";

        try {
            // 1. Crear los flujos (FUERA del try-with-resources para mayor estabilidad)
            salida = new ObjectOutputStream(socketCliente.getOutputStream());
            entrada = new ObjectInputStream(socketCliente.getInputStream());

            // 2. Recibir la petición del cliente
            Object objPeticion = entrada.readObject();

            if (objPeticion instanceof Peticion peticion) {
                
                // --- LÓGICA DE CREAR USUARIO ---
                if (peticion.getTipo() == TipoPeticion.CREAR_USUARIO) {
                    LOGGER.log(Level.INFO, "Petición: CREAR USUARIO - Cliente IP: {0}, Nombre: {1}", new Object[]{ipCliente, peticion.getNombreUsuario()});

                    Usuario nuevoUsuario = new Usuario(
                        peticion.getNombreUsuario(),
                        peticion.getContrasena(),
                        peticion.getEdad()
                    );

                    boolean creado = GestorUsuarios.crearUsuario(nuevoUsuario);

                    if (creado) {
                        respuesta = new Peticion(true, "Usuario creado y guardado con éxito.");
                        ventanaServidor.log("[INFO] Usuario Creado: " + nuevoUsuario.getNombre() + " (IP: " + ipCliente + ")");
                    } else {
                        respuesta = new Peticion(false, "ERROR: El nombre de usuario '" + nuevoUsuario.getNombre() + "' ya existe.");
                        ventanaServidor.log("[ERROR] Creación fallida: " + nuevoUsuario.getNombre() + " (IP: " + ipCliente + ")");
                    }

                // --- LÓGICA DE INICIAR SESIÓN ---
                } else if (peticion.getTipo() == TipoPeticion.INICIAR_SESION) {
                    LOGGER.log(Level.INFO, "Petición: INICIAR SESIÓN - Cliente IP: {0}, Nombre: {1}", new Object[]{ipCliente, peticion.getNombreUsuario()});

                    String nombre = peticion.getNombreUsuario();
                    String contrasena = peticion.getContrasena();

                    boolean valido = GestorUsuarios.validarCredenciales(nombre, contrasena);

                    if (valido) {
                        respuesta = new Peticion(true, "Credenciales correctas.");
                        ventanaServidor.log("[INFO] Sesión iniciada: " + nombre + " (IP: " + ipCliente + ")");
                        
                        
                    } else {
                        respuesta = new Peticion(false, "Credenciales incorrectas. Usuario o contraseña inválidos.");
                        ventanaServidor.log("[ADVERTENCIA] Intento fallido de sesión: " + nombre + " (IP: " + ipCliente + ")");
                    }
                    
                } else {
                    respuesta = new Peticion(false, "Comando de petición no reconocido.");
                }

            } else {
                LOGGER.log(Level.WARNING, "Objeto recibido no es una Peticion.");
                respuesta = new Peticion(false, "Error de protocolo: Objeto no reconocido.");
            }
            
            // 3. Enviar la respuesta
            if (respuesta != null) {
                salida.writeObject(respuesta);
                salida.flush(); 
            }

        } catch (IOException e) {
            // Atrapa errores de conexión, como cuando el cliente se desconecta
            LOGGER.log(Level.SEVERE, "Error de E/S o conexión en HiloCliente para el socket " + ipCliente, e);
            
        } catch (ClassNotFoundException e) {
            // Atrapa errores de serialización
            LOGGER.log(Level.SEVERE, "Error al deserializar objeto en HiloCliente. Posible falta de implement Serializable.", e);
        
        } catch (Exception e) {
            // Atrapa cualquier otra excepción, incluyendo las que puedan venir de GestorUsuarios
            LOGGER.log(Level.SEVERE, "Error inesperado en HiloCliente (Posible fallo en GestorUsuarios): " + ipCliente, e);
            
        } finally {
            ventanaServidor.log("Conexión finalizada y socket cerrado para cliente: " + ipCliente);
            
            // 4. Cerrar flujos y socket en el bloque finally
            try {
                if (salida != null) salida.close();
                if (entrada != null) entrada.close();
                if (socketCliente != null) socketCliente.close();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "No se pudo cerrar un flujo o el socket del cliente.", e);
            }
        }
    }
}