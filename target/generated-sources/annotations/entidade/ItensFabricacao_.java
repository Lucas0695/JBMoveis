package entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItensFabricacao.class)
public abstract class ItensFabricacao_ {

	public static volatile SingularAttribute<ItensFabricacao, Double> preco;
	public static volatile SingularAttribute<ItensFabricacao, Long> id;
	public static volatile SingularAttribute<ItensFabricacao, Fabricacao> fabricacao;
	public static volatile SingularAttribute<ItensFabricacao, Double> quantidade;
	public static volatile SingularAttribute<ItensFabricacao, MateriaPrima> materiaPrima;

}

