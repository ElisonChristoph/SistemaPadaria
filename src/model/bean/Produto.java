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
    private Categoria categoria;	
    private String nome;
    private double valor;	
    private int validade;	
    private String ingredientes;

    public Produto(int id, Categoria categoria, String nome, double valor, int validade, String ingredientes) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.valor = valor;
        this.validade = validade;
        this.ingredientes = ingredientes;
    }
    
    public Produto(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
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

    public void setValor(float valor) {
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
    
    
    
}
