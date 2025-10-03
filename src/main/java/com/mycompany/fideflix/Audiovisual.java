package com.mycompany.fideflix;

/**
 *
 * @author keny
 */
public class Audiovisual {
    protected String nombre;
    protected Float duracion;
    protected String género; 
    protected Boolean veracidad; // true = no ficticio (documental), false = ficticio (película/serie)
    
    public Audiovisual(String nombre, Float duracion, String género, Boolean veracidad) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.género = género;
        this.veracidad = veracidad;
    }
    
    public void cambiarIdioma() {
        System.out.println("Aun no sé como cambiar el idioma");
    }

    // Getters y Setters (para el que revise, esto lo hago por aca para entender mejor donde esta la diferencia) 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getDuracion() {
        return duracion;
    }

    public void setDuracion(Float duracion) {
        this.duracion = duracion;
    }

    public String getGénero() {
        return género;
    }

    public void setGénero(String género) {
        this.género = género;
    }

    public Boolean getVeracidad() {
        return veracidad;
    }

    public void setVeracidad(Boolean veracidad) {
        this.veracidad = veracidad;
    }

    // Método para mostrar información 
    public String mostrarInformacion() {
        String v = (veracidad != null && veracidad) ? "No ficticio (Documental)" : "Ficticio (Película/Serie)";
        return "Nombre: " + nombre + "\nDuración: " + duracion + " min\nGénero: " + género + "\nTipo: " + v;
    }
}

