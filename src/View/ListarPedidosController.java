/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.bean.Cliente;
import model.bean.ModeloTabelaItensPedido;
import model.bean.ModeloTabelaPedidos;
import model.bean.Pedido;
import model.bean.Produto;
import model.dao.ClienteDAO;
import model.dao.PedidoDAO;

/**
 * FXML Controller class
 *
 * @author Gian Riske
 */
public class ListarPedidosController implements Initializable {

    @FXML
    private Font x2;
    @FXML
    private Font x1;
    @FXML
    private Font x3;
    @FXML
    private TableView<ModeloTabelaPedidos> tvPedidos;
    @FXML
    private Button btEditar;
    @FXML
    private Button btCancelar;
    @FXML
    private TextField tfCodCliente;
    @FXML
    private TextField tfNomeCliente;
    @FXML
    private Button btClientes;
    @FXML
    private TextField tfCodUsuario;
    @FXML
    private TextField tfNomeUsuario;
    @FXML
    private Button btUsuarios;
    @FXML
    private Button btPesquisar;
    @FXML
    private TextField tfNum;
    @FXML
    private ChoiceBox<?> cbSituação;
    @FXML
    private DatePicker dpIn;
    @FXML
    private DatePicker dpFin;

    private List<Cliente> listCliente;
    ObservableList<ModeloTabelaPedidos> olPedidos = FXCollections.observableArrayList();
    private final Stage thisStage;
    Pedido pedido;
    PedidoDAO pedidoDao;
    ClienteDAO clienteDao;
    List<Pedido> listPedidos;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabId;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabCliente;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabDataPedido;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabSituacao;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabDataFinalizar;
    @FXML
    private TableColumn<ModeloTabelaItensPedido, String> tabValorTotal;

    public ListarPedidosController() {

        this.thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListarPedidos.fxml"));
            thisStage.initStyle(StageStyle.UNDECORATED);
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onlyNumber(tfCodCliente);
        onlyNumber(tfCodUsuario);
        onlyNumber(tfNum);
        listar();
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    public void listar() {
        olPedidos.clear();
        Double total = 0.00;
        for (Pedido p : listPedidos) {
        //    Double total = 0.00;
            //    Double subtotal = p.getQtdPedido() * p.getValorPedido();
            //   total += subtotal;
            //     olProdutos.add(new ModeloTabelaItensPedido(String.valueOf(p.getId()), p.getNome(), String.valueOf(p.getQtdPedido()).replace('.', ','), String.valueOf("R$ " + p.getValorPedido()).replace('.', ','), String.valueOf("R$ " + subtotal).replace('.', ',')));
        }
        tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tabDataPedido.setCellValueFactory(new PropertyValueFactory<>("dataPedido"));
        tabSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        tabDataFinalizar.setCellValueFactory(new PropertyValueFactory<>("dataFinalizar"));
        tabValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tvPedidos.setItems(olPedidos);
    }

    public void recebeCliente(String codCliente) {
        tfCodCliente.setText(codCliente);
    }

    public void buscaCliente() {
        PesquisaClienteController controller2 = new PesquisaClienteController(null, this);
        controller2.showStage();
    }

    public void cancelar() {
        thisStage.close();
    }

    public void editar() {

    }

    public void pesquisar() {

    }

    public static void onlyNumber(final TextField textField) {
        textField.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent t) -> {
            if (t.getCharacter().matches("[a-zA-Z\\s,.]+$")) {
                textField.setStyle("-fx-focus-color: #FF0012;");
                t.consume();
            } else {
                textField.setStyle(null);
            }
        });
        textField.setStyle(null);
    }

}
