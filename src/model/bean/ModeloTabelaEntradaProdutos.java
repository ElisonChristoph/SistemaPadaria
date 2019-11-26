/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Elison Christoph
 */
public class ModeloTabelaEntradaProdutos {

    private String id;
    private String produtos;
    private String dataEntrada;

    public ModeloTabelaEntradaProdutos(String id, String produtos, String dataEntrada) {
        this.id = id;
        this.produtos = produtos;
        this.dataEntrada = dataEntrada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    
    
    
}
