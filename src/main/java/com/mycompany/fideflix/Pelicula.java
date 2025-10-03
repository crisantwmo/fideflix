package com.mycompany.fideflix;

/**
 *
 * @author keny
 */
public class Pelicula extends Audiovisual {
    private String director;
    private int anioEstreno;

    public Pelicula(String nombre, Float duracion, String género, Boolean veracidad, String director, int anioEstreno) {
        super(nombre, duracion, género, veracidad);
        this.director = director;
        this.anioEstreno = anioEstreno;
    }

    // Getters y Setters
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public int getAnioEstreno() {
        return anioEstreno;
    }
    public void setAnioEstreno(int anioEstreno) {
        this.anioEstreno = anioEstreno;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() +
               "\nDirector: " + director +
               "\nAño de estreno: " + anioEstreno;
    }

    public void reproducirTrailer() {
        System.out.println("Reproduciendo trailer de " + nombre);
    }
}
