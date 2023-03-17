package utfpr.aulajpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
//@Table(name="alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private int RA;
    //@Column(name="media_final")
    private double media;
    @ManyToOne
    private Disciplina disciplina;

    public Aluno() {
    }
    
    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRA() {
        return RA;
    }

    public void setRA(int RA) {
        this.RA = RA;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public Aluno(String nome, int RA, double media, Disciplina disciplina) {
        this.nome = nome;
        this.RA = RA;
        this.media = media;
        this.disciplina = disciplina;
    }
     
}