package com.example.ignacio.aladino;

public class Negocio {
    
    private String name;
    private String image;
    private String id;

    public Negocio(String name, String image, String id) {
        this.name = name;
        this.image = image;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
