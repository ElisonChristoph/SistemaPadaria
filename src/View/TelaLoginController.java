/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Logica.HomeMain;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

/**
 * FXML Controller class
 *
 * @author Elison Christoph
 */
public class TelaLoginController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

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
             
            if (tflogin.getText().equals("admin") && pfsenha.getText().equals("admin")) {
                
                //seta valor do user
                user.setValue("admin");
                HomeMain h = new HomeMain();
                try {
                    h.start(new Stage());
                    
                   //fecha();
                } catch (Exception ex) {
                    Logger.getLogger(HomeMain.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("erro aqui");
                }
            } 
            
//            }else{
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Erro");
//                alert.setHeaderText("Login ou Senha inválidos!");
//                alert.setContentText("Digite novamente seu Login e sua Senha! ");
//                alert.show();
//            }
        });

        bsair.setOnMouseClicked((MouseEvent e) -> {
            fecha();
        });
    }

    public void fecha() {
        System.exit(0);
    }    
        
    

}
