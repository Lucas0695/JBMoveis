package entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Fabricacao.class)
public abstract class Fabricacao_ {

	public static volatile SingularAttribute<Fabricacao, Double> valorfinal;
	public static volatile SingularAttribute<Fabricacao, String> situacao;
	public static volatile SingularAttribute<Fabricacao, Double> percentual;
	public static volatile SingularAttribute<Fabricacao, Double> quantidadeProduto;
	public static volatile SingularAttribute<Fabricacao, Date> dataFabricacao;
	public static volatile SingularAttribute<Fabricacao, Double> valorTotal;
	public static volatile SingularAttribute<Fabricacao, ProdutoFinal> produtoFinal;
	public static volatile SingularAttribute<Fabricacao, Long> id;
	public static volatile ListAttribute<Fabricacao, ItensFabricacao> itensFabricacao;

}

