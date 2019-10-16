/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import model.bean.Categoria;
import model.bean.Produto;
import model.dao.ProdutoDAO;

/**
 *
 * @author Elison Christoph
 */
public class CadastroProdutoController implements Initializable {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private ObservableList<Categoria> list;
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtValor;
    @FXML
    private TextField txtConsumir;
    @FXML
    private ComboBox combobox;
    @FXML
    private TextArea txtaIngredientes;
    
    @FXML
    private void CadastrarProduto(ActionEvent event){
        
        Produto p = new Produto();
        ProdutoDAO dao = new ProdutoDAO();
        
        p.setNome(txtNome.getText());
        //p.setCategoria cbCategorias.getSelectionModel().getSelectedItem();
        //p.setIngredientes(txtaIngredientes.getText());
        p.setValidade(Integer.parseInt(txtConsumir.getText()));
        p.setValor(Float.parseFloat(txtValor.getText()));
        
    }
    
    private void Limpar(ActionEvent event){
        
        
        
    }
    private void Cancelar(ActionEvent event){
        
        
        
    }
    
//    public void ComboCategoria(){
//        
//        CategoriaDAO cd = new CategoriaDAO();
//        
//        for(Categoria c: cd.read()){
//            list.add(c);
//        }
//        
//        combobox.setItems(list);
//        
//    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
       
    }
    
}
