package com.example.ignacio.aladino;

import com.google.firebase.firestore.GeoPoint;

import java.util.List;

public class Negocio {

    private String id;
    private String nombre;
    private String descripcion;
    private String foto;
    private GeoPoint localizacion;
    private String direccion;
    private int numrecos;
    private String telmovil;
    private List<String> telfijo;
    private List<String> categoria;
    private List<String> horarios;
    private List<String> envios;
    private List<String> redessociales;
    private String openOrClose;

    public Negocio(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public GeoPoint getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(GeoPoint localizacion) {
        this.localizacion = localizacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumrecos() {
        return numrecos;
    }

    public void setNumrecos(int numrecos) {
        this.numrecos = numrecos;
    }

    public String getTelmovil() {
        return telmovil;
    }

    public void setTelmovil(String telmovil) {
        this.telmovil = telmovil;
    }

    public List<String> getTelfijo() {
        return telfijo;
    }

    public void setTelfijo(List<String> telfijo) {
        this.telfijo = telfijo;
    }

    public List<String> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<String> categoria) {
        this.categoria = categoria;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public List<String> getEnvios() {
        return envios;
    }

    public void setEnvios(List<String> envios) {
        this.envios = envios;
    }

    public List<String> getRedessociales() {
        return redessociales;
    }

    public void setRedessociales(List<String> redessociales) {
        this.redessociales = redessociales;
    }

    public String getOpenOrClose() {
        return openOrClose;
    }

    public void setOpenOrClose(String openOrClose) {
        this.openOrClose = openOrClose;
    }
}
