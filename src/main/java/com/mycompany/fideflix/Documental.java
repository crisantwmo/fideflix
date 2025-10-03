package com.mycompany.fideflix;

/**
 *
 * @author keny
 */
public class Documental extends Audiovisual {
    private String tema;
    private String paisProduccion;

    public Documental(String nombre, Float duracion, String género, Boolean veracidad, String tema, String paisProduccion) {
        super(nombre, duracion, género, veracidad);
        this.tema = tema;
        this.paisProduccion = paisProduccion;
    }

    // Getters y Setters
    public String getTema() {
        return tema;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }
    public String getPaisProduccion() {
        return paisProduccion;
    }
    public void setPaisProduccion(String paisProduccion) {
        this.paisProduccion = paisProduccion;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() +
               "\nTema: " + tema +
               "\nPaís de producción: " + paisProduccion;
    }
}

