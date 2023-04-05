/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utfpr.trabalho.dao;

import javax.persistence.EntityManager;
import utfpr.trabalho.model.Funcionario;

/**
 *
 * @author Pedro
 */
public class FuncionarioDAO extends CRUD<Funcionario>{
    public FuncionarioDAO(EntityManager em) {
        super(em, Funcionario.class);
    }

}
