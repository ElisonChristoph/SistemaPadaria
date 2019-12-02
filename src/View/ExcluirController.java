/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexão.Conexao;
import Logica.ExcluirMain;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import model.bean.ModeloTabela;
import model.bean.Produto;

/**
 *
 * @author Elison Christoph
 */
public class ExcluirController implements Initializable {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    //Tabela todos os ingredientes
    @FXML
    private TableView<ModeloTabela> tabela;
    
    @FXML
    private TableColumn<ModeloTabela, String> tabelaid;
    
    @FXML
    private TableColumn<ModeloTabela, String> tabelanome;
    
    @FXML
    private Button bexcluirproduto;
    @FXML
    private Button bexcluircategoria;
    @FXML
    private Button bexcluircliente;
    @FXML
    private Button bexcluirfornecedor;
    @FXML
    private Button bexcluirfuncionario;
    @FXML
    private Button bexcluiringrediente;
    @FXML
    private Button bexcluirusuario;
    @FXML
    private Button bcancelar;
    @FXML
    private Button bexcluir;
    @FXML
    private Label labelexcluir;
       
    //Listas
    ObservableList<ModeloTabela> produtos = FXCollections.observableArrayList();
    ObservableList<ModeloTabela> categorias = FXCollections.observableArrayList();
    ObservableList<ModeloTabela> ingredientes = FXCollections.observableArrayList();
    ObservableList<ModeloTabela> clientes = FXCollections.observableArrayList();
    ObservableList<ModeloTabela> fornecedores = FXCollections.observableArrayList();
    ObservableList<ModeloTabela> funcionarios = FXCollections.observableArrayList();
    ObservableList<ModeloTabela> usuarios = FXCollections.observableArrayList();
    
    
    //Excluir Produtos//
    @FXML
    public void excluirProduto() throws SQLException {
    Connection con = Conexão.Conexao.getConnection();
        PreparedStatement stmt = null;
        
        ModeloTabela selectProduto = tabela.getSelectionModel().getSelectedItem();
        tabela.getItems().remove(selectProduto);

    try {
        stmt = con.prepareStatement("DELETE FROM produtos where id = ?");
        stmt.setInt(1, Integer.parseInt(selectProduto.getId()));
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Produto Excluido com Sucesso!");
     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir!" + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    @FXML
    private void TabelaProdutos(){
        
        labelexcluir.setText("Excluir Produto");
        tabelanome.setText("Nome Produto");
        //bexcluir.set
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from produtos");
            
            while(rsi.next()){
                produtos.add(new ModeloTabela(rsi.getString("id"),rsi.getString("nome")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            tabelaid.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            tabelanome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
            
            tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            tabela.setItems(produtos);  
    }
    //- - - - - - - - - - - - - - - - - - - - - //
    
    //Excluir Categorias//
    @FXML
    public void excluirCategoria() throws SQLException {
    Connection con = Conexão.Conexao.getConnection();
        PreparedStatement stmt = null;
        
        ModeloTabela selectCategoria = tabela.getSelectionModel().getSelectedItem();
        tabela.getItems().remove(selectCategoria);

    try {
        stmt = con.prepareStatement("DELETE FROM categoriaprodutos where id = ?");
        stmt.setInt(1, Integer.parseInt(selectCategoria.getId()));
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Categoria Excluida com Sucesso!");
     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir!" + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    @FXML
    private void TabelaCategorias(){
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from categoriaprodutos");
            
            while(rsi.next()){
                categorias.add(new ModeloTabela(rsi.getString("id"),rsi.getString("nome")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            tabelaid.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            tabelanome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
            
            tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            tabela.setItems(categorias);  
    }
    //- - - - - - - - - - - - - - - - - - - - - //
    
    //Excluir Categorias//
    @FXML
    public void excluirIngrediente() throws SQLException {
    Connection con = Conexão.Conexao.getConnection();
        PreparedStatement stmt = null;
        
        ModeloTabela selectIngrediente = tabela.getSelectionModel().getSelectedItem();
        tabela.getItems().remove(selectIngrediente);

    try {
        stmt = con.prepareStatement("DELETE FROM ingredientes where id = ?");
        stmt.setInt(1, Integer.parseInt(selectIngrediente.getId()));
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Ingrediente Excluido com Sucesso!");
     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir!" + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    @FXML
    private void TabelaIngredientes(){
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from ingredientes");
            
            while(rsi.next()){
                ingredientes.add(new ModeloTabela(rsi.getString("codIngrediente"),rsi.getString("nomeIngrediente")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            tabelaid.setCellValueFactory(new PropertyValueFactory<>("codIngrediente"));
            
            tabelanome.setCellValueFactory(new PropertyValueFactory<>("nomeIngrediente")); 
            
            tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            tabela.setItems(ingredientes);  
    }
    //- - - - - - - - - - - - - - - - - - - - - //
    
    
    
    @FXML
    public void Fechar() {
        ExcluirMain.getStage().close();
    }
    


    @Override
    public void initialize(URL location, ResourceBundle rb) {
       
    
    }
    
}
