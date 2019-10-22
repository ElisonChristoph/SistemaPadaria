/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Conexão.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Ingrediente;

/**
 *
 * @author Elison Christoph
 */
public class IngredienteDAO {
    
    public void create(Ingrediente i){
        
        Connection con = Conexão.Conexao.getConnection();
        PreparedStatement stmt = null;
        
        //Passa dados do Produto
        try {
            stmt = con.prepareStatement("INSERT INTO ingredientes (nome) VALUES(?)");
            stmt.setString(1, i.getNome());
           
        
            //Executa SQL
            stmt.executeUpdate();
            
            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao Salvar!" + ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    
}
