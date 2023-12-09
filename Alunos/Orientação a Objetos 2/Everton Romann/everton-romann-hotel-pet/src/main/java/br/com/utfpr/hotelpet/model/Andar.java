package br.com.utfpr.hotelpet.model;


import org.bson.types.ObjectId;


public class Andar {
    private ObjectId idAndar;
    private Integer numAndar;
    private String especieAndar;
    private Double valorAndar;

    public ObjectId getIdAndar() {
        return idAndar;
    }

    public void setIdAndar(ObjectId idAndar) {
        this.idAndar = idAndar;
    }

    public Integer getNumAndar() {
        return numAndar;
    }

    public void setNumAndar(Integer numAndar) {
        this.numAndar = numAndar;
    }

    public String getEspecieAndar() {
        return especieAndar;
    }

    public void setEspecieAndar(String especieAndar) {
        this.especieAndar = especieAndar;
    }

    public Double getValorAndar() {
        return valorAndar;
    }

    public void setValorAndar(Double valorAndar) {
        this.valorAndar = valorAndar;
    }

    //----------- 
    //-----------
    
    
    public Andar criarId(){
        setIdAndar(new ObjectId());
        return this;
    }

}
