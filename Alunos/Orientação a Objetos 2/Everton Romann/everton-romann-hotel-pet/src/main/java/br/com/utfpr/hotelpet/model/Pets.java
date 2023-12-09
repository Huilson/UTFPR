package br.com.utfpr.hotelpet.model;

import org.bson.types.ObjectId;

public class Pets {

    private ObjectId idPet;
    private String nomePet;
    private String tutorPet;
    private String especiePet;
    private Integer idadePet;
    private String sexoPet;
    private String corPet;

    public ObjectId getIdPet() {
        return idPet;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getTutorPet() {
        return tutorPet;
    }

    public void setTutorPet(String tutorPet) {
        this.tutorPet = tutorPet;
    }

    public void setIdPet(ObjectId idPet) {
        this.idPet = idPet;
    }

    public String getEspeciePet() {
        return especiePet;
    }

    public void setEspeciePet(String especiePet) {
        this.especiePet = especiePet;
    }

    public Integer getIdadePet() {
        return idadePet;
    }

    public void setIdadePet(Integer idadePet) {
        this.idadePet = idadePet;
    }

    public String getSexoPet() {
        return sexoPet;
    }

    public void setSexoPet(String sexoPet) {
        this.sexoPet = sexoPet;
    }

    public String getCorPet() {
        return corPet;
    }

    public void setCorPet(String corPet) {
        this.corPet = corPet;
    }

    public Pets criarId() {
        setIdPet(new ObjectId());
        return this;
    }
}
