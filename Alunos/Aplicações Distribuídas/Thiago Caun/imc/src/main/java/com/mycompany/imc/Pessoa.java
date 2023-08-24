/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.imc;

import java.io.Serializable;

/**
 *
 * @author Thiago_k1
 */
public class Pessoa  implements Serializable {
    private String nome;
    private double peso;
    private double  altura;
    private int idade;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public int getIdade() {
        return idade;
    }

    public double getImc() {
        return imc;
    }
    private double imc;
    
}
