package com.mycompany.fideflix;

public class Serie extends Audiovisual implements Comparable<Serie> {
    private int temporadas;

    public Serie(String titulo, String genero, int anio, int temporadas) {
        super(titulo, genero, anio);
        this.temporadas = temporadas;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Serie: " + titulo + " (" + anio + "), Genero: " + genero + ", Temporadas: " + temporadas);
    }

    @Override
    public int compareTo(Serie otra) {
        return this.titulo.compareTo(otra.titulo);
    }
}
