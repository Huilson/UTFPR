package utfpr.oo24s.model;

import javax.persistence.*;

@Entity
@Table(name="animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_animalid")
    @SequenceGenerator(name = "seq_animalid", sequenceName = "seq_animalid", allocationSize = 1)
    private Long animalid;
    @Column(length = 75, nullable = false)
    private String nome;
    @Column(nullable = false)
    private String raca;
    @ManyToOne
    @JoinColumn(name = "profissionalid")
    private Profissional treinador;

    public Animal() {
    }

    public Animal(String nome, String raca, Profissional treinador) {
        this.nome = nome;
        this.raca = raca;
        this.treinador = treinador;
    }

    public Long getAnimalid() {
        return animalid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Profissional getTreinador() {
        return treinador;
    }

    public void setTreinador(Profissional treinador) {
        this.treinador = treinador;
    }
}