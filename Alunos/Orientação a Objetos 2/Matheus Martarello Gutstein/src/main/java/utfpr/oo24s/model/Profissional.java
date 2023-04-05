package utfpr.oo24s.model;

import javax.persistence.*;

@Entity
@Table(name = "profissional")
public class Profissional {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_profissionalid")
    @SequenceGenerator(name = "seq_profissionalid", sequenceName = "seq_profissionalid", allocationSize = 1)
    private Long profissionalid;
    @Column(nullable = false)
    private String nome;
    private String funcao;

    public Profissional() {
    }

    public Profissional(String nome, String funcao) {
        this.nome = nome;
        this.funcao = funcao;
    }

    public Long getProfissionalid() {
        return profissionalid;
    }

    public void setProfissionalid(Long profissionalid) {
        this.profissionalid = profissionalid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
