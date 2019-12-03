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
        
        tabela.setVisible(true);
        bcancelar.setVisible(true);
        bexcluir.setVisible(true);
        labelexcluir.setVisible(true);
        
        labelexcluir.setText("Excluir Produto");
        tabelanome.setText("Nome Produto");
        bexcluir.setOnAction(click -> {
            try {
                excluirProduto();
            } catch (SQLException ex) {
                Logger.getLogger(ExcluirController.class.getName()).log(Level.SEVERE, null, ex);
            }
	});
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from produtos");
            
            while(rsi.next()){
                produtos.add(new ModeloTabela(rsi.getString("id"),rsi.getString("nome")));
            }
            
        } catch (SQLException ex) {
          
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Conexao.closeConnection(conn);
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
        
        tabela.setVisible(true);
        bcancelar.setVisible(true);
        bexcluir.setVisible(true);
        labelexcluir.setVisible(true);
        
        labelexcluir.setText("Excluir Categoria");
        tabelanome.setText("Nome Categoria");
        bexcluir.setOnAction(click -> {
            try {
                excluirCategoria();
            } catch (SQLException ex) {
                Logger.getLogger(ExcluirController.class.getName()).log(Level.SEVERE, null, ex);
            }
	});
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from categoriaprodutos");
            
            while(rsi.next()){
                categorias.add(new ModeloTabela(rsi.getString("id"),rsi.getString("nome")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Conexao.closeConnection(conn); 
            tabelaid.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            tabelanome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
            
            tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            tabela.setItems(categorias);  
    }
    //- - - - - - - - - - - - - - - - - - - - - //
    
    //Excluir Ingredientes//
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
        
        tabela.setVisible(true);
        bcancelar.setVisible(true);
        bexcluir.setVisible(true);
        labelexcluir.setVisible(true);
        
        labelexcluir.setText("Excluir Ingrediente");
        tabelanome.setText("Nome Ingrediente");
        bexcluir.setOnAction(click -> {
            try {
                excluirIngrediente();
            } catch (SQLException ex) {
                Logger.getLogger(ExcluirController.class.getName()).log(Level.SEVERE, null, ex);
            }
	});
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from ingredientes");
            
            while(rsi.next()){
                ingredientes.add(new ModeloTabela(rsi.getString("codIngrediente"),rsi.getString("nomeIngrediente")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Conexao.closeConnection(conn); 
            tabelaid.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            tabelanome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
            
            tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            tabela.setItems(ingredientes);  
    }
    //- - - - - - - - - - - - - - - - - - - - - //
    
    //Excluir Clientes//
    @FXML
    public void excluirCliente() throws SQLException {
    Connection con = Conexão.Conexao.getConnection();
        PreparedStatement stmt = null;
        
        ModeloTabela selectCliente = tabela.getSelectionModel().getSelectedItem();
        tabela.getItems().remove(selectCliente);

    try {
        stmt = con.prepareStatement("DELETE FROM clientes where id = ?");
        stmt.setInt(1, Integer.parseInt(selectCliente.getId()));
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Cliente Excluido com Sucesso!");
     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir!" + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    @FXML
    private void TabelaClientes(){
        
        tabela.setVisible(true);
        bcancelar.setVisible(true);
        bexcluir.setVisible(true);
        labelexcluir.setVisible(true);
        
        labelexcluir.setText("Excluir Cliente");
        tabelanome.setText("Nome Cliente");
        bexcluir.setOnAction(click -> {
            try {
                excluirCliente();
            } catch (SQLException ex) {
                Logger.getLogger(ExcluirController.class.getName()).log(Level.SEVERE, null, ex);
            }
	});
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from clientes");
            
            while(rsi.next()){
                clientes.add(new ModeloTabela(rsi.getString("id"),rsi.getString("nome")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Conexao.closeConnection(conn); 
            tabelaid.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            tabelanome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
            
            tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            tabela.setItems(clientes);  
    }
    //- - - - - - - - - - - - - - - - - - - - - //
    
    //Excluir Fornecedor//
    @FXML
    public void excluirFornecedor() throws SQLException {
    Connection con = Conexão.Conexao.getConnection();
        PreparedStatement stmt = null;
        
        ModeloTabela selectFornecedor = tabela.getSelectionModel().getSelectedItem();
        tabela.getItems().remove(selectFornecedor);

    try {
        stmt = con.prepareStatement("DELETE FROM fornecedores where id = ?");
        stmt.setInt(1, Integer.parseInt(selectFornecedor.getId()));
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Fornecedor Excluido com Sucesso!");
     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir!" + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    @FXML
    private void TabelaFornecedores(){
        
        tabela.setVisible(true);
        bcancelar.setVisible(true);
        bexcluir.setVisible(true);
        labelexcluir.setVisible(true);
        
        labelexcluir.setText("Excluir Fornecedor");
        tabelanome.setText("Nome Fornecedor");
        bexcluir.setOnAction(click -> {
            try {
                excluirFornecedor();
            } catch (SQLException ex) {
                Logger.getLogger(ExcluirController.class.getName()).log(Level.SEVERE, null, ex);
            }
	});
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from fornecedores");
            
            while(rsi.next()){
                fornecedores.add(new ModeloTabela(rsi.getString("codFornecedor"),rsi.getString("nomeFornecedor")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Conexao.closeConnection(conn); 
            tabelaid.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            tabelanome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
            
            tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            tabela.setItems(fornecedores);  
    }
    //- - - - - - - - - - - - - - - - - - - - - //
    
    //Excluir Usuario//
    @FXML
    public void excluirUsuario() throws SQLException {
    Connection con = Conexão.Conexao.getConnection();
        PreparedStatement stmt = null;
        
        ModeloTabela selectUsuarios = tabela.getSelectionModel().getSelectedItem();
        tabela.getItems().remove(selectUsuarios);

    try {
        stmt = con.prepareStatement("DELETE FROM usuario where id = ?");
        stmt.setInt(1, Integer.parseInt(selectUsuarios.getId()));
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Usuario Excluido com Sucesso!");
     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir!" + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    @FXML
    private void TabelaUsuario(){
        
        tabela.setVisible(true);
        bcancelar.setVisible(true);
        bexcluir.setVisible(true);
        labelexcluir.setVisible(true);
        
        labelexcluir.setText("Excluir Usuario");
        tabelanome.setText("Usuario");
        bexcluir.setOnAction(click -> {
            try {
                excluirUsuario();
            } catch (SQLException ex) {
                Logger.getLogger(ExcluirController.class.getName()).log(Level.SEVERE, null, ex);
            }
	});
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from usuario");
            
            while(rsi.next()){
                usuarios.add(new ModeloTabela(rsi.getString("id"),rsi.getString("nome")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Conexao.closeConnection(conn); 
            tabelaid.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            tabelanome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
            
            tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            tabela.setItems(usuarios);  
    }
    //- - - - - - - - - - - - - - - - - - - - - //
    
    //Excluir Funcionario//
    @FXML
    public void excluirFuncionario() throws SQLException {
    Connection con = Conexão.Conexao.getConnection();
        PreparedStatement stmt = null;
        
        ModeloTabela selectFuncionarios = tabela.getSelectionModel().getSelectedItem();
        tabela.getItems().remove(selectFuncionarios);

    try {
        stmt = con.prepareStatement("DELETE FROM funcionarios where id = ?");
        stmt.setInt(1, Integer.parseInt(selectFuncionarios.getId()));
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Funcionario Excluido com Sucesso!");
     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir!" + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    @FXML
    private void TabelaFuncionario(){
        
        tabela.setVisible(true);
        bcancelar.setVisible(true);
        bexcluir.setVisible(true);
        labelexcluir.setVisible(true);
        
        labelexcluir.setText("Excluir Funcionario");
        tabelanome.setText("Nome Funcionario");
        bexcluir.setOnAction(click -> {
            try {
                excluirFuncionario();
            } catch (SQLException ex) {
                Logger.getLogger(ExcluirController.class.getName()).log(Level.SEVERE, null, ex);
            }
	});
        
        try {
            Connection con = Conexao.getConnection();
            
            ResultSet rsi = con.createStatement().executeQuery("select * from funcionarios");
            
            while(rsi.next()){
                funcionarios.add(new ModeloTabela(rsi.getString("codFuncionario"),rsi.getString("nomeFuncionario")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Conexao.closeConnection(conn); 
            tabelaid.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            tabelanome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
            
            tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            tabela.setItems(funcionarios);  
    }
    //- - - - - - - - - - - - - - - - - - - - - //
    
    
    @FXML
    public void Fechar() {
        ExcluirMain.getStage().close();
    }
    


    @Override
    public void initialize(URL location, ResourceBundle rb) {
       
        tabela.setVisible(false);
        bcancelar.setVisible(false);
        bexcluir.setVisible(false);
        labelexcluir.setVisible(false);
        
    }
    
}
