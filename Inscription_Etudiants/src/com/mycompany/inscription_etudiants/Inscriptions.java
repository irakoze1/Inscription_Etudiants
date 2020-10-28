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
public class Inscriptions extends javax.swing.JFrame {
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
                public void AfficherEtudiants(){
                       model.addColumn("Matricule");
                       model.addColumn("Nom");
                       model.addColumn("Prenom");
                       model.addColumn("Date Naissance");
                       model.addColumn("Sexe");
                       model.addColumn("Photo");
                       model.addColumn("Lieu de Naissance");
                       model.addColumn("Lieu de Residence");
                       model.addColumn("Anne Academic");
                       model.addColumn("Faculite");
                       model.addColumn("Pere");
                       model.addColumn("Mere");
                       try {
                            con = Connecter.getConnection();
                            stm = con.createStatement();
                            rs = stm.executeQuery("SELECT * FROM inscription WHERE etat = 0 ORDER BY matricule DESC");
                                    while(rs.next()){model.addRow(new Object[]{
                                        rs.getString("matricule"),
                                        rs.getString("nom"),
                                        rs.getString("prenom"),
                                        rs.getString("datenaissance"),
                                        rs.getString("sexe"),
                                        rs.getString("photo"),
                                        rs.getString("lieunaissance"),
                                        rs.getString("lieuresidence"),
                                        rs.getString("anneacademic"),
                                        rs.getString("faculite"),
                                        rs.getString("pere"),
                                        rs.getString("mere")
                                    });
                                    }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Probleme d'affichage du tableau ! " + e.getLocalizedMessage());
                    }
                       TableIns.setModel(model);
                }
    //FIN du methode d'affichage
                
    //Creation du Methode d'Ajout
                public void AjouterEtudiant(){
                        String Nom = TxtN.getText().toUpperCase();
                        String Prenom = TxtP.getText();
                        String DateNaissance = TxtD.getText();
                        String Sexe = TxtC1.getSelectedItem().toString();
                        String Photo = txt_choosen_file.getText();
                        String LieuNaissance = TxtNai.getText();
                        String LieuResidence = TxtRes.getText();
                        String AnneAcademic = TxtAn.getText();
                        String Faculite = TxtC2.getSelectedItem().toString();
                        String Pere = TxtPe.getText();
                        String Mere = TxtMe.getText();
                        
                         if(!TxtN.getText().equals("")&&
                           (!TxtP.getText().equals(""))&&
                           (!TxtD.getText().equals(""))&&
                           (!TxtC1.getSelectedItem().equals(""))&&
                           (!txt_choosen_file.getText().equals(""))&&
                           (!TxtNai.getText().equals(""))&&
                           (!TxtRes.getText().equals(""))&&
                           (!TxtAn.getText().equals(""))&&
                           (!TxtC2.getSelectedItem().equals(""))&&
                           (!TxtPe.getText().equals(""))&&
                           (!TxtMe.getText().equals("")));
                         {
                             try{
                                 con = Connecter.getConnection();
                                    stm = con.createStatement();
                                     String SqlRe = "insert into inscription "
                                             + "(nom,prenom,datenaissance,"
                                             + "sexe,photo,lieunaissance,"
                                             + "lieuresidence,anneacademic,"
                                             + "faculite,pere,mere)"
                                             + "VALUES('"+Nom+"','"+Prenom+"'"
                                             + ",'"+DateNaissance+"','"+Sexe+"'"
                                             + ",'"+Photo+"','"+LieuNaissance+"'"
                                             + ",'"+LieuResidence+"','"+AnneAcademic+"'"
                                             + ",'"+Faculite+"','"+Pere+"','"+Mere+"')";
                                        stm.executeUpdate(SqlRe);         

                                       }catch(Exception e)
                                       {
                                          JOptionPane.showMessageDialog(null, "Veuillez entrer les valeurs valides!!!"+e.getLocalizedMessage()); 
                                       }
                         }
                   }
    //Fin du Methode d'Ajout
                
                /* Methode Pour Acualiser */
                
                public void ActualiserEtudiants(){
                       try {
                           model.setRowCount(0);
                            con = Connecter.getConnection();
                            stm = con.createStatement();
                            rs = stm.executeQuery("SELECT * FROM inscription WHERE etat = 0 ORDER BY matricule DESC");
                                    while(rs.next()){
                                           model.addRow(new Object[]{
                                                rs.getString("matricule"),
                                                rs.getString("nom"),
                                                rs.getString("prenom"),
                                                rs.getString("datenaissance"),
                                                rs.getString("sexe"),
                                                rs.getString("photo"),
                                                rs.getString("lieunaissance"),
                                                rs.getString("lieuresidence"),
                                                rs.getString("anneacademic"),
                                                rs.getString("faculite"),
                                                rs.getString("pere"),
                                                rs.getString("mere")
                                           });
                                    }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Probleme d'affichage du tableau ! " + e.getLocalizedMessage());
                    }
                       TableIns.setModel(model);
                }
                /* FIn du Methode Pour Acualiser */
                
                /* Methode Pour Vide */
                public void Vider(){
                        try {
                            
                           TxtM.setText("");
                           TxtN.setText("");
                           TxtP.setText("");
                           TxtD.setText("");
                           txt_choosen_file.setText("");
                           TxtNai.setText("");
                           TxtRes.setText("");
                           TxtAn.setText("");
                           TxtPe.setText("");
                           TxtMe.setText("");
                           
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Probleme de vider les zones de saisi ! " + e.getLocalizedMessage());
                    }
                }
                /* Fin Du Methode Pour Vide */
                
                /* Methode pour recuperer la ligne du tableau */
                public void Recuper(int i){
                        try {
                                TxtM.setText(model.getValueAt(i, 0).toString());
                                TxtN.setText(model.getValueAt(i, 1).toString());
                                TxtP.setText(model.getValueAt(i, 2).toString());
                                TxtD.setText(model.getValueAt(i, 3).toString());
                                TxtC1.setSelectedItem(model.getValueAt(i, 4).toString());
                                ImageIcon image = new ImageIcon(model.getValueAt(i, 5).toString());
                                lbl_image.setIcon(scaledImage(image));
                                TxtNai.setText(model.getValueAt(i, 6).toString());
                                TxtRes.setText(model.getValueAt(i, 7).toString());
                                TxtAn.setText(model.getValueAt(i, 8).toString());
                                TxtC2.setSelectedItem(model.getValueAt(i, 9).toString());
                                TxtPe.setText(model.getValueAt(i, 10).toString());
                                TxtMe.setText(model.getValueAt(i, 11).toString());
                            } catch (Exception e) {
                         JOptionPane.showMessageDialog(null, "Probleme lors du clic sur la ligne du tableau ! " + e.getLocalizedMessage());
                    }
                }
                public ImageIcon scaledImage(ImageIcon img){
                    Image image = img.getImage();
                    image = image.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                    return new ImageIcon(image);
                }
                /* FIN du Methode pour recuperer la ligne du tableau */
                
                /* Methode Pour Modifier un Etudiant*/
                 public void ModifierEtudiant(){
                     
                        if(
                           !TxtN.getText().isEmpty()&
                           !TxtP.getText().isEmpty()&
                           !TxtD.getText().isEmpty()&
                           !TxtC1.getSelectedItem().toString().isEmpty()&
                           !txt_choosen_file.getText().isEmpty()&
                           !TxtNai.getText().isEmpty()&
                           !TxtRes.getText().isEmpty()&
                           !TxtAn.getText().isEmpty()&
                           !TxtC2.getSelectedItem().toString().isEmpty()&
                           !TxtPe.getText().isEmpty()&
                           !TxtMe.getText().isEmpty());
                   {
                    try{
                        con = Connecter.getConnection();
                           stm=con.createStatement();
                           
                           stm.executeUpdate("UPDATE inscription SET "
                                + "nom='"+TxtN.getText()+"'"
                                + ",prenom='"+ TxtP.getText()+"',"
                                + "datenaissance='"+TxtD.getText()+"'"
                                + ",sexe='"+TxtC1.getSelectedItem()+"'"
                                + ",photo='"+img.getAbsolutePath()+"'"
                                + ",lieunaissance='"+TxtNai.getText()+"'"
                                + ",lieuresidence='"+TxtRes.getText()+"'"
                                + ",anneacademic='"+TxtAn.getText()+"'"   
                                + ",faculite='"+TxtC2.getSelectedItem()+"'"
                                + ",pere='"+TxtPe.getText()+"'"
                                + ",mere='"+TxtMe.getText()+"'"       
                                + " WHERE matricule="+TxtM.getText());
                  
                  ActualiserEtudiants();
                  
                  JOptionPane.showMessageDialog(null, "Modification effectuée!!!");
                 }catch(HeadlessException | SQLException e)
                 {
                     System.err.println(e);
                // JOptionPane.showMessageDialog(null, 
                   //      "Veuillez sélectionner l'enregistrement "
                     //            + "que vous voulez modifier!!!");   
                 }         
             }
          }
        /* FIN du Methode Pour Modifier un Etudiant*/
      
      //Suppression des données
       public void SupprimerEtudienat(){
     
            if(!TxtM.getText().equals(""));
                 {
                   try{
                       con = Connecter.getConnection();
                       stm=con.createStatement();
                       stm.executeUpdate("UPDATE inscription SET etat = '" +ETAT+ "' WHERE matricule="+TxtM.getText());
                       ActualiserEtudiants();
                       Vider();
                       JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès!!!");
                      }catch(Exception exe)
                      {
                       JOptionPane.showMessageDialog(null, "Veuillez sélectionner l'enregistrement que vous voulez supprimer!!!");   
                      }         
                  }
          }
       
       //Remplissage de la table
        public void RemplirTableauEtudiant(int i){
           try {
                    TxtM.setText(model.getValueAt(i, 0).toString());
                    TxtN.setText(model.getValueAt(i, 1).toString());
                    TxtP.setText(model.getValueAt(i, 2).toString());
                    TxtD.setText(model.getValueAt(i, 3).toString());
                    TxtC1.setSelectedItem(model.getValueAt(i, 4).toString());
                    txt_choosen_file.setText(model.getValueAt(i, 5).toString());
                    TxtNai.setText(model.getValueAt(i, 6).toString());
                    TxtRes.setText(model.getValueAt(i, 7).toString());
                    TxtAn.setText(model.getValueAt(i, 8).toString());
                    TxtC2.setSelectedItem(model.getValueAt(i, 9).toString());
                    TxtPe.setText(model.getValueAt(i, 10).toString());
                    TxtMe.setText(model.getValueAt(i, 11).toString());
                    
                }catch(Exception e){
                 
                JOptionPane.showMessageDialog(null,"erreur de replissage");
             }
           //TxtNom.requestFocus();
    }
        // Methode pour les combBox
                public String ComboRechercher(){
                    switch (ComboRecherch.getSelectedIndex()) {
                        case 0: return "Matricule";
                        case 1: return "Nom";
                        case 2: return "Prenom";
                        case 3: return "Pere";
                        case 4: return "Mere";
                }
                return "";
}
        
    public Inscriptions() {
        this.model = new DefaultTableModel();
        initComponents();
        AfficherEtudiants();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TxtC1 = new javax.swing.JComboBox<>();
        TxtM = new javax.swing.JTextField();
        TxtN = new javax.swing.JTextField();
        TxtP = new javax.swing.JTextField();
        TxtD = new javax.swing.JTextField();
        TxtNai = new javax.swing.JTextField();
        TxtRes = new javax.swing.JTextField();
        TxtAn = new javax.swing.JTextField();
        TxtPe = new javax.swing.JTextField();
        TxtMe = new javax.swing.JTextField();
        TxtC2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        txt_choosen_file = new javax.swing.JLabel();
        panel_image = new javax.swing.JPanel();
        lbl_image = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        BntActualiser = new javax.swing.JButton();
        ModifierEtudiant = new javax.swing.JButton();
        SuprimerEtudiant = new javax.swing.JButton();
        AjouterEtudiant = new javax.swing.JButton();

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

        ComboRecherch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matricule", "Nom", "Prenom", "Pere", "Mere" }));
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
                "Matricule", "Nom", "Prenom", "DateNaissance", "Sexe", "Photo", "LieuNaissance", "LieuResidence", "Anne Academic", "Pere", "Mere"
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

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Matricule :");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("DateNaiss :");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nom :");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Prenom :");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("sexe :");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Lieu Naiss :");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Residence :");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("An./Ac. :");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Faculite :");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 46;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Pere :");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 46;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Mere :");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel13, gridBagConstraints);

        TxtC1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculin", "Feminin", "Autres" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtC1, gridBagConstraints);

        TxtM.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtM, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtN, gridBagConstraints);

        TxtP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 101;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtP, gridBagConstraints);

        TxtD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 101;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtD, gridBagConstraints);

        TxtNai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNaiActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtNai, gridBagConstraints);

        TxtRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtResActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtRes, gridBagConstraints);

        TxtAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtAnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtAn, gridBagConstraints);

        TxtPe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtPe, gridBagConstraints);

        TxtMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtMeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtMe, gridBagConstraints);

        TxtC2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BST", "BAM" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtC2, gridBagConstraints);

        jButton1.setText("Photo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jButton1, gridBagConstraints);

        txt_choosen_file.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txt_choosen_file.setText("no file choosed.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(txt_choosen_file, gridBagConstraints);

        panel_image.setBackground(new java.awt.Color(0, 153, 204));
        panel_image.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(), javax.swing.BorderFactory.createCompoundBorder()));
        panel_image.setMinimumSize(new java.awt.Dimension(100, 100));
        panel_image.setPreferredSize(new java.awt.Dimension(100, 100));

        lbl_image.setPreferredSize(new java.awt.Dimension(90, 90));

        javax.swing.GroupLayout panel_imageLayout = new javax.swing.GroupLayout(panel_image);
        panel_image.setLayout(panel_imageLayout);
        panel_imageLayout.setHorizontalGroup(
            panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_imageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_imageLayout.setVerticalGroup(
            panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_imageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 7;
        jPanel1.add(panel_image, gridBagConstraints);

        jPanel5.add(jPanel1);

        BntActualiser.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        BntActualiser.setText("Actualiser");
        BntActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BntActualiserActionPerformed(evt);
            }
        });

        ModifierEtudiant.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        ModifierEtudiant.setText("Modifier");
        ModifierEtudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierEtudiantActionPerformed(evt);
            }
        });

        SuprimerEtudiant.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        SuprimerEtudiant.setText("Suprimer");
        SuprimerEtudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuprimerEtudiantActionPerformed(evt);
            }
        });

        AjouterEtudiant.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        AjouterEtudiant.setText("Ajouter");
        AjouterEtudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterEtudiantActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AjouterEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ModifierEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SuprimerEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BntActualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AjouterEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModifierEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SuprimerEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BntActualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.add(jPanel2);

        getContentPane().add(jPanel5);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPActionPerformed

    private void TxtDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDActionPerformed

    private void TxtNaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNaiActionPerformed

    private void ModifierEtudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierEtudiantActionPerformed
        try {
                ModifierEtudiant();
                        
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"erreur de modification" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_ModifierEtudiantActionPerformed

    private void SuprimerEtudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuprimerEtudiantActionPerformed
        SupprimerEtudienat();
    }//GEN-LAST:event_SuprimerEtudiantActionPerformed

    private void AjouterEtudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterEtudiantActionPerformed
        AjouterEtudiant();
    }//GEN-LAST:event_AjouterEtudiantActionPerformed

    private void RechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RechercherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RechercherActionPerformed

    private void RechercherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RechercherKeyReleased
        try {
                if(Rechercher.getText().equals("")){
                    
                }else{
                        model.setColumnIdentifiers(new String[]{"Matricule", "Nom", "Prenom", "DateNaissance", "Sexe", "Photo", "LieuNaissance", "LieuResidence","AnneAcademic","Faculite","Pere","Mere"});
                        boolean Verify = true;
                        model.setRowCount(0);
                        stm = con.createStatement();
                        
                        rs = stm.executeQuery("SELECT * FROM inscription WHERE etat = 0 AND "+ ComboRechercher() +" LIKE '%" +Rechercher.getText()+"%' ");
                        while(rs.next()){
                            Verify = false;
                             model.addRow(new Object[]{
                                rs.getString("matricule"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("datenaissance"),
                                rs.getString("sexe"),
                                rs.getString("photo"),
                                rs.getString("lieunaissance"),
                                rs.getString("lieuresidence"),
                                rs.getString("anneacademic"),
                                rs.getString("faculite"),
                                rs.getString("pere"),
                                rs.getString("mere")
                             });
                        }
                        if(Verify){
                             JOptionPane.showMessageDialog(null,"Desolef il n'y a pas des resultats sur votre recherche");
                        }
                        TableIns.setModel(model);
                }
        } catch (HeadlessException | SQLException e) {
             JOptionPane.showMessageDialog(null,"erreur lors de la recherche" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_RechercherKeyReleased

    private void BntActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BntActualiserActionPerformed
            ActualiserEtudiants();
            Vider();
    }//GEN-LAST:event_BntActualiserActionPerformed

    private void TableInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableInsMouseClicked
        try {
            int i = TableIns.getSelectedRow();
            Recuper(i);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"erreur de replissage" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_TableInsMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fc = new JFileChooser();
        int response = fc.showOpenDialog(this);
        if(response==JFileChooser.APPROVE_OPTION){
            img = fc.getSelectedFile();
            txt_choosen_file.setText(img.getName());
            ImageIcon image = new ImageIcon(img.getAbsolutePath());
            lbl_image.setIcon(scaledImage(image));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TxtMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtMeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtMeActionPerformed

    private void TxtPeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPeActionPerformed

    private void TxtAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtAnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtAnActionPerformed

    private void TxtResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtResActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtResActionPerformed

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
            java.util.logging.Logger.getLogger(Inscriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inscriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inscriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inscriptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inscriptions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AjouterEtudiant;
    private javax.swing.JButton BntActualiser;
    private javax.swing.JComboBox<String> ComboRecherch;
    private javax.swing.JButton ModifierEtudiant;
    private javax.swing.JTextField Rechercher;
    private javax.swing.JButton SuprimerEtudiant;
    private javax.swing.JTable TableIns;
    private javax.swing.JTextField TxtAn;
    private javax.swing.JComboBox<String> TxtC1;
    private javax.swing.JComboBox<String> TxtC2;
    private javax.swing.JTextField TxtD;
    private javax.swing.JTextField TxtM;
    private javax.swing.JTextField TxtMe;
    private javax.swing.JTextField TxtN;
    private javax.swing.JTextField TxtNai;
    private javax.swing.JTextField TxtP;
    private javax.swing.JTextField TxtPe;
    private javax.swing.JTextField TxtRes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JPanel panel_image;
    private javax.swing.JLabel txt_choosen_file;
    // End of variables declaration//GEN-END:variables
}
