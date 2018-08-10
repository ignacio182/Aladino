package com.example.ignacio.aladino;

public class Negocio {
    
    private String name;
    private String image;

    public Negocio(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Negocio() {
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
