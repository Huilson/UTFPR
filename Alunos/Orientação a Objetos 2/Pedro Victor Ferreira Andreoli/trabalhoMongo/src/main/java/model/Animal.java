/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import org.bson.types.ObjectId;

/**
 *
 * @author Pedro
 */
public class Animal {
    private String nome;
    private String especie;
    private String familia;
    private String nome_cientifico;
    private String comportamento;
    private ObjectId _id;

    @Override
    public String toString() {
        return "Animal{" + "nome=" + nome + ", especie=" + especie + ", familia=" + familia + ", nome_cientifico=" + nome_cientifico + ", comportamento=" + comportamento + ", _id=" + _id + '}';
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getNome_cientifico() {
        return nome_cientifico;
    }

    public void setNome_cientifico(String nome_cientifico) {
        this.nome_cientifico = nome_cientifico;
    }

    public String getComportamento() {
        return comportamento;
    }

    public void setComportamento(String comportamento) {
        this.comportamento = comportamento;
    }

  
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

}
