/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utfpr.trabalho.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import utfpr.trabalho.model.Servico;
import java.util.Collection;
import org.eclipse.persistence.expressions.ExpressionOperator;

/**
 *
 * @author Pedro
 */
public class ServicoDAO extends CRUD<Servico>{

    public ServicoDAO(EntityManager em) {
        super(em, Servico.class);
    }
    
    public void listarTarefasNaoFeitas(){
        buscaTodos().forEach(a-> {
            if(!a.isStatus())
            {
                System.out.println(a);
            }
        });
    };
    
    public List<Servico> listaNaoFeitas(){
        List<Servico> lista = new ArrayList<Servico>();
        buscaTodos().forEach(a -> {
            if(!a.isStatus())
                lista.add(a);
        });
        return lista;
        //return  buscaTodos().stream().filter(predicate-> predicate.isStatus() == false).collect(collector->);
    }
    
    
    public void registrarServicoMassa(){
        LocalDateTime localdate = LocalDateTime.now();
        List<Servico> servicosregistrados = new ArrayList<Servico>();
        if(!listaNaoFeitas().isEmpty())
        {
            listaNaoFeitas().forEach(a ->
            {
                if(localdate.isAfter(a.getData()) )
                {
                    servicosregistrados.add(a);
                }
                servicosregistrados.forEach(b->{
                    a.setStatus(true);
                });
            }
            );
            if(!servicosregistrados.isEmpty())
                servicosregistrados.forEach(a ->{
                    atualizar(a);
                });
        }
        else
             System.out.println("Nao existe servicos pendentes");
    }
    
    
     public void listarFuncionariosAnimais()
    {   
        buscaTodos().forEach(a ->{
            System.out.println("o animal "+a.getAnimal().getNome()+" possui o seguinte funcionario "+
                    a.getFuncionario().getNome() +" do tipo "+a.getFuncionario().getTipo().getNome());
        });   
    }
  
}
