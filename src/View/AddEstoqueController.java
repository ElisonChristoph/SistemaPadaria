/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.bean.EntradaProduto;
import model.bean.ModeloTabelaItensEntradaProduto;
import model.bean.Produto;
import model.dao.EntradaProdutoDAO;
import model.dao.EstoqueProdutosDAO;

/**
 * FXML Controller class
 *
 * @author gianr
 */
public class AddEstoqueController implements Initializable {

    ObservableList<ModeloTabelaItensEntradaProduto> olProdutos = FXCollections.observableArrayList();
    EntradaProduto entrProd;
    EntradaProdutoDAO EntrProdDAO;
    List<Produto> listProdEntrada;
    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private Font x3;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btAddProd;
    @FXML
    private Font x4;
    @FXML
    private Button btRemProd;
    @FXML
    private TableView<ModeloTabelaItensEntradaProduto> tvProdutos;
    @FXML
    private TableColumn<ModeloTabelaItensEntradaProduto, String> tabId;
    @FXML
    private TableColumn<ModeloTabelaItensEntradaProduto, String> tabDescricao;
    @FXML
    private TableColumn<ModeloTabelaItensEntradaProduto, String> tabEstoque;
    @FXML
    private TableColumn<ModeloTabelaItensEntradaProduto, String> tabQuantidade;
    @FXML
    private TableColumn<ModeloTabelaItensEntradaProduto, String> tabEstoqueFinal;
    @FXML
    private DatePicker dpData;
    private boolean novo;
    private final Stage thisStage;
    private final ListarEntradaEstoqueController controller1;
    private EstoqueProdutosDAO epDAO;
    private List<Produto> estoque;
    private DatePicker dpDataFinalizar;
    @FXML
    private Label labelCodEntrada;

    public AddEstoqueController(ListarEntradaEstoqueController controller1) {
        this.controller1 = controller1;
        this.thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEstoque.fxml"));
            thisStage.initStyle(StageStyle.UNDECORATED);
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        epDAO = new EstoqueProdutosDAO();
        EntrProdDAO = new EntradaProdutoDAO();
        estoque = new ArrayList<>();
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        dpData.setValue(localDate);
        if (controller1 != null) {
            entrProd = new EntradaProduto();
            entrProd = controller1.getEntrProd();
            carregarEntradaProduto();
        } else {
            novo = true;
            entrProd = new EntradaProduto();
            listProdEntrada = new ArrayList<Produto>();
            entrProd.setPrudutos(listProdEntrada);
            carregarEntradaProduto();
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    public boolean estaNaLista(int id) {
        for (Produto p : entrProd.getProdutos()) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean validaCampos() {
        if (entrProd.getProdutos().size() < 1) {
            JOptionPane.showMessageDialog(null, "Erro: Insira pelo menos um produto");
            return false;
        }
        return true;
    }

    public void validaEstoque() {
        estoque = epDAO.read();
        for (Produto p : entrProd.getProdutos()) {
            boolean tem = false;
            for (Produto prod : estoque) {
                if (p.getId() == prod.getId()) {
                    p.setEstoque(prod.getEstoque());
                    tem = true;
                }
            }
            if (!tem) {
                epDAO.create(p);
            }
        }
    }

    public void salvar() {
        if (validaCampos()) {
            if (Integer.parseInt(labelCodEntrada.getText()) == 0) {
                String data = new String(String.valueOf(dpData.getValue().getYear()) + "/" + String.valueOf(dpData.getValue().getMonthValue()) + "/" + String.valueOf(dpData.getValue().getDayOfMonth()));
                entrProd.setData(new Date(data));
                if (EntrProdDAO.create(entrProd)) {
                    adicionaNoEstoque();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Não é possivel alterar uma entrada de Produto.");
            }
        }
    }

    public void adicionaNoEstoque() {
        for (Produto p : entrProd.getProdutos()) {
            System.out.println(p.getEstoque() +""+ p.getQtdEntrada());
            p.setEstoque(p.getEstoque() + p.getQtdEntrada());
            
        }
        validaEstoque();
        epDAO.update(entrProd.getProdutos());
    }

    @FXML
    public void removeItem() {
        if (tvProdutos.getSelectionModel().getSelectedIndex() >= 0) {
            entrProd.getProdutos().remove(tvProdutos.getSelectionModel().getSelectedIndex());
            popularProdutos();
        }
    }

    @FXML
    public void cancelar() {
        thisStage.close();
    }

    public void recebeItem(Produto prod) {
        entrProd.getProdutos().add(prod);
        popularProdutos();
    }

    @FXML
    public void adicionaProduto() {
        AddItemEntradaController controller2 = new AddItemEntradaController(this);
        controller2.showStage();
    }

    public void carregarEntradaProduto() {
        if (novo) {
            LocalDate localDate = LocalDate.now();
            dpData.setValue(localDate);
        } else {
            labelCodEntrada.setText(String.valueOf(entrProd.getId()));
            Instant instant = Instant.ofEpochMilli(entrProd.getData().getTime());
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            LocalDate localDate = localDateTime.toLocalDate();
            dpData.setValue(localDate);
            popularProdutos();
        }
    }

    public void popularProdutos() {
        olProdutos.clear();
        estoque = epDAO.read();
        Double total = 0.00;
        for (Produto p : entrProd.getProdutos()) {
            Double est = 0.00;
            Double estFinal = 0.00;
            for (Produto pe : estoque) {
                if (p.getId() == pe.getId()) {
                    est = pe.getEstoque();
                    estFinal = est + p.getQtdEntrada();
                    break;
                }
            }
            olProdutos.add(new ModeloTabelaItensEntradaProduto(String.valueOf(p.getId()), p.getNome(), String.valueOf(new DecimalFormat("#,##.00").format(est)),
                    String.valueOf(new DecimalFormat("#,##.00").format(p.getQtdEntrada())), String.valueOf(estFinal).replace('.', ',')));
            System.out.println(estFinal);
        }
        tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tabEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        tabQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tabEstoqueFinal.setCellValueFactory(new PropertyValueFactory<>("estoqueFinal"));
        tvProdutos.setItems(olProdutos);
    }

}
