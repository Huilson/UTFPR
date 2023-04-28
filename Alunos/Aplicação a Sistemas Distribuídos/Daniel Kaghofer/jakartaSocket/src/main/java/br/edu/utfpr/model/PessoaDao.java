package br.edu.utfpr.model;

import jakarta.persistence.EntityManager;

import java.util.List;

public class PessoaDao {
    private EntityManager _entityManager;

    public PessoaDao(EntityManager _entityManager) {
        this._entityManager = _entityManager;
    }

    public void Salvar(Pessoa pessoa)
    {
        _entityManager.getTransaction().begin();
        _entityManager.persist(pessoa);
        _entityManager.getTransaction().commit();
        //_entityManager.close();
    }

    public Pessoa Ler(Pessoa pessoa){
        String jpql = "select p from Pessoa p where p.id = :id";
        return _entityManager.createQuery(jpql, Pessoa.class).setParameter("id", pessoa.getId()).getSingleResult();
    }

}
