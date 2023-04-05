/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utfpr.trabalho.dao;

import javax.persistence.EntityManager;
import utfpr.trabalho.model.TipoFuncionario;

/**
 *
 * @author Pedro
 */
public class TipoFuncionarioDAO extends CRUD<TipoFuncionario>{
    
    public TipoFuncionarioDAO(EntityManager em) {
        super(em, TipoFuncionario.class);
    }
    
}
