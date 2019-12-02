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
public class Produto {

    private int id;
    private int categoria;
    private String nome;
    private double valor;
    private double valorPedido;//Não vai para o Banco
    private double qtdPedido;//Não vai para o Banco
    private double qtdEntrada;//Não vai para o Banco
    private double estoque;
    private int validade;
    private String ingredientes;

    public Produto(int id, int categoria, String nome, double valor, int validade, String ingredientes) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.valor = valor;
        this.validade = validade;
        this.ingredientes = ingredientes;
    }

    public Produto(int id, Double estoque) {
        this.id = id;
        this.estoque = estoque;
    }

    public Produto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public double getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(double valorPedido) {
        this.valorPedido = valorPedido;
    }

    public double getQtdPedido() {
        return qtdPedido;
    }

    public void setQtdPedido(double qtdPedido) {
        this.qtdPedido = qtdPedido;
    }

    public double getQtdEntrada() {
        return qtdEntrada;
    }

    public void setQtdEntrada(double qtdEntrada) {
        this.qtdEntrada = qtdEntrada;
    }

    public Double getEstoque() {
        return estoque;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

}
