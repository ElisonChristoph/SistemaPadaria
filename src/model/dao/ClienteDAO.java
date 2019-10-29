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
import model.bean.Cliente;

/**
 *
 * @author Elison Christoph
 */
public class ClienteDAO {

    public void create(Cliente c) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO clientes (nome, cpf, identidade, endereco, bairro, telefone)VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getIdentidade());
            stmt.setString(4, c.getEndereco());
            stmt.setString(5, c.getBairro());
            stmt.setString(6, c.getTelefone());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void update(Cliente c) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE clientes SET nome = ? ,cpf = ?,identidade = ?,endereco = ?,bairro = ?,telefone = ? WHERE id = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getIdentidade());
            stmt.setString(4, c.getEndereco());
            stmt.setString(5, c.getBairro());
            stmt.setString(6, c.getTelefone());

            stmt.setInt(7, c.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Cliente> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM clientes");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("identidade"), rs.getString("endereco"), rs.getString("bairro"), rs.getString("telefone"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return clientes;
    }

    public List<Cliente> readForDesc(String desc) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM clientes WHERE nome LIKE ?");
            stmt.setString(1, "%" + desc + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("identidade"), rs.getString("endereco"), rs.getString("bairro"), rs.getString("telefone"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    public List<Cliente> readForCpf(String cpf) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM clientes WHERE cpf LIKE ?");
            stmt.setString(1, "%" + cpf + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("identidade"), rs.getString("endereco"), rs.getString("bairro"), rs.getString("telefone"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    public List<Cliente> readForIdentidade(String identidade) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM clientes WHERE identidade LIKE ?");
            stmt.setString(1, "%" + identidade + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("identidade"), rs.getString("endereco"), rs.getString("bairro"), rs.getString("telefone"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    public List<Cliente> readForEndereco(String endereco) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM clientes WHERE endereco LIKE ?");
            stmt.setString(1, "%" + endereco + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("identidade"), rs.getString("endereco"), rs.getString("bairro"), rs.getString("telefone"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return clientes;
    }
    
    public List<Cliente> readForBairro(String bairro) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM clientes WHERE bairro LIKE ?");
            stmt.setString(1, "%" + bairro + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("identidade"), rs.getString("endereco"), rs.getString("bairro"), rs.getString("telefone"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    public void delete(Cliente c) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM clientes WHERE id = ?");
            stmt.setInt(1, c.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

    }
}
