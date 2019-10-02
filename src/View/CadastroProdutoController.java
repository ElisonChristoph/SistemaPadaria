/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexão.Conexao;
import java.awt.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Elison Christoph
 */
public class CadastroProdutoController implements Initializable {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtValor;
    @FXML
    private TextField txtConsumir;
    @FXML
    private ChoiceBox cbCategorias;
    
    @FXML
    private void CadastrarProduto(ActionEvent event){
        
        
        
    }
    
    private void Limpar(ActionEvent event){
        
        
        
    }
    private void Cancelar(ActionEvent event){
        
        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
