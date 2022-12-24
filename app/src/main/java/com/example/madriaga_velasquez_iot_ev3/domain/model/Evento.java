package com.example.madriaga_velasquez_iot_ev3.domain.model;

public class Evento {
    // Declaración de atributos de la clase Evento.
    private String titulo;
    private String lugar;
    private String observacion;
    private String fecha;
    private String aviso;
    private String importancia;

    // Región constructor
    // Un constructor es un método cuyo nombre es igual que el nombre de su tipo.
    // Su firma del método incluye solo un modificador de acceso opcional, el nombre del método y su lista de parámetros;
    // no incluye un tipo de valor devuelto.
    public Evento(String titulo, String lugar, String observacion, String fecha, String aviso, String importancia) {
        this.titulo = titulo;
        this.lugar = lugar;
        this.observacion = observacion;
        this.fecha = fecha;
        this.aviso = aviso;
        this.importancia = importancia;
    }
    // Fin región constructor.

    // Región getters & setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }
    // Fin región getter & setters
}
