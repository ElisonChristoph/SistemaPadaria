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
            stmt = con.prepareStatement("INSERT INTO entradaProdutos (itens, data, produtos)VALUES(?, ?, ?)");
            stmt.setInt(1, p.getCodCliente());
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormat = dateformat.format(p.getData());
            stmt.setString(2, dateFormat);
            String produtosPedido = new String();
            for (Produto prod : p.getProdutos()) {
                produtosPedido += String.valueOf(prod.getId()) + ";" + String.valueOf(prod.getQtdEntrada()) + ":";
            }
            stmt.setString(3, produtosPedido);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
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
                if (rs.getString("itens") != null) {
                    itenss = rs.getString("itens");
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
}
