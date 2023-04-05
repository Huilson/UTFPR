/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utfpr.trabalho.dao;

import javax.persistence.EntityManager;
import utfpr.trabalho.model.Animal;


/**
 *
 * @author Pedro
 */
public class AnimalDAO extends CRUD<Animal>{

    public AnimalDAO(EntityManager em) {
        super(em, Animal.class);
    }
    
}
