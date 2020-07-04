package entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MateriaPrima.class)
public abstract class MateriaPrima_ {

	public static volatile SingularAttribute<MateriaPrima, Double> preco;
	public static volatile SingularAttribute<MateriaPrima, Double> estoque;
	public static volatile SingularAttribute<MateriaPrima, String> nome;
	public static volatile SingularAttribute<MateriaPrima, Long> id;
	public static volatile SingularAttribute<MateriaPrima, GrupoMateriaPrima> grupoMateriaPrima;

}

