/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexão;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Elison Christoph
 */
public class Conexao {
    
    Connection conn = null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Sistema_padaria", "root","");
            return conn;
        }catch(Exception e){
            System.out.println("erro na conexão");
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
}
