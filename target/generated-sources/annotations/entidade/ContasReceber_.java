package entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasReceber.class)
public abstract class ContasReceber_ {

	public static volatile SingularAttribute<ContasReceber, Cliente> cliente;
	public static volatile SingularAttribute<ContasReceber, Venda> venda;
	public static volatile ListAttribute<ContasReceber, BaixaContasReceber> baixaContasRecebers;
	public static volatile SingularAttribute<ContasReceber, Date> dataVencimento;
	public static volatile SingularAttribute<ContasReceber, String> formapag;
	public static volatile SingularAttribute<ContasReceber, Long> id;
	public static volatile SingularAttribute<ContasReceber, Double> Valor;
	public static volatile SingularAttribute<ContasReceber, String> parcela;
	public static volatile SingularAttribute<ContasReceber, Date> dataLancamento;
	public static volatile SingularAttribute<ContasReceber, String> descricao;

}

