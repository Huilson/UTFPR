package utfpr.oo24s.dao;

import utfpr.oo24s.model.Animal;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.List;

public class AnimalDAO {
    // Injeta a dependência de um Entity Manager que será instanciado previamente
    private EntityManager em;


    public AnimalDAO(EntityManager em) {
        this.em = em;
    }

    // Métodos CRUD para persistência dos dados

    public void gravar(Animal animal){
        this.em.persist(animal);
    }

    public void excluir(Animal animal){
        this.em.merge(animal); // Garante que o objeto está sendo gerenciado pelo JPA
        this.em.remove(animal); // Deleta o objeto do banco de dados
    }

    public void atualizar(Animal animal){
        // Merge: Caso encontre um registro na tabela `animal` com a mesma chave primária do objeto, irá atualizá-lo com os novos atributos
        // Caso não encontre, irá tentar criar um novo registro com base no objeto passado.
        this.em.merge(animal);
    }

    public Animal findById(Long id){
        return this.em.find(Animal.class, id); // Busca um objeto da classe referenciada, utilizando o ID
    }

    public List<Animal> findAll(){
        String jpql = "SELECT a FROM Animal a";
        return em.createQuery(jpql, Animal.class).getResultList();
    }

    public List<Animal> buscarPorProfissional(Long profissionalid){
        // Faz a utilização do JPQL para filtrar os animais com base no ID do treinador/profissional
        String jpql = "SELECT a FROM Animal a WHERE a.treinador.profissionalid = :id";
        return em.createQuery(jpql, Animal.class).setParameter("id",profissionalid).getResultList();
    }

    public List<Animal> buscarAnimaisComServicosNaoRealizados(Calendar data){ // Busca os animais que não tiveram nenhum serviço realizado no dia.
        String jpql = "SELECT a FROM Animal a " +
                "WHERE NOT EXISTS (SELECT sr FROM ServicosRealizados sr " +
                "                  WHERE sr.animal = a AND DATE(sr.datahora) = :data)";
        return em.createQuery(jpql, Animal.class).setParameter("data", data, TemporalType.DATE).getResultList();
    }

}
