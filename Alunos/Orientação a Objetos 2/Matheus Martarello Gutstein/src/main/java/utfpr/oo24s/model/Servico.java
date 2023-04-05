package utfpr.oo24s.model;

import javax.persistence.*;
import java.math.BigInteger;


@Table(name = "servico")
public enum Servico {
    ALIMENTAR("Alimentar o animal"),
    SOLTAR("Soltar ao ar livre"),
    VACINAR("Vacinar o animal"),
    RECOLHER_TRANSPORTE("Recolher o animal para transporte"),
    BANHO("Lavar o animal");



    private String descricao;

    Servico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
