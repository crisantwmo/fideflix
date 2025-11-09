package com.mycompany.fideflix;

public class Documental extends Audiovisual implements Comparable<Documental> {
    private String tema;

    public Documental(String titulo, String genero, int anio, String tema) {
        super(titulo, genero, anio);
        this.tema = tema;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Documental: " + titulo + " (" + anio + "), Tema: " + tema);
    }

    @Override
    public int compareTo(Documental otro) {
        return this.titulo.compareTo(otro.titulo);
    }
}
