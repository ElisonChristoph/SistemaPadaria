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
public class ModeloTabelaPedidos {

    private String id;
    private String cliente;
    private String dataPedido;
    private String situacao;
    private String dataFinalizar;
    private String valorTotal;

    public ModeloTabelaPedidos(String id, String cliente, String dataPedido, String situacao, String dataFinalizar, String valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.situacao = situacao;
        this.dataFinalizar = dataFinalizar;
        this.valorTotal = valorTotal;
    }

    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDataFinalizar() {
        return dataFinalizar;
    }

    public void setDataFinalizar(String dataFinalizar) {
        this.dataFinalizar = dataFinalizar;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    
}
