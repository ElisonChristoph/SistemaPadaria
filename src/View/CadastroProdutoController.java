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
import javafx.scene.control.cell.PropertyValueFactory;
import model.bean.Categoria;
import model.bean.ModeloTabela;
import model.bean.Produto;
import model.dao.ProdutoDAO;

/**
 *
 * @author Elison Christoph
 */
public class CadastroProdutoController implements Initializable {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @FXML
    private javafx.scene.control.ComboBox comboCat;
    
    @FXML
    private javafx.scene.control.TextField txtNomeProduto;
    
    @FXML
    private javafx.scene.control.TextField txtNomeIngrediente;
    
    @FXML
    private javafx.scene.control.TextField txtValor;
    
    @FXML
    private javafx.scene.control.TextField txtConsumir;
 
    //Tabela todos os ingredientes
    @FXML
    private TableView<ModeloTabela> tableingredientes;
    
    @FXML
    private TableColumn<ModeloTabela, String> tableingredientesid;
    
    @FXML
    private TableColumn<ModeloTabela, String> tableingredientesnome;
    
    //Tabela Ingredientes Selecionados
    @FXML
    private TableView<ModeloTabela> tableselecionados;
    
    @FXML
    private TableColumn<ModeloTabela, String> tableselecionadosid;
    
    @FXML
    private TableColumn<ModeloTabela, String> tableselecionadosnome;
    
    //Listas
    ObservableList<ModeloTabela> ingredientes = FXCollections.observableArrayList();
    ObservableList<ModeloTabela> ingredientesselecionados = FXCollections.observableArrayList();
    ObservableList<Categoria> categorias = FXCollections.observableArrayList();
    
    @FXML
    private void CadastrarProduto(ActionEvent event){
        
        Produto p = new Produto();
        ProdutoDAO dao = new ProdutoDAO();
        
        p.setNome(txtNomeProduto.getText());
        p.setValidade(Integer.parseInt(txtConsumir.getText()));
        p.setValor(Float.parseFloat(txtValor.getText()));
        p.setCategoria(null);
        p.setIngredientes(null);
        
        dao.create(p);
        LimparCampos();
        
        
        
    }
    
    private void TabelaIngredientes(){
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from ingredientes");
            
            while(rsi.next()){
                ingredientes.add(new ModeloTabela(rsi.getString("id"), rsi.getString("nome")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableingredientesid.setCellValueFactory(new PropertyValueFactory<>("id"));
            tableingredientesnome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            
            tableingredientes.setItems(ingredientes);
        
    }
    
    private void TabelaIngredientesSelecionados(){
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsis = con.createStatement().executeQuery("select * from ingredientes");
            
            while(rsis.next()){
                ingredientes.add(new ModeloTabela(rsis.getString("id"), rsis.getString("nome")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableingredientesid.setCellValueFactory(new PropertyValueFactory<>("id"));
            tableingredientesnome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            
            tableingredientes.setItems(ingredientes);
        
    }
    
    private void ComboCategorias(){
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsc = con.createStatement().executeQuery("select * from categoriaprodutos");
            
            while(rsc.next()){
                categorias.add(new Categoria(rsc.getInt("id"), rsc.getString("nome")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            comboCat.setItems(categorias);
        
    }
    
    private void ResetaSelecionados(){
        ingredientesselecionados.clear();
    }
    
    @FXML
    private void LimparCampos(){
        txtNomeProduto.setText(" ");
        txtConsumir.setText(" ");
        txtNomeIngrediente.setText(" ");
        txtValor.setText(" ");
        ResetaSelecionados();
        TabelaIngredientesSelecionados();
    }
    
    @FXML
    private void LimparNome(){
       
        txtNomeIngrediente.setText(" ");
        
    }
    
    @FXML
    public void Fechar() {
        ProdutoMain.getStage().close();
    }
    


    @Override
    public void initialize(URL location, ResourceBundle rb) {
       TabelaIngredientes();
       ComboCategorias();
    }
    
}
