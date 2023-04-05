/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utfpr.trabalho.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
/**
 *
 * @author Pedro
 */

public abstract class CRUD<T> implements AbstractDAO<T>{
    private EntityManager em;
    private Class classe;

    @Override
    public  void listarLista(){
        buscaTodos().forEach(a ->{System.out.println(a);});
    };

    public CRUD(EntityManager em, Class classe) {
        this.em = em;
        this.classe = classe;
    }

    @Override
    public List<T> buscaTodos()
    {
        String jpql = "SELECT a FROM "+classe.getName()+" a";
        return (List<T>) em.createQuery(jpql, classe).getResultList();
    };

  
    @Override
    public T buscaObjeto(Long id)
    {
        return (T) this.em.find(classe, id);
    };

    @Override
    public void atualizar(T obj){
        this.em.merge(obj);
    };

    @Override
    public void excluir(T obj){
        this.em.merge(obj);
        this.em.remove(obj);
    };
  

    @Override
    public void salvar(T obj){
         this.em.persist(obj);
    };
    
    
}
