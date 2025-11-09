package com.mycompany.fideflix;

public class Pelicula extends Audiovisual implements Comparable<Pelicula> {
    private int duracion; 

    public Pelicula(String titulo, String genero, int anio, int duracion) {
        super(titulo, genero, anio);
        this.duracion = duracion;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Pelicula: " + titulo + " (" + anio + "), Genero: " + genero + ", Duración: " + duracion + " min");
    }

    @Override
    public int compareTo(Pelicula otra) {
        return this.titulo.compareTo(otra.titulo);
    }
}
