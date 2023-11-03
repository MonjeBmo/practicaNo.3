package com.bmo.practica5.models;


public class MProveedor extends Persona {
    private String nameEmpresa;


    public String getNameEmpresa() {
        return nameEmpresa;
    }

    public void setNameEmpresa(String nameEmpresa) {
        this.nameEmpresa = nameEmpresa;
    }



    public MProveedor(String name, String address, String phone, String email, String nameEmpresa, int id) {
        super(name, address, phone, email, id);
        this.nameEmpresa = nameEmpresa;
    }
    public MProveedor(String name, String address, String phone, String email, String nameEmpresa) {
        super(name, address, phone, email);
        this.nameEmpresa = nameEmpresa;
    }
}
