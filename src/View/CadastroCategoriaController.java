/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexão.Conexao;
import Logica.CategoriaMain;
import Logica.ExcluirMain;
import Logica.IngredienteMain;
import java.io.IOException;
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
import javafx.stage.Stage;
import model.bean.Categoria;
import model.bean.ModeloTabela;
import model.dao.CategoriaDAO;

/**
 * FXML Controller class
 *
 * @author Elison Christoph
 */
public class CadastroCategoriaController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    final ObservableList items = FXCollections.observableArrayList();

    @FXML
    private TextField txtNome;

    @FXML
    private TableView<ModeloTabela> table;

    @FXML
    private TableColumn<ModeloTabela, String> tableid;

    @FXML
    private TableColumn<ModeloTabela, String> tablenome;

    ObservableList<ModeloTabela> categorias = FXCollections.observableArrayList();

    @FXML
    private void CadastrarCategoria(ActionEvent event) throws SQLException {

        Categoria c = new Categoria();
        CategoriaDAO dao = new CategoriaDAO();

        c.setNome(txtNome.getText());

        dao.create(c);

        ResetaLista();
        PopularTabela();
        LimparCampo();
        
        

    }

    private void PopularTabela() {

        try {
            Connection con = Conexao.getConnection();

            ResultSet rs = con.createStatement().executeQuery("select * from categoriaprodutos");

            while (rs.next()) {
                categorias.add(new ModeloTabela(rs.getString("id"), rs.getString("nome")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CadastroCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablenome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        table.setItems(categorias);
        //conn.close();

    }
    
    //Botão Excluir//
    @FXML
    private void ExcluirProduto(ActionEvent event) throws Exception {

        ExcluirMain excluirp = new ExcluirMain();

        try {
            excluirp.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(CadastroCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void ResetaLista() {
        categorias.clear();
    }

    @FXML
    private void LimparCampo() {
        txtNome.setText(" ");
    }

    @FXML
    public void Fechar() {
        CategoriaMain.getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            PopularTabela();
    }

}
