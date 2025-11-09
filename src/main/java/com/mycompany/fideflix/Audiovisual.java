package com.mycompany.fideflix;

import java.util.ArrayList;

public abstract class Audiovisual {
    protected String titulo;
    protected String genero;
    protected int anio;

    // Colección de comentarios 
    protected static ArrayList<String> comentarios = new ArrayList<>();

    public Audiovisual(String titulo, String genero, int anio) {
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnio() {
        return anio;
    }

    // Método estático para agregar comentarios
    public static void agregarComentario(String comentario) {
        comentarios.add(comentario);
    }

    // Mostrar todos los comentarios
    public static void mostrarComentarios() {
        System.out.println("Comentarios:");
        for (String c : comentarios) {
            System.out.println("- " + c);
        }
    }

    // Método abstracto 
    public abstract void mostrarInfo();
}
