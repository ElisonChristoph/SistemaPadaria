package View;

import Logica.UsuarioMain;
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
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author gianr
 */
public class CadastroUsuarioController implements Initializable {

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
    @FXML
    private Label lCodigo;
    @FXML
    private TextField tfPesquisa;
    @FXML
    private Button btPesquisa;
    private boolean novo;
    @FXML
    private Button btExcluir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista = FXCollections.observableArrayList();
        usuarios = new ArrayList<>();
        usuario = new Usuario();
        dao = new UsuarioDAO();
        listar();
        btSalvar.setDisable(true);
        btExcluir.setDisable(true);
    }

    @FXML
    public void salvar(MouseEvent event) {
        if (verificarCampos()) {
            if (novo) {
                Usuario u = new Usuario(0, tfNome.getText(), tfLogin.getText(), pfSenha.getText());
                dao.create(u);
                novo = false;
                lvUsuarios.setDisable(false);
                tfPesquisa.setEditable(true);
                btPesquisa.setDisable(false);
            } else {
                usuario.setNome(tfNome.getText());
                usuario.setLogin(tfLogin.getText());
                usuario.setSenha(pfSenha.getText());
                dao.update(usuario);
            }
        }
        btExcluir.setDisable(true);
        listar();

    }

    public boolean verificarCampos() {
        if (tfNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite um Nome!");
            return false;
        }
        if (tfLogin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite um Login!");
            return false;
        }
        if (pfSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite uma Senha!");
            return false;
        }
        return true;
    }

    @FXML
    public void cancelar() {
        if (novo) {
            novo = false;
            lvUsuarios.setDisable(false);
            tfPesquisa.setEditable(true);
            btPesquisa.setDisable(false);
            btSalvar.setDisable(true);
            btExcluir.setDisable(true);
        } else {
            UsuarioMain.getStage().close();
        }
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
    public void excluir() {
        dao.delete(usuario);
        limpacampos();
        listar();
    }

    @FXML
    public void pesquisar() {
        usuarios.clear();
        lista.clear();
        usuarios = dao.readForDesc(tfPesquisa.getText());
        for (Usuario u : usuarios) {
            lista.add(u.getNome());
        }
        lvUsuarios.setItems(lista);
    }

    @FXML
    public void novoUsuario() {
        novo = true;
        limpacampos();
        lvUsuarios.setDisable(true);
        tfPesquisa.setEditable(false);
        btPesquisa.setDisable(true);
        btSalvar.setDisable(false);
        btExcluir.setDisable(true);
    }

    public void limpacampos() {
        lCodigo.setText("0");
        tfNome.setText("");
        tfLogin.setText("");
        pfSenha.setText("");
    }

    @FXML
    public void selecionaUsuario() {
        int index = lvUsuarios.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            usuario.setId(usuarios.get(index).getId());
            usuario.setNome(usuarios.get(index).getNome());
            usuario.setLogin(usuarios.get(index).getLogin());
            usuario.setSenha(usuarios.get(index).getSenha());
            tfNome.setText(usuario.getNome());
            tfLogin.setText(usuario.getLogin());
            pfSenha.setText(usuario.getSenha());
            lCodigo.setText(String.valueOf(usuario.getId()));
        }
        novo = false;
        btSalvar.setDisable(false);
        btExcluir.setDisable(false);

    }
}
