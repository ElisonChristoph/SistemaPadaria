/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexão.Conexao;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Elison Christoph
 */
public class LoginController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    
    @FXML
    private void EntrarLogin(ActionEvent event){
        String usuario;
        usuario = txtUsuario.getText();
        
        conn = Conexao.ConnectDB();
        String sql = "SELECT*FROM usuarios WHERE Usuario=? and Senha=?";
        
        try{
            pst = (PreparedStatement)conn.prepareStatement(sql);
            pst.setString(1,txtUsuario.getText());
            pst.setString(2,txtSenha.getText());
            rs = pst.executeQuery();
            
            if(rs.next()){
                System.out.println("Bem Vindo");
            }else{
                System.out.println("Erro");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
