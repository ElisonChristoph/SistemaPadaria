/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Logica.CategoriaMain;
import Logica.ClienteMain;
import Logica.FornecedorMain;
import Logica.FuncionarioMain;
import Logica.IngredienteMain;
import Logica.ProdutoMain;
import Logica.UsuarioMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elison Christoph
 */
public class HomeController implements Initializable {

    Stage stage = new Stage();
    Scene scene;
    
    @FXML
    private TextField tfusuario; 
    @FXML
    private Button bacessar;
    @FXML
    private Button bsair;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }  
    @FXML
    private void CadastroProduto(ActionEvent event){
        
        ProdutoMain pm = new ProdutoMain();
                
        try {
            pm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        
    }
    @FXML
    private void CadastroCategoria(ActionEvent event){
        
        CategoriaMain cm = new CategoriaMain();
                
        try {
            cm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    @FXML
    private void CadastroIngrediente(ActionEvent event){
        
        IngredienteMain im = new IngredienteMain();
                
        try {
            im.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    @FXML
    private void CadastroCliente(ActionEvent event){
        
        ClienteMain clm = new ClienteMain();
                
        try {
            clm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
    }
    @FXML
    private void CadastroFornecedor(ActionEvent event){
        
        FornecedorMain fm = new FornecedorMain();
                
        try {
            fm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
    }
    @FXML
    private void CadastroFuncionario(ActionEvent event){
        
        FuncionarioMain fnm = new FuncionarioMain();
                
        try {
            fnm.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
    }
    @FXML
    private void CadastroUsuario(ActionEvent event){
        
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
    
    
    
}
