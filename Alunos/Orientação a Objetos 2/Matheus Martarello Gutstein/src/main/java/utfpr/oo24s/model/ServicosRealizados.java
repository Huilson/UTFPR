package utfpr.oo24s.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Calendar;

@Entity
@Table(name = "servicosrealizados")
public class ServicosRealizados {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_servicosrealizadosid")
    @SequenceGenerator(name = "seq_servicosrealizadosid", sequenceName = "seq_servicosrealizadosid", allocationSize = 1)
    private Long servicosrealizadosid;
    @Enumerated(EnumType.STRING)
    private Servico servico;
    @ManyToOne
    @JoinColumn(name = "animalid")
    private Animal animal;
    @ManyToOne
    @JoinColumn(name = "profissionalid")
    private Profissional profissional;
    private Calendar datahora;

    public ServicosRealizados() {
    }

    public ServicosRealizados(Servico servico, Animal animal, Profissional profissional, Calendar datahora) {
        this.servico = servico;
        this.animal = animal;
        this.profissional = profissional;
        this.datahora = datahora;
    }

    public Long getServicosrealizadosid() {
        return servicosrealizadosid;
    }

    public void setServicosrealizadosid(Long servicosrealizadosid) {
        this.servicosrealizadosid = servicosrealizadosid;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Calendar getDatahora() {
        return datahora;
    }

    public void setDatahora(Calendar datahora) {
        this.datahora = datahora;
    }
}
