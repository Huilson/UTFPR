package br.com.utfpr.hotelpet.model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

public class Checkin {
    
    private ObjectId idCheckin;
    private String nomePet;
    private Integer numAndar;
    private String tutorPet;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataCheckin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataCheckout;
    private Integer tempoHospedagem;
    private Double valorHospedagem;

    public ObjectId getIdCheckin() {
        return idCheckin;
    }

    public void setIdCheckin(ObjectId idCheckin) {
        this.idCheckin = idCheckin;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public Integer getNumAndar() {
        return numAndar;
    }

    public void setNumAndar(Integer numAndar) {
        this.numAndar = numAndar;
    }

    public String getTutorPet() {
        return tutorPet;
    }

    public void setTutorPet(String tutorPet) {
        this.tutorPet = tutorPet;
    }

    public Integer getTempoHospedagem() {
        return tempoHospedagem;
    }

    public void setTempoHospedagem(Integer tempoHospedagem) {
        this.tempoHospedagem = tempoHospedagem;
    }

    public Double getValorHospedagem() {
        return valorHospedagem;
    }

    public void setValorHospedagem(Double valorHospedagem) {
        this.valorHospedagem = valorHospedagem;
    }
    
    public Checkin criarId(){
        setIdCheckin(new ObjectId());
        return this;
    }

    public Date getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(Date dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public Date getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(Date dataCheckout) {
        this.dataCheckout = dataCheckout;
    }
}
