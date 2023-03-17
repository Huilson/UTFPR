package utfpr.aulajpa.dao;

import java.util.List;
import javax.persistence.EntityManager;
import utfpr.aulajpa.model.Disciplina;

public class DisciplinaDAO {

    /*Instancio uma injeção de dependência*/
    private EntityManager em;
    
    /*Como só queremos usar o EM, não precisa inicia ou fecha a coneção*/
    public DisciplinaDAO(EntityManager em){
        this.em = em;
    }
    
    /*Métodos relacionados ao CRUD*/
    public void salvar(Disciplina disciplina){
        this.em.persist(disciplina);
    }
    
    public void excluir(Disciplina disciplina){
        /*preciso garantir que o objeto excluido esteja sendo gerenciando
        pela JPA*/
            this.em.merge(disciplina);
            this.em.remove(disciplina);
    }
    
    public List<Disciplina> buscaTodos(){
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Disciplina.class).getResultList();
    }
    
    public Disciplina buscaDisciplina(Long id){
        return this.em.find(Disciplina.class, id);
    }
}
