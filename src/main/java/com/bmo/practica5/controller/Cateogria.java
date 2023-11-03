package com.bmo.practica5.controller;

import com.bmo.practica5.config.Methods;
import com.bmo.practica5.models.MCategoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class Cateogria extends Methods {
    @Override
    public void create(Object object, Connection cc) {
        MCategoria categoria = (MCategoria) object;
        try {
            Statement st = cc.createStatement();

            String sql = "INSERT INTO categoria (name, description) VALUES ('" + categoria.getName() + "', '" + categoria.getDescription() + "')";
            System.out.println(sql);
            if (st.executeUpdate(sql) == 1) {
                JOptionPane.showMessageDialog(null, "Categoria creada correctamente");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Object findById(String id, Connection cc) {
        try {
            Statement st = cc.createStatement();
            String sql = "SELECT * FROM categoria WHERE idCategoria = " + id;
            var rs = st.executeQuery(sql);
            if (rs.next()) {
                return new MCategoria(rs.getString("name"), rs.getInt("idCategoria"), rs.getString("description"));
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Object> findAll(String id, Connection cc) {
        ArrayList<Object> categorias = new ArrayList<>();
        try {
            Statement st = cc.createStatement();
            String sql = "SELECT * FROM categoria";
            var rs = st.executeQuery(sql);
            while (rs.next()) {
                categorias.add(new MCategoria(rs.getString("name"), rs.getInt("idCategoria"), rs.getString("description")));
            }
            return categorias;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public DefaultTableModel modelTable(Connection cc) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"No.", "ID", "Nombre", "Descripcion"});
        ArrayList<Object> categorias = findAll("", cc);
        for (int i = 0; i < categorias.size(); i++) {
            MCategoria categoria = (MCategoria) categorias.get(i);
            model.addRow(new Object[]{i + 1, categoria.getId(), categoria.getName(), categoria.getDescription()});
        }
        return model;
    }


    @Override
    public boolean update(Object object, Connection cc) {
        MCategoria categoria = (MCategoria) object;
        try {
            Statement st = cc.createStatement();
            String sql = "UPDATE categoria SET name = '" + categoria.getName() + "', description = '" + categoria.getDescription() + "' WHERE idCategoria = '" + categoria.getId()+"'";
            return st.executeUpdate(sql) == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id, Connection cc) {
        try {
            Statement st = cc.createStatement();
            String sql = "DELETE FROM categoria WHERE idCategoria = " + id;
            if (st.executeUpdate(sql) == 1) {
                return true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
