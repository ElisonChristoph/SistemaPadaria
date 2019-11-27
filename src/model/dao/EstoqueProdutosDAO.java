package model.dao;

import java.util.ArrayList;
import java.util.List;
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
 * @author gianr
 */
public class EstoqueProdutosDAO {

    public void create(Produto p) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO estoqueProdutos (codProduto, quantProduto)VALUES(?, ?)");
            stmt.setInt(1, p.getId());
            stmt.setDouble(2, 0);
            stmt.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void update(List<Produto> listaProd) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        System.out.println("Size: "+listaProd.size());
        try {
            for (Produto p : listaProd) {
                stmt = null;
                stmt = con.prepareStatement("UPDATE estoqueProdutos SET quantProduto = ?  WHERE codProduto = ?");
                stmt.setDouble(1, p.getEstoque());
                stmt.setInt(2, p.getId());
                stmt.executeUpdate();
                System.out.println(stmt.toString());
            }
        } catch (SQLException ex) {
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
            stmt = con.prepareStatement("SELECT * FROM estoqueProdutos");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(rs.getInt("codProduto"), rs.getDouble("quantProduto"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public List<Produto> estoqueProd(int id) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM estoqueProdutos WHERE codProduto LIKE ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(rs.getInt("codProduto"), rs.getDouble("quantProduto"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public void delete(Produto r) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM estoqueProdutos WHERE id = ?");
            stmt.setInt(1, r.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
