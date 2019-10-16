/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Conexão.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Categoria;

/**
 *
 * @author Elison Christoph
 */
public class CategoriaDAO {
    
    public void create(Categoria c) {
        
        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO categoria (nome)VALUES(?)");
            stmt.setString(1, c.getNome());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

    }

    public List<Categoria> read() {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Categoria> categorias = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM categoriaprodutos");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Categoria categoria = new Categoria();

                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
      
                categorias.add(categoria);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return categorias;

    }
//    public List<> readForDesc(String desc) {
//
//        Connection con = ConnectionFactory.getConnection();
//        
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        List<Produto> produtos = new ArrayList<>();
//
//        try {
//            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
//            stmt.setString(1, "%"+desc+"%");
//            
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//
//                Produto produto = new Produto();
//
//                produto.setId(rs.getInt("id"));
//                produto.setDescricao(rs.getString("descricao"));
//                produto.setQtd(rs.getInt("qtd"));
//                produto.setPreco(rs.getDouble("preco"));
//                produtos.add(produto);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            ConnectionFactory.closeConnection(con, stmt, rs);
//        }
//
//        return produtos;
//
//    }
//
//    public void update(Produto p) {
//
//        Connection con = ConnectionFactory.getConnection();
//        
//        PreparedStatement stmt = null;
//
//        try {
//            stmt = con.prepareStatement("UPDATE produto SET descricao = ? ,qtd = ?,preco = ? WHERE id = ?");
//            stmt.setString(1, p.getDescricao());
//            stmt.setInt(2, p.getQtd());
//            stmt.setDouble(3, p.getPreco());
//            stmt.setInt(4, p.getId());
//
//            stmt.executeUpdate();
//
//            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
//        } finally {
//            ConnectionFactory.closeConnection(con, stmt);
//        }
//
//    }
//    public void delete(Produto p) {
//
//        Connection con = ConnectionFactory.getConnection();
//        
//        PreparedStatement stmt = null;
//
//        try {
//            stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
//            stmt.setInt(1, p.getId());
//
//            stmt.executeUpdate();
//
//            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
//        } finally {
//            ConnectionFactory.closeConnection(con, stmt);
//        }
//
//    }
    
}
