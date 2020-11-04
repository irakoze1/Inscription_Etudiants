/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inscription_etudiants;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tititofirakoze
 */
public class parents_et_enfant extends javax.swing.JFrame {
    /**
     * Creates new form Inscriptions
     */
    /*  Declarations des variables */
                private PreparedStatement pstm = null;
                private ResultSet rs = null;
                private Connection con = null;
                private Statement stm;
                private final DefaultTableModel model;
                private final int ETAT = 1;
                private File img;
        
    // Methode pour affichage 
                public void AfficherParents(){
                       model.addColumn("NomPere");
                       model.addColumn("PrenomPere");
                       model.addColumn("Nommere");
                       model.addColumn("PrenomMere");
                       model.addColumn("Nom Enfant");
                       model.addColumn("Prenom enfant");
                       model.addColumn("Address");
                       try {
                            con = Connecter.getConnection();
                            stm = con.createStatement();
                            rs = stm.executeQuery("SELECT nompere,prenompere,nommere,prenommere,address,nom,prenom"
                                    + " FROM parents join inscription on parents.idp = inscription.matricule");
                                    while(rs.next()){model.addRow(new Object[]{
                                        rs.getString("nompere"),
                                        rs.getString("prenompere"),
                                        rs.getString("nommere"),
                                        rs.getString("prenommere"),
                                        rs.getString("nom"),
                                        rs.getString("prenom"),
                                        rs.getString("address")
                                    });
                                    }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Probleme d'affichage du tableau ! " + e.getLocalizedMessage());
                    }
                       TableIns.setModel(model);
                }
    //FIN du methode d'affichage
                
                /* Methode Pour Acualiser */
                
                public void ActualiserParents(){
                       try {
                           model.setRowCount(0);
                            con = Connecter.getConnection();
                            stm = con.createStatement();
                            rs = stm.executeQuery("SELECT nompere,prenompere,nommere,prenommere,address,nom,prenom "
                                    + "FROM parents join inscription on parents.idp = inscription.matricule");
                                    while(rs.next()){
                                           model.addRow(new Object[]{
                                                rs.getString("nompere"),
                                                rs.getString("prenompere"),
                                                rs.getString("nommere"),
                                                rs.getString("prenommere"),
                                                rs.getString("nom"),
                                                rs.getString("prenom"),
                                                rs.getString("address")
                                           });
                                    }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Probleme d'affichage du tableau ! " + e.getLocalizedMessage());
                    }
                       TableIns.setModel(model);
                }
                /* FIn du Methode Pour Acualiser */
       
        // Methode pour les combBox
                public String ComboRechercher(){
                    switch (ComboRecherch.getSelectedIndex()) {
                        case 0: return "Nom Pere";
                        case 1: return "Prenom Pere";
                        case 2: return "Nom Mere";
                        case 3: return "Prenom mere";
                        case 4: return "Nom Enfant";
                        case 5: return "Prenom Enfant";
                }
                return "";
        }
    public parents_et_enfant() {
        this.model = new DefaultTableModel();
        initComponents();
        AfficherParents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Rechercher = new javax.swing.JTextField();
        ComboRecherch = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableIns = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        BntActualiser = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

        jPanel4.setLayout(new java.awt.GridBagLayout());

        Rechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RechercherActionPerformed(evt);
            }
        });
        Rechercher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RechercherKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(Rechercher, gridBagConstraints);

        ComboRecherch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IdParent", "NomPere", "PrenomPere", "NomMere", "PrenomMere" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(ComboRecherch, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INSCRIPTIONS ETUDIANTS BIU");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanel4.add(jLabel1, gridBagConstraints);

        jPanel5.add(jPanel4);

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {1};
        jPanel3Layout.rowWeights = new double[] {2.0};
        jPanel3.setLayout(jPanel3Layout);

        TableIns.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdParent", "NomPere", "PrenomPere", "NomMere", "PrenomMere", "Fonction Pere", "Fonction Mere", "Address"
            }
        ));
        TableIns.setShowGrid(true);
        TableIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableInsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableIns);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.9;
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jPanel5.add(jPanel3);

        BntActualiser.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        BntActualiser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/actor.png"))); // NOI18N
        BntActualiser.setText("Actualiser");
        BntActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BntActualiserActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 255, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard.png"))); // NOI18N
        jButton1.setText("DashBoard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(BntActualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(386, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(BntActualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel2);

        getContentPane().add(jPanel5);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RechercherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RechercherActionPerformed

    private void RechercherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RechercherKeyReleased
        try {
                if(Rechercher.getText().equals("")){
                    
                }else{
                        model.setColumnIdentifiers(new String[]{
                            "Nom Pere", 
                            "Prenom Pere", 
                            "Nom Mere", 
                            "Prenom Mere",  
                            "Nom Enfant", 
                            "Prenom Enfant", 
                            "Address"
                        });
                        boolean Verify = true;
                        model.setRowCount(0);
                        stm = con.createStatement();
                        
                        rs = stm.executeQuery("SELECT nompere,prenompere,nommere,prenommere,address,nom,prenom "
                                    + "FROM parents join inscription on parents.idp = inscription.matricule "+ ComboRechercher() +" LIKE '%" +Rechercher.getText()+"%' ");
                        while(rs.next()){
                            Verify = false;
                             model.addRow(new Object[]{
                                rs.getString("nompere"),
                                rs.getString("prenompere"),
                                rs.getString("nommere"),
                                rs.getString("prenommere"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("address")
                             });
                        }
                        if(Verify){
                             JOptionPane.showMessageDialog(null,"Desole il n'y a pas des resultats sur votre recherche");
                        }
                        TableIns.setModel(model);
                }
        } catch (HeadlessException | SQLException e) {
             JOptionPane.showMessageDialog(null,"erreur lors de la recherche" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_RechercherKeyReleased

    private void BntActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BntActualiserActionPerformed
            ActualiserParents();
    }//GEN-LAST:event_BntActualiserActionPerformed

    private void TableInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableInsMouseClicked
      
    }//GEN-LAST:event_TableInsMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new DashBoard().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(parents_et_enfant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(parents_et_enfant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(parents_et_enfant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(parents_et_enfant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new parents_et_enfant().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BntActualiser;
    private javax.swing.JComboBox<String> ComboRecherch;
    private javax.swing.JTextField Rechercher;
    private javax.swing.JTable TableIns;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
