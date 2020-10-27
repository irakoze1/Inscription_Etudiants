/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inscription_etudiants;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
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
                        String Photo = TxtPh.getText();
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
                           (!TxtPh.getText().equals(""))&&
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
                           TxtPh.setText("");
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
                                TxtPh.setText(model.getValueAt(i, 5).toString());
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
                /* FIN du Methode pour recuperer la ligne du tableau */
                
                /* Methode Pour Modifier un Etudiant*/
                 public void ModifierEtudiant(){
                     
                        if(
                           !TxtN.getText().equals("")&&
                           (!TxtP.getText().equals(""))&&
                           (!TxtD.getText().equals(""))&&
                           (!TxtC1.getSelectedItem().equals(""))&&
                           (!TxtPh.getText().equals(""))&&
                           (!TxtNai.getText().equals(""))&&
                           (!TxtRes.getText().equals(""))&&
                           (!TxtAn.getText().equals(""))&&
                           (!TxtC2.getSelectedItem().equals(""))&&
                           (!TxtPe.getText().equals(""))&&
                           (!TxtMe.getText().equals("")));
                   {
                    try{
                        con = Connecter.getConnection();
                           stm=con.createStatement();
                           
                           stm.executeUpdate("UPDATE inscription SET "
                                + "nom='"+TxtN.getText()+"'"
                                + ",prenom='"+ TxtP.getText()+"',"
                                + "datenaissance='"+TxtD.getText()+"'"
                                + ",sexe='"+TxtC1.getSelectedItem()+"'"
                                + ",photo='"+TxtPh.getText()+"'"
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
                    TxtPh.setText(model.getValueAt(i, 5).toString());
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TxtC1 = new javax.swing.JComboBox<>();
        TxtM = new javax.swing.JTextField();
        TxtN = new javax.swing.JTextField();
        TxtPh = new javax.swing.JTextField();
        TxtP = new javax.swing.JTextField();
        TxtD = new javax.swing.JTextField();
        TxtNai = new javax.swing.JTextField();
        TxtRes = new javax.swing.JTextField();
        TxtAn = new javax.swing.JTextField();
        TxtPe = new javax.swing.JTextField();
        TxtMe = new javax.swing.JTextField();
        TxtC2 = new javax.swing.JComboBox<>();
        AjouterEtudiant = new javax.swing.JButton();
        ModifierEtudiant = new javax.swing.JButton();
        SuprimerEtudiant = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableIns = new javax.swing.JTable();
        Rechercher = new javax.swing.JTextField();
        ComboRecherch = new javax.swing.JComboBox<>();
        BntActualiser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        jLabel1.setText("        INSCRIPTIONS ETUDIANTS BIU");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setText("Matricule   :");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setText("DateNaiss :");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setText("Nom         :");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel5.setText("Prenom     :");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel6.setText("sexe        :");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel7.setText("Photo          :");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel8.setText("Lieu Naiss   :");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel9.setText("Lieu Reside :");

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel10.setText("Anne Acedem :");

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel11.setText("Faculite     :");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel12.setText("Pere          :");

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel13.setText("Mere         :");

        TxtC1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculin", "Feminin", "Autres" }));

        TxtM.setEditable(false);

        TxtPh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPhActionPerformed(evt);
            }
        });

        TxtP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPActionPerformed(evt);
            }
        });

        TxtD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDActionPerformed(evt);
            }
        });

        TxtNai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNaiActionPerformed(evt);
            }
        });

        TxtRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtResActionPerformed(evt);
            }
        });

        TxtAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtAnActionPerformed(evt);
            }
        });

        TxtPe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPeActionPerformed(evt);
            }
        });

        TxtMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtMeActionPerformed(evt);
            }
        });

        TxtC2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BST", "BAM" }));

        AjouterEtudiant.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        AjouterEtudiant.setText("Ajouter");
        AjouterEtudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterEtudiantActionPerformed(evt);
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

        TableIns.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricule", "Nom", "Prenom", "DateNaissance", "Sexe", "Photo", "LieuNaissance", "LieuResidence", "Anne Academic", "Pere", "Mere"
            }
        ));
        TableIns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableInsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableIns);

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

        ComboRecherch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matricule", "Nom", "Prenom", "Pere", "Mere" }));

        BntActualiser.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        BntActualiser.setText("Actualiser");
        BntActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BntActualiserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtM)
                            .addComponent(TxtN)
                            .addComponent(TxtPh)
                            .addComponent(TxtP)
                            .addComponent(TxtD)
                            .addComponent(TxtNai)
                            .addComponent(TxtRes)
                            .addComponent(TxtAn)
                            .addComponent(TxtPe)
                            .addComponent(TxtMe)
                            .addComponent(TxtC1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtC2, 0, 113, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(ComboRecherch, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Rechercher, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 389, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(AjouterEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ModifierEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SuprimerEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BntActualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Rechercher, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboRecherch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtN, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtP, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TxtD)
                                .addGap(1, 1, 1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtC1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtPh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtRes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtAn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtC2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtPe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtMe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AjouterEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModifierEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SuprimerEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BntActualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtPhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPhActionPerformed

    private void TxtPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPActionPerformed

    private void TxtDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDActionPerformed

    private void TxtNaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNaiActionPerformed

    private void TxtResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtResActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtResActionPerformed

    private void TxtAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtAnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtAnActionPerformed

    private void TxtPeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPeActionPerformed

    private void TxtMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtMeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtMeActionPerformed

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

    private void TableInsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableInsMouseClicked
       try {
                int i = TableIns.getSelectedRow();
                Recuper(i);
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"erreur de replissage" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_TableInsMouseClicked

    private void BntActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BntActualiserActionPerformed
            ActualiserEtudiants();
            Vider();
    }//GEN-LAST:event_BntActualiserActionPerformed

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
    private javax.swing.JTextField TxtPh;
    private javax.swing.JTextField TxtRes;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
