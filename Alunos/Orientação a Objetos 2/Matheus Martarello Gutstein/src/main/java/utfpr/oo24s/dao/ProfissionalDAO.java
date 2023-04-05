package utfpr.oo24s.dao;

import utfpr.oo24s.model.Animal;
import utfpr.oo24s.model.Profissional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProfissionalDAO {
    // Injeta a dependência de um Entity Manager que será instanciado previamente
    private EntityManager em;

    public ProfissionalDAO(EntityManager em) {
        this.em = em;
    }

    // Métodos CRUD para persistência dos dados

    public void gravar(Profissional profissional){
        this.em.persist(profissional);
    }

    public void excluir(Profissional profissional){
        this.em.merge(profissional); // Garante que o objeto está sendo gerenciado pelo JPA
        this.em.remove(profissional); // Deleta o objeto do banco de dados
    }

    public void atualizar(Profissional profissional){
        // Merge: Caso encontre um registro na tabela `profissional` com a mesma chave primária do objeto, irá atualizá-lo com os novos atributos
        // Caso não encontre, irá tentar criar um novo registro com base no objeto passado.
        this.em.merge(profissional);
    }

    public Profissional findById(Long id){
        return this.em.find(Profissional.class, id); // Busca um objeto da classe referenciada, utilizando o ID
    }

    public List<Profissional> findAll(){
        String jpql = "SELECT p FROM Profissional p";
        return em.createQuery(jpql, Profissional.class).getResultList();
    }

    public List<Profissional> buscarPorAnimal(Long animalid){
        // Faz a utilização do JPQL para filtrar o profissional com base no ID do animal
        // Faz o select com base na tabela `animal` para que seja possível filtrar pelo atributo animalid.
        // Após isso, faz o join com a tabela `profissional` e retorna o objeto encontrado
        String jpql = "SELECT p from Animal a JOIN a.treinador p where a.animalid = :id";
        return em.createQuery(jpql, Profissional.class).setParameter("id", animalid).getResultList();
    }

    public Boolean temRegistros() {
        TypedQuery<Profissional> query = em.createQuery("SELECT p FROM Profissional p", Profissional.class);
        query.setMaxResults(1); // define que a consulta deve retornar apenas um resultado

        if (query.getSingleResult() == null){ // Verificar se existem registros no banco de dados
            return false;
        }
        return true;
    }
}
