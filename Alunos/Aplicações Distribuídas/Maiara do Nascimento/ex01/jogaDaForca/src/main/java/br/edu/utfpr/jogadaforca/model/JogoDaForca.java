package br.edu.utfpr.jogadaforca.model;

public class JogoDaForca {

    private String dificuldade;
    private String palavra;
    private Integer tentativasRestantes;
    private Integer letrasRestantes;
    private String ultLetra;
    private Boolean ultLetraAcertou;
    private String situacao;

    public JogoDaForca() {
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getUltLetra() {
        return ultLetra;
    }

    public void setUltLetra(String ultLetra) {
        this.ultLetra = ultLetra;
    }

    public Boolean getUltLetraAcertou() {
        return ultLetraAcertou;
    }

    public void setUltLetraAcertou(Boolean ultLetraAcertou) {
        this.ultLetraAcertou = ultLetraAcertou;
    }

    public Integer getLetrasRestantes() {
        return letrasRestantes;
    }

    public void setLetrasRestantes(Integer letrasRestantes) {
        this.letrasRestantes = letrasRestantes;
    }

    public Integer getTentativasRestantes() {
        return tentativasRestantes;
    }

    public void setTentativasRestantes(Integer tentativasRestantes) {
        this.tentativasRestantes = tentativasRestantes;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    @Override
    public String toString() {
        return "JogoDaForca{" +
                "dificuldade='" + dificuldade + '\'' +
                ", palavra='" + palavra + '\'' +
                ", tentativasRestantes=" + tentativasRestantes +
                ", letrasRestantes=" + letrasRestantes +
                ", ultLetra='" + ultLetra + '\'' +
                ", ultLetraAcertou=" + ultLetraAcertou +
                ", situacao='" + situacao + '\'' +
                '}';
    }
}
