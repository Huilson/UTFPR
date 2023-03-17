package utfpr.aulajpa.dao;

import java.util.List;
import javax.persistence.EntityManager;
import utfpr.aulajpa.model.Aluno;
import utfpr.aulajpa.model.Disciplina;

public class AlunoDAO {
    /*Instancio uma injeção de dependência*/
    private EntityManager em;
    
    /*Como só queremos usar o EM, não precisa inicia ou fecha a coneção*/
    public AlunoDAO(EntityManager em){
        this.em = em;
    }
    
    /*Métodos relacionados ao CRUD*/
    public void salvar(Aluno aluno){
        this.em.persist(aluno);
    }
    
    public void excluir(Aluno aluno){
        /*preciso garantir que o objeto excluido esteja sendo gerenciando
        pela JPA*/
            this.em.merge(aluno);
            this.em.remove(aluno);
    }
    
    public void atualizar (Aluno aluno){
        this.em.merge(aluno);
    }
    
    public Aluno buscaAluno(Long id){
        return this.em.find(Aluno.class, id);        
    }
    
    public List<Aluno> buscaTodos(){
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }
    
    public List<Aluno> buscaDisciplina(Long id){
        /*Na JPQL sempre traballhamos com o objeto(Classe)
        usamos :blabla para armazenar o parâmetro passado pelo metódo
        O setParameter é responsável por substituir o :blabla pelo parâmetro passado
        É possível também usar um parâmetro posicional trocando :campo por ?1
        A própria JPQL faz um JOIN quando usamos um atributo mapeado em um relacionamento*/
        String jpql = "SELECT a FROM Aluno a WHERE a.disciplina = :campo";
        return em.createQuery(jpql, Aluno.class).setParameter("campo",id).getResultList();
    }
    
    public List<Aluno>retornaAlunoReprovado(){
    
          String jpql = "SELECT a FROM Aluno a WHERE a.media<6";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }
}
