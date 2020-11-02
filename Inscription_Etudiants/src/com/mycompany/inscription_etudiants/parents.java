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
public class parents extends javax.swing.JFrame {
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
                       model.addColumn("IdParent");
                       model.addColumn("NomPere");
                       model.addColumn("PrenomPere");
                       model.addColumn("Nommere");
                       model.addColumn("PrenomMere");
                       model.addColumn("Fonction Pere");
                       model.addColumn("Fonction Mere");
                       model.addColumn("Address");
                       try {
                            con = Connecter.getConnection();
                            stm = con.createStatement();
                            rs = stm.executeQuery("SELECT * FROM parents WHERE etat = 0 ORDER BY idp DESC");
                                    while(rs.next()){model.addRow(new Object[]{
                                        rs.getString("idp"),
                                        rs.getString("nompere"),
                                        rs.getString("prenompere"),
                                        rs.getString("nommere"),
                                        rs.getString("prenommere"),
                                        rs.getString("fonctionpere"),
                                        rs.getString("fonctionmere"),
                                        rs.getString("address")
                                    });
                                    }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Probleme d'affichage du tableau ! " + e.getLocalizedMessage());
                    }
                       TableIns.setModel(model);
                }
    //FIN du methode d'affichage
                
    //Creation du Methode d'Ajout
                public void AjouterParent(){
                        String NomPere = TxtNPere.getText().toUpperCase();
                        String PrenomPere = TxtPPere.getText();
                        String FonctionPere = TxtFPere.getText();
                        String NomMere = TxtNMere.getText();
                        String PrenomMere = TxtPMere.getText();
                        String FoctionMere = TxtFMere.getText();
                        String Address = TxtAdd.getText();
                        
                         if(
                           !TxtNPere.getText().isEmpty()&
                           !TxtPPere.getText().isEmpty()&
                           !TxtFPere.getText().isEmpty()&
                           !TxtNMere.getText().isEmpty()&
                           !TxtPMere.getText().isEmpty()&
                           !TxtFMere.getText().isEmpty()&
                           !TxtAdd.getText().isEmpty()
                           );
                         {
                             try{
                                 con = Connecter.getConnection();
                                    stm = con.createStatement();
                                    rs = stm.executeQuery("SELECT matricule FROM inscription WHERE etat = 0");
                                     String SqlRe = "insert into parents "
                                             + "(nompere,prenompere,"
                                             + "nommere,prenommere,"
                                             + "fonctionmere,fonctionpere,address)"
                                             + "VALUES('"+NomPere+"','"+PrenomPere+"'"
                                             + ",'"+NomMere+"','"+PrenomMere+"'"
                                             + ",'"+FonctionPere+"','"+FoctionMere+"'"
                                             + ",'"+Address+"')";
                                        stm.executeUpdate(SqlRe);
                                        

                                       }catch(Exception e)
                                       {
                                          JOptionPane.showMessageDialog(null, "Veuillez entrer les valeurs valides!!!"+e.getLocalizedMessage()); 
                                       }
                         }
                   }
    //Fin du Methode d'Ajout
                
                /* Methode Pour Acualiser */
                
                public void ActualiserParents(){
                       try {
                           model.setRowCount(0);
                            con = Connecter.getConnection();
                            stm = con.createStatement();
                            rs = stm.executeQuery("SELECT * FROM parents WHERE etat = 0 ORDER BY idp DESC");
                                    while(rs.next()){
                                           model.addRow(new Object[]{
                                                rs.getString("idp"),
                                                rs.getString("nompere"),
                                                rs.getString("prenompere"),
                                                rs.getString("nommere"),
                                                rs.getString("prenommere"),
                                                rs.getString("fonctionpere"),
                                                rs.getString("fonctionmere"),
                                                rs.getString("address")
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
                            
                           Txtidp.setText("");
                           TxtNPere.setText("");
                           TxtFPere.setText("");
                           TxtFMere.setText("");
                           TxtNMere.setText("");
                           TxtPPere.setText("");
                           TxtPMere.setText("");
                           TxtAdd.setText("");
                           
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Probleme de vider les zones de saisi ! " + e.getLocalizedMessage());
                    }
                }
                /* Fin Du Methode Pour Vide */
                
                /* Methode pour recuperer la ligne du tableau */
                public void Recuper(int i){
                        try {
                                Txtidp.setText(model.getValueAt(i, 0).toString());
                                TxtNPere.setText(model.getValueAt(i, 1).toString());
                                TxtPPere.setText(model.getValueAt(i, 2).toString());
                                TxtNMere.setText(model.getValueAt(i, 3).toString());
                                TxtPMere.setText(model.getValueAt(i, 4).toString());
                                TxtFPere.setText(model.getValueAt(i, 5).toString());
                                TxtFMere.setText(model.getValueAt(i, 6).toString());
                                TxtAdd.setText(model.getValueAt(i, 7).toString());
                                
                            } catch (Exception e) {
                         JOptionPane.showMessageDialog(null, "Probleme lors du clic sur la ligne du tableau ! " + e.getLocalizedMessage());
                    }
                }
                /*
                public ImageIcon scaledImage(ImageIcon img){
                    Image image = img.getImage();
                    image = image.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                    return new ImageIcon(image);
                }
                /* FIN du Methode pour recuperer la ligne du tableau */
                
                /* Methode Pour Modifier un Etudiant*/
                 public void ModifierParent(){
                     
                        if(
                           !TxtNPere.getText().isEmpty()&
                           !TxtFPere.getText().isEmpty()&
                           !TxtFMere.getText().isEmpty()&
                           !TxtNMere.getText().isEmpty()&
                           !TxtPPere.getText().isEmpty()&
                           !TxtPMere.getText().isEmpty()&
                           !TxtAdd.getText().isEmpty());
                   {
                    try{
                        con = Connecter.getConnection();
                           stm=con.createStatement();
                           
                           stm.executeUpdate("UPDATE inscription SET "
                                + "nompere='"+TxtNPere.getText()+"'"
                                + ",prenompere='"+ TxtPPere.getText()+"',"
                                + ",nommere='"+TxtNMere.getText()+"'"
                                + ",prenommere='"+TxtPMere.getText()+"'"
                                + ",fonctionpere='"+TxtFPere.getText()+"'" 
                                + ",fonctonmere='"+TxtFMere.getText()+"'"
                                + ",address='"+TxtAdd.getText()+"'"       
                                + " WHERE idp="+Txtidp.getText());
                  
                  ActualiserParents();
                  
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
       public void SupprimerParent(){
     
            if(!Txtidp.getText().equals(""));
                 {
                   try{
                       con = Connecter.getConnection();
                       stm=con.createStatement();
                       stm.executeUpdate("UPDATE parents SET etat = '" +ETAT+ "' WHERE idp="+Txtidp.getText());
                       ActualiserParents();
                       Vider();
                       JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès!!!");
                      }catch(Exception exe)
                      {
                       JOptionPane.showMessageDialog(null, "Veuillez sélectionner l'enregistrement que vous voulez supprimer!!!");   
                      }         
                  }
          }
       
       //Remplissage de la table
        public void RemplirTableauParennt(int i){
           try {
                    Txtidp.setText(model.getValueAt(i, 0).toString());
                    TxtNPere.setText(model.getValueAt(i, 1).toString());
                    TxtPPere.setText(model.getValueAt(i, 2).toString());
                    TxtNMere.setText(model.getValueAt(i, 3).toString());
                    TxtPMere.setText(model.getValueAt(i, 4).toString());
                    TxtFPere.setText(model.getValueAt(i, 5).toString());
                    TxtFMere.setText(model.getValueAt(i, 6).toString());
                    TxtAdd.setText(model.getValueAt(i, 7).toString());
                    
                }catch(Exception e){
                 
                JOptionPane.showMessageDialog(null,"erreur de replissage");
             }
           //TxtNom.requestFocus();
    }
        // Methode pour les combBox
                public String ComboRechercher(){
                    switch (ComboRecherch.getSelectedIndex()) {
                        case 0: return "idParent";
                        case 1: return "Nom Pere";
                        case 2: return "Prenom Pere";
                        case 3: return "Nom Mere";
                        case 4: return "Prenom mere";
                }
                return "";
}
        
    public parents() {
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Txtidp = new javax.swing.JTextField();
        TxtNPere = new javax.swing.JTextField();
        TxtFPere = new javax.swing.JTextField();
        TxtFMere = new javax.swing.JTextField();
        TxtNMere = new javax.swing.JTextField();
        TxtPPere = new javax.swing.JTextField();
        TxtPMere = new javax.swing.JTextField();
        TxtAdd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        BntActualiser = new javax.swing.JButton();
        ModifierEtudiant = new javax.swing.JButton();
        SuprimerEtudiant = new javax.swing.JButton();
        AjouterEtudiant = new javax.swing.JButton();
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

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("IdParent :");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nom Pere :");
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
        jLabel5.setText("Fonction Pere");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Fonction Mere");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Nom Mere :");
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
        jLabel10.setText("Prenom Pere :");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel10, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Prenom Mere  :");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 46;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Address :");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel13, gridBagConstraints);

        Txtidp.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(Txtidp, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtNPere, gridBagConstraints);

        TxtFPere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFPereActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 101;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtFPere, gridBagConstraints);

        TxtFMere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFMereActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtFMere, gridBagConstraints);

        TxtNMere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNMereActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtNMere, gridBagConstraints);

        TxtPPere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPPereActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtPPere, gridBagConstraints);

        TxtPMere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPMereActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtPMere, gridBagConstraints);

        TxtAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        jPanel1.add(TxtAdd, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setText("Ajouter EtudiantID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        jPanel1.add(jLabel3, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        jPanel1.add(jComboBox1, gridBagConstraints);

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

        jButton1.setBackground(new java.awt.Color(0, 255, 204));
        jButton1.setText("DashBoard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        getContentPane().add(jPanel5);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtFPereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFPereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFPereActionPerformed

    private void TxtFMereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFMereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFMereActionPerformed

    private void ModifierEtudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierEtudiantActionPerformed
        try {
                ModifierParent();
                Vider();
                ActualiserParents();
                        
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"erreur de modification" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_ModifierEtudiantActionPerformed

    private void SuprimerEtudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuprimerEtudiantActionPerformed
                SupprimerParent();
                Vider();
                ActualiserParents();
    }//GEN-LAST:event_SuprimerEtudiantActionPerformed

    private void AjouterEtudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterEtudiantActionPerformed
                AjouterParent();
                Vider();
                ActualiserParents();
    }//GEN-LAST:event_AjouterEtudiantActionPerformed

    private void RechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RechercherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RechercherActionPerformed

    private void RechercherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RechercherKeyReleased
        try {
                if(Rechercher.getText().equals("")){
                    
                }else{
                        model.setColumnIdentifiers(new String[]{
                            "IdParent", 
                            "Nom Pere", 
                            "Prenom Pere", 
                            "Nom Mere", 
                            "Prenom Mere", 
                            "Fonction Pere", 
                            "Fonction Mere",
                            "Address"
                        });
                        boolean Verify = true;
                        model.setRowCount(0);
                        stm = con.createStatement();
                        
                        rs = stm.executeQuery("SELECT * FROM parents WHERE etat = 0 AND "+ ComboRechercher() +" LIKE '%" +Rechercher.getText()+"%' ");
                        while(rs.next()){
                            Verify = false;
                             model.addRow(new Object[]{
                                rs.getString("idp"),
                                rs.getString("nompere"),
                                rs.getString("prenompere"),
                                rs.getString("nommere"),
                                rs.getString("prenommere"),
                                rs.getString("fonctionpere"),
                                rs.getString("fonctionmere"),
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

    private void TxtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtAddActionPerformed

    private void TxtPMereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPMereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPMereActionPerformed

    private void TxtPPereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPPereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPPereActionPerformed

    private void TxtNMereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNMereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNMereActionPerformed

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
            java.util.logging.Logger.getLogger(parents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(parents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(parents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(parents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new parents().setVisible(true);
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
    private javax.swing.JTextField TxtAdd;
    private javax.swing.JTextField TxtFMere;
    private javax.swing.JTextField TxtFPere;
    private javax.swing.JTextField TxtNMere;
    private javax.swing.JTextField TxtNPere;
    private javax.swing.JTextField TxtPMere;
    private javax.swing.JTextField TxtPPere;
    private javax.swing.JTextField Txtidp;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
