/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.bean.Cliente;
import model.bean.ModeloTabelaItensPedido;
import model.bean.Pedido;
import model.bean.Produto;
import model.dao.ClienteDAO;
import model.dao.PedidoDAO;

/**
 * FXML Controller class
 *
 * @author gianr
 */
public class PedidoController implements Initializable {

    ObservableList<ModeloTabelaItensPedido> olProdutos = FXCollections.observableArrayList();
    Pedido pedido;
    PedidoDAO pedidoDao;
    ClienteDAO clienteDao;
    List<Produto> listProdPed;
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
    @FXML
    private DatePicker dpData;

    private List<Cliente> listCliente;

    private final Stage thisStage;

    public PedidoController() {
        this.thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Pedido.fxml"));
            thisStage.initStyle(StageStyle.UNDECORATED);
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidoDao = new PedidoDAO();
        clienteDao = new ClienteDAO();
        listCliente = clienteDao.read();

        carregarPedido();
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    public void recebeCliente(String codCliente) {
        tfCodCliente.setText(codCliente);
    }

    public void selecionaCliente() {
        PesquisaClienteController controller2 = new PesquisaClienteController(this);
        controller2.showStage();

    }

    public void adicionaProduto() {
        AddItemPedidoController controller2 = new AddItemPedidoController(this);
        controller2.showStage();
    }

    public void atualizandoCliente() {
        tfCodCliente.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                atualizaCliente();
            }
        });
    }

    public void carregarPedido() {
        atualizandoCliente();
        pedido = pedidoDao.read().get(0);
        tfCodCliente.setText(String.valueOf(pedido.getCodCliente()));
        labelCodPedido.setText(String.valueOf(pedido.getId()));
        Instant instant = Instant.ofEpochMilli(pedido.getData().getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();

        dpData.setValue(localDate);
        popularProdutos();
    }

    public void popularProdutos() {
        listProdPed = pedido.getPrudutos();
        Double total = 0.00;
        for (Produto p : listProdPed) {
            Double subtotal = p.getQtdPedido() * p.getValorPedido();
            total += subtotal;
            olProdutos.add(new ModeloTabelaItensPedido(String.valueOf(p.getId()), p.getNome(), String.valueOf(p.getQtdPedido()), String.valueOf("R$ " + p.getValorPedido()), String.valueOf("R$ " + subtotal)));
        }
        tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabDescricao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tabPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tvProdutos.setItems(olProdutos);
        labelTotal.setText("R$ " + String.valueOf(total));
    }

    public void atualizaCliente() {
        onlyNumber(tfCodCliente);
        for (Cliente c : listCliente) {
            if (c.getId() == Integer.parseInt(tfCodCliente.getText())) {
                tfNomeCliente.setText(c.getNome());
            }
        }
    }

    public static void onlyNumber(final TextField tfCodCliente) {
        tfCodCliente.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent t) -> {
            if (t.getCharacter().matches("[a-zA-Z\\s,.]+$")) {
                tfCodCliente.setStyle("-fx-focus-color: #FF0012;");
                t.consume();
            } else {
                tfCodCliente.setStyle(null);
            }
        });
        tfCodCliente.setStyle(null);
    }

}
