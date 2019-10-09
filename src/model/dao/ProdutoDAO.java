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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author Elison Christoph
 */
public class ProdutoDAO {
    
    public void create(Produto p){
        
        Connection con = Conexão.Conexao.getConnection();
        PreparedStatement stmt = null;
        
        //Passa dados do Produto
        try {
            stmt = con.prepareStatement("INSERT INTO produtos (Categoria, Nome, Valor, Validade, Ingredientes) VALUES(?,?,?,?,?)");
            stmt.setString(1, p.getCategoria());
            stmt.setString(2, p.getNome());
            stmt.setDouble(3, p.getValor());
            stmt.setString(4, p.getValidade());
            stmt.setString(5, p.getIngredientes());
        
            //Executa SQL
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar!" + ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    
}
