/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.bean.Categoria;
import model.bean.Ingrediente;
import model.dao.IngredienteDAO;

/**
 * FXML Controller class
 *
 * @author Elison Christoph
 */
public class CadastroIngredienteController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private ObservableList<Categoria> list;
    
    @FXML
    private TextField txtNome;
 
    @FXML
    private TextArea txtaIngredientes;
    
    @FXML
    private void CadastrarIngrediente(ActionEvent event){
        
        Ingrediente i = new Ingrediente();
        IngredienteDAO dao = new IngredienteDAO();
        
        i.setNome(txtNome.getText());
        
        dao.create(i);
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
