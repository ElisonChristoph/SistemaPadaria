package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Usuario;
import Conexão.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Produto;

/**
 *
 * @author Elison Christoph
 */
public class EstoqueProdutosDAO {

    public void create(Usuario u) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO usuario (nome, login, senha)VALUES(?, ?, ?)");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getSenha());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void update(Usuario u) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE usuario SET nome = ? ,login = ?,senha = ? WHERE id = ?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getSenha());
            stmt.setInt(4, u.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Produto> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM estoqueprodutos");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(rs.getInt("id"), rs.getDouble("quantidade"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public List<Usuario> readForDesc(String desc) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE nome LIKE ?");
            stmt.setString(1, "%" + desc + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("login"), rs.getString("senha"));
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return usuarios;
    }

    public void delete(Usuario u) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
            stmt.setInt(1, u.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

    }
}
