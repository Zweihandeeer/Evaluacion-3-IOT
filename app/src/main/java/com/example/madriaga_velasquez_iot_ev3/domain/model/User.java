package com.example.madriaga_velasquez_iot_ev3.domain.model;

public class User {
    // Declaración de atributos de la clase User.
    private String name;
    private String pass;
    private String preguntasecreta;
    private String respuestasecreta;

    // Región constructor.
    public User(String name, String pass, String preguntasecreta, String respuestasecreta) {
        this.name = name;
        this.pass = pass;
        this.preguntasecreta = preguntasecreta;
        this.respuestasecreta = respuestasecreta;
    }
    // Fin región constructor.

    // Región getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPreguntasecreta() {
        return preguntasecreta;
    }

    public void setPreguntasecreta(String preguntasecreta) {
        this.preguntasecreta = preguntasecreta;
    }

    public String getRespuestasecreta() {
        return respuestasecreta;
    }

    public void setRespuestasecreta(String respuestasecreta) {
        this.respuestasecreta = respuestasecreta;
    }
    // Fin región getter & setters
}
