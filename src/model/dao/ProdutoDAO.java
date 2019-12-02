/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Conexão.Conexao;
import View.CadastroProdutoController;
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
import model.bean.Ingrediente;
import model.bean.Produto;

/**
 *
 * @author Elison Christoph
 */
public class ProdutoDAO {

    public void create(Produto p) {

        Connection con = Conexão.Conexao.getConnection();
        PreparedStatement stmt = null;

        //Passa dados do Produto
        try {
            stmt = con.prepareStatement("INSERT INTO produtos (nome,ingredientes,valor,validade,id_categoria) VALUES(?,?,?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getIngredientes());
            stmt.setDouble(3, p.getValor());
            stmt.setInt(4, p.getValidade());
            stmt.setInt(5, p.getCategoria());

            //Executa SQL
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto Salvo com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar!" + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Produto> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Ingrediente> listaIngredientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM ingredientes");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Ingrediente i = new Ingrediente();
                i.setId(rs.getInt("codIngrediente"));
                i.setNome(rs.getString("nomeIngrediente"));
                listaIngredientes.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Conexao.closeConnection(con, stmt, rs);
        }

        stmt = null;
        rs = null;
        List<Produto> produtos = new ArrayList<>();
        Produto produto;
        Ingrediente ingrediente;
        List<Ingrediente> ingredientes;

        try {
            stmt = con.prepareStatement("SELECT p.id, p.nome, p.categoria_id, c.nome as nomeCateg, p.valor, p.validade, p.ingredientes FROM produtos p LEFT JOIN categoriaprodutos c on p.id = c.id");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("nomeCateg"));
                String ing = rs.getString("ingredientes");
                ingredientes = new ArrayList<Ingrediente>();
                List<Ingrediente> ings;
                ings = new ArrayList<>();
                String[] in;
                if (ing != null) {
                    in = ing.split(";");
                    for (int x = 0; x < in.length; x++) {
                        if (!in[x].trim().isEmpty()) {
                            int xx = Integer.parseInt(in[x]);
                            for (Ingrediente i : listaIngredientes) {
                                if (i.getId() == xx) {
                                    ingredientes.add(i);
                                }
                            }
                        }
                    }
                }

                produto = new Produto(rs.getInt("id"), rs.getInt("id_categoria"), rs.getString("nome"), rs.getDouble("valor"), rs.getInt("validade"), rs.getString("ingredientes"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public List<Produto> readForName(String name) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Ingrediente> listaIngredientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM ingredientes");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Ingrediente i = new Ingrediente();
                i.setId(rs.getInt("codIngrediente"));
                i.setNome(rs.getString("nomeIngrediente"));
                listaIngredientes.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Conexao.closeConnection(con, stmt, rs);
        }

        stmt = null;
        rs = null;
        List<Produto> produtos = new ArrayList<>();
        Produto produto;
        Ingrediente ingrediente;
        List<Ingrediente> ingredientes;

        try {
            stmt = con.prepareStatement("SELECT p.id, p.nome, p.categoria_id, c.nome as nomeCateg, p.valor, p.validade, p.ingredientes FROM produtos p  LEFT JOIN categoriaprodutos c on p.id = c.id WHERE p.nome like ?");
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("nomeCateg"));
                String ing = rs.getString("ingredientes");
                ingredientes = new ArrayList<Ingrediente>();
                List<Ingrediente> ings;
                ings = new ArrayList<>();
                String[] in;
                if (ing != null) {
                    in = ing.split(";");
                    for (int x = 0; x < in.length; x++) {
                        if (!in[x].trim().isEmpty()) {
                            int xx = Integer.parseInt(in[x]);
                            for (Ingrediente i : listaIngredientes) {
                                if (i.getId() == xx) {
                                    ingredientes.add(i);
                                }
                            }
                        }
                    }
                }

                produto = new Produto(rs.getInt("id"), rs.getInt("id_categoria"), rs.getString("nome"), rs.getDouble("valor"), rs.getInt("validade"), rs.getString("ingredientes"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

     public List<Produto> readForCat(int cat) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Ingrediente> listaIngredientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM ingredientes");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Ingrediente i = new Ingrediente();
                i.setId(rs.getInt("codIngrediente"));
                i.setNome(rs.getString("nomeIngrediente"));
                listaIngredientes.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Conexao.closeConnection(con, stmt, rs);
        }

        stmt = null;
        rs = null;
        List<Produto> produtos = new ArrayList<>();
        Produto produto;
        Ingrediente ingrediente;
        List<Ingrediente> ingredientes;

        try {
            stmt = con.prepareStatement("SELECT p.id, p.nome, p.categoria_id, c.nome as nomeCateg, p.valor, p.validade, p.ingredientes FROM produtos p  LEFT JOIN categoriaprodutos c on p.id = c.id WHERE p.categoria_id like ?");
            stmt.setInt(1, cat);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("nomeCateg"));
                String ing = rs.getString("ingredientes");
                ingredientes = new ArrayList<Ingrediente>();
                List<Ingrediente> ings;
                ings = new ArrayList<>();
                String[] in;
                if (ing != null) {
                    in = ing.split(";");
                    for (int x = 0; x < in.length; x++) {
                        if (!in[x].trim().isEmpty()) {
                            int xx = Integer.parseInt(in[x]);
                            for (Ingrediente i : listaIngredientes) {
                                if (i.getId() == xx) {
                                    ingredientes.add(i);
                                }
                            }
                        }
                    }
                }

                produto = new Produto(rs.getInt("id"), rs.getInt("id_categoria"), rs.getString("nome"), rs.getDouble("valor"), rs.getInt("validade"), rs.getString("ingredientes"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return produtos;
    }
}
