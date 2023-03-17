package utfpr.aulajpa.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import utfpr.aulajpa.model.Disciplina;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-16T23:34:43", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Aluno.class)
public class Aluno_ { 

    public static volatile SingularAttribute<Aluno, Disciplina> disciplina;
    public static volatile SingularAttribute<Aluno, String> nome;
    public static volatile SingularAttribute<Aluno, Long> id;
    public static volatile SingularAttribute<Aluno, Double> media;
    public static volatile SingularAttribute<Aluno, Integer> RA;

}