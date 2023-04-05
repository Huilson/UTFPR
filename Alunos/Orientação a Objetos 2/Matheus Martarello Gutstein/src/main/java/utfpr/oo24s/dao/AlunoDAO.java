//package utfpr.oo24s.dao;
//
//import java.util.List;
//import javax.persistence.EntityManager;
//import utfpr.oo24s.model.Animal;
//
//public class AlunoDAO {
//    /*Instancio uma injeção de dependência*/
//    private EntityManager em;
//
//    /*Como só queremos usar o EM, não precisa inicia ou fecha a coneção*/
//    public AlunoDAO(EntityManager em){
//        this.em = em;
//    }
//
//    /*Métodos relacionados ao CRUD*/
//    public void salvar(Animal aluno){
//        this.em.persist(aluno);
//    }
//
//    public void excluir(Animal aluno){
//        /*preciso garantir que o objeto excluido esteja sendo gerenciando
//        pela JPA*/
//            this.em.merge(aluno);
//            this.em.remove(aluno);
//    }
//
//    public void atualizar (Animal aluno){
//        this.em.merge(aluno);
//    }
//
//    public Animal buscaAluno(Long id){
//        return this.em.find(Animal.class, id);
//    }
//
//    public List<Animal> buscaTodos(){
//        String jpql = "SELECT a FROM Aluno a";
//        return em.createQuery(jpql, Animal.class).getResultList();
//    }
//
//    public List<Animal> buscaDisciplina(String nome){
//        /*Na JPQL sempre traballhamos com o objeto(Classe)
//        usamos :blabla para armazenar o parâmetro passado pelo metódo
//        O setParameter é responsável por substituir o :blabla pelo parâmetro passado
//        É possível também usar um parâmetro posicional trocando :campo por ?1
//        A própria JPQL faz um JOIN quando usamos um atributo mapeado em um relacionamento*/
//        String jpql = "SELECT a FROM Aluno a WHERE a.disciplina.nome = :campo";
//        return em.createQuery(jpql, Animal.class).setParameter("campo",nome).getResultList();
//    }
//}
