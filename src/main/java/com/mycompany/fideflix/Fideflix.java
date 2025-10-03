package com.mycompany.fideflix;

import javax.swing.JOptionPane;

/**
 *
 * @author keny
 */
public class Fideflix {

    static final int MAX_AUD = 50;
    static final int MAX_COMMENTS = 20;

    static Audiovisual[] audiovisuales = new Audiovisual[MAX_AUD];
    static String[][] comentarios = new String[MAX_AUD][MAX_COMMENTS];
    static int[] comentariosCount = new int[MAX_AUD];
    static int[] ratingsSum = new int[MAX_AUD];
    static int[] ratingsCount = new int[MAX_AUD];
    static int audiovisualCount = 0;

    public static void main(String[] args) {
        // Crear usuario (solicitar datos)
        String user = JOptionPane.showInputDialog(null, "Ingrese su nombre de usuario:");
        if (user == null) return;
        String pass = JOptionPane.showInputDialog(null, "Ingrese una contraseña:");
        if (pass == null) return;

        Usuario usuario = new Usuario(user, pass);
        Usuario.iniciarSesion(usuario);

        // Menú principal
        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                null,
                "¡Bienvenido " + usuario.getNombreUsuario() + "!\n" +
                "Seleccione una opción:\n" +
                "1. Revisar o Agregar\n" +
                "2. Calificar y comentar\n" +
                "3. Ver toda la información\n" +
                "4. Salir"
            );
            if (opcion == null) break;

            switch (opcion) {
                case "1":
                    menuRevisarAgregar();
                    break;
                case "2":
                    menuCalificarComentar();
                    break;
                case "3":
                    mostrarTodaInformacion();
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    // Submenu: Revisar o Agregar
    private static void menuRevisarAgregar() {
        boolean volver = false;
        while (!volver) {
            String op = JOptionPane.showInputDialog(
                null,
                "Revisar o Agregar:\n" +
                "1. Agregar Audiovisual\n" +
                "2. Revisar Audiovisuales\n" +
                "3. Eliminar Audiovisual\n" +
                "4. Volver al menú principal"
            );
            if (op == null) return;
            switch (op) {
                case "1":
                    agregarAudiovisual();
                    break;
                case "2":
                    revisarAudiovisuales();
                    break;
                case "3":
                    eliminarAudiovisual();
                    break;
                case "4":
                    volver = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    private static void agregarAudiovisual() {
        if (audiovisualCount >= MAX_AUD) {
            JOptionPane.showMessageDialog(null, "Capacidad máxima alcanzada.");
            return;
        }

        // Preguntar si es no ficticio (documental)
        String resVer = JOptionPane.showInputDialog(null, "¿Este audiovisual es NO ficticio (Documental)? (s/n)");
        if (resVer == null) return;
        boolean veracidad = resVer.trim().equalsIgnoreCase("s");

        if (veracidad) {
            // Crear Documental
            String nombre = JOptionPane.showInputDialog(null, "Nombre del documental:");
            if (nombre == null) return;
            String durStr = JOptionPane.showInputDialog(null, "Duración en minutos (ej: 90):");
            if (durStr == null) return;
            Float dur = parseFloatSafe(durStr, 0f);
            String genero = JOptionPane.showInputDialog(null, "Género:");
            if (genero == null) return;
            String tema = JOptionPane.showInputDialog(null, "Tema del documental:");
            if (tema == null) return;
            String pais = JOptionPane.showInputDialog(null, "País de producción:");
            if (pais == null) return;

            Documental d = new Documental(nombre, dur, genero, true, tema, pais);
            audiovisuales[audiovisualCount++] = d;
            JOptionPane.showMessageDialog(null, "Documental agregado correctamente.");
        } else {
            // Ficticio -> preguntar si Pelicula o Serie
            String tipo = JOptionPane.showInputDialog(null, "¿Agregar Película o Serie? (p/s)");
            if (tipo == null) return;
            if (tipo.trim().equalsIgnoreCase("p")) {
                // Pelicula
                String nombre = JOptionPane.showInputDialog(null, "Nombre de la película:");
                if (nombre == null) return;
                String durStr = JOptionPane.showInputDialog(null, "Duración en minutos (ej: 120):");
                if (durStr == null) return;
                Float dur = parseFloatSafe(durStr, 0f);
                String genero = JOptionPane.showInputDialog(null, "Género:");
                if (genero == null) return;
                String director = JOptionPane.showInputDialog(null, "Director:");
                if (director == null) return;
                String anioStr = JOptionPane.showInputDialog(null, "Año de estreno (ej: 2021):");
                if (anioStr == null) return;
                int anio = parseIntSafe(anioStr, 0);

                Pelicula p = new Pelicula(nombre, dur, genero, false, director, anio);
                audiovisuales[audiovisualCount++] = p;
                JOptionPane.showMessageDialog(null, "Película agregada correctamente.");
            } else if (tipo.trim().equalsIgnoreCase("s")) {
                // Serie
                String nombre = JOptionPane.showInputDialog(null, "Nombre de la serie:");
                if (nombre == null) return;
                String durStr = JOptionPane.showInputDialog(null, "Duración aproximada por capítulo en minutos (ej: 45):");
                if (durStr == null) return;
                Float dur = parseFloatSafe(durStr, 0f);
                String genero = JOptionPane.showInputDialog(null, "Género:");
                if (genero == null) return;
                String capStr = JOptionPane.showInputDialog(null, "Cantidad de capítulos:");
                if (capStr == null) return;
                int caps = parseIntSafe(capStr, 0);
                String tempStr = JOptionPane.showInputDialog(null, "Cantidad de temporadas:");
                if (tempStr == null) return;
                int temps = parseIntSafe(tempStr, 0);

                Serie s = new Serie(nombre, dur, genero, false, caps, temps);
                audiovisuales[audiovisualCount++] = s;
                JOptionPane.showMessageDialog(null, "Serie agregada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Opción no reconocida. Use 'p' para película o 's' para serie.");
            }
        }
    }

    // Revisar audiovisuales
    private static void revisarAudiovisuales() {
        if (audiovisualCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay audiovisuales guardados.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < audiovisualCount; i++) {
            Audiovisual a = audiovisuales[i];
            String tipo = a instanceof Pelicula ? "Película" : (a instanceof Serie ? "Serie" : "Documental");
            sb.append(i).append(". ").append(a.getNombre()).append(" (").append(tipo).append(")\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Eliminar audiovisual 
    private static void eliminarAudiovisual() {
        if (audiovisualCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay audiovisuales para eliminar.");
            return;
        }
        StringBuilder sb = new StringBuilder("Seleccione el índice a eliminar:\n");
        for (int i = 0; i < audiovisualCount; i++) {
            Audiovisual a = audiovisuales[i];
            String tipo = a instanceof Pelicula ? "Película" : (a instanceof Serie ? "Serie" : "Documental");
            sb.append(i).append(". ").append(a.getNombre()).append(" (").append(tipo).append(")\n");
        }
        String idxStr = JOptionPane.showInputDialog(null, sb.toString());
        if (idxStr == null) return;
        int idx = parseIntSafe(idxStr, -1);
        if (idx < 0 || idx >= audiovisualCount) {
            JOptionPane.showMessageDialog(null, "Índice inválido.");
            return;
        }

        
        for (int i = idx; i < audiovisualCount - 1; i++) {
            audiovisuales[i] = audiovisuales[i + 1];
            comentarios[i] = comentarios[i + 1];
            comentariosCount[i] = comentariosCount[i + 1];
            ratingsSum[i] = ratingsSum[i + 1];
            ratingsCount[i] = ratingsCount[i + 1];
        }
        audiovisuales[audiovisualCount - 1] = null;
        comentarios[audiovisualCount - 1] = new String[MAX_COMMENTS];
        comentariosCount[audiovisualCount - 1] = 0;
        ratingsSum[audiovisualCount - 1] = 0;
        ratingsCount[audiovisualCount - 1] = 0;

        audiovisualCount--;
        JOptionPane.showMessageDialog(null, "Audiovisual eliminado correctamente.");
    }

    // Menu Calificar y Comentar
    private static void menuCalificarComentar() {
        if (audiovisualCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay audiovisuales para calificar o comentar.");
            return;
        }
        StringBuilder sb = new StringBuilder("Seleccione el índice a calificar/comentar:\n");
        for (int i = 0; i < audiovisualCount; i++) {
            Audiovisual a = audiovisuales[i];
            sb.append(i).append(". ").append(a.getNombre()).append("\n");
        }
        String idxStr = JOptionPane.showInputDialog(null, sb.toString());
        if (idxStr == null) return;
        int idx = parseIntSafe(idxStr, -1);
        if (idx < 0 || idx >= audiovisualCount) {
            JOptionPane.showMessageDialog(null, "Índice inválido.");
            return;
        }

        String op = JOptionPane.showInputDialog(null, "1. Calificar\n2. Comentar\n3. Volver");
        if (op == null) return;
        switch (op) {
            case "1":
                String ratingStr = JOptionPane.showInputDialog(null, "Ingrese calificación (1-5):");
                if (ratingStr == null) return;
                int r = parseIntSafe(ratingStr, 0);
                if (r < 1 || r > 5) {
                    JOptionPane.showMessageDialog(null, "Calificación inválida.");
                    return;
                }
                ratingsSum[idx] += r;
                ratingsCount[idx] += 1;
                JOptionPane.showMessageDialog(null, "Calificación registrada.");
                break;
            case "2":
                if (comentariosCount[idx] >= MAX_COMMENTS) {
                    JOptionPane.showMessageDialog(null, "Este audiovisual ya alcanzó el número máximo de comentarios.");
                    return;
                }
                String comentario = JOptionPane.showInputDialog(null, "Escriba su comentario:");
                if (comentario == null) return;
                comentarios[idx][comentariosCount[idx]++] = comentario;
                JOptionPane.showMessageDialog(null, "Comentario guardado.");
                break;
            case "3":
                return;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida.");
        }
    }

    // Mostrar toda la información de todos los audiovisuales
    private static void mostrarTodaInformacion() {
        if (audiovisualCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay información para mostrar.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < audiovisualCount; i++) {
            sb.append("Índice: ").append(i).append("\n");
            sb.append(audiovisuales[i].mostrarInformacion()).append("\n");
            // Calificación 
            if (ratingsCount[i] > 0) {
                double avg = (double) ratingsSum[i] / ratingsCount[i];
                sb.append(String.format("Calificación promedio: %.2f (%d votos)\n", avg, ratingsCount[i]));
            } else {
                sb.append("Calificación promedio: No hay calificaciones\n");
            }
            // Comentarios
            if (comentariosCount[i] > 0) {
                sb.append("Comentarios:\n");
                for (int j = 0; j < comentariosCount[i]; j++) {
                    sb.append(" - ").append(comentarios[i][j]).append("\n");
                }
            } else {
                sb.append("Comentarios: Ninguno\n");
            }
            sb.append("--------------------------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    
    private static Float parseFloatSafe(String s, Float defecto) {
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return defecto;
        }
    }

    private static int parseIntSafe(String s, int defecto) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defecto;
        }
    }
}
