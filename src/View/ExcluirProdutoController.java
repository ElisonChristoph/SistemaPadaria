/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexão.Conexao;
import Logica.ProdutoMain;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import model.bean.Categoria;
import model.bean.ModeloTabela;
import model.bean.Produto;
import model.dao.ProdutoDAO;

/**
 *
 * @author Elison Christoph
 */
public class ExcluirProdutoController implements Initializable {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    //Tabela todos os ingredientes
    @FXML
    private TableView<ModeloTabela> tableprodutos;
    
    @FXML
    private TableColumn<ModeloTabela, String> tableprodutosid;
    
    @FXML
    private TableColumn<ModeloTabela, String> tableprodutosnome;
    
    @FXML
    private Button bcancelar;
    @FXML
    private Button bexcluirproduto;
       
    //Listas
    ObservableList<ModeloTabela> produtos = FXCollections.observableArrayList();
    
    
    @FXML
    private void ExcluirProduto(ActionEvent event){
        
        
        
    
        
        
        
    }
    
    private void TabelaIngredientes(){
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from produtos");
            
            while(rsi.next()){
                produtos.add(new ModeloTabela(rsi.getString("id"),rsi.getString("nome")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            tableprodutosid.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            tableprodutosnome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
            
            tableprodutos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            tableprodutos.setItems(produtos);
        
    }
    
    
    
    @FXML
    public void Fechar() {
        ProdutoMain.getStage().close();
    }
    


    @Override
    public void initialize(URL location, ResourceBundle rb) {
       TabelaIngredientes();
    
    }
    
}
