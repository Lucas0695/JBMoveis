package entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProdutoFinal.class)
public abstract class ProdutoFinal_ {

	public static volatile SingularAttribute<ProdutoFinal, Double> preco;
	public static volatile SingularAttribute<ProdutoFinal, Double> estoque;
	public static volatile SingularAttribute<ProdutoFinal, String> nome;
	public static volatile SingularAttribute<ProdutoFinal, Long> id;
	public static volatile SingularAttribute<ProdutoFinal, GrupoProdutoFinal> grupoProdutoFinal;

}

