/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexão.Conexao;
import Logica.HomeMain;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Elison Christoph
 */
public class TelaLoginController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    boolean aprov;
    
    Stage stage = new Stage();
    Scene scene;

    @FXML
    private TextField tflogin;
    @FXML
    private PasswordField pfsenha;
    @FXML
    private Button bacessar;
    @FXML
    private Button bsair;

    //varivel user
    protected static StringProperty user = new SimpleStringProperty();

    //retrona user
    public String retornaUser() {

        return user.get();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bacessar.setOnMouseClicked((MouseEvent e) -> {
            
            aprov = false;

            if (tflogin.getText().equals("") || pfsenha.getText().equals(""))// se login e senha em branco
            {
                JOptionPane.showMessageDialog(null, "Campos login e senha são obrigatórios");//mensagem
            }else{
                
                 conn = Conexao.getConnection();
                
                try {
                    
                    
                    ResultSet rs = conn.createStatement().executeQuery("Select * from usuario where login = '"+ tflogin.getText()+"';");
                    
                    
                    
                    while(rs.next()) {
                        String loginn = rs.getString("login");
                        String senhaa = rs.getString("senha");
                        
                        
                        if(tflogin.getText().equals(loginn) && pfsenha.getText().equals(senhaa)){
                            JOptionPane.showMessageDialog(null,"Seja bem vindo: " + loginn,"Restaurante, Padaria e Confeitaria JB",JOptionPane.INFORMATION_MESSAGE);
                            
//                        HomeController hc = new HomeController();
//                        hc.tfusuario.setText(loginn);
                            HomeMain h = new HomeMain();
                            
                            
                            try {
                                h.start(new Stage());
                            } catch (IOException ex) {
                                Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            aprov = true;
                        }
                    }
                    if(aprov == false){
                       JOptionPane.showMessageDialog(null,"Login ou Senha inválidos.","Restaurante, Padaria e Confeitaria JB",JOptionPane.ERROR_MESSAGE);
                        pfsenha.setText(""); 
                    }
                    
                    conn.close();
                }catch(SQLException a){
                    a.printStackTrace(); //vejamos que erro foi gerado e quem o gerou
                    JOptionPane.showMessageDialog(null,"Erro na conexão, com o banco de dados!","Restaurante, Padaria e Confeitaria JB",JOptionPane.WARNING_MESSAGE);
                }
                
                
                
                
            }
            
            
            

        });

        bsair.setOnMouseClicked((MouseEvent e) -> {
            fecha();
        });
    }

    public void fecha() {
        System.exit(0);
    }

}
