/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Logica.TelaLoginMain;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javax.swing.DefaultListModel;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Elison Christoph
 */
public class CadastroUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    ResourceBundle resources;
    private ObservableList lista;
    List<Usuario> usuarios;
    Usuario usuario;
    UsuarioDAO dao;
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
    private TextField tfLogin;
    @FXML
    private ListView<?> lvUsuarios;
    @FXML
    private PasswordField pfSenha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista = FXCollections.observableArrayList();
        usuarios = new ArrayList<>();
        dao = new UsuarioDAO();
        listar();
        btCancelar.setOnMouseClicked((MouseEvent e) -> {
            fecha();
        });
    }

    public void fecha() {
        TelaLoginMain.getStage().close();
    }

    @FXML

    public void salvar(MouseEvent event) {
        Usuario u = new Usuario(0, tfNome.getText(), tfLogin.getText(), pfSenha.getText());
        dao.create(u);

    }

    public void listar() {
        usuarios.clear();
        lista.clear();
        usuarios = dao.read();
        for (Usuario u : usuarios) {
            lista.add(u.getNome());
        }
        lvUsuarios.setItems(lista);
    }

    @FXML
    public void selecionaUsuario() {
        int index = lvUsuarios.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            tfNome.setText(usuarios.get(index).getNome());
            tfLogin.setText(usuarios.get(index).getLogin());
            pfSenha.setText(usuarios.get(index).getSenha());
        }

    }
}
