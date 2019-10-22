/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Logica.TelaLoginMain;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    @FXML
    ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    @FXML
    private Button btNovo;

    @FXML
    private TextField pfSenha;

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfNome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        UsuarioDAO dao = new UsuarioDAO();
        dao.create(u);

    }
}
