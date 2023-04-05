package utfpr.oo24s.dao;

import utfpr.oo24s.model.Servico;
import utfpr.oo24s.model.ServicosRealizados;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServicosRealizadosDAO {
    // Injeta a dependência de um Entity Manager que será instanciado previamente
    private EntityManager em;

    public ServicosRealizadosDAO(EntityManager em) {
        this.em = em;
    }

    // Métodos CRUD para persistência dos dados

    public void gravar(ServicosRealizados servicosRealizados){
        this.em.persist(servicosRealizados);
    }

    public void excluir(ServicosRealizados servicosRealizados){
        this.em.merge(servicosRealizados); // Garante que o objeto está sendo gerenciado pelo JPA
        this.em.remove(servicosRealizados); // Deleta o objeto do banco de dados
    }

    public void atualizar(ServicosRealizados servicosRealizados){
        // Merge: Caso encontre um registro na tabela `servicosrealizados` com a mesma chave primária do objeto, irá atualizá-lo com os novos atributos
        // Caso não encontre, irá tentar criar um novo registro com base no objeto passado.
        this.em.merge(servicosRealizados);
    }

    public ServicosRealizados findById(Long id){
        return this.em.find(ServicosRealizados.class, id); // Busca um objeto da classe referenciada, utilizando o ID
    }

    public List<ServicosRealizados> findAll(){
        String jpql = "SELECT p FROM ServicosRealizados p";
        return em.createQuery(jpql, ServicosRealizados.class).getResultList();
    }

    public List<ServicosRealizados> buscaServicosPorDataEAnimal(Calendar data, Long animalid){ // Irá retornar os serviços ja realizados no dia
        String jpql = "SELECT sr FROM ServicosRealizados sr WHERE DATE(sr.datahora) = :data  AND sr.animal.animalid = :id";
        return em.createQuery(jpql, ServicosRealizados.class)
                .setParameter("data", data, TemporalType.DATE)
                .setParameter("id", animalid).getResultList();
    }

    public List<Servico> buscaServicosNaoRealizadosNoDia(Calendar data, Long animalid){
        ArrayList<Servico> servicosNaoRealizados = new ArrayList<>(List.of(Servico.values())); // Alimenta a lista de serviços disponíveis
        List<ServicosRealizados> servicosRealizados = buscaServicosPorDataEAnimal(data, animalid); // Busca serviços já realizados no dia para o animal
        List<Servico> servicosJaRealizados = new ArrayList<Servico>();

        if (servicosRealizados == null || servicosRealizados.isEmpty()){ // Verifica se nenhum serviço foi realizado no dia
            return servicosNaoRealizados; // Se nenhum serviço foi realizado, retorna a lista cheia de serviços disponíveis
        } else { // Se não, filtra os serviços não realizados com base nos serviços já realizados no dia para o animal
            for (ServicosRealizados servicos : servicosRealizados) {
                servicosJaRealizados.add(servicos.getServico());
            }
            servicosNaoRealizados.removeIf(servico -> servicosJaRealizados.contains(servico)); // Remove da lista os serviços que já foram realizados no dia
        }
        return servicosNaoRealizados;
    }
}
