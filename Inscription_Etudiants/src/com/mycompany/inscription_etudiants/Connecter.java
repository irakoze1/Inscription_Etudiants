package com.mycompany.inscription_etudiants;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author liamsi
 */
public class Connecter {
   // Declaration des objets
            private static Connection con = null;
			
                    static{
                    try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            //Class.forName("com.mysql.jdbc.Driver");
                           // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biu","root","jv_2@getsoft_");
                           //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biu?autoReconnect=false&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","jv_2@getsoft_");
                           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biu?autoReconnect=false&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","fabriTof1");
           
                        } catch (ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                            //System.out.println("Database.getConnection() Error --> " + e.getMessage());
                        } 
                }
			
	//-----------------------------------------------------------------------
			
	public static Connection getConnection(){
                        return con;
	}
    
}
