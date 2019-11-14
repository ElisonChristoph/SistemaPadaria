package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Usuario;
import Conexão.Conexao;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.EntradaProduto;
import model.bean.Pedido;
import model.bean.Produto;

/**
 *
 * @author Elison Christoph
 */
public class EntradaProdutoDAO {

    public void create(Pedido p) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO pedidos (id, codCliente, data, produtos, finalizado)VALUES(?, ?, ?, ?, ?)");
            stmt.setInt(1, p.getId());
            stmt.setInt(2, p.getCodCliente());
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormat = dateformat.format(p.getData());
            stmt.setString(3, dateFormat);
            String produtosPedido = new String();
            for (Produto prod : p.getProdutos()) {
                produtosPedido += String.valueOf(prod.getId()) + ";" + String.valueOf(prod.getQtdPedido()) + ";" + String.valueOf(prod.getValorPedido()) + ":";
            }
            stmt.setString(4, produtosPedido);
            if (p.isFinalizado()) {
                stmt.setInt(5, 1);
            } else {
                stmt.setInt(5, 0);
            }
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void update(Pedido p) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE pedidos SET codCliente = ? ,data = ? ,produtos = ? ,finalizado = ? WHERE id = ?");
            stmt.setInt(1, p.getCodCliente());
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormat = dateformat.format(p.getData());
            stmt.setString(2, dateFormat);
            String produtosPedido = new String();
            for (Produto prod : p.getProdutos()) {
                produtosPedido += String.valueOf(prod.getId()) + ";" + String.valueOf(prod.getQtdPedido()) + ";" + String.valueOf(prod.getValorPedido()) + ":";
            }
            stmt.setString(3, produtosPedido);
            if (p.isFinalizado()) {
                stmt.setInt(4, 1);
            } else {
                stmt.setInt(4, 0);
            }
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<EntradaProduto> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                listaProdutos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Conexao.closeConnection(con, stmt, rs);
        }

        stmt = null;
        rs = null;
        List<EntradaProduto> ListaEntProd = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM entradaProdutos");
            rs = stmt.executeQuery();
            List<Produto> itens;
            Produto prod;
            String itenss;
            String[] it;
            String[] i;
            while (rs.next()) {
                itens = new ArrayList<>();
                if (rs.getString("produtos") != null) {
                    itenss = rs.getString("produtos");
                    it = itenss.split(":");
                    for (int x = 0; x < it.length; x++) {
                        if (!it[x].trim().isEmpty()) {
                            prod = new Produto();
                            i = it[x].split(";");
                            for (int xx = 0; xx < i.length; xx++) {
                                prod.setId(Integer.parseInt(i[0]));
                                for (Produto p : listaProdutos) {
                                    if (p.getId() == prod.getId()) {
                                        prod.setNome(p.getNome());
                                        break;
                                    }
                                }
                                prod.setQtdEntrada(Double.parseDouble(i[1]));
                            }
                            itens.add(prod);
                        }
                    }
                }

                EntradaProduto entProd = new EntradaProduto(rs.getInt("id"), rs.getDate("data"), itens);
                ListaEntProd.add(entProd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return ListaEntProd;
    }

    public List<Pedido> pesquisa(String id, String codCliente, String codUsuario, Date dtInicio, Date dtFim, String status) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                listaProdutos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Conexao.closeConnection(con, stmt, rs);
        }

        stmt = null;
        rs = null;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM pedidos where id like ? AND codCliente like ? AND data >= ? AND data <= ?  AND finalizado like ? AND usuario like ?");
            stmt.setString(1, "%" + id + "%");
            stmt.setString(2, "%" + codCliente + "%");
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormatIn = dateformat.format(dtInicio);
            stmt.setString(3, dateFormatIn);
            String dateFormatFn = dateformat.format(dtFim);
            stmt.setString(4, dateFormatFn);
            stmt.setString(5, "%" + status + "%");
            stmt.setString(6, "%" + codUsuario + "%");
            rs = stmt.executeQuery();
            List<Produto> itens;
            Produto prod;
            String itenss;
            String[] it;
            String[] i;
            boolean finalizado;
            while (rs.next()) {
                itens = new ArrayList<>();
                if (rs.getString("produtos") != null) {
                    itenss = rs.getString("produtos");
                    it = itenss.split(":");
                    for (int x = 0; x < it.length; x++) {
                        if (!it[x].trim().isEmpty()) {
                            prod = new Produto();
                            i = it[x].split(";");
                            for (int xx = 0; xx < i.length; xx++) {
                                prod.setId(Integer.parseInt(i[0]));
                                for (Produto p : listaProdutos) {
                                    if (p.getId() == prod.getId()) {
                                        prod.setNome(p.getNome());
                                        break;
                                    }
                                }
                                prod.setQtdPedido(Double.parseDouble(i[1]));
                                prod.setValorPedido(Double.parseDouble(i[2]));
                            }
                            itens.add(prod);
                        }
                    }
                }
                if (rs.getInt("finalizado") == 1) {
                    finalizado = true;
                } else {
                    finalizado = false;
                }
                Pedido pedido = new Pedido(rs.getInt("id"), rs.getInt("codCliente"), rs.getDate("data"), itens, finalizado);
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return pedidos;
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
            Logger.getLogger(ProdutoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
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
