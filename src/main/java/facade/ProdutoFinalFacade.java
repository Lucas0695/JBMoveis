/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.ProdutoFinal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lucas
 */
@Stateless
public class ProdutoFinalFacade extends AbstractFacade<ProdutoFinal> {

    @PersistenceContext(unitName = "jbmoveisPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFinalFacade() {
        super(ProdutoFinal.class);
    }

}
