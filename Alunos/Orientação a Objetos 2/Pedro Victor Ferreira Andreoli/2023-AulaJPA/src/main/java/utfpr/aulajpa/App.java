package utfpr.aulajpa;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import static org.hibernate.internal.util.collections.CollectionHelper.isEmpty;
import utfpr.aulajpa.dao.AlunoDAO;
import utfpr.aulajpa.dao.DisciplinaDAO;
import utfpr.aulajpa.model.Aluno;
import utfpr.aulajpa.model.Disciplina;
import utfpr.aulajpa.util.Factory;

public class App {

    public static void main(String[] args) {
       
        //Como possuo um factory posso instanciar quantos managers forem precisos
        EntityManager em = Factory.getEntityManager();
        
        AlunoDAO alunoDAO = new AlunoDAO(em);
        DisciplinaDAO disDAO = new DisciplinaDAO(em);
        int res = 0;
        String disciplina;
        int numAluno;
       int numero;
        String nome;
        int RA;
        double media;
        Long numDisciplina;
        int cont; 
        System.out.println("Qual a funçao 1- cadastrar um aluno "
                + "2- cadastrar uma disciplina via console\n" +
"\n" +
"3- excluir um aluno "
                + "4- Excluir disciplina\n" +
"\n" +
"5- consultar os alunos cadastrados na disciplina\n" +
"\n" +
"6- consultar os alunos reprovado (media<6)");
        Scanner sc = new Scanner(System.in);
       numero = sc.nextInt();
        
        switch (numero){
            case 1:
        while(res!=1)
        {
            System.out.println("Qual o nome da disciplina");
            disciplina = sc.nextLine();
            System.out.println("Qual o numero de alunos");
            numAluno = sc.nextInt();
            Disciplina dis = new Disciplina(disciplina, numAluno);
            disDAO.salvar(dis);
            em.getTransaction().commit();
            System.out.println("Se deseja parar de inserir disciplinas insira 1");
            res = sc.nextInt();
        }
        break;
        case 2:
        res = 0;
        
        if(!isEmpty(disDAO.buscaTodos()))
        {
        while(res!=1)
        {
            System.out.println("Qual o nome do aluno");
            nome = sc.nextLine();
            System.out.println("Qual o RA do aluno");
            RA = sc.nextInt();
            System.out.println("Qual a media do aluno");
            media = sc.nextDouble();
            System.out.println("Selecione a disciplina");
            disDAO.buscaTodos().forEach(a -> {
                
                System.out.println("Selecione a disciplina "+a.getId()); 
            });
            numDisciplina = sc.nextLong();;
            Aluno aluno = new Aluno(nome,RA, media, disDAO.buscaDisciplina(numDisciplina));
            alunoDAO.salvar(aluno);
            em.getTransaction().commit();
            System.out.println("Se deseja parar de inserir alunos insira 1");
            res = sc.nextInt();
        }
        }
        else
            System.out.println("Não há disciplinas cadastradas");
        em.getTransaction().begin();
        break;
        case 3:
        res = 0;
        while(res!=1)
        {
            disDAO.buscaTodos().forEach(a -> {
                
                System.out.println("Selecione a disciplina "+a.getId()); 
            });
            numDisciplina = sc.nextLong();
            disDAO.excluir(disDAO.buscaDisciplina(numDisciplina)); 
            em.getTransaction().commit();
            System.out.println("Se deseja parar de excluir insira 1");
            res = sc.nextInt();
        }
        //Instancia para managed
         res = 0;
        while(res!=1)
        {
            alunoDAO.buscaTodos().forEach(a -> {
                
                System.out.println("Selecione o aluno "+a.getId()); 
            });
            numDisciplina = sc.nextLong();
            alunoDAO.excluir(alunoDAO.buscaAluno(numDisciplina)); 
            em.getTransaction().commit();
            System.out.println("Se deseja parar de excluir insira 1");
            res = sc.nextInt();
        }
        break;
        case 4:
        res = 0;
        while(res!=1)
        {
            disDAO.buscaTodos().forEach(a -> {
                
                System.out.println("Selecione a disciplina "+a.getId()); 
            });
            numDisciplina = sc.nextLong();
            alunoDAO.buscaAluno(numDisciplina);
            em.getTransaction().commit();
            System.out.println("Se deseja parar de excluir insira 1");
            res = sc.nextInt();
        }
        break;
        case 5:
        res = 0;
        while(res!=1)
        {
            disDAO.buscaTodos().forEach(a -> {
                
                System.out.println("Selecione a disciplina "+a.getId()); 
            });
            numDisciplina = sc.nextLong();
            alunoDAO.buscaAluno(numDisciplina);
            em.getTransaction().commit();
            System.out.println("Se deseja parar de buscar insira 1");
            res = sc.nextInt();
        }
        break;
        case 6:
        res = 0;
        while(res!=1)
        {
            System.out.println("Alunos reprovados ");
            alunoDAO.retornaAlunoReprovado().forEach(a -> {
                System.out.println(a.getNome());
            });
            
        }
    }
        em.close();
    }
}
