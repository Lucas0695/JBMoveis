package entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Compra.class)
public abstract class Compra_ {

	public static volatile ListAttribute<Compra, ContasPagar> contasPagars;
	public static volatile SingularAttribute<Compra, Double> valorTotal;
	public static volatile SingularAttribute<Compra, String> formapag;
	public static volatile SingularAttribute<Compra, Long> id;
	public static volatile SingularAttribute<Compra, Fornecedor> fornecedor;
	public static volatile ListAttribute<Compra, ItensCompra> itensCompra;
	public static volatile SingularAttribute<Compra, Date> dataCompra;
	public static volatile SingularAttribute<Compra, String> descricao;

}

