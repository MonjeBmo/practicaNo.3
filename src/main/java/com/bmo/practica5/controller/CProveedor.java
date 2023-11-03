package com.bmo.practica5.controller;


import com.bmo.practica5.config.Methods;
import com.bmo.practica5.models.MProveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CProveedor extends Methods {

    @Override
    public void create(Object object, Connection cc) {
        MProveedor proveedor = (MProveedor) object;
        try {
            Statement stmt = cc.createStatement();
            String sql = new StringBuilder()
                    .append("INSERT INTO proveedor (nameEmpresa, nameContacto, address, phone, email) ")
                    .append("VALUES ('").append(proveedor.getNameEmpresa())
                    .append("', '").append(proveedor.getName())
                    .append("', '").append(proveedor.getAddress())
                    .append("', '").append(proveedor.getPhone())
                    .append("', '").append(proveedor.getEmail())
                    .append("')").toString();
            if (stmt.executeUpdate(sql) == 1) {
                JOptionPane.showMessageDialog(null, "Proveedor registrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Object findById(String id, Connection cc) {
        try {
            Statement stmt = cc.createStatement();
            String sql = new StringBuilder()
                    .append("SELECT * FROM proveedor WHERE idProveedor = ")
                    .append(id).toString();
            var rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new MProveedor(
                        rs.getString("nameContacto"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("nameEmpresa"),
                        rs.getInt("idProveedor"));
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<Object> findAll(String id, Connection cc) {
        ArrayList<Object> proveedores = new ArrayList<>();

        try {
            Statement statement = cc.createStatement();
            String sql = new StringBuilder()
                    .append("SELECT * FROM proveedor").toString();
            var rs = statement.executeQuery(sql);
            while (rs.next()) {
                proveedores.add(new MProveedor(
                        rs.getString("nameContacto"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("nameEmpresa"),
                        rs.getInt("idProveedor")));
            }

            return proveedores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public DefaultTableModel modelTable(Connection cc) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"No", "ID", "Empresa", "Contacto", "Dirección", "Teléfono", "Email"});
        ArrayList<Object> proveedores = findAll("", cc);

        for (int i = 0; i < proveedores.size(); i++) {
            MProveedor proveedor = (MProveedor) proveedores.get(i);
            model.addRow(new Object[]{i + 1, proveedor.getId(), proveedor.getNameEmpresa(), proveedor.getName(), proveedor.getAddress(), proveedor.getPhone(), proveedor.getEmail()});
        }

        return model;
    }

    @Override
    public boolean update(Object object, Connection cc) {
        try {
            Statement stmt = cc.createStatement();
            String sql = new StringBuilder()
                    .append("UPDATE proveedor SET ")
                    .append("nameEmpresa = '").append(((MProveedor) object).getNameEmpresa())
                    .append("', nameContacto = '").append(((MProveedor) object).getName())
                    .append("', address = '").append(((MProveedor) object).getAddress())
                    .append("', phone = '").append(((MProveedor) object).getPhone())
                    .append("', email = '").append(((MProveedor) object).getEmail())
                    .append("' WHERE idProveedor = ").append(((MProveedor) object).getId()).toString();
            if (stmt.executeUpdate(sql) == 1) {
                JOptionPane.showMessageDialog(null, "Proveedor actualizado");
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean delete(String id, Connection cc) {
        try {
            Statement stmt = cc.createStatement();
            String sql = new StringBuilder()
                    .append("DELETE FROM proveedor WHERE idProveedor = ").append(id).toString();
            if (stmt.executeUpdate(sql) == 1) {
                JOptionPane.showMessageDialog(null, "Proveedor eliminado");
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
