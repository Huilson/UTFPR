package br.edu.utfpr.Classes;

import org.bson.types.ObjectId;

public class Animal {

    ObjectId _id;
    private String nome;
    private String especie;
    private String descricao;
    ObjectId id_profissional;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ObjectId getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(ObjectId id_profissional) {
        this.id_profissional = id_profissional;
    }
}
