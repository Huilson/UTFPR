package br.com.utfpr.hotelpet.model;

import org.bson.types.ObjectId;

public class Tutor {

    private ObjectId idTutor;
    private String nomeTutor;
    private String telefoneTutor;
    private String emailTutor;

    public ObjectId getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(ObjectId idTutor) {
        this.idTutor = idTutor;
    }

    public String getNomeTutor() {
        return nomeTutor;
    }

    public void setNomeTutor(String nomeTutor) {
        this.nomeTutor = nomeTutor;
    }

    public String getTelefoneTutor() {
        return telefoneTutor;
    }

    public void setTelefoneTutor(String telefoneTutor) {
        this.telefoneTutor = telefoneTutor;
    }

    public String getEmailTutor() {
        return emailTutor;
    }

    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }

    public Tutor criarId() {
        setIdTutor(new ObjectId());
        return this;
    }

}
