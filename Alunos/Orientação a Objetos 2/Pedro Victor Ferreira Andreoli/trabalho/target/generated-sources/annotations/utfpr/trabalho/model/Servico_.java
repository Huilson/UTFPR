package utfpr.trabalho.model;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import utfpr.trabalho.model.Animal;
import utfpr.trabalho.model.Funcionario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-29T20:20:41", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Servico.class)
public class Servico_ { 

    public static volatile SingularAttribute<Servico, LocalDateTime> data;
    public static volatile SingularAttribute<Servico, Animal> Animal;
    public static volatile SingularAttribute<Servico, Long> id;
    public static volatile SingularAttribute<Servico, Funcionario> funcionario;
    public static volatile SingularAttribute<Servico, String> descricao;
    public static volatile SingularAttribute<Servico, Boolean> status;

}