package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.bean.Categoria;
import model.bean.Produto;
import model.dao.CategoriaDAO;
import model.dao.EstoqueProdutosDAO;
import model.dao.ProdutoDAO;

/**
 * FXML Controller class
 *
 * @author gianr
 */
public class AddItemEntradaController implements Initializable {

    private Stage thisStage;
    private final AddEstoqueController controller1;
    private List<Produto> listaProdutos;
    private List<Categoria> listaCategorias;
    private Produto produto;
    @FXML
    private ListView<String> lvProdutos;
    @FXML
    private ComboBox<String> cbPesquisaPor;
    @FXML
    private ComboBox<String> cbCategorias;
    @FXML
    private TextField tfQtd;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btAdicionar;
    @FXML
    private TextField tfPesquisa;
    @FXML
    private Button btPesquisa;
    private ObservableList lista;
    private ProdutoDAO dao;
    @FXML
    private Label labelEstoque;
    private List<Produto> estoque;
    private EstoqueProdutosDAO epDAO;
    @FXML
    private Label labelEstoqueFinal;

    public AddItemEntradaController(AddEstoqueController controller1) {
        this.controller1 = controller1;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItemEntrada.fxml"));
            thisStage.initStyle(StageStyle.UNDECORATED);
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Adicionar Produto");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estoque = new ArrayList<>();
        epDAO = new EstoqueProdutosDAO();
        estoque = epDAO.read();
        lista = FXCollections.observableArrayList();
        listaProdutos = new ArrayList<>();
        produto = new Produto();
        dao = new ProdutoDAO();
        cbPesquisaPor.getItems().addAll("Nome", "Categorias");
        listar();
        cbPesquisaPor.getSelectionModel().select(0);
        atualizandoProduto();
        onlyNumber(tfQtd);
        tfQtd.setText("1");
    }

    @FXML
    public void alteraPesquisa() {
        int index = cbPesquisaPor.getSelectionModel().getSelectedIndex();
        switch (index) {
            case -1:
                pesquisaNome();
                break;
            case 0:
                pesquisaNome();
                break;
            case 1:
                pesquisaCategoria();
                break;
        }
    }

    public void pesquisaNome() {
        tfPesquisa.setVisible(true);
        cbCategorias.setVisible(false);
    }

    public void pesquisaCategoria() {
        tfPesquisa.setVisible(false);
        cbCategorias.getItems().clear();
        cbCategorias.setVisible(true);
        listaCategorias = new ArrayList<>();
        Categoria cat = new Categoria();
        CategoriaDAO catDAO = new CategoriaDAO();
        listaCategorias = catDAO.read();
        for (Categoria c : listaCategorias) {
            cbCategorias.getItems().add(c.getNome());
        }
        cbCategorias.getSelectionModel().select(0);
    }

    @FXML
    public void listar() {
        listaProdutos.clear();
        lista.clear();
        int index = cbPesquisaPor.getSelectionModel().getSelectedIndex();
        switch (index) {
            case -1:
                listaProdutos = dao.read();
                break;
            case 0:
                listaProdutos = dao.readForName(tfPesquisa.getText());
                break;
            case 1:
                listaProdutos = dao.readForCat(listaCategorias.get(cbCategorias.getSelectionModel().getSelectedIndex()).getId());
                break;
        }
        for (Produto p : listaProdutos) {
            lista.add(p.getNome());
        }
        lvProdutos.setItems(lista);
    }

    @FXML
    public void alteraProduto() {
        if (lvProdutos.getSelectionModel().getSelectedIndex() >= 0) {
            for (Produto p : estoque) {
                if (listaProdutos.get(lvProdutos.getSelectionModel().getSelectedIndex()).getId() == p.getId()) {
                    listaProdutos.get(lvProdutos.getSelectionModel().getSelectedIndex()).setEstoque(p.getEstoque());
                    labelEstoque.setText(String.valueOf(p.getEstoque()).replace('.', ','));
                    labelEstoqueFinal.setText(String.valueOf(p.getEstoque() + Double.parseDouble(tfQtd.getText().replace(',', '.'))).replace('.', ','));
                }
            }
        }
    }

    public void atualizandoProduto() {
        tfQtd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (lvProdutos.getSelectionModel().getSelectedIndex() >= 0) {
                    if (!tfQtd.getText().isEmpty()) {
                        labelEstoqueFinal.setText(String.valueOf(listaProdutos.get(lvProdutos.getSelectionModel().getSelectedIndex()).getEstoque() + Double.parseDouble(tfQtd.getText().replace(',', '.'))).replace('.', ','));

                    }
                }
            }
        });
    }

    @FXML
    public void enviaItem() {
        int index = lvProdutos.getSelectionModel().getSelectedIndex();
        if (index >= 0 && !tfQtd.getText().isEmpty()) {
            if (controller1.estaNaLista(listaProdutos.get(index).getId())) {
                JOptionPane.showMessageDialog(null, "O produto: " + listaProdutos.get(index).getNome() + "já está inserido.");
            } else {
                produto = listaProdutos.get(index);
                produto.setQtdEntrada(Double.parseDouble(tfQtd.getText().replace(',', '.')));
                controller1.recebeItem(produto);
                thisStage.close();
            }
        }
    }

    @FXML
    public void cancelar() {
        thisStage.close();
    }

    public static void onlyNumber(final TextField textField) {
        textField.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent t) -> {
            if (t.getCharacter().matches("[a-zA-Z\\s.]+$")) {
                textField.setStyle("-fx-focus-color: #FF0012;");
                t.consume();
            } else {
                textField.setStyle(null);
            }
        });
        textField.setStyle(null);
    }
}
