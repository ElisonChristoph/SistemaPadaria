/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexão.Conexao;
import Logica.IngredienteMain;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bean.Ingrediente;
import model.bean.ModeloTabela;
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

    @FXML
    private TextField txtNome;
 
    @FXML
    private TableView<ModeloTabela> table;
    
    @FXML
    private TableColumn<ModeloTabela, String> tableid;
    
    @FXML
    private TableColumn<ModeloTabela, String> tablenome;
    
    ObservableList<ModeloTabela> ingredientes = FXCollections.observableArrayList();
    
    @FXML
    private void CadastrarIngrediente(ActionEvent event){
        
        Ingrediente i = new Ingrediente();
        IngredienteDAO dao = new IngredienteDAO();
        
        i.setNome(txtNome.getText());
        
        dao.create(i);
        LimparCampo();
        ResetaLista();
        TabelaIngredientes();
        
    }
    
    private void TabelaIngredientes(){
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select * from ingredientes");
            
            while(rs.next()){
                ingredientes.add(new ModeloTabela(rs.getString("id"), rs.getString("nome")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroIngredienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableid.setCellValueFactory(new PropertyValueFactory<>("id"));
            tablenome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            
            table.setItems(ingredientes);
        
    }
    
    private void ResetaLista(){
        ingredientes.clear();
    }
    
    @FXML
    private void LimparCampo(){
        txtNome.setText(" ");
    }
    
    @FXML
    public void Fechar() {
        IngredienteMain.getStage().close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TabelaIngredientes();
    }    
    
}
