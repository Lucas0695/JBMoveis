/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.ContasReceber;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.ContasView;

/**
 *
 * @author lucas
 */
@Stateless
public class ContasReceberFacade extends AbstractFacade<ContasReceber> {

    @PersistenceContext(unitName = "jbmoveisPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ContasReceberFacade() {
        super(ContasReceber.class);
    }
     public List<ContasView> listaCRAgrupado(){
        Query q=getEntityManager().createNativeQuery("select extract(month from datavencimento) mes, sum(valor) valor from contasreceber "
                + "group by extract(month from datavencimento) order by extract(month from datavencimento)", ContasView.class);
        return q.getResultList();
    }


}
