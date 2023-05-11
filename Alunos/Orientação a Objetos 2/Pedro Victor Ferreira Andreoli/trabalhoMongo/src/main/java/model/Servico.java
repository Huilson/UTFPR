/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;

/**
 *
 * @author Pedro
 */
public class Servico {
    private ObjectId id;
    private String  nome;
    private LocalDateTime data;
    private boolean status;
    private Animal animal;
    private Funcionario funcionario;

    @Override
    public String toString() {
        return "Servico{" + "id=" + id + ", descricao=" + nome + ", data=" + data + ", status=" + status + ", animal=" + animal + ", funcionario=" + funcionario + '}';
    }

    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDescricao() {
        return nome ;
    }

    public void setDescricao(String descricao) {
        this.nome  = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
}
