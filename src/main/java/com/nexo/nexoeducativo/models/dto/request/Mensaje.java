package com.nexo.nexoeducativo.models.dto.request;

public class Mensaje {
    private String comunicador;
    private String destinatario;
    private String contenido;

    // Getters y Setters
    public String getComunicador() {
        return comunicador;
    }

    public void setComunicador(String comunicador) {
        this.comunicador = comunicador;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}