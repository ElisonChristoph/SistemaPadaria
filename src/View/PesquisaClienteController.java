/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.bean.Cliente;
import model.dao.ClienteDAO;

/**
 * FXML Controller class
 *
 * @author Informatica
 */
public class PesquisaClienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ObservableList lista;
    List<Cliente> clientes;
    Cliente cliente;
    ClienteDAO dao;
    @FXML
    private ListView<String> lvClientes;
    @FXML
    private TextField tfPesquisa;
    @FXML
    private ComboBox<String> cbPesquisa;
    @FXML
    private Button btPesquisa;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btSelecionar;

    private Stage thisStage;

    private final PedidoController controller1;
    private final ListarPedidosController controller2;

    public PesquisaClienteController(PedidoController controller1, ListarPedidosController controller2) {
        this.controller1 = controller1;
        this.controller2 = controller2;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PesquisaCliente.fxml"));
            thisStage.initStyle(StageStyle.UNDECORATED);

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Pesquisa Cliente");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista = FXCollections.observableArrayList();
        clientes = new ArrayList<>();
        cliente = new Cliente();
        dao = new ClienteDAO();
        cbPesquisa.getItems().addAll("Nome", "CPF", "Identidade", "Endereço", "Bairro");
        listar();
        cbPesquisa.getSelectionModel().select(0);
    }

    @FXML
    public void selecionaCliente() {
        int index = lvClientes.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            if (controller1 != null) {
                controller1.recebeCliente(String.valueOf(clientes.get(index).getId()));
            } else {
                if (controller2 != null) {
                    controller2.recebeCliente(String.valueOf(clientes.get(index).getId()));
                }
            }
            thisStage.close();
        }
    }

    public void cancelar() {
        thisStage.close();
    }

    public void listar() {

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
                clientes = dao.readForBairro(tfPesquisa.getText());
                break;
        }

        for (Cliente c : clientes) {
            lista.add(c.getNome());
        }
        lvClientes.setItems(lista);
    }

}
