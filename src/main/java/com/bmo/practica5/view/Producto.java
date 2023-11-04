/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.bmo.practica5.view;

import com.bmo.practica5.controller.CProducto;
import com.bmo.practica5.controller.CProveedor;
import com.bmo.practica5.controller.Cateogria;
import com.bmo.practica5.models.MCategoria;
import com.bmo.practica5.models.MProducto;
import com.bmo.practica5.models.MProveedor;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author monje
 */
public class Producto extends javax.swing.JInternalFrame {

    /**
     * Creates new form proveedor
     */
    Connection ccPro;

    boolean edit = false;
    String ID = "";


    public Producto(Connection cc) {
        ccPro = cc;
        initComponents();
        start();
    }

    void start() {
        cls();
    }

    void cbCategory() {
        cbCategoria.removeAllItems();
        cbCategoria.addItem("Seleccione");

        ArrayList<Object> categorias = new Cateogria().findAll("", ccPro);
        for (Object categoria : categorias) {
            MCategoria cat = (MCategoria) categoria;
            cbCategoria.addItem(cat.getName());
        }
    }

    void cbProve() {
        cbProveedor.removeAllItems();
        cbProveedor.addItem("Seleccione");
        ArrayList<Object> proveedores = new CProveedor().findAll("", ccPro);
        for (Object proveedor : proveedores) {
            MProveedor prov = (MProveedor) proveedor;
            cbProveedor.addItem(prov.getNameEmpresa());
        }
    }


    void cls() {
        txtFind.setText("");
        txtDescription.setText("");
        txtNameProducto.setText("");
        txtPC.setText("");
        txtPV.setText("");
        spnStock.setValue(0);
        cbCategory();
        cbProve();
        table.setModel(new CProducto().tableModel(ccPro));
    }

    boolean validForm() {
        return txtDescription.getText().isEmpty() || txtNameProducto.getText().isEmpty() || txtPC.getText().isEmpty() ||
                txtPV.getText().isEmpty() || (spnStock.getValue().toString().equals("0"))
                || cbCategoria.getSelectedIndex() == 0 || cbProveedor.getSelectedIndex() == 0;
    }


    void save() {

        if (validForm()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
            return;
        }


        if (edit) {
            edit = false;
            MProducto producto = new MProducto(
                    Integer.parseInt(ID),
                    txtNameProducto.getText(),
                    txtDescription.getText(),
                    Integer.parseInt(spnStock.getValue().toString()),
                    Double.parseDouble(txtPV.getText()),
                    Double.parseDouble(txtPC.getText()),
                    cbProveedor.getSelectedIndex(),
                    cbCategoria.getSelectedIndex()
            );
            if (!new CProducto().update(producto, ccPro)) {
                JOptionPane.showMessageDialog(null, "Error al actualizar");
                return;
            }
        } else {
            MProducto producto = new MProducto(
                    txtNameProducto.getText(),
                    txtDescription.getText(),
                    Integer.parseInt(spnStock.getValue().toString()),
                    Double.parseDouble(txtPV.getText()),
                    Double.parseDouble(txtPC.getText()),
                    cbProveedor.getSelectedIndex(),
                    cbCategoria.getSelectedIndex()
            );

            new CProducto().create(producto, ccPro);
        }

        cls();


    }

    int getRowSelected() {
        return table.getSelectedRow();
    }

    void editView() {
        jTabbedPane1.setSelectedIndex(0);
        edit = true;
        ID = table.getValueAt(getRowSelected(), 1).toString();

        MProducto producto = (MProducto) new CProducto().findById(ID, ccPro);

        txtNameProducto.setText(producto.getNameProducto());
        txtDescription.setText(producto.getDescriptionProducto());
        txtPC.setText(String.valueOf(producto.getPrecioCompra()));
        txtPV.setText(String.valueOf(producto.getPrecioVenta()));
        spnStock.setValue(producto.getStock());
        cbCategoria.setSelectedIndex(producto.getIdCategoria());
        cbProveedor.setSelectedIndex(producto.getIdProveedor());


    }


    void delete() {
        var id = table.getValueAt(getRowSelected(), 1).toString();

        if (JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar el producto?",
                "Eliminar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (new CProducto().delete(id, ccPro)) {
                JOptionPane.showMessageDialog(null, "producto eliminado");
                cls();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        popMenuTable = new javax.swing.JPopupMenu();
        editBtn = new javax.swing.JMenuItem();
        deleteBtn = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNameProducto = new javax.swing.JTextField();
        cbCategoria = new javax.swing.JComboBox<>();
        cbProveedor = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPV = new javax.swing.JTextField();
        txtPC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        spnStock = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        btnCls1 = new javax.swing.JButton();
        btnSave1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnCSV = new javax.swing.JButton();

        jScrollPane1.setViewportView(jEditorPane1);

        editBtn.setText("Editar");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        popMenuTable.add(editBtn);

        deleteBtn.setText("Eliminar");
        deleteBtn.setToolTipText("");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        popMenuTable.add(deleteBtn);

        setClosable(true);
        setIconifiable(true);
        setTitle("Producto");
        setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Nombre:");

        txtNameProducto.setText("jTextField1");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        cbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Categoria: ");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Proveedor :");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Precio Venta:");

        txtPV.setText("jTextField1");

        txtPC.setText("jTextField1");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Precio Costo:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Stock:");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane3.setViewportView(txtDescription);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Descripcion");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel5)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtPV, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel6)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtPC, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(38, 38, 38)
                                                                .addComponent(txtNameProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(28, 28, 28)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(cbProveedor, 0, 139, Short.MAX_VALUE)
                                                                        .addComponent(cbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(spnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtNameProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spnStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel6)
                                                .addComponent(txtPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
        );

        btnCls1.setIcon(new javax.swing.ImageIcon("D:\\Documents\\practica5\\src\\main\\java\\com\\bmo\\practica5\\view\\img\\cleaning.png")); // NOI18N
        btnCls1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCls1ActionPerformed(evt);
            }
        });

        btnSave1.setIcon(new javax.swing.ImageIcon("D:\\Documents\\practica5\\src\\main\\java\\com\\bmo\\practica5\\view\\img\\diskette.png")); // NOI18N
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnSave1)
                                        .addComponent(btnCls1))
                                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(103, 103, 103)
                                                .addComponent(btnSave1)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnCls1)))
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingresar", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Buscar");

        txtFind.setText("jTextField1");

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        table.setComponentPopupMenu(popMenuTable);
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        jScrollPane2.setViewportView(table);

        btnCSV.setIcon(new javax.swing.ImageIcon("D:\\Documents\\practica5\\src\\main\\java\\com\\bmo\\practica5\\view\\img\\csv.png")); // NOI18N
        btnCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(169, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(193, 193, 193)
                                .addComponent(btnCSV)
                                .addGap(167, 167, 167))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2)
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1))
                                        .addComponent(btnCSV))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnCls1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCls1ActionPerformed
        // TODO add your handling code here:
        cls();
    }//GEN-LAST:event_btnCls1ActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        editView();
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void btnCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCSVActionPerformed
        // TODO add your handling code here:
        new CProducto().generateCSV(ccPro);
    }//GEN-LAST:event_btnCSVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCSV;
    private javax.swing.JButton btnCls1;
    private javax.swing.JButton btnSave1;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JComboBox<String> cbProveedor;
    private javax.swing.JMenuItem deleteBtn;
    private javax.swing.JMenuItem editBtn;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPopupMenu popMenuTable;
    private javax.swing.JSpinner spnStock;
    private javax.swing.JTable table;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtNameProducto;
    private javax.swing.JTextField txtPC;
    private javax.swing.JTextField txtPV;
    // End of variables declaration//GEN-END:variables
}
