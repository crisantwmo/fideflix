package com.mycompany.fideflix;

/**
 *
 * @author keny
 */
public class Serie extends Audiovisual {
    protected int cantidadCapitulos;
    protected int temporadas;

    public Serie(String nombre, Float duracion, String género, Boolean veracidad, int cantidadCapitulos, int temporadas) {
        super(nombre, duracion, género, veracidad);
        this.cantidadCapitulos = cantidadCapitulos;
        this.temporadas = temporadas;
    }

    // Getters y Setters
    public int getCantidadCapitulos() {
        return cantidadCapitulos;
    }
    public void setCantidadCapitulos(int cantidadCapitulos) {
        this.cantidadCapitulos = cantidadCapitulos;
    }
    public int getTemporadas() {
        return temporadas;
    }
    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() +
               "\nCapítulos: " + cantidadCapitulos +
               "\nTemporadas: " + temporadas;
    }
}
