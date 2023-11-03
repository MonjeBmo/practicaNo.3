package com.bmo.practica5.models;

public class MProducto {

    private int idProducto;
    private String nameProducto;
    private String descriptionProducto;
    private int stock;
    private double precioVenta;
    private double precioCompra;
    private int idProveedor;
    private int idCategoria;

    public MProducto() {
    }

    public MProducto(int idProducto, String nameProducto, String descriptionProducto, int stock, double precioVenta, double precioCompra, int idProveedor, int idCategoria) {
        this.idProducto = idProducto;
        this.nameProducto = nameProducto;
        this.descriptionProducto = descriptionProducto;
        this.stock = stock;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
    }

    public MProducto(String nameProducto, String descriptionProducto, int stock, double precioVenta, double precioCompra, int idProveedor, int idCategoria) {
        this.nameProducto = nameProducto;
        this.descriptionProducto = descriptionProducto;
        this.stock = stock;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNameProducto() {
        return nameProducto;
    }

    public void setNameProducto(String nameProducto) {
        this.nameProducto = nameProducto;
    }

    public String getDescriptionProducto() {
        return descriptionProducto;
    }

    public void setDescriptionProducto(String descriptionProducto) {
        this.descriptionProducto = descriptionProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}

