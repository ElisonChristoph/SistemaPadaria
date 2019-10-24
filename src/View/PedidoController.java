/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.bean.ModeloTabela;
import model.bean.Pedido;
import model.bean.Produto;
import model.dao.PedidoDAO;

/**
 * FXML Controller class
 *
 * @author gianr
 */
public class PedidoController implements Initializable {

    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private Label labelCodPedido;
    @FXML
    private TextField tfCodCliente;
    @FXML
    private TextField tfNomeCliente;
    @FXML
    private TextField tfData;
    @FXML
    private Button btFinalizar;
    @FXML
    private Font x3;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btCancelar;
    @FXML
    private Label labelTotal;
    @FXML
    private Button btAddProd;
    @FXML
    private Font x4;
    @FXML
    private Button btRemProd;
    @FXML
    private TableView<ModeloTabela> tvProdutos;
    ObservableList<ModeloTabela> olProdutos = FXCollections.observableArrayList();
    Pedido pedido;
    PedidoDAO dao;
    List<Produto> listProdPed;
    @FXML
    private TableColumn<ModeloTabela, String> tabId;
    @FXML
    private TableColumn<ModeloTabela, String> tabDescricao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new PedidoDAO();
        listProdPed = dao.read().get(0).getPrudutos();
        popularProdutos();
    }

    public void popularProdutos() {
        for (Produto p : listProdPed) {
            olProdutos.add(new ModeloTabela(String.valueOf(p.getId()), p.getNome()));
        }
        tvProdutos.setItems(olProdutos);
    }

}
