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

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
