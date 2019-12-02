/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexão.Conexao;
import Logica.ExcluirMain;
import Logica.ProdutoMain;
import java.io.IOException;
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
import javafx.stage.Stage;
import javafx.util.StringConverter;
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
    private javafx.scene.control.ComboBox<Categoria> comboCat;

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

    //Botões
    @FXML
    private Button badicionar;
    @FXML
    private Button blimparingredientes;
    @FXML
    private Button bcadastrar;
    @FXML
    private Button blimpar;
    @FXML
    private Button bcancelar;
    @FXML
    private Button bexcluirproduto;

    //
    @FXML
    private TextField selecionado;

    //Listas
    ObservableList<ModeloTabela> ingredientes = FXCollections.observableArrayList();
    ObservableList<ModeloTabela> ingredientesselecionados = FXCollections.observableArrayList();
    ObservableList<Categoria> categorias = FXCollections.observableArrayList();
    ArrayList selecionados = new ArrayList();
    
    //Botão Sair
    @FXML
    private void ExcluirProduto(ActionEvent event) throws Exception {

        ExcluirMain excluirp = new ExcluirMain();

        try {
            excluirp.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //HomeMain.getStage().close();
    }

    @FXML
    private void CadastrarProduto(ActionEvent event) {

        Produto p = new Produto();
        ProdutoDAO dao = new ProdutoDAO();

        String ingredientess = null;
        for (ModeloTabela palavra : ingredientesselecionados) {

            if (ingredientess == null) {
                ingredientess = palavra.getId();
            } else {
                ingredientess = ingredientess + "," + palavra.getId();
            }
        }
        //System.out.println(ingredientess);

        p.setNome(txtNomeProduto.getText());
        p.setValidade(Integer.parseInt(txtConsumir.getText()));
        p.setValor(Double.parseDouble(txtValor.getText()));
        p.setCategoria(comboCat.getValue().getId());
        p.setIngredientes(ingredientess);

        dao.create(p);
        LimparCampos();

    }

    private void TabelaIngredientes() {

        try {
            Connection con = Conexao.getConnection();

            ResultSet rsi = con.createStatement().executeQuery("select * from ingredientes");

            while (rsi.next()) {
                ingredientes.add(new ModeloTabela(rsi.getString("codIngrediente"), rsi.getString("nomeIngrediente")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableingredientesid.setCellValueFactory(new PropertyValueFactory<>("id"));

        tableingredientesnome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        tableingredientes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableingredientes.setItems(ingredientes);

    }

    private void TabelaIngredientesSelecionados() {

        tableselecionadosnome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableselecionadosid.setCellValueFactory(new PropertyValueFactory<>("id"));

    }

    private void ComboCategorias() {

        try {
            Connection con = Conexao.getConnection();

            ResultSet rsc = con.createStatement().executeQuery("select * from categoriaprodutos");

            while (rsc.next()) {

                categorias.add(new Categoria(rsc.getInt("id"), rsc.getString("nome")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboCat.setItems(categorias);
        
        comboCat.setConverter(new StringConverter<Categoria>() {

         
            @Override
            public String toString(Categoria object) {
                if (object != null) {
                    return object.getNome();
                }
                return null;
            }

            @Override
            public Categoria fromString(String string) {
                return null;
            }

        });
        
    }

    private void ResetaSelecionados() {
        ingredientesselecionados.clear();
    }

    @FXML
    private void LimparCampos() {
        txtNomeProduto.setText(" ");
        txtConsumir.setText(" ");
        txtNomeProduto.setText(" ");
        txtValor.setText(" ");
        ResetaSelecionados();
        TabelaIngredientesSelecionados();
    }

    @FXML
    private void LimparSelecao() {

    }

    @FXML
    private void SelecionaIngrediente() {

        //ingredientesselecionados = tableingredientes.getSelectionModel().getSelectedItems();
        //selecionados.add(tableingredientes.getSelectionModel().getSelectedItems());
        //tableselecionados.setItems(selecionados.);
        ingredientesselecionados.add(new ModeloTabela(tableingredientes.getSelectionModel().getSelectedItem().getId(),tableingredientes.getSelectionModel().getSelectedItem().getNome()));
        tableselecionados.setItems(ingredientesselecionados);

    }

    @FXML
    public void Fechar() {
        ProdutoMain.getStage().close();
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        TabelaIngredientes();
        TabelaIngredientesSelecionados();
        ComboCategorias();
    }

}
