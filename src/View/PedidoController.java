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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.bean.ModeloTabelaItensPedido;
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
    private TableView<ModeloTabelaItensPedido> tvProdutos;
    ObservableList<ModeloTabelaItensPedido> olProdutos = FXCollections.observableArrayList();
    Pedido pedido;
    PedidoDAO dao;
    List<Produto> listProdPed;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabId;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabDescricao;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabQtd;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabPreco;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new PedidoDAO();

        popularProdutos();
    }

    public void popularProdutos() {
        listProdPed = dao.read().get(0).getPrudutos();
        Double total = 0.00;
        for (Produto p : listProdPed) {
            Double subtotal = p.getQtdPedido() * p.getValorPedido();
            total += subtotal;
            olProdutos.add(new ModeloTabelaItensPedido(String.valueOf(p.getId()), p.getNome(), String.valueOf(p.getQtdPedido()), String.valueOf("R$ "+p.getValorPedido()), String.valueOf("R$ "+subtotal)));

        }
        tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabDescricao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tabPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tvProdutos.setItems(olProdutos);
        labelTotal.setText("R$ "+String.valueOf(total));

    }

}
