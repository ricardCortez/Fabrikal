package com.example.fabrikal;

public class Producto {
    private String tipo;
    private String color;
    private String marca;
    private String modelo;
    private Double precio;

    public Producto() {
    }

    public Producto(String tipo, String color, String marca, String modelo, Double precio) {
        this.tipo = tipo;
        this.color = color;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}



