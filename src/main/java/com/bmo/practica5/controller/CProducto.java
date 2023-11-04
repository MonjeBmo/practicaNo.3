package com.bmo.practica5.controller;


import com.bmo.practica5.config.Conn;
import com.bmo.practica5.config.Methods;
import com.bmo.practica5.models.MProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class CProducto extends Methods {


    @Override
    public void create(Object object, Connection cc) {
        MProducto product = (MProducto) object;
        try {
            Statement st = cc.createStatement();
            String sql = new StringBuilder()
                    .append("INSERT INTO producto (nameProducto, descriptionProducto, stock, precioVenta, precioCosto, idProveedor, idCategoria) ")
                    .append("VALUES ('").append(product.getNameProducto())
                    .append("', '").append(product.getDescriptionProducto())
                    .append("', '").append(product.getStock())
                    .append("', '").append(product.getPrecioVenta())
                    .append("', '").append(product.getPrecioCompra())
                    .append("', '").append(product.getIdProveedor())
                    .append("', '").append(product.getIdCategoria())
                    .append("')").toString();
            if (st.executeUpdate(sql) == 1) {
                JOptionPane.showMessageDialog(null, "Producto registrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Object findById(String id, Connection cc) {
        try {
            Statement st = cc.createStatement();
            String sql = new StringBuilder()
                    .append("SELECT * FROM producto WHERE idProducto = ")
                    .append(id).toString();
            var rs = st.executeQuery(sql);
            if (rs.next()) {
                return new MProducto(
                        rs.getInt("idProducto"),
                        rs.getString("nameProducto"),
                        rs.getString("descriptionProducto"),
                        rs.getInt("stock"),
                        rs.getDouble("precioVenta"),
                        rs.getDouble("precioCompra"),
                        rs.getInt("idProveedor"),
                        rs.getInt("idCategoria"));
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<Object> findAll(String id, Connection cc) {
        ArrayList<Object> productos = new ArrayList<>();

        try {
            Statement st = cc.createStatement();
            String sql = new StringBuilder()
                    .append("SELECT * FROM producto").toString();
            var rs = st.executeQuery(sql);
            while (rs.next()) {
                productos.add(new MProducto(
                        rs.getInt("idProducto"),
                        rs.getString("nameProducto"),
                        rs.getString("descriptionProducto"),
                        rs.getInt("stock"),
                        rs.getDouble("precioVenta"),
                        rs.getDouble("precioCosto"),
                        rs.getInt("idProveedor"),
                        rs.getInt("idCategoria")));
            }
            return productos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean update(Object object, Connection cc) {
        try {
            Statement st = cc.createStatement();
            String sql = new StringBuilder()
                    .append("UPDATE producto SET ")
                    .append("nameProducto = '").append(((MProducto) object).getNameProducto())
                    .append("', descriptionProducto = '").append(((MProducto) object).getDescriptionProducto())
                    .append("', stock = '").append(((MProducto) object).getStock())
                    .append("', precioVenta = '").append(((MProducto) object).getPrecioVenta())
                    .append("', precioCompra = '").append(((MProducto) object).getPrecioCompra())
                    .append("', idProveedor = '").append(((MProducto) object).getIdProveedor())
                    .append("', idCategoria = '").append(((MProducto) object).getIdCategoria())
                    .append("' WHERE idProducto = ").append(((MProducto) object).getIdProducto()).toString();
            if (st.executeUpdate(sql) == 1) {
                JOptionPane.showMessageDialog(null, "Producto actualizado");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id, Connection cc) {
        try {
            Statement st = cc.createStatement();
            String sql = new StringBuilder()
                    .append("DELETE FROM producto WHERE idProducto = ")
                    .append(id).toString();
            if (st.executeUpdate(sql) == 1) {
                JOptionPane.showMessageDialog(null, "Producto eliminado");
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public DefaultTableModel tableModel(Connection cc) {
        DefaultTableModel model = new DefaultTableModel();
        ArrayList<Object> productos = findAll("", cc);
        model.setColumnIdentifiers(new String[]{"ID", "Nombre", "Descripcion", "Stock", "Precio Venta", "Precio Costo", "Proveedor", "Categoria"});

        for (Object producto : productos) {
            model.addRow(new Object[]{
                    ((MProducto) producto).getIdProducto(),
                    ((MProducto) producto).getNameProducto(),
                    ((MProducto) producto).getDescriptionProducto(),
                    ((MProducto) producto).getStock(),
                    ((MProducto) producto).getPrecioVenta(),
                    ((MProducto) producto).getPrecioCompra(),
                    ((MProducto) producto).getIdProveedor(),
                    ((MProducto) producto).getIdCategoria()
            });
        }


        return model;
    }

    //function generate csv file
    public void generateCSV(Connection cc) {
        try {
            String path = "src/main/java/com/bmo/practica5/data/user.csv";
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(path, true);
            ArrayList<Object> productos = findAll("", cc);
            if (file.length() == 0) {
                fileWriter.write("Nombre, PrecioCosto, PrecioVenta, Stock, Descripcion, No.Proveedor, No.Categoria\n");
            }

            for (Object producto : productos) {
                fileWriter.write(((MProducto) producto).getNameProducto()
                        + "," + ((MProducto) producto).getPrecioCompra()
                        + "," + ((MProducto) producto).getPrecioVenta()
                        + "," + ((MProducto) producto).getStock()
                        + "," + ((MProducto) producto).getDescriptionProducto()
                        + "," + ((MProducto) producto).getIdProveedor()
                        + "," + ((MProducto) producto).getIdCategoria() + "\n");
            }

            fileWriter.close();

        } catch (Exception e) {
            System.out.println("An error occurred." + e.getMessage());
        }
    }

}
