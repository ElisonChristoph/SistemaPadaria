/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexão.Conexao;
import Logica.CategoriaMain;
import Logica.ClienteMain;
import Logica.FornecedorMain;
import Logica.FuncionarioMain;
import Logica.HomeMain;
import Logica.IngredienteMain;
import Logica.ProdutoMain;
import Logica.TelaLoginMain;
import Logica.UsuarioMain;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Elison Christoph
 */
public class HomeController implements Initializable {

    Stage stage = new Stage();
    Scene scene;

    @FXML
    public Label tfusuario;
    @FXML
    public Label tftipo;
    @FXML
    private Button bacessar;
    @FXML
    private Button bsair;
    
    TelaLoginController tlc = new TelaLoginController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tftipo.setText("Usuário:" + tlc.retornatipoUser());
        tfusuario.setText(tlc.retornaUser());

    }

    //Botão Sair
    @FXML
    private void Sair(ActionEvent event) throws Exception {

        TelaLoginMain logm = new TelaLoginMain();

        try {
            logm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HomeMain.getStage().close();
    }

    //Opções Botão Vereficar
    @FXML
    private void VereficarEstoque(ActionEvent event) {

    }

    @FXML
    private void VereficarSituCliente(ActionEvent event) {

    }

    @FXML
    private void VereficarSituFornecedor(ActionEvent event) {

    }

    @FXML
    private void VereficarSituUsuario(ActionEvent event) {

    }

    @FXML
    private void VereficarSituFuncionario(ActionEvent event) {

    }

    //Opções Botão Emitir
    @FXML
    private void ProdutosEstoque(ActionEvent event) {
        Connection con = Conexao.getConnection();
        //Arquivo template do relatorio
        String local = "src/Relatorios/Estoque.jasper";
        //Objeto da API responsavel por gerar o relatorio
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(local, null, con);
        } catch (JRException ex) {
            System.out.println("Problem");
        }

        JasperViewer view = new JasperViewer(jasperPrint, false);
        view.setVisible(true);
    }

    @FXML
    private void ProdutosFalta(ActionEvent event) {

    }

    @FXML
    private void CategoriasProdutos(ActionEvent event) {

    }

    @FXML
    private void RelacaoClientes(ActionEvent event) {

    }

    @FXML
    private void RelacaoFornecedores(ActionEvent event) {
        Connection con = Conexao.getConnection();
        //Arquivo template do relatorio
        String local = "src/Relatorios/Fornecedores.jasper";
        //Objeto da API responsavel por gerar o relatorio
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(local, null, con);
        } catch (JRException ex) {
            System.out.println("Problem");
        }
        JasperViewer view = new JasperViewer(jasperPrint, false);
        view.setVisible(true);
    }

    @FXML
    private void RelacaoFuncionarios(ActionEvent event) {

    }

    @FXML
    private void RelacaoUsuarios(ActionEvent event) {

    }

    //Opções Botão Movimentação
    @FXML
    private void EntradaMatPrima(ActionEvent event) {

    }

    @FXML
    private void Vendas(ActionEvent event) {

    }

    //Opções Botão Cadastrar
    @FXML
    private void CadastroProduto(ActionEvent event) {

        ProdutoMain pm = new ProdutoMain();

        try {
            pm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void CadastroCategoria(ActionEvent event) {

        CategoriaMain cm = new CategoriaMain();

        try {
            cm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void CadastroIngrediente(ActionEvent event) {

        IngredienteMain im = new IngredienteMain();

        try {
            im.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void CadastroCliente(ActionEvent event) {

        ClienteMain clm = new ClienteMain();

        try {
            clm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void CadastroFornecedor(ActionEvent event) {

        FornecedorMain fm = new FornecedorMain();

        try {
            fm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void CadastroFuncionario(ActionEvent event) {

        FuncionarioMain fnm = new FuncionarioMain();

        try {
            fnm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void CadastroUsuario(ActionEvent event) {

        UsuarioMain um = new UsuarioMain();

        try {
            um.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void fazerPedido() {
        PedidoController p = new PedidoController((null));
        p.showStage();
    }
    
    public void listarPedidos(){
        ListarPedidosController lp = new ListarPedidosController();
        lp.showStage();
    }
    
    public void listarEntradasEstoque(){
        ListarEntradaEstoqueController lee = new ListarEntradaEstoqueController();
        lee.showStage();
    }
    public void adicionarEstoque(){
        AddEstoqueController ae = new AddEstoqueController(null);
        ae.showStage();
    }

}
