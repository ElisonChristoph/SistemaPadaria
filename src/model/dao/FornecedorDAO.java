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
import model.bean.Fornecedor;

/**
 *
 * @author Elison Christoph
 */
public class FornecedorDAO {
    
    public void create(Fornecedor f) {
        
        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO fornecedores (nome, cnpj, email, telefone)VALUES(?,?,?,?)");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCnpj());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getTelefone());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

    }

    public List<Fornecedor> read() {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedores");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(rs.getInt("codFornecedor"));
                fornecedor.setNome(rs.getString("nomeFornecedor"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setTelefone(rs.getString("telefone"));
                
                fornecedores.add(fornecedor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return fornecedores;

    }
    
}
