package View;

import Logica.ClienteMain;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import model.bean.Cliente;
import model.dao.ClienteDAO;

/**
 * FXML Controller class
 *
 * @author gianr
 */
public class CadastroClienteController implements Initializable {

    ResourceBundle resources;
    private ObservableList lista;
    List<Cliente> clientes;
    Cliente cliente;
    ClienteDAO dao;
    @FXML
    private TextField tfNome;
    @FXML
    private Font x2;
    @FXML
    private Button btNovo;
    @FXML
    private Font x1;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btCancelar;
    @FXML
    private Label lCodigo;
    @FXML
    private TextField tfPesquisa;
    @FXML
    private Button btPesquisa;
    private boolean novo;
    @FXML
    private Button btExcluir;
    @FXML
    private TextField tfCPF;
    @FXML
    private TextField tfIdentidade;
    @FXML
    private TextField tfEndereco;
    @FXML
    private TextField tfCidade;
    @FXML
    private TextField tfTelefone;
    @FXML
    private ListView<?> lvClientes;
    @FXML
    private ComboBox<String> cbPesquisa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista = FXCollections.observableArrayList();
        clientes = new ArrayList<>();
        cliente = new Cliente();
        dao = new ClienteDAO();
        cbPesquisa.getItems().addAll("Nome", "CPF", "Identidade", "Endereço", "Cidade");
        cbPesquisa.getSelectionModel().select(0);
        listar();
        btSalvar.setDisable(true);
        btExcluir.setDisable(true);
    }

    @FXML
    public void salvar(MouseEvent event) {
        if (verificarCampos()) {
            if (novo) {
                Cliente c = new Cliente(0, tfNome.getText(), tfCPF.getText(), tfIdentidade.getText(), tfEndereco.getText(),
                        tfCidade.getText(), tfTelefone.getText());
                dao.create(c);
                novo = false;
                lvClientes.setDisable(false);
                tfPesquisa.setEditable(true);
                btPesquisa.setDisable(false);
            } else {
                cliente.setNome(tfNome.getText());
                cliente.setCpf(tfCPF.getText());
                cliente.setIdentidade(tfIdentidade.getText());
                cliente.setEndereco(tfEndereco.getText());
                cliente.setCidade(tfCidade.getText());
                cliente.setTelefone(tfTelefone.getText());
                dao.update(cliente);
            }
        }
        btExcluir.setDisable(true);
        listar();

    }

    public boolean verificarCampos() {
        if (tfNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite o Nome!");
            return false;
        }
        if (tfCPF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite o CPF!");
            return false;
        }
        if (tfIdentidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite a Identidade!");
            return false;
        }
        if (tfEndereco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite o Endereço !");
            return false;
        }
        if (tfCidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite a Cidade!");
            return false;
        }
        if (tfTelefone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite o Telefone!");
            return false;
        }

        return true;
    }

    @FXML
    public void cancelar() {
        if (novo) {
            novo = false;
            lvClientes.setDisable(false);
            tfPesquisa.setEditable(true);
            btPesquisa.setDisable(false);
            btSalvar.setDisable(true);
            btExcluir.setDisable(true);
        } else {
            ClienteMain.getStage().close();
        }
    }

    public void listar() {
        clientes.clear();
        lista.clear();
        clientes = dao.read();
        for (Cliente c : clientes) {
            lista.add(c.getNome());
        }
        lvClientes.setItems(lista);
    }

    @FXML
    public void excluir() {
        dao.delete(cliente);
        limpacampos();
        listar();
    }

    @FXML
    public void pesquisar() {
        clientes.clear();
        lista.clear();

        int index = cbPesquisa.getSelectionModel().getSelectedIndex();
        switch (index) {
            case -1:
                clientes = dao.read();
                break;
            case 0:
                clientes = dao.readForDesc(tfPesquisa.getText());
                break;
            case 1:
                clientes = dao.readForCpf(tfPesquisa.getText());
                break;
            case 2:
                clientes = dao.readForIdentidade(tfPesquisa.getText());
                break;
            case 3:
                clientes = dao.readForEndereco(tfPesquisa.getText());
                break;
            case 4:
                clientes = dao.readForCidade(tfPesquisa.getText());
                break;
        }

        for (Cliente c : clientes) {
            lista.add(c.getNome());
        }
        lvClientes.setItems(lista);

    }

    @FXML
    public void novoCliente() {
        novo = true;
        limpacampos();
        lvClientes.setDisable(true);
        tfPesquisa.setEditable(false);
        btPesquisa.setDisable(true);
        btSalvar.setDisable(false);
        btExcluir.setDisable(true);
    }

    public void limpacampos() {
        lCodigo.setText("0");
        tfNome.setText("");
        tfCPF.setText("");
        tfIdentidade.setText("");
        tfEndereco.setText("");
        tfCidade.setText("");
        tfTelefone.setText("");
    }

    @FXML
    public void selecionaCliente() {
        int index = lvClientes.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            cliente = clientes.get(index);
            tfNome.setText(cliente.getNome());
            tfCPF.setText(cliente.getCpf());
            tfIdentidade.setText(cliente.getIdentidade());
            tfEndereco.setText(cliente.getEndereco());
            tfCidade.setText(cliente.getCidade());
            tfTelefone.setText(cliente.getTelefone());
            lCodigo.setText(String.valueOf(cliente.getId()));
        }
        novo = false;
        btSalvar.setDisable(false);
        btExcluir.setDisable(false);

    }
}
