package entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItensVenda.class)
public abstract class ItensVenda_ {

	public static volatile SingularAttribute<ItensVenda, Double> preco;
	public static volatile SingularAttribute<ItensVenda, Venda> venda;
	public static volatile SingularAttribute<ItensVenda, ProdutoFinal> produtoFinal;
	public static volatile SingularAttribute<ItensVenda, Long> id;
	public static volatile SingularAttribute<ItensVenda, Double> quantidade;

}

