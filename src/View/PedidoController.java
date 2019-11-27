package View;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JOptionPane;
import model.bean.Cliente;
import model.bean.ModeloTabelaItensPedido;
import model.bean.Pedido;
import model.bean.Produto;
import model.dao.ClienteDAO;
import model.dao.EstoqueProdutosDAO;
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
    private boolean novo;
    private List<Cliente> listCliente;
    private final Stage thisStage;
    private final ListarPedidosController controller1;
    private EstoqueProdutosDAO epDAO;
    private List<Produto> estoque;
    @FXML
    private DatePicker dpDataFinalizar;

    public PedidoController(ListarPedidosController controller1) {
        this.controller1 = controller1;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidoDao = new PedidoDAO();
        clienteDao = new ClienteDAO();
        epDAO = new EstoqueProdutosDAO();
        estoque = new ArrayList<>();
        listCliente = clienteDao.read();
        onlyNumber(tfCodCliente);
        atualizandoCliente();
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        dpData.setValue(localDate);
        if (controller1 != null) {
            pedido = new Pedido();
            pedido = controller1.getPedido();
            carregarPedido();
        } else {
            novo = true;
            pedido = new Pedido();
            listProdPed = new ArrayList<Produto>();
            pedido.setPrudutos(listProdPed);
            carregarPedido();
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @FXML
    public void salvar() {
        salva(false);
    }

    public void salva(boolean finalizar) {
        if (validaCampos()) {
            if (pedido.isFinalizado()) {
                JOptionPane.showMessageDialog(null, "Pedido finalizado não é possivel editar.");
            } else {
                if (Integer.parseInt(labelCodPedido.getText()) == 0) {
                    if (finalizar) {
                        pedido.setFinalizado(true);
                    }
                    pedido.setCodCliente(Integer.parseInt(tfCodCliente.getText()));
                    String data = new String(String.valueOf(dpData.getValue().getYear()) + "/" + String.valueOf(dpData.getValue().getMonthValue()) + "/" + String.valueOf(dpData.getValue().getDayOfMonth()));
                    String dataFim = new String(String.valueOf(dpDataFinalizar.getValue().getYear()) + "/" + String.valueOf(dpDataFinalizar.getValue().getMonthValue()) + "/" + String.valueOf(dpDataFinalizar.getValue().getDayOfMonth()));
                    pedido.setData(new Date(data));
                    pedido.setDataFim(new Date(dataFim));
                    pedidoDao.create(pedido);
                } else {
                    if (finalizar) {
                        pedido.setFinalizado(true);
                    }
                    pedido.setCodCliente(Integer.parseInt(tfCodCliente.getText()));
                    String data = new String(String.valueOf(dpData.getValue().getYear()) + "/" + String.valueOf(dpData.getValue().getMonthValue()) + "/" + String.valueOf(dpData.getValue().getDayOfMonth()));
                    String dataFim = new String(String.valueOf(dpDataFinalizar.getValue().getYear()) + "/" + String.valueOf(dpDataFinalizar.getValue().getMonthValue()) + "/" + String.valueOf(dpDataFinalizar.getValue().getDayOfMonth()));
                    pedido.setData(new Date(data));
                    pedido.setDataFim(new Date(dataFim));
                    pedidoDao.update(pedido);
                }
            }
        }
    }

    @FXML
    public void finalizar() {
        if (validaCampos()) {
            if (pedido.isFinalizado()) {
                JOptionPane.showMessageDialog(null, "Pedido finalizado não é possivel editar.");
            } else {
                if (validaEstoque()) {
                    salva(true);
                    retiraDoEstoque();
                }
            }
        }
    }

    public boolean estaNaLista(int id) {
        for (Produto p : pedido.getProdutos()) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean validaCampos() {
        if (tfCodCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: Selecione um cliente");
            return false;
        } else {
            if (pedido.getProdutos().size() < 1) {
                JOptionPane.showMessageDialog(null, "Erro: Insira pelo menos um produto");
                return false;
            }
        }
        return true;
    }

    public boolean validaEstoque() {
        estoque = epDAO.read();
        for (Produto p : pedido.getProdutos()) {
            boolean tem = false;
            for (Produto prod : estoque) {
                if (p.getId() == prod.getId()) {
                    tem = true;
                    if (prod.getEstoque() - p.getQtdPedido() < 0.00) {
                        System.out.println(prod.getEstoque() + "  " + p.getNome());
                        JOptionPane.showMessageDialog(null, "Produto: " + p.getNome() + "sem estoque.");
                        tvProdutos.getSelectionModel().select(pedido.getProdutos().indexOf(p));
                        return false;
                    } else {
                        p.setEstoque(prod.getEstoque() - p.getQtdPedido());
                    }
                }
            }
            if (!tem) {
                JOptionPane.showMessageDialog(null, "Produto: " + p.getNome() + "sem estoque.");
                tvProdutos.getSelectionModel().select(pedido.getProdutos().indexOf(p));
                return false;
            }
        }
        return true;
    }

    public void retiraDoEstoque() {
        epDAO.update(pedido.getProdutos());
    }

    @FXML
    public void removeItem() {
        if (tvProdutos.getSelectionModel().getSelectedIndex() >= 0) {
            pedido.getProdutos().remove(tvProdutos.getSelectionModel().getSelectedIndex());
            popularProdutos();
        }
    }

    @FXML
    public void cancelar() {
        thisStage.close();
    }

    public void recebeCliente(String codCliente) {
        tfCodCliente.setText(codCliente);
    }

    public void recebeItem(Produto prod) {
        pedido.getProdutos().add(prod);
        popularProdutos();
    }

    @FXML
    public void selecionaCliente() {
        PesquisaClienteController controller2 = new PesquisaClienteController(this, null);
        controller2.showStage();
    }

    @FXML
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
        if (novo) {
            LocalDate localDate = LocalDate.now();
            dpData.setValue(localDate);
            dpDataFinalizar.setValue(localDate);
        } else {
            tfCodCliente.setText(String.valueOf(pedido.getCodCliente()));
            labelCodPedido.setText(String.valueOf(pedido.getId()));
            Instant instant = Instant.ofEpochMilli(pedido.getData().getTime());
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            LocalDate localDate = localDateTime.toLocalDate();
            dpData.setValue(localDate);
            Instant instant2 = Instant.ofEpochMilli(pedido.getDataFim().getTime());
            LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
            LocalDate localDate2 = localDateTime2.toLocalDate();
            dpDataFinalizar.setValue(localDate2);
            popularProdutos();
        }
    }

    public void popularProdutos() {
        olProdutos.clear();
        Double total = 0.00;
        for (Produto p : pedido.getProdutos()) {
            Double subtotal = p.getQtdPedido() * p.getValorPedido();
            total += subtotal;
            olProdutos.add(new ModeloTabelaItensPedido(String.valueOf(p.getId()), p.getNome(), String.valueOf(new DecimalFormat("#,##.00").format(p.getQtdPedido())),
                    String.valueOf("R$ " + new DecimalFormat("#,##.00").format(p.getValorPedido())), String.valueOf("R$ " + new DecimalFormat("#,##.00").format(subtotal))));
        }
        tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabDescricao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tabPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tvProdutos.setItems(olProdutos);
        labelTotal.setText("R$ " + String.valueOf(new DecimalFormat("#,##.00").format(total)));
    }

    @FXML
    public void atualizaCliente() {
        if (tfCodCliente.getText().isEmpty()) {
            tfNomeCliente.setText("");
        } else {
            for (Cliente c : listCliente) {
                if (c.getId() == Integer.parseInt(tfCodCliente.getText())) {
                    tfNomeCliente.setText(c.getNome());
                }
            }
        }
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
