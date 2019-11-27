package View;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.bean.EntradaProduto;
import model.bean.ModeloTabelaEntradaProdutos;
import model.bean.Produto;
import model.dao.EntradaProdutoDAO;
import model.dao.ProdutoDAO;

/**
 * FXML Controller class
 *
 * @author Gian Riske
 */
public class ListarEntradaEstoqueController implements Initializable {

    @FXML
    private Font x2;
    @FXML
    private Font x1;
    @FXML
    private Font x3;
    @FXML
    private TableView<ModeloTabelaEntradaProdutos> tvPedidos;
    @FXML
    private Button btEditar;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btPesquisar;
    @FXML
    private TextField tfNum;
    @FXML
    private DatePicker dpIn;
    @FXML
    private DatePicker dpFin;
    ObservableList<ModeloTabelaEntradaProdutos> olPedidos = FXCollections.observableArrayList();
    private final Stage thisStage;
    EntradaProduto entrProd;
    EntradaProdutoDAO entrProdDao;
    List<EntradaProduto> listEntrProd;
    List<Produto> listProdutos;
    ProdutoDAO prodDAO;
    @FXML
    private TableColumn<ModeloTabelaEntradaProdutos, String> tabId;
    @FXML
    private TableColumn<ModeloTabelaEntradaProdutos, String> tabProdutos;
    @FXML
    private TableColumn<ModeloTabelaEntradaProdutos, String> tabDataEntrada;
    @FXML
    private TextField tfCodProduto;
    @FXML
    private TextField tfNomeProduto;
    @FXML
    private Button btProdutos;

    public ListarEntradaEstoqueController() {
        this.thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListarEntradaEstoque.fxml"));
            thisStage.initStyle(StageStyle.UNDECORATED);
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onlyNumber(tfCodProduto);
        onlyNumber(tfNum);
        atualizandoProduto();
        entrProdDao = new EntradaProdutoDAO();
        prodDAO = new ProdutoDAO();
        listProdutos = new ArrayList<>();
        listProdutos = prodDAO.read();
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        dpIn.setValue(localDate.minusDays(30));
        dpFin.setValue(localDate);
        listar();
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @FXML
    public void listar() {
        String dataIn = new String(String.valueOf(dpIn.getValue().getYear()) + "/" + String.valueOf(dpIn.getValue().getMonthValue()) + "/" + String.valueOf(dpIn.getValue().getDayOfMonth()));
        Date dt1 = new Date(dataIn);
        String dataFn = new String(String.valueOf(dpFin.getValue().getYear()) + "/" + String.valueOf(dpFin.getValue().getMonthValue()) + "/" + String.valueOf(dpFin.getValue().getDayOfMonth()));
        Date dt2 = new Date(dataFn);
        String status = new String();
        int p = 0;
        if (!tfCodProduto.getText().isEmpty()) {
            p = Integer.parseInt(tfCodProduto.getText());
        }
        listEntrProd = entrProdDao.pesquisa(tfNum.getText(), dt1, dt2, p);
        olPedidos.clear();
        String produtos = new String();
        for (EntradaProduto ep : listEntrProd) {
            produtos = new String();
            int x = 0;
            for (Produto prod : ep.getProdutos()) {
                x++;
                produtos += prod.getNome();
                if (ep.getProdutos().size() != x) {
                    produtos += ", ";
                }
            }
            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
            String dateFormat1 = dateformat.format(ep.getData());
            olPedidos.add(new ModeloTabelaEntradaProdutos(String.valueOf(ep.getId()), produtos, dateFormat1));
        }
        tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabProdutos.setCellValueFactory(new PropertyValueFactory<>("produtos"));
        tabDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        tvPedidos.setItems(olPedidos);
    }

    public void recebeProduto(String codProduto) {
        tfCodProduto.setText(codProduto);
    }

    public void abreEntrada() {
        AddEstoqueController controller2 = new AddEstoqueController(this);
        controller2.showStage();
    }

    public void buscaProduto() {
        PesquisaProdutoController controller3 = new PesquisaProdutoController(this);
        controller3.showStage();
    }

    public EntradaProduto getEntrProd() {
        return listEntrProd.get(tvPedidos.getSelectionModel().getSelectedIndex());
    }

    public void atualizandoProduto() {
        tfCodProduto.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                atualizaProduto();
            }
        });
    }

    public void atualizaProduto() {
        if (tfCodProduto.getText().isEmpty()) {
            tfNomeProduto.setText("");
        } else {
            for (Produto p : listProdutos) {
                if (p.getId() == Integer.parseInt(tfCodProduto.getText())) {
                    tfNomeProduto.setText(p.getNome());
                }
            }
        }
    }

    @FXML
    public void cancelar() {
        thisStage.close();
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
