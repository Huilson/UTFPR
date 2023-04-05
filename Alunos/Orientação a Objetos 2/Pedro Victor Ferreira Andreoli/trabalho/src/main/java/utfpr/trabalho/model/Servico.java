/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utfpr.trabalho.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Pedro
 */
@Entity
public class Servico {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricao;
    private LocalDateTime data;
    private boolean status;
    @ManyToOne
    private Funcionario funcionario;
    @ManyToOne
    private Animal Animal;
    
    public Servico(String Descricao, LocalDateTime data, Funcionario funcionario, Animal Animal) {
        this.descricao = Descricao;
        this.data = data;
        this.funcionario = funcionario;
        this.Animal = Animal;
        this.status = false;
    }


    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Animal getAnimal() {
        return Animal;
    }

    public void setAnimal(Animal Animal) {
        this.Animal = Animal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
    
    
    public Servico() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    @Override
    public String toString() {
        return "Servico{" + "id=" + id + ", descricao=" + descricao + ", data=" + data + ", funcionario=" + funcionario.getNome() + ", Animal=" + Animal.getEspecie() + "Status: "+
                ((status) ? "Feito" : "Nao feito")+'}';
    }

    

  
    
}
