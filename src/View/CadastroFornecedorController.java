/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexão.Conexao;
import Logica.ExcluirMain;
import Logica.FornecedorMain;
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
import model.bean.Fornecedor;
import model.bean.ModeloTabela;
import model.dao.FornecedorDAO;

/**
 * FXML Controller class
 *
 * @author Elison Christoph
 */
public class CadastroFornecedorController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    final ObservableList items = FXCollections.observableArrayList();

    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtCNPJ;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtTelefone;

    @FXML
    private TableView<ModeloTabela> table;

    @FXML
    private TableColumn<ModeloTabela, String> tableid;

    @FXML
    private TableColumn<ModeloTabela, String> tablenome;

    ObservableList<ModeloTabela> fornecedores = FXCollections.observableArrayList();

    @FXML
    private void CadastrarFornecedor(ActionEvent event) throws SQLException {

        Fornecedor f = new Fornecedor();
        FornecedorDAO dao = new FornecedorDAO();

        f.setNome(txtNome.getText());
        f.setCnpj(txtCNPJ.getText());
        f.setEmail(txtEmail.getText());
        f.setTelefone(txtTelefone.getText());
        

        dao.create(f);

        ResetaLista();
        PopularTabela();
        LimparCampo();
        
        

    }

    private void PopularTabela() {

        try {
            Connection con = Conexao.getConnection();

            ResultSet rs = con.createStatement().executeQuery("select * from fornecedores");

            while (rs.next()) {
                fornecedores.add(new ModeloTabela(rs.getString("codFornecedor"), rs.getString("nomeFornecedor")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CadastroFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Conexao.closeConnection(conn);
        tableid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablenome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        table.setItems(fornecedores);
        

    }
    
    //Botão Excluir//
    @FXML
    private void ExcluirProduto(ActionEvent event) throws Exception {

        ExcluirMain excluirp = new ExcluirMain();

        try {
            excluirp.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(CadastroFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void ResetaLista() {
        fornecedores.clear();
    }

    @FXML
    private void LimparCampo() {
        txtNome.setText(" ");
        txtCNPJ.setText(" ");
        txtEmail.setText(" ");
        txtTelefone.setText(" ");
    }

    @FXML
    public void Fechar() {
        FornecedorMain.getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            PopularTabela();
    }
}
