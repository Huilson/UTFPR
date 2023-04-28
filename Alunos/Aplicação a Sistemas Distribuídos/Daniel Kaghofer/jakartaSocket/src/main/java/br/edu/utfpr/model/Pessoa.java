package br.edu.utfpr.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Pessoa implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nome", nullable = false, length = -1)
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    private Double peso;

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Basic
    private Double altura;

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    @Basic
    private Integer idade;

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Basic
    private Double IMC;

    public Double getIMC() {
        return IMC;
    }

    public void setIMC(Double peso, Double altura) {
        this.IMC = peso/(altura*altura);
    }

    public Pessoa(Integer id, String nome, Double peso, Double altura, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
        this.idade = idade;
        this.setIMC(peso,altura);
    }

    public Pessoa() {
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                ", idade=" + idade +
                ", IMC=" + IMC +
                '}';
    }
}
